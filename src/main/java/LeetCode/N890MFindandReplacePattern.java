package LeetCode;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * Given a list of strings words and a string pattern,
 * return a list of words[i] that match pattern.
 * You may return the answer in any order.
 *
 * A word matches the pattern if there exists a permutation
 * of letters p so that after replacing every letter x in the pattern with p(x),
 * we get the desired word.
 *
 * Recall that a permutation of letters is a bijection from letters
 * to letters: every letter maps to another letter, and no two letters map to the same letter.
 *
 *
 *
 * Example 1:
 * Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
 * Output: ["mee","aqq"]
 * Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}.
 * "ccc" does not match the pattern because {a -> c, b -> c, ...}
 * is not a permutation, since a and b map to the same letter.
 *
 *
 * Example 2:
 * Input: words = ["a","b","c"], pattern = "a"
 * Output: ["a","b","c"]
 *
 *
 * Constraints:
 *
 * 1 <= pattern.length <= 20
 * 1 <= words.length <= 50
 * words[i].length == pattern.length
 * pattern and words[i] are lowercase English letters.
 *
 */

/**
 * M - [time: 15-]
 * -  hashmap  >> 定长的array >> indexOf
 */
public class N890MFindandReplacePattern {
    public static void main(String[] args){

        System.out.println('a'-1);

        String[] words;
        String pattern;

        words = new String[]{"ckpzbkxapkoskxyzbvwd","ydfyvohgkaqbubahuvny","citidpbjgimncijkmlbf","qvndbejornojrskvtxmi","uajzingcclqoncbjyehm","xyaqgrtxrwuebtnzekhw","mxszpzelpzowqnkjhfzn","wxygiqidtonscxmrppsc","mfknjktbypeocnrkiryw","kwcbztpoixwmfrszuczw"};
        pattern = "mrctguqnnbbauddkzxiv";
        new N890MFindandReplacePattern().doRun(words,pattern);

        words = new String[]{"abc","deq","mee","aqq","dkd","ccc"};
        pattern = "abb";
        new N890MFindandReplacePattern().doRun(words,pattern);


    }
    private void doRun(String[] words, String pattern){
        List<String> res = findAndReplacePattern(words, pattern);
        System.out.println(res.stream().collect(Collectors.joining(",")));
        System.out.println("=================");
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> res = new ArrayList<>();
        for (String word: words)
            if (findAndReplacePattern_indexOf(word, pattern)) res.add(word);
        return res;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Find and Replace Pattern.
    //Memory Usage: 41 MB, less than 98.67% of Java online submissions for Find and Replace Pattern.
    //Time: O(N*M);  Space: O(1)
    private boolean findAndReplacePattern_indexOf(String word, String pattern){
        for (int k =0; k< word.length(); k++)
            if (word.indexOf(word.charAt(k)) != pattern.indexOf(pattern.charAt(k)))
                return false;
        return true;
    }

    //Runtime: 1 ms, faster than 98.01% of Java online submissions for Find and Replace Pattern.
    //Memory Usage: 42.6 MB, less than 74.75% of Java online submissions for Find and Replace Pattern.
    //Time: O(N*M);  Space: O(1)
    private boolean findAndReplacePattern_array(String word, String pattern){
        int[] mapping = new int[26];
        int[] used = new int[26];
        for (int i = 0; i < word.length(); i++){
            int wI = word.charAt(i) - 'a';
            int pI = pattern.charAt(i) - 'a';
            if (mapping[wI] != 0){
                if (mapping[wI] != pI+1) return false;
            }else{
                if (++used[pI] > 1) return false;
                mapping[wI] = pI+1;
            }
        }
        return true;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Runtime: 3 ms, faster than 54.32% of Java online submissions for Find and Replace Pattern.
    //Memory Usage: 43.3 MB, less than 33.39% of Java online submissions for Find and Replace Pattern.
    //Hashmap
    //Time: O(N*M);  Space: O(N)
    private boolean findAndReplacePattern_hashmap(String word, String pattern){
        Map<Character, Character> mapping = new HashMap<>();
        for (int i = 0; i < word.length(); i++){
            if (mapping.containsKey(word.charAt(i))){
                if (mapping.get(word.charAt(i)) != pattern.charAt(i)) return false;
            }else{
                if (mapping.values().contains(pattern.charAt(i))) return false;
                mapping.put(word.charAt(i), pattern.charAt(i));
            }
        }
        return true;
    }

}
