package LeetCode;


import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.LocalTime.now;

/**
 * You have n dice and each die has k faces numbered from 1 to k.
 *
 * Given three integers n, k, and target, return the number of possible ways (out of the kn total ways) to roll the dice so the sum of the face-up numbers equals target. Since the answer may be too large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1, k = 6, target = 3
 * Output: 1
 * Explanation: You throw one die with 6 faces.
 * There is only one way to get a sum of 3.
 * Example 2:
 *
 * Input: n = 2, k = 6, target = 7
 * Output: 6
 * Explanation: You throw two dice, each with 6 faces.
 * There are 6 ways to get a sum of 7: 1+6, 2+5, 3+4, 4+3, 5+2, 6+1.
 * Example 3:
 *
 * Input: n = 30, k = 30, target = 500
 * Output: 222616187
 * Explanation: The answer must be returned modulo 109 + 7.
 *
 *
 * Constraints:
 *
 * 1 <= n, k <= 30
 * 1 <= target <= 1000
 */

/**
 * M - [Time: 15-
 */
public class N1155MNumberofDiceRollsWithTargetSum {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun(6, 2,6,7);
        doRun(5, 2,6,8);
        doRun(4, 2,6,9);

        doRun(15, 3,6,7);
        doRun(20, 4,6,7);

        doRun(1, 1,6,3);

        doRun(222616187, 30,30,500);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int n, int k, int target) {
        int res = new N1155MNumberofDiceRollsWithTargetSum()
                .numRollsToTarget(n, k, target);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }
//    private int MODULO = 1_000_000_007;


    //Runtime: 19 ms, faster than 83.29% of Java online submissions for Number of Dice Rolls With Target Sum.
    //Memory Usage: 41.4 MB, less than 87.26% of Java online submissions for Number of Dice Rolls With Target Sum.
    //DP | bottom up | 1d-array
    //Time: O( N * Target * K); Space: O(Target)
    public int numRollsToTarget(int n, int k, int target) {
        int MODULO = 1_000_000_007;
        int[] dp1 = new int[target + 1];
        for (int i = 1; i <= Math.min(k, target); i++) dp1[i] = 1;

        for (int i = 2; i <= n; i++) {
            int[] dp = new int[target + 1];
            for (int ki = 1; ki <= k; ki++) {
                for (int t = 1 + ki; t <= target; t++){
                    dp[t] = (dp[t] + dp1[t - ki]) % MODULO;
                }
            }
            dp1 = dp;
        }
        return dp1[target];
    }

    //Runtime: 21 ms, faster than 78.17% of Java online submissions for Number of Dice Rolls With Target Sum.
    //Memory Usage: 41.3 MB, less than 90.72% of Java online submissions for Number of Dice Rolls With Target Sum.
    //DP | bottom up | 2D-array
    //Time: O( N * Target * K); Space: O(N * Target)
    public int numRollsToTarget_3(int n, int k, int target) {
        int MODULO = 1_000_000_007;
        int[][] dp = new int[n + 1][target + 1];
        for (int i = 1; i <= Math.min(k, target); i++) dp[1][i] = 1;

        for (int i = 2; i <= n; i++) {
            for (int ki = 1; ki <= k; ki++) {
                for (int t = 1 + ki; t <= target; t++){
                    dp[i][t] = (dp[i][t] + dp[i-1][t - ki]) % MODULO;
                }
            }
        }
        return dp[n][target];
    }


    //Runtime: 18 ms, faster than 85.98% of Java online submissions for Number of Dice Rolls With Target Sum.
    //Memory Usage: 43.1 MB, less than 36.11% of Java online submissions for Number of Dice Rolls With Target Sum.
    //DP - top down : recursion + memo
    //Time:O(N * K * T); Space:O(N * T)
    private int MODULO = 1_000_000_007;
    public int numRollsToTarget_2(int n, int k, int target) {
        return helper_dp(n, k, target,  new Integer[n + 1][target + 1]);
    }

    public int helper_dp(int n, int k, int target, Integer[][] arrMmemo) {
        if (target < 0) return 0;
        if (n == 0) return target == 0 ? 1 : 0;

        if (arrMmemo[n][target] != null) return arrMmemo[n][target];

        long res = 0;
        for (int i = 1; i <= k; i++)
            res += helper_dp(n - 1, k, target - i, arrMmemo);

        return  arrMmemo[n][target] = (int) (res % MODULO);
    }



    //Runtime: 217 ms, faster than 10.44% of Java online submissions for Number of Dice Rolls With Target Sum.
    //Memory Usage: 44.5 MB, less than 19.72% of Java online submissions for Number of Dice Rolls With Target Sum.
    //DP - topdown : recursion + memo
    //private int MODULO = 1_000_000_007;
    Map<Pair<Integer, Integer>, Integer> memo = new HashMap<>();
    public int numRollsToTarget_1(int n, int k, int target) {
        if (target < 0) return 0;
        if (n == 0) return target == 0 ? 1 : 0;

        Pair<Integer, Integer> key = new Pair<>(n, target);
        if (memo.containsKey(key)) return memo.get(key);

        long res = 0;
        for (int i = 1; i <= k; i++)
            res += numRollsToTarget_1(n - 1, k, target - i);

        memo.put(key, (int) (res % MODULO));
        return memo.get(key);
    }

}
