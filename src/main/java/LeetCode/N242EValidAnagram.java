package LeetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 *
 *
 *
 * Example 1:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 *
 *
 * Example 2:
 * Input: s = "rat", t = "car"
 * Output: false
 *
 *
 * Constraints:
 * 1 <= s.length, t.length <= 5 * 104
 * s and t consist of lowercase English letters.
 *
 *
 * Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
 */

/**
 * E - [time : 10-
 *
 */
public class N242EValidAnagram {

    public static void main(String[] a){
//        boolean res =  new N242EValidAnagram().isAnagram("a","ab");
        boolean res =  new N242EValidAnagram().isAnagram("anagram","nagaram");
        System.out.println(res);
    }

    //Runtime: 16 ms, faster than 33.30% of Java online submissions for Valid Anagram.
    //Memory Usage: 47.6 MB, less than 5.37% of Java online submissions for Valid Anagram.
    //time: O(nlogn), space: O(1) / O（N)
    //sort
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        char[] sChar = s.toCharArray(); //O（N) , language depend
        char[] tChar = t.toCharArray();
        Arrays.sort(sChar);
        Arrays.sort(tChar);

        return Arrays.toString(sChar).equals(Arrays.toString(tChar));
    }

    //Runtime: 3 ms, faster than 95.30% of Java online submissions for Valid Anagram.
    //Memory Usage: 42.2 MB, less than 96.93% of Java online submissions for Valid Anagram.
    //Frequency counter
    public boolean isAnagram_2(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] memo = new int[26];

        for (int i = 0; i<s.length(); i++){
            memo[s.charAt(i)-'a']++;
            memo[t.charAt(i)-'a']--;
        }

        for(int val : memo)
            if (val != 0) return false;

        return true;
    }


    //Runtime: 20 ms, faster than 27.40% of Java online submissions for Valid Anagram.
    //Memory Usage: 46.7 MB, less than 15.13% of Java online submissions for Valid Anagram.
    public boolean isAnagram_1(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Integer> memo = new HashMap<>();

        for (int i = 0; i < s.length(); i++){
            int c = memo.getOrDefault(s.charAt(i), 0) + 1;
            memo.put(s.charAt(i), c);
        }
        for (int i = 0; i < t.length(); i++){
            int c = memo.getOrDefault(t.charAt(i), 0) - 1;
            if (c < 0) return false;
            memo.put(t.charAt(i), c);

        }

        for(Character k : memo.keySet())
            if (memo.get(k) != 0) return false;

        return true;
    }
}



