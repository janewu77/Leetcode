package LeetCode;


import java.util.LinkedList;
import java.util.Queue;

import static java.time.LocalTime.now;

/**
 * Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.
 *
 * Notice that you can not jump outside of the array at any time.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [4,2,3,0,3,1,2], start = 5
 * Output: true
 * Explanation:
 * All possible ways to reach at index 3 with value 0 are:
 * index 5 -> index 4 -> index 1 -> index 3
 * index 5 -> index 6 -> index 4 -> index 1 -> index 3
 * Example 2:
 *
 * Input: arr = [4,2,3,0,3,1,2], start = 0
 * Output: true
 * Explanation:
 * One possible way to reach at index 3 with value 0 is:
 * index 0 -> index 4 -> index 1 -> index 3
 * Example 3:
 *
 * Input: arr = [3,0,2,1,2], start = 2
 * Output: false
 * Explanation: There is no way to reach at index 1 with value 0.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 5 * 104
 * 0 <= arr[i] < arr.length
 * 0 <= start < arr.length
 */
public class N1306MJumpGameIII {

    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");
        doRun(true, new int[]{4,2,3,0,3,1,2}, 5);
        System.out.println(now());
        System.out.println("==================");

    }

    static private void doRun(boolean expect, int[] arr, int start) {
        boolean res = new N1306MJumpGameIII().canReach(arr, start);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //2.BFS
    //Runtime: 10ms 21%; Memory: 49MB 83%
    //Time: O(N); Space: O(N)
    public boolean canReach_2(int[] arr, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int x = queue.poll();
            if (arr[x] < 0) continue;
            if (arr[x] == 0) return true;
            if (x + arr[x] <= arr.length - 1) queue.add(x + arr[x]);
            if (x - arr[x] >= 0) queue.add(x - arr[x]);
            arr[x]= -arr[x];
        }
        return false;
    }

    //1.DFS
    //Runtime: 3ms 97%; Memory: 53.8MB 47%
    //Time: O(N); Space: O(N)
    public boolean canReach_1(int[] arr, int start) {
        if (arr[start] == 0) return true;
        if (arr[start] == -1) return false;

        int tmp = arr[start];
        arr[start] = -1;
        return  ((start + tmp <= arr.length - 1) ? canReach(arr, start + tmp) : false) ||
                ((start - tmp >= 0) ? canReach(arr, start - tmp) : false);
    }

    public boolean canReach(int[] arr, int start) {
        return  start >= 0 && start < arr.length && arr[start] >= 0 &&
                ((arr[start] = -arr[start]) == 0
                        || canReach(arr, start + arr[start])
                        || canReach(arr, start - arr[start]) );
    }

}
