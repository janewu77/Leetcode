package LeetCode;

/**
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix.
 * This matrix has the following properties:
 *
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 *
 *
 * Example 1:
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * Output: true
 *
 * Example 2:
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * Output: false
 *
 *
 * Constraints:
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -104 <= matrix[i][j], target <= 104
 */

/**
 * M - [time: 15-]
 *  - binary search 的扩展
 */
public class N74MSearcha2DMatrix {

    public static void main(String[] args){
        doRun(true, new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 3);

        doRun(true, new int[][]{{1,3}}, 1);

        doRun(true, new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 10);

        doRun(false, new int[][]{{1,3}}, 8);
        doRun(false, new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 100);
        doRun(false, new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 22);


    }
    private static int c = 1;
    private static void doRun(boolean expected, int[][] nums, int target){
        boolean res = new N74MSearcha2DMatrix().searchMatrix(nums, target);
        System.out.println("[" + (expected == res) +"] "+(c++)+ ".result:"+ res + ".expected:"+expected);
    }

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Search a 2D Matrix.
    //Memory Usage: 41.6 MB, less than 97.97% of Java online submissions for Search a 2D Matrix.
    //Time:  O(log(M)+ log(N) ); Space(1)
    public boolean searchMatrix(int[][] matrix, int target) {

        int x = 0;
        int bottom = matrix.length - 1;
        int right = matrix[0].length - 1;

        //using Binary Search to find the right row
        //time: O(log(M)
        while (x <= bottom) {
            int m = (x + bottom) >> 1;
            if (matrix[m][right] == target) return true;
            if (matrix[m][right] > target) bottom = m - 1;
            else x = m + 1;
        }
        if (x >= matrix.length) return false;

        //using Binary Search in a row to search target
        int left = 0;
        // skip the last column
        right--;
        //time: O(log(N)
        while(left <= right){
            int m = (left + right) >> 1;
            if (matrix[x][m] == target) return true;
            if (matrix[x][m] > target) right = m - 1;
            else left = m + 1;
        }
        return false;
    }

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Search a 2D Matrix.
    //Memory Usage: 42.2MB
    //Time: worst case : O(N + log(M)); Space(1)
    public boolean searchMatrix_1(int[][] matrix, int target) {

        int x = 0;
        int right = matrix[0].length - 1;
        //time : O(M)
        while (x < matrix.length && matrix[x][right] < target) x++;

        if (x >= matrix.length) return false;

        int left = 0;
        while(left <= right){
            int m = (left + right) >> 1;
            if (matrix[x][m] == target) return true;
            if (matrix[x][m] > target) right = m - 1;
            else left = m + 1;
        }
        return false;
    }
}
