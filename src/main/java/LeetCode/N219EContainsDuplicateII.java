package LeetCode;

import java.util.*;

import static java.time.LocalTime.now;

/**
 * Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 * Example 2:
 *
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 * Example 3:
 *
 * Input: nums = [1,2,3,1,2,3], k = 2
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * 0 <= k <= 105
 */

/**
 * E - [time: 15-
 */
public class N219EContainsDuplicateII {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;
        doRun(true,  new int[]{

                //233,579,62,786,342,817,838,396,230,79,
                //570,702,124,727,586,542,919,372,187,
                //626,869,923,103,932,
                368,891,411,125,724,
                287,575,326,88,125,746,117,363,8,881,441,
                542,653,211,180,620, 175,747,276,832,772,
                165,733,574,186,778,586,601,165,422,956,
                357,134,857,114,450,64, 494,679,495,205,859,
                136,477,879,940,139,903,689,954,335,859,78,20}, 22);

        doRun(false,  new int[]{1,2,3,1,1,2,3}, 0);
        doRun(true,  new int[]{1,2,3,1}, 3);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(boolean expect, int[] nums, int k) {
        boolean res = new N219EContainsDuplicateII().containsNearbyDuplicate(nums, k);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //3 in-place
    //Runtime: 1406 ms, faster than 5.00% of Java online submissions for Contains Duplicate II.
    //Memory Usage: 79.9 MB, less than 60.67% of Java online submissions for Contains Duplicate II.
    //Time: O(N*K); Space:O(1)
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        for (int i = 1, left = 0; i < nums.length; i++) {
            if (i - left > k) left++;
            for (int j = left; j < i; j++)
                if (nums[j] == nums[i]) return true;
        }
        return false;
    }

    //2.Set
    //Runtime: 48 ms, faster than 57.32% of Java online submissions for Contains Duplicate II.
    //Memory Usage: 79.2 MB, less than 63.78% of Java online submissions for Contains Duplicate II.
    //Time: O(N); Space:O(K)
    public boolean containsNearbyDuplicate_2(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i])) return true;
            if (set.size() > k) set.remove(nums[i - k]);
        }
        return false;
    }

    //1. HashMap
    //Runtime: 20 ms, faster than 88.22% of Java online submissions for Contains Duplicate II.
    //Memory Usage: 55.3 MB, less than 84.62% of Java online submissions for Contains Duplicate II.
    //Time: O(N); Space:O(N)
    public boolean containsNearbyDuplicate_1(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (i - map.getOrDefault(nums[i], -100_002) <= k) return true;
            map.put(nums[i], i);
        }
        return false;
    }
}
