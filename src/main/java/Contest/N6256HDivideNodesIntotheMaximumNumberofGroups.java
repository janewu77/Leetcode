package Contest;

import java.io.IOException;
import java.util.*;

import static java.time.LocalTime.now;

/**
 * You are given a positive integer n representing the number of nodes in an undirected graph. The nodes are labeled from 1 to n.
 *
 * You are also given a 2D integer array edges, where edges[i] = [ai, bi] indicates that there is a bidirectional edge between nodes ai and bi. Notice that the given graph may be disconnected.
 *
 * Divide the nodes of the graph into m groups (1-indexed) such that:
 *
 * Each node in the graph belongs to exactly one group.
 * For every pair of nodes in the graph that are connected by an edge [ai, bi], if ai belongs to the group with index x, and bi belongs to the group with index y, then |y - x| = 1.
 * Return the maximum number of groups (i.e., maximum m) into which you can divide the nodes. Return -1 if it is impossible to group the nodes with the given conditions.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 6, edges = [[1,2],[1,4],[1,5],[2,6],[2,3],[4,6]]
 * Output: 4
 * Explanation: As shown in the image we:
 * - Add node 5 to the first group.
 * - Add node 1 to the second group.
 * - Add nodes 2 and 4 to the third group.
 * - Add nodes 3 and 6 to the fourth group.
 * We can see that every edge is satisfied.
 * It can be shown that that if we create a fifth group and move any node from the third or fourth group to it, at least on of the edges will not be satisfied.
 * Example 2:
 *
 * Input: n = 3, edges = [[1,2],[2,3],[3,1]]
 * Output: -1
 * Explanation: If we add node 1 to the first group, node 2 to the second group, and node 3 to the third group to satisfy the first two edges, we can see that the third edge will not be satisfied.
 * It can be shown that no grouping is possible.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 500
 * 1 <= edges.length <= 104
 * edges[i].length == 2
 * 1 <= ai, bi <= n
 * ai != bi
 * There is at most one edge between any pair of vertices.
 */

//2493. Divide Nodes Into the Maximum Number of Groups
public class N6256HDivideNodesIntotheMaximumNumberofGroups {

    static public void main(String... args) throws IOException{
        System.out.println(now());
        System.out.println("==================");

        doRun(7, 9, new int[][]{{1,9},{2,9},{3,9},{4,9}});
        doRun(57, 92, new int[][]{{67,29},{13,29},{77,29},{36,29},{82,29},{54,29},{57,29},{53,29},{68,29},{26,29},{21,29},{46,29},{41,29},{45,29},{56,29},{88,29},{2,29},{7,29},{5,29},{16,29},{37,29},{50,29},{79,29},{91,29},{48,29},{87,29},{25,29},{80,29},{71,29},{9,29},{78,29},{33,29},{4,29},{44,29},{72,29},{65,29},{61,29}});
        doRun(-1, 3, new int[][]{{1,2},{2,3},{3,1}});
        doRun(4, 6, new int[][]{{1,2},{1,4},{1,5},{2,6},{2,3},{4,6}});
        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, int n, int[][] edges) {
        int res = new N6256HDivideNodesIntotheMaximumNumberofGroups().magnificentSets(n, edges);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //1. union find + BFS + DFS
    //Runtime: 1009 ms, faster than 100.00% of Java online submissions for Divide Nodes Into the Maximum Number of Groups.
    //Memory Usage: 45.8 MB, less than 100.00% of Java online submissions for Divide Nodes Into the Maximum Number of Groups.
    //Time: O(N * N + E * log(N)); Space: O(N + E)
    public int magnificentSets(int n, int[][] edges) {

        //let a component be a connected graph
        //Time: O(N + N + E * log(N)); Space: O(N + E)
        UnionFind uf = new UnionFind(n + 1);
        List<Integer>[] graph = new List[n + 1];
        for (int i = 0; i < graph.length; i++) graph[i] = new ArrayList<>();
        for(int[] edge: edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
            uf.union(edge[0], edge[1]);
        }

        // check every component(a connected graph) if it contains a circle which has odd numbers of edges.
        // If it has one, then it cannot be grouped.
        //Time: O(N * (log(N) + N)); Space: O(N)
        boolean[] seen = new boolean[n + 1];
        for(int i = 1; i <= n; i++) {
            int group = uf.find(i);

            if (seen[group]) continue;
            seen[group] = true;

            int[] pos = new int[n + 1];
            Arrays.fill(pos, -1);
            if (-1 == helper_dfs(graph, pos, i, 0, new int[n + 1])) return -1;
        }

        //Time: O(N * (log(N) + N)); Space: O(N)
        int[] maxLayers = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int group = uf.find(i);
            //get the maximum numbers of layer in a connected graph via BFS
            maxLayers[group] = Math.max(maxLayers[group], helper_bfs_countLayer(graph, i));
        }

        //sum layers
        //Time: O(N * (log(N)));
        int res = 0;
        for (int i = 1; i <= n; i++) {
            int group = uf.find(i);
            res += maxLayers[group];
            maxLayers[group] = 0;
        }
        return res;
    }

    private int helper_bfs_countLayer(List<Integer>[] graph, int node){
        Set<Integer> seen = new HashSet<>();
        seen.add(node);

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(node);
        int layer = 0;
        while (!queue.isEmpty()){
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                int nodeX = queue.poll();
                for(int neighbour: graph[nodeX])
                    if (seen.add(neighbour)) queue.add(neighbour);
            }
            layer++;
        }
        return layer;
    }

    //circle
    private int helper_dfs(List<Integer>[] graph, int[] pos, int node, int steps, int[] memo) {

        if (pos[node] != -1) {
            memo[node] = 1;
            if (steps - pos[node] > 1 && (steps - pos[node]) % 2 != 0)
                memo[node] = -1;
            return memo[node];
        }

        if (memo[node] != 0) return memo[node];
        memo[node] = 1;
        pos[node] = steps;
        List<Integer> neighbours = graph[node];
        for(int neighbour: neighbours) {
            memo[node] = helper_dfs(graph, pos, neighbour, steps + 1, memo);
            if (memo[node] == -1) return memo[node];
        }
        pos[node] = -1;
        return memo[node];
    }

    public class UnionFind {

        private int[] group;
        private int[] rank;

        public UnionFind(int size) {
            group = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                group[i] = i; rank[i] = 1;
            }
        }

        //Time: O(α(N))
        public int find(int x) {
            return x == group[x] ? x: (group[x] = find(group[x]));
        }

        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) return;

            if (rank[rootX] < rank[rootY]){
                group[rootX] = rootY;
            }else{
                group[rootY] = rootX;
                if (rank[rootX] == rank[rootY]) rank[rootX]++;
            }
        }
    }
}


