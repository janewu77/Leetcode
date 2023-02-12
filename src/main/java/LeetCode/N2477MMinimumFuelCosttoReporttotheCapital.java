package LeetCode;


import java.util.*;

import static java.time.LocalTime.now;

/**
 * There is a tree (i.e., a connected, undirected graph with no cycles) structure country network consisting of n cities numbered from 0 to n - 1 and exactly n - 1 roads. The capital city is city 0. You are given a 2D integer array roads where roads[i] = [ai, bi] denotes that there exists a bidirectional road connecting cities ai and bi.
 *
 * There is a meeting for the representatives of each city. The meeting is in the capital city.
 *
 * There is a car in each city. You are given an integer seats that indicates the number of seats in each car.
 *
 * A representative can use the car in their city to travel or change the car and ride with another representative. The cost of traveling between two cities is one liter of fuel.
 *
 * Return the minimum number of liters of fuel to reach the capital city.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: roads = [[0,1],[0,2],[0,3]], seats = 5
 * Output: 3
 * Explanation:
 * - Representative1 goes directly to the capital with 1 liter of fuel.
 * - Representative2 goes directly to the capital with 1 liter of fuel.
 * - Representative3 goes directly to the capital with 1 liter of fuel.
 * It costs 3 liters of fuel at minimum.
 * It can be proven that 3 is the minimum number of liters of fuel needed.
 * Example 2:
 *
 *
 * Input: roads = [[3,1],[3,2],[1,0],[0,4],[0,5],[4,6]], seats = 2
 * Output: 7
 * Explanation:
 * - Representative2 goes directly to city 3 with 1 liter of fuel.
 * - Representative2 and representative3 go together to city 1 with 1 liter of fuel.
 * - Representative2 and representative3 go together to the capital with 1 liter of fuel.
 * - Representative1 goes directly to the capital with 1 liter of fuel.
 * - Representative5 goes directly to the capital with 1 liter of fuel.
 * - Representative6 goes directly to city 4 with 1 liter of fuel.
 * - Representative4 and representative6 go together to the capital with 1 liter of fuel.
 * It costs 7 liters of fuel at minimum.
 * It can be proven that 7 is the minimum number of liters of fuel needed.
 * Example 3:
 *
 *
 * Input: roads = [], seats = 1
 * Output: 0
 * Explanation: No representatives need to travel to the capital city.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 105
 * roads.length == n - 1
 * roads[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * roads represents a valid tree.
 * 1 <= seats <= 105
 */
public class N2477MMinimumFuelCosttoReporttotheCapital {

    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");
        doRun(3, new int[][]{{0,1},{0,2},{0,3}}, 2);
        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(long expect,int[][] roads, int seats) {
        long res = new N2477MMinimumFuelCosttoReporttotheCapital().minimumFuelCost(roads, seats);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //2.BFS
    //Runtime: 54ms 95%; Memory: 81MB 97%
    //Time: O(V + E); Space: O(V + E)
    public long minimumFuelCost(int[][] roads, int seats) {
        List<Integer>[] graph = new List[roads.length + 1];
        int[] degrees = new int[roads.length + 1];
        for (int i = 0; i <= roads.length; i++)
            graph[i] = new ArrayList<>();

        for(int[] road : roads){
            graph[road[0]].add(road[1]);
            graph[road[1]].add(road[0]);
            degrees[road[0]]++;
            degrees[road[1]]++;
        }
        return helper_bfs(graph, degrees, seats);
    }

    //BFS: from leaf to root
    private long helper_bfs(List<Integer>[] graph, int[] degrees, double seats){
        long res = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < degrees.length; i++)
            if (degrees[i] == 1) queue.add(i);

        int[] counter = new int[degrees.length];

        while (!queue.isEmpty()) {
            int v = queue.poll();
            counter[v]++;
            for (int neighbour : graph[v]) {
                if (degrees[neighbour] == 0) continue;
                counter[neighbour] += counter[v];
                res += Math.ceil(counter[v] / seats);

                degrees[neighbour]--;
                if (degrees[neighbour] == 1 && neighbour != 0 )
                    queue.add(neighbour);
            }
            degrees[v] = 0;
        }
        return res;
    }


    //1.DFS
    //Runtime: 59ms 85%; Memory: 108MB 44%
    //Time: O(V + E); Space: O(V + E)
    public long minimumFuelCost_1(int[][] roads, int seats) {
        List<Integer>[] graph = new List[roads.length + 1];
        for (int i = 0; i <= roads.length; i++)
            graph[i] = new ArrayList<>();

        for(int[] road : roads){
            graph[road[0]].add(road[1]);
            graph[road[1]].add(road[0]);
        }

        helper_dfs(graph, seats, 0, -1);
        return res;
    }

    private long res = 0;
    private long helper_dfs(List<Integer>[] graph, double seats,
                            int node, int parent) {
        int count = 1;
        for (int neighbour : graph[node]) {
            if (neighbour == parent) continue;
            long c = helper_dfs(graph, seats, neighbour, node);
            res += Math.ceil(c / seats);
            count += c;
        }
        return count;
    }

}




