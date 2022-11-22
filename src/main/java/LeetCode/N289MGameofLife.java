package LeetCode;

import java.util.Arrays;

/**
 *
 * According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 *
 * The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1) or dead (represented by a 0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
 *
 * Any live cell with fewer than two live neighbors dies as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population.
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * The next state is created by applying the above rules simultaneously to every cell in the current state,
 * where births and deaths occur simultaneously. Given the current state of the m x n grid board, return the next state.
 *
 *
 *
 * Example 1:
 * Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
 * Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
 *
 * Example 2:
 * Input: board = [[1,1],[1,0]]
 * Output: [[1,1],[1,1]]
 *
 *
 * Constraints:
 *
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 25
 * board[i][j] is 0 or 1.
 *
 *
 * Follow up:
 *
 * Could you solve it in-place? Remember that the board needs to be updated simultaneously:
 * You cannot update some cells first and then use their updated values to update other cells.
 * In this question, we represent the board using a 2D array. In principle, the board is infinite,
 * which would cause problems when the active area encroaches upon the border of the array (i.e.,
 * live cells reach the border). How would you address these problems?
 */

/**
 * M - [time: 45-]
 *  - [time: 90m] 演化： 2Darray -> 1D array -> in-place
 *
 */

public class N289MGameofLife {

    public static void main(String[] args) {

        int[][] data;

        data = new int[][]{{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        doRun(data);
    }

    private static void doRun(int[][] board){
        new N289MGameofLife().gameOfLife(board);
        //String strRes = Arrays.stream(res).mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(""));
        System.out.println("=======");
//        System.out.println("["+(expected == res)+"].[expected:"+ expected+"] res:"+res);
    }

    ////////////////////////////////////////////////////////////////
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Game of Life.
    //Memory Usage: 41.9 MB, less than 78.13% of Java online submissions for Game of Life.
    //Time: O(M*N); Space:O(1)
    //in-place
    public void gameOfLife(int[][] board) {

        for(int i = 0; i < board.length; i++){
            //count how many lives in three columns (left, middle, right)
            int leftLive = 0, midLive = 0;
            int rightLive = countColLive(board, i,0); //first column

            for (int j = 0; j < board[0].length; j++){
                leftLive = midLive;
                midLive = rightLive;
                rightLive = 0;
                if (j + 1 < board[0].length)
                    rightLive = countColLive(board, i,j + 1);

                //alive cells include self (if self is alive.)
                int totalLive = leftLive + midLive + rightLive;

                //apply rules
                if (board[i][j] == 1){
                    totalLive--; //subtract self
                    //rule: 1 & 3
                    if (totalLive < 2 || totalLive > 3) board[i][j] = -1; //-1 : 1 -> 0
                }else{
                    //rule: 4
                    if (totalLive == 3) board[i][j] = 2; //2 : 0 -> 1
                }
            }//End for j

        }//End for i

        //Restore numbers ( -1 > 0; 2 > 1)
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                board[i][j] = board[i][j] >= 1 ? 1 : 0;
    }

    //count up, middle(current), down cells
    private int countColLive(int[][] board, int i, int j){
        int c = 0;

        //up.  last row's val have -1 or 2
        if (i - 1 >= 0) c += (board[i - 1][j] == 1 || board[i - 1][j] == -1 ) ? 1 : 0;

        //current
        c += (board[i][j] == 1 ? 1 : 0);

        //down
        if (i + 1 < board.length) c += (board[i + 1][j] == 1 ? 1 : 0);

        return c;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    //Time: O(M*N); Space:O(2*N)
    //0ms 48mb
    public void gameOfLife_1darr(int[][] board) {
        int[] workRow = new int[board[0].length];
        int[] workRow_bak = new int[board[0].length];

        for(int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                workRow[j] = board[i][j];
                int countLive = liveNeighbors(board, i, j);

                if (board[i][j] == 1){
                    if (countLive < 2 || countLive > 3) workRow[j] = 0;
                }else{
                    if (countLive == 3) workRow[j] = 1;
                }
            }

            if (i > 0) board[i-1] = Arrays.copyOf(workRow_bak, workRow_bak.length);
            workRow_bak = Arrays.copyOf(workRow, workRow.length);
        }
        board[board.length-1] = Arrays.copyOf(workRow_bak, workRow_bak.length);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Game of Life.
    //Memory Usage: 42.6 MB, less than 32.53% of Java online submissions for Game of Life.
    //Time: O(M*N); Space:O(M*N)
    public void gameOfLife_workboard(int[][] board) {
        int[][] original = new int[board.length][];
        for(int i = 0; i < board.length; i++)
            original[i] = Arrays.copyOf(board[i], board[i].length);

        for(int i = 0; i < original.length; i++){
            for (int j = 0; j < original[0].length; j++){
                board[i][j] = original[i][j];
                int countLive = liveNeighbors(original, i, j);
                if (board[i][j] == 1){
                    if (countLive < 2 || countLive > 3) board[i][j] = 0;
                }else{
                    if (countLive == 3) board[i][j] = 1;
                }
            }
        }
    }

    private int liveNeighbors(int[][] board, int i, int j){
        int c = 0;

        boolean up = i - 1 >= 0;
        boolean down = i + 1 < board.length;
        boolean left = j - 1 >= 0;
        boolean right = j + 1 < board[i].length;

        if (up) c += board[i - 1][j] == 1 ? 1 : 0;
        if (down) c += board[i + 1][j] == 1 ? 1 : 0;
        if (left) c += board[i][j - 1] == 1 ? 1 : 0;
        if (right) c += board[i][j + 1] == 1 ? 1 : 0;

        if (up && left) c += board[i - 1][j - 1] == 1 ? 1 : 0;
        if (up && right) c += board[i - 1][j + 1] == 1 ? 1 : 0;

        if (down && left) c += board[i + 1][j - 1] == 1 ? 1 : 0;
        if (down && right) c += board[i + 1][j + 1] == 1 ? 1 : 0;

        return c;
    }


}
