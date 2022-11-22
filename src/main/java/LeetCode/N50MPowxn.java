package LeetCode;

import static java.time.LocalTime.now;

/**
 * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
 *
 * Example 1:
 * Input: x = 2.00000, n = 10
 * Output: 1024.00000
 *
 *
 * Example 2:
 * Input: x = 2.10000, n = 3
 * Output: 9.26100
 *
 *
 * Example 3:
 * Input: x = 2.00000, n = -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 *
 *
 * Constraints:
 * -100.0 < x < 100.0
 * -231 <= n <= 231-1
 * -104 <= xn <= 104
 */

/**
 * M - [time: 30
 */
public class N50MPowxn {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;

        System.out.println("========doRun_demo==========");

        doRun_demo(1024.00000, 2, 10);
        doRun_demo(9.261000000000001, 2.1, 3);

        doRun_demo(0.25000, 2, -2);

        doRun_demo(1, 1, 2147483647);
        doRun_demo(1, -1, -2147483648);


        doRun_demo(1, 1, 0);
        doRun_demo(1, 1, 2);
        doRun_demo(1, 1, 3);

        doRun_demo(1, -1, 0);
        doRun_demo(-1, -1, 1);
        doRun_demo(1, -1, 2);
        doRun_demo(-1, -1, 3);

        doRun_demo(0, 0, 33);

        doRun_demo(0.25000, 2, -2);

        doRun_demo(0, 2, -2147483648);

        doRun_demo(4, -2, 2);
        doRun_demo(-8, -2, 3);

        doRun_demo(0, 0, -3);


        System.out.println(now());
        System.out.println("==================");

    }

    static private void doRun_demo(double expect, double x, int n) {
        double res = new N50MPowxn().myPow(x, n);
//        String res = comm.toString(res1);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    //Runtime: 1 ms, faster than 89.85% of Java online submissions for Pow(x, n).
    //Memory Usage: 41.6 MB, less than 92.28% of Java online submissions for Pow(x, n).
    //time: O(LgN); Space: O(1)
    public double myPow(double x, int n) {
        if (x == 0) return 0;

        x = n < 0 ? 1 / x : x;
        double res = 1, dX = x;
        int dI = 1;

        if (n > 0) n = -n; //n can handle Integer.MAX_VALUE & Integer.MIN_VALUE
        while (n < 0) {
            res *= dX; n += dI;

            dX *= dX; dI += dI; //binary exponentiation
            if (dI > Math.abs(n / 2)){
                dX = x; dI = 1;
            }
        }
        return res;
    }

    //TLE
    //Time: O(N); Space: O(1)
    public double myPow_0(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double ans = 1;
        for (long i = 0; i < N; i++)
            ans = ans * x;
        return ans;
    }
}
