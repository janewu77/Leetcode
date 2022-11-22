package LeetCode;

import java.util.*;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

/**
 *
 * You are given an array of integers nums, there is a sliding window of size k which is moving
 * from the very left of the array to the very right. You can only see the k numbers in the window.
 * Each time the sliding window moves right by one position.
 *
 * Return the max sliding window.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 */

/**
 * H - [time: 25-
 */
public class N239HSlidingWindowMaximum {


    public static void main(String[] args) {

        System.out.println(now());
        int[] data;

//        data = new int[]{1, -1};
//        doRun("1,-1", data, 1);

        data = new int[]{-7,-8,7,5,7,1,6,0};
        doRun("7,7,7,7,7", data, 4);


        data = new int[]{1,3,-1,-3,5,3,6,7};
        doRun("3,3,5,5,6,7", data, 3);

        data = new int[]{1};
        doRun("1", data, 1);

        System.out.println(now());
        System.out.println("==================");
    }
    static private void doRun(String expect, int[] nums, int k) {
        int[] res1 = new N239HSlidingWindowMaximum().maxSlidingWindow(nums, k);
//        String res = comm.toString(res1);
        String res = Arrays.stream(res1).mapToObj(i-> String.valueOf(i)).collect(Collectors.joining(","));
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    //Runtime: 16 ms, faster than 99.15% of Java online submissions for Sliding Window Maximum.
    //Memory Usage: 144.7 MB, less than 69.71% of Java online submissions for Sliding Window Maximum.
    //Dynamic programming
    //Time: O(N) ; Space: O(N)
    //Time: O(N + N-K) ; Space: O(N+N)
    public int[] maxSlidingWindow_4(int[] nums, int k) {
        if (k == 1) return nums;
        int n = nums.length;

        int [] left = new int[n];//Space: O(N)
        left[0] = nums[0];
        int [] right = new int[n];//Space: O(N)
        right[n - 1] = nums[n - 1];

        //Time: O(N)
        for (int i = 1; i < n; i++) {
            // from left to right
            if (i % k == 0) left[i] = nums[i];  // block_start
            else left[i] = Math.max(left[i - 1], nums[i]);

            // from right to left
            int j = n - i - 1;
            if ((j + 1) % k == 0) right[j] = nums[j];  // block_end
            else right[j] = Math.max(right[j + 1], nums[j]);
        }

        //Time: O(N-K)
        int [] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++)
            output[i] = Math.max(left[i + k - 1], right[i]);

        return output;
    }


    //Runtime: 33 ms, faster than 92.39% of Java online submissions for Sliding Window Maximum.
    //Memory Usage: 59.7 MB, less than 91.86% of Java online submissions for Sliding Window Maximum.
    //Deque
    //Time: O(N) ; Space: O(K)
    //Time: O(2N) ; Space: O(K)
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1) return nums;

        // Time: O(2N), Space: O(K)
        int [] output = new int[nums.length - k + 1];
        Deque<Integer> deq = new ArrayDeque<>(); //Space:O(K)
        for (int i = 0; i < nums.length; i++) {

            //If an element in the deque and it is out of i-(k-1), we discard them.
            if (!deq.isEmpty() && deq.getFirst() == i - k)
                deq.removeFirst();

            //discard elements smaller than a[i] from the tail.
            while (!deq.isEmpty() && nums[i] > nums[deq.getLast()])
                deq.removeLast();

            deq.addLast(i);

            if (i >= k - 1)
                output[i - k + 1] = nums[deq.getFirst()];
        }
        return output;
    }


    //Runtime: 273 ms, faster than 9.96% of Java online submissions for Sliding Window Maximum.
    //Memory Usage: 59.3 MB, less than 93.34% of Java online submissions for Sliding Window Maximum.
    //TreeSet + HashMap
    //Time:O(N*log(K); Space: O(2K + (N - K))
    public int[] maxSlidingWindow_2(int[] nums, int k) {
        TreeSet<Integer> treeSet = new TreeSet<>((o1, o2) -> o2 - o1);//Space:O(K)
        Map<Integer, Integer> map = new HashMap<>();//Space:O(K)

        int[] res = new int[nums.length - k + 1];//Space:O(N - K)
        int i = 0;
        while (i < k){
            int n = nums[i++];
            if (map.containsKey(n)) map.put(n, map.get(n) + 1);
            else{
                map.put(n, 1);
                treeSet.add(n);
            }
        }
        int resIdx = 0;
        while (i < nums.length) {

            res[resIdx++] = treeSet.first();

            int value = nums[i - k];
            if (map.get(value) == 1){
                treeSet.remove(value);
                map.remove(value);
            }else{
                map.put(value, map.get(value) - 1);
            }

            int n = nums[i++];
            if (map.containsKey(n)) map.put(n, map.get(n) + 1);
            else{
                map.put(n, 1);
                treeSet.add(n);
            }
        }
        res[resIdx++] = treeSet.first();
        return res;
    }

    //Runtime: 374 ms, faster than 6.83% of Java online submissions for Sliding Window Maximum.
    //Memory Usage: 62.2 MB, less than 86.18% of Java online submissions for Sliding Window Maximum.
    //TreeSet
    //Time:O(N*log(K); Space: O(K + (N - K))
    public int[] maxSlidingWindow_1(int[] nums, int k) {
        //Space:O(K)
        TreeSet<int[]> treeSet = new TreeSet<>((o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            return o2[0] - o1[0];
        });

        int[] res = new int[nums.length - k + 1]; //Space:O(N - K)
        int i = 0;
        while (i < k)
            treeSet.add(new int[]{nums[i], i++});

        int resIdx = 0;
        //Time:O(N*log(K)
        while (i < nums.length) {
            res[resIdx++] = treeSet.first()[0];

            //Time:O(2*log(K)
            treeSet.remove(new int[]{nums[i - k], i-k});
            treeSet.add(new int[]{nums[i], i++});
        }

        res[resIdx++] = treeSet.first()[0];
        return res;
    }
}
