package LeetCode;


import java.util.*;

/**
 * A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.
 *
 * Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
 *
 * Return a list of all MHTs' root labels. You can return the answer in any order.
 *
 * The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 4, edges = [[1,0],[1,2],[1,3]]
 * Output: [1]
 * Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.
 * Example 2:
 *
 *
 * Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
 * Output: [3,4]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2 * 104
 * edges.length == n - 1
 * 0 <= ai, bi < n
 * ai != bi
 * All the pairs (ai, bi) are distinct.
 * The given input is guaranteed to be a tree and there will be no repeated edges.
 */
public class N310MMinimumHeightTrees {

    //Topological sort
    //Runtime: 30 ms, faster than 90.53% of Java online submissions for Minimum Height Trees.
    //Memory Usage: 54.6 MB, less than 89.55% of Java online submissions for Minimum Height Trees.
    //Time: O(N + E); Space: O(N + E) //E = N - 1
    //Time: O(N); Space: O(N)
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(0);
            return res;
        }

        //Time: O(N + E); Space: O(N + E)
        Set<Integer>[] graph = new Set[n];
        for (int i = 0; i < n; i++) graph[i] = new HashSet<>();
        for (int[] edge: edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        //BFS
        //Time: O(N); Space: O(N)
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++)
            if (graph[i].size() == 1) queue.add(i);

        while (!queue.isEmpty()){
            if (n <= 2) break;

            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                int node = queue.poll();
                n--;
                for (int neighbour : graph[node]) {
                    graph[neighbour].remove(node);
                    if (graph[neighbour].size() == 1) queue.add(neighbour);
                }
            }
        }

        while (!queue.isEmpty()) res.add(queue.poll());
        return res;
    }

}
