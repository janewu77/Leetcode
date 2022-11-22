package LeetCode;

import java.util.*;

import static java.time.LocalTime.now;

/**
 * You are given an integer array nums. In one operation, you can replace any element in nums with any integer.
 *
 * nums is considered continuous if both of the following conditions are fulfilled:
 *
 * All elements in nums are unique.
 * The difference between the maximum element and the minimum element in nums equals nums.length - 1.
 * For example, nums = [4, 2, 5, 3] is continuous, but nums = [1, 2, 3, 5, 6] is not continuous.
 *
 * Return the minimum number of operations to make nums continuous.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,2,5,3]
 * Output: 0
 * Explanation: nums is already continuous.
 * Example 2:
 *
 * Input: nums = [1,2,3,5,6]
 * Output: 1
 * Explanation: One possible solution is to change the last element to 4.
 * The resulting array is [1,2,3,5,4], which is continuous.
 * Example 3:
 *
 * Input: nums = [1,10,100,1000]
 * Output: 3
 * Explanation: One possible solution is to:
 * - Change the second element to 2.
 * - Change the third element to 3.
 * - Change the fourth element to 4.
 * The resulting array is [1,2,3,4], which is continuous.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 */

/**
 * H- [Time: 120+
 *
 */
public class N2009HMinimumNumberofOperationsMakeArrayContinuous {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;

        System.out.println("========doRun_demo==========");
        data = new int[]{4,2,5,3};
        doRun_demo(0, data);

        data = new int[]{1,2,3,5,6};
        doRun_demo(1, data);

        data = new int[]{1,10,100,1000};
        doRun_demo(3, data);

        data = new int[]{8,5,9,9,8,4};
        doRun_demo(2, data);

        data = new int[]{41,33,29,33,35,26,47,24,18,28};
        doRun_demo(5, data);

        data = new int[]{1,2,10,10,10};
        doRun_demo(3, data);

        data = new int[]{8,10,16,18,10,10,16,13,13,16};
        doRun_demo(6, data);


        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun_demo(long expect, int[] nums) {
        int res = new N2009HMinimumNumberofOperationsMakeArrayContinuous().minOperations(nums);
//        String res = comm.toString(res1);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    //Binary Index Tree?

    //网上看来的
    //Runtime: 40 ms, faster than 99.39% of Java online submissions for Minimum Number of Operations to Make Array Continuous.
    //Memory Usage: 60.3 MB, less than 93.33% of Java online submissions for Minimum Number of Operations to Make Array Continuous.
    //Time: O(N*lgN + N + 2N); Space:O(1);
    public int minOperations_4(int[] nums) {
        if(nums.length == 1) return 0;

        Arrays.sort(nums);
        //unique
        int actualLen = 1;
        for(int i = 1; i < nums.length; i++)
            if (nums[i] != nums[i-1]) nums[actualLen++] = nums[i];

        //Time: O(2N);
        int ans = nums.length;
        int j = 0;
        for (int i = 0; i < actualLen; i++) {
            while (j < actualLen && nums[j] <= nums.length + nums[i] - 1) j++;
            ans = Math.min(ans, nums.length - (j - i));
        }

        return ans;
    }



    //代码不如slidewindow 简洁
    //Runtime: 77 ms, faster than 57.58% of Java online submissions for Minimum Number of Operations to Make Array Continuous.
    //Memory Usage: 60.2 MB, less than 93.94% of Java online submissions for Minimum Number of Operations to Make Array Continuous.
    //Binary Search
    //Time: O(N*lgN + N + N*lgN); Space:O(1);
    public int minOperations(int[] nums) {
        if(nums.length == 1) return 0;

        //Time: O(N*lgN)
        Arrays.sort(nums);

        //Time: O(N)
        //unique
        int uniqueLen = 1;
        for(int i = 1; i < nums.length; i++)
            if (nums[i] != nums[i-1]) nums[uniqueLen++] = nums[i];

        //Time: O(N*lgN)
        int res = nums.length + 1;
        for (int i = 0; i < uniqueLen; i++){
            if (i >= res) break;
            int right = Math.min(uniqueLen - 1, binarySearchInsert(nums, i, uniqueLen, nums[i] + nums.length - 1));
            res = Math.min(res, nums.length - ( right - i + 1));
        }
        return res;
    }

    //Time: O(lgN)
    public int binarySearchInsert(int[] nums, int from, int to, int target){
        while (from <= to){
            int mid = (from + to) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) to = mid - 1;
            else from = mid + 1;
        }
        return to; //to
    }


    ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////
    //Runtime: 42 ms, faster than 96.97% of Java online submissions for Minimum Number of Operations to Make Array Continuous.
    //Memory Usage: 60 MB, less than 95.15% of Java online submissions for Minimum Number of Operations to Make Array Continuous.
    //Slide window
    //Time: O(N*lgN + 2N); Space:O(1);
    public int minOperations_2(int[] nums) {
        if(nums.length == 1) return 0;
        //Time: O(N*lgN)
        Arrays.sort(nums);

        //Time: O(N)
        //unique
        int UniqueLen = 1;
        for(int i = 1; i < nums.length; i++)
            if (nums[i] != nums[i-1]) nums[UniqueLen++] = nums[i];

        int maxWinSize = 0, numCount = 0;
        int left = 0, right = 0;

        //Time: worst case: O(2N).
        //Left & right pointer visited every number once respectively.
        while (right < UniqueLen){
            maxWinSize = Math.max(maxWinSize, ++numCount);
            right++;

            //move left
            while ((right < UniqueLen && nums[right] - nums[left] >= nums.length)) {
                numCount--;
                left++;
            }
        }
        return nums.length - maxWinSize;
    }

//
//    public int minOperations_2(int[] nums) {
//        if(nums.length == 1) return 0;
//        //Time: O(N*lgN)
//        Arrays.sort(nums);
//
//        int maxWinSize = 0, numCount = 0;
//        int left = 0, right = 0;
//
//        //Time: worst case: O(2N).
//        //Left & right pointer visited every number once respectively.
//        while (right < nums.length){
//            if ((right - 1 >=0 && nums[right] != nums[right - 1])
//                    || right == 0) numCount++;   //skip duplicated numbers
//            right++;
//            maxWinSize = Math.max(maxWinSize, numCount);
//
//            //move left
//            while (right < nums.length && nums[right] - nums[left] >= nums.length) {
//                if ((left - 1 >= 0 && nums[left] != nums[left - 1])
//                        || left == 0) numCount--;  //skip duplicated numbers
//                left++;
//            }
//        }
//        return nums.length - maxWinSize;
//    }

    ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////
    //Runtime: 135 ms, faster than 35.76% of Java online submissions for Minimum Number of Operations to Make Array Continuous.
    //Memory Usage: 121.5 MB, less than 6.06% of Java online submissions for Minimum Number of Operations to Make Array Continuous.
    //slide window + hashmap
    //Time: O(N* lgN + 2N) ; Space: O(N)
    //N is the length of array. L is the
    public int minOperations_1(int[] nums) {
        if(nums.length == 1) return 0;
        //Time: O(N*lgN)
        Arrays.sort(nums);

        int maxWinSize = 0;
        int left = 0, right = 0;

        Map<Integer, Integer> seen = new HashMap<>();
        while (right < nums.length){

            seen.put(nums[right], seen.getOrDefault(nums[right++], 0) + 1);
            maxWinSize = Math.max(maxWinSize, seen.keySet().size());

            //move left pointer
            while (right < nums.length && nums[right] - nums[left] >= nums.length) {
                int c = seen.get(nums[left]) - 1;
                if (c <= 0) seen.remove(nums[left]);
                else seen.put(nums[left], c);
                left++;
            }
        }
        return nums.length - maxWinSize;
    }

}
