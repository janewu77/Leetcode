package LeetCode;


/**
 * Given the head of a singly linked list, return the middle node of the linked list.
 *
 * If there are two middle nodes, return the second middle node.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5]
 * Output: [3,4,5]
 * Explanation: The middle node of the list is node 3.
 * Example 2:
 *
 *
 * Input: head = [1,2,3,4,5,6]
 * Output: [4,5,6]
 * Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [1, 100].
 * 1 <= Node.val <= 100
 */



public class N876MMiddleoftheLinkedList {

    //2.to array
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Middle of the Linked List.
    //Memory Usage: 41.5 MB, less than 50.95% of Java online submissions for Middle of the Linked List.
    //Time: O(N); Space:O(100)
    //Time: O(N); Space:O(1)
    public ListNode middleNode(ListNode head) {
        ListNode p = head;
        ListNode[] arr = new ListNode[100];
        int idx = 0;
        while (p != null) {
            arr[idx++] = p;
            p = p.next;
        }
        return arr[idx / 2];
    }

    //1.fast and slow pointer
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Middle of the Linked List.
    //Memory Usage: 39.5 MB, less than 96.66% of Java online submissions for Middle of the Linked List.
    //Time: O(N); Space:O(1)
    public ListNode middleNode_1(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

}
