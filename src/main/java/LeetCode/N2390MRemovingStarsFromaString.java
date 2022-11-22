package LeetCode;

import java.util.*;

/**
 *
 * You are given a string s, which contains stars *.
 *
 * In one operation, you can:
 *
 * Choose a star in s.
 * Remove the closest non-star character to its left, as well as remove the star itself.
 * Return the string after all stars have been removed.
 *
 * Note:
 *
 * The input will be generated such that the operation is always possible.
 * It can be shown that the resulting string will always be unique.
 *
 *
 * Example 1:
 *
 * Input: s = "leet**cod*e"
 * Output: "lecoe"
 * Explanation: Performing the removals from left to right:
 * - The closest character to the 1st star is 't' in "leet**cod*e". s becomes "lee*cod*e".
 * - The closest character to the 2nd star is 'e' in "lee*cod*e". s becomes "lecod*e".
 * - The closest character to the 3rd star is 'd' in "lecod*e". s becomes "lecoe".
 * There are no more stars, so we return "lecoe".
 * Example 2:
 *
 * Input: s = "erase*****"
 * Output: ""
 * Explanation: The entire string is removed, so we return an empty string.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s consists of lowercase English letters and stars *.
 * The operation above can be performed on s.
 */

/**
 * M - Time: [5-
 *
 */
public class N2390MRemovingStarsFromaString {



    //Runtime: 13 ms, faster than 50.00% of Java online submissions for Removing Stars From a String.
    //Memory Usage: 43.1 MB, less than 100.00% of Java online submissions for Removing Stars From a String.
    //two pointers
    //Time:O(N); Space:O(N)
    public String removeStars(String s) {
        char[] chars = s.toCharArray();
        int j = 0;
        for (int i = 0; i < chars.length; i++){
            if (chars[i] == '*') j--;
            else chars[j++] = chars[i];
        }
        return new String(chars, 0, j);
    }


    //Runtime: 45 ms, faster than 25.00% of Java online submissions for Removing Stars From a String.
    //Memory Usage: 42.9 MB, less than 100.00% of Java online submissions for Removing Stars From a String.
    public String removeStars_2(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '*') sb.deleteCharAt(sb.length() - 1); //耗时
            else sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    //Runtime: 104 ms, faster than 25.00% of Java online submissions for Removing Stars From a String.
    //Memory Usage: 73.1 MB, less than 25.00% of Java online submissions for Removing Stars From a String.
    //deque
    public String removeStars_1(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        for (char c: s.toCharArray()){
            if (c != '*') deque.addLast(c);
            else deque.removeLast();
        }

        StringBuilder sb = new StringBuilder();
        while(!deque.isEmpty())
            sb.append(deque.pollFirst());

        return sb.toString();
    }
}
