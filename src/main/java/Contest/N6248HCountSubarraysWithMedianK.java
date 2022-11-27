package Contest;

import java.io.IOException;
import java.util.*;

import static java.time.LocalTime.now;

/**
 * You are given an array nums of size n consisting of distinct integers from 1 to n and a positive integer k.
 *
 * Return the number of non-empty subarrays in nums that have a median equal to k.
 *
 * Note:
 *
 * The median of an array is the middle element after sorting the array in ascending order. If the array is of even length, the median is the left middle element.
 * For example, the median of [2,3,1,4] is 2, and the median of [8,4,3,5,1] is 4.
 * A subarray is a contiguous part of an array.
 *
 *
 * Example 1:
 *
 * Input: nums = [3,2,1,4,5], k = 4
 * Output: 3
 * Explanation: The subarrays that have a median equal to 4 are: [4], [4,5] and [1,4,5].
 * Example 2:
 *
 * Input: nums = [2,3,1], k = 3
 * Output: 1
 * Explanation: [3] is the only subarray that has a median equal to 3.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 105
 * 1 <= nums[i], k <= n
 * The integers in nums are distinct.
 */

//2488. Count Subarrays With Median K
public class N6248HCountSubarraysWithMedianK {


     static public void main(String... args) throws IOException{
         System.out.println(now());
         System.out.println("==================");

         doRun(1, new int[]{1}, 1);
         doRun(1, new int[]{2,3,1}, 3);
         doRun(3, new int[]{3,2,1,4,5}, 4);
         doRun(6, new int[]{3,1,5,4,7}, 4);
         doRun(3, new int[]{3,1,5,4}, 4);
         doRun(0, new int[]{3,1,5,4}, 2);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[] nums, int k) {
        int res = new N6248HCountSubarraysWithMedianK().countSubarrays(nums, k);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //2.two passes
    //Runtime: 18 ms, faster than 80.00% of Java online submissions for Count Subarrays With Median K.
    //Memory Usage: 56 MB, less than 60.00% of Java online submissions for Count Subarrays With Median K.
    //Time: O(N + N); Space:O(N)
    //Time: O(N); Space:O(N)
    public int countSubarrays(int[] nums, int k) {
        int res = 0, idx = -1;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0, sum = 0; i < nums.length; i++) {
            if (nums[i] == k) idx = i;
            nums[i] = nums[i] == k ? 0 : nums[i] > k ?  1 : -1;

            //count in right part
            if (idx >= 0) {
                sum += nums[i];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        for (int i = idx, sum = 0; i >= 0; i--) {
            sum += nums[i];
            res += map.getOrDefault(-sum, 0) + map.getOrDefault(-sum + 1, 0);
        }
        return res;
    }

    //1.brute force
    //TLE
    //Time: O(N + N * N); Space: O(1)
    public int countSubarrays_1(int[] nums, int k) {
        int idx = -1, idxInOrder = 0, less = 0;
        int leftLen = 0, rightLen;
        int res = 0;

        for (int i = 0; i < nums.length; i++) {
            if (idx < 0) {
                leftLen++;
                if (nums[i] < k) idxInOrder++;
            }
            if (nums[i] == k) idx = i;
            if (idx > 0 && nums[i] < k) less++;
        }
        if (idx < 0) return res;

        rightLen = nums.length - leftLen;

        //Time: O(N * N)
        for (int i = 0; i <= idx; i++) {
            for (int j = idx; j < nums.length; j++) {
                if (j > idx && nums[j] < k) idxInOrder++;

                int mid = (leftLen % 2 == 0) ? (leftLen / 2 - 1) : (leftLen / 2);
                if (idxInOrder == mid) res++;

                leftLen++;
            }
            idxInOrder -= less;
            if (nums[i] < k) idxInOrder--;
            leftLen = leftLen - rightLen - 2;
        }
        return res;
    }


}


