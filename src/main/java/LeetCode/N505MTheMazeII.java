package LeetCode;


import java.util.*;

import static java.time.LocalTime.now;

/**
 * There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1).
 * The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop
 * rolling until hitting a wall. When the ball stops, it could choose the next direction.
 *
 * Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol]
 * and destination = [destinationrow, destinationcol], return the shortest distance for the ball to stop
 * at the destination. If the ball cannot stop at destination, return -1.
 *
 * The distance is the number of empty spaces traveled by the ball from the start position (excluded)
 * to the destination (included).
 *
 * You may assume that the borders of the maze are all walls (see examples).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
 * Output: 12
 * Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
 * The length of the path is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.
 * Example 2:
 *
 *
 * Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [3,2]
 * Output: -1
 * Explanation: There is no way for the ball to stop at the destination. Notice that you can pass through
 * the destination but you cannot stop there.
 * Example 3:
 *
 * Input: maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], start = [4,3], destination = [0,1]
 * Output: -1
 *
 *
 * Constraints:
 *
 * m == maze.length
 * n == maze[i].length
 * 1 <= m, n <= 100
 * maze[i][j] is 0 or 1.
 * start.length == 2
 * destination.length == 2
 * 0 <= startrow, destinationrow <= m
 * 0 <= startcol, destinationcol <= n
 * Both the ball and the destination exist in an empty space, and they will not be in the same position initially.
 * The maze contains at least 2 empty spaces.
 */

/**
 * M - [time: 120+
 *
 */
public class N505MTheMazeII {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        data2 = new int[][]{{0,0,0,0,0}, {1,1,0,0,1}, {0,0,0,0,0}, {0,1,0,0,1}, {0,1,0,0,0}};
        doRun_demo(-1, data2, new int[]{4,3}, new int[]{0,1});

        data2 = new int[][]{{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
        doRun_demo(-1, data2, new int[]{0,4}, new int[]{3,2});

        data2 = new int[][]{{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
        doRun_demo(12, data2, new int[]{0,4}, new int[]{4,4});

        data2 = new int[][]{{0,0,0,0,1,0,0},{0,0,1,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,1},{0,1,0,0,0,0,0},
                {0,0,0,1,0,0,0},{0,0,0,0,0,0,0},{0,0,1,0,0,0,1},{0,0,0,0,1,0,0}};
        doRun_demo(26, data2, new int[]{0,0}, new int[]{8,6});



        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun_demo(int expect, int[][] maze, int[] start, int[] destination) {
        int res = new N505MTheMazeII().shortestDistance(maze, start, destination);
////        String res = comm.toString(res1);
//        String res = Arrays.stream(res1).mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(","));
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        //int[][] res1
        //        sb.append("[");
        //        for(int i = 0; i<res1.length; i++) {
        //            String str = Arrays.toString(res1[i]);
        //            sb.append(str).append(",");
        //        }
        //        if (sb.length() > 1) sb.deleteCharAt(sb.length()-1);
        //        sb.append("]");
    }


    //Runtime: 9 ms, faster than 89.13% of Java online submissions for The Maze II.
    //Memory Usage: 54.4 MB, less than 44.82% of Java online submissions for The Maze II.
    //dijkstra
    public int shortestDistance_dijkstra(int[][] maze, int[] start, int[] dest) {
        int[][] distance = new int[maze.length][maze[0].length];
        for (int[] row: distance)
            Arrays.fill(row, Integer.MAX_VALUE);
        distance[start[0]][start[1]] = 0;
        dijkstra(maze, start, distance);
        return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
    }

    public void dijkstra(int[][] maze, int[] start, int[][] distance) {
        int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        queue.offer(new int[]{start[0], start[1], 0});
        while (!queue.isEmpty()) {
            int[] s = queue.poll();
            if (distance[s[0]][s[1]] < s[2])
                continue;
            for (int[] dir : dirs) {
                int x = s[0] + dir[0];
                int y = s[1] + dir[1];
                int count = 0;
                while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    count++;
                }
                if (distance[s[0]][s[1]] + count < distance[x - dir[0]][y - dir[1]]) {
                    distance[x - dir[0]][y - dir[1]] = distance[s[0]][s[1]] + count;
                    queue.offer(new int[]{x - dir[0], y - dir[1], distance[x - dir[0]][y - dir[1]]});
                }
            }
        }

    }


    //////////////////////////////////////////////////////////////////////////////////////////
    //Runtime: 5 ms, faster than 100.00% of Java online submissions for The Maze II.
    //Memory Usage: 42.7 MB, less than 96.14% of Java online submissions for The Maze II.
    //BFS
    //Time: O(M*N*max(M,N); Space: O(M*N)
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        for (int i = 0; i < maze.length; i++)
            for (int j = 0; j < maze[0].length; j++)
                maze[i][j] = -maze[i][j];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start[0], start[1], 0}); //x,y, direction 1 up 2left 3right 4down

        while (!queue.isEmpty()){
            int[] node = queue.poll();
            int steps = maze[node[0]][node[1]];

            for (int direction = 1; direction <= 4; direction++){
                if (node[2] + direction == 5 || direction == node[2]) continue;

                int i = node[0], j = node[1];
                int[] stop = new int[]{i, j};
                int currSteps = -1;

                //rolling
                while (i >= 0 && i < maze.length
                        && j >= 0 && j < maze[0].length
                        && maze[i][j] != -1) {
                    stop[0] = i; stop[1] = j;
                    if (direction == 1 || direction == 4)
                        i = direction == 1 ? i - 1: i + 1;
                    else j = direction == 2 ? j - 1: j + 1;
                    currSteps++;
                }

                if (currSteps > 0 && (steps + currSteps < maze[stop[0]][stop[1]] || maze[stop[0]][stop[1]] == 0)) {
                    maze[stop[0]][stop[1]] = currSteps + steps;
                    if (!Arrays.equals(destination, stop))
                        queue.offer(new int[]{stop[0], stop[1], direction});
                }
            }//End for
        }//End while
        return maze[destination[0]][destination[1]] == 0 ? -1 : maze[destination[0]][destination[1]];
    }


    /////////////////////////////////////////////////////////////////////////////////
    //Runtime: 94 ms, faster than 16.91% of Java online submissions for The Maze II.
    //Memory Usage: 42.5 MB, less than 97.56% of Java online submissions for The Maze II.
    //DFS + recursion
    //Time: O(M*N*max(M,N); Space: O(M*N)
    public int shortestDistance_dfs(int[][] maze, int[] start, int[] destination) {
        int[][] res = new int[maze.length][maze[0].length]; //performance is better than HashMap;
        for (int i = 0; i< maze.length; i++)
            Arrays.fill(res[i], Integer.MAX_VALUE);
        help2_dfs(maze, start, destination, res, 0, 0);
        return res[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : res[destination[0]][destination[1]];
    }

    private void help2_dfs(int[][] maze, int[] start, int[] destination,
                           int[][] seen, int steps, int inDirections) {

        int lastStep = seen[start[0]][start[1]];
        if (Arrays.equals(start, destination)) {
            seen[start[0]][start[1]] = Math.min(steps, lastStep);
            return;
        }

        if (steps >= lastStep) return;
        seen[start[0]][start[1]] = steps;

        //four directions:1 up 2 left 3 right 4 down
        for (int direction = 1; direction <= 4; direction++) {
            if (inDirections + direction == 5 || direction == inDirections) continue;

            int i = start[0], j = start[1];
            int[] stop = new int[]{i, j};
            int currSteps = -1;

            //rolling
            while (i >= 0 && i < maze.length && j >= 0 && j < maze[0].length &&
                    maze[i][j] == 0) {
                stop[0] = i; stop[1] = j;
                if (direction == 1 || direction == 4)
                    i = direction == 1 ? i - 1: i + 1;
                else j = direction == 2 ? j - 1: j + 1;
                currSteps++;
            }

            if (currSteps > 0)
                help2_dfs(maze, stop, destination, seen, steps + currSteps, direction);
        }
    }



}
