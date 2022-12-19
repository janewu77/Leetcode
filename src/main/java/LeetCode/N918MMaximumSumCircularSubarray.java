package LeetCode;

import java.io.IOException;
import java.util.Arrays;

import static java.time.LocalTime.now;

public class N918MMaximumSumCircularSubarray {


    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");

        doRun(18, new int[]{-2,4,4,4,6});
        doRun(10, new int[]{5, -3, 5});
        doRun(3, new int[]{1,-2,3,-2});

        doRun(-2, new int[]{-3,-2,-3});
        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, int[] nums) {
        int res = new N918MMaximumSumCircularSubarray().maxSubarraySumCircular(nums);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //4.Kadane's min & max
    //Runtime: 4ms 88%; Memory: 47.2MB 97%
    //Time: O(N); Space: O(1);
    public int maxSubarraySumCircular(int[] nums){
        int n = nums.length;
        int maxSum = nums[0], currMaxSum = 0;
        int minSum = nums[0], currMinSum = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            currMaxSum = Math.max(nums[i], currMaxSum + nums[i]);
            maxSum = Math.max(maxSum, currMaxSum);

            currMinSum = Math.min(nums[i], currMinSum + nums[i]);
            minSum = Math.min(minSum, currMinSum);
            sum += nums[i];
        }
        return sum == minSum ? maxSum : Math.max(maxSum, sum - minSum);
    }

    //3.prefix and suffix sums
    //Runtime: 4ms 88%; Memory: 50.6MB 76%
    //Time: O(N); Space: O(N);
    public int maxSubarraySumCircular_3(int[] nums){
        int n = nums.length;

        int[] maxSuffixSum = new int[n];
        maxSuffixSum[n - 1] = nums[n - 1];
        int suffixSum = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum += nums[i];
            maxSuffixSum[i] = Math.max(maxSuffixSum[i + 1], suffixSum);
        }

        int circleMaxSum = nums[0], prefixSum = nums[0];
        int maxSum = nums[0], currSum = nums[0];
        //Kadane'
        for (int i = 1; i < n; i++) {
            currSum = Math.max(nums[i], currSum + nums[i]);
            maxSum = Math.max(maxSum, currSum);

            circleMaxSum = Math.max(circleMaxSum, prefixSum + maxSuffixSum[i]);
            prefixSum += nums[i];
        }
        return Math.max(maxSum, circleMaxSum);
    }

    //2.brute force
    //Time: O(N * N); Space: O(1)
    public int maxSubarraySumCircular_2(int[] nums){
        int n = nums.length;
        int head = 0, lastHead = head;
        int maxSum = nums[head];

        while (head < n) {
            int currSum = nums[head];
            int idx = (head + 1) % n;

            while (idx != head) {
                if (nums[idx] >= currSum + nums[idx] && nums[idx] >= maxSum) {
                    head = idx;
                }
                currSum = Math.max(nums[idx], currSum + nums[idx]);
                maxSum = Math.max(maxSum, currSum);
                idx = (idx + 1) % n;
            }

            head++;
            if (head <= lastHead) break;
            lastHead = head;
        }
        return maxSum;
    }

    //1.brute force
    //Time: O(N * N); Space: O(1)
    public int maxSubarraySumCircular_1(int[] nums) {
        int n = nums.length;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, helper(nums, i));
        }
        return res;
    }

    public int helper(int[] nums, int begin){
        int n = nums.length;
        int maxSum = nums[begin], currSum = nums[begin];
        int idx = (begin + 1) % n;
        while (idx != begin) {
            currSum = Math.max(nums[idx], currSum + nums[idx]);
            maxSum = Math.max(maxSum, currSum);
            idx = (idx + 1) % n;
        }
        return maxSum;
    }

//    public int maxSubarraySumCircular(int[] nums) {
//        int n = nums.length;
//        if (nums.length == 1) return nums[0];
//
//        int res = Integer.MIN_VALUE;
//        int head = 0;
//        int[] maxSum = new int[n];
//        Arrays.fill(maxSum, Integer.MIN_VALUE);
//        int[] maxidx = new int[n];
//        maxSum[0] = nums[0];
//        maxidx[0] = -1;
//
//        while (true){
//
//            int tail = head;
//            int sum = maxSum[(head - 1 + n) % n];
//            if (nums[head] >= sum + nums[head]) {
//                sum = nums[head];
//                if (head == maxidx[head]) break;
//
//                tail = head;
//            } else {
//                sum = sum + nums[head];
//                if (sum < maxSum[head]) break;
//                tail = maxidx[(head - 1 + n) % n];
//            }
//
//            maxSum[head] = sum;
//            maxidx[head] = tail;
//
//            res = Math.max(res, sum);
//            head = (head + 1) % n;
//
//            if (head == tail) {
//                sum = sum - nums[tail];
//                int end = (head - 1 + n) % n;
//                maxSum[end] = sum;
//                tail = (tail + 1) % n;
//                maxidx[end] = tail;
//
//                for (int i = tail; i <= end; i++) {
//                    if (sum - nums[i] > sum){
//                        maxSum[end] = sum - nums[i];
//                        maxidx[end] = i + 1;
//                    }
//                    sum = sum - nums[i];
//                }
//            }
//        }
//        return res;
//    }
}
