package LeetCode;


import java.util.*;

import static java.time.LocalTime.now;

/**
 * You are given two integers n and k and two integer arrays speed and efficiency both of length n.
 * There are n engineers numbered from 1 to n. speed[i] and efficiency[i] represent the speed and
 * efficiency of the ith engineer respectively.
 *
 * Choose at most k different engineers out of the n engineers to form a team with the maximum performance.
 *
 * The performance of a team is the sum of their engineers' speeds multiplied by the minimum efficiency
 * among their engineers.
 *
 * Return the maximum performance of this team. Since the answer can be a huge number, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 2
 * Output: 60
 * Explanation:
 * We have the maximum performance of the team by selecting engineer 2 (with speed=10 and efficiency=4)
 * and engineer 5 (with speed=5 and efficiency=7). That is, performance = (10 + 5) * min(4, 7) = 60.
 * Example 2:
 *
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 3
 * Output: 68
 * Explanation:
 * This is the same example as the first but k = 3. We can select engineer 1, engineer 2 and engineer 5
 * to get the maximum performance of the team. That is, performance = (2 + 10 + 5) * min(5, 4, 7) = 68.
 * Example 3:
 *
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 4
 * Output: 72
 *
 *
 * Constraints:
 *
 * 1 <= k <= n <= 105
 * speed.length == n
 * efficiency.length == n
 * 1 <= speed[i] <= 105
 * 1 <= efficiency[i] <= 108
 */

/**
 * H - [Time: 120+
 */
//2542.Maximum Subsequence Score
public class N1383HMaximumPerformanceofaTeam {

    public static void main(String[] args){
        System.out.println(now());
        int[] data;

        doRun(56, 3, new int[]{2,8,2}, new int[]{2,7,1}, 2);

        doRun(40, 6, new int[]{2,10,3,1,5,8}, new int[]{5,4,3,9,7,2}, 1);
        doRun(60, 6, new int[]{2,10,3,1,5,8}, new int[]{5,4,3,9,7,2}, 2);
        doRun(68, 6, new int[]{2,10,3,1,5,8}, new int[]{5,4,3,9,7,2}, 3);

        doRun(72, 6, new int[]{2,10,3,1,5,8}, new int[]{5,4,3,9,7,2}, 4);

        System.out.println(now());
        System.out.println("==================");
    }

    private static void doRun(int expected, int n, int[] speed, int[] efficiency, int k){
        int res = new N1383HMaximumPerformanceofaTeam().maxPerformance(n, speed, efficiency, k);
        System.out.println("["+(expected == res)+"].expected:"+ expected+".res:"+res);
    }


    //Runtime: 57 ms, faster than 90.91% of Java online submissions for Maximum Performance of a Team.
    //Memory Usage: 53.7 MB, less than 96.97% of Java online submissions for Maximum Performance of a Team.
    //Time: O(N + N * LogN + N * LogK); Space: O(N + LogN + K)
    //Time: O(N * LogN + N * LogK); Space: O(N + K)
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int MODULO = 1_000_000_007;// + 7;

        //Time: O(N) Space: O(N)
        int[][] candidates = new int[efficiency.length][2];
        for (int i = 0; i < efficiency.length; i++)
            candidates[i] = new int[]{efficiency[i], speed[i]};

        //Time: O(NlogN); Space: O(logN)
        Arrays.sort(candidates, Comparator.comparingInt(o -> -o[0]));

        //Space: O(K)
        // keep the top (k-1) speeds
        PriorityQueue<Integer> speedHeap = new PriorityQueue<>(k);

        long speedSum = 0, res = 0;
        //Time: O(N*LogK)
        for (int[] engineer : candidates) {
            if (speedHeap.size()  > k - 1 )
                speedSum -= speedHeap.poll();   //remove the smallest one

            speedHeap.add(engineer[1]);     //Time: O(LogK)
            speedSum += engineer[1];
            res = Math.max(res, speedSum * engineer[0]);
        }
        return (int) (res % MODULO);
    }


}
