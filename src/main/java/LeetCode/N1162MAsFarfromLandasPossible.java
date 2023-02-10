package LeetCode;


import java.util.*;

import static java.time.LocalTime.now;

/**
 * Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell such that its distance to the nearest land cell is maximized, and return the distance. If no land or water exists in the grid, return -1.
 *
 * The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
 * Output: 2
 * Explanation: The cell (1, 1) is as far as possible from all the land with distance 2.
 * Example 2:
 *
 *
 * Input: grid = [[1,0,0],[0,0,0],[0,0,0]]
 * Output: 4
 * Explanation: The cell (2, 2) is as far as possible from all the land with distance 4.
 *
 *
 * Constraints:
 *
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 100
 * grid[i][j] is 0 or 1
 */
public class N1162MAsFarfromLandasPossible {

    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");

//        doRun(2, new int[][]{{0, 0, 1, 1, 1}, {0, 1, 1, 0, 0}, {0, 0, 1, 1, 0}, {1, 0, 0, 0, 0}, {1, 1, 0, 0, 1}});
        doRun(4, new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 1}});
        doRun(4, new int[][]{{1, 0, 0}, {0, 0, 0}, {0, 0, 0}});
        doRun(-1, new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}});
        doRun(-1, new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}});
        System.out.println(now());
        System.out.println("==================");
    }

    //contest: 92  330 ; 97 331
    //2547 1494 460 1908 1626
    static private void doRun(int expect, int[][] grid) {
        int res = new N1162MAsFarfromLandasPossible().maxDistance(grid);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //4.DP - Space optimized
    //Runtime: 7ms 97%; Memory: 43.3MB 79%
    //Time: O(N * N); Space: O(N * N)
    public int maxDistance(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                } else{
                    int tmp = Integer.MAX_VALUE;
                    if (i - 1 >= 0 && grid[i - 1][j] != Integer.MAX_VALUE)
                        tmp = Math.min(tmp, grid[i - 1][j] + 1);
                    if (j - 1 >= 0 && grid[i][j - 1] !=  Integer.MAX_VALUE)
                        tmp = Math.min(tmp, grid[i][j - 1] + 1);
                    grid[i][j] = tmp;
                }
            }
        }

        int res = -1;
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                if (grid[i][j] == 0) continue;
                if (i + 1 < grid.length && grid[i + 1][j] != Integer.MAX_VALUE)
                    grid[i][j] = Math.min(grid[i][j], grid[i + 1][j] + 1);
                if (j + 1 < grid[0].length && grid[i][j + 1] != Integer.MAX_VALUE)
                    grid[i][j] = Math.min(grid[i][j], grid[i][j + 1] + 1);

                if (grid[i][j] != Integer.MAX_VALUE)
                    res = Math.max(res, grid[i][j]);
            }
        }
        return res;
    }


    //3.DP - 2d array
    //Runtime: 7ms 97%; Memory: 43.4MB 60%
    //Time: O(N * N); Space: O(N * N)
    public int maxDistance_3(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    dp[i][j] = 0;
                } else{
                    dp[i][j] = Integer.MAX_VALUE;
                    if (i - 1 >= 0 && dp[i - 1][j] != Integer.MAX_VALUE)
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                    if (j - 1 >= 0 && dp[i][j - 1] !=  Integer.MAX_VALUE)
                        dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                }
            }
        }

        int res = -1;
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                if (grid[i][j] == 1) continue;
                if (i + 1 < grid.length && dp[i + 1][j] != Integer.MAX_VALUE)
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);
                if (j + 1 < grid[0].length && dp[i][j + 1] != Integer.MAX_VALUE)
                    dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1);

                if (dp[i][j] != Integer.MAX_VALUE)
                    res = Math.max(res, dp[i][j]);
            }
        }

        return res;
    }


    //2.BFS
    //Runtime: 15ms 72%; Memory: 43.4MB 70%
    //Time: O(N * N); Space: O(N * N)
    int[][] pos = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int maxDistance_2(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] > 0)
                    queue.add(new int[]{i, j});
            }
        }

        //BFS
        int step = 0;
        while (!queue.isEmpty()) {
            int qSize = queue.size();
            for (int i = 0; i < qSize; i++) {
                int[] node = queue.poll();
                for (int[] p : pos){
                    int x = p[0] + node[0];
                    int y = p[1] + node[1];
                    if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) continue;
                    if (grid[x][y] == 1) continue;

                    grid[x][y] = 1;//visited
                    queue.add(new int[]{x, y});
                }
            }
            step++;
        }
        return step == 1 ? -1 : step - 1;
    }


    //1.brute force
    //TLE
    //Time: O(N^4); Space: O(1)
    public int maxDistance_1(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] > 0)
                    update(grid, i, j);
            }
        }

        int res = -1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] < 0)
                    res = Math.max(res, -grid[i][j]);
            }
        }
        return res;
    }

    public void update(int[][] grid, int x ,int y){
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] <= 0) {
                    int distance = Math.abs(i - x) + Math.abs(j - y);
                    grid[i][j] = grid[i][j] == 0 ? -distance : - Math.min( -grid[i][j], distance);
                }
            }
        }
    }

}
