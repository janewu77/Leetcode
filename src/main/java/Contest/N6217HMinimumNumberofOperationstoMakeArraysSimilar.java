package Contest;

import java.util.*;

import static java.time.LocalTime.now;

/**
 * You are given two positive integer arrays nums and target, of the same length.
 *
 * In one operation, you can choose any two distinct indices i and j where 0 <= i, j < nums.length and:
 *
 * set nums[i] = nums[i] + 2 and
 * set nums[j] = nums[j] - 2.
 * Two arrays are considered to be similar if the frequency of each element is the same.
 *
 * Return the minimum number of operations required to make nums similar to target. The test cases are generated such that nums can always be similar to target.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [8,12,6], target = [2,14,10]
 * Output: 2
 * Explanation: It is possible to make nums similar to target in two operations:
 * - Choose i = 0 and j = 2, nums = [10,12,4].
 * - Choose i = 1 and j = 2, nums = [10,14,2].
 * It can be shown that 2 is the minimum number of operations needed.
 * Example 2:
 *
 * Input: nums = [1,2,5], target = [4,1,3]
 * Output: 1
 * Explanation: We can make nums similar to target in one operation:
 * - Choose i = 1 and j = 2, nums = [1,4,3].
 * Example 3:
 *
 * Input: nums = [1,1,1,1,1], target = [1,1,1,1,1]
 * Output: 0
 * Explanation: The array nums is already similiar to target.
 *
 *
 * Constraints:
 *
 * n == nums.length == target.length
 * 1 <= n <= 105
 * 1 <= nums[i], target[i] <= 106
 * It is possible to make nums similar to target.
 */

/**
 * H = [time: 120+
 */
//2449. Minimum Number of Operations to Make Arrays Similar
public class N6217HMinimumNumberofOperationstoMakeArraysSimilar {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        //doRun(true, "abaccb", new int[]{1,2,3});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, String beginWord) {
//        int res = new N127HWordLadder().ladderLength(beginWord);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //2.sort
    //Runtime: 52 ms, faster than 100.00% of Java online submissions for Minimum Number of Operations to Make Arrays Similar.
    //Memory Usage: 60.1 MB, less than 100.00% of Java online submissions for Minimum Number of Operations to Make Arrays Similar.
    //Time: O(N * logN); Space: O(N)
    public long makeSimilar(int[] nums, int[] target) {
        Arrays.sort(nums);
        Arrays.sort(target);

        List<Integer> listEvenNums = new ArrayList<>();
        List<Integer> listOddNums = new ArrayList<>();
        List<Integer> listEvenTarget = new ArrayList<>();
        List<Integer> listOddTarget = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) listEvenNums.add(nums[i]);
            else listOddNums.add(nums[i]);

            if (target[i] % 2 == 0) listEvenTarget.add(target[i]);
            else listOddTarget.add(target[i]);
        }

        long res = 0L;
        for (int i = 0; i < listEvenNums.size(); i++)
            res += Math.abs(listEvenNums.get(i) - listEvenTarget.get(i));

        for (int i = 0; i < listOddNums.size(); i++)
            res += Math.abs(listOddNums.get(i) - listOddTarget.get(i));

        return res >> 2;
    }

    //1.PriorityQueue
    //Runtime: 334 ms, faster than 25.00% of Java online submissions for Minimum Number of Operations to Make Arrays Similar.
    //Memory Usage: 94 MB, less than 75.00% of Java online submissions for Minimum Number of Operations to Make Arrays Similar.
    public long makeSimilar_1(int[] nums, int[] target) {

        Queue<Integer> queueEvenNums = new PriorityQueue<>();
        Queue<Integer> queueOddNums = new PriorityQueue<>();
        Queue<Integer> queueEvenTarget = new PriorityQueue<>();
        Queue<Integer> queueOddTarget = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) queueEvenNums.add(nums[i]);
            else queueOddNums.add(nums[i]);

            if (target[i] % 2 == 0) queueEvenTarget.add(target[i]);
            else queueOddTarget.add(target[i]);
        }

        long res = 0l;
        while (!queueEvenNums.isEmpty()) {
            int x = queueEvenNums.poll() - queueEvenTarget.poll();
            if (x > 0) res += x >> 1;
        }
        while (!queueOddNums.isEmpty()) {
            int x = queueOddNums.poll() - queueOddTarget.poll();
            if (x > 0) res += x >> 1;
        }
        return res;
    }





}
