package Contest;

import java.util.*;

import static java.time.LocalTime.now;

/**
 * There are some robots and factories on the X-axis. You are given an integer array robot where robot[i] is the position of the ith robot. You are also given a 2D integer array factory where factory[j] = [positionj, limitj] indicates that positionj is the position of the jth factory and that the jth factory can repair at most limitj robots.
 *
 * The positions of each robot are unique. The positions of each factory are also unique. Note that a robot can be in the same position as a factory initially.
 *
 * All the robots are initially broken; they keep moving in one direction. The direction could be the negative or the positive direction of the X-axis. When a robot reaches a factory that did not reach its limit, the factory repairs the robot, and it stops moving.
 *
 * At any moment, you can set the initial direction of moving for some robot. Your target is to minimize the total distance traveled by all the robots.
 *
 * Return the minimum total distance traveled by all the robots. The test cases are generated such that all the robots can be repaired.
 *
 * Note that
 *
 * All robots move at the same speed.
 * If two robots move in the same direction, they will never collide.
 * If two robots move in opposite directions and they meet at some point, they do not collide. They cross each other.
 * If a robot passes by a factory that reached its limits, it crosses it as if it does not exist.
 * If the robot moved from a position x to a position y, the distance it moved is |y - x|.
 *
 *
 * Example 1:
 *
 *
 * Input: robot = [0,4,6], factory = [[2,2],[6,2]]
 * Output: 4
 * Explanation: As shown in the figure:
 * - The first robot at position 0 moves in the positive direction. It will be repaired at the first factory.
 * - The second robot at position 4 moves in the negative direction. It will be repaired at the first factory.
 * - The third robot at position 6 will be repaired at the second factory. It does not need to move.
 * The limit of the first factory is 2, and it fixed 2 robots.
 * The limit of the second factory is 2, and it fixed 1 robot.
 * The total distance is |2 - 0| + |2 - 4| + |6 - 6| = 4. It can be shown that we cannot achieve a better total distance than 4.
 * Example 2:
 *
 *
 * Input: robot = [1,-1], factory = [[-2,1],[2,1]]
 * Output: 2
 * Explanation: As shown in the figure:
 * - The first robot at position 1 moves in the positive direction. It will be repaired at the second factory.
 * - The second robot at position -1 moves in the negative direction. It will be repaired at the first factory.
 * The limit of the first factory is 1, and it fixed 1 robot.
 * The limit of the second factory is 1, and it fixed 1 robot.
 * The total distance is |2 - 1| + |(-2) - (-1)| = 2. It can be shown that we cannot achieve a better total distance than 2.
 *
 *
 * Constraints:
 *
 * 1 <= robot.length, factory.length <= 100
 * factory[j].length == 2
 * -109 <= robot[i], positionj <= 109
 * 0 <= limitj <= robot.length
 * The input will be generated such that it is always possible to repair every robot.
 */

/**
 * H - [time: 120+
 */
//2463. Minimum Total Distance Traveled
public class N6232HMinimumTotalDistanceTraveled {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;


        doRun(6, Arrays.asList(9,11,99,101), new int[][]{{10,1}, {7,1}, {14,1}, {100,1}, {96,1}, {103,1}});

//
        List<Integer> robot = Arrays.asList(9486709,305615257,214323605,282129380,763907021,-662831631,628054452,-132239126,50488558,381544523,-656107497,810414334,421675516,-304916551,571202823,478460906,-972398628,325714139,-86452967,660743346);
        data2 = new int[][]{{-755430217,18},{382914340,2},{977509386,4},{94299927,9},{32194684,16},{-371001457,2},{-426296769,13},{-284404215,8},{-421288725,0},{-893030428,2},{291827872,17},{-766616824,8},{-730172656,17},{-722387876,1},{510570520,20},{756326049,7},{-242350340,14},{6585224,19},{-173457765,15}};
        doRun(4, robot, data2);

        doRun(4, Arrays.asList(1,-1), new int[][]{{-2,0}, {2,2}});

        doRun(2, Arrays.asList(1,-1), new int[][]{{-2,1}, {2,1}});
        doRun(4, Arrays.asList(0,4,6), new int[][]{{2,2}, {6,2}});

        //robot = [1,-1], factory = [[-2,1],[2,1]]
        System.out.println(now());
        System.out.println("==================");

    }


    static private void doRun(long expect, List<Integer> robot, int[][] factory) {
        long res = new N6232HMinimumTotalDistanceTraveled().minimumTotalDistance(robot, factory);


        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        //Collections.binarySearch()
        List<int[]> factoryList = new ArrayList<>();
        for (int i = 0; i < factory.length; i++) {
            factoryList.add(factory[i]);
        }
        return helper(robot, 0, factory, factoryList);
    }

    private long helper(List<Integer> robot, int idx, int[][] factory, List<int[]> factoryList){

        if (idx >= robot.size()) return 0;

        long res = Integer.MAX_VALUE;
        int currRobot = robot.get(idx);

        int x = Collections.binarySearch(factoryList, new int[]{currRobot, 1}, new ComparatorN4());
        //-x + 1;
        x = (x >= 0 ? x : Math.abs(x) - 1) ;

        for (int i = x; i <= x + 1 && i < factory.length; i++) {
            int[] node = factory[i];
            if (node[1] <= 0) continue;
            int currDis = Math.abs(currRobot - node[0]);
            if (currDis >= res) continue;
            node[1]--;
            res = Math.min(res, Math.abs(currRobot - node[0]) + helper(robot, idx + 1, factory, factoryList));
            node[1]++;
        }
        return res;
    }

    class ComparatorN4 implements Comparator<int[]> {
        @Override
        public int compare(int[] a, int[] b) {
            return Math.abs(a[0]) - Math.abs(b[0]) == 0 ? a[1] - b[1] : Math.abs(a[0]) - Math.abs(b[0]);
        }
    }

    //1.backtracking
    //TLE
    public long minimumTotalDistance_1(List<Integer> robot, int[][] factory) {
        return helper(robot, 0, factory);
    }

    private long helper(List<Integer> robot, int idx, int[][] factory){
        if (idx >= robot.size()) return 0;

        long res = Integer.MAX_VALUE;
        int currRobot = robot.get(idx);
        for (int i = 0; i < factory.length; i++) {
            int[] node = factory[i];
            if (node[1] <= 0) continue;
            node[1]--;
            res = Math.min(res, Math.abs(currRobot - node[0]) + helper(robot, idx + 1, factory));
            node[1]++;
        }
        return res;
    }
}


