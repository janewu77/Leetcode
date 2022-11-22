package LeetCode;

import java.util.*;

/**
 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 * All occurrences of a character must be replaced with another character
 * while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.
 *
 *
 * Example 1:
 * Input: s = "egg", t = "add"
 * Output: true
 *
 * Example 2:
 * Input: s = "foo", t = "bar"
 * Output: false
 *
 *
 * Example 3:
 * Input: s = "paper", t = "title"
 * Output: true
 *
 * Constraints:
 * 1 <= s.length <= 5 * 104
 * t.length == s.length
 * s and t consist of any valid ascii character.
 */

/**
 * E - [time: 15-]
 */
public class N205EIsomorphicStrings {

    public static void main(String[] args){

//        String s = "badc";
//        String t = "baba";

        String s = "paper";
        String t = "title";

        boolean res = new N205EIsomorphicStrings().isIsomorphic(s, t);
        System.out.println(res);
//
//        s = "badc";
//        t = "baba";
//
//        res = new N205EIsomorphicStrings().isIsomorphic(s, t);
//        System.out.println(res);

    }


    //Runtime: 7 ms, faster than 83.12% of Java online submissions for Isomorphic Strings.
    //Memory Usage: 42.5 MB, less than 84.02% of Java online submissions for Isomorphic Strings.
    public boolean isIsomorphic(String s, String t) {
        char[] mapping = new char[26];
        Arrays.fill(mapping, 'Z');
        Set<Character> tSet = new HashSet<>();
        s = s.toLowerCase();
        t = t.toLowerCase();
        for(int i = 0; i < s.length(); i++){
            int key = s.charAt(i) - 'a';
            if(mapping[key] != 'Z'){
                if (mapping[key] != t.charAt(i)) return false;
            }else{
                if (tSet.contains(t.charAt(i))) return false;
                mapping[key] = t.charAt(i);
                tSet.add(t.charAt(i));
            }
        }
        return true;
    }

    public boolean isIsomorphic1(String s, String t) {
        Map<Character, Character> mapping = new HashMap<>();
        Set<Character> tSet = new HashSet<>();
        for(int i = 0; i < s.length(); i++){
            if (mapping.containsKey(s.charAt(i))){
                if (mapping.get(s.charAt(i)) != t.charAt(i)) return false;
            } else {
                if (tSet.contains(t.charAt(i))) return false;
                mapping.put(s.charAt(i), t.charAt(i));
                tSet.add(t.charAt(i));
            }
        }
        return true;
    }

}
