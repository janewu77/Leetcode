package Contest;

import java.util.*;

import static java.time.LocalTime.now;

/**
 * You are given a 0-indexed, strictly increasing integer array nums and a positive integer diff.
 * A triplet (i, j, k) is an arithmetic triplet if the following conditions are met:
 *
 * i < j < k,
 * nums[j] - nums[i] == diff, and
 * nums[k] - nums[j] == diff.
 * Return the number of unique arithmetic triplets.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [0,1,4,6,7,10], diff = 3
 * Output: 2
 * Explanation:
 * (1, 2, 4) is an arithmetic triplet because both 7 - 4 == 3 and 4 - 1 == 3.
 * (2, 4, 5) is an arithmetic triplet because both 10 - 7 == 3 and 7 - 4 == 3.
 * Example 2:
 *
 * Input: nums = [4,5,6,7,8,9], diff = 2
 * Output: 2
 * Explanation:
 * (0, 2, 4) is an arithmetic triplet because both 8 - 6 == 2 and 6 - 4 == 2.
 * (1, 3, 5) is an arithmetic triplet because both 9 - 7 == 2 and 7 - 5 == 2.
 *
 *
 * Constraints:
 *
 * 3 <= nums.length <= 200
 * 0 <= nums[i] <= 200
 * 1 <= diff <= 50
 * nums is strictly increasing.
 *
 *
 */

/**
 * E - [time: 20+ 】
 *
 *
 */
//2367. Number of Arithmetic Triplets
public class N6136ENumberofArithmeticTriplets {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        data = new int[]{0,1,4,6,7,10};
        doRun(2, data ,3 );

        data = new int[]{4,5,6,7,8,9};
        doRun(2, data ,2 );
        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(long expect, int[] nums, int diff) {
        int res = new N6136ENumberofArithmeticTriplets().arithmeticTriplets(nums, diff);
//        String res = comm.toString(res1);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    ////////////////////////////////////////////////////////////
    //直接按等差，往前找。尽可能多地找。 数量 = count - k + 1
    //Runtime: 3 ms, faster than 66.67% of Java online submissions for Number of Arithmetic Triplets.
    //Memory Usage: 43.2 MB, less than 8.33% of Java online submissions for Number of Arithmetic Triplets.
    //可扩展版
    public int arithmeticTriplets(int[] nums, int diff) {
        int k = 3;
        int res = 0;

        Set<Integer> data = new HashSet<>();
        for (int n : nums) data.add(n);

        for (int i = nums.length - 1; i >= 2; i--){
            int value = nums[i];
            int count = 0;
            while (data.contains(value)){
                count++;
                data.remove(value);
                value -= diff;
            }
            res += Math.max(count - k + 1 , 0);
        }
        return res;
    }

    ////////////////////////////////////////////////////////////
    //网上看来改的。从前往后算。
    //Runtime: 1 ms, faster than 75.00% of Java online submissions for Number of Arithmetic Triplets.
    //Memory Usage: 41.5 MB, less than 66.67% of Java online submissions for Number of Arithmetic Triplets.
    //Time: O(N); Space: O(201)
    public int arithmeticTriplets_2(int[] nums, int diff) {
        boolean[] data = new boolean[201];

        int res = 0;
        for (int num : nums) {
            if (num >= (diff + diff) && data[num - diff] && data[num - (diff + diff)])
                res++;
            data[num] = true;
        }
        return res;
    }

    ////////////////////////////////////////////////////////////
    //都是写死的。扩展性并不好。
    //已经在优化过了。
    //Runtime: 2 ms, faster than 75.00% of Java online submissions for Number of Arithmetic Triplets.
    //Memory Usage: 42.2 MB, less than 25.00% of Java online submissions for Number of Arithmetic Triplets.
    //HashMap
    //Time: O(N); Space: O(N)
    public int arithmeticTriplets_1(int[] nums, int diff) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) set.add(nums[i]); //value: index

        int res = 0;
        //从后往前算
        for (int i = nums.length - 1; i >= 2; i--)
            if (set.contains(nums[i] - diff) && set.contains(nums[i] - diff - diff))
                res++;

        return res;
    }
}
