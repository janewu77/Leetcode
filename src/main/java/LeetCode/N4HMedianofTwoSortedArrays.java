package LeetCode;


/**
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 *
 * The overall run time complexity should be O(log (m+n)).
 *
 *
 *
 * Example 1:
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 *
 *
 * Example 2:
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 *
 *
 * Constraints:
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 */
public class N4HMedianofTwoSortedArrays {

    public static void main(String[] args){

        doRun(0.0d, new int[]{}, new int[]{});
        doRun(3d, new int[]{}, new int[]{3});

        doRun(1.5d, new int[]{}, new int[]{1,2});
        doRun(2.0d, new int[]{}, new int[]{1,2,3});
        doRun(2.5d, new int[]{}, new int[]{1,2,3,4});
        doRun(3d, new int[]{}, new int[]{1,2,3,4,5});

        doRun(2.5d, new int[]{1,3}, new int[]{2,4});
        doRun(2.5d, new int[]{1,2}, new int[]{3,4});
        doRun(3d, new int[]{0,8}, new int[]{1,2,3,4,5});

        doRun(2.5d, new int[]{2}, new int[]{1,3,4});

        doRun(2.5d, new int[]{1}, new int[]{2,3,4});

    }

    private static int c = 1;
    private static void doRun(double expected, int[] nums1, int[] nums2){
        double res = new N4HMedianofTwoSortedArrays().findMedianSortedArrays(nums1, nums2);
        System.out.println("[" + (expected == res) +"] "+(c++)+ ".result:"+ res + ".expected:"+expected);
    }

    //Runtime: 2 ms, faster than 100.00% of Java online submissions for Median of Two Sorted Arrays.
    //Memory Usage: 43.1 MB, less than 95.12% of Java online submissions for Median of Two Sorted Arrays.
    //merge nums1+nums2
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0 && nums2.length == 0) return 0;

        int k = (nums1.length + nums2.length) >> 1;
        int i = 0; int j = 0;
        int sum = 0;
        int pre = 0;
        while (i + j <= k ){
            pre = sum;
            if (i == nums1.length){
                j = k - i;
                if (j - 1 >= 0) pre = Math.max(pre, nums2[j - 1]);
                sum = nums2[j++];
            }else if (j == nums2.length){
                i = k - j;
                if (i - 1 >= 0) pre = Math.max(pre, nums1[i - 1]);
                sum = nums1[i++];
            }else
                sum = nums1[i] < nums2[j] ? nums1[i++] : nums2[j++];
        }

        //even
        if (((nums1.length + nums2.length) & 1) == 0) return (0d + pre + sum) / 2;
        return sum;
    }

}
