package LeetCode;


import static java.time.LocalTime.now;

/**
 * You are playing a game that has n levels numbered from 0 to n - 1. You are given a 0-indexed integer array damage where damage[i] is the amount of health you will lose to complete the ith level.
 *
 * You are also given an integer armor. You may use your armor ability at most once during the game on any level which will protect you from at most armor damage.
 *
 * You must complete the levels in order and your health must be greater than 0 at all times to beat the game.
 *
 * Return the minimum health you need to start with to beat the game.
 *
 *
 *
 * Example 1:
 *
 * Input: damage = [2,7,4,3], armor = 4
 * Output: 13
 * Explanation: One optimal way to beat the game starting at 13 health is:
 * On round 1, take 2 damage. You have 13 - 2 = 11 health.
 * On round 2, take 7 damage. You have 11 - 7 = 4 health.
 * On round 3, use your armor to protect you from 4 damage. You have 4 - 0 = 4 health.
 * On round 4, take 3 damage. You have 4 - 3 = 1 health.
 * Note that 13 is the minimum health you need to start with to beat the game.
 * Example 2:
 *
 * Input: damage = [2,5,3,4], armor = 7
 * Output: 10
 * Explanation: One optimal way to beat the game starting at 10 health is:
 * On round 1, take 2 damage. You have 10 - 2 = 8 health.
 * On round 2, use your armor to protect you from 5 damage. You have 8 - 0 = 8 health.
 * On round 3, take 3 damage. You have 8 - 3 = 5 health.
 * On round 4, take 4 damage. You have 5 - 4 = 1 health.
 * Note that 10 is the minimum health you need to start with to beat the game.
 * Example 3:
 *
 * Input: damage = [3,3,3], armor = 0
 * Output: 10
 * Explanation: One optimal way to beat the game starting at 10 health is:
 * On round 1, take 3 damage. You have 10 - 3 = 7 health.
 * On round 2, take 3 damage. You have 7 - 3 = 4 health.
 * On round 3, take 3 damage. You have 4 - 3 = 1 health.
 * Note that you did not use your armor ability.
 *
 *
 * Constraints:
 *
 * n == damage.length
 * 1 <= n <= 105
 * 0 <= damage[i] <= 105
 * 0 <= armor <= 105
 */
public class N2214MMinimumHealthtoBeatGame {

    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");
        doRun(10, new int[]{2,5,3,4}, 7);
        doRun(10, new int[]{3, 3, 3}, 0);
        doRun(3, new int[]{3}, 1);
        doRun(22, new int[]{6,6,6,6}, 3);
        System.out.println(now());
        System.out.println("==================");
    }

    //93
    static private void doRun(long expect, int[] damage, int armor) {
        long res = new N2214MMinimumHealthtoBeatGame().minimumHealth(damage, armor);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //1.one pass
    //Runtime: 2ms 99%; Memory: 60.6MB 67%
    //Time: O(N); Space: O(1)
    public long minimumHealth(int[] damage, int armor) {
        long health = 0, max = 0;
        for (int i = 0; i < damage.length; i++) {
            health += damage[i];
            max = Math.max(max, damage[i]);
        }
        return health - Math.min(armor, max) + 1;
    }

}
