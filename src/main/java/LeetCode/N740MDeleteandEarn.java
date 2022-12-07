package LeetCode;


import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.time.LocalTime.now;

/**
 * ou are given an integer array nums. You want to maximize the number of points you get by performing the following operation any number of times:
 *
 * Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete every element equal to nums[i] - 1 and every element equal to nums[i] + 1.
 * Return the maximum number of points you can earn by applying the above operation some number of times.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,4,2]
 * Output: 6
 * Explanation: You can perform the following operations:
 * - Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
 * - Delete 2 to earn 2 points. nums = [].
 * You earn a total of 6 points.
 * Example 2:
 *
 * Input: nums = [2,2,3,3,3,4]
 * Output: 9
 * Explanation: You can perform the following operations:
 * - Delete a 3 to earn 3 points. All 2's and 4's are also deleted. nums = [3,3].
 * - Delete a 3 again to earn 3 points. nums = [3].
 * - Delete a 3 once more to earn 3 points. nums = [].
 * You earn a total of 9 points.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i] <= 104
 */
public class N740MDeleteandEarn {


    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");

        doRun(37, new int[]{8,10,4,9,1,3,5,9,4,10});
        doRun(18, new int[]{1,1,1,2,4,5,5,5,6});
        doRun(7, new int[]{7});
        doRun(3, new int[]{1,1,1});
        doRun(9, new int[]{2,2,3,3,3,4});
        doRun(6, new int[]{3,4,2});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[] nums) {
        int res = new N740MDeleteandEarn().deleteAndEarn(nums);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //4.dp top-down
    //Runtime: 6ms, 87.21%; Memory: 43.5MB, 84.24%
    //Time: O(N); Space: O(N)
    public int deleteAndEarn(int[] nums) {
        int maxValue = 0;
        for (int i = 0; i < nums.length; i++)
            maxValue = Math.max(maxValue, nums[i]);

        int[] data = new int[maxValue + 1];
        for (int i = 0; i < nums.length; i++)
            data[nums[i]] += nums[i];
        return helper(data, maxValue, new HashMap<>());
    }

    public int helper(int[] data, int x, Map<Integer, Integer> memo) {
        if (x == 0) return data[0];
        if (x == 1) return Math.max(data[0], data[1]);
        if (memo.containsKey(x)) return memo.get(x);
        memo.put(x, Math.max( helper(data, x - 1, memo), helper(data, x - 2, memo) + data[x]) );
        return memo.get(x);
    }

    //3.DP bottom-up without sort
    //Runtime: 4ms, 92.68%; Memory: 44.8 MB, 78.51%
    //Time: O(N); Space: O(N)
    public int deleteAndEarn_3(int[] nums) {
        int maxValue = 0;
        for (int i = 0; i < nums.length; i++)
            maxValue = Math.max(maxValue, nums[i]);

        int[] dp = new int[maxValue + 1];
        for (int i = 0; i < nums.length; i++)
            dp[nums[i]] += nums[i];

        dp[1] = Math.max(dp[0], dp[1]);
        for (int i = 2; i < dp.length; i++)
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + dp[i]);

        return dp[maxValue];
    }

    //2.Sort + DP bottom-up without array
    //Runtime: 5 ms, 90.11%; Memory: 42.2 MB, 95.91%;
    //Time: O(N * logN); Space: O(logN)
    public int deleteAndEarn_2(int[] nums) {
        int res = 0, lastMaxSum = 0, prevSum = 0;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] - 1 > nums[i - 1]){
                lastMaxSum = prevSum;
                prevSum = 0;
            }

            int sum = lastMaxSum + nums[i];
            while (i + 1 < nums.length && nums[i + 1] == nums[i]) sum += nums[i++];
            sum = Math.max(sum, prevSum);

            if (prevSum != 0) lastMaxSum = prevSum;
            prevSum = sum;

            res = Math.max(res, sum);
        }
        return res;
    }

    //1.Sort + dp bottom-up
    //Runtime: 18 ms, 49.24%; Memory: 47.9 MB, 44.13%;
    //Time: O(N * logN); Space: O(logN)
    public int deleteAndEarn_1(int[] nums) {
        int res = 0;
        int[] dp = new int[10_001];
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            int idx = i;
            while (idx + 1 < nums.length && nums[idx + 1] == nums[i]) idx++;
            int sum = nums[i] * (idx - i + 1);

            int x = nums[i] - 2;
            while (x > 0 && dp[x] == 0) x--;
            if (x >= 0) sum += dp[x];

            dp[nums[i]] = Math.max(sum, dp[nums[i] - 1]);
            res = Math.max(res, dp[nums[i]]);
            i = idx;
        }
        return res;
    }
}
