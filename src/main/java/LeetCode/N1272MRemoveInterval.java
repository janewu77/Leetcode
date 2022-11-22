package LeetCode;


import utils.comm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalTime.now;

/**
 * A set of real numbers can be represented as the union of several disjoint intervals, where each interval is in the form [a, b). A real number x is in the set if one of its intervals [a, b) contains x (i.e. a <= x < b).
 *
 * You are given a sorted list of disjoint intervals intervals representing a set of real numbers as described above, where intervals[i] = [ai, bi] represents the interval [ai, bi). You are also given another interval toBeRemoved.
 *
 * Return the set of real numbers with the interval toBeRemoved removed from intervals. In other words, return the set of real numbers such that every x in the set is in intervals but not in toBeRemoved. Your answer should be a sorted list of disjoint intervals as described above.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: intervals = [[0,2],[3,4],[5,7]], toBeRemoved = [1,6]
 * Output: [[0,1],[6,7]]
 * Example 2:
 *
 *
 * Input: intervals = [[0,5]], toBeRemoved = [2,3]
 * Output: [[0,2],[3,5]]
 * Example 3:
 *
 * Input: intervals = [[-5,-4],[-3,-2],[1,2],[3,5],[8,9]], toBeRemoved = [-1,4]
 * Output: [[-5,-4],[-3,-2],[4,5],[8,9]]
 *
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 104
 * -109 <= ai < bi <= 109
 */
public class N1272MRemoveInterval {


    public static void main(String[] args) {

        System.out.println(now());
        String[] data;



        doRun("[[-5, -4],[-3, -2],[4, 5],[8, 9]]",
                new int[][]{{-5,-4},{-3,-2},{1,2},{3,5},{8,9}}, new int[]{-1,4});

        doRun("[[0, 1],[6, 7]]", new int[][]{{0,2}, {3,4}, {5,7}}, new int[]{1,6});
        doRun("[[0, 2],[3, 5]]", new int[][]{{0,5}}, new int[]{2,3});

        doRun("[[0, 50]]", new int[][]{{0,100}}, new int[]{50,100});


        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(String expect, int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> res1 = new N1272MRemoveInterval().removeInterval(intervals, toBeRemoved);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
        String res = comm.toString(res1);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //写得太复杂，容易错！
    //Runtime: 8 ms, faster than 89.52% of Java online submissions for Remove Interval.
    //Memory Usage: 50.2 MB, less than 99.13% of Java online submissions for Remove Interval.
    //one pass
    public List<List<Integer>> removeInterval_2(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> res = new ArrayList<>();

        for (int[] item : intervals) {

            if (item[0] < toBeRemoved[0] && toBeRemoved[1] < item[1]) {
                res.add(Arrays.asList(item[0], toBeRemoved[0]));
                res.add(Arrays.asList(toBeRemoved[1], item[1]));
                continue;
            }

            int right = toBeRemoved[1] < item[1] ? item[1] : item[1] <= toBeRemoved[0] ? item[1] : toBeRemoved[0];
            int left = item[0] < toBeRemoved[0] ? item[0] : toBeRemoved[1] <= item[0] ? item[0] : toBeRemoved[1];
            if (left < right) res.add(Arrays.asList(left, right));
        }
        return res;
    }


    //Runtime: 7 ms, faster than 95.63% of Java online submissions for Remove Interval.
    //Memory Usage: 50.6 MB, less than 93.01% of Java online submissions for Remove Interval.
    //one pass
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> res = new ArrayList<>();
        for (int[] item : intervals) {

            if (item[1] <= toBeRemoved[0] || toBeRemoved[1] <= item[0]){
                res.add(Arrays.asList(item[0], item[1]));
                continue;
            }

//            if (toBeRemoved[0] <= item[0] && item[1] <= toBeRemoved[1]){
//                continue;
//            }
//
//            if (item[0] < toBeRemoved[0] && toBeRemoved[1] < item[1]) {
//                res.add(Arrays.asList(item[0], toBeRemoved[0]));
//                res.add(Arrays.asList(toBeRemoved[1], item[1]));
//                continue;
//            }

            if (item[0] < toBeRemoved[0])
                res.add(Arrays.asList(item[0], toBeRemoved[0]));

            if (toBeRemoved[1] < item[1])
                res.add(Arrays.asList(toBeRemoved[1], item[1]));
        }

        return res;
    }
}
