package LeetCode;


/**
 *
 * A wiggle sequence is a sequence where the differences between successive numbers strictly
 * alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with one element and a sequence with two non-equal elements are trivially wiggle sequences.
 *
 * For example, [1, 7, 4, 9, 2, 5] is a wiggle sequence because the differences (6, -3, 5, -7, 3)
 * alternate between positive and negative.
 * In contrast, [1, 4, 7, 2, 5] and [1, 7, 4, 5, 5] are not wiggle sequences.
 * The first is not because its first two differences are positive, and the second is not because its last difference is zero.
 * A subsequence is obtained by deleting some elements (possibly zero) from the original sequence,
 * leaving the remaining elements in their original order.
 *
 * Given an integer array nums, return the length of the longest wiggle subsequence of nums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,7,4,9,2,5]
 * Output: 6
 * Explanation: The entire sequence is a wiggle sequence with differences (6, -3, 5, -7, 3).
 *
 *
 *
 * Example 2:
 * Input: nums = [1,17,5,10,13,15,10,5,16,8]
 * Output: 7
 * Explanation: There are several subsequences that achieve this length.
 * One is [1, 17, 10, 13, 10, 16, 8] with differences (16, -7, 3, -3, 6, -8).
 *
 *
 * Example 3:
 * Input: nums = [1,2,3,4,5,6,7,8,9]
 * Output: 2
 *
 *
 * Constraints:
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 *
 *
 * Follow up: Could you solve this in O(n) time?
 */

public class N376MWiggleSubsequence {

    public static void main(String[] args) {


        int[] num = {};
        System.out.println("expect 0, result:" + new N376MWiggleSubsequence().wiggleMaxLength(num));

        int[] nums = {1};
        System.out.println("expect 1, result:" + new N376MWiggleSubsequence().wiggleMaxLength(nums));

        System.out.println("=====");

        int[] nums1 = {1,7,4,9,2,5};
        System.out.println("expect 6, result:" + new N376MWiggleSubsequence().wiggleMaxLength(nums1) );

        int[] nums2 = {1,17,5,10,13,15,10,5,16,8};
        System.out.println("expect 7, result:"+ new N376MWiggleSubsequence().wiggleMaxLength(nums2));

        int[] nums3 = {1,2,3,4,5,6,7,8,9};
        System.out.println("expect 2, result:"+ new N376MWiggleSubsequence().wiggleMaxLength(nums3));

        int[] nums4 = {2,2};
        System.out.println("expect 1, result:"+ new N376MWiggleSubsequence().wiggleMaxLength(nums4));

        int[] nums5 ={33,53,12,64,50,41,45,21,97,35,47,92,39,0,93,55,40,46,69,42,6,95,51,68,72,9,32,84,34,64,6,2,26,98,3,43,30,60,3,68,82,9,97,19,27,98,99,4,30,96,37,9,78,43,64,4,65,30,84,90,87,64,18,50,60,1,40,32,48,50,76,100,57,29,63,53,46,57,93,98,42,80,82,9,41,55,69,84,82,79,30,79,18,97,67,23,52,38,74,15};
        System.out.println("expect 67, result:"+ new N376MWiggleSubsequence().wiggleMaxLength(nums5));

        int[] nums6 ={87,64,18,50,60,1,40,32,48,50,76,57,63,53};
        System.out.println("expect 10, result:"+ new N376MWiggleSubsequence().wiggleMaxLength(nums6));

        int[] nums7 ={3,3,3,2,5};
        System.out.println("expect 3, result:"+ new N376MWiggleSubsequence().wiggleMaxLength(nums7));
    }

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Wiggle Subsequence.
    //Memory Usage: 39.7 MB, less than 93.51% of Java online submissions for Wiggle Subsequence.
    public int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1) return nums.length;

        int lastDiff = nums[1] - nums[0];
        int diff = 0;
        int c = 1;
        for (int i = 1; i< nums.length; i++){
            diff = nums[i-1] - nums[i];
            if ((lastDiff >= 0 && diff < 0) || (lastDiff <= 0 && diff > 0)){
                lastDiff = diff;
                c++;
            }
        }
        return c;
    }

}







