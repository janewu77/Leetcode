package LeetCode;

import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.*;

import static java.time.LocalTime.now;

/**
 * There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.
 *
 * You are given the integer n and the array edges where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
 *
 * Return an array answer of length n where answer[i] is the sum of the distances between the ith node in the tree and all other nodes.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
 * Output: [8,12,6,10,10,10]
 * Explanation: The tree is shown above.
 * We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
 * equals 1 + 1 + 2 + 2 + 2 = 8.
 * Hence, answer[0] = 8, and so on.
 * Example 2:
 *
 *
 * Input: n = 1, edges = []
 * Output: [0]
 * Example 3:
 *
 *
 * Input: n = 2, edges = [[1,0]]
 * Output: [1,1]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 3 * 104
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * The given input represents a valid tree.
 */
public class N834HSumofDistancesinTree {

    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");

        doRun(new int[]{8,12,6,10,10,10}, 6, new int[][]{{0,1}, {0,2},{2,3},{2,4},{2,5}});

        doRun(new int[]{2,3,3}, 3, new int[][]{{0,1}, {0,2}});
        doRun(new int[]{1,1}, 2, new int[][]{{1,0}});
        doRun(new int[]{0}, 1, new int[][]{});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int[] expect, int n, int[][] edges) {
        int[] res = new N834HSumofDistancesinTree().sumOfDistancesInTree(n, edges);
        boolean success = Arrays.equals(res, expect);
        System.out.println("["+success+"] expect:" + Arrays.toString(expect) + " res:" + Arrays.toString(res));
    }


    //3.two dfs
    //Runtime: 39ms 97%; Memory: 54MB 100%
    //Time: O(N); Space: O(N)
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];

        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        for (int[] edge: edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int[] counter = new int[n], res = new int[n];
        helper_dfs_31(graph, 0, -1, counter, res);
        helper_dfs_32(graph, 0, -1, counter, res);

        return res;
    }

    private void helper_dfs_32(List<Integer>[] graph, int root,  int parent, int[] counter, int[] res) {
        for(int neighbour : graph[root]) {
            if (neighbour == parent) continue;

            int count = counter[root] - counter[neighbour];
            res[neighbour] += res[root] - (res[neighbour] + counter[neighbour]) + count;
            counter[neighbour] += count;

            helper_dfs_32(graph, neighbour, root, counter, res);
        }
    }

    private void helper_dfs_31(List<Integer>[] graph, int node, int parent, int[] counter, int[] res) {
        counter[node] = 1;
        for (int neighbour: graph[node]) {
            if (neighbour == parent) continue;
            helper_dfs_31(graph, neighbour, node, counter, res);

            res[node] += res[neighbour] + counter[neighbour];
            counter[node] += counter[neighbour];
        }
    }

    //2. backtracking + dfs
    //Runtime: 102ms 45%; Memory: 65.9MB 81%
    //Time: O(N); Space: O(N)
    public int[] sumOfDistancesInTree_2(int n, int[][] edges) {
        Set<Integer>[] graph = new Set[n];
        for (int i = 0; i < n; i++)
            graph[i] = new HashSet<>();
        for (int[] edge: edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int[][] dp = new int[n][2];
        helper_buildtree(graph, 0, -1, dp);

        int[] res = new int[n];
        helper_dfs_backtracking(graph, 0, dp, res);

        return res;
    }

    private void helper_dfs_backtracking(Set<Integer>[] graph, int root, int[][] memo, int[] res) {
        if (res[root] != 0) return;

        res[root] = memo[root][0];

        for(int neighbour : graph[root]) {
            int[] tmp = new int[]{memo[root][0], memo[root][1]};

            memo[root][0] -= (memo[neighbour][0] + memo[neighbour][1] + 1);
            memo[root][1] -= (memo[neighbour][1] + 1);

            memo[neighbour][0] += memo[root][0] + memo[root][1] + 1;
            memo[neighbour][1] += memo[root][1] + 1;

            helper_dfs_backtracking(graph, neighbour, memo, res);

            memo[root] = tmp;
        }
    }

    private int[] helper_buildtree(Set<Integer>[] graph, int node, int parent, int[][] memo) {
        memo[node] = new int[]{0, 0};

        for (int neighbour : graph[node]) {
            if (neighbour == parent) continue;
            int[] nodeRes = helper_buildtree(graph, neighbour, node, memo);
            memo[node][0] += nodeRes[0] + nodeRes[1] + 1;
            memo[node][1] += nodeRes[1] + 1;
        }
        return memo[node];
    }

    //1.brute force DFS
    //TLE
    //Time: O(N * (N + E)); Space: O(N + E)
    public int[] sumOfDistancesInTree_1(int n, int[][] edges) {

        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        for (int[] edge: edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            totalLen = 0;
            help_dfs(graph, i, new int[n], 0);
            res[i] = totalLen;
        }
        return res;
    }

    int totalLen = 0;
    private void help_dfs(List<Integer>[] graph, int node, int[] visited, int path) {
        if (visited[node] != 0) return;

        totalLen += path++;
        visited[node] = 1;

        for (int neighbour: graph[node]) {
            if (visited[neighbour] != 0) continue;
            help_dfs(graph, neighbour, visited, path);
        }
    }
}
