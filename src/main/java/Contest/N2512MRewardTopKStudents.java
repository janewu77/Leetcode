package Contest;


import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

/**
 * You are given two string arrays positive_feedback and negative_feedback, containing the words denoting positive and negative feedback, respectively. Note that no word is both positive and negative.
 *
 * Initially every student has 0 points. Each positive word in a feedback report increases the points of a student by 3, whereas each negative word decreases the points by 1.
 *
 * You are given n feedback reports, represented by a 0-indexed string array report and a 0-indexed integer array student_id, where student_id[i] represents the ID of the student who has received the feedback report report[i]. The ID of each student is unique.
 *
 * Given an integer k, return the top k students after ranking them in non-increasing order by their points. In case more than one student has the same points, the one with the lower ID ranks higher.
 *
 *
 *
 * Example 1:
 *
 * Input: positive_feedback = ["smart","brilliant","studious"], negative_feedback = ["not"], report = ["this student is studious","the student is smart"], student_id = [1,2], k = 2
 * Output: [1,2]
 * Explanation:
 * Both the students have 1 positive feedback and 3 points but since student 1 has a lower ID he ranks higher.
 * Example 2:
 *
 * Input: positive_feedback = ["smart","brilliant","studious"], negative_feedback = ["not"], report = ["this student is not studious","the student is smart"], student_id = [1,2], k = 2
 * Output: [2,1]
 * Explanation:
 * - The student with ID 1 has 1 positive feedback and 1 negative feedback, so he has 3-1=2 points.
 * - The student with ID 2 has 1 positive feedback, so he has 3 points.
 * Since student 2 has more points, [2,1] is returned.
 *
 *
 * Constraints:
 *
 * 1 <= positive_feedback.length, negative_feedback.length <= 104
 * 1 <= positive_feedback[i].length, negative_feedback[j].length <= 100
 * Both positive_feedback[i] and negative_feedback[j] consists of lowercase English letters.
 * No word is present in both positive_feedback and negative_feedback.
 * n == report.length == student_id.length
 * 1 <= n <= 104
 * report[i] consists of lowercase English letters and spaces ' '.
 * There is a single space between consecutive words of report[i].
 * 1 <= report[i].length <= 100
 * 1 <= student_id[i] <= 109
 * All the values of student_id[i] are unique.
 * 1 <= k <= n
 */
public class N2512MRewardTopKStudents {



    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");

        String[] positive_feedback, negative_feedback, report;
        int[] student_id; int k;

        positive_feedback = new String[]{"fkeofjpc","qq","iio"};
        negative_feedback = new String[]{"jdh","khj","eget","rjstbhe","yzyoatfyx","wlinrrgcm"};
        report = new String[]{"rjstbhe eget kctxcoub urrmkhlmi yniqafy fkeofjpc iio yzyoatfyx khj iio","gpnhgabl qq qq fkeofjpc dflidshdb qq iio khj qq yzyoatfyx","tizpzhlbyb eget z rjstbhe iio jdh jdh iptxh qq rjstbhe","jtlghe wlinrrgcm jnkdbd k iio et rjstbhe iio qq jdh","yp fkeofjpc lkhypcebox rjstbhe ewwykishv egzhne jdh y qq qq","fu ql iio fkeofjpc jdh luspuy yzyoatfyx li qq v","wlinrrgcm iio qq omnc sgkt tzgev iio iio qq qq","d vhg qlj khj wlinrrgcm qq f jp zsmhkjokmb rjstbhe"};
        student_id = new int[]{96537918,589204657,765963609,613766496,43871615,189209587,239084671,908938263};
        doRun("239084671,589204657,43871615", positive_feedback, negative_feedback, report, student_id, 3);
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(String expect, String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
        List<Integer> res1 = new N2512MRewardTopKStudents().topStudents(positive_feedback, negative_feedback, report, student_id, k);
        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    //Runtime: 167ms 9%; Memory: 72.MB 5%
    //Time: O(L + S * logS + P + N); Space:O(P + N + S + logS)
    //let l be the number of words in report
    //let S be the number of students
    //let P be the number of positive words; let N be the number of negative words
    public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {

        Set<String> negativeSet = new HashSet<>();
        Set<String> positiveSet = new HashSet<>();
        for (String word: positive_feedback) positiveSet.add(word);
        for (String word: negative_feedback) negativeSet.add(word);

        int[][] res = new int[student_id.length][2];
        for (int i = 0; i < report.length; i++) {
            res[i][0] = student_id[i];

            String[] words = report[i].split(" ");
            for (String word : words) {
                if (positiveSet.contains(word)) res[i][1] += 3;
                else if (negativeSet.contains(word)) res[i][1]--;
            }
        }

        Arrays.sort(res, (a, b) -> a[1] == b[1]? a[0] - b[0] : b[1] - a[1]);

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < k; i++) list.add(res[i][0]);
        return list;
    }
}
