package LeetCode;

import java.io.IOException;

import static java.time.LocalTime.now;

public class N2256MMinimumAverageDifference {


    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");

        doRun(3, new int[]{2,5,3,9,5,3});
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[] nums) {
        int res = new N2256MMinimumAverageDifference().minimumAverageDifference(nums);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //1. precompute sum
    //Time: O(N); Space: O(1)
    public int minimumAverageDifference(int[] nums) {
        long sum = 0;
        for (int i = 0; i < nums.length; i++) sum += nums[i];

        int idx = 0;
        long LeftSum = 0, minAvg = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            LeftSum += nums[i];
            int leftC = i + 1;
            int rightC = nums.length - 1 - i;
            long currAvg = Math.abs(LeftSum / leftC - (rightC == 0 ? 0 : (sum - LeftSum) / rightC));
            if (currAvg < minAvg){
                minAvg = currAvg;
                idx = i;
            }
        }
        return idx;
    }
}
