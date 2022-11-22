package Contest;

import java.util.*;

/**
 * You are given a directed graph of n nodes numbered from 0 to n - 1,
 * where each node has at most one outgoing edge.
 *
 * The graph is represented with a given 0-indexed array edges of size n,
 * indicating that there is a directed edge from node i to node edges[i].
 * If there is no outgoing edge from node i, then edges[i] == -1.
 *
 * Return the length of the longest cycle in the graph. If no cycle exists, return -1.
 *
 * A cycle is a path that starts and ends at the same node.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: edges = [3,3,4,2,3]
 * Output: 3
 * Explanation: The longest cycle in the graph is the cycle: 2 -> 4 -> 3 -> 2.
 * The length of this cycle is 3, so 3 is returned.
 * Example 2:
 *
 *
 * Input: edges = [2,-1,3,1]
 * Output: -1
 * Explanation: There are no cycles in this graph.
 *
 *
 * Constraints:
 *
 * n == edges.length
 * 2 <= n <= 105
 * -1 <= edges[i] < n
 * edges[i] != i
 */

/**
 * H - [time: 30-]
 *
 */
//2360. Longest Cycle in a Graph
public class N6135HLongestCycleinaGraph {

    public static void main(String[] args) {
        doRun(3, new int[]{3,3,4,2,3});
        doRun(-1, new int[]{2,-1,3,1});

        doRun(4, new int[]{1,2,0,4,5,6,3,8,9,7});

        doRun(4, new int[]{1,2,0,4,5,1,7,8,9,6});

    }
    private static void doRun(int expected , int[] edges) {
        int res = new N6135HLongestCycleinaGraph().longestCycle(edges);
        System.out.println("[" + (res == expected) + "]res:"+res+".expected:"+expected);
    }

    //Runtime: 57 ms, faster than 100.00% of Java online submissions for Longest Cycle in a Graph.
    //Memory Usage: 59 MB, less than 100.00% of Java online submissions for Longest Cycle in a Graph.
    public int longestCycle(int[] edges) {
        int[] visited = new int[edges.length+1];

        int res = -1;
        for (int n : edges){
            if (n == -1 || visited[n] != 0) continue;
            Map<Integer, Integer> data = new HashMap<>();
            int step = 0;

            while (n != -1 ){
                if (data.containsKey(n)){
                    res = Math.max(res, step - data.get(n));
                    break;
                }
                data.put(n, step++);

                if ( ++visited[n] > 1 ) break;

                n = edges[n];
            } // end while
        }
        return res;
    }

    //Runtime: 112 ms, faster than 100.00% of Java online submissions for Longest Cycle in a Graph.
    //Memory Usage: 66.2 MB, less than 100.00% of Java online submissions for Longest Cycle in a Graph.
    public int longestCycle_1(int[] edges) {
        Set<Integer> visited = new HashSet<>();
        int res = -1;

        for (int n : edges) {
            if (n == -1 || visited.contains(n)) continue;
            Map<Integer, Integer> data = new HashMap<>();
            int step = 0;

            while (n != -1 ){
                if (data.containsKey(n)){
                    res = Math.max(res, step - data.get(n));
                    break;
                }
                data.put(n, step++);

                if (!visited.add(n)) break;
                if (visited.size() >= edges.length + 1) return res;

                n = edges[n];
            } // end while
        }

        return res;
    }
}
