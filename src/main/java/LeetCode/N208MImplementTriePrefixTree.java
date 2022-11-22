package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to
 * efficiently store and retrieve keys in a dataset of strings. There are various
 * applications of this data structure, such as autocomplete and spellchecker.
 *
 * Implement the Trie class:
 *
 * Trie() Initializes the trie object.
 * void insert(String word) Inserts the string word into the trie.
 * boolean search(String word) Returns true if the string word is in the trie
 * (i.e., was inserted before), and false otherwise.
 * boolean startsWith(String prefix) Returns true if there is a previously inserted
 * string word that has the prefix prefix, and false otherwise.
 *
 *
 * Example 1:
 * Input
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * Output
 * [null, null, true, false, true, null, true]
 *
 * Explanation
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // return True
 * trie.search("app");     // return False
 * trie.startsWith("app"); // return True
 * trie.insert("app");
 * trie.search("app");     // return True
 *
 *
 * Constraints:
 *
 * 1 <= word.length, prefix.length <= 2000
 * word and prefix consist only of lowercase English letters.
 * At most 3 * 104 calls in total will be made to insert, search, and startsWith.
 *
 */

/***
 * M - [time: 30-]
 *
 */
public class N208MImplementTriePrefixTree {


    //Runtime: 45 ms, faster than 88.13% of Java online submissions for Implement Trie (Prefix Tree).
    //Memory Usage: 67.5 MB, less than 57.82% of Java online submissions for Implement Trie (Prefix Tree).
    class Trie {

        Trie[] children = new Trie[26];
        private boolean isEnd = false;

        public Trie() {
        }

        public void insert(String word) {
            Trie p = this;
            for (int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if (p.children[c - 'a'] == null) p.children[c - 'a'] = new Trie();
                p = p.children[c - 'a'];
            }
            p.isEnd = true;
        }

        public boolean search(String word) {
            Trie p = this;
            for (int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if (p.children[c - 'a'] == null) return false;
                p = p.children[c - 'a'];
            }
            return p.isEnd;
        }

        public boolean startsWith(String prefix) {
            Trie p = this;
            for (int i = 0; i < prefix.length(); i++){
                char c = prefix.charAt(i);
                if (p.children[c - 'a'] == null) return false;
                p = p.children[c - 'a'];
            }
            return true;
        }
    }

    //Runtime: 83 ms, faster than 26.40% of Java online submissions for Implement Trie (Prefix Tree).
    //Memory Usage: 69.8 MB, less than 21.21% of Java online submissions for Implement Trie (Prefix Tree).
    //HashMap
//    class Trie {
//
//        final char END = '#';
//
//        private char c='$';
//        private Map<Character, Trie> children = new HashMap<>();
//
//        public Trie(char c1) {
//            c = c1;
//        }
//
//        public Trie() {
//        }
//
//        public void insert(String word) {
//            //if (word == null || word.isEmpty()) return;
//            Trie p = this;
//            for (char c : word.toCharArray()){
//                if (!p.children.containsKey(c)) p.children.put(c, new Trie(c));
//                p = p.children.get(c);
//            }
//            p.children.put(END, null);
//        }
//
//        public boolean search(String word) {
//            Trie p = this;
//            for (char c : word.toCharArray()){
//                if (!p.children.containsKey(c)) return false;
//                p = p.children.get(c);
//            }
//            return p.children.containsKey(END);
//        }
//
//        public boolean startsWith(String prefix) {
//            Trie p = this;
//            for (char c : prefix.toCharArray()){
//                if (!p.children.containsKey(c)) return false;
//                p = p.children.get(c);
//            }
//            return true;
//        }
//
//    }
}
