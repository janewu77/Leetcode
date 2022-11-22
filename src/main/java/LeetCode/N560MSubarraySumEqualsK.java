package LeetCode;

import java.util.HashMap;
import java.util.Map;


/**
 * Given an array of integers nums and an integer k,
 * return the total number of subarrays whose sum equals to k.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 *
 *
 * Example 1:
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 *
 *
 * Example 2:
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 */

/**
 * M - 【time:60+]
 *  - brute force是做出来了。
 *  - 但进一步优化，需要想到sum的点。 二个sum差值若等于target，则意味着这二个sum之间在数，加起来会是target.
 *
 */
public class  N560MSubarraySumEqualsK {

    public static void main(String[] args) {
        int[] matrix;
        int target;

        matrix = new int[]{904};
        target = 0;
        doRun(0, matrix, target);

        matrix = new int[]{1,1,1};
        target = 2;
        doRun(2, matrix, target);

        matrix = new int[]{1,2,1,2,1};
        target = 3;
        doRun(4, matrix, target);

//        matrix = new int[]{0,0};
//        target = 0;
//        doRun(3, matrix, target);
//
//        System.out.println(now());
//        matrix = new int[]{0,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,0,1,1,1,0,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0,1,0,0,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,1,0,1,1,1,1,1,1,0,1,1,0,0,1,1,1,1,0,0,1,1,0,1,1,1,0,1,0,1,1,1,1,0,1,1,1,1,1,1,0,1,1,0,1,0,0,1,0,0,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,0,1,0,1,1,1,1,0,0,0,1,1,1,0,1,1,1,0,0,0,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1,0,1,0,1,1,0,1,1,1,1,0,1,1,0,0,1,1,1,1,0,1,1,1,0,0,1,1,0,0,0,1,1,0,1,1,0,0,1,1,1,1,1,0,0,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,0,1,0,0,0,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1,0,1,0,1,1,1,1,1,1,0,0,1,1,0,1,1,0,0,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,1,0,1,0,1,0,1,0,0,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,0,1,1,1,1,1,1,1,0,0,1,0,0,1,0,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,0,1,1,0,1,1,1,0,1,1,1,0,1,1,1,1,1,1,0,0,1,0,1,1,1,1,1,0,1,0,0,1,0,0,0,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,0,1,0,1,1,1,1,1,0,1,1,0,1,0,0,1,0,1,1,1,1,1,1,1,1,1,0,0,1,0,1,1,1,1,1,1,0,1,1,0,0,1,1,1,0,1,1,1,1,1,1,0,1,0,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,1,0,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,0,0,1,1,0,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,0,1,0,1,0,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,0,1,1,0,1,0,0,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,0,0,1,0,1,1,1,1,1,1,1,1,1,0,1,0,1,1,1,1,1,0,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,1,1,1,1,1,1,0,1,1,0,0,1,1,1,1,1,1,1,1,1,1,0,1,0,1,0,1};
//        target= 143;
//        doRun(3, matrix, target);
//        System.out.println(now());

//        matrix = new int[]{1,2,3};
//        target = 3;
//        doRun(2, matrix, target);
    }



    private static int c = 1;
    private static void doRun(int expected, int[] matrix, int target){
        int res = new N560MSubarraySumEqualsK().subarraySum(matrix, target);
        System.out.println("[" + (expected ==res) +"] "+(c++)+ ".result: "+ res + ".expected:"+expected);
    }

    //Runtime: 21 ms, faster than 97.85% of Java online submissions for Subarray Sum Equals K.
    //Memory Usage: 46.8 MB, less than 86.80% of Java online submissions for Subarray Sum Equals K.
    //利用sum的特性：如果二个sum之间正好差k,说明这二个之间加起来等于k.
    //time: O(n); space :  O(n);
    public int subarraySum(int[] nums, int k) {
        if(nums.length == 1) return nums[0] == k? 1:0;

        int count = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); //给数值正好是k，预留的。
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }


    //	Time Limit Exceeded
    public int subarraySum1(int[] nums, int k) {
        if(nums.length == 1) return nums[0] == k? 1:0;

        int res = 0;
        for(int i = 0; i < nums.length; i++){
            for(int step = i; step < nums.length; step++){
                if(i == 0 && step  ==0 ) {
                    if (nums[0] == k) res++;
                    continue;
                }

                nums[step] = nums[step] + (i == 0? nums[step-1]: -nums[i-1]);
                if (nums[step] == k) res++;
            }
        }
        return res;
    }
}
