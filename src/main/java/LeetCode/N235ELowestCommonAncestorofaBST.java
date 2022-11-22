package LeetCode;


import ADraft.TreeDemo.*;

/**
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 *
 * According to the definition of LCA on Wikipedia:
 * “The lowest common ancestor is defined between two nodes p and q as the lowest node in T
 * that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 *
 *
 * Example 1:
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * Output: 6
 * Explanation: The LCA of nodes 2 and 8 is 6.
 *
 *
 * Example 2:
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * Output: 2
 * Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
 *
 *
 * Example 3:
 * Input: root = [2,1], p = 2, q = 1
 * Output: 2
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 105].
 * -109 <= Node.val <= 109
 * All Node.val are unique.
 * p != q
 * p and q will exist in the BST.
 */

/**
 * E - 30-
 *
 */
public class N235ELowestCommonAncestorofaBST {

    //Runtime: 5 ms, faster than 88.81% of Java online submissions for Lowest Common Ancestor of a Binary Search Tree.
    //Memory Usage: 50.5 MB, less than 9.38% of Java online submissions for Lowest Common Ancestor of a Binary Search Tree.
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res;
        if(p.val > q.val){
            res = p;
            p = q;
            q = res;
        }

        res = root;
        while (res != null) {
            if (p.val <= res.val && res.val <= q.val) return res;
            else {
                if (res.val < p.val) res = res.right;
                else res = res.left;
            }
        }
        return res;
    }

    //Runtime: 7 ms, faster than 61.69% of Java online submissions for Lowest Common Ancestor of a Binary Search Tree.
    //Memory Usage: 50 MB, less than 36.82% of Java online submissions for Lowest Common Ancestor of a Binary Search Tree.
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = root;
        TreeNode node = root;
        while (node != null){
            if(p.val == node.val) break;
            if (p.val < node.val) node = node.left;
            else node = node.right;

            if (findNodeInBST(node, q)) res = node;
        }
        return res;
    }

    private boolean findNodeInBST(TreeNode root, TreeNode p){
        TreeNode node = root;
        while (node != null){
            if(p.val == node.val) return true;
            if (p.val < node.val) node = node.left;
            else node = node.right;
        }
        return false;
    }

//    private List<TreeNode> findNodeAncestorInBST(TreeNode root, TreeNode p){
//        List<TreeNode> ancestors = new ArrayList<>();
//        TreeNode node = root;
//        ancestors.add(node);
//        while(p.val != node.val){
//            if (p.val < node.val) node = node.left;
//            else node = node.right;
//        }
//        return ancestors;
//    }
}
