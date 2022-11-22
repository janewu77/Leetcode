package Contest;



import java.util.PriorityQueue;

import static java.time.LocalTime.now;


/**
 * You are given a 0-indexed array nums comprising of n non-negative integers.
 *
 * In one operation, you must:
 *
 * Choose an integer i such that 1 <= i < n and nums[i] > 0.
 * Decrease nums[i] by 1.
 * Increase nums[i - 1] by 1.
 * Return the minimum possible value of the maximum integer of nums after performing any number of operations.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,7,1,6]
 * Output: 5
 * Explanation:
 * One set of optimal operations is as follows:
 * 1. Choose i = 1, and nums becomes [4,6,1,6].
 * 2. Choose i = 3, and nums becomes [4,6,2,5].
 * 3. Choose i = 1, and nums becomes [5,5,2,5].
 * The maximum integer of nums is 5. It can be shown that the maximum number cannot be less than 5.
 * Therefore, we return 5.
 * Example 2:
 *
 * Input: nums = [10,1]
 * Output: 10
 * Explanation:
 * It is optimal to leave nums as is, and since 10 is the maximum value, we return 10.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 2 <= n <= 105
 * 0 <= nums[i] <= 109
 */

//2439. Minimize Maximum of Array
public class N6210MMinimizeMaximumofArray {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun(542, new int[]{97,777,495,796,192,606,6,667,792,119,275,241,277,404,983});//,775,206,147,422,377,422,370,427,881,29,39,760,173,68,972,231,92,945,42,745,821,697,95,634,596,544,780,167,329,811,908,764,536,633,270,48,540,323,743,844,92,423,176,693,785,535,569,810,360,128,794,53,703,549});

        doRun(4,  new int[]{3,2,7,1,6});
//        doRun(16,  new int[]{13,13,20});
        doRun(16,  new int[]{13,13,20,0,8,9,9});

        doRun(5,  new int[]{3,7,1,6});
        doRun(10,  new int[]{10,1});
        doRun(6,  new int[]{1,10});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[] nums) {
        int res = new N6210MMinimizeMaximumofArray()
                .minimizeArrayValue(nums);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //2.
    //Runtime: 10 ms, faster than 88.89% of Java online submissions for Minimize Maximum of Array.
    //Memory Usage: 82.9 MB, less than 11.11% of Java online submissions for Minimize Maximum of Array.
    public int minimizeArrayValue(int[] nums) {
        long res = 0,  sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            //if (res >= nums[i]) continue;
            res = Math.max(res, (long)Math.ceil(sum / (i + 1.0)));
            //res = Math.max(res, (sum + i) / (i + 1));
        }
        return (int)res;
    }

    //1.brute force
    //TLE
    public int minimizeArrayValue_1(int[] nums) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> b[0] - a[0] == 0 ? a[1] - b[1] : b[0] - a[0]);
        int[][] list = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++){
            list[i] = new int[]{nums[i], i};
            heap.add(list[i]);
        }

        while(!heap.isEmpty()){
            if (heap.peek()[1] == 0) break;
            int[] node = heap.poll();
            int i = node[1];

            int[] preNode = list[i - 1];
            heap.remove(preNode);

            node[0] = (nums[i - 1] + nums[i]) / 2;
            preNode[0] = (int)Math.ceil((nums[i - 1] + nums[i]) / 2.0d);
            nums[i]= node[0];
            nums[i - 1]= preNode[0];
            heap.add(node);
            heap.add(preNode);
        }
        return heap.peek()[0];
    }



}
