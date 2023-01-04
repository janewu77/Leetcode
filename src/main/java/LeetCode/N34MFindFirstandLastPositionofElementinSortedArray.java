package LeetCode;

import ADraft.BinarySearchDemo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * Given an array of integers nums sorted in non-decreasing order,
 * find the starting and ending position of a given target value.
 *
 * If target is not found in the array, return [-1, -1].
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * Example 3:
 *
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 *
 *
 * Constraints:
 *
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums is a non-decreasing array.
 * -109 <= target <= 109
 */

/**
 * m : [time: 40-]
 *  - binary search
 *
 */
public class N34MFindFirstandLastPositionofElementinSortedArray {

    public static void main(String[] args) {
        int[] data;
        data = new int[]{9,9,9,9,9,9,9,9,9,9};
        doRun(new int[]{0, 9}, data, 9);

        data = new int[]{5,7,7,8,8,10};
        doRun(new int[]{3, 4}, data, 8);

        data = new int[]{1,2,2};
        doRun(new int[]{1, 2}, data, 2);

        doRun(new int[]{0, 0}, new int[]{1,3}, 1);
        doRun(new int[]{1, 1}, new int[]{1,3}, 3);
    }

    private static int c = 1;
    private static void doRun(int[] expected, int[] nums, int target){
        int[] res = new N34MFindFirstandLastPositionofElementinSortedArray().searchRange(nums, target);
        System.out.println("[" + Arrays.equals(expected, res) +"].[result:"+ Arrays.toString(res) + "][expected:"+Arrays.toString(expected)+"]");
    }

    //2. two binary searches
    //Runtime: 0ms 100%; Memory: 47.4MB 32%
    //Time: O(logN); Space: O(1)
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (target == nums[mid] && (mid == 0 || nums[mid - 1] != target)) {
                res[0] = mid; break;
            }
            if (nums[mid] >= target) right = mid - 1;
            else left = mid + 1;
        }

        left = 0; right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target == nums[mid] && (mid == nums.length - 1 || nums[mid + 1] != target)) {
                res[1] = mid; break;
            }
            if (nums[mid] > target) right = mid - 1;
            else left = mid + 1;
        }

        return res;
    }


    //1.one pass
    //Runtime: 2ms 12%; Memory: 46.5MB 58%
    //Time: O(N); Space: O(1)
    public int[] searchRange_1(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        if (nums.length <= 0) return res;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (i == 0 || nums[i - 1] != target) res[0] = i;
                if (i == nums.length - 1 || nums[i + 1] != target) res[1] = i;
            }
        }
        return res;
    }

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Find First and Last Position of Element in Sorted Array.
    //Memory Usage: 45.6 MB, less than 90.63% of Java online submissions for Find First and Last Position of Element in Sorted Array.
    //time: o(log(n) * 2* log(n/2)
    public int[] searchRange_11(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        if (nums.length <= 0) return res;
        if (nums[0] == target && nums[nums.length - 1] == target) return new int[]{0, nums.length - 1};

        int p = binarySearch(nums, target, 0, nums.length -1);
        if (p == -1) return res;

        res[0] = p;
        res[1] = p;
        if (p-1 >= 0)
            while (p != -1) {
                res[0] = p;
                p = binarySearch(nums, target, 0, p-1);
            }

        p = res[1];
        if (p+1 <nums.length){
            while(p != -1) {
                res[1] = p;
                p = binarySearch(nums, target, p+1, nums.length - 1);
            }
        }
        return res;
    }

    private int binarySearch(int[] dataList, int target, int from, int to){
        return new BinarySearchDemo().binarySearch_while(dataList,target,from,to);
    }

}
