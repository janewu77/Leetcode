package LeetCode;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import static java.time.LocalTime.now;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
 * Output: 7
 * Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
 * Example 2:
 *
 * Input: grid = [[1,2,3],[4,5,6]]
 * Output: 12
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 100
 */
public class N64MMinimumPathSum {

    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");
        doRun(12, new int[][]{{1,2,3},{4,5,6}});
        doRun(7, new int[][]{{1,3,1},{1,5,1},{4,2,1}});
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[][] grid) {
        int res = new N64MMinimumPathSum().minPathSum(grid);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //3.DP in-place
    //Runtime: 3ms 68%; Memory: 45.9 MB 50%
    //Time: O(M * N); Space: O(1);
    public int minPathSum(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0) continue;
                int top = i - 1 >= 0 ? grid[i - 1][j] : Integer.MAX_VALUE;
                int left = j - 1 >= 0 ? grid[i][j - 1] : Integer.MAX_VALUE;
                grid[i][j] = grid[i][j] + Math.min(top, left);
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }


    //2.DP bottom-up 1D-array
    //Runtime: 3ms 68%; Memory: 45.9 MB 50%
    //Time: O(M * N); Space: O(N);
    public int minPathSum_2(int[][] grid) {
        int[] dp = new int[grid[0].length];
        dp[0] = grid[0][0];
        for (int j = 1; j < grid[0].length; j++)
            dp[j] = dp[j - 1] + grid[0][j];

        for (int i = 1; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0) continue;
                int left = j - 1 >= 0 ? dp[j - 1] : Integer.MAX_VALUE;
                dp[j] = grid[i][j] + Math.min(dp[j], left);
            }
        }
        return dp[grid[0].length - 1];
    }

    //1.DP bottom-up 2D-array
    //Runtime: 5ms 47%; Memory: 45.9 MB 43%
    //Time: O(M * N); Space: O(M * N);
    public int minPathSum_1(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0) continue;
                int top = i - 1 >= 0 ? dp[i - 1][j] : Integer.MAX_VALUE;
                int left = j - 1 >= 0 ? dp[i][j - 1] : Integer.MAX_VALUE;
                dp[i][j] = grid[i][j] + Math.min(top, left);
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }

}
