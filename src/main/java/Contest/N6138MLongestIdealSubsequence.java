package Contest;

/**
 * You are given a string s consisting of lowercase letters and an integer k.
 * We call a string t ideal if the following conditions are satisfied:
 *
 * t is a subsequence of the string s.
 * The absolute difference in the alphabet order of every two adjacent letters
 * in t is less than or equal to k.
 * Return the length of the longest ideal string.
 *
 * A subsequence is a string that can be derived from another string by deleting
 * some or no characters without changing the order of the remaining characters.
 *
 * Note that the alphabet order is not cyclic. For example, the absolute difference in the alphabet order of 'a' and 'z' is 25, not 1.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "acfgbd", k = 2
 * Output: 4
 * Explanation: The longest ideal string is "acbd". The length of this string is 4, so 4 is returned.
 * Note that "acfgbd" is not ideal because 'c' and 'f' have a difference of 3 in alphabet order.
 * Example 2:
 *
 * Input: s = "abcd", k = 3
 * Output: 4
 * Explanation: The longest ideal string is "abcd". The length of this string is 4, so 4 is returned.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * 0 <= k <= 25
 * s consists of lowercase English letters.
 */



import static java.time.LocalTime.now;

/**
 * M - [time: 120+ 】
 *  - 掉进了backtracking。 这个题用DP更快。
 *
 */
//2370. Longest Ideal Subsequence
public class N6138MLongestIdealSubsequence {

    public static void main(String[] args) {

        System.out.println(now());

        doRun3(5,"acevwxzye", 2);
        doRun3(4,"acfgbd", 2);
        doRun3(5,"eduktdb", 15);
        doRun3(2,"pvjcci", 4);

        doRun3(42,"dyyonfvzsretqxucmavxegvlnnjubqnwrhwikmnnrpzdovjxqdsatwzpdjdsneyqvtvorlwbkb", 7);

        System.out.println(now());
        System.out.println("==================");

    }
    static private void doRun3(int expect, String s,  int k) {
        int res = new N6138MLongestIdealSubsequence().longestIdealString(s,k);
//        String res = comm.toString(res1);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
        //System.out.println("==================");
    }

    //120+
    // 题意：找[差距小于K]的邻居组成的最长的字符串的长度。
    // 思路：
    // 前面的算法一直TLC. 所以，目的要找现快的算法。 更快"计算"出结果。
    // 每个字母 可以统计出自己有几个K邻。然后，把自己接在"最长"的队伍上。
    //Runtime: 42 ms, faster than 71.43% of Java online submissions for Longest Ideal Subsequence.
    //Memory Usage: 48.3 MB, less than 85.71% of Java online submissions for Longest Ideal Subsequence.
    //dp
    //Time : O( N * 2 * k ); Space: O(26)
    public int longestIdealString(String s, int k) {
        int[] counter = new int[26];
        int res = 0;

        for (int i = 0; i < s.length(); i++){
            int count = 0;
            int idx = s.charAt(i) -'a';

            // in k-neighbours, find the longest line
            // Time:O(2*k)
            for (int j = idx - k; j <= idx + k; j++)
                if (j >= 0 && j < counter.length)
                    count = Math.max(count, counter[j]);

            // join the longest line.
            counter[idx] = 1 + count;
            res = Math.max(res, counter[idx]);
        }
        return res;
    }


    //////////////////////////////////////////////////
    //Time Limit Exceeded
    //backtracking
    public int longestIdealString_1(String s, int k) {
        return helper_backtracking(s, k, 0, '$', 0);
    }

    private int helper_backtracking(String s, int k, int begin, int lastC, int len){
        int res = 0;
        if (begin + 1 > s.length()) return res;

        for(int i = begin; i < s.length(); i++){
            char c = s.charAt(i);

            //if (len == 0) lastC = c;
            lastC = len == 0 ? c : lastC;

            if (Math.abs(c - lastC) <= k) {
                res = Math.max(res, 1 + helper_backtracking(s,k,i + 1, c,len + 1));
            }
        }
        return res;
    }

}
