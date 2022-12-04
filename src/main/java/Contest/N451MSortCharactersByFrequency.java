package Contest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.time.LocalTime.now;

/**
 *
 * Given a string s, sort it in decreasing order based on the frequency of the characters. The frequency of a character is the number of times it appears in the string.
 *
 * Return the sorted string. If there are multiple answers, return any of them.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "tree"
 * Output: "eert"
 * Explanation: 'e' appears twice while 'r' and 't' both appear once.
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 * Example 2:
 *
 * Input: s = "cccaaa"
 * Output: "aaaccc"
 * Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.
 * Note that "cacaca" is incorrect, as the same characters must be together.
 * Example 3:
 *
 * Input: s = "Aabb"
 * Output: "bbAa"
 * Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
 * Note that 'A' and 'a' are treated as two different characters.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 5 * 105
 * s consists of uppercase and lowercase English letters and digits.
 */
public class N451MSortCharactersByFrequency {

    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");

        System.out.println((int)'0');
//        System.out.println((int)'9');
//        System.out.println((int)'A');
//        System.out.println((int)'Z');
//        System.out.println((int)'a');
        System.out.println((int)'z');

        doRun("bbAa", "Aabb");
        doRun("aaaccc", "cccaaa");
        doRun("eert", "tree");

        doRun("", "2a554442f544asfasssffffasss");
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(String expect, String s) {
        String res = new N451MSortCharactersByFrequency().frequencySort(s);
        System.out.println("["+(expect.equals(res) )+"]expect:" + expect + " res:" + res);
    }

    //1.count & sort
    //Runtime: 5 ms, faster than 99.45% of Java online submissions for Sort Characters By Frequency.
    //Memory Usage: 42.3 MB, less than 99.78% of Java online submissions for Sort Characters By Frequency.
    //Time: O(N + 74 + 74 * log(74) + N); Space: O(log(74) + N)
    //Time: O(N); Space: O(N)
    public String frequencySort(String s) {
        int[] counter = new int['z' - '0' + 1];
        for (int i = 0; i < s.length(); i++)
            counter[s.charAt(i) - '0']++;

        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < counter.length; i++)
            list.add(new int[]{i, counter[i]});

        Collections.sort(list, (a, b) -> b[1] - a[1]);

        StringBuilder sb = new StringBuilder();
        for(int[] element : list){
            char c = (char)('0' + element[0]);
            for (int i = 0; i < element[1]; i++) sb.append(c);
        }
        return sb.toString();
    }
}
