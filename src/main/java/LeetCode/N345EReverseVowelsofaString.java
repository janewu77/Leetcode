package LeetCode;

import java.util.HashSet;
import java.util.Set;

import static java.time.LocalTime.now;


/**
 * Given a string s, reverse only all the vowels in the string and return it.
 *
 * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "hello"
 * Output: "holle"
 * Example 2:
 *
 * Input: s = "leetcode"
 * Output: "leotcede"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 3 * 105
 * s consist of printable ASCII characters.
 */

/**
 * E -[time: 10-
 */
public class N345EReverseVowelsofaString {


    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun("a.b,.", "a.b,.");
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(String expect,String s) {
        String res = new N345EReverseVowelsofaString().reverseVowels(s);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    //1.two pointers
    //Runtime: 4 ms, faster than 94.43% of Java online submissions for Reverse Vowels of a String.
    //Memory Usage: 42.5 MB, less than 91.78% of Java online submissions for Reverse Vowels of a String.
    //Time: O(N); Space: O(N)
    public String reverseVowels(String s) {
        char[] charList = s.toCharArray();
        int left = 0, right = charList.length - 1;
        while(left < right){
            while (left < charList.length && !set.contains(charList[left])) left++;
            while (right >= 0 && !set.contains(charList[right])) right--;
            if (left < right) {
                char tmp = charList[left];
                charList[left] = charList[right];
                charList[right] = tmp;
                left++; right--;
            }
        }

        return String.valueOf(charList);
    }
    final static Set<Character> set = new HashSet<Character>(){{
        add('a'); add('e'); add('i'); add('o'); add('u');
        add('A'); add('E'); add('I'); add('O'); add('U');
    }};
}
