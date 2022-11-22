package LeetCode;


import java.util.Arrays;

import static java.time.LocalTime.now;

/**
 *
 *  @author: Jane W.
 *  @date: 2021/1/19
 *
 * leetCode.com
 * 16. 3Sum Closest[Medium]
 *
 * Given an array nums of n integers and an integer target,
 * find three integers in nums such that the sum is closest to target.
 *
 * Return the sum of the three integers.
 * You may assume that each input would have exactly one solution.
 *
 * Example 1:
 *
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 *
 *
 */

/**
 * M：
 * 注意不要对"最大值"进行加操作；同理，不要对"最小值"进行减操作。
 * 利用排序后的特性，减少计算量。
 */

public class N16M3SumClosest {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun(3,  new int[]{1,1,1,1}, 0);
        doRun(2,  new int[]{-1,2,1,-4}, 1);
        doRun(0,  new int[]{0,0,0,0}, 1);
        doRun(1,  new int[]{0,0,0,1}, 1);


        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, int[] nums, int target) {
        int res = new N16M3SumClosest()
                .threeSumClosest(nums, target);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //Runtime: 23 ms, faster than 98.19% of Java online submissions for 3Sum Closest.
    //Memory Usage: 49.3 MB, less than 17.78% of Java online submissions for 3Sum Closest.
    //Two pointers
    //Time : O(N * logN + N * N); Space: O(logN)
    public int threeSumClosest(int[] nums, int target) {
        if (nums.length == 3) return nums[0] + nums[1] + nums[2];

        //T:O(NlogN); Space:O(logN)
        Arrays.sort(nums);

        int maxSum = nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3];
        if (target > maxSum) return maxSum;

        int minDiff = Integer.MAX_VALUE;
        //T:O(N * N)
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;

            //two pointers
            while (left < right){
                maxSum = nums[i] + nums[right - 1] + nums[right];
                if (target - maxSum > Math.abs(minDiff)) break;

                int minSum = nums[i] + nums[left] + nums[left + 1];
                if (minSum - target > Math.abs(minDiff)) break;

                int sum = nums[i] + nums[left] + nums[right];
                if(sum == target) return sum;
                int diff = sum - target;
                if (Math.abs(diff) < Math.abs(minDiff)) minDiff = diff;
                if (diff > 0) right--;
                else left++;
            }//end while
        }//End for
        return target + minDiff;
    }

    //Runtime: 422 ms, faster than 5.04% of Java online submissions for 3Sum Closest.
    //Memory Usage: 42.1 MB, less than 96.03% of Java online submissions for 3Sum Closest.
    //Binary Search
    //Time: O(N * logN + N * N * logN); Space: O(logN)
    public int threeSumClosest_1(int[] nums, int target) {
        if (nums.length == 3) return nums[0] + nums[1] + nums[2];

        //T:O(NlogN); Space:O(logN)
        Arrays.sort(nums);

        int minDiff = Integer.MAX_VALUE;
        //T:O(N * N)
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int idx = Arrays.binarySearch(nums, j + 1, nums.length, target - nums[i] - nums[j]);

                if (idx >= 0) return target;
                else{
                    idx = - idx - 1;
                    if (idx - 1 > j){
                        int diff = nums[i] + nums[j] + nums[idx - 1] - target;
                        if (Math.abs(diff) < Math.abs(minDiff)) minDiff = diff;
                    }
                    if (idx < nums.length) {
                        int diff = nums[i] + nums[j] + nums[idx] - target;
                        if (Math.abs(diff) < Math.abs(minDiff)) minDiff = diff;
                    }
                }
            }
        }//End for
        return target + minDiff;
    }


    //2019
    //先排序；定一个，用left /right 进行遍历
    //Runtime: 34 ms, faster than 97.92% of Java online submissions for 3Sum Closest.
    //Memory Usage: 42.4 MB, less than 89.67% of Java online submissions for 3Sum Closest.
    //Two pointers
    //Time : O(N * logN + N * N); Space: O(logN)
    public int threeSumClosest_0(int[] nums, int target) {
        int result = Integer.MIN_VALUE;

        if (nums.length < 3) return result;
        if (nums.length == 3) return nums[0] + nums[1] + nums[2];

        //T:O(NlogN); Space:O(logN)
        Arrays.sort(nums);

        int minDiff = Integer.MAX_VALUE;
        int i = 0;

        //T:O(N * N)
        while (i < nums.length - 2) {//} && (nums[i]*3-target <= minDiff) ){

            int left = i + 1;
            int right = nums.length - 1;

            int minSum = nums[i] + nums[left] + nums[left + 1];
            int maxSum = nums[i] + nums[right - 1] + nums[right];

            //数据集与目标集（目标+-差）没有交集了
            // minDiff 在初始化时是最大值，不能再加
            if ((minDiff < target - maxSum) || (minSum - target > minDiff))
                break;

            //two pointers
            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];
                int diff = sum - target;

                if(diff == 0) return sum;

                if(diff > 0) right--;
                else left++;

                int tmpDiff = Math.abs(diff);
                if (tmpDiff < minDiff) {
                    result = sum;
                    minDiff = tmpDiff;
                }
            }//end while
            i++;
        }//End for

        return result;
    }
}
