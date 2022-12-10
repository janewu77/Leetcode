package LeetCode;

import ADraft.TreeDemo.*;
import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Given the root of a binary tree, find the maximum value v for which there exist different nodes a and b where v = |a.val - b.val| and a is an ancestor of b.
 *
 * A node a is an ancestor of b if either: any child of a is equal to b or any child of a is an ancestor of b.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [8,3,10,1,6,null,14,null,null,4,7,13]
 * Output: 7
 * Explanation: We have various ancestor-node differences, some of which are given below :
 * |8 - 3| = 5
 * |3 - 7| = 4
 * |8 - 1| = 7
 * |10 - 13| = 3
 * Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
 * Example 2:
 *
 *
 * Input: root = [1,null,2,null,0,3]
 * Output: 3
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 5000].
 * 0 <= Node.val <= 105
 */

public class N1026MMaximumDifferenceBetweenNodeandAncestor {

    //2.BFS
    //Runtime: 1ms, 80.2%; Memory: 41.7MB, 97.19%
    //Time: O(N); Space: O(N)
    public int maxAncestorDiff(TreeNode root) {
        Queue<Pair<TreeNode, int[]>> queue = new ArrayDeque<>();
        queue.add(new Pair<>(root, new int[]{root.val, root.val}));

        int res = 0;
        while (!queue.isEmpty()) {
            Pair<TreeNode, int[]> pair = queue.poll();
            TreeNode node = pair.getKey();
            int max = pair.getValue()[0];
            int min = pair.getValue()[1];
            if (node.left == null && node.right == null) {
                res = Math.max(res, max - min);
                continue;
            }

            if (node.left != null)
                queue.add(new Pair<>(node.left, new int[]{Math.max(max, node.left.val), Math.min(min, node.left.val)}));

            if (node.right != null)
                queue.add(new Pair<>(node.right, new int[]{Math.max(max, node.right.val), Math.min(min, node.right.val)}));
        }

        return res;
    }


    //1.DFS
    //Runtime: 0ms, 100%; Memory: 41.7MB, 97.19%
    //Time: O(N); Space: O(N)
    public int maxAncestorDiff_1(TreeNode root) {
        return helper_dfs(root, root.val, root.val);
    }

    public int helper_dfs(TreeNode node, int max, int min){
        if (node == null) return max - min;

        max = Math.max(max, node.val);
        min = Math.min(min, node.val);

        int left = helper_dfs(node.left, max, min);
        int right = helper_dfs(node.right, max, min);

        return Math.max(left, right);
    }
}
