package LeetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * A pangram is a sentence where every letter of the English alphabet appears at least once.
 *
 * Given a string sentence containing only lowercase English letters, return true if sentence is a pangram, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: sentence = "thequickbrownfoxjumpsoverthelazydog"
 * Output: true
 * Explanation: sentence contains at least one of every letter of the English alphabet.
 * Example 2:
 *
 * Input: sentence = "leetcode"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= sentence.length <= 1000
 * sentence consists of lowercase English letters.
 */

/**
 * E- [time: 5-
 */
public class N1832ECheckiftheSentenceIsPangram {


    //4. Bit Manipulation
    //Runtime: 1 ms, faster than 88.72% of Java online submissions for Check if the Sentence Is Pangram.
    //Memory Usage: 40 MB, less than 98.09% of Java online submissions for Check if the Sentence Is Pangram.
    //Time: O(N); Space:O(1);
    public boolean checkIfPangram(String sentence) {
        int bit = 0;
        for (int i = 0; i < sentence.length(); i++)
            bit = bit | (1 << sentence.charAt(i)-'a');
        return bit + 1 == 1<<26;
    }

    //3.alphabet
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Check if the Sentence Is Pangram.
    //Memory Usage: 42.5 MB, less than 19.09% of Java online submissions for Check if the Sentence Is Pangram.
    //Time: O(N); Space:O(1);
    public boolean checkIfPangram_3(String sentence) {
        for (int i = 0; i < 26; i++)
            if (sentence.indexOf('a'+i) == -1) return false;
        return true;
    }

    //2.counter
    //Runtime: 3 ms, faster than 54.57% of Java online submissions for Check if the Sentence Is Pangram.
    //Memory Usage: 42.1 MB, less than 45.15% of Java online submissions for Check if the Sentence Is Pangram.
    //Time: O(N); Space:O(1);
    public boolean checkIfPangram_2(String sentence) {
        if (sentence.length() < 26) return false;
        int[] counter = new int[26];
        for (int i = 0; i < sentence.length(); i++)
            counter[sentence.charAt(i)-'a']++;
        for (int c : counter)  if (c== 0) return false;
        return true;
    }

    //1.set
    //Runtime: 7 ms, faster than 23.75% of Java online submissions for Check if the Sentence Is Pangram.
    //Memory Usage: 42.3 MB, less than 26.59% of Java online submissions for Check if the Sentence Is Pangram.
    //Time: O(N); Space:O(1);
    public boolean checkIfPangram_1(String sentence) {
        if (sentence.length() < 26) return false;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < sentence.length(); i++)
            set.add(sentence.charAt(i));
        return set.size() == 26;
    }



}
