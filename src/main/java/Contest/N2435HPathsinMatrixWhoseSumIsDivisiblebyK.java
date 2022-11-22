package Contest;

import static java.time.LocalTime.now;


/**
 * You are given a 0-indexed m x n integer matrix grid and an integer k. You are currently at position (0, 0) and you want to reach position (m - 1, n - 1) moving only down or right.
 *
 * Return the number of paths where the sum of the elements on the path is divisible by k. Since the answer may be very large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[5,2,4],[3,0,5],[0,7,2]], k = 3
 * Output: 2
 * Explanation: There are two paths where the sum of the elements on the path is divisible by k.
 * The first path highlighted in red has a sum of 5 + 2 + 4 + 5 + 2 = 18 which is divisible by 3.
 * The second path highlighted in blue has a sum of 5 + 3 + 0 + 5 + 2 = 15 which is divisible by 3.
 * Example 2:
 *
 *
 * Input: grid = [[0,0]], k = 5
 * Output: 1
 * Explanation: The path highlighted in red has a sum of 0 + 0 = 0 which is divisible by 5.
 * Example 3:
 *
 *
 * Input: grid = [[7,3,4,9],[2,3,6,2],[2,3,7,0]], k = 1
 * Output: 10
 * Explanation: Every integer is divisible by 1 so the sum of the elements on every possible path is divisible by k.
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 5 * 104
 * 1 <= m * n <= 5 * 104
 * 0 <= grid[i][j] <= 100
 * 1 <= k <= 50
 */

public class N2435HPathsinMatrixWhoseSumIsDivisiblebyK {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun(1, new int[][]{{1}, {5},{3}, {7}, {3}, {2}, {3}, {5}}, 29);

        doRun(2, new int[][]{{5,2,4}, {3,0,5},{0,7,2}}, 3);
        doRun(10, new int[][]{{7,3,4,9}, {2,3,6,2},{2,3,7,0}}, 1);

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, int[][] grid, int k) {
        int res = new N2435HPathsinMatrixWhoseSumIsDivisiblebyK().numberOfPaths(grid, k);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 124 ms, faster than 100.00% of Java online submissions for Paths in Matrix Whose Sum Is Divisible by K.
    //Memory Usage: 70.9 MB, less than 80.00% of Java online submissions for Paths in Matrix Whose Sum Is Divisible by K.
    //dp - 2d array
    //Time: O(M * N * K); Space: O(N * K)
    public int numberOfPaths(int[][] grid, int k) {
        int MODULE = 1_000_000_007;
        int[][] dp = new int[grid[0].length][k];
        dp[0][grid[0][0] % k] = 1;
        int[][] dp2;
        for (int i = 0; i < grid.length; i++){
            dp2 = new int[grid[0].length][k];
            for (int j = 0; j < grid[0].length; j++){
                if (i == 0 && j == 0) {
                    dp2[0][grid[0][0] % k] = 1;
                    continue;
                }
                for (int kk = 0; kk < k; kk++) {
                    int x = (kk + grid[i][j]) % k;
                    if (i != 0) dp2[j][x] = (dp2[j][x] + dp[j][kk]) % MODULE;
                    if (j != 0) dp2[j][x] = (dp2[j][x] + dp2[j - 1][kk]) % MODULE;
                }
            }
            dp = dp2;
        }
        return dp[grid[0].length - 1][0];
    }

    //Runtime: 159 ms, faster than 80.00% of Java online submissions for Paths in Matrix Whose Sum Is Divisible by K.
    //Memory Usage: 101.6 MB, less than 80.00% of Java online submissions for Paths in Matrix Whose Sum Is Divisible by K.
    //dp - 3d array
    //Time: O(M * N * K); Space: O(M * N * K)
    public int numberOfPaths_1(int[][] grid, int k) {
        int MODULE = 1_000_000_007;
        int[][][] dp = new int[grid.length][grid[0].length][k];
        dp[0][0][grid[0][0] % k] = 1;

        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (i == 0 && j == 0) continue;
                for (int kk = 0; kk < k; kk++) {
                    int x = (kk + grid[i][j]) % k;
                    dp[i][j][x] = 0;
                    if (i != 0) dp[i][j][x] = (dp[i][j][x] + dp[i-1][j][kk]) % MODULE;
                    if (j != 0) dp[i][j][x] = (dp[i][j][x] + dp[i][j-1][kk]) % MODULE;
                }
            }
        }
        return dp[grid.length - 1][grid[0].length - 1][0];
    }

}
