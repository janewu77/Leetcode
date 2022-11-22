package LeetCode;

import javafx.util.Pair;
import java.util.*;

/**
 * Given an array of points where points[i] = [xi, yi]
 * represents a point on the X-Y plane,
 * return the maximum number of points that lie on the same straight line.
 *
 * Example 1:
 * Input: points = [[1,1],[2,2],[3,3]]
 * Output: 3
 *
 *
 * Example 2:
 * Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= points.length <= 300
 * points[i].length == 2
 * -104 <= xi, yi <= 104
 * All the points are unique.
 */

/**
 * H - [time: 60+]
 *
 * - gcd, 求最大公约数
 *
 */
public class N149HMaxPointsonaLine {

    public static void main(String[] args){

        int[][] points;

        points = new int[][]{{0,0}};
        doRun(1, points);

        points = new int[][]{{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}};
        doRun(4, points);
        points = new int[][]{{1,1},{2,2},{3,3}};
        doRun(3, points);

        points = new int[][]{{-9,-651},{-4,-4},{-8,-582},{9,591},{-3,3}};
        doRun(3, points);

        points = new int[][]{{0,0},{4,5},{7,8},{8,9},{5,6},{3,4},{1,1}};
        doRun(5, points);
    }

    private static void doRun(int expected, int[][] points){
        int res = new N149HMaxPointsonaLine().maxPoints(points);
        System.out.println("["+(expected == res)+"].expected:"+ expected+".res:"+res);
    }

    //Runtime: 22 ms, faster than 86.08% of Java online submissions for Max Points on a Line.
    //Memory Usage: 47.5 MB, less than 65.02% of Java online submissions for Max Points on a Line.
    //Time: O(N*N); Space: O(N)
    public int maxPoints(int[][] points) {
        if (points.length <= 2) return points.length;
        int res = 2;

        //Time: O(N*N)
        for (int i = 0; i < points.length - 1; i++) {
            // slope: count
            Map<Pair<Integer, Integer>, Integer> map = new HashMap<>(); //Space: O(N)
            for (int j = i + 1; j < points.length; j++) {
                Pair<Integer, Integer> pair = getSlope(points[i], points[j]);
                int c = map.getOrDefault(pair, 1) + 1;
                map.put(pair, c);
                res = Math.max(res, c);
            }//End for
        }
        return res;
    }

    static final Pair<Integer, Integer> PairZero = new Pair(0, 0);
    static final Pair<Integer, Integer> PairMAXValue = new Pair(Integer.MAX_VALUE, Integer.MAX_VALUE);
    private Pair<Integer, Integer> getSlope(int[] p1, int[] p2){
        int deltaX = p1[0] - p2[0], deltaY = p1[1] - p2[1];
        if (deltaX == 0) return PairZero;
        else if (deltaY == 0) return PairMAXValue;
        //BigInteger.valueOf(deltaX).gcd(BigInteger.valueOf(deltaY)).intValue();
        int gcd = gcd_iteration(deltaX, deltaY);
        return new Pair(deltaX / gcd, deltaY / gcd);
    }

    public static int gcd_iteration(int a, int b) {
        while (b != 0) {
            int c = a % b;
            a = b;
            b = c;
        }
        return a;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Runtime: 284 ms, faster than 5.07% of Java online submissions for Max Points on a Line.
    //Memory Usage: 117 MB, less than 5.03% of Java online submissions for Max Points on a Line.
    //Time: O(N*N*N)
    public int maxPoints_1(int[][] points) {
        if (points.length == 1) return 1;
        int res = 2;

        for (int i = 0; i < points.length - 1; i++) {
            int[] p1 = points[i];

            //delta: point 由p1出发的，各斜率上的点
            Map<int[], Set<int[]>> map = new HashMap<>();
            for (int j = i + 1; j < points.length; j++) {
                boolean isFound = false;
                for (int[] delta : map.keySet()){
                    if (onALine(delta, p1, points[j])){
                        Set<int[]> set = map.get(delta);
                        set.add(points[j]);
                        res = Math.max(res, set.size());
                        isFound = true;
                        break;
                    }
                }// End J
                if (!isFound){
                    Set<int[]> newLines = new HashSet<>();
                    newLines.add(p1);
                    newLines.add(points[j]);
                    map.put(getDelta(p1, points[j]), newLines);
                }
            }//End for
        }
        return res;
    }

    private boolean onALine(int[] s, int[] p1, int[] p2){
        int[] delta = new int[]{p2[0]-p1[0], p2[1]-p1[1]};
        if(s[0] == 0) return p1[0] == p2[0];
        if(s[1] == 0) return p1[1] == p2[1];
        return (1.0* delta[0] / s[0]) - (1.0* delta[1] / s[1]) == 0;
    }

    private int[] getDelta(int[] p1, int[] p2){
        return new int[]{p2[0]-p1[0], p2[1]-p1[1]};
    }

}
