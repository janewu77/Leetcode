package LeetCode;


import java.util.*;

/**
 * Given a string s, return the length of the longest substring
 * that contains at most two distinct characters.
 *
 * Example 1:
 * Input: s = "eceba"
 * Output: 3
 *
 * Explanation: The substring is "ece" which its length is 3.
 * Example 2:
 * Input: s = "ccaabbb"
 * Output: 5
 * Explanation: The substring is "aabbb" which its length is 5.
 *
 *
 * Constraints:
 * 1 <= s.length <= 105
 * s consists of English letters.
 *
 */

/**
 * M - [time: 30-]
 *  -  学到： Collections.min;  计数移窗
 */
//340
public class N159MLongestSubstringwithAtMostTwoDistinctCharacters {

    public static void main(String[] args) {
        String s = "";

        s = "eceeeeba";
        doRun(6, s);

        s = "aa";
        doRun(2, s);

        s = "abcdabcccabcdabcd";
        doRun(4, s);

        s = "abccccccccccccccccc";
        doRun(18, s);

        s = "aaaa";
        doRun(4, s);
    }

    private static void doRun(int expected, String s){
        int res = new N159MLongestSubstringwithAtMostTwoDistinctCharacters().lengthOfLongestSubstringTwoDistinct(s);
        //String strRes = Arrays.stream(res).mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(""));
        //System.out.println("=======");
        System.out.println("["+(expected == res)+"].[expected:"+ expected+"] res:"+res);
    }

    //2022.9.15
    //不缩小window size. 让window保持最大
    //Runtime: 50 ms, faster than 84.23% of Java online submissions for Longest Substring with At Most Two Distinct Characters.
    //Memory Usage: 42.4 MB, less than 99.79% of Java online submissions for Longest Substring with At Most Two Distinct Characters.
    //slide window + windowsize
    //Time:O(N); Space: O(k)
    //N is a number of characters in the input. k is the number of distinct characters.
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int MaxDistinctLetter = 2;
        if (s.length() <= MaxDistinctLetter) return s.length();

        int left = 0, right = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (right < s.length()) {
            char c = s.charAt(right++);
            map.put(c, map.getOrDefault(c, 0) + 1);

            //at most two distinct characters.
            if (map.size() > 2) {
                char leftC = s.charAt(left++); //left++抵销right++, 让window可以保持最大
                int count = map.get(leftC);
                if (count <= 1) map.remove(leftC);
                else map.put(leftC, count - 1);
            }
        }
        return right - left;
    }

    //2022.8.2
    //N340
    //Runtime: 50 ms, faster than 82.54% of Java online submissions for Longest Substring with At Most Two Distinct Characters.
    //Memory Usage: 42.9 MB, less than 93.91% of Java online submissions for Longest Substring with At Most Two Distinct Characters.
    //用计数的方式，来移动window. 将减到零的字母，从窗口中移出。
    //Time：O（N），Space: O(k)
    public int lengthOfLongestSubstringTwoDistinct_5(String s) {
        int MaxDistinctLetter = 2;
        if (s.length() <= MaxDistinctLetter) return s.length();

        int res = 0;
        int left = 0, right = 0;

        Map<Character, Integer> map = new HashMap<>();
        while (right < s.length()){
            char c = s.charAt(right++);
            map.put(c, map.getOrDefault(c, 0) + 1);
            //map.compute(c, (k, v) -> v == null ? 1 : v + 1);

            //at most two distinct characters.
            if (map.size() <= MaxDistinctLetter) {
                res = Math.max(res, right - left);
                continue;
            }

            //move left, reduce the window's size
            while (map.size() > MaxDistinctLetter) {
                char delChar = s.charAt(left++);
                map.put(delChar, map.get(delChar) - 1);
                if (map.get(delChar) <= 0) map.remove(delChar);
            }
        }
        return res;
    }

    //TreeSet 与 用了Collections.min 雷同
    //保存字母的最新位置，以便能快速缩小window size
    //Runtime: 84 ms, faster than 61.98% of Java online submissions for Longest Substring with At Most Two Distinct Characters.
    //Memory Usage: 43.5 MB, less than 86.38% of Java online submissions for Longest Substring with At Most Two Distinct Characters.
    //TreeSet
    //Time：O（N * logK），Space: O(k)
    public int lengthOfLongestSubstringTwoDistinct_4(String s) {
        int MaxDistinctLetter = 2;
        if (s.length() <= MaxDistinctLetter) return s.length();

        int res = 0;
        int left = 0, right = 0;

        Map<Character, Integer> map = new HashMap<>();  //字母：位置
        TreeSet<Integer> treeSet = new TreeSet<>();     //每个字母当前最大位置

        while (right < s.length()){
            char c = s.charAt(right);
            int oldIndex = map.getOrDefault(c, -1);
            treeSet.remove(oldIndex); treeSet.add(right);
            map.put(c, right++);

            if (map.size() > MaxDistinctLetter) {
                // delete the leftmost character
                int tobeDelete = treeSet.pollFirst();
                //Collections.min(map.values());
                map.remove(s.charAt(tobeDelete));
                // move left pointer of the slide-window
                left = tobeDelete + 1;

            }else
                res = Math.max(res, right - left);
        }

        return res;
    }

    //solution里面看来的算法。 用了Collections.min。但效率还是不好。
    //本质上和 lengthOfLongestSubstringTwoDistinct_3 是一样的。
    //Runtime: 128 ms, faster than 36.36% of Java online submissions for Longest Substring with At Most Two Distinct Characters.
    //Memory Usage: 67.5 MB, less than 31.16% of Java online submissions for Longest Substring with At Most Two Distinct Characters.
    //Collections.min
    //Time：O（N * LogN），Space: O(k)
    public int lengthOfLongestSubstringTwoDistinct_3(String s) {
        int MaxDistinctLetter = 2;
        if (s.length() <= MaxDistinctLetter) return s.length();

        int res = 0;
        int left = 0, right = 0;
        Map<Character, Integer> map = new HashMap<>();

        while (right < s.length()){
            map.put(s.charAt(right), right++);

            if (map.size() > MaxDistinctLetter) {
                // delete the leftmost character
                int tobeDelete = Collections.min(map.values()); //Time: LogN?
                map.remove(s.charAt(tobeDelete));
                // move left pointer of the slidewindow
                left = tobeDelete + 1;
            }else
                res = Math.max(res, right - left);
        }
        return res;
    }

    //Runtime: 149 ms, faster than 25.69% of Java online submissions for Longest Substring with At Most Two Distinct Characters.
    //Memory Usage: 117.4 MB, less than 5.39% of Java online submissions for Longest Substring with At Most Two Distinct Characters.
    //HashMap 需要重组HashMap,效能不好 (后记， 可用LinkedHashMap 解决）
    //time: O(N); Space: O(1)
    public int lengthOfLongestSubstringTwoDistinct_2(String s) {
        int k = 2;
        int res = 0;

        int p = 0;
        char oldChar = '$';
        int count = 0;

        Map<Character, Integer> map = new HashMap<>();

        while (p < s.length()) {
            char c = s.charAt(p);
            if (c != oldChar) {
                map.put(c, p);
                oldChar = c;
            }
            p++;

            if (map.keySet().size() <= k){
                count++;
            }else{
                res = Math.max(res, count);

                // count, set
                int lastBegin = map.get(s.charAt(p-2));
                count = p - lastBegin;

                //可用LinkedHashMap 解决move的问题
                Set<Character> set = new HashSet<>();
                for (char key : map.keySet())
                    if (map.get(key) < lastBegin) set.add(key);

                for (char preChar : set)
                    map.remove(preChar);
            }
        }

        if (p == s.length()) res = Math.max(res, count);
        return res;
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    //first
    //Runtime: 419 ms, faster than 5.02% of Java online submissions for Longest Substring with At Most Two Distinct Characters.
    //Memory Usage: 145 MB, less than 5.39% of Java online submissions for Longest Substring with At Most Two Distinct Characters.
    //time: 30-
    //time: O(N*K); Space: O(K)
    public int lengthOfLongestSubstringTwoDistinct_1(String s) {
        int k = 2;
        int res = 0;

        int p = 0;
        int left = 0;
        int baseCount = 0;
        Set<Character> winSet = new HashSet<>();
        while (left < s.length()){

            int count = baseCount;
            Set<Character> set = new HashSet<>();
            set.addAll(winSet);
            while (p < s.length()) {
                char c = s.charAt(p++);
                set.add(c);
                if (set.size() > k) {
                    winSet = new HashSet<>();
                    baseCount = handleWindow(s, winSet, p - 1, k);
                    break;
                }
                count++;
            }
            res = Math.max(res, count);
            left = p;
        }
        return res;
    }

    private int handleWindow(String s, Set<Character> winSet, int idx, int k){
        int baseCount = 0;
        while (idx >= 0){
            char c = s.charAt(idx);
            winSet.add(c);
            if (winSet.size() > k){
                winSet.remove(c);
                break;
            }
            idx--;
            baseCount++;
        }
        return baseCount;
    }
}
