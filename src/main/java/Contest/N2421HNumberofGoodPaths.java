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

        doRun(24,  new int[]{20,13,19,12,14,13,8,8,13,3,3,5,11,7,15,7,11,7,5,1,8,7,15,13}, new int[][]{{0,1},{0,2},{2,3},{3,4},{0,5},{3,6},{4,7},{0,8},{9,8},{2,10},{11,7},{12,2},{5,13},{2,14},{15,2},{16,14},{11,17},{8,18},{14,19},{20,13},{21,18},{22,4},{23,4}});

        doRun(9,  new int[]{3,1,1,1,3},new int[][]{{0,1}, {1,2}, {2, 3}, {3, 4}});
        doRun(6,  new int[]{1,3,2,1,3},new int[][]{{0,1}, {0,2}, {2, 3}, {2, 4}});

        doRun(15,  new int[]{5,5,5,5,5},new int[][]{{0,1},{1,2},{3,2},{3,4}});

        doRun(20,  new int[]{2,5,5,1,5,2,3,5,1,5},new int[][]{{0,1},{2,1},{3,2},{3,4},{3,5},{5,6},{1,7},{8,4},{9,7}});

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, int[] vals, int[][] edges) {
        int res = new N2421HNumberofGoodPaths().numberOfGoodPaths(vals, edges);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //3.Union Find
    //Runtime: 32ms 98%; Memory: 62.3MB 90%
    //Time: O(N * LogN); Space: O(N);
    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        Arrays.sort(edges, (a, b) -> {
            int tmp = Math.max(vals[a[0]], vals[a[1]]) - Math.max(vals[b[0]], vals[b[1]]);
            return tmp != 0 ? tmp : Math.min(vals[a[0]], vals[a[1]]) - Math.min(vals[a[0]], vals[a[1]]);
        });

        int res = vals.length;
        UnionFind3 uf = new UnionFind3(vals.length);
        for(int[] edge: edges)
            res += uf.union(edge[0], edge[1], vals);
        return res;
    }

    class UnionFind3 {
        private int[] data;
        private int[] count;

        public UnionFind3(int n){
            data = new int[n];
            count = new int[n];
            for (int i = 0; i <n; i++){
                data[i] = i;
                count[i] = 1;
            }
        }
        public int find(int x){
            if (data[x] == x) return x;
            return data[x] = find(data[x]);
        }

        public int union(int x, int y, int[] vals){
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return 0;

            int res = 0;
            if (vals[rootX] == vals[rootY]) {
                res = count[rootX] * count[rootY];

                count[rootX] += count[rootY];
                count[rootY] = 0;
            }

            if (vals[rootX] < vals[rootY])
                data[rootX] = rootY;
            else
                data[rootY] = rootX;

            return res;
        }
    }


    //2.UnionFind
    //Runtime: 101ms 82%; Memory: 61.9MB 93%
    //Time: O(N * LogN); Space: O(N);
    public int numberOfGoodPaths_2(int[] vals, int[][] edges) {
        int n = vals.length;
        List<Integer>[] graph = new List[n];
        Map<Integer, List<Integer>> valTreeMap = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            valTreeMap.computeIfAbsent(vals[i], k-> new ArrayList<>()).add(i);
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges){
            if (vals[edge[0]] >= vals[edge[1]])
                graph[edge[0]].add(edge[1]);
            if (vals[edge[1]] >= vals[edge[0]])
                graph[edge[1]].add(edge[0]);
        }

        int res = 0;
        //Time: O(N); Space: O(N)
        UnionFind2 uf = new UnionFind2(vals.length);

        //from the smallest value to the largest one
        for (Map.Entry<Integer, List<Integer>> entry : valTreeMap.entrySet()) {
            List<Integer> nodeSet = entry.getValue();
            for (int node : nodeSet)
                uf.setCount(node, 1);

            for (int node : nodeSet)
                for (int neighbour : graph[node])
                    uf.union(node, neighbour);

            Set<Integer> rootSet = new HashSet<>();
            for (int node : nodeSet)
                rootSet.add(uf.find(node));

            for (int node : rootSet) {
                res += uf.getCount(node) * (uf.getCount(node) + 1) / 2;
                uf.setCount(node, 0);
            }
        }
        return res;
    }

    class UnionFind2 {
        private int[] data;
        private int[] rank;
        private int[] count;

        public UnionFind2(int n){
            data = new int[n];
            rank = new int[n];
            count = new int[n];
            for (int i = 0; i <n; i++){
                data[i] = i;
                rank[i] = 1;
            }
        }
        public int find(int x){
            if (data[x] == x) return x;
            return data[x] = find(data[x]);
        }

        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return;

            if (rank[rootX] < rank[rootY]){
                data[rootX] = rootY;

                count[rootY] += count[rootX];
                count[rootX] = 0;
            }else{
                data[rootY] = rootX;
                if (rank[rootX] == rank[rootY]) rank[rootX]++;

                count[rootX] += count[rootY];
                count[rootY] = 0;
            }
        }

        public void setCount(int x, int val){
            count[x] = val;
        }
        public int getCount(int x){
            return count[x];
        }
    }



    //1.DFS
    //TLE
    //Time: O(N * N); Space: O(N)
    public int numberOfGoodPaths_1(int[] vals, int[][] edges) {
        int n = vals.length;
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();
        for (int[] edge : edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int[] seen = new int[n];
        int[] counter = new int[n];
        for (int i = 0; i < vals.length; i++) {
            if (seen[i] == 1) continue;
            if (counter[i] > 0) continue;
            helper_dfs(vals, graph, i, -1, counter, i, seen);
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            int count = counter[i];
            res += count * (count + 1) / 2;
        }
        return res;
    }

    //Time: O(N); Space: O(N)
    private void helper_dfs(int[] vals, List<Integer>[] graph, int node, int parent,
                            int[] counter, int rootNode, int[] seen) {
        if (vals[rootNode] == vals[node]) {
            counter[rootNode]++;
            seen[node] = 1;
        }

        for (int neighbour : graph[node]) {
            if (neighbour == parent || vals[neighbour] > vals[rootNode]) continue;
            helper_dfs(vals, graph, neighbour, node, counter, rootNode, seen);
        }
    }


    /////////////before 2022.01.15/////////////////////////////
    //Runtime: 113 ms, faster than 100.00% of Java online submissions for Number of Good Paths.
    //Memory Usage: 60.4 MB, less than 100.00% of Java online submissions for Number of Good Paths.
    //Union Find
    public int numberOfGoodPaths_01(int[] vals, int[][] edges) {
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


}
