package LeetCode;

import ADraft.TreeDemo.*;

import java.util.*;

/**
 *
 * Given the root of a binary tree, return the same tree where every
 * subtree (of the given tree) not containing a 1 has been removed.
 *
 * A subtree of a node node is node plus every node that is a descendant of node.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,null,0,0,1]
 * Output: [1,null,0,null,1]
 * Explanation:
 * Only the red nodes satisfy the property "every subtree not containing a 1".
 * The diagram on the right represents the answer.
 * Example 2:
 *
 *
 * Input: root = [1,0,1,0,0,0,1]
 * Output: [1,null,1,null,1]
 * Example 3:
 *
 *
 * Input: root = [1,1,0,1,1,0,1,0]
 * Output: [1,1,0,1,1,null,1]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 200].
 * Node.val is either 0 or 1.
 */

/**
 * M - [time: 10-
 */
public class N814MBinaryTreePruning {


    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Pruning.
    //Memory Usage: 39.9 MB, less than 92.34% of Java online submissions for Binary Tree Pruning.
    //DFS + recursion
    //Time: O(N); Space: O(N)
    public TreeNode pruneTree(TreeNode node) {
        if (node == null) return null;

        node.left = pruneTree(node.left);
        node.right = pruneTree(node.right);

        return (node.val == 1 || node.left != null || node.right != null ) ? node : null;
    }


    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Binary Tree Pruning.
    //Memory Usage: 39.9 MB, less than 94.74% of Java online submissions for Binary Tree Pruning.
    //DFS + iteration
    //Time: O(N); Space: O(N)
    public TreeNode pruneTree_iteration(TreeNode root) {

        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.peek();

            if (node.left != null) {
                if (node.left.val < 0) node.left = null; //prune
                else if (Math.abs(node.left.val) < 10) stack.add(node.left);
            }
            if (node.right != null) {
                if (node.right.val < 0) node.right = null;
                else if (Math.abs(node.right.val) < 10) stack.add(node.right);
            }

            if ((node.left == null || (node.left != null && Math.abs(node.left.val) >= 10))
                    && (node.right == null || (node.right != null && Math.abs(node.right.val) >= 10))){

                stack.pop();
                node.val = (node.val == 1 || node.left != null || node.right != null) ? node.val + 10 : -10;
                //recover children's val
                if (node.left != null) node.left.val -= 10;
                if (node.right != null) node.right.val -= 10;
            }

        }
        if (root.val >= 10) root.val -= 10;
        return root.val < 0 ? null: root;
    }


}
