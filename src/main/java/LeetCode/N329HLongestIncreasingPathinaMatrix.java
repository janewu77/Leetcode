package LeetCode;


import java.util.*;

import static java.time.LocalTime.now;

/**
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 *
 * From each cell, you can either move in four directions: left, right, up, or down. You may not
 * move diagonally or move outside the boundary (i.e., wrap-around is not allowed).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 * Example 2:
 *
 *
 * Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 * Example 3:
 *
 * Input: matrix = [[1]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * 0 <= matrix[i][j] <= 231 - 1
 */

public class N329HLongestIncreasingPathinaMatrix {


    public static void main(String[] args) {

        System.out.println(now());
        int[][] data;

        System.out.println("========doRun_demo==========");
        data = new int[][]{{9,9,4},{6,6,8},{2,1,1}};
        doRun_demo(4, data);

        data = new int[][]{{3,4,5},{3,2,6},{2,2,1}};
        doRun_demo(4, data);

        data = new int[][]{{1}};
        doRun_demo(1, data);

        data = new int[][]{{0,1,2}, {5,4,3},{6,7,8}, {0,0,0}};
        doRun_demo(9, data);

        data = new int[][]{
                {0,1,2,3,4,5,6,7,8,9},
                {19,18,17,16,15,14,13,12,11,10},
                {20,21,22,23,24,25,26,27,28,29},
                {39,38,37,36,35,34,33,32,31,30},
                {40,41,42,43,44,45,46,47,48,49},
                {59,58,57,56,55,54,53,52,51,50},
                {60,61,62,63,64,65,66,67,68,69},
                {79,78,77,76,75,74,73,72,71,70},
                {80,81,82,83,84,85,86,87,88,89},
                {99,98,97,96,95,94,93,92,91,90},
                {100,101,102,103,104,105,106,107,108,109},
                {119,118,117,116,115,114,113,112,111,110},
                {120,121,122,123,124,125,126,127,128,129},
                {139,138,137,136,135,134,133,132,131,130},
                {0,0,0,0,0,0,0,0,0,0}
        };
        doRun_demo(140, data);

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun_demo(int expect, int[][] matrix) {
        int res = new N329HLongestIncreasingPathinaMatrix().longestIncreasingPath(matrix);
//        String res = comm.toString(res1);
//        String res = Arrays.stream(res1).mapToObj(i-> String.valueOf(i)).collect(Collectors.joining(","));
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    //from solution
    //Runtime: 18 ms, faster than 30.47% of Java online submissions for Longest Increasing Path in a Matrix.
    //Memory Usage: 42.1 MB, less than 99.27% of Java online submissions for Longest Increasing Path in a Matrix.
    //db : 剥洋葱式的一层层去掉外层（和BFS的层概念一样）。
    private static final int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int longestIncreasingPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (m == 0) return 0;

        // padding the matrix with zero as boundaries
        // assuming all positive integer, otherwise use INT_MIN as boundaries
        int[][] matrix = new int[m + 2][n + 2];
        for (int i = 0; i < m; ++i)
            System.arraycopy(grid[i], 0, matrix[i + 1], 1, n);

        // calculate outdegrees
        int[][] outdegree = getOutdegrees(matrix);

        // find leaves who have zero out degree as the initial level
        n += 2; m += 2;
        Queue<int[]> leaves = new LinkedList<>();
        for (int i = 1; i < m - 1; ++i)
            for (int j = 1; j < n - 1; ++j)
                if (outdegree[i][j] == 0) leaves.add(new int[]{i, j});

        // remove leaves level by level in topological order
        int height = 0;
        while (!leaves.isEmpty()) {
            height++;
            int size = leaves.size();
            for(int i = 0; i < size; i++){
                int[] node = leaves.poll();
                for (int[] d:dir) {
                    int x = node[0] + d[0], y = node[1] + d[1];
                    if (matrix[node[0]][node[1]] > matrix[x][y])
                        if (--outdegree[x][y] == 0)
                            leaves.add(new int[]{x, y});
                }
            }
        }
        return height;
    }

    // calculate outdegrees
    private int[][] getOutdegrees(int[][] matrix) {
        int[][] outdegree = new int[matrix.length][matrix[0].length];
        for (int i = 1; i < matrix.length-1; ++i)
            for (int j = 1; j < matrix[0].length-1; ++j)
                for (int[] d: dir)
                    if (matrix[i][j] < matrix[i + d[0]][j + d[1]])
                        outdegree[i][j]++;
        return outdegree;
    }


    //same as longestIncreasingPath_1
    // 1。去掉了queue 2。memo内移。
    //Runtime: 8 ms, faster than 98.06% of Java online submissions for Longest Increasing Path in a Matrix.
    //Memory Usage: 42.5 MB, less than 95.11% of Java online submissions for Longest Increasing Path in a Matrix.
    //DFS + memo
    //Time: O(N*M); Space: O(N*M)
    public int longestIncreasingPath_2(int[][] matrix) {
        int[][] memo = new int[matrix.length][matrix[0].length];
        int res = 0;
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                res = Math.max(res, helper_2(matrix, i, j, memo));
        return res;
    }

    private int helper_2(int[][] matrix, int i, int j, int[][] memo) {
        if (memo[i][j] > 0) return memo[i][j];

        //up left
        if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j])
            memo[i][j] = Math.max(memo[i][j], helper_2(matrix, i - 1, j, memo));

        if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j])
            memo[i][j] = Math.max(memo[i][j], helper_2(matrix, i, j - 1, memo));

        //down right
        if (i + 1 < matrix.length && matrix[i + 1][j] > matrix[i][j])
            memo[i][j] = Math.max(memo[i][j], helper_2(matrix, i + 1, j, memo));

        if (j + 1 < matrix[0].length && matrix[i][j + 1] > matrix[i][j])
            memo[i][j] = Math.max(memo[i][j], helper_2(matrix, i, j + 1, memo));

        return ++memo[i][j];
    }


    //Runtime: 31 ms, faster than 18.07% of Java online submissions for Longest Increasing Path in a Matrix.
    //Memory Usage: 42.5 MB, less than 93.49% of Java online submissions for Longest Increasing Path in a Matrix.
    //DFS + memo
    //Time: O(N*M); Space: O(N*M)
    public int longestIncreasingPath_1(int[][] matrix) {
        int res = 0;
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                res = Math.max(res, helper1(matrix, new int[]{i, j}));
        return res;
    }

    private Map<Integer, Integer> map = new HashMap<>();
    //Time: O(4)
    private int helper1(int[][] matrix, int[]pos) {
        int key = pos[0] * 1000 + pos[1];
        if (map.containsKey(key)) return map.get(key);

        Queue<int[]> queue = new LinkedList<>();
        int i = pos[0], j = pos[1];
        int value = matrix[i][j];

        //up left
        if (i - 1 >= 0 && matrix[i - 1][j] > value) queue.add(new int[]{i - 1, j});
        if (j - 1 >= 0 && matrix[i][j - 1] > value) queue.add(new int[]{i, j - 1});

        //down right
        if (i + 1 < matrix.length && matrix[i + 1][j] > value) queue.add(new int[]{i + 1, j});
        if (j + 1 < matrix[0].length && matrix[i][j + 1] > value) queue.add(new int[]{i, j + 1});
        //if (queue.isEmpty()) return 0;

        int res = 0;
        while (!queue.isEmpty())
            res = Math.max(res, helper1(matrix, queue.poll()));

        map.put(key, ++res);
        return res;
    }
}
