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

    //same as 121
    //from chipbk10
    //Runtime: 1 ms, faster than 97.57% of Java online submissions for Best Time to Buy and Sell Stock II.
    //Memory Usage: 44.2 MB, less than 53.31% of Java online submissions for Best Time to Buy and Sell Stock II.
    //DP
    //Time: O(N); Space: O(1)
    public int maxProfit(int[] prices) {
        int balAfterSold0 = 0, balAfterBuy1 = -prices[0];
        for (int price: prices) {
            int afterSold0 = Math.max(balAfterSold0, balAfterBuy1 + price);
            int afterBuy1 = Math.max(balAfterBuy1, balAfterSold0 - price);
            balAfterSold0 = afterSold0;
            balAfterBuy1 = afterBuy1;
        }
        return balAfterSold0;
    }

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Best Time to Buy and Sell Stock II.
    //Memory Usage: 42.6 MB, less than 87.20% of Java online submissions for Best Time to Buy and Sell Stock II.
    //Peak Valley Approach
    //Time: O(N); Space: O(1)
    public int maxProfit2(int[] prices) {
        int profit = 0, idx = 0;
        while (idx < prices.length - 1) {

            //valley
            while (idx < prices.length - 1 && prices[idx] >= prices[idx + 1]) idx++;
            int buyerPrice = prices[idx];

            //peak
            while (idx < prices.length - 1 && prices[idx] <= prices[idx + 1]) idx++;
            profit += prices[idx++] - buyerPrice;
        }
        return profit;
    }


    //逐个累加
    //Runtime: 1 ms, faster than 97.57% of Java online submissions for Best Time to Buy and Sell Stock II.
    //Memory Usage: 44.3 MB, less than 46.58% of Java online submissions for Best Time to Buy and Sell Stock II.
    //Single pass.
    //Time: O(N)
    public int maxProfit1(int[] prices) {
        int profit = 0;
        for(int i = 1; i< prices.length; i++)
            if (prices[i] - prices[i-1] > 0)
                profit += prices[i] - prices[i-1];
        return profit;
    }

}
