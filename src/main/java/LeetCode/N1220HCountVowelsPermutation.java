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
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //2.DP top-down
    //Runtime: 89ms 18%; Memory: 57.3mb 16.2%
    //Time: O(N); Space: O(N);
    int MODULO = 1000000007;
    public int countVowelPermutation(int n) {
        Map<Integer, Integer> memo = new HashMap<>();
        //a e i o u
        int res = 0;
        for (int i = 0; i < 5; i++)
            res = (res + helper_dp(n, i, memo)) % MODULO;
        return res;
    }

    private int helper_dp(int n, int c, Map<Integer, Integer> memo){
        if (n == 1) return 1;

        int key = n * 10 + c;
        if (memo.containsKey(key)) return memo.get(key);

        int res = 0;
        switch (c){
            case 0:
                //ae
                res = helper_dp(n - 1, 1, memo);
                break;
            case 1:
                //ea ei
                res = (helper_dp(n - 1, 0, memo) + helper_dp(n - 1, 2, memo)) % MODULO;
                break;
            case 2:
                //ia ie io iu
                long tmp = helper_dp(n - 1, 0, memo);
                tmp += helper_dp(n - 1, 1, memo);
                tmp += helper_dp(n - 1, 3, memo);
                tmp += helper_dp(n - 1, 4, memo);
                res = (int)(tmp % MODULO);
                break;
            case 3:
                // oi ou
                res = (helper_dp(n - 1, 2, memo) + helper_dp(n - 1, 4, memo)) % MODULO;
                break;
            default://4
                //  ua
                res =helper_dp(n - 1, 0, memo);

        }
        memo.put(key, res);
        return res;
    }

    //1.DP bottom-up
    //Runtime: 9ms 99%; Memory: 41.7mb 73%
    //Time: O(N); Space:O(1)
    public int countVowelPermutation_1(int n) {
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
    public int countVowelPermutation_tle(int n) {

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
