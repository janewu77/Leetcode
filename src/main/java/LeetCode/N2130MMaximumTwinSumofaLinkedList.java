package LeetCode;

/**
 * In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.
 *
 * For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2. These are the only nodes with twins for n = 4.
 * The twin sum is defined as the sum of a node and its twin.
 *
 * Given the head of a linked list with even length, return the maximum twin sum of the linked list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [5,4,2,1]
 * Output: 6
 * Explanation:
 * Nodes 0 and 1 are the twins of nodes 3 and 2, respectively. All have twin sum = 6.
 * There are no other nodes with twins in the linked list.
 * Thus, the maximum twin sum of the linked list is 6.
 * Example 2:
 *
 *
 * Input: head = [4,2,2,3]
 * Output: 7
 * Explanation:
 * The nodes with twins present in this linked list are:
 * - Node 0 is the twin of node 3 having a twin sum of 4 + 3 = 7.
 * - Node 1 is the twin of node 2 having a twin sum of 2 + 2 = 4.
 * Thus, the maximum twin sum of the linked list is max(7, 4) = 7.
 * Example 3:
 *
 *
 * Input: head = [1,100000]
 * Output: 100001
 * Explanation:
 * There is only one node with a twin in the linked list having twin sum of 1 + 100000 = 100001.
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is an even integer in the range [2, 105].
 * 1 <= Node.val <= 105
 */

/**
 * M - [Time: 5-
 *
 */
public class N2130MMaximumTwinSumofaLinkedList {


    //1.Get mid and separate the LinkedList into two parts.
    //2.Reverse the second part, for traversing it in forward direction.
    //3.Traverse in both parts and get maximum the twin sum.
    //4.Recover the Linked List Again, by connection the parts again, for good practice.
    //Runtime: 5 ms, faster than 97.61% of Java online submissions for Maximum Twin Sum of a Linked List.
    //Memory Usage: 111.4 MB, less than 67.05% of Java online submissions for Maximum Twin Sum of a Linked List.
    public int pairSum(ListNode head) {
        if (head.next == null) return head.val;
        //get mid
        ListNode fastRunner = head;
        ListNode slowRunner = head;
        while(fastRunner != null && fastRunner.next != null){
            fastRunner = fastRunner.next.next;
            slowRunner = slowRunner.next;
        }

        //reverse
        ListNode half2 = reverse(slowRunner);;

        //get sum
        fastRunner = half2;
        slowRunner = head;
        int res = 0;
        while (fastRunner != null){
            res = Math.max(res, fastRunner.val + slowRunner.val);
            fastRunner = fastRunner.next;
            slowRunner = slowRunner.next;
        }

        //recover
        reverse(half2);
        return res;
    }

    private ListNode reverse(ListNode head){
        ListNode pointer = head;
        ListNode res = null;
        while(pointer != null){
            ListNode tmp = pointer;
            pointer = pointer.next;
            tmp.next = res;
            res = tmp;
        }
        return res;
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
