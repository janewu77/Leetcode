package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Given an integer array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 *
 * You may assume the input array always has a valid answer.
 *
 * Example 1:
 * Input: nums = [1,5,1,1,6,4]
 * Output: [1,6,1,5,1,4]
 * Explanation: [1,4,1,5,1,6] is also accepted.
 *
 *
 * Example 2:
 * Input: nums = [1,3,2,2,3,1]
 * Output: [2,3,1,3,1,2]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5 * 104
 * 0 <= nums[i] <= 5000
 * It is guaranteed that there will be an answer for the given input nums.
 *
 *
 * Follow Up: Can you do it in O(n) time and/or in-place with O(1) extra space?
 */

/**
 * M - [time:120+]
 * - sort后再处理的话，有几种方式：1。建新空间 、 2。不停swap取尾、3.数组后移。
 * - in-place现在的做法，时间效能很差。
 * - 还有一种 Space(1) 是 new int[5001], 实际上并不是正的no extra space.  但性能比我的好。
 *
 */
public class N324MWiggleSortII {

    public static void main(String[] a){
        int[] nums;

//        nums = new int[]{3,5,2,1,6,4};
//        doRun(nums);
//
//        nums = new int[]{2,1};
//        doRun(nums);
//
//        nums = new int[]{1,2,3,4,5,6,7};
//        doRun(nums);
//        nums = new int[]{1,2,3,4,5,6,7,8};
//        doRun(nums);
//
//        nums = new int[]{8,7,6,5,4,3,2,1};
//        doRun(nums);
////
//        nums = new int[]{7,6,5,4,3,2,1};
//        doRun(nums);
//
//        nums = new int[]{1,1,1,2,2};
//        doRun(nums);
//        nums = new int[]{1,1,1,2,3,3,4,4};
//        doRun(nums);
//
//        nums = new int[]{1,2,3,4,4,4};
//        doRun(nums);
//
//        nums = new int[]{1,2,3,4,4,5,5,5};
//        doRun(nums);
//
//        nums = new int[]{1,1,1,1,2,2,3,3,3};
//        doRun(nums);
//
//        nums = new int[]{4,5,5,6};
//        doRun(nums);
        nums = new int[]{1,5,1,1,6,4};
        doRun(nums);


//        System.out.println("[3,4,2,4,2,3,1,3,1,3,1,3,1]");
//        nums = new int[]{1,4,3,4,1,2,1,3,1,3,2,3,3};
//        doRun(nums);

    }
    private static void doRun(int[] nums){
        //int[] nums = new int[]{3,5,2,1,6,4};
        new N324MWiggleSortII().wiggleSort(nums);
        Arrays.stream(nums).forEach(System.out::print);
        System.out.println();
    }

    //Runtime: 1168 ms, faster than 5.00% of Java online submissions for Wiggle Sort II.
    //Memory Usage: 46.5 MB, less than 84.83% of Java online submissions for Wiggle Sort II.
    //time: ; space: O(1)
    // 排序后，用左右交换的方式，轮流取 低位、高位
    public void wiggleSort(int[] nums) {
        if (nums.length <= 1) return;
        Arrays.sort(nums); //O(nlogN)

        int left = (nums.length - 1) / 2 ;
        swap(nums,0,left);
        for (int i = 1; i < nums.length; i++)
            swap(nums, i, nums.length-1);
    }

    private void swap(int[] nums, int left, int right){
        while (left < right){
            int t = nums[left];
            nums[left++] = nums[right];
            nums[right--] = t;
        }
    }


    //920ms 46.7 MB
    //in place
    public void wiggleSort_2(int[] nums) {
        if (nums.length <= 1) return;
        Arrays.sort(nums);

        int left = (nums.length - 1) / 2 ;
        int right = nums.length - 1;

        for (int i = 0; i < nums.length; i ++){
            if (i % 2 == 0) {
                int t = nums[left];
                int k = left;
                while (k > i) nums[k--] = nums[k];
                nums[i] = t;
            } else {
                int t = nums[right];
                int k = right;
                while (k > i)  nums[k--] = nums[k];
                nums[i] = t;
                left++;
            }
        }
    }


    //Runtime: 8 ms, faster than 60.74% of Java online submissions for Wiggle Sort II.
    //Memory Usage: 56 MB, less than 37.57% of Java online submissions for Wiggle Sort II.
    //time: O(n); space: O(n)
    public void wiggleSort_1(int[] nums) {
        if (nums.length <= 1) return;
        Arrays.sort(nums);

        int[] res = new int[nums.length];

        int left = (nums.length - 1) / 2 ;
        int right = nums.length -1;

        for (int i = 0; i < nums.length; i ++){
            if (i % 2 == 0) res[i] = nums[left--];
            else res[i] = nums[right--];
        }

        int i = 0;
        for(int n : res) nums[i++] = n;
    }
}
