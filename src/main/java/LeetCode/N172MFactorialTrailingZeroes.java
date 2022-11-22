package LeetCode;


import java.math.BigInteger;

import static java.time.LocalTime.now;

/**
 * Given an integer n, return the number of trailing zeroes in n!.
 *
 * Note that n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: 0
 * Explanation: 3! = 6, no trailing zero.
 * Example 2:
 *
 * Input: n = 5
 * Output: 1
 * Explanation: 5! = 120, one trailing zero.
 * Example 3:
 *
 * Input: n = 0
 * Output: 0
 *
 *
 * Constraints:
 *
 * 0 <= n <= 104
 *
 *
 * Follow up: Could you write a solution that works in logarithmic time complexity?
 */
public class N172MFactorialTrailingZeroes {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;

        System.out.println("========doRun_demo==========");
        doRun_demo(0, 3);
        doRun_demo(1, 5);
        doRun_demo(0, 0);

        doRun_demo(1249, 5000);

        System.out.println(now());
        System.out.println("==================");

    }

    static private void doRun_demo(int expect, int n) {
        int res = new N172MFactorialTrailingZeroes().trailingZeroes(n);
//        String res = comm.toString(res1);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 1 ms, faster than 88.08% of Java online submissions for Factorial Trailing Zeroes.
    //Memory Usage: 40.9 MB, less than 74.05% of Java online submissions for Factorial Trailing Zeroes.
    public int trailingZeroes(int n) {
        int count5 = 0;
        while ((n /= 5) > 0) count5 += n;
        return count5;
    }


    //Runtime: 1 ms, faster than 88.08% of Java online submissions for Factorial Trailing Zeroes.
    //Memory Usage: 41.8 MB, less than 8.42% of Java online submissions for Factorial Trailing Zeroes.
    //Time : O(logN);
    public int trailingZeroes_3(int n) {
        int count5 = 0, powerOf5 = 5;
        while (powerOf5 <= n){
            count5 += n / powerOf5;
            powerOf5 *= 5;
        }
        return count5;
    }


    //Runtime: 4 ms, faster than 14.42% of Java online submissions for Factorial Trailing Zeroes.
    //Memory Usage: 38.9 MB, less than 99.77% of Java online submissions for Factorial Trailing Zeroes.
    //5*2 = 10 可得到一个零。 统计5的个数，可统计出尾零的个数。
    //Time: O(n/5) ; Space : O(1)
    public int trailingZeroes_2(int n) {
        int count5 = 0;
        for (int i = 5 ; i <= n; i += 5){
            int powerOf5 = 5;
            while (i % powerOf5 == 0) {
                count5++;
                powerOf5 *= 5;
            }
        }
        return count5;
    }

    //TLE
    // space:O(nlogn).
    public int trailingZeroes_1(int n) {

        // Calculate n!
        BigInteger nFactorial = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            nFactorial = nFactorial.multiply(BigInteger.valueOf(i));
        }

        // Count how many 0's are on the end.
        int zeroCount = 0;

        while (nFactorial.mod(BigInteger.TEN).equals(BigInteger.ZERO)) {
            nFactorial = nFactorial.divide(BigInteger.TEN);
            zeroCount++;
        }

        return zeroCount;
    }
}
