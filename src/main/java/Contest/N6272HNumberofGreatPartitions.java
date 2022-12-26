package Contest;

import java.io.IOException;
import java.util.Arrays;

import static java.time.LocalTime.now;

/**
 * You are given an array nums consisting of positive integers and an integer k.
 *
 * Partition the array into two ordered groups such that each element is in exactly one group. A partition is called great if the sum of elements of each group is greater than or equal to k.
 *
 * Return the number of distinct great partitions. Since the answer may be too large, return it modulo 109 + 7.
 *
 * Two partitions are considered distinct if some element nums[i] is in different groups in the two partitions.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4], k = 4
 * Output: 6
 * Explanation: The great partitions are: ([1,2,3], [4]), ([1,3], [2,4]), ([1,4], [2,3]), ([2,3], [1,4]), ([2,4], [1,3]) and ([4], [1,2,3]).
 * Example 2:
 *
 * Input: nums = [3,3,3], k = 4
 * Output: 0
 * Explanation: There are no great partitions for this array.
 * Example 3:
 *
 * Input: nums = [6,6], k = 2
 * Output: 2
 * Explanation: We can either put nums[0] in the first partition or in the second partition.
 * The great partitions will be ([6], [6]) and ([6], [6]).
 *
 *
 * Constraints:
 *
 * 1 <= nums.length, k <= 1000
 * 1 <= nums[i] <= 109
 */

//2518. Number of Great Partitions
    //todo:
public class N6272HNumberofGreatPartitions {

     static public void main(String... args) throws IOException{
         System.out.println(now());
         System.out.println("==================");
        //doRun(0, 0);
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(long expect, int n) {
         //List<List<Integer>> keys = comm.convert2DArr2List(rooms);
//        long res = new C0806().n1(n);
//        comm.printListListString(expect, res);

//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
        //boolean success = Arrays.equals(res, expect);
        //        System.out.println("["+success+"] expect:" + Arrays.toString(expect) + " res:" + Arrays.toString(res)));
    }

    public int countPartitions(int[] nums, int k) {
        long mod = (long)1e9 + 7, sum = 0, res = 1;
        long[] dp = new long[k];
        dp[0] = 1;

        for (int a : nums) {
            for (int i = k - 1 - a; i >= 0; --i)
                dp[i + a] = (dp[i + a] + dp[i]) % mod;
            res = (res << 1) % mod;
            sum += a;
        }
        for (int i = 0; i < k; ++i) {
            res -= (sum - i < k ? dp[i] : dp[i] * 2);
        }
        return (int)((res % mod + mod) % mod);
    }

//    public int countPartitions(int[] nums, int k) {
//         int[] sum = new int[nums.length];
//         sum[0] = nums[0];
//        for (int i = 1; i < nums.length; i++) {
//            sum[i] = sum[i-1] + nums[i];
//        }
//
//         if (sum[nums.length-1] <= k) return 0;
//
//
//        int res = 0;
//        int idx = Arrays.binarySearch(nums, k);
//        if (idx < 0) idx = -idx - 1;
//
//        if (sum[idx - 1] >= k && nums[idx] >= k) {
//            res++;
//        }
//
//        res += nums.length - idx - 1;
//
//        return res;
//    }

}


