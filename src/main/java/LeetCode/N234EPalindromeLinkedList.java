package LeetCode;

import java.util.ArrayList;

import static java.time.LocalTime.now;

/**
 *
 * Given the head of a singly linked list, return true if it is a palindrome.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,2,1]
 * Output: true
 * Example 2:
 *
 *
 * Input: head = [1,2]
 * Output: false
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [1, 105].
 * 0 <= Node.val <= 9
 *
 *
 * Follow up: Could you do it in O(n) time and O(1) space?
 *
 *
 */

/**
 * E - [Time: 10-
 *
 */
public class N234EPalindromeLinkedList {


    public static void main(String[] args) {

        System.out.println(now());
        int[] data;

        System.out.println("========doRun_demo==========");

        ListNode node1 = getNode2(1);
        doRun_demo(true, node1);


        ListNode node2 = getNode2(2);
        node1.next = node2;
        doRun_demo(false, node1);

//        ListNode node3 = getNode2(1);
//        node2.next = node3;
//        doRun_demo(true, node1);

        ListNode node3 = getNode2(3);
        node2.next = node3;
        doRun_demo(false, node1);


        System.out.println(now());
        System.out.println("==================");
    }


    static ListNode getNode2(int value) {
        return new N234EPalindromeLinkedList().getNode(value);
    }


    static private void doRun_demo(boolean expect, ListNode head) {
        boolean res = new N234EPalindromeLinkedList().isPalindrome(head);
//        String res = comm.toString(res1);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    private ListNode getNode(int v) {
        return new ListNode(v);
    }


    //Runtime: 8 ms, faster than 58.29% of Java online submissions for Palindrome Linked List.
    //Memory Usage: 98.2 MB, less than 45.77% of Java online submissions for Palindrome Linked List.
    //Time: O(N/2 + N/2 + N/2); Space(1)
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        ListNode point1 = head;
        ListNode point2 = head;
        while (point2 != null && point2.next != null){
            point2 = point2.next.next;
            point1 = point1.next;
        }

        //如果list是奇数；则多出来的那一个挂在前半段。
        if (point2 != null && point1 != null) point1 = point1.next;

        ListNode half2 = reverseList(point1);

        point1 = head;
        point2 = half2;
        boolean isSuccess = true;
        while (isSuccess && point2 != null){ //用point2跳过多出来的中间节点
            if (point2.val != point1.val) isSuccess = false;
            point1 = point1.next;
            point2 = point2.next;
        }
        //还原
        reverseList(half2);
        return isSuccess;
    }


    //Time: O(N)
    private ListNode reverseList(ListNode head){
        ListNode pointer = head;
        ListNode newHead = null;

        while (pointer != null) {
            ListNode tmp = pointer;
            pointer = pointer.next;

            tmp.next = newHead;
            newHead = tmp;
        }
        return newHead;
    }


    //Runtime: 16 ms, faster than 39.17% of Java online submissions for Palindrome Linked List.
    //Memory Usage: 105.2 MB, less than 23.71% of Java online submissions for Palindrome Linked List.
    //ArrayList
    //Time: O(N + N/2); Space: O(N);
    public boolean isPalindrome_2(ListNode head) {
        ArrayList list = new ArrayList();
        while( head!= null){
            list.add(head.val);
            head = head.next;
        }
        int max = list.size() / 2;
        for(int i = 0; i < max; i++)
            if (list.get(list.size()-1-i) != list.get(i)) return false;
        return true;
    }

    //Runtime: 20 ms, faster than 30.71% of Java online submissions for Palindrome Linked List.
    //Memory Usage: 99.8 MB, less than 39.88% of Java online submissions for Palindrome Linked List.
    //StringBuilder
    //Time: O(N + N/2); Space: O(N);
    public boolean isPalindrome_1(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while( head!= null){
            sb.append(head.val);
            head = head.next;
        }
        int max = sb.length() / 2;
        for(int i = 0; i < max; i++)
            if (sb.charAt(sb.length()-1-i) != sb.charAt(i)) return false;
        return true;
    }


    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
