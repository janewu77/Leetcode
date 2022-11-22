package LeetCode;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

/**
 *
 * * Given an array of integers nums and an integer target, return indices of the two numbers such
 * * that they add up to target.
 * * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * * You can return the answer in any order.
 *
 * Example 1:
 *
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Output: Because nums[0] + nums[1] == 9, we return [0, 1].
 * Example 2:
 *
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * Example 3:
 *
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 103
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * Only one valid answer exists.
 *
 * */

/*
E：基本数据结构 HashMap 的应用
 */

public class N1ETwoSum {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun("1,2", new int[]{3,2,4}, 6);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(String expect, int[] nums, int target) {
        int[] res1 = new N1ETwoSum().twoSum(nums, target);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 4 ms, faster than 84.70% of Java online submissions for Two Sum.
    //Memory Usage: 45.6 MB, less than 36.27% of Java online submissions for Two Sum.
    // HashMap + one pass
    // Time: O(N; Space:O(N)
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i)
                return new int[] {i, map.get(complement)};
            map.put(nums[i], i);
        }
        return null;
    }

    //Runtime: 4 ms, faster than 84.70% of Java online submissions for Two Sum.
    //Memory Usage: 43 MB, less than 86.50% of Java online submissions for Two Sum.
    // HashMap + two pass
    // Time: O(N + N); Space:O(N)
    public int[] twoSum_1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) map.put(nums[i], i);

        for (int i = 0; i < nums.length; i++){
           int complement = target - nums[i];
           if (map.containsKey(complement) && map.get(complement) != i)
                return new int[] {i, map.get(complement)};
        }
        return null;
    }

    //Runtime: 58 ms, faster than 42.72% of Java online submissions for Two Sum.
    //Memory Usage: 45.4 MB, less than 42.92% of Java online submissions for Two Sum.
    // brute force
    // Time: O(N * N); Space:O(1)
    public int[] twoSum_brute(int[] nums, int target) {
        for(int i = 0; i < nums.length; i++)
            for(int j = i + 1; j < nums.length; j++)
                if(target == nums[i] + nums[j])
                    return new int[]{i,j};
        return null;
    }
}
