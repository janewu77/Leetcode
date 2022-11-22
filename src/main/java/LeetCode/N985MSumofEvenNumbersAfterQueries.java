package LeetCode;

import utils.comm;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

/**
 * You are given an integer array nums and an array queries where queries[i] = [vali, indexi].
 *
 * For each query i, first, apply nums[indexi] = nums[indexi] + vali, then print the sum of the even values of nums.
 *
 * Return an integer array answer where answer[i] is the answer to the ith query.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4], queries = [[1,0],[-3,1],[-4,0],[2,3]]
 * Output: [8,6,2,4]
 * Explanation: At the beginning, the array is [1,2,3,4].
 * After adding 1 to nums[0], the array is [2,2,3,4], and the sum of even values is 2 + 2 + 4 = 8.
 * After adding -3 to nums[1], the array is [2,-1,3,4], and the sum of even values is 2 + 4 = 6.
 * After adding -4 to nums[0], the array is [-2,-1,3,4], and the sum of even values is -2 + 4 = 2.
 * After adding 2 to nums[3], the array is [-2,-1,3,6], and the sum of even values is -2 + 6 = 4.
 * Example 2:
 *
 * Input: nums = [1], queries = [[4,0]]
 * Output: [0]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * 1 <= queries.length <= 104
 * -104 <= vali <= 104
 * 0 <= indexi < nums.length
 */

public class N985MSumofEvenNumbersAfterQueries {


    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;


        data = new int[]{1,2,3,4};
        data2 = new int[][]{{1,0},{-3,1},{-4,0},{2,3}};
        doRun("8,6,2,4", data, data2);


        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(String expect, int[] nums, int[][] queries) {
        int[] res1 = new N985MSumofEvenNumbersAfterQueries().sumEvenAfterQueries(nums, queries);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString(res1);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 7 ms, faster than 60.77% of Java online submissions for Sum of Even Numbers After Queries.
    //Memory Usage: 75.8 MB, less than 8.04% of Java online submissions for Sum of Even Numbers After Queries.
    //Time: O(N + Q); Space:O(1)
    //N is the number of numbs; Q is the size of queries;
    public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        int[] res = new int[nums.length];

        int evenSum = 0;
        for (int num: nums)
            if (num % 2 == 0) evenSum += num;

        for(int i =0 ; i<nums.length; i++){
            int idx = queries[i][1];
            if (nums[idx] % 2 == 0) evenSum -= nums[idx];
            nums[idx] += queries[i][0];
            if (nums[idx] % 2 == 0) evenSum += nums[idx];
            res[i] = evenSum;
        }

        return res;
    }
}
