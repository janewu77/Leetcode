package LeetCode;

import java.util.ArrayList;
import java.util.List;

import static java.time.LocalTime.now;

/**
 * Given an integer n, return true if it is a power of four. Otherwise, return false.
 *
 * An integer n is a power of four, if there exists an integer x such that n == 4x.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 16
 * Output: true
 * Example 2:
 *
 * Input: n = 5
 * Output: false
 * Example 3:
 *
 * Input: n = 1
 * Output: true
 *
 *
 * Constraints:
 *
 * -231 <= n <= 231 - 1
 */
public class N342EPowerofFour {


    public static void main(String[] args) {

        System.out.println(now());
        int[] data;

        System.out.println("========doRun_demo==========");
        doRun_demo(false, 0);
        doRun_demo(true, 4);
        doRun_demo(false, 8);
        doRun_demo(true, 16);
        doRun_demo(false, 32);

        doRun_demo(false, 12);

        doRun_demo(false, 13);

        doRun_demo(false, -2147483648);
        doRun_demo(true, 268435456);

//        long n = 1;
//        while (n <= Integer.MAX_VALUE){
//            System.out.println(n);
//            n = n * 4;
//        }

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun_demo(boolean expect, int n) {
        boolean res = new N342EPowerofFour().isPowerOfFour(n);
//        String res = comm.toString(res1);
//        String res = Arrays.stream(res1).mapToObj(i-> String.valueOf(i)).collect(Collectors.joining(","));
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    ////////////////////////////////////////////////////////////////////////
    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Power of Four.
    //Memory Usage: 41.2 MB, less than 67.12% of Java online submissions for Power of Four.
    public boolean isPowerOfFour(int num) {
        return (num > 0) && ((num & (num - 1)) == 0) && (num % 3 == 1);
    }

    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Power of Four.
    //Memory Usage: 39.7 MB, less than 90.46% of Java online submissions for Power of Four.
    static final int OFF_BITS = 0xaaaaaaaa;
//    static final int OFF_BITS = 0x55555555;
    public boolean isPowerOfFour_6(int num) {
        return (num & ( num - 1)) == 0 && OFF_BITS != (OFF_BITS | num);
    }

    /////////////////////////////////////////////////////////////////////////////////
    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Power of Four.
    //Memory Usage: 41.7 MB, less than 19.85% of Java online submissions for Power of Four.
    // check if num is a power of two: x > 0 and x & (x - 1) == 0.
    //Time: O(1); Space: O(1)
    public boolean isPowerOfFour_5(int num) {
        //only have one '1' bit in their binary notation,so we use x & (x - 1) to delete the lowest '1',
        //and if then it becomes 0,it prove that there is only one '1' bit.T

        //0xaaaaaaaa 1010 1010 1010 1010 1010 1010 1010 1010
        return (num > 0) && ((num & (num - 1)) == 0) && ((num & 0xaaaaaaaa) == 0);

        //0x55555555 0101 0101 0101 0101 0101 0101 0101 0101
        //return num > 0 && (num&(num-1)) == 0 && (num & 0x55555555) != 0;
    }

    /////////////////////////////////////////////////////////////////////////////////
    //Runtime: 2 ms, faster than 65.34% of Java online submissions for Power of Four.
    //Memory Usage: 41 MB, less than 73.02% of Java online submissions for Power of Four.
    //Brute Force + Precomputations
    public boolean isPowerOfFour_4(int num) {
        return p.nums.contains(num);
    }
    public static Powers p = new Powers();
    static class Powers {
        private int n = 15;
        public List<Integer> nums = new ArrayList();
        Powers() {
            int lastNum = 1;
            nums.add(lastNum);
            for (int i = 1; i < n + 1; ++i) {
                lastNum = lastNum * 4;
                nums.add(lastNum);
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////
    //Runtime: 2 ms, faster than 65.34% of Java online submissions for Power of Four.
    //Memory Usage: 41.3 MB, less than 50.03% of Java online submissions for Power of Four.
    //O(1)
    public boolean isPowerOfFour_3(int n) {
        return (n > 0) && (Math.log(n) / Math.log(2) % 2 == 0);
        //return (Math.log10(n) / Math.log10(4)) % 1 == 0;
        //return (Math.log(n) / Math.log(4) + epsilon) % 1 <= 2 * epsilon;
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    //Runtime: 10 ms, faster than 11.07% of Java online submissions for Power of Four.
    //Memory Usage: 42.4 MB, less than 5.32% of Java online submissions for Power of Four.
    public boolean isPowerOfFour_2(int n) {
        return Integer.toString(n, 4).matches("^10*$");
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Power of Four.
    //Memory Usage: 41.4 MB, less than 50.03% of Java online submissions for Power of Four.
    //Time: O(logN)
    public boolean isPowerOfFour_1(int n) {
        if (n < 1) return false;
        while (n % 4 == 0) n >>= 2;
        return n == 1;
    }

}
