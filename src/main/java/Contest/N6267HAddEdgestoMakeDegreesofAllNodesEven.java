package Contest;

import java.io.IOException;
import java.util.*;

import static java.time.LocalTime.now;


/**
 * There is an undirected graph consisting of n nodes numbered from 1 to n. You are given the integer n and a 2D array edges where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi. The graph can be disconnected.
 *
 * You can add at most two additional edges (possibly none) to this graph so that there are no repeated edges and no self-loops.
 *
 * Return true if it is possible to make the degree of each node in the graph even, otherwise return false.
 *
 * The degree of a node is the number of edges connected to it.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 5, edges = [[1,2],[2,3],[3,4],[4,2],[1,4],[2,5]]
 * Output: true
 * Explanation: The above diagram shows a valid way of adding an edge.
 * Every node in the resulting graph is connected to an even number of edges.
 * Example 2:
 *
 *
 * Input: n = 4, edges = [[1,2],[3,4]]
 * Output: true
 * Explanation: The above diagram shows a valid way of adding two edges.
 * Example 3:
 *
 *
 * Input: n = 4, edges = [[1,2],[1,3],[1,4]]
 * Output: false
 * Explanation: It is not possible to obtain a valid graph with adding at most 2 edges.
 *
 *
 * Constraints:
 *
 * 3 <= n <= 105
 * 2 <= edges.length <= 105
 * edges[i].length == 2
 * 1 <= ai, bi <= n
 * ai != bi
 * There are no repeated edges.
 */


//2508. Add Edges to Make Degrees of All Nodes Even
public class N6267HAddEdgestoMakeDegreesofAllNodesEven {



    static public void main(String... args) throws IOException{
        System.out.println(now());
        System.out.println("==================");
        int[][] edges;

        edges = new int[][]{{1,2}, {1,3}, {1,4}};
        doRun(false,4, edges);

        edges = new int[][]{{1,2}, {3,4}};
        doRun(true,4, edges);

        edges = new int[][]{{1,2}};
        doRun(true,4, edges);

        edges = new int[][]{{5,9},{8,1},{2,3},{7,10},{3,6},{6,7},{7,8},{5,1},{5,7},{10,11},{3,7},{6,11},{8,11},{3,4},{8,9},{9,1},{2,10},{9,11},{5,11},{2,5},{8,10},{2,7},{4,1},{3,10},{6,1},{4,9},{4,6},{4,5},{2,4},{2,11},{5,8},{6,9},{4,10},{3,11},{4,7},{3,5},{7,1},{2,9},{6,10},{10,1},{5,6},{3,9},{2,6},{7,9},{4,11},{4,8},{6,8},{3,8},{9,10},{5,10},{2,8},{7,11}};
        doRun(false,11, edges);
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(boolean expect, int n, int[][] data) {
        List<List<Integer>> list = new ArrayList<>();
        for(int[] edge: data) {
            List<Integer> l = new ArrayList<>();
            l.add(edge[0]);l.add(edge[1]);
            list.add(l);
        }

        boolean res = new N6267HAddEdgestoMakeDegreesofAllNodesEven().isPossible(n, list);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 173 ms, 33%; Memory: 134.9MB 83%
    //Time: O(E + N); Space: O(E + N)
    public boolean isPossible(int n, List<List<Integer>> edges) {
        List<Set<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new HashSet<>());

        int[] counter = new int[n + 1];

        for(List<Integer> edge : edges) {
            Set<Integer> set0 = graph.get(edge.get(0));
            set0.add(edge.get(1));

            Set<Integer> set1= graph.get(edge.get(1));
            set1.add(edge.get(0));

            counter[edge.get(0)]++;
            counter[edge.get(1)]++;
        }

        List<Integer> oddNode = new ArrayList<>();
        for (int i = 1; i <= n; i++)
            if (counter[i] % 2 != 0) oddNode.add(i);

        if (oddNode.size() == 0) return true;
        if (oddNode.size() != 2 && oddNode.size() != 4) return false;

        return oddNode.size() == 2 ? helper_2nodes(graph, n, counter, oddNode) : helper_4nodes(graph, oddNode);
    }

    static int[][] offsets = new int[][]{{0,1,2,3}, {0,2,1,3},{0,3,1,2}};
    private boolean helper_4nodes(List<Set<Integer>> graph, List<Integer> oddNode){
        for(int[] offset: offsets){
            if (!graph.get(oddNode.get(offset[0])).contains(oddNode.get(offset[1]))
                && !graph.get(oddNode.get(offset[2])).contains(oddNode.get(offset[3])))
                    return true;
        }
        return false;
    }

    private boolean helper_2nodes(List<Set<Integer>> graph, int n, int[] counter, List<Integer> oddNode){
        if (!graph.get(oddNode.get(0)).contains(oddNode.get(1)))
            return true;

        for (int i = 1; i <= n; i++) {
            if (i == oddNode.get(0) || i == oddNode.get(1)) continue;
            if (counter[i] >= n - 2) continue;
            Set<Integer> set = graph.get(i);
            if (!set.contains(oddNode.get(0)) && !set.contains(oddNode.get(1)))
                return true;
        }
        return false;
    }


}


