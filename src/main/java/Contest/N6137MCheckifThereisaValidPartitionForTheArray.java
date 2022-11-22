package Contest;

import LeetCode.N1220HCountVowelsPermutation;

import static java.time.LocalTime.now;

/**
 * You are given a 0-indexed integer array nums. You have to partition the array into one or more contiguous subarrays.
 *
 * We call a partition of the array valid if each of the obtained subarrays satisfies one of the following conditions:
 *
 * The subarray consists of exactly 2 equal elements. For example, the subarray [2,2] is good.
 * The subarray consists of exactly 3 equal elements. For example, the subarray [4,4,4] is good.
 * The subarray consists of exactly 3 consecutive increasing elements, that is, the difference between
 * adjacent elements is 1. For example, the subarray [3,4,5] is good, but the subarray [1,3,5] is not.
 * Return true if the array has at least one valid partition. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,4,4,5,6]
 * Output: true
 * Explanation: The array can be partitioned into the subarrays [4,4] and [4,5,6].
 * This partition is valid, so we return true.
 *
 *
 * Example 2:
 *
 * Input: nums = [1,1,1,2]
 * Output: false
 * Explanation: There is no valid partition for this array.
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 105
 * 1 <= nums[i] <= 106
 */

/**
 *
 * M - [time: 120+
 */
//2369. Check if There is a Valid Partition For The Array
public class N6137MCheckifThereisaValidPartitionForTheArray {


    public static void main(String[] args) {

        System.out.println(now());

        doRun(false,new int[]{2,2,3,4});
        doRun(true,new int[]{2,2,2,3,4});
        doRun(false,new int[]{2,2,2,3,5});

        System.out.println(now());
        System.out.println("==================");

    }
    static private void doRun(boolean expect, int[] nums) {
        boolean res = new N6137MCheckifThereisaValidPartitionForTheArray().validPartition(nums);
//        String res = comm.toString(res1);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
        //System.out.println("==================");
    }

    //dp坐标比nums往后一个。
    //dp 只需要5个位置。再早的dp没有用了。
    //Runtime: 10 ms, faster than 27.27% of Java online submissions for Check if There is a Valid Partition For The Array.
    //Memory Usage: 84.7 MB, less than 27.27% of Java online submissions for Check if There is a Valid Partition For The Array.
    //Time:O(N) ; Space: O(1)
    public boolean validPartition(int[] nums) {
        boolean[] dp = new boolean[5];
        dp[0] = true;
        for (int i = 2; i <= nums.length; i++) {
            int idx = i % 5;
            //rule 1,2,3
            dp[idx] = dp[(i - 2) % 5] && nums[i - 2] == nums[i - 1];
            dp[idx] |= i > 2 && dp[(i - 3) % 5] && nums[i - 3] == nums[i - 2] && nums[i - 2] == nums[i - 1];
            dp[idx] |= i > 2 && dp[(i - 3) % 5] && nums[i - 3] + 1 == nums[i - 2] && nums[i - 2] + 1 == nums[i - 1];
        }
        return dp[nums.length % 5];
    }


    //Runtime: 16 ms, faster than 9.09% of Java online submissions for Check if There is a Valid Partition For The Array.
    //Memory Usage: 51.7 MB, less than 90.91% of Java online submissions for Check if There is a Valid Partition For The Array.
    //Time:O(N) ; Space: O(N)
    public boolean validPartition_1(int[] nums) {
        boolean[] dp = new boolean[nums.length+1];

        dp[0] = true;
        for (int i = 2; i <= nums.length; i++) {
            dp[i] = helper_dp(nums, i-2, i-1) && dp[i-2];
            dp[i] |= i > 2 && helper_dp(nums, i-3, i-1) && dp[i-3];
        }
        return dp[nums.length];
    }

    private boolean helper_dp(int[] nums, int left ,int right){
        if (right - left == 1) return (nums[left] == nums[right] ); //rule1

        //rule2 & 3
        if (right - left == 2){
            if (helper_dp(nums ,left + 1, right) && helper_dp(nums ,left, right - 1)) return true;
            return (nums[right] - nums[left] == 2 && nums[right-1] - nums[left] == 1);
        }
        return false;
    }
}
