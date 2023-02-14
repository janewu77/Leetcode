package LeetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given two binary strings a and b, return their sum as a binary string.
 *
 *
 *
 * Example 1:
 *
 * Input: a = "11", b = "1"
 * Output: "100"
 * Example 2:
 *
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 *
 *
 * Constraints:
 *
 * 1 <= a.length, b.length <= 104
 * a and b consist only of '0' or '1' characters.
 * Each string does not contain leading zeros except for the zero itself.
 */
public class N67EAddBinary {

    //1.Iteration
    //Runtime: 1ms 100%; Memory: 41MB 82%
    //Time: O(Max(lenA, lenB)); Space: O(Max(lenA, lenB))
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int tmp = 0;
        for (int len_a = a.length() - 1, len_b = b.length() - 1; len_a >= 0 || len_b >= 0; len_a--, len_b--) {
            int valueA = len_a >= 0 ? a.charAt(len_a) - '0' : 0;
            int valueB = len_b >= 0 ? b.charAt(len_b) - '0' : 0;
            int res = valueA + valueB + tmp;
            tmp = res >> 1;
            sb.append(res % 2 == 0 ? 0 : 1);
        }
        if (tmp == 1) sb.append(1);
        return sb.reverse().toString();
    }


    public String addBinary_1(String a, String b) {
       return Integer.toBinaryString(Integer.valueOf(a, 2) + Integer.valueOf(b, 2));
    }
}
