package Contest;


import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static java.time.LocalTime.now;

/**
 * You are given a string s containing one or more words. Every consecutive pair of words is separated by a single space ' '.
 *
 * A string t is an anagram of string s if the ith word of t is a permutation of the ith word of s.
 *
 * For example, "acb dfe" is an anagram of "abc def", but "def cab" and "adc bef" are not.
 * Return the number of distinct anagrams of s. Since the answer may be very large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "too hot"
 * Output: 18
 * Explanation: Some of the anagrams of the given string are "too hot", "oot hot", "oto toh", "too toh", and "too oht".
 * Example 2:
 *
 * Input: s = "aa"
 * Output: 1
 * Explanation: There is only one anagram possible for the given string.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s consists of lowercase English letters and spaces ' '.
 * There is single space between consecutive words.
 */
public class N2514HCountAnagrams {



    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");

        doRun(210324488, "b okzojaporykbmq tybq zrztwlolvcyumcsq jjuowpp");
        doRun(2520, "ptx cccbhbq");
        doRun(119750400, "ukgqajqsuset kk hm");
        doRun(36, "abc def");
        doRun(18, "abb def");
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, String s) {
        int res = new N2514HCountAnagrams().countAnagrams(s);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    private int modulo = 1_000_000_007;

    //2.Math
    //https://cp-algorithms.com/algebra/module-inverse.html#finding-the-modular-inverse-using-extended-euclidean-algorithm
    //(a / b) % mod != (a % mod) / ( b % mod)
    //(A / B) % mod = A * ( B ^ -1 ) % mod
    //Runtime: 80ms 68%; Memory: 70.5MB 5%
    //Time: O(N); Space: O(N)
    public int countAnagrams(String s) {
        long[] factorial = new long[s.length() + 1];
        long[] inv = new long[s.length() + 1];
        long[] ifac = new long[s.length() + 1];
        factorial[1] =  inv[1] = ifac[1] = 1;
        for (int i = 2; i <= s.length(); i++) {
            factorial[i] = (factorial[i - 1] * i) % modulo;

            inv[i] = modulo - modulo / i * inv[modulo % i] % modulo;
            ifac[i] = ifac[i - 1] * inv[i] % modulo;
        }

        long res = 1;
        int total = 0;
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' '){
                counter[s.charAt(i) - 'a']++;
                total++;
            }

            if (i == s.length() - 1 || s.charAt(i) == ' ') {

                long currRes = factorial[total];
                // ( a! / b! ) % modulo
                for (int j = 0; j < counter.length; j++) {
                    if (counter[j] > 1)
                        currRes = currRes * ifac[counter[j]] % modulo;
                }

                res = res * currRes % modulo;
                counter = new int[26];
                total = 0;
            }
        }
        return (int)res;
    }

    //1.Math BigInteger.modInverse
    // a / b != (a % mod) / (b % mod)
    //Runtime: 40ms 90%; Memory: 60.3MB 9%
    //Time: O(N); Space: O(N)
    public int countAnagrams_1(String s) {

        long[] factorial = new long[s.length() + 1];
        factorial[1] = 1;
        for (int i = 2; i <= s.length(); i++)
            factorial[i] = (factorial[i - 1] * i) % modulo;

        long res = 1;
        int total = 0;
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' '){
                counter[s.charAt(i) - 'a']++;
                total++;
            }

            if (i == s.length() - 1 || s.charAt(i) == ' '){
                res = res * helper(factorial, counter, total) % modulo;
                counter = new int[26];
                total = 0;
            }
        }
        return (int)res;
    }

    //a! / b!
    // BigInteger.modInverse
    private long helper(long[] factorial, int[] counter, int total){
        long res = factorial[total];
        for (int i = 0; i < counter.length; i++) {
            if (counter[i] > 1)
                res = res * BigInteger.valueOf(factorial[counter[i]]).modInverse(BigInteger.valueOf(modulo)).longValue()  % modulo;
        }
        return res;
    }

}
