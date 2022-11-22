package LeetCode;


import java.util.*;

import static java.time.LocalTime.now;

/**
 * Given an m x n binary grid grid where each 1 marks the home of one friend, return the minimal total travel distance.
 *
 * The total travel distance is the sum of the distances between the houses of the friends and the meeting point.
 *
 * The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,0,0,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 * Output: 6
 * Explanation: Given three friends living at (0,0), (0,4), and (2,2).
 * The point (0,2) is an ideal meeting point, as the total travel distance of 2 + 2 + 2 = 6 is minimal.
 * So return 6.
 * Example 2:
 *
 * Input: grid = [[1,1]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * grid[i][j] is either 0 or 1.
 * There will be at least two friends in the grid.
 */
public class N296HBestMeetingPoint {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        data2 = new int[][]{{1,1,1,0},{0,0,1,0},{0,1,0,0},{0,0,0,1},{0,1,1,0},{1,1,0,0},{0,0,1,0},{1,0,0,1},{1,0,0,0},{0,0,0,1},{0,0,0,0},{1,0,1,0},{0,0,0,0},{0,0,0,1},{0,0,0,0},{1,1,1,0},{0,0,0,0},{0,0,0,0},{1,0,0,0},{0,1,1,0}};
        doRun(145, data2);

        data2 = new int[][]{{1,0,0,0,1}, {0,0,0,0,0},{0,0,1,0,0}};
        doRun(6, data2);
        data2 = new int[][]{{1,1}};
        doRun(1, data2);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[][] grid) {
        int res = new N296HBestMeetingPoint().minTotalDistance(grid);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //4.count
    //Runtime: 2 ms, faster than 99.39% of Java online submissions for Best Meeting Point.
    //Memory Usage: 43.8 MB, less than 91.10% of Java online submissions for Best Meeting Point.
    //Time: O(M * N + 2M + 2N); Space: O(M + N)
    //Time: O(M * N); Space: O(M + N)
    public int minTotalDistance(int[][] grid) {
        //Space: O(M + N)
        int[] rowList = new int[grid.length];
        int[] colList = new int[grid[0].length];

        //Time: O(M * N)
        int sum = 0;
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    rowList[i]++; colList[j]++;
                    sum++;
                }
            }
        }
        //Time: (M + N)
        return distance1D4(rowList, sum) + distance1D4(colList, sum);
    }

    //Time: O(N)
    private int distance1D4(int[] points, int sum){
        int idx = 0;
        int currSum = points[0];
        while (idx < points.length && currSum < (sum / 2 + 1))
            currSum += points[++idx];

        int distance = 0;
        for (int i = 0; i < points.length; i++)
            distance += points[i] * Math.abs(i - idx);
        return distance;
    }

    //3.without sort + two points
    //Runtime: 8 ms, faster than 85.37% of Java online submissions for Best Meeting Point.
    //Memory Usage: 49.4 MB, less than 36.22% of Java online submissions for Best Meeting Point.
    //Time: O(M*N); Space: O(M*N)
    public int minTotalDistance_3(int[][] grid) {
        //Space: O(2*M*N)
        List<Integer> rowList = new ArrayList<>();
        List<Integer> colList = new ArrayList<>();

        //Time: O(M*N)
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                if (grid[i][j] == 1) rowList.add(i);

        for (int j = 0; j < grid[0].length; j++)
            for (int i = 0; i < grid.length; i++)
                if (grid[i][j] == 1) colList.add(j);

        return distance1D3(rowList) + distance1D3(colList);
    }
    //Time: (M*N/2)
    private int distance1D3(List<Integer> points) {
        int distance = 0;
        int i = 0, j = points.size() - 1;
        while (i < j)
            distance += points.get(j--) - points.get(i++);
        return distance;
    }

    //2.sort
    //Runtime: 38 ms, faster than 19.63% of Java online submissions for Best Meeting Point.
    //Memory Usage: 49.4 MB, less than 43.66% of Java online submissions for Best Meeting Point.
    //Time: O(M*N + M*N * log(M*N) + 2*M*N); Space: O(M*N + log(M*N))
    //Time: O(M*N * logM*N); Space: O(M*N)
    public int minTotalDistance_2(int[][] grid) {
        //Space: O(2*M*N)
        List<Integer> rowList = new ArrayList<>();
        List<Integer> colList = new ArrayList<>();
        //Time: O(M*N)
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                if (grid[i][j] == 1) {
                    rowList.add(i); colList.add(j);
                }

        int row = rowList.get(rowList.size() / 2);
        //sort
        //Time: O(M*N log(M*N)); Space: O(log(M*N))
        Collections.sort(colList);
        int column = colList.get(colList.size() / 2);
        //Time: (2*M*N)
        return distance1D2(rowList, row) + distance1D2(colList, column);
    }
    //Time: (M*N)
    private int distance1D2(List<Integer> points, int x){
        int distance = 0;
        for (int p : points) distance += Math.abs(p - x);
        return distance;
    }


    //1.BFS
    //TLE
    public int minTotalDistance_1(int[][] grid) {

        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                if (grid[i][j] == 1) list.add(new int[]{i, j});

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a->a[2]));
        for (int[] xy : list)
            queue.add(new int[]{xy[0], xy[1], manhattanDistance(xy[0], xy[1], list)});

        int res = Integer.MAX_VALUE;
        while(!queue.isEmpty()){

            int[] node = queue.poll();
            grid[node[0]][node[1]] = node[2];
            res = Math.min(res, node[2]);
            boolean changed = false;

            if (node[0] - 1 >= 0 && grid[node[0] - 1 ][node[1]] == 0) {
                changed = true;
                int distance = manhattanDistance(node[0] - 1, node[1], list);
                res = Math.min(res, distance);
                queue.add(new int[]{node[0] - 1, node[1], distance});
            }

            if (node[0] + 1 < grid.length && grid[node[0] + 1 ][node[1]] == 0) {
                changed = true;
                int distance = manhattanDistance(node[0] + 1, node[1], list);
                res = Math.min(res, distance);
                queue.add(new int[]{node[0] + 1, node[1], distance});
            }

            if (node[1] - 1 >= 0 && grid[node[0]][node[1] - 1] == 0) {
                changed = true;
                int distance = manhattanDistance(node[0], node[1] - 1, list);
                res = Math.min(res, distance);
                queue.add(new int[]{node[0], node[1] - 1, distance});
            }

            if (node[1] + 1 < grid[0].length && grid[node[0]][node[1] + 1] == 0) {
                changed = true;
                int distance = manhattanDistance(node[0], node[1] + 1, list);
                res = Math.min(res, distance);
                queue.add(new int[]{node[0], node[1] + 1, distance});
            }
            if (!changed) break;
        }
        return res;
    }

    //Time：worst case：O(M*N)
    private int manhattanDistance(int i, int j, List<int[]> list){
        int distance = 0;
        for (int[] xy : list) distance += Math.abs(xy[0] - i) + Math.abs(xy[1] - j);
        return distance;
    }
}
