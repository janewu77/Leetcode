package Contest;


import java.util.*;

/**
 * You are given a directed graph of n nodes numbered from 0 to n - 1, where each node has at most one outgoing edge.
 *
 * The graph is represented with a given 0-indexed array edges of size n, indicating that there is a directed edge from node i to node edges[i]. If there is no outgoing edge from i, then edges[i] == -1.
 *
 * You are also given two integers node1 and node2.
 *
 * Return the index of the node that can be reached from both node1 and node2,
 * such that the maximum between the distance from node1 to that node, and from node2 to that node is minimized.
 * If there are multiple answers, return the node with the smallest index, and if no possible answer exists, return -1.
 *
 * Note that edges may contain cycles.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: edges = [2,2,3,-1], node1 = 0, node2 = 1
 * Output: 2
 * Explanation: The distance from node 0 to node 2 is 1, and the distance from node 1 to node 2 is 1.
 * The maximum of those two distances is 1. It can be proven that we cannot
 * get a node with a smaller maximum distance than 1, so we return node 2.
 * Example 2:
 *
 *
 * Input: edges = [1,2,-1], node1 = 0, node2 = 2
 * Output: 2
 * Explanation: The distance from node 0 to node 2 is 2, and the distance from node 2 to itself is 0.
 * The maximum of those two distances is 2. It can be proven that we cannot get
 * a node with a smaller maximum distance than 2, so we return node 2.
 *
 *
 * Constraints:
 *
 * n == edges.length
 * 2 <= n <= 105
 * -1 <= edges[i] < n
 * edges[i] != i
 * 0 <= node1, node2 < n
 *
 */

/**[2359. Find Closest Node to Given Two Nodes]
 * M - [time: 60+]
 *
 *
 */

//2359. Find Closest Node to Given Two Nodes
public class N6134MFindClosestNodeToGivenTwoNodes {

    public static void main(String[] args){

        doRun(2, new int[]{1,2,-1}, 0,2);
        doRun(2, new int[]{2,2,3,-1}, 0,1);
        doRun(4, new int[]{4,3,0,5,3,-1}, 4,0);
        doRun(0, new int[]{2,0,0}, 2,0);

        doRun(3, new int[]{5,3,1,0,2,4,5}, 3,2);
        doRun(-1, new int[]{5,4,5,4,3,6,-1}, 0,1);
        doRun(1, new int[]{4,4,8,-1,9,8,4,4,1,1}, 5,6);

    }

    private static void doRun(int expected , int[] edges, int node1, int node2){
        int res = new N6134MFindClosestNodeToGivenTwoNodes().closestMeetingNode(edges,node1,node2);
        System.out.println("[" + (res == expected) + "]res:"+res+".expected:"+expected);
    }

    //Time: O(N); Space: O(N)
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        if (node1 == node2) return node2;

        //node1: path & step
        int step = 0;
        Map<Integer, Integer> start = new HashMap<>();
        start.put(node1, step++);
        //Time: worst cast: O(N)
        while (edges[node1] != -1) {
            if (start.containsKey(edges[node1])) break; //cycle
            start.put(edges[node1], step++);
            node1 = edges[node1];
        }

        int distance = Integer.MAX_VALUE;
        int resNode = -1;
        if (start.isEmpty()) return resNode;

        //node2: path & step
        step = 0;
        Set<Integer> visited = new HashSet<>();
        //Time: worst cast: O(N)
        while (node2 != -1 ) {
            if (!visited.add(node2)) break; //cycle

            if (start.containsKey(node2)){
                //a smaller maximum distance
                int thisDistance = Math.max(start.get(node2), step);
                if (distance > thisDistance){
                    distance = thisDistance;
                    resNode = node2;
                }else if (distance == thisDistance){
                    //the smallest index
                    resNode = Math.min(node2, resNode);
                }
            }
            node2 = edges[node2];
            step++;
        }
        return resNode;
    }
}
