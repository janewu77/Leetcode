package LeetCode;

import LeetCode.Database.N153MFindMinimuminRotatedSortedArray;

/**
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,4,4,5,6,7] might become:
 *
 * [4,5,6,7,0,1,4] if it was rotated 4 times.
 * [0,1,4,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 *
 * Given the sorted rotated array nums that may contain duplicates, return the minimum element of this array.
 *
 * You must decrease the overall operation steps as much as possible.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,5]
 * Output: 1
 * Example 2:
 *
 * Input: nums = [2,2,2,0,1]
 * Output: 0
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * nums is sorted and rotated between 1 and n times.
 *
 *
 * Follow up: This problem is similar to Find Minimum in Rotated Sorted Array, but nums may contain duplicates. Would this affect the runtime complexity? How and why?
 *
 */

/**
 * H - [Time :20-]
 *  - 和153可用同样的解法。
 *  - 注意，有些排序是"不稳定"的，当出现duplicated value时，会出错。
 *
 */
public class N154HFindMinimuminRotatedSortedArraII {

    public static void main(String[] args){
        doRun(1, new int[]{3,4,5,1,2});
        doRun(0, new int[]{4,5,6,7,0,1,2});
        doRun(11, new int[]{11,13,15,17});
        doRun(17, new int[]{17});

        doRun(0, new int[]{4,5,6,7,0,1,4});
        doRun(0, new int[]{2,2,2,0,1});

        doRun(10, new int[]{10,10,10,10,10});

        doRun(1, new int[]{10,1,10,10,10});

    }

    private static int c = 0;
    private static void doRun(int expected, int[] nums){
        int res = new N154HFindMinimuminRotatedSortedArraII().findMin(nums);
        System.out.println("[" + (expected ==res) +"] "+(c++)+ ".result: "+ res + ".expected:"+expected);
    }

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Find Minimum in Rotated Sorted Array II.
    //Memory Usage: 42.1 MB, less than 90.45% of Java online submissions for Find Minimum in Rotated Sorted Array II.
    public int findMin(int[] nums) {
        if (nums.length == 1) return nums[0];
        if(isSorted(nums, 0, nums.length-1)) return nums[0];
        return target;
    }
    private int target = -1;
    private boolean isSorted(int[] nums, int i, int j){
        if (i == j) return true;
        if (i + 1 == j) {
            if (nums[i] > nums[j]) {
                target = nums[j];
                return false;
            }
            return true;
        }

        int n = (j + i) / 2; //(j - (i+1)+1) / 2 + i;
        if (isSorted(nums, i , n) && isSorted(nums, n + 1 , j)) {
            if(nums[i] > nums[n+1]) {
                target = nums[n + 1];
                return false;
            }
            return true;
        }
        return false;
    }
}
