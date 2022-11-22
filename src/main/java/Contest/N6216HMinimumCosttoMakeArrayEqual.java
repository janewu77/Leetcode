package Contest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import static java.time.LocalTime.now;

/**
 * You are given two 0-indexed arrays nums and cost consisting each of n positive integers.
 *
 * You can do the following operation any number of times:
 *
 * Increase or decrease any element of the array nums by 1.
 * The cost of doing one operation on the ith element is cost[i].
 *
 * Return the minimum total cost such that all the elements of the array nums become equal.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,5,2], cost = [2,3,1,14]
 * Output: 8
 * Explanation: We can make all the elements equal to 2 in the following way:
 * - Increase the 0th element one time. The cost is 2.
 * - Decrease the 1st element one time. The cost is 3.
 * - Decrease the 2nd element three times. The cost is 1 + 1 + 1 = 3.
 * The total cost is 2 + 3 + 3 = 8.
 * It can be shown that we cannot make the array equal with a smaller cost.
 * Example 2:
 *
 * Input: nums = [2,2,2,2,2], cost = [4,2,8,1,3]
 * Output: 0
 * Explanation: All the elements are already equal, so no operations are needed.
 *
 */

/**
 * H - [time: 120+
 */

//2448. Minimum Cost to Make Array Equal
public class N6216HMinimumCosttoMakeArrayEqual {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[] data2;

        doRun(0L, new int[]{2,2,2,2,2}, new int[]{2,2,2,2,2});

        doRun(8l, new int[]{1,3,5,2}, new int[]{2,3,1,14});

        doRun(3l, new int[]{1,2,2,2,2}, new int[]{3,1,1,1,1});

        doRun(11l, new int[]{1,2,3,4, -4}, new int[]{3,1,1,1,1});

        data = new int[]{735103,366367,132236,133334,808160,113001,49051,735598,686615,665317,999793,426087,587000,649989,509946,743518};
        data2 = new int[]{724182,447415,723725,902336,600863,287644,13836,665183,448859,917248,397790,898215,790754,320604,468575,825614};
        doRun(1907611126748l, data, data2);

        doRun(1l, new int[]{1,2}, new int[]{1,100});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(long expect, int[] nums, int[] cost) {
        long res = new N6216HMinimumCosttoMakeArrayEqual().minCost(nums, cost);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //4.Binary Search
    //Runtime: 54 ms, faster than 100.00% of Java online submissions for Minimum Cost to Make Array Equal.
    //Memory Usage: 80.5 MB, less than 100.00% of Java online submissions for Minimum Cost to Make Array Equal.
    //Time: O(N + N + logN * (N + N)); Space(1)
    //Time: O(N * logN); Space(1)
    public long minCost(int[] nums, int[] cost) {
        long minNum = Integer.MAX_VALUE, maxNum = Integer.MIN_VALUE;
        //Time: O(N)
        for(int n : nums){
            minNum = Math.min(minNum, n);
            maxNum = Math.max(maxNum, n);
        }

        //Time: O(N)
        long res = findCost(nums, cost, minNum);

        //Time: O(logN * (N + N))
        while (minNum < maxNum) {
            long mid = (minNum + maxNum) / 2;
            long costLeft = findCost(nums, cost, mid);         //Time: O(N)
            long costRight = findCost(nums, cost, mid + 1); //Time: O(N)
            res = Math.min(costLeft, costRight);

            if (costLeft < costRight) maxNum = mid; //keep mid, because mid might be our answer
            else minNum = mid + 1;
        }
        return res;
    }

    //Time: O(N)
    private long findCost(int[] nums, int[] cost, long x) {
        long res = 0L;
        for (int i = 0; i < nums.length; i++)
            res += Math.abs(nums[i] - x) * cost[i];
        return res;
    }

    //3.sort + prefix sum
    //idea:
    //Before this, I code brute force first. But they are all TLE.... Then I tried to solve TLE.
    //The time complexity is O(N * N), which means I DO compute the cost for N times. It's too much.
    //
    //Then I try to do the compute only once.(if so, we can save a lot of time...)
    //If we already have a cost, then I wonder if possible to add or minus something to get the next smaller cost?
    //
    //Let's pickup one number which has the largest cost, let it be X, its cost is costOfX,  its position is positionX.
    //then we can easily get the whole cost, let be costX.
    //
    //now next step : shall we move to positionX + 1 or positionX - 1 ? or just stay at positionX?
    //we know we want to get the smaller cost.
    //if we move to the right neighbour (move from positionX to positionX + 1):
    //1.every number on the right side, their costs should be removed from costX;
    //2.every number on the left side, their costs should be added to costX;
    //3.costOfX also need to be added to costX.
    //   costX1 = costX - costRight + costLeft + costOfX
    //  if costX1 is smaller than original cost costX, then we should move to right neighbour.
    //   costX1 < costX
    //   ==> costX - costRight + costLeft + costOfX < costX
    //   ==> costLeft + costOfX < costRight
    //   now we get the equation
    // similarly, we can get the other equation for positionX - 1
    //  costX_1 = costX - costLeft + costRight + costOfX < costX
    //   ==> costRight + costOfX < costLeft
    //  now, we can use these two equations to chose : positionX + 1 or positionX - 1 ? or positionX?
    //  In equations we need the costRight and costLeft, the prefix sum is very useful to such scenarios.
    //
    // In the end, we get the index of number which is the element in the final array.
    //
    //Runtime: 189 ms, faster than 50.00% of Java online submissions for Minimum Cost to Make Array Equal.
    //Memory Usage: 95.2 MB, less than 75.00% of Java online submissions for Minimum Cost to Make Array Equal.
    //Time: O(N + N * logN + N + N + N); Space: O(N + logN + N)
    //Time: O(4N + N * logN); Space: O(N)
    //Time: O(N * logN); Space: O(N)
    public long minCost_3(int[] nums, int[] cost) {

        //1. sort nums
        //Time: O(N); Space: O(N)
        int[][] data = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++)
            data[i] = new int[]{nums[i],cost[i]};

        //Time: O(N * logN); Space: O(logN)
        Arrays.sort(data, Comparator.comparingInt(a -> a[0]));

        //2.precompute prefix sum
        //prefix sum, max cost
        //Time: O(N); Space: O(N)
        int idx = 0, maxCost = Integer.MIN_VALUE;
        long[] preSum = new long[nums.length + 1];
        for (int i = 0; i < data.length; i++) {
            preSum[i + 1] = preSum[i] + data[i][1];
            if (data[i][1] > maxCost){
                idx = i;
                maxCost = data[i][1];
            }
        }

        //Time: worstCase : O(N)
        long sum = preSum[preSum.length - 1];
        int lastIdx = -1;
        while (lastIdx != idx) {
            lastIdx = idx;
            if (preSum[idx + 1] < sum - preSum[idx + 1]) idx++;
            else if (preSum[idx] > sum - preSum[idx]) idx--;
        }

        //Time: O(N)
        long res = 0l;
        for (int i = 0; i < nums.length; i++)
            res += 1l * Math.abs(nums[i] - data[idx][0]) * cost[i];
        return res;
    }


    //2.Brute force 2
    //TLE
    //Time: O(N * N)
    public long minCost_2(int[] nums, int[] cost) {

        Set<Integer> set = new HashSet<>();
        long res = Long.MAX_VALUE;
        for (int j = 0; j < nums.length; j++) {
            if (!set.add(nums[j])) continue;
            long sum = 0;
            for (int i = 0; i < nums.length; i++)
                sum += 1l * Math.abs(nums[i] - nums[j]) * cost[i];
            res = Math.min(res, sum);
        }
        return res;
    }

    //1.Brute force
    //TLE
    //Time: O(N * N)
    public long minCost_1(int[] nums, int[] cost) {
        int minNum = Integer.MAX_VALUE;
        int maxNum = Integer.MIN_VALUE;

        for(int n : nums){
            minNum = Math.min(minNum, n);
            maxNum = Math.max(maxNum, n);
        }

        long res = Long.MAX_VALUE;
        for (int k = minNum; k <= maxNum; k++){
            long sum = 0;
            for (int i = 0; i < nums.length; i++)
                sum += 1l * Math.abs(nums[i] - k) * cost[i];
            res = Math.min(res, sum);
        }
        return res;
    }


}
