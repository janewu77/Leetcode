package LeetCode;


import ADraft.TreeDemo.*;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Given the root of a binary tree, return the length of the longest consecutive sequence path.
 *
 * The path refers to any sequence of nodes from some starting node to
 * any node in the tree along the parent-child connections. The longest consecutive path needs
 * to be from parent to child (cannot be the reverse).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,null,3,2,4,null,null,null,5]
 * Output: 3
 * Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
 * Example 2:
 *
 *
 * Input: root = [2,null,3,2,null,1]
 * Output: 2
 * Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 3 * 104].
 * -3 * 104 <= Node.val <= 3 * 104
 */

/**
 * M - [time: 30-
 *
 */
public class N298MBinaryTreeLongestConsecutiveSequence {


    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Binary Tree Longest Consecutive Sequence.
    //Memory Usage: 50.8 MB, less than 66.45% of Java online submissions for Binary Tree Longest Consecutive Sequence.
    //Depth First Search (DFS: bottom up)
    //Time: O(N); Space: O(N)
    private int maxLen = 0;
    public int longestConsecutive(TreeNode root) {
        helper_dfs_bottomup(root);
        return maxLen;
    }

    private int helper_dfs_bottomup(TreeNode node){
        if (node == null) return 0;

        int lenLeft = helper_dfs_bottomup(node.left) + 1;
        int lenRight = helper_dfs_bottomup(node.right) + 1;

        if (node.left != null && node.left.val != node.val + 1)
            lenLeft = 1;

        if (node.right != null && node.right.val != node.val + 1)
            lenRight = 1;

        int currLen = Math.max(lenLeft , lenRight);
        maxLen = Math.max(maxLen, currLen);
        return currLen;
    }


    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Binary Tree Longest Consecutive Sequence.
    //Memory Usage: 43.6 MB, less than 93.61% of Java online submissions for Binary Tree Longest Consecutive Sequence.
    //Depth First Search(DFS) : top down
    //Time: O(N); Space: O(N)
    public int longestConsecutive_2(TreeNode root) {
        return helper_dfs_topdown(root, null, 0);
    }

    private int helper_dfs_topdown(TreeNode node, TreeNode parent, int len){
        if (node == null) return len;
        len = (parent != null && node.val == parent.val + 1) ? len + 1: 1;
        return Math.max(len,
                Math.max(helper_dfs_topdown(node.left, node, len),
                        helper_dfs_topdown(node.right, node, len)));
    }


    //Runtime: 20 ms, faster than 5.95% of Java online submissions for Binary Tree Longest Consecutive Sequence.
    //Memory Usage: 54.2 MB, less than 5.09% of Java online submissions for Binary Tree Longest Consecutive Sequence.
    //BFS
    public int longestConsecutive_1(TreeNode root) {
        int res = 1;

        Queue<Pair<TreeNode,Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(root, 1));

        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> pair = queue.poll();
            TreeNode node = pair.getKey();
            if (node.left != null) {
                int newLen = node.left.val == node.val + 1 ? pair.getValue() + 1 : 1;
                res = Math.max(res, newLen);
                queue.add(new Pair<>(node.left, newLen));
            }

            if (node.right != null) {
                int newLen = node.right.val == node.val + 1 ? pair.getValue() + 1 : 1;
                res = Math.max(res, newLen);
                queue.add(new Pair<>(node.right, newLen));
            }
        }
        return res;
    }


}
