package Comm;

public class ListNode {

    public int val;
    public ListNode next;
    public ListNode() {}
    public ListNode(int val) { this.val = val; }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        ListNode node = this;
        while (node != null){
            sb.append(node.val).append(",");
            node = node.next;
        }
        return sb.toString();
    }


    public static ListNode buildLink(int[] data){
        //build
        ListNode dummy = new ListNode();
        ListNode head = dummy;
        for (int i = 0; i < data.length; i++) {
            ListNode node = new ListNode(data[i]);
            dummy.next = node;
            dummy = node;
        }
        return head.next;
    }

    public static boolean isEqual(ListNode expect, ListNode data){
        ListNode nodeP = expect, nodeQ = data;
        while (nodeP != null && nodeQ != null){
            if (nodeP.val != nodeQ.val) return false;
            nodeP = nodeP.next;
            nodeQ = nodeQ.next;
        }
        return (nodeP == null && nodeQ == null) ?  true : false;
    }

}
