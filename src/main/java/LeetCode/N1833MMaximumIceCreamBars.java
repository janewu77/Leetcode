package LeetCode;

import java.util.*;

/**
 * Example 1:
 *
 * Input: costs = [1,3,2,4,1], coins = 7
 * Output: 4
 * Explanation: The boy can buy ice cream bars at indices 0,1,2,4 for a total price of 1 + 3 + 2 + 1 = 7.
 * Example 2:
 *
 * Input: costs = [10,6,8,7,7,8], coins = 5
 * Output: 0
 * Explanation: The boy cannot afford any of the ice cream bars.
 * Example 3:
 *
 * Input: costs = [1,6,3,1,2,5], coins = 20
 * Output: 6
 * Explanation: The boy can buy all the ice cream bars for a total price of 1 + 6 + 3 + 1 + 2 + 5 = 18.
 *
 *
 * Constraints:
 *
 * costs.length == n
 * 1 <= n <= 105
 * 1 <= costs[i] <= 105
 * 1 <= coins <= 108
 */
public class N1833MMaximumIceCreamBars {


    //2.counter
    //Runtime: 9ms 98%; Memory: 82.3MB 5%
    //Time: O(N + M); Space: O(M)
    //let n be the number of bars
    //M be tha max price of bar
    public int maxIceCream(int[] costs, int coins) {
        int max = -1;
        for (int i = 0; i < costs.length; i++)
            max = Math.max(max, costs[i]);

        int[] counter = new int[max + 1];
        for(int i = 0; i < costs.length; i++)
            counter[costs[i]]++;

        int res = 0;
        for (int i = 1; i < counter.length; i++) {
            if (counter[i] == 0) continue;
            if (coins < i) break;

            int c = Math.min(counter[i], coins / i);
            res += c;
            coins -= c * i;
        }

        return res;
    }

    //1.sort
    //Runtime: 53ms 36%; Memory: 74.5MB 38%
    //Time: O(N * logN); Space: O(logN);
    public int maxIceCream_1(int[] costs, int coins) {
        Arrays.sort(costs);
        int i = 0;
        for (; i < costs.length && coins >= costs[i]; coins -= costs[i], i++);
        return i;
    }
}
