package LeetCode;

import java.util.HashMap;
import java.util.Map;

import static java.time.LocalTime.now;

/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 *
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given an integer, convert it to a roman numeral.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 3
 * Output: "III"
 * Explanation: 3 is represented as 3 ones.
 * Example 2:
 *
 * Input: num = 58
 * Output: "LVIII"
 * Explanation: L = 50, V = 5, III = 3.
 * Example 3:
 *
 * Input: num = 1994
 * Output: "MCMXCIV"
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 *
 *
 * Constraints:
 *
 * 1 <= num <= 3999
 */

/**
 * M - [time: 25-
 */
public class N12MIntegertoRoman {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun("MCMXCIV", 1994);
        doRun("LVIII", 58);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(String expect, int num) {
        String res = new N12MIntegertoRoman().intToRoman(num);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //Runtime: 6 ms, faster than 91.95% of Java online submissions for Integer to Roman.
    //Memory Usage: 41.8 MB, less than 96.31% of Java online submissions for Integer to Roman.
    //Time: O(logNum); Space:O(1)
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        helper(num, 1000, sb);
        helper(num % 1000, 100, sb);
        helper(num % 100, 10, sb);
        helper(num % 10, 1, sb);
        return sb.toString();
    }
    Map<Integer, String> map = new HashMap<Integer, String>(){{
        put(1, "I");
        put(5, "V");
        put(10, "X");
        put(50, "L");
        put(100, "C");
        put(500, "D");
        put(1000, "M");
    }};
    private void helper(int num,  int base, StringBuilder sb){
        int h = num / base;
        if (h == 9 || h == 4) {
            sb.append(map.get(base)).append(map.get((h + 1) * base));
            h = 0;
        }else if (h >= 5) {
            sb.append(map.get(5 * base));
            h -= 5;
        }
        for (int i = 0; i < h; i++) sb.append(map.get(base));
    }

}
