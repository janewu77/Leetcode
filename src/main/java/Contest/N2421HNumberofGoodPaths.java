package Contest;


/**
 * 2421. Number of Good Paths
 * User Accepted:390
 * User Tried:1927
 * Total Accepted:443
 * Total Submissions:4515
 * Difficulty:Hard
 * There is a tree (i.e. a connected, undirected graph with no cycles) consisting of n nodes numbered from 0 to n - 1 and exactly n - 1 edges.
 *
 * You are given a 0-indexed integer array vals of length n where vals[i] denotes the value of the ith node. You are also given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.
 *
 * A good path is a simple path that satisfies the following conditions:
 *
 * The starting node and the ending node have the same value.
 * All nodes between the starting node and the ending node have values less than or equal to the starting node (i.e. the starting node's value should be the maximum value along the path).
 * Return the number of distinct good paths.
 *
 * Note that a path and its reverse are counted as the same path. For example, 0 -> 1 is considered to be the same as 1 -> 0. A single node is also considered as a valid path.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: vals = [1,3,2,1,3], edges = [[0,1],[0,2],[2,3],[2,4]]
 * Output: 6
 * Explanation: There are 5 good paths consisting of a single node.
 * There is 1 additional good path: 1 -> 0 -> 2 -> 4.
 * (The reverse path 4 -> 2 -> 0 -> 1 is treated as the same as 1 -> 0 -> 2 -> 4.)
 * Note that 0 -> 2 -> 3 is not a good path because vals[2] > vals[0].
 * Example 2:
 *
 *
 * Input: vals = [1,1,2,2,3], edges = [[0,1],[1,2],[2,3],[2,4]]
 * Output: 7
 * Explanation: There are 5 good paths consisting of a single node.
 * There are 2 additional good paths: 0 -> 1 and 2 -> 3.
 * Example 3:
 *
 *
 * Input: vals = [1], edges = []
 * Output: 1
 * Explanation: The tree consists of only one node, so there is one good path.
 *
 *
 * Constraints:
 *
 * n == vals.length
 * 1 <= n <= 3 * 104
 * 0 <= vals[i] <= 105
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * edges represents a valid tree.
 */

/**
 * H - [time: not completed in contest
 */
//2421. Number of Good Paths
import java.util.*;

import static java.time.LocalTime.now;

public class N2421HNumberofGoodPaths {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;


        doRun(6,  new int[]{1,3,2,1,3},new int[][]{{0,1}, {0,2}, {2, 3}, {2, 4}});

        doRun(15,  new int[]{5,5,5,5,5},new int[][]{{0,1},{1,2},{3,2},{3,4}});

        doRun(20,  new int[]{2,5,5,1,5,2,3,5,1,5},new int[][]{{0,1},{2,1},{3,2},{3,4},{3,5},{5,6},{1,7},{8,4},{9,7}});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[] vals, int[][] edges) {
        int res = new N2421HNumberofGoodPaths()
                .numberOfGoodPaths(vals, edges);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 113 ms, faster than 100.00% of Java online submissions for Number of Good Paths.
    //Memory Usage: 60.4 MB, less than 100.00% of Java online submissions for Number of Good Paths.
    //Union Find
    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        //Space: O(N + N)
        List<List<Integer>> adjList = new ArrayList<>(); //graph 邻接表
        Map<Integer, List<Integer>> valueIdxMap = new TreeMap<>(); //value：nodeList

        //Time: O(N * lgN)
        for (int i = 0; i < vals.length; i++){
            List<Integer> nodeSet = valueIdxMap.getOrDefault(vals[i], new ArrayList<>());
            valueIdxMap.put(vals[i], nodeSet);
            nodeSet.add(i);

            adjList.add(new ArrayList<>());
        }

        //graph
        //Time: O(N)
        for (int[] edge : edges) {
            if (vals[edge[1]] <= vals[edge[0]]) adjList.get(edge[0]).add(edge[1]);
            else adjList.get(edge[1]).add(edge[0]);
        }

        int res = 0;
        //Time: O(N); Space: O(N)
        UnionFind uf = new UnionFind(vals.length);

        //from smallest to largest
        for (int value : valueIdxMap.keySet()) {

            List<Integer> nodeSet = valueIdxMap.get(value);
            List<Integer> rootSet = new ArrayList<>(); //按value形成的 root set

            //reset count
            uf.resetCount(nodeSet);

            //union
            for (Integer node : nodeSet)
                rootSet.add(uf.unionNeighbors(node, adjList.get(node)));

            //每组计数
            for (int node : rootSet)
                res += uf.getPathCountAndReset(node);
        }
        return res;
    }

    class UnionFind {
        int[] data;
        int[] rank;
        int[] count;

        //Time: O(N)
        public UnionFind(int n){
            data = new int[n];
            rank = new int[n];
            for (int i = 0 ; i < n; i++) {
                data[i] = i; rank[i] = 1;
            }
            count = new int[n];
        }

        public void resetCount(List<Integer> nodeSet) {
            for (int idx : nodeSet) count[idx] = 1;
        }

        public int getPathCountAndReset(int node){
            int res = (count[node] * (count[node] + 1)) / 2;
            count[node] = 0;
            return res;
        }

        private int find (int node){
            if (node == data[node]) return node;
            return data[node] = find(data[node]);
        }
//        private int find(int node){
//            while (data[node] != node) {
//                // Path compression.
//                data[node] = data[data[node]];
//                node = data[node];
//            }
//            return node;
//        }

        public int unionNeighbors(int node1, List<Integer> neighbors) {
            int root1 = find(node1);

            for (Integer neighbor : neighbors){
                int root2 = find(neighbor);
                if (root1 == root2) continue;

                if (rank[root1] < rank[root2]) {
                    data[root1] = root2;

                    count[root2] += count[root1];
                    count[root1] = 0;

                    root1 = root2;
                } else {
                    data[root2] = root1;

                    if (rank[root2] == rank[root1]) rank[root1]++;

                    count[root1] += count[root2];
                    count[root2] = 0;
                }
            }
            return root1;
        }
    }









    //TLE
    public int numberOfGoodPaths_2(int[] vals, int[][] edges) {
        if (edges.length == 0) return vals.length;

        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] node : edges){
            Set<Integer> a = graph.getOrDefault(node[0], new HashSet<>());
            graph.put(node[0], a);
            a.add(node[1]);

            Set<Integer> b = graph.getOrDefault(node[1], new HashSet<>());
            graph.put(node[1], b);
            b.add(node[0]);
        }

        Set<Integer> seen = new HashSet<>();
        int res = 0;
        for (int i = 0; i < vals.length; i++) {
            if (!seen.contains(i))
                res += helper2(vals, graph, seen, i);
        }
        return res;
    }

    private int helper2(int[] vals, Map<Integer, Set<Integer>> graph, Set<Integer> seen, int startNode){

        Set<Integer> set = new HashSet<>();
        set.add(startNode);
        int count = 1;
        int res = 1;

        Deque<Integer> stack = new ArrayDeque();
        stack.add(startNode);

        while (!stack.isEmpty()){
            int node = stack.pop();
            //if (!graph.containsKey(node)) continue;
            Set<Integer> neighbours = graph.get(node);
            for (int neighbour: neighbours) {
                if (vals[neighbour] <= vals[startNode] && set.add(neighbour)) {
                    if (vals[neighbour] == vals[startNode]) {
                        res += ++count;
                        seen.add(neighbour);
                    }
                    stack.add(neighbour);
                }
            }
        }
        return res;
    }

    //TLE
    public int numberOfGoodPaths_1(int[] vals, int[][] edges) {
        if (edges.length == 0) return vals.length;

        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] node : edges){
            Set<Integer> a = graph.getOrDefault(node[0], new HashSet<>());
            graph.put(node[0],a);
            a.add(node[1]);

            Set<Integer> b = graph.getOrDefault(node[1], new HashSet<>());
            graph.put(node[1],b);
            b.add(node[0]);
        }

        int res = 0;
        Set<Integer> seen = new HashSet<>();
        for (int i = vals.length - 1; i >= 0; i--) {
            seen.add(i);
            res += helper(graph, vals, i, i, seen);
        }
        return res;
    }

    private int helper(Map<Integer, Set<Integer>> graph, int[] vals, int startNode, int node, Set<Integer> seen){
        int res = 0;
        if (vals[startNode] == vals[node]) res++;

        if (graph.containsKey(node)){
            Set<Integer> set = graph.get(node);
            for (int n: set) {
                if (vals[n] <= vals[startNode] && !seen.contains(n)){
                    seen.add(n);
                    res += helper(graph, vals, startNode, n, seen);
                    seen.remove(n);
                }
            }
        }
        return res;
    }


//    private int helper(Map<Integer, Set<Integer>> graph, int[] vals, int beginNode, Set<Integer> seen){
//        int res = 1;
//        Set<Integer> currSeen = new HashSet<>();
//
//        Deque<Integer> stack = new ArrayDeque();
//        stack.add(beginNode);
//
//        while (!stack.isEmpty()){
//            int node = stack.pop();
//            currSeen.add(node);
//            if (graph.containsKey(node)){
//
//                Set<Integer> set = graph.get(node);
//                for (int n: set){
//                    if (vals[n] <= vals[beginNode]){
//                        if (!currSeen.contains(n) && !seen.contains(n)) {
//                            if (vals[n] == vals[beginNode]) res++;
//                            stack.add(n);
//                        }
//                    }
//                }
//
//            }
//        }
//        return res;
//    }


}
