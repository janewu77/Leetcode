package LeetCode;

import java.util.Arrays;

import static java.time.LocalTime.now;

/**
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 *
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:
 *
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
 *
 * Given a string s containing only digits, return the number of ways to decode it.
 *
 * The test cases are generated so that the answer fits in a 32-bit integer.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "12"
 * Output: 2
 * Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 *
 * Input: s = "226"
 * Output: 3
 * Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 * Example 3:
 *
 * Input: s = "06"
 * Output: 0
 * Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").
 */

/**
 * M - [time:30-
 */
public class N91MDecodeWays {


    public static void main(String[] args){
        System.out.println(now());
        int[] data;

        doRun(2, "12");


        doRun(3, "226");
        doRun(1, "27");
        doRun(1, "102");

        doRun(1836311903,"111111111111111111111111111111111111111111111");
        doRun(0, "06");

        doRun(0, "60");

        System.out.println(now());
        System.out.println("==================");
    }

    private static void doRun(int expected, String s){
        int res = new N91MDecodeWays().numDecodings( s);
        System.out.println("["+(expected == res)+"].expected:"+ expected+".res:"+res);
    }


    //Runtime: 1 ms, faster than 98.73% of Java online submissions for Decode Ways.
    //Memory Usage: 40.6 MB, less than 93.46% of Java online submissions for Decode Ways.
    //DP
    //Time: O(N); Space: O(1)
    public int numDecodings(String s) {
        if (s.startsWith("0")) return 0;

        int dp2 = 1, dp1 = s.charAt(s.length() - 1) != '0' ? 1 : 0;
        for (int begin = s.length() - 2; begin >= 0; begin--) {
            int count = 0;
            if (s.charAt(begin) != '0')
                count = Integer.parseInt(s.substring(begin, begin + 2)) <= 26 ? dp1 + dp2 : dp1;
//                count = ((s.charAt(begin) - '0') * 10 + s.charAt(begin + 1) - '0' <= 26) ?
            dp2 = dp1;  dp1 = count;
        }
        return dp1;
    }

    //This one is based on the first version.
    //I review this version I found the 1-arr is unnecessary, it can be replace by two varaibles.
    //Runtime: 1 ms, faster than 98.73% of Java online submissions for Decode Ways.
    //Memory Usage: 42.7 MB, less than 24.16% of Java online submissions for Decode Ways.
    //DP 1-arr + iteration
    //Time: O(N); Space: O(N)
    public int numDecodings_2(String s) {
        if (s.startsWith("0")) return 0;

        int[] dp = new int[s.length() + 1];
        dp[s.length() - 1] = s.charAt(s.length() - 1) != '0'? 1 : 0;
        dp[s.length()] = 1;

        for (int begin = s.length() - 2; begin >= 0; begin--) {
            char c1 = s.charAt(begin);
            if (c1 != '0') {
                dp[begin] = dp[begin + 1];
                int x = (c1 - '0') * 10 + s.charAt(begin + 1) - '0';
                if (x <= 26) dp[begin] += dp[begin + 2];
            }
        }
        return dp[0];
    }

    //This is the first version (Brute force).
    //After it encountered TLE, I added memo to improve it's performance.
    //
    //Runtime: 23 ms, faster than 5.15% of Java online submissions for Decode Ways.
    //Memory Usage: 42.1 MB, less than 68.23% of Java online submissions for Decode Ways.
    //recursion + memo
    //Time: O(2^N) without memo; Space:O(N)
    //Time: O(N) with memo; Space:O(N)
    public int numDecodings_1(String s) {
        memo = new int[s.length()];
        Arrays.fill(memo, -1);
        if (s.startsWith("0")) return 0;
        return helper(s, 0);
    }

    int[] memo;
    private int helper(String s, int begin){
       if (begin >= s.length())  return 1;

       if (memo[begin] > 0) return memo[begin];

       char c1 = s.charAt(begin);
       if (c1 == '0') return 0;
       int res = helper(s, begin + 1);

       if (begin + 1 < s.length()){
           int x = (c1 - '0') * 10 + s.charAt(begin + 1) - '0';
           if (x <= 26 ) res += helper(s, begin + 2);
       }
       return memo[begin] = res;
    }
}
