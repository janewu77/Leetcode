package LeetCode;


/**
 * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
 *
 * Given an integer n, return true if n is an ugly number.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 6
 * Output: true
 * Explanation: 6 = 2 × 3
 * Example 2:
 *
 * Input: n = 1
 * Output: true
 * Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
 * Example 3:
 *
 * Input: n = 14
 * Output: false
 * Explanation: 14 is not ugly since it includes the prime factor 7.
 *
 *
 * Constraints:
 *
 * -231 <= n <= 231 - 1
 */
public class N263EUglyNumber {


    //1. recursion
    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Ugly Number.
    //Memory Usage: 39.7 MB, less than 85.15% of Java online submissions for Ugly Number.
    //Time: O(logN); Space: O(logN)
    public boolean isUgly(int n) {
        if (n <= 0) return false;
        if (n == 1) return true;
        if (n % 2 == 0) return isUgly(n / 2);
        if (n % 3 == 0) return isUgly(n / 3);
        if (n % 5 == 0) return isUgly(n / 5);
        return false;
    }

    //2.one pass
    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Ugly Number.
    //Memory Usage: 41.7 MB, less than 14.60% of Java online submissions for Ugly Number.
    //Time: O(logN); Space: O(1)
    public boolean isUgly_2(int n) {
        if (n <= 0) return false;
        while (n % 2 == 0 || n % 3 == 0 || n % 5 == 0) {
            if (n % 2 == 0) n /= 2;
            if (n % 3 == 0) n /= 3;
            if (n % 5 == 0) n /= 5;
        }
        return n == 1;
    }

    //1. brute force
    //Runtime: 2 ms, faster than 80.60% of Java online submissions for Ugly Number.
    //Memory Usage: 40.6 MB, less than 80.67% of Java online submissions for Ugly Number.
    //Time: O(logN); Space: O(1)
    public boolean isUgly_1(int n) {
        if (n <= 0) return false;
        while (n % 2 == 0) n /= 2;
        while (n % 3 == 0) n /= 3;
        while (n % 5 == 0) n /= 5;
        return n == 1;
    }


}
