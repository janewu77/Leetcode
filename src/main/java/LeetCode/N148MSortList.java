package LeetCode;


import java.util.*;

/**
 * Given the head of a linked list, return the list after sorting it in ascending order.
 *
 *
 *
 * Example 1:
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 *
 *
 * Example 2:
 * Input: head = [-1,5,3,4,0]
 * Output: [-1,0,3,4,5]
 * Example 3:
 *
 * Input: head = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 5 * 104].
 * -105 <= Node.val <= 105
 *
 *
 * Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
 *
 */

/**
 * M - [time: 30-]
 *  - 本质是考察排序
 *  - merge 符合题意
 *  - 写了二种merge (递归和循环）、冒泡排序
 *  - 利用treeset 也可以得到不错的效果。
 *  - 自己建树，如果不是平衡树，在某些特别情下，性能会很糟。如单边树。
 *
 */
public class N148MSortList {

    public static void main(String[] args){
        int[] data;
        data = new int[]{4,19,14,5,-3,1,8,5,11,15};
        doRun(data);

        doRun(new int[]{});
    }

    private static void doRun(int[] data){
        N148MSortList n148MSortList = new N148MSortList();
        ListNode head = n148MSortList.buildLink(data);
        ListNode p = head;
        while(p != null){
            System.out.print(p.val+",");
            p = p.next;
        }
        System.out.println();
        ListNode sorted = n148MSortList.sortList(head);
        while(sorted != null){
            System.out.print(sorted.val+",");
            sorted = sorted.next;
        }
        System.out.println();
    }
    private ListNode buildLink(int[] data){
        ListNode dummy = new ListNode(3);
        ListNode p = dummy;
        for (int n: data){
            p.next = new ListNode(n);
            p = p.next;
        }
        return dummy.next;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //Runtime: 14 ms, faster than 83.17% of Java online submissions for Sort List.
    //Memory Usage: 50.6 MB, less than 92.17% of Java online submissions for Sort List.
    //iterate | merge
    //代码没有recursive solution来得简洁，但是能省空间。理论上，recursive可能存在内存溢出,iteration则不会。
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode left = head;
        ListNode right = head.next;
        int c = 0;
        while (right!= null) {
            if (left.val > right.val) {
                int t = left.val;
                left.val = right.val;
                right.val = t;
            }
            left = right.next;
            right = left == null? null: left.next;
            c+=2;
        }

        //dummy head
        ListNode dummy = new ListNode();
        dummy.next = head;
        //merge by step
        for (int step = 2; step <= c; step *= 2){
            head = dummy;
            ListNode nextPointer = dummy.next;

            while (nextPointer != null) {
                left = nextPointer;
                right = nextPointer;

                int j = 0;
                while(right != null && ++j < step) right = right.next;
                if (right != null && right.next != null){
                    ListNode tmp = right.next;
                    right.next = null;
                    right = tmp;
                }else  right = null;

                //next segment
                nextPointer = right;
                j = 0;
                while (nextPointer != null && nextPointer.next != null && ++j < step) nextPointer = nextPointer.next;
                if (nextPointer != null && nextPointer.next != null){
                    ListNode tmp = nextPointer.next;
                    nextPointer.next = null;
                    nextPointer = tmp;
                }else nextPointer = null;

                //merge: left & right
                while (left != null && right != null){
                    if (left.val <= right.val){
                        head.next = left;
                        left = left.next;
                    }else{
                        head.next = right;
                        right = right.next;
                    }
                    head = head.next;
                    head.next = null;
                }
                head.next = left != null ? left : right;
                while (head.next != null) head = head.next;

            } //End while (pointer != null) {
        }// End for
        return dummy.next;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //Runtime: 19 ms, faster than 49.02% of Java online submissions for Sort List.
    //Memory Usage: 77.5 MB, less than 48.49% of Java online submissions for Sort List.
    //merge + recursion
    public ListNode sortList_merge(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode mid = getMid(head);
        ListNode left = sortList_merge(head);
        ListNode right = sortList_merge(mid);
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right){
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        while (left != null && right != null){
            if (left.val <= right.val){
                p.next = left;
                left = left.next;
            }else{
                p.next = right;
                right = right.next;
            }
            p = p.next;
        }
        p.next = left != null? left: right;
        return dummy.next;
    }

    private ListNode getMid(ListNode head){
        ListNode mid = head;
        ListNode prevMid = mid; //用来断link的。
        ListNode p = head;
        while (p.next != null){
            prevMid = mid;
            mid = mid.next;
            p = p.next;
            if (p.next != null) p = p.next;
        }
        prevMid.next = null; //断开左右
        return mid;
    }

    //Runtime: 42 ms, faster than 12.88% of Java online submissions for Sort List.
    //Memory Usage: 93.3 MB, less than 5.15% of Java online submissions for Sort List.
    //类似于桶排序。 速度比tree set 快，但很占空间哦。
    public ListNode sortList_x(ListNode head) {
        int[] data = new int[100000*2+1];
        int offset = 100000;
        while(head != null){
            int idx = head.val+offset;
            data[idx] = data[idx]+1;
            head = head.next;
        }

        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        for(int i = 0; i< data.length; i++){
            int n = 0;
            while (data[i] > 0 && n++ < data[i]) {
                p.next = new ListNode(i-offset);
                p = p.next;
            }
        }
        return dummy.next;
    }

    //////////////////////////////////////////////////////////////////
    //Runtime: 244 ms, faster than 5.10% of Java online submissions for Sort List.
    //Memory Usage: 79.1 MB, less than 33.81% of Java online submissions for Sort List.
    //binarySearchAndInsert 排序插入
    //Insert时，ArrayList的add(i,val)性能会很差。
    public ListNode sortList_binarysearch(ListNode head) {
        if (head == null || head.next == null) return head;

        List<ListNode> dataList = new ArrayList<>();
        while (head != null){
            int x = binarySearchAndInsert(dataList, head.val, 0, dataList.size()-1);
            dataList.add(x, head);
            head = head.next;
        }

        ListNode head2 = new ListNode(0);
        ListNode nodePointer = head2;
        for (ListNode node : dataList){
            node.next = null;
            nodePointer.next = node;
            nodePointer = nodePointer.next;
        }
        return head2.next;
    }

    private int binarySearchAndInsert(List<ListNode> dataList, int target, int from, int to){
        if (dataList.size() <= 0) return 0;
        if (to < from) return  from;

        int k = (to - from) / 2 + from;
        if (dataList.get(k).val == target) return k;

        if (dataList.get(k).val > target) return binarySearchAndInsert(dataList,target,from, k-1);
        else return binarySearchAndInsert(dataList,target,k+1, to);
    }

    //////////////////////////////////////////////////////////////////
    //Time Limit Exceeded
    //Binary tree： 会出现单边树。
    public ListNode sortList_tree(ListNode head) {
        if (head == null || head.next == null) return head;
        TreeNode root = new TreeNode(Integer.MIN_VALUE);
        while (head != null){
            insert(root, head.val);
            head = head.next;
        }
        return visit(root.right);
    }
    public class TreeNode {
        int val;
        public int c = 1;
        TreeNode left = null;
        TreeNode right = null;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
    }
    private void insert(TreeNode root, int val){
        TreeNode treeNode = root;
        while (treeNode != null) {
            if (val == treeNode.val) {
                treeNode.c = treeNode.c + 1;
                return;
            } else if (val > treeNode.val) {
                if (treeNode.right != null) treeNode = treeNode.right;
                else {
                    treeNode.right = new TreeNode(val);
                    return;
                }
            } else {
                if (treeNode.left != null) treeNode = treeNode.left;
                else{
                    treeNode.left = new TreeNode(val);
                    return;
                }
            }
        }
    }

    private ListNode visit(TreeNode root){
        ListNode listNodeRoot = new ListNode(0);
        ListNode listPointer = listNodeRoot;

        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()){
            TreeNode treeNode = stack.pop();
            if (treeNode.left == null) {
                if (treeNode.right != null) stack.add(treeNode.right);

                for (int k = 0; k<treeNode.c;k++) {
                    ListNode lNode = new ListNode(treeNode.val);
                    listPointer.next = lNode;
                    listPointer = listPointer.next;
                }
            }else{
                stack.add(treeNode);
                stack.add(treeNode.left);
                treeNode.left = null;
            }
        }
        return listNodeRoot.next;
    }

    //////////////////////////////////////////////////////////////////
    //Time Limit Exceeded
    //冒泡排序
    public ListNode sortList_2(ListNode head) {
        if (head == null || head.next == null) return head;
        boolean isSorted = false;
        while(!isSorted){
            isSorted = true;

            ListNode p = head;
            ListNode q = p.next;
            while (q != null) {
                if (q.val < p.val) {
                    int tmp = p.val;
                    p.val = q.val;
                    q.val = tmp;
                    isSorted = false;
                }
                p = q;
                q = p.next;
            }
        }
        return head;
    }

    //////////////////////////////////////////////////////////////////
    //Runtime: 58 ms, faster than 10.09% of Java online submissions for Sort List.
    //Memory Usage: 50.7 MB, less than 92.17% of Java online submissions for Sort List.
    //treeset + hashmap
    //第一次做出来的。
    public ListNode sortList_1(ListNode head) {

        Map<Integer, Integer> duplcated = new HashMap<>();
        TreeSet<Integer> treeSet = new TreeSet<>();
        while (head != null){
            if (!treeSet.add(head.val)){
                int c = duplcated.getOrDefault(head.val, 1) + 1;
                duplcated.put(head.val, c);
            }
            head = head.next;
        }
        ListNode root  = new ListNode();
        ListNode node = root;
        while(!treeSet.isEmpty()) {
            int v = treeSet.first();
            treeSet.remove(v);
            int c = duplcated.getOrDefault(v, 1);
            for (int i=0;i<c; i++) {
                node.next = new ListNode(v);
                node = node.next;
            }
        }
        return root.next;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
