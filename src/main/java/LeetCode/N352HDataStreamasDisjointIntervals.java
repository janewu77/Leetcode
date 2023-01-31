package LeetCode;


import utils.comm;

import java.util.*;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

/**
 * Given a data stream input of non-negative integers a1, a2, ..., an, summarize the numbers seen so far as a list of disjoint intervals.
 *
 * Implement the SummaryRanges class:
 *
 * SummaryRanges() Initializes the object with an empty stream.
 * void addNum(int value) Adds the integer value to the stream.
 * int[][] getIntervals() Returns a summary of the integers in the stream currently as a list of disjoint intervals [starti, endi]. The answer should be sorted by starti.
 *
 *
 * Example 1:
 *
 * Input
 * ["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
 * [[], [1], [], [3], [], [7], [], [2], [], [6], []]
 * Output
 * [null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]], null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]
 *
 * Explanation
 * SummaryRanges summaryRanges = new SummaryRanges();
 * summaryRanges.addNum(1);      // arr = [1]
 * summaryRanges.getIntervals(); // return [[1, 1]]
 * summaryRanges.addNum(3);      // arr = [1, 3]
 * summaryRanges.getIntervals(); // return [[1, 1], [3, 3]]
 * summaryRanges.addNum(7);      // arr = [1, 3, 7]
 * summaryRanges.getIntervals(); // return [[1, 1], [3, 3], [7, 7]]
 * summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
 * summaryRanges.getIntervals(); // return [[1, 3], [7, 7]]
 * summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
 * summaryRanges.getIntervals(); // return [[1, 3], [6, 7]]
 *
 *
 * Constraints:
 *
 * 0 <= value <= 104
 * At most 3 * 104 calls will be made to addNum and getIntervals.
 *
 *
 * Follow up: What if there are lots of merges and the number of disjoint intervals is small compared to the size of the data stream?
 */


public class N352HDataStreamasDisjointIntervals {


    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");

        SummaryRanges summaryRanges;
        int[][] expect;
        List<int[][]> expectList;


        expectList = new ArrayList<>();
        int[] data = new int[]{49,97,53,5,33,65,62, 51, 100,38};
        expect = new int[][]{{49,49}};
        expectList.add(expect);
        expect = new int[][]{{49,49}, {97,97}};
        expectList.add(expect);
        expect = new int[][]{{49,49}, {53,53}, {97,97}};
        expectList.add(expect);
        expect = new int[][]{{5,5}, {49,49}, {53,53}, {97,97}};
        expectList.add(expect);
        expect = new int[][]{{5,5}, {33,33}, {49,49}, {53,53}, {97,97}};
        expectList.add(expect);
        expect = new int[][]{{5,5}, {33,33}, {49,49}, {53,53}, {65,65}, {97,97}};
        expectList.add(expect);
        expect = new int[][]{{5,5}, {33,33}, {49,49}, {53,53}, {62,62}, {65,65}, {97,97}};
        expectList.add(expect);
        expect = new int[][]{{5,5}, {33,33}, {49,49},{51,51}, {53,53}, {62,62}, {65,65}, {97,97}};
        expectList.add(expect);
        expect = new int[][]{{5,5}, {33,33}, {49,49},{51,51}, {53,53}, {62,62}, {65,65}, {97,97}, {100,100}};
        expectList.add(expect);
        expect = new int[][]{{5,5}, {33,33}, {38,38},{49,49},{51,51}, {53,53}, {62,62}, {65,65}, {97,97}, {100,100}};
        expectList.add(expect);
        doRun(expectList, data);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(List<int[][]> expectList,int[] data) {
        SummaryRanges summaryRanges = new SummaryRanges();
        for (int i = 0; i < data.length; i++) {
            summaryRanges.addNum(data[i]);
            //if (i == data.length - 1)
                checkRes(expectList.get(i), summaryRanges);
        }
        System.out.println();
    }

    static private void checkRes(int[][] expect, SummaryRanges summaryRanges) {
        int[][] res = summaryRanges.getIntervals();
        boolean success = expect.length == res.length;
        for (int i = 0; i < expect.length && success; i++)
            success &= res[i][0] == expect[i][0] && res[i][1] == expect[i][1];

        String res1 = Arrays.stream(res).map(a -> "{" +a[0] +"," + a[1]+ "}").collect(Collectors.joining(","));
        String expect1 = Arrays.stream(expect).map(a -> "{" +a[0] +"," + a[1]+ "}").collect(Collectors.joining(","));
        System.out.println("["+success+"] expect:" + expect1 + " res:" + res1);
    }

    //2.flag
    // Runtime: 60ms 94%; Memory:47.1MB 92%
    static class SummaryRanges {
        private boolean[] flags;
        private int min = 10_001, max = 0;

        public SummaryRanges() {
            flags = new boolean[10_001];
        }

        //Time: O(1)
        public void addNum(int value) {
            flags[value] = true;
            min = Math.min(min, value);
            max = Math.max(max, value);
        }

        //Time: O(10^4)
        public int[][] getIntervals() {
            List<int[]> list = new ArrayList<>();

            for (int i = min; i <= max; i++) {
                if (!flags[i]) continue;
                int[] item = new int[]{i, i};
                while (flags[i]) i++;
                item[1] = i - 1;
                list.add(item);
            }
            return list.toArray(new int[list.size()][]);
        }
    }




    //1.insert sort + binary search
    //Runtime: 62ms 85%; Memory:47MB 92%
    static class SummaryRanges_1 {

        private List<int[]> list;
        public SummaryRanges_1() {
            list = new ArrayList<>();
        }

        //Time: O(logN)
        public void addNum(int value) {
            int[] item = new int[]{value, value};

            int idx = Collections.binarySearch(list, item, Comparator.comparingInt(a -> a[0]));
            if (idx >= 0) return;
            idx = -idx - 1;

            if (idx >= list.size()) list.add(item);
            else list.add(idx, item);
            merge(idx, idx + 1);
            merge(idx - 1, idx);
        }

        //Time: O(N)
        public int[][] getIntervals() {
            int[][] res = new int[list.size()][2];
            for (int i = 0; i < res.length; i++)
                res[i] = list.get(i);
            return res;
        }

        private void merge(int prev, int next) {
            if (prev < 0 || next >= list.size()) return;
            int[] prvData = list.get(prev);
            int[] nextData = list.get(next);
            if (prvData[1] + 1 >= nextData[0]){
                prvData[0] = Math.min(prvData[0],nextData[0]);
                prvData[1] = Math.max(prvData[1],nextData[1]);
                list.remove(next);
            }
        }
    }
}
