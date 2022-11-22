package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node,
 * the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 *
 * Example 1:
 * Input: root = [1,2,3,4,5,null,7]
 * Output: [1,#,2,3,#,4,5,7,#]
 * Explanation: Given the above binary tree (Figure A), your function should populate each next pointer
 * to point to its next right node, just like in Figure B. The serialized output is in level order as connected
 * by the next pointers, with '#' signifying the end of each level.
 *
 *
 * Example 2:
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 6000].
 * -100 <= Node.val <= 100
 *
 *
 * Follow-up:
 *
 * You may only use constant extra space.
 * The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.
 */

/**
 * M = 耗时（20-
 *  - 利用已有next. 注意中间缺node的情况
 */
public class N117MPopulatingNRightPointersinEachNodeII {


    //Runtime: 1 ms, faster than 75.07% of Java online submissions for Populating Next Right Pointers in Each Node II.
    //Memory Usage: 41.7 MB, less than 94.24% of Java online submissions for Populating Next Right Pointers in Each Node II.
    //
    public Node connect(Node root) {
        if (root == null) return null;
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()){
            Node node = q.poll();
            if (node.left != null){
                node.left.next = (node.right != null? node.right : getRightNeighbourChild(node.next));
                q.add(node.left);
            }
            if (node.right != null){
                node.right.next = getRightNeighbourChild(node.next);
                q.add(node.right);
            }
        }
        return root;
    }

    private Node getRightNeighbourChild(Node node){
        //find the closest right neighbour (it has at one child ). or return null
        while (node != null && node.left == null && node.right == null) node = node.next;
        if (node == null) return node;
        return node.left != null? node.left : node.right;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
}
