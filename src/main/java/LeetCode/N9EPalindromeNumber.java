package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given an integer x, return true if x is palindrome integer.
 *
 * An integer is a palindrome when it reads the same backward as forward.
 *
 * For example, 121 is a palindrome while 123 is not.
 *
 *
 * Example 1:
 *
 * Input: x = 121
 * Output: true
 * Explanation: 121 reads as 121 from left to right and from right to left.
 * Example 2:
 *
 * Input: x = -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 * Example 3:
 *
 * Input: x = 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 *
 *
 * Constraints:
 *
 * -231 <= x <= 231 - 1
 *
 */

/**
 * E - [耗时 10-m]
 *  - 直接用数学的方式，折叠计算对称性。
 *
 */
public class N9EPalindromeNumber {

    public static void main(String[] args){
        int d1 = 123;
        System.out.println("expected false. result:"+ new N9EPalindromeNumber().isPalindrome(d1));
        System.out.println("expected false. result:"+ new N9EPalindromeNumber().isPalindrome(-33));
        System.out.println("expected false. result:"+ new N9EPalindromeNumber().isPalindrome(11123111));

        System.out.println("expected true. result:"+ new N9EPalindromeNumber().isPalindrome(0));
        System.out.println("expected true. result:"+ new N9EPalindromeNumber().isPalindrome(121));
        System.out.println("expected true. result:"+ new N9EPalindromeNumber().isPalindrome(1221));
        System.out.println("expected true. result:"+ new N9EPalindromeNumber().isPalindrome(12321));

        System.out.println("expected true. result:"+ new N9EPalindromeNumber().isPalindrome(101));
        System.out.println("expected false. result:"+ new N9EPalindromeNumber().isPalindrome(10));
        System.out.println("expected false. result:"+ new N9EPalindromeNumber().isPalindrome(21120));
    }

    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x < 10) return true;

        int n = x % 10;
        if (n == 0) return false;
        x /= 10;
        while (x > n) {
            n = n * 10 + x % 10;
            x /= 10;
        }
        return x == n || x == n / 10;
    }

    public boolean isPalindrome2(int x) {
        if (x < 0) return false;

        StringBuilder sb = new StringBuilder();
        while (x > 0) {
            int n = x % 10;
            x /= 10;
            sb.append(n);
        }
        String str = sb.toString();
        for (int i = 0 ; i < str.length() / 2; i++)
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) return false;
        return true;
    }

    public boolean isPalindrome1(int x) {
        if (x < 0) return false;

        List<Integer> data = new ArrayList<>();

        while (x > 0) {
            int n = x % 10;
            x = (x - n) / 10;
            data.add(n);
        }
        for (int i = 0 ; i < data.size()/2; i++){
            if (data.get(i) != data.get(data.size()-i-1)) return false;
        }
        return true;
    }
}
