package LeetCode;

import java.util.*;

import static java.time.LocalTime.now;

/**
 * Given an array of distinct integers nums and a target integer target,
 * return the number of possible combinations that add up to target.
 *
 * The test cases are generated so that the answer can fit in a 32-bit integer.
 *
 *
 *
 * Example 1:
 * Input: nums = [1,2,3], target = 4
 * Output: 7
 * Explanation:
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * Note that different sequences are counted as different combinations.
 *
 *
 *
 * Example 2:
 * Input: nums = [9], target = 3
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 1000
 * All the elements of nums are unique.
 * 1 <= target <= 1000
 *
 *
 */

/**
 * M - [time: 45-]做过类似的，所以比较快就做出来了。
 *  - 求和、数字可多次利用。 结果是 排列组合的 个数！
 *  - backtracking + dp 2种
 *
 */
public class N377MCombinationSumIV {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data  = new int[]{1,2,3};
        doRun(7, data, 4);

        data  = new int[]{1,2};
        doRun(34, data, 8);

        data  = new int[]{1,2};
        doRun(5, data, 4);

        data  = new int[]{1,2,4};
        doRun(55, data, 8);

        data  = new int[]{1,2,4,5};
        doRun(65, data, 8);

        data  = new int[]{9};
        doRun(0, data, 8);
        System.out.println(now());
    }
    static private void doRun(int expect, int[] nums,  int goal) {
        int res = new N377MCombinationSumIV().combinationSum4(nums, goal);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Combination Sum IV.
    //Memory Usage: 41.6 MB, less than 46.68% of Java online submissions for Combination Sum IV.
    //DP: Bottom-Up Dynamic Programming
    //Time: O(T*N) T相当于参与和的最大的个数。 Space: O(T)
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;

        //time: O(T*N)
        for (int sum = 1; sum < target + 1; sum++) {
            //Time:O(N)
            for(int num: nums)
                if (sum - num >= 0)
                    dp[sum] += dp[sum - num];
        }
        return dp[target];
    }


    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Combination Sum IV.
    //Memory Usage: 41.6 MB, less than 46.68% of Java online submissions for Combination Sum IV.
    //DP: Top-Down Dynamic Programming
    //Time: O(T*N) T相当于参与和的最大的个数。 Space: O(T)
    int[] int_memo; //这个速度比HashMap 快。
    public int combinationSum4_2(int[] nums, int target) {
        //Space: O(T)
        int_memo = new int[target+1];
        Arrays.fill(int_memo, -1);
        int_memo[0] = 1;

        return dp_helper(nums, target);
    }

    private int dp_helper(int[] nums, int target) {
        if (int_memo[target] != -1)
            return int_memo[target];

        int res = 0;
        //Time: O(N)
        for(int n: nums){
            if (target - n >= 0)
                res += dp_helper(nums, target - n);
        }
        return int_memo[target] = res;
    }


    //Runtime: 3 ms, faster than 36.49% of Java online submissions for Combination Sum IV.
    //Memory Usage: 41.5 MB, less than 46.68% of Java online submissions for Combination Sum IV.
    //Backtracking
    public int combinationSum4_1(int[] nums, int target) {
        memo = new HashMap<>();
        return backtracking_helper(nums, target, new LinkedList<>());
    }

    Map<Integer, Integer> memo = new HashMap<>();
    private int backtracking_helper(int[] nums, int target, LinkedList<Integer> list){
        if (target < 0) return 0;
        if (target == 0){
            return 1;
        }
        if (memo.containsKey(target))
            return memo.get(target);

        int res = 0;
        for(int n: nums){
            list.add(n);
            res += backtracking_helper(nums, target - n, list);
            list.removeLast();
        }
        memo.put(target, res);
        return res;
    }


}
