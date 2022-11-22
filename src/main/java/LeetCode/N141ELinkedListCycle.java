package LeetCode;

/**
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 *
 * There is a cycle in a linked list if there is some node in the list that can be reached
 * again by continuously following the next pointer. Internally, pos is used to denote
 * the index of the node that tail's next pointer is connected to.
 * Note that pos is not passed as a parameter.
 *
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 *
 *
 *
 * Example 1:
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
 *
 *
 * Example 2:
 * Input: head = [1,2], pos = 0
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.
 *
 *
 * Example 3:
 * Input: head = [1], pos = -1
 * Output: false
 * Explanation: There is no cycle in the linked list.
 *
 *
 * Constraints:
 * The number of the nodes in the list is in the range [0, 104].
 * -105 <= Node.val <= 105
 * pos is -1 or a valid index in the linked-list.
 *
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * E - [耗时20m]
 *
 */
public class N141ELinkedListCycle {

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Linked List Cycle.
    //Memory Usage: 43.2 MB, less than 96.15% of Java online submissions for Linked List Cycle.
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;

        ListNode p1 = head;
        ListNode p2 = head.next;
        while (p1 != p2){
            if (p2 == null || p2.next == null) return false;
            p1 = p1.next;
            p2 = p2.next.next;
        }
        return true;
    }

    //Runtime: 8 ms, faster than 10.97% of Java online submissions for Linked List Cycle.
    //Memory Usage: 47.7 MB, less than 6.52% of Java online submissions for Linked List Cycle.
    public boolean hasCycle_hash(ListNode head) {
        if (head == null || head.next == null) return false;

        Set<ListNode> memo = new HashSet<>();

        while (head.next != null){
            if (memo.contains(head.next)) return true;
            memo.add(head.next);
            head = head.next;
        }
        return false;
    }



    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
