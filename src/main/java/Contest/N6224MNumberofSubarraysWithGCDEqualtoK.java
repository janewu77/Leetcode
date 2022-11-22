package Contest;

import java.math.BigInteger;

import static java.time.LocalTime.now;

/**
 * Given an integer array nums and an integer k, return the number of subarrays of nums where the greatest common divisor of the subarray's elements is k.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * The greatest common divisor of an array is the largest integer that evenly divides all the array elements.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [9,3,1,2,6,3], k = 3
 * Output: 4
 * Explanation: The subarrays of nums where 3 is the greatest common divisor of all the subarray's elements are:
 * - [9,3,1,2,6,3]
 * - [9,3,1,2,6,3]
 * - [9,3,1,2,6,3]
 * - [9,3,1,2,6,3]
 * Example 2:
 *
 * Input: nums = [4], k = 7
 * Output: 0
 * Explanation: There are no subarrays of nums where 7 is the greatest common divisor of all the subarray's elements.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * 1 <= nums[i], k <= 109
 */



//2447. Number of Subarrays With GCD Equal to K
public class N6224MNumberofSubarraysWithGCDEqualtoK {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;
        doRun(7,  new int[]{3,12,9,6}, 3);

        doRun(1,  new int[]{1}, 1);
        doRun(4, new int[]{9,3,1,2,6,3}, 3);
        doRun(0,  new int[]{12}, 3);


        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[] nums, int k) {
        int res = new N6224MNumberofSubarraysWithGCDEqualtoK().subarrayGCD(nums, k);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //2.Math
    //Runtime: 7 ms, faster than 100.00% of Java online submissions for Number of Subarrays With GCD Equal to K.
    //Memory Usage: 41.8 MB, less than 100.00% of Java online submissions for Number of Subarrays With GCD Equal to K.
    //Time: O(N * N * logN); Space: O(1)
    public int subarrayGCDx(int[] nums, int k) {
        int res = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % k != 0) continue;
            int idx = nums[i] == k ? i : -1;

            int j = i + 1;
            for (; j < nums.length; j++) {
                int currGCD = gcd(nums[i], nums[j]);
                if (currGCD < k) break;
                else if (idx == -1 && currGCD == k) idx = j;
            }
            if (idx != -1) res += j - idx;
        }
        return res;

    }
    public int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b,a % b);
    }


    //1.build-in GCD
    //Runtime: 228 ms, faster than 100.00% of Java online submissions for Number of Subarrays With GCD Equal to K.
    //Memory Usage: 117.4 MB, less than 100.00% of Java online submissions for Number of Subarrays With GCD Equal to K.
    public int subarrayGCD(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % k != 0) continue;

            int idx = nums[i] == k ? i : -1;
            BigInteger valueI = BigInteger.valueOf(nums[i]);

            int j = i + 1;
            for (; j < nums.length; j++) {
                if (valueI.gcd(BigInteger.valueOf(nums[j])).intValue() < k) break;
                else if (idx == -1 && valueI.gcd(BigInteger.valueOf(nums[j])).intValue() == k)
                    idx = j;
            }
            if (idx != -1) res += j - idx;
        }
        return res;
    }



}
