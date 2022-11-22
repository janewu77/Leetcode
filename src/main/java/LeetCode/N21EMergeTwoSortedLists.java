package LeetCode;


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

/**
 * 耗时：11m
 * E :
 *  - 方法一：逐一比较合并；
 *  - 方式二：递归
 *
 */
public class N21EMergeTwoSortedLists {

//    public static void main(String[] args) {
//        ListNode list1;
//        int[] data1 = {1,2,3};
//        int[] data2 = {1,2,3};
//
//
////        Arrays.stream(data1).mapToObj(v -> new ListNode(v)).reduce(v.next = v)
//    }

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // 递归合并
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) return null;
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        }else{
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    // 逐一比对
    public ListNode mergeTwoLists_1(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) return null;
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode resultNode = new ListNode(-1);
        ListNode p = resultNode;

        while (list1 != null && list2 != null){
            if (list1.val < list2.val){
                p.next = list1;
                list1 = list1.next;
            }else{
                p.next = list2;
                list2 = list2.next;
            }
            p = p.next;
        }

        p.next = list1 == null ? list2 : list1;
        return resultNode.next;
    }
}
