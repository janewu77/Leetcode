package LeetCode;


/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve. You may complete as many transactions as you like
 * (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
 *
 * After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 * Example 2:
 *
 * Input: prices = [1]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= prices.length <= 5000
 * 0 <= prices[i] <= 1000
 */

import static java.time.LocalTime.now;

/**
 * M - [time : 60+
 */
public class N309MBestTimetoBuyandSellStockwithCooldown {


    public static void main(String[] args){
        System.out.println(now());
        int[] data;

        data = new int[]{1,2,3,0,2};
        doRun(3, data);
        data = new int[]{1};
        doRun(0, data);

        data = new int[]{2,1};
        doRun(0, data);

        System.out.println(now());
        System.out.println("==================");
    }

    private static void doRun(int expected, int[] prices){
        int res = new N309MBestTimetoBuyandSellStockwithCooldown().maxProfit(prices);
        System.out.println("["+(expected == res)+"].expected:"+ expected+".res:"+res);
    }

    //和下面一样的。这个变量名含义更容易理解
    //Runtime: 1 ms, faster than 94.55% of Java online submissions for Best Time to Buy and Sell Stock with Cooldown.
    //Memory Usage: 42.1 MB, less than 68.09% of Java online submissions for Best Time to Buy and Sell Stock with Cooldown.
    //DP
    //Time: O(N); Space: O(1)
    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;

        int sold0 = Integer.MIN_VALUE;
        int buy1 = Integer.MIN_VALUE;
        int reset = 0;

        for (int price : prices){
            int prevSold = sold0;
            sold0 = Math.max(sold0, buy1 + price);// sold after buy
            buy1 = Math.max(buy1, reset - price); // buy after reset
            reset = Math.max(reset, prevSold);    // reset after sold
        }
        return Math.max(sold0, reset);
    }


    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/75927/share-my-thinking-process
    //rest时， buy < rest < sold, 所以这里rest被简化成了前二天(prevSold0)
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Best Time to Buy and Sell Stock with Cooldown.
    //Memory Usage: 40 MB, less than 99.21% of Java online submissions for Best Time to Buy and Sell Stock with Cooldown.
    //DP
    //Time: O(N); Space: O(1)
    public int maxProfit_dp1(int[] prices) {
        if (prices.length < 2) return 0;

        int sold0 = 0, prevSold0 = 0;
        int buy1 = Integer.MIN_VALUE;

        for (int price : prices) {
            int lastSold0 = sold0;
            int currSold0 = Math.max(sold0, buy1 + price);
            int currBuy1 = Math.max(buy1, prevSold0 - price); //buy after a Cooldown
            prevSold0 = lastSold0;
            sold0 = currSold0;
            buy1 = currBuy1;
        }
        return sold0;
    }

}
