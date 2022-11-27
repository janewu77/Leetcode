package Contest;

import java.io.IOException;

import static java.time.LocalTime.now;

/**
 * You are given two strings s and t consisting of only lowercase English letters.
 *
 * Return the minimum number of characters that need to be appended to the end of s so that t becomes a subsequence of s.
 *
 * A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "coaching", t = "coding"
 * Output: 4
 * Explanation: Append the characters "ding" to the end of s so that s = "coachingding".
 * Now, t is a subsequence of s ("coachingding").
 * It can be shown that appending any 3 characters to the end of s will never make t a subsequence.
 * Example 2:
 *
 * Input: s = "abcde", t = "a"
 * Output: 0
 * Explanation: t is already a subsequence of s ("abcde").
 * Example 3:
 *
 * Input: s = "z", t = "abcde"
 * Output: 5
 * Explanation: Append the characters "abcde" to the end of s so that s = "zabcde".
 * Now, t is a subsequence of s ("zabcde").
 * It can be shown that appending any 4 characters to the end of s will never make t a subsequence.
 *
 *
 * Constraints:
 *
 * 1 <= s.length, t.length <= 105
 * s and t consist only of lowercase English letters.
 */

/**
 * Time:20-
 */

//2486. Append Characters to String to Make Subsequence
public class N6246MAppendCharacterstoStringtoMakeSubsequence {

     static public void main(String... args) throws IOException{
         System.out.println(now());
         System.out.println("==================");

         doRun(2, "addddabbbbabbbb", "abaaa");
         doRun(0, "abcdd", "a");
         doRun(5, "z", "abcde");
         doRun(4, "coaching", "coding");
         doRun(1, "aaaaaa", "ab");
         doRun(0, "a", "a");
         doRun(1, "a", "b");
        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(long expect, String s, String t) {
        int res = new N6246MAppendCharacterstoStringtoMakeSubsequence().appendCharacters(s, t);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //1.two pointers
    //Runtime: 4 ms, faster than 100.00% of Java online submissions for Append Characters to String to Make Subsequence.
    //Memory Usage: 43.2 MB, less than 80.00% of Java online submissions for Append Characters to String to Make Subsequence.
    //Time: O(min(S.len,T.len)); Space: O(1)
    public int appendCharacters(String s, String t) {
        int i = 0, j = 0;

        while (i < s.length() && j < t.length()) {
            int idx = s.indexOf(t.charAt(j), i);
            if (idx < 0) return t.length() - j;
            i = idx + 1;
            j++;
        }

        return t.length() - j;
    }

}


