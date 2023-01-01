package LeetCode;

import java.io.IOException;

import static java.time.LocalTime.now;

/**
 * Given an integer n, return the number of prime numbers that are strictly less than n.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 * Example 2:
 *
 * Input: n = 0
 * Output: 0
 * Example 3:
 *
 * Input: n = 1
 * Output: 0
 *
 *
 * Constraints:
 *
 * 0 <= n <= 5 * 106
 */

//204. Count Primes
//2523. Closest Prime Numbers in Range
public class N204MCountPrimes {

    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");
        doRun(0, 2);
        doRun(1, 3);
        doRun(4, 10);
        doRun(114155, 1500000);
        doRun(348513, 5000000);

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, int n) {
        int res = new N204MCountPrimes().countPrimes(n);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //2.Sieve of Eratosthenes
    //Runtime: 173ms 52%; Memory: 69.3MB 11%
    //Time: O(sqrt(N) * loglogN); Space: O(N)
    public int countPrimes(int n) {
        if (n <= 2) return 0;
        int[] data = new int[n];
        data[0] = -1; data[1] = -1;
        int res = 0;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (data[i] == 0) {
                for (int j = i * i; j < n; j += i)
                    data[j] = -1;
            }
        }
        for (int i = 2; i < n; i ++) if (data[i] == 0) res++;
        return res;
    }


    //1.brute force
    // TLE
    //Time: O(N * sqrt(N)); Space: O(1)
    public int countPrimes_1(int n) {
        if (n <= 2) return 0;
        int count = 1;
        for (int i = 3; i < n; i += 2) {
            if (isPrimes(i)) count++;
        }
        return count;
    }
    private boolean isPrimes(int n){
        for (int i = 3; i <= Math.sqrt(n); i+= 2)
            if (n % i == 0) return false;
        return true;
    }

}
