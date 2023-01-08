package Contest;

import java.util.HashMap;
import java.util.Map;

import static java.time.LocalTime.now;

/**
 * You are given two 0-indexed strings word1 and word2.
 *
 * A move consists of choosing two indices i and j such that 0 <= i < word1.length and 0 <= j < word2.length and swapping word1[i] with word2[j].
 *
 * Return true if it is possible to get the number of distinct characters in word1 and word2 to be equal with exactly one move. Return false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: word1 = "ac", word2 = "b"
 * Output: false
 * Explanation: Any pair of swaps would yield two distinct characters in the first string, and one in the second string.
 * Example 2:
 *
 * Input: word1 = "abcc", word2 = "aab"
 * Output: true
 * Explanation: We swap index 2 of the first string with index 0 of the second string. The resulting strings are word1 = "abac" and word2 = "cab", which both have 3 distinct characters.
 * Example 3:
 *
 * Input: word1 = "abcde", word2 = "fghij"
 * Output: true
 * Explanation: Both resulting strings will have 5 distinct characters, regardless of which indices we swap.
 *
 *
 * Constraints:
 *
 * 1 <= word1.length, word2.length <= 105
 * word1 and word2 consist of only lowercase English letters.
 */
public class N2531MMakeNumberofDistinctCharactersEqual {


    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");
        doRun(false, "ikaflbdajfhkdcjmfhoddbnkdflejhofekknfhbfkdibegkejijefgocnakbdahhhbbilmflhmkk", "nlfommedklmgjcijamgddfkkgfkehimmbchflbneekeeggaiexaambkdkncghiedijkljblilefgmjiilbdkkgfmbobf");
        doRun(true, "az", "a");
        doRun(true, "aa", "bb");
        doRun(false, "aa", "ab");
        doRun(true, "abcc", "aab");
        doRun(true, "abcde", "fghij");
        doRun(false, "ab", "abcc");
        doRun(false, "a", "bb");
        doRun(true, "aab", "bccd");
        System.out.println(now());
        System.out.println("==================");
    }

    //93
    static private void doRun(boolean expect, String word1, String word2) {
        boolean res = new N2531MMakeNumberofDistinctCharactersEqual().isItPossible(word1, word2);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //2.switch
    //Runtime: 73ms 25%; Memory: 43.8MB 75%
    //Time: O(L1 + L2 + 26*26); Space: O(L1 + L2)
    public boolean isItPossible(String word1, String word2) {
        Map<Character, Integer> map1 = new HashMap<>();
        for (int i = 0; i < word1.length(); i++)
            map1.put(word1.charAt(i), map1.getOrDefault(word1.charAt(i), 0) + 1);

        Map<Character, Integer> map2 = new HashMap<>();
        for (int i = 0; i < word2.length(); i++)
            map2.put(word2.charAt(i), map2.getOrDefault(word2.charAt(i), 0) + 1);

        int diff = Math.abs(map1.keySet().size() - map2.keySet().size());
        if (diff > 2) return false;

        for (char c1 = 'a'; c1 <= 'z'; c1++) {
            if (!map1.containsKey(c1)) continue;
            helper_remove1chart(map1, c1);

            for (char c2 = 'a'; c2 <= 'z'; c2++) {
                if (!map2.containsKey(c2)) continue;

                //switch
                helper_remove1chart(map2, c2);
                map1.put(c2, map1.getOrDefault(c2, 0) + 1);
                map2.put(c1, map2.getOrDefault(c1, 0) + 1);

                //compare
                if (map1.keySet().size() - map2.keySet().size() == 0)
                    return true;

                //recover
                helper_remove1chart(map1, c2);
                helper_remove1chart(map2, c1);
                map2.put(c2, map2.getOrDefault(c2, 0) + 1);
            }

            map1.put(c1, map1.getOrDefault(c1, 0) + 1);
        }
        return false;
    }

    private void helper_remove1chart(Map<Character, Integer> map, char c){
        int count = map.getOrDefault(c, 0) - 1;
        if (count == 0) map.remove(c);
        else map.put(c, count);
    }

    //1.counter
    //Runtime: 69ms 25%; Memory: 43.2MB 100%
    //Time: O(L1 + L2); Space: O(L1 + L2)
    public boolean isItPossible_1(String word1, String word2) {
        Map<Character, Integer> map1 = new HashMap<>();
        for (int i = 0; i < word1.length(); i++)
            map1.put(word1.charAt(i), map1.getOrDefault(word1.charAt(i), 0) + 1);

        Map<Character, Integer> map2 = new HashMap<>();
        for (int i = 0; i < word2.length(); i++)
            map2.put(word2.charAt(i), map2.getOrDefault(word2.charAt(i), 0) + 1);

        int diff = Math.abs(map1.keySet().size() - map2.keySet().size());
        if (diff > 2) return false;

        //exchange
        if (map1.keySet().size() < map2.keySet().size()){
            Map<Character, Integer> tmp = map1;
            map1 = map2;
            map2 = tmp;
        }

        if (diff == 2) {
            return (helper_11(map1, map2) && helper_00(map2, map1));
        }

        if (diff == 1) {
            if (helper_01(map1, map2) && helper_00(map2, map1))
                return true;

            if (helper_11(map1, map2) && (helper_01(map2, map1) || helper_10(map2, map1)))
                return true;

            return false;
        }

        //diff == 0
        if (helper_00(map1, map2) && helper_00(map2, map1))
            return true;

        if (helper_01(map1, map2) && helper_01(map2, map1))
            return true;

        if (helper_10(map1, map2) && helper_10(map2, map1))
            return true;

        if (helper_11(map1, map2) && helper_11(map2, map1))
            return true;

        return false;
    }

    //map1 : unchanged; map2 :unchanged;
    private boolean helper_00(Map<Character, Integer> map1, Map<Character, Integer> map2){
        boolean c1 = false;
        for (char c : map1.keySet()) {
            if (map1.get(c) > 1 && map2.containsKey(c)) {
                c1 = true; break;
            }
        }
        return c1;
    }

    //map1 : unchanged; map2 : + 1;
    private boolean helper_01(Map<Character, Integer> map1, Map<Character, Integer> map2){
        boolean c1 = false;
        for (char c : map1.keySet()) {
            if (map1.get(c) > 1 && !map2.containsKey(c)) {
                c1 = true; break;
            }
        }
        return c1;
    }

    //map1 : -1; map2 :unchanged;
    private boolean helper_10(Map<Character, Integer> map1, Map<Character, Integer> map2){
        boolean c1 = false;
        for (char c : map1.keySet()) {
            if (map1.get(c) == 1 && map2.containsKey(c)) {
                c1 = true; break;
            }
        }
        return c1;
    }

    //map1 : - 1; map2 : + 1;
    private boolean helper_11(Map<Character, Integer> map1, Map<Character, Integer> map2){
        boolean c1 = false;
        for (char c : map1.keySet()) {
            if (map1.get(c) == 1 && !map2.containsKey(c)) {
                c1 = true; break;
            }
        }
        return c1;
    }
}
