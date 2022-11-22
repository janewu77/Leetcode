package LeetCode;

import java.util.*;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

/**
 * You are given two strings stamp and target. Initially, there is a string s
 * of length target.length with all s[i] == '?'.
 *
 * In one turn, you can place stamp over s and replace every letter in the s
 * with the corresponding letter from stamp.
 *
 * For example, if stamp = "abc" and target = "abcba", then s is "?????" initially. In one turn you can:
 * place stamp at index 0 of s to obtain "abc??",
 * place stamp at index 1 of s to obtain "?abc?", or
 * place stamp at index 2 of s to obtain "??abc".
 * Note that stamp must be fully contained in the boundaries of s in order to stamp
 * (i.e., you cannot place stamp at index 3 of s).
 * We want to convert s to target using at most 10 * target.length turns.
 *
 * Return an array of the index of the left-most letter being stamped at each turn.
 * If we cannot obtain target from s within 10 * target.length turns, return an empty array.
 *
 *
 *
 * Example 1:
 *
 * Input: stamp = "abc", target = "ababc"
 * Output: [0,2]
 * Explanation: Initially s = "?????".
 * - Place stamp at index 0 to get "abc??".
 * - Place stamp at index 2 to get "ababc".
 * [1,0,2] would also be accepted as an answer, as well as some other answers.
 * Example 2:
 *
 * Input: stamp = "abca", target = "aabcaca"
 * Output: [3,0,1]
 * Explanation: Initially s = "???????".
 * - Place stamp at index 3 to get "???abca".
 * - Place stamp at index 0 to get "abcabca".
 * - Place stamp at index 1 to get "aabcaca".
 *
 *
 * Constraints:
 *
 * 1 <= stamp.length <= target.length <= 1000
 * stamp and target consist of lowercase English letters.
 */

/**
 * H - [time: 120+
 *
 */

public class N936HStampingTheSequence {

    public static void main(String[] args) {

        System.out.println(now());
        int[][] data;

        System.out.println("========doRun_demo==========");
//
        doRun_demo("0,2", "abc", "ababc");
        doRun_demo("0,3,1", "abca", "aabcaca");
        doRun_demo("2,4,0", "abc", "abcbabc");
        doRun_demo("4,3,1,0", "abc", "abccbcc");

        doRun_demo("", "aye", "eyeye");
        doRun_demo("", "lemk", "lleme");
        doRun_demo("", "cbcf", "ccbbf");

        doRun_demo("", "mda", "mdadddaaaa");

        doRun_demo("0,3,2,1,5,4", "ffebb", "fffeffebbb");

        doRun_demo("14,13,12,11,10,9,8,7,6,5,4,3,2,1,0", "k", "kkkkkkkkkkkkkkk");

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun_demo(String expect, String stamp, String target) {
        int[] res1 = new N936HStampingTheSequence().movesToStamp(stamp, target);
//        String res = comm.toString(res1);
        String res = Arrays.stream(res1).mapToObj(i-> String.valueOf(i)).collect(Collectors.joining(","));
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    //还原法（倒推法）：逐步还原，并记录位置。
    //Runtime: 4 ms, faster than 98.21% of Java online submissions for Stamping The Sequence.
    //Memory Usage: 43.2 MB, less than 91.07% of Java online submissions for Stamping The Sequence.
    //Time: O( (N-M) * 2M ) ; Space: O(M + N + (N-M))
    public int[] movesToStamp(String stamp, String target){
        if (target.indexOf(stamp) == -1) return new int[0];

        char[] stampChars = stamp.toCharArray();
        char[] targetChars = target.toCharArray();
        int N = targetChars.length, M = stampChars.length;

        int count = 0; //被还原的字母个数。当与target的一样时，表示都已还原。
        int[] res = new int[N]; //放结果，从后往前放。 （相当于反向操作）
        int resIdx = N - 1;

        //queue：未替换过的坐标。
        //Time: O(N-M); Space: O(N-M);
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i <= N - M; i++)  queue.add(i);

        //Time: O((N-M) * 2M)
        while (count < N && !queue.isEmpty()) {
            int qSize = queue.size();
            for (int i = 0; i < qSize; i++) {
                int begin = queue.poll();

                //Time: O(2M)
                if (isMatch(stampChars, targetChars, begin)) {
                    res[resIdx--] = begin; //记录还原的位置
                    count += doReplace(targetChars, begin, begin + M);
                    if (count >= N) break;
                } else
                    queue.add(begin); //不匹配.不能还原.先放入q.
            }

            //本轮操作未发生还原，则说明无法还原。
            if (queue.size() == qSize) return new int[0];
        }
        return Arrays.copyOfRange(res, resIdx+1, N);
    }

    //是否匹配
    //Time: O(M); Space: O(1)
    private boolean isMatch(char[] stamp, char[] chars, int begin){
        for (int i = 0; i < stamp.length; i++)
            if (chars[begin + i] != '?' && chars[begin + i] != stamp[i]) return false;
        return true;
    }

    //把'非？'还原成'？'，并记录还原的个数.
    //Time: O(M); Space: O(1)
    private int doReplace(char[] seen, int begin, int end){
        //replace
        int count = 0;
        for (int i = begin; i < end; i++) {
            if (seen[i] != '?'){
                count++; seen[i] = '?';
            }
        }
        return count;
    }

}
