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

    //1.Recursion + memo
    //Runtime: 44ms, 14.19%%; Memory: 46MB, 84.28%
    //Time: O(M * N); Space: O(M * N)
    public int longestCommonSubsequence(String text1, String text2) {
        return helper(text1, 0, text2, 0, new int[text1.length()][text2.length()]);
    }

    private int helper(String text1, int idx1, String text2, int idx2, int[][] memo){
        if (idx1 == text1.length() || idx2 == text2.length()) return 0;

        if (memo[idx1][idx2] > 0) return memo[idx1][idx2];

        int idx = text2.indexOf(text1.charAt(idx1), idx2);
        int len = helper(text1, idx1 + 1, text2, idx2, memo);
        if (idx >= 0)
            len = Math.max(len, 1 + helper(text1, idx1 + 1, text2, idx + 1, memo));

        memo[idx1][idx2] = len;
        return len;
    }
}
