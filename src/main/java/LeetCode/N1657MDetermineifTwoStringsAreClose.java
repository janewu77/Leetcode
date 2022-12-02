package LeetCode;

import java.io.IOException;
import java.util.*;

import static java.time.LocalTime.now;

/**
 * Two strings are considered close if you can attain one from the other using the following operations:
 *
 * Operation 1: Swap any two existing characters.
 * For example, abcde -> aecdb
 * Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
 * For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
 * You can use the operations on either string as many times as necessary.
 *
 * Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: word1 = "abc", word2 = "bca"
 * Output: true
 * Explanation: You can attain word2 from word1 in 2 operations.
 * Apply Operation 1: "abc" -> "acb"
 * Apply Operation 1: "acb" -> "bca"
 * Example 2:
 *
 * Input: word1 = "a", word2 = "aa"
 * Output: false
 * Explanation: It is impossible to attain word2 from word1, or vice versa, in any number of operations.
 * Example 3:
 *
 * Input: word1 = "cabbba", word2 = "abbccc"
 * Output: true
 * Explanation: You can attain word2 from word1 in 3 operations.
 * Apply Operation 1: "cabbba" -> "caabbb"
 * Apply Operation 2: "caabbb" -> "baaccc"
 * Apply Operation 2: "baaccc" -> "abbccc"
 *
 *
 * Constraints:
 *
 * 1 <= word1.length, word2.length <= 105
 * word1 and word2 contain only lowercase English letters.
 */
public class N1657MDetermineifTwoStringsAreClose {


    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");
        doRun(false, "uau", "ssx");
        doRun(true, "aba", "bab");
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(boolean expect, String word1, String word2) {
        boolean res = new N1657MDetermineifTwoStringsAreClose().closeStrings(word1, word2);
//        comm.printListListString(expect, res);

//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Every character that exists in word1 must exist in word2 as well
    //The frequency of all the characters is always

    //3.bitwise
    //Runtime: 13 ms, faster than 92.44% of Java online submissions for Determine if Two Strings Are Close.
    //Memory Usage: 42.8 MB, less than 95.56% of Java online submissions for Determine if Two Strings Are Close.
    //Time: O(N); Space: O(1)
    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) return false;

        int bitwise1 = 0, bitwise2 = 0;
        int[] data1 = new int[26];
        int[] data2 = new int[26];

        for(char c: word1.toCharArray()) {
            data1[c - 'a']++;
            bitwise1 |= (1<< (c - 'a'));
        }
        for(char c: word2.toCharArray()) {
            data2[c - 'a']++;
            bitwise2 |= (1<< (c - 'a'));
        }

        if (bitwise1 != bitwise2) return false;

        Arrays.sort(data1);
        Arrays.sort(data2);
        return Arrays.equals(data1, data2);
    }


    //2.counter array
    //Runtime: 18 ms, faster than 81.33% of Java online submissions for Determine if Two Strings Are Close.
    //Memory Usage: 60 MB, less than 35.56% of Java online submissions for Determine if Two Strings Are Close.
    //Time: O(N); Space: O(26 + 26 + 26)
    //Time: O(N); Space: O(1)
    public boolean closeStrings_2(String word1, String word2) {
        if (word1.length() != word2.length()) return false;

        int[] data1 = new int[26];
        Map<Integer, Integer> map = new HashMap<>();
        for(char c: word1.toCharArray()) data1[c - 'a']++;
        for (int i = 0; i < 26; i++) {
            if (data1[i] <= 0) continue;
            map.put(data1[i], map.getOrDefault(data1[i], 0) + 1);
        }

        int[] data2 = new int[26];
        for(char c: word2.toCharArray()) data2[c - 'a']++;

        for (int i = 0; i < 26; i++) {
            if (data1[i] == 0 && data1[i] == 0) continue;
            if (data1[i] == 0 || data2[i] == 0) return false;
        }

        for (int i = 0; i < 26; i++) {
            if (data2[i] <= 0) continue;
            int count = map.getOrDefault(data2[i], 0) - 1;
            if (count < 0) return false;
            else map.put(data2[i], count);
        }
        return true;
    }


    //1.HashMap
    //Runtime: 88 ms, faster than 38.22% of Java online submissions for Determine if Two Strings Are Close.
    //Memory Usage: 43.5 MB, less than 80.44% of Java online submissions for Determine if Two Strings Are Close.
    //Time: O(N); Space: O(1)
    public boolean closeStrings_1(String word1, String word2) {
        if (word1.length() != word2.length()) return false;

        Map<Character,Integer> map1 = new HashMap<>();
        Map<Character,Integer> map2 = new HashMap<>();
        for (int i = 0; i < word1.length(); i++)
            map1.put(word1.charAt(i), map1.getOrDefault(word1.charAt(i), 0) + 1);

        for (int i = 0; i < word2.length(); i++)
            map2.put(word2.charAt(i), map2.getOrDefault(word2.charAt(i), 0) + 1);

        if (!map1.keySet().equals(map2.keySet())) return false;

        Map<Integer, Integer> map = new HashMap<>();
        for(Map.Entry<Character, Integer> entry : map1.entrySet())
            map.put(entry.getValue(), map.getOrDefault(entry.getValue(), 0) + 1);

        for(Map.Entry<Character, Integer> entry : map2.entrySet()){
            int c = map.getOrDefault(entry.getValue(), 0) - 1;
            if (c < 0) return false;
            map.put(entry.getValue(), c);
        }

        return true;
    }
}
