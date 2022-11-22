package LeetCode;


import java.util.*;

/**
 * A matrix diagonal is a diagonal line of cells starting from some cell in either the topmost row
 * or leftmost column and going in the bottom-right direction until reaching the matrix's end.
 * For example, the matrix diagonal starting from mat[2][0], where mat is a 6 x 3 matrix,
 * includes cells mat[2][0], mat[3][1], and mat[4][2].
 *
 * Given an m x n matrix mat of integers, sort each matrix diagonal in ascending order and return the resulting matrix.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
 * Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]
 * Example 2:
 *
 * Input: mat = [[11,25,66,1,69,7],[23,55,17,45,15,52],[75,31,36,44,58,8],[22,27,33,25,68,4],[84,28,14,11,5,50]]
 * Output: [[5,17,4,1,52,7],[11,11,25,45,8,69],[14,23,25,44,58,15],[22,27,31,36,50,66],[84,28,75,33,55,68]]
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 100
 * 1 <= mat[i][j] <= 100
 */

/**
 *
 * M - [Time: 10-
 *
 */
public class N1329MSorttheMatrixDiagonally {


    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Sort the Matrix Diagonally.
    //Memory Usage: 43.3 MB, less than 93.32% of Java online submissions for Sort the Matrix Diagonally.
    //bucket sort
    //Time : O( M * N); Space(1)
    //let M be the number of rows; N be the number of columns; V be the constant 100
    public int[][] diagonalSort(int[][] mat) {
        for (int i = 0; i< mat[0].length; i++) sort_by_bucket(mat, 0, i);
        for (int i = 1; i< mat.length; i++) sort_by_bucket(mat, i, 0);
        return mat;
    }

    //Time：O(N) ; Space: O(1)
    private void sort_by_bucket(int[][] mat, int x, int y){
        int[] counter = new int[101];  //Space: O(101)

        int i = x, j = y;
        //Time：O(N)
        while(i < mat.length && j < mat[0].length)
            counter[mat[i++][j++]]++;

        i = x; j = y;
        //Time：O(100)
        for (int idx = 1; idx < counter.length; idx++)
            while (counter[idx] > 0) {
                mat[i++][j++] = idx;
                counter[idx]--;
            }
    }

    //Base on diagonalSort_1
    //Runtime: 6 ms, faster than 85.42% of Java online submissions for Sort the Matrix Diagonally.
    //Memory Usage: 47.9 MB, less than 56.25% of Java online submissions for Sort the Matrix Diagonally.
    //Sort Diagonals One by One Using Heap
    //PriorityQueue
    //Time：O( M * N * lg(min(m,n)) ) ; Space: O(min(m,n))
    // M : mat.length; N: mat[0].length
    public int[][] diagonalSort_2(int[][] mat) {
        int i = 0, j = 0;
        for (j = 0; j< mat[0].length; j++) sort_by_pq(mat, i, j);

        j = 0;
        for (i = 1; i< mat.length; i++) sort_by_pq(mat, i, j);
        return mat;
    }
    //Time：O(N log(min(m,n))) ; Space: O(min(m,n))
    private void sort_by_pq(int[][] mat, int x, int y){
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        int i = x, j = y;
        while(i < mat.length && j < mat[0].length)
            priorityQueue.add(mat[i++][j++]);

        i = x; j = y;
        while(i < mat.length && j < mat[0].length)
            mat[i++][j++] = priorityQueue.poll();
    }



    //Runtime: 12 ms, faster than 47.83% of Java online submissions for Sort the Matrix Diagonally.
    //Memory Usage: 51.5 MB, less than 5.04% of Java online submissions for Sort the Matrix Diagonally.
    //HashMap + PriorityQueue
    //Time: O(M*N*log(min(m,n))); Space:O(M*N);
    public int[][] diagonalSort_1(int[][] mat) {

        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int i = 0; i < mat.length; i++){
            for (int j = 0; j < mat[0].length; j++){
                PriorityQueue<Integer> priorityQueue = map.getOrDefault( j - i, new PriorityQueue<>());
                map.put(j - i, priorityQueue);
                priorityQueue.add(mat[i][j]);
            }
        }

        for (int i = 0; i < mat.length; i++)
            for (int j = 0; j < mat[0].length; j++)
                mat[i][j] = map.get(j - i).poll();

        return mat;
    }


}
