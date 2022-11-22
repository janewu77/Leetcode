package LeetCode;


/**
 * There is an integer array nums sorted in ascending order (with distinct values).
 *
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot
 * index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ...,
 * nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7]
 * might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 *
 * Given the array nums after the possible rotation and an integer target, return the index
 * of target if it is in nums, or -1 if it is not in nums.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 *
 *
 * Example 1:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 *
 *
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 *
 *
 * Example 3:
 * Input: nums = [1], target = 0
 * Output: -1
 *
 *
 * Constraints:
 * 1 <= nums.length <= 5000
 * -104 <= nums[i] <= 104
 * All values of nums are unique.
 * nums is an ascending array that is possibly rotated.
 * -104 <= target <= 104
 *
 */

/**
 * M - 【time： 60-】
 *
 * binary search的变形
 */
public class N33MSearchinRotatedSortedArray {

    public static void main(String[] args){
        doRun(4, new int[]{4,5,6,7,0,1,2}, 0);

        //doRun(4, new int[]{4,5,6,7,8,1,2,3}, 8);

    }
    private static int c = 1;
    private static void doRun(int expected, int[] nums, int target){
        int res = new N33MSearchinRotatedSortedArray().search(nums, target);
        System.out.println("[" + (expected == res) +"] "+(c++)+ ".result:"+ res + ".expected:"+expected);
    }


    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Search in Rotated Sorted Array.
    //Memory Usage: 41.7 MB, less than 95.81% of Java online submissions for Search in Rotated Sorted Array.
    //Time：O(logN); Spance: O(1)
    public int search(int[] nums, int target) {
        if (nums.length == 0) return -1;

        int l = 0; int r = nums.length - 1;
        while (l <= r){
            int k = (l + r) >> 1; // 位操作比 / 要快
            if (nums[k] == target) return k;
            if (nums[l] <= nums[k]){
                if (target >= nums[l] && target < nums[k])  {
                    r = k - 1;
                }else
                    l = k + 1;
            }else if (nums[k] <= nums[r]){
                if(target > nums[k] && target <= nums[r]) {
                    l = k + 1;
                }else
                    r = k - 1;
            }
        }
        return -1;
    }

}
