package LeetCode;


import ADraft.TreeDemo.*;

import java.util.*;

/**
 * Given a binary tree where node values are digits from 1 to 9. A path in the binary tree is said to be pseudo-palindromic if at least one permutation of the node values in the path is a palindrome.
 *
 * Return the number of pseudo-palindromic paths going from the root node to leaf nodes.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: root = [2,3,1,3,1,null,1]
 * Output: 2
 * Explanation: The figure above represents the given binary tree. There are three
 * paths going from the root node to leaf nodes: the red path [2,3,3], the green path [2,1,1],
 * and the path [2,3,1]. Among these paths only red path and green path are pseudo-palindromic
 * paths since the red path [2,3,3] can be rearranged in [3,2,3] (palindrome) and the green
 * path [2,1,1] can be rearranged in [1,2,1] (palindrome).
 * Example 2:
 *
 *
 *
 * Input: root = [2,1,1,1,3,null,null,null,null,null,1]
 * Output: 1
 * Explanation: The figure above represents the given binary tree. There are three paths
 * going from the root node to leaf nodes: the green path [2,1,1], the path [2,1,3,1],
 * and the path [2,1]. Among these paths only the green path is pseudo-palindromic since
 * [2,1,1] can be rearranged in [1,2,1] (palindrome).
 * Example 3:
 *
 * Input: root = [9]
 * Output: 1
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 105].
 * 1 <= Node.val <= 9
 */

/**
 * M - [time: 30-
 */
public class N1457MPseudoPalindromicPathsinaBinaryTree {


    //Runtime: 8 ms, faster than 99.71% of Java online submissions for Pseudo-Palindromic Paths in a Binary Tree.
    //Memory Usage: 67.6 MB, less than 99.42% of Java online submissions for Pseudo-Palindromic Paths in a Binary Tree.
    //DFS + Recursion + bit set
    //Time: O(N); Space:O(N)
    public int pseudoPalindromicPaths(TreeNode root) {
        return helper_dfs(root, 0);
    }

    private int helper_dfs(TreeNode node, int path){
        if (node == null) return 0;

        path = path ^ (1 << node.val);  //add to path

//        if (node.left == null && node.right == null
//                && (path == 0 || Integer.bitCount(path) <= 1))
        if (node.left == node.right)
            return (path & -path) == path ? 1: 0;

        return helper_dfs(node.left, path) + helper_dfs(node.right, path);
    }


    //Runtime: 46 ms, faster than 31.58% of Java online submissions for Pseudo-Palindromic Paths in a Binary Tree.
    //Memory Usage: 105.9 MB, less than 71.93% of Java online submissions for Pseudo-Palindromic Paths in a Binary Tree.
    //DFS + iteration
    //Time: O(N); Space:O(N)
    public int pseudoPalindromicPaths_1(TreeNode root) {
        if (root == null) return 0;

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.addLast(root);
        int[] path = new int[10];

        int res = 0;
        while (!stack.isEmpty()){
            TreeNode node = stack.peekLast();

            if (node.left == node.right) { // == null )
                //add to path
                if (path[node.val] == 1) path[node.val] = 0;
                else path[node.val] = 1;
                node.val += 100;//mark

                //check is pseudo-palindromic or not
                int sum = 0;
                for (int value : path) sum += value;
                if (sum <= 1) res++;
            }

            if (node.val > 100){ //marked

                stack.pollLast();
                if (node.val > 100) node.val -= 100; //remove the mark

                //remove from path
                if (path[node.val] == 1) path[node.val] = 0;
                else path[node.val] = 1;

            } else {
                //add to path
                if (path[node.val] == 1) path[node.val] = 0;
                else path[node.val] = 1;
                node.val += 100;//mark

                if (node.left != null) stack.addLast(node.left);
                if (node.right != null) stack.addLast(node.right);
            }
        }
        return res;
    }



}
