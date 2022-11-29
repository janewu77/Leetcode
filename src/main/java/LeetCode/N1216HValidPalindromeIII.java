package LeetCode;


import java.io.IOException;
import java.util.*;

import static java.time.LocalTime.now;

/**
 * Given a string s and an integer k, return true if s is a k-palindrome.
 *
 * A string is k-palindrome if it can be transformed into a palindrome by removing at most k characters from it.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcdeca", k = 2
 * Output: true
 * Explanation: Remove 'b' and 'e' characters.
 * Example 2:
 *
 * Input: s = "abbababa", k = 1
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consists of only lowercase English letters.
 * 1 <= k <= s.length
 */
public class N1216HValidPalindromeIII {

    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");
        doRun(false, "adb", 1);
        doRun(true, "acbbbcaaadbdddcbbaabdabbdbaadcadadaadcdbabbcbbbcaadaaddcbdcaabdbacdcdccbaaadcdbadbbbcbbbddbadcacbacbaacdbddcbcadddadadbabdaacbdacdbcccbcbadbacdbaaadbcbddbbabcdccaccabbabdcadaddbadbccdddccbdbdacabbcbbbcadcdadaadbdaaad", 216);

        doRun(true, "bbab", 2);
        doRun(true, "bbbbabbb", 2);

        doRun(true, "abcdeca", 2);
        doRun(true, "abbababa", 1);

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(boolean expect, String s, int k) {
        boolean res = new N1216HValidPalindromeIII().isValidPalindrome(s, k);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //4.DP bottom-up 1d array
    //Runtime: 11 ms, faster than 97.81% of Java online submissions for Valid Palindrome III.
    //Memory Usage: 40 MB, less than 99.69% of Java online submissions for Valid Palindrome III.
    //Time: O(N * N); Space: O(N);
    public boolean isValidPalindrome(String s, int k) {

        int[] dp = new int[s.length()];

        for (int left = (s.length() - 2) ; left >= 0; left--) {
            int prev = 0;
            for (int right = left + 1; right < s.length(); right++) {
                int tmp = dp[right];

                if (s.charAt(left) == s.charAt(right))
                    dp[right] = prev; //dp[left + 1][right - 1];
                else
                    //1 + Math.min(dp[left + 1][right], dp[left][right - 1]);
                    dp[right] = 1 + Math.min(dp[right], dp[right - 1]);
                prev = tmp;
            }
        }
        return dp[s.length() - 1] <= k;
    }


    //3.DP bottom-up
    //Runtime: 25 ms, faster than 65.63% of Java online submissions for Valid Palindrome III.
    //Memory Usage: 48.9 MB, less than 78.13% of Java online submissions for Valid Palindrome III.
    //Time: O(N * N); Space: O(N * N);
    public boolean isValidPalindrome_3(String s, int k) {
        int[][] dp = new int[s.length()][s.length()];

        for (int left = (s.length() - 2) ; left >= 0; left--) {
            for (int right = left + 1; right < s.length(); right++) {
                if (s.charAt(left) == s.charAt(right))
                    dp[left][right] = dp[left + 1][right - 1];
                else
                    dp[left][right] = 1 + Math.min(dp[left + 1][right], dp[left][right - 1]);
            }
        }
        return dp[0][s.length() - 1] <= k;
    }

    //2.DP top-down
    //Runtime: 30 ms, faster than 44.69% of Java online submissions for Valid Palindrome III.
    //Memory Usage: 49.1 MB, less than 69.06% of Java online submissions for Valid Palindrome III.
    //Time: O(N * N); Space: O(N * N);
    public boolean isValidPalindrome_2(String s, int k) {
        Integer[][] dp = new Integer[s.length()][s.length()];
        return helper2(s, 0, s.length() - 1, dp) <= k;
    }

    private int helper2(String s, int left, int right, Integer[][] dp){
        if (left == right) return 0;
        if (left + 1 == right)
            return s.charAt(left) != s.charAt(right) ? 1 : 0;

        if (dp[left][right] != null) return dp[left][right];

        if (s.charAt(left) == s.charAt(right))
            dp[left][right] = helper2(s, left + 1, right - 1, dp);
        else
            dp[left][right] = 1 + Math.min(helper2(s, left + 1, right, dp), helper2(s, left, right - 1, dp));

        return dp[left][right];
    }

    //1.recursion
    //Runtime: 398 ms, faster than 5.00% of Java online submissions for Valid Palindrome III.
    //Memory Usage: 133.6 MB, less than 5.00% of Java online submissions for Valid Palindrome III.
    //Time: O(3^N); Space: O(N * N * K);
    public boolean isValidPalindrome_1(String s, int k) {
        return helper1(s, 0, s.length() - 1, k, new HashMap<>());
    }

    private boolean helper1(String s, int left, int right, int k, Map<Integer, Boolean> map){
        if (k == 0) {
            while (left <= right) {
                if (s.charAt(left) != s.charAt(right)) return false;
                left++; right--;
            }
            return true;
        }
        if (left >= s.length() || right < 0) return false;

        int key = left * 1000 * 1000 + right * 1000 + k;
        if (map.containsKey(key)) return map.get(key);

        boolean res;
        if (s.charAt(left) == s.charAt(right)){
            res = helper1(s, left + 1, right - 1, k, map);
        }else{
            res = helper1(s, left + 1, right, k - 1, map) || helper1(s, left, right - 1, k - 1, map);
        }
        map.put(key, res);
        return res;
    }

}
