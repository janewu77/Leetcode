package LeetCode;

import static java.time.LocalTime.now;

/**
 * Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
 * Output: 3
 * Explanation: The repeated subarray with maximum length is [3,2,1].
 * Example 2:
 *
 * Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
 * Output: 5
 *
 *
 * Constraints:
 *
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 100
 */

/**
 * M - [time: 60
 */
public class N718MMaximumLengthofRepeatedSubarray {


    public static void main(String[] args) {

        System.out.println(now());
        String[] data;

        doRun(3, new int[]{1,0,0,0,1}, new int[]{1,0,0,1,1});


        doRun(4, new int[]{0,0,0,0,1}, new int[]{1,0,0,0,0});

        doRun(3, new int[]{1,2,3,2,1}, new int[]{3,2,1,4,7});
        doRun(5, new int[]{0,0,0,0,0}, new int[]{0,0,0,0,0});


        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[] nums1, int[] nums2) {
        int res = new N718MMaximumLengthofRepeatedSubarray().findLength(nums1, nums2);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 31 ms, faster than 99.16% of Java online submissions for Maximum Length of Repeated Subarray.
    //Memory Usage: 42 MB, less than 98.75% of Java online submissions for Maximum Length of Repeated Subarray.
    //DP 1d-array
    //Time: O(N1 * N2); Space: O(N2)
    public int findLength(int[] nums1, int[] nums2) {
        int res = 0;
        int[] dp = new int[nums2.length + 1];

        for(int i = nums1.length - 1; i >= 0; i--){
            int[] dp2 = new int[nums2.length + 1];

            for (int j = nums2.length - 1; j >= 0; j--){
                if (nums1[i] == nums2[j]) {
                    dp2[j] = dp[j + 1] + 1;
                    res = Math.max(res, dp2[j]);
                }
            }
            dp = dp2;
        }
        return res;
    }

    //Runtime: 39 ms, faster than 96.58% of Java online submissions for Maximum Length of Repeated Subarray.
    //Memory Usage: 51.2 MB, less than 83.72% of Java online submissions for Maximum Length of Repeated Subarray.
    //DP 2d-array
    //Time: O(N1 * N2); Space: O(N1 * N2)
    public int findLength_1(int[] nums1, int[] nums2) {
        int res = 0;
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];

        for(int i = nums1.length - 1; i >= 0; i--){
            for (int j = nums2.length - 1; j >= 0; j--){
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res;
    }
}
