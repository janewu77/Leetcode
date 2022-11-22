package Contest;

import static java.time.LocalTime.now;


/**
 *
 * Given two positive integers num1 and num2, find the integer x such that:
 *
 * x has the same number of set bits as num2, and
 * The value x XOR num1 is minimal.
 * Note that XOR is the bitwise XOR operation.
 *
 * Return the integer x. The test cases are generated such that x is uniquely determined.
 *
 * The number of set bits of an integer is the number of 1's in its binary representation.
 *
 *
 *
 * Example 1:
 *
 * Input: num1 = 3, num2 = 5
 * Output: 3
 * Explanation:
 * The binary representations of num1 and num2 are 0011 and 0101, respectively.
 * The integer 3 has the same number of set bits as num2, and the value 3 XOR 3 = 0 is minimal.
 * Example 2:
 *
 * Input: num1 = 1, num2 = 12
 * Output: 3
 * Explanation:
 * The binary representations of num1 and num2 are 0001 and 1100, respectively.
 * The integer 3 has the same number of set bits as num2, and the value 3 XOR 1 = 2 is minimal.
 *
 *
 * Constraints:
 *
 * 1 <= num1, num2 <= 109
 */

/**
 * M - [time: 35-
 */
//2429. Minimize XOR
public class N6194MMinimizeXOR {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun(76, 79, 74);
        doRun(5, 4, 12);

        doRun(24, 25, 72);

        doRun(3, 1, 12);

        doRun(3, 3, 5);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int num1, int num2) {
        int res = new N6194MMinimizeXOR()
                .minimizeXor(num1, num2);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //  用位差处理。 minimizeXor_1 一样，但代码不够清晰易懂。
    // 当num1与num2的1个数相同时，返回num1；
    // 当num1中1的个数大于 num2的个数时， 将num1的低位1置成0; 反之，则置成1.
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Minimize XOR.
    //Memory Usage: 39.2 MB, less than 60.00% of Java online submissions for Minimize XOR.
    //Time:O(bitCount1 - bitCount2)
    public int minimizeXor(int num1, int num2) {
        int bitCount1 = Integer.bitCount(num1);
        int bitCount2 = Integer.bitCount(num2);
        if (bitCount1 == bitCount2) return num1;

        int res1 = num1;
        int res0 = bitCount1 - bitCount2 > 0 ? num1 : ~num1;
        for (int idx = 0; idx < Math.abs(bitCount1 - bitCount2); idx++) {
            int s = res0 & (-res0);
            res1 = res1 | s; //set 1
            res0 = res0 ^ s; //set 0
        }
        return bitCount1 - bitCount2 > 0 ? res0 : res1;
    }

    //Runtime: 1 ms, faster than 60.00% of Java online submissions for Minimize XOR.
    //Memory Usage: 41.2 MB, less than 60.00% of Java online submissions for Minimize XOR.
    //Integer bitCount
    //Time: O(logNum2); Space: O(1)
    public int minimizeXor_1(int num1, int num2) {
        int bitCount1 = Integer.bitCount(num1);
        int bitCount2 = Integer.bitCount(num2);
        if (bitCount1 == bitCount2) return num1;

        int res = 0, s, i = 0;

        //from highest
        int x = num1;
        while (i < bitCount2 && x != 0) {
            s = Integer.highestOneBit(x);
            res |= s;
            x = x ^ s;
            i++;
        }

        //from lowest
        x = ~num1;
        while (i < bitCount2 && x != 0){
            s = x & (-x);
            res |= s;
            x = x ^ s;
            i++;
        }
        return res;
    }


}
