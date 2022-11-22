package LeetCode;

import utils.comm;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Numbers can be regarded as the product of their factors.
 *
 * For example, 8 = 2 x 2 x 2 = 2 x 4.
 * Given an integer n, return all possible combinations of its factors. You may return the answer in any order.
 *
 * Note that the factors should be in the range [2, n - 1].
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: []
 * Example 2:
 *
 * Input: n = 12
 * Output: [[2,6],[3,4],[2,2,3]]
 * Example 3:
 *
 * Input: n = 37
 * Output: []
 *
 *
 * Constraints:
 *
 * 1 <= n <= 107
 */

/**
 * M - [time: 60-
 *  - 这里有个math的点。  Math.sqrt(num);
 */

public class N254MFactorCombinations {

    public static void main(String[] args){

        doRun_demo("[]", 1);
        doRun_demo("[]", 37);
        doRun_demo("[]", 13);

        doRun_demo("[[2, 2]]", 4);
        doRun_demo("[[2, 6],[2, 2, 3],[3, 4]]", 12);
        doRun_demo("[[2, 16],[2, 2, 8],[2, 2, 2, 4],[2, 2, 2, 2, 2],[2, 4, 4],[4, 8]]", 32);

        doRun_demo("[[2, 12],[2, 2, 6],[2, 2, 2, 3],[2, 3, 4],[3, 8],[4, 6]]", 24);
        System.out.println("==================");
    }

    static private void doRun_demo(String expect, int n) {
        List<List<Integer>> res1 = new N254MFactorCombinations().getFactors(n);
        String res = comm.toString(res1);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    //
    //Runtime: 4 ms, faster than 97.73% of Java online submissions for Factor Combinations.
    //Memory Usage: 54.2 MB, less than 30.12% of Java online submissions for Factor Combinations.
    //Time：O（ logN * logN ） 【Math.sqrt(N) == logN】
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (n == 1) return res;
        helper_backtracking(n, 2, res, new ArrayList<>());
        return res;
    }

    private void helper_backtracking(int num, int currfactor,
                                     List<List<Integer>> res, List<Integer> list){
        if (num < currfactor) return; //这句一定要加

        if (list.size() > 0) {
            List<Integer> tmp = new ArrayList<>(list);
            tmp.add(num);
            res.add(tmp);
        }

        double factorMax = Math.sqrt(num); //math point!
//        double factorMax = (num+1)/2;
        for (int factor = currfactor; factor <= factorMax; factor++) {
            if (num % factor == 0) {
                list.add(factor);
                helper_backtracking(num / factor, factor, res, list);
                list.remove(list.size() - 1);
            }
        }
    }

}
