package Contest;


import java.io.IOException;

import static java.time.LocalTime.now;

/**
 * Given a positive integer n, find the pivot integer x such that:
 *
 * The sum of all elements between 1 and x inclusively equals the sum of all elements between x and n inclusively.
 * Return the pivot integer x. If no such integer exists, return -1. It is guaranteed that there will be at most one pivot index for the given input.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 8
 * Output: 6
 * Explanation: 6 is the pivot integer since: 1 + 2 + 3 + 4 + 5 + 6 = 6 + 7 + 8 = 21.
 * Example 2:
 *
 * Input: n = 1
 * Output: 1
 * Explanation: 1 is the pivot integer since: 1 = 1.
 * Example 3:
 *
 * Input: n = 4
 * Output: -1
 * Explanation: It can be proved that no such integer exist.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 1000
 */
//Time: 8m
//2485. Find the Pivot Integer
public class N6245EFindthePivotInteger {

    //1.math
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Find the Pivot Integer.
    //Memory Usage: 39.2 MB, less than 88.89% of Java online submissions for Find the Pivot Integer.
    //Time: O(1); Space: O(1)
    public int pivotInteger(int n) {
        int x = (int) Math.sqrt((n * n  + n) >> 1);
        return (x * x == ((n * n  + n) >> 1)) ? x : -1;
    }
}
