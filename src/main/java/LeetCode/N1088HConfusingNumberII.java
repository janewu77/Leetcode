package LeetCode;

import java.util.HashMap;
import java.util.Map;

import static java.time.LocalTime.now;

/**
 * A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.
 *
 * We can rotate digits of a number by 180 degrees to form new digits.
 *
 * When 0, 1, 6, 8, and 9 are rotated 180 degrees, they become 0, 1, 9, 8, and 6 respectively.
 * When 2, 3, 4, 5, and 7 are rotated 180 degrees, they become invalid.
 * Note that after rotating a number, we can ignore leading zeros.
 *
 * For example, after rotating 8000, we have 0008 which is considered as just 8.
 * Given an integer n, return the number of confusing numbers in the inclusive range [1, n].
 *
 *
 *
 * Example 1:
 *
 * Input: n = 20
 * Output: 6
 * Explanation: The confusing numbers are [6,9,10,16,18,19].
 * 6 converts to 9.
 * 9 converts to 6.
 * 10 converts to 01 which is just 1.
 * 16 converts to 91.
 * 18 converts to 81.
 * 19 converts to 61.
 * Example 2:
 *
 * Input: n = 100
 * Output: 19
 * Explanation: The confusing numbers are [6,9,10,16,18,19,60,61,66,68,80,81,86,89,90,91,98,99,100].
 *
 *
 * Constraints:
 *
 * 1 <= n <= 109
 */

public class N1088HConfusingNumberII {

    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");

        doRun(15411, 999959);

        doRun(1196, 20000);
        doRun(1196, 19999);
        doRun(587, 10000);
        doRun(586, 9999);
        doRun(466 + 1, 9000);
        doRun(572, 9950);
        doRun(40 + 12, 668);
        doRun(62 + 25 - 3, 899);
        doRun(84, 899);
        doRun(18 + 25 - 3, 200);
        doRun(40, 230);
        doRun(18+15-2, 171);
        doRun(26, 120);
        doRun(19, 100);
        doRun(19, 101);
        doRun(20, 106);
        doRun(37, 195);
        doRun(6 + 1, 60);
        doRun(6, 30);
        doRun(6, 20);
        doRun(3, 10);

        System.out.println("==================");
        doRun(0, 0);
        doRun(0, 1);
        doRun(0, 2);
        doRun(1, 6);
        doRun(1, 8);
        doRun(2, 9);

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, int n) {
        int res = new N1088HConfusingNumberII().confusingNumberII(n);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }



    //2.DFS
    //Runtime: 108ms 89%; Memory: 38.8MB 99%
    //Time:O(5^(logN)); Space: O(logN)
    //Time:O(N^log5); Space: O(logN)
    public int confusingNumberII_2(int n) {
        help_dfs(0, 0, 1, n);
        return count;
    }
    private int count = 0;
    private static final int[] DIGITS = {0, 1, 6, 8, 9};
    private static final int[] ROTATED_DIGITS = {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
    private void help_dfs(long n, long rotated, int base, int limit) {
        if (n > limit) return;
        if (n != rotated) count++;
        for (int d : DIGITS) {
            if (n == 0 && d == 0) continue;
            help_dfs(n * 10 + d, base * ROTATED_DIGITS[d] + rotated, base * 10, limit);
        }
    }

    //1.backtracking
    //Runtime: 498ms 38%; Memory: 42.7MB 45%
    //Time:O(5^(logN)); Space: O(logN)
    //Time:O(N^log5); Space: O(logN)
    public int confusingNumberII(int n) {
        //stack space: O(N)
        return helper(String.valueOf(n), 0, "");
    }

    private static final char[] digits = new char[]{'0', '1', '6', '8', '9'};
    private static final char[] rotated_digits = new char[]{'0', '1', '-', '-', '-', '-', '9', '-', '8', '6'};
    private int helper(String str, int begin, String currStr){
        int res = 0;
        if (str.length() == currStr.length()) {
            if (currStr.compareTo(str) > 0) return res;
            if (!isSame(currStr)) res = 1;
            return res;
        }

        for (char c : digits) {//01689
            String tmp = currStr + c;
            if (tmp.compareTo(str.substring(0, begin + 1)) > 0) break;
            res += helper(str, begin + 1, tmp);
        }
        return res;
    }

    private boolean isSame(String str) {
        int left = 0, right = str.length() - 1;
        while (left <= right && str.charAt(left) == '0')
            left++;

        while (left <= right) {
            if (rotated_digits[str.charAt(left++)-'0'] != str.charAt(right--))
                return false;
        }
        return true;
    }

}
