package LeetCode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Given an input string s, reverse the order of the words.
 *
 * A word is defined as a sequence of non-space characters.
 * The words in s will be separated by at least one space.
 *
 * Return a string of the words in reverse order concatenated by a single space.
 * Note that s may contain leading or trailing spaces or multiple spaces between two words.
 * The returned string should only have a single space separating the words. Do not include any extra spaces.
 *
 *
 *
 * Example 1:
 * Input: s = "the sky is blue"
 * Output: "blue is sky the"
 *
 *
 * Example 2:
 * Input: s = "  hello world  "
 * Output: "world hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 *
 *
 * Example 3:
 * Input: s = "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * s contains English letters (upper-case and lower-case), digits, and spaces ' '.
 * There is at least one word in s.
 *
 *
 * Follow-up: If the string data type is mutable in your language, can you solve it in-place with O(1) extra space?
 */
public class N151MReverseWordsinaString {

    public static void main(String[] args){

//        System.out.println("resut:"+new Solution().reverseWords("a good   example"));
//        System.out.println("resut:"+new Solution().reverseWords("  hello world  "));
//        System.out.println("resut:"+new Solution().reverseWords(" a "));
    }

    //2.
    //Runtime: 3 ms, faster than 98.80% of Java online submissions for Reverse Words in a String.
    //Memory Usage: 41.7 MB, less than 99.42% of Java online submissions for Reverse Words in a String.
    //Time: O(N); Space:O(N)
    public String reverseWords_2(String s) {
        StringBuilder sb = new StringBuilder();

        int right = s.length() - 1;
        while (right >= 0 ) {
            while (right >= 0 && (s.charAt(right) == ' ')) right--;

            int left = right;
            while (left >= 0 && (s.charAt(left) != ' ')) left--;

            if (left <= right && left >= -1 && right >= 0){
                sb.append(s.substring(left + 1, right + 1)).append(" ");
            }
            right = left;
        }
        sb.deleteCharAt(sb.length()- 1);
        return sb.toString();
    }


    //1.build-in split reverse
    //Runtime: 8 ms, faster than 84.19% of Java online submissions for Reverse Words in a String.
    //Memory Usage: 42.5 MB, less than 88.12% of Java online submissions for Reverse Words in a String.
    //Time: O(N); Space:O(N)
    public String reverseWords_1(String s) {
        List<String> list = Arrays.asList(s.trim().split("\\s+"));
        Collections.reverse(list);
        return String.join(" ", list);
    }



}
