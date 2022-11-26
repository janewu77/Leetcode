package LeetCode;

import javafx.util.Pair;

import java.io.IOException;
import java.util.*;

import static java.time.LocalTime.now;

/**
 * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].
 *
 * You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.
 *
 * If you choose a job that ends at time X you will be able to start another job that starts at time X.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
 * Output: 120
 * Explanation: The subset chosen is the first and fourth job.
 * Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
 * Example 2:
 *
 *
 *
 * Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
 * Output: 150
 * Explanation: The subset chosen is the first, fourth and fifth job.
 * Profit obtained 150 = 20 + 70 + 60.
 * Example 3:
 *
 *
 *
 * Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
 * Output: 6
 *
 *
 * Constraints:
 *
 * 1 <= startTime.length == endTime.length == profit.length <= 5 * 104
 * 1 <= startTime[i] < endTime[i] <= 109
 * 1 <= profit[i] <= 104
 */
public class N1235HMaximumProfitinJobScheduling {


    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");
        int[] startTime, endTime, profit;


        startTime = new int[]{6,15,7,11,1,3,16,2};
        endTime = new int[]{19,18,19,16,10,8,19,8};
        profit = new int[]{2,9,1,19,5,7,3,19};
        doRun(41, startTime,endTime,profit);

        startTime = new int[]{1,1,1};
        endTime = new int[]{2,3,4};
        profit = new int[]{5,6,4};
        doRun(6, startTime,endTime,profit);

        startTime = new int[]{16,11,2};
        endTime = new int[]{19,16,8};
        profit = new int[]{3,19,19};
        doRun(41, startTime,endTime,profit);

        startTime = new int[]{4,2,4,8,2};
        endTime = new int[]{5,5,5,10,8};
        profit = new int[]{1,2,8,10,4};
        doRun(18, startTime,endTime,profit);

        startTime = new int[]{1,2,3,3};
        endTime = new int[]{3,4,5,6};
        profit = new int[]{50,10,40,70};
        doRun(120, startTime,endTime,profit);

        startTime = new int[]{1,2,3,4,6};
        endTime = new int[]{3,5,10,6,9};
        profit = new int[]{20,20,100,70,60};
        doRun(150, startTime,endTime,profit);

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, int[] startTime, int[] endTime, int[] profit) {
        int res = new N1235HMaximumProfitinJobScheduling().jobScheduling(startTime, endTime, profit);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //5.heap
    //Runtime: 52 ms, faster than 73.86% of Java online submissions for Maximum Profit in Job Scheduling.
    //Memory Usage: 72.3 MB, less than 59.94% of Java online submissions for Maximum Profit in Job Scheduling.
    //Time: O(N + N * log(N) + N * log(N) + N); Space: O(N + logN + N)
    //Time: O(N * log(N)); Space: O(N)
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int[][] tasks = new int[startTime.length][3];
        for (int i = 0; i < startTime.length; i++) {
            tasks[i][0] = startTime[i];
            tasks[i][1] = endTime[i];
            tasks[i][2] = profit[i];
        }
        Arrays.sort(tasks, (a, b) -> a[0] == b[0] ? a[1] - b[1]: a[0] - b[0]);

        int maxProfit = 0;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        for (int i = 0; i < tasks.length; i++) {
            while (!minHeap.isEmpty() && minHeap.peek()[0] <= tasks[i][0])
                maxProfit = Math.max(maxProfit, minHeap.poll()[1]);
            minHeap.add(new int[]{tasks[i][1], tasks[i][2] + maxProfit});
        }

        while (!minHeap.isEmpty()) maxProfit = Math.max(maxProfit, minHeap.poll()[1]);
        return maxProfit;
    }

    //4.DP bottom - up
    //Runtime: 32 ms, faster than 92.54% of Java online submissions for Maximum Profit in Job Scheduling.
    //Memory Usage: 50.9 MB, less than 92.71% of Java online submissions for Maximum Profit in Job Scheduling.
    //Time: O(N * log(N));  Space: O(N);
    public int jobScheduling_4(int[] startTime, int[] endTime, int[] profit) {
        //Time: O(N); Space: O(N)
        int[][] tasks = new int[startTime.length][3];
        for (int i = 0; i < startTime.length; i++) {
            tasks[i][0] = startTime[i];
            tasks[i][1] = endTime[i];
            tasks[i][2] = profit[i];
        }
        //Time: O(N * log(N)); Space: O(log(N))
        Arrays.sort(tasks, (a, b) -> a[0] == b[0] ? a[1] - b[1]: a[0] - b[0]);

        //Time: O(N * log(N)); Space: O(N)
        int[] dp = new int[tasks.length];
        dp[dp.length - 1] = tasks[tasks.length - 1][2];
        for (int i = tasks.length - 2; i >= 0 ; i--) {
            int[] task = tasks[i];
            int idx = Arrays.binarySearch(tasks, i + 1, tasks.length,
                    new int[]{task[1], 0}, (a, b) -> a[0] == b[0] ? a[1] - b[1]: a[0] - b[0]);

            if (idx < 0) idx = -idx - 1;
            dp[i] = Math.max(task[2] + (idx < tasks.length ? dp[idx] : 0), dp[i + 1]);
        }
        return dp[0];
    }


    //3.DP top-down
    //Runtime: 30 ms, faster than 94.04% of Java online submissions for Maximum Profit in Job Scheduling.
    //Memory Usage: 51.1 MB, less than 92.20% of Java online submissions for Maximum Profit in Job Scheduling.
    //Time: O(N * log(N));  Space: O(N);
    public int jobScheduling_3(int[] startTime, int[] endTime, int[] profit) {
        //Time: O(N); Space: O(N)
        int[][] tasks = new int[startTime.length][3];
        for (int i = 0; i < startTime.length; i++) {
            tasks[i][0] = startTime[i];
            tasks[i][1] = endTime[i];
            tasks[i][2] = profit[i];
        }
        //Time: O(N* log(N)); Space: O(log(N))
        Arrays.sort(tasks, (a, b) -> a[0] == b[0] ? a[1] - b[1]: a[0] - b[0]);

        return helper3(tasks, 0, new int[tasks.length]);
    }

    // The number of calls to helper3 is (N + N) because each non-memoized call will call helper3 twice.
    // Each memoized call will take O(1) time while for the non-memoized call,
    // we will perform a binary search that takes O(log N) time, hence the time complexity will be O(N * log(N) + N)
    //Time: O(N * log(N) + N); Space: O(N)
    private int helper3(int[][] tasks, int begin, int[] dp) {
        if (begin == tasks.length) return 0;
        if (dp[begin] != 0) return dp[begin];

        int[] task = tasks[begin];
        int idx = Arrays.binarySearch(tasks, begin + 1, tasks.length,
                new int[]{task[1], 0}, (a, b) -> a[0] == b[0] ? a[1] - b[1]: a[0] - b[0]);
        if (idx < 0) idx = -idx - 1;
        dp[begin] = Math.max(task[2] + helper3(tasks, idx, dp), helper3(tasks, begin + 1, dp));
        return dp[begin];
    }


    //2.Brute force 2
    //TLE
    //Time: O(N * logN + N * N)
    public int jobScheduling_2(int[] startTime, int[] endTime, int[] profit) {
        int[][] tasks = new int[startTime.length][3];
        for (int i = 0; i < startTime.length; i++) {
            tasks[i][0] = startTime[i];
            tasks[i][1] = endTime[i];
            tasks[i][2] = profit[i];
        }
        Arrays.sort(tasks, (a, b) -> a[0] == b[0] ? a[1] - b[1]: a[0] - b[0]);

        int res = 0;
        for (int i = 0; i < tasks.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++)
                if (tasks[j][1] <= tasks[i][0]) max = Math.max(max, tasks[j][2]);

            tasks[i][2] += max;
            res = Math.max(res, tasks[i][2]);
        }

        return res;
    }


    //1.Brute force 1
    //TLE
    public int jobScheduling_1(int[] startTime, int[] endTime, int[] profit) {
        int[][] tasks = new int[startTime.length][3];
        for (int i = 0; i < startTime.length; i++) {
            tasks[i][0] = startTime[i];
            tasks[i][1] = endTime[i];
            tasks[i][2] = profit[i];
        }
        Arrays.sort(tasks, (a, b) -> a[0]==b[0] ? a[1] - b[1]: a[0] - b[0]);
        return helper(tasks,0, tasks[0][0]);
    }

    Map<Pair<Integer, Integer>, Integer> memo = new HashMap<>();
    private int helper(int[][] tasks, int lastIdx, int nextStartTime){
        Pair<Integer, Integer> pair = new Pair<>(lastIdx, nextStartTime);
        if (memo.containsKey(pair)) return memo.get(pair);
        int res = 0;
        int idx = Arrays.binarySearch(tasks, lastIdx, tasks.length,
                new int[]{nextStartTime, 0}, (a, b) -> a[0] == b[0] ? a[1] - b[1]: a[0] - b[0]);

        if (idx < 0) idx = -idx - 1;
        for (int i = idx; i < tasks.length; i++) {
            int currProfit =  tasks[i][2];
            currProfit += helper(tasks, i, tasks[i][1]);
            res = Math.max(res, currProfit);
        }
        memo.put(pair, res);
        return res;
    }

}
