package LeetCode;


import java.io.IOException;

import static java.time.LocalTime.now;

/**
 * You are given an integer array nums. You are initially positioned at the array's first index,
 * and each element in the array represents your maximum jump length at that position.
 *
 * Return true if you can reach the last index, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0,
 * which makes it impossible to reach the last index.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 105
 */
public class N55MJumpGame {

    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");
        doRun(true, new int[]{2,3,1,1,4});
        doRun(false, new int[]{3,2,1,0,3});
        doRun(true, new int[]{0});
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(boolean expect, int[] nums) {
        boolean res = new N55MJumpGame().canJump(nums);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //3.Greedy
    //Runtime: 1 ms 100%; Memory: 43MB 81%
    //Time: O(N); Space: O(1);
    public boolean canJump(int[] nums) {
        int end = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
             if (i + nums[i] >= end)
                 end = i;
        }
        return end == 0;
    }


    //2.dp bottom-up
    //Runtime: 274ms 17%; Memory: 42.9MB 81%
    //Time: O(N * N); Space:O(N);
    public boolean canJump_2(int[] nums) {
        if (nums.length == 1) return true;

        boolean[] dp = new boolean[nums.length];
        dp[nums.length - 1] = true;

        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = 1; j <= nums[i] && dp[i] == false; j++) {
                if (i + j < nums.length)
                    dp[i] |= dp[i + j];
            }
        }
        return dp[0];
    }


    //1.DP top-down
    //Runtime: 212ms 18%; Memory: 42.8MB 89%
    //Time: O(N * N); Space: O(N)
    public boolean canJump_1(int[] nums) {
        return helper(nums,0, new int[nums.length]);
    }

    private boolean helper(int[] nums, int idx, int[] memo){
        if (idx >= nums.length - 1) return true;
        if (memo[idx] != 0) return memo[idx] == 1;
        for (int i = nums[idx]; i >= 1 ; i--){
            memo[idx] = -1;
            if (helper(nums, idx + i, memo)) {
                memo[idx] = 1;
                return true;
            }
        }
        return false;
    }
}
