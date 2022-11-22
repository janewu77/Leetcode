package LeetCode;

import java.io.IOException;
import java.util.*;

import static java.time.LocalTime.now;

/**
 * You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.
 *
 * We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 * Output: 2
 * Example 2:
 *
 * Input: times = [[1,2,1]], n = 2, k = 1
 * Output: 1
 * Example 3:
 *
 * Input: times = [[1,2,1]], n = 2, k = 2
 * Output: -1
 *
 *
 * Constraints:
 *
 * 1 <= k <= n <= 100
 * 1 <= times.length <= 6000
 * times[i].length == 3
 * 1 <= ui, vi <= n
 * ui != vi
 * 0 <= wi <= 100
 * All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
 */
public class N743MNetworkDelayTime {


    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");

        doRun(2,new int[][]{{2,1,1},{2,3,1},{3,4,1}}, 4, 2);
        doRun(1,new int[][]{{1,2,1}}, 2, 1);
        doRun(-1,new int[][]{{1,2,1}}, 2, 2);
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[][] times, int n, int k) {
        int res = new N743MNetworkDelayTime().networkDelayTime(times, n, k);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //3.BFS
    //Runtime: 20 ms, faster than 88.00% of Java online submissions for Network Delay Time.
    //Memory Usage: 62.5 MB, less than 73.74% of Java online submissions for Network Delay Time.
    //Time: O(N * E); Space:O(N * E)
    public int networkDelayTime(int[][] times, int n, int k) {

        //Time: O(E); Space:O(E)
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge: times) {
            graph.computeIfAbsent(edge[0], x -> new ArrayList<>());
            graph.get(edge[0]).add(edge);
        }

        //Time: O(N); Space:O(N)
        int[] resList = new int[n + 1];
        Arrays.fill(resList, Integer.MAX_VALUE);
        if (!graph.containsKey(k)) return -1;
        resList[k] = 0;

        //BFS
        //Time: O(N * E); Space:O(N * E)
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(k);
        while (!queue.isEmpty()){
            int node = queue.poll();
            for (int[] neighbour : graph.get(node)) {
                if (resList[neighbour[1]] > resList[node] + neighbour[2]) {
                    resList[neighbour[1]] = resList[node] + neighbour[2];
                    if (graph.containsKey(neighbour[1])) queue.add(neighbour[1]);
                }
            }
        }

        //Time: O(N)
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (resList[i] == Integer.MAX_VALUE) return -1;
            res = Math.max(res, resList[i]);
        }
        return res;
    }


    //2.DFS
    //Runtime: 70 ms, faster than 25.07% of Java online submissions for Network Delay Time.
    //Memory Usage: 63.1 MB, less than 72.33% of Java online submissions for Network Delay Time.
    //Time: O((N-1)! +  E * logE); Space:O(N + E)
    public int networkDelayTime_2(int[][] times, int n, int k) {

        //Time: O(E); Space:O(E)
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge: times) {
            graph.computeIfAbsent(edge[0], x -> new ArrayList<>());
            graph.get(edge[0]).add(edge);
        }

        //Time: O(E * logE); Space:O(logE)
        for (int node : graph.keySet())
            Collections.sort(graph.get(node), Comparator.comparingInt(a -> a[2]));

        //Time: O(N); Space:O(N)
        int[] resList = new int[n + 1];
        Arrays.fill(resList, Integer.MAX_VALUE);
        if (!graph.containsKey(k)) return -1;
        resList[k] = 0;

        //Time: O((N-1)!); Space:O(N)
        helper_DFS(graph, k, resList);

        //Time: O(N)
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (resList[i] == Integer.MAX_VALUE) return -1;
            res = Math.max(res, resList[i]);
        }
        return res;
    }

    private void helper_DFS(Map<Integer, List<int[]>> graph, int node, int[] resList){
        for (int[] neighbour : graph.get(node)) {
            if (resList[neighbour[1]] > resList[node] + neighbour[2]) {
                resList[neighbour[1]] = resList[node] + neighbour[2];
                if (graph.containsKey(neighbour[1]))
                    helper_DFS(graph, neighbour[1], resList);
            }
        }
    }

    //1.Dijkstra's Algorithm
    //Runtime: 10 ms, faster than 97.92% of Java online submissions for Network Delay Time.
    //Memory Usage: 46.6 MB, less than 88.43% of Java online submissions for Network Delay Time.
    //Time: O(E * log(N) + N); Space: O(E + N)
    public int networkDelayTime_1(int[][] times, int n, int k) {
        //Adjacency
        //Time: O(E); Space:O(E)
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge: times) {
            graph.computeIfAbsent(edge[0], x -> new ArrayList<>());
            graph.get(edge[0]).add(edge);
        }

        //Time: O(N); Space:O(N)
        int[] resList = new int[n + 1];
        Arrays.fill(resList, Integer.MAX_VALUE);

        //minHeap
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1] != 0 ? a[1] - b[1] : a[0] - b[0]);
        if (graph.containsKey(k)){
            queue.add(new int[]{k, 0});
            resList[k] = 0;
        }

        //Time: O(E * logE)
        //Time: O(E * log(N * (N-1)))
        //Time: O(E * log(N)
        while (graph.size() > 0 && !queue.isEmpty()) {
            int[] node = queue.poll();
            if (!graph.containsKey(node[0])) continue;

            for (int[] neighbour : graph.get(node[0])) {
                //update
                if (resList[neighbour[1]] > node[1] + neighbour[2]) {
                    resList[neighbour[1]] = node[1] + neighbour[2];
                    //log(E)
                    if (graph.containsKey(neighbour[1]))
                        queue.add(new int[]{neighbour[1], resList[neighbour[1]]});
                }
            }
            graph.remove(node[0]);
        }

        //Time: O(N)
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (resList[i] == Integer.MAX_VALUE) return -1;
            res = Math.max(res, resList[i]);
        }
        return res;
    }


}
