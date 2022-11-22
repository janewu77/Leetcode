package Contest;

import java.util.HashSet;
import java.util.Set;

import static java.time.LocalTime.now;

/**
 * You are given an array nums consisting of positive integers.
 *
 * You have to take each integer in the array, reverse its digits, and add it to the end of the array. You should apply this operation to the original integers in nums.
 *
 * Return the number of distinct integers in the final array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,13,10,12,31]
 * Output: 6
 * Explanation: After including the reverse of each number, the resulting array is [1,13,10,12,31,1,31,1,21,13].
 * The reversed integers that were added to the end of the array are underlined. Note that for the integer 10, after reversing it, it becomes 01 which is just 1.
 * The number of distinct integers in this array is 6 (The numbers 1, 10, 12, 13, 21, and 31).
 * Example 2:
 *
 * Input: nums = [2,2,2]
 * Output: 1
 * Explanation: After including the reverse of each number, the resulting array is [2,2,2,2,2,2].
 * The number of distinct integers in this array is 1 (The number 2).
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 106
 */

/**
 * M - [time 20-
 */

//2442. Count Number of Distinct Integers After Reverse Operations
public class N6205MCountNumberofDistinctIntegersAfterReverseOperations {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun(2, new int[]{1,2,1});

        doRun(7, new int[]{2,13,201,12,31});
        doRun(6, new int[]{1,13,10,12,31});
        doRun(1, new int[]{2,2,2});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[] nums) {
        int res = new N6205MCountNumberofDistinctIntegersAfterReverseOperations().countDistinctIntegers(nums);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //1.Brute force
    //Runtime: 74 ms, faster than 83.33% of Java online submissions for Count Number of Distinct Integers After Reverse Operations.
    //Memory Usage: 63.6 MB, less than 83.33% of Java online submissions for Count Number of Distinct Integers After Reverse Operations.
    //Time: O(N); Space:O(N)
    public int countDistinctIntegers(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums){
            set.add(n);
            set.add(reverse(n));
        }
        return set.size();
    }

    private int reverse(int val) {
        if (val < 9) return val;
        int res = 0;
        while (val >= 1) {
            res = res * 10 + val % 10;
            val /= 10;
        }
        return res;
    }

}
