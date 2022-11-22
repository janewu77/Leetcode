package Contest;


import static java.time.LocalTime.now;

/**
 * You are given a 0-indexed string s consisting of only lowercase English letters, where each letter in s appears exactly twice. You are also given a 0-indexed integer array distance of length 26.
 *
 * Each letter in the alphabet is numbered from 0 to 25 (i.e. 'a' -> 0, 'b' -> 1, 'c' -> 2, ... , 'z' -> 25).
 *
 * In a well-spaced string, the number of letters between the two occurrences of the ith letter is distance[i]. If the ith letter does not appear in s, then distance[i] can be ignored.
 *
 * Return true if s is a well-spaced string, otherwise return false.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abaccb", distance = [1,3,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
 * Output: true
 * Explanation:
 * - 'a' appears at indices 0 and 2 so it satisfies distance[0] = 1.
 * - 'b' appears at indices 1 and 5 so it satisfies distance[1] = 3.
 * - 'c' appears at indices 3 and 4 so it satisfies distance[2] = 0.
 * Note that distance[3] = 5, but since 'd' does not appear in s, it can be ignored.
 * Return true because s is a well-spaced string.
 * Example 2:
 *
 * Input: s = "aa", distance = [1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
 * Output: false
 * Explanation:
 * - 'a' appears at indices 0 and 1 so there are zero letters between them.
 * Because distance[0] = 1, s is not a well-spaced string.
 *
 *
 * Constraints:
 *
 * 2 <= s.length <= 52
 * s consists only of lowercase English letters.
 * Each letter appears in s exactly twice.
 * distance.length == 26
 * 0 <= distance[i] <= 50
 */

//2399. Check Distances Between Same Letters
public class N6167ECheckDistancesBetweenSameLetters {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun_demo(true, "abaccb", new int[]{1,3,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun_demo(boolean expect, String s, int[] distance) {
        boolean res = new N6167ECheckDistancesBetweenSameLetters().checkDistances(s, distance);
////        String res = comm.toString(res1);
//        String res = Arrays.stream(res1).mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(","));
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Check Distances Between Same Letters.
    //Memory Usage: 42.4 MB, less than 87.50% of Java online submissions for Check Distances Between Same Letters.
    //Time: O(N); Space: O(1)
    public boolean checkDistances(String s, int[] distance) {
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            int i2 = s.indexOf(c, i + 1);
            if (i2 != -1 && (i2 - i - 1) != distance[c -'a'])
                return false;
        }
        return true;
    }
}
