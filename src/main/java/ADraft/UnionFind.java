package ADraft;

public class UnionFind {

    private int[] group;
    // Use a rank array to record the height of each vertex, i.e., the "rank" of each vertex.
    private int[] rank;
    int count;

    //Time: O(N)
    //N is the number of vertices in the graph.
    public UnionFind(int size) {
        group = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            group[i] = i;
            rank[i] = 1; // The initial "rank" of each vertex is 1, because each of them is
            // a standalone vertex with no connection to other vertices.
        }
        count = size;
    }

    //Time: O(α(N))
    //α refers to the Inverse Ackermann function
    //Time:O(1)
    // The find function here is the same as that in the disjoint set with path compression.
    public int find(int x) {
        return x == group[x] ? x: (group[x] = find(group[x]));
    }
//    public int find(int x){
//        if (group[x] == x) return x;
//        return group[x] = find(group[x]);
//    }

    //Time:
    // The union function with union by rank
    public void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) return;
        count--;
        if (rank[rootX] < rank[rootY]){
            group[rootX] = rootY;
        }else{
            group[rootY] = rootX;
            if (rank[rootX] == rank[rootY]) rank[rootX]++;
        }
        count--;
    }


    //Time:
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
    public int getCount(){
        return count;
    }

}
