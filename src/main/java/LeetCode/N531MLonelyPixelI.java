package LeetCode;

import java.util.*;

import static java.time.LocalTime.now;


/**
 * 531. Lonely Pixel I
 * Medium
 *
 * 320
 *
 * 38
 *
 * Add to List
 *
 * Share
 * Given an m x n picture consisting of black 'B' and white 'W' pixels, return the number of black lonely pixels.
 *
 * A black lonely pixel is a character 'B' that located at a specific position where the same row and same column don't have any other black pixels.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: picture = [["W","W","B"],["W","B","W"],["B","W","W"]]
 * Output: 3
 * Explanation: All the three 'B's are black lonely pixels.
 * Example 2:
 *
 *
 * Input: picture = [["B","B","B"],["B","B","W"],["B","B","B"]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * m == picture.length
 * n == picture[i].length
 * 1 <= m, n <= 500
 * picture[i][j] is 'W' or 'B'.
 */

/**
 * M - [time: 60-
 */
public class N531MLonelyPixelI {


    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        char[][] data2;

        data2 = new char[][]{{'W','W','B'}, {'W','B','W'}, {'B','W','W'}};
        doRun(3, data2);

        data2 = new char[][]{{'B','B','B'}, {'B','B','W'}, {'B','B','B'}};
        doRun(0, data2);

        data2 = new char[][]{{'B','W','W'}, {'W','W','W'}, {'B','W','W'}};
        doRun(0, data2);

        data2 = new char[][]{{'W','B','W','W'}, {'W','B','B','W'}, {'W','W','W','W'}};
        doRun(0, data2);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, char[][] picture) {
        int res = new N531MLonelyPixelI()
                .findLonelyPixel(picture);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Lonely Pixel I.
    //Memory Usage: 44.1 MB, less than 93.63% of Java online submissions for Lonely Pixel I.
    //Time: O(M * N + M); Space: O(M + N)
    //let M be the number of row; N be the number of column
    public int findLonelyPixel(char[][] picture) {

        //track the only one 'B''s column index
        int[] row = new int[picture.length];
        //count the number of 'B' in column
        int[] column = new int[picture[0].length];

        //Time: O(M * N)
        for (int i = 0; i < picture.length; i++) {
            int c = 0; int x = -1;
            for (int j = 0; j < picture[0].length; j++) {
                if (picture[i][j] == 'B') {
                    c++; x = j; column[j]++;
                }
            }
            //if there is only one 'B' in the row, then we track it's column
            if (c == 1) row[i] = x + 1;
        }

        //Time: O(M)
        int res = 0;
        for (int r: row)
            if (r > 0  && column[r - 1] == 1) res++;
        return res;
    }
}
