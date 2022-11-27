package Contest;

import Comm.ListNode;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;

import static java.time.LocalTime.now;


/**
 * You are given the head of a linked list.
 *
 * Remove every node which has a node with a strictly greater value anywhere to the right side of it.
 *
 * Return the head of the modified linked list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [5,2,13,3,8]
 * Output: [13,8]
 * Explanation: The nodes that should be removed are 5, 2 and 3.
 * - Node 13 is to the right of node 5.
 * - Node 13 is to the right of node 2.
 * - Node 8 is to the right of node 3.
 * Example 2:
 *
 * Input: head = [1,1,1,1]
 * Output: [1,1,1,1]
 * Explanation: Every node has value 1, so no nodes are removed.
 *
 *
 * Constraints:
 *
 * The number of the nodes in the given list is in the range [1, 105].
 * 1 <= Node.val <= 105
 */
//Time: 30

//2487. Remove Nodes From Linked List
public class N6247MRemoveNodesFromLinkedList {


     static public void main(String... args) throws IOException{
         System.out.println(now());
         System.out.println("==================");

         int[] expect= new int[]{13,8};
         int[] data= new int[]{5,2,13,3,8};
         doRun(expect, data);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int[] expect, int[] data) {
        ListNode head = ListNode.buildLink(data);
        ListNode res = new N6247MRemoveNodesFromLinkedList().removeNodes(head);

        ListNode expectRes = ListNode.buildLink(expect);
        boolean res1 = ListNode.isEqual(expectRes, res);
        System.out.println("["+(res1)+"]expect:" + expectRes.toString() + " res:" + res.toString());
    }

    //2.Recursion
    //Runtime: 18 ms, faster than 85.71% of Java online submissions for Remove Nodes From Linked List.
    //Memory Usage: 73.2 MB, less than 57.14% of Java online submissions for Remove Nodes From Linked List.
    //Time: O(N); Space: O(N)
    public ListNode removeNodes_1(ListNode head) {
        if (head == null) return head;
        head.next = removeNodes_1(head.next);
        return (head.next != null && head.next.val > head.val) ? head.next : head;
    }

    //1.a monotonic stack
    //Runtime: 28 ms, faster than 85.71% of Java online submissions for Remove Nodes From Linked List.
    //Memory Usage: 62 MB, less than 71.43% of Java online submissions for Remove Nodes From Linked List.
    //Time: O(N); Space: O(N)
    public ListNode removeNodes(ListNode head) {

        Deque<ListNode> deque = new ArrayDeque<>();
        ListNode node = head;

        while (node != null) {
            while (!deque.isEmpty() && deque.peekLast().val < node.val)
                deque.pollLast();

            if (!deque.isEmpty()) deque.peekLast().next = node;
            else head = node;

            deque.addLast(node);
            node = node.next;
        }
        return head;
    }


}


