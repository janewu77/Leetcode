package LeetCode;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * here is an m x n grid with a ball.
 * The ball is initially at the position [startRow, startColumn].
 * You are allowed to move the ball to one of the four adjacent cells in the grid
 * (possibly out of the grid crossing the grid boundary).
 * You can apply at most maxMove moves to the ball.
 *
 * Given the five integers m, n, maxMove, startRow, startColumn,
 * return the number of paths to move the ball out of the grid boundary.
 * Since the answer can be very large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 * Input: m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
 * Output: 6
 *
 * Example 2:
 * Input: m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
 * Output: 12
 *
 *
 * Constraints:
 * 1 <= m, n <= 50
 * 0 <= maxMove <= 50
 * 0 <= startRow < m
 * 0 <= startColumn < n
 */

/**
 * M - [Time 120+]
 *  - 这个化成DP的时候比较难。
 *
 */
public class N576MOutofBoundaryPaths {

    public static void main(String[] args){


        doRun(4,1,1,1,0,0);

        int  m = 1, n = 2, maxMove = 1, startRow = 0, startColumn = 0;
        doRun(3,m,n,maxMove,startRow,startColumn);

        m = 1; n = 2; maxMove = 2; startRow = 0; startColumn = 0;
        doRun(6,m,n,maxMove,startRow,startColumn);

        m = 1; n = 3; maxMove = 3; startRow = 0; startColumn = 1;
        doRun(12,m,n,maxMove,startRow,startColumn);

        m = 8; n = 50; maxMove = 23; startRow = 5; startColumn = 26;
        doRun(914783380,m,n,maxMove,startRow,startColumn);

        m = 2; n = 2; maxMove = 2; startRow = 0; startColumn = 0;
        doRun(6 ,m,n,maxMove,startRow,startColumn);

        m = 1; n = 3; maxMove = 3; startRow = 0; startColumn = 1;
        doRun(12 ,m,n,maxMove,startRow,startColumn);

        m = 3; n = 3; maxMove = 4; startRow = 1; startColumn = 1;
        doRun(52 ,m,n,maxMove,startRow,startColumn);
    }

    private static int c = 0;
    private static void doRun(int expected, int m, int n, int maxMove, int startRow, int startColumn){
        int res = new N576MOutofBoundaryPaths().findPaths(m,n,maxMove,startRow,startColumn);
        System.out.println("[" + (expected ==res) +"] "+(c++)+ ".result: "+ res + ".expected:"+expected);
    }

    //Runtime: 9 ms, faster than 57.71% of Java online submissions for Out of Boundary Paths.
    //Memory Usage: 41.8 MB, less than 83.13% of Java online submissions for Out of Boundary Paths.
    //Dynamic programming
    public int findPaths(int m, int n, int N, int x, int y) {
        int M = 1000000000 + 7;
        int dp[][] = new int[m][n];
        dp[x][y] = 1;
        int count = 0;
        for (int moves = 1; moves <= N; moves++) {
            int[][] temp = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {

                    if (i == m - 1) count = (count + dp[i][j]) % M;
                    if (j == n - 1) count = (count + dp[i][j]) % M;
                    if (i == 0) count = (count + dp[i][j]) % M;
                    if (j == 0) count = (count + dp[i][j]) % M;

                    // dp[i][j] = dp[i−1][j] + dp[i+1][j] + dp[i][j−1] + dp[i][j+1]
                    temp[i][j] = (i > 0 ? dp[i - 1][j] : 0);
                    temp[i][j] = (temp[i][j] + (i < m - 1 ? dp[i + 1][j] : 0)) % M;
                    temp[i][j] = (temp[i][j] + (j > 0 ? dp[i][j - 1] : 0)) % M;
                    temp[i][j] = (temp[i][j] + (j < n - 1 ? dp[i][j + 1] : 0)) % M;
                }
            }
            dp = temp;
        }
        return count;
    }


    //Runtime: 6 ms, faster than 85.83% of Java online submissions for Out of Boundary Paths.
    //Memory Usage: 42.5 MB, less than 64.58% of Java online submissions for Out of Boundary Paths.
    //Brute Force + memo
    final int M = 1000000007;
    int[][][] intMemo;
    public int findPaths2(int m, int n, int maxMove, int startRow, int startColumn) {
        intMemo = new int[m][n][maxMove+1];
        for (int[][] items : intMemo) for (int[] item : items) Arrays.fill(item, -1);
        return findPaths_intmemo(m, n, maxMove, startRow, startColumn);
    }

    private int findPaths_intmemo(int m, int n, int maxMove, int startRow, int startColumn) {
        if ((startRow < 0 || startRow == m || startColumn < 0 || startColumn == n)) return 1;
        if (maxMove == 0) return 0;

        if (intMemo[startRow][startColumn][maxMove] >= 0) return intMemo[startRow][startColumn][maxMove];

        int step = findPaths_intmemo(m,n,maxMove-1, startRow - 1, startColumn);
        step = (step + findPaths_intmemo(m,n,maxMove-1, startRow + 1, startColumn)) % M;
        step = (step + findPaths_intmemo(m,n,maxMove-1, startRow , startColumn - 1)) % M;
        step = (step + findPaths_intmemo(m,n,maxMove-1, startRow , startColumn + 1)) % M;

        return intMemo[startRow][startColumn][maxMove] = step;
    }

    /////////
    //Runtime: 11 ms, faster than 46.25% of Java online submissions for Out of Boundary Paths.
    //Memory Usage: 42.3 MB, less than 74.79% of Java online submissions for Out of Boundary Paths.
    Map<Integer, Integer> hashMemo = new HashMap<>();
    public int findPaths1(int m, int n, int maxMove, int startRow, int startColumn) {
        return findPaths_Hashmemo(m, n, maxMove, startRow, startColumn);
    }

    private int findPaths_Hashmemo(int m, int n, int maxMove, int startRow, int startColumn) {
        if ((startRow < 0 || startRow == m || startColumn < 0 || startColumn == n)) return 1;
        if (maxMove == 0) return 0;

        int key = (startRow * 100 + startColumn) * 100 + maxMove;
        if (hashMemo.containsKey(key)) return hashMemo.get(key);

        int step = findPaths_Hashmemo(m,n,maxMove-1, startRow - 1, startColumn);
        step = (step + findPaths_Hashmemo(m,n,maxMove-1, startRow + 1, startColumn)) % M;
        step = (step + findPaths_Hashmemo(m,n,maxMove-1, startRow , startColumn - 1)) % M;
        step = (step + findPaths_Hashmemo(m,n,maxMove-1, startRow , startColumn + 1)) % M;
        hashMemo.put(key, step);
        return step;
    }


}
