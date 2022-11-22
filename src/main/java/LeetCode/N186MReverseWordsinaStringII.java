package LeetCode;


/**
 * Given a character array s, reverse the order of the words.
 *
 * A word is defined as a sequence of non-space characters. The words in s will be separated by a single space.
 *
 * Your code must solve the problem in-place, i.e. without allocating extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: s = ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
 * Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
 * Example 2:
 *
 * Input: s = ["a"]
 * Output: ["a"]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s[i] is an English letter (uppercase or lowercase), digit, or space ' '.
 * There is at least one word in s.
 * s does not contain leading or trailing spaces.
 * All the words in s are guaranteed to be separated by a single space.
 */

/**
 * M -  (5m + 5m)
 *  - 二层翻转
 *
 */
public class N186MReverseWordsinaStringII {

    public void reverseWords(char[] s) {
        reverseString(s,0, s.length-1);

        int r = 0;
        int lastI = 0;
        while(r < s.length){
            //找到单词结束
            while (r < s.length && s[r] != ' ') r++;

            //反转单词
            reverseString(s,lastI, r-1);
            r++;
            lastI = r;
        }
    }


    private void reverseString(char[] s, int left , int right) {
        while (left < right){
            char t = s[left];
            s[left++] = s[right];
            s[right--] = t;
        }
    }
}
