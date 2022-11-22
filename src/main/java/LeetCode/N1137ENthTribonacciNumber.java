package LeetCode;


/**
 * The Tribonacci sequence Tn is defined as follows:
 *
 * T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
 *
 * Given n, return the value of Tn.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4
 * Output: 4
 * Explanation:
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 * Example 2:
 *
 * Input: n = 25
 * Output: 1389537
 *
 *
 * Constraints:
 *
 * 0 <= n <= 37
 * The answer is guaranteed to fit within a 32-bit integer, ie. answer <= 2^31 - 1.
 */

/**
 * E - [time: 5-
 */
public class N1137ENthTribonacciNumber {

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for N-th Tribonacci Number.
    //Memory Usage: 40.6 MB, less than 66.74% of Java online submissions for N-th Tribonacci Number.
    public int tribonacci(int n) {
        int dp[] = {0, 1, 1};
        for (int i = 3; i <= n; ++i)
            dp[i % 3] = dp[0] + dp[1] + dp[2];
        return dp[n % 3];
    }


    //Runtime: 0 ms, faster than 100.00% of Java online submissions for N-th Tribonacci Number.
    //Memory Usage: 40.7 MB, less than 58.70% of Java online submissions for N-th Tribonacci Number.
    //O(N).
    public int tribonacci_1(int n) {
        int t0 = 0, t1 = 1, t2 = 1;
        if (n <= 1) return n;

        for(int i = 3; i <= n; i++){
            int res = t0 + t1 + t2;
            t0 = t1; t1 = t2; t2 = res;
        }
        return t2;
    }
}
