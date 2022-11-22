package LeetCode;


/**
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters
 * and removing all non-alphanumeric characters, it reads the same forward and backward.
 * Alphanumeric characters include letters and numbers.
 *
 * Given a string s, return true if it is a palindrome, or false otherwise.
 *
 *
 * Example 1:
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 *
 *
 * Example 2:
 * Input: s = "race a car"
 * Output: false
 * Explanation: "raceacar" is not a palindrome.
 *
 *
 * Example 3:
 * Input: s = " "
 * Output: true
 * Explanation: s is an empty string "" after removing non-alphanumeric characters.
 * Since an empty string reads the same forward and backward, it is a palindrome.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 2 * 105
 * s consists only of printable ASCII characters.
 */

/**
 * E
 * 字符串基本操作
 *  - String charAt
 *  - Character isLetterOrDigit
 *
 * 方式一：two point
 *  时间O(n);空间O(1)
 * 方式二：one point
 *  时间O(n);空间O(n)
 *  取出字母和数字；然后造一个反转的字串。然后比较二个字串是否相等
 *  用stream
 *
 *
 */
public class N125EValidPalindrome {

    /**
     * Runtime: 6 ms, faster than 64.53% of Java online submissions for Valid Palindrome.
     * Memory Usage: 43.5 MB, less than 65.04% of Java online submissions for Valid Palindrome.
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        boolean isVP;

        do {
            while (l < r && !Character.isLetterOrDigit(s.charAt(l))) l++;
            while (r > l && !Character.isLetterOrDigit(s.charAt(r))) r--;
            isVP = Character.toLowerCase(s.charAt(l++)) == Character.toLowerCase(s.charAt(r--));

        } while( isVP && (l < r) );

        return isVP;
    }

    public static void main(String[] args) {
        String s1 = "A man, a plan, a canal: Panama";
        boolean result1 = new N125EValidPalindrome().isPalindrome(s1);
        System.out.println( s1 + " result:"+ result1);

        String s2 = "race a car";
        boolean result2 = new N125EValidPalindrome().isPalindrome(s2);
        System.out.println( s2 + " result:"+ result2);
//
        String s3 = " ";
        boolean result3 = new N125EValidPalindrome().isPalindrome(s3);
        System.out.println( s3 + " result:"+ result3);

        String s4 = "0P";
        boolean result4 = new N125EValidPalindrome().isPalindrome(s4);
        System.out.println( s4 + " result:"+ result4);

        String s5 = ".P";
        boolean result5 = new N125EValidPalindrome().isPalindrome(s5);
        System.out.println( s5 + " result:"+ result5);

        String s6= "      ";
        boolean result6 = new N125EValidPalindrome().isPalindrome(s6);
        System.out.println( s6 + " result:"+ result6);
    }
}
