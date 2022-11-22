package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
 *
 * Only numbers 1 through 9 are used.
 * Each number is used at most once.
 * Return a list of all possible valid combinations.
 * The list must not contain the same combination twice, and the combinations may be returned in any order.
 *
 *
 * Example 1:
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * Explanation:
 * 1 + 2 + 4 = 7
 * There are no other valid combinations.
 *
 *
 * Example 2:
 * Input: k = 3, n = 9
 * Output: [[1,2,6],[1,3,5],[2,3,4]]
 * Explanation:
 * 1 + 2 + 6 = 9
 * 1 + 3 + 5 = 9
 * 2 + 3 + 4 = 9
 * There are no other valid combinations.
 *
 *
 * Example 3:
 * Input: k = 4, n = 1
 * Output: []
 * Explanation: There are no valid combinations.
 * Using 4 different numbers in the range [1,9],
 * the smallest sum we can get is 1+2+3+4 = 10 and since 10 > 1,
 * there are no valid combination.
 *
 *
 * Constraints:
 *
 * 2 <= k <= 9
 * 1 <= n <= 60
 */

/**
 * M - [time: 10-]
 *
 * - 求和，指定了加数的个数，数字只能用一次。 返回结果，是 组合 列表，组合不可重复。
 */
public class N216MCombinationSumIII {


    //Runtime: 1 ms, faster than 85.65% of Java online submissions for Combination Sum III.
    //Memory Usage: 42.1 MB, less than 12.78% of Java online submissions for Combination Sum III.
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();

        helper(1,k, n, res, new ArrayList<>());
        return res;
    }

    private void helper(int begin, int size, int target,
                        List<List<Integer>>res, List<Integer> list){
        if (target == 0 && list.size() == size) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = begin; i <= 9; i++){

            if (i > target || list.size() > size) break;

            list.add(i);
            helper(i + 1, size,target - i, res, list);
            list.remove(list.size()-1);
        }

    }



}
