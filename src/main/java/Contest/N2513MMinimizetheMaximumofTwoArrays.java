package Contest;

import java.io.IOException;

import static java.time.LocalTime.now;

/**
 * We have two arrays arr1 and arr2 which are initially empty. You need to add positive integers to them such that they satisfy all the following conditions:
 *
 * arr1 contains uniqueCnt1 distinct positive integers, each of which is not divisible by divisor1.
 * arr2 contains uniqueCnt2 distinct positive integers, each of which is not divisible by divisor2.
 * No integer is present in both arr1 and arr2.
 * Given divisor1, divisor2, uniqueCnt1, and uniqueCnt2, return the minimum possible maximum integer that can be present in either array.
 *
 *
 *
 * Example 1:
 *
 * Input: divisor1 = 2, divisor2 = 7, uniqueCnt1 = 1, uniqueCnt2 = 3
 * Output: 4
 * Explanation:
 * We can distribute the first 4 natural numbers into arr1 and arr2.
 * arr1 = [1] and arr2 = [2,3,4].
 * We can see that both arrays satisfy all the conditions.
 * Since the maximum value is 4, we return it.
 * Example 2:
 *
 * Input: divisor1 = 3, divisor2 = 5, uniqueCnt1 = 2, uniqueCnt2 = 1
 * Output: 3
 * Explanation:
 * Here arr1 = [1,2], and arr2 = [3] satisfy all conditions.
 * Since the maximum value is 3, we return it.
 * Example 3:
 *
 * Input: divisor1 = 2, divisor2 = 4, uniqueCnt1 = 8, uniqueCnt2 = 2
 * Output: 15
 * Explanation:
 * Here, the final possible arrays can be arr1 = [1,3,5,7,9,11,13,15], and arr2 = [2,6].
 * It can be shown that it is not possible to obtain a lower maximum satisfying all conditions.
 *
 *
 * Constraints:
 *
 * 2 <= divisor1, divisor2 <= 105
 * 1 <= uniqueCnt1, uniqueCnt2 < 109
 * 2 <= uniqueCnt1 + uniqueCnt2 <= 109
 */
public class N2513MMinimizetheMaximumofTwoArrays {



    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");
        doRun(10, 4,6,2,8);
        doRun(217679202, 92761,48337,208563424,9115778);


        doRun(7611, 41,59,6148,1460);

        doRun(123, 2,13,62,52);
        doRun(39, 2,5,20,2);
        doRun(39, 5,2,2,20);
        doRun(19, 2,2,6,4);
        doRun(14, 12, 3,2,10);
        doRun(15, 2,4,8,2);
        doRun(15, 4,2,2,8);

        doRun(41373726, 47911, 14723,18200983,23172743);
        doRun(70, 49, 17,29,41);
        doRun(4, 3, 7,2,2);
        doRun(1857, 2225, 1121,940,917);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int divisor1, int divisor2, int uniqueCnt1, int uniqueCnt2) {
        int res = new N2513MMinimizetheMaximumofTwoArrays().minimizeSet(divisor1, divisor2, uniqueCnt1, uniqueCnt2);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //3.lcm + Binary search
    //Runtime: 0ms 100%; Memory: 39.1MB 47%
    public int minimizeSet(int divisor1, int divisor2, int uniqueCnt1, int uniqueCnt2) {
        long left = uniqueCnt1 + uniqueCnt2, right = Integer.MAX_VALUE;
        long g = lcm(divisor1 , divisor2);

        long res = Integer.MAX_VALUE;
        while (left <= right) {
            long mid =  (left + right) / 2;

            boolean x = (mid - mid / divisor1) >= uniqueCnt1;
            boolean y = (mid - mid / divisor2) >= uniqueCnt2;
            boolean z = (mid - mid / g) >= uniqueCnt1 + uniqueCnt2;

            if (x && y && z){
                res = mid;
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return (int)res;
    }

    private long lcm(int a, int b) {
        return 1l * a * b / gcd(a, b);
    }
    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b,a % b);
    }

    //2.binary search
    public int minimizeSet_2(int divisor1, int divisor2, int uniqueCnt1, int uniqueCnt2) {
        long left = uniqueCnt1 + uniqueCnt2 , right = Integer.MAX_VALUE;
        long res = Integer.MAX_VALUE;
        long g = lcm(divisor1, divisor2);
        while (left <= right) {
            long mid = (right + left) / 2;
            if (isFit(divisor1, uniqueCnt1, uniqueCnt2, mid, g)
                    && isFit(divisor2, uniqueCnt2, uniqueCnt1, mid, g)){
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return (int)res;
    }

    private boolean isFit(int divisor1, int uniqueCnt1, int uniqueCnt2, long x, long g){
        long res = x;
        boolean flag = false;
        long remainder = res - res / divisor1;
        if (remainder >= uniqueCnt1) {
            remainder = remainder - uniqueCnt1 - uniqueCnt2;
            flag = true;
            if (remainder < 0)
                flag = (res / divisor1 - res / g) >= Math.abs(remainder);
        }
        return flag;
    }


    //1.brute force
    //TLE
    public int minimizeSet_1(int divisor1, int divisor2, int uniqueCnt1, int uniqueCnt2) {
        return helper(divisor1, divisor2, uniqueCnt1, uniqueCnt2, 1);
    }

    private int helper(int divisor1, int divisor2, int uniqueCnt1, int uniqueCnt2, int x){
        if ( (x % divisor1 == 0 && x % divisor2 == 0)
                || (uniqueCnt1 == 0 && x % divisor2 == 0)
                || (uniqueCnt2 == 0 && x % divisor1 == 0))
            return helper(divisor1, divisor2, uniqueCnt1, uniqueCnt2, x + 1);

        int res = Integer.MAX_VALUE;
        if (uniqueCnt1 >= 1 && x % divisor1 != 0) {
            int tmp = x;
            if (uniqueCnt1 - 1 != 0 || uniqueCnt2 != 0)
                tmp = helper(divisor1, divisor2, uniqueCnt1 - 1, uniqueCnt2, x + 1);
            res = Math.min(res, tmp);
        }

        if (uniqueCnt2 >= 1 && x % divisor2 != 0) {
            int tmp = x;
            if (uniqueCnt2 - 1 != 0 || uniqueCnt1 != 0)
                tmp = helper(divisor1, divisor2, uniqueCnt1, uniqueCnt2 - 1, x + 1);
            res = Math.min(res, tmp);
        }
        return res;
    }
}
