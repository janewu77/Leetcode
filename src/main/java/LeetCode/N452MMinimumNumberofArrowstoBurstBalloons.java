package LeetCode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.time.LocalTime.now;

/**
 * There are some spherical balloons taped onto a flat wall that represents the XY-plane. The balloons are represented as a 2D integer array points where points[i] = [xstart, xend] denotes a balloon whose horizontal diameter stretches between xstart and xend. You do not know the exact y-coordinates of the balloons.
 *
 * Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis. A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend. There is no limit to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.
 *
 * Given the array points, return the minimum number of arrows that must be shot to burst all balloons.
 *
 *
 *
 * Example 1:
 *
 * Input: points = [[10,16],[2,8],[1,6],[7,12]]
 * Output: 2
 * Explanation: The balloons can be burst by 2 arrows:
 * - Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
 * - Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].
 * Example 2:
 *
 * Input: points = [[1,2],[3,4],[5,6],[7,8]]
 * Output: 4
 * Explanation: One arrow needs to be shot for each balloon for a total of 4 arrows.
 * Example 3:
 *
 * Input: points = [[1,2],[2,3],[3,4],[4,5]]
 * Output: 2
 * Explanation: The balloons can be burst by 2 arrows:
 * - Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
 * - Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].
 *
 *
 * Constraints:
 *
 * 1 <= points.length <= 105
 * points[i].length == 2
 * -231 <= xstart < xend <= 231 - 1
 */
public class N452MMinimumNumberofArrowstoBurstBalloons {

    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");

        doRun(3, new int[][]{{0,9},{1,8},{7,8},{1,6},{9,16},{7,13},{7,10},{6,11},{6,9},{9,13}});
        doRun(2, new int[][]{{3,9},{7,12},{3,8},{6,8},{9,10},{2,9},{0,9},{3,9},{0,6},{2,8}});
        doRun(2, new int[][]{{-2147483646,-2147483645}, {2147483646,2147483647}});
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[][] points) {
        int res = new N452MMinimumNumberofArrowstoBurstBalloons().findMinArrowShots(points);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //1.greedy
    //Runtime: 78ms 67%; Memory: 80.2MB 65%
    //Time: O(N * logN + N); Space: O(logN)
    //Time: O(N * logN); Space: O(logN)
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;

        Arrays.sort(points, Comparator.comparingInt(a -> a[1]));
        int res = 1;
        int end = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > end){
                res++;
                end = points[i][1];
            }
        }
        return res;
    }

    public int findMinArrowShots_1(int[][] points) {
        if (points.length == 0) return 0;

        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
        int res = 1;
        int end = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= end){
                end = Math.min(end, points[i][1]);
            }else{
                res++;
                end = points[i][1];
            }
        }
        return res;
    }
}
