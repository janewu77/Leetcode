package LeetCode;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static java.time.LocalTime.now;

/**
 *
 * Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 *
 *
 *
 * Example 1:
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 *
 *
 * Example 2:
 * Input: s = "aa", p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 *
 *
 * Example 3:
 * Input: s = "ab", p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 20
 * 1 <= p.length <= 30
 * s contains only lowercase English letters.
 * p contains only lowercase English letters, '.', and '*'.
 * It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
 *
 *
 */

/**
 * H - [time: 120-]
 *  - Accepted, 虽然性能不是很理想。
 *  - 解法与44H类似。面对复杂的条件，整理清楚，再写if-else。
 *  - 这里用了 back tracking 的概念。 （要再study一下）
 *
 */
public class N10HRegularExpressionMatching {

    public static void main(String[] args){

        System.out.println(now());
//        doRun(true, "a", "a");
//        doRun(false, "b", "a");
//        doRun(false, "aa", "a");
//
//        doRun(false, "", ".");
//        doRun(false, "a", "a.");
//
//        doRun(true, "ad", "a.");
//        doRun(false, "ada", "a.");
//        doRun(false, "cb", ".a");
//        doRun(true, "aac", "a.c");
//        doRun(false, "ab", "ab.");
//        doRun(false, "abdadsf", "ab.");
//        doRun(true, "abd", "ab.");
//
//        doRun(true, "aab", "c*a*b");
//
//        doRun(true, "", "a*b*c*");
//        doRun(false, "", "a*c");
//
//        doRun(true, "a", "a*");
//        doRun(false, "sadfsdfa", "a*");
//
//        doRun(true, "bbbbba", "b*a");
//        doRun(false, "bbaddbbadd", "b*add");
//        doRun(false, "bbccba", "b*a");
//        doRun(false, "bbccb", "b*b");
//        doRun(false, "ababzzx", "a*");
//        doRun(true, "ababx", "a*b*..x*");
//
//        doRun(true, "bbb", ".*");
//        doRun(true, "bbaab", ".*");
//
//        doRun(false, "ad", "......");
//        doRun(false, "", "......");
//
//        doRun(true, "a", "..*");
//        doRun(true, "aafdfdfasd", "..*");
//
//        doRun(true, "aaa", "ab*a*c*a");

        doRun(true, "bbbb", "b*b*b*bbbb");

        System.out.println(now());
        System.out.println("=========");

    }
    private static int c = 1;
    private static void doRun(boolean expected, String s, String p){
        boolean res = new N10HRegularExpressionMatching().isMatch(s, p);
        System.out.println("[" + (expected == res) +"] "+(c++)+ ".result:"+ res + ".expected:"+expected);
    }

    //add memo
    //Runtime: 2 ms, faster than 97.02% of Java online submissions for Regular Expression Matching.
    //Memory Usage: 42.1 MB, less than 86.41% of Java online submissions for Regular Expression Matching.
    //without memo
    //Runtime: 39 ms, faster than 36.36% of Java online submissions for Regular Expression Matching.
    //Memory Usage: 40.8 MB, less than 95.73% of Java online submissions for Regular Expression Matching.
    public boolean isMatch_dp(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        return  isMatch_recursion(text, pattern, 0 ,0 );
    }
    Map<Integer, Boolean> memo = new HashMap<>();
    private boolean isMatch_recursion(String text, String pattern, int sFrom, int pFrom) {
        boolean res;
        int memoKey = sFrom * 100 + pFrom;
        if (memo.containsKey(memoKey)) return memo.get(memoKey);
        if (pFrom == pattern.length()) {
            res = sFrom == text.length();
        }else {

            boolean first_match = (sFrom < text.length() &&
                    (pattern.charAt(pFrom) == text.charAt(sFrom) || pattern.charAt(pFrom) == '.'));

            if (pFrom + 1 < pattern.length() && pattern.charAt(pFrom + 1) == '*') {
                res = (isMatch_recursion(text, pattern, sFrom, pFrom + 2) ||
                        (first_match && isMatch_recursion(text, pattern, sFrom + 1, pFrom)));
            } else {
                res = first_match && isMatch_recursion(text, pattern, sFrom + 1, pFrom + 1);
            }
        }
        memo.put(memoKey, res);
        return res;
    }

    //Runtime: 149 ms, faster than 14.07% of Java online submissions for Regular Expression Matching.
    //Memory Usage: 42.7 MB, less than 51.49% of Java online submissions for Regular Expression Matching.
    //back tracking + stack
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();

        int pLen = p.length();
        int sIdx = 0, pIdx = 0;
        Stack<Pair<Integer, Integer>> stack = new Stack<>();
        while (sIdx < s.length()){

            if ((pIdx < pLen - 1) && p.charAt(pIdx + 1) == '*'){
                //put to stack. for back tracking
                if (p.charAt(pIdx) == s.charAt(sIdx) || p.charAt(pIdx) == '.')
                    stack.add(new Pair<>(sIdx, pIdx));
                pIdx += 2;

            }else if (pIdx < pLen && (p.charAt(pIdx) == '.' || p.charAt(pIdx) == s.charAt(sIdx))) {
                pIdx++;
                sIdx++;
            }else if (stack.isEmpty()){
                return false;
            }else{
                Pair<Integer, Integer> pair = stack.pop();
                sIdx = pair.getKey() + 1;
                pIdx = pair.getValue();
            }
        }//End while

        // skip tail ：a*b*c*
        while (pIdx < pLen - 1){
            if (p.charAt(pIdx+1) == '*') pIdx += 2;
            else break;
        }

        return pIdx == pLen;
    }

}
