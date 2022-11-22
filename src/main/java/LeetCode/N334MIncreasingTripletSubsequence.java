package LeetCode;


import java.util.*;

import static java.time.LocalTime.now;

/**
 * Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that
 * i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5]
 * Output: true
 * Explanation: Any triplet where i < j < k is valid.
 * Example 2:
 *
 * Input: nums = [5,4,3,2,1]
 * Output: false
 * Explanation: No triplet exists.
 * Example 3:
 *
 * Input: nums = [2,1,5,0,4,6]
 * Output: true
 * Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5 * 105
 * -231 <= nums[i] <= 231 - 1
 *
 *
 * Follow up: Could you implement a solution that runs in O(n) time complexity and O(1) space complexity?
 */

/**
 * M - [time: 60+
 */
public class N334MIncreasingTripletSubsequence {


    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun(true, new int[]{1,5,0,4,1,3});
        doRun(true, new int[]{1,2,5,0,1,0});
        doRun(true, new int[]{1,0,0,0,-1,0,3});
        doRun(false, new int[]{1,1,1,1,1});
        doRun(true, new int[]{1,2,3,4,5});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(boolean expect, int[] nums) {
        boolean res = new N334MIncreasingTripletSubsequence()
                .increasingTriplet(nums);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }
//
//    public boolean increasingTriplet(int[] nums) {
//
//    }


    //https://leetcode.com/problems/increasing-triplet-subsequence/solution/
    //One pass
    //Runtime: 4 ms, faster than 59.53% of Java online submissions for Increasing Triplet Subsequence.
    //Memory Usage: 93.4 MB, less than 49.81% of Java online submissions for Increasing Triplet Subsequence.
    //Time: O(N); Space: O(1)
    public boolean increasingTriplet(int[] nums) {
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;

        for (int value : nums) {
            if (value <= a) a = value;
            else if (value <= b) b = value;
            else return true; //value > a && value > b
        }
        return false;
    }

    //1.Brute Force
    //Time Limit Exceeded
    //Time: O(N^3); Space: O(1)
    public boolean increasingTriplet_1(int[] nums) {
        for (int i1 = 0; i1 < nums.length - 2; i1++) {
            for (int i2 = i1 + 1; i2 < nums.length - 1; i2++) {
                if (nums[i1] < nums[i2]) {
                    for (int i3 = i2 + 1; i3 < nums.length; i3++) {
                        if (nums[i2] < nums[i3]) return true;
                    }
                }
            }
        }
        return false;
    }

    //TLE
//    public boolean increasingTriplet(int[] nums) {
//        List<int[]> list = new ArrayList<>();
//        List<Integer> bList = new ArrayList<>();
//
//        for (int i = 0; i < nums.length; i++){
//            if (i!= 0 && nums[i] == nums[i-1]) continue;
//
//            int idx3 = Collections.binarySearch(bList, nums[i] - 1);
//            if (idx3 != -1) return true;
//            //for(int valueB : bList) if (valueB < nums[i]) return true;
//
//            int[] node = new int[]{nums[i], i, 0};
//            int idx = Collections.binarySearch(list, node, (a,b) -> a[0] - b[0] == 0 ? b[1] - a[1] : a[0] - b[0]);
//            idx = idx == -1 ? 0 : -idx - 1;
//            if (idx != 0) {
//                node[2] = 1; bList.add(nums[i]);
//
//                int idx2 = Collections.binarySearch(bList, nums[i]);
//                idx2 = idx2 == -1 ? 0 : -idx2 - 1;
//                bList.add(idx2, nums[i]);
//            }
//            list.add(idx, node);
//        }
//        return false;
//    }



}
