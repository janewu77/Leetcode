package LeetCode;

import ADraft.TreeDemo.*;
import javafx.util.Pair;

import java.util.*;

/**
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
 * (i.e., from left to right, then right to left for the next level and alternate between).
 *
 *
 *
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[20,9],[15,7]]
 *
 * Example 2:
 * Input: root = [1]
 * Output: [[1]]
 *
 * Example 3:
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 2000].
 * -100 <= Node.val <= 100
 */

/**
 * M - 耗时60+
 *  - 自己把自己抗了 a*10+b 当a是负数是会出问题
 *
 */
public class N103MBinaryTreeZigzagLevelOrderTraversal {

    public static void main(String[] args){
        //[3,9,20,null,null,15,7]
        List<Integer> data = new ArrayList<>();//{3,9,20,null,null,15,7};
        data.add(1);
        data.add(2);
        data.add(3);
        data.add(4);
        data.add(5);
        data.add(6);
        data.add(7);
//        TreeNode root = new ADraft.TreeDemo().buildTree(data);
//        System.out.println("ddd");
//
//        List<List<Integer>> result = new N103MBinaryTreeZigzagLevelOrderTraversal().zigzagLevelOrder(root);
//        System.out.println("ddd");
    }



    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();

        List<List<Integer>> result = new ArrayList<>();
        int flag = 0; //0:left>right; 1:left<right

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair<>(root, flag));

        List<Integer> layer = new ArrayList<>();
        result.add(layer);

        Deque<Integer> dqValue = new LinkedList<>();
        while (!q.isEmpty()){
            Pair<TreeNode,Integer> currP = q.poll();
            TreeNode node = currP.getKey();
            int currF = currP.getValue();

            if (currF != flag){
                while(!dqValue.isEmpty()) layer.add(dqValue.pollFirst());

                layer = new ArrayList<>();
                result.add(layer);
                flag = currF;
            }

            int nextFlag = (currF == 0? 1:0);
            if (node.left != null) q.offer(new Pair<>(node.left, nextFlag));
            if (node.right != null) q.offer(new Pair<>(node.right, nextFlag));

            if (currF == 0)
                dqValue.addLast(node.val);
            else
                dqValue.addFirst(node.val);
        }

        while(!dqValue.isEmpty()) layer.add(dqValue.pollFirst());
        return result;
    }


}
