package LeetCode;


import ADraft.TreeDemo.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a Binary Search Tree (BST), return the minimum difference
 * between the values of any two different nodes in the tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [4,2,6,1,3]
 * Output: 1
 * Example 2:
 *
 *
 * Input: root = [1,0,48,null,null,12,49]
 * Output: 1
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 100].
 * 0 <= Node.val <= 105
 *
 *
 * Note: This question is the same as 530: https://leetcode.com/problems/minimum-absolute-difference-in-bst/
 */
public class N783EMinimumDistanceBetweenBSTNodes {

    //2.in-order Traversal + List
    //Time: O(N); Space: (N)
    //Runtime: 0ms 100%; Memory: 39.7MB 92%
    public int minDiffInBST_2(TreeNode root) {
        List<Integer> list = new ArrayList();
        helper_dfs(root, list);
        int res = list.get(list.size() - 1);
        for (int i = list.size() - 2; i >= 0; i--)
            res = Math.min(res, list.get(i + 1) -  list.get(i));
        return res;
    }

    private void helper_dfs(TreeNode root, List list){
        if (root == null) return;
        helper_dfs(root.left, list);
        list.add(root.val);
        helper_dfs(root.right, list);
    }

    //1.DFS (in-order)
    //Runtime: 0ms 100%; Memory: 39.7MB 92%
    //Time: O(N); Space: (H)
    //let H be the tree's height
    public int minDiffInBST(TreeNode root) {
        helper_dfs(root, -1);
        return minDistance;
    }

    private int minDistance = Integer.MAX_VALUE;
    private int helper_dfs(TreeNode root, int lastValue){
        if (root.left != null)
            lastValue = helper_dfs(root.left, lastValue);
        if (lastValue != -1)
            minDistance = Math.min(minDistance, root.val - lastValue);
        if (root.right != null)
            return helper_dfs(root.right, root.val);
        return root.val;
    }

}
