package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3
 * Output: [[1,2,3],[8,9,4],[7,6,5]]
 * Example 2:
 *
 * Input: n = 1
 * Output: [[1]]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 20
 */

//Same as N54
public class N59MSpiralMatrixII {

    //1. Iteration
    //Time: 0ms 100%; 40.2MB 98%
    //Time: O(N); Space: O(N)
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int flag = 1;
        int num = 1;

        //放边界
        int left = 0, right = matrix[0].length - 1;
        int up = 0, bottom = matrix.length - 1;

        //游标
        int i = 0; int j = 0;

        while (num <= n * n) {
            //j
            while (left <= j && j <= right) {
                matrix[i][j] = num++;
                j += flag;
            }

            if (flag > 0) up += flag;
            else bottom += flag;

            i += flag;
            j -= flag;

            //i
            while (up <= i && i <= bottom) {
                matrix[i][j] = num++;
                i += flag;
            }
            if (flag > 0) right -= flag;
            else left -= flag;

            i -= flag;
            j -= flag;

            flag = -flag;
        }
        return matrix;
    }
}
