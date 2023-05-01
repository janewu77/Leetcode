package LeetCode;

import ADraft.TriesDemo;

import java.util.*;

import static java.time.LocalTime.now;

/**
 * Given a string text and an array of strings words, return an array of all index pairs [i, j] so that the substring text[i...j] is in words.
 *
 * Return the pairs [i, j] in sorted order (i.e., sort them by their first coordinate, and in case of ties sort them by their second coordinate).
 *
 *
 *
 * Example 1:
 *
 * Input: text = "thestoryofleetcodeandme", words = ["story","fleet","leetcode"]
 * Output: [[3,7],[9,13],[10,17]]
 * Example 2:
 *
 * Input: text = "ababa", words = ["aba","ab"]
 * Output: [[0,1],[0,2],[2,3],[2,4]]
 * Explanation: Notice that matches can overlap, see "aba" is found in [0,2] and [2,4].
 *
 *
 * Constraints:
 *
 * 1 <= text.length <= 100
 * 1 <= words.length <= 20
 * 1 <= words[i].length <= 50
 * text and words[i] consist of lowercase English letters.
 * All the strings of words are unique.
 */
public class N1065EIndexPairsofaString {


    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");
        doRun(new int[][]{{3,7},{9,13},{10,17}}, "thestoryofleetcodeandme", new String[]{"story","fleet","leetcode"});
        System.out.println(now());
        System.out.println("==================");
    }

    //contest: 92  330 ; 97 331; 98 333
    //2547 1494 460 1908 1626 2565. 2564 1259
    static private void doRun(int[][] expect, String text, String[] words) {
        int[][] res = new N1065EIndexPairsofaString().indexPairs(text, words);
    }

    //3. HashSet
    //Time: 5ms 51%; Memory: 42.7MB 75%
    //Time: O(M + N * M * K) ; Space: O(N + M)
    //N: text.length M: words.length K: word.length
    public int[][] indexPairs(String text, String[] words) {
        List<int[]> list = new ArrayList<>();

        Set<String> set = new HashSet<>();
        int minLen = text.length();
        int maxLen = 0;
        for (String word : words) {
            set.add(word);
            minLen = Math.min(word.length(), minLen);
            maxLen = Math.max(word.length(), maxLen);
        }

        for(int i = 0; i < text.length(); i++) {
           for (int l = minLen; l <= maxLen; l++) {
               if ( i + l > text.length()) break;
               if (set.contains(text.substring(i, i + l)))
               list.add(new int[]{i, i + l - 1});
           }
        }
        return list.toArray(new int[list.size()][2]);
    }

    //2.tries
    //Time: 3ms 64%; Memory: 43.2MB 18%
    //Time: O(M * K + N * N) ; Space: O(N + M * K)
    //N: text.length M: words.length K: word.length
    public int[][] indexPairs_2(String text, String[] words) {
        //Space: O(N)
        List<int[]> list = new ArrayList<>();

        //Time: O(M * K); Space: O(M * K)
        Trie[] tries = new Trie[words.length];
        Trie root = new Trie();
        for (String word : words)
            root.insert(word);

        //Time: O(N * N)
        for(int i = 0; i < text.length(); i++) {
            Trie p = root;

            for(int j = i; j < text.length(); j++) {
                char c = text.charAt(j);
                if (p.children[c - 'a'] == null) break;
                p = p.children[c - 'a'];
                if (p.isEnd)
                    list.add(new int[]{i, j});
            }
        }
        return list.toArray(new int[list.size()][2]);
    }

    class Trie {
        Trie[] children = new Trie[26];
        private boolean isEnd = false;

        public Trie() {
        }

        public void insert(String word) {
            Trie p = this;
            for (int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if (p.children[c - 'a'] == null)
                    p.children[c - 'a'] = new Trie();
                p = p.children[c - 'a'];
            }
            p.isEnd = true;
        }
    }


    //1.indexOf
    //Time: 2ms 99%; Memory: 42.9MB 63%
    //Time: O(N * M * K + N + NlogN) ; Space: O(N + logN)
    //N: text.length M: words.length K: word.length
    public int[][] indexPairs_1(String text, String[] words) {
        List<int[]> list = new ArrayList<>();

        for (String word : words){
            int i = 0;
            while (i < text.length()) {
                int idx = text.indexOf(word, i);
                if (idx == -1)
                    break;
                list.add(new int[]{idx, idx + word.length() - 1});
                i = idx + 1;
            }
        }

        int[][] res = new int[list.size()][2];
        res = list.toArray(res);
        Arrays.sort(res, (a, b) -> a[0] == b[0] ? a[1] - b[1] :a[0] - b[0]);
        return res;
    }
}
