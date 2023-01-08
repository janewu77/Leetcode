package Contest;


import java.util.Arrays;

/**
 * 2529. Maximum Count of Positive Integer and Negative Integer
 * Easy
 * 14
 * 0
 * Given an array nums sorted in non-decreasing order, return the maximum between the number of positive integers and the number of negative integers.
 *
 * In other words, if the number of positive integers in nums is pos and the number of negative integers is neg, then return the maximum of pos and neg.
 * Note that 0 is neither positive nor negative.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-2,-1,-1,1,2,3]
 * Output: 3
 * Explanation: There are 3 positive integers and 3 negative integers. The maximum count among them is 3.
 * Example 2:
 *
 * Input: nums = [-3,-2,-1,0,0,1,2]
 * Output: 3
 * Explanation: There are 2 positive integers and 3 negative integers. The maximum count among them is 3.
 * Example 3:
 *
 * Input: nums = [5,20,66,1314]
 * Output: 4
 * Explanation: There are 4 positive integers and 0 negative integers. The maximum count among them is 4.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2000
 * -2000 <= nums[i] <= 2000
 * nums is sorted in a non-decreasing order.
 */
public class N2529EMaximumCountofPositiveIntegerandNegativeInteger {



    //3.Binary Search
    //Runtime: 0ms 100%; Memory:42.1MB 100%
    //Time: O(logN + L); Space: O(1)
    //let L be the number of zero
    public int maximumCount(int[] nums) {
        int idx = Arrays.binarySearch(nums, 0);
        if (idx < 0) {
            idx = -idx - 1;
            return Math.max(idx, nums.length - idx);
        }

        int nc = idx - 1, pc = idx;
        for (; nc >= 0 && nums[nc] >= 0; nc--);
        for (; pc < nums.length && nums[pc] == 0; pc++);

        return Math.max(nc, nums.length - pc);
    }


    //2.compare
    //Runtime: 0ms 100%; Memory:42.1MB 100%
    //Time: O(N); Space: O(1)
    public int maximumCount_2(int[] nums) {
        int nc = -1, pc = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > -1 && nc < 0)
                nc = i;
            if (nums[i] > 0) {
                pc = nums.length - i;
                break;
            }
        }
        return Math.max(pc, nc);
    }

    //1.brute force
    //Runtime: 1ms 57%; Memory:42.3MB 100%
    //Time: O(N); Space: O(1)
    public int maximumCount_1(int[] nums) {
        int nc = 0, pc = 0;
        for (int value : nums) {
            if (value > 0) pc++;
            if (value < 0) nc++;
        }
        return Math.max(pc, nc);
    }
}
