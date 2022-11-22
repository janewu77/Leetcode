package Contest;

import ADraft.TreeDemo.*;
import javafx.util.Pair;

import java.util.*;

import static java.time.LocalTime.now;

/**
 * You are given the root of a binary tree with n nodes. Each node is assigned a unique value from 1 to n. You are also given an array queries of size m.
 *
 * You have to perform m independent queries on the tree where in the ith query you do the following:
 *
 * Remove the subtree rooted at the node with the value queries[i] from the tree. It is guaranteed that queries[i] will not be equal to the value of the root.
 * Return an array answer of size m where answer[i] is the height of the tree after performing the ith query.
 *
 * Note:
 *
 * The queries are independent, so the tree returns to its initial state after each query.
 * The height of a tree is the number of edges in the longest simple path from the root to some node in the tree.
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,3,4,2,null,6,5,null,null,null,null,null,7], queries = [4]
 * Output: [2]
 * Explanation: The diagram above shows the tree after removing the subtree rooted at node with value 4.
 * The height of the tree is 2 (The path 1 -> 3 -> 2).
 * Example 2:
 *
 *
 * Input: root = [5,8,9,2,1,3,7,4,6], queries = [3,2,4,8]
 * Output: [3,2,3,2]
 * Explanation: We have the following queries:
 * - Removing the subtree rooted at node with value 3. The height of the tree becomes 3 (The path 5 -> 8 -> 2 -> 4).
 * - Removing the subtree rooted at node with value 2. The height of the tree becomes 2 (The path 5 -> 8 -> 1).
 * - Removing the subtree rooted at node with value 4. The height of the tree becomes 3 (The path 5 -> 8 -> 2 -> 6).
 * - Removing the subtree rooted at node with value 8. The height of the tree becomes 2 (The path 5 -> 9 -> 3).
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is n.
 * 2 <= n <= 105
 * 1 <= Node.val <= n
 * All the values in the tree are unique.
 * m == queries.length
 * 1 <= m <= min(n, 104)
 * 1 <= queries[i] <= n
 * queries[i] != root.val
 */

/**
 * H - [time: 120+
 */

//2458. Height of Binary Tree After Subtree Removal Queries
public class N6223HHeightofBinaryTreeAfterSubtreeRemovalQueries {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        //doRun(true, "abaccb", new int[]{1,2,3});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, String beginWord) {
//        int res = new N6223HHeightofBinaryTreeAfterSubtreeRemovalQueries().treeQueries(beginWord);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //2.DFS : pre-order + post-order
    //Runtime: 46 ms, faster than 66.67% of Java online submissions for Height of Binary Tree After Subtree Removal Queries.
    //Memory Usage: 131.9 MB, less than 33.33% of Java online submissions for Height of Binary Tree After Subtree Removal Queries.
    //Time: O(N + Q); Space: O(N)
    //let N be the number of tree;
    public int[] treeQueries(TreeNode root, int[] queries) {
        int[] treeHeights = new int[100001];
        preorder(root, treeHeights, maxHeight = 0);
        postorder(root, treeHeights, maxHeight = 0);

        //result
        int[] res = new int[queries.length];
        for (int i = 0; i < res.length; i++) res[i] = treeHeights[queries[i]];
        return res;
    }
    int maxHeight = 0;
    private void preorder(TreeNode root, int[] treeHeights, int height) {
        if (root == null) return;
        treeHeights[root.val] = maxHeight;
        maxHeight = Math.max(maxHeight, height);
        preorder(root.left, treeHeights, height + 1);
        preorder(root.right, treeHeights, height + 1);
    }
    private void postorder(TreeNode root, int[] treeHeights, int height) {
        if (root == null) return;
        treeHeights[root.val] = Math.max(treeHeights[root.val], maxHeight);
        maxHeight = Math.max(maxHeight, height);
        postorder(root.right, treeHeights, height + 1);
        postorder(root.left, treeHeights, height + 1);
    }

    //1.DFS + BFS
    //Runtime: 33 ms, faster than 100.00% of Java online submissions for Height of Binary Tree After Subtree Removal Queries.
    //Memory Usage: 80.6 MB, less than 33.33% of Java online submissions for Height of Binary Tree After Subtree Removal Queries.
    //Time: O(N + Q); Space: O(N)
    //let N be the number of tree;
    public int[] treeQueries_1(TreeNode root, int[] queries) {
        int[] treeHeights = new int[100001];
        //DFS
        int treeHeight = helper_dfs(root, treeHeights);

        //BFS
        helper_bfs(root, treeHeights, treeHeight);

        //Time: O(Q)
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) res[i] = treeHeights[queries[i]];
        return res;
    }

    //Time: O(N); Space: O(N)
    private int helper_dfs(TreeNode node, int[] treeHeights){
        if (node == null) return -1;
        int left = -1, right = -1;

        if (node.left != null) left = helper_dfs(node.left, treeHeights);
        if (node.right != null) right = helper_dfs(node.right, treeHeights);
        return treeHeights[node.val] = Math.max(left, right) + 1;
    }

    //Time: O(N); Space: O(N)
    private void helper_bfs(TreeNode root, int[] treeHeights, int treeHeight){
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {

            int top1 = -1, top2 = -1;
            int queueSize = queue.size();
            List<TreeNode> list = new ArrayList<>();
            for (int i = 0; i < queueSize; i++) {
                TreeNode node = queue.poll();

                int subTreeHeight = treeHeights[node.val];
                if (subTreeHeight > top1) {
                    if (top1 > top2) top2 = top1;
                    top1 = subTreeHeight;
                }else if (subTreeHeight > top2) {
                    top2 = subTreeHeight;
                }

                list.add(node);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            if (list.size() == 1){
                treeHeights[list.get(0).val] = treeHeight - (top1 + 1);
                continue;
            }

            for (int i = 0; i < queueSize; i++)
                treeHeights[list.get(i).val] = treeHeight - (treeHeights[list.get(i).val] == top1 ? top1 - top2 : 0);
        }
    }

}
