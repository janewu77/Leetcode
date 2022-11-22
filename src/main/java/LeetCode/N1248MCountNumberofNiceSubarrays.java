package LeetCode;

import static java.time.LocalTime.now;

/***
 * Given an array of integers nums and an integer k. A continuous
 * subarray is called nice if there are k odd numbers on it.
 *
 * Return the number of nice sub-arrays.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,2,1,1], k = 3
 * Output: 2
 * Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
 *
 *
 * Example 2:
 *
 * Input: nums = [2,4,6], k = 1
 * Output: 0
 * Explanation: There is no odd numbers in the array.
 * Example 3:
 *
 * Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * Output: 16
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 50000
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 */

/**
 * M - [time: 120+
 *  - slide window 但是又一次掉在了同一坑里。 反省。
 */
public class N1248MCountNumberofNiceSubarrays {

    public static void main(String[] args){
        int[] data;

        System.out.println(now());

        data = new int[]{1,1,2,1,1};
        doRun_demo(2, data, 3);

        data = new int[]{2,4,6};
        doRun_demo(0, data, 1);

        data = new int[]{2,2,2,1,2,2,1,2,2,2};
        doRun_demo(16, data, 2);
        data = new int[]{1,1,1,1,1};
        doRun_demo(5, data, 1);

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun_demo(int expect, int[] nums, int k) {
        int res = new N1248MCountNumberofNiceSubarrays().numberOfSubarrays(nums, k);
//        String res = comm.toString(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //Runtime: 8 ms, faster than 96.06% of Java online submissions for Count Number of Nice Subarrays.
    //Memory Usage: 71.5 MB, less than 81.68% of Java online submissions for Count Number of Nice Subarrays.
    //dp
    //DP[i] 表示 第i个奇数到i+1个奇数之前，数字个数。  ex: 7009 DP[1] = 3。第1个奇数7,到9之前，一共有3个数字.
    //进一步表示，有多少个子串。
    //Time: O(N) ; Space: O(N)
    public int numberOfSubarrays(int[] nums, int k) {
        int[] prefix = new int[nums.length + 1];
        prefix[0] = 1;
        int res = 0;
        int oddIdx = 0;

        for(int i = 0; i < nums.length; i++){
            oddIdx += nums[i] % 2;
            prefix[oddIdx]++;

            if (oddIdx >= k)
                res += prefix[oddIdx - k];
        }
        return res;
    }


    //Runtime: 12 ms, faster than 86.81% of Java online submissions for Count Number of Nice Subarrays.
    //Memory Usage: 70.6 MB, less than 83.97% of Java online submissions for Count Number of Nice Subarrays.
    //slide-window ：one pass [count window]
    //Time: O(N) ; Space: O(1)
    public int numberOfSubarrays_2(int[] nums, int goal) {
        int res = 0;
        int left = 0, right = 0;
        int count = 0;

        for (right = 0; right < nums.length; right++) {
            if (nums[right] % 2 == 1) {
                goal--;
                count = 0; //这里count重新开始计数。 解决困扰的右侧问题
            }
            while (goal == 0) {
                count++;
                goal += nums[left++] % 2;
                //if (nums[left++] % 2 == 1) goal++;
            }
            res += count;
        }
        return res;
    }

    //Runtime: 17 ms, faster than 66.30% of Java online submissions for Count Number of Nice Subarrays.
    //Memory Usage: 75.6 MB, less than 44.50% of Java online submissions for Count Number of Nice Subarrays.
    //slide - window
    //Time: O(2 * N * goal) ; Space: O(1)
    public int numberOfSubarrays_1(int[] nums, int goal) {
        return atMost(nums, goal) - atMost(nums, goal - 1);
    }

    private int atMost(int[] nums, int goal) {
        int res = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            goal -= nums[right] % 2;
            while (goal < 0)
                goal += nums[left++] % 2;
            res += right - left + 1;
        }
        return res;
    }


}
