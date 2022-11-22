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
        String expected;
        data = new int[]{9,9,9,9,9,9,9,9,9,9};
        expected = "0,9";
        doRun("0,9", data, 9);

        data = new int[]{5,7,7,8,8,10};
        expected = "3,4";
        doRun(expected, data, 8);

        data = new int[]{1,2,2};
        expected = "1,2";
        doRun(expected, data, 2);

    }

    private static int c = 1;
    private static void doRun(String expected, int[] nums, int target){
        int[] res = new N34MFindFirstandLastPositionofElementinSortedArray().searchRange(nums, target);
        String tmp = Arrays.stream(res).mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(","));
        System.out.println("[" + (expected.equals(tmp)) +"] "+(c++)
                + ".[result:"+ tmp + "][expected:"+expected+"]");
    }

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Find First and Last Position of Element in Sorted Array.
    //Memory Usage: 45.6 MB, less than 90.63% of Java online submissions for Find First and Last Position of Element in Sorted Array.
    //time: o(log(n) * 2* log(n/2)
    public int[] searchRange(int[] nums, int target) {
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
