package LeetCode;


import java.util.Arrays;

import static java.time.LocalTime.now;

/***
 *
 * You are given an integer array coins representing coins of different denominations
 * and an integer amount representing a total amount of money.
 *
 * Return the number of combinations that make up that amount. If that amount of money
 * cannot be made up by any combination of the coins, return 0.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 * The answer is guaranteed to fit into a signed 32-bit integer.
 *
 *
 *
 * Example 1:
 *
 * Input: amount = 5, coins = [1,2,5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * Example 2:
 *
 * Input: amount = 3, coins = [2]
 * Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 * Example 3:
 *
 * Input: amount = 10, coins = [10]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= coins.length <= 300
 * 1 <= coins[i] <= 5000
 * All the values of coins are unique.
 * 0 <= amount <= 5000
 */

/**
 * M - [time: forget
 *   - 用DP累加
 *
 */
public class N518MCoinChange2 {


    public static void main(String[] args){

        System.out.println(now());
        int[] nums;

        nums = new int[]{2};
        doRun(0,3, nums);

        nums = new int[]{10};
        doRun(1,10, nums);

        nums = new int[]{5};
        doRun(1,10, nums);

        nums = new int[]{1,5,2};
        doRun(4,5, nums);

        nums = new int[]{1,2,5};
        doRun(5,6, nums);

        nums = new int[]{3,5,7,8,9,10,11};
        doRun(35502874,500, nums);

        System.out.println(now());
        System.out.println("==================");

    }
    static private void doRun(int expect, int amount, int[] coins) {
        int res = new N518MCoinChange2().change(amount, coins);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    ////////////////////////////////////////////////////////////////////////////
    //2.Dynamic programming bottom-up
    ///Runtime: 2ms 100%; Memory: 39.6 MB, 96%
    //Time: O(coins.length * amount); Space: O(amount)
    public int change(int amount, int[] coins) {
        if (amount == 0) return 1;

        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for(int i = 0; i < coins.length; i++)
            for (int j = coins[i]; j <= amount; j++)
                dp[j] += dp[j - coins[i]];
        return dp[amount];
    }


    //1.DP top-down + memo
    //Runtime: 104ms 8%; Memory: 46.3 MB, 57%
    //Time: O(N * L); Space:  O(N * L);
    //let N be the amount; L be the length of the input array coins
    public int change_1(int amount, int[] coins) {
        int[][] memo = new int[amount + 1][coins.length];
        for (int i = 0; i < amount + 1; i++)
            Arrays.fill(memo[i], -1);
        return helper_backtracking(amount, coins, 0, memo);
    }

    private int helper_backtracking(int amount, int[] coins, int begin, int[][] memo){
        if (amount == 0) return 1;
        if (memo[amount][begin] >= 0) return memo[amount][begin];
        int res = 0;
        for (int i = begin; i < coins.length; i++) {
            if (coins[i] > amount) continue;
            res += helper_backtracking(amount - coins[i], coins, i, memo);
        }
        memo[amount][begin] = res;
        return res;
    }
}
