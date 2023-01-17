package LeetCode;


import java.util.*;
import java.util.concurrent.DelayQueue;

import static java.time.LocalTime.now;

/**
 * A binary string is monotone increasing if it consists of some number of 0's (possibly none), followed by some number of 1's (also possibly none).
 *
 * You are given a binary string s. You can flip s[i] changing it from 0 to 1 or from 1 to 0.
 *
 * Return the minimum number of flips to make s monotone increasing.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "00110"
 * Output: 1
 * Explanation: We flip the last digit to get 00111.
 * Example 2:
 *
 * Input: s = "010110"
 * Output: 2
 * Explanation: We flip to get 011111, or alternatively 000111.
 * Example 3:
 *
 * Input: s = "00011000"
 * Output: 2
 * Explanation: We flip to get 00000000.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s[i] is either '0' or '1'.
 */
public class N926MFlipStringtoMonotoneIncreasing {


    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");

        doRun(1, "01110");
        doRun(5, "10011111110010111011");
        doRun(1, "00110");
        doRun(3, "0101100011");

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, String s) {
        int res = new N926MFlipStringtoMonotoneIncreasing().minFlipsMonoIncr(s);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //5.DP without array
    //Runtime: 9ms 92%; Memory: 42.7MB 89%
    //Time: O(N); Space: O(1)
    public int minFlipsMonoIncr(String s) {
        int dp0 = 0, dp1 = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0')
                dp1 = Math.min(dp1 + 1, dp0);
            else dp0++;
        }
        return dp1;//Math.min(dp0, dp1);
    }

    //4.DP bottom-up
    //Runtime: 41ms 13%; Memory: 50.3MB 36%
    //Time: O(N); Space: O(N)
    public int minFlipsMonoIncr_4(String s) {
        int[][] dp = new int[s.length()][2];

        if (s.charAt(0) == '1')
            dp[0][0] = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = Math.min(dp[i - 1][1] + 1, dp[i - 1][0]);
            }else{
                dp[i][0] = dp[i - 1][0] + 1;
                dp[i][1] = dp[i - 1][1];
            }
        }
        return Math.min(dp[s.length() - 1][0], dp[s.length() - 1][1]);
    }


    //3.prefix sum (improved the second loop)
    //Runtime: 7ms 99%; Memory: 43.2MB 51%
    //Time: O(N); Space: O(N)
    public int minFlipsMonoIncr_3(String s) {
        int n = s.length();
        int[] counter = new int[n]; //left 1

        //forward
        int count = 0;
        for (int i = 0; i < n; i++)
            counter[i] = (count += s.charAt(i) - '0');

        int res = Math.min(counter[n - 1], n - counter[n - 1]);
        for (int i = 0; i < n; i++) {
            //n - 1 - i : the number of char after i;
            //counter[n - 1] - counter[i]: the number of one after i;
            //(n - 1 - i - (counter[n - 1] - counter[i])): the number of zero after i
            res = Math.min(res, counter[i] + (n - 1 - i - (counter[n - 1] - counter[i])));
        }
        return res;
    }

    //2.prefix sum
    //Runtime: 15ms 61%; Memory: 44MB 47%
    //Time: O(N); Space: O(N)
    public int minFlipsMonoIncr_2(String s) {
        int n = s.length();
        int[] counter1 = new int[n];
        List<int[]> listZeroRange = new ArrayList();

        int left = -1, right = -1;
        if (s.charAt(0) == '1') counter1[0] = 1;
        else left = right = 0;

        //forward
        for (int i = 1; i < n; i++) {
            counter1[i] = counter1[i - 1];
            if (s.charAt(i) == '1') {
                if (left != -1) {
                    listZeroRange.add(new int[]{left, right});
                    left = right = -1;
                }
                counter1[i]++;
            }else {
                if (left == -1) left = right = i;
                else right = i;
            }
        }
        if (left != -1) listZeroRange.add(new int[]{left, right});

        int res = Math.min(counter1[n - 1], n - counter1[n - 1]);
        if (res == 0) return res;

        int count0 = 0;
        for (int i = listZeroRange.size() - 1; i >= 0; i--) {
            int[] range = listZeroRange.get(i);
            if (count0 > res) break;
            res = Math.min(res, counter1[range[0]] + count0);
            count0 += range[1] - range[0] + 1;
        }
        return res;
    }

    //1.prefix sum
    //Runtime: 18ms 45%; Memory: 42.8MB 84%
    //Time: O(N); Space: O(N)
    public int minFlipsMonoIncr_1(String s) {
        int n = s.length();
        int[] counter = new int[n]; //left 1

        //forward
        int count = 0;
        for (int i = 0; i < n; i++)
            counter[i] = (count += s.charAt(i) - '0');//count 1

        int res = Math.min(counter[n - 1], n - counter[n - 1]);
        if (res == 0) return res;

        //backward: count 0
        int count0 = s.charAt(n - 1) == '0' ? 1 : 0;
        for (int i = n - 2; i >= 0; i--) {
            if (counter[i] > res){
                count0 = s.charAt(i) == '0' ? count0 + 1 : count0;
                continue;
            }

            if (s.charAt(i) == '0') {
                int left = i;
                while(left - 1 >= 0 && s.charAt(left - 1) == '0') left--;

                res = Math.min(res, counter[left] + count0);
                count0 += i - left + 1;
                i = left;
                if (count0 > res) break;
            }
        }
        return res;
    }

}
