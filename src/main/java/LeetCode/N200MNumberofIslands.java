package LeetCode;

/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water),
 * return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally
 * or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 *
 *
 * Example 1:
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 *
 *
 * Example 2:
 *
 * Input: grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * Output: 3
 *
 *
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] is '0' or '1'.
 *
 *
 */

/**
 *
 * M - [time:30-]
 *  - 注意上下左右一起算。
 *
 */
public class N200MNumberofIslands {

    public static void main(String[] args){
        char[][] data = new char[][]{{'1','1','1'}, {'0','1','0'}, {'1','1','1'}};
        int result = new N200MNumberofIslands().numIslands(data);
        System.out.println(result);
    }

    //Runtime: 2 ms, faster than 100.00% of Java online submissions for Number of Islands.
    //Memory Usage: 50.8 MB, less than 90.74% of Java online submissions for Number of Islands.
    //time:O(M×N)  space O(M×N) < worst case
    //DFS
    public int numIslands(char[][] grid) {
        int result = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1'){
                    disLands(grid, i,j);
                    result++;
                }
            }
        }
        return result;
    }

    //Space complexity : worst case O(M×N) in case
    //that the grid map is filled with lands where DFS goes by M × N deep.
    private void disLands(char[][] grid, int x, int y){
        if (x < 0 || x >= grid.length
                || y < 0 || y >= grid[0].length
                || grid[x][y] == '0') return;

        grid[x][y] = '0';
        //up down
        disLands(grid, x - 1, y);
        disLands(grid, x + 1, y);
        //left right
        disLands(grid, x, y - 1);
        disLands(grid, x, y + 1);
        return;
    }

}
