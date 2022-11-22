package LeetCode;

import static java.time.LocalTime.now;

/**
 * Given an integer n, you must transform it into 0 using the following operations any number of times:
 *
 * Change the rightmost (0th) bit in the binary representation of n.
 * Change the ith bit in the binary representation of n if the (i-1)th bit is set to 1
 * and the (i-2)th through 0th bits are set to 0.
 * Return the minimum number of operations to transform n into 0.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: 2
 * Explanation: The binary representation of 3 is "11".
 * "11" -> "01" with the 2nd operation since the 0th bit is 1.
 * "01" -> "00" with the 1st operation.
 * Example 2:
 *
 * Input: n = 6
 * Output: 4
 * Explanation: The binary representation of 6 is "110".
 * "110" -> "010" with the 2nd operation since the 1st bit is 1 and 0th through 0th bits are 0.
 * "010" -> "011" with the 1st operation.
 * "011" -> "001" with the 2nd operation since the 0th bit is 1.
 * "001" -> "000" with the 1st operation.
 *
 *
 * Constraints:
 *
 * 0 <= n <= 109
 */

/**
 * H - 【time：60+
 *  - 需要在计算过程中，找出数据的规律。
 *  - gray code!!!
 */
public class N1611HMinimumOneBitOperationstoMakeIntegersZero {


    public static void main(String[] args) {

        System.out.println(now());

        int[] data;
        int[][] data2;

        doRun(1, 1);
        doRun(2, 3);
        doRun(4, 6);


        doRun(3, 2);
        doRun(7, 4);
        doRun(6, 5);


        //[]
        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, int nums) {
        int res = new N1611HMinimumOneBitOperationstoMakeIntegersZero().minimumOneBitOperations(nums);
//        String res = comm.toString(res1);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
//        System.out.println("==================");
    }

    //这个网上看来的. 这是直接在输入 / 输出间发现的规律。 但找不到更好的解释。
    //https://en.wikipedia.org/wiki/Gray_code
    //https://leetcode.com/problems/minimum-one-bit-operations-to-make-integers-zero/discuss/877809/GreyCode-to-decimal-C%2B%2B-Math-solution
    //Runtime: 1 ms, faster than 90.44% of Java online submissions for Minimum One Bit Operations to Make Integers Zero.
    //Memory Usage: 41 MB, less than 63.24% of Java online submissions for Minimum One Bit Operations to Make Integers Zero.
    public int minimumOneBitOperations(int n) {
        int res = 0;
        while (n != 0) {
            res ^= n;
            n >>= 1;
        }
        return res;
    }

    //拿2,4,8先试算，然后找规律。
    //规律1： 2需要3步； 4需要7步；8需要15步
    //1 -> 0 needs 1 operation,
    //2 -> 0 needs 3 operations,
    //4 -> 0 needs 7 operations,
    //2^k needs 2^(k+1)-1 operations.
    //
    //规律2： 2需要3步，3只需要2步，1只需要1步；  4需要7步；5需要6步，7需要5步
    //subtract the cost we have already counted on eliminating lower bits

    //Runtime: 1 ms, faster than 90.44% of Java online submissions for Minimum One Bit Operations to Make Integers Zero.
    //Memory Usage: 41 MB, less than 63.24% of Java online submissions for Minimum One Bit Operations to Make Integers Zero.
    //Time: O(logN); Space: O(1)
    public int minimumOneBitOperations_1(int n) {
        int res = 0, x = 2;
        while (n != 0){
            if ((n & 1) == 1)
                res = (x == 2) ? 1 : x - 1 - res;
            x *= 2;
            n >>= 1;
        }
        return res;
    }
}
