package Contest;

import static java.time.LocalTime.now;


/**
 * 6184. Count Days Spent Together
 * User Accepted:2585
 * User Tried:3926
 * Total Accepted:2617
 * Total Submissions:6194
 * Difficulty:Easy
 * Alice and Bob are traveling to Rome for separate business meetings.
 *
 * You are given 4 strings arriveAlice, leaveAlice, arriveBob, and leaveBob. Alice will be in the city from the dates arriveAlice to leaveAlice (inclusive), while Bob will be in the city from the dates arriveBob to leaveBob (inclusive). Each will be a 5-character string in the format "MM-DD", corresponding to the month and day of the date.
 *
 * Return the total number of days that Alice and Bob are in Rome together.
 *
 * You can assume that all dates occur in the same calendar year, which is not a leap year. Note that the number of days per month can be represented as: [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31].
 *
 *
 *
 * Example 1:
 *
 * Input: arriveAlice = "08-15", leaveAlice = "08-18", arriveBob = "08-16", leaveBob = "08-19"
 * Output: 3
 * Explanation: Alice will be in Rome from August 15 to August 18. Bob will be in Rome from August 16 to August 19. They are both in Rome together on August 16th, 17th, and 18th, so the answer is 3.
 * Example 2:
 *
 * Input: arriveAlice = "10-01", leaveAlice = "10-31", arriveBob = "11-01", leaveBob = "12-31"
 * Output: 0
 * Explanation: There is no day when Alice and Bob are in Rome together, so we return 0.
 *
 *
 * Constraints:
 *
 * All dates are provided in the format "MM-DD".
 * Alice and Bob's arrival dates are earlier than or equal to their leaving dates.
 * The given dates are valid dates of a non-leap year.
 */
//2409. Count Days Spent Together
public class N6184ECountDaysSpentTogether {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;


        doRun(49, "09-01", "10-19", "06-19", "10-20");


        doRun(2, "01-01", "01-27", "01-03", "01-04");

        doRun(3, "08-15", "08-18", "08-16", "08-19");

        doRun(3, "08-16", "08-19", "08-15", "08-18");


        doRun(0, "10-01", "10-31", "11-01", "12-31");



        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int res = new N6184ECountDaysSpentTogether()
                .countDaysTogether(arriveAlice, leaveAlice, arriveBob, leaveBob);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 1 ms, faster than 90.00% of Java online submissions for Count Days Spent Together.
    //Memory Usage: 41.5 MB, less than 100.00% of Java online submissions for Count Days Spent Together.
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] days = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        //later arrive
        int[] begin = arriveAlice.compareTo(arriveBob) < 0 ? helper(arriveBob) : helper(arriveAlice);
        //early leave
        int[] end = leaveAlice.compareTo(leaveBob) < 0 ? helper(leaveAlice) : helper(leaveBob);

        int res = 0;
        if (begin[0] < end[0] || (begin[0] == end[0] && begin[1] <= end[1])) {
            res = end[1] - begin[1] + 1;
            if (begin[0] < end[0]) {
                for (int i = begin[0]; i < end[0] - 1; i++) res += days[i]; //whole month
                res += days[begin[0]-1]; //first month
            }
        }
        return res;
    }

    private int[] helper(String arriveDT){
        int[] res = new int[2];
        res[0] = Integer.parseInt(arriveDT.substring(0,2));
        res[1] = Integer.parseInt(arriveDT.substring(3));
        return res;
    }

}
