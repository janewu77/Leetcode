package LeetCode;


import java.util.HashSet;
import java.util.Set;

/**
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 *
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * Note:
 *
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 *
 *
 * Example 1:
 *
 *
 * Input: board =
 * [["5","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: true
 * Example 2:
 *
 * Input: board =
 * [["8","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: false
 * Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 *
 *
 * Constraints:
 *
 * board.length == 9
 * board[i].length == 9
 * board[i][j] is a digit 1-9 or '.'.
 */
public class N36MValidSudoku {


    //4.Bitmasking
    //Runtime: 2 ms, faster than 97.46% of Java online submissions for Valid Sudoku.
    //Memory Usage: 45.1 MB, less than 80.70% of Java online submissions for Valid Sudoku.
    //Time: O(9 * 9); Space: O(1 + 9 + 3)
    public boolean isValidSudoku_4(char[][] board) {
        int[] column = new int[9];
        int[] box = new int[3];

        for (int i = 0; i < board.length; i++) {
            int row = 0;
            if (i % 3 == 0) box = new int[3];
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') continue;

                int b = 1 << (board[i][j] - '0');

                if (row == (row | b)) return false;
                else row = row | b;

                if ((column[j] | b) == column[j]) return false;
                else column[j] = column[j] | b;

                int boxIdx = j / 3;
                if ((box[boxIdx] | b) == box[boxIdx]) return false;
                else box[boxIdx] = box[boxIdx] | b;
            }
        }
        return true;
    }

    //3. One Set
    //Runtime: 4 ms, faster than 80.88% of Java online submissions for Valid Sudoku.
    //Memory Usage: 46.6 MB, less than 59.29% of Java online submissions for Valid Sudoku.
    //Time: O(9 * 9); Space: O(9 * 9 * 3)
    public boolean isValidSudoku_3(char[][] board) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < board.length; i++) {
            int bi = i <= 2 ? 0 : i <= 5 ? 1: 2;
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] =='.') continue;
                int n = board[i][j] - '0';
                int bj = j <= 2 ? 0 : j <= 5 ? 1: 2;
                if (!set.add(n * 10 + i)                        //row
                        || !set.add(n * 100 + j * 10)           //column
                        || !set.add(n * 1000 + bi * 10 + bj))   //box
                    return false;
            }
        }
        return true;
    }

    //2. Set
    //Runtime: 4 ms, faster than 80.88% of Java online submissions for Valid Sudoku.
    //Memory Usage: 46.6 MB, less than 59.29% of Java online submissions for Valid Sudoku.
    //Time: O(9 + 81); Space: O(30 * 10)
    public boolean isValidSudoku_2(char[][] board) {
        Set<Integer>[] set = new HashSet[10];
        for (int i = 1; i <= 9; i++)
            set[i] = new HashSet<>();

        for (int i = 0; i < board.length; i++) {
            int bi = i <= 2 ? 0 : i <= 5 ? 1: 2;
            bi = bi * 10 + 100;
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] =='.') continue;
                int n = board[i][j] - '0';
                int bj = j <= 2 ? 0 : j <= 5 ? 1: 2;
                if (!set[n].add(i) || !set[n].add(j + 10) || !set[n].add(bi + bj))
                    return false;
            }
        }
        return true;
    }

    //1. fix-length Array
    //Runtime: 3 ms, faster than 89.41% of Java online submissions for Valid Sudoku.
    //Memory Usage: 46.4 MB, less than 62.29% of Java online submissions for Valid Sudoku.
    //Time: O(81); Space: O(100 + 30 + 10)
    public boolean isValidSudoku_1(char[][] board) {

        int[][] columns = new int[10][10];
        int[] box1 = new int[10];
        int[] box2 = new int[10];
        int[] box3 = new int[10];

        for (int i = 0; i < board.length; i++) {

            int[] row = new int[10];
            if (i % 3 == 0){
                box1 = new int[10];
                box2 = new int[10];
                box3 = new int[10];
            }

            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] =='.') continue;
                int n = board[i][j] - '0';

                if (row[n] == 0) row[n] = n;
                else return false;

                if (columns[j][n] == 0) columns[j][n] = n;
                else return false;

                int[] cell = j < 3 ? box1 : (j < 6 ? box2 : box3);
                if (cell[n] == 0) cell[n] = n;
                else return false;
            }
        }
        return true;
    }
}
