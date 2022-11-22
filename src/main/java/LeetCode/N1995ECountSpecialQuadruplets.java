package LeetCode;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.LocalTime.now;

/**
 * Given a 0-indexed integer array nums, return the number of distinct quadruplets (a, b, c, d) such that:
 *
 * nums[a] + nums[b] + nums[c] == nums[d], and
 * a < b < c < d
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,6]
 * Output: 1
 * Explanation: The only quadruplet that satisfies the requirement is (0, 1, 2, 3) because 1 + 2 + 3 == 6.
 * Example 2:
 *
 * Input: nums = [3,3,6,4,5]
 * Output: 0
 * Explanation: There are no such quadruplets in [3,3,6,4,5].
 * Example 3:
 *
 * Input: nums = [1,1,1,3,5]
 * Output: 4
 * Explanation: The 4 quadruplets that satisfy the requirement are:
 * - (0, 1, 2, 3): 1 + 1 + 1 == 3
 * - (0, 1, 3, 4): 1 + 1 + 3 == 5
 * - (0, 2, 3, 4): 1 + 1 + 3 == 5
 * - (1, 2, 3, 4): 1 + 1 + 3 == 5
 *
 *
 * Constraints:
 *
 * 4 <= nums.length <= 50
 * 1 <= nums[i] <= 100
 */
public class N1995ECountSpecialQuadruplets {


    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;


        doRun(4, new int[]{1,1,1,3,5});
        doRun(1, new int[]{1,2,3,6});
        doRun(0, new int[]{3,3,6,4,5});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[] nums) {
        int res = new N1995ECountSpecialQuadruplets().countQuadruplets(nums);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //3.nums[a] + nums[b] == nums[d] - nums[c]
    // nums[a] + nums[b] + nums[c] == nums[d]
    // a < b < c < d
    //Runtime: 14 ms, faster than 93.79% of Java online submissions for Count Special Quadruplets.
    //Memory Usage: 42.4 MB, less than 40.69% of Java online submissions for Count Special Quadruplets.
    //Time: O(N^2); Space: O(N^2)
    public int countQuadruplets(int[] nums) {
        int res = 0;
        Map<Integer, List<Integer>> map = new HashMap<>(); //Sum : i2

        for (int i1 = 0; i1 < nums.length - 3; i1++){
            for (int i2 = i1 + 1; i2 < nums.length - 2; i2++){
                int sum = nums[i1] + nums[i2];
                List<Integer> list = map.getOrDefault(sum, new ArrayList<>());
                map.put(sum, list);
                list.add(i2);
            }
        }

        for (int i4 = nums.length - 1; i4 > 2; i4--){
            for (int i3 = i4 - 1; i3 > 1; i3--){
                int sum = nums[i4] - nums[i3];
                if (map.containsKey(sum)) {
                    List<Integer> list = map.get(sum);
                    for (int i2 : list) if (i2 < i3) res++;
                }
            }
        }
        return res;
    }

    //2.HashMap
    //Runtime: 52 ms, faster than 9.65% of Java online submissions for Count Special Quadruplets.
    //Memory Usage: 53.9 MB, less than 5.06% of Java online submissions for Count Special Quadruplets.
    //Time: O(N^3); Space: O(N)
        public int countQuadruplets_2(int[] nums) {
            int res = 0;
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int i = 0; i <nums.length; i++){
                List<Integer> list = map.getOrDefault(nums[i], new ArrayList<>());
                map.put(nums[i], list);
                list.add(i);
            }
            for (int i1 = 0; i1<nums.length - 3; i1++) {
                for (int i2 = i1 + 1; i2 < nums.length - 2; i2++) {
                    for (int i3 = i2 + 1; i3 < nums.length - 1; i3++) {
                        int sum = nums[i1] + nums[i2] + nums[i3];
                        if (map.containsKey(sum)) {
                            List<Integer> list = map.get(sum);
                            for (int i4 : list) if (i4 > i3) res++;
                        }
                    }
                }
            }
            return res;
        }


    //1.brute force
    //Runtime: 20 ms, faster than 73.10% of Java online submissions for Count Special Quadruplets.
    //Memory Usage: 42.4 MB, less than 40.69% of Java online submissions for Count Special Quadruplets.
    //Time: O(N^4); Space: O(1)
    public int countQuadruplets_1(int[] nums) {
        int res = 0;
        for (int i1 = 0; i1<nums.length - 3; i1++)
            for (int i2 = i1 + 1; i2 < nums.length - 2; i2++)
                for (int i3 = i2 + 1; i3 <nums.length - 1; i3++)
                    for (int i4 = i3 + 1; i4 <nums.length; i4++)
                        if (nums[i1] + nums[i2] + nums[i3] == nums[i4]) res++;
        return res;
    }
}
