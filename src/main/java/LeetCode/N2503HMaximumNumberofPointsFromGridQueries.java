package LeetCode;

import java.io.IOException;
import java.util.*;

import static java.time.LocalTime.now;

/**
 * You are given an m x n integer matrix grid and an array queries of size k.
 *
 * Find an array answer of size k such that for each integer queres[i] you start in the top left cell of the matrix and repeat the following process:
 *
 * If queries[i] is strictly greater than the value of the current cell that you are in, then you get one point if it is your first time visiting this cell, and you can move to any adjacent cell in all 4 directions: up, down, left, and right.
 * Otherwise, you do not get any points, and you end this process.
 * After the process, answer[i] is the maximum number of points you can get. Note that for each query you are allowed to visit the same cell multiple times.
 *
 * Return the resulting array answer.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,2,3],[2,5,7],[3,5,1]], queries = [5,6,2]
 * Output: [5,8,1]
 * Explanation: The diagrams above show which cells we visit to get points for each query.
 * Example 2:
 *
 *
 * Input: grid = [[5,2,1],[1,1,2]], queries = [3]
 * Output: [0]
 * Explanation: We can not get any points because the value of the top left cell is already greater than or equal to 3.
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 2 <= m, n <= 1000
 * 4 <= m * n <= 105
 * k == queries.length
 * 1 <= k <= 104
 * 1 <= grid[i][j], queries[i] <= 106
 */
public class N2503HMaximumNumberofPointsFromGridQueries {


    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");

        int[][] grid;
        int[] queries;

        grid = new int[][]{{1,2,1}, {1,1,2}};
        queries = new int[]{2};
        doRun(new int[]{3}, grid, queries);

        grid = new int[][]{{5,2,1}, {1,1,2}};
        queries = new int[]{3};
        doRun(new int[]{0}, grid, queries);


        grid = new int[][]{{1,2,3},{2,5,7},{3,5,1}};
        queries = new int[]{5,6,17,2};
        doRun(new int[]{5,8,9,1}, grid, queries);

        grid = new int[][]{{1,2,3},{2,5,7},{3,5,1}};
        queries = new int[]{5,6,2,2};
        doRun(new int[]{5,8,1,1}, grid, queries);
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int[] expect, int[][] grid, int[] queries) {
        int[] res = new N2503HMaximumNumberofPointsFromGridQueries().maxPoints(grid, queries);
        Arrays.equals(res, expect);
        boolean success = Arrays.equals(res, expect);
        System.out.println("["+success+"] expect:" + Arrays.toString(expect) + " res:" + Arrays.toString(res));
    }

    //3.BFS + Sort
    //Runtime: 188 ms, 100%; Memory: 57.8 MB, 100%
    //Time: O(L + L * log(L) + M * N * log(M * N)); Space: O(L + log(L) + M * N)
    //let L be the length of the queries
    public int[] maxPoints(int[][] grid, int[] queries) {
        int[] res = new int[queries.length];

        //Time: O(L + L * log(L)); Space: O(L + log(L)
        List<int[]> querylist = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) querylist.add(new int[]{queries[i],i});
        Collections.sort(querylist, Comparator.comparingInt(a -> a[0]));

        //Space: O(M * N);
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        queue.add(new int[]{0, 0, grid[0][0]});
        visited[0][0] = true;

        //Time: O(M * N * log(M * N));
        int idx = 0, sum = 0;
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int val = cell[2];

            while (idx < querylist.size() && val >= querylist.get(idx)[0])
                res[querylist.get(idx++)[1]] = sum;

            if (idx == querylist.size()) break;
            sum++;

            for (int[] offset : offsets) {
                int x = cell[0] + offset[0];
                int y = cell[1] + offset[1];
                if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) continue;
                if (visited[x][y] == true) continue;
                visited[x][y] = true;
                queue.offer(new int[]{x, y, grid[x][y]});
            }
        }

        while (idx < querylist.size()) res[querylist.get(idx++)[1]] = sum;
        return res;
    }


    //2.BFS + TreeMap
    //Runtime: 149 ms, 100%; Memory: 59.6 MB, 100%
    //Time: O(M * N * log(M * N) + L * log(M * N)); Space: O(M * N)
    //let L be the length of the queries
    public int[] maxPoints_2(int[][] grid, int[] queries) {
        int max = 0, sum = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        queue.offer(new int[]{0, 0, grid[0][0]});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            max = Math.max(cell[2], max);
            map.put(max, ++sum);

            for (int[] offset : offsets) {
                int x = offset[0] + cell[0];
                int y = offset[1] + cell[1];
                if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) continue;
                if (visited[x][y]) continue;
                visited[x][y] = true;
                queue.offer(new int[]{x, y, grid[x][y]});
            }
        }

        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            Integer cur = map.lowerKey(queries[i]);
            if (cur != null) res[i] = map.get(cur);
        }
        return res;
    }


    //1.Brute force + BFS
    //TLE
    //Time: O(L * M * N); Space: O(M * N + L)
    //let L be the length of the queries
    int[][] offsets = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public int[] maxPoints_1(int[][] grid, int[] queries) {
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++)
            res[i] = help_bfs(grid, queries[i]);
        return res;
    }

    private int help_bfs(int[][] grid, int query){
        int res = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<int[]> queue = new ArrayDeque<>();

        if (query > grid[0][0]) {
            visited[0][0] = true;
            queue.add(new int[]{0, 0});
            res++;
        }

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();

            for (int[] offset : offsets) {
                int x = cell[0] + offset[0];
                int y = cell[1] + offset[1];
                if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) continue;
                if (visited[x][y]) continue;
                visited[x][y] = true;
                if (query > grid[x][y]) {
                    queue.add(new int[]{x, y});
                    res++;
                }
            }
        }
        return res;
    }
}
