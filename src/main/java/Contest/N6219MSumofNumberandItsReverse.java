package Contest;

import static java.time.LocalTime.now;


/**
 * Given a non-negative integer num, return true if num can be expressed as the sum of any non-negative integer and its reverse, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 443
 * Output: true
 * Explanation: 172 + 271 = 443 so we return true.
 * Example 2:
 *
 * Input: num = 63
 * Output: false
 * Explanation: 63 cannot be expressed as the sum of a non-negative integer and its reverse so we return false.
 * Example 3:
 *
 * Input: num = 181
 * Output: true
 * Explanation: 140 + 041 = 181 so we return true. Note that when a number is reversed, there may be leading zeros.
 *
 *
 * Constraints:
 *
 * 0 <= num <= 105
 */

/**
 * M: - [time: 25-
 */
//2443. Sum of Number and Its Reverse
public class N6219MSumofNumberandItsReverse {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun(true, 443);
        doRun(true, 12221);
        doRun(false, 63);


        doRun(true, 0);
        doRun(false, 1);
        doRun(true, 2);
        doRun(false, 3);
        doRun(true, 8);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(boolean expect, int num) {
        boolean res = new N6219MSumofNumberandItsReverse().sumOfNumberAndReverse(num);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    public boolean sumOfNumberAndReverse(int num) {
        if (num < 10) return num % 2 == 0;
        for (int i = 0; i <= num; i++)
            if (num - i == reverse(i)) return true;
        return false;
    }

    //Runtime: 321 ms, faster than 100.00% of Java online submissions for Sum of Number and Its Reverse.
    //Memory Usage: 41.1 MB, less than 100.00% of Java online submissions for Sum of Number and Its Reverse.
    //1.Math
    private int reverse(int val) {
        if (val < 9) return val;
        int res = 0;
        while (val >= 1) {
            res = res * 10 + val % 10;
            val /= 10;
        }
        return res;
    }

    //Runtime: 2199 ms, faster than 100.00% of Java online submissions for Sum of Number and Its Reverse.
    //Memory Usage: 145.1 MB, less than 100.00% of Java online submissions for Sum of Number and Its Reverse.
    //2.Build-in reverse
    private int reverse2(int val) {
        return Integer.valueOf(new StringBuffer(Integer.toString(val)).reverse().toString());
    }

    //Runtime: 1814 ms, faster than 100.00% of Java online submissions for Sum of Number and Its Reverse.
    //Memory Usage: 144.8 MB, less than 100.00% of Java online submissions for Sum of Number and Its Reverse.
    //3.String
    private int reverse3(int val) {
        char[] s = String.valueOf(val).toCharArray();
        int left = 0,right = s.length - 1;

        while (left < right){
            char t = s[left];
            s[left++] = s[right];
            s[right--] = t;
        }
        return Integer.valueOf(String.valueOf(s));
    }

}
