package LeetCode;


import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.time.LocalTime.now;

/**
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring
 * of s such that every character in t (including duplicates) is included in the window.
 * If there is no such substring, return the empty string "".
 *
 * The testcases will be generated such that the answer is unique.
 *
 * A substring is a contiguous sequence of characters within the string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 * Example 2:
 *
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 * Example 3:
 *
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 *
 *
 * Constraints:
 *
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 105
 * s and t consist of uppercase and lowercase English letters.
 *
 *
 * Follow up: Could you find an algorithm that runs in O(m + n) time?
 */

/**
 *
 * M - [time: 45-
 *
 * - slide window
 */
public class N76HMinimumWindowSubstring {

    public static void main(String[] args) {

        System.out.println(now());
        int[][] data;
//        System.out.println('a'-'A');
//        System.out.println('z'-'A');
//        System.out.println('A'-'A');
//        System.out.println('Z'-'A');


        doRun("abbbbbcdd", "aaaaaabbbbbcddx", "abcdd");
        doRun("BANC", "ADOBECODEBANC", "ABC");
        doRun("a", "a", "a");
        doRun("", "a", "aa");

        doRun("", "a", "b");
        doRun("bfc", "adfcdbbdsdfbfc", "bfc");

        System.out.println(now());
        System.out.println("==================");
    }
    static private void doRun(String expect, String s, String t) {
        String res = new N76HMinimumWindowSubstring().minWindow(s,t);
//        String res = comm.toString(res1);
//        String res = Arrays.stream(res1).mapToObj(i-> String.valueOf(i)).collect(Collectors.joining(","));
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    //2.Slide window
    //Runtime: 6 ms, faster than 89.74% of Java online submissions for Minimum Window Substring.
    //Memory Usage: 46.6 MB, less than 47.72% of Java online submissions for Minimum Window Substring.
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";
        int[] counterT = new int[58];
        //Time: O(T)
        for (Character c: t.toCharArray()) counterT[c-'A']++;

        //Time: O(S)
        List<Pair<Character, Integer>> list  = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (counterT[s.charAt(i) -'A'] > 0)
                list.add(new Pair<>(s.charAt(i), i));
        }

        int[] res = new int[]{0, s.length() - 1, s.length() + 1};
        int[] counter = new int[58];
        int left = 0, right = 0, count = 0;
        //Time: O(S)
        while (right < list.size()) {
            int currChar = list.get(right++).getKey() - 'A';

            if (counter[currChar] < counterT[currChar]) count++;
            counter[currChar]++;

            while (count == t.length()){
                int i = list.get(left).getValue();
                int j = list.get(right - 1).getValue();
                if (j - i < res[2]) res = new int[]{i, j, j - i};

                int firstChar = list.get(left++).getKey() - 'A';
                counter[firstChar]--;
                if (counter[firstChar] < counterT[firstChar]) count--;
            }
        }
        return res[2] == s.length() + 1 ?  "" : s.substring(res[0], res[1] + 1);
    }


    //1.slide window
    //Runtime: 3 ms, faster than 98.41% of Java online submissions for Minimum Window Substring.
    //Memory Usage: 42.1 MB, less than 99.18% of Java online submissions for Minimum Window Substring.
    //Slide window
    //Time: O(M + 2N) ; Space: O(1) : { O(123+123+2) }
    //N: s.length(),  M: t.length()
    public String minWindow_1(String s, String t) {
        if (s.length() < t.length()) return "";

        //Time: O(M); Space: O(123)
        int[] mapT = new int['z'+1];
        for (Character c: t.toCharArray()) mapT[c]++;

        int count = 0;
        int[] map = new int['z'+1]; //Space: O(123)
        int[] pos = new int[2];
        int left = 0, right = 0;

        //Time: O(2N)
        while (right < s.length()) {
            int idx = s.charAt(right++);
            if (mapT[idx] != 0) {
                if (map[idx] < mapT[idx]) count++;
                map[idx]++;

                //move left pointer
                while (count == t.length()) {
                    if (right - left < pos[1] - pos[0] || pos[1] == 0) {
                        pos[0] = left;  pos[1] = right;
                    }

                    int idxLeft = s.charAt(left++);
                    if (mapT[idxLeft] != 0) {
                        map[idxLeft]--;
                        if (map[idxLeft] < mapT[idxLeft]) count--;
                    }
                }//while (count == t.length())
            }
        }//End while (right < s.length())
        return s.substring(pos[0],pos[1]);
    }

}
