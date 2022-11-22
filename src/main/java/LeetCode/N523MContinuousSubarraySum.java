package LeetCode;


import java.util.HashMap;
import java.util.Map;

import static java.time.LocalTime.now;

/**
 * Given an integer array nums and an integer k, return true if nums has a continuous subarray of
 * size at least two whose elements sum up to a multiple of k, or false otherwise.
 *
 * An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [23,2,4,6,7], k = 6
 * Output: true
 * Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.
 * Example 2:
 *
 * Input: nums = [23,2,6,4,7], k = 6
 * Output: true
 * Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
 * 42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.
 * Example 3:
 *
 * Input: nums = [23,2,6,4,7], k = 13
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 109
 * 0 <= sum(nums[i]) <= 231 - 1
 * 1 <= k <= 231 - 1
 */
public class N523MContinuousSubarraySum {


    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun(false, new int[]{1,0}, 2);

        doRun(true, new int[]{2,5,2,5,7}, 6);

        doRun(true, new int[]{23,2,4,6,7}, 6);
        doRun(true, new int[]{23,2,6,4,7}, 6);
        doRun(false, new int[]{23,2,6,4,7}, 13);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(boolean expect, int[] nums, int k) {
        boolean res = new N523MContinuousSubarraySum().checkSubarraySum(nums, k);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //3.prefix sum + one pass
    //pref[right] − pref[left]) % k = 0
    // ==> pref[right] % k = pref[left] % k
    //Runtime: 19 ms, faster than 98.91% of Java online submissions for Continuous Subarray Sum.
    //Memory Usage: 59.9 MB, less than 92.49% of Java online submissions for Continuous Subarray Sum.
    //Time: O(N); Space:O(min(N,K));
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            int remainder = sum % k;
            if (i > 0 && remainder == 0) return true;

            if (map.containsKey(remainder)){
                //i - 2 : at least two
                if (map.get(remainder) <= i - 2 ) return true;
            }else{
                map.put(remainder, i);
            }
        }
        return false;
    }


    //2.prefix sum
    //Runtime: 3 ms, faster than 100.00% of Java online submissions for Continuous Subarray Sum.
    //Memory Usage: 54.6 MB, less than 99.78% of Java online submissions for Continuous Subarray Sum.
    //Time: O(N * N); Space:O(1);
    public boolean checkSubarraySum_2(int[] nums, int k) {
        int lastNum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (lastNum == 0 && nums[i] == 0) return true;

            lastNum = nums[i];
            nums[i] += nums[i - 1];
            if (nums[i] % k == 0) return true;

            if (nums[i] < k) continue;
            for (int j = i - 2; j >= 0; j--)
                if ((nums[i] - nums[j]) % k == 0) return true;
        }
        return false;
    }

    //1.brute force
    //Time: O(N * N); Space:O(1);
    public boolean checkSubarraySum_1(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum % k == 0) return true;
            }
        }
        return false;
    }

}
