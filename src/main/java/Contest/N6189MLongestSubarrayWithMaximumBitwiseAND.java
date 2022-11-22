package Contest;



import static java.time.LocalTime.now;


/**
 * You are given an integer array nums of size n.
 *
 * Consider a non-empty subarray from nums that has the maximum possible bitwise AND.
 *
 * In other words, let k be the maximum value of the bitwise AND of any subarray of nums. Then, only subarrays with a bitwise AND equal to k should be considered.
 * Return the length of the longest such subarray.
 *
 * The bitwise AND of an array is the bitwise AND of all the numbers in it.
 *
 * A subarray is a contiguous sequence of elements within an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,3,2,2]
 * Output: 2
 * Explanation:
 * The maximum possible bitwise AND of a subarray is 3.
 * The longest subarray with that value is [3,3], so we return 2.
 * Example 2:
 *
 * Input: nums = [1,2,3,4]
 * Output: 1
 * Explanation:
 * The maximum possible bitwise AND of a subarray is 4.
 * The longest subarray with that value is [4], so we return 1.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 106
 */

//2419. Longest Subarray With Maximum Bitwise AND
public class N6189MLongestSubarrayWithMaximumBitwiseAND {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun(2, new int[]{1,2,3,3,2,3});
        doRun(1, new int[]{1,2,3,4});

        doRun(3, new int[]{1,2,3,3,2,3,5,5,5});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[] nums) {
        int res = new N6189MLongestSubarrayWithMaximumBitwiseAND()
                .longestSubarray(nums);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }



    //Runtime: 3 ms, faster than 66.67% of Java online submissions for Longest Subarray With Maximum Bitwise AND.
    //Memory Usage: 51 MB, less than 100.00% of Java online submissions for Longest Subarray With Maximum Bitwise AND.
    //Time: O(N); Space:O(1)
    public int longestSubarray(int[] nums) {
        int res = 0, max = 0, count = 0;

        for (int n : nums){
            if (n > max) {
                res = count = 0; //reset
                max = n;
            }
            count = n == max ? count + 1 : 0;
            res = Math.max(res, count);
        }
        return res;
    }

    //Runtime: 6 ms, faster than 33.33% of Java online submissions for Longest Subarray With Maximum Bitwise AND.
    //Memory Usage: 98.4 MB, less than 66.67% of Java online submissions for Longest Subarray With Maximum Bitwise AND.
    //Contest version
    //Time: O(N + N); Space:O(1)
    //Time: O(N); Space:O(1)
    public int longestSubarray_1(int[] nums) {

        //find the maximum
        int max = Integer.MIN_VALUE;
        for (int n : nums) max = Math.max(max, n);

        //count the number of maximum in a contiguous sequence
        int res = 0, right = 0;
        while (right < nums.length){
            int c = 0;
            while (right < nums.length && nums[right++] == max)  c++;
            res = Math.max(res, c);
        }
        return res;
    }
}
