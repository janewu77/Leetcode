package LeetCode;


import java.util.Arrays;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

/**
 * You are given an integer array nums of length n, and an integer array queries of length m.
 *
 * Return an array answer of length m where answer[i] is the maximum size of a subsequence
 * that you can take from nums such that the sum of its elements is less than or equal to queries[i].
 *
 * A subsequence is an array that can be derived from another array by deleting some or
 * no elements without changing the order of the remaining elements.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,5,2,1], queries = [3,10,21]
 * Output: [2,3,4]
 * Explanation: We answer the queries as follows:
 * - The subsequence [2,1] has a sum less than or equal to 3. It can be proven
 * that 2 is the maximum size of such a subsequence, so answer[0] = 2.
 * - The subsequence [4,5,1] has a sum less than or equal to 10. It can be proven
 * that 3 is the maximum size of such a subsequence, so answer[1] = 3.
 * - The subsequence [4,5,2,1] has a sum less than or equal to 21. It can be proven
 * that 4 is the maximum size of such a subsequence, so answer[2] = 4.
 * Example 2:
 *
 * Input: nums = [2,3,4,5], queries = [1]
 * Output: [0]
 * Explanation: The empty subsequence is the only subsequence that has a sum less than or equal to 1, so answer[0] = 0.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * m == queries.length
 * 1 <= n, m <= 1000
 * 1 <= nums[i], queries[i] <= 106
 */

/**
 * M - [time:
 */
public class N2389ELongestSubsequenceWithLimitedSum {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[] queries;
        int[][] data2;

        data = new int[]{1,2,3,4,5};
        queries = new int[]{16, 13};
        doRun_demo("5", data, queries);

        data = new int[]{1,2,4,6};
        queries = new int[]{9};
        doRun_demo("3", data, queries);

        data = new int[]{4,5,2,1};
        queries = new int[]{3,10,21};
        doRun_demo("2,3,4", data, queries);

        data = new int[]{736411,184882,914641,37925,214915};
        queries = new int[]{331244,273144,118983,118252,305688,718089,665450};
        doRun_demo("2,2,1,1,2,3,3", data, queries);


        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun_demo(String expect, int[] nums, int[] queries) {
        int[] res1 = new N2389ELongestSubsequenceWithLimitedSum().answerQueries(nums,queries);
//        String res = comm.toString(res1);
        String res = Arrays.stream(res1).mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("==================");
    }


    //from @lee215
    //Runtime: 5 ms, faster than 60.00% of Java online submissions for Longest Subsequence With Limited Sum.
    //Memory Usage: 49.1 MB, less than 20.00% of Java online submissions for Longest Subsequence With Limited Sum.
    //sort + Binary search
    //Time:O(NlogN + N + MlogN) ; Space(N)
    //N : nums.length ; M:queries.length
    public int[] answerQueries(int[] nums, int[] queries) {

        //Time:O(NlgN); Space: O(N)
        Arrays.sort(nums);

        int res[] = new int[queries.length];

        //Time:O(N)
        for (int i = 1; i < nums.length; i++)
            nums[i] += nums[i - 1];

        //Time:O(MlogN)
        for (int i = 0; i < queries.length; i++) {
            int j = Arrays.binarySearch(nums, queries[i]); //这个弯拐的相当优秀！！！
            res[i] = Math.abs(j + 1); //when not found , j = -x
        }
        return res;
    }


    //Runtime: 24 ms, faster than 40.00% of Java online submissions for Longest Subsequence With Limited Sum.
    //Memory Usage: 48.8 MB, less than 40.00% of Java online submissions for Longest Subsequence With Limited Sum.
    //recursion
    //Time:O(N * logN + M * logN); Space: O(logN)
    //N : nums.length; M: queries.length
    public int[] answerQueries_1(int[] nums, int[] queries) {

        //Time:O(NlogN)
        Arrays.sort(nums);

        int[] res = new int[queries.length];
        //Time: O(M* logN) ; Space: O(logN)
        for (int i = 0; i < queries.length; i++)
            res[i] = helper(nums,queries[i], 0, 0);
        return res;

    }

    private int helper(int[] nums, int target, int begin, int step) {
        if (begin >= nums.length || target < nums[begin]) return step;

        int remainer = target - nums[begin];
        if (remainer == 0) return step + 1;

        return helper(nums, remainer, begin + 1, step + 1);
    }
}
