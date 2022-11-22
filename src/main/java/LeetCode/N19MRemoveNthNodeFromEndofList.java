package LeetCode;


/**
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * Example 2:
 *
 * Input: head = [1], n = 1
 * Output: []
 * Example 3:
 *
 * Input: head = [1,2], n = 1
 * Output: [1]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is sz.
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 */

import java.util.HashMap;
import java.util.Map;

import static java.time.LocalTime.now;

/**
 * M - [time: 10-
 */
public class N19MRemoveNthNodeFromEndofList {


    //Runtime: 1 ms, faster than 75.59% of Java online submissions for Remove Nth Node From End of List.
    //Memory Usage: 42 MB, less than 71.99% of Java online submissions for Remove Nth Node From End of List.
    //one pass
    //Time: O(M); Space:O(1)
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode p = dummy;
        for (int i = 0; i <= n; i++) p = p.next;

        ListNode q = dummy;
        while (p !=null) {
            p = p.next;
            q = q.next;
        }

        q.next = q.next.next;
        return dummy.next;
    }

    //Runtime: 1 ms, faster than 75.59% of Java online submissions for Remove Nth Node From End of List.
    //Memory Usage: 40.3 MB, less than 96.23% of Java online submissions for Remove Nth Node From End of List.
    //one pass + HashMap
    //Time: O(M); Space:O(M)
    //M is the length of input list;
    public ListNode removeNthFromEnd_2(ListNode head, int n) {

        ListNode dummy = new ListNode();
        dummy.next = head;

        Map<Integer, ListNode> map = new HashMap<>();
        ListNode p = dummy;
        int length = 0;
        while (p != null) {
            map.put(length++, p);
            p = p.next;
        }

        p = map.get(length - n);
        p.next = p.next.next;
        return dummy.next;
    }

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Nth Node From End of List.
    //Memory Usage: 40.3 MB, less than 94.15% of Java online submissions for Remove Nth Node From End of List.
    //Two pass
    //Time: O(M + N); Space: O(1)
    //M is the length of input list;
    public ListNode removeNthFromEnd_1(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode p = head;
        int size = 0;
        while (p != null) {
            p = p.next; size++;
        }

        p = dummy;
        while (size-- > n) p = p.next;

        p.next = p.next.next;
        return dummy.next;
    }

}
