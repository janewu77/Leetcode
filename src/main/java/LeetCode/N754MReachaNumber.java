package LeetCode;


import static java.time.LocalTime.now;

/**
 * You are standing at position 0 on an infinite number line. There is a destination at position target.
 *
 * You can make some number of moves numMoves so that:
 *
 * On each move, you can either go left or right.
 * During the ith move (starting from i == 1 to i == numMoves), you take i steps in the chosen direction.
 * Given the integer target, return the minimum number of moves required (i.e., the minimum numMoves)
 * to reach the destination.
 *
 *
 *
 * Example 1:
 *
 * Input: target = 2
 * Output: 3
 * Explanation:
 * On the 1st move, we step from 0 to 1 (1 step).
 * On the 2nd move, we step from 1 to -1 (2 steps).
 * On the 3rd move, we step from -1 to 2 (3 steps).
 * Example 2:
 *
 * Input: target = 3
 * Output: 2
 * Explanation:
 * On the 1st move, we step from 0 to 1 (1 step).
 * On the 2nd move, we step from 1 to 3 (2 steps).
 *
 *
 * Constraints:
 *
 * -109 <= target <= 109
 * target != 0
 */

/**
 * M - [time: 120+
 */
public class N754MReachaNumber {


    public static void main(String[] args){
        String[] data;
        String expect;

        System.out.println(now());
        doRun(1,1);
        doRun(3,2);
        doRun(2,3);
        doRun(3,4);

        doRun(4,10);
        doRun(5,15);
        doRun(7,16);

        doRun(24,300);
        doRun(25,301);
        doRun(27,302);

        doRun(44723,1_000_000_000);
        doRun(44723,-1_000_000_000);

        System.out.println(now());
        System.out.println("==================");

    }


    static private void doRun(int expect, int target) {
        int res = new N754MReachaNumber()
                .reachNumber(target);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 1 ms, faster than 93.69% of Java online submissions for Reach a Number.
    //Memory Usage: 41 MB, less than 34.07% of Java online submissions for Reach a Number.
    //Mathematical
    //Time: O(sqrN); Space:O(1)
    public int reachNumber_x(int target) {
        target = Math.abs(target);
        int k = 0;
        while (target > 0) target -= (++k);
        return target % 2 == 0 ? k : k + 1 + k % 2;
    }

    public int reachNumber(int target) {
        target = Math.abs(target);
        int k = 0, sum = 0;
        while (sum < target) sum += (++k);
        return  ((sum - target) % 2 == 0) ? k : k + 1 + k % 2;

//        if ((sum - target) % 2 == 0) return i;
//
//        sum += (++i);
//        if ((sum - target) % 2 == 0) return i;
//        else return i + 1;
    }


    public int reachNumber_1(int target) {
        target = Math.abs(target);

        int i = 1, sum = 1;
        while (sum < target) sum += (++i);
        if (sum == target) return i;

        if ((sum - target) % 2 == 0) return i;

        sum += (++i);
        if ((sum - target) % 2 == 0) return i;
        else return i + 1;
    }

}
