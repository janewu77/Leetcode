package LeetCode;

/**
 * Given an array of integers nums which is sorted in ascending order,
 * and an integer target, write a function to search target in nums.
 * If target exists, then return its index. Otherwise, return -1.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 * Example 2:
 *
 * Input: nums = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * -104 < nums[i], target < 104
 * All the integers in nums are unique.
 * nums is sorted in ascending order.
 *
 */

/**
 * 基本数据结构：二分搜索
 * 注意：边界的计算
 */
public class N704EBinarySearch {

    public static void main(String[] args) {

        System.out.println((new N704EBinarySearch()).search(new int[]{1, 3, 434, 4443} , 4443 ));
        System.out.println((new N704EBinarySearch()).search(new int[]{3} , 4443 ));
    }

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int n;
        while (left <= right) {
            n = (left + right) / 2;
            if((n < 0) || (n > nums.length))
                return -1;
            if (nums[n] == target)
                return n;
            if (nums[n] > target)
                right = n - 1;
            else
                left = n + 1;
        }
        return -1;
    }

}
