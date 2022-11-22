package LeetCode;

import ADraft.TreeDemo.*;
import java.util.LinkedList;
import java.util.Queue;


/**
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,2,3,4,4,3]
 * Output: true
 * Example 2:
 *
 *
 * Input: root = [1,2,2,null,3,null,3]
 * Output: false
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 1000].
 * -100 <= Node.val <= 100
 */

/**
 * E -[time: 45
 */
public class N101ESymmetricTree {

        //[1,2,2,3,4,4,3]
    //[1,2,2,2,null,2] [1,2,2,null,3,null,3]


    //2.recursion
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Symmetric Tree.
    //Memory Usage: 40.6 MB, less than 89.25% of Java online submissions for Symmetric Tree.
    //Time:O(N); Space:O(N)
    public boolean isSymmetric(TreeNode root) {
        return helper(root.left, root.right);
    }
    private boolean helper(TreeNode leftNode, TreeNode rightNode){
        if (leftNode == null && rightNode == null) return true;
        if (leftNode == null || rightNode == null) return false;
        return leftNode.val == rightNode.val
                && helper(leftNode.left, rightNode.right)
                && helper(rightNode.left, leftNode.right);
    }


    //1.iteration
    //Runtime: 1 ms, faster than 76.81% of Java online submissions for Symmetric Tree.
    //Memory Usage: 41.7 MB, less than 81.61% of Java online submissions for Symmetric Tree.
    //Time:O(N); Space:O(N)
    public boolean isSymmetric1(TreeNode root) {

        Queue<TreeNode> leftQueue = new LinkedList<>();
        Queue<TreeNode> rightQueue = new LinkedList<>();
        leftQueue.add(root);
        rightQueue.add(root);

        while (!leftQueue.isEmpty()) {
            TreeNode leftNode = leftQueue.poll();
            TreeNode rightNode = rightQueue.poll();
            if (leftNode == null && rightNode == null) continue;
            if (leftNode == null || rightNode == null
                    || leftNode.val != rightNode.val) return false;

            leftQueue.add(leftNode.left);
            rightQueue.add(rightNode.right);

            leftQueue.add(leftNode.right);
            rightQueue.add(rightNode.left);
        }
        return true;
    }


}
