package LeetCode;

import static java.time.LocalTime.now;

/**
 * Given a non-negative integer x, compute and return the square root of x.
 *
 * Since the return type is an integer, the decimal digits are truncated,
 * and only the integer part of the result is returned.
 *
 * Note: You are not allowed to use any built-in exponent function or operator, such as pow(x, 0.5) or x ** 0.5.
 *
 *
 *
 * Example 1:
 *
 * Input: x = 4
 * Output: 2
 * Example 2:
 *
 * Input: x = 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842...,
 * and since the decimal part is truncated, 2 is returned.
 *
 *
 * Constraints:
 *
 * 0 <= x <= 231 - 1
 */

/**
 * E - [time: 15-
 */
public class N69ESqrtx {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;

        System.out.println("========doRun_demo==========");
        doRun_demo(0, 0);
        doRun_demo(1, 1);
        doRun_demo(1, 2);
        doRun_demo(1, 3);

        doRun_demo(2, 8);
        doRun_demo(2, 4);
        doRun_demo(3, 10);
        doRun_demo(17, 300);
        doRun_demo(46340, 2147395600);
        doRun_demo(46340, Integer.MAX_VALUE);

        System.out.println(now());
        System.out.println("==================");

    }

    static private void doRun_demo(int expect, int x) {
        int res = new N69ESqrtx().mySqrt(x);
//        String res = comm.toString(res1);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }


    public int mySqrt(int n) {
        if (n < 2) return n;
        int s = 1;
        int e = n / 2;

        while (s <= e) {
            int m = (e + s) / 2;
            //double p = 1d * m * m;
            if(m == n / m) return m;
            if(m >  n/ m) e = m - 1;
            else s = m + 1;
        }
        return e;
    }

    //```
    //Runtime: 33 ms, faster than 10.51% of Java online submissions for Sqrt(x).
    //Memory Usage: 41.1 MB, less than 75.13% of Java online submissions for Sqrt(x).
    //自己写的
    public int mySqrt5(int x) {
        if (x < 2) return x;

        long res = 1;
        long currRes = 1;
        int Max = x / 2;
        double power = res * res;
        while (power <= x && currRes <= Max){
            res = currRes;
            currRes++;
            if (currRes * currRes + power + 2 * res * currRes <= x) {
                currRes = res + currRes;
            }
            power = currRes * currRes;
        }
        return (int)res;
    }


    //from solution
    //Runtime: 2 ms, faster than 83.93% of Java online submissions for Sqrt(x).
    //Memory Usage: 41.3 MB, less than 54.28% of Java online submissions for Sqrt(x).
    //Newton's Method (逼近法）
    //Time: O(logN); Space: O(1)
    public int mySqrt_4(int x) {
        if (x < 2) return x;

        double x0 = x;
        double x1 = (x0 + x / x0) / 2.0;
        while (Math.abs(x0 - x1) >= 1) {
            x0 = x1;
            x1 = (x0 + x / x0) / 2.0;
        }
        return (int)x1;
    }


    //from solution
    //Runtime: 2 ms, faster than 83.93% of Java online submissions for Sqrt(x).
    //Memory Usage: 41.6 MB, less than 29.73% of Java online submissions for Sqrt(x).
    //Approach 3: Recursion + Bit Shifts
    //Time: O(logN); Space: O(logN)
    public int mySqrt_3(int x) {
        if (x < 2) return x;

        int left = mySqrt_3(x >> 2) << 1;
        int right = left + 1;
        return (long)right * right > x ? left : right;
    }

    //from solution
    //Runtime: 3 ms, faster than 38.37% of Java online submissions for Sqrt(x).
    //Memory Usage: 41.2 MB, less than 69.64% of Java online submissions for Sqrt(x).
    //Binary search
    //Time: O(lgn)
    public int mySqrt_2(int x) {
        if (x < 2) return x;

        long num;
        int pivot, left = 2, right = x / 2;
        while (left <= right) {
            pivot = (right + left) / 2;
            num = (long) pivot * pivot;
            if (num > x) right = pivot - 1;
            else if (num < x) left = pivot + 1;
            else return pivot;
        }
        return right;
    }

    //from solution
    //Math
    //Runtime: 2 ms, faster than 83.93% of Java online submissions for Sqrt(x).
    //Memory Usage: 42.1 MB, less than 9.91% of Java online submissions for Sqrt(x).
    //Time: O(1); Space: O(1)
    public int mySqrt_1(int x) {
        if (x < 2) return x;
        int left = (int)Math.pow(Math.E, 0.5 * Math.log(x));
        int right = left + 1;
        return (long)right * right > x ? left : right;
    }
}
