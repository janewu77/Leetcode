package LeetCode;

/**
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *
 * A valid BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 *
 * Example 1:
 * Input: root = [2,1,3]
 * Output: true
 *
 *
 * Example 2:
 * Input: root = [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -231 <= Node.val <= 231 - 1
 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * M: - 耗时60以内
 *  - 用左中右进行遍历。如果是顺序的，由是bst
 */
public class N98MValidateBST {

    /**
     *  [5,4,6,null,null,3,7]
     *  [3,1,5,0,2,4,6,null,null,null,3]
     *  [2,1,3]
     *  [-2147483648]
     *
     */
    private class TreeNode {
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


    Integer prev = null;
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;

        if (!isValidBST(root.left)) return false;

        if (prev != null && root.val <= prev) return false;
        prev = root.val;

        return isValidBST(root.right);
    }
}
