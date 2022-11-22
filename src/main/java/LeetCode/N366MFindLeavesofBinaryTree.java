package LeetCode;

import java.util.*;

/***
 * Given the root of a binary tree, collect a tree's nodes as if you were doing this:
 *
 * Collect all the leaf nodes.
 * Remove all the leaf nodes.
 * Repeat until the tree is empty.
 *
 *
 * Example 1:
 * Input: root = [1,2,3,4,5]
 * Output: [[4,5,3],[2],[1]]
 * Explanation:
 * [[3,5,4],[2],[1]] and [[3,4,5],[2],[1]] are also considered correct answers
 * since per each level it does not matter the order on which elements are returned.
 *
 *
 * Example 2:
 * Input: root = [1]
 * Output: [[1]]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 100].
 * -100 <= Node.val <= 100
 *
 */

/**
 * M - [45+
 *
 */
public class N366MFindLeavesofBinaryTree {


    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Find Leaves of Binary Tree.
    //Memory Usage: 40.6 MB, less than 94.34% of Java online submissions for Find Leaves of Binary Tree.
    //Time: O(N), Space: O(1)
    private List<List<Integer>> res;
    public List<List<Integer>> findLeaves(TreeNode root) {
        res = new ArrayList<>();
        dfs(root);
        return res;
    }

    private int dfs(TreeNode node) {
        if (node == null) return -1;

        int layer = 1 + Math.max(dfs(node.left), dfs(node.right));

        if (res.size() <= layer) res.add(new ArrayList<>());
        res.get(layer).add(node.val);

        return layer;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////
    //Runtime: 1 ms, faster than 66.28% of Java online submissions for Find Leaves of Binary Tree.
    //Memory Usage: 42.6 MB, less than 45.87% of Java online submissions for Find Leaves of Binary Tree.
    //recursion, HashMap
    //Time: O(N), Space: O(N)
    Map<Integer, List<Integer>> data;
    public List<List<Integer>> findLeaves_map(TreeNode root) {
        data = new HashMap<>();

        findLeaves_help(root, 0);
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < data.size(); i++){
            res.add(data.get(i));
        }
        return res;
    }

    private int findLeaves_help(TreeNode node, int layer) {
        List<Integer> tmp = data.getOrDefault(layer, new ArrayList<>());
        data.put(layer, tmp);
        if (node.left == null && node.right == null) {
            tmp.add(node.val);
            return layer + 1;
        }

        int nextLayer = layer;
        if (node.left != null) {
            nextLayer = Math.max(nextLayer, findLeaves_help(node.left, layer));
        }

        if (node.right != null) {
            nextLayer = Math.max(nextLayer, findLeaves_help(node.right, layer));
        }

        tmp = data.getOrDefault(nextLayer, new ArrayList<>());
        data.put(nextLayer, tmp);
        tmp.add(node.val);
        return nextLayer + 1;
    }

    
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
