package LeetCode;


import ADraft.TreeDemo.*;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[9,20],[15,7]]
 * Example 2:
 *
 * Input: root = [1]
 * Output: [[1]]
 * Example 3:
 *
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 2000].
 * -1000 <= Node.val <= 1000
 */

/**
 * M - 耗时45-
 *  - 基本概念：按层遍历binary tree
 */
public class N102MBinaryTreeLevelOrderTraversal {

    public static void main(String[] args){
        //[3,9,20,null,null,15,7]
        List<Integer> data = new ArrayList<>();//{3,9,20,null,null,15,7};
        data.add(3);
        data.add(9);
        data.add(20);
        data.add(null);
        data.add(null);
        data.add(15);
        data.add(7);
//        TreeNode root = new ADraft.TreeDemo().buildTree(data);
//        System.out.println("ddd");

//        List<List<Integer>> result = new N102MBinaryTreeLevelOrderTraversal().levelOrder(root);
//        System.out.println("ddd");
    }

    //45m
    //Runtime: 1 ms, faster than 92.53% of Java online submissions for Binary Tree Level Order Traversal.
    //Memory Usage: 42.3 MB, less than 91.94% of Java online submissions for Binary Tree Level Order Traversal.
    //Queue
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();

        List<List<Integer>> result = new ArrayList<>();

        Queue<Pair> q = new LinkedList();
        q.offer(new Pair(root, 0));
        int layerNo = -1;
        List<Integer> layer =  new ArrayList<>();
        while (!q.isEmpty()){
            Pair<TreeNode, Integer> pair = q.poll();
            TreeNode node = pair.getKey();
            int currLayerNo = pair.getValue();

            if (currLayerNo != layerNo){
                layer = new ArrayList<>();
                result.add(layer);
                layerNo = currLayerNo;
            }
            layer.add(node.val);

            if (node.left != null) q.offer(new Pair(node.left, currLayerNo+1));
            if (node.right != null) q.offer(new Pair(node.right, currLayerNo+1));
        }
        return result;
    }

}







