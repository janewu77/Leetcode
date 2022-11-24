package LeetCode;

import javafx.util.Pair;

import java.io.IOException;
import java.util.*;

import static java.time.LocalTime.now;

public class N79MWordSearch {


    static public void main(String[] args) throws IOException {
        //System.out.println('z');
        System.out.println(now());
        System.out.println("==================");
        char[][] board;

        board = new char[][]{{'A'}};
        doRun(false, board, "B");
        board = new char[][]{{'A','B','C','E'},{'S','F','C','S'}, {'A','D','E','E'}};
        doRun(true, board, "ABCCED");


        board = new char[][] {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        doRun(true, board, "SEE");

        board = new char[][] {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        doRun(false, board, "ABCB");
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(boolean expect, char[][] board, String word) {
        boolean res = new N79MWordSearch().exist(board, word);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //1.DFS backtracking
    //Runtime: 2 ms, faster than 99.76% of Java online submissions for Word Search.
    //Memory Usage: 41.9 MB, less than 68.68% of Java online submissions for Word Search.
    //Time: O(N * (3 ^ L)); Space: O(L)
    //let N be the number of cells in the board.
    //Let L be the length of the word
    public boolean exist(char[][] board, String word) {

        int flag = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) flag++;
                else if (board[i][j] == word.charAt(word.length() - 1)) flag--;
            }
        }
        //reduce the choices of the first letter of the word in the board
        if (flag > 0) word = new StringBuilder(word).reverse().toString();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (helper_backtrack(board, word, 0, i, j)) return true;
            }
        }
        return false;
    }

    int[][] pos = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    //Time: O(L); Space: O(L)
    //Let L be the length of word
    private boolean helper_backtrack(char[][] board, String word, int idx, int i, int j) {

        if (word.charAt(idx) != board[i][j]) return false;
        if (idx == word.length() - 1) return true;
        char c = board[i][j];
        board[i][j] = '#'; // ^ 256
        for (int[] p : pos) {
            int x = i + p[0], y = j + p[1];
            if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) continue;

            if (idx + 1 < word.length() && board[x][y] == word.charAt(idx + 1))
                if (helper_backtrack(board, word, idx + 1, x, y)) return true;

        }
        board[i][j] = c;
        return false;
    }

}
