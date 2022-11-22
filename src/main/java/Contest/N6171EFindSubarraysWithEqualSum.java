package Contest;

import java.util.HashSet;
import java.util.Set;

import static java.time.LocalTime.now;

/**
 * Given a 0-indexed integer array nums, determine whether there exist two subarrays of
 * length 2 with equal sum. Note that the two subarrays must begin at different indices.
 *
 * Return true if these subarrays exist, and false otherwise.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,2,4]
 * Output: true
 * Explanation: The subarrays with elements [4,2] and [2,4] have the same sum of 6.
 * Example 2:
 *
 * Input: nums = [1,2,3,4,5]
 * Output: false
 * Explanation: No two subarrays of size 2 have the same sum.
 * Example 3:
 *
 * Input: nums = [0,0,0]
 * Output: true
 * Explanation: The subarrays [nums[0],nums[1]] and [nums[1],nums[2]] have the same sum of 0.
 * Note that even though the subarrays have the same content, the two subarrays
 * are considered different because they are in different positions in the original array.
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 1000
 * -109 <= nums[i] <= 109
 */

/**
 * E- [time: 10-
 */

//2395. Find Subarrays With Equal Sum
public class N6171EFindSubarraysWithEqualSum {


    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        data = new int[]{4,2,4};
        doRun_demo(true, data);

        data = new int[]{77,95,90,98,8,100,88,96,6,40,86,56,98,96,40,52,30,33,97,72,54,15,33,77,78,8,21,47,99,48};
        doRun_demo(true, data);

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun_demo(boolean expect, int[] nums) {
        boolean res = new N6171EFindSubarraysWithEqualSum().findSubarrays(nums);
//        String res = comm.toString(res1);
//        String res = Arrays.stream(res1).mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(","));
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        //int[][] res1
        //        sb.append("[");
        //        for(int i = 0; i<res1.length; i++) {
        //            String str = Arrays.toString(res1[i]);
        //            sb.append(str).append(",");
        //        }
        //        if (sb.length() > 1) sb.deleteCharAt(sb.length()-1);
        //        sb.append("]");
    }

    //Runtime: 1 ms, faster than 92.31% of Java online submissions for Find Subarrays With Equal Sum.
    //Memory Usage: 42.2 MB, less than 46.15% of Java online submissions for Find Subarrays With Equal Sum.
    //Time: O(N); Space: O(N)
    public boolean findSubarrays(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length - 1; i++)
            if (!set.add(nums[i] + nums[i+1]))
                return true;
        return false;
    }
}
