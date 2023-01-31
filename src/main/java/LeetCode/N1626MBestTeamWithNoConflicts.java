package LeetCode;


import java.util.*;

import static java.time.LocalTime.now;

/**
 * You are the manager of a basketball team. For the upcoming tournament, you want to choose the team with the highest overall score. The score of the team is the sum of scores of all the players in the team.
 *
 * However, the basketball team is not allowed to have conflicts. A conflict exists if a younger player has a strictly higher score than an older player. A conflict does not occur between players of the same age.
 *
 * Given two lists, scores and ages, where each scores[i] and ages[i] represents the score and age of the ith player, respectively, return the highest overall score of all possible basketball teams.
 *
 *
 *
 * Example 1:
 *
 * Input: scores = [1,3,5,10,15], ages = [1,2,3,4,5]
 * Output: 34
 * Explanation: You can choose all the players.
 * Example 2:
 *
 * Input: scores = [4,5,6,5], ages = [2,1,2,1]
 * Output: 16
 * Explanation: It is best to choose the last 3 players. Notice that you are allowed to choose multiple people of the same age.
 * Example 3:
 *
 * Input: scores = [1,2,3,5], ages = [8,9,10,1]
 * Output: 6
 * Explanation: It is best to choose the first 3 players.
 *
 *
 * Constraints:
 *
 * 1 <= scores.length, ages.length <= 1000
 * scores.length == ages.length
 * 1 <= scores[i] <= 106
 * 1 <= ages[i] <= 1000
 */


public class N1626MBestTeamWithNoConflicts {

    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");

        int[] scores, ages;

        scores = new int[]{1,2,3,5};
        ages = new int[]{8,9,10,1};
        doRun(6, scores, ages);

        scores = new int[]{596,277,897,622,500,299,34,536,797,32,264,948,645,537,83,589,770};
        ages = new int[]{18,52,60,79,72,28,81,33,96,15,18,5,17,96,57,72,72};
        doRun(3287, scores, ages);

        scores = new int[]{1,3,7,3,2,4,10,7,5};
        ages = new int[]{4,5,2,1,1,2,4,1,4};
        doRun(29, scores, ages);

        scores = new int[]{4,5,6,5};
        ages = new int[]{2,1,2,1};
        doRun(16, scores, ages);

        scores = new int[]{1,3,5,10,15};
        ages = new int[]{1,2,3,4,5};
        doRun(34, scores, ages);

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, int[] scores, int[] ages) {
        int res = new N1626MBestTeamWithNoConflicts().bestTeamScore(scores, ages);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //2.DP bottom-up
    //Runtime: 43ms 79%; Memory: 42.4MB 75%
    //Time: O(N + N * logN + N * N); Space: O(N + logN + N);
    //Time: O(N * N); Space: O(N);
    public int bestTeamScore(int[] scores, int[] ages) {

        int[][] data = new int[ages.length][2];
        for (int i = 0; i < ages.length; i++) {
            data[i][0] = scores[i];
            data[i][1] = ages[i];
        }
        Arrays.sort(data, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);

        int[] dp = new int[ages.length];
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            dp[i] = data[i][0];
            for (int j = i - 1; j >= 0; j--) {
                if (data[j][0] <= data[i][0])
                    dp[i] = Math.max(dp[i], data[i][0] + dp[j]);
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    //1.backtracking
    //TLE
    //Time: O(N * logN + 2^N); Space: O(N + logN + N);
    //Time: O(2^N); Space: O(N);
    public int bestTeamScore_1(int[] scores, int[] ages) {
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < ages.length; i++)
            data.add(i);
        Collections.sort(data, (a, b) -> ages[a] == ages[b] ? scores[a] - scores[b] : ages[a] - ages[b]);
        helper_backtracking(scores, data, 0,  0, 0);
        return res;
    }

    private int res = 0;
    private void helper_backtracking(int[] scores, List<Integer> data, int lastScore, int currIdx, int sum) {
        if (currIdx >= data.size()) {
            res = Math.max(res, sum);
            return;
        }

        helper_backtracking(scores, data, lastScore, currIdx + 1, sum);

        int currScore = scores[data.get(currIdx)];
        if (lastScore <= currScore)
            helper_backtracking(scores, data, currScore, currIdx + 1, sum + currScore);

    }





}

