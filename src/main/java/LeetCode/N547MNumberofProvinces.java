package LeetCode;

/**
 * 547. Number of Provinces
 * Medium
 *
 * 6202
 *
 * 268
 *
 * Add to List
 *
 * Share
 * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
 *
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 *
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
 *
 * Return the total number of provinces.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * Output: 2
 * Example 2:
 *
 *
 * Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= n <= 200
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] is 1 or 0.
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 */

/**
 * M - [Time: 15-
 */
public class N547MNumberofProvinces {


    //Runtime: 1 ms, faster than 99.98% of Java online submissions for Number of Provinces.
    //Memory Usage: 43.3 MB, less than 89.95% of Java online submissions for Number of Provinces.
    //Union find
    public int findCircleNum(int[][] isConnected) {
        UnionFind uf = new UnionFind(isConnected.length);
        for (int i = 0; i < isConnected.length; i++)
            for (int j = i; j < isConnected[0].length; j++)
                if (isConnected[i][j] == 1) uf.union(i, j);

//        Set<Integer> set = new HashSet<>();
//        for (int i = 0; i < isConnected.length; i++)
//            set.add(uf.find(i));
        return uf.getCount();
    }

    class UnionFind {
        int[] data;
        int[] rank;
        int count;

        public UnionFind(int n){
            data = new int[n];
            rank = new int[n];
            for (int i = 0; i < data.length; i++) {
                data[i] = i; rank[i] = 1;
            }
            count = n;
        }

        public int find(int x){
            if (data[x] == x) return x;
            return data[x] = find(data[x]);
        }

        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return;

            if (rank[rootX] > rank[rootY])
                data[rootY] = rootX;
            else {
                data[rootX] = rootY;
                if (rank[rootX] == rank[rootY])
                    rank[rootY]++;
            }
            count--;
        }

        public int getCount() {
            return count;
        }

    }
}








