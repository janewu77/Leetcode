package LeetCode;

import static java.time.LocalTime.now;

/**
 *
 * Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
 *
 * The integer division should truncate toward zero, which means losing its fractional part.
 * For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.
 *
 * Return the quotient after dividing dividend by divisor.
 *
 * Note: Assume we are dealing with an environment that could only store integers within the 32-bit
 * signed integer range: [−231, 231 − 1]. For this problem, if the quotient is strictly greater than 231 - 1,
 * then return 231 - 1, and if the quotient is strictly less than -231, then return -231.
 *
 *
 *
 * Example 1:
 *
 * Input: dividend = 10, divisor = 3
 * Output: 3
 * Explanation: 10/3 = 3.33333.. which is truncated to 3.
 * Example 2:
 *
 * Input: dividend = 7, divisor = -3
 * Output: -2
 * Explanation: 7/-3 = -2.33333.. which is truncated to -2.
 *
 *
 * Constraints:
 *
 * -231 <= dividend, divisor <= 231 - 1
 * divisor != 0
 */

/**
 * M - [time: 45-
 */
public class N29MDivideTwoIntegers {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;

        System.out.println("========doRun_demo==========");

        doRun_demo(18, 55, 3);
        doRun_demo(1, 1, 1);
        doRun_demo(3, 10, 3);
        doRun_demo(-3, -10, 3);
        doRun_demo(3, -10, -3);
        doRun_demo(-3, 10, -3);

        doRun_demo(2147483647, 2147483647, 1);
        doRun_demo(2147483647, Integer.MAX_VALUE, 1);

        doRun_demo(-2147483648, Integer.MIN_VALUE, 1);
        doRun_demo(2147483647, Integer.MIN_VALUE, -1);
        doRun_demo(2147483647, -2147483648, -1);

        doRun_demo(1, -2147483648, -2147483647);


        doRun_demo(-1073741824, -2147483648, 2);
        doRun_demo(2, -2147483648, -1073741824);

        doRun_demo(-1073741824, -2147483648, 2);

        doRun_demo(-2147483648, -2147483648, 1);


        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun_demo(int expect, int dividend, int divisor) {
        int res = new N29MDivideTwoIntegers().divide(dividend, divisor);
//        String res = comm.toString(res1);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Divide Two Integers.
    //Memory Usage: 39.7 MB, less than 92.12% of Java online submissions for Divide Two Integers.
    //negative 用负数处理大数 （代码更优雅）
    //Time:O(lgN)
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;

        int DIV_MAX = -1073741824;

        boolean isNegative = (dividend > 0 ? true : false) ^ (divisor > 0 ? true: false);

        //转负数处理
        dividend = dividend < 0 ? dividend : -dividend;
        divisor = divisor < 0 ? divisor : -divisor;

        int res = 0;
        int div = divisor, x = 1;
        while (dividend <= divisor && div <= divisor){
            while (div < dividend && div < divisor) {
                div >>= 1; x >>= 1;
            }
            dividend -= div; res -= x;
            if (div > DIV_MAX) {
                div <<= 1; x <<= 1;
            }
        }
        return isNegative ? res : -res;
    }


    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Divide Two Integers.
    //Memory Usage: 39.7 MB, less than 92.12% of Java online submissions for Divide Two Integers.
    //double + positive + extra
    //用了一个变量来处理大数 ； 被除数翻倍
    public int divide_1(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == -1) return Integer.MAX_VALUE;
            if (divisor == 1) return Integer.MIN_VALUE;
        }

        if (divisor == Integer.MIN_VALUE)
            return (dividend == Integer.MIN_VALUE) ? 1 : 0;

        int DIV_MAX = 1073741824;

        boolean isNegative = (dividend > 0 ? true : false) ^ (divisor > 0 ? true: false);

        int extra = 0;
        if (dividend == Integer.MIN_VALUE) {
            dividend++;
            extra = 1;
        }

        dividend = dividend > 0 ? dividend : -dividend;
        divisor = divisor > 0 ? divisor : -divisor;

        int res = 0;
        int div = divisor;
        int x = 1;
        while (dividend >= divisor && div >= divisor){ //lDividend >= div ||
            while (div > dividend && div > divisor) {
                div >>= 1; x >>= 1;
            }
            dividend -= div; res = res + x;
            dividend = dividend + extra;
            extra = 0;

            if (div < DIV_MAX) {
                div <<= 1; x <<= 1;
            }
        }
        return isNegative ? -res : res;
    }

}
