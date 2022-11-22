package Contest;



import java.util.List;

import static java.time.LocalTime.now;

/**
 * You are given an m x n integer matrix grid.
 *
 * We define an hourglass as a part of the matrix with the following form:
 *
 *
 * Return the maximum sum of the elements of an hourglass.
 *
 * Note that an hourglass cannot be rotated and must be entirely contained within the matrix.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[6,2,1,3],[4,2,1,5],[9,2,8,7],[4,1,2,9]]
 * Output: 30
 * Explanation: The cells shown above represent the hourglass with the maximum sum: 6 + 2 + 1 + 2 + 9 + 2 + 8 = 30.
 * Example 2:
 *
 *
 * Input: grid = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: 35
 * Explanation: There is only one hourglass in the matrix, with the sum: 1 + 2 + 3 + 5 + 7 + 8 + 9 = 35.
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 3 <= m, n <= 150
 * 0 <= grid[i][j] <= 106
 */

/**
 * M - [time: 15-
 */

//8. Maximum Sum of an Hourglass
public class N6193MMaximumSumofanHourglass {

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



    //Runtime: 5 ms, faster than 50.00% of Java online submissions for Maximum Sum of an Hourglass.
    //Memory Usage: 43.8 MB, less than 100.00% of Java online submissions for Maximum Sum of an Hourglass.
    //brute force
    //Time:O(M * N * 7)
    public int maxSum(int[][] grid) {
        int res = 0;
        for (int i = 0; i <= grid.length - 3; i++)
            for (int j = 0; j <= grid[0].length - 3; j++)
                res = Math.max(res, helper7(grid, i, j));
        return res;
    }

    public int helper7(int[][] grid, int x, int y) {
        int res = 0;
        res += grid[x + 0][y + 0];
        res += grid[x + 0][y + 1];
        res += grid[x + 0][y + 2];
        res += grid[x + 1][y + 1];
        res += grid[x + 2][y + 0];
        res += grid[x + 2][y + 1];
        res += grid[x + 2][y + 2];
        return res;
    }

    public int helper9(int[][] grid, int x, int y) {
        int res = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                res += grid[x + i][y + j];

        res -= (grid[x + 1][y] + grid[x + 1][y + 2]);
        return res;
    }

}
