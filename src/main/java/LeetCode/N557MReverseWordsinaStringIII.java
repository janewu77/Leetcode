package LeetCode;

import java.util.List;

import static java.time.LocalTime.now;

/**
 * Given a string s, reverse the order of characters in each word within a sentence while
 * still preserving whitespace and initial word order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "Let's take LeetCode contest"
 * Output: "s'teL ekat edoCteeL tsetnoc"
 * Example 2:
 *
 * Input: s = "God Ding"
 * Output: "doG gniD"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 5 * 104
 * s contains printable ASCII characters.
 * s does not contain any leading or trailing spaces.
 * There is at least one word in s.
 * All the words in s are separated by a single space.
 */

/**
 * E - [time: 20-
 */
public class N557MReverseWordsinaStringIII {


    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun("s'teL ekat edoCteeL tsetnoc", "Let's take LeetCode contest");
        doRun("doG gniD", "God Ding");

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(String expect, String s) {
        String res = new N557MReverseWordsinaStringIII().reverseWords(s);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 4 ms, faster than 97.75% of Java online submissions for Reverse Words in a String III.
    //Memory Usage: 42.4 MB, less than 98.60% of Java online submissions for Reverse Words in a String III.
    //Time: O(N); Space: O(N)
    //N is the number of characters in input
    public String reverseWords(String s) {
        char[] charlist = s.toCharArray(); //Space: O(N)
        int left = 0, right = 0;
        //Time: O(N)
        while(right < charlist.length) {
            if (right + 1 == charlist.length || charlist[right + 1] == ' ') {
                reverse(charlist, left, right);
                left = right + 2; right++;
            }
            right++;
        }
        return String.valueOf(charlist);
    }

    //Time: O((End - Begin) / 2); Space:O(1)
    private void reverse(char[] charlist, int begin, int end){
        while (begin < end) {
            char c = charlist[begin];
            charlist[begin] = charlist[end];
            charlist[end] = c;
            begin++; end--;
        }
    }
}
