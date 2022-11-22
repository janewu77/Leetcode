package LeetCode;

import static java.time.LocalTime.now;

/**
 * The score of an array is defined as the product of its sum and its length.
 *
 * For example, the score of [1, 2, 3, 4, 5] is (1 + 2 + 3 + 4 + 5) * 5 = 75.
 * Given a positive integer array nums and an integer k, return the number of non-empty subarrays of nums
 * whose score is strictly less than k.
 *
 * A subarray is a contiguous sequence of elements within an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,1,4,3,5], k = 10
 * Output: 6
 * Explanation:
 * The 6 subarrays having scores less than 10 are:
 * - [2] with score 2 * 1 = 2.
 * - [1] with score 1 * 1 = 1.
 * - [4] with score 4 * 1 = 4.
 * - [3] with score 3 * 1 = 3.
 * - [5] with score 5 * 1 = 5.
 * - [2,1] with score (2 + 1) * 2 = 6.
 * Note that subarrays such as [1,4] and [4,3,5] are not considered because their scores
 * are 10 and 36 respectively, while we need scores strictly less than 10.
 * Example 2:
 *
 * Input: nums = [1,1,1], k = 5
 * Output: 5
 * Explanation:
 * Every subarray except [1,1,1] has a score less than 5.
 * [1,1,1] has a score (1 + 1 + 1) * 3 = 9, which is greater than 5.
 * Thus, there are 5 subarrays having scores less than 5.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * 1 <= k <= 1015
 */
public class N2302HCountSubarraysWithScoreLessThanK {

    public static void main(String[] args) {

        System.out.println(now());
        String[] data;

        doRun(6, new int[]{2,1,4,3,5}, 10);
        doRun(5, new int[]{1,1,1}, 5);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(long expect, int[] nums, long k) {
        long res = new N2302HCountSubarraysWithScoreLessThanK().countSubarrays(nums, k);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 3 ms, faster than 100.00% of Java online submissions for Count Subarrays With Score Less Than K.
    //Memory Usage: 52.6 MB, less than 90.36% of Java online submissions for Count Subarrays With Score Less Than K.
    //slide window
    //Time:O(N); Space: O(1)
    public long countSubarrays(int[] nums, long k) {
        int left = 0, right = 0;
        long res = 0, sum = 0;

        while (right < nums.length){
            sum += nums[right++];

            while (sum * (right - left) >= k)
                sum -= nums[left++];

            res += right - left;
        }
        return res;
    }

}
