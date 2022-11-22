package LeetCode;

import utils.comm;

import java.util.*;

import static java.time.LocalTime.now;

/**
 * There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean.
 * The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches
 * the island's right and bottom edges.
 *
 * The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights
 * where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
 *
 * The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east,
 * and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow
 * from any cell adjacent to an ocean into the ocean.
 *
 * Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow
 * from cell (ri, ci) to both the Pacific and Atlantic oceans.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 * Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 * Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:
 * [0,4]: [0,4] -> Pacific Ocean
 *        [0,4] -> Atlantic Ocean
 * [1,3]: [1,3] -> [0,3] -> Pacific Ocean
 *        [1,3] -> [1,4] -> Atlantic Ocean
 * [1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean
 *        [1,4] -> Atlantic Ocean
 * [2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean
 *        [2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
 * [3,0]: [3,0] -> Pacific Ocean
 *        [3,0] -> [4,0] -> Atlantic Ocean
 * [3,1]: [3,1] -> [3,0] -> Pacific Ocean
 *        [3,1] -> [4,1] -> Atlantic Ocean
 * [4,0]: [4,0] -> Pacific Ocean
 *        [4,0] -> Atlantic Ocean
 * Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.
 * Example 2:
 *
 * Input: heights = [[1]]
 * Output: [[0,0]]
 * Explanation: The water can flow from the only cell to the Pacific and Atlantic oceans.
 *
 *
 * Constraints:
 *
 * m == heights.length
 * n == heights[r].length
 * 1 <= m, n <= 200
 * 0 <= heights[r][c] <= 105
 */
public class N417MPacificAtlanticWaterFlow {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        data2 = new int[][]{{1,2,3},{8,9,4},{7,6,5}};
        doRun_demo("[[0, 2],[1, 0],[1, 1],[1, 2],[2, 0],[2, 1],[2, 2]]", data2);

        data2 = new int[][]{{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        doRun_demo("[[0, 4],[1, 3],[1, 4],[2, 2],[3, 0],[3, 1],[4, 0]]", data2);

        data2 = new int[][]{{1}};
        doRun_demo("[[0, 0]]", data2);

        data2 = new int[][]{{1,2,3,4},{12,13,14,5},{11,16,15,6},{10,9,8,7}};
        doRun_demo("[[0, 3],[1, 0],[1, 1],[1, 2],[1, 3],[2, 0],[2, 1],[2, 2],[2, 3],[3, 0],[3, 1],[3, 2],[3, 3]]", data2);

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun_demo(String expect, int[][] heights) {
        List<List<Integer>> res1 = new N417MPacificAtlanticWaterFlow().pacificAtlantic(heights);
        String res = comm.toString(res1); //List<List<Integer>>
//        String res = Arrays.stream(res1).mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);

        //int[][] res1
        //        sb.append("[");
        //        for(int i = 0; i<res1.length; i++) {
        //            String str = Arrays.toString(res1[i]);
        //            sb.append(str).append(",");
        //        }
        //        if (sb.length() > 1) sb.deleteCharAt(sb.length()-1);
        //        sb.append("]");
    }

    //Runtime: 6 ms, faster than 83.64% of Java online submissions for Pacific Atlantic Water Flow.
    //Memory Usage: 54.9 MB, less than 54.73% of Java online submissions for Pacific Atlantic Water Flow.
    //DFS + recursion
    //Time: O(M*N); Space: O(M*N)
    private static final int[][] DIRECTIONS = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        boolean[][] pacific = new boolean[heights.length][heights[0].length];
        boolean[][] atlantic = new boolean[heights.length][heights[0].length];

        for (int i = 0; i < heights.length; i++) {
            helper_dfs(heights, i, 0, pacific);
            helper_dfs(heights,i, heights[0].length - 1, atlantic);
        }

        for (int j = 0; j < heights[0].length; j++) {
            helper_dfs(heights, 0, j, pacific);
            helper_dfs(heights,heights.length - 1, j, atlantic);
        }

        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < heights.length; i++)
            for (int j = 0; j < heights[0].length; j++)
                if (pacific[i][j] && atlantic[i][j]) res.add(Arrays.asList(i, j));
        return res;
    }

    private void helper_dfs(int[][] heights, int i, int j, boolean[][] res){
        res[i][j] = true;

        for (int[] dir : DIRECTIONS){
            int x = i + dir[0], y = j + dir[1];
            if (x >= 0 && x < heights.length
                    && y >= 0 && y < heights[0].length
                    && heights[x][y] >= heights[i][j]
                    && !res[x][y]
            )
                helper_dfs(heights, x, y, res);
        }
    }


    //Runtime: 18 ms, faster than 31.87% of Java online submissions for Pacific Atlantic Water Flow.
    //Memory Usage: 55.3 MB, less than 31.12% of Java online submissions for Pacific Atlantic Water Flow.
    //BFS + iteration
    //Time: O(M*N); Space: O(M*N)
    //private static final int[][] DIRECTIONS = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    public List<List<Integer>> pacificAtlantic_bfs(int[][] heights) {

        boolean[][] pacific = helper_bfs(heights, true);
        boolean[][] atlantic = helper_bfs(heights, false);

        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < heights.length; i++)
            for (int j = 0; j < heights[0].length; j++)
                if (pacific[i][j] && atlantic[i][j]) res.add(Arrays.asList(i, j));
        return res;
    }

    //Time: O(M + N + M*N); Space:  O(M*N)
    private boolean[][] helper_bfs(int[][] heights, boolean flag){
        //flag true: pacific
        Queue<int[]> queue = new LinkedList<>();
        int fixJ = flag ? 0 :heights[0].length - 1;
        for (int i = 0; i < heights.length; i++) queue.add(new int[]{i, fixJ});

        int fixI = flag ? 0 : heights.length - 1;
        for (int j = 0; j < heights[0].length; j++) queue.add(new int[]{fixI, j});

        boolean[][] res = new boolean[heights.length][heights[0].length];
        while (!queue.isEmpty()){
            int[] node = queue.poll();
            int i = node[0], j = node[1];
            res[i][j] = true;

            for (int[] dir : DIRECTIONS){
                int x = i + dir[0], y = j + dir[1];
                if (x >= 0 && x < heights.length
                        && y >= 0 && y < heights[0].length
                        && heights[x][y] >= heights[i][j]
                        && !res[x][y]
                )
                    queue.add(new int[]{x, y});
            }
        }
        return res;
    }

}
