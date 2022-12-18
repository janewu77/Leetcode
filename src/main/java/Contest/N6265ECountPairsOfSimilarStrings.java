package Contest;

import java.io.IOException;
import java.util.*;

import static java.time.LocalTime.now;

/**
 * You are given a 0-indexed string array words.
 *
 * Two strings are similar if they consist of the same characters.
 *
 * For example, "abca" and "cba" are similar since both consist of characters 'a', 'b', and 'c'.
 * However, "abacba" and "bcfd" are not similar since they do not consist of the same characters.
 * Return the number of pairs (i, j) such that 0 <= i < j <= word.length - 1 and the two strings words[i] and words[j] are similar.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["aba","aabb","abcd","bac","aabc"]
 * Output: 2
 * Explanation: There are 2 pairs that satisfy the conditions:
 * - i = 0 and j = 1 : both words[0] and words[1] only consist of characters 'a' and 'b'.
 * - i = 3 and j = 4 : both words[3] and words[4] only consist of characters 'a', 'b', and 'c'.
 * Example 2:
 *
 * Input: words = ["aabb","ab","ba"]
 * Output: 3
 * Explanation: There are 3 pairs that satisfy the conditions:
 * - i = 0 and j = 1 : both words[0] and words[1] only consist of characters 'a' and 'b'.
 * - i = 0 and j = 2 : both words[0] and words[2] only consist of characters 'a' and 'b'.
 * - i = 1 and j = 2 : both words[1] and words[2] only consist of characters 'a' and 'b'.
 * Example 3:
 *
 * Input: words = ["nba","cba","dba"]
 * Output: 0
 * Explanation: Since there does not exist any pair that satisfies the conditions, we return 0.
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 100
 * words[i] consist of only lowercase English letters.
 */


//2506. Count Pairs Of Similar Strings
public class N6265ECountPairsOfSimilarStrings {

    static public void main(String... args) throws IOException{
        System.out.println(now());
        System.out.println("==================");

        doRun(2, new String[]{"aba","aabb","abcd","bac","aabc"});
        doRun(3, new String[]{"aabb","ab","ba"});
        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, String[] words) {
        int res = new N6265ECountPairsOfSimilarStrings().similarPairs(words);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //2.Bitmask
    //Runtime: 6ms, 75%; Memory: 45.4MB, 25%
    //Time: O(N * L); Space: O(N)
    //let N be the size of input array; L be the max length of word in words.
    public int similarPairs(String[] words) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            int key = 0;
            for(char c : words[i].toCharArray())
                key = (1 << (c - 'a')) | key;

            int c = map.getOrDefault(key, 0);
            res += c;
            map.put(key , c + 1);
        }
        return res;
    }


    //1.Set
    //Runtime: 36ms, 50%; Memory: 53.4MB, 12%
    //Time: O(N * (L * Log(L)) + N * N); Space: O(N * L)
    //let N be the size of input array; L be the max length of word in words.
    public int similarPairs_1(String[] words) {
        String[] keys = new String[words.length];

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            Set<Character> set = new HashSet<>();
            for(char c : word.toCharArray()) set.add(c);
            List<Character> list = new ArrayList<>(set);
            Collections.sort(list);
            StringBuilder keyBuilder = new StringBuilder();
            for (char c : list) keyBuilder.append(c);
            keys[i] = keyBuilder.toString();
        }

        int res = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (keys[i].equals(keys[j])) res++;
            }
        }
        return res;
    }

}


