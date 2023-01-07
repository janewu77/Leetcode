package Contest;


import java.util.*;

/**
 * There is an undirected graph consisting of n nodes numbered from 0 to n - 1. You are given a 0-indexed integer array vals of length n where vals[i] denotes the value of the ith node.
 *
 * You are also given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.
 *
 * A star graph is a subgraph of the given graph having a center node containing 0 or more neighbors. In other words, it is a subset of edges of the given graph such that there exists a common node for all edges.
 *
 * The image below shows star graphs with 3 and 4 neighbors respectively, centered at the blue node.
 *
 *
 * The star sum is the sum of the values of all the nodes present in the star graph.
 *
 * Given an integer k, return the maximum star sum of a star graph containing at most k edges.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: vals = [1,2,3,4,10,-10,-20], edges = [[0,1],[1,2],[1,3],[3,4],[3,5],[3,6]], k = 2
 * Output: 16
 * Explanation: The above diagram represents the input graph.
 * The star graph with the maximum star sum is denoted by blue. It is centered at 3 and includes its neighbors 1 and 4.
 * It can be shown it is not possible to get a star graph with a sum greater than 16.
 * Example 2:
 *
 * Input: vals = [-5], edges = [], k = 0
 * Output: -5
 * Explanation: There is only one possible star graph, which is node 0 itself.
 * Hence, we return -5.
 *
 *
 * Constraints:
 *
 * n == vals.length
 * 1 <= n <= 105
 * -104 <= vals[i] <= 104
 * 0 <= edges.length <= min(n * (n - 1) / 2, 105)
 * edges[i].length == 2
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * 0 <= k <= n - 1
 */
public class N2497MMaximumStarSumofaGraph {


    //2.heap
    //Runtime: 75ms 84%; Memory: 85.6MB 97%
    //Time: O(N + E * logK + N * K * logK); Space: O(E)
    public int maxStarSum_2(int[] vals, int[][] edges, int k) {
        PriorityQueue<Integer>[] graph = new PriorityQueue[vals.length];
        for (int i = 0; i < graph.length; i++)
            graph[i] = new PriorityQueue<>(Comparator.comparingInt(a -> vals[a]));

        for (int[] edge: edges){
            graph[edge[0]].add(edge[1]);
            if (graph[edge[0]].size() > k) graph[edge[0]].poll();

            graph[edge[1]].add(edge[0]);
            if (graph[edge[1]].size() > k) graph[edge[1]].poll();
        }

        int res = Integer.MIN_VALUE;
        for (int i = 0; i < vals.length; i++) {
            PriorityQueue<Integer> neighbours = graph[i];

            int sum = vals[i];
            while (!neighbours.isEmpty()) {
                int node = neighbours.poll();
                sum += vals[node] > 0 ? vals[node] : 0;
            }
            res = Math.max(res, sum);
        }
        return res;
    }


    //1.sort
    //Runtime: 105ms 65%; Memory: 91.3MB 76%
    //Time: O(N + E + N * N * logN); Space: O(E + logN)
    public int maxStarSum(int[] vals, int[][] edges, int k) {
        List<Integer>[] graph = new List[vals.length];
        for (int i = 0; i < graph.length; i++)
            graph[i] = new ArrayList<>();

        for (int[] edge: edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int res = Integer.MIN_VALUE;
        for (int i = 0; i < vals.length; i++) {
            List<Integer> neighbours = graph[i];
            Collections.sort(neighbours, (a, b) -> vals[b] - vals[a]);

            int count = 0;
            int sum = vals[i];
            while (count < Math.min(k, neighbours.size()) && vals[neighbours.get(count)] > 0) {
                sum += vals[neighbours.get(count)];
                count++;
            }
            res = Math.max(res, sum);
        }
        return res;
    }
}
