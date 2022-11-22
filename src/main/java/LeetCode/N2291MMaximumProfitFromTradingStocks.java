package LeetCode;


import javafx.util.Pair;

import java.util.*;

import static java.time.LocalTime.now;

/**
 * You are given two 0-indexed integer arrays of the same length present and future where present[i] is the current price of the ith stock and future[i] is the price of the ith stock a year in the future. You may buy each stock at most once. You are also given an integer budget representing the amount of money you currently have.
 *
 * Return the maximum amount of profit you can make.
 *
 *
 *
 * Example 1:
 *
 * Input: present = [5,4,6,2,3], future = [8,5,4,3,5], budget = 10
 * Output: 6
 * Explanation: One possible way to maximize your profit is to:
 * Buy the 0th, 3rd, and 4th stocks for a total of 5 + 2 + 3 = 10.
 * Next year, sell all three stocks for a total of 8 + 3 + 5 = 16.
 * The profit you made is 16 - 10 = 6.
 * It can be shown that the maximum profit you can make is 6.
 * Example 2:
 *
 * Input: present = [2,2,5], future = [3,4,10], budget = 6
 * Output: 5
 * Explanation: The only possible way to maximize your profit is to:
 * Buy the 2nd stock, and make a profit of 10 - 5 = 5.
 * It can be shown that the maximum profit you can make is 5.
 * Example 3:
 *
 * Input: present = [3,3,12], future = [0,3,15], budget = 10
 * Output: 0
 * Explanation: One possible way to maximize your profit is to:
 * Buy the 1st stock, and make a profit of 3 - 3 = 0.
 * It can be shown that the maximum profit you can make is 0.
 *
 *
 * Constraints:
 *
 * n == present.length == future.length
 * 1 <= n <= 1000
 * 0 <= present[i], future[i] <= 100
 * 0 <= budget <= 1000
 */

/**
 * M - [time: 120+
 */
public class N2291MMaximumProfitFromTradingStocks {


    public static void main(String[] args){
        System.out.println(now());
        int[] data, data2;

        data = new int[]{0};
        data2 = new int[]{1};
        doRun(1, data,  data2, 0);

        data = new int[]{2,2,5};
        data2 = new int[]{3,4,10};
        doRun(5, data,  data2, 6);

        data = new int[]{5,4,6,2,3};
        data2 = new int[]{8,5,4,3,5};
        doRun(6, data,  data2, 10);

        data = new int[]{3,3,12};
        data2 = new int[]{0,3,15};
        doRun(0, data,  data2, 10);



        System.out.println(now());
        System.out.println("==================");
    }

    private static void doRun(int expected, int[] present, int[] future, int budget){
        int res = new N2291MMaximumProfitFromTradingStocks().maximumProfit(present, future, budget);
        System.out.println("["+(expected == res)+"].expected:"+ expected+".res:"+res);
    }


    //https://leetcode.com/problems/maximum-profit-from-trading-stocks/discuss/2095843/Java-or-Bottom-Up-1D-DP-or-Buy-or-Not-Buy
    //Let dp[i][j] be the maximum profit that can be made with budget j and 0 ~ ith stock.
    //Then, we have dp[i][j] = max(dp[i-1][j - present[i]] + future[i] - present[i], dp[i-1][j])
    //   max(买， 不买）
    //
    //knapSack 背包问题
    //Runtime: 13 ms, faster than 99.00% of Java online submissions for Maximum Profit From Trading Stocks.
    //Memory Usage: 41.8 MB, less than 97.00% of Java online submissions for Maximum Profit From Trading Stocks.
    //knapSack DP
    //Time: O(N * Budget); Space: O(Budget)
    public int maximumProfit(int present[], int future[], int budget) {
        int[] dp = new int[budget + 1];

        for (int i = 0; i < present.length; i++) {
            for (int b = budget; b >= 0; b--) {
                if (present[i] <= b && present[i] < future[i])
                    // finding the maximum value
                    dp[b] = Math.max(dp[b], dp[b - present[i]] + future[i] - present[i]);
            }
        }
        return dp[budget];
    }


    //Time Limit Exceeded
    public int maximumProfit_bt(int[] present, int[] future, int budget) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < present.length; i++)
            if (future[i] - present[i] >= 0)
                list.add(new int[]{present[i], future[i] - present[i]});
        return helper(list, budget, 0, 0);
    }

    Map<Integer, int[]> memo = new HashMap<>();
    private int helper(List<int[]> list, int budget, int begin, int profit){
        if (begin >= list.size()) return profit;

        int memoKey = begin;
        if (memo.containsKey(memoKey)) {
            int[] tmp = memo.get(memoKey);
            if (budget < tmp[0]) return profit;
            if (budget == tmp[0]) return profit + tmp[1];
        }
        int res = profit;
        for (int i = begin; i < list.size(); i++){
            if (budget < list.get(i)[0]) continue;
            res = Math.max(res, helper(list, budget - list.get(i)[0], i + 1, profit + list.get(i)[1]));
        }
        memo.put(memoKey, new int[]{budget, res - profit});
        return res;
    }
}
