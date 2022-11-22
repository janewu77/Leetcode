package LeetCode;

import java.util.Arrays;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

/**
 * Given an array of integers nums, sort the array in ascending order and return it.
 *
 * You must solve the problem without using any built-in functions in O(nlog(n)) time complexity
 * and with the smallest space complexity possible.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,2,3,1]
 * Output: [1,2,3,5]
 * Explanation: After sorting the array, the positions of some numbers are not changed (for example, 2 and 3),
 * while the positions of other numbers are changed (for example, 1 and 5).
 * Example 2:
 *
 * Input: nums = [5,1,1,2,0,0]
 * Output: [0,0,1,1,2,5]
 * Explanation: Note that the values of nums are not necessairly unique.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5 * 104
 * -5 * 104 <= nums[i] <= 5 * 104
 */

/**
 * M - [time : 30-
 *
 *
 * 排序： merge sort, bucket sort,
 */
public class N912MSortanArray {


    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun("1,2,3,5", new int[]{5,2,3,1});
        doRun("1,2,3,3", new int[]{1,2,3,3});
        doRun("1,2,3,4", new int[]{4,3,2,1});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(String expect, int[] nums) {
        int[] res1 = new N912MSortanArray().sortArray(nums);
        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    //桶排序，不符题意的 nlogn
    //Runtime: 5 ms, faster than 99.48% of Java online submissions for Sort an Array.
    //Memory Usage: 50.7 MB, less than 99.69% of Java online submissions for Sort an Array.
    //Time: O(50_000 * 2 + 1); Space:O(50_000 * 2 + 1)
    public int[] sortArray_bucket(int[] nums) {
        int offset = 50_000;
        int[] data = new int[offset + offset + 1];
        for (int n : nums) data[n+offset]++;

        int idx = 0;
        for (int i = 0; i < data.length; i++){
            while (data[i] > 0) {
                nums[idx++] = i - offset;
                data[i]--;
            }
        }
        return nums;
    }


    //Runtime: 40 ms, faster than 54.40% of Java online submissions for Sort an Array.
    //Memory Usage: 99.7 MB, less than 21.33% of Java online submissions for Sort an Array.
    //merge sort
    public int[] sortArray(int[] nums) {
        if (nums.length < 2) return nums;
        mergeSort(nums, 0, nums.length-1);
        return nums;
    }

    //Time: O(lgN * N); Space:O(lgN)
    private void mergeSort(int[] nums, int left, int right) {
        if (right == left) return;

        int mid = (left + right) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);

        merge(nums, left, mid, mid + 1, right);
    }
    //Time: O(N); Space:O(N)
    private void merge(int[] nums, int left1, int right1, int left2, int right2){
        if (left1 == left2) return;
        int[] data = Arrays.copyOfRange(nums, left1, right1 + 1);
        int idx = 0;
        while (idx < data.length && left2 <= right2) {
            nums[left1++] = data[idx] <= nums[left2] ? data[idx++] : nums[left2++];
        }
        while (idx < data.length) nums[left1++] = data[idx++];
        while (left2 <= right2) nums[left1++] = nums[left2++];
    }

    private void merge_1(int[] nums, int left1, int right1, int left2, int right2){
        if (left1 == left2) return;
        int begin = left1;
        int[] data = new int[right1 - left1 + 1 + right2 - left2 + 1];
        int idx = 0;

        while (left1 <= right1 && left2 <= right2) {
            data[idx++] = nums[left1]<= nums[left2]?nums[left1++] : nums[left2++];
        }

        while (left1 <= right1) data[idx++] = nums[left1++];
        while (left2 <= right2) data[idx++] = nums[left2++];

        for (int i = 0; i < data.length; i++) nums[begin + i] = data[i];

    }



}
