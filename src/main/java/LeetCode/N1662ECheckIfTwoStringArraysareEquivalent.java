package LeetCode;


import static java.time.LocalTime.now;

/**
 * Given two string arrays word1 and word2, return true if the two arrays represent the same string, and false otherwise.
 *
 * A string is represented by an array if the array elements concatenated in order forms the string.
 *
 *
 *
 * Example 1:
 *
 * Input: word1 = ["ab", "c"], word2 = ["a", "bc"]
 * Output: true
 * Explanation:
 * word1 represents string "ab" + "c" -> "abc"
 * word2 represents string "a" + "bc" -> "abc"
 * The strings are the same, so return true.
 * Example 2:
 *
 * Input: word1 = ["a", "cb"], word2 = ["ab", "c"]
 * Output: false
 * Example 3:
 *
 * Input: word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= word1.length, word2.length <= 103
 * 1 <= word1[i].length, word2[i].length <= 103
 * 1 <= sum(word1[i].length), sum(word2[i].length) <= 103
 * word1[i] and word2[i] consist of lowercase letters.
 */
public class N1662ECheckIfTwoStringArraysareEquivalent {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun(true, new String[]{"ab","c"}, new String[]{"a", "bc"});
        doRun(false, new String[]{"abc","d","defg"}, new String[]{"abcddef"});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(boolean expect, String[] word1, String[] word2) {
        boolean res = new N1662ECheckIfTwoStringArraysareEquivalent().arrayStringsAreEqual(word1,word2);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //2. two pointers [easy to understand)
    //Runtime: 3 ms, faster than 21.99% of Java online submissions for Check If Two String Arrays are Equivalent.
    //Memory Usage: 41.8 MB, less than 63.91% of Java online submissions for Check If Two String Arrays are Equivalent.
    //Time: O(N * K); Space: O(N * K)
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int idx1 = 0, idx2 = 0;
        int i = 0, j = 0;

        while (idx1 < word1.length && idx2 < word2.length) {
            String worda = word1[idx1];
            String wordb = word2[idx2];

            if (worda.charAt(i++) != wordb.charAt(j++) )return false;
            if (i == worda.length()) {
                i = 0; idx1++;
            }
            if (j == wordb.length()) {
                j = 0; idx2++;
            }
        }
        return (idx1 == word1.length && idx2 == word2.length);
    }


    //1.Concatenate and Compare
    //Runtime: 1 ms, faster than 95.64% of Java online submissions for Check If Two String Arrays are Equivalent.
    //Memory Usage: 42.5 MB, less than 19.60% of Java online submissions for Check If Two String Arrays are Equivalent.
    //Time: O(N * K); Space: O(N * K)
    //let N be the number of of string in the input; K is the maximum length of a string in it.
    public boolean arrayStringsAreEqual_1(String[] word1, String[] word2) {
        StringBuilder sb = new StringBuilder();
        for (String word: word1) sb.append(word);

        StringBuilder sb2 = new StringBuilder();
        for (String word: word2) sb2.append(word);

        return sb.toString().equals(sb2.toString());
    }
}
