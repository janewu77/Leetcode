package Contest;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.time.LocalTime.now;

/**
 * Given a 0-indexed integer array nums of size n and two integers lower and upper, return the number of fair pairs.
 *
 * A pair (i, j) is fair if:
 *
 * 0 <= i < j < n, and
 * lower <= nums[i] + nums[j] <= upper
 *
 *
 * Example 1:
 *
 * Input: nums = [0,1,7,4,4,5], lower = 3, upper = 6
 * Output: 6
 * Explanation: There are 6 fair pairs: (0,3), (0,4), (0,5), (1,3), (1,4), and (1,5).
 * Example 2:
 *
 * Input: nums = [1,7,9,2,5], lower = 11, upper = 11
 * Output: 1
 * Explanation: There is a single fair pair: (2,3).
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * nums.length == n
 * -109 <= nums[i] <= 109
 * -109 <= lower <= upper <= 109
 */
public class N2563MCounttheNumberofFairPairs {

    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");

        //
        doRun(3, new int[]{6,9,4,2,7,5,10,3}, 13, 13);
        doRun(6, new int[]{0,1,7,4,4,5}, 3, 6);
        doRun(15, new int[]{0,0,0,0,0,0}, 0, 0);


        doRun(15, new int[]{0,0,0,0,0,0}, -1000000000, 1000000000);


        doRun(1, new int[]{1,7,9,2,5}, 11, 11);
        doRun(4, new int[]{1,2,2,2,2}, 3, 3);
        doRun(6, new int[]{-5,-7,-5,-7,-5}, -12, -12);
        doRun(0, new int[]{8,9,10}, 3, 6);


        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(long expect, int[] nums, int lower, int upper) {
        long res = new N2563MCounttheNumberofFairPairs().countFairPairs(nums, lower, upper);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }



    //3.two pointers
    //Runtime: 27ms 55%; Memory: 59MB 11%;
    //time: O(N * LogN); Space: O(lgN)
    public long countFairPairs(int[] nums, int lower, int upper) {
        long res = 0;
        Arrays.sort(nums);
        int idxL = nums.length - 1, idxR = nums.length - 1;

        for (int i = 0; i < nums.length; i++) {
            idxL = Math.max(idxL, i + 1);
            while (idxL - 1 > i && nums[idxL - 1] + nums[i] >= lower)
                idxL--;

            while (idxR > 0 && nums[idxR] + nums[i] > upper)
                idxR--;

            if (idxL <= idxR && nums[i] + nums[idxL] >= lower && nums[i] + nums[idxR] <= upper)
                res += idxR - idxL + 1;
        }
        return res;
    }


    //2.Binary
    //Runtime: 42ms 55%; Memory: 60MB 11%;
    //time: O(N * LogN); Space: O(lgN)
    public long countFairPairs_2(int[] nums, int lower, int upper) {
        long res = 0;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 1 && (nums[i] <= 0 || nums[i] <= upper); i++) {
            int idxL =  i + 1;
            if (nums[i] < lower - nums[i])
                idxL = help_binarysearch_left(nums, i, lower - nums[i]);

            int idxR = help_binarysearch_right(nums, i, upper - nums[i]);
            if (idxR > 0 && idxL > 0 && idxL <= idxR)
                res += idxR - idxL + 1;
        }
        return res;
    }

    private int help_binarysearch_left(int[] nums, int begin, int key){
        int idx = Arrays.binarySearch(nums, key);
        if (idx < 0) idx = - idx - 1;
        else {
            if (idx <= begin) idx = begin + 1;
            if (nums[idx] != key) return -1;

            while(idx - 1 > begin && nums[idx - 1] == key)
                idx--;
        }
        return idx;
    }

    private int help_binarysearch_right(int[] nums, int begin, int key){

        int idx = Arrays.binarySearch(nums, key);
        if (idx < 0) idx = - idx - 2;
        else {
            if (idx <= begin) idx = begin + 1;
            if (nums[idx] != key) return -1;

            while (idx + 1 < nums.length && nums[idx + 1] == key)
                idx++;
        }
        return idx;
    }


    //1.brute force
    //TLE
    public long countFairPairs_1(int[] nums, int lower, int upper) {
        long res = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                if (sum >= lower && sum <= upper)
                    res++;
            }
        }
       return res;
    }


}
