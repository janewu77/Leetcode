package LeetCode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.time.LocalTime.now;

/**
 * Given a pattern and a string s, find if s follows the same pattern.
 *
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.
 *
 *
 *
 * Example 1:
 *
 * Input: pattern = "abba", s = "dog cat cat dog"
 * Output: true
 * Example 2:
 *
 * Input: pattern = "abba", s = "dog cat cat fish"
 * Output: false
 * Example 3:
 *
 * Input: pattern = "aaaa", s = "dog cat cat dog"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= pattern.length <= 300
 * pattern contains only lower-case English letters.
 * 1 <= s.length <= 3000
 * s contains only lowercase English letters and spaces ' '.
 * s does not contain any leading or trailing spaces.
 * All the words in s are separated by a single space.
 */
public class N290EWordPattern {

    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");

        doRun(true, "ccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccdd", "s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s t t");
        doRun(true, "abba", "dog cat cat dog");

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(boolean expect, String pattern, String s) {
        boolean res = new N290EWordPattern().wordPattern(pattern, s);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //2.Map index
    //Runtime: 4ms 11%; Space: 40.8MB 65%
    //Time: O(L); Space: O(L + M)
    //let L be the length of the input String s
    //let M be the length of the input String pattern
    public boolean wordPattern(String pattern, String s) {
        Map<String, Integer> map = new HashMap<>();

        String[] words = s.split(" ");
        if (words.length != pattern.length()) return false;

        for (int i = 0; i < pattern.length(); i++) {
            String c = "_" + pattern.charAt(i);
            if (!map.containsKey(c)) map.put(c, i);
            if (!map.containsKey(words[i])) map.put(words[i], i);
            if (!map.get(c).equals(map.get(words[i])))
                return false;
        }
        return true;
    }

    //1.Map
    //Runtime: 1ms 95%; Space: 40MB 95%
    //Time: O(L); Space: O(L)
    //let L be the length of the input String s
    public boolean wordPattern_1(String pattern, String s) {
        Map<Character, String> map = new HashMap<>();

        String[] strs = s.split(" ");
        if (strs.length != pattern.length()) return false;

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);

            if (map.containsKey(c)) {
                if (!map.get(c).equals(strs[i]))
                    return false;
            }else {
                if (map.containsValue(strs[i]))
                    return false;
                map.put(c, strs[i]);
            }
        }
        return true;
    }
}
