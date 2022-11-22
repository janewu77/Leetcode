package LeetCode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.time.LocalTime.now;

/**
 * The count-and-say sequence is a sequence of digit strings defined by the recursive formula:
 *
 * countAndSay(1) = "1"
 * countAndSay(n) is the way you would "say" the digit string from countAndSay(n-1), which is then converted into a different digit string.
 * To determine how you "say" a digit string, split it into the minimal number of substrings such that each substring contains exactly one unique digit. Then for each substring, say the number of digits, then say the digit. Finally, concatenate every said digit.
 *
 * For example, the saying and conversion for digit string "3322251":
 *
 *
 * Given a positive integer n, return the nth term of the count-and-say sequence.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: "1"
 * Explanation: This is the base case.
 * Example 2:
 *
 * Input: n = 4
 * Output: "1211"
 * Explanation:
 * countAndSay(1) = "1"
 * countAndSay(2) = say "1" = one 1 = "11"
 * countAndSay(3) = say "11" = two 1's = "21"
 * countAndSay(4) = say "21" = one 2 + one 1 = "12" + "11" = "1211"
 *
 *
 * Constraints:
 *
 * 1 <= n <= 30
 */

/**
 * M - [time: 25-
 */
public class N38MCountandSay {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;


        doRun("1211",4);
        doRun("1",1);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(String expect, int n) {
        String res = new N38MCountandSay().countAndSay(n);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //4.regulation
    //Runtime: 26 ms, faster than 35.45% of Java online submissions for Count and Say.
    //Memory Usage: 42.9 MB, less than 44.11% of Java online submissions for Count and Say.
    public String countAndSay(int n) {
        StringBuilder currStr = new StringBuilder("1");
        Pattern pattern = Pattern.compile("(.)\\1*");
        for (int i = 1; i < n; ++i) {
            Matcher m = pattern.matcher(currStr);
            StringBuilder sb = new StringBuilder();
            while (m.find())
                sb.append(m.group().length() + String.valueOf(m.group().charAt(0)));
            currStr = sb;
        }
        return currStr.toString();
    }

    //3.iteration
    //Runtime: 9 ms, faster than 56.22% of Java online submissions for Count and Say.
    //Memory Usage: 41.3 MB, less than 86.39% of Java online submissions for Count and Say.
    public String countAndSay_3(int n) {
        StringBuilder currStr = new StringBuilder("1");

        for (int k = 0; k < n - 1; k++) {
            StringBuilder sb = new StringBuilder();
            int count = 1;
            for (int i = 1; i < currStr.length(); i++) {
                if (currStr.charAt(i) == currStr.charAt(i - 1)) count++;
                else{
                    sb.append(count).append(currStr.charAt(i - 1));
                    count = 1;
                }
            }
            sb.append(count).append(currStr.charAt(currStr.length()-1));
            currStr = sb;
        }
        return currStr.toString();
    }

    //2.recursion
    //Runtime: 3 ms, faster than 93.92% of Java online submissions for Count and Say.
    //Memory Usage: 41.4 MB, less than 83.82% of Java online submissions for Count and Say.
    public String countAndSay_2(int n) {
        if (n == 1) return "1";
        String s = countAndSay_2(n - 1);

        //say
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) count++;
            else{
                sb.append(count).append(s.charAt(i - 1));
                count = 1;
            }
        }
        sb.append(count).append(s.charAt(s.length() - 1));
        return sb.toString();
    }

    //1.two helpers
    //Runtime: 11 ms, faster than 51.50% of Java online submissions for Count and Say.
    //Memory Usage: 46.9 MB, less than 30.84% of Java online submissions for Count and Say.
    // Time: O(4^(n/3))
    public String countAndSay_1(int n) {
        if (n == 1) return "1";
        String x = "1";
        for (int i = 0; i < n - 1; i++)
            x = helper_say(helper_count(x));
        return x;
    }

    private List<String[]> helper_count(String s){
        List<String[]> list = new ArrayList<>();

        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) count++;
            else{
                list.add(new String[]{String.valueOf(s.charAt(i - 1)), String.valueOf(count)});
                count = 1;
            }
        }
        list.add(new String[]{String.valueOf(s.charAt(s.length() - 1)), String.valueOf(count)});
        return list;
    }

    private String helper_say(List<String[]> list){
        StringBuilder sb = new StringBuilder();
        for (String[] node: list)
            sb.append(node[1]).append(node[0]);
        return sb.toString();
    }
}
