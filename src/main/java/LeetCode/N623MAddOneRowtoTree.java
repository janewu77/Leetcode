package LeetCode;


import ADraft.TreeDemo;
import ADraft.TreeDemo.*;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a binary tree and two integers val and depth, add a row of nodes with value val at the given depth depth.
 *
 * Note that the root node is at depth 1.
 *
 * The adding rule is:
 *
 * Given the integer depth, for each not null tree node cur at the depth depth - 1, create two tree nodes with value val as cur's left subtree root and right subtree root.
 * cur's original left subtree should be the left subtree of the new left subtree root.
 * cur's original right subtree should be the right subtree of the new right subtree root.
 * If depth == 1 that means there is no depth depth - 1 at all, then create a tree node with value val as the new root of the whole original tree, and the original tree is the new root's left subtree.
 *
 *
 * Example 1:
 *
 *
 * Input: root = [4,2,6,3,1,5], val = 1, depth = 2
 * Output: [4,1,1,2,null,null,6,3,1,5]
 * Example 2:
 *
 *
 * Input: root = [4,2,null,3,1], val = 1, depth = 3
 * Output: [4,2,null,1,1,3,null,null,1]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * The depth of the tree is in the range [1, 104].
 * -100 <= Node.val <= 100
 * -105 <= val <= 105
 * 1 <= depth <= the depth of tree + 1
 */

/**
 *
 * M - [time: 25-
 */
public class N623MAddOneRowtoTree {

    //Runtime: 1 ms, faster than 88.29% of Java online submissions for Add One Row to Tree.
    //Memory Usage: 45 MB, less than 76.57% of Java online submissions for Add One Row to Tree.
    //DFS + recursion
    //Time: O(N); Space:O(N)
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (root == null) return null;
        if (depth == 1 || depth == 0){
            TreeNode node = new TreeNode(val);
            if (depth == 1) node.left = root;
            else node.right = root;
            return node;
        }

        root.left = addOneRow(root.left, val, depth == 2 ? 1 : depth - 1);
        root.right = addOneRow(root.right, val, depth == 2 ? 0 : depth - 1);
        return root;
    }

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Add One Row to Tree.
    //Memory Usage: 42 MB, less than 89.59% of Java online submissions for Add One Row to Tree.
    //BFS
    //worstcase: Time: O(N); Space:O(N/2)
    //Time: O(N); Space:O(N)
    public TreeNode addOneRow_1(TreeNode root, int val, int depth) {
        if (depth == 1){
            TreeNode node = new TreeNode(val);
            node.left = root;
            return node;
        }

        int layer = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (layer + 1 != depth) {
                    if (node.left != null) queue.add(node.left);
                    if (node.right != null) queue.add(node.right);
                } else {

                    TreeNode tmpL = new TreeNode(val);
                    if (node.left != null) tmpL.left = node.left;
                    node.left = tmpL;

                    TreeNode tmpR = new TreeNode(val);
                    if (node.right != null) tmpR.right = node.right;
                    node.right = tmpR;
                }
            }
            layer++;
        }
        return root;
    }
}
