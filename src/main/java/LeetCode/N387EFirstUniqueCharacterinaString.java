package LeetCode;


/**
 * Given a string s, find the first non-repeating character in it
 * and return its index. If it does not exist, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "leetcode"
 * Output: 0
 * Example 2:
 *
 * Input: s = "loveleetcode"
 * Output: 2
 * Example 3:
 *
 * Input: s = "aabb"
 * Output: -1
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s consists of only lowercase English letters.
 */

/**
 * E - [time: 10-
 *
 */
public class N387EFirstUniqueCharacterinaString {


    //Runtime: 28 ms, faster than 63.86% of Java online submissions for First Unique Character in a String.
    //Memory Usage: 49.1 MB, less than 56.14% of Java online submissions for First Unique Character in a String.
    ///Time: O(N) Worst case: O(N * N), best case: O(N); Space: O(1)
    public int firstUniqChar_3(String s) {
        for (int i = 0 ; i < s.length(); i++) {
            char c = s.charAt(i);
            if (s.indexOf(c) == s.lastIndexOf(c)) return i;
        }
        return  -1;
    }

    //Runtime: 1 ms, faster than 100.00% of Java online submissions for First Unique Character in a String.
    //Memory Usage: 42.8 MB, less than 92.10% of Java online submissions for First Unique Character in a String.
    //indexOf & lastIndexOf
    ///Time: O(N) Worst case: O(26 * N), best case: O(N); Space: O(1)
    public int firstUniqChar_2(String s) {
         int res = s.length() + 1;
         for (char c = 'a'; c <= 'z'; c++){
             int i = s.indexOf(c);
             if (i != -1 && i == s.lastIndexOf(c)) res = Math.min(res, i);
         }
         return  res == s.length() + 1 ?  -1 : res;
     }

    //Runtime: 8 ms, faster than 91.28% of Java online submissions for First Unique Character in a String.
    //Memory Usage: 50 MB, less than 51.45% of Java online submissions for First Unique Character in a String.
    //char[26] (better than hashmap）
    //Time: O(2N) ; Space: O(26)
    //N is a number of character in the string.
    public int firstUniqChar_1(String s) {

        char[] map = new char[26];
        for (char c: s.toCharArray())
            map[c - 'a']++;

        for (int i = 0 ; i <s.length(); i++)
            if (map[s.charAt(i) - 'a'] == 1) return i;

        return  -1;
    }

}
