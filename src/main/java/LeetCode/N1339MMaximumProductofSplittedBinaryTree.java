package LeetCode;

import ADraft.TreeDemo.*;

import java.util.*;

/**
 * Given the root of a binary tree, split the binary tree into two subtrees by removing one edge such that the product of the sums of the subtrees is maximized.
 *
 * Return the maximum product of the sums of the two subtrees. Since the answer may be too large, return it modulo 109 + 7.
 *
 * Note that you need to maximize the answer before taking the mod and not after taking it.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,5,6]
 * Output: 110
 * Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)
 * Example 2:
 *
 *
 * Input: root = [1,null,2,3,4,null,null,5,6]
 * Output: 90
 * Explanation: Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 5 * 104].
 * 1 <= Node.val <= 104
 */
public class N1339MMaximumProductofSplittedBinaryTree {


    //3.DFS + min diff
    //Runtime: 9 ms, 98.79%; 65.8 MB, 78.59%
    //Time: O(N); Space: O(N);
    public int maxProduct(TreeNode root) {
        int treeSum = helper_dfs_3(root);
        int[] res = new int[]{treeSum / 2, treeSum / 2, treeSum};  //treeSum, minDiff, subSum
        helper_dfs_mindiff(root, res);
        return (int) ((long)res[2] * (treeSum - res[2]) % 1_000_000_007);
    }

    private int helper_dfs_3(TreeNode node){
        if (node == null) return 0;
        node.val += helper_dfs_3(node.left) + helper_dfs_3(node.right);
        return node.val;
    }

    private void helper_dfs_mindiff(TreeNode node, int[] result){
        if (node == null) return;
        int diff = Math.abs(node.val - result[0]);
        if (diff < result[1]){
            result[1] = diff;
            result[2] = node.val;
        }
        helper_dfs_mindiff(node.left, result);
        helper_dfs_mindiff(node.right, result);
    }


    //2. two DFS
    //Runtime: 16 ms, 47.26%; 72.4 MB, 30.3%
    //Time: O(N); Space: O(N);
    Long maxProd = 0l;
    int treeSum = 0;
    public int maxProduct_2(TreeNode root) {
        treeSum = helper_dfs_2(root);
        helper_dfs_2(root);
        return (int)(maxProd % 1_000_000_007);
    }

    private int helper_dfs_2(TreeNode node){
        if (node == null) return 0;
        int sum = node.val + helper_dfs_2(node.left) + helper_dfs_2(node.right);
        if (treeSum != 0) maxProd = Math.max(maxProd, (long)sum * (treeSum - sum));
        return sum;
    }


    //1.DFS + Set
    //Runtime: 40 ms, 15.76%; 62.6 MB, 79.6%
    //Time: O(N); Space: O(N);
    public int maxProduct_1(TreeNode root) {
        Set<Integer> set = new HashSet<>();
        int sum = helper_dfs(root, set);
        long res = 0l;
        for (int value : set)
            res = Math.max(res, 1l * (sum - value) * value);
        return (int)(res % 1_000_000_007);
    }

    private int helper_dfs(TreeNode node, Set<Integer> set){
        if (node == null) return 0;
        int sum = node.val + helper_dfs(node.left, set) + helper_dfs(node.right, set);
        set.add(sum);
        return sum;
    }
}
