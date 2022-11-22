package Contest;

import java.util.ArrayList;
import java.util.List;

import static java.time.LocalTime.now;

/**
 * You are given two positive integers n and target.
 *
 * An integer is considered beautiful if the sum of its digits is less than or equal to target.
 *
 * Return the minimum non-negative integer x such that n + x is beautiful. The input will be generated such that it is always possible to make n beautiful.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 16, target = 6
 * Output: 4
 * Explanation: Initially n is 16 and its digit sum is 1 + 6 = 7. After adding 4, n becomes 20 and digit sum becomes 2 + 0 = 2. It can be shown that we can not make n beautiful with adding non-negative integer less than 4.
 * Example 2:
 *
 * Input: n = 467, target = 6
 * Output: 33
 * Explanation: Initially n is 467 and its digit sum is 4 + 6 + 7 = 17. After adding 33, n becomes 500 and digit sum becomes 5 + 0 + 0 = 5. It can be shown that we can not make n beautiful with adding non-negative integer less than 33.
 * Example 3:
 *
 * Input: n = 1, target = 1
 * Output: 0
 * Explanation: Initially n is 1 and its digit sum is 1, which is already smaller than or equal to target.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 1012
 * 1 <= target <= 150
 * The input will be generated such that it is always possible to make n beautiful.
 */

/**
 * M - [time: 120+
 */
//2457. Minimum Addition to Make Integer Beautiful
public class N6222MMinimumAdditiontoMakeIntegerBeautiful {

    public static void main(String[] args) {

        System.out.println(now());

        doRun(3931939239l,6068060761l,3);
        doRun(0,1,1);
        doRun(326,14674,6);
        doRun(298,6702,12);
        doRun(32,668,12);

        doRun(4,16,6);
        doRun(0,1,1);
        doRun(33,467,6);

        doRun(2,658,12);
        doRun(32,668,12);

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(long expect, long n, int target ) {
        long res = new N6222MMinimumAdditiontoMakeIntegerBeautiful().makeIntegerBeautiful(n, target);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //4.算10 100 1000 10000
    //https://leetcode.com/problems/minimum-addition-to-make-integer-beautiful/discuss/2758216/JavaC%2B%2BPython-Straight-Forward-Solution
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Minimum Addition to Make Integer Beautiful.
    //Memory Usage: 39 MB, less than 100.00% of Java online submissions for Minimum Addition to Make Integer Beautiful.
    //Time: O(logN * logN); Space:O(1)
    public long makeIntegerBeautiful(long n, int target) {
        long n0 = n, base = 1;
        while (sum(n) > target) {
            n = n / 10 + 1;
            base *= 10;
        }
        return n * base - n0;
    }
    private int sum(long n) {
        int res = (int)(n % 10);
        while ((n /= 10) > 0) res += n % 10;
        return res;
    }


    //从低位 到 高位，求差。
    //从高位 到 低位，检查需要跳过的差的位置。
    //3.Runtime: 1 ms, faster than 50.00% of Java online submissions for Minimum Addition to Make Integer Beautiful.
    //Memory Usage: 40.9 MB, less than 75.00% of Java online submissions for Minimum Addition to Make Integer Beautiful.
    //Time: O(2 * lgN); Space: O(lgN)
    public long makeIntegerBeautiful_3(long n, int target) {
        List<Integer> list = new ArrayList<>();
        int sum = (int)(n % 10);
        long res = 10 - n % 10;
        long y = 10;
        //from low to high, pre-compute result
        while ((n /= 10) > 0) {
            int x = (int)(n % 10);
           // list.add(x);
            sum += x;
            res += (9 - x) * y;
            y *= 10;
        }
        if (sum <= target) return 0;


        y/= 10;
        //from high to low, skip part of the result
        for (int i = list.size() - 1; i >= 0; --i) {
            target -= list.get(i);
            if (target <= 0) break;
            res %= y;
            y /= 10;
        }
        return res;
    }

    //2.two pass
    //Runtime: 1 ms, faster than 50.00% of Java online submissions for Minimum Addition to Make Integer Beautiful.
    //Memory Usage: 40.5 MB, less than 75.00% of Java online submissions for Minimum Addition to Make Integer Beautiful.
    //Time: O(2 * lgN); Space: O(lgN)
    public long makeIntegerBeautiful_2(long n, int target) {
        List<Integer> list = new ArrayList<>();
        int sum = 0;
        while (n > 0) {
            int x = (int)(n % 10);
            list.add(x);
            n /= 10;
            sum += x;
        }
        if (sum <= target) return 0;

        long res = 10 - list.get(0), x = 10;
        sum = sum - list.get(0) + 1;

        for (int i = 1; i < list.size(); ++i) {
            if (sum > target){
                res += (9 - list.get(i)) * x;
                x *= 10;
                sum = sum - list.get(i);
            }else break;
        }
        return res;
    }

    //1.prefix sum
    //Runtime: 1 ms, faster than 50.00% of Java online submissions for Minimum Addition to Make Integer Beautiful.
    //Memory Usage: 37 MB, less than 100.00% of Java online submissions for Minimum Addition to Make Integer Beautiful.
    //Time: O(3 * lgN); Space: O(2 * lgN)
    public long makeIntegerBeautiful_1(long n, int target) {
        List<Integer> list = new ArrayList<>();
        list.add((int)(n % 10));
        while ((n /= 10) > 0) list.add((int)(n % 10));

        int[] prxSum = new int[list.size() + 1];
        for (int i = prxSum.length - 2; i >= 0; --i)
            prxSum[i] = prxSum[i + 1] + list.get(i);

        long res = 0; long x = 1;
        for (int i = 0; i < prxSum.length; ++i) {
            if (prxSum[i] > target){
                res += (10 - (prxSum[i] - prxSum[i + 1])) * x;
                x *= 10;
                prxSum[i + 1]++;
            }else break;
        }
        return res;
    }

//    private List<Integer> long2List(long n){
//        List<Integer> list = new ArrayList<>();
//        list.add((int)(n % 10));
//        while ((n /= 10) > 0) list.add((int)(n % 10));
//        return list;
//    }


}
