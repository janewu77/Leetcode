package LeetCode;



import static java.time.LocalTime.now;

/**
 * You are climbing a staircase. It takes n steps to reach the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 *
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 */
public class N70EClimbingStairs {


    public static void main(String[] args){
        String[] data;
        String expect;

        System.out.println(now());

        doRun(3,3);
        doRun(2,2);
        doRun(5,4);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int n) {
        int res = new N70EClimbingStairs()
                .climbStairs(n);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Climbing Stairs.
    //Memory Usage: 40.9 MB, less than 50.48% of Java online submissions for Climbing Stairs.
    //Fibonacci Formula
    //Time: O(logn); Space: O(1)
    public int climbStairs(int n) {
        double sqrt5 = Math.sqrt(5);
        double phi = (1 + sqrt5) / 2;
        double psi = (1 - sqrt5) / 2;
        //pow method takes nlogn time.
        return (int) ((Math.pow(phi, n + 1) - Math.pow(psi, n + 1)) / sqrt5);
    }

    //Runtime: 1 ms, faster than 9.97% of Java online submissions for Climbing Stairs.
    //Memory Usage: 40.4 MB, less than 77.49% of Java online submissions for Climbing Stairs.
    //Matrix
    // Time: O(logn); Space: O(1)
    public int climbStairs_Binets(int n) {
        int[][] q = {{1, 1}, {1, 0}};
        int[][] res = pow(q, n);
        return res[0][0];
    }
    public int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }
    public int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }


    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Climbing Stairs.
    //Memory Usage: 40.8 MB, less than 50.48% of Java online submissions for Climbing Stairs.
    //Fibonacci Number
    //Time: O(N); Space: O(1)
    public int climbStairs_fn1(int n) {
        int d0 = 0, d1 = 1;
        int res = 1;
        for (int i = 2; i <= n; i++) {
            d0 = d1; d1 = res;
            res = d0 + d1;
        }
        return res;
    }
    public int climbStairs_fn2(int n) {
        int a = 1, b = 1;
        while (n-- > 0)
            a = (b += a) - a;
        return a;
    }


    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Climbing Stairs.
    //Memory Usage: 40.6 MB, less than 73.03% of Java online submissions for Climbing Stairs.
    //DP: bottom-up
    //Time: O(N); Space: O(N)
    public int climbStairs_dp2(int n) {
        int[] dp = new int[n + 1];
        //dp[0] = 1;
        dp[1] = 1; dp[2] = 2;
        for (int i = 3; i <= n; i++)
            dp[i] = dp[i - 1] + dp[i - 2];
        return dp[n];
    }

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Climbing Stairs.
    //Memory Usage: 40.5 MB, less than 77.49% of Java online submissions for Climbing Stairs.
    //recursion
    //Time: O(N); Space: O(N+N)
    public int climbStairs_dp1(int n) {
        int[] memo = new int[n + 1];
        helper1(n, memo);
        return memo[n];
    }

    private int helper1(int n, int[] memo){
        if (n < 0) return 0;
        if (n == 0)  return 1;
        if (memo[n] != 0) return memo[n];
        return memo[n] = helper1(n - 1, memo) + helper1(n - 2, memo);
    }
}
