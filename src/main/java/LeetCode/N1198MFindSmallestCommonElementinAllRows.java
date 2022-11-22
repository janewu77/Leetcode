package LeetCode;

import Contest.C0806;

import static java.time.LocalTime.now;

/**
 * Given an m x n matrix mat where every row is sorted in strictly increasing order, return the smallest common element in all rows.
 *
 * If there is no common element, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: mat = [[1,2,3,4,5],[2,4,5,8,10],[3,5,7,9,11],[1,3,5,7,9]]
 * Output: 5
 * Example 2:
 *
 * Input: mat = [[1,2,3],[2,3,4],[2,3,5]]
 * Output: 2
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 500
 * 1 <= mat[i][j] <= 104
 * mat[i] is sorted in strictly increasing order.
 */

/**
 * M - [time: 20-
 */
public class N1198MFindSmallestCommonElementinAllRows extends C0806 {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        data2 = new int[][]{{1,2,3}, {3,4,5}, {5,6,7}};
        doRun(-1, data2);
        data2 = new int[][]{{1,2,3,4,5}, {2,4,5,8,10}, {3,5,7,9,11}, {1,3,5,7,9}};
        doRun(5, data2);

        System.out.println(now());
        System.out.println("==================");

        N1198MFindSmallestCommonElementinAllRows xx = new N1198MFindSmallestCommonElementinAllRows();
        xx.p1();
    }


    static private void doRun(int expect, int[][] mat) {
        int res = new N1198MFindSmallestCommonElementinAllRows().smallestCommonElement(mat);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //1. count by column
    //Runtime: 2 ms, faster than 75.92% of Java online submissions for Find Smallest Common Element in All Rows.
    //Memory Usage: 64.4 MB, less than 89.80% of Java online submissions for Find Smallest Common Element in All Rows.
    //Time: O(M * N); Space:O(10_001);
    public int smallestCommonElement(int[][] mat) {
        int[] counter = new int[10_001];
        for (int j = 0; j < mat[0].length; j++) {
            for (int i = 0; i < mat.length; i++) {
                counter[mat[i][j]]++;
                if (counter[mat[i][j]] == mat.length) return mat[i][j];
            }
        }
        return  -1;
    }



    //    public int smallestCommonElement(int[][] mat) {
//        int[] counter = new int[10_001];
//        int res = Integer.MAX_VALUE;
//        int min = mat[0][0], max = mat[0][mat[0].length - 1];
//        int maxCount = 0;
//        for (int i = 0; i < mat.length; i++) {
//            min = Math.max(min, mat[i][0]);
//            max = Math.min(max, mat[i][mat[0].length - 1]);
//            if (min > max) return -1;
//
//            for (int j = 0; j < mat[0].length; j++) {
//                if (mat[i][j] < min) continue;
//                if (mat[i][j] > max) break;
//                counter[mat[i][j]]++;
//                maxCount = Math.max(maxCount, counter[mat[i][j]]);
//                if (counter[mat[i][j]] == mat.length) res = Math.min(res, mat[i][j]);
//            }
//            if (maxCount != i + 1) return -1;
//        }
//        return res == Integer.MAX_VALUE ? -1: res;
//    }



    public static void p1(){
        System.out.println("2222");
    }
}
