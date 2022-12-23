package LeetCode;



import static java.time.LocalTime.now;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve. You may complete at most two transactions.
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 * Example 2:
 *
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you
 * are engaging multiple transactions at the same time. You must sell before buying again.
 * Example 3:
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 *
 *
 * Constraints:
 *
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 105
 */


/**
 * H -[time: 120+
 */
//121. Best Time to Buy and Sell Stock
//122. Best Time to Buy and Sell Stock II
//123. Best Time to Buy and Sell Stock III
//188. Best Time to Buy and Sell Stock IV
//309. Best Time to Buy and Sell Stock with Cooldown
public class N123HBestTimetoBuyandSellStockIII {
    public static void main(String[] args){
        System.out.println(now());
        int[] data;



        data = new int[]{1,2,3,4,5};
        doRun(4, data);

        data = new int[]{3,3,5,0,0,3,1,4};
        doRun(6, data);

        data = new int[]{7,1,5,3,6,4};
        doRun(7, data);

        data = new int[]{7,6,4,3,1};
        doRun(0, data);

        System.out.println(now());
        System.out.println("==================");
    }

    private static void doRun(int expected, int[] prices){
        int res = new N123HBestTimetoBuyandSellStockIII().maxProfit(prices);
        System.out.println("["+(expected == res)+"].expected:"+ expected+".res:"+res);
    }


    //2022.12.23
    //3.DP
    //Runtime: 5ms 79%; Memory: 75.9MB 74%
    //Time: O(N); Space: O(1)
    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;

        int k = 2;
        int[][] dp = new int[k][2]; //0 : buy; 1 sold
        for (int i = 0; i < k; i++)
            dp[i][0] = -prices[i];

        for (int i = 1; i < prices.length; i++) {
            for (int j = k - 1; j >= 0; j--) {
                dp[j][1] = Math.max(dp[j][1], dp[j][0] + prices[i]);
                dp[j][0] = Math.max(dp[j][0], (j - 1 >= 0 ? dp[j - 1][1] : 0) - prices[i]);
            }
        }
        int res = 0;
        for (int i = 0; i < k; i++) res = Math.max(res, dp[i][1]);
        return res;
    }


    //2.DP
    //Runtime: 2ms 99%; Memory: 58.4MB 88%
    //Time: O(N); Space: O(1)
    public int maxProfit_22(int[] prices) {
        if (prices.length < 2) return 0;

        int buy1 = -prices[0], sold1 = 0;
        int buy2 = -prices[1], sold2 = 0;

        for (int i = 1; i < prices.length; i++) {
            sold2 = Math.max(sold2, buy2 + prices[i]);
            buy2 = Math.max(buy2, sold1 - prices[i]);

            sold1 = Math.max(sold1, buy1 + prices[i]);
            buy1 = Math.max(buy1, -prices[i]);
        }
        return Math.max(sold1, sold2);
    }

    //1.Bidirectional Dynamic Programming
    //Runtime: 4ms, 85%; Memory: 51.7MB 95%
    //Time: O(N); Space: O(N)
    public int maxProfit_21(int[] prices) {
        int[] leftProfit = new int[prices.length];
        int[] rightProfit = new int[prices.length];

        int cost = prices[0];
        for (int i = 1; i < prices.length; i++) {
            cost = Math.min(cost, prices[i]);
            leftProfit[i] = Math.max(leftProfit[i - 1], prices[i] - cost);
        }

        int maxPrice = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            maxPrice = Math.max(maxPrice, prices[i]);
            rightProfit[i] = Math.max(rightProfit[i + 1], maxPrice - prices[i]);
        }

        int res = 0;
        for (int i = 0; i < prices.length; i++)
            res = Math.max(res, leftProfit[i] + rightProfit[i]);

        return res;
    }

    //before 2022.12.23
    //Runtime: 5 ms, faster than 83.20% of Java online submissions for Best Time to Buy and Sell Stock III.
    //Memory Usage: 54.4 MB, less than 96.61% of Java online submissions for Best Time to Buy and Sell Stock III.
    //DP
    //Time: O(N*K); Space: O(1);
    public int maxProfit_3(int[] prices) {
        int k = 2; //at most two transactions.

        //balance after buy or sold
        //k : 2 trans  | buy(1) or sell(0)
        int[][] dp = new int[k][2];
        for (int[] x : dp) x[1] = Integer.MIN_VALUE;

        for (int i = 0; i < prices.length; i++) {
            for (int j = 0; j < k; j++){
                int last0 = j == 0 ? 0 : dp[j - 1][0];
                dp[j][0] = Math.max(dp[j][0], dp[j][1] + prices[i]);//sold
                dp[j][1] = Math.max(dp[j][1], last0 - prices[i]);//buy
            }
        }
        return Math.max(dp[0][0], dp[1][0]);
    }

    //Runtime: 2 ms, faster than 99.98% of Java online submissions for Best Time to Buy and Sell Stock III.
    //Memory Usage: 58.7 MB, less than 92.59% of Java online submissions for Best Time to Buy and Sell Stock III.
    //Time: O(N); Space: O(1);
    public int maxProfit_1(int[] prices) {
        if (prices.length < 2) return 0;

        int buy1 = -prices[0];
        int sell1 = buy1 + prices[0];
        int buy2 = sell1 - prices[0];
        int sell2 = buy2 + prices[0];

        for (int i = 1; i < prices.length; i++) {

            // The maximum money left after buying 1st stock.
            buy1 = Math.max(buy1, -prices[i]);

            // The maximum money left after selling 1st stock.
            sell1 = Math.max(sell1, buy1 + prices[i]);

            // The maximum money left after buying 2nd stock.
            buy2 = Math.max(buy2, sell1 - prices[i]);

            // The maximum money left after selling 2nd stock.
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return Math.max(sell1, sell2);
    }

    //from solution
    //从二边同时计算。仅适用于"最多二笔交易"
    //Runtime: 16 ms, faster than 45.38% of Java online submissions for Best Time to Buy and Sell Stock III.
    //Memory Usage: 80.2 MB, less than 52.86% of Java online submissions for Best Time to Buy and Sell Stock III.
    //Bidirectional Dynamic Programming
    //Time: O(2N); Space: O(2N)
    public int maxProfit_bidp(int[] prices) {

        int[] leftProfits = new int[prices.length];
        int[] rightProfits = new int[prices.length];
        int leftMinCost = prices[0];
        int rightMinCost = prices[prices.length - 1];
        for (int i = 0; i < prices.length; i++){
            int leftPrice = prices[i];
            leftMinCost = Math.min(leftMinCost, leftPrice);
            if (i > 0) leftProfits[i] = Math.max(leftProfits[i - 1], leftPrice - leftMinCost);
            else leftProfits[i] = leftPrice - leftMinCost;

            int rightIdx = prices.length - 1 - i;
            int rightPrice = prices[rightIdx];
            rightMinCost = Math.max(rightMinCost, rightPrice);
            if (rightIdx < prices.length - 1) rightProfits[rightIdx] = Math.max(rightProfits[rightIdx + 1], rightMinCost - rightPrice);
            else rightProfits[rightIdx] = rightMinCost - rightPrice;
            rightIdx--;
        }

        int res = Math.max(leftProfits[prices.length-1], rightProfits[0]);
        for (int i = 0; i < prices.length - 1; i++)
            res = Math.max(res, leftProfits[i] + rightProfits[i + 1]);
        return res;
    }
}
