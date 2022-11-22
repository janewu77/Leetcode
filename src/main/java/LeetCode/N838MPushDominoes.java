package LeetCode;

import static java.time.LocalTime.now;

/**
 * There are n dominoes in a line, and we place each domino vertically upright. In the beginning, we simultaneously push some of the dominoes either to the left or to the right.
 *
 * After each second, each domino that is falling to the left pushes the adjacent domino on the left. Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.
 *
 * When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.
 *
 * For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.
 *
 * You are given a string dominoes representing the initial state where:
 *
 * dominoes[i] = 'L', if the ith domino has been pushed to the left,
 * dominoes[i] = 'R', if the ith domino has been pushed to the right, and
 * dominoes[i] = '.', if the ith domino has not been pushed.
 * Return a string representing the final state.
 *
 *
 *
 * Example 1:
 *
 * Input: dominoes = "RR.L"
 * Output: "RR.L"
 * Explanation: The first domino expends no additional force on the second domino.
 * Example 2:
 *
 *
 * Input: dominoes = ".L.R...LR..L.."
 * Output: "LL.RR.LLRRLL.."
 *
 *
 * Constraints:
 *
 * n == dominoes.length
 * 1 <= n <= 105
 * dominoes[i] is either 'L', 'R', or '.'.
 */

/**
 * M - [time: 60+
 */
public class N838MPushDominoes {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;


        doRun("RR.LL", "R...L");
        doRun("LLLL......", "L.LL......");
        doRun("RR.L", "RR.L");
        doRun("R.LL", "R.LL");

        doRun("RRRL...", "RRRL...");

        doRun("RRRR", "R.RR");
        doRun("LL.RR", ".L.R.");

        doRun("RR", "RR");


        doRun("LL.RR.LLRRLL..", ".L.R...LR..L..");

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(String expect, String dominoes) {
        String res = new N838MPushDominoes()
                .pushDominoes(dominoes);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 27 ms, faster than 63.09% of Java online submissions for Push Dominoes.
    //Memory Usage: 54.3 MB, less than 58.52% of Java online submissions for Push Dominoes.
    //Calculate force
    //Time: O(2N); Space: O(2N)
    //Time: O(N); Space: O(N)
    public String pushDominoes(String dominoes) {
        char[] chars = dominoes.toCharArray();

        int[] forward = new int[chars.length];
        int force = 0;
        for (int idx = 0; idx < forward.length; idx++) {
            if (dominoes.charAt(idx) == 'R') force = chars.length;
            else if (dominoes.charAt(idx) == 'L') force = 0;
            else force = force == 0 ? 0 : force - 1;
            forward[idx] = force;
        }

        force = 0;
        for (int idx = forward.length - 1; idx >= 0; idx--) {
            if (dominoes.charAt(idx) == 'L')  force = chars.length;
            else if (dominoes.charAt(idx) == 'R') force = 0;
            else force = force == 0 ? 0 : force - 1;

            if (force > forward[idx]) chars[idx] = 'L';
            if (force < forward[idx]) chars[idx] = 'R';
        }
        return String.valueOf(chars);
    }


    //根据 当前字母与上一字母，进行处理。
    //Runtime: 17 ms, faster than 92.09% of Java online submissions for Push Dominoes.
    //Memory Usage: 53.7 MB, less than 82.95% of Java online submissions for Push Dominoes.
    //one pass
    //Time: O(2N); Space: O(N)
    //Time: O(N); Space: O(N)
    public String pushDominoes_2(String dominoes) {
        int left = -1, right = -1;
        char[] chars = dominoes.toCharArray();

        for (int idx = 0; idx <= chars.length; idx++) {
            if (idx == chars.length || chars[idx] == 'R') {

                if (right > left)
                    while (right < idx) chars[right++] = 'R';
                else right = idx;

            } else if (chars[idx] == 'L') {

                if (right > left){
                    left = idx;
                    while (right < left) {
                        chars[right++] = 'R'; chars[left--] = 'L';
                    }
                }else
                    while (++left <= idx) chars[left] = 'L';
                left = idx;
            }
        }
        return String.valueOf(chars);
    }


    //根据当前字母，往右或往左进行处理。
    //Runtime: 15 ms, faster than 97.01% of Java online submissions for Push Dominoes.
    //Memory Usage: 43.4 MB, less than 97.36% of Java online submissions for Push Dominoes.
    //one pass
    //Time: O(2N); Space: O(N)
    //Time: O(N); Space: O(N)
    public String pushDominoes_1(String dominoes) {
        int left = 0;
        char[] chars = dominoes.toCharArray();

        for (int idx = 0; idx < chars.length; idx++) {

            if (chars[idx] == 'L') {
                while (left <= idx) chars[left++] = 'L';

            } else if (chars[idx] == 'R') {

                left = idx;
                int leftIdx = dominoes.indexOf('L', idx + 1);
                int rightIdx = dominoes.indexOf('R', idx + 1);

                if ((rightIdx <= leftIdx && rightIdx > 0) || (leftIdx < 0)) {
                    if (rightIdx < 0) rightIdx = dominoes.length();
                    idx = rightIdx - 1;

                    while (left < rightIdx) chars[left++] = 'R';
                }else{
                    idx = leftIdx;
                    while (left < leftIdx) {
                        chars[left++] = 'R'; chars[leftIdx--] = 'L';
                    }
                    left = idx + 1;
                }
            }
        }
        return String.valueOf(chars);
    }


    private void fallOneSide(char[] chars, int left, int right, char dir){
        while (left <= right) chars[left++] = dir;
    }

    private void fall2Side(char[] chars, int left, int right){
        while (left < right) {
            chars[left++] = 'R'; chars[right--] = 'L';
        }
    }
}
