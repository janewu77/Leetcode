package LeetCode;


/**
 * Given a node in a binary search tree, return the in-order successor of that node in the BST.
 * If that node has no in-order successor, return null.
 *
 * The successor of a node is the node with the smallest key greater than node.val.
 *
 * You will have direct access to the node but not to the root of the tree.
 * Each node will have a reference to its parent node. Below is the definition for Node:
 *
 * class Node {
 *     public int val;
 *     public Node left;
 *     public Node right;
 *     public Node parent;
 * }
 *
 * Example 1:
 * Input: tree = [2,1,3], node = 1
 * Output: 2
 * Explanation: 1's in-order successor node is 2. Note that both the node and the return value is of Node type.
 *
 * Example 2:
 * Input: tree = [5,3,6,2,4,null,null,1], node = 6
 * Output: null
 * Explanation: There is no in-order successor of the current node, so the answer is null.
 *
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 104].
 * -105 <= Node.val <= 105
 * All Nodes will have unique values.
 *
 */

/**
 * M - 耗时30-
 *  - 先理清存在几种情况，然后分别处理
 */
public class N510MInorderSuccessorinBSTII {


    public Node inorderSuccessor(Node node) {
        if (node.right != null){
            //存在右子女 ：若右子女没有左孙，则返回右子女；若右子女有左孙，则最左孙
            node = node.right;
            while (node.left != null) node = node.left;
            return node;
        }else {
            //为父的右： 往上找： 若是父的右，继续往上.
            //为父的左： 返回父
            while (node.parent != null && node.parent.right == node) node = node.parent;
            return node.parent;
        }
    }



    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    };
}
