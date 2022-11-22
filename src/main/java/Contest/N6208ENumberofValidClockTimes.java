package Contest;

import static java.time.LocalTime.now;
/**
 * You are given a string of length 5 called time, representing the current time on a digital clock in the format "hh:mm". The earliest possible time is "00:00" and the latest possible time is "23:59".
 *
 * In the string time, the digits represented by the ? symbol are unknown, and must be replaced with a digit from 0 to 9.
 *
 * Return an integer answer, the number of valid clock times that can be created by replacing every ? with a digit from 0 to 9.
 *
 *
 *
 * Example 1:
 *
 * Input: time = "?5:00"
 * Output: 2
 * Explanation: We can replace the ? with either a 0 or 1, producing "05:00" or "15:00". Note that we cannot replace it with a 2, since the time "25:00" is invalid. In total, we have two choices.
 * Example 2:
 *
 * Input: time = "0?:0?"
 * Output: 100
 * Explanation: Each ? can be replaced by any digit from 0 to 9, so we have 100 total choices.
 * Example 3:
 *
 * Input: time = "??:??"
 * Output: 1440
 * Explanation: There are 24 possible choices for the hours, and 60 possible choices for the minutes. In total, we have 24 * 60 = 1440 choices.
 *
 *
 * Constraints:
 *
 * time is a valid string of length 5 in the format "hh:mm".
 * "00" <= hh <= "23"
 * "00" <= mm <= "59"
 * Some of the digits might be replaced with '?' and need to be replaced with digits from 0 to 9.
 */

/**
 * E - [time: 15-
 */
//2437. Number of Valid Clock Times
public class N6208ENumberofValidClockTimes {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;


        System.out.println('a' + 1 == 'b');
        System.out.println('b' - 1 == 'a');
        System.out.println('b' > 'a');

        doRun(60, "14:??");
        doRun(60 * 10, "1?:??");
        doRun(100, "0?:0?");
        doRun(1440, "??:??");

        doRun(1440, "2?:??");

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, String time) {
        int res = new N6208ENumberofValidClockTimes()
                .countTime(time);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Number of Valid Clock Times.
    //Memory Usage: 41.8 MB, less than 50.00% of Java online submissions for Number of Valid Clock Times.
    public int countTime(String time) {
        if (time.equals("??:??")) return 24 * 60;
        int res = 1;

        if (time.charAt(0) == '?' && time.charAt(1) == '?') {
            res *= 24;
        } else if (time.charAt(0) == '?'){
            res *= (time.charAt(1) >= '4' ? 2 : 3);
        } else if (time.charAt(1) == '?'){
            res *= (time.charAt(0) >= '2' ? 4 : 10);
        }

        if (time.charAt(4) == '?') res *= 10;
        if (time.charAt(3) == '?') res *= 6;
        return res;
    }

}
