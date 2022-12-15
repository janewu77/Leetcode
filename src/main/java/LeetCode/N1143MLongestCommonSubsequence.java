package LeetCode;

import java.io.IOException;

import static java.time.LocalTime.now;

/**
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 *
 *
 * Example 1:
 *
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 * Example 2:
 *
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 * Example 3:
 *
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 *
 *
 * Constraints:
 *
 * 1 <= text1.length, text2.length <= 1000
 * text1 and text2 consist of only lowercase English characters.
 */
public class N1143MLongestCommonSubsequence {

    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");

        doRun(2, "cpqrs", "shmtulqrypy");
        doRun(2, "ezupkr", "ubmrapg");

        doRun(3, "abc", "abc");
        doRun(3, "abcde", "ace");
        doRun(0, "abc", "def");
        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, String text1, String text2) {
        int res = new N1143MLongestCommonSubsequence().longestCommonSubsequence(text1, text2);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //3.dp bottom-up + 1d array
    //Runtime: 11ms, 97.47%; Memory: 40.1MB, 99.48%
    //Time: O(M * N); Space: O(min(M,N))
    public int longestCommonSubsequence(String text1, String text2) {
        if (text2.length() > text1.length()){
            String tmp = text1;
            text1 = text2;
            text2 = tmp;
        }

        int[] dp = new int[text2.length() + 1];
        for (int i = text1.length() - 1; i >= 0; i--) {
            int lastValue = 0;
            for (int j = text2.length() - 1; j >= 0 ; j--){
                int tmp = dp[j];
                dp[j] = Math.max(dp[j], dp[j + 1]);
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[j] = Math.max(dp[j], 1 + lastValue);
                }
                lastValue = tmp;
            }
        }
        return dp[0];
    }

    //2.dp bottom-up
    //Runtime: 12ms, 96.2%; Memory: 45.8MB, 91.65%
    //Time: O(M * N); Space: O(M * N)
    public int longestCommonSubsequence_2(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];

        for (int i = text1.length() - 1; i >= 0; i--) {
            for (int j = text2.length() - 1; j >= 0 ; j--){
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                if (text1.charAt(i) == text2.charAt(j))
                    dp[i][j] = Math.max(dp[i][j], 1 + dp[i + 1][j + 1]);
            }
        }
        return dp[0][0];
    }

    //1.Recursion + memo
    //Runtime: 44ms, 14.19%; Memory: 46MB, 84.28%
    //Time: O(M * N * N); Space: O(M * N)
    public int longestCommonSubsequence_1(String text1, String text2) {
        return helper(text1, 0, text2, 0, new int[text1.length()][text2.length()]);
    }

    private int helper(String text1, int idx1, String text2, int idx2, int[][] memo){
        if (idx1 == text1.length() || idx2 == text2.length()) return 0;

        if (memo[idx1][idx2] > 0) return memo[idx1][idx2];


        int idx = text2.indexOf(text1.charAt(idx1), idx2);  //Time:O(N)

        int len = helper(text1, idx1 + 1, text2, idx2, memo);
        if (idx >= 0)
            len = Math.max(len, 1 + helper(text1, idx1 + 1, text2, idx + 1, memo));

        memo[idx1][idx2] = len;
        return len;
    }
}
