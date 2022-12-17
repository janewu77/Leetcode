package LeetCode;


import java.util.*;

import static java.time.LocalTime.now;

/**
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 *
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements
 * without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence
 * of the array [0,3,1,6,2,2,7].
 *
 *
 * Example 1:
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

 *
 * Example 2:
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 *
 * Example 3:
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 *
 *
 * Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 */
public class N300MLongestIncreasingSubsequence {

    public static void main(String[] args){
        int[] data;
        System.out.println(now());

        data = new int[]{5,4,4,5,6,7}; //4567
        doRun(data, 4);

        data = new int[]{0,1};
        doRun(data, 2);

        data = new int[]{0};
        doRun(data, 1);

        data = new int[]{1,0};
        doRun(data, 1);

        data = new int[]{7,7,7,7,7,7,7};
        doRun(data, 1);

        data = new int[]{10,9,2,5,3,7,101,18};
        doRun(data, 4);

        data = new int[]{0,1,0,3,2,3}; //0123
        doRun(data, 4);

        data = new int[]{1,3,6,7,9,4,10,5,6}; //0123
        doRun(data, 6);

        data = new int[]{1,3,6,7,9,4,10,5,6,7,8,9,10,11}; //0123
        doRun(data, 10);

        data = new int[]{-1,-2,-3,-4,-5,-6,-7,-8,-9,-10,-11,-12,-13,-14,-15,-16,-17,-18,-19,-20};
        doRun(data, 1);
        System.out.println(now());
    }
    static private int c = 1;
    private static void doRun(int[] data, int expected){
        int r = new N300MLongestIncreasingSubsequence().lengthOfLIS(data);
        System.out.println("["+(expected == r)+"]"+(c++)+". expected:"+expected +". result:"+r);
    }


    //2.Binary Search
    //Runtime: 99 ms, faster than 31.12% of Java online submissions for Longest Increasing Subsequence.
    //Memory Usage: 46.2 MB, less than 13.88% of Java online submissions for Longest Increasing Subsequence.
    //Time: O(N * logN); Space:O(N)
    public int lengthOfLIS_2(int[] nums) {
        int[] counts = new int[nums.length];
        counts[0] = 1;

        List<int[]> list = new ArrayList<>();
        list.add(new int[]{nums[0], 0, 1});//value:idx:count

        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            int tmp = counts[i];

            int[] node = new int[]{nums[i], i, 1};
            int idx = Collections.binarySearch(list, node, (a, b)-> a[0] - b[0] == 0 ? b[1] - a[1] : a[0] - b[0]);
            idx = idx == -1 ? 0 : -idx - 1;
            for(int j = 0; j < idx; j++) tmp = Math.max(tmp, list.get(j)[2]);

            counts[i] = tmp + 1;
            res = Math.max(counts[i], res);
            node[2] = counts[i];
            list.add(idx, node);
        }
        return res;
    }


    //2022.10.11 改写成forward
    //1.dp bottom-up
    //Runtime: 55 ms, 72%; 41.7MB 94%
    //Time:O(N * N); Space:O(N)
    public int lengthOfLIS_1(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;

        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            int currCount = dp[i];
            for (int j = 0; j < i; j++)
                if (nums[j] < nums[i]) currCount = Math.max(currCount, dp[j]);
            dp[i] = currCount + 1;
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    //2022.07
    public int lengthOfLIS_1_1(int[] nums) {
        int[] counts = new int[nums.length];
        counts[nums.length - 1] = 1;

        int maxCount = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            int tmp = 0;//counts[i];
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[j] > nums[i]) tmp = Math.max(tmp, counts[j]);
            }
            counts[i] = tmp + 1;
            maxCount = Math.max(counts[i], maxCount);
        }
        return maxCount;
    }

    //updated 2022/12/17
    //from Discussion
    //Runtime: 4ms, 96%; Memory: 42.1MB 82%
    //Time: O(N * logN); Space: O(N)
    public int lengthOfLIS(int[] nums) {
        ArrayList<Integer> window = new ArrayList<>();
        window.add(nums[0]);

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num > window.get(window.size() - 1)) {
                window.add(num);
            } else {
                // Find the first element in sub that is greater than or equal to num

                // A.iteration find
                //  int j = 0;
                //  while (num > sub.get(j)) j++;

                // B.binary search
                int j = Collections.binarySearch(window, num);
                if (j < 0) j = -j - 1;

                window.set(j, num);
            }
        }
        return window.size();
    }


}
