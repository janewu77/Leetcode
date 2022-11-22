package LeetCode;

import java.util.*;

import static java.time.LocalTime.now;

/**
 * You are given an array of strings words. Each element of words consists of two lowercase English letters.
 *
 * Create the longest possible palindrome by selecting some elements from words and concatenating them in any order. Each element can be selected at most once.
 *
 * Return the length of the longest palindrome that you can create. If it is impossible to create any palindrome, return 0.
 *
 * A palindrome is a string that reads the same forward and backward.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["lc","cl","gg"]
 * Output: 6
 * Explanation: One longest palindrome is "lc" + "gg" + "cl" = "lcggcl", of length 6.
 * Note that "clgglc" is another longest palindrome that can be created.
 * Example 2:
 *
 * Input: words = ["ab","ty","yt","lc","cl","ab"]
 * Output: 8
 * Explanation: One longest palindrome is "ty" + "lc" + "cl" + "yt" = "tylcclyt", of length 8.
 * Note that "lcyttycl" is another longest palindrome that can be created.
 * Example 3:
 *
 * Input: words = ["cc","ll","xx"]
 * Output: 2
 * Explanation: One longest palindrome is "cc", of length 2.
 * Note that "ll" is another longest palindrome that can be created, and so is "xx".
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 105
 * words[i].length == 2
 * words[i] consists of lowercase English letters.
 */

/**
 * M - [time: 25-
 */
public class N2131MLongestPalindromebyConcatenatingTwoLetterWords {


    //[]
    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;


        doRun(14, new String[]{"em","pe","mp","ee","pp","me","ep","em","em","me"});
        doRun(22, new String[]{"dd","aa","bb","dd","aa","dd","bb","dd","aa","cc","bb","cc","dd","cc"});
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, String[] words) {
        int res = new N2131MLongestPalindromebyConcatenatingTwoLetterWords().longestPalindrome(words);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //3. 2D- array
    //Runtime: 19 ms, faster than 94.31% of Java online submissions for Longest Palindrome by Concatenating Two Letter Words.
    //Memory Usage: 97.1 MB, less than 72.53% of Java online submissions for Longest Palindrome by Concatenating Two Letter Words.
    //Time: O(N + 26); Space: O(26*26);
    //Time: O(N); Space: O(1)
    public int longestPalindrome(String[] words) {
        int[][] counter = new int[26][26];
        int res = 0;
        for (String word : words) {
            int c0 = word.charAt(0) - 'a';
            int c1 = word.charAt(1) - 'a';
            if (counter[c1][c0] > 0){
                res += 4;
                counter[c1][c0]--;
            }else
                counter[c0][c1]++;
        }

        for (int i = 0; i < 26; i++) {
            if (counter[i][i] > 0) {
                res += 2;
                break;
            }
        }
        return res;
    }

    //2. 2D-array
    //Runtime: 7 ms, faster than 99.87% of Java online submissions for Longest Palindrome by Concatenating Two Letter Words.
    //Memory Usage: 57.7 MB, less than 95.35% of Java online submissions for Longest Palindrome by Concatenating Two Letter Words.
    //Time: O(N + 26*26/2); Space: O(26*26);
    //Time: O(N); Space: O(1)
    public int longestPalindrome_2(String[] words) {
        int[][] counter = new int[26][26];
        for (String word : words)
            counter[word.charAt(0) - 'a'][word.charAt(1) - 'a']++;

        int res = 0, maxCount = 0;
        for (int i = 0; i < 26; i++) {
            for (int j = i; j < 26; j++) {
                if (counter[i][j] <= 0) continue;
                if (i == j){
                    res += (counter[i][j] / 2) * 4;
                    maxCount = Math.max(maxCount, counter[i][j] % 2 * 2);
                }else {
                    res += Math.min(counter[i][j], counter[j][i]) * 4;
                }
            }
        }
        return res + maxCount;
    }

    //1.hashmap
    //Runtime: 110 ms, faster than 74.79% of Java online submissions for Longest Palindrome by Concatenating Two Letter Words.
    //Memory Usage: 58 MB, less than 92.37% of Java online submissions for Longest Palindrome by Concatenating Two Letter Words.
    //Time: O(N); Space: O(N)
    public int longestPalindrome_1(String[] words) {
        int res = 0;

        Map<String, Integer> map = new HashMap<>();
        for (String word : words)
            map.put(word, map.getOrDefault(word, 0) + 1);

        int maxCount = 0;
        for (String word : words) {
            if (!map.containsKey(word)) continue;

            if (word.charAt(0) == word.charAt(1)) {

                int count = map.get(word);
                res += (count / 2) * 4;
                count = count % 2;
                maxCount = Math.max(maxCount, count * 2);
                if (count <= 0) map.remove(word);
                else map.put(word, count);

            }else{

                String target = new StringBuilder().append(word.charAt(1)).append(word.charAt(0)).toString();
                if (map.containsKey(target)) {
                    int count0 = map.get(word);
                    int count1 = map.get(target);
                    int min = Math.min(count0, count1);
                    res += min * 4;

                    if (count0 <= min) map.remove(word);
                    else map.put(word, count0 - min);
                    if (count1 <= min) map.remove(target);
                    else map.put(target, count1 - min);
                }
            }
        }
        return res + maxCount;
    }

}
