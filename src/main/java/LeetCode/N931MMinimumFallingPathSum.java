package LeetCode;

public class N931MMinimumFallingPathSum {

    //1.DP bottom-up in-place
    //Runtime: 4ms, 74.91%; Memory: 42.8MB, 91.99%
    //Time: O(N * N); Space: O(1);
    public int minFallingPathSum(int[][] matrix) {
        for (int i = matrix.length - 2; i >= 0; i--) {
            for (int j = 0; j < matrix[0].length; j++) {
                int min = matrix[i + 1][j];
                if (j - 1 >= 0) min = Math.min(min, matrix[i + 1][j - 1]);
                if (j + 1 < matrix[0].length) min = Math.min(min, matrix[i + 1][j + 1]);
                matrix[i][j] += min;
            }
        }
        int res = Integer.MAX_VALUE;
        for (int v : matrix[0]) res = Math.min(res, v);
        return res;
    }
}
