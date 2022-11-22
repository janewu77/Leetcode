package LeetCode;

/**
 * Given a binary array nums, return the maximum number of consecutive 1's
 * in the array if you can flip at most one 0.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,0,1,1,0]
 * Output: 4
 * Explanation: Flip the first zero will get the maximum number of consecutive 1s.
 * After flipping, the maximum number of consecutive 1s is 4.
 * Example 2:
 *
 * Input: nums = [1,0,1,1,0,1]
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * nums[i] is either 0 or 1.
 */

/**
 * M - [time: 10- ] N1004MMaxConsecutiveOnesIII
 *
 *
 */
public class N487MMaxConsecutiveOnesII {

    public static void main(String[] args) {
        int[] data  = new int[]{0,0,1,1,0,0,0,1};
        int res = new N487MMaxConsecutiveOnesII().findMaxConsecutiveOnes(data);
        System.out.println(res);
    }

    //1.Slide window
    //Runtime: 2 ms, faster than 99.24% of Java online submissions for Max Consecutive Ones II.
    //Memory Usage: 43.1 MB, less than 92.80% of Java online submissions for Max Consecutive Ones II.
    //Time: O(N); Space:O(1)
    public int findMaxConsecutiveOnes(int[] nums) {
        int k = 1;
        int left = 0, right = 0;

        while (right < nums.length) {
            if (nums[right++] == 0) k--;
            //keep the size of the window
            if (k < 0) k += 1 - nums[left++];
        }

        //max window
        return right - left;
    }
}
