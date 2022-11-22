package LeetCode;


import ADraft.TreeDemo.*;
import javafx.util.Pair;

import java.util.*;

/**
 * Given the root of a complete binary tree, return the number of the nodes in the tree.
 *
 * According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 *
 * Design an algorithm that runs in less than O(n) time complexity.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,5,6]
 * Output: 6
 * Example 2:
 *
 * Input: root = []
 * Output: 0
 * Example 3:
 *
 * Input: root = [1]
 * Output: 1
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 5 * 104].
 * 0 <= Node.val <= 5 * 104
 * The tree is guaranteed to be complete.
 */
public class N222MCountCompleteTreeNodes {

    //4.Binary Search
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Count Complete Tree Nodes.
    //Memory Usage: 50.5 MB, less than 11.61% of Java online submissions for Count Complete Tree Nodes.
    //Time: O(log(N) * d); Space:O(1)
    public int countNodes(TreeNode root) {
        if (root == null) return 0;

        //Time: O(d)
        int height = 0;
        TreeNode node = root;
        while(node.left != null) {
            node = node.left;
            height++;
        }
        if (height == 0) return 1;

        //Time: O(log(N) * d); Space:O(1)
        int left = 1, right = (int)Math.pow(2, height) - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (helper_BinarySearch(mid, height, root)) left = mid + 1;
            else right = mid - 1;
        }
        return ((int) Math.pow(2, height)) - 1 + left;
    }

    //Time: O(d); Space:O(1)
    private boolean helper_BinarySearch(int idx, int d, TreeNode node) {
        int left = 0, right = (int)Math.pow(2, d) - 1;
        for (int i = 0; i < d; i++) {
            int mid = (left + right) / 2;
            if (idx <= mid){
                node = node.left;
                right = mid;
            }else{
                node = node.right;
                left = mid + 1;
            }
        }
        return node != null;
    }

    //3.DFS
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Count Complete Tree Nodes.
    //Memory Usage: 44.8 MB, less than 94.58% of Java online submissions for Count Complete Tree Nodes.
    //Time:O(N); Space: O(logN)
    public int countNodes3(TreeNode root) {
        if (root == null) return 0;
        int res = helper_DFS(root, 1);
        return res != 0 ? res: ((int) Math.pow(2, layer1)) - 1 ;
    }

    int count = 0;
    int layer1 = 0;
    private int helper_DFS(TreeNode node, int layer) {
        if (node.right == null) {
            if (node.left != null)
                return ((int)Math.pow(2, layer + 1)) - 1 - count * 2 - 1;

            if (layer1 != 0 && layer > layer1)
                return ((int) Math.pow(2, layer)) - 1 - count * 2;
            else {
                if (layer1 == 0) layer1 = layer;
                count++;
            }
        } else {
            int res = helper_DFS(node.right, layer + 1);
            if (res != 0) return res;
            return helper_DFS(node.left, layer + 1);
        }
        return 0;
    }

    //2.DFS
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Count Complete Tree Nodes.
    //Memory Usage: 50.7 MB, less than 9.89% of Java online submissions for Count Complete Tree Nodes.
    //Time:O(N); Space: O(logN)
    public int countNodes2(TreeNode root) {
        return root != null ? 1 + countNodes2(root.left) + countNodes2(root.right) : 0;
    }

    //1.BFS
    //Runtime: 25 ms, faster than 5.20% of Java online submissions for Count Complete Tree Nodes.
    //Memory Usage: 50.3 MB, less than 22.17% of Java online submissions for Count Complete Tree Nodes.
    //Time:O(N); Space: O(N)
    public int countNodes_1(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        int res = 0;
        if (!queue.isEmpty()){
            TreeNode node  = queue.poll();
            res++;
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        return res;
    }
}
