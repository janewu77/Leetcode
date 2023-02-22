package LeetCode;

import static java.time.LocalTime.now;

/**
 * You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.
 *
 * Return the single element that appears only once.
 *
 * Your solution must run in O(log n) time and O(1) space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,2,3,3,4,4,8,8]
 * Output: 2
 * Example 2:
 *
 * Input: nums = [3,3,7,7,10,11,11]
 * Output: 10
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 105
 */
public class N540MSingleElementinaSortedArray {

    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");
        doRun(3,  new int[]{3});
        doRun(10,  new int[]{3,3,7,7,10,11,11});
        doRun(2,  new int[]{1,1,2,3,3,4,4,8,8});
        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, int[] nums) {
        int res = new N540MSingleElementinaSortedArray().singleNonDuplicate(nums);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //1.Binary Search
    //Runtime: 0ms 100%; Memory: 48.3MB 39%
    //Time: O(logN); Space: O(1)
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int countLeft = (right - left) / 2;
            int countRight = (right - left) / 2;

            if (mid > 0 && nums[mid - 1] == nums[mid])
                countLeft--;

            if (mid + 1 < nums.length && nums[mid + 1] == nums[mid])
                countRight--;

            if (countLeft == countRight)
                return nums[mid];

            if (countLeft % 2 == 0)
                left = nums[mid + 1] == nums[mid] ? mid + 2 : mid + 1;

            if (countRight % 2 == 0)
                right = nums[mid - 1] == nums[mid] ? mid - 2 : mid - 1;
        }
        return left;
    }


}
