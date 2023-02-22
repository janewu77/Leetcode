package LeetCode;


import com.sun.jmx.snmp.SnmpUnknownMsgProcModelException;

import java.util.Arrays;

import static java.time.LocalTime.now;

/**
 * You are given an even number of people numPeople that stand around a circle and each person shakes hands with someone else so that there are numPeople / 2 handshakes total.
 *
 * Return the number of ways these handshakes could occur such that none of the handshakes cross.
 *
 * Since the answer could be very large, return it modulo 109 + 7.
 */
public class N1259HHandshakesThatDontCross {

    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");
//        doRun(685542858, 140);
        doRun(1, 2);
        doRun(2, 4);
        doRun(5, 6);
        doRun(14, 8);
        doRun(265470434, 100);
        doRun(685542858, 140);
        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, int n) {
        int res = new N1259HHandshakesThatDontCross().numberOfWays(n);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //3.DP top-down
    //Runtime: 29ms 96%; Memory: 41MB 15.9%;
    //Time: O(N * N); Space: O(N)
    public int numberOfWays(int numPeople) {
        if (numPeople <= 2) return 1;

        int modulo = 1_000_000_007;
        int[] dp = new int[numPeople + 2];
        dp[0] = 1;

        for (int i = 2; i <= numPeople; i += 2) {
            long count = 0;
            for (int j = 0; j < i / 2; j += 2) {
                count += (long)dp[j] * dp[i - j - 2] * (j == i - j - 2 ? 1 : 2) % modulo;
                count %= modulo;
            }
            dp[i] = (int)count;
        }
        return dp[numPeople];
    }


    //2.DP top-down
    //Runtime: 48ms 43.4%; Memory: 40.5MB 18%;
    //Time: O(2^N); Space: O(N)
    int modulo = 1_000_000_007;
    public int numberOfWays_2(int numPeople) {
        if (numPeople <= 2) return 1;
        return helper_dp(numPeople, new int[numPeople + 2]);
    }
    private int helper_dp(int numPeople, int[] memo){
        if (numPeople <= 2) return 1;
        if (memo[numPeople] != 0) return memo[numPeople];

        long res = 0l;
        for (int i = 2; i <= numPeople; i += 2)
            res += (long)helper_dp(i - 2, memo) * helper_dp(numPeople - i, memo) % modulo;
        return memo[numPeople] = (int) (res % modulo);
    }


    //1.recursion
    //TLE
    public int numberOfWays_1(int numPeople) {
        if (numPeople <= 2) return 1;
        long res = 0;
        for (int i = 2; i <= numPeople; i += 2)
            res += numberOfWays_1(i - 2) * numberOfWays_1(numPeople - i);
        return (int)(res % modulo);
    }
}
