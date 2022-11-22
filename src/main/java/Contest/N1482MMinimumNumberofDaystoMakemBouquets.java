package Contest;

import javafx.util.Pair;
import utils.comm;

import java.util.*;

import static java.time.LocalTime.now;

/**
 * You are given an integer array bloomDay, an integer m and an integer k.
 *
 * You want to make m bouquets. To make a bouquet, you need to use k adjacent flowers from the garden.
 *
 * The garden consists of n flowers, the ith flower will bloom in the bloomDay[i] and then can be used in exactly one bouquet.
 *
 * Return the minimum number of days you need to wait to be able to make m bouquets from the garden. If it is impossible to make m bouquets return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: bloomDay = [1,10,3,10,2], m = 3, k = 1
 * Output: 3
 * Explanation: Let us see what happened in the first three days. x means flower bloomed and _ means
 * flower did not bloom in the garden.
 * We need 3 bouquets each should contain 1 flower.
 * After day 1: [x, _, _, _, _]   // we can only make one bouquet.
 * After day 2: [x, _, _, _, x]   // we can only make two bouquets.
 * After day 3: [x, _, x, _, x]   // we can make 3 bouquets. The answer is 3.
 * Example 2:
 *
 * Input: bloomDay = [1,10,3,10,2], m = 3, k = 2
 * Output: -1
 * Explanation: We need 3 bouquets each has 2 flowers, that means we need 6 flowers. We only have 5 flowers
 * so it is impossible to get the needed bouquets and we return -1.
 * Example 3:
 *
 * Input: bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
 * Output: 12
 * Explanation: We need 2 bouquets each should have 3 flowers.
 * Here is the garden after the 7 and 12 days:
 * After day 7: [x, x, x, x, _, x, x]
 * We can make one bouquet of the first three flowers that bloomed. We cannot make another bouquet
 * from the last three flowers that bloomed because they are not adjacent.
 * After day 12: [x, x, x, x, x, x, x]
 * It is obvious that we can make two bouquets in different ways.
 *
 *
 * Constraints:
 *
 * bloomDay.length == n
 * 1 <= n <= 105
 * 1 <= bloomDay[i] <= 109
 * 1 <= m <= 106
 * 1 <= k <= n
 */

/**
 * M - [time: 120+
 */
public class N1482MMinimumNumberofDaystoMakemBouquets {

    public static void main(String[] args) {

        System.out.println(now());
        String[] data;

        doRun(93, new int[]{5,37,55,92,22,52,31,62,99,64,92,53,34,84,93,50,28}, 8, 2);

        doRun(1_000_000_000, new int[]{1_000_000_000, 1_000_000_000}, 1, 1);

        doRun(3, new int[]{1,10,3}, 2, 1);

        doRun(12, new int[]{7,7,7,7,12,7,7}, 2, 3);
        doRun(3, new int[]{1,10,3,10,2}, 3, 1);


        doRun(12, new int[]{7,7,7,7,12,7,7}, 2, 3);
        doRun(-1, new int[]{1,10,3,10,2}, 3, 2);


        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[] bloomDay, int m, int k) {
        int res = new N1482MMinimumNumberofDaystoMakemBouquets().minDays(bloomDay, m, k);
        comm.print(expect, res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //用估计的天数去试算。 天数通过binary search 进行得出
    //  x : 未成功； x+ 1: 成功；   则x + 1为答案
    //Runtime: 20 ms, faster than 98.18% of Java online submissions for Minimum Number of Days to Make m Bouquets.
    //Memory Usage: 50.6 MB, less than 95.17% of Java online submissions for Minimum Number of Days to Make m Bouquets.
    //binary search
    //Time: O(lg1_000_000_000 * N); Space: O(1)
    //Time: O(N); Space: O(1)
    public int minDays(int[] bloomDay, int m, int k) {
        if (bloomDay.length < m * k) return -1;

        //Binary Search
        int left = 1, right = 1_000_000_000;
        while (left <= right) {
            int mid = (right + left) / 2;
            if (isBloomAllAfter(bloomDay, m, k, mid)) right = mid - 1;
            else left = mid + 1;
        }
        return left;
    }

    //第day天后，是否可以得到所有花束
    //Time: O(N); Space: O(1)
    private boolean isBloomAllAfter(int[] bloomDay, int m, int k, int day){
        int bouquet = 0, count = 0;
        for (int i = 0; i < bloomDay.length; i++) {
            if (bloomDay[i] > day) {
                bouquet += count / k;
                count = 0;
            } else count++;
        }
        bouquet += count / k;
        return bouquet >= m;
    }


    //TLE
    //brute force
    public int minDays_2(int[] bloomDay, int m, int k) {
        memo = new HashMap<>();

        //提前算好每组的最大天数
        maxDay = new int[bloomDay.length];
        int c = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < bloomDay.length; i++) {
            if (c++ >= k)
                maxDay[i - k] = deque.peekFirst() == bloomDay[i-k] ? deque.pollFirst(): deque.peekFirst();
            while (!deque.isEmpty() && deque.peekLast() < bloomDay[i])
                deque.pollLast();
            deque.addLast(bloomDay[i]);
        }

        if (!deque.isEmpty() )
            maxDay[bloomDay.length - k] = deque.pollFirst();

        int res = helper(bloomDay, m ,k, 0);
        return res == Integer.MAX_VALUE?  -1: res;
    }
    int[] maxDay;
    Map<Pair<Integer,Integer>, Integer> memo ;
    private int helper(int[] bloomDay, int m, int k, int begin) {
        if (begin + m * k > bloomDay.length) return Integer.MAX_VALUE;

        Pair<Integer, Integer> pair = new Pair<>(m, begin);
        if (memo.containsKey(pair)) return memo.get(pair);

        int res = Integer.MAX_VALUE;
        for (int i = begin; i < bloomDay.length - k * m + 1; i++){
            int current = maxDay[i];
            if (m > 1)
                current = Math.max(current, helper(bloomDay, m - 1, k, i + k));
            res = Math.min(res, current);
        }

        memo.put(pair, res);
        return res;
    }


    //TLE
    public int minDays_1(int[] bloomDay, int m, int k) {

        Set<Integer> set = new TreeSet<>();//(o1, o2) -> o2 - o1);
        for (int bd : bloomDay) set.add(bd);

        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int maxDays : set)
            map.put(maxDays, new ArrayList<>());

        for (int i = 0; i < bloomDay.length; i++){
            for (int maxDays : set){
                if (bloomDay[i] <= maxDays) {
                    List<int[]> list = map.get(maxDays);

                    if (list.size() <= 0  || list.get(list.size()-1)[0] + 1 != i){
                        list.add(new int[]{i, 0});
                    }
                    list.get(list.size()-1)[0] = i;
                    list.get(list.size()-1)[1]++;
                }
            }
        }

        for (int maxDays : set) {
            List<int[]> list = map.get(maxDays);
            int count = m;
            for (int[] item: list) {
                count -= item[1] / k;
//                while (item[1] >= k && count > 0) {
//                    count--; item[1] -= k;
//                }
                if (count <= 0) return maxDays;
            }
        }
        return -1;
    }

}
