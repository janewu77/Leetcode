package LeetCode;


import java.util.ArrayList;
import java.util.List;

/**
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 *
 * Input: s = "abab", p = "ab"
 * Output: [0,1,2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 *
 *
 * Constraints:
 *
 * 1 <= s.length, p.length <= 3 * 104
 * s and p consist of lowercase English letters.
 */
public class N438MFindAllAnagramsinaString {

    //1. slide window
    //Runtime: 5ms 97%; Memory: 43.4MB 68%
    //Time: O(N1 + N2); Space: O(1)
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();

        int[] counter = new int[26];
        for (int i = 0; i < p.length(); i++)
            counter[p.charAt(i) - 'a']++;

        for (int left = 0, right = 0; right < s.length(); right++ ) {
            int c = s.charAt(right) - 'a';
            while (left < right && counter[c] == 0)
                counter[s.charAt(left++) - 'a']++;

            if (counter[c] == 0) left++;
            else counter[c]--;

            if (right - left == p.length() - 1)
                res.add(left);
        }
        return res;
    }
}
