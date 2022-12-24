package LeetCode;

import java.io.IOException;

import static java.time.LocalTime.now;

/**
 * You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.
 *
 *
 * Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large,
 * return it modulo 109 + 7.
 *
 * In a tiling, every square must be covered by a tile. Two tilings are different if and only
 * if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings
 * has both squares occupied by a tile.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3
 * Output: 5
 * Explanation: The five different ways are show above.
 * Example 2:
 *
 * Input: n = 1
 * Output: 1
 */
public class N790MDominoandTrominoTiling {

    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");
        doRun(5, 3);
        doRun(11, 4);
        doRun(24, 5);
        doRun(258, 8);
        doRun(882347204, 60);
        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, int n) {
        int res = new N790MDominoandTrominoTiling().numTilings(n);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //1.DP bottom-up
    //Runtime : 1ms 90%; Memory: 39.3MB 82%
    //Time: O(N); Space: O(1)
    public int numTilings(int n) {
        if (n <= 2) return n;
        long[] dp = new long[3];
        dp[0] = 1l;

        for (int i = 1; i <= n; i++) {
            long[] lastDP = dp;
            dp = new long[3];
            dp[0] = (lastDP[0] + lastDP[1] + lastDP[2]) % 1_000_000_007;
            dp[1] = lastDP[0];
            dp[2] = (lastDP[2] + 2 * lastDP[1]) % 1_000_000_007;
        }
        return (int)dp[0];
    }
}
