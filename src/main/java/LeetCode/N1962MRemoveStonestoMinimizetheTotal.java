package LeetCode;


import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import static java.time.LocalTime.now;

/**
 *
 * You are given a 0-indexed integer array piles, where piles[i] represents the number of stones in the ith pile, and an integer k. You should apply the following operation exactly k times:
 *
 * Choose any piles[i] and remove floor(piles[i] / 2) stones from it.
 * Notice that you can apply the operation on the same pile more than once.
 *
 * Return the minimum possible total number of stones remaining after applying the k operations.
 *
 * floor(x) is the greatest integer that is smaller than or equal to x (i.e., rounds x down).
 *
 *
 *
 * Example 1:
 *
 * Input: piles = [5,4,9], k = 2
 * Output: 12
 * Explanation: Steps of a possible scenario are:
 * - Apply the operation on pile 2. The resulting piles are [5,4,5].
 * - Apply the operation on pile 0. The resulting piles are [3,4,5].
 * The total number of stones in [3,4,5] is 12.
 * Example 2:
 *
 * Input: piles = [4,3,6,7], k = 3
 * Output: 12
 * Explanation: Steps of a possible scenario are:
 * - Apply the operation on pile 2. The resulting piles are [4,3,3,7].
 * - Apply the operation on pile 3. The resulting piles are [4,3,3,4].
 * - Apply the operation on pile 0. The resulting piles are [2,3,3,4].
 * The total number of stones in [2,3,3,4] is 12.
 *
 *
 * Constraints:
 *
 * 1 <= piles.length <= 105
 * 1 <= piles[i] <= 104
 * 1 <= k <= 105
 *
 *
 */



public class N1962MRemoveStonestoMinimizetheTotal {


    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");
        doRun(12, new int[]{5,4,9},  2);
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[] piles, int k) {
        int res = new N1962MRemoveStonestoMinimizetheTotal().minStoneSum(piles, k);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //1.heap
    //Runtime: 397 ms 97%; Memory: 55.2 MB 96%
    //Time: O(N + K * logN); Space: O(N);
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int sum = 0;
        for (int v : piles) {
            sum += v;
            heap.add(v);
        }

        while (k-- > 0) {
            int v = heap.poll();
            int x = v / 2;
            sum -= x;
            heap.add(v - x);
        }
        return sum;
    }
}
