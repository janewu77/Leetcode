package LeetCode;


import java.util.*;

import static java.time.LocalTime.now;


/**
 *
 * You are given an array nums that consists of non-negative integers.
 * Let us define rev(x) as the reverse of the non-negative integer x.
 * For example, rev(123) = 321, and rev(120) = 21. A pair of indices (i, j)
 * is nice if it satisfies all of the following conditions:
 *
 * 0 <= i < j < nums.length
 * nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
 * Return the number of nice pairs of indices. Since that number can be too large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [42,11,1,97]
 * Output: 2
 * Explanation: The two pairs are:
 *  - (0,3) : 42 + rev(97) = 42 + 79 = 121, 97 + rev(42) = 97 + 24 = 121.
 *  - (1,2) : 11 + rev(1) = 11 + 1 = 12, 1 + rev(11) = 1 + 11 = 12.
 * Example 2:
 *
 * Input: nums = [13,10,35,24,76]
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 109
 */

/**
 * M - [time: 30-
 *
 */
public class N1814MCountNicePairsinanArray {

    public static void main(String[] args) {

        System.out.println(now());

        doRun_rev(32, 23);
        doRun_rev(1, 100);
        doRun_rev(2, 20);
        doRun_rev(87, 7800);
        doRun_rev(5, 5);
        doRun_rev(54321, 12345);
        System.out.println("==================");

        int[] data;
        int[][] data2;

        data = new int[]{7};
        doRun(0, data);

        data = new int[]{42,11,1,97};
        doRun(2, data);

        data = new int[]{13,10,35,24,76};
        doRun(4, data);

        data = new int[]{121, 11};
        doRun(1, data);

        data = new int[]{11, 2, 3};
        doRun(3, data);

        data = new int[]{352171103,442454244,42644624,152727101,413370302,293999243};
        doRun(2, data);


        data = new int[]{1, 4567854};
        doRun(0, data);

        //[]
        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, int[] nums) {
        int res = new N1814MCountNicePairsinanArray().countNicePairs(nums);
//        String res = comm.toString(res1);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
//        System.out.println("==================");
    }

    static private void doRun_rev(int expect,int nums) {
        int res = new N1814MCountNicePairsinanArray().rev(nums);
//        String res = comm.toString(res1);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
//        System.out.println("==================");
    }


    // nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
    // nums[i] - rev(nums[i]) == nums[j] - rev(nums[j])
    //
    //Runtime: 55 ms, faster than 78.24% of Java online submissions for Count Nice Pairs in an Array.
    //Memory Usage: 76.3 MB, less than 59.72% of Java online submissions for Count Nice Pairs in an Array.
    //Time : O(N); Space: O(N)
    private int MODULO = 1_000_000_007;
    public int countNicePairs(int[] nums) {
        long res = 0;

        //Space: O(N)
        Map<Integer, Integer> map = new HashMap<>();

        //Time : O(N);
        for (int num: nums){
            int diff = num - rev(num);
            int count = map.getOrDefault(diff,0);
            map.put(diff, (count + 1) % MODULO);
            res += count;
        }
        return (int) (res % MODULO);
    }

    //Time : O(lgNUM);
    private int rev(int n) {
        long res = 0;
        while (n > 0){
            res = res * 10 + n % 10;
            n /= 10;
        }
        return (int) (res % MODULO);
    }


    //Runtime: 115 ms, faster than 20.14% of Java online submissions for Count Nice Pairs in an Array.
    //Memory Usage: 123 MB, less than 5.55% of Java online submissions for Count Nice Pairs in an Array.
    public int rev_1(int n) {
        String str = String.valueOf(n);
        char[] chars = str.toCharArray();
        for(int i = 0; i < chars.length / 2; i++){
            char t = chars[i];
            chars[i] = chars[chars.length - 1 - i];
            chars[chars.length-1 - i] = t;
        }
        return Integer.valueOf(String.valueOf(chars));
    }

}
