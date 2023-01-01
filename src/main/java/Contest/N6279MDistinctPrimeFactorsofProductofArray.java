package Contest;

import java.io.IOException;
import java.util.*;

import static java.time.LocalTime.now;

/**
 * Given an array of positive integers nums, return the number of distinct prime factors in the product of the elements of nums.
 *
 * Note that:
 *
 * A number greater than 1 is called prime if it is divisible by only 1 and itself.
 * An integer val1 is a factor of another integer val2 if val2 / val1 is an integer.
 *
 *
 * Example 1:
 *
 * Input: nums = [2,4,3,7,10,6]
 * Output: 4
 * Explanation:
 * The product of all the elements in nums is: 2 * 4 * 3 * 7 * 10 * 6 = 10080 = 25 * 32 * 5 * 7.
 * There are 4 distinct prime factors so we return 4.
 * Example 2:
 *
 * Input: nums = [2,4,8,16]
 * Output: 1
 * Explanation:
 * The product of all the elements in nums is: 2 * 4 * 8 * 16 = 1024 = 210.
 * There is 1 distinct prime factor so we return 1.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * 2 <= nums[i] <= 1000
 */

//2521. Distinct Prime Factors of Product of Array
public class N6279MDistinctPrimeFactorsofProductofArray {

    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");
        doRun(4,new int[]{10,2,4,3,7,6});
        doRun(1, new int[]{2,4,8,16});
        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, int[] nums) {
        int res = new N6279MDistinctPrimeFactorsofProductofArray().distinctPrimeFactors(nums);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 35ms 50%; Memory: 43.4MB 100%
    public int distinctPrimeFactors(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int x : nums)
            set.addAll(primeDecompose(x));
        return set.size();
    }

    public Set<Integer> primeDecompose(int num) {
        Set<Integer> primeFactors = new HashSet<>();

        for (int factor = 2; factor <= Math.sqrt(num); factor++) {
            if (num % factor == 0) {
                primeFactors.add(factor);
                while (num % factor == 0)
                    num /= factor;
            }
        }
        if (num != 1) primeFactors.add(num);
        return primeFactors;
    }

}
