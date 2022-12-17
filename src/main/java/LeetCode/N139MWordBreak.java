package LeetCode;

import Contest.N6183HSumofPrefixScoresofStrings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalTime.now;

/**
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 */
public class N139MWordBreak {

    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");
        doRun(true, "aaaaaaa", Arrays.asList("aaaa","aaa"));
        doRun(true, "cars", Arrays.asList("car","ca","rs"));
        doRun(true, "leetcode", Arrays.asList("leet","code"));
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(boolean expect, String s, List<String> wordDict) {
        boolean res = new N139MWordBreak().wordBreak(s, wordDict);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);

    }

    //3.tries
    //Runtime: 2m 98%; Memory: 42.3MB 85%
    //Time: O(N * M + L * (M + N)); Space: O(N * M + L + N)
    //let L be the length of S,
    // N be the length of the input wordDict, M be th length of word in wordDict;
    public boolean wordBreak(String s, List<String> wordDict) {

        //Time: O(N * M); Space: O(N * M)
        Trie root = new Trie();
        for(String word: wordDict) root.insert(word);

        //Space: O(L)
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        //Time: O(L * (M + N))
        for (int i = 0; i < s.length(); i++) {
            if (dp[i] == false) continue;

            List<Integer> res = root.find(s, i, s.length() - i);
            for (int wordLen : res)
                if (i + wordLen < dp.length) dp[i + wordLen] = true;
        }
        return dp[s.length()];
    }

    class Trie {
        Trie[] children = new Trie[26];
        boolean isEnd = false;
        public Trie() {
        }

        private void insert(String word) {
            Trie node = this;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] == null) node.children[c - 'a'] = new Trie();
                node = node.children[c - 'a'];
            }
            node.isEnd = true;
        }

        private List<Integer> find(String str, int idx, int maxLen){
            List<Integer> res = new ArrayList<>();
            Trie node = this;
            int len = 0;
            for (int i = idx; i < str.length(); i++) {
                char c = str.charAt(i);
                node = node.children[c - 'a'];
                if (node == null) break;
                len++;
                if (node.isEnd == true) res.add(len);
                if (len == maxLen) break;
            }
            return res;
        }
    }

    //2.DP bottom-up
    //Runtime: 2m 98%; Memory: 40.2MB 99%
    //Time: O(L * N * L * M); Space: O(L)
    //let L be the length of S,
    // N be the length of the input wordDict, M be th length of word in wordDict;
    public boolean wordBreak_2(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 0; i < s.length(); i++) {
            if (dp[i] == false) continue;
            for (String word: wordDict) {
                if (i == s.indexOf(word, i))
                    dp[i + word.length()] = true;
            }
        }
        return dp[s.length()];
    }

    //1.DP Top-down
    //Runtime: 2m 98.16%; Memory: 39.9MB 99.93%
    //Time: O(L * N * L * M); Space: O(L)
    //let L be the length of S,
    // N be the length of the input wordDict, M be th length of word in wordDict;
    public boolean wordBreak_1(String s, List<String> wordDict) {
        return helper(s, 0, wordDict, new Boolean[s.length()]);
    }

    private boolean helper(String s, int idx,  List<String> wordDict, Boolean[] memo) {
        if (idx == s.length()) return true;
        if (memo[idx] != null) return memo[idx];

        memo[idx] = false;
        for (String word: wordDict) {
            if (idx == s.indexOf(word, idx)) //Time: O(L * M)
                memo[idx] |= helper(s, idx + word.length(), wordDict, memo);
        }
        return memo[idx];
    }


}
