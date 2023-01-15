package Contest;


import java.util.HashMap;
import java.util.Map;

import static java.time.LocalTime.now;

/**
 * Given an integer array nums and an integer k, return the number of good subarrays of nums.
 *
 * A subarray arr is good if it there are at least k pairs of indices (i, j) such that i < j and arr[i] == arr[j].
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,1,1], k = 10
 * Output: 1
 * Explanation: The only good subarray is the array nums itself.
 * Example 2:
 *
 * Input: nums = [3,1,4,3,2,2,4], k = 2
 * Output: 4
 * Explanation: There are 4 different good subarrays:
 * - [3,1,4,3,2,2] that has 2 pairs.
 * - [3,1,4,3,2,2,4] that has 3 pairs.
 * - [1,4,3,2,2,4] that has 2 pairs.
 * - [4,3,2,2,4] that has 2 pairs.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i], k <= 109
 */
public class N2537MCounttheNumberofGoodSubarrays {

    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");
        doRun(4, new int[]{3,1,4,3,2,2,4}, 2);
        doRun(1, new int[]{1,1,1,1,1}, 10);
        doRun(21, new int[]{2,1,3,1,2,2,3,3,2,2,1,1,1,3,1}, 11);
        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(long expect, int[] nums, int k) {
        long res = new N2537MCounttheNumberofGoodSubarrays().countGood(nums, k);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //1.sliding window
    //Runtime: 54ms 100%; Memory: 55.1MB 100%
    //Time: O(N); Space: O(N)
    public long countGood(int[] nums, int k) {
        long res = 0;
        int left = 0, right = 1, pairCount = 0;

        Map<Integer, Integer> counter = new HashMap<>();
        counter.put(nums[0], 1);

        while (right < nums.length) {
            int val = nums[right];
            int count = counter.getOrDefault(val, 0);
            counter.put(val, count + 1);
            pairCount += count;

            while (left < right && pairCount >= k) {
                res += nums.length - right;

                val = nums[left++];
                count = counter.get(val);
                counter.put(val, --count);
                pairCount -= count;
            }
            right++;
        }
        return res;
    }
}
