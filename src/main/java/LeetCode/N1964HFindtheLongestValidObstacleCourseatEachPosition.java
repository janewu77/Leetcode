package LeetCode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

import static java.time.LocalTime.now;

/**
 * You want to build some obstacle courses. You are given a 0-indexed integer array obstacles of length n, where obstacles[i] describes the height of the ith obstacle.
 *
 * For every index i between 0 and n - 1 (inclusive), find the length of the longest obstacle course in obstacles such that:
 *
 * You choose any number of obstacles between 0 and i inclusive.
 * You must include the ith obstacle in the course.
 * You must put the chosen obstacles in the same order as they appear in obstacles.
 * Every obstacle (except the first) is taller than or the same height as the obstacle immediately before it.
 * Return an array ans of length n, where ans[i] is the length of the longest obstacle course for index i as described above.
 *
 *
 *
 * Example 1:
 *
 * Input: obstacles = [1,2,3,2]
 * Output: [1,2,3,3]
 * Explanation: The longest valid obstacle course at each position is:
 * - i = 0: [1], [1] has length 1.
 * - i = 1: [1,2], [1,2] has length 2.
 * - i = 2: [1,2,3], [1,2,3] has length 3.
 * - i = 3: [1,2,3,2], [1,2,2] has length 3.
 * Example 2:
 *
 * Input: obstacles = [2,2,1]
 * Output: [1,2,1]
 * Explanation: The longest valid obstacle course at each position is:
 * - i = 0: [2], [2] has length 1.
 * - i = 1: [2,2], [2,2] has length 2.
 * - i = 2: [2,2,1], [1] has length 1.
 * Example 3:
 *
 * Input: obstacles = [3,1,5,6,4,2]
 * Output: [1,1,2,3,2,2]
 * Explanation: The longest valid obstacle course at each position is:
 * - i = 0: [3], [3] has length 1.
 * - i = 1: [3,1], [1] has length 1.
 * - i = 2: [3,1,5], [3,5] has length 2. [1,5] is also valid.
 * - i = 3: [3,1,5,6], [3,5,6] has length 3. [1,5,6] is also valid.
 * - i = 4: [3,1,5,6,4], [3,4] has length 2. [1,4] is also valid.
 * - i = 5: [3,1,5,6,4,2], [1,2] has length 2.
 *
 *
 * Constraints:
 *
 * n == obstacles.length
 * 1 <= n <= 105
 * 1 <= obstacles[i] <= 107
 */
public class N1964HFindtheLongestValidObstacleCourseatEachPosition {

    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");
        doRun(new int[]{1,2,3,3}, new int[]{1,2,3,2});
        doRun(new int[]{1,1,2,3,2,3,4,5,3,5}, new int[]{5,1,5,5,1,3,4,5,1,4});
        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int[] expect, int[] n) {
        int[] res = new N1964HFindtheLongestValidObstacleCourseatEachPosition().longestObstacleCourseAtEachPosition(n);
        boolean success = Arrays.equals(res, expect);
        System.out.println("["+success+"] expect:" + Arrays.toString(expect) + " res:" + Arrays.toString(res));
    }


    //2. Binary Search
    //Time: 93ms 39%; Space: 55.8MB 42%
    //Time: O(N * logN); Space:O(N)
    private int binarySearch(int[] data, int target, int right) {
        if (right == 0) return 0;
        int left = 0;
        while (left < right) {
            int mid = (left + right) / 2;
            if (data[mid] <= target)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }

    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int[] res = new int[obstacles.length];
        int[] list= new int[obstacles.length];
        int listLen = 0;
        for (int i = 0; i < obstacles.length; i++) {
            int idx = binarySearch(list, obstacles[i], listLen);
            if (idx == listLen) listLen++;
            list[idx] = obstacles[i];
            res[i] = idx + 1;
        }
        return res;
    }


    //1.Brute force
    //TLE
    //Time: O(N * N); Space: O(1)
    public int[] longestObstacleCourseAtEachPosition_1(int[] obstacles) {
        int[] res = new int[obstacles.length];
        res[0] = 1;
        for (int i = 1; i < obstacles.length; i++) {
            int len = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (obstacles[j] <=  obstacles[i])
                    len = Math.max(len, res[j]);
            }
            res[i] = len + 1;
        }
        return res;
    }
}






