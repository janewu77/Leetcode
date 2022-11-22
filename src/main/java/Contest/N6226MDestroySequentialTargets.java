package Contest;

import java.util.*;

import static java.time.LocalTime.now;


/**
 * You are given a 0-indexed array nums consisting of positive integers, representing targets on a number line. You are also given an integer space.
 *
 * You have a machine which can destroy targets. Seeding the machine with some nums[i] allows it to destroy all targets with values that can be represented as nums[i] + c * space, where c is any non-negative integer. You want to destroy the maximum number of targets in nums.
 *
 * Return the minimum value of nums[i] you can seed the machine with to destroy the maximum number of targets.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,7,8,1,1,5], space = 2
 * Output: 1
 * Explanation: If we seed the machine with nums[3], then we destroy all targets equal to 1,3,5,7,9,...
 * In this case, we would destroy 5 total targets (all except for nums[2]).
 * It is impossible to destroy more than 5 targets, so we return nums[3].
 * Example 2:
 *
 * Input: nums = [1,3,5,2,4,6], space = 2
 * Output: 1
 * Explanation: Seeding the machine with nums[0], or nums[3] destroys 3 targets.
 * It is not possible to destroy more than 3 targets.
 * Since nums[0] is the minimal integer that can destroy 3 targets, we return 1.
 * Example 3:
 *
 * Input: nums = [6,2,5], space = 100
 * Output: 2
 * Explanation: Whatever initial seed we select, we can only destroy 1 target. The minimal seed is nums[1].
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 * 1 <= space <= 109
 */

/**
 * M - [time: 60+
 */
//2453. Destroy Sequential Targets
public class N6226MDestroySequentialTargets {

    public static void main(String[] args) {

        System.out.println(now());
        doRun(235326233, new int[]{625879766,235326233,250224393,501422042,683823101,948619719,680305710,733191937,182186779,353350082},4);

        doRun(1, new int[]{1,2,20,21},2);

        doRun(1, new int[]{3,7,8,1,1,5},2);
        doRun(1, new int[]{1,3,5,2,4,6},2);
        doRun(2, new int[]{6,2,5},100);
        doRun(2, new int[]{1,5,3,2,2},10000);

        doRun(98143252, new int[]{916295060,579800536,213931972,924157829,730387212,211656716,442375097,252533555,516331783,770263691,355571302,770650341,712271488,660251142,284371784,68417361,283004497,314182509,716389456,214108571,477619353,706798519,446439407,802256102,938303192,204688887,748897411,328448150,695386000,939452651,841734893,470178497,390802190,499273452,205243736,814194673,348939941,983186858,899246901,488418441,114648292,671127871,746965912,75961918,548104235,313015690,667295036,761289562,684142216,136936773,717115132,559448926,710758125,271214907,737424409,862830887,866796431,138686664,150725295,728283282,919993105,663958082,853015962,116452119,261613678,609809644,262090889,577302189,480585789,731811985,257464102,280159306,722668522,490962035,957762353,617024431,514219090,5556053,890895272,911005649,153973152,146945106,196632946,525202150,869840516,449690564,653780767,70527214,857449318,364212100,794022432,405946715,487986650,248027584,957254587,647000549,646587321,98143252,193148550,357618776},4);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[] nums, int space) {
        int res = new N6226MDestroySequentialTargets().destroyTargets(nums, space);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //1.one pass
    //Runtime: 27 ms, faster than 50.00% of Java online submissions for Destroy Sequential Targets.
    //Memory Usage: 58.2 MB, less than 50.00% of Java online submissions for Destroy Sequential Targets.
    //Time: O(N); Space: O(S)
    public int destroyTargets(int[] nums, int space) {

        int maxNum = 0, res = 0;
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int remainder = nums[i] % space;
            int[] value = map.getOrDefault(remainder, new int[]{0, nums[i]});
            map.put(remainder, value);
            value[0]++;
            value[1] = Math.min(value[1], nums[i]);

            if (value[0] >= maxNum) {
                res = value[0] == maxNum ? Math.min(res, value[1]) : value[1];
                maxNum = value[0];
            }
        }
        return res;
    }


}
