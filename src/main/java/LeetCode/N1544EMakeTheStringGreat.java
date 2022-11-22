package LeetCode;

import java.io.*;
import java.util.*;

import static java.time.LocalTime.now;


/**
 * Given a string s of lower and upper case English letters.
 *
 * A good string is a string which doesn't have two adjacent characters s[i] and s[i + 1] where:
 *
 * 0 <= i <= s.length - 2
 * s[i] is a lower-case letter and s[i + 1] is the same letter but in upper-case or vice-versa.
 * To make the string good, you can choose two adjacent characters that make the string bad and remove them. You can keep doing this until the string becomes good.
 *
 * Return the string after making it good. The answer is guaranteed to be unique under the given constraints.
 *
 * Notice that an empty string is also good.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "leEeetcode"
 * Output: "leetcode"
 * Explanation: In the first step, either you choose i = 1 or i = 2, both will result "leEeetcode" to be reduced to "leetcode".
 * Example 2:
 *
 * Input: s = "abBAcC"
 * Output: ""
 * Explanation: We have many possible scenarios, and all lead to the same answer. For example:
 * "abBAcC" --> "aAcC" --> "cC" --> ""
 * "abBAcC" --> "abBA" --> "aA" --> ""
 * Example 3:
 *
 * Input: s = "s"
 * Output: "s"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s contains only lower and upper case English letters.
 */

/**
 * E - Time: [20-
 */
public class N1544EMakeTheStringGreat {



    public static void main(String[] args) throws IOException {

        System.out.println(now());
        doRun("abc", "abc");
        doRun("", "abBAcC");
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(String expect, String s) {
        String res = new N1544EMakeTheStringGreat().makeGood(s);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }


    //2.two pass
    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Make The String Great.
    //Memory Usage: 41.1 MB, less than 94.46% of Java online submissions for Make The String Great.
    //Time: O(N + N); Space: O(N)
    //Time: O(N); Space: O(N)
    public String makeGood(String s) {
        int[] charList = new int[s.length()];
        int left = -1;
        for (int idx = 0; idx < s.length();  idx++) {
            if (left >= 0 && (charList[left] ^ 32) ==  s.charAt(idx)) left--;
            else charList[++left] =  s.charAt(idx);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= left; i++) sb.append((char)charList[i]);
        return sb.toString();
    }

    //1.one pass
    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Make The String Great.
    //Memory Usage: 41.1 MB, less than 94.46% of Java online submissions for Make The String Great.
    //Time: O(N); Space: worst case: O(N)
    public String makeGood_2(String s) {
        StringBuilder sb = new StringBuilder();
        for (int idx = 0; idx < s.length();  idx++) {
            char sRight = s.charAt(idx);
            if (sb.length() != 0 && Math.abs(sb.charAt(sb.length() - 1) - sRight) == 32)
                sb.deleteCharAt(sb.length() - 1);
            else sb.append(sRight);
        }
        return sb.toString();
    }

}
