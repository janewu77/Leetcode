package LeetCode;

/**
 * You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and a list of edges where edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.
 *
 * Return true if the edges of the given graph make up a valid tree, and false otherwise.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
 * Output: true
 * Example 2:
 *
 *
 * Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2000
 * 0 <= edges.length <= 5000
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * There are no self-loops or repeated edges.
 */

/**
 * M- [time: 10-
 */
public class N261MGraphValidTree {

    //原题转换： 检查是否为tree.
    //tree的特点： n个点； n - 1条边；所有边连通。
    //Runtime: 1 ms, faster than 93.38% of Java online submissions for Graph Valid Tree.
    //Memory Usage: 46.4 MB, less than 62.20% of Java online submissions for Graph Valid Tree.
    public boolean validTree(int n, int[][] edges) {

        if (n != edges.length + 1) return false;

        UnionFind uf = new UnionFind(n);
        for (int[] edge: edges)
            uf.union(edge[0], edge[1]);
        return uf.getCount() == 1;
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
