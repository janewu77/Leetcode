package Contest;

import javafx.util.Pair;

import java.io.IOException;
import java.util.*;

import static java.time.LocalTime.now;


/**
 * There is an undirected tree with n nodes labeled from 0 to n - 1, rooted at node 0. You are given a 2D integer array edges of length n - 1 where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
 *
 * At every node i, there is a gate. You are also given an array of even integers amount, where amount[i] represents:
 *
 * the price needed to open the gate at node i, if amount[i] is negative, or,
 * the cash reward obtained on opening the gate at node i, otherwise.
 * The game goes on as follows:
 *
 * Initially, Alice is at node 0 and Bob is at node bob.
 * At every second, Alice and Bob each move to an adjacent node. Alice moves towards some leaf node, while Bob moves towards node 0.
 * For every node along their path, Alice and Bob either spend money to open the gate at that node, or accept the reward. Note that:
 * If the gate is already open, no price will be required, nor will there be any cash reward.
 * If Alice and Bob reach the node simultaneously, they share the price/reward for opening the gate there. In other words, if the price to open the gate is c, then both Alice and Bob pay c / 2 each. Similarly, if the reward at the gate is c, both of them receive c / 2 each.
 * If Alice reaches a leaf node, she stops moving. Similarly, if Bob reaches node 0, he stops moving. Note that these events are independent of each other.
 * Return the maximum net income Alice can have if she travels towards the optimal leaf node.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: edges = [[0,1],[1,2],[1,3],[3,4]], bob = 3, amount = [-2,4,2,-4,6]
 * Output: 6
 * Explanation:
 * The above diagram represents the given tree. The game goes as follows:
 * - Alice is initially on node 0, Bob on node 3. They open the gates of their respective nodes.
 *   Alice's net income is now -2.
 * - Both Alice and Bob move to node 1.
 *   Since they reach here simultaneously, they open the gate together and share the reward.
 *   Alice's net income becomes -2 + (4 / 2) = 0.
 * - Alice moves on to node 3. Since Bob already opened its gate, Alice's income remains unchanged.
 *   Bob moves on to node 0, and stops moving.
 * - Alice moves on to node 4 and opens the gate there. Her net income becomes 0 + 6 = 6.
 * Now, neither Alice nor Bob can make any further moves, and the game ends.
 * It is not possible for Alice to get a higher net income.
 * Example 2:
 *
 *
 * Input: edges = [[0,1]], bob = 1, amount = [-7280,2350]
 * Output: -7280
 * Explanation:
 * Alice follows the path 0->1 whereas Bob follows the path 1->0.
 * Thus, Alice opens the gate at node 0 only. Hence, her net income is -7280.
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
 * 1 <= bob < n
 * amount.length == n
 * amount[i] is an even integer in the range [-104, 104].
 */

//2467. Most Profitable Path in a Tree
public class N6240MMostProfitablePathinaTree {

     static public void main(String... args) throws IOException{
         System.out.println(now());
         System.out.println("==================");

         doRun(6, new int[][]{{0,1},{1,2},{1,3},{3,4}}, 3,new int[]{-2,4,2,-4,6});
         doRun(2785, new int[][]{{0,2},{1,8},{1,6},{2,3},{2,4},{3,7},{4,5},{4,9},{6,7}}, 3,new int[]{-378,-3744,-638,9938,3818,-336,2722,154,-1750,-1672});

         doRun(-7280, new int[][]{{0,1}}, 1,new int[]{-7280,2350});
         doRun(7472, new int[][]{{0,2}, {0,6}, {1,3}, {1,5},{2,5},{4,6}}, 6,new int[]{8838,-6396,-5940,2694,-1366,4616,2966});
         doRun(20328, new int[][]{{0,2},{0,5},{1,3},{1,5},{2,4}}, 4,new int[]{5018,8388,6224,3466,3808,3456});
         doRun(-11662, new int[][]{{0,1}, {1,2},{2,3}}, 3,new int[]{-5644,-6018,1188,-8502});

         System.out.println(now());
         System.out.println("==================");
    }


    static private void doRun(int expect, int[][] edges, int bob, int[] amount) {
        int res = new N6240MMostProfitablePathinaTree().mostProfitablePath(edges, bob, amount);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //2. one DFS
    //Runtime: 82 ms, faster than 100.00% of Java online submissions for Most Profitable Path in a Tree.
    //Memory Usage: 185.1 MB, less than 25.00% of Java online submissions for Most Profitable Path in a Tree.
    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        //Time: O(E); Space: O(N + E)
        //build graph
        List<Integer>[] graph = new ArrayList[amount.length];
        for (int i = 0; i < amount.length; i++) graph[i] = new ArrayList<>();
        for (int[] edge: edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        return helper_dfs(graph, 0, bob, amount, new boolean[amount.length], 1)[0];
    }

    //Time: O(N); Space: O(N)
    private int[] helper_dfs(List<Integer>[] graph, int node, int bob, int[] amount, boolean[] seen, int height) {
        int res = Integer.MIN_VALUE;
        seen[node] = true;

        int bobPathLen = node == bob ? 1 : 0;

        for (int nextNode : graph[node]) {
            if (seen[nextNode] == true) continue;
            int[] tmp = helper_dfs(graph, nextNode, bob, amount, seen, height + 1);
            if (tmp[1] > 0) bobPathLen = tmp[1] + 1;
            res = Math.max(res, tmp[0]);
        }

        if (bobPathLen > 0 && bobPathLen <= height){
            if (bobPathLen == height) amount[node] = amount[node] / 2;
            else amount[node] = 0;
        }

        return new int[]{res == Integer.MIN_VALUE ? amount[node] : amount[node] + res, bobPathLen};
    }


    //1. BFS + DFS
    //Runtime: 85 ms, faster than 100.00% of Java online submissions for Most Profitable Path in a Tree.
    //Memory Usage: 144 MB, less than 50.00% of Java online submissions for Most Profitable Path in a Tree.
    //Time: O(E + N); Space: O(E + N)
    public int mostProfitablePath_1(int[][] edges, int bob, int[] amount) {
        //Time: O(E); Space: O(N + E)
        //build graph
        List<Integer>[] graph = new ArrayList[amount.length];
        for (int i = 0; i < amount.length; i++) graph[i] = new ArrayList<>();
        for (int[] edge: edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        //update amount
        helper_bfs(graph, bob, amount);
        return helper_dfs(graph, 0, amount, new boolean[amount.length]);
    }

    //Time: O(N); Space: O(N)
    private void helper_bfs(List<Integer>[] graph, int bob, int[] amount) {
        int[] preList = new int[amount.length];
        boolean[] seen = new boolean[amount.length];
        seen[bob] = true;

        //Time: O(N); Space: O(N)
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(bob);
        while (!queue.isEmpty()) {
            int currNode = queue.poll();
            for (int nextNode : graph[currNode]) {
                if (seen[nextNode] == true) continue;
                preList[nextNode] = currNode;
                if (nextNode == 0) break;

                queue.add(nextNode);
                seen[nextNode] = true;
            }
        }

        List<Integer> list = new ArrayList<>();
        list.add(0);
        int p = 0;
        while (p != bob) {
            p = preList[p]; list.add(p);
        }

        int resListSize = list.size();
        for (int i = 0; i < resListSize / 2; i++) amount[list.get(list.size() - 1 - i)] = 0;

        if (resListSize % 2 != 0) {
            int midIdx = resListSize / 2;
            amount[list.get(midIdx)] =  amount[list.get(midIdx)] / 2;
        }
    }

    //Time: O(N); Space: O(N)
    private int helper_dfs(List<Integer>[] graph, int node, int[] amount, boolean[] seen) {
        int res = Integer.MIN_VALUE;
        seen[node] = true;
        for (int nextNode : graph[node]) {
            if (seen[nextNode] == true) continue;
            res = Math.max(res, helper_dfs(graph, nextNode, amount, seen));
        }
        return res == Integer.MIN_VALUE ? amount[node] : amount[node] + res;
    }

}


