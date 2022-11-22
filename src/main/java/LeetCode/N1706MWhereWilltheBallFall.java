package LeetCode;

import java.util.Arrays;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

/**
 * You have a 2-D grid of size m x n representing a box, and you have n balls. The box is open on the top and bottom sides.
 *
 * Each cell in the box has a diagonal board spanning two corners of the cell that can redirect a ball to the right or to the left.
 *
 * A board that redirects the ball to the right spans the top-left corner to the bottom-right corner and is represented in the grid as 1.
 * A board that redirects the ball to the left spans the top-right corner to the bottom-left corner and is represented in the grid as -1.
 * We drop one ball at the top of each column of the box. Each ball can get stuck in the box or fall out of the bottom. A ball gets stuck if it hits a "V" shaped pattern between two boards or if a board redirects the ball into either wall of the box.
 *
 * Return an array answer of size n where answer[i] is the column that the ball falls out of at the bottom after dropping the ball from the ith column at the top, or -1 if the ball gets stuck in the box.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: grid = [[1,1,1,-1,-1],[1,1,1,-1,-1],[-1,-1,-1,1,1],[1,1,1,1,-1],[-1,-1,-1,-1,-1]]
 * Output: [1,-1,-1,-1,-1]
 * Explanation: This example is shown in the photo.
 * Ball b0 is dropped at column 0 and falls out of the box at column 1.
 * Ball b1 is dropped at column 1 and will get stuck in the box between column 2 and 3 and row 1.
 * Ball b2 is dropped at column 2 and will get stuck on the box between column 2 and 3 and row 0.
 * Ball b3 is dropped at column 3 and will get stuck on the box between column 2 and 3 and row 0.
 * Ball b4 is dropped at column 4 and will get stuck on the box between column 2 and 3 and row 1.
 * Example 2:
 *
 * Input: grid = [[-1]]
 * Output: [-1]
 * Explanation: The ball gets stuck against the left wall.
 * Example 3:
 *
 * Input: grid = [[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1],[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1]]
 * Output: [0,1,2,3,4,-1]
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * grid[i][j] is 1 or -1.
 */

/**
 * M - time: 30+
 */
public class N1706MWhereWilltheBallFall {


    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun("0,1,2,3,4,-1", new int[][]{{1,1,1,1,1,1}, {-1,-1,-1,-1,-1,-1}, {1,1,1,1,1,1}, {-1,-1,-1,-1,-1,-1}});

        //[[],[],[],[],[]]
        //Output: [1,-1,-1,-1,-1]
        doRun("1,-1,-1,-1,-1", new int[][]{{1,1,1,-1,-1}, {1,1,1,-1,-1}, {-1,-1,-1,1,1}, {1,1,1,1,-1}, {-1,-1,-1,-1,-1}});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(String expect, int[][] grid) {
        int[] res1 = new N1706MWhereWilltheBallFall().findBall(grid);
        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //3.DP
    //Runtime: 23 ms, faster than 8.42% of Java online submissions for Where Will the Ball Fall.
    //Memory Usage: 54.8 MB, less than 8.54% of Java online submissions for Where Will the Ball Fall.
    //Time: O(M*N); Space:(N)
    public int[] findBall(int[][] grid) {

        int[] dp = new int[grid[0].length];
        for (int i = 0; i < grid[0].length; i++) dp[i] = i;

        for (int i = 0; i < grid.length; i++) {
            int[] dp2 = new int[grid[0].length];
            Arrays.fill(dp2, - 1);
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == 1 && j < grid[0].length - 1 && grid[i][j] == grid[i][j + 1])
                    dp2[j + 1] = dp[j];
                else if (grid[i][j] == -1 && j > 0 && grid[i][j] == grid[i][j - 1])
                    dp2[j - 1] = dp[j];
            }
            dp = dp2;
        }

        int[] res = new int[grid[0].length];
        Arrays.fill(res, - 1);
        for (int i = 0; i < dp.length; i++)
            if (dp[i] != -1) res[dp[i]] = i;
        return res;
    }

    //2.iteration
    //Runtime: 3 ms, faster than 58.35% of Java online submissions for Where Will the Ball Fall.
    //Memory Usage: 43.2 MB, less than 97.91% of Java online submissions for Where Will the Ball Fall.
    //Time: O(M*N); Space:(1)
    public int[] findBall_2(int[][] grid) {
        int[] res = new int[grid[0].length];
        for (int k = 0; k < grid[0].length; k++) {
            int i = 0, j = k;
            for (; i < grid.length && j >= 0 && j <= grid[0].length; i++) {
                if (grid[i][j] == 1){
                    j = ((j + 1 >= grid[0].length || grid[i][j + 1] != grid[i][j])) ? -1 : j + 1;
                }else{
                    j = ((j - 1 < 0 || grid[i][j - 1] != grid[i][j])) ? -1 : j - 1;
                }
            }//End for i
            res[k] = j;
        }
        return res;
    }

    //1.DFS
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Where Will the Ball Fall.
    //Memory Usage: 43.9 MB, less than 85.56% of Java online submissions for Where Will the Ball Fall.
    //Time: O(M*N); Space:(1)
    public int[] findBall_1(int[][] grid) {
        int[] res = new int[grid[0].length];
        for (int j = 0; j < grid[0].length; j++)
            res[j] = helper(grid, 0, j);
        return res;
    }

    private int helper(int[][] grid, int i, int j) {
        if (grid[i][j] == 1 && (j + 1 >= grid[0].length || grid[i][j + 1] != grid[i][j])) return -1;
        if (grid[i][j] == -1 && (j - 1 < 0 || grid[i][j - 1] != grid[i][j])) return -1;

        j += grid[i][j] == 1 ? 1 : -1;
        if (i == grid.length - 1) return j;
        return helper(grid, ++i, j);
    }
}

