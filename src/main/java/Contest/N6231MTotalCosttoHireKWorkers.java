package Contest;

import java.util.*;

import static java.time.LocalTime.now;


/**
 * You are given a 0-indexed integer array costs where costs[i] is the cost of hiring the ith worker.
 *
 * You are also given two integers k and candidates. We want to hire exactly k workers according to the following rules:
 *
 * You will run k sessions and hire exactly one worker in each session.
 * In each hiring session, choose the worker with the lowest cost from either the first candidates workers or the last candidates workers. Break the tie by the smallest index.
 * For example, if costs = [3,2,7,7,1,2] and candidates = 2, then in the first hiring session, we will choose the 4th worker because they have the lowest cost [3,2,7,7,1,2].
 * In the second hiring session, we will choose 1st worker because they have the same lowest cost as 4th worker but they have the smallest index [3,2,7,7,2]. Please note that the indexing may be changed in the process.
 * If there are fewer than candidates workers remaining, choose the worker with the lowest cost among them. Break the tie by the smallest index.
 * A worker can only be chosen once.
 * Return the total cost to hire exactly k workers.
 *
 *
 *
 * Example 1:
 *
 * Input: costs = [17,12,10,2,7,2,11,20,8], k = 3, candidates = 4
 * Output: 11
 * Explanation: We hire 3 workers in total. The total cost is initially 0.
 * - In the first hiring round we choose the worker from [17,12,10,2,7,2,11,20,8]. The lowest cost is 2, and we break the tie by the smallest index, which is 3. The total cost = 0 + 2 = 2.
 * - In the second hiring round we choose the worker from [17,12,10,7,2,11,20,8]. The lowest cost is 2 (index 4). The total cost = 2 + 2 = 4.
 * - In the third hiring round we choose the worker from [17,12,10,7,11,20,8]. The lowest cost is 7 (index 3). The total cost = 4 + 7 = 11. Notice that the worker with index 3 was common in the first and last four workers.
 * The total hiring cost is 11.
 * Example 2:
 *
 * Input: costs = [1,2,4,1], k = 3, candidates = 3
 * Output: 4
 * Explanation: We hire 3 workers in total. The total cost is initially 0.
 * - In the first hiring round we choose the worker from [1,2,4,1]. The lowest cost is 1, and we break the tie by the smallest index, which is 0. The total cost = 0 + 1 = 1. Notice that workers with index 1 and 2 are common in the first and last 3 workers.
 * - In the second hiring round we choose the worker from [2,4,1]. The lowest cost is 1 (index 2). The total cost = 1 + 1 = 2.
 * - In the third hiring round there are less than three candidates. We choose the worker from the remaining workers [2,4]. The lowest cost is 2 (index 0). The total cost = 2 + 2 = 4.
 * The total hiring cost is 4.
 *
 *
 * Constraints:
 *
 * 1 <= costs.length <= 105
 * 1 <= costs[i] <= 105
 * 1 <= k, candidates <= costs.length
 */

/**
 * M - [Time: 35-
 */
//2462. Total Cost to Hire K Workers
public class N6231MTotalCosttoHireKWorkers {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun(1407, new int[]{28,35,21,13,21,72,35,52,74,92,25,65,77,1,73,32,43,68,8,100,84,80,14,88,42,53,98,69,64,40,60,23,99,83,5,21,76,34}, 32, 12);

        doRun(526, new int[]{57,33,26,76,14,67,24,90,72,37,30}, 11, 2);

        doRun(13, new int[]{2,2,2,2,2,2,1,4,5,5,5,5,5,2,2,2,2,2,2,2,2,2,2,2,2,2}, 7, 3);
        doRun(11, new int[]{17,12,10,2,7,2,11,20,8}, 3, 4);
        doRun(48, new int[]{48}, 1, 1);
        doRun(11, new int[]{10,1,11,10}, 2, 1);


        doRun(423, new int[]{31,25,72,79,74,65,84,91,18,59,27,9,81,33,17,58}, 11, 2);
        doRun(261, new int[]{60,87,94,42,12,60}, 5, 4);
        doRun(223, new int[]{18,64,12,21,21,78,36,58,88,58,99,26,92,91,53,10,24,25,20,92,73,63,51,65,87,6,17,32,14,42,46,65,43,9,75}, 13, 23);
        doRun(4, new int[]{1,2,4,1}, 3, 3);

        System.out.println(now());
        System.out.println("==================");

    }


    static private void doRun(long expect, int[] costs, int k, int candidates) {
        long res = new N6231MTotalCosttoHireKWorkers().totalCost(costs, k ,candidates);

//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }




    //2.PriorityQueue
    //Runtime: 113 ms, faster than 80.00% of Java online submissions for Total Cost to Hire K Workers.
    //Memory Usage: 52.8 MB, less than 100.00% of Java online submissions for Total Cost to Hire K Workers.
    //Time: O(N * 2*log(2*N) + K * 2*log(2*N)); Space: O(N)
    //Time: O((N + K) * log(N)); Space: O(N)
    //let N be the number of candidates; K is the k;
    public long totalCost(int[] costs, int k, int candidates) {
        int left = 0, right = costs.length - 1;

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> (costs[a] - costs[b] != 0 ? costs[a] - costs[b] : a - b));
        for (int i = 0; i < candidates && left <= right; i++) {
            priorityQueue.add(left++);
            if (left <= right) priorityQueue.add(right--);
        }

        long res = 0;
        while (k > 0) {
            int idx = priorityQueue.poll();
            if (left <= right && left < costs.length && right >= 0) {
                if (idx <= left) priorityQueue.add(left++);
                else priorityQueue.add(right--);
            }
            res += costs[idx];
            k--;
        }
        return res;
    }

    //1.TreeSet
    //Runtime: 363 ms, faster than 20.00% of Java online submissions for Total Cost to Hire K Workers.
    //Memory Usage: 113.4 MB, less than 20.00% of Java online submissions for Total Cost to Hire K Workers.
    public long totalCost_1(int[] costs, int k, int candidates) {
        int[][] data = new int[costs.length][2];
        for (int i = 0; i < costs.length; i++) {
            data[i][0] = costs[i];
            data[i][1] = i;
        }

        TreeSet<int[]> treeSet = new TreeSet<>((a, b) -> (a[0] - b[0] != 0 ? a[0] - b[0] : a[1] - b[1]));
        for (int i = 0; i < candidates; i++) {
            treeSet.add(data[i]);
            treeSet.add(data[costs.length - 1 - i]);
        }

        long res = 0;
        int left = candidates, right = costs.length - 1 - candidates;

        Set<int[]> seen = new HashSet<>();
        while (k > 0) {
            int[] element = treeSet.first();
            treeSet.remove(element);
            if (left < costs.length && right >= 0) {
                if (element[1] < left) {
                    if (!seen.contains(data[left])) treeSet.add(data[left]);
                    left++;
                } else {
                    if (!seen.contains(data[right])) treeSet.add(data[right]);
                    right--;
                }
            }
            res += element[0];
            seen.add(element);
            k--;
        }
        return res;
    }

}


