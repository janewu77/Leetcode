package Contest;



import java.util.List;

import static java.time.LocalTime.now;


/**
 * You are given an integer array pref of size n. Find and return the array arr of size n that satisfies:
 *
 * pref[i] = arr[0] ^ arr[1] ^ ... ^ arr[i].
 * Note that ^ denotes the bitwise-xor operation.
 *
 * It can be proven that the answer is unique.
 *
 *
 *
 * Example 1:
 *
 * Input: pref = [5,2,0,3,1]
 * Output: [5,7,2,3,2]
 * Explanation: From the array [5,7,2,3,2] we have the following:
 * - pref[0] = 5.
 * - pref[1] = 5 ^ 7 = 2.
 * - pref[2] = 5 ^ 7 ^ 2 = 0.
 * - pref[3] = 5 ^ 7 ^ 2 ^ 3 = 3.
 * - pref[4] = 5 ^ 7 ^ 2 ^ 3 ^ 2 = 1.
 * Example 2:
 *
 * Input: pref = [13]
 * Output: [13]
 * Explanation: We have pref[0] = arr[0] = 13.
 *
 */

/**
 * M - [time:20-
 */
public class N2433MFindTheOriginalArrayofPrefixXor {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        //doRun(true, "abaccb", new int[]{1,3,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, String beginWord, String endWord, List<String> wordList) {
//        int res = new N2().ladderLength(beginWord, endWord, wordList);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 2 ms, faster than 100.00% of Java online submissions for Find The Original Array of Prefix Xor.
    //Memory Usage: 54.9 MB, less than 100.00% of Java online submissions for Find The Original Array of Prefix Xor.
    //x^y = z  >>> z^x = y
    //Time: O(N)
    public int[] findArray(int[] pref) {
        for (int i = pref.length - 1; i > 0 ; i--)
            pref[i] ^= pref[i-1];
        return pref;
    }


}
