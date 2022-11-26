package LeetCode;


import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

import static java.time.LocalTime.now;

/**
 * Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous)
 * subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [3,1,2,4]
 * Output: 17
 * Explanation:
 * Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
 * Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
 * Sum is 17.
 * Example 2:
 *
 * Input: arr = [11,81,94,43,3]
 * Output: 444
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 3 * 104
 * 1 <= arr[i] <= 3 * 104
 */
public class N907MSumofSubarrayMinimums {


    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");

        doRun(444, new int[]{11,81,94,43,3});
        doRun(84, new int[]{7,5,8,5,9});
        doRun(18, new int[]{3,3,3});
        doRun(323, new int[]{19,19,62,66});
        doRun(593, new int[]{71,55,82,55});

        doRun(17, new int[]{3,1,2,4});

        System.out.println(now());
        System.out.println("==================");

        Integer xx[] = {1,3};
    }


    static private void doRun(int expect, int[] arr) {
        int res = new N907MSumofSubarrayMinimums().sumSubarrayMins(arr);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //3.DP
    //Runtime: 14 ms, faster than 99.42% of Java online submissions for Sum of Subarray Minimums.
    //Memory Usage: 64 MB, less than 78.96% of Java online submissions for Sum of Subarray Minimums.
    //Time: O(N); Space: O(N + N)
    //Time: O(N); Space: O(N)
    public int sumSubarrayMins(int[] arr) {
        final int modulo = 1_000_000_007;

        int[] stack = new int[arr.length + 1];
        int idxStack = 0;

        int[] dp = new int[arr.length];
        dp[0] = arr[0];

        long res = dp[0];
        for (int i = 1; i < arr.length; i++) {
            while (idxStack >= 0 && arr[stack[idxStack]] >= arr[i]) idxStack--;//pop
            dp[i] = idxStack < 0 ? arr[i] * (i + 1) : dp[stack[idxStack]] + arr[i] * (i - stack[idxStack]);
            res += dp[i];
            stack[++idxStack] = i;//push
        }

        return (int) (res % modulo);
    }

    //2.monotonic stack
    //Runtime: 5 ms, faster than 100.00% of Java online submissions for Sum of Subarray Minimums.
    //Memory Usage: 42.6 MB, less than 99.93% of Java online submissions for Sum of Subarray Minimums.
    //Time: O(N); Space: O(N)
    public int sumSubarrayMins_2(int[] arr) {
        final int modulo = 1_000_000_007;

        int[] stack = new int[arr.length + 1];
        int idxStack = 0;

        long res = 0;
        for (int i = 1; i <= arr.length; i++) {
            while (idxStack >= 0 && (i == arr.length || arr[stack[idxStack]] > arr[i])){
                int idx = stack[idxStack--];//pop
                int leftIdx = idxStack < 0 ? -1 : stack[idxStack];
                res += 1l * arr[idx] * (i - idx) * (idx - leftIdx);
            }
            stack[++idxStack] = i;//push
        }
        return (int) (res % modulo);
    }

    //1.two directions
    //Runtime: 8 ms, faster than 100.00% of Java online submissions for Sum of Subarray Minimums.
    //Memory Usage: 48.2 MB, less than 99.42% of Java online submissions for Sum of Subarray Minimums.
    //Time: O(N + N + N); Space: O(N + N)
    //Time: O(N); Space: O(N)
    public int sumSubarrayMins_1(int[] arr) {
        final int modulo = 1_000_000_007;
        int[] left = new int[arr.length];
        int[] right = new int[arr.length];
        right[arr.length - 1] = arr.length - 1;

        for (int i = 1; i < arr.length; i++){
            int idx = i - 1;
            while (idx >= 0 && arr[idx] > arr[i]) idx = left[idx] - 1;
            left[i] = idx + 1;

//            int j = arr.length - 1 - i;
//            idx = j + 1;
//            while (idx < arr.length && arr[j] <= arr[idx]) idx = right[idx] + 1;
//            right[j] = idx - 1;
        }

        right[arr.length - 1] = arr.length - 1;
        for (int i = arr.length - 2; i >= 0; i--){
            int idx = i + 1;
            while (idx < arr.length && arr[i] <= arr[idx]) idx = right[idx] + 1;
            right[i] = idx - 1;
        }

        //result
        long res = 0;
        for (int i = 0; i < arr.length; i++)
            res = res + 1l *  arr[i] * (i - left[i] + 1)  * (right[i] - i + 1);

        return (int) (res % modulo);
    }
}
