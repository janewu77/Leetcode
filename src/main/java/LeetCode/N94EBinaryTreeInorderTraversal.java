package LeetCode;

import ADraft.TreeDemo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 *

 * Example 1:
 * Input: root = [1,null,2,3]
 * Output: [1,3,2]
 *
 * Example 2:
 * Input: root = []
 * Output: []
 *
 * Example 3:
 * Input: root = [1]
 * Output: [1]
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 */

/**
 * E - 耗时 10-
 *  - 有三种解法。其中的mirror不熟。
 *
 */
public class N94EBinaryTreeInorderTraversal {

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Inorder Traversal.
    //Memory Usage: 42 MB, less than 64.97% of Java online submissions for Binary Tree Inorder Traversal.
    //mirror
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode node = root;
        while (node != null){
            while (node.left != null) {
                TreeNode left = node.left;
                TreeNode rightMost = left;
                while (rightMost.right != null) rightMost = rightMost.right;
                rightMost.right = node;
                node.left = null;
                node = left;
            }
            result.add(node.val);
            node = node.right;
        }
        return result;
    }

    //Runtime: 1 ms, faster than 49.02% of Java online submissions for Binary Tree Inorder Traversal.
    //Memory Usage: 43 MB, less than 5.03% of Java online submissions for Binary Tree Inorder Traversal.
    //statck
    public List<Integer> inorderTraversal_stack(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()){
            while(node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            result.add(node.val);
            node = node.right;
        }
        return result;
    }


    //Runtime: 1 ms, faster than 49.02% of Java online submissions for Binary Tree Inorder Traversal.
    //Memory Usage: 41.7 MB, less than 79.58% of Java online submissions for Binary Tree Inorder Traversal.
    //Recursive
    public List<Integer> inorderTraversal_recursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        inorderHelper(root , result);
        return result;
    }

    private void inorderHelper(TreeNode root, List<Integer> result){
        if (root.left != null) inorderHelper(root.left, result);
        result.add(root.val);
        if (root.right != null) inorderHelper(root.right, result);
    }

}
