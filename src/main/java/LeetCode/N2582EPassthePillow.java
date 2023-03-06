package LeetCode;

import static java.time.LocalTime.now;

/**
 * There are n people standing in a line labeled from 1 to n. The first person in the line is holding a pillow initially. Every second, the person holding the pillow passes it to the next person standing in the line. Once the pillow reaches the end of the line, the direction changes, and people continue passing the pillow in the opposite direction.
 *
 * For example, once the pillow reaches the nth person they pass it to the n - 1th person, then to the n - 2th person and so on.
 * Given the two positive integers n and time, return the index of the person holding the pillow after time seconds.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4, time = 5
 * Output: 2
 * Explanation: People pass the pillow in the following way: 1 -> 2 -> 3 -> 4 -> 3 -> 2.
 * Afer five seconds, the pillow is given to the 2nd person.
 * Example 2:
 *
 * Input: n = 3, time = 2
 * Output: 3
 * Explanation: People pass the pillow in the following way: 1 -> 2 -> 3.
 * Afer two seconds, the pillow is given to the 3rd person.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 1000
 * 1 <= time <= 1000
 */
public class N2582EPassthePillow {

    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");

        doRun(6, 8,9 );
        doRun(3, 3,2 );

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, int n, int time) {
        int res = new N2582EPassthePillow().passThePillow(n, time);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //1.Math
    //Runtime: 0ms 100%; Memory: 39.2MB 100%
    //Time: O(1); Space: O(1)
    public int passThePillow_1(int n, int time) {
        int m = time % (2 * (n - 1));
        return m < n ? m + 1 : 2 * (n - 1) - m + 1;
    }

    public int passThePillow(int n, int time) {
        return n - Math.abs(n - 1 - time % (n * 2 - 2));
    }

}
