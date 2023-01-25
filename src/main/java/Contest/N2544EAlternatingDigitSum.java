package Contest;


/**
 * You are given a positive integer n. Each digit of n has a sign according to the following rules:
 *
 * The most significant digit is assigned a positive sign.
 * Each other digit has an opposite sign to its adjacent digits.
 * Return the sum of all digits with their corresponding sign.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 521
 * Output: 4
 * Explanation: (+5) + (-2) + (+1) = 4.
 * Example 2:
 *
 * Input: n = 111
 * Output: 1
 * Explanation: (+1) + (-1) + (+1) = 1.
 * Example 3:
 *
 * Input: n = 886996
 * Output: 0
 * Explanation: (+8) + (-8) + (+6) + (-9) + (+9) + (-6) = 0.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 109
 */

public class N2544EAlternatingDigitSum {


    //2.left to right
    //Runtime: 0ms 100%; Memory: 39MB 100%
    //Time: O(logN); Space:O(logN)
    public int alternateDigitSum(int n) {
        String str = String.valueOf(n);
        int flag = 1, sum = 0;
        for (int i = 0; i < str.length(); i++) {
            sum += (str.charAt(i) - '0') * flag;
            flag = -flag;
        }
        return sum;
    }

    //1.right to left
    //Runtime: 0ms 100%; Memory: 39MB 100%
    //Time: O(logN); Space:O(1)
    public int alternateDigitSum_1(int n) {
        int flag = 1, sum = 0;
        for (;n > 0; n /= 10) {
            flag = -flag;
            sum += (n % 10) * flag;
        }
        return flag * sum;
    }
}
