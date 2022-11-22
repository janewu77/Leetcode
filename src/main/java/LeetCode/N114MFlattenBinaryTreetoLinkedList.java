package LeetCode;

/**
 * Given the root of a binary tree, flatten the tree into a "linked list":
 *
 * The "linked list" should use the same TreeNode class where the right child
 * pointer points to the next node in the list and the left child pointer is always null.
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 *
 *
 * Example 1:
 * Input: root = [1,2,5,3,4,null,6]
 * Output: [1,null,2,null,3,null,4,null,5,null,6]
 * Example 2:
 *
 * Input: root = []
 * Output: []
 *
 *
 * Example 3:
 *
 * Input: root = [0]
 * Output: [0]
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 2000].
 * -100 <= Node.val <= 100
 * Follow up: Can you flatten the tree in-place (with O(1) extra space)?
 *
 *
 */

/**
 * m - [time: 15-]
 *  - binary tree 的常规操作
 *
 */
public class N114MFlattenBinaryTreetoLinkedList {


    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Flatten Binary Tree to Linked List.
    //Memory Usage: 43.1 MB, less than 22.28% of Java online submissions for Flatten Binary Tree to Linked List.
    public void flatten(TreeNode root) {
        if (root == null) return;
        flatten_helper(root);
    }

    private TreeNode flatten_helper(TreeNode node){
        if (node == null) return node;
        if (node.left == null && node.right == null) return node;

        TreeNode rightTail = flatten_helper(node.right);
        TreeNode leftTail = flatten_helper(node.left);

        if (leftTail != null) {
            leftTail.right = node.right;
            node.right = node.left;
            node.left = null;
        }
        return rightTail == null? leftTail: rightTail;
    }

    //Runtime: 1 ms, faster than 78.58% of Java online submissions for Flatten Binary Tree to Linked List.
    //Memory Usage: 42 MB, less than 83.03% of Java online submissions for Flatten Binary Tree to Linked List.
    //iterative solution
    //a little bit slower than recursive solution, because this one will visit a node at most twice.
    //每个结点可能被访问二次。（第一次是currRoot。第二次是找最右时）
    public void flatten_3(TreeNode root) {

        TreeNode currRoot = root;
        TreeNode p = null;
        while(currRoot != null){

            if (currRoot.left != null) {
                p = currRoot.left;

                while (p.right != null) p = p.right;
                p.right = currRoot.right;

                currRoot.right = currRoot.left;
                currRoot.left = null;
            }
            currRoot = currRoot.right;
        }
    }

    //Runtime: 1 ms, faster than 78.58% of Java online submissions for Flatten Binary Tree to Linked List.
    //Memory Usage: 42.8 MB, less than 43.96% of Java online submissions for Flatten Binary Tree to Linked List.
    //recursion
    private TreeNode prev;
    public void flatten_2(TreeNode root) {
        if (root == null) return;

        flatten_2(root.right);
        flatten_2(root.left);
        root.right = prev;
        root.left = null;

        prev = root;
    }

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Flatten Binary Tree to Linked List.
    //Memory Usage: 41.8 MB, less than 93.24% of Java online submissions for Flatten Binary Tree to Linked List.
    //recursion
    //time: O(N) ; Spance:  O(N)
    //其他解法：另写一个方法，每次返回tail。就可以跳过找 左子树的最右结点了。
    public void flatten_1(TreeNode root) {
        if (root == null) return;

        TreeNode tmp = root.right;

        flatten_1(root.right);
        flatten_1(root.left);

        root.right = root.left;
        root.left = tmp;

        tmp = root;
        while(tmp.right != null) tmp = tmp.right;
        tmp.right = root.left;
        root.left = null;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
