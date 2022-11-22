package Contest;

import java.util.*;

import static java.time.LocalTime.now;


/**
 * You are given an array of equal-length strings words. Assume that the length of each string is n.
 *
 * Each string words[i] can be converted into a difference integer array difference[i] of length n - 1 where difference[i][j] = words[i][j+1] - words[i][j] where 0 <= j <= n - 2. Note that the difference between two letters is the difference between their positions in the alphabet i.e. the position of 'a' is 0, 'b' is 1, and 'z' is 25.
 *
 * For example, for the string "acb", the difference integer array is [2 - 0, 1 - 2] = [2, -1].
 * All the strings in words have the same difference integer array, except one. You should find that string.
 *
 * Return the string in words that has different difference integer array.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["adc","wzy","abc"]
 * Output: "abc"
 * Explanation:
 * - The difference integer array of "adc" is [3 - 0, 2 - 3] = [3, -1].
 * - The difference integer array of "wzy" is [25 - 22, 24 - 25]= [3, -1].
 * - The difference integer array of "abc" is [1 - 0, 2 - 1] = [1, 1].
 * The odd array out is [1, 1], so we return the corresponding string, "abc".
 * Example 2:
 *
 * Input: words = ["aaa","bob","ccc","ddd"]
 * Output: "bob"
 * Explanation: All the integer arrays are [0, 0] except for "bob", which corresponds to [13, -13].
 *
 *
 * Constraints:
 *
 * 3 <= words.length <= 100
 * n == words[i].length
 * 2 <= n <= 20
 * words[i] consists of lowercase English letters.
 */

/**
 * E - [Time: 30-
 */
//2451. Odd String Difference
public class N6225EOddStringDifference {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        //doRun(true, "abaccb", new int[]{1,2,3});

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, String beginWord) {
//        int res = new N1().ladderLength(beginWord);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //
    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Odd String Difference.
    //Memory Usage: 39.5 MB, less than 100.00% of Java online submissions for Odd String Difference.
    //Time: O(N * K); Space:O(K)
    //let N be the number of words in input list; K be the number of character in word.
    public String oddString(String[] words) {

        //Time: O(K); Space:O(K)
        int count0 = 1, count1 = 0;
        int[] counter0 = convert(words[0]);

        String oddWord = "";

        //Time: O(N * K); Space:O(K)
        for (int k = 1; k < words.length; k++) {
            if (Arrays.equals(convert(words[k]), counter0)) {
                if (count1 > 1) return words[0];
                count0++;
            } else {
                if (count0 > 1) return words[k];
                count1++;
                oddWord = words[k];
            }
        }
        return count0 == 1 ? words[0] : oddWord;
    }

    private int[] convert(String word){
        int[] data = new int[word.length() - 1];
        for (int i = 1; i < word.length(); i++)
            data[i - 1] = word.charAt(i) - word.charAt(i - 1);
        return data;
    }


}
