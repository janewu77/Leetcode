package LeetCode;

/**
 *
 * ou are given an m x n binary matrix grid. An island is a group of 1's (representing land)
 * connected 4-directionally (horizontal or vertical.) You may assume all four edges of
 * the grid are surrounded by water.
 *
 * The area of an island is the number of cells with a value 1 in the island.
 * Return the maximum area of an island in grid. If there is no island, return 0.
 *
 * Example 1:
 * Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],
 * [0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * Output: 6
 * Explanation: The answer is not 11, because the island must be connected 4-directionally.
 *
 * Example 2:
 * Input: grid = [[0,0,0,0,0,0,0,0]]
 * Output: 0
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j] is either 0 or 1.
 *
 *
 */

/**
 * M - [time: 30-]
 *
 */
public class N695MMaxAreaofIsland {

    public static void main(String[] args){
        int[][] data = new int[][]{{1,1,1}, {0,1,0}, {1,1,1}};
        doRun(data, 7);

        data = new int[][]{{0}};
        doRun(data, 0);

        data = new int[][]{{1}};
        doRun(data, 1);

        data = new int[][]{{0,1,0,1}};
        doRun(data, 1);

        data = new int[][]{{0,0,0,0}, {0,0,0,0}, {0,0,0,0}};
        doRun(data, 0);

        data = new int[][]{{0,0,1,0,0,0,0,1,0,0,0,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,1,1,0,1,0,0,0,0,0,0,0,0},{0,1,0,0,1,1,0,0,1,0,1,0,0},{0,1,0,0,1,1,0,0,1,1,1,0,0},{0,0,0,0,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,0,0,0,0,0,0,1,1,0,0,0,0}};
        doRun(data, 6);
    }

    static int c = 0;
    private static void doRun(int[][] data, int expected){
        int result = new N695MMaxAreaofIsland().maxAreaOfIsland(data);
        expected = 7;
        System.out.println( (c++)+".["+(expected == result)+"] result: " + result);
    }

    //Runtime: 2 ms, faster than 100.00% of Java online submissions for Max Area of Island.
    //Memory Usage: 42.1 MB, less than 97.07% of Java online submissions for Max Area of Island.
    //DFS
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == 1){
                    maxArea = Math.max(maxArea, countArea(grid, i, j));
                }
            }
        }
        return maxArea;
    }

    private int countArea(int[][] grid, int x, int y){
        if (x < 0 || x >= grid.length
                || y < 0 || y >= grid[0].length
                || grid[x][y] == 0) return 0;

        int result = 1;
        grid[x][y] = 0;
        //up down
        result += countArea(grid, x-1, y);
        result += countArea(grid, x+1, y);
        //left right
        result += countArea(grid, x, y-1);
        result += countArea(grid, x, y+1);
        return result;
    }

}
