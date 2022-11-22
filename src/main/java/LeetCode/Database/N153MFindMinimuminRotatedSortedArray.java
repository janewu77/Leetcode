package LeetCode.Database;

import LeetCode.N576MOutofBoundaryPaths;

/**
 *
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:
 *
 * [4,5,6,7,0,1,2] if it was rotated 4 times.
 * [0,1,2,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 *
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
 * You must write an algorithm that runs in O(log n) time.
 *
 * Example 1:
 * Input: nums = [3,4,5,1,2]
 * Output: 1
 * Explanation: The original array was [1,2,3,4,5] rotated 3 times.
 *
 *
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2]
 * Output: 0
 * Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
 *
 * Example 3:
 * Input: nums = [11,13,15,17]
 * Output: 11
 * Explanation: The original array was [11,13,15,17] and it was rotated 4 times.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * All the integers of nums are unique.
 * nums is sorted and rotated between 1 and n times.
 *
 *
 */

/**
 * M - [Time: 20-]
 *  - 二分法处理 or binary search
 *
 */
public class N153MFindMinimuminRotatedSortedArray {

    public static void main(String[] args){
        doRun(1, new int[]{3,4,5,1,2});
        doRun(0, new int[]{4,5,6,7,0,1,2});
        doRun(11, new int[]{11,13,15,17});
        doRun(17, new int[]{17});
    }

    private static int c = 0;
    private static void doRun(int expected, int[] nums){
        int res = new N153MFindMinimuminRotatedSortedArray().findMin(nums);
        System.out.println("[" + (expected ==res) +"] "+(c++)+ ".result: "+ res + ".expected:"+expected);
    }

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Find Minimum in Rotated Sorted Array.
    //Memory Usage: 42.3 MB, less than 69.44% of Java online submissions for Find Minimum in Rotated Sorted Array.
    //binary search
    public int findMin(int[] nums) {
        if (nums.length == 1) return nums[0];

        int l = 0;
        int r = nums.length - 1;
        if (nums[r] > nums[0]) return nums[0];

        while (l < r ){
            int m = (l + r) / 2;

            if (nums[l] > nums[m]) {
                r = m; continue;
            }

            if (nums[m+1] > nums[r]) {
                l = m + 1; continue;
            }

            if(nums[l] > nums[m+1]) return nums[m+1];
            return nums[l];
        }
        return -1;
    }



    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Find Minimum in Rotated Sorted Array.
    //Memory Usage: 42.5 MB, less than 46.50% of Java online submissions for Find Minimum in Rotated Sorted Array.
    //Time: O(logN)
    public int findMin1(int[] nums) {
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
