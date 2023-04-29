package LeetCode;


import java.util.*;

import static java.time.LocalTime.now;

/**
 * You are given a 0-indexed binary string s and two integers minJump and maxJump. In the beginning, you are standing at index 0, which is equal to '0'. You can move from index i to index j if the following conditions are fulfilled:
 *
 * i + minJump <= j <= min(i + maxJump, s.length - 1), and
 * s[j] == '0'.
 * Return true if you can reach index s.length - 1 in s, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "011010", minJump = 2, maxJump = 3
 * Output: true
 * Explanation:
 * In the first step, move from index 0 to index 3.
 * In the second step, move from index 3 to index 5.
 * Example 2:
 *
 * Input: s = "01101110", minJump = 2, maxJump = 3
 * Output: false
 *
 *
 * Constraints:
 *
 * 2 <= s.length <= 105
 * s[i] is either '0' or '1'.
 * s[0] == '0'
 * 1 <= minJump <= maxJump < s.length
 */
//todo:
public class N1871MJumpGameVII {

    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");
        //
        //todo
        doRun(false, "011000001011110000", 4, 7);
        doRun(true, "011111011110111110110111101011110111011111011110101110111110", 2, 6);
        doRun(false, "00111010", 3, 5);
        doRun(false, "01101110", 2, 3);

        doRun(true, "011010", 2, 3);
        doRun(true, "00", 1, 1);

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(boolean expect, String s, int minJump, int maxJump) {
        boolean res = new N1871MJumpGameVII().canReach(s, minJump, maxJump);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //1.Slide window
    public boolean canReach(String s, int minJump, int maxJump) {
        if (s.charAt(s.length() - 1) == '1') return false;

        int left = minJump;
        int right = Math.min(maxJump, s.length() - 1);
        if (right < left) return false;
        if (right == s.length() - 1) return true;//right >= left;
        int nextLeft = Integer.MAX_VALUE, nextRight = 0;

        while (left <= right) {

            if (s.charAt(left) == '0') {
                nextLeft = Math.min(nextLeft, left + minJump);
                nextRight = Math.max(nextRight, left + maxJump);
            }

            left++;
            if (left > right){
                if (nextLeft == Integer.MAX_VALUE)
                    return false;

                left = Math.max(nextLeft, right + 1);
                right = Math.min(nextRight, s.length() - 1);
                if (right < left) return false;
                if (right == s.length() - 1) return true;//right >= left;
                nextLeft = Integer.MAX_VALUE; nextRight = 0;
            }
        }
        return false;
    }

    //2.
    //TLE
    public boolean canReach_2(String s, int minJump, int maxJump) {
        return helper(s.toCharArray(), minJump, maxJump, 0);
    }

    private boolean helper(char[] chars, int minJump, int maxJump, int i){
        if (i == chars.length - 1)
            return chars[i] == '0';
        for (int j = i + minJump; j <= Math.min(i + maxJump, chars.length - 1); j++){
            if (chars[j] == '0') {
                if (helper(chars, minJump, maxJump, j)) return true;
                chars[j] = '1';
            }
        }
        return false;
    }

    //1.BFS
    //TLE
    public boolean canReach_1(String s, int minJump, int maxJump) {
        char[] chars = s.toCharArray();
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        queue.add(0);

        while (!queue.isEmpty()) {
            int idx = queue.poll();
            if (idx == chars.length - 1) return true;

            for (int j = idx + minJump; j <= Math.min(idx + maxJump, s.length() - 1); j++){
                if (chars[j] == '0') {
                    chars[j] = '1';
                    queue.add(j);
                }
            }
        }
        return false;
    }


//    public boolean canReach(String s, int minJump, int maxJump) {
//        char[] chars = s.toCharArray();
//        if (chars[chars.length - 1] == '1') return false;
//
//        int end = Math.min(0 + maxJump, s.length() - 1);
//        int idx = 0 + minJump;
//        while (end <= s.length() - 1){
//            if (end == s.length() - 1)
//                return true;
//        }
//        return false;
//
//
////        Set<Integer> set = new HashSet<>();
//        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
//        queue.add(0);
//        chars[0] = 'x';
//
//        while (!queue.isEmpty()){
//            int i = queue.poll();
//            if (i == s.length() - 1) return true;
//
//            for (int j = i + minJump; j <= Math.min(i + maxJump, s.length() - 1); j++){
//                if (chars[j] == '0') {
//                    chars[j] = 'x';
//                    queue.add(j);
//                }
//            }
//        }
//        return false;
//    }
//
//    private boolean helper(String s, int minJump, int maxJump, int i) {
//        if (i == s.length() - 1 && s.charAt(i) == 0) return true;
//        for (int j = i + minJump; j <= Math.min(i + maxJump, s.length() - 1); i++)
//            if (s.charAt(j) == 0 && helper(s, minJump, maxJump, j)) return true;
//        return false;
//    }
//
//
//    //TLE
////    public boolean canReach(String s, int minJump, int maxJump) {
////        if (s.charAt(s.length() - 1) != '0') return false;
////        return helper(s, minJump, maxJump, 0);
////    }
////
////    private boolean helper(String s, int minJump, int maxJump, int i) {
////        if (i == s.length() - 1) return true;
////        for (int j = i + minJump; j <= Math.min(i + maxJump, s.length() - 1); i++)
////            if (s.charAt(j) == '0' && helper(s, minJump, maxJump, j)) return true;
////        return false;
////    }

}
