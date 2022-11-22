package LeetCode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static java.time.LocalTime.now;

/**
 * Return all non-negative integers of length n such that the absolute difference between every two consecutive digits is k.
 *
 * Note that every number in the answer must not have leading zeros. For example, 01 has one leading zero and is invalid.
 *
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3, k = 7
 * Output: [181,292,707,818,929]
 * Explanation: Note that 070 is not a valid number, because it has leading zeroes.
 * Example 2:
 *
 * Input: n = 2, k = 1
 * Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 *
 *
 * Constraints:
 *
 * 2 <= n <= 9
 * 0 <= k <= 9
 */

/**
 * M - [time : 25-
 */
public class N967MNumberWithSameConsecutiveDifferences {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun_demo("[181, 292, 707, 818, 929]", 3, 7);
        doRun_demo("[10, 12, 21, 23, 32, 34, 43, 45, 54, 56, 65, 67, 76, 78, 87, 89, 98]", 2, 1);

        doRun_demo("[11, 22, 33, 44, 55, 66, 77, 88, 99]", 2, 0);

        //doRun_demo("[11, 22, 33, 44, 55, 66, 77, 88, 99]", 1, 0);

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun_demo(String expect, int n, int k) {
        int[] res1 = new N967MNumberWithSameConsecutiveDifferences().numsSameConsecDiff(n, k);
//        String res1 = comm.toString(res1);
        String res = Arrays.toString(res1);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        //int[][] res1
        //        sb.append("[");
        //        for(int i = 0; i<res1.length; i++) {
        //            String str = Arrays.toString(res1[i]);
        //            sb.append(str).append(",");
        //        }
        //        if (sb.length() > 1) sb.deleteCharAt(sb.length()-1);
        //        sb.append("]");
    }


    //Runtime: 3 ms, faster than 80.94% of Java online submissions for Numbers With Same Consecutive Differences.
    //Memory Usage: 43.2 MB, less than 41.56% of Java online submissions for Numbers With Same Consecutive Differences.
    //BFS + iteration
    public int[] numsSameConsecDiff(int n, int k) {
        if (n == 1) return new int[]{1,2,3,4,5,6,7,8,9};

        Queue<Integer> queue = new LinkedList<>();
        for (int d = 1; d <= 9; d++) queue.add(d);

        for(int j = 2; j <= n; j++) {
            int Len = queue.size();
            for(int i = 0; i < Len; i++) {
                int number = queue.poll();
                int d = number % 10;
                int currNumb = number * 10 + d;
                if (d - k >= 0) queue.add(currNumb - k);
                if (d + k <= 9 && k != 0) queue.add(currNumb + k);
            }
        }

        int idx = 0;
        int[] res = new int[queue.size()];
        while (!queue.isEmpty()) res[idx++] = queue.poll();
        return res;
        // return list.stream().mapToInt(i->i).toArray();
    }

    //Runtime: 3 ms, faster than 80.94% of Java online submissions for Numbers With Same Consecutive Differences.
    //Memory Usage: 42.4 MB, less than 84.69% of Java online submissions for Numbers With Same Consecutive Differences.
    //DFS + Recursion
    //Time: O(2^N); Space: O(2^N)
    //If K >= 5, time and Space O(N)
    //If K <= 4, time and space O(2^N)
    public int[] numsSameConsecDiff_dfs(int n, int k) {
        if (n == 1) return new int[]{1,2,3,4,5,6,7,8,9};

        List<Integer> list = new LinkedList<>();
        for (int d = 1; d <= 9; d++)
            helper_dfs(d, n - 1, k, list);

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            res[i] = list.get(i);
        return res;
        // return list.stream().mapToInt(i->i).toArray();
    }

    private void helper_dfs(int number, int n, int k, List<Integer> list){
        if (n == 0) {
            list.add(number);
            return;
        }
        int d = number % 10;
        int currNumb = number * 10 + d;
        if (d - k >= 0)
            helper_dfs(currNumb - k ,n - 1, k, list);

        if (k != 0 && d + k <= 9)
            helper_dfs(currNumb + k, n - 1, k, list);
    }
}
