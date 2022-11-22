package LeetCode;

/**
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 *
 *
 * Example 2:
 * Input: head = [1,2]
 * Output: [2,1]
 *
 *
 * Example 3:
 * Input: head = []
 * Output: []
 *
 * Constraints:
 * The number of nodes in the list is the range [0, 5000].
 * -5000 <= Node.val <= 5000
 *
 *
 * Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
 */

/**
 * E - [耗时：10m]
 * -- two runner + reverse a linked list
 */

public class N206EReverseLinkedList {

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Linked List.
    //Memory Usage: 42.9 MB, less than 60.68% of Java online submissions for Reverse Linked List.
    //reverseList_1
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pointer = head;
        ListNode result = null;
        while(pointer != null){
            ListNode tmp = pointer;
            pointer = pointer.next;
            tmp.next = result;
            result = tmp;
        }
        return result;
    }


    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Linked List.
    //Memory Usage: 42.3 MB, less than 81.18% of Java online submissions for Reverse Linked List.
    public ListNode reverseList_2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode result = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return result;
    }

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Linked List.
    //Memory Usage: 42.5 MB, less than 75.10% of Java online submissions for Reverse Linked List.
    public ListNode reverseList_1(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode result = null;
        while (head.next != null) {
            ListNode x = head.next;
            head.next = result;
            result = head;
            head = x;
        }
        head.next = result;
        return head;
    }


    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
