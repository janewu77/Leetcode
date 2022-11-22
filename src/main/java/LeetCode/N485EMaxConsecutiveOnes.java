package LeetCode;


/**
 * Given a binary array nums, return the maximum number of consecutive 1's in the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,0,1,1,1]
 * Output: 3
 * Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
 * Example 2:
 *
 * Input: nums = [1,0,1,1,0,1]
 * Output: 2
 */

/**
 *  E - [time: 5-]
 *
 */
public class N485EMaxConsecutiveOnes {


    public static void main(String[] args) {
        int[] data  = new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        int res = new N485EMaxConsecutiveOnes().findMaxConsecutiveOnes( data);
        System.out.println(res);
    }

    //Runtime: 3 ms, faster than 58.40% of Java online submissions for Max Consecutive Ones.
    //Memory Usage: 56.5 MB, less than 70.30% of Java online submissions for Max Consecutive Ones.
    //Time: O(N); Space: O(1)
    public int findMaxConsecutiveOnes(int[] nums) {
        int res = 0;
        int count = 0;
        for (int n : nums){
            if (n == 1) {
                res = Math.max(res, ++count);
            }else {
                count = 0;
            }
        }
        return res;
    }
}
