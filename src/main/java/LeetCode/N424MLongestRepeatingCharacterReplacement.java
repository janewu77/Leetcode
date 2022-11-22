package LeetCode;

import javafx.util.Pair;

import java.util.*;

/**
 *
 * You are given a string s and an integer k. You can choose any character of the string
 * and change it to any other uppercase English character. You can perform this operation at most k times.
 *
 * Return the length of the longest substring containing the same letter you can get
 * after performing the above operations.
 *
 *
 *
 * Example 1:
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation: Replace the two 'A's with two 'B's or vice versa.
 *
 *
 * Example 2:
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s consists of only uppercase English letters.
 * 0 <= k <= s.length
 *
 *
 */

/**
 * M - [time: 90- 代码一次过）
 *  - 更优秀的算法：计数window。(网上看来的）
 *
 */
public class N424MLongestRepeatingCharacterReplacement {


    public static void main(String[] args) {
        String str;
        int k;

        str = "AAA";
        k = 0;
        doRun(3, str, k);

        str = "ABAB";
        k = 2;
        doRun(4, str, k);

        str = "AABABBA";
        k = 1;
        doRun(4, str, k);
    }

    static private void doRun(int expect, String s, int k) {
        int res = new N424MLongestRepeatingCharacterReplacement().characterReplacement(s,k);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //excellent
    //leetcode 看来的 @singhsaket_
    //Runtime: 2 ms, faster than 100.00% of Java online submissions for Max Consecutive Ones III.
    //Memory Usage: 42.9 MB, less than 98.52% of Java online submissions for Max Consecutive Ones III.
    //count 计数window
    //Time: O(N * 26); Space: O(26)
    public int characterReplacement(String s, int k) {
        int res = 0;

        int[] count = new int[26];

        int left = 0;
        int max = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            count[c - 'A']++;

            for (int j = 0; j < 26; j++) max = Math.max(max, count[j]);

            //总长度 < K + 字母组的长度 说明K可以将字母组扩更长。
            if (right - left + 1 <= k + max) {
                res = right - left + 1;
            } else {
                char ch = s.charAt(left);
                count[ch - 'A']--;
                left++;
            }
        }
        return res;
    }


    //base on characterReplacement_1 . one pass 但逻辑太罗索了。
    //先把 每个字母的长度 和 尾坐标，组成list 放入map;
    //处理list计算出单个字母，可能组成的最长长度。
    //Runtime: 22 ms, faster than 41.48% of Java online submissions for Longest Repeating Character Replacement.
    //Memory Usage: 43.7 MB, less than 35.92% of Java online submissions for Longest Repeating Character Replacement.
    //one pass
    //Time: O(N); Space: worst case:  O(N), best case : O(1)
    public int characterReplacement_2(String s, int k) {
        int res = 0;
        int strLen = s.length();

        Map<Character, Deque<int[]>> map = new HashMap<>();
        Map<Character, int[]> mapSummary = new HashMap<>(); //为了避免 Deque的复杂操作，所以这个单放了。

        int i = 0;
        while (i < strLen){
            char c = s.charAt(i);

            Deque<int[]> list = map.getOrDefault(c, new LinkedList<>());
            map.put(c, list);

            //init. add head to another map 放当前串总长的
            if (list.isEmpty()) mapSummary.put(c, new int[]{0, 0});

            //加入新的段
            //current segment
            int[] currItem = new int[]{i, 0}; //idx: len
            //list.add(currItem);

            int len = 1;
            while(i + len < strLen && s.charAt(i + len) == c) len++; //same chars
            i += len;

            currItem[0] += len;
            currItem[1] += len;
            res = Math.max(res, currItem[1] + k);

            int currGap = k + 1;
            if (!list.isEmpty()) currGap = currItem[0] - currItem[1] - list.peekLast()[0];
            //首个字母段 or gap is too large to fill
            if (list.isEmpty() || currGap > k) {
                Deque<int[]> list2 = new LinkedList<>();
                map.put(c, list2);
                list2.addLast(currItem);
                mapSummary.put(c, new int[]{0, currItem[1]});
                continue;
            }

            list.addLast(currItem);
            //加上当前段
            int[] headItem = mapSummary.get(c);
            int totalGap = headItem[0];
            int totalLen = headItem[1];

            int[] firstItem = list.peekFirst();
            // K不够时，从头开始拆字串，给后端用。
            while (list.size() > 1 && currGap > k - totalGap){ //k不够了，从第一个开始置换
                list.pollFirst();
                int[] secondItem = list.peekFirst();
                totalGap -= secondItem[0] - secondItem[1] - firstItem[0];
                totalLen -= firstItem[1];
                firstItem = secondItem;
            }

            headItem[0] = totalGap + currGap;
            headItem[1] = totalLen + currItem[1];

            res = Math.max(res, headItem[1] + k);
        }
        return Math.min(res, strLen);
    }

    //先把 每个字母的长度 和 尾坐标，组成list 放入map;
    //处理list计算出单个字母，可能组成的最长长度。
    //逻辑太啰嗦!
    //Runtime: 22 ms, faster than 41.48% of Java online submissions for Longest Repeating Character Replacement.
    //Memory Usage: 43.7 MB, less than 35.92% of Java online submissions for Longest Repeating Character Replacement.
    //Time: O(N); Space: worst case:  O(N), best case : O(1)
    public int characterReplacement_1(String s, int k) {
        int res = 0;

        Map<Character, List<int[]>> map = new HashMap<>();
        int i = 0;
        //Time: O(N)
        while (i < s.length()){
            char c = s.charAt(i);

            int len = 1;
            while(i + len <s.length() && s.charAt(i + len) == c) len++;
            res = Math.max(res, len + k);

            i += len;

            int[] idxLen = new int[]{i, len}; //index, length
            List<int[]> list = map.getOrDefault(c, new ArrayList<>());
            map.put(c, list);
            list.add(idxLen);
        }

        Set<Character> set = new HashSet<>();
        for (Character c: map.keySet()){
            if (!set.add(c)) continue;

            List<int[]> list = map.get(c);

            int firstItemIdx = 0;
            int[] firstItem = list.get(firstItemIdx);
            int lastIdx = firstItem[0];
            int charLen = firstItem[1];
            int charSpan = 0;

            for(int j = 1; j < list.size(); j++){
                int[] item = list.get(j);
                int idx = item[0];
                int len = item[1];
                int span = idx -len - lastIdx;

                //the gap is too large to fill.
                if (span > k) {
                    firstItemIdx = j;
                    firstItem = list.get(firstItemIdx);
                    lastIdx = idx;
                    charLen = len;
                    charSpan = 0;
                    continue;
                }

                while (firstItemIdx < j && span > (k - charSpan)){ //k不够了。
                    int[] secondItem = list.get(firstItemIdx + 1);
                    charLen -= firstItem[1];
                    charSpan -= secondItem[0] - secondItem[1] - firstItem[0];
                    firstItem = secondItem;
                    firstItemIdx++;
                }

                charLen += len;
                charSpan += span;
                res = Math.max(res, charLen + k);
                lastIdx = idx;
            } // End for

        }// End For

        return Math.min(res, s.length());
    }


}
