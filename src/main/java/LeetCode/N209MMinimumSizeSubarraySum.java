package LeetCode;


/**
 * Given an array of positive integers nums and a positive integer target,
 * return the minimal length of a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr]
 * of which the sum is greater than or equal to target. If there is no such subarray, return 0 instead.
 *
 *
 *
 * Example 1:
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 *
 *
 * Example 2:
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 *
 *
 * Example 3:
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 *
 *
 * Follow up: If you have figured out the O(n) solution,
 * try coding another solution of which the time complexity is O(n log(n)).
 *
 *
 */

/**
 * M - [time: 20-
 *  - 连做了几个slide window，所以很快就做完了。
 *  - slide window
 *
 */
public class N209MMinimumSizeSubarraySum {
    public static void main(String[] args) {
        int[] data;

        data  = new int[]{2,3,1,2,4,3};
        doRun(2, data, 7);

        data  = new int[]{1,4,4};
        doRun(1, data, 4);

        data  = new int[]{1,1,1,1,1,1,1,1};
        doRun(0, data, 11);
    }

    static private void doRun(int expected, int[] nums, int k) {
        int res = new N209MMinimumSizeSubarraySum().minSubArrayLen(k, nums);
        System.out.println("["+(expected == res)+"]expect:" + expected + " res:" + res);
    }


    //Runtime: 2 ms, faster than 75.79% of Java online submissions for Minimum Size Subarray Sum.
    //Memory Usage: 58.3 MB, less than 23.52% of Java online submissions for Minimum Size Subarray Sum.
    //two pointers slide-window
    //Time: O(N); Space: O(1)
    public int minSubArrayLen(int target, int[] nums) {
        int sum = 0;
        int minLen = Integer.MAX_VALUE;
        int left = 0, right = 0;

        while (right < nums.length) {
            sum += nums[right++];

            //shrink window
            while (sum >= target) {
                minLen = Math.min(minLen, right - left);
                sum -= nums[left++];
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

}
