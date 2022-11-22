package LeetCode;


import java.util.*;

import static java.time.LocalTime.now;

/**
 * A k-booking happens when k events have some non-empty intersection (i.e., there is some time that is common to all k events.)
 *
 * You are given some events [start, end), after each given event, return an integer k representing the maximum k-booking between all the previous events.
 *
 * Implement the MyCalendarThree class:
 *
 * MyCalendarThree() Initializes the object.
 * int book(int start, int end) Returns an integer k representing the largest integer such that there exists a k-booking in the calendar.
 *
 *
 * Example 1:
 *
 * Input
 * ["MyCalendarThree", "book", "book", "book", "book", "book", "book"]
 * [[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
 * Output
 * [null, 1, 1, 2, 3, 3, 3]
 *
 * Explanation
 * MyCalendarThree myCalendarThree = new MyCalendarThree();
 * myCalendarThree.book(10, 20); // return 1, The first event can be booked and is disjoint, so the maximum k-booking is a 1-booking.
 * myCalendarThree.book(50, 60); // return 1, The second event can be booked and is disjoint, so the maximum k-booking is a 1-booking.
 * myCalendarThree.book(10, 40); // return 2, The third event [10, 40) intersects the first event, and the maximum k-booking is a 2-booking.
 * myCalendarThree.book(5, 15); // return 3, The remaining events cause the maximum K-booking to be only a 3-booking.
 * myCalendarThree.book(5, 10); // return 3
 * myCalendarThree.book(25, 55); // return 3
 *
 *
 * Constraints:
 *
 * 0 <= start < end <= 109
 * At most 400 calls will be made to book.
 */
// 731. My Calendar II
// 732. My Calendar III
public class N732HMyCalendarIII {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        N732HMyCalendarIII n732HMyCalendarIII = new N732HMyCalendarIII();
        n732HMyCalendarIII.doRun();


        System.out.println(now());
        System.out.println("==================");
    }


    private void doRun() {
        MyCalendarThree obj = new MyCalendarThree();
        int res, expect;
        res = obj.book(10,20);
        expect = 1;
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);

        res = obj.book(50,60);
        expect = 1;
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);

        res = obj.book(10,40);
        expect = 2;
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);

        res = obj.book(5,15);
        expect = 3;
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);

        res = obj.book(5,10);
        expect = 3;
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);

        res = obj.book(25,55);
        expect = 3;
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);

    }

    //https://oi-wiki.org/misc/odt/
    //https://zhuanlan.zhihu.com/p/102786071
    //https://docs.rs/chtholly_tree/latest/chtholly_tree/
    //Runtime: 34 ms, faster than 98.27% of Java online submissions for My Calendar III.
    //Memory Usage: 42.7 MB, less than 96.29% of Java online submissions for My Calendar III.
    //Chtholly Tree
    class MyCalendarThree {

        private TreeMap<Integer, Integer> treeMap; //start points
        private int max;

        //Space: O(2N)
        public MyCalendarThree() {
            treeMap = new TreeMap<>();
            treeMap.put(0, 0);
            max = 0;
        }

        //Time:O(logN + N)
        public int book(int start, int end) {
            updateTree(start, end);
            return max;
        }

        private void updateTree(int start, int end){
            splitTree(start); splitTree(end);
            for (Map.Entry<Integer, Integer> entry : treeMap.subMap(start, true, end, false).entrySet())
                max = Math.max(max, entry.setValue(entry.getValue() + 1) + 1);
        }

        //Time: O(lgN)
        private void splitTree(int x) {
            Integer prev = treeMap.floorKey(x);
            Integer next = treeMap.ceilingKey(x);
            if (next != null && next == x) return;
            treeMap.put(x, treeMap.get(prev));
        }
    }

    //Runtime: 146 ms, faster than 62.38% of Java online submissions for My Calendar III.
    //Memory Usage: 50 MB, less than 88.37% of Java online submissions for My Calendar III.
    //Segment Tree
    class MyCalendarThree_segmenttree {
        //-0 <= start < end <= 10^9
        private Map<Integer, Integer> segmentTree;
        private Map<Integer, Integer> lazy;

        public MyCalendarThree_segmenttree() {
            segmentTree = new HashMap<>();
            lazy = new HashMap<>();
        }

        public int book(int start, int end) {
            updateSTree(start, end - 1, 0, 1_000_000_000, 1);
            return segmentTree.getOrDefault(1, 0);
        }

        //Time: O(lgC) C = 10^9
        public void updateSTree(int start, int end, int left, int right, int idx) {
            if (start > right || end < left) return;
            if (start <= left && right <= end) {
                segmentTree.put(idx, segmentTree.getOrDefault(idx, 0) + 1);
                lazy.put(idx, lazy.getOrDefault(idx, 0) + 1);
            } else {
                int mid = (left + right) / 2;
                updateSTree(start, end, left, mid, idx * 2);
                updateSTree(start, end, mid + 1, right, idx * 2 + 1);
                segmentTree.put(idx, lazy.getOrDefault(idx, 0)
                        + Math.max(segmentTree.getOrDefault(idx * 2, 0),
                        segmentTree.getOrDefault(idx * 2 + 1, 0)));
            }
        }
    }


    //Runtime: 123 ms, faster than 73.22% of Java online submissions for My Calendar III.
    //Memory Usage: 42.5 MB, less than 99.02% of Java online submissions for My Calendar III.
    //Sweep-line
    class MyCalendarThree_sweepLine {
        TreeMap <Integer, Integer> treeMap;
        int max = 0;
        public MyCalendarThree_sweepLine() {
            treeMap = new TreeMap<>();
        }

        //Time: O(2 * logN + N)
        public int book(int start, int end) {
            treeMap.put(start, treeMap.getOrDefault(start, 0) + 1);
            treeMap.put(end, treeMap.getOrDefault(end, 0) - 1);

            int total = 0;

            for (int count : treeMap.values()){
                total += count;
                if (total > max) return max = total;
            }
            return max;
        }
    }
}
