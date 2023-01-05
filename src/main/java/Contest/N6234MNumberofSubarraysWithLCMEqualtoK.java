package Contest;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

import static java.time.LocalTime.now;

/**
 * Given an integer array nums and an integer k, return the number of subarrays of nums where the least common multiple of the subarray's elements is k.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * The least common multiple of an array is the smallest positive integer that is divisible by all the array elements.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,6,2,7,1], k = 6
 * Output: 4
 * Explanation: The subarrays of nums where 6 is the least common multiple of all the subarray's elements are:
 * - [3,6,2,7,1]
 * - [3,6,2,7,1]
 * - [3,6,2,7,1]
 * - [3,6,2,7,1]
 * Example 2:
 *
 * Input: nums = [3], k = 2
 * Output: 0
 * Explanation: There are no subarrays of nums where 2 is the least common multiple of all the subarray's elements.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * 1 <= nums[i], k <= 1000
 */

/**
 * M - [time :120+
 */

//2470. Number of Subarrays With LCM Equal to K
public class N6234MNumberofSubarraysWithLCMEqualtoK {


     static public void main(String... args) throws IOException{
         System.out.println(now());
         System.out.println("==================");

         doRun(1, new int[]{2,3}, 6);

         doRun(6, new int[]{5,1,1,1,2}, 1);

         doRun(4, new int[]{3,6,2,7,1}, 6);
         doRun(1, new int[]{1,2}, 1);
         doRun(1, new int[]{3,12,9,6}, 3);
         doRun(1, new int[]{1}, 1);
         doRun(0, new int[]{3}, 2);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[] nums, int k) {
        int  res = new N6234MNumberofSubarraysWithLCMEqualtoK().subarrayLCM(nums, k);

//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //Math GCD
    //Runtime: 32 ms, faster than 66.67% of Java online submissions for Number of Subarrays With LCM Equal to K.
    //Memory Usage: 42.7 MB, less than 25.00% of Java online submissions for Number of Subarrays With LCM Equal to K.
    //Time: O(N * N *); Space: O(1)
    public int subarrayLCM(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (k % nums[i] != 0) continue;
            int lastLCM = 1;
            for (int j = i; j < nums.length; j++) {
                lastLCM = lcm(lastLCM, nums[j]);
                if (lastLCM == k) res++;
                else if (lastLCM > k) break;
            }
        }
        return res;
    }
    //最小公倍数 4,6
    private int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
    //最大公约数
    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b,a % b);
    }



    //下面二个solutions当时可通过testcase，但逻辑上是错的。 testcase : [2,3], k = 6, expected: 1
//
//    //2.slide window + one pass
//    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Number of Subarrays With LCM Equal to K.
//    //Memory Usage: 41.5 MB, less than 91.67% of Java online submissions for Number of Subarrays With LCM Equal to K.
//    //Time: O(N); Space: O(1)
//    public int subarrayLCM_2(int[] nums, int k) {
//        int left = 0, right = 0;
//        int res = 0, count = 0;
//        while (right < nums.length){
//            if (k % nums[right] == 0 && k >= nums[right]) {
//                if (k == nums[right]) {
//                    count += right - left + 1;
//                    left = right + 1;
//                }
//                res += count;
//            } else {
//                count = 0;
//                left = right + 1;
//            }
//            right++;
//        }
//        return res;
//    }
//
//
//
//    //1.slide window + queue
//    //Runtime: 6 ms, faster than 100.00% of Java online submissions for Number of Subarrays With LCM Equal to K.
//    //Memory Usage: 43.1 MB, less than 25.00% of Java online submissions for Number of Subarrays With LCM Equal to K.
//    //Time: O(N); Space: O(N)
//    public int subarrayLCM_1(int[] nums, int k) {
//        int left = 0, right = 0;
//        int res = 0;
//        Queue<Integer> kQ = new ArrayDeque<>();
//
//        while (right < nums.length){
//            if (k % nums[right] == 0 && k >= nums[right]) {
//                if (k == nums[right]) kQ.add(right);
//            } else {
//                while (!kQ.isEmpty()) {
//                    int idx = kQ.poll();
//                    res += (right - idx) * (idx - left + 1);
//                    left = idx + 1;
//                }
//                left = right + 1;
//            }
//            right++;
//        }
//
//        while (!kQ.isEmpty()) {
//            int idx = kQ.poll();
//            res += (right - idx) * (idx - left + 1);
//            left = idx + 1;
//        }
//
//        return res;
//    }

}


