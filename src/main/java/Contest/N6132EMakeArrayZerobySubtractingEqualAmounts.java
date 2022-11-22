package Contest;

import java.util.*;

/**
 * You are given a non-negative integer array nums. In one operation, you must:
 *
 * Choose a positive integer x such that x is less than or equal to the smallest non-zero element in nums.
 * Subtract x from every positive element in nums.
 * Return the minimum number of operations to make every element in nums equal to 0.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,5,0,3,5]
 * Output: 3
 * Explanation:
 * In the first operation, choose x = 1. Now, nums = [0,4,0,2,4].
 * In the second operation, choose x = 2. Now, nums = [0,2,0,0,2].
 * In the third operation, choose x = 2. Now, nums = [0,0,0,0,0].
 * Example 2:
 *
 * Input: nums = [0]
 * Output: 0
 * Explanation: Each element in nums is already 0 so no operations are needed.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 */

/**
 *
 * 问题可以被转换成：问数组里，有多少个不同的数字。
 * 每次拿最小的出来减，相当于每个数都要减自身一次。所以就是求"不同数的个数"。
 *
 */
//2357. Make Array Zero by Subtracting Equal Amounts
public class N6132EMakeArrayZerobySubtractingEqualAmounts {

    public static void main(String[] args){
        int res = new N6132EMakeArrayZerobySubtractingEqualAmounts().minimumOperations(new int[]{0,4,0,2,4});
        System.out.println(res);
    }

    public int minimumOperations_set(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int a: nums)
            if (a > 0)
                set.add(a);
        return set.size();
    }

    public int minimumOperations_hashmap(int[] nums) {
        Map<Integer, Integer> mp = new HashMap<>();
        for (int it : nums)
            if (it != 0) mp.put(it, 0);
        return mp.size();
    }

    // 最差的一个。
    public int minimumOperations(int[] nums) {
        int res = 0;
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int n : nums)
            if (n > 0) treeSet.add(n);

        while (!treeSet.isEmpty()) {
            int min = treeSet.first();
            res++;

            TreeSet<Integer> treeSet2 = new TreeSet<>();
            while (!treeSet.isEmpty()) {
                int tmp = treeSet.pollFirst() - min;
                if (tmp > 0) treeSet2.add(tmp);
            }
            treeSet = treeSet2;
        }
        return res;
    }
}
