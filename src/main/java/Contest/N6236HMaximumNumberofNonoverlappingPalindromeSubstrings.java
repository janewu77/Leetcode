package Contest;

import java.io.IOException;

import static java.time.LocalTime.now;

/**
 * You are given a string s and a positive integer k.
 *
 * Select a set of non-overlapping substrings from the string s that satisfy the following conditions:
 *
 * The length of each substring is at least k.
 * Each substring is a palindrome.
 * Return the maximum number of substrings in an optimal selection.
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abaccdbbd", k = 3
 * Output: 2
 * Explanation: We can select the substrings underlined in s = "abaccdbbd". Both "aba" and "dbbd" are palindromes and have a length of at least k = 3.
 * It can be shown that we cannot find a selection with more than two valid substrings.
 * Example 2:
 *
 * Input: s = "adbcda", k = 2
 * Output: 0
 * Explanation: There is no palindrome substring of length at least 2 in the string.
 *
 *
 * Constraints:
 *
 * 1 <= k <= s.length <= 2000
 * s consists of lowercase English letters.
 */
//2472. Maximum Number of Non-overlapping Palindrome Substrings
public class N6236HMaximumNumberofNonoverlappingPalindromeSubstrings {


     static public void main(String... args) throws IOException{
         System.out.println(now());
         System.out.println("==================");
//
//        System.out.println(a);
//        System.out.println(A);
//        //doRun(0, 0);
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(long expect, int n) {
//        long res = new C0806().n1(n);

//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //最小原则，Palindrome的长度其实为 k or k+1的就进行统计。更长的Palindrome，其实是不需要的。
    //2.one pass
    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Maximum Number of Non-overlapping Palindrome Substrings.
    //Memory Usage: 39.8 MB, less than 100.00% of Java online submissions for Maximum Number of Non-overlapping Palindrome Substrings.
    //Time: O(N * K); Space: O(1)
    public int maxPalindromes(String s, int k) {
        int res = 0;
        for (int i = 0; i + k - 1 < s.length(); i++) {
            if (checkPalindrome2(s, i, i + k - 1)){
                res++;
                i = i + k - 1;
            }else if (i + k < s.length() && checkPalindrome2(s, i, i + k )){
                res++;
                i = i + k ;
            }
        }
        return res;
    }

    private boolean checkPalindrome2(String s, int left, int right){
        while (left < right)
            if (s.charAt(left++) != s.charAt(right--)) return false;
        return true;
    }

    //1.DP
    //Runtime: 2 ms, faster than 100.00% of Java online submissions for Maximum Number of Non-overlapping Palindrome Substrings.
    //Memory Usage: 40.3 MB, less than 100.00% of Java online submissions for Maximum Number of Non-overlapping Palindrome Substrings.
    //Time: O(N * K); Space: O(N)
    public int maxPalindromes_2(String s, int k) {
        int[] dp = new int[s.length() + 1];

        for (int i = k - 1; i < s.length(); i++) {
            dp[i + 1] = dp[i];
            if (checkPalindrome1(s, i - k + 1, i))
                dp[i + 1] = Math.max(dp[i + 1], 1 + dp[i - k + 1]);

            if (i - k >= 0 && checkPalindrome1(s,i - k, i))
                dp[i + 1] = Math.max(dp[i + 1], 1 + dp[i - k]);
        }
        return dp[s.length()];
    }

    private boolean checkPalindrome1(String s, int left, int right){
         while (left < right)
             if (s.charAt(left++) != s.charAt(right--)) return false;
         return true;
    }


}


