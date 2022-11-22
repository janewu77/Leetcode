package LeetCode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import static java.time.LocalTime.now;


/**
 * You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the ith job, you have to finish all the jobs j where 0 <= j < i).
 *
 * You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the d days. The difficulty of a day is the maximum difficulty of a job done on that day.
 *
 * You are given an integer array jobDifficulty and an integer d. The difficulty of the ith job is jobDifficulty[i].
 *
 * Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: jobDifficulty = [6,5,4,3,2,1], d = 2
 * Output: 7
 * Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
 * Second day you can finish the last job, total difficulty = 1.
 * The difficulty of the schedule = 6 + 1 = 7
 * Example 2:
 *
 * Input: jobDifficulty = [9,9,9], d = 4
 * Output: -1
 * Explanation: If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.
 * Example 3:
 *
 * Input: jobDifficulty = [1,1,1], d = 3
 * Output: 3
 * Explanation: The schedule is one job per day. total difficulty will be 3.
 *
 *
 * Constraints:
 *
 * 1 <= jobDifficulty.length <= 300
 * 0 <= jobDifficulty[i] <= 1000
 * 1 <= d <= 10
 */
public class N1335HMinimumDifficultyofaJobSchedule {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;
        doRun(3,  new int[]{1,1,1},3);
        doRun(7,  new int[]{6,5,4,3,2,1},2);
        doRun(1803,  new int[]{186,398,479,206,885,423,805,112,925,656,16,932,740,292,671,360},4);

        doRun(6,  new int[]{6,3,2,1},1);

        doRun(-1,  new int[]{9,9,9},4);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[] jobDifficulty, int d) {
        int res = new N1335HMinimumDifficultyofaJobSchedule().minDifficulty(jobDifficulty,d);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //4.stack
    //This one is really hard to understand. I spend a lots of time to figure it out.......
    //Here are some useful comments. https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/discuss/490316/JavaC%2B%2BPython3-DP-O(nd)-Solution
    //
    //Runtime: 7 ms, faster than 98.32% of Java online submissions for Minimum Difficulty of a Job Schedule.
    //Memory Usage: 40.2 MB, less than 90.69% of Java online submissions for Minimum Difficulty of a Job Schedule.
    //Time: O(N * D); Space: O(N)
    public int minDifficulty(int[] jobDifficulty, int d) {
        if (jobDifficulty.length < d) return -1;

        // prevDP, currDP is the minimum total job difficulty for the current day and previous day, respectively
        int[] prevDP = new int[jobDifficulty.length];
        int[] currDP = new int[jobDifficulty.length];
        int[] tmp;

        Arrays.fill(prevDP, 1000);
        Deque<Integer> stack = new ArrayDeque<>();

        for (int day = 0; day < d; ++day) {
            stack.clear();
            for (int i = day; i < jobDifficulty.length; i++) {

                currDP[i] = i > 0 ? prevDP[i - 1] + jobDifficulty[i] : jobDifficulty[i];

                while (!stack.isEmpty() && jobDifficulty[stack.peek()] <= jobDifficulty[i]) {
                    int j = stack.pop();
                    currDP[i] = Math.min(currDP[i], currDP[j] - jobDifficulty[j] + jobDifficulty[i]);
                }

                if (!stack.isEmpty())
                    currDP[i] = Math.min(currDP[i], currDP[stack.peek()]);

                stack.push(i);
            }
            tmp = prevDP; prevDP = currDP; currDP = tmp;
        }
        return prevDP[jobDifficulty.length - 1];
    }

//    public int minDifficulty(int[] jobDifficulty, int d) {
//        if (jobDifficulty.length < d) return -1;
//
//        // prevDP, currDP is the minimum total job difficulty for the current day and previous day, respectively
//        int[] prevDP = new int[jobDifficulty.length];
//        int[] currDP = new int[jobDifficulty.length];
//        int[] tmp;
//
//        Arrays.fill(prevDP, 1000);
//        Deque<Integer> stack = new ArrayDeque<>();
//
//        for (int day = 0; day < d; ++day) {
//            // Use a monotonically decreasing stack to record job difficulties
//            stack.clear();
//            // The number of jobs needs to be no less than number of days passed.
//            for (int i = day; i < jobDifficulty.length; i++) {
//                // We initialize min_diff_curr_day[i] as only performing 1 job at the i-th index.
//                // At day 0, the minimum total job difficulty is to complete the 0th job only.
//                // Otherwise, we increment min_diff_prev_day[i - 1] by the i-th job difficulty
//                currDP[i] = i > 0 ? prevDP[i - 1] + jobDifficulty[i] : jobDifficulty[i];
//
//                // When we find the last element in the stack is smaller than or equal to current job,
//                // we need to pop out the element to maintain a monotonic decreasing stack.
//                while (!stack.isEmpty() && jobDifficulty[stack.peek()] <= jobDifficulty[i]) {
//                    // If we include all jobs with index j+1 to i to the current d,
//                    // total job difficulty of the current d will be increased
//                    // by the amount of jobDifficulty[i] - jobDifficulty[j]
//                    int j = stack.pop();
//                    currDP[i] = Math.min(currDP[i], currDP[j] - jobDifficulty[j] + jobDifficulty[i]);
//                }
//
//                // When the last element in the stack is larger than current element,
//                // if we include all jobs with index j+1 to i to the current d
//                // the overall job difficulty will not change
//                if (!stack.isEmpty())
//                    currDP[i] = Math.min(currDP[i], currDP[stack.peek()]);
//
//                // Update the monotonic stack by adding in the current index
//                stack.push(i);
//            }
//            tmp = prevDP; prevDP = currDP; currDP = tmp;
//        }
//        return prevDP[jobDifficulty.length - 1];
//    }


    //3.DP - 1d array
    //Runtime: 8 ms, faster than 96.74% of Java online submissions for Minimum Difficulty of a Job Schedule.
    //Memory Usage: 39.9 MB, less than 98.54% of Java online submissions for Minimum Difficulty of a Job Schedule.
    //Time: O(N * N * D)Space: O(N)
    public int minDifficulty_3(int[] jobDifficulty, int d) {
        if (jobDifficulty.length < d) return -1;

        int[] prevDP = new int[jobDifficulty.length + 1];
        int[] currDP = new int[jobDifficulty.length + 1];
        int[] tmp;

        for (int r = 1; r <= d; r++) {
            //int[] dp2 = new int[jobDifficulty.length + 1];
            for (int i = d - r; i < jobDifficulty.length - (r - 1); i++) {
                currDP[i] = Integer.MAX_VALUE;
                int currMax = jobDifficulty[i];
                for (int j = i; j < jobDifficulty.length - (r - 1); j++) {
                    currMax = Math.max(currMax, jobDifficulty[j]);
                    currDP[i] = Math.min(currDP[i], currMax + prevDP[j + 1]);
                }
                currDP[i] = r == 1 ? currMax : currDP[i];
            }
            tmp = prevDP; prevDP = currDP; currDP = tmp;
        }
        return prevDP[0];
    }

    //2.DP - bottom-up
    //Runtime: 8 ms, faster than 96.74% of Java online submissions for Minimum Difficulty of a Job Schedule.
    //Memory Usage: 40 MB, less than 96.94% of Java online submissions for Minimum Difficulty of a Job Schedule.
    //Time: O(N * N * D)Space: O(N * D)
    public int minDifficulty_2(int[] jobDifficulty, int d) {
        if (jobDifficulty.length < d) return -1;

        int[][] dp = new int[jobDifficulty.length + 1 ][d + 1];

        for (int r = 1; r <= d; r++) {
            for (int i = d - r; i < jobDifficulty.length - (r - 1); i++) {

                dp[i][r] = Integer.MAX_VALUE;
                int currMax = jobDifficulty[i];
                for (int j = i; j < jobDifficulty.length - (r - 1); j++) {
                    currMax = Math.max(currMax, jobDifficulty[j]);
                    dp[i][r] = Math.min(dp[i][r], currMax + dp[j + 1][r - 1]);
                }
                dp[i][r] = r == 1 ? currMax : dp[i][r];
            }
        }
        return dp[0][d];
    }


    //1.DP - top-down
    //Runtime: 11 ms, faster than 90.45% of Java online submissions for Minimum Difficulty of a Job Schedule.
    //Memory Usage: 40.2 MB, less than 93.41% of Java online submissions for Minimum Difficulty of a Job Schedule.
    //Time: O(N + N * N * D)Space: O(N * D)
    //let n be the length of input array.
    public int minDifficulty_1(int[] jobDifficulty, int d) {
        if (jobDifficulty.length < d) return -1;

        memo = new int[jobDifficulty.length][d + 1];
        for (int[] m : memo) Arrays.fill(m, -1);

        return helper_dp(jobDifficulty, 0, d);
    }
    int[][] memo;
    private int helper_dp(int[] jobDifficulty, int begin, int d){
        if (memo[begin][d] != -1) return memo[begin][d];

        int res = Integer.MAX_VALUE;
        int currMax = -1;
        for (int i = begin; i < jobDifficulty.length - (d - 1); i++) {
            if (jobDifficulty[i] > currMax) currMax = jobDifficulty[i];
            if (d > 1)
                res = Math.min(res, currMax + helper_dp(jobDifficulty, i + 1, d - 1));
        }
        return memo[begin][d] = d == 1 ? currMax : res;
    }

}
