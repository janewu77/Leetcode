package Contest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalTime.now;

/**
 * You are given a positive integer n.
 *
 * Continuously replace n with the sum of its prime factors.
 *
 * Note that if a prime factor divides n multiple times, it should be included in the sum as many times as it divides n.
 * Return the smallest value n will take on.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 15
 * Output: 5
 * Explanation: Initially, n = 15.
 * 15 = 3 * 5, so replace n with 3 + 5 = 8.
 * 8 = 2 * 2 * 2, so replace n with 2 + 2 + 2 = 6.
 * 6 = 2 * 3, so replace n with 2 + 3 = 5.
 * 5 is the smallest value n will take on.
 * Example 2:
 *
 * Input: n = 3
 * Output: 3
 * Explanation: Initially, n = 3.
 * 3 is the smallest value n will take on.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 105
 */


//2507. Smallest Value After Replacing With Sum of Prime Factors
public class N6266MSmallestValueAfterReplacingWithSumofPrimeFactors {

    static public void main(String... args) throws IOException{
        System.out.println(now());
        System.out.println("==================");

        doRun(5, 15);
        doRun(4, 4);
        doRun(3, 3);
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int n) {
        int res = new N6266MSmallestValueAfterReplacingWithSumofPrimeFactors().smallestValue(n);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //1.Math
    //Runtime: 2ms 90%; Memory: 41.3MB 30%
    //Time: O(N*logN); Space: O(logN)
    public int smallestValue(int n) {
        int lastN = n, minN = Integer.MAX_VALUE;

        while (lastN != minN) {
            lastN = n;
            List<Integer> factors = primeDecompose(n);
            n = 0;
            for (int v : factors) n += v;
            minN = Math.min(minN, n);
        }
        return minN;
    }

    //Time: O(sqrt(num))
    public List<Integer> primeDecompose(int num) {
        List<Integer> primeFactors = new ArrayList<>();
        int factor = 2;

        while (factor * factor <= num) {
            if (num % factor == 0) {
                primeFactors.add(factor);
                num = num / factor;
            } else {
                factor += 1;
            }
        }
        primeFactors.add(num);
        return primeFactors;
    }



}


