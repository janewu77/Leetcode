package LeetCode;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect.
 * If the two linked lists have no intersection at all, return null.
 *
 * For example, the following two linked lists begin to intersect at node c1:
 *
 *
 * The test cases are generated such that there are no cycles anywhere in the entire linked structure.
 *
 * Note that the linked lists must retain their original structure after the function returns.
 *
 * Custom Judge:
 *
 * The inputs to the judge are given as follows (your program is not given these inputs):
 *
 * intersectVal - The value of the node where the intersection occurs. This is 0 if there is no intersected node.
 * listA - The first linked list.
 * listB - The second linked list.
 * skipA - The number of nodes to skip ahead in listA (starting from the head) to get to the intersected node.
 * skipB - The number of nodes to skip ahead in listB (starting from the head) to get to the intersected node.
 * The judge will then create the linked structure based on these inputs and pass the two heads, headA and headB
 * to your program. If you correctly return the intersected node, then your solution will be accepted.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
 * Output: Intersected at '8'
 * Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
 * From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5].
 * There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
 * Example 2:
 *
 *
 * Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * Output: Intersected at '2'
 * Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect).
 * From the head of A, it reads as [1,9,1,2,4]. From the head of B, it reads as [3,2,4].
 * There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.
 * Example 3:
 *
 *
 * Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * Output: No intersection
 * Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5].
 * Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
 * Explanation: The two lists do not intersect, so return null.
 *
 *
 * Constraints:
 *
 * The number of nodes of listA is in the m.
 * The number of nodes of listB is in the n.
 * 1 <= m, n <= 3 * 104
 * 1 <= Node.val <= 105
 * 0 <= skipA < m
 * 0 <= skipB < n
 * intersectVal is 0 if listA and listB do not intersect.
 * intersectVal == listA[skipA] == listB[skipB] if listA and listB intersect.
 *
 *
 * Follow up: Could you write a solution that runs in O(m + n) time and use only O(1) memory?
 */

/**
 * E - 耗时15-
 *  - 有M级别的算法（数学）
 */
public class N160EIntersectionofTwoLinkedLists {
    public static void main(String[] args) {

        ListNode[] heads;
        ListNode result = null;
        heads = buildLink(new int[]{4, 1, 8, 4, 5}, new int[]{5, 6, 1, 8, 4, 5}, 8);
        result = new N160EIntersectionofTwoLinkedLists().getIntersectionNode(heads[0], heads[1]);
        System.out.print("1.result:");
        if (result != null)
            System.out.println(result.val);

        heads = buildLink(new int[]{1, 9, 1, 2}, new int[]{3, 2, 4}, 2);
        result = new N160EIntersectionofTwoLinkedLists().getIntersectionNode(heads[0], heads[1]);
        System.out.print("2.result:");
        if (result != null)
            System.out.println(result.val);

        heads = buildLink(new int[]{2, 2, 14, 5, 4}, new int[]{2, 2, 14, 5, 4}, 4);
        result = new N160EIntersectionofTwoLinkedLists().getIntersectionNode(heads[0], heads[1]);
        System.out.print("3.result:");
        if (result != null)
            System.out.println(result.val);


        heads = buildLink(new int[]{1,3,6,7,9,11,13,15,17,19,21,23,25,27,29,30,31,32}, new int[]{30,31,32}, 30);
        result = new N160EIntersectionofTwoLinkedLists().getIntersectionNode(heads[0], heads[1]);
        System.out.print("4.result:");
        if (result != null)
            System.out.println(result.val);
    }

    //Runtime: 1 ms, faster than 99.70% of Java online submissions for Intersection of Two Linked Lists.
    //Memory Usage: 55.5 MB, less than 34.87% of Java online submissions for Intersection of Two Linked Lists.
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;

        while (p1 != p2) {
            p1 = p1 == null? headB : p1.next;
            p2 = p2 == null? headA : p2.next;
        }
        return p1;
    }


    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;

        int diff = 1;
        while ( p1.next != null) {
            diff++; p1 = p1.next;
        }
        diff--;
        while ( p2.next != null) {
            diff--; p2 = p2.next;
        }
        if (p1 != p2) return null;

        p1 = headA; p2 = headB;
        for(int i = 0; i < Math.abs(diff); i++)
            if (diff > 0)  p1 = p1.next;
            else p2 = p2.next;

        while (p1 != null){
            if (p1.equals(p2)) return p1;
            p1 = p1.next; p2 = p2.next;
        }
        return null;
    }


    //Runtime: 8 ms, faster than 24.08% of Java online submissions for Intersection of Two Linked Lists.
    //Memory Usage: 55.8 MB, less than 17.77% of Java online submissions for Intersection of Two Linked Lists.
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        Set<ListNode> memo = new HashSet<>();
        while (headA != null) {
            memo.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if(memo.contains(headB)) return headB;
            else headB = headB.next;
        }
        return null;
    }

    static public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }


    //////////
    private static ListNode[] buildLink(int[] data1, int[] data2, int i){
        ListNode iNode = new ListNode(i);

        ListNode resultA = new ListNode(-1);
        ListNode p = resultA;
        for(int d1: data1){
            if(d1 == i) p.next = iNode;
            else p.next = new ListNode(d1);
            p = p.next;
        }

        ListNode resultB = new ListNode(-1);
        p = resultB;
        for(int d2: data2){
            if(d2 == i) {
                p.next = iNode;
                break;
            }else
                p.next = new ListNode(d2);
            p = p.next;
        }
        return new ListNode[]{resultA.next, resultB.next};
    }
}
