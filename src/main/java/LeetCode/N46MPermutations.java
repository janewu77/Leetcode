package LeetCode;

import java.util.ArrayList;
import java.util.List;


/**
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * Example 2:
 *
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 * Example 3:
 *
 * Input: nums = [1]
 * Output: [[1]]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * All the integers of nums are unique.
 */

/**
 * M - [time: 10-  （熟悉的题）
 *
 *
 */

public class N46MPermutations {


    //Runtime: 2 ms, faster than 77.49% of Java online submissions for Permutations.
    //Memory Usage: 45 MB, less than 32.30% of Java online submissions for Permutations.
    //backtracking
    //Time: O(N*N); Space: O(N)
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        helper_backtracking(nums, res, new ArrayList<>());
        return res;
    }

    private void helper_backtracking(int[] nums, List<List<Integer>> res, List<Integer> list){

        if (list.size() == nums.length){
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i = 0; i< nums.length; i++){
            if (list.contains(nums[i])) continue;
            list.add(nums[i]);
            helper_backtracking(nums, res, list);
            list.remove(list.size()-1);
        }
    }
}
