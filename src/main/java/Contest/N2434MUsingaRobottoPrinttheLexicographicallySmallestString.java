package Contest;



import java.util.ArrayDeque;
import java.util.Deque;

import static java.time.LocalTime.now;


/**
 * You are given a string s and a robot that currently holds an empty string t. Apply one of the following operations until s and t are both empty:
 *
 * Remove the first character of a string s and give it to the robot. The robot will append this character to the string t.
 * Remove the last character of a string t and give it to the robot. The robot will write this character on paper.
 * Return the lexicographically smallest string that can be written on the paper.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "zza"
 * Output: "azz"
 * Explanation: Let p denote the written string.
 * Initially p="", s="zza", t="".
 * Perform first operation three times p="", s="", t="zza".
 * Perform second operation three times p="azz", s="", t="".
 * Example 2:
 *
 * Input: s = "bac"
 * Output: "abc"
 * Explanation: Let p denote the written string.
 * Perform first operation twice p="", s="c", t="ba".
 * Perform second operation twice p="ab", s="c", t="".
 * Perform first operation p="ab", s="", t="c".
 * Perform second operation p="abc", s="", t="".
 * Example 3:
 *
 * Input: s = "bdda"
 * Output: "addb"
 * Explanation: Let p denote the written string.
 * Initially p="", s="bdda", t="".
 * Perform first operation four times p="", s="", t="bdda".
 * Perform second operation four times p="addb", s="", t="".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s consists of only English lowercase letters.
 */

/**
 * 未参加
 * M - [time: 60
 */
public class N2434MUsingaRobottoPrinttheLexicographicallySmallestString {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun("fnohopzv", "vzhofnpo");
        doRun("eekstrlpmomwzqummz", "mmuqezwmomeplrtskz");
        doRun("biknrqw", "ibwqrkn");

        doRun("abc", "bac");

        doRun("azz", "zza");
        doRun("addb", "bdda");
        doRun("bdd", "bdd");

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(String expect, String s) {
        String res = new N2434MUsingaRobottoPrinttheLexicographicallySmallestString().robotWithString(s);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 245 ms, faster than 100.00% of Java online submissions for Using a Robot to Print the Lexicographically Smallest String.
    //Memory Usage: 118.7 MB, less than 100.00% of Java online submissions for Using a Robot to Print the Lexicographically Smallest String.
    //Time: O(N + N) Space:O(26 + N + N)
    //Time: O(N) Space:O(N)
    public String robotWithString(String s) {
        int[] counter = new int[26];
        for (char c: s.toCharArray()) counter[c -'a']++;

        int idx = 0;
        while (counter[idx] == 0) idx++;

        StringBuffer sb = new StringBuffer();
        Deque<Character> stack = new ArrayDeque<>();

        for (char c: s.toCharArray()) {
            while (!stack.isEmpty() && stack.peek() - 'a' <= idx)
                sb.append(stack.pop());

            if (c - 'a' <= idx) sb.append(c);
            else stack.push(c);

            counter[c - 'a']--;
            while (idx < 26 && counter[idx] == 0) idx++;
        }
        while (!stack.isEmpty()) sb.append(stack.pop());
        return sb.toString();
    }


}
