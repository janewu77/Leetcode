package LeetCode;

import java.io.IOException;

import static java.time.LocalTime.now;

/**
 * You are given an m x n integer array grid where grid[i][j] could be:
 *
 * 1 representing the starting square. There is exactly one starting square.
 * 2 representing the ending square. There is exactly one ending square.
 * 0 representing empty squares we can walk over.
 * -1 representing obstacles that we cannot walk over.
 * Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
 * Output: 2
 * Explanation: We have the following two paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
 * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 * Example 2:
 *
 *
 * Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
 * Output: 4
 * Explanation: We have the following four paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
 * 2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
 * 3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
 * 4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
 * Example 3:
 *
 *
 * Input: grid = [[0,1],[2,0]]
 * Output: 0
 * Explanation: There is no path that walks over every empty square exactly once.
 * Note that the starting and ending square can be anywhere in the grid.
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 20
 * 1 <= m * n <= 20
 * -1 <= grid[i][j] <= 2
 * There is exactly one starting cell and one ending cell.
 */
public class N980HUniquePathsIII {


    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");

        doRun(2, new int[][]{{1,0,0,0}, {0,0,0,0}, {0,0,2,-1}});
        doRun(0, new int[][]{{0,1}, {2,0}});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[][] grid) {
        int res = new N980HUniquePathsIII().uniquePathsIII(grid);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //1.backtracking
    //Runtime: 1ms 85%; Memory: 39.9MB 78%
    //Time: O(3 ^ (M * N)); Space: O(M * N)
    public int uniquePathsIII(int[][] grid) {
        int count = 0, x = -1, y = -1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) count++;
                else if (grid[i][j] == 1) {
                    x = i; y = j;
                }
            }
        }
        return helper(grid, count + 1, x, y);
    }

    private int[][] offsets = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private int helper(int[][] grid, int count, int i, int j) {
        if (i >= grid.length || i < 0 || j >= grid[0].length || j < 0 || grid[i][j] == -1)
            return 0;

        if (grid[i][j] == 2)
            return count == 0 ? 1 : 0;

        int x = grid[i][j];
        grid[i][j] = -1;
        count--;
        int res = 0;
        for(int[] offset : offsets)
            res += helper(grid, count, offset[0] + i, offset[1] + j);

        grid[i][j] = x;
        return res;
    }

}
