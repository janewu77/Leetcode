package LeetCode;

/**
 * You are given an m x n integer array grid. There is a robot initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
 *
 * An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot include any square that is an obstacle.
 *
 * Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 *
 * The testcases are generated so that the answer will be less than or equal to 2 * 109.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: 2
 * Explanation: There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 * Example 2:
 *
 *
 * Input: obstacleGrid = [[0,1],[0,0]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * m == obstacleGrid.length
 * n == obstacleGrid[i].length
 * 1 <= m, n <= 100
 * obstacleGrid[i][j] is 0 or 1.
 */
public class N63MUniquePathsII {

    public static void main(String[] args){

        doRun(2, new int[][]{{0,0,0}, {0,1,0}, {0,0,0}});
        doRun(0, new int[][]{{0}, {1}});

    }

    private static void doRun(int expected, int[][] obstacleGrid){
        int res = new N63MUniquePathsII().uniquePathsWithObstacles(obstacleGrid);
        System.out.println("["+(expected == res)+"].expected:"+ expected+".res:"+res);
    }

    //3.DP bottom-up in-place
    //Runtime: 1 ms 74%; Memory: 42.2MB 51%
    //Time: O(M * N); Space: O(1);
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        obstacleGrid[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                if (obstacleGrid[i][j] == 0){
                    if (i - 1 >= 0) obstacleGrid[i][j] += obstacleGrid[i - 1][j];
                    if (j - 1 >= 0) obstacleGrid[i][j] += obstacleGrid[i][j - 1];
                }else {
                    obstacleGrid[i][j] = 0;
                }
            }
        }
        return obstacleGrid[m - 1][n - 1];
    }

    //2.DP bottom-up 1d-array
    //Runtime: 0 ms 100%; Memory: 40MB 98%
    //Time: O(M * N); Space: O(N);
    public int uniquePathsWithObstacles_2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n];
        dp[0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                dp[j] = obstacleGrid[i][j] == 1 ? 0 : dp[j] + (j - 1 >= 0 ? dp[j - 1] : 0);
            }
        }
        return dp[n - 1];
    }

    //1.DP bottom-up 2D-array
    //Runtime: 0 ms 100%; Memory: 40.1MB 96%
    //Time: O(M * N); Space: Time: O(M * N);
    public int uniquePathsWithObstacles_1(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                if (obstacleGrid[i][j] == 1) dp[i][j] = 0;
                else
                    dp[i][j] = (i - 1 >= 0 ? dp[i - 1][j] : 0) + (j - 1 >= 0 ? dp[i][j - 1] : 0);
            }
        }
        return dp[m - 1][n - 1];
    }
}
