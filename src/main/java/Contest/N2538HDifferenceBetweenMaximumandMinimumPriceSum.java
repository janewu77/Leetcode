package Contest;


import java.util.*;

import static java.time.LocalTime.now;

/**
 * There exists an undirected and initially unrooted tree with n nodes indexed from 0 to n - 1. You are given the integer n and a 2D integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
 *
 * Each node has an associated price. You are given an integer array price, where price[i] is the price of the ith node.
 *
 * The price sum of a given path is the sum of the prices of all nodes lying on that path.
 *
 * The tree can be rooted at any node root of your choice. The incurred cost after choosing root is the difference between the maximum and minimum price sum amongst all paths starting at root.
 *
 * Return the maximum possible cost amongst all possible root choices.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 6, edges = [[0,1],[1,2],[1,3],[3,4],[3,5]], price = [9,8,7,6,10,5]
 * Output: 24
 * Explanation: The diagram above denotes the tree after rooting it at node 2. The first part (colored in red) shows the path with the maximum price sum. The second part (colored in blue) shows the path with the minimum price sum.
 * - The first path contains nodes [2,1,3,4]: the prices are [7,8,6,10], and the sum of the prices is 31.
 * - The second path contains the node [2] with the price [7].
 * The difference between the maximum and minimum price sum is 24. It can be proved that 24 is the maximum cost.
 * Example 2:
 *
 *
 * Input: n = 3, edges = [[0,1],[1,2]], price = [1,1,1]
 * Output: 2
 * Explanation: The diagram above denotes the tree after rooting it at node 0. The first part (colored in red) shows the path with the maximum price sum. The second part (colored in blue) shows the path with the minimum price sum.
 * - The first path contains nodes [0,1,2]: the prices are [1,1,1], and the sum of the prices is 3.
 * - The second path contains node [0] with a price [1].
 * The difference between the maximum and minimum price sum is 2. It can be proved that 2 is the maximum cost.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 105
 * edges.length == n - 1
 * 0 <= ai, bi <= n - 1
 * edges represents a valid tree.
 * price.length == n
 * 1 <= price[i] <= 105
 */
public class N2538HDifferenceBetweenMaximumandMinimumPriceSum {

    static public void main(String... args){

        long y = Integer.MAX_VALUE;
        long x = Integer.MAX_VALUE + y;
        System.out.println(now());
        System.out.println("==================");
        doRun(2,3 , new int[][]{{0,1},{1,2}}, new int[]{1,1,1});
        doRun(24,6 , new int[][]{{0,1},{1,2},{1,3},{3,4},{3,5}}, new int[]{9,8,7,6,10,5});
        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(long expect, int n, int[][] edges, int[] price) {
        long res = new N2538HDifferenceBetweenMaximumandMinimumPriceSum().maxOutput(n, edges, price);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //2.two DFS
    //Runtime: 105ms 100%; Memory 99.1MB 100%
    //Time: O(N); Space: O(N);
    public long maxOutput(int n, int[][] edges, int[] price) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        for(int[] edge: edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        long[] counter = new long[n];
        //first dfs
        res2 = helper2_dfs(price, graph, 0, -1, counter);
        //second dfs: re-root
        helper2_dfs2(price, graph, 0, counter, new int[n]);
        return res2;
    }

    private long res2 = 0;
    private void helper2_dfs2(int[] price, List<Integer>[] graph, int node,
                              long[] counter, int[] seen) {

        long tmpX = counter[node];
        seen[node] = 1;
        for (int neighbour : graph[node]) {
            if (seen[neighbour] == 1) continue;

            //root -> child
            long childCount = 0l;
            for (int child : graph[node]) {
                if (child == neighbour) continue;
                childCount = Math.max(childCount, price[child] + counter[child]);
            }
            counter[node] = childCount;

            //child -> root
            long tmpY = counter[neighbour];
            counter[neighbour] = Math.max(price[node] + childCount, counter[neighbour]);
            res2 = Math.max(res2, counter[neighbour]);

            seen[neighbour] = 1;
            helper2_dfs2(price, graph, neighbour, counter, seen);
            counter[neighbour] = tmpY;
        }
        counter[node] = tmpX;
    }

    private long helper2_dfs(int[] price, List<Integer>[] graph, int node, int parent, long[] counter) {
        long res = 0l;
        for (int neighbour: graph[node]) {
            if (neighbour == parent) continue;
            res = Math.max(res, price[neighbour] + helper2_dfs(price, graph, neighbour, node, counter));
        }
        return counter[node] = res;
    }



    //1.brute force | DFS
    //TLE
    //Time: O(N * N); Space: O(N)
    public long maxOutput_1(int n, int[][] edges, int[] price) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        for(int[] edge: edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        long res = 0;
        for (int i = 0; i < n; i++)
            res = Math.max(res, helper_dfs(price, graph, i, -1));

        return res;
    }

    private long helper_dfs(int[] price, List<Integer>[] graph, int node, int parent) {
        long res = 0;
        for (int neighbour:  graph[node]) {
            if (neighbour == parent) continue;
            res = Math.max(res, price[neighbour] + helper_dfs(price, graph, neighbour, node));
        }
        return res;
    }

}
