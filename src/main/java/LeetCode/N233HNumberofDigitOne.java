package LeetCode;


import static java.time.LocalTime.now;

/**
 * Given an integer n, count the total number of digit 1 appearing in all non-negative
 * integers less than or equal to n.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 13
 * Output: 6
 *
 * Example 2:
 * Input: n = 0
 * Output: 0
 *
 *
 * Constraints:
 *
 * 0 <= n <= 109
 *
 */

public class N233HNumberofDigitOne {


    public static void main(String[] args) {

        System.out.println(now());
        int[][] data;
        doRun_demo(0, 0);
        doRun_demo(1, 6);
        doRun_demo(13, 22);
        doRun_demo(154, 234);
        doRun_demo(1753, 2222);

        doRun_demo(4, 11);
        doRun_demo(448, 1111);
        doRun_demo(1, 1);
        doRun_demo(2, 10);
        doRun_demo(21, 100);
        doRun_demo(301, 1000);
        doRun_demo(175, 342);
        doRun_demo(165, 312);


        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun_demo(int expect, int n) {
        int res = new N233HNumberofDigitOne().countDigitOne(n);
//        String res = comm.toString(res1);
//        String res = Arrays.stream(res1).mapToObj(i-> String.valueOf(i)).collect(Collectors.joining(","));
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    //```
    //[0..9] there has only one '1' :             1
    //[0..99]                           20 = 10 * 1 + 10
    //[0..999] :             300 = 10 * 20 + 100
    //[0..9999]: 4000 = 10 * 300 + 1000

    // 342 >>>  [2] 1 + [40] 4 * 1 + 10 +                 [300] 3 * 20 + 100 >>> 1 + 14 + 160 >>> 175
    // 312 >>>  [2] 1 + [10] 1 * 1 + Math.min(10, 12-9) + [300] 3 * 20 + 100 >>> 1 + 4 + 160 >>> 165

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Number of Digit One.
    //Memory Usage: 40.1 MB, less than 84.55% of Java online submissions for Number of Digit One.
    //Time: O(log(N)); Space:O(1)
    public int countDigitOne(int num) {
        if (num == 0) return 0;

        int count = 0;
        int a = 0, b = 1;

        while (num / b > 0) {
            int m = (num / b) % 10;
            if (m > 0) count += m * a + Math.min(b, num % (b * 10) - (b - 1));

            a = 10 * a + b;
            b *= 10;
        }
        return count;
    }
}
