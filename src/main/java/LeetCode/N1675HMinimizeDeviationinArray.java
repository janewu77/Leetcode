package LeetCode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

import static java.time.LocalTime.now;

/**
 * You are given an array nums of n positive integers.
 *
 * You can perform two types of operations on any element of the array any number of times:
 *
 * If the element is even, divide it by 2.
 * For example, if the array is [1,2,3,4], then you can do this operation on the last element, and the array will be [1,2,3,2].
 * If the element is odd, multiply it by 2.
 * For example, if the array is [1,2,3,4], then you can do this operation on the first element, and the array will be [2,2,3,4].
 * The deviation of the array is the maximum difference between any two elements in the array.
 *
 * Return the minimum deviation the array can have after performing some number of operations.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4]
 * Output: 1
 * Explanation: You can transform the array to [1,2,3,2], then to [2,2,3,2], then the deviation will be 3 - 2 = 1.
 * Example 2:
 *
 * Input: nums = [4,1,5,20,3]
 * Output: 3
 * Explanation: You can transform the array after two operations to [4,2,5,5,3], then the deviation will be 5 - 2 = 3.
 * Example 3:
 *
 * Input: nums = [2,10,8]
 * Output: 3
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 2 <= n <= 5 * 104
 * 1 <= nums[i] <= 109
 */
public class N1675HMinimizeDeviationinArray {


    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");

        doRun(2, new int[]{3,4,10});
        doRun(1, new int[]{3, 5});

        doRun(1, new int[]{2,8,6,1,6});
        doRun(1, new int[]{4,10});

        doRun(3, new int[]{2,10,8});
        doRun(3, new int[]{4,1,5,20,3});
        doRun(1, new int[]{1,2,3,4});

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, int[] nums) {
        int res = new N1675HMinimizeDeviationinArray().minimumDeviation(nums);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //3.Sort + array
    //Runtime: 99ms 90%; Space: 52.5MB 20%
    //Time: O(N * logN); Space: O(1)
    public int minimumDeviation(int[] nums) {
        for (int i = nums.length - 1; i >= 0; i--)
           if (nums[i] % 2 == 1) nums[i] *= 2;

        int[] data = new int[2_000_000];
        Arrays.sort(nums);
        int res = nums[nums.length - 1] - nums[0];
        int idx = 0;
        for (int i = nums.length - 1; i >= 0; i--)
            data[idx++] = nums[i];

        int q1Head = 0, q1End = idx - 1;
        int q2Head = idx + 1, q2End = idx;
        while (true) {
            int max = Math.max(data[q1Head], data[q2Head]);
            if (data[q1Head] >= data[q2Head]) {
                if (++q1Head > q1End) q1Head = q1End = idx;
            } else q2Head++;

            int min = data[q2End];
            if (data[q1End] != 0 && data[q2End] != 0) {
                min = Math.min(data[q1End], data[q2End]);
            } else if (data[q1End] != 0) {
                min = data[q1End];
            }
            res = Math.min(res, max - min);

            if (max % 2 == 1) break;
            data[++q2End] = max >> 1;
        }
        return res;
    }


    //2.TreeSet
    //Runtime: 127ms 62%; Memory: 52.5MB 20.75%
    //Time: O(N * logN); Space: O(N)
    public int minimumDeviation_2(int[] nums) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int num : nums) {
            if (num % 2 == 1) treeSet.add(num << 1);
            else treeSet.add(num);
        }

        int res = treeSet.last() - treeSet.first();
        while (treeSet.last() % 2 == 0) {
            treeSet.add(treeSet.last() >> 1);
            treeSet.remove(treeSet.last());
            res = Math.min(res, treeSet.last() - treeSet.first());
        }
        return res;
    }


    //1.PriorityQueue
    //Runtime: 670ms 26%; Memory: 50.8MB 50%
    //Time: O(N * logN); Space: O(N)
    public int minimumDeviation_1(int[] nums) {
        int min = Integer.MAX_VALUE;
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(o -> -o));
        for (int num : nums) {
            if (num % 2 == 1) {
                heap.add(num << 1);
                min = Math.min(min, num << 1);
            } else {
                heap.add(num);
                min = Math.min(min, num);
            }
        }

        int res = Integer.MAX_VALUE;
        while (!heap.isEmpty()) {
            int max = heap.poll();
            res = Math.min(res, max - min);
            if (max % 2 == 1) break;
            max >>= 1;
            min = Math.min(min, max);
            heap.add(max);
        }

        return res;
    }
}
