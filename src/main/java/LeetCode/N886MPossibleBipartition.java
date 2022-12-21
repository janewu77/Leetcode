package LeetCode;

import javafx.util.Pair;

import java.io.IOException;
import java.util.*;

import static java.time.LocalTime.now;

/**
 * We want to split a group of n people (labeled from 1 to n) into two groups of any size. Each person may dislike some other people, and they should not go into the same group.
 *
 * Given the integer n and the array dislikes where dislikes[i] = [ai, bi] indicates that the person labeled ai does not like the person labeled bi, return true if it is possible to split everyone into two groups in this way.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
 * Output: true
 * Explanation: group1 [1,4] and group2 [2,3].
 * Example 2:
 *
 * Input: n = 3, dislikes = [[1,2],[1,3],[2,3]]
 * Output: false
 * Example 3:
 *
 * Input: n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2000
 * 0 <= dislikes.length <= 104
 * dislikes[i].length == 2
 * 1 <= dislikes[i][j] <= n
 * ai < bi
 * All the pairs of dislikes are unique.
 */
public class N886MPossibleBipartition {


    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");
        doRun(true, 4, new int[][]{{1,2}, {1,3}, {2,4}});
        doRun(false, 3, new int[][]{{1,2}, {1,3}, {2,3}});
        doRun(false, 5, new int[][]{{1,2}, {2,3}, {3,4},{4,5},{1,5}});
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(boolean expect, int n, int[][] dislikes) {
        boolean res = new N886MPossibleBipartition().possibleBipartition(n, dislikes);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //3.Union Find
    //Runtime: 14 ms 92%; Memory: 50.3 MB 81%
    //Time: O(N + E); Space: O(N + E)
    public boolean possibleBipartition_3(int n, int[][] dislikes) {
        UnionFind uf = new UnionFind(n + 1);

        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();
        for (int[] dislike : dislikes) {
            graph[dislike[0]].add(dislike[1]);
            graph[dislike[1]].add(dislike[0]);
        }

        for (int i = 1; i < n; i++) {
            if (graph[i].size() == 0) continue;
            int node0 = graph[i].get(0);
            for (int node : graph[i]) {
                uf.union(node0, node);
                if (uf.connected(i, node)) return false;
            }
        }
        return true;
    }

    private class UnionFind {

        private int[] group;
        private int[] rank;

        public UnionFind(int size) {
            group = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                group[i] = i; rank[i] = 1;
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

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }
    }


    //2.BFS
    //Runtime: 11ms 98%; Memory: 50.1 MB 86%
    //Time: O(N + E); Space: O(N + E)
    //let E be the number of the pair in the input array.
    public boolean possibleBipartition(int n, int[][] dislikes) {
        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();
        for (int[] dislike : dislikes) {
            graph[dislike[0]].add(dislike[1]);
            graph[dislike[1]].add(dislike[0]);
        }

        int[] color = new int[n + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (graph[i].size() == 0 || color[i] != 0) continue;

            color[i] = 1;
            queue.add(i);

            while (!queue.isEmpty()) {
                int node = queue.poll();

                for (int dislikeNode : graph[node]) {
                    if (color[dislikeNode] == 0){
                        color[dislikeNode] = -color[node];
                        queue.add(dislikeNode);
                    }else{
                        if (color[dislikeNode] == color[node])
                            return false;
                    }
                }
            } //End while (!queue.isEmpty()) {
        }//End for
        return true;
    }


    //1.DFS
    //Runtime: 25ms 84%; Memory: 73 MB 50%
    //Time: O(N + E + (N + E); Space: O(N + E)
    //Time: O(N + E); Space: O(N + E)
    //let E be the number of the pair in the input array.
    public boolean possibleBipartition_1(int n, int[][] dislikes) {
        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();
        for (int[] dislike : dislikes) {
            graph[dislike[0]].add(dislike[1]);
            graph[dislike[1]].add(dislike[0]);
        }

        int[] group = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (graph[i].size() == 0 || group[i] != 0) continue;
            if (!helper_dfs(graph, i, group, 1)) return false;
        }
        return true;
    }

    private boolean helper_dfs(List<Integer>[] graph, int node, int[] group, int flag){
        if (group[node] != 0)
            return group[node] == flag;

        group[node] = flag;
        for (int dislikeNode : graph[node])
            if (!helper_dfs(graph, dislikeNode, group, -flag))
                return false;
        return true;
    }



}
