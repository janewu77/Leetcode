package LeetCode;


/**
 * Given an m x n matrix, return true if the matrix is Toeplitz. Otherwise, return false.
 *
 * A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same elements.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
 * Output: true
 * Explanation:
 * In the above grid, the diagonals are:
 * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
 * In each diagonal all elements are the same, so the answer is True.
 * Example 2:
 *
 *
 * Input: matrix = [[1,2],[2,2]]
 * Output: false
 * Explanation:
 * The diagonal "[1, 2]" has different elements.
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 20
 * 0 <= matrix[i][j] <= 99
 *
 * Follow up:
 *
 * What if the matrix is stored on disk, and the memory is limited such that you can only load at most one row of the matrix into the memory at once?
 * What if the matrix is so large that you can only load up a partial row into the memory at once?
 */

/**
 * E - [time: 15-]
 */
public class N766EToeplitzMatrix {

    //2.by row
    //Runtime: 1 ms, faster than 93.83% of Java online submissions for Toeplitz Matrix.
    //Memory Usage: 46.2 MB, less than 16.33% of Java online submissions for Toeplitz Matrix.
    //Time: O(M*N); Space: O(1)
    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if(matrix[i][j] != matrix[i-1][j-1]) return false;
            }
        }
        return true;
    }


    //1.brute force
    //Runtime: 1 ms, faster than 93.83% of Java online submissions for Toeplitz Matrix.
    //Memory Usage: 44.8 MB, less than 83.72% of Java online submissions for Toeplitz Matrix.
    //Time: O(M*N); Space: O(1)
    public boolean isToeplitzMatrix_1(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            int j = 0, x = i + 1, y = j + 1;
            int element = matrix[i][j];
            while (x < matrix.length && y < matrix[0].length) {
                if (matrix[x][y] != element) return false;
                x++; y++;
            }
        }

        for (int j = 1; j < matrix[0].length; j++) {
            int i = 0, x = i + 1, y = j + 1;
            int element = matrix[i][j];
            while(x <matrix.length && y < matrix[0].length) {
                if (matrix[x][y] != element) return false;
                x++; y++;
            }
        }
        return true;
    }

}
