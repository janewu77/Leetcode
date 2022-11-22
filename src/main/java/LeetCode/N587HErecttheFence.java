package LeetCode;


import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

/**
 * You are given an array trees where trees[i] = [xi, yi] represents the location of a tree in the garden.
 *
 * You are asked to fence the entire garden using the minimum length of rope as it is expensive. The garden is well fenced only if all the trees are enclosed.
 *
 * Return the coordinates of trees that are exactly located on the fence perimeter.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: points = [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
 * Output: [[1,1],[2,0],[3,3],[2,4],[4,2]]
 * Example 2:
 *
 *
 * Input: points = [[1,2],[2,2],[4,2]]
 * Output: [[4,2],[2,2],[1,2]]
 *
 *
 * Constraints:
 *
 * 1 <= points.length <= 3000
 * points[i].length == 2
 * 0 <= xi, yi <= 100
 * All the given points are unique.
 */
public class N587HErecttheFence {



    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");

        doRun("[[1,1],[2,0],[2,4],[3,3],[4,2]]", new int[][]{{1,1}, {2,2}, {2,0}, {2,4}, {3,3}, {4,2}});
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(String expect, int[][] points) {
        int[][] res = new N587HErecttheFence().outerTrees(points);
        Arrays.sort(res, (a, b) -> a[0] != b[0] ? a[0] - b[0]: a[1] - b[1]);
        StringBuilder sb = new StringBuilder();
        for (int[] node : res) {
            if (sb.length() != 0) sb.append(",");
            else sb.append("[");
            String tmp = Arrays.stream(node).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
            sb.append("[").append(tmp).append("]");
        }
        sb.append("]");
        System.out.println("["+(expect.equals(sb.toString()))+"]expect:" + expect + " res:" + sb.toString());
    }

    //https://web.ntnu.edu.tw/~algo/ConvexHull.html

    //4.Monotone Chain + array
    //Runtime: 9 ms, faster than 98.10% of Java online submissions for Erect the Fence.
    //Memory Usage: 43.5 MB, less than 96.64% of Java online submissions for Erect the Fence.
    //Time: O(N * logN + 2N + N); Space: O(logN + N)
    //Time: O(N * logN); Space: O(N)
    public int[][] outerTrees(int[][] points) {
        Arrays.sort(points, (p, q) -> q[0] - p[0] == 0 ? q[1] - p[1] : q[0] - p[0]);

        int[][] hull = new int[points.length << 1][2];
        int idx = 0;
        //lower
        for (int i = 0; i < points.length; i++) {
            while (idx - 2 >= 0 && orientation(hull[idx - 2], hull[idx - 1], points[i]) > 0) idx--;
            hull[idx++] = points[i];
        }

        //upper
        for (int i = points.length - 1; i >= 0; i--) {
            while (idx - 2 >= 0 && orientation(hull[idx - 2], hull[idx - 1], points[i]) > 0) idx--;
            hull[idx++] = points[i];
        }

        HashSet<int[]> ret = new HashSet<>();
        for (int i = 0; i < idx; i++) ret.add(hull[i]);
        return ret.toArray(new int[ret.size()][]);
    }


    //3.Monotone Chain + stack
    //Runtime: 21 ms, faster than 93.32% of Java online submissions for Erect the Fence.
    //Memory Usage: 54.6 MB, less than 42.03% of Java online submissions for Erect the Fence.
    //Time: O(N * logN); Space: O(logN)
    public int[][] outerTrees_3(int[][] points) {
        Arrays.sort(points, (p, q) -> q[0] - p[0] == 0 ? q[1] - p[1] : q[0] - p[0]);

        Deque<int[]> hull = new LinkedList<>();
        for (int i = 0; i < points.length; i++) {
            if (hull.size() >= 2) {
                int[] top = hull.pop();
                while (hull.size() >= 1 && orientation(hull.peek(), top, points[i]) > 0) top = hull.pop();
                hull.push(top);
            }
            hull.push(points[i]);
        }
        //hull.pop();

        for (int i = points.length - 1; i >= 0; i--) {
            if (hull.size() >= 2) {
                int[] top = hull.pop();
                while (hull.size() >= 1 && orientation(hull.peek(), top, points[i]) > 0) top = hull.pop();
                hull.push(top);
            }
            hull.push(points[i]);
        }

        // remove redundant elements from the stack
        HashSet<int[]> ret = new HashSet<>(hull);
        return ret.toArray(new int[ret.size()][]);
    }

    //2.Graham Scan
    //Runtime: 33 ms, faster than 63.53% of Java online submissions for Erect the Fence.
    //Memory Usage: 54.4 MB, less than 60.60% of Java online submissions for Erect the Fence.
    //Time: O(N * logN); Space: O(logN)
    public int[][] outerTrees_2(int[][] points) {
        if (points.length <= 1) return points;
        int[] bm = bottomLeft(points);

        //Time: O(N * logN); Space: O(logN)
        Arrays.sort(points, (p, q) -> {
            double diff = orientation(bm, p, q) - orientation(bm, q, p);
            if (diff == 0) return distance(bm, p) - distance(bm, q);
            else return diff > 0 ? 1 : -1;
        });

        //reverse
        int i = points.length - 1;
        while (i >= 0 && orientation(bm, points[points.length - 1], points[i]) == 0) i--;
        for (int l = i + 1, h = points.length - 1; l < h; l++, h--) {
            int[] temp = points[l];
            points[l] = points[h];
            points[h] = temp;
        }

        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(points[0]);
        stack.push(points[1]);
        for (int j = 2; j < points.length; j++) {
            int[] top = stack.pop();
            while (orientation(stack.peek(), top, points[j]) > 0) top = stack.pop();
            stack.push(top);
            stack.push(points[j]);
        }
        return stack.toArray(new int[stack.size()][]);
    }

    public int distance(int[] p, int[] q) {
        return (p[0] - q[0]) * (p[0] - q[0]) + (p[1] - q[1]) * (p[1] - q[1]);
    }

    private static int[] bottomLeft(int[][] points) {
        int[] bottomLeft = points[0];
        for (int[] p: points)
            if (p[1] < bottomLeft[1]) bottomLeft = p;
        return bottomLeft;
    }

    //1. Jarvis Algorithm
    //TLE
    //Time: O(N * N); Space:O(N)
    public int[][] outerTrees_1(int[][] points) {
        HashSet<int[]> hull = new HashSet<> ();
        if (points.length < 4) {
            for (int[] p: points) hull.add(p);
            return hull.toArray(new int[hull.size()][]);
        }

        int left_most = 0;
        for (int i = 0; i < points.length; i++)
            if (points[i][0] < points[left_most][0])
                left_most = i;

        int p = left_most;
        do {
            int q = (p + 1) % points.length;

            for (int i = 0; i < points.length; i++) {
                if (orientation(points[p], points[i], points[q]) < 0) q = i;
            }

            for (int i = 0; i < points.length; i++) {
                if (i != p && i != q
                        && orientation(points[p], points[i], points[q]) == 0
                        && inBetween(points[p], points[i], points[q])) {
                    hull.add(points[i]);
                }
            }
            hull.add(points[q]);
            p = q;
        } while (p != left_most);

        return hull.toArray(new int[hull.size()][]);
    }

    //< 0 : counterclockwise
    public int orientation(int[] p, int[] q, int[] r) {
        return (q[1] - p[1]) * (r[0] - q[0]) - (q[0] - p[0]) * (r[1] - q[1]);
    }

    public boolean inBetween(int[] p, int[] i, int[] q) {
        boolean a = (i[0] >= p[0] && i[0] <= q[0]) || (i[0] <= p[0] && i[0] >= q[0]);
        boolean b = (i[1] >= p[1] && i[1] <= q[1]) || (i[1] <= p[1] && i[1] >= q[1]);
        return a && b;
    }



//    public int[][] outerTrees(int[][] trees) {
//
//        List<int[]>[] rows = new List[101];
//        List<int[]>[] columns = new List[101];
//
//        for (int i = 0; i<= 100; i++) {
//            rows[i] = new ArrayList<>();
//            columns[i] = new ArrayList<>();
//        }
//        int top = 0, bottom = 100, left = 100, right = 0;
//        for (int[] tree : trees){
//            rows[tree[0]].add(tree);
//            columns[tree[1]].add(tree);
//
//            top = Math.max(top, tree[0]);
//            bottom = Math.min(bottom, tree[0]);
//            left = Math.min(left, tree[1]);
//            right = Math.max(right, tree[1]);
//        }
//
//        for (int i = 0; i<= 100; i++) {
//            Collections.sort(rows[i], Comparator.comparingInt(a -> a[1]));
//            Collections.sort(columns[i], Comparator.comparingInt(a -> a[0]));
//        }
//
//        Set<int[]> set = new HashSet();
//        set.addAll(rows[top]);
//        set.addAll(rows[bottom]);
//        set.addAll(columns[left]);
//        set.addAll(columns[right]);
//
//
//
//        List<int[]> topRow = rows[top];
//        int[] topTree = topRow.get(topRow.size() - 1);
//        int[] rightTree = columns[right].get(columns[right].size() - 1);
//        Deque<int[]> stack = new ArrayDeque<>();
//        for (int i = topTree[1]; i < right; i++) {
//            int[] tree = columns[i].get(columns[i].size() - 1);
//            if (tree[0] >= rightTree[0]) {
//
//            }
//
//        }
////        for
//
//        //.addAll(topRow);
//
//
//
//        //result
//        int[][] res = new int[set.size()][2];
//        int idx = 0;
//        for(int[] tree: set) res[idx++] = tree;
//        return res;
//    }
}
