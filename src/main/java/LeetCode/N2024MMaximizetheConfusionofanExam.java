package LeetCode;


import java.util.Arrays;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

/**
 * A teacher is writing a test with n true/false questions, with 'T' denoting true and 'F'
 * denoting false. He wants to confuse the students by maximizing the number of consecutive questions
 * with the same answer (multiple trues or multiple falses in a row).
 *
 * You are given a string answerKey, where answerKey[i] is the original answer to the ith question.
 * In addition, you are given an integer k, the maximum number of times you may perform the following operation:
 *
 * Change the answer key for any question to 'T' or 'F' (i.e., set answerKey[i] to 'T' or 'F').
 * Return the maximum number of consecutive 'T's or 'F's in the answer key after performing the operation
 * at most k times.
 *
 *
 *
 * Example 1:
 *
 * Input: answerKey = "TTFF", k = 2
 * Output: 4
 * Explanation: We can replace both the 'F's with 'T's to make answerKey = "TTTT".
 * There are four consecutive 'T's.
 * Example 2:
 *
 * Input: answerKey = "TFFT", k = 1
 * Output: 3
 * Explanation: We can replace the first 'T' with an 'F' to make answerKey = "FFFT".
 * Alternatively, we can replace the second 'T' with an 'F' to make answerKey = "TFFF".
 * In both cases, there are three consecutive 'F's.
 * Example 3:
 *
 * Input: answerKey = "TTFTTFTT", k = 1
 * Output: 5
 * Explanation: We can replace the first 'F' to make answerKey = "TTTTTFTT"
 * Alternatively, we can replace the second 'F' to make answerKey = "TTFTTTTT".
 * In both cases, there are five consecutive 'T's.
 *
 *
 * Constraints:
 *
 * n == answerKey.length
 * 1 <= n <= 5 * 104
 * answerKey[i] is either 'T' or 'F'
 * 1 <= k <= n
 */
public class N2024MMaximizetheConfusionofanExam {

    public static void main(String[] args){
        int[] data;

        System.out.println(now());
        doRun_demo(2, "TF", 2);
        doRun_demo(4, "TTFF", 2);
        doRun_demo(3, "TFFT", 1);
        doRun_demo(5, "TTFTTFTT", 1);


        System.out.println(now());
        System.out.println("==================");

    }

    static private void doRun_demo(int expect, String answerKey, int k) {
        int res = new N2024MMaximizetheConfusionofanExam()
                .maxConsecutiveAnswers(answerKey, k);
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
        //String res = comm.toString(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //Runtime: 13 ms, faster than 90.30% of Java online submissions for Maximize the Confusion of an Exam.
    //Memory Usage: 42.6 MB, less than 92.46% of Java online submissions for Maximize the Confusion of an Exam.
    //Slide window
    //Time: O(N); Space: O(1)
    public int maxConsecutiveAnswers(String answerKey, int k) {
        int res = 0, countT = 0, left = 0;

        for (int right = 0; right < answerKey.length(); right++) {
            if (answerKey.charAt(right) == 'T') countT++;

            if (countT > k && right - left + 1 - countT > k) {
                if (answerKey.charAt(left++) == 'T') countT--;
            }else
                res = Math.max(res, right - left + 1);
        }
        return res;
    }

    //Runtime: 30 ms, faster than 23.71% of Java online submissions for Maximize the Confusion of an Exam.
    //Memory Usage: 47.9 MB, less than 68.32% of Java online submissions for Maximize the Confusion of an Exam.
    //Slide window
    //Time:O(2N); Space:O(1);
    public int maxConsecutiveAnswers_1(String answerKey, int k) {
        int left = 0, right = 0;
        int resT = 0, resF = 0;
        int spanT = k, spanF = k;

        while (right < answerKey.length() ){
            while (spanT < 0)
                if (answerKey.charAt(left++) == 'F') spanT++;

            if (answerKey.charAt(right++) == 'F') spanT--;

            if (spanT < 0)
                resT = Math.max(resT, right - left - 2);
            else
                resT = Math.max(resT, right - left );

        }

        left = 0;
        right = 0;
        while (right < answerKey.length() ){
            while (spanF < 0)
                if (answerKey.charAt(left++) == 'T') spanF++;

            if (answerKey.charAt(right++) == 'T') spanF--;

            if (spanF < 0)
                resF = Math.max(resF, right - left - 2);
            else
                resF = Math.max(resF, right - left );

        }

        return Math.max(resF, resT);
    }
}
