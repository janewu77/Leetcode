package LeetCode;

/**
 * Given a signed 32-bit integer x, return x with its digits reversed.
 * If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
 *
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 *
 *
 *
 * Example 1:
 *
 * Input: x = 123
 * Output: 321
 * Example 2:
 *
 * Input: x = -123
 * Output: -321
 * Example 3:
 *
 * Input: x = 120
 * Output: 21
 *
 *
 * Constraints:
 *
 * -231 <= x <= 231 - 1
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * M - [time:45- ]
 */
public class N7MReverseInteger {

    public static void main(String[] args){
        //Integer.MAX_VALUE 2147483647
        //Integer.MIN_VALUE -2147483648
//        System.out.println(Integer.MAX_VALUE);
        int res;
        res = new N7MReverseInteger().reverse(-321);
        System.out.println(res);

        res = new N7MReverseInteger().reverse(321);
        System.out.println(res);

        res = new N7MReverseInteger().reverse(1534236469);
        System.out.println(res);

        res = new N7MReverseInteger().reverse(-1534236469);
        System.out.println(res);

        res = new N7MReverseInteger().reverse(-2147483648);
        System.out.println(res);

        res = new N7MReverseInteger().reverse(-901000);
        System.out.println(res);

        res = new N7MReverseInteger().reverse(1534236469);
        System.out.println(res);


    }

    //Runtime: 2 ms, faster than 77.05% of Java online submissions for Reverse Integer.
    //Memory Usage: 41 MB, less than 77.07% of Java online submissions for Reverse Integer.
    //time: O(log(x)), Space: O(1)
    public int reverse(int x) {
        if (x <= Integer.MIN_VALUE || x >= Integer.MAX_VALUE) return 0;

        int sign = x < 0 ? -1 : 1;
        int k = 0;
        while (x != 0){

            int i = x % 10;
            x /= 10;

            if (sign < 0){
                if (k < Integer.MIN_VALUE / 10 || Integer.MIN_VALUE - k * 10 > i) return 0;
            }else{
                if (k > Integer.MAX_VALUE / 10 || Integer.MAX_VALUE - k * 10 < i) return 0;
            }
            k = k * 10 + i;
        }
        return k;
    }
}
