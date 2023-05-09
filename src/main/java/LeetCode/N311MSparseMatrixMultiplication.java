package LeetCode;

/**
 * Given two sparse matrices mat1 of size m x k and mat2 of size k x n, return the result of mat1 x mat2. You may assume that multiplication is always possible.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat1 = [[1,0,0],[-1,0,3]], mat2 = [[7,0,0],[0,0,0],[0,0,1]]
 * Output: [[7,0,0],[-7,0,3]]
 * Example 2:
 *
 * Input: mat1 = [[0]], mat2 = [[0]]
 * Output: [[0]]
 *
 *
 * Constraints:
 *
 * m == mat1.length
 * k == mat1[i].length == mat2.length
 * n == mat2[i].length
 * 1 <= m, n, k <= 100
 * -100 <= mat1[i][j], mat2[i][j] <= 100
 */
public class N311MSparseMatrixMultiplication {

    //1. Iteration
    //Time: 3ms 30%; Memory: 43.1MB 68%
    //Time: O(L * M * N); Space: O(1)
    //L: the length of the mat1; M * N: the number of elements in mat2
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        int[][] res = new int[mat1.length][mat2[0].length];
        for(int i = 0; i < mat1.length; i++) {
            for(int j = 0; j < mat2[0].length; j++) {
                int sum = 0;
                for(int t = 0; t < mat2.length; t++) {
                    sum += mat1[i][t] * mat2[t][j];
                }
                res[i][j] = sum;
            }
        }
        return res;
    }

}
