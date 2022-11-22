package LeetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.time.LocalTime.now;

/**
 * You are given an integer n. We reorder the digits in any order (including the original order) such that the leading digit is not zero.
 *
 * Return true if and only if we can do this so that the resulting number is a power of two.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: true
 * Example 2:
 *
 * Input: n = 10
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= n <= 109
 */

/**
 * M - [time: 30-
 */
public class N869MReorderedPowerof2 {


    public static void main(String[] args) {

        System.out.println(now());
        int[][] data;

//        doRun_isPowerOf2(true, 1);
//        doRun_isPowerOf2(true, 2);
//        doRun_isPowerOf2(true, 4);
//        doRun_isPowerOf2(true, 8);
//        doRun_isPowerOf2(false, 6);
//        doRun_isPowerOf2(true, 16);
//        doRun_isPowerOf2(false, 5);
//        doRun_isPowerOf2(false, 1_000_000_000);

//        doRun_isPowerOf2(false, 610);
        doRun(false, 610);
        doRun(true, 61);
        doRun(false, 10);
        doRun(true, 1);

        doRun(true, 2410);
        doRun(false, 24100);

        System.out.println(now());
        System.out.println("==================");
    }
    static private void doRun(boolean expect, int n) {
        boolean res = new N869MReorderedPowerof2().reorderedPowerOf2(n);
//        String res = comm.toString(res1);
//        String res = Arrays.stream(res1).mapToObj(i-> String.valueOf(i)).collect(Collectors.joining(","));
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    static private void doRun_isPowerOf2(boolean expect, int n) {
        boolean res = new N869MReorderedPowerof2().isPowerOf2(n);
//        String res = comm.toString(res1);
//        String res = Arrays.stream(res1).mapToObj(i-> String.valueOf(i)).collect(Collectors.joining(","));
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Reordered Power of 2.
    //Memory Usage: 41.4 MB, less than 39.33% of Java online submissions for Reordered Power of 2.
    //counter & compare
    //Time: O(lgN * logN); Space: O(1)
    //Time: O(logN + lgN * logN); Space: O(log10)
    public boolean reorderedPowerOf2(int n) {
        //Time: O(logN)
        int[] counterN = convert_2(n); //Space: O(1)
        int x = 1;
        //Time: O(lgN * logN)
        while (x < 1_000_000_000) {
            if (Arrays.equals(counterN, convert_2(x))) return true;
            x <<= 1;
        }
        return false;
    }

    //Time: O(logN); Space: O(1)
    private int[] convert_2(int n){
        int[] counter = new int[10]; //Space: O(10)
        while (n > 0) {
            counter[n % 10]++;
            n /= 10;
        }
        return counter;
    }


    //1.compute the power of 2 (less than 1_000_000_001 by using x <<= 1)
    //2.covert every power of 2 to a string by counting and put them into a set.
    //3.covert the n in the same way
    //4.check the set does contain the number or not.
    //Runtime: 4 ms, faster than 30.67% of Java online submissions for Reordered Power of 2.
    //Memory Usage: 42.3 MB, less than 6.00% of Java online submissions for Reordered Power of 2.
    //computer & covert to string & compare
    //Time: O(lgN * LogN + logN); Space: O(lgN)
    public boolean reorderedPowerOf2_1(int n) {

        Set<String> powerOf2Set = new HashSet<>();
        //Time: O(logN); Space: O(lgN)
        powerOf2Set.add(convert_1(1));
        int x = 1;
        //the power of 2 which less than 1_000_000_001
        //Time: O(lgN * logN); Space: O(1)
        while (x < 1_000_000_001) powerOf2Set.add(convert_1(x <<= 1));

        return powerOf2Set.contains(convert_1(n));
    }

    //Time: O(logN); Space: O(1)
    //covert a number to string by counting it's digits.
    //ex:  123 : 0111000000; 144: 0100200000; 1024:  1110100000
    private String convert_1(int n){
        int[] counter = new int[10];//Space: O(10)
        //Time: O(logN);
        while (n > 0) {
            counter[n % 10]++;
            n /= 10;
        }
        StringBuilder sb = new StringBuilder();
        //Time: O(10);
        for (int v : counter)  sb.append(v);//.append('_');
        return sb.toString();
    }

    //Runtime: 4 ms, faster than 30.67% of Java online submissions for Reordered Power of 2.
    //Memory Usage: 41.9 MB, less than 16.00% of Java online submissions for Reordered Power of 2.
    //Time: O(31 + logN + 10); Space: O(31 + 10 + N)
    //computer & covert to string & compare
    //多线程
    //Time: O(lgN); Space: O(lgN)
    public boolean reorderedPowerOf2_X(int n) {
       initSet();
       return powerOf2Set.contains(convert(n));
    }

    //Time: O(lgN); Space: O(lgN)
    private Set<String> powerOf2Set;//Space: O(lgN)
    private volatile byte flag = 0;
    void initSet(){
        if (flag == 1) return;
        powerOf2Set = new HashSet<>();
        powerOf2Set.add(convert(1));
        int x = 1;
        while (x < 1_000_000_001) powerOf2Set.add(convert(x *= 2));
        flag = 1;
    }

    //Time: O(logN); Space: O(1)
    private String convert(int n){
        int[] counter = new int[10]; //Space: O(10)
        //Time: O(logN)
        while (n > 0) {
            counter[n % 10]++;
            n /= 10;
        }
        //Time: O(10)
        StringBuilder sb = new StringBuilder();
        for (int v : counter)  sb.append(v);//.append('_');
        return sb.toString();
    }

    private boolean isPowerOf2(int n){
        return (n & (n - 1)) == 0;
    }

}
