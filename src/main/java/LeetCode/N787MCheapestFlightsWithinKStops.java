package LeetCode;


import java.io.IOException;
import java.util.*;

import static java.time.LocalTime.now;

/**
 * There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.
 *
 * You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
 * Output: 700
 * Explanation:
 * The graph is shown above.
 * The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
 * Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.
 * Example 2:
 *
 *
 * Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
 * Output: 200
 * Explanation:
 * The graph is shown above.
 * The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.
 * Example 3:
 *
 *
 * Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
 * Output: 500
 * Explanation:
 * The graph is shown above.
 * The optimal path with no stops from city 0 to 2 is marked in red and has cost 500.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 100
 * 0 <= flights.length <= (n * (n - 1) / 2)
 * flights[i].length == 3
 * 0 <= fromi, toi < n
 * fromi != toi
 * 1 <= pricei <= 104
 * There will not be any multiple flights between two cities.
 * 0 <= src, dst, k < n
 * src != dst
 */
public class N787MCheapestFlightsWithinKStops {


    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");

        doRun(7, 5, new int[][]{{0,1,5}, {1,2,5},{0,3,2},{3,1,2},{1,4,1},{4,2,1}} , 0, 2, 2);
        doRun(6, 4, new int[][]{{0,1,1},{0,2,5},{1,2,1},{2,3,1}} , 0, 3, 1);
        doRun(1, 3, new int[][]{{0,1,2}, {1,2,1}, {2,0,10}} , 1, 2, 1);


        doRun(700, 4, new int[][]{{0,1,100}, {1,2,100},{2,0,100}, {1,3,600}, {2,3,200}} , 0, 3, 1);

        doRun(200, 3, new int[][]{{0,1,100}, {1,2,100},{0,2,500}} , 0, 2, 1);

        doRun(500, 3, new int[][]{{0,1,100}, {1,2,100},{0,2,500}} , 0, 2, 0);


        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, int n, int[][] flights, int src, int dst, int k) {
        int res = new N787MCheapestFlightsWithinKStops().findCheapestPrice(n, flights, src, dst, k);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //4.Dijkstra's algo
    //Runtime: 15 ms, faster than 51.40% of Java online submissions for Cheapest Flights Within K Stops.
    //Memory Usage: 47.5 MB, less than 37.51% of Java online submissions for Cheapest Flights Within K Stops.
    //Time: O(N + E * K * log(E * K) ); Space: O(N + E * K)
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        //Build graph
        //Time: O(N + E); Space: O(E)
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < graph.length; i++) graph[i] = new ArrayList<>();
        for (int[] flight: flights) graph[flight[0]].add(flight);

        //Space: O(E * K)
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        heap.add(new int[]{0, src, 0}); //distance, node, stop

        int[] stops = new int[n];
        Arrays.fill(stops, Integer.MAX_VALUE);

        //Time: O(E * K * log(E * K));
        while (!heap.isEmpty()) {
            int[] node = heap.poll();

            int distance = node[0];
            int v = node[1];
            int stop = node[2];

            if (stop > stops[v] || stop > k + 1) continue;
            stops[v] = stop;

            if (v == dst) return distance;

            for(int[] flight : graph[v])
                heap.add(new int[]{distance + flight[2], flight[1], stop + 1});
        }
        return -1;
    }

    //3.bfs
    //Runtime: 3 ms, faster than 99.59% of Java online submissions for Cheapest Flights Within K Stops.
    //Memory Usage: 43.7 MB, less than 83.47% of Java online submissions for Cheapest Flights Within K Stops.
    //Time: O(N + E * K); Space: O(N + E * K)
    public int findCheapestPrice_3(int n, int[][] flights, int src, int dst, int k) {
        //Time: O(N); Space: O(N)
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        //Build graph
        //Time: O(N + E); Space: O(E)
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < graph.length; i++) graph[i] = new ArrayList<>();
        for (int[] flight: flights) graph[flight[0]].add(flight);

        int res = Integer.MAX_VALUE;

        //BFS
        //Time: O(E * K); Space: O(E * K)
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{src, 0});
        while (k >= 0 && !queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                int[] node = queue.poll();
                for (int[] flight : graph[node[0]]){
                    int cost = node[1] + flight[2];
                    if (cost >= dist[flight[1]]) continue;
                    if (flight[1] == dst) res = cost;
                    dist[flight[1]] = cost;
                    queue.add(new int[]{flight[1], cost});
                }
            }
            k--;
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    //2.Bellman dp 1d-Array
    //Runtime: 2 ms, faster than 99.87% of Java online submissions for Cheapest Flights Within K Stops.
    //Memory Usage: 44.1 MB, less than 81.52% of Java online submissions for Cheapest Flights Within K Stops.
    //Time: Time: O(K * (N + E)); Space: O(N)
    public int findCheapestPrice_2(int n, int[][] flights, int src, int dst, int k) {
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[src] = 0;

        //Time: O(K * (N + E))
        for (int i = 0; i <= k; i++) {
            int[] lastDP = Arrays.copyOf(dp, dp.length);
            boolean hasChanged = false;

            for (int[] flight: flights){
                if (lastDP[flight[0]] == Integer.MAX_VALUE) continue;
                    int cost = lastDP[flight[0]] + flight[2];
                    if (cost < dp[flight[1]]) {
                        dp[flight[1]] = cost;
                        hasChanged = true;
                    }
            }
            if (!hasChanged) break;
        }
        return dp[dst] == Integer.MAX_VALUE ? -1 : dp[dst];
    }


    //1.Bellman algorithm
    //Runtime: 33 ms, faster than 15.48% of Java online submissions for Cheapest Flights Within K Stops.
    //Memory Usage: 48.4 MB, less than 14.78% of Java online submissions for Cheapest Flights Within K Stops.
    //Time: O(K + N + E + K * N * E); Space: O(N + E)
    //Time: O(K * N * E); Space: O(N + E)
    public int findCheapestPrice_1(int n, int[][] flights, int src, int dst, int k) {

        int[][] dp = new int[k + 2][n];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][src] = 0;
        }

        //build graph
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < graph.length; i++) graph[i] = new ArrayList<>();
        for (int[] flight: flights) graph[flight[1]].add(flight); //dst

        for (int i = 1; i <= k + 1; i++) {
            for (int j = 0; j < n; j++) {
                if (j == src) continue;
                for(int[] flight: graph[j]){
                    int cost = dp[i - 1][flight[0]] == Integer.MAX_VALUE ? Integer.MAX_VALUE : dp[i - 1][flight[0]] + flight[2];
                    dp[i][j]= Math.min(dp[i][j], cost);
                }
            }
        }
        return dp[k + 1][dst] == Integer.MAX_VALUE ? -1 : dp[k + 1][dst] ;
    }
}
