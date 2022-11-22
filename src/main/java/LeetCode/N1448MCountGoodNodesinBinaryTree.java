package LeetCode;


import ADraft.TreeDemo.*;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Given a binary tree root, a node X in the tree is named good if in the path
 * from root to X there are no nodes with a value greater than X.
 *
 * Return the number of good nodes in the binary tree.
 *
 *
 *
 * Example 1:
 * Input: root = [3,1,4,3,null,1,5]
 * Output: 4
 * Explanation: Nodes in blue are good.
 * Root Node (3) is always a good node.
 * Node 4 -> (3,4) is the maximum value in the path starting from the root.
 * Node 5 -> (3,4,5) is the maximum value in the path
 * Node 3 -> (3,1,3) is the maximum value in the path.
 *
 * Example 2:
 * Input: root = [3,3,null,4,2]
 * Output: 3
 * Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.
 * Example 3:
 *
 * Input: root = [1]
 * Output: 1
 * Explanation: Root is considered as good.
 *
 *
 * Constraints:
 *
 * The number of nodes in the binary tree is in the range [1, 10^5].
 * Each node's value is between [-10^4, 10^4].
 *
 */

/**
 * M - [time: 15-
 *
 *
 */
public class N1448MCountGoodNodesinBinaryTree {

    public static void main(String[] args) {
        String[] data;
        String expect;

        doMergeInt(1,1);
        doMergeInt(2,3);
        doMergeInt(-10_000,10_000);

    }
    static void doMergeInt(int value, int maxvalue){
        int Base = 10_000;
        int High = 100_000;
        int val = (maxvalue + Base) * High + (value) + Base;
        System.out.println(val);

        value = val % High - Base;
        maxvalue = val / High - Base;
        System.out.println(value);
        System.out.println(maxvalue);
        System.out.println("--------");
    }

    //Runtime: 2 ms, faster than 100.00% of Java online submissions for Count Good Nodes in Binary Tree.
    //Memory Usage: 59.1 MB, less than 81.81% of Java online submissions for Count Good Nodes in Binary Tree.
    //DFS + recursion
    //Time: O(N); Space:O(N)
    public int goodNodes3(TreeNode root) {
        //if (root == null) return 0;
        return helper_dfs_recursion(root, root.val);
    }

    private int helper_dfs_recursion(TreeNode node, int maxValue){
        if (node == null) return 0;
        maxValue = Math.max(node.val, maxValue);
        return (node.val >= maxValue ? 1 : 0)
                + helper_dfs_recursion(node.left, maxValue)
                + helper_dfs_recursion(node.right, maxValue);
    }


    //Runtime: 21 ms, faster than 7.23% of Java online submissions for Count Good Nodes in Binary Tree.
    //Memory Usage: 67.2 MB, less than 5.03% of Java online submissions for Count Good Nodes in Binary Tree.
    //BFS + iteration
    public int goodNodes_3(TreeNode root) {
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, root.val));

        int res = 0;
        while (!queue.isEmpty()){
            Pair<TreeNode, Integer> pair = queue.poll();
            TreeNode node = pair.getKey();
            res = node.val >= pair.getValue() ? res + 1 : res;

            if (node.left != null)
                queue.offer(new Pair<>(node.left, Math.max(node.left.val, pair.getValue())));

            if (node.right != null)
                queue.offer(new Pair<>(node.right, Math.max(node.right.val, pair.getValue())));
        }
        return res;
    }


    //Runtime: 15 ms, faster than 8.74% of Java online submissions for Count Good Nodes in Binary Tree.
    //Memory Usage: 64.2 MB, less than 5.83% of Java online submissions for Count Good Nodes in Binary Tree.
    // DFS + iteration (without pair) (modify node.val)
    // Time: O(N); Space:O(N)
    public int goodNodes(TreeNode root) {
        int Base = 10_000;
        int High = 100_000;
        Queue<TreeNode> queue = new LinkedList<>();
        int maxValue = root.val;
        root.val =  (maxValue + Base) * High + root.val + Base;
        queue.offer(root);

        int res = 0;
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            int value = node.val % High - Base;
            maxValue = node.val / High - Base;
            node.val = value;

            res = (value >= maxValue) ? res + 1 : res;
            maxValue = Math.max(value, maxValue);

            if (node.left != null) {
                node.left.val = (maxValue + Base) * High + node.left.val + Base;
                queue.offer(node.left);
            }

            if (node.right != null) {
                node.right.val = (maxValue + Base) * High + node.right.val + Base;
                queue.offer(node.right);
            }
        }
        return res;
    }

    //Runtime: 41 ms, faster than 5.01% of Java online submissions for Count Good Nodes in Binary Tree.
    //Memory Usage: 67.2 MB, less than 5.03% of Java online submissions for Count Good Nodes in Binary Tree.
    // DFS + iteration
    // Time: O(N); Space:O(N)
    public int goodNodes_2(TreeNode root) {
        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
        stack.add(new Pair<>(root, root.val));

        int res = 0;
        while (!stack.isEmpty()){
            Pair<TreeNode, Integer> pair = stack.pop();
            TreeNode node = pair.getKey();
            res = node.val >= pair.getValue() ? res + 1 : res;

            if (node.right != null)
                stack.add(new Pair<>(node.right, Math.max(node.right.val, pair.getValue())));

            if (node.left != null)
                stack.add(new Pair<>(node.left, Math.max(node.left.val, pair.getValue())));
        }
        return res;
    }

}
