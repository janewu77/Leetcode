package LeetCode;


import java.util.*;

/**
 * You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls (represented as '+'). You are also given the entrance of the maze, where entrance = [entrancerow, entrancecol] denotes the row and column of the cell you are initially standing at.
 *
 * In one step, you can move one cell up, down, left, or right. You cannot step into a cell with a wall, and you cannot step outside the maze. Your goal is to find the nearest exit from the entrance. An exit is defined as an empty cell that is at the border of the maze. The entrance does not count as an exit.
 *
 * Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: maze = [["+","+",".","+"],[".",".",".","+"],["+","+","+","."]], entrance = [1,2]
 * Output: 1
 * Explanation: There are 3 exits in this maze at [1,0], [0,2], and [2,3].
 * Initially, you are at the entrance cell [1,2].
 * - You can reach [1,0] by moving 2 steps left.
 * - You can reach [0,2] by moving 1 step up.
 * It is impossible to reach [2,3] from the entrance.
 * Thus, the nearest exit is [0,2], which is 1 step away.
 * Example 2:
 *
 *
 * Input: maze = [["+","+","+"],[".",".","."],["+","+","+"]], entrance = [1,0]
 * Output: 2
 * Explanation: There is 1 exit in this maze at [1,2].
 * [1,0] does not count as an exit since it is the entrance cell.
 * Initially, you are at the entrance cell [1,0].
 * - You can reach [1,2] by moving 2 steps right.
 * Thus, the nearest exit is [1,2], which is 2 steps away.
 * Example 3:
 *
 *
 * Input: maze = [[".","+"]], entrance = [0,0]
 * Output: -1
 * Explanation: There are no exits in this maze.
 *
 *
 * Constraints:
 *
 * maze.length == m
 * maze[i].length == n
 * 1 <= m, n <= 100
 * maze[i][j] is either '.' or '+'.
 * entrance.length == 2
 * 0 <= entrancerow < m
 * 0 <= entrancecol < n
 * entrance will always be an empty cell.
 */
public class N1926MNearestExitfromEntranceinMaze {


    //1.BFS
    //Runtime: 9 ms, faster than 82.02% of Java online submissions for Nearest Exit from Entrance in Maze.
    //Memory Usage: 71.6 MB, less than 50.52% of Java online submissions for Nearest Exit from Entrance in Maze.
    //Time: O(N); Space: O(N);
    //let N be the number of cells in maze
    public int nearestExit(char[][] maze, int[] entrance) {

        int[][] shifts = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        maze[entrance[0]][entrance[1]] = '+';

        //Space: O(N)
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{entrance[0], entrance[1], 0});

        //Time: O(N);
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            if (node[0] == 0 || node[1] == 0 || node[0] == maze.length - 1 || node[1] == maze[0].length - 1)
                if (node[2] != 0) return node[2];

            //up down left right
            for(int[] shift : shifts) {
                int x = node[0] + shift[0], y = node[1] + shift[1];
                if (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == '.'){
                    maze[x][y] = '+';
                    queue.add(new int[]{x, y, node[2] + 1});
                }
            }
        }
        return -1;
    }
}
