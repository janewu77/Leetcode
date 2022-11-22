package LeetCode;


/**
 *
 * 121. Best Time to Buy and Sell Stock
 * Easy
 *
 * 17406
 *
 * 564
 *
 * Add to List
 *
 * Share
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * You want to maximize your profit by choosing a single day to buy one stock
 * and choosing a different day in the future to sell that stock.
 *
 * Return the maximum profit you can achieve from this transaction.
 * If you cannot achieve any profit, return 0.
 *
 *
 *
 * Example 1:
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 *
 *
 * Example 2:
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transactions are done and the max profit = 0.
 *
 *
 * Constraints:
 *
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 104
 *
 */

import static java.time.LocalTime.now;

/**
 * E - 耗时60+
 *  - 被困在了被"超时"里。
 *
 *
 */
public class N121EBestTimetoBuyandSellStock {

    public static void main(String[] args){
        System.out.println(now());
        int[] data;

        data = new int[]{7,1,5,3,6,4};
        doRun(5, data);
        data = new int[]{7,6,4,3,1};
        doRun(0, data);


        System.out.println(now());
        System.out.println("==================");
    }

    private static void doRun(int expected, int[] prices){
        int res = new N121EBestTimetoBuyandSellStock().maxProfit(prices);
        System.out.println("["+(expected == res)+"].expected:"+ expected+".res:"+res);
    }

    //2022.9.13
    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Best Time to Buy and Sell Stock.
    //Memory Usage: 59.4 MB, less than 87.24% of Java online submissions for Best Time to Buy and Sell Stock.
    //Time: O(N); Space: O(1)
    public int maxProfit(int[] prices) {
        int balAfterSold0 = 0, balAfterBuy1 = Integer.MIN_VALUE;
        for(int price: prices){
            balAfterSold0 = Math.max(balAfterSold0, balAfterBuy1 + price);
            balAfterBuy1  = Math.max(balAfterBuy1, -price); //at most one transaction
        }
        return balAfterSold0;
    }

    public int maxProfit2(int[] prices) {
        int profit = 0, cost = prices[0];
        for(int price: prices){
            cost = Math.min(cost, price); //最小成本
            profit = Math.max(profit, price - cost);
        }
        return profit;
    }

    //Time Limit Exceeded
    //Brute Force
    //Time: O(N*N); Space: O(1)
    public int maxProfit_Brute(int[] prices) {
        int result = 0;
        for(int i = 0; i < prices.length; i++)
            for(int j = i + 1; j < prices.length; j++)
                result = Math.max(result, prices[j] - prices[i]);
        return result;
    }

    // 2022.7.1
    //Runtime: 2 ms, faster than 93.04% of Java online submissions for Best Time to Buy and Sell Stock.
    //Memory Usage: 59 MB, less than 89.53% of Java online submissions for Best Time to Buy and Sell Stock.
    //Time: O(N); Space: O(1)
    public int maxProfit_0(int[] prices) {
        int min= prices[0];
        int result = 0;
        int yP = -1;
        for(int p: prices){
            if(p == yP) continue; //忽略没有价格波动的
            yP = p;
            if(p < min) min = p;
            else if (p - min > result)
                result = p - min;
        }
        return result;
    }

}
