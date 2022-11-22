package LeetCode;

/**
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 *
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 * Example 1:
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 *
 *
 * Example 2:
 * Input: lists = []
 * Output: []
 *
 * Example 3:
 * Input: lists = [[]]
 * Output: []
 *
 *
 * Constraints:
 *
 * k == lists.length
 * 0 <= k <= 104
 * 0 <= lists[i].length <= 500
 * -104 <= lists[i][j] <= 104
 * lists[i] is sorted in ascending order.
 * The sum of lists[i].length will not exceed 104.
 */

/**
 * Runtime: 5 ms, faster than 81.32% of Java online submissions for Merge k Sorted Lists.
 * Memory Usage: 47.2 MB, less than 61.27% of Java online submissions for Merge k Sorted Lists.
 */

/**
 * H：（耗时45m)
 *  - 多路合并，注意step的变化。
 *
 */
public class N23HMergekSortedLists {

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        if ( lists.length <= 1) return lists[0];

        int step = 1;
        while (step < lists.length ){
            int k = 0;
            while (k + step < lists.length) {
                lists[k] = mergeTwoLists(lists[k], lists[k+step]);
                lists[k+step] = null;
                k = k + step + step;
            }
            step = step + step;
        }
        return lists[0];
    }

    // 参见 N21EMergeTwoSortedLists
    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) return null;
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        if (list1.val < list2.val){
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        }else{
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
}
