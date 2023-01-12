package LeetCode;


import javafx.util.Pair;

import java.util.*;

import static java.time.LocalTime.now;

/**
 * Given an undirected tree consisting of n vertices numbered from 0 to n-1, which has some apples in their vertices. You spend 1 second to walk over one edge of the tree. Return the minimum time in seconds you have to spend to collect all apples in the tree, starting at vertex 0 and coming back to this vertex.
 *
 * The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means that exists an edge connecting the vertices ai and bi. Additionally, there is a boolean array hasApple, where hasApple[i] = true means that vertex i has an apple; otherwise, it does not have any apple.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
 * Output: 8
 * Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.
 * Example 2:
 *
 *
 * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,false,true,false]
 * Output: 6
 * Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.
 * Example 3:
 *
 * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,false,false,false,false,false]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= n <= 105
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai < bi <= n - 1
 * fromi < toi
 * hasApple.length == n
 */
public class N1443MMinimumTimetoCollectAllApplesinaTree {


    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");

        doRun(4, 4, new int[][]{{0,2},{0,3},{1,2}}, new boolean[]{false,true,false,false});
        doRun(8, 7, new int[][]{{0,1},{0,2},{1,4},{1,5},{2,3},{2,6}}, new boolean[]{false,false,true,false,true,true,false});
        System.out.println(now());
        System.out.println("==================");
    }

    //92
    static private void doRun(int expect, int n, int[][] edges, boolean[] hasAppleArray) {
        List<Boolean> hasApple = new ArrayList<>();
        for(int i = 0; i<hasAppleArray.length; i++)
            hasApple.add(hasAppleArray[i]);
        int res = new N1443MMinimumTimetoCollectAllApplesinaTree().minTime(n, edges, hasApple);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //3.DP
    //Runtime: 11ms 99% ; Memory Usage: 76 MB, 97%
    //Time: O(E * logE); Space: O(logE)
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        Arrays.sort(edges, (a,b) -> {
            if (a[1] == b[0]) return -1;
            else if (a[0]==b[1]) return 1;
            return 0;
        });

        int[] dp = new int[n];
        for (int i = edges.length - 1; i >= 0; i--) {
            int[] edge = edges[i];
            if (hasApple.get(edge[1]) || dp[edge[1]] > 0)
                dp[edge[0]] += 2 + dp[edge[1]];

            if (hasApple.get(edge[0]) || dp[edge[0]] > 0)
                dp[edge[1]] += 2 + dp[edge[0]];
        }
        return dp[0];
    }


    //2.DFS
    //Runtime: 27ms 95% ; Memory Usage: 86 MB, 51%
    //Time: O(N); Space: O(N)
    public int minTime_2(int n, int[][] edges, List<Boolean> hasApple) {

        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        for (int[] edge : edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        return helper_dfs(graph, 0, -1, hasApple);
    }

    private int helper_dfs(List<Integer>[] graph, int node, int parent, List<Boolean> hasApple){
        int count = 0;
        for (int neighbour : graph[node]) {
            if (neighbour == parent) continue;
            int tmp = helper_dfs(graph, neighbour, node, hasApple);
            if (hasApple.get(neighbour) || tmp > 0)
                count += 2 + tmp;
        }
        return count;
    }


    //1.backtrack
    //Runtime: 87ms 41% ; Memory Usage: 86.1 MB, 51%
    //Time: O(N * logN); Space: O(N)
    public int minTime_1(int n, int[][] edges, List<Boolean> hasApple) {

        Set<Integer>[] graph = new Set[n];
        for (int i = 0; i < n; i++)
            graph[i] = new HashSet<>();

        for (int[] edge : edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        Set<Integer> res = new HashSet<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        helper(graph, 0, -1, hasApple, path, res);

        return res.size() == 0 ? 0 : (res.size() - 1) * 2;
    }

    private void helper(Set<Integer>[] graph, int node, int parent, List<Boolean> hasApple,
                            List<Integer> path, Set<Integer> res){

        if (hasApple.get(node)) res.addAll(path);

        for (int neighbour : graph[node]) {
            if (neighbour != parent){
                path.add(neighbour);
                helper(graph, neighbour, node, hasApple, path, res);
                path.remove(path.size() - 1);
            }
        }
    }


}
