package LeetCode;


import java.util.*;

import static java.time.LocalTime.now;

/**
 * You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
 *
 * The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
 *
 * Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 * Output: 20
 * Explanation:
 *
 * We can connect the points as shown above to get the minimum cost of 20.
 * Notice that there is a unique path between every pair of points.
 * Example 2:
 *
 * Input: points = [[3,12],[-2,5],[-4,1]]
 * Output: 18
 *
 *
 * Constraints:
 *
 * 1 <= points.length <= 1000
 * -106 <= xi, yi <= 106
 * All pairs (xi, yi) are distinct.
 */
public class N1584MMinCosttoConnectAllPoints {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun(85,  new int[][]{{7,18}, {-15,19}, {-18, -15}, {-7,14}, {4,1}, {-6,3}});
        doRun(4,  new int[][]{{0,0}, {1,1},{1,0}, {-1,1}});
        doRun(53,  new int[][]{{2,-3}, {-17, -8}, {13,8}, {-17, -15}});
        doRun(0,  new int[][]{{0,0}});
        doRun(20,  new int[][]{{0,0}, {2,2}, {3,10}, {5,2}, {7,0}});
        doRun(18,  new int[][]{{3,12},{-2,5}, {-4,1}});

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, int[][] points) {
        int res = new N1584MMinCosttoConnectAllPoints().minCostConnectPoints(points);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //3.Prim's algorithm 2
    //Runtime: 29 ms, faster than 99.31% of Java online submissions for Min Cost to Connect All Points.
    //Memory Usage: 44.6 MB, less than 92.68% of Java online submissions for Min Cost to Connect All Points.
    //Time: O(V * V); Space: O(V)
    public int minCostConnectPoints(int[][] points) {
        //Time:O(V); Space: O(V)
        int[] minCostArray = new int[points.length];
        Arrays.fill(minCostArray, Integer.MAX_VALUE);
        minCostArray[0] = 0;

        int res = 0, idx = 0;
        //Time: O(V * V)
        for (int count = points.length - 1; count > 0; count--) {
            int minCost = Integer.MAX_VALUE;
            int currIdx = 0;
            for (int i = 0; i < minCostArray.length; i++) {
                if (minCostArray[i] == 0) continue;
                int distance = Math.abs(points[idx][0] - points[i][0]) + Math.abs(points[idx][1] - points[i][1]);
                minCostArray[i] = Math.min(minCostArray[i], distance);
                if (minCostArray[i] < minCost) {
                    minCost = minCostArray[i];
                    currIdx = i;
                }
            }
            res += minCostArray[currIdx];
            minCostArray[currIdx] = 0;
            idx = currIdx;
        }
        return res;
    }


    //2.Prim's algorithm
    //Runtime: 112 ms, faster than 88.14% of Java online submissions for Min Cost to Connect All Points.
    //Memory Usage: 64.9 MB, less than 85.04% of Java online submissions for Min Cost to Connect All Points.
    //Time: O(V * (log(E) + V * log(E)); Space: O(V + E)
    //Time: O(E * log(E)); Space: O(E)
    public int minCostConnectPoints_2(int[][] points) {
        //Space: O(V)
        boolean[] visited = new boolean[points.length];
        visited[0] = true;
        int count = points.length - 1;

        //Time: O(V * log(V)); Space: O(E)
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] != b[2] ? a[2]-b[2] : a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        for (int j = 1; j < points.length; j++)
            heap.add(new int[]{0, j, Math.abs(points[0][0] - points[j][0]) + Math.abs(points[0][1] - points[j][1])});

        int res = 0;
        //Time: O(V * (log(E) + V * log(E));
        while (count > 0) {
            int[] edge = heap.poll();
            if (!visited[edge[1]]){
                visited[edge[1]] = true;
                res += edge[2];
                count--;
                //Time: O(V * log(E))
                for (int j = 0; j < points.length; j++) {
                    if (!visited[j]) {
                        heap.add(new int[]{edge[1], j,
                                Math.abs(points[edge[1]][0] - points[j][0])
                                        + Math.abs(points[edge[1]][1] - points[j][1])});
                    }
                }
            }
        }
        return res;
    }

    //1. Kruskal’s Algorithm + union find
    //Runtime: 112 ms, faster than 88.14% of Java online submissions for Min Cost to Connect All Points.
    //Memory Usage: 64.7 MB, less than 85.56% of Java online submissions for Min Cost to Connect All Points.
    //Time: O(E * (log(E) + log(V))); Space: O(E + V)
    //Time: O(E * log(E)); Space: O(E)
    public int minCostConnectPoints_1(int[][] points) {

        //Time: O(E * log(E)); Space: O(E)
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        for (int i = 0; i < points.length - 1; i++)
            for (int j = i + 1; j < points.length; j++)
                queue.add(new int[]{i, j, Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1])});

        int res = 0, count = 0;
        //Time: O(V); Space: O(V)
        UnionFind unionFind = new UnionFind(points.length);
        //Time: O(E * (log(E) + log(V)))
        while (!queue.isEmpty() ){
            int[] edge = queue.poll();
            if (unionFind.union(edge[0], edge[1])) {
                res += edge[2];
                if (++count == points.length - 1) break;
            }
        }
        return res;
    }

    class UnionFind {
        int[] data;
        int[] rank;

        public UnionFind(int n){
            data = new int[n];
            rank = new int[n];
            for (int i = 0; i < data.length; i++) {
                data[i] = i; rank[i] = 1;
            }
        }

        public int find(int x){
            if (data[x] == x) return x;
            return data[x] = find(data[x]);
        }

        public boolean union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return false;

            if (rank[rootX] > rank[rootY])
                data[rootY] = rootX;
            else {
                data[rootX] = rootY;
                if (rank[rootX] == rank[rootY])
                    rank[rootY]++;
            }
            return true;
        }
    }
}
