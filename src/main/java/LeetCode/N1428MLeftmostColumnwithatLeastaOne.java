package LeetCode;

import java.io.IOException;
import java.util.List;

import static java.time.LocalTime.now;

/**
 * A row-sorted binary matrix means that all elements are 0 or 1 and each row of the matrix is sorted in non-decreasing order.
 *
 * Given a row-sorted binary matrix binaryMatrix, return the index (0-indexed) of the leftmost column with a 1 in it. If such an index does not exist, return -1.
 *
 * You can't access the Binary Matrix directly. You may only access the matrix using a BinaryMatrix interface:
 *
 * BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
 * BinaryMatrix.dimensions() returns the dimensions of the matrix as a list of 2 elements [rows, cols], which means the matrix is rows x cols.
 * Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.
 *
 * For custom testing purposes, the input will be the entire binary matrix mat. You will not have access to the binary matrix directly.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat = [[0,0],[1,1]]
 * Output: 0
 * Example 2:
 *
 *
 * Input: mat = [[0,0],[0,1]]
 * Output: 1
 * Example 3:
 *
 *
 * Input: mat = [[0,0],[0,0]]
 * Output: -1
 *
 *
 * Constraints:
 *
 * rows == mat.length
 * cols == mat[i].length
 * 1 <= rows, cols <= 100
 * mat[i][j] is either 0 or 1.
 * mat[i] is sorted in non-decreasing order.
 */
public class N1428MLeftmostColumnwithatLeastaOne {

    //2. top-right to bottom-left
    //Runtime: 0ms 100%; Memory: 42.8MB 91%
    //Time: O(M + N); Space: O(1)
    //Let N be the number of columns, and M be the number of rows.
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> list = binaryMatrix.dimensions();
        int rows = list.get(0);
        int cols = list.get(1);

        int row = 0, col = cols - 1;
        while (row < rows && col >= 0) {
            if (binaryMatrix.get(row, col) == 1)
                col--;
            else row++;
        }
        return col == cols - 1 ? -1 : col + 1;
    }

    //1.Binary Search
    //Runtime: 0ms 100%; Memory: 43.1MB 78%
    //Time: O(logN * M); Space: O(1)
    //Let N be the number of columns, and M be the number of rows.
    public int leftMostColumnWithOne_1(BinaryMatrix binaryMatrix) {
        List<Integer> list = binaryMatrix.dimensions();
        int rows = list.get(0);
        int cols = list.get(1);

        int left = 0, right = cols - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (helper_findInColumn(binaryMatrix, mid, rows)) {
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return left >= cols ? -1 : left;
    }

    private boolean helper_findInColumn(BinaryMatrix binaryMatrix, int j, int iEnd){
        for (int i = 0; i < iEnd; i++)
            if (binaryMatrix.get(i, j) == 1) return true;
        return false;
    }


    interface BinaryMatrix {
        public int get(int row, int col);
        public List<Integer> dimensions();
    }
}
