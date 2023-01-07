package LeetCode;


import java.io.IOException;

import static java.time.LocalTime.now;

/**
 * There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
 *
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its next (i + 1)th station. You begin the journey with an empty tank at one of the gas stations.
 *
 * Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1. If there exists a solution, it is guaranteed to be unique
 *
 *
 *
 * Example 1:
 *
 * Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 * Output: 3
 * Explanation:
 * Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 4. Your tank = 4 - 1 + 5 = 8
 * Travel to station 0. Your tank = 8 - 2 + 1 = 7
 * Travel to station 1. Your tank = 7 - 3 + 2 = 6
 * Travel to station 2. Your tank = 6 - 4 + 3 = 5
 * Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
 * Therefore, return 3 as the starting index.
 * Example 2:
 *
 * Input: gas = [2,3,4], cost = [3,4,3]
 * Output: -1
 * Explanation:
 * You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
 * Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 0. Your tank = 4 - 3 + 2 = 3
 * Travel to station 1. Your tank = 3 - 3 + 3 = 3
 * You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
 * Therefore, you can't travel around the circuit once no matter where you start.
 *
 *
 * Constraints:
 *
 * n == gas.length == cost.length
 * 1 <= n <= 105
 * 0 <= gas[i], cost[i] <= 104
 */
public class N134MGasStation {

    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");

        doRun(3, new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2});
        doRun(0, new int[]{3,1,1}, new int[]{1,2,2});

        System.out.println(now());
        System.out.println("==================");
    }

    //93
    static private void doRun(int expect, int[] gas, int[] cost) {
        int res = new N134MGasStation().canCompleteCircuit(gas, cost);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //3.one pass
    //Runtime: 2ms 89%; Memory: 62MB 82%
    //Time: O(N); Space: O(1)
    public int canCompleteCircuit3(int[] gas, int[] cost) {
        int curr_tank = 0, total_tank = 0, station = -1;
        for (int i = 0; i < gas.length; i++) {
            int tmp = gas[i] - cost[i];
            total_tank += tmp;
            curr_tank += tmp;
            if (curr_tank < 0){
                curr_tank = 0;
                station = i;
            }
        }
        return total_tank >= 0 ? station + 1: -1;
    }

    //2.two pointers
    //Runtime: 8ms 39%; Memory: 62mb 61%
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int tank = 0, n = gas.length;
        int idx = 0, start_station = 0, count = 0;

        while (count < n) {
            tank += gas[idx] - cost[idx];
            idx = (idx + 1) % n;
            count++;

            while (tank < 0 && count > 0) {
                tank -= (gas[start_station] - cost[start_station]);
                start_station = (start_station + 1) % n;
                count--;
                if (start_station == 0) return -1;
            }
        }
        return start_station;
    }

    //1.Brute force
    //TLE
    //Time: O(N * N); Space: O(1)
    public int canCompleteCircuit_1(int[] gas, int[] cost) {
        for(int i = 0; i < gas.length; i++){
            if (cost[i] > gas[i]) continue;
            if (helper(gas, cost, i)) return i;
        }
        return -1;
    }

    private boolean helper(int[] gas, int[] cost, int begin) {
        int n = gas.length;
        int idx = begin;
        int tank = gas[idx] - cost[idx];

        while ((idx + 1) % n != begin && tank >= 0){
            idx = (idx + 1) % n;
            tank += gas[idx] - cost[idx];
        }
        return (idx + 1) % n == begin && tank >= 0;
    }

}
