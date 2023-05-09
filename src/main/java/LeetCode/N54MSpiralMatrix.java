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


    //Time: 0ms 100%; Memory: 40.4MB 82.7%
    //Time: O(N); Space: O(N)
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int flag = 1;

        //放边界
        int left = 0, right = matrix[0].length - 1;
        int up = 0, bottom = matrix.length - 1;

        //游标
        int i = 0; int j = 0;

        while (res.size() < matrix.length * matrix[0].length) {
            //j
            while (left <= j && j <= right) {
                res.add(matrix[i][j]);
                j += flag;
            }

            if (flag > 0) up += flag;
            else bottom += flag;

            i += flag;
            j -= flag;

            //i
            while (up <= i && i <= bottom) {
                res.add(matrix[i][j]);
                i += flag;
            }
            if (flag > 0) right -= flag;
            else left -= flag;

            i -= flag;
            j -= flag;

            flag = -flag;
        }
        return res;
    }


    //Time: 0ms 100%; Memory: 40.4MB 82.7%
    //Time: O(N); Space: O(N)
    public List<Integer> spiralOrder_1(int[][] matrix) {
        List<Integer> res = new ArrayList<>();

        int flag = 1;

        //放边界
        Integer[] scope = {0, matrix.length - 1, 0, matrix[0].length - 1};

        //游标
        Integer[] cursor = {0, 0};

        while (true) {
            if (doColumn(matrix, cursor, flag, scope, res) == 0)
                return res;
            if (doRow(matrix, cursor, flag, scope, res) == 0)
                return res;
            flag = -flag;
        }
    }

    //在行上移动，（行不变，列变化）
    private int doColumn(int[][] matrix, Integer[] cursor, int f, Integer[] scope, List<Integer> res){
        //flag 正为加 负为减
        int count = 0;
        int i = cursor[0];
        int j = cursor[1];
        while (scope[2] <= j && j <= scope[3]) {
            res.add(matrix[i][j]);
            j = j + f;
            count++;
        }

        if ( f > 0 ) scope[0] += f;
        else scope[1] += f;

        cursor[0] = i + f;
        cursor[1] = j - f;
        return count;
    }

    private int doRow(int[][] matrix, Integer[] cursor, int f, Integer[] scope, List<Integer> res){
        //flag 正为加 负为减
        int count = 0;
        int i = cursor[0];
        int j = cursor[1];
        while (scope[0] <= i && i <= scope[1]) {
            res.add(matrix[i][j]);
            i = i + f;
            count++;
        }
        if (f > 0) scope[3] -= f;
        else scope[2] -= f;

        cursor[0] = i - f;
        cursor[1] = j - f;
        return count;
    }

}

