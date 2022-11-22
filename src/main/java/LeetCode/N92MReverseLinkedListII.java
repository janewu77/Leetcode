package LeetCode;

import java.util.Stack;

/**
 *
 * Given the head of a singly linked list and two integers left and right where left <= right,
 * reverse the nodes of the list from position left to position right, and return the reversed list.
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 * Example 2:
 *
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is n.
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 *
 *
 * Follow up: Could you do it in one pass?
 *
 */

/**
 * M - [time: 30-]
 *  - 利用Stack的filo,重新赋值。time : O(right + right-left ) ; space : O(right-left)
 *  - 原地反转，重新link 【time: O(N); space: O(1)】
 */
public class N92MReverseLinkedListII {


    public static void main(String[] args) {
//
        ListNode link = new N92MReverseLinkedListII().buildLink(10);
        doRun(link, 3, 5);

        link = new N92MReverseLinkedListII().buildLink(10);
        doRun(link, 5, 6);

        int n = 5;
        link = new N92MReverseLinkedListII().buildLink(n);
        doRun(link, 1, n);

    }

    private ListNode buildLink(int n){
        ListNode link = null;
        for(int i = 0; i< n;i++)
            link =  new ListNode(n - i, link);
        return link;
    }

    static String linkToString(ListNode node){
        StringBuilder sb = new StringBuilder();
        while (node != null){
            sb.append(node.val).append(",");
            node = node.next;
        }
        return sb.substring(0, sb.length()-1);
    }

    private static int c = 1;
    private static void doRun(ListNode head, int left, int right){
        ListNode res = new N92MReverseLinkedListII().reverseBetween( head, left, right);
        System.out.println(linkToString(res));
        //System.out.println("[" + (expected.equals(res)) +"] "+(c++)+ ".result:"+ res + ".expected:"+expected);
    }


    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Linked List II.
    //Memory Usage: 41.9 MB, less than 37.90% of Java online submissions for Reverse Linked List II.
    //time: O(n); space: O(1)
    //one pass: reverse in place
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left >= right) return head;
        if (head == null || head.next == null) return head;

        ListNode prev = head;
        ListNode p = head;

        ListNode subHead = null;
        ListNode tail = null;

        for (int i = 1; i <= right; i++) {

            if (i == left) {
                subHead = p; tail = p;
                if (prev == p) prev = null; //the head node will be moved.
                p = p.next;
            }else if (i > left) {
                ListNode tmp = p.next;
                p.next = subHead;
                subHead = p;

                p = tmp;
            }else {
                prev = p;
                p = p.next;
            }
        }
        tail.next = p;

        if(prev == null) head = subHead;
        else prev.next = subHead;

        return head;
    }


    //Runtime: 1 ms, faster than 8.45% of Java online submissions for Reverse Linked List II.
    //Memory Usage: 42.7 MB, less than 5.78% of Java online submissions for Reverse Linked List II.
    // stack : filo
    // time : O(right + right-left ) ; space: O(right-left)
    public ListNode reverseBetween_stack(ListNode head, int left, int right) {
        if (left == right) return head;

        Stack<Integer> stack  = new Stack<>();
        ListNode p = head;
        ListNode q = head;

        for (int idx = 1; idx <= right; idx++) {
            if (idx == left) q = p;
            if (idx >= left) stack.push(p.val);
            p = p.next;
        }
        while (q != p) {
            q.val = stack.pop();
            q = q.next;
        }
        return head;
    }


    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
