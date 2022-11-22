package LeetCode;

import java.util.Arrays;

import static java.time.LocalTime.now;

/**
 * You are given a 0-indexed integer array nums. For each index i (1 <= i <= nums.length - 2) the beauty of nums[i] equals:
 *
 * 2, if nums[j] < nums[i] < nums[k], for all 0 <= j < i and for all i < k <= nums.length - 1.
 * 1, if nums[i - 1] < nums[i] < nums[i + 1], and the previous condition is not satisfied.
 * 0, if none of the previous conditions holds.
 * Return the sum of beauty of all nums[i] where 1 <= i <= nums.length - 2.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: 2
 * Explanation: For each index i in the range 1 <= i <= 1:
 * - The beauty of nums[1] equals 2.
 * Example 2:
 *
 * Input: nums = [2,4,6,4]
 * Output: 1
 * Explanation: For each index i in the range 1 <= i <= 2:
 * - The beauty of nums[1] equals 1.
 * - The beauty of nums[2] equals 0.
 * Example 3:
 *
 * Input: nums = [3,2,1]
 * Output: 0
 * Explanation: For each index i in the range 1 <= i <= 1:
 * - The beauty of nums[1] equals 0.
 */

/**
 * M - [time: 30-
 */
public class N2012MSumofBeautyintheArray {

    public static void main(String[] args){
        System.out.println(now());
        int[] data;

        data = new int[]{2,4,6,4};
        doRun(1, data);

        data = new int[]{2,2,6};
        doRun(0, data);

        data = new int[]{1,2,3};
        doRun(2, data);

        data = new int[]{3,2,1};
        doRun(0, data);

        System.out.println(now());
        System.out.println("==================");
    }

    private static void doRun(int expected, int[] nums){
        int res = new N2012MSumofBeautyintheArray().sumOfBeauties(nums);
        System.out.println("["+(expected == res)+"].expected:"+ expected+".res:"+res);
    }


    //Runtime: 5 ms, faster than 100.00% of Java online submissions for Sum of Beauty in the Array.
    //Memory Usage: 50.3 MB, less than 96.32% of Java online submissions for Sum of Beauty in the Array.
    //Time: O(2N); Space: O(N)
    //Time: O(N); Space: O(N)
    public int sumOfBeauties(int[] nums) {
        int[] minInRight = new int[nums.length];
        minInRight[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 2; i--)
            minInRight[i] = Math.min(minInRight[i + 1], nums[i]);

        int maxInLeft = nums[0], sum = 0;
        for (int i = 1; i < nums.length - 1; i++){
            if (nums[i] > maxInLeft && nums[i] < minInRight[i + 1])  sum += 2;
            else if (nums[i] > nums[i - 1] && nums[i] < nums[i + 1]) sum += 1;

            maxInLeft = Math.max(maxInLeft, nums[i]);
        }
        return sum;
    }

    //Runtime: 13 ms, faster than 36.81% of Java online submissions for Sum of Beauty in the Array.
    //Memory Usage: 92.9 MB, less than 67.48% of Java online submissions for Sum of Beauty in the Array.
    //Time: O(2N); Space: O(2N)
    public int sumOfBeauties1(int[] nums) {
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];
        max[0] = nums[0];
        min[nums.length - 1] = nums[nums.length - 1];

        for (int i = 1; i < nums.length - 1; i++){
            max[i] =  Math.max(max[i - 1], nums[i]);
            int j = nums.length - 1 - i;
            min[j] = Math.min(min[j + 1], nums[j]);
        }

        int sum = 0;
        for (int i = 1; i < nums.length - 1; i++){
            if (nums[i] > max[i - 1] && nums[i] < min[i + 1])  sum += 2;
            else if (nums[i] > nums[i - 1] && nums[i] < nums[i + 1]) sum += 1;
        }
        return sum;
    }
}
