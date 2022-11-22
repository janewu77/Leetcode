package LeetCode;

/**
 * Given a binary array nums and an integer k, return the maximum number of
 * consecutive 1's in the array if you can flip at most k 0's.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 * Output: 6
 * Explanation: [1,1,1,0,0,1,1,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 * Example 2:
 *
 * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
 * Output: 10
 * Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * nums[i] is either 0 or 1.
 * 0 <= k <= nums.length
 */

/**
 * M - [time: 10- 与424题的简版】
 *  - 计数window
 */
public class N1004MMaxConsecutiveOnesIII {


    public static void main(String[] args) {
        String str;
        int k;

        int[] data  = new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        doRun(10, data, 3);


    }

    static private void doRun(int expect, int[] nums, int k) {
        int res = new N1004MMaxConsecutiveOnesIII().longestOnes(nums, k);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //Runtime: 2 ms, faster than 100.00% of Java online submissions for Max Consecutive Ones III.
    //Memory Usage: 43.8 MB, less than 88.92% of Java online submissions for Max Consecutive Ones III.
    // 计数window . 用K的增减来控制window
    //Time： O(N); Space: O(1)
    public int longestOnes(int[] nums, int k) {
        int left = 0, right;
        for (right = 0; right < nums.length; right++) {
            // If we included a zero in the window we reduce the value of k.
            // Since k is the maximum zeros allowed in a window.
            if (nums[right] == 0) {
                k--;
            }
            // A negative k denotes we have consumed all allowed flips and window has
            // more than allowed zeros, thus increment left pointer by 1 to keep the window size same.
            if (k < 0) {
                // If the left element to be thrown out is zero we increase k.
                k += 1 - nums[left];
                left++;
            }
        }
        return right - left;
    }

    //Runtime: 3 ms, faster than 92.57% of Java online submissions for Max Consecutive Ones III.
    //Memory Usage: 43.9 MB, less than 87.40% of Java online submissions for Max Consecutive Ones III.
    // same as N424
    // 计数window
    //Time： O(N); Space: O(1)
    public int longestOnes_1(int[] nums, int k) {
        int res = 0;
        int count1 = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++){
            if (nums[right] == 1) count1++;

            if (right - left + 1 <= count1 + k){
                res = right - left + 1;
            }else{
                if (nums[left++] == 1) count1--;
            }
        }
        return res;
    }
}
