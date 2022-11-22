package Contest;

import static java.time.LocalTime.now;

/**
 * You are given two arrays of strings that represent two inclusive events that happened on the same day, event1 and event2, where:
 *
 * event1 = [startTime1, endTime1] and
 * event2 = [startTime2, endTime2].
 * Event times are valid 24 hours format in the form of HH:MM.
 *
 * A conflict happens when two events have some non-empty intersection (i.e., some moment is common to both events).
 *
 * Return true if there is a conflict between two events. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: event1 = ["01:15","02:00"], event2 = ["02:00","03:00"]
 * Output: true
 * Explanation: The two events intersect at time 2:00.
 * Example 2:
 *
 * Input: event1 = ["01:00","02:00"], event2 = ["01:20","03:00"]
 * Output: true
 * Explanation: The two events intersect starting from 01:20 to 02:00.
 * Example 3:
 *
 * Input: event1 = ["10:00","11:00"], event2 = ["14:00","15:00"]
 * Output: false
 * Explanation: The two events do not intersect.
 *
 *
 * Constraints:
 *
 * evnet1.length == event2.length == 2.
 * event1[i].length == event2[i].length == 5
 * startTime1 <= endTime1
 * startTime2 <= endTime2
 * All the event times follow the HH:MM format.
 */

/**
 *
 * E - [time: 15-
 */
//2446. Determine if Two Events Have Conflict
public class N6214EDetermineifTwoEventsHaveConflict {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        //doRun(true, "abaccb", new int[]{1,2,3});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, String beginWord) {
//        int res = new N127HWordLadder().ladderLength(beginWord);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //2.
    //Runtime: 1 ms, faster than 90.00% of Java online submissions for Determine if Two Events Have Conflict.
    //Memory Usage: 41 MB, less than 60.00% of Java online submissions for Determine if Two Events Have Conflict.
    public boolean haveConflict(String[] event1, String[] event2) {
        return event1[0].compareTo(event2[0]) <= 0  &&  event2[0].compareTo(event1[0]) <= 0;
    }


    //1.brute force
    public boolean haveConflict_1(String[] event1, String[] event2) {
        int hour1  = Integer.valueOf(event1[0].substring(0,2));
        int minutes1 = Integer.valueOf(event1[0].substring(3,5));

        int hour2  = Integer.valueOf(event2[0].substring(0,2));
        int minutes2 = Integer.valueOf(event2[0].substring(3,5));

        if (hour1 > hour2 || hour1 == hour2 && minutes1 > minutes2) {
            int hour2E  = Integer.valueOf(event2[1].substring(0,2));
            int minutes2E = Integer.valueOf(event2[1].substring(3,5));
            if (hour1 < hour2E || hour1 == hour2E && minutes1 <= minutes2E)
                return true;
        }else{
            int hour1E  = Integer.valueOf(event1[1].substring(0,2));
            int minutes1E = Integer.valueOf(event1[1].substring(3,5));
            if (hour2 < hour1E || hour2 == hour1E && minutes2 <= minutes1E)
                return true;
        }
        return false;
    }



}
