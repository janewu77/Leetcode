package LeetCode;


import java.util.*;

/**
 *
 * An undirected graph of n nodes is defined by edgeList, where edgeList[i] = [ui, vi, disi] denotes an edge between nodes ui and vi with distance disi. Note that there may be multiple edges between two nodes.
 *
 * Given an array queries, where queries[j] = [pj, qj, limitj], your task is to determine for each queries[j] whether there is a path between pj and qj such that each edge on the path has a distance strictly less than limitj .
 *
 * Return a boolean array answer, where answer.length == queries.length and the jth value of answer is true if there is a path for queries[j] is true, and false otherwise.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries = [[0,1,2],[0,2,5]]
 * Output: [false,true]
 * Explanation: The above figure shows the given graph. Note that there are two overlapping edges between 0 and 1 with distances 2 and 16.
 * For the first query, between 0 and 1 there is no path where each distance is less than 2, thus we return false for this query.
 * For the second query, there is a path (0 -> 1 -> 2) of two edges with distances less than 5, thus we return true for this query.
 * Example 2:
 *
 *
 * Input: n = 5, edgeList = [[0,1,10],[1,2,5],[2,3,9],[3,4,13]], queries = [[0,4,14],[1,4,13]]
 * Output: [true,false]
 * Exaplanation: The above figure shows the given graph.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 105
 * 1 <= edgeList.length, queries.length <= 105
 * edgeList[i].length == 3
 * queries[j].length == 3
 * 0 <= ui, vi, pj, qj <= n - 1
 * ui != vi
 * pj != qj
 * 1 <= disi, limitj <= 109
 * There may be multiple edges between two nodes.
 */
public class N1697HCheckingExistenceofEdgeLengthLimitedPaths {


    //1. Union find
    //Runtime: 119ms 13.11%; Memory: 91.1MB 11.48%
    //Time: O(N + ElogE + QlogQ); Space: O(N + Q + logE + logQ);
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        boolean[] result = new boolean[queries.length];

        //sort edge
        //Time:O(ElogE); Space:O(logE)
        Arrays.sort(edgeList, Comparator.comparingInt(o -> o[2]));

        //sort query
        //Space:O(Q)
        int[][] queriesWithIndex = new int[queries.length][4];
        for(int i = 0; i < queriesWithIndex.length; i++) {
            queriesWithIndex[i][0] = queries[i][0];
            queriesWithIndex[i][1] = queries[i][1];
            queriesWithIndex[i][2] = queries[i][2];
            queriesWithIndex[i][3] = i;
        }
        //Time:O(QlogQ); Space:O(logQ)
        Arrays.sort(queriesWithIndex, Comparator.comparingInt(o -> o[2]));

        // O(N)
        UnionFind unionFind = new UnionFind(n);
        int idxEdge = 0;
        for (int[] query : queriesWithIndex){
            while (idxEdge < edgeList.length && edgeList[idxEdge][2] < query[2]){
                unionFind.union(edgeList[idxEdge][0], edgeList[idxEdge][1]);
                idxEdge++;
            }
            result[query[3]] = unionFind.isConnected(query[0], query[1]);
        }
        return result;
    }

    class UnionFind {

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

        public boolean isConnected(int x, int y){
            return find(x) == find(y);
        }
    }
}
