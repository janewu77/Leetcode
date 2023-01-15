package Contest;


/**
 * You are given a positive integer n, indicating that we initially have an n x n 0-indexed integer matrix mat filled with zeroes.
 *
 * You are also given a 2D integer array query. For each query[i] = [row1i, col1i, row2i, col2i], you should do the following operation:
 *
 * Add 1 to every element in the submatrix with the top left corner (row1i, col1i) and the bottom right corner (row2i, col2i). That is, add 1 to mat[x][y] for for all row1i <= x <= row2i and col1i <= y <= col2i.
 * Return the matrix mat after performing every query.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3, queries = [[1,1,2,2],[0,0,1,1]]
 * Output: [[1,1,0],[1,2,1],[0,1,1]]
 * Explanation: The diagram above shows the initial matrix, the matrix after the first query, and the matrix after the second query.
 * - In the first query, we add 1 to every element in the submatrix with the top left corner (1, 1) and bottom right corner (2, 2).
 * - In the second query, we add 1 to every element in the submatrix with the top left corner (0, 0) and bottom right corner (1, 1).
 * Example 2:
 *
 *
 * Input: n = 2, queries = [[0,0,1,1]]
 * Output: [[1,1],[1,1]]
 * Explanation: The diagram above shows the initial matrix and the matrix after the first query.
 * - In the first query we add 1 to every element in the matrix.
 */

public class N2536MIncrementSubmatricesbyOne {


    //2.
    //Runtime: 21ms 100%; Memory: 50MB 91%
    //Time: O(Q * N + N * N); Space: O(1)
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] res = new int[n][n];

        for (int[] query: queries) {
            for (int j = query[1]; j <= query[3]; j++) {
                res[query[0]][j]++;
                if (query[2] + 1 < n)
                    res[query[2] + 1][j]--;
            }
        }

        for (int i = 1; i < n; i++)
            for (int j = 0; j < n; j++)
                res[i][j] += res[i - 1][j];

        return res;
    }

    //1.brute force
    //Time: O(N * N * Q); Space: O(1)
    public int[][] rangeAddQueries_1(int n, int[][] queries) {
        int[][] res = new int[n][n];
        for(int[] query : queries){
            for(int i = query[0]; i <= query[2]; i++) {
                for (int j = query[1]; j <= query[3]; j++) {
                    res[i][j]++;
                }
            }
        }
        return res;
    }
}
