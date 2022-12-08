package LeetCode;

import ADraft.TreeDemo.*;

import java.util.*;

/**
 * Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value sequence.
 *
 *
 *
 * For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
 *
 * Two binary trees are considered leaf-similar if their leaf value sequence is the same.
 *
 * Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
 * Output: true
 * Example 2:
 *
 *
 * Input: root1 = [1,2,3], root2 = [1,3,2]
 * Output: false
 *
 *
 * Constraints:
 *
 * The number of nodes in each tree will be in the range [1, 200].
 * Both of the given trees will have values in the range [0, 200].
 */
public class N872ELeafSimilarTrees {

    //3.dfs + array
    //Runtime: 0ms, 100%; Memory: 42.2MB, 29.16%
    //Time: O(N + M); Space: O(N + M)
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        int[] list1 = new int[200];
        int[] list2 = new int[200];

        int idx1 = helper_dfs(root1, list1, 0);
        int idx2 = helper_dfs(root2, list2, 0);
        if (idx1 != idx2) return false;
        return Arrays.equals(list1, list2);
    }

    private int helper_dfs(TreeNode node, int[] list, int idx) {
        if(node.left == null && node.right == null) {
            list[idx++] = node.val;
            return idx;
        }
        if(node.left != null) idx = helper_dfs(node.left, list, idx);
        if(node.right != null) idx = helper_dfs(node.right, list, idx);
        return idx;
    }


    //2. two dfs
    //Runtime: 1ms, 77.58%; Memory: 42.5 MB, 13.21%
    //Time: O(N + M); Space: O(N + M)
    public boolean leafSimilar_2(TreeNode root1, TreeNode root2) {
        List<Integer> list = new ArrayList<>();
        helper_dfs1(root1, list);
        return helper_dfs2(root2, list) && list.size() == 0;
    }
    private void helper_dfs1(TreeNode node, List<Integer> list) {
        if(node.left == null && node.right == null) list.add(node.val);
        if(node.left != null) helper_dfs1(node.left, list);
        if(node.right != null) helper_dfs1(node.right, list);
    }
    private boolean helper_dfs2(TreeNode node, List<Integer> list) {
        if(node.left == null && node.right == null) {
            if (list.size() <= 0 || node.val != list.get(list.size() - 1)) return false;
            list.remove(list.size() - 1);
            return true;
        }
        if (node.right != null && !helper_dfs2(node.right, list)) return false;
        if (node.left != null && !helper_dfs2(node.left, list)) return false;
        return true;
    }

    //1.dfs
    //Runtime: 1ms, 77.58%; Memory: 42.5 MB, 13.21%
    //Time: O(N + M); Space: O(N + M)
    public boolean leafSimilar_1(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        helper_dfs(root1, list1);
        helper_dfs(root2, list2);
        return list1.equals(list2);
    }

    private void helper_dfs(TreeNode node, List<Integer> list) {
        if(node.left == null && node.right == null) list.add(node.val);
        if(node.left != null) helper_dfs(node.left, list);
        if(node.right != null) helper_dfs(node.right, list);
    }
}
