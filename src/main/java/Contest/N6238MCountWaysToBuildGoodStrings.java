package Contest;

import java.io.IOException;

import static java.time.LocalTime.now;


/**
 * Given the integers zero, one, low, and high, we can construct a string by starting with an empty string, and then at each step perform either of the following:
 *
 * Append the character '0' zero times.
 * Append the character '1' one times.
 * This can be performed any number of times.
 *
 * A good string is a string constructed by the above process having a length between low and high (inclusive).
 *
 * Return the number of different good strings that can be constructed satisfying these properties. Since the answer can be large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: low = 3, high = 3, zero = 1, one = 1
 * Output: 8
 * Explanation:
 * One possible valid good string is "011".
 * It can be constructed as follows: "" -> "0" -> "01" -> "011".
 * All binary strings from "000" to "111" are good strings in this example.
 * Example 2:
 *
 * Input: low = 2, high = 3, zero = 1, one = 2
 * Output: 5
 * Explanation: The good strings are "00", "11", "000", "110", and "011".
 *
 *
 * Constraints:
 *
 * 1 <= low <= high <= 105
 * 1 <= zero, one <= low
 */

//todo:
//2466. Count Ways To Build Good Strings
public class N6238MCountWaysToBuildGoodStrings {


     static public void main(String... args) throws IOException{
         System.out.println(now());
         System.out.println("==================");
//
//        System.out.println(a);
//        System.out.println(A);
//        //doRun(0, 0);
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(long expect, int n) {
//        long res = new N2().n1(n);

//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    public int countGoodStrings(int low, int high, int zero, int one) {
        int MODULE = 1_000_000_007;
        return  1;
    }

}


