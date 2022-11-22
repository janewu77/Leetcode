package LeetCode;

import javafx.print.Collation;

import java.util.Arrays;

/**
 *
 * Given an array nums with n objects colored red, white, or blue,
 * sort them in-place so that objects of the same color are adjacent,
 * with the colors in the order red, white, and blue.
 *
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 *
 * You must solve this problem without using the library's sort function.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * Example 2:
 *
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] is either 0, 1, or 2.
 *
 *
 * Follow up: Could you come up with a one-pass algorithm using only constant extra space?
 */
/*
* M - 耗时10-
*   - count order
*   - three pointers & exchange
*
* */
public class N75MSortColors {

    public static void main(String[] args){

        int[] data = new int[]{2,0,2,1,1,0};
        new N75MSortColors().sortColors(data);
        Arrays.stream(data).forEach(System.out::print);
    }

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Sort Colors.
    //Memory Usage: 40.6 MB, less than 93.42% of Java online submissions for Sort Colors.
    // one pass
    public void sortColors(int[] nums) {
        if (nums.length <= 1) return;
        int i = 0;
        int j = nums.length - 1;
        int k = i;
        while (i < j && k<=j) {
            while (i < nums.length && nums[i] == 0) {
                i++; k = i;
            }
            while (j >= 0 && nums[j] == 2) j--;
            if (i >= j) break;

            if (nums[k] == 0) {nums[k] = nums[i]; nums[i] = 0;}
            else if (nums[k] == 2) {nums[k] = nums[j]; nums[j] = 2;}
            else k++;
        }
    }

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Sort Colors.
    //Memory Usage: 42 MB, less than 79.45% of Java online submissions for Sort Colors.
    public void sortColors1(int[] nums) {
        if (nums.length <= 1) return;

        int[] counts = new int[]{0,0,0};
        for(int n : nums) counts[n]++;

        for(int i = 0; i <nums.length; i++){
            if (i <counts[0]) nums[i] = 0;
            else if (i < counts[0]+counts[1]) nums[i] = 1;
            else nums[i] = 2;
        }
    }
}
