package LeetCode;

import ADraft.TreeDemo.*;


/**
 * Given the root of a binary tree, return its maximum depth.
 *
 * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 * Example 2:
 *
 * Input: root = [1,null,2]
 * Output: 2
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 104].
 * -100 <= Node.val <= 100
 */

/**
 * E - [time: 3-
 */
public class N104EMaximumDepthofBinaryTree {


    //Runtime: 1 ms, faster than 24.55% of Java online submissions for Maximum Depth of Binary Tree.
    //Memory Usage: 43 MB, less than 60.78% of Java online submissions for Maximum Depth of Binary Tree.
    //Time: O(N); Space:O(N)
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left) + 1, maxDepth(root.right) +1);
    }


}
