package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * A linked list of length n is given such that each node contains an additional random pointer,
 * which could point to any node in the list, or null.
 *
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes,
 * where each new node has its value set to the value of its corresponding original node.
 * Both the next and random pointer of the new nodes should point to new nodes in the copied list
 * such that the pointers in the original list and copied list represent the same list state.
 * None of the pointers in the new list should point to nodes in the original list.
 *
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y,
 * then for the corresponding two nodes x and y in the copied list, x.random --> y.
 *
 * Return the head of the copied linked list.
 *
 * The linked list is represented in the input/output as a list of n nodes.
 * Each node is represented as a pair of [val, random_index] where:
 *
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) that the random pointer points to,
 * or null if it does not point to any node.
 * Your code will only be given the head of the original linked list.
 *
 *
 *
 * Example 1:
 * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
 *
 *
 * Example 2:
 * Input: head = [[1,1],[2,1]]
 * Output: [[1,1],[2,1]]
 *
 *
 * Example 3:
 * Input: head = [[3,null],[3,0],[3,null]]
 * Output: [[3,null],[3,0],[3,null]]
 *
 *
 * Constraints:
 * 0 <= n <= 1000
 * -104 <= Node.val <= 104
 * Node.random is null or is pointing to some node in the linked list.
 *
 */

/**
 * M - 耗时20-
 *  - 用hashmap 保存新旧引用
 *  - 用"位置"来保存新旧引用的对应，以节省空间。
 *
 */
public class N138MCopyListwithRandomPointer {

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Copy List with Random Pointer.
    //Memory Usage: 45.5 MB, less than 48.73% of Java online submissions for Copy List with Random Pointer.
    //1。新旧节点先交织在一起，2。处理新节点的引用；3。分离新旧节点。
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        Node oldP = head;
        while(oldP != null){
            Node node = new Node(oldP.val);
            node.next = oldP.next;
            oldP.next = node;
            oldP = node.next;
        }

        oldP = head;
        while(oldP != null && oldP.next != null){
            if (oldP.random != null)
                oldP.next.random = oldP.random.next;
            oldP = oldP.next.next;
        }

        Node newNode = null;
        Node newP = null;
        oldP = head;
        while (oldP != null){
            if (newNode == null) {
                newNode = oldP.next;
                newP = newNode;
            }else {
                newP.next = oldP.next;
                newP = newP.next;
            }
            oldP.next = oldP.next.next;
            oldP = oldP.next;
        }
        return newNode;
    }

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Copy List with Random Pointer.
    //Memory Usage: 42 MB, less than 89.30% of Java online submissions for Copy List with Random Pointer.
    Map<Node, Node> memo = new HashMap<>();
    public Node copyRandomList1(Node head) {
        if (head == null) return null;
        Node newNode = null;
        Node p = null;

        Node old = head;
        while(old != null){
            Node node = new Node(old.val);
            node.random = old.random;
            memo.put(old, node);
            if (newNode == null) newNode = node;
            else p.next = node;
            p = node;
            old = old.next;
        }
        p = newNode;
        while(p != null){
            if (p.random != null) p.random = memo.getOrDefault(p.random, null);
            p = p.next;
        }
        return newNode;
    }


    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
