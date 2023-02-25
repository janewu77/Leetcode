package LeetCode;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

import static java.time.LocalTime.now;

/**
 * Suppose LeetCode will start its IPO soon. In order to sell a good price of its shares to Venture Capital, LeetCode would like to work on some projects to increase its capital before the IPO. Since it has limited resources, it can only finish at most k distinct projects before the IPO. Help LeetCode design the best way to maximize its total capital after finishing at most k distinct projects.
 *
 * You are given n projects where the ith project has a pure profit profits[i] and a minimum capital of capital[i] is needed to start it.
 *
 * Initially, you have w capital. When you finish a project, you will obtain its pure profit and the profit will be added to your total capital.
 *
 * Pick a list of at most k distinct projects from given projects to maximize your final capital, and return the final maximized capital.
 *
 * The answer is guaranteed to fit in a 32-bit signed integer.
 *
 *
 *
 * Example 1:
 *
 * Input: k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
 * Output: 4
 * Explanation: Since your initial capital is 0, you can only start the project indexed 0.
 * After finishing it you will obtain profit 1 and your capital becomes 1.
 * With capital 1, you can either start the project indexed 1 or the project indexed 2.
 * Since you can choose at most 2 projects, you need to finish the project indexed 2 to get the maximum capital.
 * Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.
 * Example 2:
 *
 * Input: k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
 * Output: 6
 *
 *
 * Constraints:
 *
 * 1 <= k <= 105
 * 0 <= w <= 109
 * n == profits.length
 * n == capital.length
 * 1 <= n <= 105
 * 0 <= profits[i] <= 104
 * 0 <= capital[i] <= 109
 */
public class N502HIPO {

    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");

        int[] profits; int[] capital;

//        profits = new int[]{};
//        capital = new int[]{};
//        doRun(17, 11, 11, profits, capital);

        doRun(17, 11, 11, new int[]{1,2,3}, new int[]{11,12,13});

        doRun(5, 1, 2, new int[]{1,2,3}, new int[]{1,1,2});

        doRun(6, 10, 0, new int[]{1,2,3}, new int[]{0,1,2});
        doRun(4, 2, 0, new int[]{1,2,3}, new int[]{0,1,1});
        doRun(6, 3, 0, new int[]{1,2,3}, new int[]{0,1,2});

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, int k, int w, int[] profits, int[] capital) {
        int res = new N502HIPO().findMaximizedCapital(k, w, profits, capital);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //2. two PriorityQueues
    //Runtime: 34ms 98%; Memory: 96MB 12%
    //Time: O(N * logN + min(K, N) * logN); Space: O(N)
    //Time: O(N * logN); Space: O(N)
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {

        PriorityQueue<Integer> queueCapital = new PriorityQueue(profits.length, Comparator.comparingInt(a -> capital[(int) a]));
        PriorityQueue<Integer> queueProfit = new PriorityQueue(profits.length, Comparator.comparingInt(a -> -profits[(int) a]));
        //init
        for (int i = 0; i < profits.length; i++) {
            if (capital[i] <= w) queueProfit.add(i);
            else queueCapital.add(i);
        }

        while (k-- > 0 && !queueProfit.isEmpty()){
            w += profits[queueProfit.poll()];
            while (!queueCapital.isEmpty() && capital[queueCapital.peek()] <= w)
                queueProfit.add(queueCapital.poll());
        }
        return w;
    }


    //1.Greedy : sort + PriorityQueue
    //Runtime: 114ms 23%; Memory: 103.3MB 9%
    //Time: O(N + N * logN + min(K, N) * logN); Space: O(N + logN)
    //Time: O(N * logN); Space: O(N)
    public int findMaximizedCapital_1(int k, int w, int[] profits, int[] capital) {
        int[][] data = new int[profits.length][2];
        for (int i = 0; i < profits.length; i++) {
            data[i][0] = capital[i];
            data[i][1] = profits[i];
        }

        Arrays.sort(data, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> queue = new PriorityQueue(profits.length, Collections.reverseOrder());

        int idx = 0;
        while (idx < profits.length && data[idx][0] <= w)
            queue.add(data[idx++][1]);

        while (k-- > 0 && !queue.isEmpty()) {
            w += queue.poll();
            while (idx < profits.length && data[idx][0] <= w)
                queue.add(data[idx++][1]);
        }
        return w;
    }

}
