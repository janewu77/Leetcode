package LeetCode;


import java.util.LinkedList;
import java.util.Queue;

import static java.time.LocalTime.now;

/**
 * You are given an m x n grid where each cell can have one of three values:
 *
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 * Example 2:
 *
 * Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
 * Example 3:
 *
 * Input: grid = [[0,2]]
 * Output: 0
 * Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * grid[i][j] is 0, 1, or 2.
 */
public class N994MRottingOranges {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun(4, new int[][]{{2,1,1}, {1,1,0},{0,1,1}});
        doRun(-1, new int[][]{{2,1,1}, {0,1,1},{1,0,1}});
        doRun(0, new int[][]{{0,2}});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[][] grid) {
        int res = new N994MRottingOranges().orangesRotting(grid);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //2.BFS without queue
    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Rotting Oranges.
    //Memory Usage: 42.7 MB, less than 61.69% of Java online submissions for Rotting Oranges.
    //Time: O((N+M)*N*M); Space: O(1)
    public int orangesRotting(int[][] grid) {
        int currTime = -1, nextTime = 2;

        //Time: O((N+M)*N*M)
        while(nextTime != currTime ) {
            currTime = nextTime;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == currTime) {
                        if (i - 1 >= 0 && grid[i - 1][j] == 1) grid[i - 1][j] = nextTime = currTime + 1;
                        if (j - 1 >= 0 && grid[i][j - 1] == 1) grid[i][j - 1] = nextTime = currTime + 1;
                        if (j + 1 < grid[0].length && grid[i][j + 1] == 1) grid[i][j + 1] = nextTime = currTime + 1;
                        if (i + 1 < grid.length && grid[i + 1][j] == 1) grid[i + 1][j] = nextTime = currTime + 1;
                    }
                }
            }
        }

        //Time: O(N*M)
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                if (grid[i][j] == 1) return -1;

        return currTime - 2;
    }

    //1.BFS
    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Rotting Oranges.
    //Memory Usage: 41.6 MB, less than 94.63% of Java online submissions for Rotting Oranges.
    //Time: O(N * M); Space: O(N * M);
    public int orangesRotting_1(int[][] grid) {
        int res = 0;

        int totalFreshOranges = 0;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                totalFreshOranges += (grid[i][j] == 1) ? 1 : 0;
                if (grid[i][j] == 2) queue.add(new int[]{i, j});
            }
        }

        while (!queue.isEmpty() && totalFreshOranges > 0){

            int queueSize = queue.size();
            for (int k = 0; k < queueSize; k++) {
                int[] cell = queue.poll();
                int i = cell[0], j = cell[1];

                if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                    totalFreshOranges--; grid[i - 1][j] = 2;
                    queue.add(new int[]{i - 1, j});
                }
                if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                    totalFreshOranges--; grid[i][j - 1] = 2;
                    queue.add(new int[]{i, j - 1});
                }
                if (j + 1 < grid[0].length && grid[i][j + 1] == 1) {
                    totalFreshOranges--; grid[i][j + 1] = 2;
                    queue.add(new int[]{i, j + 1});
                }
                if (i + 1 < grid.length && grid[i + 1][j] == 1) {
                    totalFreshOranges--; grid[i + 1][j] = 2;
                    queue.add(new int[]{i + 1, j});
                }
            }
            res++;
        }

        if (totalFreshOranges != 0) return -1;
        return res;
    }


}
