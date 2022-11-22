package Contest;

/**
 * There is an undirected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.
 *
 * You are given a 2D integer array edges of length n - 1 where edges[i] = [ai, bi] indicates
 * that there is an edge between nodes ai and bi in the tree. You are also given an integer
 * array restricted which represents restricted nodes.
 *
 * Return the maximum number of nodes you can reach from node 0 without visiting a restricted node.
 *
 * Note that node 0 will not be a restricted node.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 7, edges = [[0,1],[1,2],[3,1],[4,0],[0,5],[5,6]], restricted = [4,5]
 * Output: 4
 * Explanation: The diagram above shows the tree.
 * We have that [0,1,2,3] are the only nodes that can be reached from node 0 without visiting a restricted node.
 * Example 2:
 *
 *
 * Input: n = 7, edges = [[0,1],[0,2],[0,5],[0,4],[3,2],[6,5]], restricted = [4,2,1]
 * Output: 3
 * Explanation: The diagram above shows the tree.
 * We have that [0,5,6] are the only nodes that can be reached from node 0 without visiting a restricted node.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 105
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * edges represents a valid tree.
 * 1 <= restricted.length < n
 * 1 <= restricted[i] < n
 * All the values of restricted are unique.
 *
 *
 */

import java.util.*;

import static java.time.LocalTime.now;

/**
 * M - [time : 45- ]
 *

 */
//2368. Reachable Nodes With Restrictions
public class N6139MReachableNodesWithRestrictions {


    public static void main(String[] args) {

        System.out.println(now());

        int[][] edges;
        int[] restricted;
        edges = new int[][]{{0,1}, {1,2},{3,1}, {4, 0}, {0, 5}, {5, 6}};
        restricted = new int[]{4,5};
        doRun2(4,7, edges, restricted);

        System.out.println(now());
        System.out.println("==================");

    }

    //////////////////////////////////////////////////////////////////////////////////////////
    static private void doRun2(int expect, int n, int[][] edges, int[] restricted) {
        int res = new N6139MReachableNodesWithRestrictions().reachableNodes(n, edges,restricted);
//        String res = comm.toString(res1);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
        System.out.println("==================");
    }

    //Runtime: 62 ms, faster than 71.43% of Java online submissions for Reachable Nodes With Restrictions.
    //Memory Usage: 90.2 MB, less than 85.71% of Java online submissions for Reachable Nodes With Restrictions.
    //DFS
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        boolean[] visited = new boolean[n];
        for (int i : restricted) visited[i] = true;

        //build graph
        List<Integer>[] graph = new List[n];
        for (int i = 0 ; i < graph.length; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < edges.length; i++){
            int[] edge = edges[i];
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        //DFS
        int res = 0;
        Stack<Integer> candidates = new Stack<>();
        candidates.add(0);
        while (!candidates.isEmpty()) {

            int node = candidates.pop();
            visited[node] = true;
            res++;

            for (int tmp : graph[node])
                if (!visited[tmp]) candidates.add(tmp);
        }
        return res;
    }


    //Runtime: 298 ms, faster than 14.29% of Java online submissions for Reachable Nodes With Restrictions.
    //Memory Usage: 224.8 MB, less than 14.29% of Java online submissions for Reachable Nodes With Restrictions.
    //第一版
    public int reachableNodes_1(int n, int[][] edges, int[] restricted) {
        Set<Integer> resNode = new HashSet<>();
        for(int i : restricted) resNode.add(i);

        Set<Integer> visited = new HashSet<>();

        Stack<Integer> stack = new Stack<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i<edges.length; i++){
            int[] edge = edges[i];
            List<Integer> nodes = graph.getOrDefault(edge[0], new ArrayList<>());
            graph.put(edge[0], nodes);
            nodes.add(edge[1]);

            nodes = graph.getOrDefault(edge[1], new ArrayList<>());
            graph.put(edge[1], nodes);
            nodes.add(edge[0]);
        }

        visited.add(0);
        List<Integer> headNChild = graph.get(0);
        for(int node: headNChild) {
            if (!resNode.contains(node)) {
                stack.add(node);
            }
        }

        while(!stack.isEmpty()){
            int node = stack.pop();
            visited.add(node);

            List<Integer> nodes = graph.get(node);
            for(int nei: nodes){
                if (!resNode.contains(nei) && !visited.contains(nei)) {
                    stack.add(nei);
                }
            }
        }
        return visited.size();
    }

}
