package LeetCode;


import java.util.*;

/**
 * Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.
 *
 * A subsequence of a string is a new string generated from the original string with some
 * characters (can be none) deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 *
 *
 * Example 1:
 * Input: s = "abcde", words = ["a","bb","acd","ace"]
 * Output: 3
 * Explanation: There are three strings in words that are a subsequence of s: "a", "acd", "ace".
 *
 *
 * Example 2:
 * Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 5 * 104
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 50
 * s and words[i] consist of only lowercase English letters.
 */

/**
 * M - [time: 20-]
 *  - 这题比较简单。 用 indexOf即可完成，但性能不太好。 （indexOf用的顺序搜索）
 *  - 优化：做位置映射 + binary search
 *  - 其他解法：同时匹配所有words(待写） 【Next Letter Pointers】[time:O（s.length+sum(word[i].length) || space:O(words.length)]
 */
public class N792MNumberofMatchingSubsequences {

    public static void main(String[] args) {

        String s = "abcde";
        String[] words = new String[]{"a","bb","acd","ace"};
        doRun(3,s, words);

        s = "z";
        words = new String[]{"a","bb","acd","ace"};
        doRun(0,s, words);

        s = "abcd";
        words = new String[]{"dx"};
        doRun(0,s, words);

        s = "abcd";
        words = new String[]{"d"};
        doRun(1,s, words);

        s = "kguhsugfxvwxakdcovjeczhqvbevkhlixsrhumxykbkihjdfxxxwragzcbhngbzgasxysxdtwntvbpdihtvkffacmxhbxxqniyqm";
        words = new String[]{"ykbkihjdfxxxwragzcbhngbzgasxysxdtwn","wxakdcovjeczhqvbevkhlixsrhumxykbkihj","diht","covjeczhqvbevkhlixsrhumxykbkihjdfxxxwragzcbhngbz","ovjeczhqvbevkhlixsrhumxykbkihjdfxxxwragzcbhng","qhzucvqxalfrtlrdjverseuldzymzbunhugekoyyghmkpkfqmd","eydmbsorvjnnifqxsyuypsrijzrosukrhtbneprpyyoawbvoki","uanfzlfmmtvhzzebrazyuslbapsfzwtlvqbhqxsfmqagwxetro","fffaawedchlcyqvzzxbzczbwyfjkllpsjhoozyresqemmawban","astrknwzefcmuswdxalooatmiduspjuofthtomoqilgdojwhon"};
        doRun(5,s, words);
    }

    private static int c = 1;
    private static void doRun(int expected, String s, String[] words){
        int res = new N792MNumberofMatchingSubsequences().numMatchingSubseq(s, words);
        System.out.println("[" + (expected==res) +"] "+(c++)+ ".result: "+ res + ".expected:"+expected);
    }


    //还有一种解法： 同时匹配所有words.

    //Runtime: 288 ms, faster than 20.03% of Java online submissions for Number of Matching Subsequences.
    //Memory Usage: 76.3 MB, less than 61.53% of Java online submissions for Number of Matching Subsequences.
    //pre-compute + binary search
    // time: O(N + M logS) N = s.length m = words.length S = 字母重复次数
    public int numMatchingSubseq(String s, String[] words) {
        List<Integer>[] mapping = new ArrayList[26];
        for(int i = 0; i<s.length();i++){
            int idx = s.charAt(i)-'a';
            List<Integer> l = mapping[idx];
            if (l == null) mapping[idx] = l = new ArrayList<>();
            l.add(i);
        }

        int res = 0;
        outer:
        for(String word: words) {
            if (word.length() > s.length()) continue;

            int lastIdx = -1;
            char[] chars = word.toCharArray();
            for (char c : chars) {
                lastIdx = bSearch(mapping[c - 'a'], lastIdx+1);
                if (lastIdx < 0) continue outer;
            }
            res++;
        }
        return res;
    }

    public int bSearch(List<Integer> list, int target){
        if (list == null || list.isEmpty()) return -1;
        if (list.size() == 1) return list.get(0) < target? -1 : list.get(0);

        int l = 0;
        int r = list.size()-1;

        while (l <= r){
            if (list.get(l) >= target) return list.get(l);

            int m = (r + l)/2;
            if (list.get(m) == target) return list.get(m);

            if (list.get(m) < target) l = m + 1;
            else  r = m;
        }

        return -1;
    }

    public int xSearch(List<Integer> list, int target){
        if (list == null || list.isEmpty()) return -1;
        if (list.size() == 1) return list.get(0) < target? -1 : list.get(0);
        for (Integer x: list) if (x>=target) return x;
        return -1;
    }

    //Runtime: 1478 ms, faster than 5.04% of Java online submissions for Number of Matching Subsequences.
    //Memory Usage: 43.1 MB, less than 98.08% of Java online submissions for Number of Matching Subsequences.
    //indexOf
    public int numMatchingSubseq1(String s, String[] words) {
        int res = 0;
        int sLen = s.length();
        for(String word: words){
            if (word.length() > sLen) continue;

            int lastIdx = -1;
            char[] chars = word.toCharArray();
            for(char c : chars ){
                lastIdx = s.indexOf(c, lastIdx+1);
                if (lastIdx < 0) break;
            }
            if (lastIdx >= 0 && lastIdx <= sLen)  res++;
        }
        return res;
    }


}
