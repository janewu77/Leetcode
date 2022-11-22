package LeetCode;

import java.io.IOException;
import java.util.*;

import static java.time.LocalTime.now;

public class N279MPerfectSquares {



    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");

        doRun(3, 43);//25 9 9
        doRun(3, 48); //
        doRun(1, 1);
        doRun(2, 2);
        doRun(3, 3);
        doRun(1, 4);
        doRun(2, 5);
        doRun(2, 13);
        doRun(3, 12);
        doRun(1, 16);
        doRun(2, 20);
        doRun(3, 12);
        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(long expect, int n) {
        int res = new N279MPerfectSquares().numSquares(n);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //3.DP bottom-up
    //Runtime: 37 ms, faster than 82.64% of Java online submissions for Perfect Squares.
    //Memory Usage: 41.2 MB, less than 95.44% of Java online submissions for Perfect Squares.
    //Time: O(N * sqrt(N); Space: O(N)
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int k = 1, sq = 1; sq <= i; k++, sq = k * k) {
                if (i - sq >= 0) dp[i] = Math.min(dp[i], dp[i - sq] + 1);
            }
        }
        return dp[n];
    }

    //2.Mathematics
    //three-square theorem & Lagrange's four-square theorem
    //Runtime: 2 ms, faster than 98.34% of Java online submissions for Perfect Squares.
    //Memory Usage: 41.4 MB, less than 93.63% of Java online submissions for Perfect Squares.
    //Time: O(sqrt(n); Space: O(1)
    public int numSquares_2(int n) {
        // four-square and three-square theorems.
        while (n % 4 == 0) n /= 4;
        if (n % 8 == 7) return 4;

        if (this.isSquare(n)) return 1;

        // enumeration to check if the number can be decomposed into sum of two squares.
        for (int i = 1; i * i <= n; ++i)
            if (isSquare(n - i * i)) return 2;

        // bottom case of three-square theorem.
        return 3;
    }
    private boolean isSquare(int n) {
        int sq = (int) Math.sqrt(n);
        return n == sq * sq;
    }

    //1.recursion + memo
    //Runtime: 18 ms, faster than 95.34% of Java online submissions for Perfect Squares.
    //Memory Usage: 42.2 MB, less than 80.75% of Java online submissions for Perfect Squares.
    private Map<Integer,Integer> memo = new HashMap<>();
    public int numSquares_1(int n) {
        if (n <= 3) return n;
        if (memo.containsKey(n)) return memo.get(n);

        int res = Integer.MAX_VALUE;
        //int res = 4; //Lagrange's four-square theorem
        for (int i = (int)Math.sqrt(n); i >= 2; i--) {
            int sq = i * i, count = n /sq;
            //if (count > 3) break; //Lagrange's four-square theorem
            res = Math.min(res, count + numSquares_1(n % sq));
        }
        memo.put(n, res);
        return res;
    }


}
