package LeetCode;

import javafx.util.Pair;

import java.util.*;

/**
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
 *
 * Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 *
 * The test cases are generated so that the answer will be less than or equal to 2 * 109.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: m = 3, n = 7
 * Output: 28
 * Example 2:
 *
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Down -> Down
 * 2. Down -> Down -> Right
 * 3. Down -> Right -> Down
 *
 *
 * Constraints:
 *
 * 1 <= m, n <= 100
 *
 *
 */

/**
 *  M - [30+
 *
 */
public class N62MUniquePaths {

    public static void main(String[] args){

        doRun(3, 3, 2);
        doRun(193536720, 23, 12);
        doRun(48620, 10, 10);
        doRun(1, 1, 2);

        doRun(28, 3, 7);
    }

    private static void doRun(int expected, int m, int n){
        int res = new N62MUniquePaths().uniquePaths(m,n);
        System.out.println("["+(expected == res)+"].expected:"+ expected+".res:"+res);
    }

    //2022.12.20
    //4.Math
    //res = ((m+n−2)! / (m−1)!(n−1)! )  =  ( (m + n)! / m!)  * ( 1 / n!) )
    //Runtime: 0 ms, 100%; Memory: 40.7 MB 56%
    //Time: O(N); Space: O(1)
    public int uniquePaths(int m, int n) {
        if(m == 1 || n == 1) return 1;
        return helper_factorial(m - 1, n - 1);
    }

    //((m + n)! / (m!n)! )
    private int helper_factorial(int m, int n) {
        //let M be the larger one
        if (m < n) {
            m = m + n;
            n = m - n; m = m - n;
        }
        long res = 1;
        for (int i = m + 1, j = 1; i <= m + n; i++, j++){
            res *= i; // (m + n)! / (m!) = (m + n) * (m + n - 1) * ....* (m + 2) * (m + 1)
            res /= j; // 1 / n!
        }
        return (int)res;
    }

    //3.DP bottom-up 1D array
    //Runtime: 0 ms, 100%; Memory: 41.8 MB 5%
    //Time: O(M * N); Space:O(N)
    public int uniquePaths_23(int m, int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    //2.DP bottom-up 2D array
    //Runtime: 2 ms, 30%; Memory: 41.1 MB 28%
    //Time: O(M * N); Space:O(M * N)
    public int uniquePaths_22(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                dp[i][j] = (i - 1 >= 0 ? dp[i - 1][j] : 0) + (j - 1 >= 0 ? dp[i][j - 1] : 0);
            }
        }
        return dp[m - 1][n - 1];
    }


    //1.DP top-down
    //Runtime: 0ms, 100%; Memory: 39.2 MB 85%
    //Time: O(M * N); Space:O(M * N)
    public int uniquePaths_21(int m, int n) {
        return helper(m - 1, n - 1, new int[m][n]);
    }

    private int helper(int m, int n, int[][] memo){
        if (m < 0 || n < 0 ) return 0;
        if (m == 0 && n == 0 ) return 1;
        if (memo[m][n] != 0) return memo[m][n];
        memo[m][n] = helper(m - 1, n, memo) + helper(m ,n - 1, memo);
        return memo[m][n];
    }

    //2022/08/01
    ////////////////////////////////////////////////////////////////////////////////
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths.
    //Memory Usage: 41 MB, less than 40.73% of Java online submissions for Unique Paths.
    //优化了2d array
    //DP : 1d array
    //Time: O(M*N); Space: O(M);
    public int uniquePaths_3(int m, int n) {
        if (m == 1 || n == 1) return 1;
        int[] res = new int[m];
        res[m-1] = 1;

        for (int j = n - 1 ; j >= 0; j--)
            for (int i = m - 2; i >= 0; i--)
                res[i] += res[i + 1];
        return res[0];
    }

    ////////////////////////////////////////////////////////////////////////
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths.
    //Memory Usage: 40.9 MB, less than 57.24% of Java online submissions for Unique Paths.
    //本质和uniquePaths_DFS一样。但逻辑清晰简单多了。 看了solution的提示，写出来的。
    //DP
    //Time: O(M*N); Space: O(M*N);
    public int uniquePaths_2(int m, int n) {
        if (m == 1 || n == 1) return 1;
        int[][] res = new int[m][n];
        res[m-1][n-1] = 1;

        for (int j = n - 1 ; j >= 0; j--) {
            for (int i = m - 1; i >= 0; i--) {
                if (i + 1 < m) res[i][j] += res[i + 1][j];
                if (j + 1 < n) res[i][j] += res[i][j + 1];
            }
        }
        return res[0][0];
    }

    ////////////////////////////////////////////////////////////////////
    //Runtime: 9 ms, faster than 5.30% of Java online submissions for Unique Paths.
    //Memory Usage: 42.6 MB, less than 5.09% of Java online submissions for Unique Paths.
    //逻辑写得太复杂了！
    //DFS : map + stack
    //Time:O(M*N), Space: O(M*N)
    public int uniquePaths_DFS(int m, int n) {
        if (m == 1 || n == 1) return 1;

        Pair<Integer,Integer> start = new Pair<>(0, 0);
        Pair<Integer,Integer> End = new Pair<>(m-1, n-1);

        Map<Pair<Integer,Integer>, Integer> finish = new HashMap<>();
        finish.put(End, 1);

        //DFS : FILO
        Stack<Pair<Integer,Integer>> stack = new Stack<>();
        stack.push(start);

        while(!stack.isEmpty()){

            Pair<Integer,Integer> currPair = stack.peek();
            int currCount = 0;
            boolean hasVisited = true;

            int i = currPair.getKey();
            int j = currPair.getValue();

            //right
            if (i + 1 < m) {
                Pair nextKey = new Pair<> (i + 1, j);
                if (finish.containsKey(nextKey)) {
                    currCount += finish.get(nextKey);
                } else {
                    hasVisited = false;
                    stack.push(nextKey);
                }
            }
            //down
            if (j + 1 < n) {
                Pair nextKey = new Pair<> (i , j + 1);
                if (finish.containsKey(nextKey)) {
                    currCount += finish.get(nextKey);
                } else {
                    hasVisited = false;
                    stack.push(nextKey);
                }
            }

            if (hasVisited) {
                currPair = stack.pop();
                finish.put(currPair, currCount);
            }
        }

        return finish.get(start);
    }
}
