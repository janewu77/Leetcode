package LeetCode;

import java.io.IOException;

import static java.time.LocalTime.now;

public class N221MMaximalSquare {

    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");

        //
        doRun(16, new char[][]{{'1','1','1','1','0'},{'1','1','1','1','0'},{'1','1','1','1','1'},{'1','1','1','1','1'},{'0','0','1','1','1'}});
        doRun(4, new char[][]{{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}});
        doRun(4, new char[][]{{'1','1'},{'1','1'}});
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, char[][] matrix) {
        int res = new N221MMaximalSquare().maximalSquare(matrix);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //3.DP bottom-up 1d-array
    //Runtime: 5ms, 98.7%; Memory: 55.9MB 81.23%
    //Time: O(M * N); Space: O(N);
    public int maximalSquare(char[][] matrix) {
        int res = 0;
        int[] dp = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++){
            int prevSize = 0;

            for (int j = 0; j < matrix[0].length; j++){
                int currSize = 0;
                if (matrix[i][j] == '1') {
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        currSize = Math.min(Math.min(dp[j], dp[j - 1]), prevSize);
                    }
                    currSize += 1;
                }
                res = Math.max(res, currSize);
                prevSize = dp[j];
                dp[j] = currSize;
            }
        }
        return res * res;
    }

    //2.DP bottom-up 2d-array
    //Runtime: 28ms, 37.93%; Memory: 55.6MB 82.28%
    //Time: O(M * N); Space: O(M * N);
    public int maximalSquare_2(char[][] matrix) {
        int res = 0;
        int[][][] dp = new int[matrix.length][matrix[0].length][2];
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                int[] node = new int[]{0, 0};
                if (matrix[i][j] == '1') {
                    node = new int[]{1, 1};
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        node[0] = Math.min(dp[i - 1][j][0], dp[i][j - 1][0]);
                        node[0] = Math.min(node[0], dp[i - 1][j - 1][0]) + 1;

                        node[1] = Math.min(dp[i - 1][j][1], dp[i][j - 1][1]);
                        node[1] = Math.min(node[1], dp[i - 1][j - 1][1]) + 1;
                    }
                }
                dp[i][j] = node;
                if (node[0] > res) res = node[0];
            }
        }
        return res * res;
    }


    //1.Brute force
    //Runtime: 35ms, 19.64%; Memory: 55.8MB 81.61%
    //Time: O(M * N * M * N); Space: O(1);
    public int maximalSquare_1(char[][] matrix) {
        int res = 0;

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                while (isAllOne(matrix, i, j, res)) res++;

        return res * res;
    }

    private boolean isAllOne(char[][] matrix, int x, int y, int size){
        if (x + size >= matrix.length || y + size >= matrix[0].length)
            return false;

        for (int i = x; i <= x + size; i++)
            for (int j = y; j <= y + size; j++)
                if (matrix[i][j] == '0') return false;
        return true;
    }

}
