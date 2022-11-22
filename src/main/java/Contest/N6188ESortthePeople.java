package Contest;

/**
 * You are given an array of strings names, and an array heights that consists of distinct positive integers. Both arrays are of length n.
 *
 * For each index i, names[i] and heights[i] denote the name and height of the ith person.
 *
 * Return names sorted in descending order by the people's heights.
 *
 *
 *
 * Example 1:
 *
 * Input: names = ["Mary","John","Emma"], heights = [180,165,170]
 * Output: ["Mary","Emma","John"]
 * Explanation: Mary is the tallest, followed by Emma and John.
 * Example 2:
 *
 * Input: names = ["Alice","Bob","Bob"], heights = [155,185,150]
 * Output: ["Bob","Alice","Bob"]
 * Explanation: The first Bob is the tallest, followed by Alice and the second Bob.
 *
 *
 * Constraints:
 *
 * n == names.length == heights.length
 * 1 <= n <= 103
 * 1 <= names[i].length <= 20
 * 1 <= heights[i] <= 105
 * names[i] consists of lower and upper case English letters.
 * All the values of heights are distinct.
 */


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.time.LocalTime.now;

/**
 * E - [time: 6+
 */

//2418. Sort the People
public class N6188ESortthePeople {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        //doRun_demo(true, "abaccb", new int[]{1,3,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, String beginWord, String endWord, List<String> wordList) {
//        int res = new N127HWordLadder()
//                .ladderLength(beginWord, endWord, wordList);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }



    //contest version
    //Runtime: 9 ms, faster than 80.00% of Java online submissions for Sort the People.
    //Memory Usage: 42.9 MB, less than 100.00% of Java online submissions for Sort the People.
    //build-in sort
    //Time: O(NlgN + N); Space: O(N + lgN)
    //Time: O(NlgN); Space: O(N)
    public String[] sortPeople(String[] names, int[] heights) {

        //Space: O(N)
        int[][] people = new int[names.length][2];
        for (int i = 0; i < names.length; i++)
            people[i]  = new int[] {heights[i], i};

        //Time: O(NlgN); Space: O(lgN)
        Arrays.sort(people, (a, b) -> b[0] - a[0]);

        String[] res = new String[names.length];
        //Time: O(N)
        for (int i = 0; i < names.length; i++)
            res[i] = names[people[i][1]];

        return res;
    }

}
