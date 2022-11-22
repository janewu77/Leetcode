package Contest;

import java.util.HashMap;
import java.util.Map;

import static java.time.LocalTime.now;
/***
 *
 * You are given a 0-indexed array of positive integers tasks, representing tasks that need to
 * be completed in order, where tasks[i] represents the type of the ith task.
 *
 * You are also given a positive integer space, which represents the minimum number of days
 * that must pass after the completion of a task before another task of the same type can be performed.
 *
 * Each day, until all tasks have been completed, you must either:
 *
 * Complete the next task from tasks, or
 * Take a break.
 * Return the minimum number of days needed to complete all tasks.
 *
 *
 *
 * Example 1:
 *
 * Input: tasks = [1,2,1,2,3,1], space = 3
 * Output: 9
 * Explanation:
 * One way to complete all tasks in 9 days is as follows:
 * Day 1: Complete the 0th task.
 * Day 2: Complete the 1st task.
 * Day 3: Take a break.
 * Day 4: Take a break.
 * Day 5: Complete the 2nd task.
 * Day 6: Complete the 3rd task.
 * Day 7: Take a break.
 * Day 8: Complete the 4th task.
 * Day 9: Complete the 5th task.
 * It can be shown that the tasks cannot be completed in less than 9 days.
 * Example 2:
 *
 * Input: tasks = [5,8,8,5], space = 2
 * Output: 6
 * Explanation:
 * One way to complete all tasks in 6 days is as follows:
 * Day 1: Complete the 0th task.
 * Day 2: Complete the 1st task.
 * Day 3: Take a break.
 * Day 4: Take a break.
 * Day 5: Complete the 2nd task.
 * Day 6: Complete the 3rd task.
 * It can be shown that the tasks cannot be completed in less than 6 days.
 *
 *
 * Constraints:
 *
 * 1 <= tasks.length <= 105
 * 1 <= tasks[i] <= 109
 * 1 <= space <= tasks.length
 *
 */

/**
 * //2365. Task Scheduler II
 * M - [time:45-
 *  - 反省：
 *  - 时间花在处理边界上了！
 *  - 没有注意类型是long!!!!! 当大数加的时候，千万要注意尽量先减后加。
 *
 */
public class N6174TaskSchedulerII {

    public static void main(String[] args) {

        System.out.println(now());
        int[] tasks;
        tasks = new int[]{1,2,1,2,3,1};
        doRun_taskSchedulerII(9, tasks, 3);

    }

    static private void doRun_taskSchedulerII(long expect, int[] tasks, int space) {
        long res = new N6174TaskSchedulerII().taskSchedulerII(tasks,space);
////        String res = comm.toString(res1);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
        System.out.println("==================");
    }

    //网上看来的。
    public long taskSchedulerII(int[] tasks, int space) {
        Map<Integer, Long> last = new HashMap<>();
        long res = 0;
        for (int a : tasks)
            if (last.containsKey(a))
                last.put(a, res = Math.max(res, last.get(a) + space) + 1);
            else
                last.put(a, ++res);
        return res;
    }


    public long taskSchedulerII_1(int[] tasks, int space) {
        if (tasks.length == 1) return 1;

        Map<Integer,Long> map = new HashMap<>(); //type: days

        long days = 1;
        for (int i = 0 ; i < tasks.length; i++){
            int currTask = tasks[i];
            long lastDay = map.getOrDefault(currTask, -1l);
            if (lastDay > 0  && space >= (days - lastDay))
                days += (space - (days - lastDay) + 1);

            map.put(currTask, days);
            days++;
        }

        return map.get(tasks[tasks.length-1]);
    }
}
