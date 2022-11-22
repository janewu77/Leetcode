package Contest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.time.LocalTime.now;

/**
 * You are given an integer array nums and an integer k. Find the maximum subarray sum of all the subarrays of nums that meet the following conditions:
 *
 * The length of the subarray is k, and
 * All the elements of the subarray are distinct.
 * Return the maximum subarray sum of all the subarrays that meet the conditions. If no subarray meets the conditions, return 0.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,5,4,2,9,9,9], k = 3
 * Output: 15
 * Explanation: The subarrays of nums with length 3 are:
 * - [1,5,4] which meets the requirements and has a sum of 10.
 * - [5,4,2] which meets the requirements and has a sum of 11.
 * - [4,2,9] which meets the requirements and has a sum of 15.
 * - [2,9,9] which does not meet the requirements because the element 9 is repeated.
 * - [9,9,9] which does not meet the requirements because the element 9 is repeated.
 * We return 15 because it is the maximum subarray sum of all the subarrays that meet the conditions
 * Example 2:
 *
 * Input: nums = [4,4,4], k = 3
 * Output: 0
 * Explanation: The subarrays of nums with length 3 are:
 * - [4,4,4] which does not meet the requirements because the element 4 is repeated.
 * We return 0 because no subarrays meet the conditions.
 *
 *
 * Constraints:
 *
 * 1 <= k <= nums.length <= 105
 * 1 <= nums[i] <= 105
 */

/**
 * M - [time: 25-d
 */
//2461. Maximum Sum of Distinct Subarrays With Length K
public class N6230MMaximumSumofDistinctSubarraysWithLengthK {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun(3l, new int[]{1,2,2}, 2);
        doRun(24l, new int[]{1,1,1,7,8,9}, 3);
        doRun(5l, new int[]{3, 5}, 1);
        doRun(5l, new int[]{5, 3}, 1);
        doRun(15l, new int[]{1,5,4,2,9,9,9}, 3);
        System.out.println(now());
        System.out.println("==================");

    }


    static private void doRun(long expect, int[] nums, int k) {
        long res = new N6230MMaximumSumofDistinctSubarraysWithLengthK().maximumSubarraySum(nums, k);

//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //2.prefix sum
    //Runtime: 80 ms, faster than 80.00% of Java online submissions for Maximum Sum of Distinct Subarrays With Length K.
    //Memory Usage: 105.6 MB, less than 20.00% of Java online submissions for Maximum Sum of Distinct Subarrays With Length K.
    //Time: O(N); Space: O(N)
    public long maximumSubarraySum(int[] nums, int k) {
        long res = 0;
        long[] preSum = new long[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) preSum[i] = preSum[i - 1] + nums[i - 1];

        Map<Integer, Integer> map = new HashMap<>();
        int left = 0;
        for (int idx = 0; idx < nums.length; idx++) {
            left = Math.max(left, map.getOrDefault(nums[idx], -2) + 1);
            if (idx - left >= k - 1)
                res = Math.max(res, preSum[idx + 1] - preSum[idx + 1 - k]);
            map.put(nums[idx], idx);
        }
        return res;
    }

    //1.Slide window
    //Runtime: 105 ms, faster than 80.00% of Java online submissions for Maximum Sum of Distinct Subarrays With Length K.
    //Memory Usage: 105.9 MB, less than 20.00% of Java online submissions for Maximum Sum of Distinct Subarrays With Length K.
    //Time: O(N); Space: O(1)
    public long maximumSubarraySum_1(int[] nums, int k) {
        long res = 0, sum = 0;
        int left = 0, right = 0;

        Set<Integer> set = new HashSet<>();
        while (right < nums.length) {
            while (left < right && (set.contains(nums[right]) || set.size() >= k)) {
                set.remove(nums[left]);
                sum -= nums[left++];
            }
            set.add(nums[right]);
            sum += nums[right++];
            if (set.size() == k) res = Math.max(res, sum);
        }
        return res;
    }

}


