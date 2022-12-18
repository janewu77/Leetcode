package LeetCode;

import Contest.N6268HCycleLengthQueriesinaTree;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import static java.time.LocalTime.now;

/**
 * Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.
 *
 *
 *
 * Example 1:
 *
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 * Example 2:
 *
 * Input: temperatures = [30,40,50,60]
 * Output: [1,1,1,0]
 * Example 3:
 *
 * Input: temperatures = [30,60,90]
 * Output: [1,1,0]
 *
 *
 * Constraints:
 *
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 */
public class N739MDailyTemperatures {

    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");

        //[]
        doRun(new int[]{8,1,5,4,3,2,1,1,0,0},  new int[]{89,62,70,58,47,47,46,76,100,70});
        doRun(new int[]{1,1,4,2,1,1,0,0},  new int[]{73,74,75,71,69,72,76,73});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int[] expect,  int[] temperatures) {
        int[] res = new N739MDailyTemperatures().dailyTemperatures(temperatures);
        boolean success = Arrays.equals(res, expect);
        System.out.println("["+success+"] expect:" + Arrays.toString(expect) + " res:" + Arrays.toString(res));
    }

    //3.backward
    //Runtime: 7 ms 100%; Memory: 52.7MB 98%
    //Time: O(N); Space: O(1)
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];

        for (int i = temperatures.length - 2; i >= 0 ; i--) {
            int idx = i + 1;
            while (idx < temperatures.length && temperatures[i] >= temperatures[idx]){
                if (res[idx] == 0) idx = temperatures.length;
                else idx += res[idx];
            }
            if (idx < temperatures.length)
                res[i] = idx - i;
        }
        return res;
    }

    //2.in-place
    //Runtime: 10 ms 97%; Memory: 52.1MB 99%
    //Time: O(N * N); Space: O(1)
    public int[] dailyTemperatures_2(int[] temperatures) {
        int[] res = new int[temperatures.length];

        for (int i = 1; i < temperatures.length; i++) {
            for (int idx = i - 1; idx >= 0 && temperatures[i] > temperatures[idx]; idx--){
                if(res[idx] != 0) continue;
                res[idx] = i - idx;
            }
        }
        return res;
    }

    //1.Monotonic stack
    //Runtime: 30ms 86%; Memory: 60.7MB 76%
    //Time: O(N); Space: O(N)
    public int[] dailyTemperatures_1(int[] temperatures) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[temperatures.length];

        stack.addLast(0);
        for (int i = 1; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peekLast()]){
                res[stack.peekLast()] = i - stack.peekLast();
                stack.pollLast();
            }
            stack.addLast(i);
        }
        return res;
    }
}
