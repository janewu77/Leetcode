package Contest;


import java.util.HashMap;
import java.util.Map;

import static java.time.LocalTime.now;

/**
 *
 * You are given a 0-indexed integer array nums. A pair of indices (i, j) is a bad pair
 * if i < j and j - i != nums[j] - nums[i].
 *
 * Return the total number of bad pairs in nums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,1,3,3]
 * Output: 5
 * Explanation: The pair (0, 1) is a bad pair since 1 - 0 != 1 - 4.
 * The pair (0, 2) is a bad pair since 2 - 0 != 3 - 4, 2 != -1.
 * The pair (0, 3) is a bad pair since 3 - 0 != 3 - 4, 3 != -1.
 * The pair (1, 2) is a bad pair since 2 - 1 != 3 - 1, 1 != 2.
 * The pair (2, 3) is a bad pair since 3 - 2 != 3 - 3, 1 != 0.
 * There are a total of 5 bad pairs, so we return 5.
 * Example 2:
 *
 * Input: nums = [1,2,3,4,5]
 * Output: 0
 * Explanation: There are no bad pairs.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 */

/**
 * //2364. Count Number of Bad Pairs
 *
 * M - [time: 60+
 *
 *  - math
 */

public class N6142MCountNumberofBadPairs {

    public static void main(String[] args) {

        System.out.println(now());
        int[] candidates;

        candidates  = new int[]{4,1,3,3};
        doRun(5l,candidates);

        candidates  = new int[]{1,2,3,4,5};
        doRun(0l,candidates);


        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(long expect, int[] nums) {
        long res = new N6142MCountNumberofBadPairs().countBadPairs(nums);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Total= Valid + Invalid
    //( j - i ) != ( A[j] - A[i] ) 相当于  ( A[i] - i ) != ( A[j] - j )
    //Runtime: 59 ms, faster than 80.00% of Java online submissions for Count Number of Bad Pairs.
    //Memory Usage: 93.4 MB, less than 80.00% of Java online submissions for Count Number of Bad Pairs.
    //one pass
    //Time:O(N); Space:O(N)
    public long countBadPairs(int[] nums) {
        long res = 1l * nums.length * (nums.length - 1) / 2l;

        //count valid
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            int prev = map.getOrDefault(nums[i] - i, 0);
            res -= prev;
            map.put(nums[i] - i, prev + 1);
        }

        return res;
    }

}
