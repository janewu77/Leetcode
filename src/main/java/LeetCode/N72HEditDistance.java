package LeetCode;


import java.util.Arrays;

import static java.time.LocalTime.now;

/**
 * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
 *
 * You have the following three operations permitted on a word:
 *
 * Insert a character
 * Delete a character
 * Replace a character
 *
 *
 * Example 1:
 *
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * Example 2:
 *
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 *
 *
 * Constraints:
 *
 * 0 <= word1.length, word2.length <= 500
 * word1 and word2 consist of lowercase English letters.
 */
public class N72HEditDistance {

    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");

        doRun(3, "horse", "ros");
        doRun(5, "intention", "execution");
        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, String word1, String word2) {
        int res = new N72HEditDistance().minDistance(word1, word2);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //1.helper_backtracking
    //Runtime: 6ms 67%; Space: 42.9MB 22%
    //Time: O(M * N); Space: O(M * N)
    public int minDistance(String word1, String word2) {
        int[][] memo = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < memo.length; i++)
            Arrays.fill(memo[i], -1);
        return helper_backtracking(word1, word2, memo, word1.length(), word2.length());
    }


    private int helper_backtracking(String word1, String word2, int[][] memo, int i, int j){
        if (i == 0) return j;
        if (j == 0) return i;

        if (memo[i][j] != -1)
            return memo[i][j];

        if (word1.charAt(i - 1) == word2.charAt(j - 1))
            return helper_backtracking(word1, word2, memo, i - 1 ,  j - 1);

        int insert = helper_backtracking(word1, word2, memo, i, j - 1);
        int delete = helper_backtracking(word1, word2, memo, i - 1, j);
        int replace = helper_backtracking(word1, word2, memo, i - 1, j - 1);
        return memo[i][j] = 1 + Math.min(replace, Math.min(insert, delete));
    }

}
