package LeetCode;

import java.util.Arrays;
import java.util.Comparator;

import static java.time.LocalTime.now;

/**
 * Alice and Bob have an undirected graph of n nodes and three types of edges:
 *
 * Type 1: Can be traversed by Alice only.
 * Type 2: Can be traversed by Bob only.
 * Type 3: Can be traversed by both Alice and Bob.
 * Given an array edges where edges[i] = [typei, ui, vi] represents a bidirectional edge of type typei between nodes ui and vi, find the maximum number of edges you can remove so that after removing the edges, the graph can still be fully traversed by both Alice and Bob. The graph is fully traversed by Alice and Bob if starting from any node, they can reach all other nodes.
 *
 * Return the maximum number of edges you can remove, or return -1 if Alice and Bob cannot fully traverse the graph.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: n = 4, edges = [[3,1,2],[3,2,3],[1,1,3],[1,2,4],[1,1,2],[2,3,4]]
 * Output: 2
 * Explanation: If we remove the 2 edges [1,1,2] and [1,1,3]. The graph will still be fully traversable by Alice and Bob. Removing any additional edge will not make it so. So the maximum number of edges we can remove is 2.
 * Example 2:
 *
 *
 *
 * Input: n = 4, edges = [[3,1,2],[3,2,3],[1,1,4],[2,1,4]]
 * Output: 0
 * Explanation: Notice that removing any edge will not make the graph fully traversable by Alice and Bob.
 * Example 3:
 *
 *
 *
 * Input: n = 4, edges = [[3,2,3],[1,1,2],[2,3,4]]
 * Output: -1
 * Explanation: In the current graph, Alice cannot reach node 4 from the other nodes. Likewise, Bob cannot reach 1. Therefore it's impossible to make the graph fully traversable.
 *
 *
 *
 *
 * Constraints:
 *
 * 1 <= n <= 105
 * 1 <= edges.length <= min(105, 3 * n * (n - 1) / 2)
 * edges[i].length == 3
 * 1 <= typei <= 3
 * 1 <= ui < vi <= n
 * All tuples (typei, ui, vi) are distinct.
 */
public class N1579HRemoveMaxNumberofEdgestoKeepGraphFullyTraversable {



    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");
        doRun(2, 4,new int[][] {{3,1,2},{3,2,3},{1,1,3},{1,2,4},{1,1,2},{2,3,4}});
        int[][] edges = new int[][]{{1,1,2},{2,1,3},{3,2,4},{3,2,5},{1,2,6},{3,6,7},{3,7,8},{3,6,9},{3,4,10},{2,3,11},{1,5,12},{3,3,13},{2,1,10},{2,6,11},{3,5,13},{1,9,12},{1,6,8},{3,6,13},{2,1,4},{1,1,13},{2,9,10},{2,1,6},{2,10,13},{2,2,9},{3,4,12},{2,4,7},{1,1,10},{1,3,7},{1,7,11},{3,3,12},{2,4,8},{3,8,9},{1,9,13},{2,4,10},{1,6,9},{3,10,13},{1,7,10},{1,1,11},{2,4,9},{3,5,11},{3,2,6},{2,1,5},{2,5,11},{2,1,7},{2,3,8},{2,8,9},{3,4,13},{3,3,8},{3,3,11},{2,9,11},{3,1,8},{2,1,8},{3,8,13},{2,10,11},{3,1,5},{1,10,11},{1,7,12},{2,3,5},{3,1,13},{2,4,11},{2,3,9},{2,6,9},{2,1,13},{3,1,12},{2,7,8},{2,5,6},{3,1,9},{1,5,10},{3,2,13},{2,3,6},{2,2,10},{3,4,11},{1,4,13},{3,5,10},{1,4,10},{1,1,8},{3,3,4},{2,4,6},{2,7,11},{2,7,10},{2,3,12},{3,7,11},{3,9,10},{2,11,13},{1,1,12},{2,10,12},{1,7,13},{1,4,11},{2,4,5},{1,3,10},{2,12,13},{3,3,10},{1,6,12},{3,6,10},{1,3,4},{2,7,9},{1,3,11},{2,2,8},{1,2,8},{1,11,13},{1,2,13},{2,2,6},{1,4,6},{1,6,11},{3,1,2},{1,1,3},{2,11,12},{3,2,11},{1,9,10},{2,6,12},{3,1,7},{1,4,9},{1,10,12},{2,6,13},{2,2,12},{2,1,11},{2,5,9},{1,3,8},{1,7,8},{1,2,12},{1,5,11},{2,7,12},{3,1,11},{3,9,12},{3,2,9},{3,10,11}};
        doRun(114, 13, edges);
        doRun(0, 2,new int[][] {{3,1,2}});
        System.out.println(now());
        System.out.println("==================");
    }

    //contest: 92  330 ; 97 331; 98 333
    //2547 1494 460 1908 1626 2565. 2564 1259
    static private void doRun(int expect, int n, int[][] edges) {
        int res = new N1579HRemoveMaxNumberofEdgestoKeepGraphFullyTraversable().maxNumEdgesToRemove(n, edges);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //1.Union Find
    //Time: 13ms 79%; Memory: 96.8MB 57%
    //Time: O(E*α(N)); Space: O(N)
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        UnionFind ufAlice = new UnionFind(n);
        UnionFind ufBob = new UnionFind(n);
        int res = 0;

        //type 3 could be used by Alice and Bob
        // if both of them are already connected then the edge can be removed
        for (int[] edge: edges) {
            if (edge[0] != 3)
                continue;
            if (ufAlice.isConnected(edge[1] - 1, edge[2] - 1)
                    && ufBob.isConnected(edge[1] - 1, edge[2] - 1)) {
                res++;
            } else {
                ufAlice.union(edge[1] - 1, edge[2] - 1);
                ufBob.union(edge[1] - 1, edge[2] - 1);
            }
        }

        //type 1, type 2
        for (int[] edge: edges) {
            if (edge[0] == 3)
                continue;
            if (edge[0] == 1){
                if (ufAlice.isConnected(edge[1] - 1, edge[2] - 1)){
                    res++;
                }else
                    ufAlice.union(edge[1] - 1, edge[2] - 1);
            } else if (edge[0] == 2){
                if (ufBob.isConnected(edge[1] - 1, edge[2] - 1)){
                    res++;
                }else
                    ufBob.union(edge[1] - 1, edge[2] - 1);
            }
        }

        return (ufAlice.c == 1 && ufBob.c == 1) ? res : -1;
    }

    class UnionFind {

        private int[] group;
        private int[] rank;
        int c = 0;

        public UnionFind(int size) {
            group = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                group[i] = i; rank[i] = 1;
            }
            c = size;
        }

        //Time: O(α(N))
        public int find(int x) {
            return x == group[x] ? x: (group[x] = find(group[x]));
        }

        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return;
            c--;
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
