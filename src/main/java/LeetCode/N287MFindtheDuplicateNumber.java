package LeetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 *
 * There is only one repeated number in nums, return this repeated number.
 *
 * You must solve the problem without modifying the array nums and uses only constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 * Example 2:
 *
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= n <= 105
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * All the integers in nums appear only once except for precisely one integer which appears two or more times.
 *
 *
 * Follow up:
 * How can we prove that at least one duplicate number must exist in nums?
 * Can you solve the problem in linear runtime complexity?
 *
 */
public class N287MFindtheDuplicateNumber {

    public static void main(String[] args) {

        int[] data;

        data = new int[]{1,3,4,2,2};
        doRun(2, data);
    }

    private static void doRun(int expected, int[] nums){
        int res = new N287MFindtheDuplicateNumber().findDuplicate(nums);
        //String strRes = Arrays.stream(res).mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(""));
        //System.out.println("=======");
        System.out.println("["+(expected == res)+"].[expected:"+ expected+"] res:"+res);
    }

    ////////////////////////////////////////////////////////////////////////////
    //Runtime: 8 ms, faster than 56.08% of Java online submissions for Find the Duplicate Number.
    //Memory Usage: 75.8 MB, less than 53.06% of Java online submissions for Find the Duplicate Number.
    //Negative Marking
    //Time : O(2N); Space: O(1)
    public int findDuplicate(int[] nums) {
        int idx = 0;
        for (int i = 0; i < nums.length; i++){
            idx = Math.abs(nums[i]);
            if (nums[idx] > 0) nums[idx] = -nums[idx];
            else break; //duplicate
        }

        // Restore numbers
        for (int i = 0; i<nums.length; i++)
            if (nums[i] < 0) nums[i] = -nums[i];

        return idx;
    }

    //////////////////////////////////////////////////////////////////////////////
    //Runtime: 38 ms, faster than 29.24% of Java online submissions for Find the Duplicate Number.
    //Memory Usage: 90 MB, less than 14.29% of Java online submissions for Find the Duplicate Number.
    //Time: O(N); Space: O(N)
    public int findDuplicate_1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n: nums)
            if (!set.add(n)) return n;
        return -1;
    }

}
