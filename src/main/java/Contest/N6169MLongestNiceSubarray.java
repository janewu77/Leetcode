package Contest;


import static java.time.LocalTime.now;

/**
 * You are given an array nums consisting of positive integers.
 *
 * We call a subarray of nums nice if the bitwise AND of every pair of elements
 * that are in different positions in the subarray is equal to 0.
 *
 * Return the length of the longest nice subarray.
 *
 * A subarray is a contiguous part of an array.
 *
 * Note that subarrays of length 1 are always considered nice.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,8,48,10]
 * Output: 3
 * Explanation: The longest nice subarray is [3,8,48]. This subarray satisfies the conditions:
 * - 3 AND 8 = 0.
 * - 3 AND 48 = 0.
 * - 8 AND 48 = 0.
 * It can be proven that no longer nice subarray can be obtained, so we return 3.
 * Example 2:
 *
 * Input: nums = [3,1,5,11,13]
 * Output: 1
 * Explanation: The length of the longest nice subarray is 1. Any subarray of length 1 can be chosen.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 */

//2401. Longest Nice Subarray
public class N6169MLongestNiceSubarray {


    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;
//
//        doRun_demo2(3, new int[]{1,3,8,16});
//        doRun_demo2(3, new int[]{3,8,16,2,2});
//        doRun_demo2(3, new int[]{1,3,8,48,10});
//
//        doRun_demo2(1, new int[]{3,1,5,11,13});
//
//        data = new int[]{904163577,321202512,470948612,490925389,550193477,87742556,151890632,
//                655280661,4,263168,32,573703555,886743681,937599702,120293650,725712231,257119393};
//        doRun_demo2(3, data);
//
//        doRun_demo2(3, new int[]{4,263168,32,573703555,886743681,937599702,120293650,725712231,257119393});

//        data = new int[]{178830999,19325904,844110858,806734874,280746028,64,256,33554432,
//                882197187,104359873,453049214,820924081,624788281,710612132,839991691};

        doRun_demo2(4, new int[]{8,7,4,8,16,32,9});
        data = new int[]{19325904,844110858,806734874,280746028,64,256,33554432};
//        data = new int[]{882197187,104359873,453049214,820924081,624788281,710612132,839991691};
        doRun_demo2(4, data);

//        System.out.println(2 & 7);
//        System.out.println((844110858) & 806734874);

//        System.out.println(4 & 263168);
//        System.out.println((263168 |4) & 573703555);
//        System.out.println(8 & 573703555);
//        System.out.println(263168 & 8);

//        System.out.println(3 & 8);
        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun_demo2(int expect,int[] nums) {
        int res = new N6169MLongestNiceSubarray().longestNiceSubarray(nums);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //Runtime: 4 ms, faster than 75.00% of Java online submissions for Longest Nice Subarray.
    //Memory Usage: 78.8 MB, less than 50.00% of Java online submissions for Longest Nice Subarray.
    //slide window
    //Time : O(N); Space: O(1)
    public int longestNiceSubarray(int[] nums) {
        int left = 0, currNum = 0;
        int res = 0;
        for (int right = 0; right < nums.length; right++) {

            while ((currNum & nums[right]) != 0)
                currNum ^= nums[left++];

            res = Math.max(res, right - left + 1);
            currNum |= nums[right];
        }
        return res;
    }

    //Runtime: 6 ms, faster than 75.00% of Java online submissions for Longest Nice Subarray.
    //Memory Usage: 78.6 MB, less than 50.00% of Java online submissions for Longest Nice Subarray.
    //brute force
    //Time : O(N*N); Space: O(1)
    public int longestNiceSubarray_1(int[] nums) {
        int res = 1;
        for(int i = 0; i < nums.length - res; i++) {
            int first = nums[i];
            int count = 1;
            for (int j = i + 1; j < nums.length; j++) {
                if ((first & nums[j]) == 0) {
                    first = first | nums[j];
                    count++;
                } else break;
            }
            res = Math.max(res, count);
        }
        return res;
    }
}
