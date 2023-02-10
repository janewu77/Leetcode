package LeetCode;

import java.util.Arrays;

import static java.time.LocalTime.now;

/**
 * You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
 *
 * Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i], you can jump to any nums[i + j] where:
 *
 * 0 <= j <= nums[i] and
 * i + j < n
 * Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 1000
 */
public class N45MJumpGameII {

    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");
        //
        doRun(1, new int[]{3,2,1});

        doRun(3, new int[]{5,9,3,2,1,0,2,3,3,1,0,0});
        doRun(2, new int[]{5,9,3,2,1,0,2,3,3,1,0});

        doRun(1, new int[]{1,2});
        doRun(2, new int[]{2,3,0,1,4});
        doRun(2, new int[]{7,0,9,6,9,6,1,7,9,0,1,2,9,0,3});
        doRun(2, new int[]{2,3,1,1,4});

        doRun(0, new int[]{0});
        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, int[] nums) {
        int res = new N45MJumpGameII().jump(nums);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //3.greedy
    //Runtime: 1ms 99%; Memory: 42.9MB 47%
    //Time: O(N); Space:O(1)
    public int jump(int[] nums) {
        int end = 0, nextEnd = 0, step = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            nextEnd = Math.max(nextEnd, i + nums[i]);
            if (i == end) {
                step++;
                end = nextEnd;
            }
        }
        return step;
    }


    //2.greedy
    //Runtime: 1ms 99%; Memory: 42.9MB 47%
    //Time: O(N); Space:O(1)
    public int jump_2(int[] nums) {
        int begin = 0, end = 0, nextIdx = 0, step = 0;

        while (end < nums.length) {
            for (int j = begin; j <= end; j++) {
                nextIdx = Math.max(nextIdx, j + nums[j]);
                if (j >= nums.length - 1)
                    return step;
            }
            step++;
            begin = end + 1;
            end = nextIdx;
        }
        return step;
    }



    //1.iteration
    //Runtime: 49ms 33%; Memory: 42.3MB 93%
    //Time: O(N*N); Space: O(N)
    public int jump_1(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length && dp[i] + 1 < res; i++) {
            for (int j = 1; j <= nums[i] && (i + j < nums.length); j++) {
                dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
                if (i + j == nums.length - 1)
                    res = dp[nums.length - 1];
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }


}
