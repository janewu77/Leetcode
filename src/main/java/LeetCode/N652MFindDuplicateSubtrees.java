package LeetCode;


import ADraft.TreeDemo.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given the root of a binary tree, return all duplicate subtrees.
 *
 * For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 *
 * Two trees are duplicate if they have the same structure with the same node values.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,null,2,4,null,null,4]
 * Output: [[2,4],[4]]
 * Example 2:
 *
 *
 * Input: root = [2,1,1]
 * Output: [[1]]
 * Example 3:
 *
 *
 * Input: root = [2,2,2,3,null,3,null]
 * Output: [[2,3],[3]]
 * Constraints:
 *
 * The number of the nodes in the tree will be in the range [1, 5000]
 * -200 <= Node.val <= 200
 */
public class N652MFindDuplicateSubtrees {


    //1.DFS
    //Runtime: 9ms 98%; Memory: 43.3MB 89%
    //Time:O(N + logN * N); Space: O(N)
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Set<TreeNode> res = new HashSet<>();
        List<TreeNode>[] data = new List[5000];
        data[1] = new ArrayList<>();

        helper(root, data);
        for (int i = 1; i < data.length; i++) {
            List<TreeNode> list = data[i];
            if (list == null) continue;

            Set<Integer> seen = new HashSet<>();
            for (int j = 0; j < list.size(); j++) {
                if (seen.contains(j)) continue;
                for (int k = j + 1; k < list.size(); k++) {
                    if (seen.contains(k)) continue;
                    if (isSame(list.get(j), list.get(k))) {
                        res.add(list.get(j));
                        seen.add(j); seen.add(k);
                    }
                }
            }
        }

        List<TreeNode> resSet = new ArrayList<>(res);
        return resSet;
    }

    private boolean isSame(TreeNode node1, TreeNode node2){
        if (node1 == null && node2 == null) return true;
        if (node1 == null || node2 == null) return false;
        return node1.val == node2.val && isSame(node1.left, node2.left) && isSame(node1.right, node2.right);
    }

    //DFS: Time: O(N); Space: O(N)
    private int helper(TreeNode root, List<TreeNode>[] data){
        if (root == null) return 0;
        if (root.left == null && root.right == null) {
            data[1].add(root);
            return 1;
        }

        int n = 1 + helper(root.left, data) + helper(root.right, data);
        if (data[n] == null) data[n] = new ArrayList<>();
        data[n].add(root);
        return n;
    }



    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
}
