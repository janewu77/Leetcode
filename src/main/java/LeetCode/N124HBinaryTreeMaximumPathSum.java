package LeetCode;

import ADraft.TreeDemo.TreeNode;

/**
 *
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence
 * has an edge connecting them. A node can only appear in the sequence at most once.
 * Note that the path does not need to pass through the root.
 *
 * The path sum of a path is the sum of the node's values in the path.
 *
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3]
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 * Example 2:
 *
 *
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 3 * 104].
 * -1000 <= Node.val <= 1000
 */

/**
 * H - [time: 45-
 */
public class N124HBinaryTreeMaximumPathSum {

    //Runtime: 1 ms, faster than 99.73% of Java online submissions for Binary Tree Maximum Path Sum.
    //Memory Usage: 43.9 MB, less than 94.03% of Java online submissions for Binary Tree Maximum Path Sum.
    //Time: O(N); Space: worst case:(N)
    public int maxPathSum(TreeNode root) {
        helper(root);
        return res;
    }

    int res = -1001;
    private int helper(TreeNode node){
        if (node == null) return 0;
        int leftSum = Math.max(helper(node.left), 0);
        int rightSum = Math.max(helper(node.right), 0);
        res = Math.max(res, node.val + leftSum + rightSum);
        return node.val + Math.max(leftSum, rightSum);
    }

}
