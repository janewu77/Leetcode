package LeetCode;

import java.util.Arrays;

/**
 * You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.
 * You can either start from the step with index 0, or the step with index 1.
 * Return the minimum cost to reach the top of the floor.
 *
 *
 *
 * Example 1:
 * Input: cost = [10,15,20]
 * Output: 15
 * Explanation: You will start at index 1.
 * - Pay 15 and climb two steps to reach the top.
 * The total cost is 15.
 *
 *
 * Example 2:
 * Input: cost = [1,100,1,1,1,100,1,1,100,1]
 * Output: 6
 * Explanation: You will start at index 0.
 * - Pay 1 and climb two steps to reach index 2.
 * - Pay 1 and climb two steps to reach index 4.
 * - Pay 1 and climb two steps to reach index 6.
 * - Pay 1 and climb one step to reach index 7.
 * - Pay 1 and climb two steps to reach index 9.
 * - Pay 1 and climb one step to reach the top.
 * The total cost is 6.
 *
 *
 * Constraints:
 * 2 <= cost.length <= 1000
 * 0 <= cost[i] <= 999
 *
 */
public class N746EMinCostClimbingStairs {

    public static void main(String[] args){

        int[] data = new int[]{1,100,1,1,1,100,1,1,100,1};
        //int result = new N746EMinCostClimbingStairs().minCostClimbingStairs(data);
        run(data, 6, "1");
        run(new int[]{10,15,20}, 15, "1");

    }


    private static void run(int[] nums , int expected, String title){
        int result = new N746EMinCostClimbingStairs().minCostClimbingStairs(nums);
        System.out.println( "["+(result == expected)+"]"+title+".expected "+expected+"; result:"+ result);
    }

    //3.DP bottom-up constant space
    //Runtime: 0ms, 100%; Memory: 41.6MB, 97.33%
    //Time:O(N); Space: O(1)
    public int minCostClimbingStairs(int[] cost) {
        int cost0 = cost[0], cost1 = cost[1], cost2;
        for (int i = 2; i < cost.length; i++) {
            cost2 = cost[i] + Math.min(cost0, cost1);
            cost0 = cost1; cost1 = cost2;
        }
        return Math.min(cost0, cost1);
    }
    
    //2.DP top-down
    //Runtime: 2ms, 33.42%; Memory: 43.7MB, 45.75%
    //Time:O(N); Space: O(N)
    public int minCostClimbingStairs_2(int[] cost) {
        int[] memo = new int[cost.length + 1];
        Arrays.fill(memo, -1);
        return helper_dp(cost, cost.length, memo);
    }
    private int helper_dp(int[] cost, int idx, int[] memo){
        if (idx < 2) return cost[idx];
        if (memo[idx] != -1) return memo[idx];
        int res = 0;
        if (idx < cost.length) res = cost[idx];
        memo[idx] = res + Math.min(helper_dp(cost, idx - 2, memo), helper_dp(cost, idx - 1, memo));
        return memo[idx];
    }

    //1.DP bottom-up in place
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Min Cost Climbing Stairs.
    //Memory Usage: 41.8 MB, less than 93.59% of Java online submissions for Min Cost Climbing Stairs.
    //Time:O(N); Space: O(1)
    public int minCostClimbingStairs_1(int[] cost) {
        for (int i = 2; i < cost.length; i++)
            cost[i] += Math.min(cost[i - 2], cost[i - 1]);
        return Math.min(cost[cost.length - 2], cost[cost.length - 1]);
    }

}
