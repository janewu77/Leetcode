package Contest;

/**
 * You are given a string s consisting of only lowercase English letters. In one operation, you can:
 *
 * Delete the entire string s, or
 * Delete the first i letters of s if the first i letters of s are equal to the following i letters in s, for any i in the range 1 <= i <= s.length / 2.
 * For example, if s = "ababc", then in one operation, you could delete the first two letters of s to get "abc", since the first two letters of s and the following two letters of s are both equal to "ab".
 *
 * Return the maximum number of operations needed to delete all of s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcabcdabc"
 * Output: 2
 * Explanation:
 * - Delete the first 3 letters ("abc") since the next 3 letters are equal. Now, s = "abcdabc".
 * - Delete all the letters.
 * We used 2 operations so return 2. It can be proven that 2 is the maximum number of operations needed.
 * Note that in the second operation we cannot delete "abc" again because the next occurrence of "abc" does not happen in the next 3 letters.
 * Example 2:
 *
 * Input: s = "aaabaab"
 * Output: 4
 * Explanation:
 * - Delete the first letter ("a") since the next letter is equal. Now, s = "aabaab".
 * - Delete the first 3 letters ("aab") since the next 3 letters are equal. Now, s = "aab".
 * - Delete the first letter ("a") since the next letter is equal. Now, s = "ab".
 * - Delete all the letters.
 * We used 4 operations so return 4. It can be proven that 4 is the maximum number of operations needed.
 * Example 3:
 *
 * Input: s = "aaaaa"
 * Output: 5
 * Explanation: In each operation, we can delete the first letter of s.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 4000
 * s consists only of lowercase English letters.
 */

/**
 * H - 120+
 */

import java.util.HashMap;

import static java.time.LocalTime.now;

public class N2430HMaximumDeletionsonaString {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun(4, "aaabaab");

        doRun(2, "abcabcdabc");
        doRun(5, "aaaaa");

        doRun(48, "ilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkeilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqilqzuvkebqumabhsdwjesovwhqjvphhigtqubeazichenrkipozlnrjpmxojqmonwdwkeyfptfkwcwzyqglgrqoaiufbhcplnrhvljsjqvvcryxfiifzkvdwtrveehprjrycsiljusynfxtgvzhzczqhbmrfutryzguvpmsrsoudxbinfrdwadboontbjjbzbyhextdynmdwhodebxwgcdrvwtttvhuxzqweahbmfecmoawmwunzlltklrcchkvbpkbpfrupkwmbfmbmldefzichboxigbgusfyirfaypemrjqjaprtblyjdyyycqymoxuuwyvbyqeihlysakceywfjqczosimkpkiqyautfymxsgkiagshmdxgsudaijuxcrkbsrcedirltpjhrdozrghtvinqofwomywcharaabfprotzlmfhoxbzbamqypjwrlivyrytehwsmdxpdrlnxdiqagtfmuqvckqabvlmedptfaerixwsnzzzucildclmximjhpkcvvwadzwsquflhznwoeyhtiydbsivhimkynqydfkdjonicpywvtgtmwisetxsyjpuffvkhmdptagagrivjnsabgqoltdypvirauiwgjlupliioletrpthrslwyofyomldadbtqhoczvwczmfcfazncwrsvkotsognwpdcuounqhuhtstfaenrzwmhatnwqzdtqoscuxgxnsavyhplcgqczfamyyzyukfswpnjbyexesuhgmjhzfdrdmcjxcieiejwwenwnwbfxesanttgwarcykrclbzwmyj");

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, String s) {
        int res = new N2430HMaximumDeletionsonaString().deleteString(s);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 188 ms, faster than 100.00% of Java online submissions for Maximum Deletions on a String.
    //Memory Usage: 42.9 MB, less than 77.78% of Java online submissions for Maximum Deletions on a String.
    //Time : O(N * N); Space : O(N)
    public int deleteString(String s) {
        int[] dp = new int[s.length()];

        for (int i = s.length() - 1; i >= 0; i--){
            dp[i] = 1;
            int[] prefix = calcPrefixLen(s, i);
            for (int j = 2; j < prefix.length; j+=2) {
                if (j == prefix[j] * 2) {
                    dp[i] = Math.max(dp[i], 1 + dp[i + prefix[j]]);
                }
            }
        }
        return dp[0];
    }

    //Time: O(N)
    public int[] calcPrefixLen(String pattern, int offset) {
        int n = pattern.length() - offset;
        int[] res = new int[n + 1];
        res[0] = -1; //res[1] = 0;

        int prefixLen = 0;
        int i = 1;
        while (i < n) {

            if (pattern.charAt(prefixLen + offset) == pattern.charAt(i + offset)) {
                prefixLen++; i++;
                res[i] = prefixLen;
            } else if (prefixLen > 0) {
                prefixLen = res[prefixLen]; // note  that we do not increment i here
            } else {
                i++;
                //res[i] = 0; // 'prefixLen' reached 0, so save that into ar[] and move forward
            }
        }
        return res;
    }

    //Runtime: 396 ms, faster than 88.89% of Java online submissions for Maximum Deletions on a String.
    //Memory Usage: 277.8 MB, less than 33.33% of Java online submissions for Maximum Deletions on a String.
    //DP + bottom up
    //Time: O(N * N); Space: O(N * N)
    public int deleteString_2(String s) {
        int[] dp = new int[s.length()];

        //lsp[i][j]：第i位和第j位起相同字串的长度
        //lcs[i][j] means the length of the longest common substring.
        int[][] lsp = new int[s.length() + 1][s.length() + 1];

        for (int i = s.length() - 1; i >= 0; i--){
            dp[i] = 1;
            for (int j = i + 1; j < s.length(); j++){

                if (s.charAt(i) == s.charAt(j))
                    lsp[i][j] = lsp[i + 1][j + 1] + 1; //在上一个的基础上比较的。

                //lsp[i][j] >= j - i 意味着二段相邻了。
                //比如  abab
                if (lsp[i][j] >= j - i)
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
            }
        }
        return dp[0];
    }


    //TLE
    public int deleteString_1(String s) {
        return helper(s,  0) + 1;
    }

    HashMap<Integer, Integer> map = new HashMap<>();
    public int helper(String s, int begin) {

        if (begin >= s.length()) return 0;
        if (map.containsKey(begin)) return map.get(begin);

        char c = s.charAt(begin);
        int idx = s.lastIndexOf(c);//, s.length());

        int res = 0;
        while (idx > begin){
            if (s.indexOf(s.substring(begin, idx), idx) == idx){
                int count = 1 + helper(s, idx);
                res = Math.max(res, count);
            }
            idx = s.lastIndexOf(c, idx-1);
        }
        map.put(begin, res);
        return res;
    }


}
