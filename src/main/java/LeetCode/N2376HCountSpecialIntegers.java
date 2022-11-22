package LeetCode;


import java.util.*;

import static java.time.LocalTime.now;

/**
 * We call a positive integer special if all of its digits are distinct.
 *
 * Given a positive integer n, return the number of special integers that belong to the interval [1, n].
 *
 *
 *
 * Example 1:
 *
 * Input: n = 20
 * Output: 19
 * Explanation: All the integers from 1 to 20, except 11, are special. Thus, there are 19 special integers.
 * Example 2:
 *
 * Input: n = 5
 * Output: 5
 * Explanation: All the integers from 1 to 5 are special.
 * Example 3:
 *
 * Input: n = 135
 * Output: 110
 * Explanation: There are 110 integers from 1 to 135 that are special.
 * Some of the integers that are not special are: 22, 114, and 131.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2 * 109
 */

/**
 * H - [time: 120+
 */
public class N2376HCountSpecialIntegers {

    public static void main(String[] args) {

        System.out.println(now());
        int[][] data;
//        doRun_helper1(1,9, 0 );
//        doRun_helper1(9,9, 1 );
//        doRun_helper1(9*8,9,2);
//        doRun_helper1(9*8*7,9,3);
//        doRun_helper1(9*8*7*6,9,4);

        System.out.println("========doRun_demo==========");

        doRun_demo(90, 100);
        doRun_demo(10, 10);
        doRun_demo(19, 20);

        doRun_demo(20, 22);

        doRun_demo(288, 376);
        doRun_demo(178, 225);
        doRun_demo(90, 99);
        doRun_demo(288, 376);
        doRun_demo(254, 325);
        doRun_demo(551, 735);
        doRun_demo(110, 135);
        doRun_demo(9*9*8 + 81 + 9, 1000);

        doRun_demo(11, 12);
        doRun_demo(9, 9);
        doRun_demo(5, 5);

        System.out.println(now());
        System.out.println("==================");

    }

    static private void doRun_demo(int expect, int n) {
        int res = new N2376HCountSpecialIntegers().countSpecialNumbers(n);
//        String res = comm.toString(res1);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    static private void doRun_helper1(int expect, int n, int c) {
        int res = new N2376HCountSpecialIntegers().helper(n, c);
//        String res = comm.toString(res1);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }


    //Transform N to arrayList
    //Count the number with digits < n
    //Count the number with same prefix
    //3 4 6 5
    //X                    ==>  9
    //XX                   ==>  9 * 8
    //XXX                  ==>  9 * 8 * 7
    //1 X X X - 2 X X X    ==>  2 * 9 * 8 * 7
    //3 0 X X - 3 3 X X    ==>  1 * 3 * 8 * 7
    //3 4 0 X - 3 4 5 X    ==>  1 * 1 * 4 * 7
    //3 4 6 0 - 3 4 6 5    ==>  1 * 1 * 1 * 3
    //Runtime: 1 ms, faster than 97.68% of Java online submissions for Count Special Integers.
    //Memory Usage: 39.2 MB, less than 95.19% of Java online submissions for Count Special Integers.
    //Math
    //Time: O(lgN + lgN * lgN); Space: O(lgN)
    public int countSpecialNumbers(int n) {
        //Transform N to arrayList
        //N + 1 is in order to handle the last one digit.
        //Time: O(lgN); Space: O(lgN)
        List<Integer> list = new ArrayList<>();
        for (int x = n + 1; x > 0; x /= 10) list.add(x % 10);

        int res = 0;
        //the number of digits < N
        //Time: O(lgN * lgN)
        for (int i = 0; i < list.size() - 1 ; i++)
            res += 9 * helper(9, i);

        //he number of digits == N
        //Time: O(lgN * lgN); Space:O(lgN)
        Set<Integer> seen = new HashSet<>();
        int listSize = list.size();
        for (int i = 1; i <= listSize; i++) {
            int num = list.get(listSize - i);
            if (i == 1) num--;

//            int lessCount = 0;
//            for (int j = 1; j < i; j++)
//                 if (list.get(listSize - j) < num) lessCount++;
//            if (num > lessCount)
//                res += (num - lessCount) * helper(10 - i , listSize - i);

            for (int j = 0; j < num; j++)
                if (!seen.contains(j))
                    res += helper(10 - i , listSize - i);

            if (!seen.add(list.get(listSize - i))) break;
        }
        return res;
    }

    //9*8*7...
    //Time:O(C)
    private int helper(int n, int c){
        if (c == 0) return 1;
        int res = n--;
        while (--c > 0) res *= (n--);
        return res;
        // return c == 0 ? 1 : helper(n, c - 1) * (n - c + 1);
    }

}
