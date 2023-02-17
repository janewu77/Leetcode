package LeetCode;

import ADraft.TreeDemo.TreeNode;

/**
 * Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [4,2,6,1,3]
 * Output: 1
 * Example 2:
 *
 *
 * Input: root = [1,0,48,null,null,12,49]
 * Output: 1
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 104].
 * 0 <= Node.val <= 105
 *
 *
 * Note: This question is the same as 783: https://leetcode.com/problems/minimum-distance-between-bst-nodes/
 */
//same as 783
public class N530EMinimumAbsoluteDifferenceinBST {

    //2.DFS
    //Runtime: 0ms 100%; Memory: 42.2MB 61%
    //Time: O(N); Space: (H)
    private int lastVal = -1;
    public int getMinimumDifference(TreeNode root) {
        int res = Integer.MAX_VALUE;
        if (root.left != null)
            res = Math.min(res, getMinimumDifference(root.left));
        if (lastVal != -1)
            res = Math.min(res, root.val - lastVal);
        lastVal = root.val;
        if (root.right != null)
            res = Math.min(res, getMinimumDifference(root.right));
        return res;
    }


    //1.DFS (in-order)
    //Runtime: 0ms 100%; Memory: 41.7MB 89%
    //Time: O(N); Space: (H)
    //let H be the tree's height
    public int getMinimumDifference_1(TreeNode root) {
        helper_dfs(root, -1);
        return minDistance;
    }

    private int minDistance = Integer.MAX_VALUE;
    private int helper_dfs(TreeNode root, int lastValue){
        if (root.left != null)
            lastValue = helper_dfs(root.left, lastValue);
        if (lastValue != -1)
            minDistance = Math.min(minDistance, root.val - lastValue);
        if (root.right != null)
            return helper_dfs(root.right, root.val);
        return root.val;
    }
}
