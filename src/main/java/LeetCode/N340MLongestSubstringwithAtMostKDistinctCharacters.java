package LeetCode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a string s and an integer k, return the length of the longest substring
 * of s that contains at most k distinct characters.
 *
 * Example 1:
 * Input: s = "eceba", k = 2
 * Output: 3
 * Explanation: The substring is "ece" with length 3.
 *
 *
 * Example 2:
 * Input: s = "aa", k = 1
 * Output: 2
 * Explanation: The substring is "aa" with length 2.
 *
 *
 * Constraints:
 * 1 <= s.length <= 5 * 104
 * 0 <= k <= 50
 *
 *
 */

/**
 * M - [time: 120+
 *  - 学到新结构 LinkedHashMap
 *  - 是159问题的扩展版
 */
public class N340MLongestSubstringwithAtMostKDistinctCharacters {

    public static void main(String[] args) {
        String s = "";
        int k = 0;

        s = "eceeeeba";
        k = 2;
        doRun(6, s, k);

        s = "aa";
        k = 1;
        doRun(2, s, k);

        s = "aa";
        k = 0;
        doRun(0, s, k);

        s = "abcdabcccabcdabcd";
        k = 3;
        doRun(8, s, k);

    }

    private static void doRun(int expected, String s, int k){
        int res = new N340MLongestSubstringwithAtMostKDistinctCharacters().lengthOfLongestSubstringKDistinct(s, k);
        //String strRes = Arrays.stream(res).mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(""));
        //System.out.println("=======");
        System.out.println("["+(expected == res)+"].[expected:"+ expected+"] res:"+res);
    }

    //Runtime: 14 ms, faster than 60.37% of Java online submissions for Longest Substring with At Most K Distinct Characters.
    //Memory Usage: 42.7 MB, less than 84.78% of Java online submissions for Longest Substring with At Most K Distinct Characters.
    //LinkedHashMap(Ordered dictionary)
    //Time：O（N），Space: O(k)
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0) return 0;
        if (s.length() <= k) return s.length();

        int res = 0;
        int left = 0, right = 0;

        Map<Character, Integer> map = new LinkedHashMap<>(); //Ordered dictionary
        while (right < s.length()){
            char c = s.charAt(right);
            //LinkedHashMap
            map.remove(c); map.put(c, right++);

            if (map.size() > k) {
                Map.Entry<Character, Integer> leftmost = map.entrySet().iterator().next();
                map.remove(leftmost.getKey());
                left = leftmost.getValue() + 1;
            }else
                res = Math.max(res, right - left);
        }

        return res;
    }


    //copy from  N159 lengthOfLongestSubstringTwoDistinct
    //Runtime: 14 ms, faster than 60.37% of Java online submissions for Longest Substring with At Most K Distinct Characters.
    //Memory Usage: 44.9 MB, less than 58.90% of Java online submissions for Longest Substring with At Most K Distinct Characters.
    //two pointers & window
    //Time：O（2N），Space: O(k)
    public int lengthOfLongestSubstringKDistinct_2(String s, int k) {
        if (s.length() <= k) return s.length();

        int res = 0;
        int left = 0, right = 0;

        Map<Character, Integer> map = new HashMap<>();
        while (right < s.length()){
            char c = s.charAt(right++);
            map.put(c, map.getOrDefault(c, 0) + 1);
            //map.compute(c, (k, v) -> v == null ? 1 : v + 1);

            if (map.size() <= k) {
                res = Math.max(res, right - left);
                continue;
            }

            while (map.size() > k) {
                char delChar = s.charAt(left++);
                map.put(delChar, map.get(delChar) - 1);
                if (map.get(delChar) <= 0) map.remove(delChar);
            }
        }

        return res;
    }


    ////////////////////////////////////////////////////////////////////////////
    //Time Limit Exceeded
    public int lengthOfLongestSubstringKDistinct_1(String s, int k) {
        int left = 0;
        int res = 0;

        while (left < s.length()){
            Set<Character> set = new HashSet<>();
            int count = 0;
            int p = left;
            while (p < s.length()) {
                set.add(s.charAt(p++));
                if (set.size() > k) break;
                count++;
            }
            left++;
            res = Math.max(res, count);
        }
        return res;
    }
}
