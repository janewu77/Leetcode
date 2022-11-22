package Contest;

import ADraft.TriesDemo;

import java.util.*;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

/**
 * You are given an array words of size n consisting of non-empty strings.
 *
 * We define the score of a string word as the number of strings words[i] such that word is a prefix of words[i].
 *
 * For example, if words = ["a", "ab", "abc", "cab"], then the score of "ab" is 2, since "ab" is a prefix of both
 * "ab" and "abc".
 * Return an array answer of size n where answer[i] is the sum of scores of every non-empty prefix of words[i].
 *
 * Note that a string is considered as a prefix of itself.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["abc","ab","bc","b"]
 * Output: [5,4,3,2]
 * Explanation: The answer for each string is the following:
 * - "abc" has 3 prefixes: "a", "ab", and "abc".
 * - There are 2 strings with the prefix "a", 2 strings with the prefix "ab", and 1 string with the prefix "abc".
 * The total is answer[0] = 2 + 2 + 1 = 5.
 * - "ab" has 2 prefixes: "a" and "ab".
 * - There are 2 strings with the prefix "a", and 2 strings with the prefix "ab".
 * The total is answer[1] = 2 + 2 = 4.
 * - "bc" has 2 prefixes: "b" and "bc".
 * - There are 2 strings with the prefix "b", and 1 string with the prefix "bc".
 * The total is answer[2] = 2 + 1 = 3.
 * - "b" has 1 prefix: "b".
 * - There are 2 strings with the prefix "b".
 * The total is answer[3] = 2.
 * Example 2:
 *
 * Input: words = ["abcd"]
 * Output: [4]
 * Explanation:
 * "abcd" has 4 prefixes: "a", "ab", "abc", and "abcd".
 * Each prefix has a score of one, so the total is answer[0] = 1 + 1 + 1 + 1 = 4.
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 1000
 * words[i] consists of lowercase English letters.
 */

/**
 * H - [time:120+
 */
//2416. Sum of Prefix Scores of Strings
public class N6183HSumofPrefixScoresofStrings {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;


        doRun("5,4,3,2",  new String[]{"abc","ab","bc","b"});
        doRun("4",  new String[]{"abcd"});

        doRun("24,10,34,26,32,13,34,24,20",  new String[]{"qtcqcmwcin","vkjotbrbzn","eoorlyfche","eoorlyhn","eoorlyfcxk","qfnmjilcom","eoorlyfche","qtcqcmwcnl","qtcqcrpjr"});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(String expect, String[] words) {
        int[] res1 = new N6183HSumofPrefixScoresofStrings()
                .sumPrefixScores(words);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 158 ms, faster than 100.00% of Java online submissions for Sum of Prefix Scores of Strings.
    //Memory Usage: 282.4 MB, less than 20.00% of Java online submissions for Sum of Prefix Scores of Strings.
    //Tries
    //Time: O(N*K + N*K); Space: O(N*K*26 + N)
    //Time: O(N*K); Space: O(N*K)
    //N is the number of words; k is the number of characters in the longest word.
    public int[] sumPrefixScores(String[] words) {

        //Time: O(N*K) Space: O(N*K*26)
        Trie root = new Trie();
        for (String word : words) insert(root, word);

        //Time: O(N*K) Space: O(N)
        int[] res = new int[words.length];
        int idx = 0;
        for (String word : words)
            res[idx++] = countPrefix(root, word);

        return res;
    }

    //Time :O(K)
    private void insert(Trie trie, String word) {
        for (char c : word.toCharArray()) {
            if (trie.children[c - 'a'] == null) trie.children[c - 'a'] = new Trie();
            trie = trie.children[c - 'a'];
            trie.count++;
        }
    }

    //Time :O(K)
    private int countPrefix(Trie trie, String word) {
        int sum = 0;
        for (char c : word.toCharArray()) {
            trie = trie.children[c - 'a'];
            sum += trie.count;
        }
        return sum;
    }

    class Trie {
        Trie[] children = new Trie[26];
        int count = 0;
        public Trie() {
        }
    }


    //TLE
    public int[] sumPrefixScores_1(String[] words) {

        Map<String, List<Integer>> idxMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            List<Integer> list = idxMap.getOrDefault(words[i], new ArrayList<>());
            idxMap.put(words[i], list);
            list.add(i);
        }

        Arrays.sort(words, Comparator.comparingInt(String::length));

        Map<String, Integer> prefixMap = new HashMap<>();
        Map<String, Set<String>> wordMap = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            Set<String> set = new HashSet<>();
            for (int k = word.length(); k > 0 ; k--) {
                String prefix = word.substring(0, k);
                if (wordMap.containsKey(prefix)) {
                    set.addAll(wordMap.get(prefix));
                    break;
                } else {
                    set.add(prefix);
                }
            }

            for (String prefix : set)
                prefixMap.put(prefix, prefixMap.getOrDefault(prefix, 0) + 1);
            wordMap.put(word, set);
        }

        int[] res = new int[words.length];
        for (int i = 0; i < words.length; i++){

            if (i != 0 && words[i].equals(words[i-1])) continue;

            int sum = 0;
            Set<String> set = wordMap.get(words[i]);
            for (String prefix : set) sum += prefixMap.get(prefix);

            //res
            List<Integer> list = idxMap.get(words[i]);
            for (int idx : list) res[idx] = sum;
        }
        return res;
    }
}
