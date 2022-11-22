package LeetCode;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * Given four integer arrays nums1, nums2, nums3, and nums4 all of length n,
 * return the number of tuples (i, j, k, l) such that:
 *
 * 0 <= i, j, k, l < n
 * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 *
 *
 * Example 1:
 * Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
 * Output: 2
 * Explanation:
 * The two tuples are:
 * 1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
 *
 *
 * Example 2:
 * Input: nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
 * Output: 1
 *
 *
 * Constraints:
 * n == nums1.length
 * n == nums2.length
 * n == nums3.length
 * n == nums4.length
 * 1 <= n <= 200
 * -228 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 228
 *
 *
 */

/**
 * M- 【time：30-】
 */
public class N454M4SumII {

    public static void main(String[] args) {
        int expected;
        int[] nums1, nums2, nums3, nums4;

        nums1 = new int[]{1,2};
        nums2 = new int[]{-2,-1};
        nums3 = new int[]{-1,2};
        nums4 = new int[]{0,2};
        expected = 2;
        doRun(expected, nums1, nums2, nums3, nums4);

        nums1 = new int[]{-1,-1};
        nums2 = new int[]{-1,1};
        nums3 = new int[]{-1,1};
        nums4 = new int[]{1,-1};
        expected = 6;
        doRun(expected, nums1, nums2, nums3, nums4);


    }
    private static void doRun(int expected, int[] nums1, int[] nums2, int[] nums3, int[] nums4){
        int res = new N454M4SumII().fourSumCount(nums1,nums2,nums3,nums4);
        //String strRes = Arrays.stream(res).mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(""));
        System.out.println("["+(expected == res)+"].[expected:"+ expected+"] res:"+res);
    }

//    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
//        return kSumCount(new int[][]{A, B, C, D});
//    }
//
//    public int kSumCount(int[][] lists) {
//        Map<Integer, Integer> m = new HashMap<>();
//        addToHash(lists, m, 0, 0);
//        return countComplements(lists, m, lists.length / 2, 0);
//    }
//
//    void addToHash(int[][] lists, Map<Integer, Integer> m, int i, int sum) {
//        if (i == lists.length / 2) {
//            m.put(sum, m.getOrDefault(sum, 0) + 1);
//        } else {
//            for (int a : lists[i]) {
//                addToHash(lists, m, i + 1, sum + a);
//            }
//        }
//    }
//
//    int countComplements(int[][] lists, Map<Integer, Integer> m, int i, int complement) {
//        if (i == lists.length) {
//            return m.getOrDefault(complement, 0);
//        }
//        int cnt = 0;
//        for (int a : lists[i]) {
//            cnt += countComplements(lists, m, i + 1, complement - a);
//        }
//        return cnt;
//    }


    ////////////////////////////////////////////////////////////////
    //Runtime: 133 ms, faster than 96.14% of Java online submissions for 4Sum II.
    //Memory Usage: 42.6 MB, less than 89.48% of Java online submissions for 4Sum II.
    // hashmap key放sum; 然后用求补的方式找。
    //time:O(N*N); Space:O(N*N)
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {

        //nums1, nums2;
        Map<Integer, Integer> sum = new HashMap<>();
        for (int a : nums1)
            for (int b : nums2)
                sum.put(a + b, sum.getOrDefault(a + b, 0) + 1);

        //nums3, nums4
        int count = 0;
        for (int c : nums3)
            for (int d : nums4)
                count += sum.getOrDefault(- c - d, 0);

        return count;
    }


    //////////////////////////////////////////////////////////////////////////////////////
    //Time Limit Exceeded
    public int fourSumCount_1(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int count = 0;
        for(int i1 = 0; i1 < nums1.length; i1++){
            for(int i2 = 0; i2 < nums2.length; i2++){

                int sum2 = nums1[i1] + nums2[i2];
                for(int i3 = 0; i3 < nums3.length; i3++){
                    int sum3 = sum2 + nums3[i3];

                    for(int i4 = 0; i4 < nums4.length; i4++)
                        if (sum3 + nums4[i4] == 0) count++;
                }
            }
        }
        return count;
    }
}
