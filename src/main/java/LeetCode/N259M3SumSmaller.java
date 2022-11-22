package LeetCode;

import java.util.Arrays;

import static java.time.LocalTime.now;

/**
 * Given an array of n integers nums and an integer target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-2,0,1,3], target = 2
 * Output: 2
 * Explanation: Because there are two triplets which sums are less than 2:
 * [-2,0,1]
 * [-2,0,3]
 * Example 2:
 *
 * Input: nums = [], target = 0
 * Output: 0
 * Example 3:
 *
 * Input: nums = [0], target = 0
 * Output: 0
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 0 <= n <= 3500
 * -100 <= nums[i] <= 100
 * -100 <= target <= 100
 */
public class N259M3SumSmaller {


    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun(2,  new int[]{-2,0,1,3}, 2);
        //doRun(2,  new int[]{-2,0,1,3}, 2);
        doRun(151,  new int[]{3,2,-2,6,2,-2,6,-2,-4,2,3,0,4,4,1}, 3);


        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[] nums, int target) {
        int res = new N259M3SumSmaller()
                .threeSumSmaller(nums, target);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 3 ms, faster than 100.00% of Java online submissions for 3Sum Smaller.
    //Memory Usage: 41.9 MB, less than 91.47% of Java online submissions for 3Sum Smaller.
    //two pointers
    //Time: O(N * logN + N * N); Space:O(LogN)
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length && nums[i] * 3 < target; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] >= target) right--;
                else res += right - (left++);
            }
        }
        return res;
    }

    //Runtime: 8 ms, faster than 94.97% of Java online submissions for 3Sum Smaller.
    //Memory Usage: 42.1 MB, less than 89.19% of Java online submissions for 3Sum Smaller.
    //Sort + Binary Search
    //Time: O(N * logN + N * N * logN); Space: O(logN)
    public int threeSumSmaller_2(int[] nums, int target) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i++){
            for (int j = i + 1; j < nums.length; j++) {
                int value = target - (nums[i] + nums[j]);
                if (value < nums[i]) break;

                int idx = Arrays.binarySearch(nums, j + 1, nums.length, value);
                if (idx > 0) {
                    while (idx - 1 > j && nums[idx] == nums[idx - 1]) idx--;
                    idx--;
                } else idx = Math.abs(idx) - 2;
                res += (idx - j);
            }
        }
        return res;
    }


    //TLE
    //Brute force
    //Time : O( N * N * N); Space: O(1)
    public int threeSumSmaller_1(int[] nums, int target) {
        int res =0;
        for (int i = 0; i < nums.length; i++){
            for (int j = i + 1; j < nums.length; j++){
                for (int k = j + 1; k < nums.length; k++){
                    if (nums[i] + nums[j] + nums[k] < target) res++;
                }
            }
        }
        return res;
    }
}
