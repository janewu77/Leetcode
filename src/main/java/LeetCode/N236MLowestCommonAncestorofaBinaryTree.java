package LeetCode;

import ADraft.TreeDemo.*;

import java.util.*;

/**
 *
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined
 * between two nodes p and q as the lowest node in T that has both p and q as descendants
 * (where we allow a node to be a descendant of itself).”
 *
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 *
 * Example 2:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5,
 * since a node can be a descendant of itself according to the LCA definition.
 *
 * Example 3:
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 105].
 * -109 <= Node.val <= 109
 * All Node.val are unique.
 * p != q
 * p and q will exist in the tree.
 *
 */

/**
 * M - 【time：60+]
 *  - 有不同算法。
 *  - 代码可以写得很优雅。
 */

public class N236MLowestCommonAncestorofaBinaryTree {

    public static void main(String[] args){
        //[3,5,1,6,2,0,8,null,null,7,4
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        TreeNode node6 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node0 = new TreeNode(0);
        TreeNode node8 = new TreeNode(8);
        TreeNode node7 = new TreeNode(7);
        TreeNode node4 = new TreeNode(4);

        build(node3, node5, node1);
        build(node5, node6, node2);
        build(node1, node0, node8);
        build(node2, node7, node4);

        TreeNode result = new N236MLowestCommonAncestorofaBinaryTree().lowestCommonAncestor(node3, node4,node7);
        System.out.println(result.val);

    }
    private static void build(TreeNode p, TreeNode l , TreeNode r){
        p.left = l; p.right = r;
    }

    //最简写法 论坛看来的。
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q){
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        return (left == null) ? right : (right == null) ? left : root;
    }

    //Runtime: 29 ms, faster than 5.51% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
    //Memory Usage: 49 MB, less than 8.13% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
    //利用先根、后根遍历来定位共同的根。
    //先根遍历，只需要保存到第一个点之前的节点。然后从后往前，与后根序列比对。找出第一个共有。
    //后根遍历只需要保留val set就够了。
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> preVisitNodes = new ArrayList<>();
        Set<Integer> postNodeVals = new HashSet<>();

        preRootVisit(root, preVisitNodes, p.val, q.val);
        postRootVisit(root, postNodeVals, p.val, q.val);

        // if p&q are in different order in Preorder and Postorder visit,
        // then p or q is the other one's ancestor
        if (preVisitNodes.get(preVisitNodes.size()-1).val == tmpNode.val)
            return tmpNode;

        //find the LCA
        for (int i = preVisitNodes.size()-1; i>=0; i--) {
            TreeNode node = preVisitNodes.get(i);
            if (postNodeVals.contains(node.val)) return node;
        }
        return null;
    }

    TreeNode tmpNode = null;
    //Preorder & only keep nodes those before p or q
    private void preRootVisit(TreeNode root, List<TreeNode> nodeList , int val1, int val2 ){
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            nodeList.add(node);
            if (node.val == val1 || node.val == val2) return;
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
    }

    //Postorder & only keep the values，after both p & q
    private void postRootVisit(TreeNode node, Set<Integer> postNodeVals, int val1, int val2 ){
        if (node == null) return;
        postRootVisit(node.left, postNodeVals, val1, val2);
        postRootVisit(node.right, postNodeVals, val1, val2);
        if(node.val == val1 || node.val == val2) {
            postNodeVals.clear();
            tmpNode = node;
        }else postNodeVals.add(node.val);
    }

    //////////////
    //Runtime: 7 ms, faster than 89.23% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
    //Memory Usage: 43.7 MB, less than 90.74% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
    // 先序遍历，找到P并记录它的所有祖先结点集。 然后遍历【祖先结点集】如果从祖先结点能找到q，则该点为最近祖先。
    Queue<TreeNode> ancestors = new LinkedList<>();
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        getAncestors(root, p.val);
        while (!ancestors.isEmpty()){
            TreeNode node = ancestors.poll();
            if (preVisit(node, q.val)) return node;
        }
        return null;
    }

    private boolean preVisit(TreeNode node, int val){
        if (node == null) return false;
        if (node.val == val) return true;
        if (preVisit(node.left, val)) return true;
        if (preVisit(node.right, val)) return true;
        return false;
    }

    //找出所有父，按序进队列。 最近的父在最前面。
    private boolean getAncestors(TreeNode node, int val){
        if (node == null) return false;

        if(node.val == val) {
            ancestors.add(node);
            return true;
        }
        if (getAncestors(node.left, val)) {
            ancestors.add(node);
            return true;
        }
        if (getAncestors(node.right, val)){
            ancestors.add(node);
            return true;
        }
        return false;
    }

}
