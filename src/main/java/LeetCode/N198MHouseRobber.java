package LeetCode;


import java.util.Arrays;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 2:
 *
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 */
public class N198MHouseRobber {

    //3.dp top-down
    //Runtime: 0 ms, 100%; Memory: 41.6 MB, 34.41%
    //Time: O(N); Space: O(N)
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return helper_dp_topdown(nums, nums.length - 1, memo);
    }
    private int helper_dp_topdown(int[]nums, int idx, int[] memo){
        if (idx == 0) return nums[0];
        if (idx == 1) return Math.max(nums[0], nums[1]);
        if (memo[idx] != -1) return memo[idx];
        memo[idx] = Math.max(helper_dp_topdown(nums, idx - 1, memo), helper_dp_topdown(nums, idx - 2, memo) + nums[idx]);
        return memo[idx];
    }

    //2.dp bottom-up without array
    //Runtime: 0 ms, 100%; Memory: 39.4 MB, 96.86%%
    //Time: O(N); Space: O(1)
    public int rob_2(int[] nums) {
        if (nums.length == 1) return nums[0];
        int dp, dp0 = nums[0], dp1 = Math.max(dp0, nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp = Math.max(dp1, dp0 + nums[i]);
            dp0 = dp1; dp1 = dp;
        }
        return dp1;
    }

    //1.dp bottom-up
    //Runtime: 0 ms, 100%; Memory: 39.8 MB, 84.60%
    //Time: O(N); Space: O(N)
    public int rob_1(int[] nums) {
        if (nums.length == 1) return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);

        for (int i = 2; i < nums.length; i++)
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        return dp[nums.length - 1];
    }
}
