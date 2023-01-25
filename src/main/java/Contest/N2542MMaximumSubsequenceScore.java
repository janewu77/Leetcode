package Contest;

import java.util.Arrays;
import java.util.PriorityQueue;

import static java.time.LocalTime.now;

/**
 * You are given two 0-indexed integer arrays nums1 and nums2 of equal length n and a positive integer k. You must choose a subsequence of indices from nums1 of length k.
 *
 * For chosen indices i0, i1, ..., ik - 1, your score is defined as:
 *
 * The sum of the selected elements from nums1 multiplied with the minimum of the selected elements from nums2.
 * It can defined simply as: (nums1[i0] + nums1[i1] +...+ nums1[ik - 1]) * min(nums2[i0] , nums2[i1], ... ,nums2[ik - 1]).
 * Return the maximum possible score.
 *
 * A subsequence of indices of an array is a set that can be derived from the set {0, 1, ..., n-1} by deleting some or no elements.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,3,3,2], nums2 = [2,1,3,4], k = 3
 * Output: 12
 * Explanation:
 * The four possible subsequence scores are:
 * - We choose the indices 0, 1, and 2 with score = (1+3+3) * min(2,1,3) = 7.
 * - We choose the indices 0, 1, and 3 with score = (1+3+2) * min(2,1,4) = 6.
 * - We choose the indices 0, 2, and 3 with score = (1+3+2) * min(2,3,4) = 12.
 * - We choose the indices 1, 2, and 3 with score = (3+3+2) * min(1,3,4) = 8.
 * Therefore, we return the max score, which is 12.
 * Example 2:
 *
 * Input: nums1 = [4,2,3,1,1], nums2 = [7,5,10,9,6], k = 1
 * Output: 30
 * Explanation:
 * Choosing index 2 is optimal: nums1[2] * nums2[2] = 3 * 10 = 30 is the maximum possible score.
 *
 *
 * Constraints:
 *
 * n == nums1.length == nums2.length
 * 1 <= n <= 105
 * 0 <= nums1[i], nums2[j] <= 105
 * 1 <= k <= n
 */
//1383. Maximum Performance of a Team
public class N2542MMaximumSubsequenceScore {

    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");

        doRun(168, new int[]{2,1,14,12}, new int[]{11,7,13,6}, 3);

        doRun(12, new int[]{1,3,3,2}, new int[]{2,1,3,4}, 3);
        doRun(30, new int[]{4,2,3,1,1}, new int[]{7,5,10,9,6}, 1);

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(long expect, int[] nums1, int[] nums2, int k) {
        long res = new N2542MMaximumSubsequenceScore().maxScore(nums1, nums2, k);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //2.PriorityQueue + sort
    //Runtime: 131ms 58%; Space: 59.4MB 84%
    //Time: O(N * logN + N * logK); Space: O(N + logN + K)
    //Time: O(N * logN); Space: O(N)
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int[][] data = new int[nums1.length][2];
        for (int i = 0; i < nums1.length; i++)
            data[i] = new int[]{nums1[i], nums2[i]};
        Arrays.sort(data, (a, b) -> b[1] - a[1]);

        long sum = 0, res = Integer.MIN_VALUE;
        PriorityQueue<Integer> sumQueue = new PriorityQueue<>(k);
        for (int i = 0; i < data.length; i++) {
            int[] node = data[i];
            sumQueue.add(node[0]);
            sum += node[0];
            if (sumQueue.size() == k){
                res = Math.max(res, sum * node[1]);
                sum -= sumQueue.poll();
            }
        }
        return res;
    }


    //1.recursion
    //TLE
    //Time: O(N * (N - 1) * (N - 2) *...* (N - (K - 1)); Space: O(K)
    public long maxScore_1(int[] nums1, int[] nums2, int k) {
        maxScore(nums1, nums2, k, 0, 0, Integer.MAX_VALUE);
        return res;
    }

    long res = 0;
    private void maxScore(int[] nums1, int[] nums2, int k, int idx, long sum, int min) {
        if (k == 0) {
            res = Math.max(res, sum * min);
            return;
        }
        if (idx >= nums1.length) return;
        maxScore(nums1, nums2, k - 1, idx + 1, sum + nums1[idx], Math.min(min, nums2[idx]));
        maxScore(nums1, nums2, k, idx + 1, sum, min);
    }


    //X.bitmask (if n < 32)
    public long maxScore_X(int[] nums1, int[] nums2, int k) {
        long res = 0;
        for(int i = 1; i < (1<< nums1.length); i++){
            if (Integer.bitCount(i) == k) {
                int mask = i, idx = 0, min = Integer.MAX_VALUE;
                long sum = 0;
                while (mask > 0){
                    if ((mask & 1) == 1){
                        sum += nums1[idx];
                        min = Math.min(min, nums2[idx]);
                    }
                    mask >>= 1;
                    idx++;
                }
                res = Math.max(res, sum * min);
            }
        }
        return res;
    }

}
