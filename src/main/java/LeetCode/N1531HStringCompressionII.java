package LeetCode;


import java.util.*;

import static java.time.LocalTime.now;

/**
 * Run-length encoding is a string compression method that works by replacing consecutive identical characters (repeated 2 or more times) with the concatenation of the character and the number marking the count of the characters (length of the run). For example, to compress the string "aabccc" we replace "aa" by "a2" and replace "ccc" by "c3". Thus the compressed string becomes "a2bc3".
 *
 * Notice that in this problem, we are not adding '1' after single characters.
 *
 * Given a string s and an integer k. You need to delete at most k characters from s such that the run-length encoded version of s has minimum length.
 *
 * Find the minimum length of the run-length encoded version of s after deleting at most k characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aaabcccd", k = 2
 * Output: 4
 * Explanation: Compressing s without deleting anything will give us "a3bc3d" of length 6. Deleting any of the characters 'a' or 'c' would at most decrease the length of the compressed string to 5, for instance delete 2 'a' then we will have s = "abcccd" which compressed is abc3d. Therefore, the optimal way is to delete 'b' and 'd', then the compressed version of s will be "a3c3" of length 4.
 * Example 2:
 *
 * Input: s = "aabbaa", k = 2
 * Output: 2
 * Explanation: If we delete both 'b' characters, the resulting compressed string would be "a4" of length 2.
 * Example 3:
 *
 * Input: s = "aaaaaaaaaaa", k = 0
 * Output: 3
 * Explanation: Since k is zero, we cannot delete anything. The compressed string is "a11" of length 3.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * 0 <= k <= s.length
 * s contains only lowercase English letters.
 */

/**
 * H - [time: 120+
 */
public class N1531HStringCompressionII {


    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun(3, "aabbaabaaabbabaabbbaabbabaaaababbbbbbbbaaabbabbbbbaaabbabbbaaaabbabaabbaabaaabaaabaabbbba", 52);

        doRun(1, "abc", 2);
        doRun(3, "aaaaaaaaaaa", 0);
        doRun(4, "aaabcccd", 2);
        doRun(2, "aabbaa", 2);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, String s, int k) {
        int res = new N1531HStringCompressionII()
                .getLengthOfOptimalCompression(s, k);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //3.DP
    //Runtime: 30 ms, faster than 92.53% of Java online submissions for String Compression II.
    //Memory Usage: 42.8 MB, less than 71.84% of Java online submissions for String Compression II.
    //Time: O(N * K * K); Space: O(N * K)
    public int getLengthOfOptimalCompression(String s, int k) {
        dp3 = new int[s.length()][k + 1];
        for (int[] x : dp3) Arrays.fill(x, - 1);
        return helper_dp3(s, 0, k);
    }

    int[][] dp3;
    private int helper_dp3(String s, int idx, int k){
        if (k < 0) return s.length();
        if (idx + k >= s.length()) return 0;

        //cache
        if (dp3[idx][k] != -1) return  dp3[idx][k];

        //delete it
        int res = helper_dp3(s, idx + 1, k - 1);

        //keep it
        int len = 0, sameCount = 0, diff = 0;
        for (int j = idx; j < s.length() && diff <= k; j++) {
            if (s.charAt(j) == s.charAt(idx)) {
                sameCount++;
                if (sameCount <= 2 || sameCount == 10 || sameCount == 100) len++;
            }
            else diff++;

            res = Math.min(res, len + helper_dp3(s,j + 1, k - diff));
        }
        return dp3[idx][k] = res;
    }


    //2.dp (same as 1.)
    //Memory Limit Exceeded
    public int getLengthOfOptimalCompression_2(String s, int k) {
        dp = new int[s.length()][27][s.length()][k + 1];
        Arrays.stream(dp)
                .forEach(a -> Arrays.stream(a)
                .forEach(b -> Arrays.stream(b)
                .forEach(c -> Arrays.fill(c, Integer.MAX_VALUE))));

        return helper_dp2(s, 0, (char)('a'+26),0, k);
    }
    int[][][][] dp;
    private int helper_dp2(String s, int idx, char lastChar, int lastCount, int k){
        if (k < 0) return s.length();
        if (idx + k >= s.length()) return 0;

        if (dp[idx][lastChar-'a'][lastCount][k] != Integer.MAX_VALUE) return dp[idx][lastChar-'a'][lastCount][k];

        //delete it
        int afterDelete = (k < 1) ? Integer.MAX_VALUE : helper_dp2(s, idx + 1, lastChar, lastCount, k - 1);

        //keep it
        char currChar = s.charAt(idx);
        int expandLen = 1, currCount = 1;
        if (lastChar == currChar) {
            expandLen = (lastCount == 1 || lastCount == 9 || lastCount == 99) ? 1 :0;
            currCount = lastCount + 1;
        }
        int afterKeep = helper_dp2(s, idx + 1, currChar, currCount, k) + expandLen;
        return dp[idx][lastChar-'a'][lastCount][k] = Math.min(afterDelete, afterKeep);
    }


    //1.dp
    //Runtime: 236 ms, faster than 13.79% of Java online submissions for String Compression II.
    //Memory Usage: 50.4 MB, less than 17.24% of Java online submissions for String Compression II.
    //DP + recursion + memo
    //Time: O(N * N * K); Space: O(N * N * K)
    public int getLengthOfOptimalCompression_1(String s, int k) {
        memo = new HashMap<>();
        set9 = new HashSet<>();
        set9.addAll(Arrays.asList(1,9,99));
        return helper_dp1(s, 0, '#',0, k);
    }

    Set<Integer> set9;
    Map<Integer, Integer> memo;
    private int helper_dp1(String s, int idx, char lastChar, int lastCount, int k){
        if (idx + k >= s.length()) return 0;

        int key = idx * 10_000_000 + lastCount * 100_000 + k * 100 + lastChar - 'a';
        if (memo.containsKey(key)) return memo.get(key);

        //delete it
        int afterDelete = (k < 1) ? Integer.MAX_VALUE : helper_dp1(s, idx + 1, lastChar, lastCount, k - 1);

        //keep it
        char currChar = s.charAt(idx);
        int expandLen = 1, currCount = 1;
        if (lastChar == currChar) {
            expandLen = set9.contains(lastCount) ? 1 : 0;
            currCount = lastCount + 1;
        }
        int afterKeep = helper_dp1(s, idx + 1, currChar, currCount, k) + expandLen;

        memo.put(key, Math.min(afterDelete, afterKeep));
        return memo.get(key);
    }
}
