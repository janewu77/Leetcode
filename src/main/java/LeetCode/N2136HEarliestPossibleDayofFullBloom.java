package LeetCode;

import java.util.*;

import static java.time.LocalTime.now;

/**
 * You have n flower seeds. Every seed must be planted first before it can begin to grow, then bloom. Planting a seed takes time and so does the growth of a seed. You are given two 0-indexed integer arrays plantTime and growTime, of length n each:
 *
 * plantTime[i] is the number of full days it takes you to plant the ith seed. Every day, you can work on planting exactly one seed. You do not have to work on planting the same seed on consecutive days, but the planting of a seed is not complete until you have worked plantTime[i] days on planting it in total.
 * growTime[i] is the number of full days it takes the ith seed to grow after being completely planted. After the last day of its growth, the flower blooms and stays bloomed forever.
 * From the beginning of day 0, you can plant the seeds in any order.
 *
 * Return the earliest possible day where all seeds are blooming.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: plantTime = [1,4,3], growTime = [2,3,1]
 * Output: 9
 * Explanation: The grayed out pots represent planting days, colored pots represent growing days, and the flower represents the day it blooms.
 * One optimal way is:
 * On day 0, plant the 0th seed. The seed grows for 2 full days and blooms on day 3.
 * On days 1, 2, 3, and 4, plant the 1st seed. The seed grows for 3 full days and blooms on day 8.
 * On days 5, 6, and 7, plant the 2nd seed. The seed grows for 1 full day and blooms on day 9.
 * Thus, on day 9, all the seeds are blooming.
 * Example 2:
 *
 *
 * Input: plantTime = [1,2,3,2], growTime = [2,1,2,1]
 * Output: 9
 * Explanation: The grayed out pots represent planting days, colored pots represent growing days, and the flower represents the day it blooms.
 * One optimal way is:
 * On day 1, plant the 0th seed. The seed grows for 2 full days and blooms on day 4.
 * On days 0 and 3, plant the 1st seed. The seed grows for 1 full day and blooms on day 5.
 * On days 2, 4, and 5, plant the 2nd seed. The seed grows for 2 full days and blooms on day 8.
 * On days 6 and 7, plant the 3rd seed. The seed grows for 1 full day and blooms on day 9.
 * Thus, on day 9, all the seeds are blooming.
 * Example 3:
 *
 * Input: plantTime = [1], growTime = [1]
 * Output: 2
 * Explanation: On day 0, plant the 0th seed. The seed grows for 1 full day and blooms on day 2.
 * Thus, on day 2, all the seeds are blooming.
 *
 *
 * Constraints:
 *
 * n == plantTime.length == growTime.length
 * 1 <= n <= 105
 * 1 <= plantTime[i], growTime[i] <= 104
 */

/**
 * H - [time: 60-
 */
public class N2136HEarliestPossibleDayofFullBloom {


    public static void main(String[] args) {

        System.out.println(now());
        int[] plantTime, growTime;

        plantTime = new int[]{2,3};
        growTime = new int[]{3,2};
        doRun(7, plantTime, growTime);

        plantTime = new int[]{3,2,1};
        growTime = new int[]{1,3,1};
        doRun(7, plantTime, growTime);

        doRun(4, new int[]{1,1}, new int[]{3,1});

        doRun(2, new int[]{1}, new int[]{1});

        plantTime = new int[]{1,4,3};
        growTime = new int[]{2,3,1};
        doRun(9, plantTime, growTime);

        plantTime = new int[]{1,2,3,2};
        growTime = new int[]{2,1,2,1};
        doRun(9, plantTime, growTime);

        plantTime = new int[]{27,5,24,17,27,4,23,16,6,26,13,17,21,3,9,10,28,26,4,10,28,2};
        growTime = new int[]{26,9,14,17,6,14,23,24,11,6,27,14,13,1,15,5,12,15,23,27,28,12};
        doRun(348, plantTime, growTime);

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, int[] plantTime, int[] growTime) {
        int res = new N2136HEarliestPossibleDayofFullBloom().earliestFullBloom(plantTime, growTime);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //https://leetcode.com/problems/earliest-possible-day-of-full-bloom/discuss/1676833/C%2B%2B-Largest-Growing-Time-First-with-Illustrations
    //2. sort Collections.sort
    //Runtime: 77 ms, faster than 93.61% of Java online submissions for Earliest Possible Day of Full Bloom.
    //Memory Usage: 55.9 MB, less than 94.52% of Java online submissions for Earliest Possible Day of Full Bloom.
    //Time:O(N + N * log(N) + N); Space: O(N + log(N)
    //Time:O(N * log(N)); Space: O(N)
    public int earliestFullBloom(int[] plantTime, int[] growTime) {
        List<Integer> indices = new ArrayList<>();

        for (int i = 0; i < plantTime.length; i++) indices.add(i);
        Collections.sort(indices, Comparator.comparingInt(a -> -growTime[a]));

        int res = 0, sumPlantTime = 0;
        for (int i : indices){
            sumPlantTime += plantTime[i];
            res = Math.max(res, sumPlantTime + growTime[i]);
        }
        return res;
    }

    //1. sort  Arrays.sort
    //Runtime: 149 ms, faster than 43.84% of Java online submissions for Earliest Possible Day of Full Bloom.
    //Memory Usage: 127 MB, less than 9.13% of Java online submissions for Earliest Possible Day of Full Bloom.
    //Time:O(N + N * log(N) + N); Space: O(N + log(N)
    //Time:O(N * log(N)); Space: O(N)
    public int earliestFullBloom_1(int[] plantTime, int[] growTime) {
        int[][] data = new int[plantTime.length][2];

        for (int i = 0; i < plantTime.length; i++) {
            data[i][0] = plantTime[i];
            data[i][1] = growTime[i];
        }
        Arrays.sort(data, (a, b) -> b[1] - a[1]);

        int res = 0, sumPlantTime = 0;
        for (int[] node : data){
            sumPlantTime += node[0];
            res = Math.max(res, sumPlantTime + node[1]);
        }
        return res;
    }
}
