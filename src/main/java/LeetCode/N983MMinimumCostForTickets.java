package LeetCode;

import java.io.IOException;
import java.util.Arrays;

import static java.time.LocalTime.now;

/**
 * You have planned some train traveling one year in advance. The days of the year in which you will travel are given as an integer array days. Each day is an integer from 1 to 365.
 *
 * Train tickets are sold in three different ways:
 *
 * a 1-day pass is sold for costs[0] dollars,
 * a 7-day pass is sold for costs[1] dollars, and
 * a 30-day pass is sold for costs[2] dollars.
 * The passes allow that many days of consecutive travel.
 *
 * For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
 * Return the minimum number of dollars you need to travel every day in the given list of days.
 *
 *
 *
 * Example 1:
 *
 * Input: days = [1,4,6,7,8,20], costs = [2,7,15]
 * Output: 11
 * Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
 * On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
 * On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
 * On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
 * In total, you spent $11 and covered all the days of your travel.
 * Example 2:
 *
 * Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
 * Output: 17
 * Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
 * On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
 * On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
 * In total, you spent $17 and covered all the days of your travel.
 *
 *
 * Constraints:
 *
 * 1 <= days.length <= 365
 * 1 <= days[i] <= 365
 * days is in strictly increasing order.
 * costs.length == 3
 * 1 <= costs[i] <= 1000
 */
public class N983MMinimumCostForTickets {

    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");

        doRun(7, new int[]{1,2,3,4,5,6,7}, new int[]{2,7,15});
        doRun(9, new int[]{1,2,3,4,5,6,7,8}, new int[]{2,7,15});
        doRun(11, new int[]{1,2,3,4,5,6,7,8,9}, new int[]{2,7,15});
        doRun(11, new int[]{1,2,3,4,5,6,7,8,90}, new int[]{2,7,15});
        doRun(4, new int[]{1,2}, new int[]{2,7,15});
        doRun(7, new int[]{1,2}, new int[]{100,7,15});
        doRun(11, new int[]{1,4,6,7,8,20}, new int[]{2,7,15});
        doRun(17, new int[]{1,2,3,4,5,6,7,8,9,10,30,31}, new int[]{2,7,15});

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, int[] days, int[] costs) {
        int res = new N983MMinimumCostForTickets().mincostTickets(days, costs);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //1.DP top-down
    //Runtime: 0ms 100%; Memory: 40.1MB 94%
    //Time: O(N); Space:O(N)
    public int mincostTickets(int[] days, int[] costs) {
        return helper_2(days, costs, 0, new int[days.length]);
    }
    private int[] d = new int[]{1,7,30};
    private int helper_2(int[] days, int[] costs, int idx, int[] memo){
        if (idx >= days.length) return 0;
        if (memo[idx] > 0) return memo[idx];

        int res = Integer.MAX_VALUE;
        int i = idx;
        for (int k = 0; k < 3; k++) {
            while (i < days.length && days[i] < days[idx] + d[k]) i++;
            res = Math.min(res, costs[k] + helper_2(days, costs, i, memo));
        }
        return memo[idx] = res;
    }

    //1.DP top-down
    //Runtime: 0ms 100%; Memory: 40.3MB 84%
    //Time: O(N); Space:O(N)
    public int mincostTickets_1(int[] days, int[] costs) {
        return helper(days, costs, 0, new int[days.length]);
    }

    private int helper(int[] days, int[] costs, int idx, int[] memo){
        if (idx >= days.length) return 0;

        if (memo[idx] > 0) return memo[idx];

        int i7 = Arrays.binarySearch(days, idx, days.length, days[idx] + 7);
        if (i7 < 0) i7 = -i7 - 1;
        int tmp7 = costs[1] + helper(days, costs, i7, memo);

        int i30 = Arrays.binarySearch(days, idx, days.length, days[idx] + 30);
        if (i30 < 0) i30 = -i30 - 1;
        int tmp30 = costs[2] + helper(days, costs, i30, memo);

        memo[idx] = Math.min(Math.min(tmp7, tmp30), costs[0] + helper(days, costs, idx + 1, memo));
        return memo[idx];
    }
}
