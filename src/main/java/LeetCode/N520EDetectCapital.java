package LeetCode;


/**
 * We define the usage of capitals in a word to be right when one of the following cases holds:
 *
 * All letters in this word are capitals, like "USA".
 * All letters in this word are not capitals, like "leetcode".
 * Only the first letter in this word is capital, like "Google".
 * Given a string word, return true if the usage of capitals in it is right.
 *
 *
 *
 * Example 1:
 *
 * Input: word = "USA"
 * Output: true
 * Example 2:
 *
 * Input: word = "FlaG"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= word.length <= 100
 * word consists of lowercase and uppercase English letters.
 */
public class N520EDetectCapital {

    //2.Regex
    //Runtime: 7ms 9%; Memory: 42.6MB 35%
    //Time: O(N); Space: O(1);
    public boolean detectCapitalUse(String word) {
        //[A-Z]* all capitals
        //.[a-z]* first one can be capital or not; others are non-capitals
        return word.matches("[A-Z]*|.[a-z]*");
    }

    //1.character by character
    //Runtime: 1ms 100%; Memory: 40.5MB 90%
    //Time: O(N); Space: O(1);
    public boolean detectCapitalUse_1(String word) {
        if (word.toUpperCase().equals(word))
            return true;
        for (int i = 1; i < word.length(); i++)
            if (word.charAt(i) <'a') return false;
        return true;
    }


}
