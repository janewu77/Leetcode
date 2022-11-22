package Contest;

import java.util.List;

import static java.time.LocalTime.now;

/**
 * Example 1:
 *
 * Input: n = 5
 * Output: 10
 * Explanation: The smallest multiple of both 5 and 2 is 10.
 * Example 2:
 *
 * Input: n = 6
 * Output: 6
 * Explanation: The smallest multiple of both 6 and 2 is 6. Note that a number is a multiple of itself.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 150
 */

/**
 * E - [time: 2-
 */
//2413. Smallest Even Multiple
public class N6180ESmallestEvenMultiple {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        //doRun(true, "abaccb", new int[]{1,3,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, String beginWord, String endWord, List<String> wordList) {
//        int res = new C01()
//                .ladderLength(beginWord, endWord, wordList);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Smallest Even Multiple.
    //Memory Usage: 39.2 MB, less than 100.00% of Java online submissions for Smallest Even Multiple.
    public int smallestEvenMultiple(int n) {
        return n % 2 == 0? n : n<< 1;
        //return n << (n & 1);
    }
}
