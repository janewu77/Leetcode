package LeetCode;


import java.io.IOException;

import static java.time.LocalTime.now;

/**
 * You are given a string s consisting of lowercase English letters. A duplicate removal consists of choosing two adjacent and equal letters and removing them.
 *
 * We repeatedly make duplicate removals on s until we no longer can.
 *
 * Return the final string after all such duplicate removals have been made. It can be proven that the answer is unique.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abbaca"
 * Output: "ca"
 * Explanation:
 * For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
 * Example 2:
 *
 * Input: s = "azxxzy"
 * Output: "ay"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s consists of lowercase English letters.
 */

/**
 * E = [Time: 5-
 */
public class N1047ERemoveAllAdjacentDuplicatesInString {



    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");

        doRun("ca", "abbaca");
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(String expect, String s) {
        String res = new N1047ERemoveAllAdjacentDuplicatesInString().removeDuplicates(s);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    //2.two pointers
    //Runtime: 11 ms, faster than 93.54% of Java online submissions for Remove All Adjacent Duplicates In String.
    //Memory Usage: 49.3 MB, less than 68.80% of Java online submissions for Remove All Adjacent Duplicates In String.
    //Time: O(N); Space: O(N)
    public String removeDuplicates(String s) {
        char[] charList = s.toCharArray();
        int j = -1;
        for (int i = 0; i < s.length(); i++) {
            if (j >= 0 && charList[j] == charList[i]) j--;
            else charList[++j] = charList[i];
        }
        return String.valueOf(charList, 0, j + 1);
    }

    //1. stack
    //Runtime: 29 ms, faster than 80.76% of Java online submissions for Remove All Adjacent Duplicates In String.
    //Memory Usage: 48.6 MB, less than 76.72% of Java online submissions for Remove All Adjacent Duplicates In String.
    //Time: O(N); Space: O(N)
    public String removeDuplicates_1(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i< s.length(); i++){
            if (sb.length() > 0 && sb.charAt(sb.length()-1) == s.charAt(i))
                sb.deleteCharAt(sb.length() - 1);
            else sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}
