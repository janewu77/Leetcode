package LeetCode;

import java.util.*;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

/**
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 *
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 *
 * Return intervals after the insertion.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * Example 2:
 *
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 *
 *
 * Constraints:
 *
 * 0 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 105
 * intervals is sorted by starti in ascending order.
 * newInterval.length == 2
 * 0 <= start <= end <= 105
 */
public class N57MInsertInterval {

    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");
        int[][] intervals; int[] newInterval;
        int[][] expect;

        expect = new int[][]{{1,2},{3,18}};
        intervals = new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}};
        newInterval = new int[]{4,18};
        doRun(expect, intervals, newInterval);

        expect = new int[][]{{1,2},{3,10},{12,16}};
        intervals = new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}};
        newInterval = new int[]{4,8};
        doRun(expect, intervals, newInterval);

        expect = new int[][]{{1,5},{6,9}};
        intervals = new int[][]{{1,3},{6,9}};
        newInterval = new int[]{2,5};
        doRun(expect, intervals, newInterval);

        expect = new int[][]{{2,3},{6,9},{10,11}};
        intervals = new int[][]{{2,3},{6,9}};
        newInterval = new int[]{10,11};
        doRun(expect, intervals, newInterval);

        expect = new int[][]{{1,1},{2,3},{6,9}};
        intervals = new int[][]{{2,3},{6,9}};
        newInterval = new int[]{1,1};
        doRun(expect, intervals, newInterval);

        expect = new int[][]{{1,3},{4,5},{6,9}};
        intervals = new int[][]{{1,3},{6,9}};
        newInterval = new int[]{4,5};
        doRun(expect, intervals, newInterval);

        System.out.println(now());
        System.out.println("==================");
    }

    //92
    static private void doRun(int[][] expect, int[][] intervals, int[] newInterval) {
        int[][] result = new N57MInsertInterval().insert(intervals, newInterval);

        boolean success = expect.length == result.length;
        for (int i = 0; i < expect.length && success; i++) {
            success = (expect[i].length == result[i].length);
            for (int j = 0; j < expect[0].length && success; j++) {
                if (expect[i][j] != result[i][j]) {
                    success = false;break;
                }
            }
        }
        String strExpect = "[" + Arrays.asList(expect).stream().map(x->Arrays.toString(x)).collect(Collectors.joining(",")) + "]";
        String strRes = "[" + Arrays.asList(result).stream().map(x->Arrays.toString(x)).collect(Collectors.joining(",")) + "]";
        System.out.println("["+success+"] expect:" + strExpect+ " res:" + strRes);
    }

    //3.one pass
    //Runtime: 2ms 64%; Memory: 44.9MB 58%
    //Time: O(N); Space: O(N);
    public int[][] insert_3(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();

        int i = 0;
        for (; i < intervals.length; i++) {
            if (newInterval[1] < intervals[i][0]) {
                i--; break;
            }

            boolean isOverlap = Math.min(intervals[i][1], newInterval[1]) - Math.max(intervals[i][0], newInterval[0]) >= 0;
            if (!isOverlap) {
                list.add(intervals[i]);
            }else{
                newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
                if (newInterval[1] <= intervals[i][1]) {
                    newInterval[1] = intervals[i][1]; break;
                }
            }
        }

        list.add(newInterval);
        for (i++; i < intervals.length; i++)
            list.add(intervals[i]);

        return list.toArray(new int[list.size()][2]);
    }


    //2.one pass
    //Runtime: 1ms 99%; Memory: 44.3MB 96%
    //Time: O(N); Space: O(N);
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();

        int i = 0;
        for (; i < intervals.length; i++) {
            if (newInterval[0] > intervals[i][1]) {
                list.add(intervals[i]);
                continue;
            }

            if (newInterval[1] < intervals[i][0]) {
                i--; break;
            }
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            if (newInterval[1] <= intervals[i][1]) {
                newInterval[1] = intervals[i][1]; break;
            }
        }

        list.add(newInterval);
        for (i++; i < intervals.length; i++)
            list.add(intervals[i]);

        return list.toArray(new int[list.size()][2]);
    }

    //1.Binary Search
    //Runtime: 2ms 64%; Memory: 44.5MB 86%
    //Time: O(N + logN + N); Space: O(N);
    //Time: O(N); Space: O(N);
    public int[][] insert_1(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();
        for (int[] interval : intervals)
            list.add(interval);

        int idxBegin = Collections.binarySearch(list, new int[]{newInterval[0], newInterval[0]}, (a, b) -> {
            if (a[0] >= b[0] && a[0] <= b[1] || b[0] >= a[0] && b[0] <= a[1]) return 0;
            return a[0] - b[0];
        });
        if (idxBegin < 0) idxBegin = -idxBegin - 1;

        int idxEnd = idxBegin;
        if (idxBegin < list.size()) {
            idxEnd = Collections.binarySearch(list, new int[]{newInterval[1], newInterval[1]}, (a, b) -> {
                if (a[1] >= b[0] && a[1] <= b[1] || b[1] >= a[0] && b[1] <= a[1]) return 0;
                return a[1] - b[1];
            });
            if (idxEnd < 0) idxEnd = -idxEnd - 1;
        }

        if (idxBegin == list.size()) {
            list.add(newInterval);
        } else {
            //merge
            int mergeEnd = idxEnd;
            if (mergeEnd == intervals.length || newInterval[1] < intervals[mergeEnd][0])
                mergeEnd--;

            newInterval[0] = Math.min(newInterval[0], intervals[idxBegin][0]);

            if (idxEnd < intervals.length && newInterval[1] >= intervals[idxEnd][0])
                newInterval[1] = Math.max(newInterval[1], intervals[idxEnd][1]);

            for (int i = mergeEnd; i >= idxBegin; list.remove(i--));
            list.add(idxBegin, newInterval);
        }
        return list.toArray(new int[list.size()][2]);
    }


}
