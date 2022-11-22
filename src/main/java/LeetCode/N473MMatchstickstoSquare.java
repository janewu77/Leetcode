package LeetCode;

import java.util.*;

/**
 *
 * You are given an integer array matchsticks where matchsticks[i] is the length of the ith matchstick.
 * You want to use all the matchsticks to make one square.
 * You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.
 *
 * Return true if you can make this square and false otherwise.
 *
 * Example 1:
 * Input: matchsticks = [1,1,2,2,2]
 * Output: true
 * Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
 *
 *
 * Example 2:
 * Input: matchsticks = [3,3,3,3,4]
 * Output: false
 * Explanation: You cannot find a way to form a square with all the matchsticks.
 *
 *
 * Constraints:
 * 1 <= matchsticks.length <= 15
 * 1 <= matchsticks[i] <= 108
 *
 */

/**
 * M - 耗时 （120+）
 *
 *  - DFS & DP 都没做出来。学习了。
 *
 *
 */
public class N473MMatchstickstoSquare {

    static int c = 1;
    public static void main(String[] args){
        int[] data;

        data = new int[]{1,1,1,1};
        doRun(data, true);

        data = new int[]{1,1,1};
        doRun(data, false);

        data = new int[]{1,1};
        doRun(data, false);

        data = new int[]{1, 4,4, 4,4, 4,4, 7};
        doRun(data, true);

        data = new int[]{2, 4,4, 4,4, 4,4, 7};
        doRun(data, false);

        data = new int[]{3,3,3,3,4};
        doRun(data, false);

        data = new int[]{1,1,2,2,2};
        doRun(data, true);

        data = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        doRun(data, true);
        data = new int[]{13,11,1,8,6,7,8,8,6,7,8,9,8};
        doRun(data, true);

        data = new int[]{5,5,5,5,4,4,4,4,3,3,3,3};
        doRun(data, true);

    }

    private static void doRun(int[] data, boolean expected){
        boolean result = new N473MMatchstickstoSquare().makesquare(data);
        System.out.println("["+(expected == result)+"]"+(c++)+". expected:"+expected +". result:"+result);
    }


    //Runtime: 58 ms, faster than 77.07% of Java online submissions for Matchsticks to Square.
    //Memory Usage: 44.1 MB, less than 17.62% of Java online submissions for Matchsticks to Square.
    //Dynamic Programming
    public boolean makesquare(int[] matchsticks) {
        if (matchsticks.length < 4) return false;
        int total = 0;
        for(int m : matchsticks) total += m;
        if (total % 4 != 0) return false;
        memo = new HashMap<>();
        Arrays.sort(matchsticks);
        total /= 4;
        if (matchsticks[matchsticks.length-1] > total) return false;

        //int mask = (1 << matchsticks.length) - 1;
        return doDP(matchsticks, total, (1 << matchsticks.length) - 1, 0);
    }

    public HashMap<Integer, Boolean> memo = null;
    private boolean doDP(int[] data, int sidelen, int mask, int sides){
        int mKey = mask * 10 + sides;
        if (memo.containsKey(mKey)) return memo.get(mKey);

        int currSum = 0;
        int c = 0;
        for(int i = 0; i < data.length; i++){
            if ((mask & (1 << i)) == 0) {
                currSum += data[i];
                c++;
            }
        }
        if (currSum > 0 && currSum % sidelen == 0) sides++;
        if (sides == 3) return true;

        int currKey = mask * 10 + sides;
        if (data.length - c < 4 - sides){
            //余下的个数 小于 需要完成的边数
            memo.put(currKey, false);
            return false;
        }

        // 将空位至0，然后继续
        int remind = sidelen * ((currSum / sidelen) +1) - currSum;
        for(int i = data.length - 1; i >= 0; i--){
            if ((mask & (1 << i)) != 0 && data[i] <= remind){
                //mask ^ (1 << i); mask & ~(1 << i)
                if (doDP(data, sidelen, mask ^ (1 << i), sides))  return true;
            }
        }
        memo.put(currKey, false);
        return false;
    }


    //Runtime: 133 ms, faster than 60.10% of Java online submissions for Matchsticks to Square.
    //Memory Usage: 40 MB, less than 93.91% of Java online submissions for Matchsticks to Square.
    //DFS
    public boolean makesquare1(int[] matchsticks) {
        if (matchsticks.length < 4) return false;

        int total = 0;
        for(int m : matchsticks) total += m;
        Arrays.sort(matchsticks);
        if (total % 4 != 0 || matchsticks[matchsticks.length-1] > total / 4) return false;

        return doDFS(matchsticks, total / 4, matchsticks.length - 1, new int[]{0,0,0,0} );
    }

    private boolean doDFS(int[] data, int sideLen, int idx, int[] sums){
        if (idx < 0 )
            return sums[0] == sums[1] && sums[1] == sums[2] && sums[2] == sums[3];

        int n = data[idx];
        for(int i = 0; i < 4; i++) {
            if (sums[i] + n <= sideLen) {
                sums[i] += n;
                if (this.doDFS(data, sideLen,idx - 1, sums))  return true;
                sums[i] -= n;
            }
        }// End for
        return false;
    }

}
