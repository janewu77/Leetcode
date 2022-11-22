package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array nums and an integer k, return the number of good subarrays of nums.
 *
 * A good array is an array where the number of different integers in that array is exactly k.
 *
 * For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
 * A subarray is a contiguous part of an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,1,2,3], k = 2
 * Output: 7
 * Explanation: Subarrays formed with exactly 2 different integers:
 * [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
 * Example 2:
 *
 * Input: nums = [1,2,1,3,4], k = 3
 * Output: 3
 * Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i], k <= nums.length
 */

/**
 * H - [time: 120+
 *
 *  - 二个解法：
 *  - 1。 用二个互减。 比如。 6及6岁以下的人数 - 5及5岁以下的 = 6岁的人数
 *  - 2。 用二个window (待整理）
 *
 */
public class N992HSubarrayswithKDifferentIntegers {


    public static void main(String[] args) {
        int[] data;

        data  = new int[]{1,2,1,2};
        doRun(6, data, 2);

        data  = new int[]{1,2,1,2,3};
        doRun(7, data, 2);

        data  = new int[]{1,2,1,3,4};
        doRun(3, data, 3);

        data  = new int[]{1,2,3,4,4};
        doRun(3, data, 3);

        data  = new int[]{1,2,3,4,4,4,4};
        doRun(5, data, 3);

        data  = new int[]{2,1,1,1,2};
        doRun(8, data, 1);

        data  = new int[]{1,2,1,2,1,2,3};
        doRun(16, data, 2);

    }

    static private void doRun(int expected, int[] nums, int k) {
        int res = new N992HSubarrayswithKDifferentIntegers().subarraysWithKDistinct(nums, k);
        System.out.println("["+(expected == res)+"]expect:" + expected + " res:" + res);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    //from @63_vishakha
    //The problem can be simply solved by counting the subarrays in range of 1 - k and
    // then subtracting the subarrays in range of 1 - (k - 1) with two pointers for both subarray count.
    //精彩！ 这个太妙了。 性能差一些，主要是有大部分是重复算了。但易读。
    //Runtime: 83 ms, faster than 48.97% of Java online submissions for Subarrays with K Different Integers.
    //Memory Usage: 74.5 MB, less than 11.48% of Java online submissions for Subarrays with K Different Integers.
    public int subarraysWithKDistinct_3(int[] nums, int k) {
        //相减就是把长度不到K的那些子串减掉了。
        return  atMostCount(nums, k) - atMostCount(nums, k - 1);
    }
    //统计子串的个数：子串内 不同字母的数量 小于K。
    //比如 K = 2时， 会统计出 长度为1 + 长度为2的字串
    //count for at-most k distinct
    private int atMostCount(int[] nums, int k){
        HashMap<Integer, Integer> hm = new HashMap<>();

        int left = 0, right = 0, ans = 0;

        while(right < nums.length){
            hm.put(nums[right] , hm.getOrDefault(nums[right], 0) + 1);

            while(hm.size() == k + 1){
                hm.put(nums[left], hm.get(nums[left]) - 1);
                if(hm.get(nums[left]) == 0)
                    hm.remove(nums[left]);
                left++;
            }
            //主要是这句，相当于 累加 = 1 + 2 + 3 + 4 。
            //长度 n +1 后，新增的字母可以产生 n + 1 个新组合：（ n个接在上一个字母后面 + 1个自己就是一个子串。）
            ans += right - left + 1;
            right++;
        }
        return ans;
    }


    //从solution里看来的。 todo:待理解双window
    //Runtime: 58 ms, faster than 70.08% of Java online submissions for Subarrays with K Different Integers.
    //Memory Usage: 48.2 MB, less than 86.92% of Java online submissions for Subarrays with K Different Integers.
    //Time：O(N); Space：O(N)
    public int subarraysWithKDistinct(int[] nums, int k) {
        Window window1 = new Window();
        Window window2 = new Window();
        int res = 0, left1 = 0, left2 = 0;

        for (int right = 0; right < nums.length; ++right) {
            window1.add(nums[right]);
            window2.add(nums[right]);

            while (window1.size() > k)
                window1.remove(nums[left1++]);

            while (window2.size() >= k)
                window2.remove(nums[left2++]);

            //可以读 subarraysWithKDistinct_3 来理解。
            res += left2 - left1;
        }

        return res;
    }

    //Time Limit Exceeded
    public int subarraysWithKDistinct_1(int[] nums, int k) {
        int c = 0;
        int left = 0, right = 0;
        Window window = new Window();

        while (right < nums.length) {
            // expand right
            window.add(nums[right++]);

            // expand left
            while(left > 0 && window.size() <= k) {
                window.add(nums[--left]);
            }

            // downsize from left
            while (left >= 0 && left < right){
                if (window.size() == k ) c++;
                window.remove(nums[left]);
                left++;
            }
        }
        return c;
    }

    class Window{
        private Map<Integer, Integer> data;
        private int win_size = 0;
        Window() {
            data = new HashMap();
            win_size = 0;
        }

        void add(int x) {
            data.put(x, data.getOrDefault(x, 0) + 1);
            if (data.get(x) == 1)
                win_size++;
        }

        void remove(int x) {
            data.put(x, data.get(x) - 1);
            if (data.get(x) == 0)
                win_size--;
        }

        int size() {
            return win_size;
        }
    }

}
