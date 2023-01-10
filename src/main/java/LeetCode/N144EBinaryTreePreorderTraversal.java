package LeetCode;

import ADraft.TreeDemo.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class N144EBinaryTreePreorderTraversal {

    //3.Mirror
    //Runtime: 0ms 100%; Memory: 40.3MB 88%
    //Time: O(N); Space: O(1);
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode curr = root, last;

        while (curr != null) {

            if (curr.left == null) {
                res.add(curr.val);
                curr = curr.right;

            }else{

                last = curr.left;
                while(last.right != null && last.right != curr)
                    last = last.right;

                if (last.right == null){
                    last.right = curr;
                    res.add(curr.val);
                    curr = curr.left;
                }else{
                    last.right = null;
                    curr = curr.right;
                }
            }
        }
        return res;
    }

    //2.Iteration
    //Runtime: 0ms 100%; Memory: 40.2MB 97%
    //Time: O(N); Space: O(N);
    public List<Integer> preorderTraversal_2(TreeNode root) {
        List<Integer> res = new ArrayList();

        Deque<TreeNode> stack = new ArrayDeque<>();
        if (root != null) stack.addFirst(root);

        while (!stack.isEmpty()){
            TreeNode node = stack.pollFirst();
            res.add(node.val);
            if (node.right != null) stack.addFirst(node.right);
            if (node.left != null) stack.addFirst(node.left);
        }
        return res;
    }


    //1.Recursion
    //Runtime: 0ms 100%; Memory: 40.4MB 81%
    //Time: O(N); Space: O(N);
    public List<Integer> preorderTraversal_1(TreeNode root) {
        List<Integer> res = new ArrayList();
        helper_DFS(root, res);
        return res;
    }


    private void helper_DFS(TreeNode node, List<Integer> res){
        if (node == null) return;
        res.add(node.val);
        helper_DFS(node.left, res);
        helper_DFS(node.right, res);
    }
}
