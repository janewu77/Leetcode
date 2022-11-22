package LeetCode;


import java.util.*;

import static java.time.LocalTime.now;

/**
 * Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.
 *
 * A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
 *
 * All the visited cells of the path are 0.
 * All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
 * The length of a clear path is the number of visited cells of this path.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[0,1],[1,0]]
 * Output: 2
 * Example 2:
 *
 *
 * Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
 * Output: 4
 * Example 3:
 *
 * Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
 * Output: -1
 *
 *
 * Constraints:
 *
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 100
 * grid[i][j] is 0 or 1
 */

/**
 * H - [time: 120+
 */
public class N1091MShortestPathinBinaryMatrix {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        data2 = new int[][]{{0,1,0,0,0,0},{0,1,0,1,1,0}, {0, 1, 1, 0, 1, 0}, {0, 0, 0, 0, 1, 0}, {1, 1, 1, 1, 1, 0},{1,1,1,1,1,0}};
        doRun(14, data2);

        data2 = new int[][]{{0,1,0,1,0}, {1,0,0,0,1}, {0,0,1,1,1}, {0,0,0,0,0}, {1,0,1,0,0}};
        doRun(6, data2);


        data2 = new int[][]{{0,0,0}, {1,1,0}, {1,1,0}};
        doRun(4, data2);

        data2 = new int[][]{{0,1}, {1,0}};
        doRun(2, data2);

        data2 = new int[][]{{1,0,0}, {1,1,0}, {1,1,0}};
        doRun(-1, data2);

        data2 = new int[][]{{0,0,0,0,1,1}, {0,1,0,0,1,0}, {1,1,0,1,0,0}, {0,1,0,0,1,1}, {0,1,0,0,0,1}, {0,0,1,0,0,0}};
        doRun(7, data2);

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, int[][] grid) {
        int res = new N1091MShortestPathinBinaryMatrix().shortestPathBinaryMatrix(grid);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //3.estimate
    //Runtime: 34 ms, faster than 56.67% of Java online submissions for Shortest Path in Binary Matrix.
    //Memory Usage: 58.7 MB, less than 71.82% of Java online submissions for Shortest Path in Binary Matrix.
    //Time: O(N); Space:O(N);
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) return -1;
        if (grid.length == 1 &&  grid[0].length == 1) return 1;
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        Queue<int[]> queue1 = new LinkedList<>();
        Queue<int[]> queue2 = new LinkedList<>();
        Queue<int[]> queue3 = new LinkedList<>();
        Queue<int[]> tmp;
        queue1.add(new int[]{0, 0, 1, 1 + helper_estimate_3(0, 0, grid)});

        while (!queue1.isEmpty() || !queue2.isEmpty() || !queue3.isEmpty()) {
            int[] node;
            if (!queue1.isEmpty()) node = queue1.poll();
            else if (!queue2.isEmpty()) {
                node = queue2.poll();
                tmp = queue1; queue1 = queue2; queue2 = queue3; queue3 = tmp;
            } else {
                node = queue3.poll();
                tmp = queue1; queue1 = queue3; queue3 = tmp;
            }

            if (node[0] == node[1] && node[1] == grid.length - 1) return node[2];
            if (visited[node[0]][node[1]] == true) continue;
            visited[node[0]][node[1]] = true;

            int distance = node[2] + 1;
            //8: neighbours
            for (int i = node[0] - 1; i <= node[0] + 1; i++){
                for (int j = node[1] - 1; j <= node[1] + 1; j++) {
                    if (i >= 0 && j >= 0 && i < grid.length && j < grid[0].length) {
                        if (visited[i][j] == true || grid[i][j] == 1) continue;
                        int estimate = distance + helper_estimate_3(i, j, grid);
                        int[] nextNode = new int[]{i, j, distance, estimate};
                        if (estimate == node[3]) queue1.add(nextNode);
                        else if (estimate == node[3] + 1) queue2.add(nextNode);
                        else queue3.add(nextNode);
                    }
                }
            }
        }//End while
        return -1;
    }
    private int helper_estimate_3(int i, int j, int[][] grid) {
        return Math.max(grid.length - 1 - i, grid[0].length - 1 - j);
    }

    //2.min heap
    //Runtime: 71 ms, faster than 18.83% of Java online submissions for Shortest Path in Binary Matrix.
    //Memory Usage: 55.3 MB, less than 82.91% of Java online submissions for Shortest Path in Binary Matrix.
    //Time: O(N * logN); Space:O(N);
    public int shortestPathBinaryMatrix_2(int[][] grid) {
        if (grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) return -1;
        if (grid.length == 1 &&  grid[0].length == 1) return 1;
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        Queue<int[]> heap = new PriorityQueue<>((a, b) ->
                a[3] != b[3] ? a[3] - b[3] : (a[2] != b[2] ? a[2] - b[2] : (a[1] == b[1] ? a[0] - b[0] : a[1] - b[1])));
        heap.add(new int[]{0, 0, 1, 1 + helper_estimate_2(0, 0, grid)});

        while (!heap.isEmpty()) {
            int[] node = heap.poll();

            if (node[0] == node[1] && node[1] == grid.length - 1) return node[2];
            if (visited[node[0]][node[1]] == true) continue;
            visited[node[0]][node[1]] = true;

            int distance = node[2] + 1;
            //8: neighbours
            for (int i = node[0] - 1; i <= node[0] + 1; i++){
                for (int j = node[1] - 1; j <= node[1] + 1 ; j++) {
                    if (i >= 0 && j >= 0 && i < grid.length && j < grid[0].length) {
                        if (visited[i][j] == true || grid[i][j] == 1) continue;
                        int[] nextNode = new int[]{i, j, distance, distance + helper_estimate_2(i, j, grid)};
                        heap.add(nextNode);
                    }
                }
            }
        }//End while
        return -1;
    }

    private int helper_estimate_2(int i, int j, int[][] grid) {
        return Math.max(grid.length - 1 - i, grid[0].length - 1 - j);
    }

    //1.BFS , Overwriting Input
    //Runtime: 11 ms, faster than 99.82% of Java online submissions for Shortest Path in Binary Matrix.
    //Memory Usage: 43.7 MB, less than 92.62% of Java online submissions for Shortest Path in Binary Matrix.
    //Time: O(N); Space:O(N)
    public int shortestPathBinaryMatrix_1(int[][] grid) {
        if (grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) return -1;
        if (grid.length == 1 &&  grid[0].length == 1) return 1;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});//i,j
        grid[0][0] = 1;

        while(!queue.isEmpty()) {
            int[] node = queue.poll();
            int distance = grid[node[0]][node[1]] + 1;

            //neighbours
            for (int i = node[0] - 1; i <= node[0] + 1; i++){
                for (int j = node[1] - 1; j <= node[1] + 1 ; j++) {
                    if (i >= 0 && j >= 0 && i < grid.length && j < grid[0].length) {
                        if (i == j && j == grid.length - 1) return distance;
                        if (grid[i][j] == 0) {
                            queue.offer(new int[]{i, j});
                            grid[i][j] = distance;
                        }
                    }
                }
            }
        }
        return -1;
    }

}
