package LeetCode;


import java.util.*;

import static java.time.LocalTime.now;

/**
 * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
 *
 * An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:
 *
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
 * Note: a + b is the concatenation of strings a and b.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 * Example 2:
 *
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 * Example 3:
 *
 * Input: s1 = "", s2 = "", s3 = ""
 * Output: true
 *
 *
 * Constraints:
 *
 * 0 <= s1.length, s2.length <= 100
 * 0 <= s3.length <= 200
 * s1, s2, and s3 consist of lowercase English letters.
 *
 *
 * Follow up: Could you solve it using only O(s2.length) additional memory space?
 */
public class N97MInterleavingString {
    public static void main(String[] args){
        String s1,s2,s3;
        System.out.println(now());
        s1 = "bccd";
        s2 = "bbcad";
        s3 = "bbbacccdd";
        doRun(false, s1, s2, s3);

        s1 = "a";
        s2 = "ccb";
        s3 = "ccab";
        doRun(true, s1, s2, s3);

        s1 = "aabcc";
        s2 = "dbbca";
        s3 = "aadbbcbcac";

        s1 = "cc";
        s2 = "bbca";
        s3 = "bcbcac";
//
        s1 = "c";
        s2 = "ca";
        s3 = "cac";
        doRun(true, s1, s2, s3);

        s1 = "aabcc";
        s2 = "dbbca";
        s3 = "aadbcbbcac";
        doRun(true, s1, s2, s3);

        s1 = "a";
        s2 = "";
        s3 = "c";
        doRun(false, s1, s2, s3);

        s1 = "aa";
        s2 = "ab";
        s3 = "abaa";
        doRun(true, s1, s2, s3);

        s1 = "aabaac";
        s2 = "aadaaeaaf";
        s3 = "aadaaeaabaafaac";
        doRun(true, s1, s2, s3);

        s1 = "aabc";
        s2 = "abad";
        s3 = "aabcabad";
        doRun(true, s1, s2, s3);

        s1 = "ac";
        s2 = "f";
        s3 = "fac";
        doRun(true, s1, s2, s3);

        s1 = "aabd";
        s2 = "abdc";
        s3 = "aabdbadc";
        doRun(false, s1, s2, s3);

        s1 = "";
        s2 = "";
        s3 = "";
        doRun(true, s1, s2, s3);

        s1 = "bbbbbabbbbabaababaaaabbababbaaabbabbaaabaaaaababbbababbbbbabbbbababbabaabababbbaabababababbbaaababaa";
        s2 = "babaaaabbababbbabbbbaabaabbaabbbbaabaaabaababaaaabaaabbaaabaaaabaabaabbbbbbbbbbbabaaabbababbabbabaab";
        s3 = "babbbabbbaaabbababbbbababaabbabaabaaabbbbabbbaaabbbaaaaabbbbaabbaaabababbaaaaaabababbababaababbababbbababbbbaaaabaabbabbaaaaabbabbaaaabbbaabaaabaababaababbaaabbbbbabbbbaabbabaabbbbabaaabbababbabbabbab";
        doRun(false, s1, s2, s3);

        s1 = "abababababababababababababababababababababababababababababababababababababababababababababababababbb";
        s2 = "babababababababababababababababababababababababababababababababababababababababababababababababaaaba";
        s3 = "abababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababbb";
        doRun(false, s1, s2, s3);
        System.out.println(now());
    }

    static private void doRun(boolean expect, String ss1, String ss2, String ss3) {
        boolean res = new N97MInterleavingString().isInterleave(ss1, ss2, ss3);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //1.recursion + memo
    //Runtime: 1 ms, 98%; Memory: 41MB 72%
    //Time: O(L1 * L2); Space: O(L1 * L2)
    public boolean isInterleave(String ss1, String ss2, String ss3) {
        if(ss1.length() + ss2.length() != ss3.length()) return false;
        if (ss3.length() == 0) return true;
        return helper(ss1, ss2, ss3,0, 0, 0, new Boolean[ss1.length()+1][ss2.length()+1]);
    }

    private boolean helper(String s1, String s2, String s3, int i1, int i2, int i3, Boolean[][] memo) {
        if(memo[i1][i2] != null)
            return memo[i1][i2];

        boolean result;
        if (i1 >= s1.length()) {
            result = s3.substring(i3).equals(s2.substring(i2));

        } else if (i2 >= s2.length()) {
            result = s3.substring(i3).equals(s1.substring(i1));

        } else {
            result = ((s3.charAt(i3) == s1.charAt(i1) && helper(s1, s2, s3,i1 + 1, i2, i3 + 1, memo))
                    || (s3.charAt(i3) == s2.charAt(i2) && helper(s1, s2, s3, i1, i2 + 1, i3 + 1, memo)));
        }
        return memo[i1][i2] = result;
    }


    //Stack转换掉了递归
    //超时
    public boolean isInterleave3(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length()) return false;

        int i1, i2, i3;
        String c1, c2, c3;
        Stack<Integer[]> stack = new Stack();
        stack.push(new Integer[]{0,0,0});
        while(!stack.isEmpty()){
            Integer[] item = stack.pop();
            i1 = item[0]; i2 = item[1]; i3 = item[2];

            if (!(i1 < s1.length() && i2 < s2.length() && i3 < s3.length())) {
                if (i1 <= s1.length() - 1 && s1.substring(i1).equals(s3.substring(i3)))
                    return true;
                if (i2 <= s2.length() - 1 && s2.substring(i2).equals(s3.substring(i3)))
                    return true;
                continue;
            }

            c1 = s1.substring(i1, i1+1);
            c2 = s2.substring(i2, i2+1);
            c3 = s3.substring(i3, i3+1);
            if (!c3.equals(c1) && !c3.equals(c2)){
                if (stack.isEmpty()) return false;
                continue;
            }

            if (c3.equals(c1) && c3.equals(c2)){
                stack.push(new Integer[]{i1 + 1, i2, i3 + 1});
                stack.push(new Integer[]{i1, i2 + 1, i3 + 1});
                continue;
            }

            //用"不同" 加快缩短需要比对的字串
            String diff = c3.equals(c1)? c2: c1;
            int idx = s3.indexOf(diff, i3);
            if (idx <= 0) return false;
            String p3 = s3.substring(i3, idx);

            if (c3.equals(c1)) {
                if (s1.indexOf(p3, i1) != i1) continue;
                i1 += p3.length();
            } else {
                if (s2.indexOf(p3, i2) != i2)  continue;
                i2 += p3.length();
            }
            i3 += p3.length();
            stack.push(new Integer[]{i1, i2, i3});
        }// End while
        return false;
    }


    // 递归
    // TEL 超时
    public boolean isInterleave1(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length()) return false;

        int i1 = 0;
        int i2 = 0;
        int i3 = 0;
        while(i1 < s1.length() && i2 < s2.length() && i3 < s3.length()){
            String c1 = s1.substring(i1, i1+1);
            String c2 = s2.substring(i2, i2+1);
            String c3 = s3.substring(i3, i3+1);
            if (!c3.equals(c1) && !c3.equals(c2)){
                return false;
            }
            if (c3.equals(c1) && c3.equals(c2)){
                if (isInterleave(s1.substring(i1+1), s2.substring(i2), s3.substring(i3+1)))
                    return true;
                if (isInterleave(s1.substring(i1), s2.substring(i2+1), s3.substring(i3+1)))
                    return true;
            }else{
                if (c3.equals(c1)) i1++;
                if (c3.equals(c2)) i2++;
            }
            i3++;
        }

        if (i3 <= s3.length() - 1){
            if (i1 <= s1.length() - 1)
                return s1.substring(i1).equals(s3.substring(i3));

            if (i2 <= s2.length() - 1)
                return s2.substring(i2).equals(s3.substring(i3));
        }
        return true;
    }
}
