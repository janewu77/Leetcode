package LeetCode;

import static java.time.LocalTime.now;

/**
 * In an alien language, surprisingly, they also use English lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.
 *
 * Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographically in this alien language.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * Output: true
 * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
 * Example 2:
 *
 * Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * Output: false
 * Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
 * Example 3:
 *
 * Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * Output: false
 * Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 20
 * order.length == 26
 * All characters in words[i] and order are English lowercase letters.
 */
public class N953EVerifyinganAlienDictionary {

    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");

        doRun(false, new String[]{"word","world","row"}, "worldabcefghijkmnpqstuvxyz");
        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(boolean expect, String[] words, String order) {
        boolean res = new N953EVerifyinganAlienDictionary().isAlienSorted(words, order);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 0ms 100%; Memory: 40.3MB 99%
    //Time: O(N * L); Space: O(1)
    //let N be the number of words, let L be the average length of words;
    public boolean isAlienSorted(String[] words, String order) {
        int[] dict = new int[26];

        for (int i = 0; i < order.length(); i++)
            dict[order.charAt(i) - 'a'] = i;

        String word = words[0];
        for (int i = 1; i < words.length; i++) {
            if (compare(word, words[i], dict) > 0)
                return false;
            word = words[i];
        }
        return true;
    }

    private int compare(String str1, String str2, int[] dict){
        int i = 0, j = 0;
        while (i < str1.length() && j < str2.length()){
            if (str1.charAt(i) == str2.charAt(j)){
                i++; j++;
            }else {
                return dict[str1.charAt(i) - 'a'] - dict[str2.charAt(j) - 'a'];
            }
        }
        return (i >= str1.length()) ? -1 : 1;
    }

    //iteration
    // compare(word, 0, words[i], 0, dict)
    private int compare1(String str1, int i, String str2, int j, int[] dict){

        if (i >= str1.length()) return -1;
        if (j >= str2.length()) return 1;

        if (str1.charAt(i) == str2.charAt(j)){
            return compare1(str1, i + 1, str2, j + 1, dict);
        }else{
            return dict[str1.charAt(i) - 'a'] - dict[str2.charAt(j) - 'a'];
        }
    }
}
