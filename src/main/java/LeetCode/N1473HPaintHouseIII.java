package LeetCode;


/**
 *
 * There is a row of m houses in a small city, each house must be painted with one of the n colors
 * (labeled from 1 to n), some houses that have been painted last summer should not be painted again.
 *
 * A neighborhood is a maximal group of continuous houses that are painted with the same color.
 *
 * For example: houses = [1,2,2,3,3,2,1,1] contains 5 neighborhoods [{1}, {2,2}, {3,3}, {2}, {1,1}].
 * Given an array houses, an m x n matrix cost and an integer target where:
 *
 * houses[i]: is the color of the house i, and 0 if the house is not painted yet.
 * cost[i][j]: is the cost of paint the house i with the color j + 1.
 * Return the minimum cost of painting all the remaining houses in such a way that there are
 * exactly target neighborhoods. If it is not possible, return -1.
 *
 *
 *
 * Example 1:
 * Input: houses = [0,0,0,0,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
 * Output: 9
 * Explanation: Paint houses of this way [1,2,2,1,1]
 * This array contains target = 3 neighborhoods, [{1}, {2,2}, {1,1}].
 * Cost of paint all houses (1 + 1 + 1 + 1 + 5) = 9.
 *
 *
 * Example 2:
 * Input: houses = [0,2,1,2,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
 * Output: 11
 * Explanation: Some houses are already painted, Paint the houses of this way [2,2,1,2,2]
 * This array contains target = 3 neighborhoods, [{2,2}, {1}, {2,2}].
 * Cost of paint the first and last house (10 + 1) = 11.
 *
 *
 * Example 3:
 * Input: houses = [3,1,2,3], cost = [[1,1,1],[1,1,1],[1,1,1],[1,1,1]], m = 4, n = 3, target = 3
 * Output: -1
 * Explanation: Houses are already painted with a total of 4 neighborhoods [{3},{1},{2},{3}] different of target = 3.
 *
 *
 * Constraints:
 * m == houses.length == cost.length
 * n == cost[i].length
 * 1 <= m <= 100
 * 1 <= n <= 20
 * 1 <= target <= m
 * 0 <= houses[i] <= n
 * 1 <= cost[i][j] <= 104
 *
 */

import java.util.Arrays;

/**
 * H - (耗时 120+）
 *  - 通常H级别的题比较复杂，要转几个弯。但这个题没有转弯，只是烦锁。碰到"烦锁"的题，要先冷静下来，理清要解决的问题。（大事化小来解决）
 *  - 对于递归，通常可用memo保存中间结果，以提高整体性能。
 *
 */
public class N1473HPaintHouseIII {

    public static void main(String[] args){
        int[] houses;
        int[][] cost;
        int m;
        int n;
        int target;

        houses = new int[]{0,0,0,0,0};
        cost = new int[][]{{1,10},{10,1},{10,1},{1,10},{5,1}};
        m = 5; n = 2; target = 3;
        System.out.println("1.expected 9. result: "+ new N1473HPaintHouseIII().minCost(houses,cost,m,n,target));

        houses = new int[]{0,2,1,2,0};
        cost = new int[][]{{1,10},{10,1},{10,1},{1,10},{5,1}};
        m = 5; n = 2; target = 3;
        System.out.println("2.expected 11. result: "+ new N1473HPaintHouseIII().minCost(houses,cost,m,n,target));

        houses = new int[]{3,1,2,3};
        cost = new int[][]{{1,1,1},{1,1,1},{1,1,1},{1,1,1}};
        m = 4; n = 3; target = 3;
        System.out.println("3.expected -1. result: "+ new N1473HPaintHouseIII().minCost(houses,cost,m,n,target));
    }

    //Runtime: 23 ms, faster than 90.94% of Java online submissions for Paint House III.
    //Memory Usage: 48.9 MB, less than 52.50% of Java online submissions for Paint House III.
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        int MAX_COST = 1000001; //10000 * 100 + 1; // see Constraints
        //target(+0) | color
        int[][] prevData = new int[target+1][n];

        // fill MAX_COST to prev & current
        for (int j = 0; j <= target; j++)
            Arrays.fill(prevData[j], MAX_COST);

        if (houses[0] != 0)
            prevData[1][houses[0]-1] = 0;
        else
            for(int c = 1; c <= n; c++) prevData[1][c - 1] = cost[0][c - 1];

        for (int house = 1; house < m; house++) {
            int[][] currData = new int[target + 1][n];
            for (int j = 0; j <= target; j++)
                Arrays.fill(currData[j], MAX_COST);

            for (int neighborhoods = 1; neighborhoods <= Math.min(target, house + 1); neighborhoods++){
                for (int color = 1; color <= n; color++) {
                    if (houses[house] != 0 && color != houses[house]) continue;

                    int prevCost = MAX_COST;
                    for (int prevColor = 1; prevColor <= n; prevColor++) {
                        int group = prevColor != color ? neighborhoods - 1: neighborhoods;
                        prevCost = Math.min(prevCost, prevData[group][prevColor - 1]);
                    }

                    int currCost = houses[house] != 0 ? 0 : cost[house][color - 1];
                    currData[neighborhoods][color - 1] = currCost + prevCost;

                }//End for color
            } //End for group
            prevData = currData;
        }// End for house

        int minCost = MAX_COST;
        for (int color = 1; color <= n; color++)
            minCost = Math.min(minCost, prevData[target][color - 1]);
        return minCost == MAX_COST ? -1 : minCost;
    }


    //Runtime: 32 ms, faster than 76.25% of Java online submissions for Paint House III.
    //Memory Usage: 48.6 MB, less than 58.75% of Java online submissions for Paint House III.
    //从前往后进行累加
    public int minCost2(int[] houses, int[][] cost, int m, int n, int target) {
        int MAX_COST = 1000001 ;//10000 * 100 + 1; // see Constraints
        //houseIndex | target(+0) | color
        int[][][] memo = new int[m][target+1][n];
        // fill MAX_COST to memo
        for (int i = 0; i < m; i++)
            for (int j = 0; j <= target; j++)
                Arrays.fill(memo[i][j], MAX_COST);

        if (houses[0] != 0)
            memo[0][1][houses[0]-1] = 0;
        else
            for(int c = 1; c <= n; c++) memo[0][1][c - 1] = cost[0][c - 1];

        for (int house = 1; house < m; house++) {
            for (int neighborhoods = 1; neighborhoods <= Math.min(target, house + 1); neighborhoods++){
                for (int color = 1; color <= n; color++) {
                    if (houses[house] != 0 && color != houses[house]) continue;

                    int prevCost = MAX_COST;
                    for (int prevColor = 1; prevColor <= n; prevColor++) {
                        int group = prevColor != color ? neighborhoods - 1: neighborhoods;
                        prevCost = Math.min(prevCost, memo[house - 1][group][prevColor - 1]);
                    }

                    int currCost = houses[house] != 0 ? 0 : cost[house][color - 1];
                    memo[house][neighborhoods][color - 1] = currCost + prevCost;

                }//End for color
            } //End for group
        }// End for house

        int minCost = MAX_COST;
        for (int color = 1; color <= n; color++)
            minCost = Math.min(minCost, memo[m - 1][target][color - 1]);
        return minCost == MAX_COST ? -1 : minCost;
    }

    //Runtime: 36 ms, faster than 70.00% of Java online submissions for Paint House III.
    //Memory Usage: 45.4 MB, less than 84.69% of Java online submissions for Paint House III.
    //逐步从大往小缩小问题 top-down tp
    final int MAX_COST = 1000001; //10000 * 100 + 1; // see Constraints
    //houseIndex | group | color(+0)
    int[][][] memo;
    public int minCost1(int[] houses, int[][] cost, int m, int n, int target) {
        memo = new int[m][target+1][n+1];
        int result = costSum(houses, cost, target, 0, 0, 0);
        return result == MAX_COST ? -1 : result;
    }

    //neighborhoodCount 当前分组数
    int costSum(int[] houses, int[][] cost, int targetCount,
                int currentI, int neighborhoodCount, int prevColor) {
        //已是最后一个house
        if(currentI == houses.length)
            return targetCount == neighborhoodCount ? 0 : MAX_COST;

        //分组过大
        if (neighborhoodCount > targetCount) return MAX_COST;

        //找缓存
        if (memo[currentI][neighborhoodCount][prevColor] != 0)
            return memo[currentI][neighborhoodCount][prevColor];

        int minCost = MAX_COST;
        if (houses[currentI] == 0){
            for(int color = 1; color <= cost[0].length; color++){
                int nhCount = neighborhoodCount + (color != prevColor? 1:0);
                int tmpCost = cost[currentI][color-1] + costSum(houses, cost , targetCount,
                        currentI + 1, nhCount, color);
                minCost = Math.min(minCost, tmpCost);
            }
        }else{
            int nhCount = neighborhoodCount + (houses[currentI] != prevColor? 1:0);
            minCost = costSum(houses, cost , targetCount,
                    currentI + 1, nhCount, houses[currentI]);
        }
        memo[currentI][neighborhoodCount][prevColor] = minCost;
        return minCost;
    }

}
