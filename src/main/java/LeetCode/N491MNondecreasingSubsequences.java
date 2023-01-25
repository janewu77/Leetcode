package LeetCode;


import java.util.*;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

/**
 * Given an integer array nums, return all the different possible non-decreasing subsequences of the given array with at least two elements. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,6,7,7]
 * Output: [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
 * Example 2:
 *
 * Input: nums = [4,4,3,2,1]
 * Output: [[4,4]]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 15
 * -100 <= nums[i] <= 100
 */
public class N491MNondecreasingSubsequences {


    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");
        String expect;


        expect = "[[4, 6],[4, 7],[6, 7],[4, 6, 7],[7, 7],[4, 7, 7],[6, 7, 7],[4, 6, 7, 7]]";
        doRun(expect, new int[]{4,6,7,7});

        expect = "[[4, 4]]";
        doRun(expect, new int[]{4,4,3,2,1});

        expect = "[[1]]";
        doRun(expect, new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15});

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(String expect, int[] nums) {
        List<List<Integer>>  res1 = new N491MNondecreasingSubsequences().findSubsequences(nums);
        String res = "["+res1.stream().map(x->x.toString()).collect(Collectors.joining(","))+"]";
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    //3.backtracking
    //Runtime: 9ms 68%; Space: 48MB 73%
    //Time: O( (2 ^ N) * N); Space: O(2 ^ N * N)
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> set = new HashSet<>();
        helper_backtracking(nums, 0, new ArrayList<>(), set);
        return new ArrayList<>(set);
    }
    private void helper_backtracking(int[] nums, int idx, List<Integer> list, Set<List<Integer>> result){
        if (idx == nums.length) {
            if (list.size() >= 2) result.add(new ArrayList<>(list));
            return;
        }

        if (list.isEmpty() || nums[idx] >= list.get(list.size() - 1)) {
            list.add(nums[idx]);
            helper_backtracking(nums, idx + 1, list, result);
            list.remove(list.size() - 1);
        }
        if (list.isEmpty() || nums[idx] != list.get(list.size() - 1))
            helper_backtracking(nums, idx + 1, list, result);
    }


    //2.List
    //Runtime: 20ms 40%; Space: 50.5MB; 52%
    //Time: O(N * (2^N) * N); Space: O(2 ^ N * N)
    public List<List<Integer>> findSubsequences_2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(nums[i]);

            Set<List<Integer>> set2 = new HashSet<>();
            for (List<Integer> oldList : set) {
                if (nums[i] < oldList.get(oldList.size() - 1)) continue;
                List<Integer> list2 = new ArrayList<>(oldList);//O(N)
                list2.add(nums[i]);
                if (set2.add(list2)) res.add(list2);
            }
            set.add(list);
            set.addAll(set2);
        }
        return res;
    }


    //1.bitmask
    //Runtime: 68ms 7%; Memory: 50.2MB 54.9%
    //Time: O(2^N * N); Space:O(2^N * N)
    public List<List<Integer>> findSubsequences_1(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();

        int maxMask = 1 << nums.length;
        for (int i = 1; i < maxMask; i++) {
            int bitMask = i, lastIdx = -1;
            List<Integer> list = new ArrayList<>();
            for (int idx = 0; bitMask > 0; idx++){
                if ((bitMask & 1) == 1 && (lastIdx == -1 || nums[idx] >= nums[lastIdx] )) {
                    list.add(nums[idx]);
                    lastIdx = idx;
                }
                bitMask >>= 1;
            }
            if (list.size() >= 2) res.add(list);
        }
        return new ArrayList<>(res);
    }
}
