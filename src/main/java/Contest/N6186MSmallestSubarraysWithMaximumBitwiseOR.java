package Contest;

import java.util.Arrays;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

/**
 * You are given a 0-indexed array nums of length n, consisting of non-negative integers. For each index i from 0 to n - 1, you must determine the size of the minimum sized non-empty subarray of nums starting at i (inclusive) that has the maximum possible bitwise OR.
 *
 * In other words, let Bij be the bitwise OR of the subarray nums[i...j]. You need to find the smallest subarray starting at i, such that bitwise OR of this subarray is equal to max(Bik) where i <= k <= n - 1.
 * The bitwise OR of an array is the bitwise OR of all the numbers in it.
 *
 * Return an integer array answer of size n where answer[i] is the length of the minimum sized subarray starting at i with maximum bitwise OR.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,0,2,1,3]
 * Output: [3,3,2,2,1]
 * Explanation:
 * The maximum possible bitwise OR starting at any index is 3.
 * - Starting at index 0, the shortest subarray that yields it is [1,0,2].
 * - Starting at index 1, the shortest subarray that yields the maximum bitwise OR is [0,2,1].
 * - Starting at index 2, the shortest subarray that yields the maximum bitwise OR is [2,1].
 * - Starting at index 3, the shortest subarray that yields the maximum bitwise OR is [1,3].
 * - Starting at index 4, the shortest subarray that yields the maximum bitwise OR is [3].
 * Therefore, we return [3,3,2,2,1].
 * Example 2:
 *
 * Input: nums = [1,2]
 * Output: [2,1]
 * Explanation:
 * Starting at index 0, the shortest subarray that yields the maximum bitwise OR is of length 2.
 * Starting at index 1, the shortest subarray that yields the maximum bitwise OR is of length 1.
 * Therefore, we return [2,1].
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 105
 * 0 <= nums[i] <= 109
 */

/**
 * M - [time: 当时TLE
 */
//2411. Smallest Subarrays With Maximum Bitwise OR
public class N6186MSmallestSubarraysWithMaximumBitwiseOR {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun("4,3,3,2,3,4,3,2,1", new int[]{9,5,0,10,7,2,9,2,4});
        doRun("2,1,1", new int[]{8,10,8});

        doRun("3,3,2,2,1", new int[]{1,0,2,1,3});
        doRun("2,1", new int[]{1,2});

        doRun("36,35,34,33,32,31,30,29,28,27,26,25,24,23,22,21,20,19,18,43,42,41,40,39,38,37,43,42,41,40,39,38,37,36,35,34,43,42,41,40,39,38,37,36,35,34,33,32,31,30,29,28,27,26,25,24,23,22,21,20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,1,1,1,1,1,1",
                new int[]{0,0,0,0,0,0,0,0,0,8,0,0,0,0,0,0,0,32,2,0,0,0,0,0,0,28,16,32,0,0,0,0,0,0,0,1,0,8,0,0,0,0,0,0,0,0,0,32,0,0,0,0,0,0,0,0,16,0,0,0,0,2,0,0,32,0,0,0,4,16,0,4,0,32,0,0,8,0,1,1,0,0,0,0,0});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(String expect, int[] nums) {
        int[] res1 = new N6186MSmallestSubarraysWithMaximumBitwiseOR()
                .smallestSubarrays(nums);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //from lee215
    //用一个数组 来对应 2进制位。 记录每个1最近出现的位置（index）
    //转化问题后，只需要每次检查'1'出现的最远位置，就可计算出结果。不需要count'1'的个数。
    //Runtime: 31 ms, faster than 100.00% of Java online submissions for Smallest Subarrays With Maximum Bitwise OR.
    //Memory Usage: 52.3 MB, less than 100.00% of Java online submissions for Smallest Subarrays With Maximum Bitwise OR.
    //Bit operation
    //Time: O(N * 30); Space: O(N + 30)
    //Time: O(N); Space: O(N)
    public int[] smallestSubarrays(int[] nums) {
        int[] res = new int[nums.length];

        int oneBitRightMostIndex[] = new int[30];
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] = 1;
            for (int j = 0; j < 30; j++) { //30来源：题目的限定条件 10^9 小于 2^30。
                //1 << j ：按位查看是否为'1'。如果该位是1，则把当前i记录下来。（即最新出新的1）
                if ((nums[i] & (1 << j)) > 0)  oneBitRightMostIndex[j] = i;

                //用最"远"的位置来减。 比如，其中有个"1"，只出现在最尾。则长度就是 当前 到 最尾部。
                res[i] = Math.max(res[i], oneBitRightMostIndex[j] - i + 1);
            }
        }
        return res;
    }



    //Runtime: 61 ms, faster than 60.00% of Java online submissions for Smallest Subarrays With Maximum Bitwise OR.
    //Memory Usage: 90.2 MB, less than 40.00% of Java online submissions for Smallest Subarrays With Maximum Bitwise OR.
    //Slide window + bit opertaion
    //Time:O(N); Space:O(N)
    //n is the nums.length
    public int[] smallestSubarrays_3(int[] nums) {
        int[] res = new int[nums.length];
        res[nums.length - 1] = 1;

        //Time:O(N); Space:O(N)
        int[] maxNums = new int[nums.length];
        maxNums[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--)
            maxNums[i] = nums[i] | maxNums[i + 1];

        //Time:O(N); Space:O(32)
        int[] bitArr = new int[32 + 1];
        int x = 0, left = 0, right = 0;
        int maxC = Integer.bitCount(maxNums[0]);
        while (right < nums.length){
            x |= nums[right];
            setOnes(bitArr, nums[right], right + 1);
            while (left <= right && Integer.bitCount(x) >= maxC){
                res[left] = right - left + 1;
                //remove leftmost from x
                x = removeOnes(bitArr, ++left);
                if (left < nums.length)
                    maxC = Integer.bitCount(maxNums[left]);
            }
            right++;
        }
        return res;
    }

    //Time: O(32)
    private int removeOnes(int[] bitArr, int idx){
        int total = 0;
        for (int i = bitArr.length - 1; i > 0; i--){
            total = total << 1;
            if (bitArr[i] <= idx) bitArr[i] = 0;
            else total += 1;
        }
        return total;
    }

    //Time: worst case: O(32 * 32)
    private void setOnes(int[] bitArr, int number, int idx){
        while (number != 0) {
            int lb = number & -number; //lowest one bit
            number = number ^ lb; //set 0

            int k = 1;
            while ((lb = lb >> 1) != 0) k++;

            bitArr[k] = idx;
        }
    }


    //Time Limit Exceeded
    public int[] smallestSubarrays_2(int[] nums) {
        int[] maxNums = new int[nums.length];
        maxNums[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--)
            maxNums[i] = nums[i] | maxNums[i + 1];

        int[] res = new int[nums.length];
        res[nums.length - 1] = 1;
        for (int i = 0; i < nums.length - 1; i++){
            if (i != 0 && nums[i] == nums[i - 1] && res[i - 1] > 1) {
                res[i] = res[i - 1] - 1;
                continue;
            }

            int maxC = Integer.bitCount(maxNums[i]);
            int maxIdx = i;
            int x = 0;
            for (int j = i; j < nums.length; j++){
                if (j != i && nums[j]== nums[j-1]) continue;
                x |= nums[j];
                if (Integer.bitCount(x) >= maxC) {
                    maxIdx = j;
                    break;
                }
            }
            res[i] = maxIdx - i + 1;
        }
        return res;
    }


    //TLE
    //Time: O(N*N)
    public int[] smallestSubarrays_1(int[] nums) {
        int[] res = new int[nums.length];
        int k = 0;

        for (int i = 0; i<nums.length; i++){
            int maxC = 0, maxIdx = i, x = 0;
            for (int j = i; j < nums.length; j++){
                x |= nums[j];
                int count = Integer.bitCount(x);
                if (count > maxC) {
                    maxC = count; maxIdx = j;
                }
            }
            res[k++] = maxIdx - i + 1;
        }
        return res;
    }


}
