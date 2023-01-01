package Contest;

import java.io.IOException;
import java.util.Arrays;

import static java.time.LocalTime.now;

/**
 * Given two positive integers left and right, find the two integers num1 and num2 such that:
 *
 * left <= nums1 < nums2 <= right .
 * nums1 and nums2 are both prime numbers.
 * nums2 - nums1 is the minimum amongst all other pairs satisfying the above conditions.
 * Return the positive integer array ans = [nums1, nums2]. If there are multiple pairs satisfying these conditions, return the one with the minimum nums1 value or [-1, -1] if such numbers do not exist.
 *
 * A number greater than 1 is called prime if it is only divisible by 1 and itself.
 *
 *
 *
 * Example 1:
 *
 * Input: left = 10, right = 19
 * Output: [11,13]
 * Explanation: The prime numbers between 10 and 19 are 11, 13, 17, and 19.
 * The closest gap between any pair is 2, which can be achieved by [11,13] or [17,19].
 * Since 11 is smaller than 17, we return the first pair.
 * Example 2:
 *
 * Input: left = 4, right = 6
 * Output: [-1,-1]
 * Explanation: There exists only one prime number in the given range, so the conditions cannot be satisfied.
 *
 *
 * Constraints:
 *
 * 1 <= left <= right <= 106
 */

//204. Count Primes
//2523. Closest Prime Numbers in Range

public class N6280MClosestPrimeNumbersinRange {

    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");
        doRun(new int[]{11,13}, 10, 19);
        doRun(new int[]{-1, -1}, 4, 6);
        doRun(new int[]{2,3}, 1,1900);
        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int[] expect, int left, int right) {
        int[] res = new N6280MClosestPrimeNumbersinRange().closestPrimes(left, right);
        boolean success = Arrays.equals(res, expect);
        System.out.println("["+success+"] expect:" + Arrays.toString(expect) + " res:" + Arrays.toString(res));
    }

    //2.Sieve of Eratosthenes
    //Runtime: 127 ms 66%; Memory: 46.3MB 83%
    //Time: O(sqrt(right) * loglog(right) + right - left); Space:O(right)
    public int[] closestPrimes(int left, int right) {
        int[] primes = new int[right + 1];
        primes[0] = -1; primes[1] = -1;
        for (int i = 2; i <= Math.sqrt(right); i++) {
            if (primes[i] == 0) {
                for (int j = i * i; j <= right; j += i)
                    primes[j] = -1;
            }
        }

        int[] res = new int[]{-1, -1};
        int diff = Integer.MAX_VALUE;
        int lastPrime = -1;
        for (int i = left; i <= right; i++) {
            if (primes[i] == 0) {
                if (lastPrime != -1 && i - lastPrime < diff) {
                    res[0] = lastPrime;
                    res[1] = i;
                    diff = i - lastPrime;
                }
                lastPrime = i;
            }
        }
        return res;
    }

    //1.brute force
    //Runtime: 1492 ms 16%; Memory: 39.9MB 100%
    //Time: O( (right - left) * sqrt(right)); Space:O(1)
    public int[] closestPrimes_1(int left, int right) {
        int[] res = new int[]{-1, -1};
        int diff = Integer.MAX_VALUE;

        int lastPrimes = -1;
        if (left <= 2) {
            lastPrimes = 2;
            left = 3;
        }
        if (left % 2 == 0) left++;

        for (int i = left; i <= right; i += 2) {
            if (isPrimes(i)) {
                if (lastPrimes != -1) {
                    int x = i - lastPrimes;
                    if (x < diff) {
                        res[0] = lastPrimes;
                        res[1] = i;
                        diff = x;
                    }
                }
                lastPrimes = i;
            }
        }
        return res;
    }

    //Time: O(sqrt(N) / 2); Space: O(1)
    private boolean isPrimes(int n){
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(n); i+=2)
            if (n % i == 0) return false;
        return true;
    }
}
