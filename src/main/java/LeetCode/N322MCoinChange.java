package LeetCode;


import java.util.Arrays;

import static java.time.LocalTime.now;

/**
 * You are given an integer array coins representing coins of different denominations
 * and an integer amount representing a total amount of money.
 *
 * Return the fewest number of coins that you need to make up that amount. If that amount
 * of money cannot be made up by any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 *
 *
 * Example 1:
 *
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * Example 2:
 *
 * Input: coins = [2], amount = 3
 * Output: -1
 * Example 3:
 *
 * Input: coins = [1], amount = 0
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 */

/**
 * - M - 【time：20-
 * - backtracling 卡了很多时间。放弃
 * - DP 花了20分钟就完成了
 *
 */
public class N322MCoinChange {

    public static void main(String[] args){
        int[] data;

        System.out.println(now());

        data = new int[]{1,2,5};
        doRun_demo(3, data, 11);

        data = new int[]{2};
        doRun_demo(-1, data, 3);

        data = new int[]{1};
        doRun_demo(0, data, 0);

        data = new int[]{1,2147483647};
        doRun_demo(2, data, 2);
//
        data = new int[]{411,412,413,414,415,416,417,418,419,420,421,422};
        doRun_demo(24, data, 9864);

        data = new int[]{2};
        doRun_demo(4, data, 8);

        data = new int[]{186,419,83,408};
        doRun_demo(20, data, 6249);

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun_demo(int expect, int[] coins, int amount) {
        int res = new N322MCoinChange().coinChange(coins, amount);
//        String res = comm.toString(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //20分钟左右写出来的。但在写这个之间在又以一次卡在backtracking上了。
    //Runtime: 18 ms, faster than 91.22% of Java online submissions for Coin Change.
    //Memory Usage: 41.6 MB, less than 98.34% of Java online submissions for Coin Change.
    //DP - bottom up
    //Time：O(A + N * A); Space: O(A+1);
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        int MAX = amount + 1;

        //Time：O(A); Space: O(A+1);
        //[sum] = count sum是由count个coin累加的
        int[] dp_sum = new int[amount + 1];
        Arrays.fill(dp_sum, MAX);

        for (int n : coins)
            if (n <= amount) dp_sum[n] = 1;

        //Time：O(N * A)
        for (int coin : coins) {
            if (coin > amount) continue;
            for (int sum = 1; sum <= amount; sum++) {
                if(coin + sum <= amount && dp_sum[sum] != MAX) {
                    dp_sum[coin + sum] = Math.min(dp_sum[coin + sum], dp_sum[coin] + dp_sum[sum]);
                }
            }
        }
        return dp_sum[amount] == MAX ? -1 : dp_sum[amount];
    }



    ////////////////////////////////////////////////
    //Time Limit Exceeded
    //In the worst case, complexity is exponential in the number of the coins nn.
    //Back tracking
    public int coinChange_1(int[] coins, int amount) {
        if (amount == 0) return 0;
        Arrays.sort(coins);
        int res = helper_backtracking_1(coins, amount, coins.length-1);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int helper_backtracking_1(int[] coins, int amount, int begin){
        if (amount == 0) return 0;
        int res = Integer.MAX_VALUE;
        for (int i = begin; i >= 0; i--){
            if (coins[i] > amount) continue;
            res = Math.min(res, helper_backtracking_1(coins, amount - coins[i], i));
        }
        return res == Integer.MAX_VALUE ? Integer.MAX_VALUE : (res + 1);//% Integer.MAX_VALUE;
    }
}
