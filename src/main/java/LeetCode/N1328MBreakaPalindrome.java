package LeetCode;


/**
 * Given a palindromic string of lowercase English letters palindrome, replace exactly one character with any lowercase English letter so that the resulting string is not a palindrome and that it is the lexicographically smallest one possible.
 *
 * Return the resulting string. If there is no way to replace a character to make it not a palindrome, return an empty string.
 *
 * A string a is lexicographically smaller than a string b (of the same length) if in the first position where a and b differ, a has a character strictly smaller than the corresponding character in b. For example, "abcc" is lexicographically smaller than "abcd" because the first position they differ is at the fourth character, and 'c' is smaller than 'd'.
 *
 *
 *
 * Example 1:
 *
 * Input: palindrome = "abccba"
 * Output: "aaccba"
 * Explanation: There are many ways to make "abccba" not a palindrome, such as "zbccba", "aaccba", and "abacba".
 * Of all the ways, "aaccba" is the lexicographically smallest.
 * Example 2:
 *
 * Input: palindrome = "a"
 * Output: ""
 * Explanation: There is no way to replace a single character to make "a" not a palindrome, so return an empty string.
 *
 *
 * Constraints:
 *
 * 1 <= palindrome.length <= 1000
 * palindrome consists of only lowercase English letters.
 */

/**
 * M - [30+
 */

public class N1328MBreakaPalindrome {


    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Break a Palindrome.
    //Memory Usage: 42.3 MB, less than 31.17% of Java online submissions for Break a Palindrome.
    //Time: O(N); Space: O(N)
    public String breakPalindrome(String palindrome) {
        if (palindrome.length() == 1) return "";

        char[] chars = palindrome.toCharArray();
        for (int i = 0; i < chars.length / 2; i++){
            if (chars[i] != 'a') {
                chars[i] = 'a';
                return String.valueOf(chars);
            }
        }
        chars[chars.length - 1] = 'b';
        return String.valueOf(chars);
    }


    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Break a Palindrome.
    //Memory Usage: 42.5 MB, less than 10.85% of Java online submissions for Break a Palindrome.
    //Time: O(N); Space: O(N)
    public String breakPalindrome_1(String palindrome) {
        if (palindrome.length() == 1) return "";

        char[] chars = palindrome.toCharArray();

        if (chars[0] != 'a') {
            chars[0] = 'a';
            return String.valueOf(chars);
        }

        int idx = 0;
        while (idx <= chars.length / 2 && chars[idx] == 'a') idx++;

        if (chars.length - idx - idx <= 1) chars[chars.length - 1] = 'b';
        else chars[idx] = 'a';

        return String.valueOf(chars);
    }
}
