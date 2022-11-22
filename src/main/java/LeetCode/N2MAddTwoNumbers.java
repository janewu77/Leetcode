package LeetCode;


/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 *
 *
 * Example 1:
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 *
 * Example 2:
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 *
 * Example 3:
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 *
 *
 * Constraints:
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading zeros.
 *
 * Accepted 2,903,057 Submissions 7,437,305
 */

/**
 * M - 耗时 30+
 */
public class N2MAddTwoNumbers {

    public static void main(String[] args){

        ListNode l1 = makeList(4);
        ListNode l2 = makeList(7);
        ListNode l3 = new N2MAddTwoNumbers().addTwoNumbers(l1, l2);

        while(l3!=null){
            System.out.print(l3.val+",");
            l3 = l3.next;
        }
    }

    private static ListNode makeList(int n){
        ListNode l1 = new ListNode(9);
        ListNode p = l1;
        for(int i = 1 ; i< n; i++) {
            p.next = new ListNode(9);
            p = p.next;
        }
        return l1;
    }

    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Add Two Numbers.
    //Memory Usage: 42.4 MB, less than 91.06% of Java online submissions for Add Two Numbers.
    //Space complexity : O(1)
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int tmp = 0;
        ListNode p = l1;
        ListNode prev = p;

        while (p != null) {
            int sum  = tmp + p.val + (l2 == null ? 0: l2.val);
            tmp = sum / 10;
            p.val = sum % 10;
            prev = p;
            p = p.next;
            if (p == null && l2 != null) {
                prev.next = l2.next;
                p = l2.next;
                l2 = null;
            }
            if (l2 != null) l2 = l2.next;
        }
        if (tmp != 0) prev.next = new ListNode(tmp);
        return l1;
    }

}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}