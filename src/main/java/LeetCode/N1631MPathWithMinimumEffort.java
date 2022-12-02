package LeetCode;

import java.io.IOException;
import java.util.*;

import static java.time.LocalTime.now;

/**
 * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
 *
 * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
 *
 * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
 * Output: 2
 * Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
 * This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
 * Example 2:
 *
 *
 *
 * Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
 * Output: 1
 * Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
 * Example 3:
 *
 *
 * Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * Output: 0
 * Explanation: This route does not require any effort.
 *
 *
 * Constraints:
 *
 * rows == heights.length
 * columns == heights[i].length
 * 1 <= rows, columns <= 100
 * 1 <= heights[i][j] <= 106
 */
public class N1631MPathWithMinimumEffort {

    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");

        //[[1,2,2],[3,8,2],[5,3,5]]
        doRun(2, new int[][]{{1,2,2},{3,8,2},{5,3,5}});
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[][] heights) {
        int res = new N1631MPathWithMinimumEffort().minimumEffortPath(heights);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    int[][] offsets = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    //5.Binary Search + DFS
    //Runtime: 73 ms, faster than 81.94% of Java online submissions for Path With Minimum Effort.
    //Memory Usage: 53.5 MB, less than 80.66% of Java online submissions for Path With Minimum Effort.
    //Time: O(N); Space: O(N);
    public int minimumEffortPath(int[][] heights) {
        int max = 1_000_000;
        int left = 0, right = max;
        int res = right;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (helper_dfs(heights, 0, 0, mid, new boolean[heights.length][heights[0].length])) {
                res = Math.min(res, mid);
                right = mid - 1;
            } else left = mid + 1;
        }
        return res;
    }
    private boolean helper_dfs(int[][] heights, int i, int j, int k, boolean[][] visited) {
        if (i == heights.length - 1 && j == heights[0].length - 1) return true;
        visited[i][j] = true;
        for(int[] offset : offsets) {
            int x = i + offset[0], y = j + offset[1];
            if (x < 0 || y < 0 || x >= heights.length || y >= heights[0].length) continue;
            if (visited[x][y]) continue;
            if (Math.abs(heights[i][j] - heights[x][y]) <= k && helper_dfs(heights, x, y, k, visited))
                return true;
        }
        return false;
    }


    //4.Binary Search + BFS
    //Runtime: 157 ms, faster than 28.60% of Java online submissions for Path With Minimum Effort.
    //Memory Usage: 42.1 MB, less than 99.67% of Java online submissions for Path With Minimum Effort.
    //Time: O(log(1_000_000) * N); Space: O(N);
    //Time: O(N); Space: O(N);
    public int minimumEffortPath_4(int[][] heights) {
//        int max = 0;
//        for (int i = 0; i < heights.length ; i++) {
//            for (int j = 0; j < heights[0].length ; j++) {
//                if (j + 1 < heights[0].length)
//                    max = Math.max(max, Math.abs(heights[i][j] - heights[i][j + 1]));
//                if (i + 1 < heights.length)
//                    max = Math.max(max, Math.abs(heights[i][j] - heights[i + 1][j]));
//            }
//        }
        int max = 1_000_000;
        int left = 0, right = max;
        int res = right;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (helper_bfs(heights, mid)) {
                res = Math.min(res, mid);
                right = mid - 1;
            } else left = mid + 1;
        }
        return res;
    }

    //Time: O(N); Space:O(N)
    private boolean helper_bfs(int[][] heights, int k) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0});

        boolean[][] visited = new boolean[heights.length][heights[0].length];
        visited[0][0] = true;

        while (!queue.isEmpty()){
            int[] cell = queue.poll();
            if (cell[0] == heights.length - 1 && cell[1] == heights[0].length - 1) return true;

            for(int[] offset : offsets){
                int x = cell[0] + offset[0], y = cell[1] + offset[1];
                if (x < 0 || y < 0 || x >= heights.length || y >= heights[0].length) continue;
                if (visited[x][y]) continue;

                if (Math.abs(heights[cell[0]][cell[1]] - heights[x][y]) <= k) {
                    queue.addLast(new int[]{x, y});
                    visited[x][y] = true;
                }
            }
        }
        return false;
    }


    //3. union find
    //Runtime: 203 ms, faster than 19.90% of Java online submissions for Path With Minimum Effort.
    //Memory Usage: 68.5 MB, less than 25.25% of Java online submissions for Path With Minimum Effort.
    //Time: O(N * log(N)); Space: O(N)
    public int minimumEffortPath_3(int[][] heights) {
        int n = heights.length * heights[0].length;
        if (n == 1) return 0;
        int first = 0, last = n - 1;

        //Time: O(N); Space:O(N)
        UnionFind uf = new UnionFind(n);

        //Time: O(N); Space:O(N)
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < heights.length ; i++) {
            int idx = i * heights[0].length;
            for (int j = 0; j < heights[0].length ; j++) {
                if (j + 1 < heights[0].length)
                    list.add(new int[]{ idx + j, idx + j + 1, Math.abs(heights[i][j] - heights[i][j + 1])});
                if (i + 1 < heights.length)
                    list.add(new int[]{ idx + j, idx + heights[0].length + j, Math.abs(heights[i][j] - heights[i + 1][j])});
            }
        }

        //Time: O(N * log(N)); Space: O(log(N))
        Collections.sort(list, Comparator.comparingInt(a -> a[2]));

        //Time: O(N * log(N))
        for(int[] node : list) {
            uf.union(node[0], node[1]);
            if (uf.connected(first, last)) return node[2];
        }
        return -1;
    }
    public class UnionFind {

        private int[] group;
        private int[] rank;

        //Time: O(N)
        //N is the number of vertices in the graph.
        public UnionFind(int size) {
            group = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                group[i] = i;rank[i] = 1;
            }
        }

        //Time: O(α(N))
        public int find(int x) {
            return x == group[x] ? x: (group[x] = find(group[x]));
        }

        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return;

            if (rank[rootX] < rank[rootY]){
                group[rootX] = rootY;
            }else{
                group[rootY] = rootX;
                if (rank[rootX] == rank[rootY]) rank[rootX]++;
            }

        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }
    }


    //2.backtracking
    //TLE
    //Time: O(3 ^ N); Space: O(N)
    public int minimumEffortPath_2(int[][] heights) {
        return helper(heights, 0, 0);
    }

    private int helper(int[][] heights, int i, int j){
        if (i == heights.length - 1 && j == heights[0].length - 1) return 0;

        int res = Integer.MAX_VALUE;
        int currHeight = heights[i][j];
        heights[i][j] = 0;
        for (int[] offset : offsets){
            int x = i + offset[0], y = j + offset[1];
            if (x < 0 || y < 0 || x >= heights.length || y >= heights[0].length) continue;
            if (heights[x][y] == 0) continue;
            res = Math.min(res, Math.max(Math.abs(currHeight - heights[x][y]), helper(heights, x, y)));
        }
        heights[i][j] = currHeight;
        return res;
    }

    //1.Dijkstra
    //Runtime: 56 ms, faster than 91.36% of Java online submissions for Path With Minimum Effort.
    //Memory Usage: 42.9 MB, less than 87.46% of Java online submissions for Path With Minimum Effort.
    //Time: O(N * log(N)); Space: O(N)
    public int minimumEffortPath_1(int[][] heights) {
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        heap.add(new int[]{0, 0, 0});

        int[][] res = new int[heights.length][heights[0].length];
        for (int i = 0; i < res.length; i++) Arrays.fill(res[i], Integer.MAX_VALUE);
        res[0][0] = 0;

        while (!heap.isEmpty()){
            int[] cell = heap.poll();

            if (cell[0] == heights.length - 1 && cell[1] == heights[0].length - 1) return cell[2];

            for (int[] offset : offsets){
                int x = cell[0] + offset[0], y = cell[1] + offset[1];
                if (x < 0 || y < 0 || x >= heights.length || y >= heights[0].length) continue;

                if (res[x][y] <= cell[2] || res[x][y] <= Math.abs(heights[cell[0]][cell[1]] - heights[x][y])) continue;

                int maxEffort = Math.max(cell[2], Math.abs(heights[cell[0]][cell[1]] - heights[x][y]));
                if (maxEffort < res[x][y]){
                    res[x][y] = maxEffort;
                    heap.add(new int[]{x, y, res[x][y]});
                }
            }
        }
        return res[heights.length - 1][heights[0].length - 1];
    }
}
