package LeetCode;

import java.util.*;

import static java.time.LocalTime.now;

/**
 * There are n houses in a village. We want to supply water for all the houses by building wells and laying pipes.
 *
 * For each house i, we can either build a well inside it directly with cost wells[i - 1] (note the -1 due to 0-indexing), or pipe in water from another well to it. The costs to lay pipes between houses are given by the array pipes where each pipes[j] = [house1j, house2j, costj] represents the cost to connect house1j and house2j together using a pipe. Connections are bidirectional, and there could be multiple valid connections between the same two houses with different costs.
 *
 * Return the minimum total cost to supply water to all houses.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
 * Output: 3
 * Explanation: The image shows the costs of connecting houses using pipes.
 * The best strategy is to build a well in the first house with cost 1 and connect the other houses to it with cost 2 so the total cost is 3.
 * Example 2:
 *
 * Input: n = 2, wells = [1,1], pipes = [[1,2,1],[1,2,2]]
 * Output: 2
 * Explanation: We can supply water with cost two using one of the three options:
 * Option 1:
 *   - Build a well inside house 1 with cost 1.
 *   - Build a well inside house 2 with cost 1.
 * The total cost will be 2.
 * Option 2:
 *   - Build a well inside house 1 with cost 1.
 *   - Connect house 2 with house 1 with cost 1.
 * The total cost will be 2.
 * Option 3:
 *   - Build a well inside house 2 with cost 1.
 *   - Connect house 1 with house 2 with cost 1.
 * The total cost will be 2.
 * Note that we can connect houses 1 and 2 with cost 1 or with cost 2 but we will always choose the cheapest option.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 104
 * wells.length == n
 * 0 <= wells[i] <= 105
 * 1 <= pipes.length <= 104
 * pipes[j].length == 3
 * 1 <= house1j, house2j <= n
 * 0 <= costj <= 105
 * house1j != house2j
 */

/**
 * H - [time:
 */
public class N1168HOptimizeWaterDistributioninaVillage {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        data2 = new int[][]{{1,2,5},{1,3,2},{1,4,3}};
        doRun(11, 4,  new int[]{8,6,1,4}, data2);

        data2 = new int[][]{{1,2,12}, {2,3,11}};
        doRun(33, 3,  new int[]{10,30,80}, data2);

        data2 = new int[][]{{1,2,1}, {2,3,1}};
        doRun(3, 3,  new int[]{1,2,2}, data2);

        data2 = new int[][]{{1,2,1}, {1,2,2}};
        doRun(2, 2,  new int[]{1,1}, data2);

        data2 = new int[][]{{2,1,79394}, {3,1,45649}, {4,1,75810} ,{5,3,22340} ,{6,1,6222}};
        doRun(204321, 6,  new int[]{4625,65696,86292,68291,37147,7880}, data2);

        //10
        //[22238,38788,73611,22861,18865,52721,85325,98901,72035,74803]
        //[[2,1,82145],[3,1,83958],[4,2,52824],[5,4,60736],[6,1,38042],[7,6,30343],[9,3,34316]]
        data2 = new int[][]{{2,1,82145},{3,1,83958},{4,2,52824},{5,4,60736},{6,1,38042},{7,6,30343},{9,3,34316}};
        doRun(451192, 10,  new int[]{22238,38788,73611,22861,18865,52721,85325,98901,72035,74803}, data2);

        //9
        //[58732,77988,55446,79246,8265,30789,39905,79968,61679]
        //[[2,1,45475],[3,2,41579],[4,1,79418],[5,2,17589],[7,5,4371],[8,5,82103],[9,7,55500]]
        data2 = new int[][]{{2,1,45475},{3,2,41579},{4,1,79418},{5,2,17589},{7,5,4371},{8,5,82103},{9,7,55500}};
        doRun(362782, 9,  new int[]{58732,77988,55446,79246,8265,30789,39905,79968,61679}, data2);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int n, int[] wells, int[][] pipes) {
        int res = new N1168HOptimizeWaterDistributioninaVillage()
                .minCostToSupplyWater(n, wells, pipes);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 57 ms, faster than 57.78% of Java online submissions for Optimize Water Distribution in a Village.
    //Memory Usage: 61.9 MB, less than 71.20% of Java online submissions for Optimize Water Distribution in a Village.
    //Union find
    //Time: O(N + M + (M+N) * log(M+N) +  N + N + M); Space: O(N + M + log(M + N) + N)
    //Time: O((M+N) * log(M+N)); Space: O(N + M)
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        //Space: O(N + M)
        List<int[]> graph = new ArrayList<>(n + pipes.length + 1);

        //Time: O(N)
        for (int i = 0; i < wells.length; i++)
            graph.add(new int[]{0, i + 1, wells[i]});

        //Time: O(M)
        for (int[] pipe: pipes) graph.add(pipe);

        // Time: O( (M+N) * log(M+N) ); Space: O(log(M+N));
        Collections.sort(graph, Comparator.comparingInt(a -> a[2]));

        //Time: O(N); Space: O(N)
        UnionFind uf = new UnionFind(n + 1);
        int res = 0;
        //Time: O(N + M)
        for (int[] edge : graph)
            if (uf.union(edge[0], edge[1])) res += edge[2];

        return res;
    }

    class UnionFind {
        int[] group;
        int[] rank;

        public UnionFind(int n){
            group = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++){
                group[i] = i; rank[i] = 1;
            }
        }

        public int find(int x){
            if (group[x] == x) return x;
            return group[x] = find(group[x]);
        }

        public boolean union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return false;

            if (rank[rootX] < rank[rootY]){
                group[rootX] = rootY;
                //rootX = rootY;
            }else{
                group[rootY] = rootX;
                if (rank[rootX] == rank[rootY]) rank[rootX]++;
            }
            return true;
        }
    }

    //每次加入"最小cost"
    //Runtime: 83 ms, faster than 28.21% of Java online submissions for Optimize Water Distribution in a Village.
    //Memory Usage: 73.2 MB, less than 5.64% of Java online submissions for Optimize Water Distribution in a Village.
    //Prim's Algorithm with Heap
    //Time: O((N + M) * log(N + M)); Space: O(N)
    //N is the number of wells; m is the number of pipes
    public int minCostToSupplyWater_1(int n, int[] wells, int[][] pipes) {
        //Space: O(N + M)
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        List<List<int[]>> graph = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();

        //Time: O(N * lgN)
        for (int i = 0;  i < n; i++){
            heap.add(new int[]{i, wells[i]});
            graph.add(new ArrayList<>());
        }

        //Time: O(M)
        for (int[] pipe : pipes){
            graph.get(pipe[0] - 1).add(new int[]{pipe[1] - 1, pipe[2]});
            graph.get(pipe[1] - 1).add(new int[]{pipe[0] - 1, pipe[2]});
        }

        //Time: worst O(N * M * logN)
        int res = 0;
        while (seen.size() < n) {
            int[] edgeCost = heap.poll();
            if (!seen.add(edgeCost[0])) continue;
            res += edgeCost[1];

            List<int[]> edges = graph.get(edgeCost[0]);
            for (int[] edge: edges)
                if (!seen.contains(edge[0])) heap.add(edge);
        }
        return res;
    }


}
