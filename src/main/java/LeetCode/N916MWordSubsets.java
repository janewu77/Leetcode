package LeetCode;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * You are given two string arrays words1 and words2.
 *
 * A string b is a subset of string a if every letter in b occurs in a including multiplicity.
 *
 * For example, "wrr" is a subset of "warrior" but is not a subset of "world".
 * A string a from words1 is universal if for every string b in words2, b is a subset of a.
 *
 * Return an array of all the universal strings in words1. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 * Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["e","o"]
 * Output: ["facebook","google","leetcode"]
 *
 *
 * Example 2:
 * Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["l","e"]
 * Output: ["apple","google","leetcode"]
 *
 *
 * Constraints:
 * 1 <= words1.length, words2.length <= 104
 * 1 <= words1[i].length, words2[i].length <= 10
 * words1[i] and words2[i] consist only of lowercase English letters.
 * All the strings of words1 are unique.
 *
 */

/**
 * M - [time: 30+]
 * - 第二个数组，合并操作。reduce to Single Word B
 *
 */
public class N916MWordSubsets {

    public List<String> wordSubsets(String[] words1, String[] words2) {
        final int[] condition = new int[26];
        Arrays.stream(words2)
                .map(s -> toCountArr(s))
                .forEach(c -> {
                    for (int i = 0; i < 26; i++) {
                        condition[i] = Math.max(condition[i], c[i]);
                    }
                });

        return Arrays.stream(words1)
                .filter(s -> possible(toCountArr(s), condition))
                .collect(Collectors.toList());
    }

    private boolean possible(int[] cntArr, int[] condition) {
        for (int i = 0; i < 26; i++)
            if (cntArr[i] < condition[i]) return false;
        return true;
    }

    private int[] toCountArr(String s) {
        int[] charCount = new int[26];
        for (int i = 0; i < s.length(); i++) charCount[s.charAt(i) - 'a']++;
        return charCount;
    }

    //Runtime: 16 ms, faster than 93.50% of Java online submissions for Word Subsets.
    //Memory Usage: 50.5 MB, less than 90.24% of Java online submissions for Word Subsets.
    //Time: O(N+M), Space: O(1)
    public List<String> wordSubsets_array(String[] words1, String[] words2) {

        //init pattern
        int[] pattern = new int[26]; //Space(1)
        //time: O(N*26)
        for (String word: words2){
            int[] tmpPattern = new int[26]; //Space(1)
            //time: O(l) l<26
            for (char c: word.toCharArray()) tmpPattern[c-'a']++;

            //time: O(26)
            for (int i = 0; i < pattern.length; i++)
                pattern[i] = Math.max(tmpPattern[i], pattern[i]);
        }

        //time: O(M*26)
        List<String> res = new ArrayList<>();
        outer:
        for (String word: words1){

            int[] tmp = new int[26]; //Space(1)
            //time: O(l) l<26
            for (char c: word.toCharArray()) tmp[c - 'a']++;

            //time: O(26)
            for (int i = 0; i < pattern.length; i++)
                if (tmp[i] < pattern[i]) continue outer;

            res.add(word);
        }
        return res;
    }


}
