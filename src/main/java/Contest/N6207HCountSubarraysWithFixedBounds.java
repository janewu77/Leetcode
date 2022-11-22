package Contest;

import static java.time.LocalTime.now;

/**
 * You are given an integer array nums and two integers minK and maxK.
 *
 * A fixed-bound subarray of nums is a subarray that satisfies the following conditions:
 *
 * The minimum value in the subarray is equal to minK.
 * The maximum value in the subarray is equal to maxK.
 * Return the number of fixed-bound subarrays.
 *
 * A subarray is a contiguous part of an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,5,2,7,5], minK = 1, maxK = 5
 * Output: 2
 * Explanation: The fixed-bound subarrays are [1,3,5] and [1,3,5,2].
 * Example 2:
 *
 * Input: nums = [1,1,1,1], minK = 1, maxK = 1
 * Output: 10
 * Explanation: Every subarray of nums is a fixed-bound subarray. There are 10 possible subarrays.
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 105
 * 1 <= nums[i], minK, maxK <= 106
 */

/**
 * H - [120+
 */
//2444. Count Subarrays With Fixed Bounds
public class N6207HCountSubarraysWithFixedBounds {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;
        doRun(10, new int[]{1,1,1,1}, 1,1);

        doRun(2, new int[]{1,3,5,2,7,5}, 1,5);
        doRun(22, new int[]{2,2,1,3,1,5,3,1,2}, 1,5);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(long expect, int[] nums, int minK, int maxK) {
        long res = new N6207HCountSubarraysWithFixedBounds().countSubarrays(nums,minK,maxK);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Slide window
    //Runtime: 7 ms, faster than 50.00% of Java online submissions for Count Subarrays With Fixed Bounds.
    //Memory Usage: 56.5 MB, less than 75.00% of Java online submissions for Count Subarrays With Fixed Bounds.
    //Time: O(N); Space :O(1)
    public long countSubarrays(int[] nums, int minK, int maxK) {
        int left = -1, right = 0;
        int minIdx = -1, maxIdx = -1;
        long res = 0;
        for (; right < nums.length; right++) {
            if (nums[right] < minK || nums[right] > maxK)
                left = right;

            if (nums[right] == minK) minIdx = right;
            if (nums[right] == maxK) maxIdx = right;

            res += Math.max(0L, Math.min(minIdx, maxIdx) - left);
        }
        return res;
    }

}
