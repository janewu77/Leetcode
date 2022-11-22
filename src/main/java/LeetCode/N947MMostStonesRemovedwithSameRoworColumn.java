package LeetCode;

import java.io.IOException;
import java.util.*;

import static java.time.LocalTime.now;

/**
 * On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.
 *
 * A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
 *
 * Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the largest possible number of stones that can be removed.
 *
 *
 *
 * Example 1:
 *
 * Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * Output: 5
 * Explanation: One way to remove 5 stones is as follows:
 * 1. Remove stone [2,2] because it shares the same row as [2,1].
 * 2. Remove stone [2,1] because it shares the same column as [0,1].
 * 3. Remove stone [1,2] because it shares the same row as [1,0].
 * 4. Remove stone [1,0] because it shares the same column as [0,0].
 * 5. Remove stone [0,1] because it shares the same row as [0,0].
 * Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
 * Example 2:
 *
 * Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * Output: 3
 * Explanation: One way to make 3 moves is as follows:
 * 1. Remove stone [2,2] because it shares the same row as [2,0].
 * 2. Remove stone [2,0] because it shares the same column as [0,0].
 * 3. Remove stone [0,2] because it shares the same row as [0,0].
 * Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.
 * Example 3:
 *
 * Input: stones = [[0,0]]
 * Output: 0
 * Explanation: [0,0] is the only stone on the plane, so you cannot remove it.
 *
 *
 * Constraints:
 *
 * 1 <= stones.length <= 1000
 * 0 <= xi, yi <= 104
 * No two stones are at the same coordinate point.
 */

/**
 * M - [time: 60-
 */
public class N947MMostStonesRemovedwithSameRoworColumn {


    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");

        doRun(0, new int[][]{{0,1}, {1,0}});
        doRun(3, new int[][]{{0,0}, {0,2}, {1,1},{2,0},{2,2}});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[][] stones) {
        int res = new N947MMostStonesRemovedwithSameRoworColumn().removeStones(stones);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //2.Union Find
    //Runtime: 13 ms, faster than 95.97% of Java online submissions for Most Stones Removed with Same Row or Column.
    //Memory Usage: 53 MB, less than 40.56% of Java online submissions for Most Stones Removed with Same Row or Column.
    //Time: O(N * α(N)); Space: O(N)
    public int removeStones_2(int[][] stones) {
        //Time: O(N); Space: O(N)
        UnionFind uf = new UnionFind(stones.length);

        //Space:O(max(row or column))
        int count = 0;
        Map<Integer, Integer> map = new HashMap();// row or column : idx
        //Time: O(N * α(N))
        for (int i = 0; i < stones.length; i++) {
            int x = (stones[i][0] + 1) * 100_000;
            if (map.containsKey(x)) count += uf.union(map.get(x), i);
            else map.put(x, i);

            int y = stones[i][1];
            if (map.containsKey(y)) count += uf.union(map.get(y), i);
            else map.put(y, i);
        }
        return count;
    }

    class UnionFind {
        int[] data;
        int[] rank;

        public UnionFind(int n){
            data = new int[n];
            rank = new int[n];
            for (int i = 0; i <n; i++){
                data[i] = i; rank[i] = 1;
            }
        }
        private int find(int x){
            if (data[x] == x) return x;
            return data[x] = find(data[x]);
        }

        public int union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return 0;
            if (rank[rootX] < rank[rootY]){
                data[rootX] = rootY;
            }else{
                data[rootY] = rootX;
                if (rank[rootX] == rank[rootY]) rank[rootX]++;
            }
            return 1;
        }
    }

    //1.DFS
    //Runtime: 17 ms, faster than 93.57% of Java online submissions for Most Stones Removed with Same Row or Column.
    //Memory Usage: 42.3 MB, less than 95.43% of Java online submissions for Most Stones Removed with Same Row or Column.
    //Time: O(N + N); Space: O(2N + N + N)
    //Time: O(N); Space: O(N)
    public int removeStones(int[][] stones) {
        //Time: O(N); Space: O(2N)
        Map<Integer, List<int[]>> graph = new HashMap();
        for (int[] stone: stones) {

            int x = stone[0] + 10_001;//(stone[0] + 1) * 100_000;
            graph.computeIfAbsent(x, k -> new ArrayList<>());
            graph.get(x).add(stone);

            graph.computeIfAbsent(stone[1], k -> new ArrayList<>());
            graph.get(stone[1]).add(stone);
        }

        //Time: O(N); Space: O(N + N)
        int res = 0;
        Set<int[]> seen =  new HashSet<>();
        for (int[] stone: stones){
            if (seen.contains(stone)) continue;
            res += helper_dfs(graph, stone, seen) - 1;
        }
        return res;
    }

    private int helper_dfs(Map<Integer, List<int[]>> graph, int[] stone, Set<int[]> seen){
        seen.add(stone);
        int res = 1;
        int x = stone[0] + 10_001;
        for (int[] s : graph.get(x))
            if (!seen.contains(s)) res += helper_dfs(graph, s , seen);
        for (int[] s : graph.get(stone[1]))
            if (!seen.contains(s)) res += helper_dfs(graph, s , seen);
        return res;
    }
}
