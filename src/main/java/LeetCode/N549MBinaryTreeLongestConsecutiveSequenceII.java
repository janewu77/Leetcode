package LeetCode;

import ADraft.TreeDemo.TreeNode;


/**
 * Given the root of a binary tree, return the length of the longest consecutive path in the tree.
 *
 * A consecutive path is a path where the values of the consecutive nodes in the path differ by one. This path can be either increasing or decreasing.
 *
 * For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid.
 * On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3]
 * Output: 2
 * Explanation: The longest consecutive path is [1, 2] or [2, 1].
 * Example 2:
 *
 *
 * Input: root = [2,1,3]
 * Output: 3
 * Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 3 * 104].
 * -3 * 104 <= Node.val <= 3 * 104
 */

/**
 * M - [time: 120+ 没有马上做，跨天了。。。
 *
 */
public class N549MBinaryTreeLongestConsecutiveSequenceII {


    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Binary Tree Longest Consecutive Sequence II.
    //Memory Usage: 42.3 MB, less than 89.22% of Java online submissions for Binary Tree Longest Consecutive Sequence II.
    //DFS: recursion
    //Time:O(N); Space: O(N)
    private int maxLen = 0;
    public int longestConsecutive(TreeNode root) {
        maxLen = 0;
        helper_dfs(root, null, new int[]{1,1});
        return maxLen;
    }

    public void helper_dfs(TreeNode child, TreeNode parent, int[] parentLens) {
        if (child == null) return;

        int[] childLen = new int[]{1,1}; //[0]: p > c decreasing ; [1] c > p increasing
        helper_dfs(child.left, child, childLen);
        helper_dfs(child.right, child, childLen);

        if (parent != null) {
            if (child.val + 1 == parent.val) parentLens[0] = Math.max(parentLens[0], childLen[0] + 1);
            else if (child.val == parent.val + 1) parentLens[1] = Math.max(parentLens[1], childLen[1] + 1);
        }
        maxLen = Math.max(maxLen, parentLens[0] + parentLens[1] - 1);
    }


}
