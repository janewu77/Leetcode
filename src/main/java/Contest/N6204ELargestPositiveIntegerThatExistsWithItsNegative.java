package Contest;

import java.util.HashSet;
import java.util.Set;

import static java.time.LocalTime.now;

/**
 * Given an integer array nums that does not contain any zeros, find the largest positive integer k such that -k also exists in the array.
 *
 * Return the positive integer k. If there is no such integer, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-1,2,-3,3]
 * Output: 3
 * Explanation: 3 is the only valid k we can find in the array.
 * Example 2:
 *
 * Input: nums = [-1,10,6,7,-7,1]
 * Output: 7
 * Explanation: Both 1 and 7 have their corresponding negative values in the array. 7 has a larger value.
 * Example 3:
 *
 * Input: nums = [-10,8,6,7,-2,-3]
 * Output: -1
 * Explanation: There is no a single valid k, we return -1.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * -1000 <= nums[i] <= 1000
 * nums[i] != 0
 */

/**
 * E - [time: 5-
 */
//2441. Largest Positive Integer That Exists With Its Negative
public class N6204ELargestPositiveIntegerThatExistsWithItsNegative {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        //doRun(true, "abaccb", new int[]{1,2,3});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, String beginWord) {
//        int res = new N1().ladderLength(beginWord);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 5 ms, faster than 100.00% of Java online submissions for Largest Positive Integer That Exists With Its Negative.
    //Memory Usage: 42.5 MB, less than 60.00% of Java online submissions for Largest Positive Integer That Exists With Its Negative.
    //Time: O(N); Space: O(N)
    public int findMaxK(int[] nums) {

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++)
            //if (nums[i] < 0)
            set.add(nums[i]);

        int res = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > res && set.contains(-nums[i]))
                res = nums[i];
        }
        return res;
    }

}
