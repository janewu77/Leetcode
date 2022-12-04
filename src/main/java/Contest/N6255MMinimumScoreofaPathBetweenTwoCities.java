package Contest;

import java.io.IOException;
import java.util.*;

import static java.time.LocalTime.now;

/**
 * You are given a positive integer n representing n cities numbered from 1 to n. You are also given a 2D array roads where roads[i] = [ai, bi, distancei] indicates that there is a bidirectional road between cities ai and bi with a distance equal to distancei. The cities graph is not necessarily connected.
 *
 * The score of a path between two cities is defined as the minimum distance of a road in this path.
 *
 * Return the minimum possible score of a path between cities 1 and n.
 *
 * Note:
 *
 * A path is a sequence of roads between two cities.
 * It is allowed for a path to contain the same road multiple times, and you can visit cities 1 and n multiple times along the path.
 * The test cases are generated such that there is at least one path between 1 and n.
 *
 *
 * Example 1:
 *
 *
 * Input: n = 4, roads = [[1,2,9],[2,3,6],[2,4,5],[1,4,7]]
 * Output: 5
 * Explanation: The path from city 1 to 4 with the minimum score is: 1 -> 2 -> 4. The score of this path is min(9,5) = 5.
 * It can be shown that no other path has less score.
 * Example 2:
 *
 *
 * Input: n = 4, roads = [[1,2,2],[1,3,4],[3,4,7]]
 * Output: 2
 * Explanation: The path from city 1 to 4 with the minimum score is: 1 -> 2 -> 1 -> 3 -> 4. The score of this path is min(2,2,4,7) = 2.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 105
 * 1 <= roads.length <= 105
 * roads[i].length == 3
 * 1 <= ai, bi <= n
 * ai != bi
 * 1 <= distancei <= 104
 * There are no repeated edges.
 * There is at least one path between 1 and n.
 */

//2492. Minimum Score of a Path Between Two Cities
public class N6255MMinimumScoreofaPathBetweenTwoCities {


    static public void main(String... args) throws IOException{
        System.out.println(now());
        System.out.println("==================");

        doRun(5, 4, new int[][]{{1,2,9},{2,3,6},{2,4,5},{1,4,7}});
        doRun(2, 4, new int[][]{{1,2,2},{1,3,4},{3,4,7}});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int n, int[][] roads) {
        int res = new N6255MMinimumScoreofaPathBetweenTwoCities().minScore(n, roads);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //3.DFS
    //Runtime: 49 ms, faster than 12.50% of Java online submissions for Minimum Score of a Path Between Two Cities.
    //Memory Usage: 114.2 MB, less than 12.50% of Java online submissions for Minimum Score of a Path Between Two Cities.
    //Time: (N + E); Space: O(E + N);
    public int minScore_3(int n, int[][] roads) {
        List<int[]>[] graph = new List[n + 1];
        for (int i = 0; i < graph.length; i++) graph[i] = new ArrayList<>();
        for(int[] road: roads){
            graph[road[0]].add(new int[]{road[1], road[2]});
            graph[road[1]].add(new int[]{road[0], road[2]});
        }
        Set<Integer> seen = new HashSet<>();
        seen.add(1);
        return helper_dfs(graph, 1, seen);
    }

    private int helper_dfs(List<int[]>[] graph, int node, Set<Integer> seen) {
        int res = Integer.MAX_VALUE;
        for(int[] neighbour : graph[node]) {
            res = Math.min(res, neighbour[1]);
            if (seen.add(neighbour[0]))
                res = Math.min(res, helper_dfs(graph, neighbour[0], seen));
        }
        return res;
    }

    //2.BFS
    //Runtime: 48 ms, faster than 12.50% of Java online submissions for Minimum Score of a Path Between Two Cities.
    //Memory Usage: 111.8 MB, less than 12.50% of Java online submissions for Minimum Score of a Path Between Two Cities.
    //Time: (N + E); Space: O(E + N);
    public int minScore_2(int n, int[][] roads) {
        int res = Integer.MAX_VALUE;

        List<int[]>[] graph = new List[n + 1];
        for (int i = 0; i < graph.length; i++) graph[i] = new ArrayList<>();
        for(int[] road: roads){
            graph[road[0]].add(new int[]{road[1], road[2]});
            graph[road[1]].add(new int[]{road[0], road[2]});
        }

        Set<Integer> seen = new HashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        seen.add(1);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for(int[] neighbour: graph[node]){
                res = Math.min(res, neighbour[1]);
                if (seen.add(neighbour[0])) queue.add(neighbour[0]);
            }
        }
        return res;
    }

    //1.Union find
    //Runtime: 7 ms, faster than 62.50% of Java online submissions for Minimum Score of a Path Between Two Cities.
    //Memory Usage: 108.1 MB, less than 12.50% of Java online submissions for Minimum Score of a Path Between Two Cities.
    //Time: O(E * log(N); Space:O(N)
    public int minScore(int n, int[][] roads) {
        UnionFind uf = new UnionFind(n + 1);
        for(int[] road: roads)
            uf.union(road[0], road[1], road[2]);

        int group = uf.find(1);
        if (group == uf.find(n)) return uf.distance[group];

        return -1;
    }

    public class UnionFind {

        private int[] group;
        private int[] rank;
        private int[] distance;

        public UnionFind(int size) {
            group = new int[size];
            rank = new int[size];
            distance = new int[size];
            for (int i = 0; i < size; i++) {
                group[i] = i; rank[i] = 1;
                distance[i] = Integer.MAX_VALUE;
            }
        }

        //Time: O(α(N))
        public int find(int x) {
            return x == group[x] ? x: (group[x] = find(group[x]));
        }

        public void union(int x, int y, int d){
            int rootX = find(x);
            int rootY = find(y);

            int minD = Math.min(distance[rootX], distance[rootY]);
            minD = Math.min(minD, d);
            distance[rootX] = distance[rootY] = minD;

            if (rootX == rootY) return;

            if (rank[rootX] < rank[rootY]){
                group[rootX] = rootY;
            }else{
                group[rootY] = rootX;
                if (rank[rootX] == rank[rootY]) rank[rootX]++;
            }
        }
    }

}


