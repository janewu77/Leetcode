package LeetCode;


import java.util.*;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

/**
 * Given an array of strings words (without duplicates), return all the concatenated words in the given list of words.
 *
 * A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 * Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 * "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
 * "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 * Example 2:
 *
 * Input: words = ["cat","dog","catdog"]
 * Output: ["catdog"]
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 104
 * 1 <= words[i].length <= 30
 * words[i] consists of only lowercase English letters.
 * All the strings of words are unique.
 * 1 <= sum(words[i].length) <= 105
 */
public class N472HConcatenatedWords {


    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");
        String[] expect; String[] words;

//        expect = new String[]{"ife"};
//        words = new String[]{"f","e","if","ife", "ab"};
//        doRun(expect, words);

        expect = new String[]{"ab"};
        words = new String[]{"a","b","abc","ab"};
        doRun(expect, words);

        expect = new String[]{"catsdogcats","dogcatsdog","ratcatdogcat"};
        words = new String[]{"catsdogcats","cat","cats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
        doRun(expect, words);

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(String[] expect1, String[] words) {
        String expect = Arrays.stream(expect1).sorted().collect(Collectors.joining(","));
        List<String> res1 = new N472HConcatenatedWords().findAllConcatenatedWordsInADict(words);
        String res = res1.stream().sorted().collect(Collectors.joining(","));
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    //5.DP
    //Runtime: 59ms 78%; Memory: 42MB 91%
    //Time: O(N * L * L * L); Space: O(N * L)
    //let N be the number of string in the input array,
    //let L be the length of the longest string in the input array.
    public List<String> findAllConcatenatedWordsInADict_5(String[] words) {
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>(Arrays.asList(words));

        for (String word : words) {
            boolean[] dp = new boolean[word.length() + 1];
            //Time: O(L * L * L)
            for (int i = 0; i < word.length() && !dp[word.length() - 1]; i++) {
                if (i != 0 && !dp[i - 1]) continue;

                for (int j = i; j < word.length() - (i == 0 ? 1 : 0); j++)
                        dp[j] |= set.contains(word.substring(i, j + 1));    //Time: O(L)

                if (dp[word.length() - 1])
                    res.add(word);
            }
        }
        return res;
    }



    //4.tries
    //Runtime: 50ms 90%; Memory: 59.6MB 40%
    //Time: O(N * L * L * L); Space: O(N * L)
    //let N be the number of string in the input array,
    //let L be the length of the longest string in the input array.
    public List<String> findAllConcatenatedWordsInADict_4(String[] words) {
        List<String> res = new ArrayList<>();

        //Time: O(N * L); Space: O(N * L)
        Trie root = new Trie();
        for (int i = 0; i < words.length; i++)
            root.insert(words[i], i);

        for (int i = 0; i < words.length; i++) {
            if (helper(root, i, words[i], 0))
                res.add(words[i]);
        }
        return res;
    }

    //Time: O(L + L * L); Space: O(L)
    private boolean helper(Trie root, int idx, String target, int begin){
        if (begin == target.length()) return true;

        List<Integer> list = root.find(idx, target, begin);
        for (int end : list) {
            if (helper(root, idx, target, end + 1))
                return true;
        }
        return false;
    }


    class Trie {
        Trie[] children = new Trie[26];
        boolean isEnd = false;
        int idx = -1;
        public Trie() {
        }

        private void insert(String word, int i) {
            Trie node = this;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] == null)
                    node.children[c - 'a'] = new Trie();
                node = node.children[c - 'a'];
            }
            node.isEnd = true;
            node.idx = i;
        }

        private List<Integer> find(int idx, String str, int begin){
            List<Integer> res = new ArrayList<>();
            Trie node = this;
            for (int i = begin; i < str.length(); i++) {
                char c = str.charAt(i);
                node = node.children[c - 'a'];
                if (node == null) break;
                if (node.isEnd == true && node.idx != idx) //skip self
                    res.add(i);
            }
            return res;
        }
    }


    //3.sort + binary search
    //Runtime: 592 ms 5%; Memory: 44.5MB 99%
    public List<String> findAllConcatenatedWordsInADict_3(String[] words) {
        Arrays.sort(words);

        List<String> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++){
            if (helper_3(words, i, words[i], 0)) {
                res.add(words[i]);
            }
        }
        return res;
    }

    private boolean helper_3(String[] words, int idx, String target, int x) {
        if (x >= target.length()) return true;

        int begin = Arrays.binarySearch(words, String.valueOf(target.charAt(x)));
        if (begin < 0) begin = -begin - 1;

        int end = Arrays.binarySearch(words, String.valueOf((char)(target.charAt(x)+1)));
        if (end < 0) end = -end - 1;

        for (int i = begin; i < end; i++) {
            if (i != idx && target.indexOf(words[i], x) == x
                    && helper_3(words, idx, target, x + words[i].length() ))
                return true;
        }
        return false;
    }


    //2.Sort
    //TLE
    //Time: O(N * N * N); Space: O(log(N) + N + L)
    //Time: O(N * N * N); Space: O(N)
    public List<String> findAllConcatenatedWordsInADict_2(String[] words) {
        //Time: O(N * log(N)); Space: O(log(N))
        Arrays.sort(words, Comparator.comparingInt(s -> s.length()));

        List<int[]>[] components = new List[words.length]; //parts
        for (int i = 0; i < words.length; i++)
            components[i] = new ArrayList<>();

        //Time: O(N * N * N)
        for (int i = 0; i < words.length; i++){
            String word = words[i];
            for (int j = 0; j < i; j++) {
                int idx = word.indexOf(words[j]);
                while (idx >= 0) {
                    components[i].add(new int[]{idx, words[j].length()});
                    idx = word.indexOf(words[j], idx + words[j].length());
                }
            }
        }

        List<String> res = new ArrayList<>();

        //Time: O( N * L * (logL)); Space: O(L)
        for (int i = 0; i < components.length; i++){
            if (components[i].size() >= 2 && helper2(components[i], words[i].length()))
                res.add(words[i]);
        }

        return res;
    }

    //Time: O(L * (log(L))); Space: O(log(L) + L)
    //let L be the size of components.
    private boolean helper2(List<int[]> components, int target){
        Collections.sort(components, (a, b) -> a[0] - b[0] == 0 ? a[1] - b[1] : a[0] - b[0]);

        Deque<Integer> queue = new ArrayDeque<>();
        int idx = 0;

        queue.add(0);
        while (!queue.isEmpty()) {
            int x = queue.pollFirst();

            while (idx < components.size() && components.get(idx)[0] < x)
                idx++;

            while (idx < components.size() && components.get(idx)[0] == x) {
                int next = components.get(idx)[0] + components.get(idx)[1];
                if (next == target) return true;
                queue.addLast(next);
                idx++;
            }
        }
        return false;
    }



    //1.brute force
    //TLE
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();

        for (int i = 0; i < words.length; i++){
            String word = words[i];
            words[i] = "0";
            if (helper(words, i, word, 0)) res.add(word);
            words[i] = word;
        }
        return res;
    }

    private boolean helper(String[] words, int idx, String target, int from){
        if (from >= target.length()) return true;
        for (int j = 0; j < words.length; j++) {
            if (target.indexOf(words[j], from) == from
                    && helper(words, idx, target, from + words[j].length()))
                return true;
        }
        return false;
    }
}
