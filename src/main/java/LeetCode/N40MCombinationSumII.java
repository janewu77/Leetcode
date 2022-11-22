package LeetCode;


import utils.comm;

import java.util.*;

import static java.time.LocalTime.now;

/**
 *
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sum to target.
 *
 * Each number in candidates may only be used once in the combination.
 * Note: The solution set must not contain duplicate combinations.
 *
 *
 *
 * Example 1:
 *
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Output:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 *
 *
 * Example 2:
 *
 * Input: candidates = [2,5,2,1,2], target = 5
 * Output:
 * [
 * [1,2,2],
 * [5]
 * ]
 *
 *
 * Constraints:
 *
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 */

/**
 * M - [time: 120+
 * - 算和、数字本身有重复、数字只能被使用一次； 结果是 组合，组合不可重复。
 *
 */
public class N40MCombinationSumII {

    public static void main(String[] args) {

        System.out.println(now());
        int[] candidates;

//        candidates  = new int[]{1,2,3,10};
//        doRun("[[1, 2, 3]]",candidates,6);

        candidates  = new int[]{10,1,2,7,6,1,5};
        doRun("[[1, 1, 6],[1, 2, 5],[1, 7],[2, 6]]",candidates,8);

        candidates  = new int[]{2,5,2,1,2,1};
        doRun("[[1, 2, 2],[5]]",candidates,5);

        //candidates  = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        candidates  = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        doRun("[[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]]",candidates,13);

        candidates  = new int[]{1,1};
        doRun("[[1]]",candidates,1);

        candidates  = new int[]{4,4,2,1,4,2,2,1,3};
        doRun("[[1, 1, 2, 2],[1, 1, 4],[1, 2, 3],[2, 2, 2],[2, 4]]",candidates,6);


        System.out.println(now());
    }
    static private void doRun(String expect, int[] candidates,  int target) {
        List<List<Integer>> res1 = new N40MCombinationSumII().combinationSum2(candidates, target);
        String res = comm.toString(res1);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    /////////////////////////////////////////////////////////////////////
    //把原本的candidates。转成了 数字与其个数。
    //把问题转成了，用数字来组合成结果。而不再是用子数组加。
    //backtracking - counter table
    //Runtime: 5 ms, faster than 74.10% of Java online submissions for Combination Sum II.
    //Memory Usage: 44.7 MB, less than 13.50% of Java online submissions for Combination Sum II.
    //Backtracking with Counters
    //Time: O(N + 2的N次方), N个数字的排列组合; Space: O(N)
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        //Time：O（N）
        Map<Integer, Integer> countTable = new HashMap<>(); //num: count
        for(int n: candidates)
            countTable.put(n, countTable.getOrDefault(n, 0) + 1);

        List<int[]> counterList = new ArrayList<>();
        countTable.forEach((key, value) -> counterList.add(new int[]{key, value}));

        List<List<Integer>> res = new ArrayList<>();
        backtracking_2_helper(counterList, target, 0, res, new ArrayList<>());
        return res;
    }

    private void backtracking_2_helper(List<int[]> counterList, int target, int begin,
                                       List<List<Integer>> res, ArrayList<Integer> list){
        if (target == 0){
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = begin; i < counterList.size(); i++){
            int[] data = counterList.get(i);
            if (data[1] == 0) continue;

            int n = data[0];
            if (target - n >= 0){
                data[1]--;
                list.add(n);
                //这里用i,不用i+1，是因为，数字可能有多个。
                backtracking_2_helper(counterList, target - n, i, res, list);
                //list.removeLast();
                list.remove(list.size() - 1);
                data[1]++;
            }
        }
    }


    //////////////////////////////////////////////////////////////////////
    //这个是拿n39过来改的。
    //Runtime: 2 ms, faster than 99.92% of Java online submissions for Combination Sum II.
    //Memory Usage: 43.1 MB, less than 84.68% of Java online submissions for Combination Sum II.
    //Backtracking with Index
    //Time: O(NlogN + 2的N次方) ; Space: O(T) 给list用的。
    public List<List<Integer>> combinationSum2_1(int[] candidates, int target) {
        //Time : O(NlogN)
        Arrays.sort(candidates);

        List<List<Integer>> res = new ArrayList<>();
        backtracking_1_helper(candidates, target, 0, res, new ArrayList<>());
        return res;
    }

    private void backtracking_1_helper(int[] candidates, int target, int begin,
                                       List<List<Integer>> res, ArrayList<Integer> list){
        if (target == 0 ){
            res.add(new ArrayList<>(list));
            return;
        }
        //Time:O(2的N次方)
        for (int i = begin; i < candidates.length; i++){

            // skip duplicates!!!!
            if (i > begin && candidates[i] == candidates[i - 1])
                continue;

            if (target - candidates[i] >= 0) {
                list.add(candidates[i]);
                backtracking_1_helper(candidates, target - candidates[i], i + 1, res, list);
                //list.removeLast();
                list.remove(list.size() - 1);
            }else break;
        }
    }


}
