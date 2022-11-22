package LeetCode;

import ADraft.TreeDemo.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/***
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal
 * of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 *
 *
 *
 * Example 1:
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 *
 *
 * Example 2:
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 *
 *
 * Constraints:
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder and inorder consist of unique values.
 * Each value of inorder also appears in preorder.
 * preorder is guaranteed to be the preorder traversal of the tree.
 * inorder is guaranteed to be the inorder traversal of the tree.
 *
 */

/**
 * M - 【time：35-】
 *  - 直接递归 但性能不太好
 *  - 利hashmap提升性能
 */
public class N105MConstructBinaryTreefromPreorderandInorderTraversal {

    public static void main(String[] args){

        int[] preorder = new int[]{3,9,20,15,7};
        int[] inorder = new int[]{9,3,15,20,7};
        TreeNode root;
        preorder = new int[]{1,2};
        inorder = new int[]{1,2};
        root = new N105MConstructBinaryTreefromPreorderandInorderTraversal().buildTree(preorder, inorder);
        System.out.println(root.val);

        preorder = new int[]{1,2,3};
        inorder = new int[]{3,2,1};
        root = new N105MConstructBinaryTreefromPreorderandInorderTraversal().buildTree(preorder, inorder);
        System.out.println(root.val);

        preorder = new int[]{3,9,20,15,7};
        inorder = new int[]{9,3,15,20,7};
        root = new N105MConstructBinaryTreefromPreorderandInorderTraversal().buildTree(preorder, inorder);
        System.out.println(root.val);

    }

//    //不用hashmap每次现找。（队列较短）
//    int preorderIndex3 = 0;
//    public TreeNode buildTree3(int[] preorder, int[] inorder) {
//        if (preorder.length < 1) return null;
//        if (preorder.length == 1) return new TreeNode(preorder[0]);
//
//        preorderIndex3 = 0;
//        return buildTreeHelper(preorder, inorder,0, preorder.length-1);
//    }
//    private TreeNode buildTreeHelper(int[] preorder,int[] inorder, int from, int to){
//        if (from > to) return null;
//
//        TreeNode root = new TreeNode(preorder[preorderIndex3++]);
//        root.left= buildTreeHelper(preorder, inorder,from, findIdxInInorder(inorder, root.val)-1);
//        root.right = buildTreeHelper(preorder,inorder, findIdxInInorder(inorder, root.val)+1, to);
//        return root;
//    }
    //1 <= preorder.length <= 3000
//    private int findIdxInInorder(int[] inorder, int val){
//        for(int i = 0; i < inorder.length; i++)
//            if(inorder[i] == val) return i;
//        return -1;
//    }


    //Runtime: 3 ms, faster than 88.47% of Java online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
    //Memory Usage: 45.1 MB, less than 16.95% of Java online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
    //在 buildTree1上，用HashMap来提升性能
    int preorderIndex = 0;
    Map<Integer, Integer> inorderIdx;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length < 1) return null;
        if (preorder.length == 1) return new TreeNode(preorder[0]);

        //val : idx
        inorderIdx = new HashMap<>();
        for (int i = 0; i<inorder.length; i++)
            inorderIdx.put(inorder[i], i);

        preorderIndex = 0;
        return buildTreeHelper(preorder,0, preorder.length-1);
    }
    private TreeNode buildTreeHelper(int[] preorder, int from, int to){
        if (from > to) return null;

        TreeNode root = new TreeNode(preorder[preorderIndex++]);
        root.left= buildTreeHelper(preorder, from, inorderIdx.get(root.val)-1);
        root.right = buildTreeHelper(preorder, inorderIdx.get(root.val)+1, to);
        return root;
    }

    //Runtime: 42 ms, faster than 5.04% of Java online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
    //Memory Usage: 68.5 MB, less than 5.69% of Java online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        if (preorder.length < 1) return null;
        if (preorder.length == 1) return new TreeNode(preorder[0]);

        int n = preorder[0];
        TreeNode root = new TreeNode(n);

        int k = 0;
        for (int x: inorder) {
            if (x == n) break;
            k++;
        }

        // split to two parts(left & right subtree)
        int[] inorderL = Arrays.copyOfRange(inorder, 0, k);
        int[] inorderR = k+1 < inorder.length ? Arrays.copyOfRange(inorder, k+1, inorder.length) : new int[0];

        int[] preorderL = new int[inorderL.length];
        int[] preorderR = new int[inorderR.length];
        int l = 0; int r = 0;
        for(int i = 1; i<preorder.length; i++){
            if (l<inorderL.length) preorderL[l++] = preorder[i];
            else preorderR[r++] = preorder[i];
        }

        root.left= buildTree(preorderL, inorderL);
        root.right = buildTree(preorderR, inorderR);
        return root;
    }



}
