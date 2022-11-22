package LeetCode;

import java.util.TreeSet;

/**
 * Given an unsorted integer array nums, return the smallest missing positive integer.
 *
 * You must implement an algorithm that runs in O(n) time and uses constant extra space.
 *
 * Example 1:
 * Input: nums = [1,2,0]
 * Output: 3
 *
 * Example 2:
 * Input: nums = [3,4,-1,1]
 * Output: 2
 *
 * Example 3:
 * Input: nums = [7,8,9,11,12]
 * Output: 1
 *
 * Constraints:
 * 1 <= nums.length <= 5 * 105
 * -231 <= nums[i] <= 231 - 1
 */

/**
 * H - [time: 45- / 120+]
 *  - 45内可以做出来，但不符题意；
 *  - 写了4种解法
 */
public class N41HFirstMissingPositive {

    public static void main(String[] args) {

        int[] data;

        data = new int[]{1,2,0};
        doRun(3, data);

        data = new int[]{1,3,0};
        doRun(2, data);

        data = new int[]{4,5,5,0};
        doRun(1, data);

        data = new int[]{1,2,6,3,5,4};
        doRun(7, data);

        data = new int[]{3,4,-1,1};
        doRun(2, data);

        data = new int[]{2147483647,2147483646,2147483645,3,2,1,-1,0,-2147483648};
        doRun(4, data);

        data = new int[]{99,94,96,11,92,5,91,89,57,85,66,63,84,81,79,61,74,78,77,30,64,13,58,18,70,69,51,12,32,34,9,43,39,8,1,38,49,27,21,45,47,44,53,52,48,19,50,59,3,40,31,82,23,56,37,41,16,28,22,33,65,42,54,20,29,25,10,26,4,60,67,83,62,71,24,35,72,55,75,0,2,46,15,80,6,36,14,73,76,86,88,7,17,87,68,90,95,93,97,98};
        doRun(100, data);

        data = new int[]{1,2,3};
        doRun(4, data);
    }

    private static void doRun(int expected, int[] nums){
        int res = new N41HFirstMissingPositive().firstMissingPositive(nums);
        //String strRes = Arrays.stream(res).mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(""));
        //System.out.println("=======");
        System.out.println("["+(expected == res)+"].[expected:"+ expected+"] res:"+res);
    }

    /////////////////////////////////////////////////////////////////////////////////
    //Same idea as firstMissingPositive_3.
    //Runtime: 2 ms, faster than 99.80% of Java online submissions for First Missing Positive.
    //Memory Usage: 105 MB, less than 85.09% of Java online submissions for First Missing Positive.
    //ignore : negative, zero,larger than n(nums.length)
    //in-place
    //time: best case: O(N), worst case :O(3N); Space: O(1)
    public int firstMissingPositive(int[] nums) {

        int expect = 1;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == expect) expect++;
            if (nums[i] <= 0 || nums[i] > nums.length) nums[i] = 1;
        }
        if (expect == 1) return 1;
        if (expect > nums.length) return nums.length + 1;

        //use '-' to flag
        for (int i = 0; i < nums.length; i++) {
            int idx = Math.abs(nums[i]);
            if (idx == nums.length) idx = 0;
            if (nums[idx] > 0) nums[idx] = -nums[idx];
        }

        for (int i = 1; i < nums.length; i++)
            if (nums[i] > 0) return i;

        if (nums[0] > 0) return nums.length;
        return nums.length + 1;
    }

    /////////////////////////////////////////////////////////////////////////////////
    //Simple! Easy to understand!
    //Runtime: 3 ms, faster than 93.07% of Java online submissions for First Missing Positive.
    //Memory Usage: 123.2 MB, less than 81.73% of Java online submissions for First Missing Positive.
    //use extra space to mark numbers
    //ignore : negative, zero, larger than nums.length
    //time: O(2N); Space: O(n)
    public int firstMissingPositive_3(int[] nums) {
        boolean[] arrIndex = new boolean[nums.length + 1];

        //only care 1 to nums.length
        for (int n : nums)
            if (n > 0 && n <= nums.length)
                arrIndex[n] = true;

         //begin : 1
        for (int i = 1; i < arrIndex.length; i++)
            if (!arrIndex[i]) return i;

        return nums.length + 1;
    }

    /////////////////////////////////////////////////////////////////////////////////
    //My favorite
    //Runtime: 2 ms, faster than 99.80% of Java online submissions for First Missing Positive.
    //Memory Usage: 98.9 MB, less than 98.33% of Java online submissions for First Missing Positive.
    //iteration
    //time: best case:O(N), worst case：O(N * N/2); Space: O(1)
    public int firstMissingPositive_2(int[] nums) {
        int expect = 1;
        int lastExpect = 0;

        while (expect != lastExpect) {
            lastExpect = expect;
            for (int i = 0; i < nums.length; i++)
                if (nums[i] == expect) expect++;
        }
        return expect;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //time & space 都不符题意。
    //Runtime: 317 ms, faster than 5.03% of Java online submissions for First Missing Positive.
    //Memory Usage: 167.3 MB, less than 15.94% of Java online submissions for First Missing Positive.
    //TreeSet
    //time: O(NlogN); Space: O(N)
    public int firstMissingPositive_1(int[] nums) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int n: nums) if (n > 0) treeSet.add(n);

        int res = 0;
        while (!treeSet.isEmpty() ){
            int x = treeSet.pollFirst();
            if (res + 1 != x) break;
            res = x;
        }
        return res + 1;
    }
}
