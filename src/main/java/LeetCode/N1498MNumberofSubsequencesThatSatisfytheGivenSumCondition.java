package LeetCode;

import java.util.Arrays;

import static java.time.LocalTime.now;

public class N1498MNumberofSubsequencesThatSatisfytheGivenSumCondition {


    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");
        doRun(4, new int[]{3,5,6,7}, 9);
        doRun(6, new int[]{3,3,6,8}, 10);
        doRun(61, new int[]{2,3,3,4,6,7}, 12);
        doRun(56, new int[]{7,10,7,3,7,5,4}, 12);
        doRun(272187084, new int[]{14,4,6,6,20,8,5,6,8,12,6,10,14,9,17,16,9,7,14,11,14,15,13,11,10,18,13,17,17,14,17,7,9,5,10,13,8,5,18,20,7,5,5,15,19,14}, 22);
        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, int[] nums, int target) {
        int res = new N1498MNumberofSubsequencesThatSatisfytheGivenSumCondition().numSubseq(nums,target);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //2.two pointers
    //Time: 33ms 100%; Memory: 51.4MB 43%
    //Time: O(N + NlogN + N); Space: O(N + logN)
    public int numSubseq(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        long res = 0;

        //Time: O(N); Space: O(N)
        int[] power = new int[nums.length];
        power[0] = 1;
        for (int i = 1; i < power.length; ++i)
            power[i] = (power[i - 1] << 1) % MODULO;

        //Time: O(NlogN); Space: O(logN)
        Arrays.sort(nums);

        while (left <= right) {
            if (nums[left] + nums[right] <= target) {
                res += power[right - left];
                left++;
            }else {
                right--;
            }
        }
        return (int)(res % MODULO);
    }

    //1.Binary Search
    //Time: 42ms 22%; Memory: 51MB 75%
    //Time: O(N + NlogN + NlogN); Space: O(N + logN)
    final static int MODULO = 1_000_000_007;
    public int numSubseq_1(int[] nums, int target) {
        long res = 0;
        //Time: O(NlogN); Space: O(logN)
        Arrays.sort(nums);

        //Time: O(N); Space: O(N)
        int[] power = new int[nums.length];
        power[0] = 1;
        for (int i = 1; i < power.length; ++i)
            power[i] = (power[i - 1] << 1) % MODULO;

        //Time: O(N*logN); Space: O(1)
        for(int i = 0; i < nums.length && nums[i] <= (target >> 1); i++) {
            int idx = Arrays.binarySearch(nums, i, nums.length,target - nums[i]);
            if (idx < 0) idx = -idx - 1 - 1;
            while(idx + 1 < nums.length && nums[idx+1] == nums[idx])
                idx++;
            res += power[idx - i];
        }
        return (int)(res % MODULO);
    }


}
