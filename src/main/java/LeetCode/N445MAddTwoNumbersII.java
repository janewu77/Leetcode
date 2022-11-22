package LeetCode;

/**
 *
 * You are given two non-empty linked lists representing two non-negative integers.
 * The most significant digit comes first and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 *
 *
 * Example 1:
 * Input: l1 = [7,2,4,3], l2 = [5,6,4]
 * Output: [7,8,0,7]
 *
 *
 * Example 2:
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [8,0,7]
 *
 *
 * Example 3:
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 *
 *
 * Constraints:
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading zeros.
 */
public class N445MAddTwoNumbersII {

    public static void main(String[] args){

        ListNode l1 = makeList(2, 3);
        ListNode l2 = makeList(5, 9);
        ListNode l3 = new N445MAddTwoNumbersII().addTwoNumbers(l1, l2);

        while(l3!=null){
            System.out.print(l3.val+",");
            l3 = l3.next;
        }
    }

    private static ListNode makeList(int n, int v){
        ListNode l1 = new ListNode(v);
        ListNode p = l1;
        for(int i = 1 ; i< n; i++) {
            p.next = new ListNode(v);
            p = p.next;
        }
        return l1;
    }


    //Runtime: 2 ms, faster than 100.00% of Java online submissions for Add Two Numbers II.
    //Memory Usage: 42.4 MB, less than 91.29% of Java online submissions for Add Two Numbers II.
    //recursion | no Reverse | no stack
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p = l1;
        int diff = 0;
        while (p != null) {
            p = p.next;
            diff++;
        }
        p = l2;
        while (p != null) {
            p = p.next;
            diff--;
        }
        if (diff < 0) {
            p = l1;
            l1 = l2;
            l2 = p;
        }

        p = l1;
        diff = Math.abs(diff);
        for(int i = 0; i < diff; i++) p = p.next;

        ListNode carry = null;
        ListNode carryH = null;
        while(l2 != null){
            int sum  = p.val + l2.val;
            p.val = sum % 10;
            int tmp = sum / 10;
            if (tmp != 0 || carry != null ){
                if (carry == null) {
                    carryH = new ListNode(-1);
                    carry = carryH;
                }
                carry.next = new ListNode(tmp);
                carry = carry.next;
            }
            p = p.next;
            l2 = l2.next;
        }

        if (carryH != null) {
            carry.next = new ListNode(0);
            return addTwoNumbers(l1, carryH.next);
        }else
            return l1;
    }

}

//class ListNode {
//    int val;
//    ListNode next;
//    ListNode() {}
//    ListNode(int val) { this.val = val; }
//    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
//}