package LeetCode;

import static java.time.LocalTime.now;

/**
 * Given two integers a and b, return the sum of the two integers without using the operators + and -.
 *
 *
 *
 * Example 1:
 *
 * Input: a = 1, b = 2
 * Output: 3
 * Example 2:
 *
 * Input: a = 2, b = 3
 * Output: 5
 *
 *
 * Constraints:
 *
 * -1000 <= a, b <= 1000
 *
 */

/**
 * M - [time: 30-
 *
 * bit manipulation
 *
 *
 */
public class N371MSumofTwoIntegers {


    public static void main(String[] args) {

        System.out.println(now());
        int[] data;

        System.out.println("========doRun_demo==========");
        doRun_demo(4, 0,4);
        doRun_demo(2, 1,1);
        doRun_demo(3, 1,2);

        doRun_demo(-1, 1,-2);

        doRun_demo(50, 20,30);
        doRun_demo(23, 20,3);

        doRun_demo(500, 200,300);

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun_demo(int expect, int a, int b) {
        int res = new N371MSumofTwoIntegers().getSum(a, b);
//        String res = comm.toString(res1);
//        String res = Arrays.stream(res1).mapToObj(i-> String.valueOf(i)).collect(Collectors.joining(","));
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Sum of Two Integers.
    //Memory Usage: 39.3 MB, less than 85.96% of Java online submissions for Sum of Two Integers.
    public int getSum(int a, int b) {
        while ((a & b) != 0) {
            int ans = a ^ b;
            int carry = (a & b) << 1;
            a = ans;
            b = carry;
        }
        return a | b;
    }
}
