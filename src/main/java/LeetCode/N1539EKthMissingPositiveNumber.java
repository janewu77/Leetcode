package LeetCode;


/**
 * Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
 *
 * Return the kth positive integer that is missing from this array.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [2,3,4,7,11], k = 5
 * Output: 9
 * Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
 * Example 2:
 *
 * Input: arr = [1,2,3,4], k = 2
 * Output: 6
 * Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 1000
 * 1 <= arr[i] <= 1000
 * 1 <= k <= 1000
 * arr[i] < arr[j] for 1 <= i < j <= arr.length
 *
 *
 * Follow up:
 *
 * Could you solve this problem in less than O(n) complexity?
 */
public class N1539EKthMissingPositiveNumber {

    //2.Binary Search
    //Runtime: 0ms 100%; Memory: 41.7MB 69%
    //Time: O(logN); Space: O(1)
    public int findKthPositive(int[] arr, int k) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int m = (left + right) >> 1;
            if (arr[m] - m - 1 >= k) right--;
            else left++;
        }
        return k + left;
    }

    //1.one pass
    //Runtime: 0ms 100%; Memory: 42.2MB 24%
    //Time: O(N); Space: O(1)
    public int findKthPositive_1(int[] arr, int k) {
        int res = 1, idx = 0;
        while (k > 0) {
            if (idx < arr.length && res == arr[idx]) idx++;
            else k--;
            res++;
        }
        return res - 1;
    }

    public int findKthPositive_12(int[] arr, int k) {
        int res = 1;
        for (int idx = 0; idx < arr.length && k > 0; res++) {
            if (res == arr[idx]) idx++;
            else k--;
        }
        return res + k - 1;
    }
}
