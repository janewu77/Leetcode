package LeetCode;


import ADraft.TreeDemo;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [1,#,2,3,#,4,5,6,7,#]
 * Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 *
 * Example 2:
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 212 - 1].
 * -1000 <= Node.val <= 1000
 *
 *
 * Follow-up:
 *
 * You may only use constant extra space.
 * The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.
 *
 */

/**
 * M - 耗时30-
 *  - 用q 常规操作 按层遍历binarytree
 *  - 利用父的next(相当于有个快捷父）
 *
 */
public class N116MPopulatingNextRightPointersinEachNode {

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Populating Next Right Pointers in Each Node.
    //Memory Usage: 47.1 MB, less than 64.03% of Java online submissions for Populating Next Right Pointers in Each Node.
    //recursive
    public Node connect(Node root) {
        if (root == null) return root;
        if (root.left == null && root.right == null) return root;
        doSub(root);
        return root;
    }
    private void doSub(Node node){
        if (node.left == null) return;
        node.left.next = node.right;
        //利用已有的next
        if(node.next != null)
            node.right.next = node.next.left;
        doSub(node.left);
        doSub(node.right);
    }

    //Runtime: 5 ms, faster than 18.96% of Java online submissions for Populating Next Right Pointers in Each Node.
    //Memory Usage: 47.9 MB, less than 25.17% of Java online submissions for Populating Next Right Pointers in Each Node.
    //利用上一个next
    public Node connect2(Node root) {
        if (root == null) return root;
        if (root.left == null && root.right == null) return root;

        Queue<Node> q = new LinkedList();
        q.add(root);
        while (!q.isEmpty()){
            Node node = q.poll();
            if (node.left != null){
                node.left.next = node.right;
                //利用已有的next
                if(node.next != null)
                    node.right.next = node.next.left;
                q.add(node.left);
                q.add(node.right);
            }
        }
        return root;
    }


    //1.BFS
    //Runtime: 11 ms, faster than 8.26% of Java online submissions for Populating Next Right Pointers in Each Node.
    //Memory Usage: 47.6 MB, less than 35.28% of Java online submissions for Populating Next Right Pointers in Each Node.
    //Time: O(N); Space: O(N)
    public Node connect_1(Node root) {
        if (root == null || (root.left == null && root.right == null)) return root;

        Queue<Node> q = new LinkedList();
        q.add(root);

        while (!q.isEmpty()){
            int n = q.size();
            for (int i = 0; i < n; i++){
                Node node = q.poll();
                if (i + 1 < n) node.next = q.peek();

                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
        }
        return root;
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
    }
}
