package Contest;

/**
 * You are given a 0-indexed integer array nums. In one operation you can replace any element
 * of the array with any two elements that sum to it.
 *
 * For example, consider nums = [5,6,7]. In one operation, we can replace nums[1] with 2 and 4 and convert nums to [5,2,4,7].
 * Return the minimum number of operations to make an array that is sorted in non-decreasing order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,9,3]
 * Output: 2
 * Explanation: Here are the steps to sort the array in non-decreasing order:
 * - From [3,9,3], replace the 9 with 3 and 6 so the array becomes [3,3,6,3]
 * - From [3,3,6,3], replace the 6 with 3 and 3 so the array becomes [3,3,3,3,3]
 * There are 2 steps to sort the array in non-decreasing order. Therefore, we return 2.
 *
 * Example 2:
 *
 * Input: nums = [1,2,3,4,5]
 * Output: 0
 * Explanation: The array is already in non-decreasing order. Therefore, we return 0.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 */



/**
 * //2366. Minimum Replacements to Sort the Array
 *
 * H - [time -
 *  - math
 *
 */

public class N6144HMinimumReplacementstoSorttheArray {


    public static void main(String[] args){
        int x = 2 % 7;
        System.out.println(x);

        int[] nums;

        //[3,3,3,3],[3,3,3],[3,4],[6,17]
        nums = new int[]{12,9,7,6,17};
        doRun(6, nums);

        nums = new int[]{3,9,3};
        doRun(2, nums);

    }
    static private void doRun(long expect, int[] nums) {
        long res = new N6144HMinimumReplacementstoSorttheArray().minimumReplacement(nums);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    // 由下面的代码优化而来
    public long minimumReplacement(int[] nums) {
        long res = 0;
        int prev = nums[nums.length -1];
        for (int i = nums.length - 2; i >= 0; i--){

            //以下二句等价
            int count = (nums[i] + prev - 1) / prev;
//            int count =  (int)Math.ceil(1.0 * nums[i] / prev);

            prev = nums[i] / count;
            res += count - 1;
        }
        return res;
    }

    //思路：
    //大数往前拆。 大数拆成小数时，按后数的倍数去拆。 比如 7｜2 拆成 1222｜2。 7至少要拆出4个数来，相当于要拆 3 次。
    // [A.count]count = 大数 / 小数 - 1 + 1。  -1：减去自身个数才是新增。  +1： 当有余数时，余数+1。
    // [B.新的头数如何计算？] 7｜6 ： 拆成 25|6 ; 不应该是16|6。 头数在条件允许的情况下，应尽量大。
    //Runtime: 4 ms, faster than 100.00% of Java online submissions for Minimum Replacements to Sort the Array.
    //Memory Usage: 81.7 MB, less than 28.57% of Java online submissions for Minimum Replacements to Sort the Array.
    //网上看来，修改的。
    public long minimumReplacement_2(int[] nums) {

        long res = 0;
        int prev = nums[nums.length - 1];

        //从后往前。从最大的数开始。
        for (int i = nums.length -2; i >= 0; i--){
//            if (nums[i] > prev) {
                int count = nums[i] / prev ;
                if (nums[i] % prev != 0) {
                    count++;
                    //重新计算头数。头数尽量大。
                    prev = nums[i] / count;
                }
                res += count - 1;
//            }else{
//                prev = nums[i];
//            }
        }
        return res;
    }


    //Runtime: 12 ms, faster than 14.29% of Java online submissions for Minimum Replacements to Sort the Array.
    //Memory Usage: 60.5 MB, less than 85.71% of Java online submissions for Minimum Replacements to Sort the Array.
    public long minimumReplacement_1(int[] nums) {

        long x = 1000000000, res = 0, k;
        for (int i = nums.length - 1; i >= 0; --i) {
            k = (nums[i] + x - 1) / x;
            x = nums[i] / k;
            res += k - 1;
        }
        return res;
    }
}
