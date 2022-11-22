package LeetCode;

/**
 * Given the head of a linked list and a value x, partition it such
 * that all nodes less than x come before nodes greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 *
 *
 * Example 1:
 * Input: head = [1,4,3,2,5,2], x = 3
 * Output: [1,2,2,4,3,5]
 *
 *
 * Example 2:
 * Input: head = [2,1], x = 2
 * Output: [1,2]
 *
 *
 * Constraints:
 * The number of nodes in the list is in the range [0, 200].
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 */

/**
 * M - [time: 30-]
 *  - 调试时花了一些额外的时间（head的位置没做对）
 *  - 普通link的换位操作。
 *  - 借用 fakenode 简化代码
 */
public class N86MPartitionList {

    public static void main(String[] args) {
        ListNode link = new N86MPartitionList().build();
        doRun(link, 2);

    }
    private ListNode build(){
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2, n1);
        return n2;
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
    private static void doRun(ListNode head, int x){
        ListNode res = new N86MPartitionList().partition( head, x);
        System.out.println(linkToString(res));
        //System.out.println("[" + (expected.equals(res)) +"] "+(c++)+ ".result:"+ res + ".expected:"+expected);
    }



    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Partition List.
    //Memory Usage: 41.7 MB, less than 90.59% of Java online submissions for Partition List.
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;

        ListNode sPointer = new ListNode();
        ListNode lPointer = new ListNode();

        ListNode sHead = sPointer;
        ListNode lHead = lPointer;

        while (head != null){
            if (head.val < x){
                sPointer.next = head;
                sPointer = sPointer.next;
            }else{
                lPointer.next = head;
                lPointer = lPointer.next;
            }
            head = head.next;
        }
        lPointer.next = null;
        sPointer.next = lHead.next;

        return sHead.next;
    }


    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
