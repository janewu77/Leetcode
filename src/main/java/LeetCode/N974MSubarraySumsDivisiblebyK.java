package LeetCode;


import static java.time.LocalTime.now;

/**
 * Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.
 *
 * A subarray is a contiguous part of an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,5,0,-2,-3,1], k = 5
 * Output: 7
 * Explanation: There are 7 subarrays with a sum divisible by k = 5:
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 * Example 2:
 *
 * Input: nums = [5], k = 9
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 3 * 104
 * -104 <= nums[i] <= 104
 * 2 <= k <= 104
 */
public class N974MSubarraySumsDivisiblebyK {



    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");

        //[]
        doRun(11, new int[]{7,-5,5,-8,-6,6,-4,7,-8,-7}, 7);
        doRun(2, new int[]{-1,-9,-4,0}, 9);
        doRun(2, new int[]{-2, 3, 8}, 3);

        doRun(2, new int[]{-1,2,9}, 2);
        doRun(2, new int[]{2,-2,2,-4}, 6);
        doRun(0, new int[]{2}, 6);
        doRun(7, new int[]{4,5,0,-2,-3,1}, 5);

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(long expect, int[] nums, int k) {
        int res = new N974MSubarraySumsDivisiblebyK().subarraysDivByK(nums, k);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //2.Math
    //Runtime: 3ms 98%; Memory: 45MB 98%
    //Time: O(N); Space: O(K)
    public int subarraysDivByK(int[] nums, int k) {
        int total = 0, res = 0;
        int[] counter = new int[k];
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
            int remainder = total % k;
            remainder = Math.max(remainder, (remainder >= 0) ? remainder - k : remainder + k);
            if (remainder == 0) res++;
            counter[remainder]++;
            nums[i] = remainder;
        }

        for (int i = 0; i < nums.length; i++)
            res += --counter[nums[i]];

        return res;
    }


    //1.brute force
    //TLE
    //Time: O(N * N); Space: O(1)
    public int subarraysDivByK_1(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            if (sum % k == 0) res++;
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum % k == 0) res++;
            }
        }
        return res;
    }

}
