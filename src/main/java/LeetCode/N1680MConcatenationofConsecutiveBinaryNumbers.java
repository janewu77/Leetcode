package LeetCode;


import Contest.N1482MMinimumNumberofDaystoMakemBouquets;

import java.math.BigInteger;
import java.util.stream.LongStream;

import static java.time.LocalTime.now;

/**
 * Given an integer n, return the decimal value of the binary string formed by concatenating the
 * binary representations of 1 to n in order, modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: 1
 * Explanation: "1" in binary corresponds to the decimal value 1.
 * Example 2:
 *
 * Input: n = 3
 * Output: 27
 * Explanation: In binary, 1, 2, and 3 corresponds to "1", "10", and "11".
 * After concatenating them, we have "11011", which corresponds to the decimal value 27.
 * Example 3:
 *
 * Input: n = 12
 * Output: 505379714
 * Explanation: The concatenation results in "1101110010111011110001001101010111100".
 * The decimal value of that is 118505380540.
 * After modulo 109 + 7, the result is 505379714.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 105
 */
public class N1680MConcatenationofConsecutiveBinaryNumbers {


    public static void main(String[] args) {

        System.out.println(now());
        String[] data;

//        System.out.println(Integer.toString(7, 2));

//        doRun(1, 1);
        doRun(27, 3);
        doRun(6, 2);

        doRun(505379714, 12);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int n) {
        int res = new N1680MConcatenationofConsecutiveBinaryNumbers().concatenatedBinary(n);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //from @poiuytrewq1as
    public int concatenatedBinary_3(int n) {
        return (int) LongStream.range(1, n + 1)
                .reduce(0, (sum, i) -> (sum * (int) Math.pow(2, Long.toBinaryString(i).length()) + i) % 1_000_000_007);
    }


    //Runtime: 311 ms, faster than 54.55% of Java online submissions for Concatenation of Consecutive Binary Numbers.
    //Memory Usage: 42.3 MB, less than 29.87% of Java online submissions for Concatenation of Consecutive Binary Numbers.
    public int concatenatedBinary(int n) {
        return (int) LongStream.range(2, n + 1)
                .reduce(1, (sum, i) -> ((sum << Long.toBinaryString(i).length()) | i) % 1_000_000_007);
    }

    //Runtime: 84 ms, faster than 90.91% of Java online submissions for Concatenation of Consecutive Binary Numbers.
    //Memory Usage: 40.8 MB, less than 81.82% of Java online submissions for Concatenation of Consecutive Binary Numbers.
    //Bitwise operation
    //Time: O(N); Space: O(1)
    public int concatenatedBinary_2(int n) {
        final int MODULO = 1_000_000_007;

        long res = 1, shift = 1;
        for (int i = 2; i <= n; i++){
            if ((i & (i - 1)) == 0) shift++;
            res = ((res << shift) | i) % MODULO;
        }
        return (int) (res);
    }

    //1。计算n转成2进制后的长度；
    //2。对已经转过的数据进行向左位移，位移距离为上一步计算出来的长度。
    //Runtime: 468 ms, faster than 46.75% of Java online submissions for Concatenation of Consecutive Binary Numbers.
    //Memory Usage: 121.9 MB, less than 5.19% of Java online submissions for Concatenation of Consecutive Binary Numbers.
    //Integer.toBinaryString
    //Time: O(N * lgN); Space: O(N)
    public int concatenatedBinary_1(int n) {
        final int MODULO = 1_000_000_007;

        long res = 1;
        for (int i = 2; i <= n; i++){
            int shift = Integer.toBinaryString(i).length(); //耗时
//            int shift = Integer.toString(i, 2).length();
            res = ((res << shift) | i) % MODULO;
        }
        return (int) (res);
    }
}
