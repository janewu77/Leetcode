package LeetCode;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 *
 * In other words, return true if one of s1's permutations is the substring of s2.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2:
 *
 * Input: s1 = "ab", s2 = "eidboaoo"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s1.length, s2.length <= 104
 * s1 and s2 consist of lowercase English letters.
 */
public class N567MPermutationinString {


    //2.slide window
    //Runtime: 4ms 92%; Memory: 41.8MB 98%
    //Time: O(N1 + N2); Space: O(1)
    public boolean checkInclusion(String s1, String s2) {
        int[] counter = new int[26];
        for (int i = 0; i < s1.length(); i++)
            counter[s1.charAt(i) - 'a']++;

        for (int left = 0, right = 0; right < s2.length(); right++){
            int c = s2.charAt(right) - 'a';
            while (left < right && counter[c] == 0)
                counter[s2.charAt(left++) - 'a']++;

            if (counter[c] == 0) left++;
            else counter[c]--;

            if (right - left == s1.length() - 1)
                return true;
        }
        return false;
    }


    //1.counter
    //Runtime: 254ms 20%; Memory: 42.2MB 72%
    //Time: O(N1 + N2 * N1); Space: O(1)
    public boolean checkInclusion_1(String s1, String s2) {
        int[] counter1 = new int[26];
        for (int i = 0; i < s1.length(); i++)
            counter1[s1.charAt(i) - 'a']++;

        for (int i = 0; i <= s2.length() - s1.length(); i++) {
            if (counter1[s2.charAt(i) - 'a'] == 0)
                continue;
            int[] counter2 = Arrays.copyOf(counter1, counter1.length);
            if (check(counter2, s2, i, i + s1.length()))
                return true;
        }
        return false;
    }

    private boolean check(int[] counter, String str, int begin, int end) {
        for (int i = begin; i < end; i++) {
            int count = (--counter[str.charAt(i) - 'a']);
            if (count < 0) return false;
        }
        return true;
    }

}
