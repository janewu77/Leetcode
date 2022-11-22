package LeetCode;


import static java.time.LocalTime.now;

/**
 * Given an array nums which consists of non-negative integers and an integer m,
 * you can split the array into m non-empty continuous subarrays.
 *
 * Write an algorithm to minimize the largest sum among these m subarrays.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [7,2,5,10,8], m = 2
 * Output: 18
 * Explanation:
 * There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8],
 * where the largest sum among the two subarrays is only 18.
 * Example 2:
 *
 * Input: nums = [1,2,3,4,5], m = 2
 * Output: 9
 * Example 3:
 *
 * Input: nums = [1,4,4], m = 3
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 106
 * 1 <= m <= min(50, nums.length)
 */
public class N410HSplitArrayLargestSum {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        data = new int[]{7,2,5,10,8};
        doRun_demo(18, data, 2);

        data = new int[]{1,2,3,4,5};
        doRun_demo(9, data, 2);

        data = new int[]{1,4,4};
        doRun_demo(4, data, 3);

        data = new int[]{2,3,1,2,4,3};
        doRun_demo(4, data, 5);

        data = new int[]{2,16,14,15};
        doRun_demo(29, data, 2);

        data = new int[]{10,5,13,4,8,4,5,11,14,9,16,10,20,8};
        doRun_demo(25, data, 8);

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun_demo(long expect, int[] nums, int m) {
        int res = new N410HSplitArrayLargestSum().splitArray(nums,m);
//        String res = comm.toString(res1);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    //Runtime: 1 ms, faster than 87.73% of Java online submissions for Split Array Largest Sum.
    //Memory Usage: 40.2 MB, less than 88.08% of Java online submissions for Split Array Largest Sum.
    //Binary Search
    //Time: O(N log(S)); Space: O(1)
    //N is the lenght of array. S is the sum of integers in the array.
    public int splitArray(int[] nums, int m) {
        int leftN = 0, rightN = 0;
        for (int n: nums) {
            leftN = Math.max(leftN, n);
            rightN += n;
        }

        int res = 0;
        //Binary search
        while (leftN <= rightN) {
            int midN = (rightN + leftN) / 2;
            int count = 1, sum = 0;
            for (int num: nums){
                sum += num;
                if (sum > midN) {
                    count++;
                    sum = num;
                }
            }
            if (count <= m) {
                rightN = midN - 1;
                res = midN;
            }
            else leftN = midN + 1;
        }
        return res;
    }

    //From solution
    //Runtime: 34 ms, faster than 16.17% of Java online submissions for Split Array Largest Sum.
    //Memory Usage: 47.3 MB, less than 5.43% of Java online submissions for Split Array Largest Sum.
    //DP : bottom-up
    //Time:(N*N * M); Space:(N*M)
    public int splitArray_2(int[] nums, int m) {
        int n = nums.length;
        int[][] dp = new int[1000][51];

        // Store the prefix sum of nums array
        int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++)
            prefixSum[i + 1] = prefixSum[i] + nums[i];

        for (int subarrayCount = 1; subarrayCount <= m; subarrayCount++) {
            for (int currIndex = 0; currIndex < n; currIndex++) {
                // Base Case: If there is only one subarray left, then all of the remaining numbers
                // must go in the current subarray. So return the sum of the remaining numbers.
                if (subarrayCount == 1) {
                    dp[currIndex][subarrayCount] = prefixSum[n] - prefixSum[currIndex];
                    continue;
                }

                // Otherwise, use the recurrence relation to determine the minimum largest subarray
                // sum between currIndex and the end of the array with subarrayCount subarray remaining.
                int minimumLargestSplitSum = Integer.MAX_VALUE;
                for (int i = currIndex; i <= n - subarrayCount; i++) {
                    // Store the sum of the first subarray.
                    int firstSplitSum = prefixSum[i + 1] - prefixSum[currIndex];

                    // Find the maximum subarray sum for the current first split.
                    int largestSplitSum = Math.max(firstSplitSum, dp[i + 1][subarrayCount - 1]);

                    // Find the minimum among all possible combinations.
                    minimumLargestSplitSum = Math.min(minimumLargestSplitSum, largestSplitSum);

                    if (firstSplitSum >= minimumLargestSplitSum) break;
                }
                dp[currIndex][subarrayCount] = minimumLargestSplitSum;
            }
        }
        return dp[0][m];
    }


    //Runtime: 47 ms, faster than 14.66% of Java online submissions for Split Array Largest Sum.
    //Memory Usage: 42.2 MB, less than 26.88% of Java online submissions for Split Array Largest Sum.
    //DP + memo | top down
    //Time:(N*N * M); Space:(N*M)
    Integer[][] memo;
    public int splitArray_1(int[] nums, int m) {
        memo = new Integer[nums.length][m+1];
        int[] sums = new int[nums.length];
        sums[nums.length-1] = nums[nums.length-1];
        //Time:O(N)
        for(int i = nums.length - 2; i >= 0; i--) sums[i] = nums[i] + sums[i+1];

        //Time:(N*N * M)
        return helper_dptopdown(sums, 0, m);
    }

    public int helper_dptopdown(int[] sums, int idx, int m) {
        if (m == 1) return sums[idx];
        if (memo[idx][m] != null) return memo[idx][m];

        int res = Integer.MAX_VALUE;
        for (int i = idx; i <= sums.length - m; i++) {
            int sum = (sums[idx] - sums[i+1]);
            if (sum > res) break;
            res = Math.min(res, Math.max(sum, helper_dptopdown(sums, i + 1, m - 1)));
        }
        return memo[idx][m] = res;
    }


}
