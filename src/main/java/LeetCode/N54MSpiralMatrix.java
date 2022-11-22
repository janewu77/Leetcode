package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 *
 * Example 1:
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 *
 *
 * Example 2:
 * Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 */

/**
 * M
 *
 *
 */

public class N54MSpiralMatrix {

    public static void main(String[] args){

        int[][] matrix = {{1,2,3, 31}, {4,5,6,61},{7,8,9,91}};

        List<Integer> results = (new N54MSpiralMatrix()).spiralOrder(matrix);
        results.stream().forEach(System.out::println);
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length < 0) return res;
        int flag = 1;

        //放边界
        int left = 0; int right =matrix.length-1;
        int up = 0; int bottom = matrix[0].length-1;

        //游标
        int i = 0; int j = 0;
        while(res.size() < matrix.length * matrix[0].length) {

            // do doColumn
            while( up <= j  && j <= bottom) {
                res.add(matrix[i][j]);
                j += flag;
            }

            if (flag > 0 ) left += flag;
            else right += flag;
            i += flag;
            j -= flag;

            // doRow
            while( left <= i && i <= right) {
                res.add(matrix[i][j]);
                i += flag;
            }
            if (flag > 0 ) bottom -= flag;
            else up -= flag;
            i -= flag;
            j -= flag;

            flag *= (-1);
        }
        return res;
    }
        public List<Integer> spiralOrder1(int[][] matrix) {
            List<Integer> res = new ArrayList<>();
            if (matrix.length < 1) return res;

            int flag = 1;

            //放边界
            Integer[] scope = {0,matrix.length-1, 0, matrix[0].length-1};

            //游标
            Integer[] cursor = {0,0};

            while(true) {
                List<Integer> resTmp = doColumn(matrix, cursor, flag, scope);
                if (resTmp.size() <= 0) return res;
                res.addAll(resTmp);

                resTmp = doRow(matrix, cursor, flag, scope);
                if (resTmp.size() <= 0) return res;
                res.addAll(resTmp);

                flag = flag * (-1);
            }
        }

        //在行上移动，（行不变，列变化）
        private List<Integer> doColumn(int[][] matrix, Integer[] cursor, int f, Integer[] scope){
            //flag 正为加 负为减
            List<Integer> res = new ArrayList<>();
            int i = cursor[0];
            int j = cursor[1];
            while( scope[2] <= j  && j <= scope[3]) {
                res.add(matrix[i][j]);
                j = j + f;
            }

            if (f > 0 ) scope[0] += f;
            else scope[1] += f;

            cursor[0] = i + f;
            cursor[1] = j - f;
            return res;
        }

        private List<Integer> doRow(int[][] matrix, Integer[] cursor, int f, Integer[] scope){
            //flag 正为加 负为减
            List<Integer> res = new ArrayList<>();
            int i = cursor[0];
            int j = cursor[1];
            while( scope[0] <= i && i <= scope[1]) {
                res.add(matrix[i][j]);
                i = i + f;
            }
            if (f > 0 )scope[3] -= f;
            else scope[2] -= f;
            cursor[0] = i - f;
            cursor[1] = j - f;
            return res;
        }

    }

