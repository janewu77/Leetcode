package LeetCode;


import utils.comm;

import java.util.*;

import static java.time.LocalTime.now;

/**
 * Given an array of distinct integers candidates and a target integer target,
 * return a list of all unique combinations of candidates where the chosen numbers sum to target.
 * You may return the combinations in any order.
 *
 * The same number may be chosen from candidates an unlimited number of times.
 * Two combinations are unique if the frequency of at least one of the chosen numbers is different.
 *
 * It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
 *
 *
 *
 * Example 1:
 *
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation:
 * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 * 7 is a candidate, and 7 = 7.
 * These are the only two combinations.
 *
 *
 * Example 2:
 * Input: candidates = [2,3,5], target = 8
 * Output: [[2,2,2,2],[2,3,3],[3,5]]
 *
 *
 * Example 3:
 * Input: candidates = [2], target = 1
 * Output: []
 *
 *
 * Constraints:
 *
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * All elements of candidates are distinct.
 * 1 <= target <= 500
 */

/**
 * M - 【time：120+
 *  - dp & backtracking
 *
 */
public class N39MCombinationSum {


    public static void main(String[] args) {

        int[] data;
        System.out.println(now());

        data = new int[]{2,8};
        doRun("[[2, 2, 2, 2],[8]]",data,8);

        data = new int[]{2,3,5};
        doRun("[[2, 2, 2, 2],[2, 3, 3],[3, 5]]",data,8);

        data = new int[]{100,200,4,12};
        doRun("[]",data,400);

        data = new int[]{2,7,6,3,5,1};
        doRun("[[1, 1, 1, 1, 1, 1, 1, 1, 1],[1, 1, 1, 1, 1, 1, 1, 2],[1, 1, 1, 1, 1, 1, 3],[1, 1, 1, 1, 1, 2, 2],[1, 1, 1, 1, 2, 3],[1, 1, 1, 1, 5],[1, 1, 1, 2, 2, 2],[1, 1, 1, 3, 3],[1, 1, 1, 6],[1, 1, 2, 2, 3],[1, 1, 2, 5],[1, 1, 7],[1, 2, 2, 2, 2],[1, 2, 3, 3],[1, 2, 6],[1, 3, 5],[2, 2, 2, 3],[2, 2, 5],[2, 7],[3, 3, 3],[3, 6]]",
                data,9);
        doRun("[[1, 1, 7],[1, 1, 1, 6],[1, 1, 1, 1, 5],[1, 1, 1, 1, 1, 1, 3],[1, 1, 1, 1, 1, 1, 1, 2],[1, 1, 1, 1, 1, 1, 1, 1, 1]," +
                "[1, 1, 1, 1, 1, 2, 2],[1, 2, 6],[1, 1, 2, 2, 3],[1, 1, 1, 2, 2, 2],[1, 2, 2, 2, 2]," +
                "[1, 3, 5],[1, 2, 3, 3],[2, 7]," +
                "[1, 1, 1, 1, 2, 3],[1, 1, 1, 3, 3]," +//???
                "[2, 2, 5],[2, 2, 2, 3],[3, 6],[3, 3, 3],[1, 1, 2, 5]",data,9);


        data = new int[]{8,10,7,6,12,9};
        doRun("[[8, 8, 9],[7, 8, 10],[6, 9, 10],[6, 6, 6, 7],[6, 7, 12],[7, 9, 9]]",data,25);

        data = new int[]{3,12,9,11,6,7,8,5,4};
        doRun("[[3, 3, 3, 3, 3],[3, 3, 3, 6],[3, 3, 9],[3, 3, 4, 5],[3, 4, 4, 4],[3, 4, 8],[3, 5, 7],[3, 6, 6],[3, 12],[4, 4, 7],[4, 5, 6],[4, 11],[5, 5, 5],[6, 9],[7, 8]]",data,15);

        System.out.println(now());
    }

    static private void doRun(String expect,  int[] candidates, int target) {
        List<List<Integer>> res1 = new N39MCombinationSum().combinationSum(candidates, target);

        String res = comm.toString(res1);
        System.out.println("["+(expect.equals(res))+"]");
        System.out.println("expect:" + expect);
        System.out.println("result:" + res);
        System.out.println("----------------");
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    //按solution写的。
    //Runtime: 5 ms, faster than 70.18% of Java online submissions for Combination Sum.
    //Memory Usage: 42.9 MB, less than 87.58% of Java online submissions for Combination Sum.
    //
    //helper_backtracking
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //memo_bt = new HashMap<>();
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        helper_backtracking(candidates, target, 0, res, new LinkedList<>());
        return res;
    }

    private void helper_backtracking(int[] candidates, int target, int begin,
                                  List<List<Integer>> res, LinkedList<Integer> list){
        if (target < 0) return;
        if (target == 0) {
            res.add(new LinkedList<>(list));
            return;
        }

        for (int i = begin; i < candidates.length; i++) {
            int n = candidates[i];
            list.add(n);
            helper_backtracking(candidates, target-n, i, res, list);
            list.removeLast();
        }
    }


    ////////////////////////////////////////////////////////////////////////////////
    //Runtime: 274 ms, faster than 5.16% of Java online submissions for Combination Sum.
    //Memory Usage: 110.1 MB, less than 5.13% of Java online submissions for Combination Sum.
    //dp + memoe
    public List<List<Integer>> combinationSum_1(int[] candidates, int target) {
        memo = new HashMap<>();
        set = new HashSet<>();
        for (int n: candidates) {
            set.add(n);
        }
        return helper_dp(candidates, target);
    }

    Map<Integer, List<List<Integer>>> memo;
    Set<Integer> set;
    public List<List<Integer>> helper_dp(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (target == 0) return res;

        if (memo.containsKey(target)) {
//            System.out.println("target:"+target);
            return memo.get(target);
        }
        //Base case
        if (target == 1){
            if (set.contains(target)) res.add(Arrays.asList(target));
            memo.put(target, res);
            return res;
        }

        for (int i = 0; i < candidates.length; i++){
            int n = candidates[i];

            LinkedList<Integer> list = new LinkedList<>();
            int sum = 0;
            while (sum <= target) {
                list.add(n);
                sum += n;

                if (sum == target){
                    List<Integer> tmp = new ArrayList<>(list);
                    if (!res.contains(tmp)) res.add(tmp);
                    break;
                }

                //用remainer当target
                List<List<Integer>> resX = helper_dp(candidates, target - sum);
                for (List<Integer> t : resX) {
                    List<Integer> tmp = new ArrayList<>(t);
                    tmp.addAll(list);
                    Collections.sort(tmp);
                    if (!res.contains(tmp)) res.add(tmp);
                }
            }
        }
        memo.put(target, res);
        return res;
    }


}
