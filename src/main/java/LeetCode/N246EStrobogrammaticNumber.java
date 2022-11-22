package LeetCode;


/**
 * Given a string num which represents an integer, return true if num is a strobogrammatic number.
 *
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 *
 *
 * Example 1:
 *
 * Input: num = "69"
 * Output: true
 * Example 2:
 *
 * Input: num = "88"
 * Output: true
 * Example 3:
 *
 * Input: num = "962"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= num.length <= 50
 * num consists of only digits.
 * num does not contain any leading zeros except for zero itself.
 */
public class N246EStrobogrammaticNumber {


    //0 1 8 6 9

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Strobogrammatic Number.
    //Memory Usage: 41.6 MB, less than 71.78% of Java online submissions for Strobogrammatic Number.
    //Time: O(N); Space: O(1)
    public boolean isStrobogrammatic(String num) {
        int[] map = new int[]{0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
        for (int left = 0, right = num.length() - 1; left <= right; left++, right--)
            if (map[num.charAt(left) - '0'] != num.charAt(right) - '0')
                return false;
        return true;
    }

    //1.two pointers
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Strobogrammatic Number.
    //Memory Usage: 42 MB, less than 39.54% of Java online submissions for Strobogrammatic Number.
    //Time: O(N); Space: O(1)
    public boolean isStrobogrammatic_1(String num) {
        int left = 0, right = num.length()-1;
        while (left < right) {
            char c = num.charAt(left);
            if (!is018(c) && !is69(c)) return false;
            else if (c == '6' && num.charAt(right) != '9') return false;
            else if (c == '9' && num.charAt(right) != '6') return false;
            else if (is018(c) && num.charAt(right) != c) return false;
            left++; right--;
        }

        if (left == right) return is018(num.charAt(left));
        return true;
    }

    private boolean is018(char c) {
        return c == '0' || c == '1' || c == '8';
    }
    private boolean is69(char c) {
        return c == '6' || c == '9';
    }

}
