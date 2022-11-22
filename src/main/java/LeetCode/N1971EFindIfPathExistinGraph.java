package LeetCode;


/**
 * 1971. Find if Path Exists in Graph
 * Easy
 *
 * 1533
 *
 * 85
 *
 * Add to List
 *
 * Share
 * There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
 *
 * You want to determine if there is a valid path that exists from vertex source to vertex destination.
 *
 * Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
 * Output: true
 * Explanation: There are two paths from vertex 0 to vertex 2:
 * - 0 → 1 → 2
 * - 0 → 2
 * Example 2:
 *
 *
 * Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
 * Output: false
 * Explanation: There is no path from vertex 0 to vertex 5.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2 * 105
 * 0 <= edges.length <= 2 * 105
 * edges[i].length == 2
 * 0 <= ui, vi <= n - 1
 * ui != vi
 * 0 <= source, destination <= n - 1
 * There are no duplicate edges.
 * There are no self edges.
 */

import java.util.*;

/**
 * e- time: [5-
 */
public class N1971EFindIfPathExistinGraph {

    //Runtime: 67 ms, faster than 85.63% of Java online submissions for Find if Path Exists in Graph.
    //Memory Usage: 116.8 MB, less than 97.42% of Java online submissions for Find if Path Exists in Graph.
    //DFS
    //Time: O(V + E); Space: O(V + E);
    //V represents the number of vertices, and E represents the number of edges.
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        //Space: O(V + E)
        List<List<Integer>> adjList = new ArrayList<>();
        //Time: O(V)
        for (int i = 0; i < n; i++) adjList.add(new ArrayList<>());

        //Time: O(E)
        for (int[] edge: edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        //Space:O(V)
        int[] seen = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.add(source);
        seen[source] = 1;

        //Time: O(V);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (node == destination) return true;

            List<Integer> neighbours = adjList.get(node);
            for (int neighbour: neighbours) {
                if (seen[neighbour] == 0) {
                    stack.add(neighbour);
                    seen[neighbour] = 1;
                }
            }
        }
        return false;
    }


    //Runtime: 30 ms, faster than 90.77% of Java online submissions for Find if Path Exists in Graph.
    //Memory Usage: 138.2 MB, less than 84.41% of Java online submissions for Find if Path Exists in Graph.
    //Union Find
    //Time: O(UF); Space: O(UF)
    public boolean validPath_1(int n, int[][] edges, int source, int destination) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge: edges) uf.union(edge[0], edge[1]);
        return uf.find(source) == uf.find(destination);
    }

    public class UnionFind {

        private int[] group;
        private int[] rank;

        public UnionFind(int size) {
            group = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                group[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x) {
            return x == group[x] ? x : (group[x] = find(group[x]));
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return;
            if (rank[rootX] < rank[rootY]) {
                group[rootX] = rootY;
            } else {
                group[rootY] = rootX;
                if (rank[rootX] == rank[rootY]) rank[rootX]++;
            }
        }

    }

}
