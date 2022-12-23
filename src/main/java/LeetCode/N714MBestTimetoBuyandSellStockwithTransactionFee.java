package LeetCode;


import static java.time.LocalTime.now;

/**
 * 714. Best Time to Buy and Sell Stock with Transaction Fee
 * Medium
 *
 * 4585
 *
 * 113
 *
 * Add to List
 *
 * Share
 * You are given an array prices where prices[i] is the price of a given stock on the ith day,
 * and an integer fee representing a transaction fee.
 *
 * Find the maximum profit you can achieve. You may complete as many transactions as you like,
 * but you need to pay the transaction fee for each transaction.
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [1,3,2,8,4,9], fee = 2
 * Output: 8
 * Explanation: The maximum profit can be achieved by:
 * - Buying at prices[0] = 1
 * - Selling at prices[3] = 8
 * - Buying at prices[4] = 4
 * - Selling at prices[5] = 9
 * The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * Example 2:
 *
 * Input: prices = [1,3,7,5,10,3], fee = 3
 * Output: 6
 *
 *
 * Constraints:
 *
 * 1 <= prices.length <= 5 * 104
 * 1 <= prices[i] < 5 * 104
 * 0 <= fee < 5 * 104
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
public class N714MBestTimetoBuyandSellStockwithTransactionFee {


    public static void main(String[] args){
        System.out.println(now());
        int[] data;

        data = new int[]{1,3,2,8,4,9};
        doRun(8, data, 2);

        data = new int[]{1,3,7,5,10,3};
        doRun(6, data, 3);

        System.out.println(now());
        System.out.println("==================");
    }

    private static void doRun(int expected, int[] prices, int fee){
        int res = new N714MBestTimetoBuyandSellStockwithTransactionFee().maxProfit(prices, fee);
        System.out.println("["+(expected == res)+"].expected:"+ expected+".res:"+res);
    }

    //base on 122 (fee)
    //Runtime: 6 ms, faster than 69.98% of Java online submissions for Best Time to Buy and Sell Stock with Transaction Fee.
    //Memory Usage: 68.4 MB, less than 80.40% of Java online submissions for Best Time to Buy and Sell Stock with Transaction Fee.
    //DP
    //Time: O(N); Space: O(1)
    public int maxProfit(int[] prices, int fee) {
        if (prices.length < 2) return 0;

        int balAfterSold0 = 0, balAfterBuy1 = -prices[0];

        for (int price: prices) {
            int prevSold = balAfterSold0;
            balAfterSold0= Math.max(balAfterSold0, balAfterBuy1 + price - fee);
            balAfterBuy1 = Math.max(balAfterBuy1, prevSold - price);
        }
        return balAfterSold0;
    }


}
