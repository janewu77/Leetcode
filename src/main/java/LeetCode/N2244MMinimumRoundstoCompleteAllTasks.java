package LeetCode;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.time.LocalTime.now;

/**
 * You are given a 0-indexed integer array tasks, where tasks[i] represents the difficulty level of a task. In each round, you can complete either 2 or 3 tasks of the same difficulty level.
 *
 * Return the minimum rounds required to complete all the tasks, or -1 if it is not possible to complete all the tasks.
 *
 *
 *
 * Example 1:
 *
 * Input: tasks = [2,2,3,3,2,4,4,4,4,4]
 * Output: 4
 * Explanation: To complete all the tasks, a possible plan is:
 * - In the first round, you complete 3 tasks of difficulty level 2.
 * - In the second round, you complete 2 tasks of difficulty level 3.
 * - In the third round, you complete 3 tasks of difficulty level 4.
 * - In the fourth round, you complete 2 tasks of difficulty level 4.
 * It can be shown that all the tasks cannot be completed in fewer than 4 rounds, so the answer is 4.
 * Example 2:
 *
 * Input: tasks = [2,3,3]
 * Output: -1
 * Explanation: There is only 1 task of difficulty level 2, but in each round, you can only complete either 2 or 3 tasks of the same difficulty level. Hence, you cannot complete all the tasks, and the answer is -1.
 *
 *
 * Constraints:
 *
 * 1 <= tasks.length <= 105
 * 1 <= tasks[i] <= 109
 */
public class N2244MMinimumRoundstoCompleteAllTasks {

    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");
        doRun(1, new int[]{4,4,4});
        doRun(5, new int[]{9,9,9,11,11,22,22,22,22,55,55});
        doRun(4, new int[]{2,2,3,3,2,4,4,4,4,4});
        doRun(-1, new int[]{2,3,3});
        doRun(-1, new int[]{2,1000000000});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[] tasks) {
        int res = new N2244MMinimumRoundstoCompleteAllTasks().minimumRounds(tasks);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //5.counter + sort
    //Runtime: 15ms 95%; Memory: 84.6MB 60%
    //Time: O(N * logN); Space: O(logN)
    public int minimumRounds(int[] tasks) {
        Arrays.sort(tasks);
        int count = 0, res = 0;
        for (int i = 0; i < tasks.length; i++) {
            count++;
            if (i == tasks.length - 1 || tasks[i] != tasks[i + 1]) {
                if (count == 1) return -1;
                res += count / 3;
                if (count % 3 != 0) res++;
                count = 0;
            }
        }
        return res;
    }

    //4.counter
    //Time: O(N); Space: O(N)
    public int minimumRounds_4(int[] tasks) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int i = 0; i < tasks.length; i++)
            counter.put(tasks[i], counter.getOrDefault(tasks[i], 0) + 1);

        int res = 0;
        for (Map.Entry<Integer,Integer> entry: counter.entrySet()){
            int count = entry.getValue();
            if (count <= 1) return -1;
            res += count / 3;
            if (count % 3 != 0) res++;
        }
        return res;
    }

    //3.DP bottom-up
    //Runtime: 28ms 93%; Memory: 83.6MB 62%
    //Time: O(N * logN + N); Space: O(logN)
    //Time: O(N * logN); Space: O(logN)
    public int minimumRounds_3(int[] tasks) {
        Arrays.sort(tasks);

        int dp1 = Integer.MAX_VALUE, dp2 = 0, dp3 = 0;
        for (int i = tasks.length - 2; i >= 0; i--) {
            int tmp = Integer.MAX_VALUE;

            if (tasks[i + 1] == tasks[i])
                tmp = Math.min(tmp, dp2);

            if (i + 2 < tasks.length && tasks[i + 2] == tasks[i])
                tmp = Math.min(tmp, dp3);

            dp3 = dp2;
            dp2 = dp1;
            dp1 = tmp == Integer.MAX_VALUE ? tmp : tmp + 1;
        }
        return dp1 == Integer.MAX_VALUE ? -1 : dp1;
    }

    //2.DP bottom-up
    //Runtime: 17ms 95%; Memory: 52.3MB 99%
    //Time: O(N * logN + N); Space: O(logN + N)
    //Time: O(N * logN); Space: O(N)
    public int minimumRounds_2(int[] tasks) {
        Arrays.sort(tasks);

        int[] dp = new int[tasks.length + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[tasks.length] = 0;

        for (int i = tasks.length - 2; i >= 0; i--) {
            if (tasks[i] != tasks[i + 1] ) continue;

            if (tasks[i + 1] == tasks[i])
                dp[i] = Math.min(dp[i], dp[i + 2]);
            if (i + 2 < tasks.length && tasks[i + 2] == tasks[i])
                dp[i] = Math.min(dp[i], dp[i + 3]);

            if (dp[i] != Integer.MAX_VALUE)
                dp[i] = dp[i] + 1;
        }
        return dp[0] == Integer.MAX_VALUE ? -1 : dp[0] ;
    }

    //1.recursion + memo
    //Runtime: 28ms 93%; Memory: 58MB 75%
    //Time: O(N * logN + N); Space: O(logN + N)
    //Time: O(N * logN); Space: O(N)
    public int minimumRounds_1(int[] tasks) {
        Arrays.sort(tasks);
        int res = helper(tasks ,0, new int[tasks.length]);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int helper(int[] tasks, int begin, int[] memo){
        if (begin >= tasks.length) return 0;

        if (memo[begin] != 0) return memo[begin];

        int res = Integer.MAX_VALUE;
        if (begin + 1 < tasks.length && tasks[begin + 1] == tasks[begin])
            res = Math.min(res, helper(tasks, begin + 2, memo));
        if (begin + 2 < tasks.length && tasks[begin + 2] == tasks[begin])
            res = Math.min(res, helper(tasks, begin + 3, memo));

        return memo[begin] = res == Integer.MAX_VALUE ? res : res + 1;
    }
}
