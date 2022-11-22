package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a binary array nums and an integer goal,
 * return the number of non-empty subarrays with a sum goal.
 *
 * A subarray is a contiguous part of the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,0,1,0,1], goal = 2
 * Output: 4
 * Explanation: The 4 subarrays are bolded and underlined below:
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * Example 2:
 *
 * Input: nums = [0,0,0,0,0], goal = 0
 * Output: 15
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 3 * 104
 * nums[i] is either 0 or 1.
 * 0 <= goal <= nums.length
 */

/**
 * M - [time: 60+
 * 困难 和992一样
 */
public class N930MBinarySubarraysWithSum {


    public static void main(String[] args) {

        int[] data  = new int[]{1,0,1,0,1};
        doRun(4, data, 2);

        data  = new int[]{0,0,0,0,0};
        doRun(15, data, 0);

    }

    static private void doRun(int expect, int[] nums,  int goal) {
        int res = new N930MBinarySubarraysWithSum().numSubarraysWithSum(nums, goal);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //这个理解起来有点费力。 而且会想不起来。
    //todo:待理解双window
    //Runtime: 5 ms, faster than 65.82% of Java online submissions for Binary Subarrays With Sum.
    //Memory Usage: 55.3 MB, less than 72.64% of Java online submissions for Binary Subarrays With Sum.
    //three pointers
    public int numSubarraysWithSum(int[] A, int goal) {
        int iLo = 0, iHi = 0;
        int sumLo = 0, sumHi = 0;
        int ans = 0;

        for (int j = 0; j < A.length; ++j) {
            // While sumLo is too big, iLo++
            sumLo += A[j];
            while (iLo < j && sumLo > goal)
                sumLo -= A[iLo++];

            // While sumHi is too big, or equal and we can move, iHi++
            sumHi += A[j];
            while (iHi < j && (sumHi > goal || (sumHi == goal && A[iHi] == 0)))
                sumHi -= A[iHi++];

            if (sumLo == goal)
                ans += iHi - iLo + 1;
        }

        return ans;
    }

    //这个atMost理解起来有点费力。 而且会想不起来。
    //Runtime: 3 ms, faster than 86.95% of Java online submissions for Binary Subarrays With Sum.
    //Memory Usage: 55.7 MB, less than 63.97% of Java online submissions for Binary Subarrays With Sum.
    //slide window
    //time: O(N); Space: O(1)
    public int numSubarraysWithSum_3(int[] nums, int goal) {
        return atMost(nums, goal) - atMost(nums, goal - 1);
    }

    private int atMost(int[] nums, int goal) {
        if (goal < 0) return 0;
        int res = 0 ;
        int left = 0;

        for (int right = 0; right< nums.length; right++){
            goal -= nums[right];
            while (goal < 0) goal += nums[left++];
            res += right - left + 1;
        }
        return res;
    }


    //Base on numSubarraysWithSum_1 做的优化。 去掉了int[] sum.
    //Runtime: 18 ms, faster than 56.18% of Java online submissions for Binary Subarrays With Sum.
    //Memory Usage: 49.5 MB, less than 85.00% of Java online submissions for Binary Subarrays With Sum.
    //Prefix Sums
    //time: O(N); Space: O(N)
    public int numSubarraysWithSum_2(int[] nums, int goal) {
        int res = 0;

        Map<Integer, Integer> count = new HashMap<>();
        count.put(goal, 1); //for goal self

        int currSum = 0; //Prefix Sums
        for (int i = 0; i < nums.length; ++i){
            currSum += nums[i];

            res += count.getOrDefault(currSum, 0);

            //expect this value
            int expectedSum = currSum + goal;
            count.put(expectedSum, count.getOrDefault(expectedSum, 0) + 1);
        }
        return res;
    }


    //从网上看来的。理解如下：
    //1。先把prex sum 算好。
    //2。sum 之间的差就是target. 所以，对于已经访问过的节点。 加上target之后，保存&计数。
    //3。当未来有一个sum满足时，直接拿count.
    //Runtime: 34 ms, faster than 33.11% of Java online submissions for Binary Subarrays With Sum.
    //Memory Usage: 63.2 MB, less than 29.02% of Java online submissions for Binary Subarrays With Sum.
    //time: O(N + N); Space: O(N+N)
    public int numSubarraysWithSum_1(int[] nums, int goal) {
        int res = 0;

        int[] sum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; ++i)
            sum[i+1] = sum[i] + nums[i];

        Map<Integer, Integer> count = new HashMap<>();
        for (int s : sum){
            res += count.getOrDefault(s, 0);

            //把当前sum + goal 放入hashmap.  算成未来sum. 如果未来sum在列表中存在。则一定会拿到count.
            count.put(s + goal, count.getOrDefault(s + goal, 0) + 1);
        }

        return res;
    }

}
