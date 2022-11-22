package LeetCode;


import ADraft.TreeDemo.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths
 * where the sum of the node values in the path equals targetSum. Each path should be returned as
 * a list of the node values, not node references.
 *
 * A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: [[5,4,11,2],[5,8,4,5]]
 * Explanation: There are two paths whose sum equals targetSum:
 * 5 + 4 + 11 + 2 = 22
 * 5 + 8 + 4 + 5 = 22
 * Example 2:
 *
 *
 * Input: root = [1,2,3], targetSum = 5
 * Output: []
 * Example 3:
 *
 * Input: root = [1,2], targetSum = 0
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 5000].
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 */

/**
 * M - [time: 20-
 */
public class N113MPathSumII {


    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Path Sum II.
    //Memory Usage: 42.5 MB, less than 95.44% of Java online submissions for Path Sum II.
    //DFS + recursion
    //Time: O(N*N); Space: O(N)
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        //Time: O(N*N); Space: O(N)
        helper_DFS(root, targetSum, res, 0, new ArrayList<>());
        return res;
    }

    private void helper_DFS(TreeNode node, int targetSum, List<List<Integer>> res, int currSum , List<Integer> list){
        if (currSum + node.val == targetSum
                && node.left == null && node.right == null) {
            List<Integer> tmp = new ArrayList<>(list); //Time: O(N); Space:O(N)
            tmp.add(node.val);
            res.add(tmp);
            return;
        }

        list.add(node.val);
        currSum += node.val;
        if (node.left != null) helper_DFS(node.left, targetSum, res, currSum, list);
        if (node.right != null) helper_DFS(node.right, targetSum, res, currSum, list);
        list.remove(list.size()-1);
    }
}
