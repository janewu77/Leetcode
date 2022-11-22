package LeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * You must write an algorithm that runs in O(n) time.
 *
 * Example 1:
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 *
 * Example 2:
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 *
 * Constraints:
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 */

/**
 * M [耗时45m]
 *  - 题有O(n)的要求，则不能排序。 则考虑用数学方式
 *  - 利用：set的唯一性、 顺序是+1
 *
 */
public class N128MLongestConsecutiveSequence {

    public static void main(String[] args){
        int[] d1 = {100,4,200,1,3,2};
        doRun(4,d1);

        int[] d2 = {0,3,7,2,5,8,4,6,0,1};
        doRun(9,d2);

        int[] d3 = {0,0};
        doRun(1,d3);

        int[] d4 = {0,0,-1};
        doRun(2,d4);

        int[] d5 = {8};
        doRun(1,d5);

    }

    private static void doRun(int expteced, int[] nums){
        int res = new N128MLongestConsecutiveSequence().longestConsecutive(nums);
        System.out.println("["+(expteced == res)+"]expected "+expteced+", result:"+ res);
    }

    //////////////////////////////////////////////////////////////
    //Runtime: 23 ms, faster than 87.86% of Java online submissions for Longest Consecutive Sequence.
    //Memory Usage: 60.8 MB, less than 85.84% of Java online submissions for Longest Consecutive Sequence.
    //Time: O(N); Space: O(N)
    public int longestConsecutive(int[] nums) {
        if( nums.length <= 1 ) return nums.length;

        Set<Integer> set = new HashSet<>();
        for (int n: nums) set.add(n);

        int result = 1;
        for (int key: set){
            //先找到序列中最大的。然后倒数计数。
            if (set.contains(key + 1)) continue;
            int count = 1;
            while (set.contains(--key)) count++; //倒数计数
//            result = Math.max(result, count);
            result = count > result ? count: result;
        }
        return result;
    }


    ////////////////////////////////////////////////////////////////////////////////
    //逻辑写得太复杂了！
    public int longestConsecutive1(int[] nums) {
        if( nums.length <= 1 ) return nums.length;

        Map<Integer, Integer> data = new HashMap<>();
        for(int n: nums)
            if (!data.containsKey(n))
                data.put(n, 0);

        for(int key: nums){
            if (data.containsKey(key)) {
                if (data.get(key) == 0)
                    data.put(key, data.get(key) + 1);

                int newKey = key - 1;
                while (data.containsKey(newKey)) {
                    data.put(key, data.get(key) + Math.max(data.get(newKey),1));
                    data.remove(newKey);
                    newKey--;
                }
            }
        }

        int result = 0;
        for(Integer k : data.keySet())
            result = Math.max(data.get(k), result);
        return result;
    }
}
