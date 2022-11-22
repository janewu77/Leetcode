package LeetCode;

/**
 * You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.
 *
 * Return the number of connected components in the graph.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 5, edges = [[0,1],[1,2],[3,4]]
 * Output: 2
 * Example 2:
 *
 *
 * Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2000
 * 1 <= edges.length <= 5000
 * edges[i].length == 2
 * 0 <= ai <= bi < n
 * ai != bi
 * There are no repeated edges.
 */

/**
 * M - [time: 5-
 */
//same as 547
public class N323MNumberofConnectedComponentsinanUndirectedGraph {


    //Runtime: 4 ms, faster than 67.60% of Java online submissions for Number of Connected Components in an Undirected Graph.
    //Memory Usage: 46.7 MB, less than 51.00% of Java online submissions for Number of Connected Components in an Undirected Graph.
    //union find /  Disjoint Set Union (DSU)
    public int countComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge: edges) uf.union(edge[0], edge[1]);
        return uf.getCount();
    }

    class UnionFind {
        int[] data;
        int[] rank;
        int count;

        public UnionFind(int n){
            data = new int[n];
            rank = new int[n];
            count = n;
            for (int i = 0; i <n; i++){
                data[i] = i; rank[i] = 1;
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
            count--;
            if (rank[rootX] < rank[rootY]){
                data[rootX] = rootY;
            }else{
                data[rootY] = rootX;
                if (rank[rootX] == rank[rootY]) rank[rootX]++;
            }
        }

        public int getCount(){
            return count;
        }
    }
}
