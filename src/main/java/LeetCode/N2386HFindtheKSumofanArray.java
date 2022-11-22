package LeetCode;


import java.util.*;

import static java.time.LocalTime.now;

/**
 * You are given an integer array nums and a positive integer k. You can choose any subsequence of the array and sum all of its elements together.
 *
 * We define the K-Sum of the array as the kth largest subsequence sum that can be obtained (not necessarily distinct).
 *
 * Return the K-Sum of the array.
 *
 * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
 *
 * Note that the empty subsequence is considered to have a sum of 0.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,4,-2], k = 5
 * Output: 2
 * Explanation: All the possible subsequence sums that we can obtain are the following sorted in decreasing order:
 * - 6, 4, 4, 2, 2, 0, 0, -2.
 * The 5-Sum of the array is 2.
 * Example 2:
 *
 * Input: nums = [1,-2,3,4,-10,12], k = 16
 * Output: 10
 * Explanation: The 16-Sum of the array is 10.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 105
 * -109 <= nums[i] <= 109
 * 1 <= k <= min(2000, 2n)
 */

/**
 * H - [time: 120-
 */
public class N2386HFindtheKSumofanArray {


    public static void main(String[] args) {

        System.out.println(now());
        int[] data;

        System.out.println("========doRun_demo==========");

        data = new int[]{2,4,-2};
        doRun_demo(2, data, 5);

        data = new int[]{1,-2,3,4,-10,12};
        doRun_demo(10, data, 16);

        data = new int[]{1,-1};
        doRun_demo(1, data, 1);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun_demo(long expect, int[] nums, int k) {
        long res = new N2386HFindtheKSumofanArray().kSum(nums, k);
//        String res = comm.toString(res1);
//        String res = Arrays.stream(res1).mapToObj(i-> String.valueOf(i)).collect(Collectors.joining(","));
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    //from discuss
    //算法与_1类似。 不同之处是。找最小和序列的方式这个更优秀！
    //Runtime: 88 ms, faster than 100.00% of Java online submissions for Find the K-Sum of an Array.
    //Memory Usage: 79.8 MB, less than 100.00% of Java online submissions for Find the K-Sum of an Array.
    //Time: O(N + NlgN + KlogK); Space:O(K)
    public long kSum(int[] nums, int k) {
        long res = 0, max = 0;
        //Time:O(N)
        for (int i = 0; i < nums.length; i++){
            if (nums[i] > 0) max += nums[i]; // take all positive values
            else nums[i] = -nums[i]; // make all non-negative
        }
        if (k == 1) return max;

        //Time:O(NlgN)
        Arrays.sort(nums);

        //Space:O(K)
        Queue<long[]> minheap = new PriorityQueue<>(Comparator.comparingLong(o -> o[0]));
        minheap.offer(new long[]{nums[0], 0});

        //Time: O(KlogK)
        while (--k > 0){ // construct the smallest subsequence value in a smart way
            long[] top = minheap.poll();
            int i = (int)top[1]++;
            long val = top[0];
            res = val;
            if (i < nums.length - 1){ // each item branches out into 2
                top[0] += nums[i + 1];
                minheap.offer(new long[]{val - nums[i] + nums[i + 1], i + 1});
                minheap.offer(top);
            }
        }
        return max - res;
    }


    //最大sum，一定是所有正数之后；
    //负数对和的影响 是加上它或者不加它。 （和正数其实是一样的），所以把负数换成正数。然后排序。
    //把问题转换成： 前k-1个最小和。
    //用累加和（用list保存），找最前n-1个最小的和。
    //Runtime: 285 ms, faster than 100.00% of Java online submissions for Find the K-Sum of an Array.
    //Memory Usage: 138.5 MB, less than 33.33% of Java online submissions for Find the K-Sum of an Array.
    //Dp
    //Time: O(N + NlgN + K*lg(N)); Space: O(K)
    public long kSum_1(int[] nums, int k) {
        long max = 0;
        //Time:O(N)
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) max += nums[i];
            else nums[i] = -nums[i];
        }
        if (k == 1) return max;

        //Time:O(NlgN)
        Arrays.sort(nums);

        int idx = 0;
        //Space: O(K)
        List<Long> list = new ArrayList<>();
        list.add(Long.valueOf(nums[idx++]));

        //Time: O(lg(N)*K)
        while (idx < nums.length
                && (list.size() < k - 1  || nums[idx] + list.get(0) <= list.get(list.size()-1))
        ){
            List<Long> list2 = new ArrayList<>();
            long currN = Long.valueOf(nums[idx++]);
            list2.add(currN);
            for (Long n : list)  list2.add(n + currN);
            merge(list, list2, k - 1);
        }
        return max - list.get(k - 2);
    }

    //Time: O(K)
    void merge(List<Long> list, List<Long> list2, int k){
        int i = 0, j = 0;

        while (i < Math.min(k, list.size()) && j < list2.size()){
            if (list.get(i) <= list2.get(j)) i++;
            else list.add(i++, list2.get(j++));
        }

        while (i < k && j < list2.size()) {
            list.add(list2.get(j++));
            i++;
        }
    }
}
