package LeetCode;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * International Morse Code defines a standard encoding where each letter is mapped to a series of dots and dashes, as follows:
 *
 * 'a' maps to ".-",
 * 'b' maps to "-...",
 * 'c' maps to "-.-.", and so on.
 * For convenience, the full table for the 26 letters of the English alphabet is given below:
 *
 * [".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
 * Given an array of strings words where each word can be written as a concatenation
 * of the Morse code of each letter.
 *
 * For example, "cab" can be written as "-.-..--...", which is the concatenation of "-.-.", ".-",
 * and "-...". We will call such a concatenation the transformation of a word.
 * Return the number of different transformations among all words we have.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["gin","zen","gig","msg"]
 * Output: 2
 * Explanation: The transformation of each word is:
 * "gin" -> "--...-."
 * "zen" -> "--...-."
 * "gig" -> "--...--."
 * "msg" -> "--...--."
 * There are 2 different transformations: "--...-." and "--...--.".
 * Example 2:
 *
 * Input: words = ["a"]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 12
 * words[i] consists of lowercase English letters.
 */

/**
 * E - [time: 5-
 *
 */
public class N804EUniqueMorseCodeWords {

    //HashSet
    //Time: O(S); Space: O(S)
    //where S is the sum of the lengths of words in words. We iterate through each character of each word in words.
    public int uniqueMorseRepresentations(String[] words) {
      String[] map = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

      Set<String> set = new HashSet<>();
      for (String word : words) {
          StringBuilder sb = new StringBuilder();
          for (char c : word.toCharArray())
              sb.append(map[c - 'a']);
          set.add(sb.toString());
      }
        return set.size();
    }
}
