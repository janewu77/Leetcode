package LeetCode;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The array-form of an integer num is an array representing its digits in left to right order.
 *
 * For example, for num = 1321, the array form is [1,3,2,1].
 * Given num, the array-form of an integer, and an integer k, return the array-form of the integer num + k.
 *
 *
 *
 * Example 1:
 *
 * Input: num = [1,2,0,0], k = 34
 * Output: [1,2,3,4]
 * Explanation: 1200 + 34 = 1234
 * Example 2:
 *
 * Input: num = [2,7,4], k = 181
 * Output: [4,5,5]
 * Explanation: 274 + 181 = 455
 * Example 3:
 *
 * Input: num = [2,1,5], k = 806
 * Output: [1,0,2,1]
 * Explanation: 215 + 806 = 1021
 *
 *
 * Constraints:
 *
 * 1 <= num.length <= 104
 * 0 <= num[i] <= 9
 * num does not contain any leading zeros except for the zero itself.
 * 1 <= k <= 104
 */
public class N989EAddtoArrayFormofInteger {

    //Runtime: 4ms 84%; Memory: 43.6MB 91%
    //time: O(Max(logK , N)); Space: O(Max(logK , N))
    //let N be the number of digital in input array [num]
    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> res = new ArrayList();
        int carry = k;
        for (int i = num.length - 1; i >= 0 || carry > 0; i--) {
            int sum = (i >= 0 ? num[i] : 0) + carry;
            res.add(sum % 10);
            carry = sum / 10;
        }
        Collections.reverse(res);
        return res;
    }
}
