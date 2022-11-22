package LeetCode;

/**
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
 *
 *
 * Example 1:
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[7,4,1],[8,5,2],[9,6,3]]
 *
 *
 * Example 2:
 * Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 *
 *
 * Constraints:
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 *
 */
public class N48MRotateImage {

    public static void main(String[] args){
        new N48MRotateImage().rotate(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
    }

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Rotate Image.
    //Memory Usage: 40.9 MB, less than 93.37% of Java online submissions for Rotate Image.
    //using matrix sub-diagonal
    //Time: O(M)
    public void rotate(int[][] matrix) {

        //exchange : matrix sub-diagonal
        for (byte i = 0; i < matrix.length-1; i++){
            for (byte j= 0; j < matrix[i].length - 1 - i; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[matrix[i].length - 1 - j][matrix[i].length - 1 - i];
                matrix[matrix[i].length - 1- j][matrix[i].length - 1- i] = tmp;
            }
        }

        //exchange rows
        byte len = (byte) (matrix.length/2);
        for (byte i = 0; i < len; i++){
            int[] tmp = matrix[i]; //just a ref
            matrix[i] = matrix[matrix.length - 1 - i];
            matrix[matrix.length - 1 - i] = tmp;
        }
    }


    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Rotate Image.
    //Memory Usage: 41.3 MB, less than 86.19% of Java online submissions for Rotate Image.
    // 按对角线交换 + 前后交换
    //Time: O(M)
    public void rotate1(int[][] matrix) {
        //exchange row : column
        for (byte i = 0; i < matrix.length; i++){
            for (int j= i + 1; j < matrix[i].length; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        byte len = (byte)matrix.length;
        for (byte i = 0; i < matrix.length; i++){
            for (byte j= 0; j < len / 2; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][len -1 - j];
                matrix[i][len -1 - j] = tmp;
            }
        }
    }


}
