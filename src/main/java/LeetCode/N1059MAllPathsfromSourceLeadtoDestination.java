package LeetCode;


import java.util.*;

/***
 *
 * Given the edges of a directed graph where edges[i] = [ai, bi] indicates there is an edge between nodes ai and bi,
 * and two nodes source and destination of this graph, determine whether or not all paths starting from source eventually, end at destination, that is:
 *
 * At least one path exists from the source node to the destination node
 * If a path exists from the source node to a node with no outgoing edges,
 * then that node is equal to destination.（入度为0的点，就是destination
 * The number of possible paths from source to destination is a finite number.（destination不会有出的意思）
 * Return true if and only if all roads from source lead to destination.
 *
 *
 *
 * Example 1:
 * Input: n = 3, edges = [[0,1],[0,2]], source = 0, destination = 2
 * Output: false
 * Explanation: It is possible to reach and get stuck on both node 1 and node 2.
 *
 *
 * Example 2:
 * Input: n = 4, edges = [[0,1],[0,3],[1,2],[2,1]], source = 0, destination = 3
 * Output: false
 * Explanation: We have two possibilities: to end at node 3, or to loop over node 1 and node 2 indefinitely.
 *
 *
 * Example 3:
 * Input: n = 4, edges = [[0,1],[0,2],[1,3],[2,3]], source = 0, destination = 3
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= n <= 104
 * 0 <= edges.length <= 104
 * edges.length == 2
 * 0 <= ai, bi <= n - 1
 * 0 <= source <= n - 1
 * 0 <= destination <= n - 1
 * The given graph may have self-loops and parallel edges.
 *
 *
 */

/**
 * M - [time: 120+]
 *  - 花了相当多的时候处理cycle ，虽然是解决了，但不优雅。
 *  - graph + gray\black 更优雅的写法。
 *
 */
public class N1059MAllPathsfromSourceLeadtoDestination {

    public static void main(String[] args) {

        boolean expected; int n; int[][] edges; int source; int destination;

        n = 4;
        edges = new int[][]{{0,1},{0,2},{1,3},{2,3}};
        source = 0;
        destination = 3;
        doRun(true ,  n, edges,  source,  destination);

        n = 5;
        edges = new int[][]{{0,1},{0,2},{0,3},{0,3},{1,2},{1,3},{1,4},{2,3},{2,4},{3,4}};
        source = 0;
        destination = 4;
        doRun(true ,  n, edges,  source,  destination);


        n = 3;
        edges = new int[][]{{0,1},{1,0},{1,2}};
        source = 0;
        destination = 2;
        doRun(false ,  n, edges,  source,  destination);

        n = 1;
        edges = new int[][]{};
        source = 0;
        destination = 0;
        doRun(true ,  n, edges,  source,  destination);

        n = 1;
        edges = new int[][]{{0,0}};
        source = 0;
        destination = 0;
        doRun(false ,  n, edges,  source,  destination);

        n = 3;
        edges = new int[][]{};
        source = 0;
        destination = 1;
        doRun(false ,  n, edges,  source,  destination);

        n = 4;
        edges = new int[][]{{0,1},{0,2},{1,3},{2,3}};
        source = 1;
        destination = 3;
        doRun(true ,  n, edges,  source,  destination);

        n = 4;
        edges = new int[][]{{0,1},{0,2},{1,2},{2,3}};
        source = 0;
        destination = 3;
        doRun(true ,  n, edges,  source,  destination);

        n = 4;
        edges = new int[][]{{1,2},{2,1},{0,3}};
        source = 0;
        destination = 3;
        doRun(true ,  n, edges,  source,  destination);

    }

    private static int c = 1;
    private static void doRun(boolean expected, int n, int[][] edges, int source, int destination){
        boolean res = new N1059MAllPathsfromSourceLeadtoDestination().leadsToDestination( n, edges, source, destination);
        System.out.println("[" + (expected == res) +"] "+(c++)+ ".result:"+ res + ".expected:"+expected);
    }


    //Runtime: 4 ms, faster than 100.00% of Java online submissions for All Paths from Source Lead to Destination.
    //Memory Usage: 45.8 MB, less than 89.65% of Java online submissions for All Paths from Source Lead to Destination.
    // We don't use the state WHITE as such anywhere. Instead, the "null" value in the states array below is a substitute for WHITE.
    // 白、灰、黑解法
    //Time: O(V + E); Space: O(V+E);
    // V represents the number of vertices in the graph;
    // E represents the number of edges in the graph.
    enum Color { GRAY, BLACK };
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        List<Integer>[] graph = buildDigraph(n, edges);
        return leadsToDest(graph, source, destination, new Color[n]);
    }

    //Time: O(V + E); Space: O(V);
    private boolean leadsToDest(List<Integer>[] graph, int node, int dest, Color[] states) {
        if (states[node] != null) return states[node] == Color.BLACK;
        if (graph[node].isEmpty()) return node == dest;

        states[node] = Color.GRAY;
        for(Integer child : graph[node]){
            if (!leadsToDest(graph, child, dest, states)) return false;
        }
        states[node] = Color.BLACK;
        return true;
    }

    //Time: O(V + E); Space: O(V + E);
    private List<Integer>[] buildDigraph(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];
        for(int i = 0; i < n; i++)  graph[i] = new LinkedList();
        for(int[] edge: edges) graph[edge[0]].add(edge[1]);
        return graph;
    }


    //Runtime: 20 ms, faster than 35.76% of Java online submissions for All Paths from Source Lead to Destination.
    //Memory Usage: 48.1 MB, less than 83.89% of Java online submissions for All Paths from Source Lead to Destination.
    // destination indegree = 0 且只有一个
    // 用出入度计算的
    public boolean leadsToDestination_1(int n, int[][] edges, int source, int destination) {
        if (edges.length == 0 && n == 1) return true;

        //Space: O(V + E)
        Set<Integer> nodes = new HashSet<>();
        Map<Integer,Integer> outdegree = new HashMap<>(); //source: outdegree
        Map<Integer, List<Integer>> targetEdges = new HashMap<>(); // target: sourceList

        //Time: O(E)
        for (int[] edge: edges){
            if (edge[0] == destination) return false;

            nodes.add(edge[0]);
            nodes.add(edge[1]);
            outdegree.put(edge[0], outdegree.getOrDefault(edge[0], 0) + 1);

            //source -> targets ab cb db   b -> acd
            List<Integer> sourceNodes = targetEdges.getOrDefault(edge[1], new ArrayList<>());
            sourceNodes.add(edge[0]);
            targetEdges.put(edge[1], sourceNodes);
        }

        //check destination (outdegree == 0 and only one)
        if (nodes.size() != n) return false;
//        if (outdegree.containsKey(destination)) return false;

        List<Integer> todoList = new ArrayList<>();
        todoList.add(destination);

        while (!todoList.isEmpty()){
            List<Integer> sourceNodes = new ArrayList<>();
            for(Integer node : todoList){
                if (targetEdges.containsKey(node))
                    sourceNodes.addAll(targetEdges.get(node));
            }
            nodes.removeAll(todoList);
            todoList.clear();

            //更新节点的出度。 出度为零时，视为destination，放入todolist处理。
            for (int sourceNode: sourceNodes) {
                int degree = outdegree.getOrDefault(sourceNode, 0) - 1;
                if (degree < 0) return false;
                if (degree == 0) todoList.add(sourceNode);
                else outdegree.put(sourceNode, degree);
            }
        }

        for(int node: nodes)
            if (node == source) return false;

        return true;
    }
}