package LeetCode;


import java.util.*;

import static java.time.LocalTime.now;

/**
 * You are given an n x n integer matrix board where the cells are labeled from 1 to n2 in a Boustrophedon style starting from the bottom left of the board (i.e. board[n - 1][0]) and alternating direction each row.
 *
 * You start on square 1 of the board. In each move, starting from square curr, do the following:
 *
 * Choose a destination square next with a label in the range [curr + 1, min(curr + 6, n2)].
 * This choice simulates the result of a standard 6-sided die roll: i.e., there are always at most 6 destinations, regardless of the size of the board.
 * If next has a snake or ladder, you must move to the destination of that snake or ladder. Otherwise, you move to next.
 * The game ends when you reach the square n2.
 * A board square on row r and column c has a snake or ladder if board[r][c] != -1. The destination of that snake or ladder is board[r][c]. Squares 1 and n2 do not have a snake or ladder.
 *
 * Note that you only take a snake or ladder at most once per move. If the destination to a snake or ladder is the start of another snake or ladder, you do not follow the subsequent snake or ladder.
 *
 * For example, suppose the board is [[-1,4],[-1,3]], and on the first move, your destination square is 2. You follow the ladder to square 3, but do not follow the subsequent ladder to 4.
 * Return the least number of moves required to reach the square n2. If it is not possible to reach the square, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,35,-1,-1,13,-1],[-1,-1,-1,-1,-1,-1],[-1,15,-1,-1,-1,-1]]
 * Output: 4
 * Explanation:
 * In the beginning, you start at square 1 (at row 5, column 0).
 * You decide to move to square 2 and must take the ladder to square 15.
 * You then decide to move to square 17 and must take the snake to square 13.
 * You then decide to move to square 14 and must take the ladder to square 35.
 * You then decide to move to square 36, ending the game.
 * This is the lowest possible number of moves to reach the last square, so return 4.
 * Example 2:
 *
 * Input: board = [[-1,-1],[-1,3]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * n == board.length == board[i].length
 * 2 <= n <= 20
 * grid[i][j] is either -1 or in the range [1, n2].
 * The squares labeled 1 and n2 do not have any ladders or snakes.
 */

public class N909MSnakesandLadders {

    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");

//        doRun(new int[]{3, 5}, 6, 18);
//        doRun(new int[]{5, 5}, 6, 6);
//        doRun(new int[]{5, 1}, 6, 2);
//
//        doRun(new int[]{0, 0}, 6, 36);
//        doRun(new int[]{4, 3}, 6, 9);
//        doRun(new int[]{4, 4}, 6, 8);
//        doRun(new int[]{5, 0}, 6, 1);
//
//        doRun2(8, 6, new int[]{4, 4});
//
//        doRun2(1, 6, new int[]{5, 0});
//        doRun2(2, 6, new int[]{5, 1});
//        doRun2(6, 6, new int[]{5, 5});
//        doRun2(18, 6, new int[]{3, 5});
//        doRun2(17, 6, new int[]{3, 4});

        doRun( 1, new int[][]{{-1, -1}, {-1,3}});
        doRun( 4, new int[][]{{-1,-1,-1,-1,-1,-1}, {-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},
                {-1,35,-1,-1,13,-1},{-1,-1,-1,-1,-1,-1},{-1,15,-1,-1,-1,-1}});

        System.out.println(now());
        System.out.println("==================");
    }
    static private void doRun(int expect, int[][] board) {
        int res = new N909MSnakesandLadders().snakesAndLadders(board);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    static private void doRun1(int[] expect, int n, int square) {
        int[] res = new N909MSnakesandLadders().getXY(n, square);
        boolean success = Arrays.equals(res, expect);
        System.out.println("["+success+"] expect:" + Arrays.toString(expect) + " res:" + Arrays.toString(res));
    }

    static private void doRun2(int expect, int n, int[] xy) {
        int res = new N909MSnakesandLadders().getSquare(n, xy);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //1.BFS
    //Runtime: 6ms 86%; Memory: 42.1MB 87%
    //Time: O(N * N); Space: O(N * N)
    public int snakesAndLadders(int[][] board) {
        int n = board.length, targetSquare = n * n;

        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[]{1, 0}); //square : step

        boolean[] seen = new boolean[targetSquare + 1];
        seen[1] = true;

        while (!deque.isEmpty()){
            int[] node = deque.pop();
            if (node[0] == targetSquare)
                return node[1];

            int step = node[1] + 1;
            for (int i = node[0] + 1; i <= Math.min(node[0] + 6, targetSquare); i++) {
                int nextSquare = i;
                int[] xy = getXY(n, nextSquare);
                if (board[xy[0]][xy[1]] != -1)
                    nextSquare = board[xy[0]][xy[1]];
                if (!seen[nextSquare]) {
                    seen[nextSquare] = true;
                    deque.add(new int[]{nextSquare, step});
                }
            }
        }
        return -1;
    }

    private int getSquare(int n , int[] xy){
        int square = (n - xy[0] - 1) * n;
        if ((n - xy[0] - 1) % 2 == 0) square += xy[1] + 1;
        else square += n - xy[1];
        return square;
    }

    private int[] getXY(int n, int square){
        int x = (square - 1) / n + 1;
        int y = (square - 1) % n;
        y = x % 2 == 1 ? y : n - 1 - y;
        return new int[]{n - x, y};
    }
}
