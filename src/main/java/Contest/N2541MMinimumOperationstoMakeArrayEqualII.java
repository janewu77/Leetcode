package Contest;


import java.util.Arrays;

import static java.time.LocalTime.now;

/**
 * You are given two integer arrays nums1 and nums2 of equal length n and an integer k. You can perform the following operation on nums1:
 *
 * Choose two indexes i and j and increment nums1[i] by k and decrement nums1[j] by k. In other words, nums1[i] = nums1[i] + k and nums1[j] = nums1[j] - k.
 * nums1 is said to be equal to nums2 if for all indices i such that 0 <= i < n, nums1[i] == nums2[i].
 *
 * Return the minimum number of operations required to make nums1 equal to nums2. If it is impossible to make them equal, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [4,3,1,4], nums2 = [1,3,7,1], k = 3
 * Output: 2
 * Explanation: In 2 operations, we can transform nums1 to nums2.
 * 1st operation: i = 2, j = 0. After applying the operation, nums1 = [1,3,4,4].
 * 2nd operation: i = 2, j = 3. After applying the operation, nums1 = [1,3,7,1].
 * One can prove that it is impossible to make arrays equal in fewer operations.
 * Example 2:
 *
 * Input: nums1 = [3,8,5,2], nums2 = [2,4,1,6], k = 1
 * Output: -1
 * Explanation: It can be proved that it is impossible to make the two arrays equal.
 *
 *
 * Constraints:
 *
 * n == nums1.length == nums2.length
 * 2 <= n <= 105
 * 0 <= nums1[i], nums2[j] <= 109
 * 0 <= k <= 105
 */
public class N2541MMinimumOperationstoMakeArrayEqualII {


    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");

        doRun(-1, new int[]{4,4,0}, new int[]{2,2,4}, 4);


        doRun(2, new int[]{4,3,1,4}, new int[]{1,3,7,1}, 3);
        doRun(-1, new int[]{3,8,5,2}, new int[]{2,4,1,6}, 1);
        doRun(-1, new int[]{4,3,1,4}, new int[]{1,3,7,1}, 0);
        doRun(0, new int[]{4,3,1,4}, new int[]{4,3,1,4}, 0);
        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(long expect, int[] nums1, int[] nums2, int k) {
        long res = new N2541MMinimumOperationstoMakeArrayEqualII().minOperations(nums1, nums2, k);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //1.one pass
    //Runtime: 3ms 100%; Memory: 60.3MB 88%
    //Time: O(N); Space: O(1)
    public long minOperations(int[] nums1, int[] nums2, int k) {
        if (k == 0) return Arrays.equals(nums1, nums2) ? 0 : -1;
        long res = 0, total = 0;
        for (int i = 0; i < nums1.length; i++) {
            int val = nums1[i] - nums2[i];
            if (val == 0) continue;
            if (val % k != 0) return -1;
            total += val;
            if (val > 0) res += val / k;
        }
        return total == 0 ? res : -1;
    }
}
