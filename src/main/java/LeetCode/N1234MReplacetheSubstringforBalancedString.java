package LeetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.time.LocalTime.now;

/**
 * You are given a string s of length n containing only four kinds of
 * characters: 'Q', 'W', 'E', and 'R'.
 *
 * A string is said to be balanced if each of its characters appears n / 4 times
 * where n is the length of the string.
 *
 * Return the minimum length of the substring that can be replaced with
 * any other string of the same length to make s balanced. If s is already balanced, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "QWER"
 * Output: 0
 * Explanation: s is already balanced.
 * Example 2:
 *
 * Input: s = "QQWE"
 * Output: 1
 * Explanation: We need to replace a 'Q' to 'R', so that "RQWE" (or "QRWE") is balanced.
 * Example 3:
 *
 * Input: s = "QQQW"
 * Output: 2
 * Explanation: We can replace the first "QQ" to "ER".
 *
 *
 * Constraints:
 *
 * n == s.length
 * 4 <= n <= 105
 * n is a multiple of 4.
 * s contains only 'Q', 'W', 'E', and 'R'.
 */

/***
 * M - [time: 60-
 *
 */
public class N1234MReplacetheSubstringforBalancedString {


    public static void main(String[] args){
        int[] data;

        System.out.println(now());
        doRun_demo(1, "QQWE");
        doRun_demo(2, "QQQW");
        doRun_demo(6, "QQQQQQQQ");
        doRun_demo(0, "QWER");
        doRun_demo(3, "WQWRQQQW");

        doRun_demo(3, "WQWEWQWE");

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun_demo(int expect, String s) {
        int res = new N1234MReplacetheSubstringforBalancedString().balancedString(s);
//        String res = comm.toString(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 22 ms, faster than 38.19% of Java online submissions for Replace the Substring for Balanced String.
    //Memory Usage: 42.1 MB, less than 97.24% of Java online submissions for Replace the Substring for Balanced String.
    //slide window
    private Map<Character, Integer> charIdx = new HashMap<Character, Integer>(){{
        put('Q', 0); put('W', 1); put('E', 2); put('R', 3);
    }};

    //Time: O(N); Space: O(1)
    public int balancedString(String s) {
        int x = s.length() / 4;

        //Space: O(4)
        int[] arr = new int[4];
        //Time: O(N)
        for (int i = 0; i < s.length(); i++)
            arr[charIdx.get(s.charAt(i))] = (arr[charIdx.get(s.charAt(i))] + 1);

        int res = s.length();
        int left = 0, right = 0;
        //Slide window
        //Time: O(2N)
        while (left < s.length()) {
            boolean isFit = true;
            for (int i = 0; i < arr.length; i++)  isFit &= (arr[i] <= x);

            if (isFit) res = Math.min(res, right - left );

            if (isFit || right >= s.length())
                arr[charIdx.get(s.charAt(left++))] += 1;
            else
                arr[charIdx.get(s.charAt(right++))] -= 1;
        }
        return res;
    }

}
