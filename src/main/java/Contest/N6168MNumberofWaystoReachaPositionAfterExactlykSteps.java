package Contest;


import java.math.BigInteger;

import static java.time.LocalTime.now;

/**
 * You are given two positive integers startPos and endPos. Initially, you are standing
 * at position startPos on an infinite number line. With one step, you can move either
 * one position to the left, or one position to the right.
 *
 * Given a positive integer k, return the number of different ways to reach the position
 * endPos starting from startPos, such that you perform exactly k steps. Since the answer
 * may be very large, return it modulo 109 + 7.
 *
 * Two ways are considered different if the order of the steps made is not exactly the same.
 *
 * Note that the number line includes negative integers.
 *
 *
 *
 * Example 1:
 *
 * Input: startPos = 1, endPos = 2, k = 3
 * Output: 3
 * Explanation: We can reach position 2 from 1 in exactly 3 steps in three ways:
 * - 1 -> 2 -> 3 -> 2.
 * - 1 -> 2 -> 1 -> 2.
 * - 1 -> 0 -> 1 -> 2.
 * It can be proven that no other way is possible, so we return 3.
 * Example 2:
 *
 * Input: startPos = 2, endPos = 5, k = 10
 * Output: 0
 * Explanation: It is impossible to reach position 5 from position 2 in exactly 10 steps.
 *
 *
 * Constraints:
 *
 * 1 <= startPos, endPos, k <= 1000
 */

//2400. Number of Ways to Reach a Position After Exactly k Steps
public class N6168MNumberofWaystoReachaPositionAfterExactlykSteps {


    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun(0, 2, 5, 10);
        doRun(0, 2, 5, 1);
        doRun(1, 2, 5, 3);

        doRun(3, 1, 2, 3);

        doRun(1, 1, 2, 1);
        doRun(0, 1, 2, 2);
        doRun(3, 1, 2, 3);
        doRun(0, 1, 2, 4);
        doRun(10, 1, 2, 5);
        doRun(35, 1, 2, 7);
        doRun(68, 264, 198, 68);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int startPos, int endPos, int k) {
        int res = new N6168MNumberofWaystoReachaPositionAfterExactlykSteps()
                .numberOfWays(startPos, endPos, k);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    private int MODULO = 1_000_000_000 + 7;

    //Runtime: 3 ms, faster than 50.00% of Java online submissions for Number of Ways to Reach a Position After Exactly k Steps.
    //Memory Usage: 40.9 MB, less than 100.00% of Java online submissions for Number of Ways to Reach a Position After Exactly k Steps
    //Time: O(klogk)
    public int numberOfWays(int startPos, int endPos, int k) {
        int distance = Math.abs(endPos - startPos);
        if ((distance > k) || (k - distance) % 2 != 0) return 0;

        long res = 1L;
        for (int i = 0; i < (distance + k) / 2; ++i) {
            res = res * (k - i) % MODULO;
            res = res * inv(i + 1) % MODULO;
        }
        return (int)res;
    }

    private long inv(long a) {
        if (a == 1) return 1;
        return (MODULO - MODULO / a) * inv(MODULO % a) % MODULO;
    }


    //from @houxiaoge1992
    //You have to choose k steps, number of ways is factorial(k)
    //among those k steps, suppose d1 step is left, and d2 step is right.
    // All the left and all the right is the same. so need to divide factorial(d1) and factorial(d2) from result.
    //Runtime: 9 ms, faster than 50.00% of Java online submissions for Number of Ways to Reach a Position After Exactly k Steps.
    //Memory Usage: 41.6 MB, less than 50.00% of Java online submissions for Number of Ways to Reach a Position After Exactly k Steps.
    //用了阶乘
    //Time:O(K)
    public int numberOfWays_factorial(int startPos, int endPos, int k) {
        int distance = Math.abs(endPos - startPos);
        if ((distance > k) || (k - distance) % 2 != 0) return 0;
        if (distance == k) return 1;
        //d1 = (k - abs(endPos - startPos)) // 2
        //d2 = k - d1
        BigInteger res = factorial(k)
                .divide(factorial((k - distance) / 2))
                .divide(factorial((k + distance) / 2));

        return res.mod(BigInteger.valueOf(MODULO)).intValue();
    }
    private BigInteger factorial(int n){
        BigInteger res = BigInteger.ONE;
        while (n > 1) res = res.multiply(BigInteger.valueOf(n--));
        return res;
    }


    //from @votrubac
    //Runtime: 20 ms, faster than 50.00% of Java online submissions for Number of Ways to Reach a Position After Exactly k Steps.
    //Memory Usage: 49.8 MB, less than 50.00% of Java online submissions for Number of Ways to Reach a Position After Exactly k Steps.
    //DP: bottom-up
    //Time: O(K * K); Space: O(K * K)
    public int numberOfWays_dp(int startPos, int endPos, int k) {
        int distance = Math.abs(endPos - startPos);
        if ((distance > k) || (k - distance) % 2 != 0) return 0;
        int MODULO = 1_000_000_000 + 7;

        //Space: O(K * K)
        int[][] dp = new int[k + 1][k + 1];
        dp[1][1] = 1;
        //Time: O(K * K)
        for (int step = 2; step <= k; ++step) {
            dp[step][step] = 1;
            for (int d = 0; d < step - 1; ++d)
                dp[d][step] = (dp[Math.abs(d - 1)][step - 1] + dp[d + 1][step - 1]) % MODULO;
        }
        return dp[distance][k];
    }

    //from @votrubac
    //For k number of steps and distance d, we sum the number of ways for (k - 1 and d + 1), and (k - 1 and abs(d - 1)).
    //Note that We can reach 0 from positions 1 and -1, and the number of ways for negative positions mirrors positive positions.
    //
    //Runtime: 15 ms, faster than 50.00% of Java online submissions for Number of Ways to Reach a Position After Exactly k Steps.
    //Memory Usage: 44.4 MB, less than 50.00% of Java online submissions for Number of Ways to Reach a Position After Exactly k Steps.
    //Dynamic programming : top-down
    //Time: O(2^K); Space: O(Max(D,K) * K + 2^K)
    //private int MODULO = 1_000_000_000 + 7;
    public int numberOfWays_dp_topdonw(int startPos, int endPos, int k) {
        int distance = Math.abs(endPos - startPos);
        if ((distance > k) || (k - distance) % 2 != 0) return 0;

        //Space: O( Max(D,K) * K)
        int[][] memo = new int[(distance + k) / 2 ][k + 1];
        return helper_dp_topdown(distance, k, memo);
    }

    //Time: O(2^K); Space: O(2^K)
    private int helper_dp_topdown(int distance, int steps, int[][] memo){
        if (distance == steps) return 1;

        if (memo[distance][steps] == 0){
            memo[distance][steps] = (helper_dp_topdown(Math.abs(distance - 1), steps - 1, memo)
                    + helper_dp_topdown(distance + 1, steps - 1, memo) ) % MODULO;
        }
        return memo[distance][steps];
    }


}
