package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.LocalTime.now;

/**
 * Given an integer n, your task is to count how many strings of length n can be formed under the following rules:
 *
 * Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
 * Each vowel 'a' may only be followed by an 'e'.
 * Each vowel 'e' may only be followed by an 'a' or an 'i'.
 * Each vowel 'i' may not be followed by another 'i'.
 * Each vowel 'o' may only be followed by an 'i' or a 'u'.
 * Each vowel 'u' may only be followed by an 'a'.
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: 5
 * Explanation: All possible strings are: "a", "e", "i" , "o" and "u".
 * Example 2:
 *
 * Input: n = 2
 * Output: 10
 * Explanation: All possible strings are: "ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" and "ua".
 * Example 3:
 *
 * Input: n = 5
 * Output: 68
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2 * 10^4
 *
 */

/**
 * H - [time: 120-
 *
 */
public class N1220HCountVowelsPermutation {

    public static void main(String[] args) {

        System.out.println(now());

        doRun(5,1);
        doRun(10,2);
        doRun(68,5);
        doRun(18208803,144);
        System.out.println(now());
        System.out.println("==================");

    }
    static private void doRun(int expect, int n) {
        int res = new N1220HCountVowelsPermutation().countVowelPermutation(n);
//        String res = comm.toString(res1);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
        //System.out.println("==================");
    }


    //Runtime: 15 ms, faster than 88.24% of Java online submissions for Count Vowels Permutation.
    //Memory Usage: 48.7 MB, less than 52.94% of Java online submissions for Count Vowels Permutation.
    //Time: O(N); Space:O(5)
    public int countVowelPermutation(int n) {
        int MODULO = 1000000007;
        //a e i o u
        int[] dp = new int[]{1,1,1,1,1};
        for (int i = 2; i <= n; i++){
            int[] dp2 = new int[5];
            //ea ia ua
            dp2[0] = ((dp[1] + dp[2]) % MODULO + dp[4]) % MODULO;
            //ae ie
            dp2[1] = (dp[0] + dp[2]) % MODULO;
            //ei oi
            dp2[2] = (dp[1] + dp[3]) % MODULO;
            //io
            dp2[3] = dp[2];
            //iu ou
            dp2[4] = (dp[2] + dp[3]) % MODULO;
            dp = dp2;
        }

        int res = 0;
        for (int c : dp) res = (res + c) % MODULO;
        return res;
    }


    ////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////
    //Time Limit Exceed
    public int countVowelPermutation_1(int n) {

        List<Character> list = new ArrayList<>();
        int res = 0;

        for (char c : Vowels){
            list.add(c);
            res += helper_backtrack(n-1, c);
            res = res % 1000000007;
            list.remove(list.size()-1);
        }
        return res;
    }
    final private char[] Vowels = new char[]{'a', 'e', 'i', 'o', 'u'};
    final private  Map<Character, char[]> VowelMap = new HashMap<Character, char[]>(){{
        put('a', new char[]{'e'});
        put('e', new char[]{'a','i'});
        put('i', new char[]{'a', 'e', 'o', 'u'});
        put('o', new char[]{'i','u'});
        put('u', new char[]{'a'});
    }};
    private int helper_backtrack(int n , char currChar){
        int res = 0;
        if (n == 0) return 1;

        char[] vowels = VowelMap.get(currChar);
        for (char c : vowels){
            res += helper_backtrack(n - 1, c);
            res = res % 1000000007;
        }
        return res;
    }

}
