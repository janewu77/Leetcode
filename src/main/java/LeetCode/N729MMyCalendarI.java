package LeetCode;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * You are implementing a program to use as your calendar. We can add a new event
 * if adding the event will not cause a double booking.
 *
 * A double booking happens when two events have some non-empty intersection
 * (i.e., some moment is common to both events.).
 *
 * The event can be represented as a pair of integers start and end that represents
 * a booking on the half-open interval [start, end), the range of real numbers
 * x such that start <= x < end.
 *
 * Implement the MyCalendar class:
 *
 * MyCalendar() Initializes the calendar object.
 * boolean book(int start, int end) Returns true if the event can be added to
 * the calendar successfully without causing a double booking. Otherwise,
 * return false and do not add the event to the calendar.
 *
 *
 * Example 1:
 *
 * Input
 * ["MyCalendar", "book", "book", "book"]
 * [[], [10, 20], [15, 25], [20, 30]]
 * Output
 * [null, true, false, true]
 *
 * Explanation
 * MyCalendar myCalendar = new MyCalendar();
 * myCalendar.book(10, 20); // return True
 * myCalendar.book(15, 25); // return False, It can not be booked because time 15
 * is already booked by another event.
 * myCalendar.book(20, 30); // return True, The event can be booked,
 * as the first event takes every time less than 20, but not including 20.
 *
 *
 * Constraints:
 *
 * 0 <= start < end <= 109
 * At most 1000 calls will be made to book.
 *
 */

/**
 * M - [time: 60+ 为什么用了这么久？]
 *
 */
public class N729MMyCalendarI {

    public static void main(String[] args) {

        new N729MMyCalendarI().doRun();
    }


    private void doRun(){
        MyCalendar obj = new MyCalendar();
        boolean res;
        boolean expect;

        res = obj.book(47, 50);
        expect = true;
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
        res = obj.book(33, 41);
        expect = true;
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
        res = obj.book(39, 45);
        expect = false;
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);

        res = obj.book(1, 2);
        expect = true;
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);


        //System.out.println("["+(expect == res)+"]expect:" + expect + "res:" + res);

//        res = obj.book(15, 25);
//        expect = false;
//        System.out.println("["+(expect == res)+"]expect:" + expect + "res:" + res);
//
//        res = obj.book(20, 30);
//        expect = true;
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);

        res = obj.book(200, 300);
        expect = true;
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);

        res = obj.book(100, 200);
        expect = true;
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);

        res = obj.book(100, 101);
        expect = false;
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);

    }

    public class MyCalendar {

        private List<Pair<Integer, Integer>> data;
        public MyCalendar() {
            data = new ArrayList<>();
        }

        public boolean book(int start, int end) {
            return bSearchInsert(data, start, end);
        }

        //Runtime: 21 ms, faster than 96.29% of Java online submissions for My Calendar I.
        //Memory Usage: 54.9 MB, less than 28.22% of Java online submissions for My Calendar I.
        //Binary search & insert
        //Time: O(NlogN) Space: O(N)
        private boolean bSearchInsert(List<Pair<Integer, Integer>> list, int start, int end){
            int left = 0, right = list.size();

            //Binary Search. Time: O(logN)
            while (left < right){
                int m = (right + left) / 2;

                Pair<Integer, Integer> before = list.get(m);
                if (before.getKey() <= start) {
                    if (before.getValue() > start) return false; //DoubleBooking
                    left = m + 1;
                }else{
                    if (end > before.getKey()) return false; //DoubleBooking
                    right = m;
                }
            }

            //////////////////////////////////////////
            //unnecessary! this part can be deleted.//
            //In order to save space (& reduce N),
            //merge new item with prev or behind one when their range is consecutive,
            if (left - 1 >= 0){
                Pair<Integer, Integer> tmp = list.get(left - 1);
                if (tmp.getValue() == start){
                    list.remove(left - 1);
                    list.add(left - 1, new Pair<>(tmp.getKey(), end));
                    return true;
                }
            }else if (left + 1 < list.size()){
                Pair<Integer, Integer> tmp = list.get(left + 1);
                if (tmp.getKey() == end){
                    list.remove(left + 1);
                    list.add(left + 1, new Pair<>(start, tmp.getValue()));
                    return true;
                }
            }
            //unnecessary! this part can be deleted.//
            //////////////////////////////////////////

            list.add(left, new Pair<>(start ,end));
            return true;
        }
    }


}
