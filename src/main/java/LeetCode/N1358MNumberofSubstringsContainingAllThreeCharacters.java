package LeetCode;

import static java.time.LocalTime.now;

/**
 * Given a string s consisting only of characters a, b and c.
 *
 * Return the number of substrings containing at least one occurrence of all these characters a, b and c.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcabc"
 * Output: 10
 * Explanation: The substrings containing at least one occurrence of the
 * characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab",
 * "cabc" and "abc" (again).
 * Example 2:
 *
 * Input: s = "aaacb"
 * Output: 3
 * Explanation: The substrings containing at least one occurrence of the characters a, b
 * and c are "aaacb", "aacb" and "acb".
 * Example 3:
 *
 * Input: s = "abc"
 * Output: 1
 *
 *
 * Constraints:
 *
 * 3 <= s.length <= 5 x 10^4
 * s only consists of a, b or c characters.
 */

/**
 * M - [time: 15-
 * slide window
 */
public class N1358MNumberofSubstringsContainingAllThreeCharacters {

    public static void main(String[] args){
        int[] data;

        System.out.println(now());
        doRun_demo(1, "abc");
        doRun_demo(10, "abcabc");
        doRun_demo(3, "aaacb");

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun_demo(int expect, String s) {
        int res = new N1358MNumberofSubstringsContainingAllThreeCharacters().numberOfSubstrings(s);
//        String res = comm.toString(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //from lee215
    public int numberOfSubstrings_3(String s) {
        int last[] = {-1, -1, -1}, res = 0, n = s.length();
        for (int i = 0; i < n; ++i) {
            last[s.charAt(i) - 'a'] = i;
            res += 1 + Math.min(last[0], Math.min(last[1], last[2]));
        }
        return res;
    }

    //from lee215
    //Runtime: 13 ms, faster than 69.70% of Java online submissions for Number of Substrings Containing All Three Characters.
    //Memory Usage: 45.5 MB, less than 46.67% of Java online submissions for Number of Substrings Containing All Three Characters.
    public int numberOfSubstrings_2(String s) {
        int res = 0;
        int left = 0, right = 0;
        int[] counter = new int[3];

        for (right = 0; right<s.length(); right++){
            counter[s.charAt(right)-'a']++;

            while (counter[0] > 0 && counter[1] > 0 && counter[2] > 0){
                //res += s.length() - right;
                counter[s.charAt(left++)-'a']--;
            }
            res += left;
        }
        return res;
    }

    //Runtime: 13 ms, faster than 69.70% of Java online submissions for Number of Substrings Containing All Three Characters.
    //Memory Usage: 45.8 MB, less than 41.33% of Java online submissions for Number of Substrings Containing All Three Characters.
    //slide window
    //Time: O(N); Space: O(1)
    public int numberOfSubstrings(String s) {
        int res = 0;
        int left = 0, right = 0;
        int[] counter = new int[3];

        while (left < s.length()){
            boolean isFit = counter[0] >=1 && counter[1] >=1 && counter[2] >=1;

            if (!isFit && right < s.length()){
                counter[s.charAt(right++)-'a']++;
            }else{
                if (isFit) res += s.length() - right + 1;
                counter[s.charAt(left++)-'a']--;
            }
        }
        return res;
    }


}
