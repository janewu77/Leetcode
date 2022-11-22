package Contest;

import java.util.*;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

/**
 * You are given a 0-indexed array of non-negative integers nums. For each integer in nums, you must find its respective second greater integer.
 *
 * The second greater integer of nums[i] is nums[j] such that:
 *
 * j > i
 * nums[j] > nums[i]
 * There exists exactly one index k such that nums[k] > nums[i] and i < k < j.
 * If there is no such nums[j], the second greater integer is considered to be -1.
 *
 * For example, in the array [1, 2, 4, 3], the second greater integer of 1 is 4, 2 is 3, and that of 3 and 4 is -1.
 * Return an integer array answer, where answer[i] is the second greater integer of nums[i].
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,4,0,9,6]
 * Output: [9,6,6,-1,-1]
 * Explanation:
 * 0th index: 4 is the first integer greater than 2, and 9 is the second integer greater than 2, to the right of 2.
 * 1st index: 9 is the first, and 6 is the second integer greater than 4, to the right of 4.
 * 2nd index: 9 is the first, and 6 is the second integer greater than 0, to the right of 0.
 * 3rd index: There is no integer greater than 9 to its right, so the second greater integer is considered to be -1.
 * 4th index: There is no integer greater than 6 to its right, so the second greater integer is considered to be -1.
 * Thus, we return [9,6,6,-1,-1].
 * Example 2:
 *
 * Input: nums = [3,3]
 * Output: [-1,-1]
 * Explanation:
 * We return [-1,-1] since neither integer has any integer greater than it.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 109
 */


//2454. Next Greater Element IV
public class N6227HNextGreaterElementIV {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun( "9,6,6,-1,-1", new int[]{2,4,0,9,6});

        doRun( "-1", new int[]{1});
        doRun( "-1,10,-1,-1,-1,-1", new int[]{18,0,18,10,20,0});
        doRun( "-1,10,-1,-1,-1,-1", new int[]{18,0,18,10,20,0});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(String expect, int[] nums) {
        int[] res1 = new N6227HNextGreaterElementIV().secondGreaterElement(nums);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //https://leetcode.com/problems/next-greater-element-iv/discuss/2756668/JavaC%2B%2BPython-One-Pass-Stack-Solution-O(n)
    //3.mono stack X 2
    //Runtime: 24 ms, faster than 100.00% of Java online submissions for Next Greater Element IV.
    //Memory Usage: 57.2 MB, less than 88.89% of Java online submissions for Next Greater Element IV.
    //Time: O(N); Space: O(N)
    public int[] secondGreaterElement(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);

        Deque<Integer> stack1 = new ArrayDeque<>(); //first
        Deque<Integer> stack2 = new ArrayDeque<>(); //second
        Deque<Integer> tmp = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {

            while (!stack2.isEmpty() && nums[i] > nums[stack2.peek()])
                res[stack2.pop()] = nums[i];

            while (!stack1.isEmpty() && nums[i] > nums[stack1.peek()])
                tmp.push(stack1.pop());

            while(!tmp.isEmpty())
                stack2.push(tmp.pop());

            stack1.push(i);
        }
        return res;
    }

    //2. mono stack
    //Runtime: 39 ms, faster than 77.78% of Java online submissions for Next Greater Element IV.
    //Memory Usage: 105.6 MB, less than 11.11% of Java online submissions for Next Greater Element IV.
    //Time: O(N + N * N); Space: O(N)
    public int[] secondGreaterElement_2(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);

        //first larger
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peekLast()])
                res[stack.pollLast()] = i;
            stack.add(i);
        }

        //search second larger
        for (int i = 0; i < res.length; i++) {
            if (res[i] == -1) continue;
            if (i > 0 && nums[i] == nums[i - 1]) {
                res[i] = res[i - 1]; continue;
            }

            int secondIdx = res[i];
            for (int j = secondIdx + 1; j < nums.length; j++) {
                if (nums[j] > nums[i]) {
                    secondIdx = j; break;
                }
            }
            res[i] = secondIdx == res[i] ? -1 : nums[secondIdx];
        }
        return res;
    }


    //1. Slide window
    //TLE
    //Time: O(N * N); Space: O(1)
    public int[] secondGreaterElement_1(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(res,-1);
        if (nums.length <= 2) return res;

        int left = 0, right = 1;
        int k = 0;
        while (left < nums.length - 2) {

            if (nums[right++] > nums[left]) k++;

            if (k == 2 || right == nums.length){
                res[left] = k == 2 ? nums[right - 1] :  -1;
                left++;
                while (left < nums.length - 2 && nums[left] == nums[left - 1]) {
                    res[left] = res[left - 1];
                    left++;
                }

                if (right == nums.length) {
                    while (left < nums.length - 2 && nums[left] >= nums[left - 1]) {
                        res[left] = res[left - 1];
                        left++;
                    }
                }

                if (k == 2 && right - left> 2 && left < nums.length - 2 && nums[left] > nums[left - 1]) {
                    k--;
                }else {
                    right = left + 1;
                    k = 0;
                }
            }
        }
        return res;
    }


}
