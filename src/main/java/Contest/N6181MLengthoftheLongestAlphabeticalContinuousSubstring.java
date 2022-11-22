package Contest;

import static java.time.LocalTime.now;

/**
 * An alphabetical continuous string is a string consisting of consecutive letters in the alphabet. In other words, it is any substring of the string "abcdefghijklmnopqrstuvwxyz".
 *
 * For example, "abc" is an alphabetical continuous string, while "acb" and "za" are not.
 * Given a string s consisting of lowercase letters only, return the length of the longest alphabetical continuous substring.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abacaba"
 * Output: 2
 * Explanation: There are 4 distinct continuous substrings: "a", "b", "c" and "ab".
 * "ab" is the longest continuous substring.
 * Example 2:
 *
 * Input: s = "abcde"
 * Output: 5
 * Explanation: "abcde" is the longest continuous substring.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s consists of only English lowercase letters.
 */

/**
 * M - [time: 8-
 */
//2414. Length of the Longest Alphabetical Continuous Substring
public class N6181MLengthoftheLongestAlphabeticalContinuousSubstring {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun(2, "abacaba");
        doRun(5, "abcde");

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, String s) {
        int res = new N6181MLengthoftheLongestAlphabeticalContinuousSubstring()
                .longestContinuousSubstring(s);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 8 ms, faster than 100.00% of Java online submissions for Length of the Longest Alphabetical Continuous Substring.
    //Memory Usage: 54.2 MB, less than 55.56% of Java online submissions for Length of the Longest Alphabetical Continuous Substring.
    //one pass
    //time: O(N); Space:(1)
    public int longestContinuousSubstring(String s) {

        int res = 1, count = 1;
        char prevChar = 'B';
        for (char c : s.toCharArray()) {

            if (prevChar + count == c) {
                res = Math.max(res, ++count);
            } else {
                count = 1; prevChar = c;
            }
        }
        return res;
    }


    //Runtime: 10 ms, faster than 77.78% of Java online submissions for Length of the Longest Alphabetical Continuous Substring.
    //Memory Usage: 42.9 MB, less than 88.89% of Java online submissions for Length of the Longest Alphabetical Continuous Substring.
    //Slide window
    //time: O(N); Space:(1)
    public int longestContinuousSubstring_1(String s) {
        int maxLen = 1;
        int left = 0, right = 1;

        while (right < s.length()) {
            if (s.charAt(right - 1) + 1 != s.charAt(right)) {
                maxLen = Math.max(maxLen, right - left);
                left = right;
            }
            right++;
        }
        return Math.max(maxLen, right - left);
    }
}
