package LeetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
 * You must do it in place.
 *
 * Example 1:
 * Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[1,0,1],[0,0,0],[1,0,1]]
 *
 * Example 2:
 * Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 *
 *
 * Constraints:
 * m == matrix.length
 * n == matrix[0].length
 * 1 <= m, n <= 200
 * -231 <= matrix[i][j] <= 231 - 1
 *
 *
 * Follow up:
 *
 * A straightforward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 */

/**
 * M - 【耗时：20m]
 *  - 利用首行首列+extra(1)
 */
public class N73MSetMatrixZeroes {

    public static void main(String[] args){

        int[][] matrix = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        System.out.println("----------------1:");
        print(matrix);
        new N73MSetMatrixZeroes().setZeroes(matrix);
        print(matrix);

        matrix = new int[][]{{0,0,0,0},{3,4,5,2},{1,3,1,5}};
        System.out.println("----------------2:");
        print(matrix);
        new N73MSetMatrixZeroes().setZeroes(matrix);
        print(matrix);

        matrix = new int[][]{{1,2,3,4},{5,0,7,8},{0,10,11,12},{13,14,15,0}};
        System.out.println("----------------3:");
        print(matrix);
        new N73MSetMatrixZeroes().setZeroes(matrix);
        print(matrix);

        matrix = new int[][]{{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        System.out.println("----------------4:");
        print(matrix);
        new N73MSetMatrixZeroes().setZeroes(matrix);
        print(matrix);
    }

    static private void print(int[][] nums){
        for(int i = 0; i< nums.length;i++){
            System.out.println(Arrays.stream(nums[i])
                    .mapToObj(x -> String.valueOf(x))
                    .collect(Collectors.joining(", ")));
        }
        System.out.println("----------------");
    }

    //Runtime: 1 ms, faster than 96.88% of Java online submissions for Set Matrix Zeroes.
    //Memory Usage: 43.9 MB, less than 91.07% of Java online submissions for Set Matrix Zeroes.
    //time: O(m*n) space : O(1)
    public void setZeroes(int[][] matrix) {
        byte i0 = 1;

        for (int j = 0; j< matrix[0].length; j++){
            if(matrix[0][j] == 0) {
                i0 = 0; break;
            }
        }

        for (int i = 1; i< matrix.length; i++)
            for (int j = 0; j< matrix[i].length; j++)
                if (matrix[i][j] == 0)
                    matrix[i][0] = matrix[0][j] = 0;

        for (int i = 1; i< matrix.length; i++)
            //backward
            for (int j = matrix[i].length-1; j>=0; j--)
                if(matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;

        if (i0 == 0)
            for (int j = 0; j< matrix[0].length; j++) matrix[0][j] = 0;
    }


    public void setZeroes2(int[][] matrix) {
        Set<Integer> row = new HashSet<>();
        Set<Integer> column = new HashSet<>();

        for (int i = 0; i< matrix.length; i++){
            if(column.size() == matrix[i].length)  break;
            for (int j = 0; j< matrix[i].length; j++){
                if (matrix[i][j] == 0) {
                    row.add(i); column.add(j);
                }
            }
        }
        if (row.size() == matrix.length || column.size() == matrix[0].length){
            for(int[] sub : matrix)  Arrays.fill(sub, 0);
            return;
        }

        for(int i : row)  Arrays.fill(matrix[i], 0);

        for(int j : column)
            for (int i = 0; i < matrix.length; i++) matrix[i][j] = 0;
    }


    //Runtime: 2 ms, faster than 45.62% of Java online submissions for Set Matrix Zeroes.
    //Memory Usage: 54.5 MB, less than 21.01% of Java online submissions for Set Matrix Zeroes.
    //time: O(m*n) space : O(m+n)
    public void setZeroes1(int[][] matrix) {
        int[] m = new int[matrix.length];
        int[] n = new int[matrix[0].length];
        for (int i = 0; i< matrix.length; i++){
            for (int j = 0; j< matrix[i].length; j++){
                if (matrix[i][j] == 0) {
                    m[i] = 1; n[j] = 1;
                }
            }
        }

        for (int i = 0; i< m.length; i++)
            if (m[i] == 1)
                Arrays.fill(matrix[i], 0);

        for (int j = 0; j< n.length; j++)
            if (n[j] == 1)
                for (int i = 0; i < matrix.length; i++)
                    matrix[i][j] = 0;
    }

}
