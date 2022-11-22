package LeetCode;

import static java.time.LocalTime.now;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * There is a row of n houses, where each house can be painted one of three colors: red, blue, or green.
 * The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by an n x 3 cost matrix costs.
 *
 * For example, costs[0][0] is the cost of painting house 0 with the color red;
 * costs[1][2] is the cost of painting house 1 with color green, and so on...
 * Return the minimum cost to paint all houses.
 *
 *
 *
 * Example 1:
 * Input: costs = [[17,2,17],[16,16,5],[14,3,19]]
 * Output: 10
 * Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
 * Minimum cost: 2 + 5 + 3 = 10.
 *
 *
 * Example 2:
 * Input: costs = [[7,6,2]]
 * Output: 2
 *
 *
 * Constraints:
 *
 * costs.length == n
 * costs[i].length == 3
 * 1 <= n <= 100
 * 1 <= costs[i][j] <= 20
 */

/**
 * M - 30m+
 *  - 精简后的代码，很让人惊奇。
 *  - 从前往后累加 & 利用原空间
 */
public class N256MPaintHouse {


    public static void main(String[] args){
        int[][] costs;

        costs = new int[][]{{7,6,2}};
        doRun(costs ,2, "1");

        costs = new int[][]{{17,2,17},{16,16,5},{14,3,19}};
        doRun(costs ,10, "2");

        costs = new int[][]{{17,19,16},{11,17,17}};
        doRun(costs ,27, "3");
        costs = new int[][]{{3,5,3},{6,17,6},{7,13,18},{9,10,18}};
        doRun(costs ,26, "4");

        costs = new int[][]{{17,19,16},{11,17,17},{14,18,10},{17,20,17},{14,2,13},{9,6,14},{20,5,17},{19,19,5},{5,14,6},{9,17,19},{17,13,6},{7,10,7},{2,18,15},{9,9,13},{8,17,14},{8,20,11},{2,11,18},{14,20,6},{10,1,9},{16,12,7}};
        System.out.println(now());
        doRun(costs ,159, "time1");
        System.out.println(now());
    }

    private static void doRun(int[][] costs, int expected, String  title){
        int result = new N256MPaintHouse().minCost(costs);
        System.out.println ("["+(expected == result)+"] "+title+".expected: "+expected +". result:"+ result);
    }

    //Runtime: 1 ms, faster than 93.27% of Java online submissions for Paint House.
    //Memory Usage: 42.8 MB, less than 80.57% of Java online submissions for Paint House.
    // 从上一次中挑便宜的color forward
    public int minCost(int[][] costs) {
        for (int h = 1; h < costs.length; h++) {
            costs[h][0] += Math.min(costs[h - 1][1], costs[h - 1][2]);
            costs[h][1] += Math.min(costs[h - 1][0], costs[h - 1][2]);
            costs[h][2] += Math.min(costs[h - 1][0], costs[h - 1][1]);
        }
        return Math.min(costs[costs.length-1][0], Math.min(costs[costs.length-1][1], costs[costs.length-1][2]));
    }

    // up to top + memo
    Map<Integer, Integer> memo = new HashMap<>();
    public int minCost1(int[][] costs) {
        return  doCost(costs, 0, -1);
    }

    private int doCost(int[][] costs, int house, int prv_color){
        if (house >= costs.length) return 0;
        int mKey = house*10+prv_color;
        if (memo.containsKey(mKey)) return memo.get(mKey);

        int result = Integer.MAX_VALUE;
        for (int color = 0; color < costs[house].length; color++){
            if (color != prv_color)
                result = Math.min(result, costs[house][color] + doCost(costs, house + 1, color));
        }
        memo.put(mKey, result);
        return result;
    }
}
