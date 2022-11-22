package LeetCode;


/**
 * Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val, and return the new head.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,6,3,4,5,6], val = 6
 * Output: [1,2,3,4,5]
 * Example 2:
 *
 * Input: head = [], val = 1
 * Output: []
 * Example 3:
 *
 * Input: head = [7,7,7,7], val = 7
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 104].
 * 1 <= Node.val <= 50
 * 0 <= val <= 50
 */
public class N203ERemoveLinkedListElements {


    //2.Recusion
    //Runtime: 1 ms, faster than 98.95% of Java online submissions for Remove Linked List Elements.
    //Memory Usage: 49.3 MB, less than 32.37% of Java online submissions for Remove Linked List Elements.
    //Time: O(N); Space:O(N)
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }


    //1.Sentinel node
    //Runtime: 2 ms, faster than 43.91% of Java online submissions for Remove Linked List Elements.
    //Memory Usage: 49.5 MB, less than 19.36% of Java online submissions for Remove Linked List Elements.
    //Time: O(N); Space:O(1)
    public ListNode removeElements_1(ListNode head, int val) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode p = head, q = dummy;
        while (p != null) {
            if (p.val == val) q.next = p.next;
            else q = p;
            p = p.next;
        }
        return dummy.next;
    }

}
