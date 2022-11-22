package LeetCode;

import ADraft.TreeDemo.*;

/**
 * Given an integer array nums where the elements are sorted in ascending order,
 * convert it to a height-balanced binary search tree.
 *
 * A height-balanced binary tree is a binary tree in which the depth of the two
 * subtrees of every node never differs by more than one.
 *
 *
 *
 * Example 1:
 * Input: nums = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * Explanation: [0,-10,5,null,-3,null,9] is also accepted:
 *
 * Example 2:
 *
 *
 * Input: nums = [1,3]
 * Output: [3,1]
 * Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums is sorted in a strictly increasing order.
 */

/**
 * E - [time: 10-
 *
 */
public class N108EConvertSortedArraytoBinarySearchTree {

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Convert Sorted Array to Binary Search Tree.
    //Memory Usage: 43.8 MB, less than 47.11% of Java online submissions for Convert Sorted Array to Binary Search Tree.
    //recursive solution
    //DFS: pre-order
    //Time: O(N) ; Space:  O(logN)
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper_binaryBuilder(nums,0, nums.length-1) ;
    }

    private TreeNode helper_binaryBuilder(int[] nums, int left, int right){
        if (left > right) return null;
        int m = (right + left) / 2;
        TreeNode root = new TreeNode(nums[m]);
        root.left = helper_binaryBuilder(nums, left, m - 1);
        root.right = helper_binaryBuilder(nums, m + 1, right);
        return root;
    }
}
