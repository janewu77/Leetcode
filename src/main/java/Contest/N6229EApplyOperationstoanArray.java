package Contest;

import static java.time.LocalTime.now;


/**
 * You are given a 0-indexed array nums of size n consisting of non-negative integers.
 *
 * You need to apply n - 1 operations to this array where, in the ith operation (0-indexed), you will apply the following on the ith element of nums:
 *
 * If nums[i] == nums[i + 1], then multiply nums[i] by 2 and set nums[i + 1] to 0. Otherwise, you skip this operation.
 * After performing all the operations, shift all the 0's to the end of the array.
 *
 * For example, the array [1,0,2,0,0,1] after shifting all its 0's to the end, is [1,2,1,0,0,0].
 * Return the resulting array.
 *
 * Note that the operations are applied sequentially, not all at once.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,2,1,1,0]
 * Output: [1,4,2,0,0,0]
 * Explanation: We do the following operations:
 * - i = 0: nums[0] and nums[1] are not equal, so we skip this operation.
 * - i = 1: nums[1] and nums[2] are equal, we multiply nums[1] by 2 and change nums[2] to 0. The array becomes [1,4,0,1,1,0].
 * - i = 2: nums[2] and nums[3] are not equal, so we skip this operation.
 * - i = 3: nums[3] and nums[4] are equal, we multiply nums[3] by 2 and change nums[4] to 0. The array becomes [1,4,0,2,0,0].
 * - i = 4: nums[4] and nums[5] are equal, we multiply nums[4] by 2 and change nums[5] to 0. The array becomes [1,4,0,2,0,0].
 * After that, we shift the 0's to the end, which gives the array [1,4,2,0,0,0].
 * Example 2:
 *
 * Input: nums = [0,1]
 * Output: [1,0]
 * Explanation: No operation can be applied, we just shift the 0 to the end.
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 2000
 * 0 <= nums[i] <= 1000
 */

/**
 * E - [time: 5-
 */
//2460. Apply Operations to an Array
public class N6229EApplyOperationstoanArray {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        //doRun(0, 0);
        System.out.println(now());
        System.out.println("==================");

    }


    static private void doRun(long expect, int n) {
//        long res = new N1().n1(n);

//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //1. one pass
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Apply Operations to an Array.
    //Memory Usage: 42.4 MB, less than 66.67% of Java online submissions for Apply Operations to an Array.
    //Time: O(N); Space: O(1)
    public int[] applyOperations(int[] nums) {
        int[] res = new int[nums.length];
        int idx = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != 0 ) {
                if (nums[i] == nums[i + 1]) {
                    nums[i++] <<= 1;
                    nums[i] = 0;
                    res[idx++] = nums[i - 1];
                }else
                    res[idx++] = nums[i];
            }
        }
        res[idx] = nums[nums.length - 1];
        return res;
    }


}


