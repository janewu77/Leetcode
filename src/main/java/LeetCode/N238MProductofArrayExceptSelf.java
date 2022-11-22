package LeetCode;

/**
 * Given an integer array nums, return an array answer such that answer[i]
 * is equal to the product of all the elements of nums except nums[i].
 *
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 *
 *
 *
 * Example 1:
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 *
 *
 * Example 2:
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 105
 * -30 <= nums[i] <= 30
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 *
 * Follow up: Can you solve the problem in O(1) extra space complexity?
 * (The output array does not count as extra space for space complexity analysis.)
 *
 */

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * M - [time: 30-]
 *  - 注意题目，不能用除。
 *  - 当计算太复杂时，分二次进行，易写不易错。
 */
public class N238MProductofArrayExceptSelf {

    public static void main(String[] args){
        int[] data;
        //0, 0, 9, 0, 0
        data = new int[]{-1,1,0,-3,3};
        doRun("0,0,9,0,0", data);
        data = new int[]{1,2,3,4,5};
        doRun("120,60,40,30,24", data);

        data = new int[]{1,2,3,4};
        doRun("24,12,8,6", data);
    }

    private static void doRun(String expected, int[] nums){
        int[] res = new N238MProductofArrayExceptSelf().productExceptSelf(nums);

        String res2 = Arrays.stream(res).mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(","));
        System.out.println("["+ expected.equals(res2) + "]expected:"+expected+".res:"+res2);
    }

    //Runtime: 2 ms, faster than 88.64% of Java online submissions for Product of Array Except Self.
    //Memory Usage: 57.6 MB, less than 61.63% of Java online submissions for Product of Array Except Self.
    //往右和往右，进行累乘。
    //与 productExceptSelf_3 雷同，分了左右做二遍，但代码更简洁易懂
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i = 1; i < nums.length; i++) res[i] = res[i-1] * nums[i-1];

        int right = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] = res[i] * right;
            right *= nums[i];
        }
        return res;
    }


    //Runtime: 3 ms, faster than 53.83% of Java online submissions for Product of Array Except Self.
    //Memory Usage: 50.1 MB, less than 98.04% of Java online submissions for Product of Array Except Self.
    //time: O(N); Space: O(1)
    //one pass
    public int[] productExceptSelf_3(int[] nums) {
        int[] res = new int[nums.length];
        int left = res[0] = 1;
        int right = res[res.length-1] = 1;

        for (int i = 0; i < nums.length - 1; i++) {
            int idx = nums.length - 2 - i;

            left = left * nums[i];
            right = right * nums[idx + 1];

            if (idx > i + 1) {
                res[i + 1] = left;
                res[idx] = right;
            } else if (idx == i + 1) {
                res[i + 1] = left * right ;
            } else {
                res[i + 1] = left * res[i + 1];
                res[idx] = right * res[idx];
            }
        }
        return res;
    }


    //Runtime: 3 ms, faster than 53.83% of Java online submissions for Product of Array Except Self.
    //Memory Usage: 50 MB, less than 99.08% of Java online submissions for Product of Array Except Self.
    //time: O(N); Space: O(N)
    public int[] productExceptSelf_2(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(res, 1);

        int[] right = new int[nums.length];
        Arrays.fill(right, 1);

        for (int i = 0; i < nums.length - 1; i++){
            res[i + 1] = res[i] * nums[i];
            int idx = nums.length - 2 - i;
            right[idx] = right[idx + 1] * nums[idx + 1];
        }

        for (int i = 0; i < nums.length; i++){
            res[i] = res[i] * right[i];
        }
        return res;
    }

    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Product of Array Except Self.
    //Memory Usage: 50.5 MB, less than 91.57% of Java online submissions for Product of Array Except Self.
    //time: O(N); Space: O(1)
    //不能用除，所以，这个不能算解法
    public int[] productExceptSelf_division(int[] nums) {
        int[] res = new int[nums.length];

        int product = 1;
        int k = 0;
        int idx = -1;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == 0) {
                k++; idx = i;
            } else product *= nums[i];
        }

        if (k <= 0) {
            for (int i = 0; i < nums.length; i++)
                if (nums[i] != 0) res[i] = product / nums[i];
        }else if (k == 1) {
            res[idx] = product;
        }
        return res;
    }
}
