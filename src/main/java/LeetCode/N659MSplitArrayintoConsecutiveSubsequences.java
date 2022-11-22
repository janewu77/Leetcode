package LeetCode;


import java.util.*;

import static java.time.LocalTime.now;


/**
 * You are given an integer array nums that is sorted in non-decreasing order.
 *
 * Determine if it is possible to split nums into one or more subsequences such that both of the following conditions are true:
 *
 * Each subsequence is a consecutive increasing sequence (i.e. each integer is exactly one more than the previous integer).
 * All subsequences have a length of 3 or more.
 * Return true if you can split nums according to the above conditions, or false otherwise.
 *
 * A subsequence of an array is a new array that is formed from the original array by deleting some (can be none) of the elements without disturbing the relative positions of the remaining elements. (i.e., [1,3,5] is a subsequence of [1,2,3,4,5] while [1,3,2] is not).
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,3,4,5]
 * Output: true
 * Explanation: nums can be split into the following subsequences:
 * [1,2,3,3,4,5] --> 1, 2, 3
 * [1,2,3,3,4,5] --> 3, 4, 5
 * Example 2:
 *
 * Input: nums = [1,2,3,3,4,4,5,5]
 * Output: true
 * Explanation: nums can be split into the following subsequences:
 * [1,2,3,3,4,4,5,5] --> 1, 2, 3, 4, 5
 * [1,2,3,3,4,4,5,5] --> 3, 4, 5
 * Example 3:
 *
 * Input: nums = [1,2,3,4,4,5]
 * Output: false
 * Explanation: It is impossible to split nums into consecutive increasing subsequences of length 3 or more.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * -1000 <= nums[i] <= 1000
 * nums is sorted in non-decreasing order.
 */

/**
 * M - [time: 120+
 */
public class N659MSplitArrayintoConsecutiveSubsequences {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;

        System.out.println("========doRun_demo==========");

        data = new int[]{1,2,3,3,4,5};
        doRun_demo(true, data);

        data = new int[]{1,2,3,3,4,4,5,5};
        doRun_demo(true, data);

        data = new int[]{1,2,3,4,4,5};
        doRun_demo(false, data);

        data = new int[]{1,2,3,8,9,10};
        doRun_demo(true, data);

        data = new int[]{1,2,3,3,8,9,10};
        doRun_demo(false, data);

        data = new int[]{1,2,3,4,5,6};
        doRun_demo(true, data);

        data = new int[]{1,2,2,2,3,3,3,4,4,4,5,6};
        doRun_demo(true, data);

        data = new int[]{14,14,15,15,16,16,17,17,18,18,19,19,20,20,20,21,21,21,22,22,22,23,23,23,24,24,24,24,25,25,25,25,26,26,26,26,27,27,27,27,28,28,28,28,29,29,29,30,30,30,31,31,31,32,32,32,33,33,33,34,34,34,35,35,35,36,36,36,37,37,37,38,38,38,39,39,39,40,40,40,41,41,41,42,42,43,43,44,44,45,45,46,46,47,47,47,48,48,48,49,49,49,50,50,50,51,51,51,52,52,52,53,53,53,54,54,54,55,55,55,56,56,56,57,57,57,58,58,58,59,59,59,60,60,60,61,61,61,62,62,62,62,63,63,63,63,64,64,64,64,65,65,65,65,65,66,66,66,66,66,67,67,67,67,67,68,68,68,68,68,68,69,69,69,69,69,69,70,70,70,70,70,70,71,71,71,71,71,71,72,72,72,72,72,72,73,73,73,73,73,73,74,74,74,74,74,74,75,75,75,75,75,75,76,76,76,76,76,76,77,77,77,77,77,77,78,78,78,78,78,78,79,79,79,79,79,79,80,80,80,80,80,80,80,81,81,81,81,81,81,81,82,82,82,82,82,82,82,83,83,83,83,83,83,83,84,84,84,84,84,84,84,85,85,85,85,85,85,85,86,86,86,86,86,86,86,86,87,87,87,87,87,87,87,87,88,88,88,88,88,88,88,88,89,89,89,89,89,89,89,89,90,90,90,90,90,90,90,90,91,91,91,91,91,91,91,92,92,92,92,92,92,92,93,93,93,93,93,93,93,94,94,94,94,94,94,95,95,95,95,95,95,96,96,96,96,96,96,97,97,97,97,97,97,98,98,98,98,98,98,99,99,99,99,99,99,100,100,100,100,100,101,101,101,101,101,102,102,102,102,102,103,103,103,103,103,104,104,104,104,104,105,105,105,105,105,106,106,106,106,106,107,107,107,107,107,108,108,108,108,108,109,109,109,109,109,110,110,110,110,110,111,111,111,111,111,112,112,112,113,113,113,114,114,114,115,115,115,116,116,116,117,117,117,118,118,118,119,119,119,120,120,120,121,121,121,122,122,122,123,123,123,124,124,124,125,125,125,126,126,126,127,127,127,128,128,128,129,129,129,130,130,130,131,131,131,132,132,132,133,133,133,134,134,135,135,136,136,137,137,138,138,139,139,140,140,141,141,142,142,143,143,144,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170};
        doRun_demo(true, data);

        data = new int[]{3,4,4,5,6,7,8,9,10,11};
        doRun_demo(false, data);

        data = new int[]{10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,26,26,26,26,26,26,26,26,26,26,26,26,26,26,26,26,26,26,26,26,26,26,26,26,26,26,26,26,26,26,26,26,27,27,27,27,27,27,27,27,27,27,27,27,27,27,27,27,27,27,27,27,27,27,27,28,28,28,28,28,28,28,28,28,28,28,28,28,28,28,28,28,28,28,28,28,28,28,28,28,28,28,28,28,28,28,28,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,34,34,34,34,34,34,34,34,34,34,34,34,34,34,34,34,34,34,34,34,34,34,34,34,34,34,34,34,35,35,35,35,35,35,35,35,35,35,35,35,35,35,35,35,35,35,35,35,35,35,35,35,35,35,35,35,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,37,37,37,37,37,37,37,37,37,37,37,37,37,37,37,37,37,37,37,37,37,37,37,37,37,37,37,37,37,37,37,37,38,38,38,38,38,38,38,38,38,38,38,38,38,38,38,38,38,38,38,38,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,41,41,41,41,41,41,41,41,41,41,41,41,41,41,41,41,41,41,41,41,41,41,41,41,41,41,41,41,41,41,41,41,41,41,41,41,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,43,43,43,43,43,43,43,43,43,43,43,43,43,43,43,43,43,43,43,43,43,43,43,43,44,44,44,44,44,44,44,44,44,44,44,44,44,44,44,44,44,44,44,44,44,44,44,44,44,44,44,44,44,44,44,44,44,45,45,45,45,45,45,45,45,45,45,45,45,45,45,45,45,45,45,45,45,45,45,46,46,46,46,46,46,46,46,46,46,46,46,46,46,46,46,46,46,46,46,46,46,46,46,46,46,46,46,46,46,46,47,47,47,47,47,47,47,47,47,47,47,47,47,47,47,47,47,47,47,47,47,47,47,47,47,47,47,47,47,48,48,48,48,48,48,48,48,48,48,48,48,48,48,48,48,48,48,49,49,49,49,49,49,49,49,49,49,49,49,49,49,49,49,49,49,49,49,49,49,49,49,49,49,49,49,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,51,51,51,51,51,51,51,51,51,51,51,51,51,51,51,51,51,51,51,51,51,51,51,52,52,52,52,52,52,52,52,52,52,52,52,52,52,52,52,52,52,52,52,52,52,52,52,52,52,52,52,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,54,54,54,54,54,54,54,54,54,54,54,54,54,54,54,54,54,54,54,54,54,54,54,54,54,54,54,54,54,54,54,54,54,54,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,57,57,57,57,57,57,57,57,57,57,57,57,57,57,57,57,57,57,57,57,57,57,57,57,57,57,57,57,57,57,58,58,58,58,58,58,58,58,58,58,58,58,58,58,58,58,58,58,58,58,58,58,58,58,58,58,58,58,58,58,59,59,59,59,59,59,59,59,59,59,59,59,59,59,59,59,59,59,59,59,59,59,59,59,59,59,59,59,59,59,59,59,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,61,61,61,61,61,61,61,61,61,61,61,61,61,61,61,61,61,61,61,61,61,61,61,61,61,62,62,62,62,62,62,62,62,62,62,62,62,62,62,62,62,62,62,62,62,62,62,62,62,62,62,62,62,62,63,63,63,63,63,63,63,63,63,63,63,63,63,63,63,63,63,63,63,63,63,63,63,63,63,63,63,63,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,66,66,66,66,66,66,66,66,66,66,66,66,66,66,66,66,66,66,66,66,66,66,66,66,66,66,66,66,66,66,66,66,66,66,66,66,66,66,67,67,67,67,67,67,67,67,67,67,67,67,67,67,67,67,67,67,67,67,67,67,67,67,67,67,67,67,68,68,68,68,68,68,68,68,68,68,68,68,68,68,68,68,68,68,68,68,68,69,69,69,69,69,69,69,69,69,69,69,69,69,69,69,69,69,69,69,69,69,69,69,69,69,69,69,69,69,69,69,69,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,71,71,71,71,71,71,71,71,71,71,71,71,71,71,71,71,71,71,71,71,71,71,71,71,71,71,71,72,72,72,72,72,72,72,72,72,72,72,72,72,72,72,72,72,72,72,72,72,72,72,72,72,73,73,73,73,73,73,73,73,73,73,73,73,73,73,73,73,73,73,73,73,73,73,73,73,73,73,74,74,74,74,74,74,74,74,74,74,74,74,74,74,74,74,74,74,74,74,74,74,74,74,74,74,74,74,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,76,76,76,76,76,76,76,76,76,76,76,76,76,76,76,76,76,76,76,76,76,76,76,76,76,76,76,76,76,76,77,77,77,77,77,77,77,77,77,77,77,77,77,77,77,77,77,77,77,78,78,78,78,78,78,78,78,78,78,78,78,78,78,78,78,78,78,78,78,78,78,78,78,78,78,78,78,78,78,78,79,79,79,79,79,79,79,79,79,79,79,79,79,79,79,79,79,79,79,79,79,79,79,79,79,79,79,79,79,79,79,79,79,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,81,81,81,81,81,81,81,81,81,81,81,81,81,81,81,81,81,81,81,81,81,81,81,81,81,81,81,81,82,82,82,82,82,82,82,82,82,82,82,82,82,82,82,82,82,82,82,82,82,82,82,82,82,82,82,82,82,82,82,82,82,82,82,82,82,82,82,82,82,82,82,82,83,83,83,83,83,83,83,83,83,83,83,83,83,83,83,83,83,83,83,84,84,84,84,84,84,84,84,84,84,84,84,84,84,84,84,84,84,84,84,84,84,84,85,85,85,85,85,85,85,85,85,85,85,85,85,85,85,85,85,85,85,85,85,85,85,85,85,85,85,85,85,85,85,85,85,85,85,86,86,86,86,86,86,86,86,86,86,86,86,86,86,86,86,86,86,86,86,86,86,87,87,87,87,87,87,87,87,87,87,87,87,87,87,87,87,87,87,87,87,87,87,87,87,87,87,87,87,87,87,87,87,87,87,87,87,87,87,87,88,88,88,88,88,88,88,88,88,88,88,88,88,88,88,88,88,88,89,89,89,89,89,89,89,89,89,89,89,89,89,89,89,89,89,89,89,89,89,89,89,89,89,89,89,89,89,89,89,89,89,89,89,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,92,92,92,92,92,92,92,92,92,92,92,92,92,92,92,92,92,92,92,92,92,92,92,92,92,92,93,93,93,93,93,93,93,93,93,93,93,93,93,93,93,93,93,93,93,93,93,93,93,93,93,93,93,93,93,94,94,94,94,94,94,94,94,94,94,94,94,94,94,94,94,94,94,94,94,94,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,97,97,97,97,97,97,97,97,97,97,97,97,97,97,97,97,97,97,97,97,97,97,97,97,97,97,97,97,97,97,98,98,98,98,98,98,98,98,98,98,98,98,98,98,98,98,98,98,98,98,98,98,98,98,98,98,98,98,98,98,98,98,98,98,98,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100};
        doRun_demo(false, data);

        data = new int[]{1,2,3,5,5,6,7};
        doRun_demo(false, data);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun_demo(boolean expect, int[] nums) {
        boolean res = new N659MSplitArrayintoConsecutiveSubsequences().isPossible(nums);
//        String res = comm.toString(res1);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }


    //base on isPossible_1
    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Split Array into Consecutive Subsequences.
    //Memory Usage: 43.2 MB, less than 95.91% of Java online submissions for Split Array into Consecutive Subsequences.
    //one pass
    //Time: O(N); Space: O(1)
    public boolean isPossible(int[] nums) {
        if (nums.length < 3) return false;

        int lastCount = 0, lastValue = 0;
        int begin = 0, i = 0, idx = 0;

        while (i < nums.length){
            //a gap
            if (i - 1 >= begin && nums[i] > lastValue + 1) {
                if (lastValue - nums[idx] < 2) return false;
                //new segment
                lastCount = 0; lastValue = 0;
                idx = begin = i;
            }

            //duplicated numbers
            int count = 1;
            while (i + 1 < nums.length && nums[i + 1] == nums[i]) {
                i++; count++;
                if (count > lastCount) nums[++idx] = nums[i];
            }

            if (count < lastCount && (lastValue - nums[idx - count] < 2))
                return false;

            lastCount = count;
            lastValue = nums[i++];
        }
//        if (lastValue - nums[idx] + 1 < 3) return false;
        if (lastValue - nums[idx] < 2) return false;
        return true;
    }

    //Runtime: 2 ms, faster than 98.86% of Java online submissions for Split Array into Consecutive Subsequences.
    //Memory Usage: 43.2 MB, less than 95.91% of Java online submissions for Split Array into Consecutive Subsequences.
    //from solution
    //DP
    public boolean isPossible_3(int[] nums) {
        int n = nums.length, start = 0;

        for (int i = 1; i < n; i++) {
            //Check possibility of a valid segment starting at index start and ending at index i - 1.
            if (nums[i] - nums[i - 1] > 1) {
                if (!isSegmentValid(nums, start, i - 1)) {
                    return false;
                }
                //Update the starting index of the next segment.
                start = i;
            }
        }
        //Check for the last segment
        return isSegmentValid(nums, start, n - 1);
    }

    private boolean isSegmentValid(int[] nums, int start, int end) {
        int frequency = 0;

        //lengthOneSubsequences holds count of subsequences of length 1.
        int lengthOneSubsequences = 0;

        //lengthTwoSubsequences holds count of subsequences of length 2.
        int lengthTwoSubsequences = 0;

        //totalNoOfSubsequences holds count of all subsequences.
        int totalNoOfSubsequences = 0;

        for (int i = start; i <= end; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                frequency++;
            } else if (frequency < lengthOneSubsequences + lengthTwoSubsequences) {
                // If the frequency[i] is less than total number of subsequences ending with i - 1,
                // we do not have enough subsequences where we can put i.
                return false;
            } else {
                // Total number of subsequences of length 2 can be obtained by
                // adding i to subsequences of length 1 ending with i - 1.
                lengthTwoSubsequences = lengthOneSubsequences;
                lengthOneSubsequences = Math.max(0, frequency - totalNoOfSubsequences);
                totalNoOfSubsequences = frequency;
                frequency = 1;
            }
        }
        // For the last element in the segment
        lengthTwoSubsequences = lengthOneSubsequences;
        lengthOneSubsequences = Math.max(0, frequency - totalNoOfSubsequences);
        // If there is no remaining subsequence of length one or two, we can return true.
        // Otherwise, return false.
        return lengthOneSubsequences == 0 && lengthTwoSubsequences == 0;
    }


    //from solution
    //Approach 1: Greedy using Heap
    //Runtime: 15 ms, faster than 80.45% of Java online submissions for Split Array into Consecutive Subsequences.
    //Memory Usage: 72.1 MB, less than 34.55% of Java online submissions for Split Array into Consecutive Subsequences.
    //Time:  O(Nlog(N)); Space: O(N)
    public boolean isPossible_2(int[] nums) {
        PriorityQueue<int[]> subsequences = new PriorityQueue<>((int[] subsequence1, int[] subsequence2) -> {
            if (subsequence1[1] == subsequence2[1]) {
                return (subsequence1[1] - subsequence1[0]) - (subsequence2[1] - subsequence2[0]);
            }
            return (subsequence1[1] - subsequence2[1]);
        });

        //Time: O(Nlog(N))
        for (int num : nums) {
            // Condition 1 - remove non-qualifying subsequences
            while (!subsequences.isEmpty() && subsequences.peek()[1] + 1 < num) {
                int[] currentSubsequence = subsequences.poll();
                int subsequenceLength = currentSubsequence[1] - currentSubsequence[0] + 1;
                if (subsequenceLength < 3) {
                    return false;
                }
            }

            //Time: O(log(N))
            //Condition 2 - create a new subsequence
            if (subsequences.isEmpty() || subsequences.peek()[1] == num) {
                subsequences.add(new int[]{num, num});
            } else {
                // Condition 3 - add num to an existing subsequence
                int[] currentSubsequence = subsequences.poll();
                subsequences.add(new int[]{currentSubsequence[0], num});
            }
        }

        // If any subsequence is of length less than 3, return false
        while (!subsequences.isEmpty()) {
            int[] currentSubsequence = subsequences.poll();
            int subsequenceLength = currentSubsequence[1] - currentSubsequence[0] + 1;
            if (subsequenceLength < 3) {
                return false;
            }
        }

        return true;
    }


    //Runtime: 2 ms, faster than 98.86% of Java online submissions for Split Array into Consecutive Subsequences.
    //Memory Usage: 42.7 MB, less than 99.77% of Java online submissions for Split Array into Consecutive Subsequences.
    //one pass + queue
    //Time: O(N + R); Space: O(R)
    //R is the numbers of sub-array which has different length.
    public boolean isPossible_1(int[] nums) {
        if (nums.length < 3) return false;
        Queue<int[]> beginQueue = new LinkedList<>();

        int begin = 0;
        int lastCount = 0;
        int lastValue = nums[0] - 1;
        int i = 0;
        while (i < nums.length){
            //span
            if (i - 1 >= begin && nums[i] > lastValue + 1) {
                while(!beginQueue.isEmpty())
                    if ((lastValue - beginQueue.poll()[0]) + 1 < 3) return false;

                lastCount = 0;
                lastValue = nums[0] - 1;
                begin = i;
                continue;
            }

            //duplicated numbers
            int count = 1;
            while (i + 1 < nums.length && nums[i+1] == nums[i]) {
                i++; count++;
            }

            int x = count - lastCount;
            if (x > 0){
                beginQueue.add(new int[]{nums[i], x});
            }else if (x < 0) {
                x = Math.abs(x);
                while (x > 0 && !beginQueue.isEmpty()){
                    int[] pair = beginQueue.peek();
                    if ((lastValue - pair[0]) + 1 < 3) return false;
                    if (x >= pair[1]){
                        beginQueue.poll();
                        x -= pair[1];
                    }else{
                        pair[1] -= x;
                        x = 0;
                    }
                }
            }
            lastCount = count;
            lastValue = nums[i];
            i++;
        }

        while(!beginQueue.isEmpty())
            if ((lastValue - beginQueue.poll()[0]) + 1 < 3) return false;
        return true;
    }

}
