package Contest;


import javafx.util.Pair;

import java.util.Arrays;

import static java.time.LocalTime.now;


/**
 * You are given a 0-indexed integer array stations of length n, where stations[i] represents the number of power stations in the ith city.
 *
 * Each power station can provide power to every city in a fixed range. In other words, if the range is denoted by r, then a power station at city i can provide power to all cities j such that |i - j| <= r and 0 <= i, j <= n - 1.
 *
 * Note that |x| denotes absolute value. For example, |7 - 5| = 2 and |3 - 10| = 7.
 * The power of a city is the total number of power stations it is being provided power from.
 *
 * The government has sanctioned building k more power stations, each of which can be built in any city, and have the same range as the pre-existing ones.
 *
 * Given the two integers r and k, return the maximum possible minimum power of a city, if the additional power stations are built optimally.
 *
 * Note that you can build the k power stations in multiple cities.
 *
 *
 *
 * Example 1:
 *
 * Input: stations = [1,2,4,5,0], r = 1, k = 2
 * Output: 5
 * Explanation:
 * One of the optimal ways is to install both the power stations at city 1.
 * So stations will become [1,4,4,5,0].
 * - City 0 is provided by 1 + 4 = 5 power stations.
 * - City 1 is provided by 1 + 4 + 4 = 9 power stations.
 * - City 2 is provided by 4 + 4 + 5 = 13 power stations.
 * - City 3 is provided by 5 + 4 = 9 power stations.
 * - City 4 is provided by 5 + 0 = 5 power stations.
 * So the minimum power of a city is 5.
 * Since it is not possible to obtain a larger power, we return 5.
 * Example 2:
 *
 * Input: stations = [4,4,4,4], r = 0, k = 3
 * Output: 4
 * Explanation:
 * It can be proved that we cannot make the minimum power of a city greater than 4.
 *
 *
 * Constraints:
 *
 * n == stations.length
 * 1 <= n <= 105
 * 0 <= stations[i] <= 105
 * 0 <= r <= n - 1
 * 0 <= k <= 109
 */
//2528. Maximize the Minimum Powered City
public class N6290HMaximizetheMinimumPoweredCity {

    static public void main(String... args) {
        System.out.println(now());
        System.out.println("==================");
        doRun(5, new int[]{1,2,4,5,0}, 1, 2);

        doRun(52, new int[]{13,12,8,14,7}, 2, 23);

        doRun(5, new int[]{4,4,4,4}, 0, 7);
        doRun(4, new int[]{4,4,4,4}, 0, 3);

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(long expect, int[] stations, int r, int k) {
        long res = new N6290HMaximizetheMinimumPoweredCity().maxPower(stations, r, k);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //2.Binary search
    //Runtime: 63ms 88%; Memory: 50.7MB 100%
    //Time: O(N + logM * (R + N)); Space: O(N)
    //let N be the number of cities
    //let M the number of stations in all cities
    //R be the range
    public long maxPower_2(int[] stations, int r, int k) {
        long left = Integer.MAX_VALUE, right = k;
        for (int station : stations) {
            right += station;
            left = Math.min(left, station);
        }

        while (left <= right) {
            long mid = (left + right) >> 1;
            if (helper_2(Arrays.copyOf(stations, stations.length), r, k, mid))
                left = mid + 1;
            else
                right = mid - 1;
        }
        return right;
    }

    //Time: O(R + N); Space: O(1)
    private boolean helper_2(int[] stations, int r, int k, long x){
        long res = 0, used = 0;
        for (int i = 0; i < r; i++)
            res += stations[i];

        for (int i = 0; i < stations.length && k >= used; i++) {
            if (i + r < stations.length) res += stations[i + r];
            if (i - r > 0) res -= stations[i - r - 1];

            long diff = Math.max(0, x - res);
            res += diff;
            stations[Math.min(stations.length - 1, i + r)] += diff;
            used += diff;
        }
        return k >= used;
    }

    //1.iteration
    //TLE
    //Time: O(K * (R + N); Space: O(1)
    public long maxPower(int[] stations, int r, int k) {
        long res = 0;
        while (k >= 0) {
            Pair<Integer, Long> pair = helper_min(stations, r);
            res = pair.getValue();
            int idx = Math.min(stations.length - 1, pair.getKey() + r);
            stations[idx]++;
            k--;
        }
        return res;
    }

    //Time: O(R + N); Space: O(1)
    private Pair<Integer, Long> helper_min(int[] stations, int r){
        long power = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < r; i++)
            power += stations[i];

        int idx = 0;
        for (int i = 0; i < stations.length; i++) {
            if (i + r < stations.length) power += stations[i + r];
            if (i - r > 0) power -= stations[i - r - 1];

            if (power < min) {
                min = power; idx = i;
            }
        }
        return new Pair<>(idx, min);
    }



}


