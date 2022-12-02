package LeetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * You are given a string s of even length. Split this string into two halves of equal lengths, and let a be the first half and b be the second half.
 *
 * Two strings are alike if they have the same number of vowels ('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'). Notice that s contains uppercase and lowercase letters.
 *
 * Return true if a and b are alike. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "book"
 * Output: true
 * Explanation: a = "bo" and b = "ok". a has 1 vowel and b has 1 vowel. Therefore, they are alike.
 * Example 2:
 *
 * Input: s = "textbook"
 * Output: false
 * Explanation: a = "text" and b = "book". a has 1 vowel whereas b has 2. Therefore, they are not alike.
 * Notice that the vowel o is counted twice.
 *
 *
 * Constraints:
 *
 * 2 <= s.length <= 1000
 * s.length is even.
 * s consists of uppercase and lowercase letters.
 */

public class N1704EDetermineifStringHalvesAreAlike {

    private Set<Character> set = new HashSet<>(Arrays.asList('a','e','i','o','u','A','E','I','O','U'));

    //3.count
    //Runtime: 6 ms, faster than 70.18% of Java online submissions for Determine if String Halves Are Alike.
    //Memory Usage: 42.3 MB, less than 59.86% of Java online submissions for Determine if String Halves Are Alike.
    //Time: O(N); Space: O(1)
    public boolean halvesAreAlike(String s) {
        return helper_counter(s, 0, s.length() / 2) == helper_counter(s, s.length()/ 2, s.length());
    }
    public int helper_counter(String s, int begin, int end){
        int res = 0;
        for (int i = begin; i < end; i++)
            if (set.contains(s.charAt(i))) res++;
        return res;
    }

    //2.two pointers
    //Runtime: 5 ms, faster than 78.43% of Java online submissions for Determine if String Halves Are Alike.
    //Memory Usage: 40.9 MB, less than 84.63% of Java online submissions for Determine if String Halves Are Alike.
    //Time: O(N); Space: O(1)
    public boolean halvesAreAlike_2(String s) {
        int p1 = -1, p2 = s.length() / 2 - 1;

        while (p1 < s.length() / 2 && p2 < s.length()){
            while (p1 + 1 < s.length() / 2 && !set.contains(s.charAt(p1 + 1))) p1++;
            while (p2 + 1 < s.length() && !set.contains(s.charAt(p2 + 1))) p2++ ;
            p1++; p2++;
        }
        return  p1 >= s.length()/ 2 && p2 >= s.length();
    }


    //1.brute force
    //Runtime: 12 ms, faster than 34.66% of Java online submissions for Determine if String Halves Are Alike.
    //Memory Usage: 42.8 MB, less than 21.71% of Java online submissions for Determine if String Halves Are Alike.
    //Time: O(N); Space: O(1)
    public boolean halvesAreAlike_1(String s) {
        int leftCount = 0;
        for (int i = 0; i < s.length() / 2; i++)
            if (set.contains(s.charAt(i))) leftCount++;

        for (int i = s.length() / 2; i < s.length(); i++)
            if (set.contains(s.charAt(i)) && (--leftCount < 0))  return false;

        return leftCount == 0;
    }

}
