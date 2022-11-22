package LeetCode;


import java.util.Arrays;

/**
 * Given an integer array nums sorted in non-decreasing order,
 * return an array of the squares of each number sorted in non-decreasing order.
 *
 *
 *
 * Example 1:
 * Input: nums = [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 * Explanation: After squaring, the array becomes [16,1,0,9,100].
 * After sorting, it becomes [0,1,9,16,100].
 *
 *
 * Example 2:
 * Input: nums = [-7,-3,2,3,11]
 * Output: [4,9,9,49,121]
 *
 * Constraints:
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums is sorted in non-decreasing order.
 *
 */

/**
 * 基本概念：排序
 *  - 二端同时开始计算，对计算结果按序从大到小、从后往前存放
 */

public class N977ESquaresOfASortedArray {

    public static void main(String[] args) {

        int[] nums = {-7,-3,2,3,11,13};
        doTest(nums);

        int[] nums2 = {-7};
        doTest(nums2);

        int[] nums3 = {-7,3};
        doTest(nums3);

        int[] nums4 = {-7,-3,2,3,11};
        doTest(nums4);

        int[] nums5 = {-5,-3,-2,-1};
        doTest(nums5);

    }
    private static void doTest(int[] nums){
        System.out.println("========");
        int[] res = (new N977ESquaresOfASortedArray()).sortedSquares(nums);
//        int[] res = (new N977ESquaresOfASortedArray()).sortedSquares_steam(nums);
        for(int i=0; i<res.length;i++){
            System.out.println(res[i]);
        }
    }

    // 二端同时开始计算，对计算结果按序从大到小、从后往前存放
    public int[] sortedSquares(int[] nums) {
        int[] results = new int[nums.length];
        int l=0, r = nums.length-1;
        int i = r;
        while (l <= r){
            int square_l = nums[l] * nums[l];
            int square_r = nums[r] * nums[r];
            if (square_l > square_r) {
                results[i--] = square_l;
                l++;
            }else{
                results[i--] = square_r;
                r--;
            }
        }
        return results;
    }


    // 计算完成之后，再排序
    // 影响原nums
    public int[] sortedSquares3(int[] nums) {
        int[] results = new int[nums.length];
        for (int i = 0; i< nums.length; i++){
            nums[i] *= nums[i];
        }

        int l=0, r = nums.length-1;
        int i = r;
        while (l <= r){
            if (nums[l] > nums[r]) {
                results[i--] = nums[l];
                l++;
            }else{
                results[i--] = nums[r];
                r--;
            }
        }
        return results;
    }

    //算完后，再调方法进行排序   n log n
    // 这里的Arrays.sort(nums) 性能应该不如 sortedSquares3内的排序
    // 6ms
    public int[] sortedSquares2(int[] nums) {
        for (int i = 0; i< nums.length; i++){
            nums[i] *= nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }

    // 21ms
    public int[] sortedSquares_steam(int[] nums) {
        return Arrays.stream(nums).map(i -> i*i).sorted().toArray();
    }
}
