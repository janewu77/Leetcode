package LeetCode;


import java.util.*;

import static java.time.LocalTime.now;

/**
 *
 * You are given an integer n, the number of nodes in a directed graph where the nodes are labeled from 0 to n - 1. Each edge is red or blue in this graph, and there could be self-edges and parallel edges.
 *
 * You are given two arrays redEdges and blueEdges where:
 *
 * redEdges[i] = [ai, bi] indicates that there is a directed red edge from node ai to node bi in the graph, and
 * blueEdges[j] = [uj, vj] indicates that there is a directed blue edge from node uj to node vj in the graph.
 * Return an array answer of length n, where each answer[x] is the length of the shortest path from node 0 to node x such that the edge colors alternate along the path, or -1 if such a path does not exist.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3, redEdges = [[0,1],[1,2]], blueEdges = []
 * Output: [0,1,-1]
 * Example 2:
 *
 * Input: n = 3, redEdges = [[0,1]], blueEdges = [[2,1]]
 * Output: [0,1,-1]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 100
 * 0 <= redEdges.length, blueEdges.length <= 400
 * redEdges[i].length == blueEdges[j].length == 2
 * 0 <= ai, bi, uj, vj < n
 *
 *
 */
public class N1129MShortestPathwithAlternatingColors {


    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");
        doRun(new int[]{0,1,2,3,7}, 5, new int[][]{{0,1},{1,2},{2,3},{3,4}}, new int[][]{{1,2},{2,3},{3,1}});
        doRun(new int[]{0,1,-1}, 3, new int[][]{{0,1},{1,2}}, new int[][]{});
        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int[] expect, int n, int[][] redEdges, int[][] blueEdges) {
        int[] res = new N1129MShortestPathwithAlternatingColors().shortestAlternatingPaths(n, redEdges, blueEdges);
        boolean success = Arrays.equals(res, expect);
        System.out.println("["+success+"] expect:" + Arrays.toString(expect) + " res:" + Arrays.toString(res));
    }



    //1.BFS
    //Runtime: 3ms 98%; Memory: 42.5MB 95%
    //Time: O(N + E); Space: O(E + N)
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        int[] res = new int[n];
        Arrays.fill(res, -1);

        List<Integer>[][] graph = new ArrayList[n][2];
        for (int i = 0; i < n; i++) {
            graph[i][0] = new ArrayList<>();
            graph[i][1] = new ArrayList<>();
        }

        for(int[] edge : redEdges)
            graph[edge[0]][0].add(edge[1]);
        for(int[] edge : blueEdges)
            graph[edge[0]][1].add(edge[1]);

        //BFS
        Queue<int[]> queue = new LinkedList<>();
        //v, color, step
        queue.add(new int[]{0, 0, 0});
        queue.add(new int[]{0, 1, 0});

        boolean[][] visited = new boolean[n][2];

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int color = node[1];
            int step = node[2];

            if (visited[node[0]][color]) continue;
            visited[node[0]][color] = true;

            res[node[0]] = res[node[0]] == -1 ? step : Math.min(res[node[0]], step);

            int nextColor = color == 1 ? 0 : 1;
            for (int nxtNode : graph[node[0]][nextColor])
                queue.add(new int[]{nxtNode, nextColor, step + 1});
        }
        return res;
    }
}




