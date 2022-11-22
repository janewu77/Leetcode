package LeetCode;

/**
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:
 *
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 *
 *
 * Example 1:
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * Output: true
 *
 *
 * Example 2:
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * Output: false
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -109 <= matrix[i][j] <= 109
 * All the integers in each row are sorted in ascending order.
 * All the integers in each column are sorted in ascending order.
 * -109 <= target <= 109
 *
 *
 */


/**
 * m - [time: 120-]
 *   - 在算法选择上纠结、在处理边界上纠结，浪费了不少时间。
 *   - 用了二种解法：1。先缩空间+递归； 2。缩空间。 优先2.
 *   - 第三种解法：加入binary search; 在有序数里，binary search的时间性能会比较好，但注意边界的处理。不然容易出错。
 *
 */
public class N240MSearcha2DMatrixII {

    public static void main(String[] args){
        int[][] matrix;
        matrix = new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        doRun(true, matrix,5);

        matrix = new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        doRun(false, matrix,20);

        matrix = new int[][]{{10}};
        doRun(false, matrix,20);

        matrix = new int[][]{{10}};
        doRun(false, matrix,-3);
//
        matrix = new int[][]{{5,6}};
        doRun(false, matrix,20);

        matrix = new int[][]{{5,6}};
        doRun(true, matrix,6);
//
        matrix = new int[][]{{5,6}};
        doRun(false, matrix,1);
//
        matrix = new int[][]{{0,5,9,13,18},{1,8,10,14,19},{1,8,14,18,20},{2,12,15,19,22},{7,15,19,22,25}};
        doRun(true, matrix,22);

        matrix = new int[][]{{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
        doRun(true, matrix,15);

        matrix = new int[][]{{1,3,5}};
        doRun(false, matrix,2);

    }
    private static int c = 1;
    private static void doRun(boolean expected, int[][] matrix, int target){
        boolean res = new N240MSearcha2DMatrixII().searchMatrix(matrix, target);
        System.out.println("[" + (expected == res) +"] "+(c++)+ ".result:"+ res + ".expected:"+expected);
    }

    //Runtime: 5 ms, faster than 100.00% of Java online submissions for Search a 2D Matrix II.
    //Memory Usage: 48.7 MB, less than 84.36% of Java online submissions for Search a 2D Matrix II.
    //从右上角往左下角移动 （代码最简） - Search Space Reduction
    //time：O(n+m)； space(1)
    public boolean searchMatrix(int[][] matrix, int target) {
       int i = 0;
       int j = matrix[0].length - 1;

       while (i < matrix.length && j >= 0){
           if (matrix[i][j] == target) return true;
           if (matrix[i][j] < target) i++;
           else j--;
       }
       return false;
    }

    //Runtime: 6 ms, faster than 90.55% of Java online submissions for Search a 2D Matrix II.
    //Memory Usage: 48.3 MB, less than 92.10% of Java online submissions for Search a 2D Matrix II.
    public boolean searchMatrix_1(int[][] matrix, int target) {
        return searchMatrix_recursive(matrix, target, 0, matrix.length-1, 0, matrix[0].length-1);
    }

    //recursive
    private boolean searchMatrix_recursive(int[][] matrix, int target, int iFrom, int iEnd, int jFrom, int jEnd) {
        if (iFrom > iEnd || jFrom > jEnd) return false;
        if (iFrom < 0 || iEnd >= matrix.length || jFrom < 0 || jEnd > matrix[0].length) return false;

        if (matrix[iFrom][jFrom] == target) return true;

        while (iFrom <= iEnd && matrix[iFrom][jEnd] < target) iFrom++;
        if (iFrom > iEnd) return false;
        while (jFrom <= jEnd && matrix[iEnd][jFrom] < target) jFrom++;
        if (jFrom > jEnd) return false;

        int i = iFrom; int j = jFrom;
        while (i <= iEnd && j <= jEnd) {
            if (matrix[i][j] == target) return true;
            else if (matrix[i][j] > target) {

                if (searchMatrix_recursive(matrix, target, iFrom, i - 1, j, jEnd)) return true;
                if (searchMatrix_recursive(matrix, target, i, iEnd, jFrom, j - 1)) return true;
                return false;

            } else if (i == iEnd && j == jEnd) return false;

            i++; j++;
            if (i > iEnd) i = iEnd;
            if (j > jEnd) j = jEnd;
        }
        return false;
    }



}
