package Contest;


import java.util.Arrays;

/**
 * Given two integer arrays nums1 and nums2, sorted in non-decreasing order, return the minimum integer common to both arrays. If there is no common integer amongst nums1 and nums2, return -1.
 *
 * Note that an integer is said to be common to nums1 and nums2 if both arrays have at least one occurrence of that integer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,3], nums2 = [2,4]
 * Output: 2
 * Explanation: The smallest element common to both arrays is 2, so we return 2.
 * Example 2:
 *
 * Input: nums1 = [1,2,3,6], nums2 = [2,3,4,5]
 * Output: 2
 * Explanation: There are two common elements in the array 2 and 3 out of which 2 is the smallest, so 2 is returned.
 *
 *
 * Constraints:
 *
 * 1 <= nums1.length, nums2.length <= 105
 * 1 <= nums1[i], nums2[j] <= 109
 * Both nums1 and nums2 are sorted in non-decreasing order.
 */
public class N2540EMinimumCommonValue {

    //1.two pointers + binarySearch
    //Runtime: 1ms 100%; Memory: 59.1MB 90%
    //Time: O(N1 + N2); Space: O(1)
    public int getCommon(int[] nums1, int[] nums2) {
        int idx1 = 0, idx2 = 0;
        while (idx1 < nums1.length && idx2 < nums2.length) {
            if (nums1[idx1] == nums2[idx2]) return nums1[idx1];
            else if (nums1[idx1] > nums2[idx2]) {
                idx2 = Arrays.binarySearch(nums2, idx2 + 1, nums2.length, nums1[idx1]);
                if (idx2 >= 0) return nums2[idx2];
                idx2 = -idx2 - 1;
                idx1++;
            } else {
                idx1 = Arrays.binarySearch(nums1, idx1 + 1, nums1.length, nums2[idx2]);
                if (idx1 >= 0) return nums1[idx1];
                idx1 = -idx1 - 1;
                idx2++;
            }
        }
        return -1;
    }


    //1.two pointers
    //Runtime: 2ms 100%; Memory: 59.3MB 90%
    //Time: O(N1 + N2); Space: O(1)
    //let n1 be the length of nums1; n2 be the length of nums2
    public int getCommon_1(int[] nums1, int[] nums2) {
        int idx1 = 0, idx2 = 0;

        while(idx1 < nums1.length && idx2 < nums2.length){
            if (nums1[idx1] == nums2[idx2]) return nums1[idx1];
            else if (nums1[idx1] < nums2[idx2]) idx1++;
            else idx2++;
        }
        return -1;
    }
}
