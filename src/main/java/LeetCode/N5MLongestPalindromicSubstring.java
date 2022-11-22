package LeetCode;


import static java.time.LocalTime.now;

/**
 *
 * Given a string s, return the longest palindromic substring in s.
 *
 * Example 1:
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 *
 *
 * Example 2:
 * Input: s = "cbbd"
 * Output: "bb"
 *
 * Constraints:
 * 1 <= s.length <= 1000
 * s consist of only digits and English letters.
 *
 */
public class N5MLongestPalindromicSubstring {

    public static void main(String[] arg){

        String expected;
        System.out.println("==================");
        System.out.println(now());

        String tmp = "babad";
        doRun("bab", tmp);
        tmp = "bbbbb";
        doRun("bbbbb", tmp);

        tmp = "aacabdkacaa";
        doRun("aca", tmp);

        tmp = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        expected ="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        doRun(expected, tmp);
        doRun("xaabacxcabaax", "xaabacxcabaaxcabaax");
        doRun("aaabaaa", "aaabaaaa");
        doRun("bb", "cbbd");

        System.out.println(now());
        System.out.println("==================");
    }
    static private void doRun(String expect, String s) {
        String res = new N5MLongestPalindromicSubstring().longestPalindrome(s);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }


    //4.DP
    //Runtime: 276 ms, faster than 26.74% of Java online submissions for Longest Palindromic Substring.
    //Memory Usage: 45.1 MB, less than 47.42% of Java online submissions for Longest Palindromic Substring.
    //Time: O(N * N); Space: O(N * N)
    public String longestPalindrome(String s) {
        int start = 0, end = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) dp[i][i] = true;

        for (int len = 1; len < s.length(); len++) {
            for (int i = 0; i + len < s.length(); i++) {
                dp[i][i + len] = s.charAt(i) == s.charAt(i + len) && (len == 1 || dp[i + 1][i + len - 1]);

                if (dp[i][i + len] && len > end - start) {
                    start = i;
                    end = i + len;
                }
            }
        }
        return s.substring(start, end + 1);
    }


    //主要改进思路是：从中路开始，让半径可快速扩大；然后只expand有望达到加大半径的点。
    //复杂度不变，但性能会更好
    //3.expand from center
    //Runtime: 3 ms, faster than 99.81% of Java online submissions for Longest Palindromic Substring.
    //Memory Usage: 42.1 MB, less than 94.03% of Java online submissions for Longest Palindromic Substring.
    //Time: O(N * N); Space: O(1)
    public String longestPalindrome_3(String s) {
        if (s.length() <= 1) return s;
        int begin = 0, end = 0;

        //left part
        for (int i = s.length() / 2; i >= 0 && i * 2 >= end - begin; i--) {
            int radius = (end - begin) / 2 + 1;
            int len = Math.max(expand(s, i, i, radius), expand(s, i, i + 1, radius));
            if (len > end - begin){
                begin = i - (len - 1) / 2;
                end = i + len / 2 ;
            }
        }

        //right part
        for (int i = s.length() / 2; i < s.length() && (s.length() - 1 - i) * 2 >= end - begin; i++){
            int radius = (end - begin) / 2 + 1;
            int len = Math.max(expand(s, i, i, radius), expand(s, i, i + 1, radius));
            if (len > end - begin){
                begin = i - (len - 1) / 2;
                end = i + len / 2 ;
            }
        }
        return s.substring(begin, end + 1);
    }

    private int expand(String s, int l, int r, int radius){
        //check ring
        int left = l - (radius - 1);
        int right = r + (radius - 1);
        if (left < 0 || right > s.length() -1 || s.charAt(left) != s.charAt(right)) return 0;

        //expand
        while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--; r++;
        }
        return r - l - 1;
    }

    //2.one pass expand
    //Runtime: 18 ms, faster than 96.69% of Java online submissions for Longest Palindromic Substring.
    //Memory Usage: 42 MB, less than 94.03% of Java online submissions for Longest Palindromic Substring.
    //Time: O(N * N); Space: O(1)
    public String longestPalindrome_2(String s) {
        if (s.length() <= 1) return s;
        int begin = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len = Math.max(expand(s, i, i), expand(s, i, i + 1));
            if (len > end - begin){
                begin = i - (len - 1) / 2;
                end = i + len / 2 ;
            }
        }
        return s.substring(begin, end + 1);
    }

    private int expand(String s, int l, int r){
        while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
            l--; r++;
        }
        return r - l - 1;
    }

    //1.brute force
    //Runtime: 369 ms, faster than 19.07% of Java online submissions for Longest Palindromic Substring.
    //Memory Usage: 42 MB, less than 94.03% of Java online submissions for Longest Palindromic Substring.
    //Time: O(N * N * N); Space: O(1)
    public String longestPalindrome_1(String s) {
        String result = "";

        for (int i = 0; i < s.length(); i++) {
            for (int j = i + result.length(); j < s.length(); j++) {
                if (isPalindrome(s, i, j))
                    result = s.substring(i, j + 1);
            }
        }
        return result;
    }

    //Time: O((r-l)/2);
    //Time: O(N)
    private boolean isPalindrome(String s , int l, int r){
        while (l < r) if (s.charAt(l++) != s.charAt(r--)) return false;
        return true;
    }


}
