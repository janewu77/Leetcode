package Contest;



import java.util.*;

import static java.time.LocalTime.now;


/**
 * There is an undirected tree with n nodes labeled from 0 to n - 1.
 *
 * You are given a 0-indexed integer array nums of length n where nums[i] represents the value of the ith node. You are also given a 2D integer array edges of length n - 1 where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
 *
 * You are allowed to delete some edges, splitting the tree into multiple connected components. Let the value of a component be the sum of all nums[i] for which node i is in the component.
 *
 * Return the maximum number of edges you can delete, such that every connected component in the tree has the same value.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: nums = [6,2,2,2,6], edges = [[0,1],[1,2],[1,3],[3,4]]
 * Output: 2
 * Explanation: The above figure shows how we can delete the edges [0,1] and [3,4]. The created components are nodes [0], [1,2,3] and [4]. The sum of the values in each component equals 6. It can be proven that no better deletion exists, so the answer is 2.
 * Example 2:
 *
 * Input: nums = [2], edges = []
 * Output: 0
 * Explanation: There are no edges to be deleted.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2 * 104
 * nums.length == n
 * 1 <= nums[i] <= 50
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= edges[i][0], edges[i][1] <= n - 1
 * edges represents a valid tree.
 */

/**
 * H - [time: 120
 */

//2440. Create Components With Same Value
public class N6211HCreateComponentsWithSameValue {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun(1, new int[]{1,2,1,1,1}, new int[][]{{0,1},{1,3},{3,4},{4,2}});


        doRun(0, new int[]{1,1,2,1,1}, new int[][]{{0,1},{1,2},{3,2},{4,2}});
        doRun(0, new int[]{3,6,3}, new int[][]{{0,1},{1,2}});

        doRun(3, new int[]{6,2,2,2,6,6}, new int[][]{{0,1},{1,2},{1,3},{3,4}, {4,5}});


        doRun(4, new int[]{2,2,2,2,2}, new int[][]{{0,1},{1,2},{1,3},{3,4}});
        doRun(2, new int[]{6,2,2,2,6}, new int[][]{{0,1},{1,2},{1,3},{3,4}});
        doRun(0, new int[]{2}, new int[][]{});

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, int[] nums, int[][] edges) {
        int res = new N6211HCreateComponentsWithSameValue()
                .componentValue(nums, edges);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //2.BFS
    //https://leetcode.com/problems/create-components-with-same-value/discuss/2706736/Python-Explanation-with-pictures-BFS
    //Runtime: 73 ms, faster than 96.26% of Java online submissions for Create Components With Same Value.
    //Memory Usage: 54.9 MB, less than 98.28% of Java online submissions for Create Components With Same Value.
    public int componentValue(int[] nums, int[][] edges) {
        if (edges.length == 0) return 0;

        //Time: O(V)
        int sum = 0, maxValue = 0;
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
            maxValue = Math.max(maxValue, nums[i]);
        }
        int maxCount = sum / maxValue;
        if (sum % maxCount == 0 && maxCount == nums.length) return nums.length - 1;

        //build graph
        //Time: O(V + E); Space: O(V + E)
        List<Integer>[] graph = new List[nums.length];
        int[] degrees = new int[nums.length];
        for (int i = 0; i < nums.length; i++) graph[i] = new ArrayList<>();
        for (int[] edge: edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
            degrees[edge[0]]++;
            degrees[edge[1]]++;
        }

        //Time: O(logV * V); Space:O(V)
        for (int k = maxCount; k > 1; k--){
            if (sum % k == 0
                    && helper_bfs(Arrays.copyOf(nums, nums.length),
                    Arrays.copyOf(degrees, degrees.length), graph, sum / k))
                return k - 1;
        }
        return 0;
    }

    private boolean helper_bfs(int[] nums, int[] degrees, List<Integer>[] graph, int target) {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < degrees.length; i++)
            if (degrees[i] == 1) queue.add(i);

        while (!queue.isEmpty()) {
            int v = queue.poll();
            if (nums[v] > target) return false;
            if (nums[v] == target) nums[v] = 0;

            for (int p : graph[v]) {
                nums[p] += nums[v];
                degrees[v]--; degrees[p]--;
                if (degrees[p] == 1) queue.add(p);
            }
        }
        return true;
    }

    //1.DFS
    //Runtime: 64 ms, faster than 97.17% of Java online submissions for Create Components With Same Value.
    //Memory Usage: 103.1 MB, less than 83.02% of Java online submissions for Create Components With Same Value.
    //Time: O(V + V + E + logV * V); Space: O(V + E + V)
    //Time: O(logV * V); Space: O(V)
    public int componentValue_1(int[] nums, int[][] edges) {
        if (edges.length == 0) return 0;

        //Time: O(V)
        int sum = 0, maxValue = 0;
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
            maxValue = Math.max(maxValue, nums[i]);
        }
        int maxCount = sum / maxValue;
        if (sum % maxCount == 0 && maxCount == nums.length) return nums.length - 1;

        //build graph
        //Time: O(V + E); Space: O(V + E)
        List<Integer>[] graph = new List[nums.length];
        for (int i = 0; i < nums.length; i++) graph[i] = new ArrayList<>();
        for (int[] edge: edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        //Time: O(logV * V); Space:O(V)
        for (int k = maxCount; k > 1; k--)
            if (sum % k == 0 && helper_dfs_sumTarget(nums, graph,0, -1, sum / k) == 0)
                return k - 1;
        return 0;
    }

    private int helper_dfs_sumTarget(int[] nums, List<Integer>[] graph,
                       int index, int parent, int target) {
        //the sum of value of current subtree
        int sum = nums[index];
        for (int neighbour : graph[index]){
            if (neighbour != parent) {
                sum += helper_dfs_sumTarget(nums, graph, neighbour, index, target);
                if (sum > target) return sum;
            }
        }
        //sum == target means current subtree can be deleted from tree.
        return sum == target ? 0 : sum;
    }


}
