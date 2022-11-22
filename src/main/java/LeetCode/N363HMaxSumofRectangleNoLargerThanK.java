package LeetCode;


import java.util.Arrays;

import static java.time.LocalTime.now;

/**
 *
 * Given an m x n matrix matrix and an integer k, return the max sum of a rectangle in the matrix
 * such that its sum is no larger than k.
 *
 * It is guaranteed that there will be a rectangle with a sum no larger than k.
 *
 * Example 1:
 * Input: matrix = [[1,0,1],[0,-2,3]], k = 2
 * Output: 2
 * Explanation: Because the sum of the blue rectangle [[0, 1], [-2, 3]] is 2,
 * and 2 is the max number no larger than k (k = 2).
 *
 *
 * Example 2:
 * Input: matrix = [[2,2,-1]], k = 3
 * Output: 3
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -100 <= matrix[i][j] <= 100
 * -105 <= k <= 105
 *
 *
 * Follow up: What if the number of rows is much larger than the number of columns?
 */

/**
 * H - [time: 120+
 *
 */
public class N363HMaxSumofRectangleNoLargerThanK {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;
        //data = new int[]{0,1,4,6,7,10};
        //doRun_maxSumArray(4, data, 4);

        data2 = new int[][]{{2,2,-1}};
        doRun_maxSumSubmatrix(-1, data2, 0);

        data2 = new int[][]{{1,0,1}, {0,-2,3}};
        doRun_maxSumSubmatrix(2, data2, 2);

        data2 = new int[][]{{2,2,-1}};
        doRun_maxSumSubmatrix(3, data2, 3);



        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun_maxSumSubmatrix(int expect, int[][] matrix, int k) {
        int res = new N363HMaxSumofRectangleNoLargerThanK().maxSumSubmatrix(matrix,k);
////        String res = comm.toString(res1);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("==================");
    }

//    static private void doRun_maxSumArray(int expect, int[] data, int k) {
//        int res = new N363HMaxSumofRectangleNoLargerThanK().maxSumArray(data,k);
//////        String res = comm.toString(res1);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
////        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
////        System.out.println("==================");
//    }



    //Runtime: 122 ms, faster than 99.11% of Java online submissions for Max Sum of Rectangle No Larger Than K.
    //Memory Usage: 43.3 MB, less than 81.96% of Java online submissions for Max Sum of Rectangle No Larger Than K.
    //Prefix Sum
    //Time: O(N*N*M*M);  Space: O(M)
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int res = Integer.MIN_VALUE;

        //prefix sum
        //Time: O(N*M)
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i > 0) matrix[i][j] += matrix[i - 1][j];
                if (j > 0) matrix[i][j] += matrix[i][j - 1];
                if (i > 0 && j > 0) matrix[i][j] -= matrix[i-1][j-1];
            }
        }

        //Time: O(N*N * M*M); Space: O(M)
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix.length; j++) {
                //Time: O(M); Space: O(M)
                int[] data = Arrays.copyOf(matrix[j], matrix[j].length);
                if (j > i) {
                    //Time: O(M)
                    for (int x = 0; x < data.length; x++)
                        data[x] = data[x] - matrix[i][x];
                }
                //Time: O(M*M)
                res = Math.max(res, maxSumArray1(data, k));
                if (res == k) return k;
            }
        }
        return res;
    }

    //Time: O(N*N); Space: O(1)
    private int maxSumArray1(int[] data, int k) {
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < data.length; i++) {
            for (int j = i; j < data.length; j++) {
                int value = data[j];
                if (j > i) value -= data[i];
                if (value <= k) {
                    res = Math.max(res, value);
                    if (res == k) return k;
                }
            }
        }
        return res;
    }

}
