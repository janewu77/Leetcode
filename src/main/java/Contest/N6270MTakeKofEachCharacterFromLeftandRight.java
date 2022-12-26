package Contest;

import javafx.util.Pair;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.time.LocalTime.now;

/**
 *
 * You are given a string s consisting of the characters 'a', 'b', and 'c' and a non-negative integer k. Each minute, you may take either the leftmost character of s, or the rightmost character of s.
 *
 * Return the minimum number of minutes needed for you to take at least k of each character, or return -1 if it is not possible to take k of each character.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aabaaaacaabc", k = 2
 * Output: 8
 * Explanation:
 * Take three characters from the left of s. You now have two 'a' characters, and one 'b' character.
 * Take five characters from the right of s. You now have four 'a' characters, two 'b' characters, and two 'c' characters.
 * A total of 3 + 5 = 8 minutes is needed.
 * It can be proven that 8 is the minimum number of minutes needed.
 * Example 2:
 *
 * Input: s = "a", k = 1
 * Output: -1
 * Explanation: It is not possible to take one 'b' or 'c' so return -1.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s consists of only the letters 'a', 'b', and 'c'.
 * 0 <= k <= s.length
 */

//2516. Take K of Each Character From Left and Right
public class N6270MTakeKofEachCharacterFromLeftandRight {



    static public void main(String... args) throws IOException{
        System.out.println(now());
        System.out.println("==================");

        doRun(3, "cbbac", 1);
        doRun(8, "aabaaaacaabc", 2);


        doRun(6, "abcabc", 2);

        doRun(3, "acba", 1);
        doRun(0, "a", 0);
        doRun(-1, "abcbc", 2);
        doRun(-1, "a", 1);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, String s, int k) {
        int res = new N6270MTakeKofEachCharacterFromLeftandRight().takeCharacters(s, k);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //3.slide window
    //Runtime: 8ms 100%; Memory: 43.5MB 75%
    //Time: O(N); Space: O(1)
    public int takeCharacters(String s, int k) {
        if (k == 0) return 0;

        int res = s.length();
        int[] sum = new int[3];
        for (int i = 0; i < s.length(); i++) {
            sum[s.charAt(i) - 'a']++;
            if (sum[0] >= k && sum[1] >= k && sum[2] >= k) {
                res = i + 1; break;
            }
        }
        if (sum[0] < k || sum[1] < k || sum[2] < k) return -1;
        if (res == k * 3) return res;

        //slide window
        int left = res - 1, right = s.length() - 1;
        while (left >= 0) {
            sum[s.charAt(left--) - 'a']--;
            sum[s.charAt(right--) - 'a']++;
            while (left >= 0 && sum[0] >= k && sum[1] >= k && sum[2] >= k) {
                res = Math.min(res, left + 1 + s.length() - right - 1);
                sum[s.charAt(left--) - 'a']--;
            }
        }

        if (sum[0] >= k && sum[1] >= k && sum[2] >= k)
            res = Math.min(res, left + 1 + s.length() - right - 1);

        return res;
    }

    //2.prefix-sum
    //Runtime: 500ms 25%; Memory: 61.9MB 50%
    //Time: O(N * N); Space: O(N)
    public int takeCharacters_2(String s, int k) {
        if (k == 0) return 0;

        int res = s.length();

        int[][] counterLeft = new int[s.length()][3];
        int[][] counterRight = new int[s.length()][3];
        int[] sum = new int[3];
        for (int i = 0; i < s.length(); i++) {
            sum[ s.charAt(i) - 'a' ]++;
            counterLeft[i][0] = sum[0];
            counterLeft[i][1] = sum[1];
            counterLeft[i][2] = sum[2];
            if (sum[0] >= k && sum[1] >= k && sum[2] >= k)
                res = Math.min(res, i + 1);
        }
        if (sum[0] < k || sum[1] < k || sum[2] < k) return -1;

        sum = new int[3];
        for (int i = s.length() - 1; i >= 0; i--) {
            sum[ s.charAt(i) - 'a' ]++;
            counterRight[i][0] = sum[0];
            counterRight[i][1] = sum[1];
            counterRight[i][2] = sum[2];

            if (sum[0] >= k && sum[1] >= k && sum[2] >= k)
                res = Math.min(res, s.length() - i);
        }
        if (res == k * 3) return res;

        for (int i = k * 3; i < s.length(); i++) {
            for (int j = 1; j < i ; j++) {
                int left = i - j - 1;
                int right = s.length() - j;
                sum = new int[3];
                for (int l = 0; l < 3; l++)
                    sum[l] = counterLeft[left][l] + counterRight[right][l];

                if (sum[0] >= k && sum[1] >= k && sum[2] >= k)
                    return Math.min(res, i);
            }
        }
        return res;
    }


    //1.Backtracking
    //TLE
    public int takeCharacters_1(String s, int k) {
        if (s.length() < k * 3) return -1;
        if (k == 0) return 0;

        int[] counter = new int[]{k, k, k};

        int res = help(s, 0, s.length() - 1, counter, new HashMap<>());
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int[] zeroCounter = new int[3];
    private int minSteps = Integer.MAX_VALUE;
    public int help(String s, int left, int right, int[] counter, Map<Pair<Integer, Integer>, Integer> memo){
        int steps =  left + 1 + s.length() - right - 2;

        if (Arrays.equals(counter, zeroCounter))
            return minSteps = Math.min(minSteps, steps);

        if (steps + 1 >= minSteps || left > right)
            return Integer.MAX_VALUE;

        Pair<Integer, Integer> key = new Pair<>(left, right);
        if (memo.containsKey(key)) return memo.get(key);

        int res = Integer.MAX_VALUE;
        int tmp = counter[s.charAt(left) - 'a'];
        if (counter[s.charAt(left) - 'a'] > 0) counter[s.charAt(left) - 'a']--;
        res = Math.min(res, help(s, left + 1, right, counter, memo));
        counter[s.charAt(left) - 'a'] = tmp;

        tmp = counter[s.charAt(right) - 'a'];
        if (counter[s.charAt(right) - 'a'] > 0)
            counter[s.charAt(right) - 'a']--;
        res = Math.min(res, help(s, left, right - 1, counter, memo));
        counter[s.charAt(right) - 'a'] = tmp;

        memo.put(key, res);
        return res;
    }



}


