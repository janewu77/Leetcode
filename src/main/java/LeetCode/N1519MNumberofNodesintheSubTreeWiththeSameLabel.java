package LeetCode;


import java.util.*;

import static java.time.LocalTime.now;

/**
 * You are given a tree (i.e. a connected, undirected graph that has no cycles) consisting of n nodes numbered from 0 to n - 1 and exactly n - 1 edges. The root of the tree is the node 0, and each node of the tree has a label which is a lower-case character given in the string labels (i.e. The node with the number i has the label labels[i]).
 *
 * The edges array is given on the form edges[i] = [ai, bi], which means there is an edge between nodes ai and bi in the tree.
 *
 * Return an array of size n where ans[i] is the number of nodes in the subtree of the ith node which have the same label as node i.
 *
 * A subtree of a tree T is the tree consisting of a node in T and all of its descendant nodes.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], labels = "abaedcd"
 * Output: [2,1,1,1,1,1,1]
 * Explanation: Node 0 has label 'a' and its sub-tree has node 2 with label 'a' as well, thus the answer is 2. Notice that any node is part of its sub-tree.
 * Node 1 has a label 'b'. The sub-tree of node 1 contains nodes 1,4 and 5, as nodes 4 and 5 have different labels than node 1, the answer is just 1 (the node itself).
 * Example 2:
 *
 *
 * Input: n = 4, edges = [[0,1],[1,2],[0,3]], labels = "bbbb"
 * Output: [4,2,1,1]
 * Explanation: The sub-tree of node 2 contains only node 2, so the answer is 1.
 * The sub-tree of node 3 contains only node 3, so the answer is 1.
 * The sub-tree of node 1 contains nodes 1 and 2, both have label 'b', thus the answer is 2.
 * The sub-tree of node 0 contains nodes 0, 1, 2 and 3, all with label 'b', thus the answer is 4.
 * Example 3:
 *
 *
 * Input: n = 5, edges = [[0,1],[0,2],[1,3],[0,4]], labels = "aabab"
 * Output: [3,2,1,1,1]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 105
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * labels.length == n
 * labels is consisting of only of lowercase English letters.
 */
public class  N1519MNumberofNodesintheSubTreeWiththeSameLabel{


    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");
        int[][] edges;
        edges = new int[][]{{4,0},{5,4},{12,5},{3,12},{18,3},{10,18},{8,5},{16,8},{14,16},{13,16},{9,13},{22,9},{2,5},{6,2},{1,6},{11,1},{15,11},{20,11},{7,20},{19,1},{17,19},{23,19},{24,2},{21,24}};
        doRun(new int[]{2,2,1,1,1,3,2,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1}, 25, edges, "hcheiavadwjctaortvpsflssg");
        doRun(new int[]{4,2,1,1}, 4,new int[][]{{0,1},{1,2},{0,3}}, "bbbb");
        doRun(new int[]{2,1,1,1,1,1,1}, 7,new int[][]{{0,1},{0,2},{1,4},{1,5},{2,3},{2,6}}, "abaedcd");
        System.out.println(now());
        System.out.println("==================");
    }

    //92
    static private void doRun(int[] expect, int n, int[][] edges, String labels) {
        int[] res = new N1519MNumberofNodesintheSubTreeWiththeSameLabel().countSubTrees(n, edges, labels);
        boolean success = Arrays.equals(res, expect);
        System.out.println("["+success+"] expect:" + Arrays.toString(expect) + " res:" + Arrays.toString(res));
    }







    //2.dfs
    //Runtime: 62ms 97%; Memory: 113MB 96%
    //Time: O(N); Space: O(N)
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        List<Integer>[] graph = new List[n];
        for(int i = 0; i< n; i++)
            graph[i] = new ArrayList<>();

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        int[] res = new int[n];
        helper2_dfs(graph, labels.toCharArray(), 0, -1, res);
        return res;
    }

    private int[] helper2_dfs(List<Integer>[] graph, char[] labels, int node, int parent, int[] res){
        int[] counter = new int[26];
        counter[labels[node] - 'a']++;

        for (int neighbour: graph[node]) {
            if (neighbour == parent) continue;
            int[] tmp = helper2_dfs(graph, labels, neighbour, node, res);
            for (int i = 0; i < 26; i++) counter[i] += tmp[i];
        }
        res[node] = counter[labels[node] - 'a'];
        return counter;
    }


    //1.backtracking
    //TLE
    //Time: O(N * N); Space: O(N)
    public int[] countSubTrees_1(int n, int[][] edges, String labels) {
        List<Integer>[] graph = new List[n];
        for(int i = 0; i< n; i++)
            graph[i] = new ArrayList<>();

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int[] res = new int[n];
        Arrays.fill(res, 1);

        List<Integer>[] path = new List[26];
        for(int i = 0; i < 26; i++) path[i] = new ArrayList<>();

        path[labels.charAt(0)-'a'].add(0);
        helper_backtracking(graph, labels.toCharArray(), 0, -1, path, res);
        return res;
    }


    private void helper_backtracking(List<Integer>[] graph, char[] labels, int node, int parent,
                        List<Integer>[] path, int[] res){
        for (int neighbour: graph[node]) {
            if (neighbour == parent) continue;

            int idx= labels[neighbour] - 'a';
            for (int x : path[idx]) res[x]++;

            path[idx].add(neighbour);
            helper_backtracking(graph, labels, neighbour, node, path, res);
            path[idx].remove(path[idx].size() - 1);
        }
    }
}
