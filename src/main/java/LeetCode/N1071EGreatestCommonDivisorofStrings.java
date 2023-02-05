package LeetCode;


/**
 * For two strings s and t, we say "t divides s" if and only if s = t + ... + t (i.e., t is concatenated with itself one or more times).
 *
 * Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.
 *
 *
 *
 * Example 1:
 *
 * Input: str1 = "ABCABC", str2 = "ABC"
 * Output: "ABC"
 * Example 2:
 *
 * Input: str1 = "ABABAB", str2 = "ABAB"
 * Output: "AB"
 * Example 3:
 *
 * Input: str1 = "LEET", str2 = "CODE"
 * Output: ""
 *
 *
 * Constraints:
 *
 * 1 <= str1.length, str2.length <= 1000
 * str1 and str2 consist of English uppercase letters.
 */
public class N1071EGreatestCommonDivisorofStrings {

    //1.recursion
    //Runtime: 0ms 100%; Memory: 40.4MB 100%
    public String gcdOfStrings(String str1, String str2) {
        if (str1.equals(str2))
            return str1;

        String strMin = str1, strMax = str2;
        if (str1.length() > str2.length()) {
            strMin = str2; strMax = str1;
        }
        if (strMax.indexOf(strMin) != 0) return "";
        return gcdOfStrings(strMin, strMax.substring(strMin.length()));
    }
}
