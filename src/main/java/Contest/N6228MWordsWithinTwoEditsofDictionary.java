package Contest;

import java.util.ArrayList;
import java.util.List;

import static java.time.LocalTime.now;


/**
 * You are given two string arrays, queries and dictionary. All words in each array comprise of lowercase English letters and have the same length.
 *
 * In one edit you can take a word from queries, and change any letter in it to any other letter. Find all words from queries that, after a maximum of two edits, equal some word from dictionary.
 *
 * Return a list of all words from queries, that match with some word from dictionary after a maximum of two edits. Return the words in the same order they appear in queries.
 *
 *
 *
 * Example 1:
 *
 * Input: queries = ["word","note","ants","wood"], dictionary = ["wood","joke","moat"]
 * Output: ["word","note","wood"]
 * Explanation:
 * - Changing the 'r' in "word" to 'o' allows it to equal the dictionary word "wood".
 * - Changing the 'n' to 'j' and the 't' to 'k' in "note" changes it to "joke".
 * - It would take more than 2 edits for "ants" to equal a dictionary word.
 * - "wood" can remain unchanged (0 edits) and match the corresponding dictionary word.
 * Thus, we return ["word","note","wood"].
 * Example 2:
 *
 * Input: queries = ["yes"], dictionary = ["not"]
 * Output: []
 * Explanation:
 * Applying any two edits to "yes" cannot make it equal to "not". Thus, we return an empty array.
 *
 *
 * Constraints:
 *
 * 1 <= queries.length, dictionary.length <= 100
 * n == queries[i].length == dictionary[j].length
 * 1 <= n <= 100
 * All queries[i] and dictionary[j] are composed of lowercase English letters.
 *
 */

/**
 * M - 30-
 */
//2452. Words Within Two Edits of Dictionary
public class N6228MWordsWithinTwoEditsofDictionary {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        //doRun(true, "abaccb", new int[]{1,2,3});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, String beginWord) {
//        int res = new N2().ladderLength(beginWord);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //1.count two edit differences in words
    //Runtime: 3 ms, faster than 100.00% of Java online submissions for Words Within Two Edits of Dictionary.
    //Memory Usage: 43.1 MB, less than 50.00% of Java online submissions for Words Within Two Edits of Dictionary.
    //Time : O(N * M * K); Space: O(1)
    //let N be the number of the word in queries, M be the number of the words in dictionary
    //let K be the length of the word in input list.
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> res = new ArrayList<>();
        for (String word : queries) {
            for (String dicWord: dictionary){
                if (helper_compare(word, dicWord)) {
                    res.add(word);
                    break;
                }
            }
        }
        return res;
    }

    //Time: worst case : O(K)
    private boolean helper_compare(String word, String dicWord) {
        int diffCount = 0;
        for (int i = 0; i < word.length(); i++){
            if (word.charAt(i) != dicWord.charAt(i)) {
                diffCount++;
                if (diffCount > 2) return false;
            }
        }
        return true;
    }


}
