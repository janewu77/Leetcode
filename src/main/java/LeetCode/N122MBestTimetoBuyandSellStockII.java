package LeetCode;


import static java.time.LocalTime.now;

/**
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 *
 * On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of
 * the stock at any time. However, you can buy it then immediately sell it on the same day.
 *
 * Find and return the maximum profit you can achieve.
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 * Total profit is 4 + 3 = 7.
 * Example 2:
 *
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Total profit is 4.
 * Example 3:
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: There is no way to make a positive profit, so we never buy the stock to achieve
 * the maximum profit of 0.
 *
 *
 * Constraints:
 *
 * 1 <= prices.length <= 3 * 104
 * 0 <= prices[i] <= 104
 */

/**
 * M - [time: 15-
 */
//121. Best Time to Buy and Sell Stock
//122. Best Time to Buy and Sell Stock II
//123. Best Time to Buy and Sell Stock III
//188. Best Time to Buy and Sell Stock IV
//309. Best Time to Buy and Sell Stock with Cooldown
//714. Best Time to Buy and Sell Stock with Transaction Fee
public class N122MBestTimetoBuyandSellStockII {


    public static void main(String[] args){
        System.out.println(now());
        int[] data;

        data = new int[]{1,2,3,4,5};
        doRun(4, data);

        data = new int[]{2,4,1};
        doRun(2, data);

        data = new int[]{7,1,5,3,6,4};
        doRun(7, data);

        data = new int[]{7,6,4,3,1};
        doRun(0, data);

        System.out.println(now());
        System.out.println("==================");
    }

    private static void doRun(int expected, int[] prices){
        int res = new N122MBestTimetoBuyandSellStockII().maxProfit(prices);
        System.out.println("["+(expected == res)+"].expected:"+ expected+".res:"+res);
    }

    //3.DP
    //Runtime: 0 ms, faster than 100.00%; Memory: 42 MB, less than 93%
    //Time: O(N); Space: O(1)
    public int maxProfit(int[] prices) {
        int sold0 = 0, buy1= -prices[0];
        for (int price : prices){
            int prevSold = sold0;
            sold0 = Math.max(sold0, buy1 + price);
            buy1= Math.max(buy1, prevSold - price);
        }
        return sold0;
    }


    //2.Peak Valley Approach
    //Runtime: 0 ms, faster than 100.00%; Memory: 42.6 MB, less than 76%
    //Time: O(N); Space: O(1)
    public int maxProfit2(int[] prices) {
        int profit = 0;

        for (int idx = 0; idx < prices.length - 1; idx++) {
            //valley
            while (idx < prices.length - 1 && prices[idx] >= prices[idx + 1]) idx++;
            int buyerPrice = prices[idx];

            //peak
            while (idx < prices.length - 1 && prices[idx] <= prices[idx + 1]) idx++;
            profit += prices[idx] - buyerPrice;
        }
        return profit;
    }


    //1. single pass
    //Runtime: 1 ms, 95%; Memory: 41.9 MB 96%
    //Time: O(N); Space: O(N)
    public int maxProfit1(int[] prices) {
        int profit = 0;
        for(int i = 1; i< prices.length; i++) {
            int todayProfit = prices[i] - prices[i - 1];
            if (todayProfit > 0)
                profit += todayProfit;
        }
        return profit;
    }

}
