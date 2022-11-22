package LeetCode;

import ADraft.TreeDemo.*;

import java.util.*;

/**
 * You are given the root of a binary search tree and an array queries of size n consisting of positive integers.
 *
 * Find a 2D array answer of size n where answer[i] = [mini, maxi]:
 *
 * mini is the largest value in the tree that is smaller than or equal to queries[i]. If a such value does not exist, add -1 instead.
 * maxi is the smallest value in the tree that is greater than or equal to queries[i]. If a such value does not exist, add -1 instead.
 * Return the array answer.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [6,2,13,1,4,9,15,null,null,null,null,null,null,14], queries = [2,5,16]
 * Output: [[2,2],[4,6],[15,-1]]
 * Explanation: We answer the queries in the following way:
 * - The largest number that is smaller or equal than 2 in the tree is 2, and the smallest number that is greater or equal than 2 is still 2. So the answer for the first query is [2,2].
 * - The largest number that is smaller or equal than 5 in the tree is 4, and the smallest number that is greater or equal than 5 is 6. So the answer for the second query is [4,6].
 * - The largest number that is smaller or equal than 16 in the tree is 15, and the smallest number that is greater or equal than 16 does not exist. So the answer for the third query is [15,-1].
 * Example 2:
 *
 *
 * Input: root = [4,null,9], queries = [3]
 * Output: [[-1,4]]
 * Explanation: The largest number that is smaller or equal to 3 in the tree does not exist, and the smallest number that is greater or equal to 3 is 4. So the answer for the query is [-1,4].
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 105].
 * 1 <= Node.val <= 106
 * n == queries.length
 * 1 <= n <= 105
 * 1 <= queries[i] <= 106
 */
public class N2476MClosestNodesQueriesinaBinarySearchTree {

    //2.treeset
    //Runtime: 204 ms, faster than 50.00% of Java online submissions for Closest Nodes Queries in a Binary Search Tree.
    //Memory Usage: 92 MB, less than 100.00% of Java online submissions for Closest Nodes Queries in a Binary Search Tree.
    //Time: O(N * Log(N) + Q * Log(N)); Space: O(N)
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        helper(root, treeSet);

        List<List<Integer>> res = new ArrayList<>();
        for (int query : queries) {
            if (treeSet.contains(query))
                res.add(Arrays.asList(query, query));
            else {
                Integer min = treeSet.floor(query);
                Integer max = treeSet.ceiling(query);
                res.add(Arrays.asList(min == null ? -1 : min, max == null ? -1 : max));
            }
        }
        return res;
    }

    private void helper(TreeNode node, TreeSet<Integer> treeSet){
        if (node.left != null) helper(node.left, treeSet);
        treeSet.add(node.val);
        if (node.right != null) helper(node.right, treeSet);
    }

    //1.orderd list
    //Runtime: 99 ms, faster than 100.00% of Java online submissions for Closest Nodes Queries in a Binary Search Tree.
    //Memory Usage: 87.7 MB, less than 100.00% of Java online submissions for Closest Nodes Queries in a Binary Search Tree.
    //Time: O(N + Q * Log(N)); Space: O(N)
    public List<List<Integer>> closestNodes_1(TreeNode root, List<Integer> queries) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = helper_inOrder(root);
        for (int query : queries) {
            int idx = Collections.binarySearch(list, query);
            if (idx >= 0) res.add(Arrays.asList(query, query));
            else {
                idx = -idx - 1;
                int min = idx - 1 >= 0 ? list.get(idx - 1) : -1;
                int max = idx < list.size() ? list.get(idx) : -1;
                res.add(Arrays.asList(min, max));
            }
        }
        return res;
    }

    //Time: O(N); Space: O(N)
    private List<Integer> helper_inOrder(TreeNode node){
        List<Integer> list = new ArrayList<>();
        if (node.left != null) list = helper_inOrder(node.left);
        list.add(node.val);
        if (node.right != null) list.addAll(helper_inOrder(node.right));
        return list;
    }

}
