package LeetCode;


import java.util.Arrays;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

/**
 * You have a set of integers s, which originally contains all the numbers from 1 to n. Unfortunately, due to some error, one of the numbers in s got duplicated to another number in the set, which results in repetition of one number and loss of another number.
 *
 * You are given an integer array nums representing the data status of this set after the error.
 *
 * Find the number that occurs twice and the number that is missing and return them in the form of an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,2,4]
 * Output: [2,3]
 * Example 2:
 *
 * Input: nums = [1,1]
 * Output: [1,2]
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 104
 * 1 <= nums[i] <= 104
 */


public class N645ESetMismatch {


    public static void main(String[] args) {

        System.out.println(now());

        doRun( "2,10", new int[]{1,5,3,2,2,7,6,4,8,9});
        doRun( "3,1", new int[]{3,2,3,4,6,5});
        doRun( "2,1", new int[]{2,2});
        doRun( "2,3", new int[]{1,2,2,4});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(String expect, int[] nums) {
        int[] res1 = new N645ESetMismatch().findErrorNums(nums);
        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    //7.math (from @kanna17vce)
    //Runtime: 2 ms, faster than 97.19% of Java online submissions for Set Mismatch.
    //Memory Usage: 43.9 MB, less than 92.63% of Java online submissions for Set Mismatch.
    //Time:O(N); Space:O(1)
    public int[] findErrorNums(int[] nums) {
        /** Assume m is the missing and d is the duplicate element
         diff = m - d;
         squareDiff = m^2 - d^2;
         sum = m + d =  squareDiff / diff
         => sum = (m+d)(m-d)/(m-d);
         => m=(sum+diff)2; d= (sum-diff)2;
         **/
        int diff = 0, sqaureDiff = 0;
        for(int i = 0; i < nums.length; i++){
            /**
             The order doesnot matter. keep adding the 1 to n and simultaneously subtracting corresponding array element.
             Use i+1 to get 1 to n since i is the index number which is zero based.
             **/
            diff += (i + 1) - nums[i];
            /** squareDiff is also calculated in the same way as diff is calculated. **/
            sqaureDiff += (i + 1) * (i + 1) - nums[i] * nums[i];
        }
        int sum = sqaureDiff / diff;
        return new int[]{(sum - diff) / 2,(sum + diff) / 2};
    }


    //6.Xor one pass
    //Runtime: 6 ms, faster than 66.59% of Java online submissions for Set Mismatch.
    //Memory Usage: 55.7 MB, less than 19.95% of Java online submissions for Set Mismatch.
    //Time:O(N); Space:O(N)
    public int[] findErrorNums_6(int[] nums) {
        int[] res = new int[2]; //{double, missing}
        int[] counter = new int[nums.length];
        int xor = 0;
        for (int i = 0; i < nums.length; i++)  {
            //same as solution 1
            if (counter[nums[i] - 1] != 0) res[0] = nums[i];
            else counter[nums[i] - 1] = 1;
            //same as solution 5
            xor = xor ^ nums[i] ^ (i + 1);
        }
        res[1] = xor ^ res[0];
        return res;
    }

    //5.Xor
    //Runtime: 10 ms, faster than 54.68% of Java online submissions for Set Mismatch.
    //Memory Usage: 54.8 MB, less than 45.76% of Java online submissions for Set Mismatch.
    //Time:O(N + N + N + N + N); Space:O(1)
    //Time:O(N); Space:O(1)
    public int[] findErrorNums_5(int[] nums) {
        int xor = 0;
        for (int i = 0; i < nums.length; i++)  xor ^= nums[i];
        for (int i = 1; i <= nums.length; i++)  xor ^= i;
        int rightMostBit = xor & (-xor);

        int xor1 = 0, xor0 = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & rightMostBit) == 0) xor0 ^= nums[i];
            else xor1 ^= nums[i];
        }
        for (int i = 1; i <= nums.length; i++) {
            if ((i & rightMostBit) == 0) xor0 ^= i;
            else xor1 ^= i;
        }

        for (int i = 0; i < nums.length; i++)
            if (nums[i] == xor0) return new int[]{xor0, xor1};

        return new int[]{xor1, xor0};
    }

    //4.one pass
    // currentSum + Missing - Double = totalSum
    // Missing = totalSum - currentSum + doubled
    //Runtime: 5 ms, faster than 60.11% of Java online submissions for Set Mismatch.
    //Memory Usage: 54.6 MB, less than 51.12% of Java online submissions for Set Mismatch.
    //Time: O(N); Space: O(1)
    public int[] findErrorNums_4(int[] nums) {
        int[] res = new int[2];
        int diff = 0;
        for (int i = 0; i < nums.length; i++) {
            int idx = Math.abs(nums[i]);
            if (nums[idx - 1] < 0) res[0] = idx;
            else nums[idx - 1] = -nums[idx - 1];
            diff += (i + 1) - idx;
        }
        //math
        res[1] = diff + res[0];
        return res;
    }

    //3.in-place
    //Runtime: 3 ms, faster than 86.64% of Java online submissions for Set Mismatch.
    //Memory Usage: 53.9 MB, less than 82.03% of Java online submissions for Set Mismatch.
    //Time: O(N + N); Space: O(1)
    public int[] findErrorNums_3(int[] nums) {
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int idx = Math.abs(nums[i]);
            if (nums[idx - 1] < 0) res[0] = idx;
            else nums[idx - 1] = -nums[idx - 1];
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res[1] = i + 1; break;
            }
        }
        return res;
    }

    //2.sort
    //Runtime: 20 ms, faster than 22.04% of Java online submissions for Set Mismatch.
    //Memory Usage: 54.2 MB, less than 72.93% of Java online submissions for Set Mismatch.
    //Time: O(N * LogN + N); Space: O(LogN)
    //Time: O(N * LogN); Space: O(LogN)
    public int[] findErrorNums_2(int[] nums) {
        //Time: O(N * LogN); Space: O(LogN)
        Arrays.sort(nums);

        //Time: O(N);
        int[] res = new int[2];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) res[0] = nums[i];
            else if (nums[i] != nums[i - 1] + 1) res[1] = nums[i] - 1;
        }
        if (nums[0] != 1) res[1] = 1;
        if (nums[nums.length - 1] != nums.length) res[1] = nums.length;
        return res;
    }


    //1.count
    //Runtime: 9 ms, faster than 44.06% of Java online submissions for Set Mismatch.
    //Memory Usage: 43.5 MB, less than 98.85% of Java online submissions for Set Mismatch.
    //Time: O(N + N); Space:O(N);
    //Time: O(N); Space:O(N);
    public int[] findErrorNums_1(int[] nums) {
        int[] res = new int[2];
        int[] data = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (data[nums[i] - 1] != 0) res[0] = nums[i];
            else data[nums[i] - 1] = 1;
        }

        for (int i = 0; i < nums.length; i++) {
            if (data[i] == 0) {
                res[1] = i + 1; break;
            }
        }
        return res;
    }
}
