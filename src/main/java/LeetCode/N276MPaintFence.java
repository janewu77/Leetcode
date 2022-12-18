package LeetCode;


import java.io.IOException;
import java.util.Arrays;

import static java.time.LocalTime.now;

/**
 * You are painting a fence of n posts with k different colors. You must paint the posts following these rules:
 *
 * Every post must be painted exactly one color.
 * There cannot be three or more consecutive posts with the same color.
 * Given the two integers n and k, return the number of ways you can paint the fence.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3, k = 2
 * Output: 6
 * Explanation: All the possibilities are shown.
 * Note that painting all the posts red or all the posts green is invalid because there cannot be three posts in a row with the same color.
 * Example 2:
 *
 * Input: n = 1, k = 1
 * Output: 1
 * Example 3:
 *
 * Input: n = 7, k = 2
 * Output: 42
 *
 *
 * Constraints:
 *
 * 1 <= n <= 50
 * 1 <= k <= 105
 * The testcases are generated such that the answer is in the range [0, 231 - 1] for the given n and k.
 */
public class N276MPaintFence {

    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");
        doRun(2, 1, 2);
        doRun(4, 2, 2);
        doRun(6, 3, 2);

        doRun(24, 3, 3);
        doRun(42, 7, 2);
        doRun(1, 1, 1);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int n, int k) {
        int res = new N276MPaintFence().numWays(n, k);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //5.dp top-down + memo
    //Runtime: 0 ms, 100%; Memory: 40.8 MB 45%
    //Time: O(N); Space: O(N)
    public int numWays(int n, int k) {
        return help5(n, k, new int[n + 1]);
    }
    public int help5(int n, int k, int[] memo) {
        if (n == 0 ) return 0;
        if (n == 1) return k;
        if (n == 2) return k * k;
        if (memo[n] != 0) return memo[n];
        memo[n] = (help5(n - 1, k, memo) + help5( n - 2, k, memo)) * (k - 1);
        return memo[n];
    }

    //4.dp top-down
    //Runtime: 2543 ms, 5%; Memory: 38.9 MB 94%
    //Time: O(2^N); Space: O(N)
    public int numWays_4(int n, int k) {
        if (n == 0 ) return 0;
        if (n == 1) return k;
        if (n == 2) return k * k;
        return (numWays(n - 1, k) + numWays( n - 2, k)) * (k - 1);
    }

    //3.dp bottom-up Constant Space
    //Time: O(N); Space: O(1)
    //Runtime: 0 ms, 100%; Memory: 38.8MB 94%
    public int numWays_3(int n, int k) {
        if (n == 1) return k;

        int dp2 = k, dp1 = k * k;
        for (int i = 2; i < n; i++) {
            int dp = (dp1 + dp2) * (k - 1);
            dp2 = dp1; dp1 = dp;
        }
        return dp1;
    }

    //2.dp bottom-up 1d-array
    //Runtime: 0 ms, 100%; Memory: 38.8MB 98%
    //Time: O(N); Space: O(N)
    public int numWays_2(int n, int k) {
        if (n == 1) return k;
        int[] dp = new int[n];
        dp[0] = 1; dp[1] = k;
        int sum2 = k, sum1 = k * k;

        for (int i = 2; i < n; i++) {
            dp[i] = sum1 - dp[i - 1] + sum2 - dp[i - 2];
            sum2 = sum1;
            sum1 = dp[i] * k;
        }
        return sum1;
    }

    //1.dp bottom up
    //Runtime: 21ms, 5%; Memory: 42MB 5%
    //Time: O(N * K * K ); Space: O(N * K)
    public int numWays_1(int n, int k) {
        if (n == 1) return k;
        int[][] dp = new int[n][k];
        Arrays.fill(dp[0], 1);
        Arrays.fill(dp[1], k);

        for (int i = 2; i < n; i++) {
            for (int j = 0; j < k; j++) {
                for (int l = 0; l < k; l++) {
                    if(l == j) continue;
                    dp[i][j] += dp[i - 1][l] + dp[i - 2][l];
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < k; i++) sum += dp[n - 1][i];
        return sum;
    }

}
