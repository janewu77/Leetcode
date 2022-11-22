package LeetCode;


import java.util.*;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

/**
 * You are given a string s and an array of strings words of the same length.
 * Return all starting indices of substring(s) in s that is a concatenation of each
 * word in words exactly once, in any order, and without any intervening characters.
 *
 * You can return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "barfoothefoobarman", words = ["foo","bar"]
 * Output: [0,9]
 * Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
 * The output order does not matter, returning [9,0] is fine too.
 * Example 2:
 *
 * Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * Output: []
 * Example 3:
 *
 * Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * Output: [6,9,12]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 30
 * s and words[i] consist of lowercase English letters.
 */

/**
 * H - [time: 120+
 *  - slide window
 *  - 坑： map 的操作有些很耗时，比如 new Hashmap(oldMap)
 *
 *
 */
public class N30HSubstringwithConcatenationofAllWords {

    public static void main(String[] args){
        int[] data;

        System.out.println(now());
        doRun("0,9","barfoothefoobarman", new String[]{"foo","bar"});
        doRun("","wordgoodgoodgoodbestword", new String[]{"word","good","best","word"});
        doRun("6,9,12","barfoofoobarthefoobarman", new String[]{"bar","foo","the"});

        doRun("8","wordgoodgoodgoodbestword", new String[]{"word","good","best","good"});
        doRun("0,2,4,6,8,10,1,3,5,7,9","aaaaaaaaaaaaaa", new String[]{"aa","aa"});

        doRun("1","ababaab", new String[]{"ab","ba","ba"});
        doRun("1,3","abaababbaba", new String[]{"ab","ba","ab","ba"});
        doRun("6","bcabbcaabbccacacba", new String[]{"c","b","a","c","a","a","a","b","c"});

        doRun("6,16,17,18,19,20","bcabbcaabbccacacbabccacaababcbb", new String[]{"c","b","a","c","a","a","a","b","c"});
        doRun("0,3,6,15","foobarfoobarthefoobarman", new String[]{"foo","bar"});

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(String expect, String s, String[] words) {
        List<Integer> res1 = new N30HSubstringwithConcatenationofAllWords()
                .findSubstring(s, words);
        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString(res1);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 11 ms, faster than 95.84% of Java online submissions for Substring with Concatenation of All Words.
    //Memory Usage: 49.9 MB, less than 72.34% of Java online submissions for Substring with Concatenation of All Words.
    //Slide window
    //Time : O(M + N); Space: O(2M).  M is the length of words
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int left = 0;
        int STEP = words[0].length();

        //Time:  O(M); Space: O(M)
        Map<String, Integer> wordCounter = new HashMap<>();
        for (String word: words)
            wordCounter.put(word, wordCounter.getOrDefault(word, 0) + 1);

        //Time : O(STEP * N); Space: O(M)
        while (left < STEP)
            helper_slidewindow(s, left++, STEP, words.length, wordCounter, res);

        return res;
    }

    //Space: O(M)
    private void helper_slidewindow(String s, int left, int step, int n,
                               Map<String, Integer> map, List<Integer> res) {
        //Space: O(M)
        Map<String, Integer> seenMap = new HashMap<>(); //word: count
        int remain = n;
        //Time: O(N / STEP)
        for (int right = left; right <= s.length() - step; right += step) {
            String word = s.substring(right, right + step);
            if (!map.containsKey(word)) {
                left = right + step;
                seenMap.clear();
                remain = n;
            }else {
                seenMap.put(word, seenMap.getOrDefault(word, 0) + 1);
                remain--;
                //move left pointer
                while (seenMap.get(word) > map.get(word) || remain == 0) {
                    if (seenMap.get(word) <= map.get(word) && remain == 0) res.add(left);
                    String leftWord = s.substring(left, left + step);
                    seenMap.put(leftWord, seenMap.get(leftWord) - 1);
                    remain++;
                    left += step;
                }
            }
        }//End for
    }

}
