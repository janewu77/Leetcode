package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

/**
 * You are given an empty 2D binary grid grid of size m x n. The grid represents a map where 0's represent water and 1's represent land. Initially, all the cells of grid are water cells (i.e., all the cells are 0's).
 *
 * We may perform an add land operation which turns the water at position into a land. You are given an array positions where positions[i] = [ri, ci] is the position (ri, ci) at which we should operate the ith operation.
 *
 * Return an array of integers answer where answer[i] is the number of islands after turning the cell (ri, ci) into a land.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: m = 3, n = 3, positions = [[0,0],[0,1],[1,2],[2,1]]
 * Output: [1,1,2,3]
 * Explanation:
 * Initially, the 2d grid is filled with water.
 * - Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land. We have 1 island.
 * - Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land. We still have 1 island.
 * - Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land. We have 2 islands.
 * - Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land. We have 3 islands.
 * Example 2:
 *
 * Input: m = 1, n = 1, positions = [[0,0]]
 * Output: [1]
 *
 *
 * Constraints:
 *
 * 1 <= m, n, positions.length <= 104
 * 1 <= m * n <= 104
 * positions[i].length == 2
 * 0 <= ri < m
 * 0 <= ci < n
 *
 *
 * Follow up: Could you solve it in time complexity O(k log(mn)), where k == positions.length?
 */
public class N305HNumberofIslandsII {

    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");
        //[[0,1],[0,0]]
        doRun("1,1,2,2",3,3, new int[][]{{0,0},{0,1},{1,2},{1,2}});
        doRun("1",8,2, new int[][]{{7,0}});
        doRun("1,2,1",2,2, new int[][]{{0,0},{1,1},{0, 1}});
        doRun("1,1",1,2, new int[][]{{0, 1}, {0, 0}});
        doRun("1,1,2,3",3,3, new int[][]{{0, 0}, {0, 1}, {1, 2}, {2, 1}});
        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(String expect, int m, int n, int[][] positions) {
        List<Integer> res1 = new N305HNumberofIslandsII().numIslands2(m, n, positions);
        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }


    //1.Union Find
    //Runtime: 8ms 91%; Memory: 46.1MB 53.4%
    //Time: O(M * N + L * 4 * log(UF); Space: O(M * N)
    private int[][] directions = new int[][]{{-1,0},{0,-1}, {1,0},{0,1}};
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        UnionFind uf = new UnionFind(m * n);
        int[] data = new int[m * n];

        for (int i = 0; i < positions.length; i++) {
            int[] pos = positions[i];
            int v = pos[0] * n + pos[1];
            if (data[v] == 1) {
                res.add(uf.count);
                continue;
            }
            for (int[] direction : directions) {
                int x = direction[0] + pos[0];
                int y = direction[1] + pos[1];
                if (x < 0 || x >= m || y < 0 || y >= n) continue;
                int neighbour = x * n + y;
                if (data[neighbour] == 0) continue;
                uf.union(v, neighbour);
            }
            res.add(++uf.count);
            data[v] = 1;
        }
        return res;
    }


    class UnionFind {
        int[] data;
        int[] rank;
        int count;

        public UnionFind(int n){
            data = new int[n];
            rank = new int[n];
            count = 0;
            for (int i = 0; i < n; i++){
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
    }

}


