package LeetCode;

import java.util.Arrays;

/**
 *
 * Given an integer array nums, reorder it such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 * You may assume the input array always has a valid answer.
 *
 * Example 1:
 *
 * Input: nums = [3,5,2,1,6,4]
 * Output: [3,5,1,6,2,4]
 * Explanation: [1,6,2,5,3,4] is also accepted.
 *
 * Example 2:
 * Input: nums = [6,6,5,6,3,8]
 * Output: [6,6,5,6,3,8]
 *
 *
 * Constraints:
 * 1 <= nums.length <= 5 * 104
 * 0 <= nums[i] <= 104
 * It is guaranteed that there will be an answer for the given input nums.
 *
 * Follow up: Could you solve the problem in O(n) time complexity?
 *
 */

/**
 * M - 【time： 30-】
 *   - 小大小大。。。
 *
 */
public class N280MWiggleSort {


    public static void main(String[] a){
        int[] nums;

        nums = new int[]{3,5,2,1,6,4};
        doRun(nums);

        nums = new int[]{2,1};
        doRun(nums);

        nums = new int[]{1,2,3,4,5,6,7};
        doRun(nums);
        nums = new int[]{1,2,3,4,5,6,7,8};
        doRun(nums);

        nums = new int[]{8,7,6,5,4,3,2,1};
        doRun(nums);

        nums = new int[]{7,6,5,4,3,2,1};
        doRun(nums);
    }
    private static void doRun(int[] nums){
        //int[] nums = new int[]{3,5,2,1,6,4};
        new N280MWiggleSort().wiggleSort(nums);
        Arrays.stream(nums).forEach(System.out::print);
        System.out.println();
    }

    // 网上看来的。匹配条件写得很棒。但不易读。
    public void wiggleSort_x(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if ((i % 2 == 0) == (nums[i] > nums[i + 1])) {
                int t = nums[i];
                nums[i] = nums[i + 1];
                nums[i + 1] = t;
            }
        }
    }


    //逐位检查： 单小双大，不满足就互换
    //2.compare
    //Runtime: 0 ms, 100%; Memory: 42.5MB 98%
    //time: O(N); Space: O(1)
    public void wiggleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if ((i % 2 == 0 && nums[i] > nums[i + 1]) ||
                    (i % 2 == 1 && nums[i] < nums[i+1])) {
                int t = nums[i];
                nums[i] = nums[i + 1];
                nums[i + 1] = t;
            }
        }
    }

    //1.Sort
    //Runtime: 3 ms, 40.7%; Memory: 43.1MB 56%
    //time: O(NlogN); Space: O(logN)
    public void wiggleSort_1(int[] nums) {
        if (nums.length <= 1) return;

        Arrays.sort(nums);
        for (int i = 1; i < nums.length - 1; i += 2) {
            int t = nums[i];
            nums[i] = nums[i + 1];
            nums[i + 1] = t;
        }
    }


}
