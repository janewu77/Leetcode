package LeetCode;


import java.util.HashMap;
import java.util.Map;

/**
 * You are given a 0-indexed array of positive integers nums. Find the number of
 * triplets (i, j, k) that meet the following conditions:
 *
 * 0 <= i < j < k < nums.length
 * nums[i], nums[j], and nums[k] are pairwise distinct.
 * In other words, nums[i] != nums[j], nums[i] != nums[k], and nums[j] != nums[k].
 * Return the number of triplets that meet the conditions.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,4,2,4,3]
 * Output: 3
 * Explanation: The following triplets meet the conditions:
 * - (0, 2, 4) because 4 != 2 != 3
 * - (1, 2, 4) because 4 != 2 != 3
 * - (2, 3, 4) because 2 != 4 != 3
 * Since there are 3 triplets, we return 3.
 * Note that (2, 0, 4) is not a valid triplet because 2 > 0.
 * Example 2:
 *
 * Input: nums = [1,1,1,1,1]
 * Output: 0
 * Explanation: No triplets meet the conditions so we return 0.
 *
 *
 * Constraints:
 *
 * 3 <= nums.length <= 100
 * 1 <= nums[i] <= 1000
 */
public class N2475ENumberofUnequalTripletsinArray {


    //2.count
    //Runtime: 4 ms, faster than 100.00% of Java online submissions for Number of Unequal Triplets in Array.
    //Memory Usage: 41.9 MB, less than 50.00% of Java online submissions for Number of Unequal Triplets in Array.
    //Time: O(N + N); space: O(N)
    //Time: O(N); space: O(N)
    public int unequalTriplets(int[] nums) {

        //Time: O(N); space: O(N)
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums)
            map.put(num , map.getOrDefault(num ,0) + 1);

        int res = 0;
        int left = 0, right = nums.length;
        //Time: O(N)
        // leftCount * midCount * rightCount
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int midCount = entry.getValue();
            right -= midCount;
            res += left * midCount * right;

            left += midCount;
        }
        return res;
    }

    //1.brute force
    //Runtime: 7 ms, faster than 100.00% of Java online submissions for Number of Unequal Triplets in Array.
    //Memory Usage: 40.2 MB, less than 100.00% of Java online submissions for Number of Unequal Triplets in Array.
    //Time: O(N*N*N); space: O(1)
    public int unequalTriplets_1(int[] nums) {
        int res = 0;
        for(int i = 0; i<nums.length - 2; i++){
            for(int j = i + 1; j<nums.length - 1; j++){
                if (nums[i] == nums[j]) continue;
                for(int k = j + 1; k<nums.length; k++){
                    if (nums[i] != nums[k] && nums[j] != nums[k]) res++;
                }
            }
        }
        return res;
    }
}
