package Contest;


import javafx.util.Pair;

import java.util.*;

import static java.time.LocalTime.now;

/**
 * There exists an infinitely large grid. You are currently at point (1, 1), and you need to reach the point (targetX, targetY) using a finite number of steps.
 *
 * In one step, you can move from point (x, y) to any one of the following points:
 *
 * (x, y - x)
 * (x - y, y)
 * (2 * x, y)
 * (x, 2 * y)
 * Given two integers targetX and targetY representing the X-coordinate and Y-coordinate of your final position, return true if you can reach the point from (1, 1) using some number of steps, and false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: targetX = 6, targetY = 9
 * Output: false
 * Explanation: It is impossible to reach (6,9) from (1,1) using any sequence of moves, so false is returned.
 * Example 2:
 *
 * Input: targetX = 4, targetY = 7
 * Output: true
 * Explanation: You can follow the path (1,1) -> (1,2) -> (1,4) -> (1,8) -> (1,7) -> (2,7) -> (4,7).
 *
 *
 * Constraints:
 *
 * 1 <= targetX, targetY <= 109
 */
public class N2543HCheckifPointIsReachable {

    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");

        doRun(true, 3, 7);
        doRun(true, 4, 7);
        doRun(false, 6, 9);
        doRun(true, 536870912, 536870912);
        doRun(true, 671088640, 939524096);
        doRun(false, 339513622, 655934895);

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(boolean expect, int targetX, int targetY) {
        boolean res = new N2543HCheckifPointIsReachable().isReachable(targetX, targetY);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //3.recursion
    //Runtime: 0ms 100%; Memory: 41MB 50%
    public boolean isReachable(int targetX, int targetY) {
        if (targetX == 0 || targetY == 0) return false;

        while ((targetX & 1) == 0) targetX >>= 1;
        if (targetX == 1) return true;

        while ((targetY & 1) == 0) targetY >>= 1;
        if (targetY == 1) return true;

        int min = Math.min(targetX, targetY);
        int max = Math.max(targetX, targetY);
        return isReachable(min, max - min);
    }

    //2.iteration
    //Runtime: 0ms 100%; Memory: 38.9MB 100%
    public boolean isReachable_2(int targetX, int targetY) {
        if (targetX == 0 || targetY == 0) return false;

        while ((targetX & 1) == 0) targetX >>= 1;
        if (targetX == 1) return true;

        while ((targetY & 1) == 0) targetY >>= 1;
        if (targetY == 1) return true;

        int min = Math.min(targetX, targetY);
        int max = Math.max(targetX, targetY);

        while (true) {
            if (min == 0) return false;
            if (min == 1) return true;
            int tmp = max - min;
            while (tmp != 0 && (tmp & 1) == 0) tmp >>= 1;
            max = Math.max(min, tmp);
            min = Math.min(min, tmp);
        }
    }


    //1.PriorityQueue
    //TLE
    public boolean isReachable_1(int targetX, int targetY) {
        if (targetX == 0 || targetY == 0) return false;
        PriorityQueue<Pair<Integer, Integer>> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.getKey()));
        Set<Pair<Integer, Integer>> seen = new HashSet<>();

        while (targetX != 0 && (targetX & 1) == 0) targetX >>= 1;
        if (targetX == 1) return true;

        while (targetY != 0 && (targetY & 1) == 0) targetY >>= 1;
        if (targetY == 1) return true;

        Pair<Integer, Integer> target = new Pair<>(Math.min(targetX, targetY), Math.max(targetX, targetY));
        queue.add(target);
        seen.add(target);

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> val = queue.poll();
            int sum = val.getKey() + val.getValue();
            while ((sum & 1) == 0) sum >>= 1;
            if (sum == 1) return true;

            if (sum < val.getValue()) {
                Pair<Integer, Integer> tmp1 = new Pair<>(Math.min(val.getKey(), sum), Math.max(val.getKey(), sum));
                if (seen.add(tmp1)) queue.add(tmp1);
                Pair<Integer, Integer> tmp2 = new Pair<>(Math.min(val.getValue(), sum), Math.max(val.getValue(), sum));
                if (seen.add(tmp2)) queue.add(tmp2);
            }
        }
        return false;
    }
}
