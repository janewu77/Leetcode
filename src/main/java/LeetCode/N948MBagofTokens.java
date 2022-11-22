package LeetCode;


import java.util.Arrays;

import static java.time.LocalTime.now;

/**
 * You have an initial power of power, an initial score of 0, and a bag of tokens
 * where tokens[i] is the value of the ith token (0-indexed).
 *
 * Your goal is to maximize your total score by potentially playing each token in one of two ways:
 *
 * If your current power is at least tokens[i], you may play the ith token face up,
 * losing tokens[i] power and gaining 1 score.
 * If your current score is at least 1, you may play the ith token face down, gaining tokens[i] power and losing 1 score.
 * Each token may be played at most once and in any order. You do not have to play all the tokens.
 *
 * Return the largest possible score you can achieve after playing any number of tokens.
 *
 *
 *
 * Example 1:
 *
 * Input: tokens = [100], power = 50
 * Output: 0
 * Explanation: Playing the only token in the bag is impossible because you either have too little power or too little score.
 * Example 2:
 *
 * Input: tokens = [100,200], power = 150
 * Output: 1
 * Explanation: Play the 0th token (100) face up, your power becomes 50 and score becomes 1.
 * There is no need to play the 1st token since you cannot play it face up to add to your score.
 * Example 3:
 *
 * Input: tokens = [100,200,300,400], power = 200
 * Output: 2
 * Explanation: Play the tokens in this order to get a score of 2:
 * 1. Play the 0th token (100) face up, your power becomes 100 and score becomes 1.
 * 2. Play the 3rd token (400) face down, your power becomes 500 and score becomes 0.
 * 3. Play the 1st token (200) face up, your power becomes 300 and score becomes 1.
 * 4. Play the 2nd token (300) face up, your power becomes 0 and score becomes 2.
 *
 *
 * Constraints:
 *
 * 0 <= tokens.length <= 1000
 * 0 <= tokens[i], power < 104
 */

/**
 * M - [time: 90-
 */
public class N948MBagofTokens {


    public static void main(String[] args){
        System.out.println(now());
        int[] data;

        data = new int[]{1,2,3,  4,5,10};
        doRun(4, data, 6);

        data = new int[]{100};
        doRun(0, data, 50);

        data = new int[]{100,200};
        doRun(1, data, 200);

        data = new int[]{100,200,300,400};
        doRun(2, data, 200);

        System.out.println(now());
        System.out.println("==================");

    }

    private static void doRun(int expected, int[] tokens, int power){
        int res = new N948MBagofTokens().bagOfTokensScore(tokens, power);
        System.out.println("["+(expected == res)+"].expected:"+ expected+".res:"+res);
    }

    //Runtime: 2 ms, faster than 100.00% of Java online submissions for Bag of Tokens.
    //Memory Usage: 41.6 MB, less than 97.19% of Java online submissions for Bag of Tokens.
    //Time: O(NLogN + N); Space: O(LogN)
    //Time: O(NLogN); Space: O(LogN)
    //let N be the number of tokens
    public int bagOfTokensScore(int[] tokens, int power) {
        //Time: O(NLogN); Space: O(LogN)
        Arrays.sort(tokens);

        int res = 0, score = 0;
        int left = 0, right = tokens.length - 1;
        //Time: O(N)
        while (left <= right) {
            if (power >= tokens[left]) {
                //buy at the cheapest
                power -= tokens[left++];
                res = Math.max(res, (++score));
            }else if (score >= 1){
                //sell at the most expensive.
                power += tokens[right--];
                score--;
            }else break;
        }
        return res;
    }


}
