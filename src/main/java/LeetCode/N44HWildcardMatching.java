package LeetCode;

import javafx.util.Pair;

import java.util.*;

import static java.time.LocalTime.now;

/**
 *
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
 *
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 *
 *
 *
 * Example 1:
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 *
 * Example 2:
 * Input: s = "aa", p = "*"
 * Output: true
 *
 * Explanation: '*' matches any sequence.
 *
 * Example 3:
 * Input: s = "cb", p = "?a"
 * Output: false
 * Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 *
 *
 * Constraints:
 * 0 <= s.length, p.length <= 2000
 * s contains only lowercase English letters.
 * p contains only lowercase English letters, '?' or '*'.
 *
 *
 */

/**
 * H - 【time： 120-】
 *
 *  - 花的时间比较多。对于过于烦锁的步骤，要有耐心细分。
 *
 */
public class N44HWildcardMatching {

    public static void main(String[] args){
        doRun(true, "", "");
        doRun(true, "", "*");

        doRun(false, "", "?");

        doRun(false, "", "*a");

        doRun(false, "aa", "a");

        doRun(false, "cb", "?a");
        doRun(true, "aac", "a?c");
        doRun(false, "ab", "ab?");
        doRun(true, "abd", "ab?");
        doRun(false, "abdadsf", "ab?");

        doRun(true, "a", "*a");
        doRun(true, "aa", "*");

        doRun(true, "sadfsdfa", "*a");

        doRun(true, "bbbbba", "b*a");
        doRun(true, "bbbbba", "*b*a");
        doRun(true, "bbaddbbadd", "*b*add");
        doRun(true, "bbccba", "*b*a");
        doRun(true, "bbccb", "*b*b");
        doRun(true, "bbccba", "*b*a");

        doRun(true, "ababzzx", "a*");
        doRun(true, "ababzzx", "*ab??x*");


        doRun(true, "bbb", "*???*");
        doRun(true, "bbaab", "*???*");
        doRun(false, "cd", "*???*");

        doRun(true, "bbccba", "*??*");

        doRun(true, "abcabczzzde", "*abc???de*");
        doRun(false, "mississippi", "m??*ss*?i*pi");

        doRun(true, "bbccba", "*b**********a");
        doRun(true, "", "******");
        doRun(false, "x", "****b**");
        doRun(true, "b", "****b**");
        doRun(false, "", "??????");

        doRun(false, "bbccba", "*?????b*");
        doRun(true, "bbccba", "*????b*");

        doRun(false, "bbccba", "??a");
        doRun(true, "bbccba", "??*");

        doRun(true, "bbccba", "*??");
        doRun(true, "hi", "*?");

        doRun(true, "mississippi", "m*si*");
//
        System.out.println(now());
        doRun(false, "abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb",
                "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb");
        System.out.println(now());
        System.out.println("=========");

    }
    private static int c = 1;
    private static void doRun(boolean expected, String s, String p){
        boolean res = new N44HWildcardMatching().isMatch(s, p);

        System.out.println("[" + (expected == res) +"] "+(c++)+ ".result:"+ res + ".expected:"+expected);
    }

    //Runtime: 2 ms, faster than 100.00% of Java online submissions for Wildcard Matching.
    //Memory Usage: 42.5 MB, less than 95.24% of Java online submissions for Wildcard Matching.
    //Time: O(min(S,P)) - O(S⋅P); Space : O(1)
    public boolean isMatch(String s, String p) {
        if (s.isEmpty() && p.isEmpty()) return true;
        if (s.equals(p)) return true;

//        remove duplicated asterisk(*)
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i<p.length(); i++){
//            char lastC = i==0 ? '$' : p.charAt(i-1);
//            if (p.charAt(i) == '*' && lastC == '*') continue;
//            else sb.append(p.charAt(i));
//        }
//        p = sb.toString();

        int sLen = s.length(), pLen = p.length();
        int sIdx = 0, pIdx = 0;
        int tmpPIdx = -1, tmpSIdx = -1;

        while (sIdx < sLen){

            if ((pIdx < pLen) &&
                    (p.charAt(pIdx) == '?' || p.charAt(pIdx) == s.charAt(sIdx))) {

                pIdx++; sIdx++;

            }else if (pIdx < pLen && p.charAt(pIdx) == '*'){

                tmpSIdx = sIdx;
                tmpPIdx = pIdx;
                pIdx++;

            }else if (tmpPIdx == -1){
                return false;
            }else{

                pIdx = tmpPIdx + 1;
                sIdx = tmpSIdx + 1;
                tmpSIdx = sIdx; //每次重新开始时，SIDX会多移一位。

            }
        }
        // 处理tail. 若都是*,则true;
        for (int i = pIdx; i < pLen; i++)
            if (p.charAt(i) != '*') return false;

        return true;
    }


    //Runtime: 103 ms, faster than 8.44% of Java online submissions for Wildcard Matching.
    //Memory Usage: 42.9 MB, less than 89.78% of Java online submissions for Wildcard Matching.
    //cursive + memorization
    public boolean isMatch_cursive(String s, String p) {
        if (s.isEmpty() && p.isEmpty()) return true;
        if (s.equals(p)) return true;
        StringBuilder sb = new StringBuilder();
        char lastC = '$';
        for (int i = 0; i<p.length(); i++){
            char c = p.charAt(i);
            if (c == '*' && lastC == '*') continue;
            else sb.append(c);
            lastC = c;
        }
        //if (s.isEmpty()) return false;
        return isMatch_helper(s, 0, sb.toString(), 0);
    }

//    Map<Integer, Pair<Integer, Boolean>> memo = new HashMap<>();
//    private void putMemo(int sFrom, int pFrom, boolean result){
//        memo.put(pFrom, new Pair<>(sFrom, result));
//    }
//    private Boolean getMemo(int sForm, int pFrom){
//        if (!memo.containsKey(pFrom)) return null;
//        Pair<Integer, Boolean> pair = memo.get(pFrom);
//        if (pair.getKey() >= sForm ) return pair.getValue();
//        return null;
//        //memo.put(pFrom, new Pair<>(sFrom, result));
//    }

    Map<Integer, List<Pair<Integer, Boolean>>> memo = new HashMap<>();
    private void putMemo(int sFrom, int pFrom, boolean result){
        List<Pair<Integer, Boolean>> list = memo.getOrDefault(pFrom, new ArrayList<>());
        memo.put(pFrom, list);
        for(int i = 0; i< list.size();i++){
            Pair<Integer, Boolean> pair = list.get(i);
            if (pair.getKey() > sFrom ) {
                list.add(i, new Pair<>(sFrom, result));
                return;
            }
        }
        list.add(new Pair<>(sFrom, result));
    }

    private Boolean getMemo(int sForm, int pFrom){
        if (!memo.containsKey(pFrom)) return null;

        List<Pair<Integer, Boolean>> list = memo.get(pFrom);
        for (Pair<Integer, Boolean> pair : list){
            if (pair.getKey() >= sForm ) return pair.getValue();
        }
        return null;
    }


    //time limit exceeded
    private boolean isMatch_helper(String s, int sFrom, String p, int pFrom) {
        if (s.isEmpty() && p.isEmpty()) return true;

        Boolean res = getMemo(sFrom,pFrom);
        if (res != null ) return res;

        if (s.substring(sFrom).equals(p.substring(pFrom))) {
            putMemo(sFrom, pFrom,true);
            return true;
        }

        if (pFrom + 1 == p.length() && p.charAt(pFrom) == '*')  {
            putMemo(sFrom, pFrom,true);
            return true;
        }

        if (sFrom >= s.length() || pFrom >= p.length()) {
            putMemo(sFrom, pFrom,false);
            return false;
        }

        if (p.charAt(pFrom) == '*') {

            pFrom = pFrom + 1;
            while (sFrom < s.length() && pFrom < p.length()) {

                if (s.charAt(sFrom) == p.charAt(pFrom) || p.charAt(pFrom) == '*') {

                    if (isMatch_helper(s, sFrom, p, pFrom)) return true;

                } else if (p.charAt(pFrom) == '?') {
                    //处理 [?结尾]； [？？*] 的特殊情况
                    while (pFrom < p.length() && p.charAt(pFrom) == '?') {
                        pFrom++;  sFrom++;
                    }

                    if (pFrom == p.length())  {
                        putMemo(sFrom, pFrom,sFrom <= s.length());
                        return sFrom <= s.length();
                    }
                    if (sFrom > s.length()) {
                        putMemo(sFrom, pFrom,false);
                        return false;
                    }
                    sFrom--;
                }
                sFrom++;
            }

        } else {

            while (sFrom < s.length() && pFrom < p.length() && p.charAt(pFrom) != '*') {
                if (p.charAt(pFrom) != '?' && s.charAt(sFrom) != p.charAt(pFrom)) {
                    putMemo(sFrom, pFrom,false);
                    return false;
                }
                sFrom++; pFrom++;
            }
        }

        if (sFrom == s.length() && pFrom == p.length()) {
            putMemo(sFrom, pFrom,true);
            return true;
        }

        return isMatch_helper(s, sFrom, p, pFrom);
    }

}
