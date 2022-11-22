package LeetCode;


import java.util.HashSet;
import java.util.Set;

import static java.time.LocalTime.now;

/**
 * Write an algorithm to determine if a number n is happy.
 *
 * A happy number is a number defined by the following process:
 *
 * Starting with any positive integer, replace the number by the
 * sum of the squares of its digits.
 * Repeat the process until the number equals 1 (where it will stay),
 * or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy.
 * Return true if n is a happy number, and false if not.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 19
 * Output: true
 * Explanation:
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 * Example 2:
 *
 * Input: n = 2
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= n <= 231 - 1
 */

/**
 * E - [Time: 15-
 *
 */
public class N202EHappyNumber {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;

        System.out.println("========doRun_demo==========");
        doRun_demo(true, 19);
        doRun_demo(false, 2);
        doRun_demo(true, 1);


        System.out.println(now());
        System.out.println("==================");

    }

    static private void doRun_demo(boolean expect, int n) {
        boolean res = new N202EHappyNumber().isHappy(n);
//        String res = comm.toString(res1);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    //from solution
    //Runtime: 1 ms, faster than 98.90% of Java online submissions for Happy Number.
    //Memory Usage: 40.8 MB, less than 78.25% of Java online submissions for Happy Number.
    //hardcode: 4，当出现4就会进入cycle。 这个通常在面试时是做不出来的。
    public int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    public boolean isHappy(int n) {
        while (n != 1 && n != 4)
            n = getNext(n);
        return n == 1;
    }

    //Runtime: 1 ms, faster than 98.90% of Java online submissions for Happy Number.
    //Memory Usage: 40.9 MB, less than 74.41% of Java online submissions for Happy Number.
    //from solution
    //Floyd's Cycle-Finding Algorithm
    //Time: O(logn); Space:O(1)
    public boolean isHappy_2(int n) {
        int slowRunner = n;
        int fastRunner = getNext_2(n);
        while (fastRunner != 1 && slowRunner != fastRunner) {
            slowRunner = getNext_2(slowRunner);
            fastRunner = getNext_2(getNext(fastRunner));
        }
        return fastRunner == 1;
    }

    public int getNext_2(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    //Runtime: 2 ms, faster than 68.39% of Java online submissions for Happy Number.
    //Memory Usage: 41.8 MB, less than 22.47% of Java online submissions for Happy Number.
    //Time: O(logN); Space: O(logN)
    public boolean isHappy_1(int n) {

        Set<Integer> set = new HashSet<>();
        while (n != 1 && !set.contains(n)) {
            set.add(n);
            int sum = 0;
            while (n > 0) {
                int m = n % 10;
                n /= 10;
                sum += m * m;
            }
            n = sum;
        }
        return n == 1;
    }
}
