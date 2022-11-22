package LeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 *
 *
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 *
 * Example 2:
 *
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 *
 *
 * Example 3:
 *
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 5 * 104
 * s consists of English letters, digits, symbols and spaces.
 *
 *
 */

/**
 * M - [time: 45-
 */
public class N3MLongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        String s = "";

        s = "tmmzuxt";
        doRun(5, s);

        s = "abcabcbb";
        doRun(3, s);

        s = "aaab";
        doRun(2, s);

        s ="pwwkew";
        doRun(3, s);
    }

    private static void doRun(int expected, String s){
        int res = new N3MLongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring(s);
        //String strRes = Arrays.stream(res).mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(""));
        //System.out.println("=======");
        System.out.println("["+(expected == res)+"].[expected:"+ expected+"] res:"+res);
    }


    //Runtime: 8 ms, faster than 78.13% of Java online submissions for Longest Substring Without Repeating Characters.
    //Memory Usage: 44.9 MB, less than 51.17% of Java online submissions for Longest Substring Without Repeating Characters.
    //HashMap。 跳移，但HashMap里的key不移走。
    //Time: O(N), Space: O(N)
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 1) return s.length();
        int res = 0;
        int left = 0, right = 0;

        Map<Character, Integer> map = new HashMap<>();
        while (right < s.length()){
            char c = s.charAt(right);

            if (map.containsKey(c))
                left = Math.max(map.get(c) + 1, left);

            map.put(c, right++);
            res = Math.max(res, right - left);
        }
        return res;
    }


    //Runtime: 11 ms, faster than 65.69% of Java online submissions for Longest Substring Without Repeating Characters.
    //Memory Usage: 45.9 MB, less than 37.27% of Java online submissions for Longest Substring Without Repeating Characters.
    //HashMap. 不需要的字母及时移出hashmap了。
    //Time: O(N), Space: O(k)
    public int lengthOfLongestSubstring_1(String s) {
        if (s.length() == 1) return s.length();
        int res = 0;
        int left = 0, right = 0;

        Map<Character, Integer> map = new HashMap<>();

        while (right < s.length()){
            char c = s.charAt(right);

            if (map.containsKey(c)){
                while (left < right && s.charAt(left) != c) map.remove(s.charAt(left++));
                left++;
            }
            map.put(c, right++);
            res = Math.max(res, right - left);
        }
        return res;
    }


}
