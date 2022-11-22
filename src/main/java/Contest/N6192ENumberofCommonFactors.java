package Contest;

import java.util.List;

import static java.time.LocalTime.now;


/**
 * Given two positive integers a and b, return the number of common factors of a and b.
 *
 * An integer x is a common factor of a and b if x divides both a and b.
 *
 *
 *
 * Example 1:
 *
 * Input: a = 12, b = 6
 * Output: 4
 * Explanation: The common factors of 12 and 6 are 1, 2, 3, 6.
 * Example 2:
 *
 * Input: a = 25, b = 30
 * Output: 2
 * Explanation: The common factors of 25 and 30 are 1, 5.
 *
 *
 * Constraints:
 *
 * 1 <= a, b <= 1000
 */

/**
 *  E - [time: 3-
 */
//2427. Number of Common Factors
public class N6192ENumberofCommonFactors {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        //doRun(true, "abaccb", new int[]{1,3,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});

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


    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Number of Common Factors.
    //Memory Usage: 38.8 MB, less than 75.00% of Java online submissions for Number of Common Factors.
    //Time: O(min(a,b); Space:O(1)
    public int commonFactors(int a, int b) {
        int res = 0;
        int max = Math.min(a, b);
        for (int i = 1; i <= max; i++)
            if (a % i == 0 && b % i == 0) res++;
        return res;
    }

}
