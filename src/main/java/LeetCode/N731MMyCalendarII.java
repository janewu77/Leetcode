package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static java.time.LocalTime.now;

/**
 * You are implementing a program to use as your calendar. We can add a new event if adding the event will not cause a triple booking.
 *
 * A triple booking happens when three events have some non-empty intersection (i.e., some moment is common to all the three events.).
 *
 * The event can be represented as a pair of integers start and end that represents a booking on the half-open interval [start, end), the range of real numbers x such that start <= x < end.
 *
 * Implement the MyCalendarTwo class:
 *
 * MyCalendarTwo() Initializes the calendar object.
 * boolean book(int start, int end) Returns true if the event can be added to the calendar successfully without causing a triple booking. Otherwise, return false and do not add the event to the calendar.
 *
 *
 * Example 1:
 *
 * Input
 * ["MyCalendarTwo", "book", "book", "book", "book", "book", "book"]
 * [[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
 * Output
 * [null, true, true, true, false, true, true]
 *
 * Explanation
 * MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
 * myCalendarTwo.book(10, 20); // return True, The event can be booked.
 * myCalendarTwo.book(50, 60); // return True, The event can be booked.
 * myCalendarTwo.book(10, 40); // return True, The event can be double booked.
 * myCalendarTwo.book(5, 15);  // return False, The event cannot be booked, because it would result in a triple booking.
 * myCalendarTwo.book(5, 10); // return True, The event can be booked, as it does not use time 10 which is already double booked.
 * myCalendarTwo.book(25, 55); // return True, The event can be booked, as the time in [25, 40) will be double booked with the third event, the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.
 *
 *
 * Constraints:
 *
 * 0 <= start < end <= 109
 * At most 1000 calls will be made to book.
 */
// 731. My Calendar II
// 732. My Calendar III
public class N731MMyCalendarII {


    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        N731MMyCalendarII n731MMyCalendarII = new N731MMyCalendarII();
        n731MMyCalendarII.doRun();


        System.out.println(now());
        System.out.println("==================");
    }

    //["MyCalendarTwo","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book"]
    //[[],[5,12],[42,50],[4,9],[33,41],[2,7],[16,25],[7,16],[6,11],[13,18],[38,43],[49,50],[6,15],[5,13],[35,42],[19,24],[46,50],[39,44],[28,36],[28,37],[20,29],[41,49],[11,19],[41,46],[28,37],[17,23],[22,31],[4,10],[31,40],[4,12],[19,26]]

    private void doRun() {
        MyCalendarTwo obj = new MyCalendarTwo();
        boolean res, expect;
        res = obj.book(10,20);
        expect = true;
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);

        res = obj.book(50,60);
        expect = true;
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);

        res = obj.book(10,40);
        expect = true;
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);

        res = obj.book(5,15);
        expect = false;
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);

        res = obj.book(5,10);
        expect = true;
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);

        res = obj.book(25,55);
        expect = true;
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);

    }

    //Runtime: 110 ms, faster than 77.47% of Java online submissions for My Calendar II.
    //Memory Usage: 54.2 MB, less than 77.69% of Java online submissions for My Calendar II.
    //brute force
    //Time: O(N * N); Space: O(N)
    //N is the number of events booked
    class MyCalendarTwo {
        List<int[]> calendar;
        List<int[]> duplicated;

        //Space:O(N)
        public MyCalendarTwo() {
            calendar = new ArrayList<>();
            duplicated = new ArrayList<>();
        }

        //two events: b1-e1; b2-e2;
        // there is no overlap if e1 <= b2 or e2 <= b1
        // !(e1 <= b2 or e2 <= b1) >>> e1 > b2 && e2 > b1
        //Time: O(N + N)
        public boolean book(int start, int end) {
            //triple booking
            for (int[] event : duplicated)
                if (end > event[0] && event[1] > start) return false;

            //double booking
            for (int[] event : calendar)
                if (end > event[0] && event[1] > start)
                    duplicated.add(new int[]{Math.max(start, event[0]), Math.min(end, event[1])});

            calendar.add(new int[]{start, end});
            return true;
        }
    }

    //Runtime: 319 ms, faster than 50.68% of Java online submissions for My Calendar II.
    //Memory Usage: 43 MB, less than 94.17% of Java online submissions for My Calendar II.
    //sweep line
    //Time: O(N * N); Space: O(N)
    //N is the number of events booked
    class MyCalendarTwo_treemap {
        TreeMap<Integer, Integer> treeMap;

        public MyCalendarTwo_treemap() {
            treeMap = new TreeMap<>();
        }

        //Time: O(2 * lgN + N)
        public boolean book(int start, int end) {
            treeMap.put(start, treeMap.getOrDefault(start,0) + 1);
            treeMap.put(end, treeMap.getOrDefault(end,0) - 1);
            int total = 0;
            for (int count : treeMap.values()){
                total += count;
                if (total >= 3) {
                    // remove current event
                    treeMap.put(start, treeMap.get(start) - 1);
                    treeMap.put(end, treeMap.get(end) + 1);
                    return false;
                }
            }
            return true;
        }
    }

}
