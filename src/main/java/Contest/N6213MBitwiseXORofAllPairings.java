package Contest;

import java.util.List;

import static java.time.LocalTime.now;


/**
 * You are given two 0-indexed arrays, nums1 and nums2, consisting of non-negative integers. There exists another array, nums3, which contains the bitwise XOR of all pairings of integers between nums1 and nums2 (every integer in nums1 is paired with every integer in nums2 exactly once).
 *
 * Return the bitwise XOR of all integers in nums3.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [2,1,3], nums2 = [10,2,5,0]
 * Output: 13
 * Explanation:
 * A possible nums3 array is [8,0,7,2,11,3,4,1,9,1,6,3].
 * The bitwise XOR of all these numbers is 13, so we return 13.
 * Example 2:
 *
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 0
 * Explanation:
 * All possible pairs of bitwise XORs are nums1[0] ^ nums2[0], nums1[0] ^ nums2[1], nums1[1] ^ nums2[0],
 * and nums1[1] ^ nums2[1].
 * Thus, one possible nums3 array is [2,5,1,6].
 * 2 ^ 5 ^ 1 ^ 6 = 0, so we return 0.
 *
 *
 * Constraints:
 *
 * 1 <= nums1.length, nums2.length <= 105
 * 0 <= nums1[i], nums2[j] <= 109
 *
 *
 */

/**
 * M - [time: 7-
 */
//2425. Bitwise XOR of All Pairings
public class N6213MBitwiseXORofAllPairings {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        //doRun(true, "abaccb", new int[]{1,3,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, String beginWord, String endWord, List<String> wordList) {
//        int res = new N3()
//                .ladderLength(beginWord, endWord, wordList);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }



    //Runtime: 4 ms, faster than 100.00% of Java online submissions for Bitwise XOR of All Pairings.
    //Memory Usage: 86 MB, less than 100.00% of Java online submissions for Bitwise XOR of All Pairings.
    public int xorAllNums(int[] nums1, int[] nums2) {
        int res = 0;
        if (nums2.length % 2 != 0)
            for (int num : nums1) res = res ^ num;

        if (nums1.length % 2 != 0)
            for (int num : nums2) res = res ^ num;
        return res;
    }


}
