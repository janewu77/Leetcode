package LeetCode;

/**
 * A car travels from a starting position to a destination which is target miles east of the starting position.
 *
 * There are gas stations along the way. The gas stations are represented as an array stations
 * where stations[i] = [positioni, fueli] indicates that the ith gas station is positioni miles east
 * of the starting position and has fueli liters of gas.
 *
 * The car starts with an infinite tank of gas, which initially has startFuel liters of fuel in it.
 * It uses one liter of gas per one mile that it drives. When the car reaches a gas station, it may
 * stop and refuel, transferring all the gas from the station into the car.
 *
 * Return the minimum number of refueling stops the car must make in order to reach its destination.
 * If it cannot reach the destination, return -1.
 *
 * Note that if the car reaches a gas station with 0 fuel left, the car can still refuel there.
 * If the car reaches the destination with 0 fuel left, it is still considered to have arrived.
 *
 *
 *
 * Example 1:
 *
 * Input: target = 1, startFuel = 1, stations = []
 * Output: 0
 * Explanation: We can reach the target without refueling.
 * Example 2:
 *
 * Input: target = 100, startFuel = 1, stations = [[10,100]]
 * Output: -1
 * Explanation: We can not reach the target (or even the first gas station).
 * Example 3:
 *
 * Input: target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
 * Output: 2
 * Explanation: We start with 10 liters of fuel.
 * We drive to position 10, expending 10 liters of fuel.  We refuel from 0 liters to 60 liters of gas.
 * Then, we drive from position 10 to position 60 (expending 50 liters of fuel),
 * and refuel from 10 liters to 50 liters of gas.  We then drive to and reach the target.
 * We made 2 refueling stops along the way, so we return 2.
 *
 *
 * Constraints:
 *
 * 1 <= target, startFuel <= 109
 * 0 <= stations.length <= 500
 * 0 <= positioni <= positioni+1 < target
 * 1 <= fueli < 109
 *
 */


import javafx.util.Pair;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import static java.time.LocalTime.now;

/**
 *
 */
public class N871HMinimumNumberofRefuelingStops {

    public static void main(String[] args) {

        System.out.println(now());
        int[][] data;

        System.out.println("========doRun_demo==========");

        data = new int[][]{{10,10}, {20,10}, {25, 5}, {30, 50}};
        doRun_demo(3, 80, 10, data);

        data = new int[][]{{5, 100},{10,10}, {20,10}, {25, 5}, {30, 50}};
        doRun_demo(1, 80, 10, data);

        data = new int[][]{};
        doRun_demo(0, 1, 1, data);

        data = new int[][]{};
        doRun_demo(-1, 100, 1, data);

        data = new int[][]{{10,100}};
        doRun_demo(-1, 100, 1, data);

        data = new int[][]{{10,60}, {20,30}, {30, 30}, {60,40}};
        doRun_demo(2, 100, 10, data);

        data = new int[][]{{25,25}, {50,25}, {75, 25}};
        doRun_demo(3, 100, 25, data);

        data = new int[][]{{13,21}, {26,115}, {100,47}, {225,99}, {299,141} ,{444,198}, {608,190}, {636,157}, {647,255}, {841,123}};
        doRun_demo(4, 1000, 299, data);

        data = new int[][]{{7,217},{10,211},{17,146},{21,232},{25,310},{48,175},{53,23},{63,158},{71,292},{96,85},{100,302},{102,295},{116,110},{122,46},{131,20},{132,19},{141,13},{163,85},{169,263},{179,233},{191,169},{215,163},{224,231},{228,282},{256,115},{259,3},{266,245},{283,331},{299,21},{310,224},{315,188},{328,147},{345,74},{350,49},{379,79},{387,276},{391,92},{405,174},{428,307},{446,205},{448,226},{452,275},{475,325},{492,310},{496,94},{499,313},{500,315},{511,137},{515,180},{519,6},{533,206},{536,262},{553,326},{561,103},{564,115},{582,161},{593,236},{599,216},{611,141},{625,137},{626,231},{628,66},{646,197},{665,103},{668,8},{691,329},{699,246},{703,94},{724,277},{729,75},{735,23},{740,228},{761,73},{770,120},{773,82},{774,297},{780,184},{791,239},{801,85},{805,156},{837,157},{844,259},{849,2},{860,115},{874,311},{877,172},{881,109},{888,321},{894,302},{899,321},{908,76},{916,241},{924,301},{933,56},{960,29},{979,319},{983,325},{988,190},{995,299},{996,97}};
        doRun_demo(4, 1000, 10, data);

        data = new int[][]{{5510987,84329695},{10682248,76273791},{56227783,136858069},{91710087,18854476},{111148380,127134059},{165982642,122930004},{184216180,124802819},{217578071,7123113},{233719001,95862544},{339631786,7676497},{349762649,136128214},{403119403,21487501},{423890164,61095325},{424072629,50415446},{572994480,13561367},{609623597,69207102},{662818314,84432133},{678679727,20403175},{682325369,14288077},{702137485,6426204},{716318901,47662322},{738137702,129579140},{761962118,23765733},{820353983,70497719},{895811889,75533360}};
        doRun_demo(7, 1000000000, 145267354, data);


        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun_demo(int expect, int target, int startFuel, int[][] stations) {
        int res = new N871HMinimumNumberofRefuelingStops().minRefuelStops(target, startFuel, stations);
//        String res = comm.toString(res1);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    //from solution
    //Runtime: 3 ms, faster than 97.58% of Java online submissions for Minimum Number of Refueling Stops.
    //Memory Usage: 42.2 MB, less than 96.14% of Java online submissions for Minimum Number of Refueling Stops.
    //Time: O(N * lgN); Space: O(N)
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());

        int res = 0, prev = 0;
        for (int[] station: stations) {
            // startFuel = startFuel + prev - station[0];
            startFuel -= (station[0] - prev);

            while (!priorityQueue.isEmpty() && startFuel < 0){
                startFuel += priorityQueue.poll();
                res++;
            }
            if (startFuel < 0) return -1;

            priorityQueue.add(station[1]);
            prev = station[0];
        }

        //the last stop: target
        startFuel -= (target - prev);
        while (!priorityQueue.isEmpty() && startFuel < 0){
            startFuel += priorityQueue.poll();
            res++;
        }
        if (startFuel < 0) return -1;

        return res;
    }

    //Runtime: 6 ms, faster than 54.48% of Java online submissions for Minimum Number of Refueling Stops.
    //Memory Usage: 42 MB, less than 99.10% of Java online submissions for Minimum Number of Refueling Stops.
    //DP
    //Time: O(N * N); Space: O(N)
    public int minRefuelStops_2(int target, int startFuel, int[][] stations) {
        int[] dp = new int[stations.length + 1];
        dp[0] = startFuel;

        for (int i = 0; i < stations.length; i++){
            for (int j = i; j >= 0; j--){
                if (dp[j] >= stations[i][0])
                    dp[j + 1] = Math.max(dp[j + 1], dp[j] + stations[i][1]);
            }
        }

        for(int i = 0; i < dp.length; i++)
            if (dp[i] >= target) return i;

        return  -1;
    }


    //TLE
    public int minRefuelStops_1(int target, int startFuel, int[][] stations) {
        int res = helper(target, startFuel, stations, 0);
        return res == Integer.MAX_VALUE ? -1: res;
    }

    Map<Pair<Integer, Integer>, Integer> memo = new HashMap<>();
    private int helper(int target, int startFuel, int[][] stations, int begin){
        if (startFuel >= target) return 0;
        if (begin >= stations.length) return Integer.MAX_VALUE;
        if (startFuel < stations[begin][0]) return Integer.MAX_VALUE;

        Pair<Integer, Integer> pair = new Pair<>(begin, startFuel);
        if (memo.containsKey(pair)) return memo.get(pair);

        int res = Integer.MAX_VALUE;
        for (int i = begin; i < stations.length; i++) {
            if (startFuel >= stations[i][0]) {
                 // = startFuel - stations[i][0] + stations[i][1] + stations[i][0],
                res = Math.min(res,
                         helper(target, startFuel + stations[i][1], stations, i + 1));
            }else break;
        }

        res = res == Integer.MAX_VALUE ? res: res + 1;
        memo.put(pair, res);
        return res;
    }

}
