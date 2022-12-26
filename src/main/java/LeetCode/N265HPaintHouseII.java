package LeetCode;

import java.io.IOException;
import java.util.PriorityQueue;

import static java.time.LocalTime.now;

/**
 * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by an n x k cost matrix costs.
 *
 * For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on...
 * Return the minimum cost to paint all houses.
 *
 *
 *
 * Example 1:
 *
 * Input: costs = [[1,5,3],[2,9,4]]
 * Output: 5
 * Explanation:
 * Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5;
 * Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.
 * Example 2:
 *
 * Input: costs = [[1,3],[2,4]]
 * Output: 5
 *
 *
 * Constraints:
 *
 * costs.length == n
 * costs[i].length == k
 * 1 <= n <= 100
 * 2 <= k <= 20
 * 1 <= costs[i][j] <= 20
 *
 *
 * Follow up: Could you solve it in O(nk) runtime?
 */
//256. Paint House
//265. Paint House II
//1473. Paint House III
public class N265HPaintHouseII {

    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");

        doRun(5, new int[][]{{1,5,3}, {2,9,4}});

        int[][] data = new int[][]{{7,19,11,3,7,15,17,5,6,18,1,15,18,11},
                {13,18,18,8,13,12,11,13,4,8,2,4,5,20},
                {14,5,18,4,7,6,1,6,11,6,16,6,13,17},
                {18,17,11,3,12,4,8,6,2,7,10,9,19,3},
                {4,3,2,14,11,15,18,1,17,1,6,14,14,9},
                {9,13,15,14,5,1,1,6,11,15,16,12,10,18},
                {19,2,11,3,13,4,13,7,16,16,20,18,20,8},
                {8,19,20,9,18,13,17,1,2,4,3,20,15,9},
                {9,10,11,6,14,20,4,1,5,15,13,10,13,5},
                {13,11,9,11,9,16,3,19,1,11,6,7,12,13},
                {14,1,15,14,11,12,7,14,12,11,6,9,5,5}};
        doRun(17, data);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[][] costs) {
        int res = new N265HPaintHouseII().minCostII(costs);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //6.Optimized Space
    //Runtime: 2ms 99%; Memory: 42.3MB 86%
    //Time: O(N * K); Space: O(1)
    public int minCostII(int[][] costs) {
        int minFirst = 0, minSecond = 0, minColor = 0;

        for (int i = 0; i < costs.length; i++) {
            int lastMinFirst = minFirst, lastMinSecond = minSecond;
            int lastMinColor = minColor;

            minFirst = Integer.MAX_VALUE; minSecond = Integer.MAX_VALUE;
            minColor = -1;
            for (int color = 0; color < costs[0].length; color++) {
                int currMinCost = costs[i][color] + (color == lastMinColor ? lastMinSecond : lastMinFirst);

                if (currMinCost <= minFirst){
                    minSecond = minFirst;
                    minFirst = currMinCost;
                    minColor = color;
                } else if (currMinCost <= minSecond) {
                    minSecond = currMinCost;
                }
            }
        }
        return minFirst;
    }


    //5.Optimized Time
    //Runtime: 2ms 99%; Memory: 42.5MB 76%
    //Time: O(N * K); Space: O(K)
    public int minCostII_5(int[][] costs) {
        int[] dp = new int[costs[0].length];
        int minFirst = 0, minSecond = 0;

        for (int i = 0; i < costs.length; i++) {
            int[] lastDP = dp;
            dp = new int[costs[0].length];

            int lastMinFirst = minFirst, lastMinSecond = minSecond;
            minFirst = Integer.MAX_VALUE; minSecond = Integer.MAX_VALUE;
            for (int color = 0; color < costs[0].length; color++) {
                dp[color] = costs[i][color] + (lastMinFirst == lastDP[color] ? lastMinSecond : lastMinFirst);

                if (dp[color] <= minFirst){
                    minSecond = minFirst;
                    minFirst = dp[color];
                }else if (dp[color] <= minSecond){
                    minSecond = dp[color];
                }
            }
        }
        return minFirst;
    }


    //4.heap
    //Runtime: 7 ms 48%; Memory: 42.9MB 71%;
    //Time: O(K * logK + N * K * logK + K); Space: O(K)
    //Time: O(N * K * logK); Space: O(K)
    public int minCostII_4(int[][] costs) {
        int[] dp = new int[costs[0].length];
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.add(0);heap.add(0);

        for (int i = 0; i < costs.length; i++) {
            int[] lastDP = dp;
            dp = new int[costs[0].length];

            PriorityQueue<Integer> lastHeap = heap;
            heap = new PriorityQueue<>();

            for (int color = 0; color < costs[0].length; color++) {
                if (lastHeap.peek() == lastDP[color]){
                    int tmp = lastHeap.poll();
                    dp[color] = costs[i][color] + lastHeap.peek();
                    lastHeap.add(tmp);
                }else{
                    dp[color] = costs[i][color] + lastHeap.peek();
                }
                heap.add(dp[color]);
            }
        }

        return heap.peek();
    }

    //3.DP bottom-up 1D-array
    //Runtime: 2ms 99%; Memory: 42.1MB 95%;
    //Time: O(N * K * K); Space: O(K);
    public int minCostII_3(int[][] costs) {
        int[] dp = costs[0];

        for (int i = 1; i < costs.length; i++) {
            int[] lastDP = dp;
            dp = new int[costs[0].length];
            for (int color = 0; color < costs[0].length; color++) {
                int minCost = Integer.MAX_VALUE;
                for (int j = 0; j < costs[0].length; j++) {
                    if (j == color) continue;
                    minCost = Math.min(minCost, lastDP[j]);
                }
                dp[color] = costs[i][color] + minCost;
            }
        }

        int res = Integer.MAX_VALUE;
        for (int color = 0; color < costs[0].length; color++)
            res = Math.min(res, dp[color]);

        return res;
    }

    //2.DP bottom-up 2D-array
    //Runtime: 3ms 85%; Memory: 42.1MB 95%;
    //Time: O(N * K * K); Space: O(N * K);
    public int minCostII_2(int[][] costs) {
        int[][] dp = new int[costs.length][costs[0].length];
        dp[0] = costs[0];

        for (int i = 1; i < costs.length; i++) {
            for (int color = 0; color < costs[0].length; color++) {
                int minCost = Integer.MAX_VALUE;
                for (int j = 0; j < costs[0].length; j++) {
                    if (j == color) continue;
                    minCost = Math.min(minCost, dp[i - 1][j]);
                }
                dp[i][color] = costs[i][color] + minCost;
            }
        }
        int res = Integer.MAX_VALUE;
        for (int color = 0; color < costs[0].length; color++)
            res = Math.min(res, dp[costs.length - 1][color]);

        return res;
    }

    //1.DP top-down
    //Runtime: 3ms 85%; Memory: 42.1MB 95%;
    //Time: O(N * K * K); Space: O(N * K);
    public int minCostII_1(int[][] costs) {
        int res = Integer.MAX_VALUE;

        int[][] memo = new int[costs.length][costs[0].length];
        for (int color = 0; color < costs[0].length; color++)
            res = Math.min(res, helper_dp(costs, costs.length - 1, color, memo));

        return res;
    }

    private int helper_dp(int[][] costs, int d, int color, int[][] memo){
        if (d == 0) return costs[d][color];

        if (memo[d][color] != 0) return memo[d][color];

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < costs[0].length; i++) {
            if (i == color) continue;
            res = Math.min(res, helper_dp(costs, d - 1, i, memo));
        }
        memo[d][color] = res + costs[d][color];
        return memo[d][color];
    }
}
