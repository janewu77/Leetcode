package LeetCode;

import ADraft.TreeDemo.*;
import javafx.util.Pair;

import java.util.*;
import static java.time.LocalTime.now;

/**
 * Given the root of a Binary Search Tree and a target number k, return true if there exist
 * two elements in the BST such that their sum is equal to the given target.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,3,6,2,4,null,7], k = 9
 * Output: true
 * Example 2:
 *
 *
 * Input: root = [5,3,6,2,4,null,7], k = 28
 * Output: false
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -104 <= Node.val <= 104
 * root is guaranteed to be a valid binary search tree.
 * -105 <= k <= 105
 */

/**
 * E - [time: 45-
 */
public class N653ETwoSumIVInputisaBST {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;
        TreeNode root;

        root = new TreeNode(5);
        TreeNode node3 = new TreeNode(3);
        TreeNode node6 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        root.left = node3; root.right = node6;
        node3.left = node2; node3.right = node4;
        node6.left = node7;
        doRun(true, root, 9);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(boolean expect, TreeNode root, int k) {
        boolean res = new N653ETwoSumIVInputisaBST().findTarget(root, k);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 5 ms, faster than 81.36% of Java online submissions for Two Sum IV - Input is a BST.
    //Memory Usage: 51 MB, less than 68.13% of Java online submissions for Two Sum IV - Input is a BST.
    //DFS + BST + two pointer
    //Time: O(N + N); Space: O(N + log(N))
    //Time: O(N); Space: O(N)
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        helper4_inorder(root, list);

        int left = 0, right = list.size() - 1;
        while (left < right){
            int sum = list.get(left) + list.get(right);
            if (sum == k)
                return true;
            else if (sum > k) right--;
            else left++;
        }
        return false;
    }

    //Time: O(N); Space: O(log(N))
    private void helper4_inorder(TreeNode node, List<Integer> list){
        if (node.left != null) helper4_inorder(node.left, list);
        list.add(node.val);
        if (node.right != null) helper4_inorder(node.right, list);
    }

    //Runtime: 3 ms, faster than 97.13% of Java online submissions for Two Sum IV - Input is a BST.
    //Memory Usage: 42.8 MB, less than 91.67% of Java online submissions for Two Sum IV - Input is a BST.
    //DFS + BST + binary search
    //Time: O(N + N + N*lgN); Space: O(N + log(N))
    //Time: O(N * lgN); Space: O(N)
    public boolean findTarget_3(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        helper3_inorder(root, list);

        int[] data = new int[list.size()];
        for (int i = 0; i < list.size(); i++) data[i] = list.get(i);

        for (int i = 0; i < data.length; i++)
            if (Arrays.binarySearch(data, i + 1, data.length, k - data[i]) > 0)
                return true;

        return false;
    }

    //Time: O(N); Space: O(log(N))
    private void helper3_inorder(TreeNode node, List<Integer> list){
        if (node.left != null) helper3_inorder(node.left, list);
        list.add(node.val);
        if (node.right != null) helper3_inorder(node.right, list);
    }


    //Runtime: 3 ms, faster than 97.13% of Java online submissions for Two Sum IV - Input is a BST.
    //Memory Usage: 42.3 MB, less than 98.84% of Java online submissions for Two Sum IV - Input is a BST.
    //Hashset + BFS + iteration
    //Time: O(N); Space: O(N + lgN)
    //Time: O(N); Space: O(N)
    public boolean findTarget_2(TreeNode root, int k) {
        Set<Integer> set  = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode node =queue.poll();
            if (set.contains(k - node.val)) return true;
            set.add(node.val);
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        return false;
    }

    //Runtime: 7 ms, faster than 56.82% of Java online submissions for Two Sum IV - Input is a BST.
    //Memory Usage: 51.7 MB, less than 38.47% of Java online submissions for Two Sum IV - Input is a BST.
    //HashSet + DFS + recursion
    //Time: O(N); Space: O(N + log(N))
    //Time: O(N); Space: O(N)
    public boolean findTarget_1(TreeNode root, int k) {
        return helper1_dfs(root, new HashSet<>(), k);
    }

    private boolean helper1_dfs(TreeNode node, Set<Integer> set, int target){
        boolean res = false;
        if(set.contains(target - node.val)) return true;
        set.add(node.val);
        if (node.left != null) res |= helper1_dfs(node.left, set, target);
        if (!res && node.right != null) res |= helper1_dfs(node.right, set, target);
        return res;
    }



}
