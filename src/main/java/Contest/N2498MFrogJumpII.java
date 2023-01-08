package Contest;


import java.util.Arrays;

import static java.time.LocalTime.now;

/**
 * You are given a 0-indexed integer array stones sorted in strictly increasing order representing the positions of stones in a river.
 *
 * A frog, initially on the first stone, wants to travel to the last stone and then return to the first stone. However, it can jump to any stone at most once.
 *
 * The length of a jump is the absolute difference between the position of the stone the frog is currently on and the position of the stone to which the frog jumps.
 *
 * More formally, if the frog is at stones[i] and is jumping to stones[j], the length of the jump is |stones[i] - stones[j]|.
 * The cost of a path is the maximum length of a jump among all jumps in the path.
 *
 * Return the minimum cost of a path for the frog.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: stones = [0,2,5,6,7]
 * Output: 5
 * Explanation: The above figure represents one of the optimal paths the frog can take.
 * The cost of this path is 5, which is the maximum length of a jump.
 * Since it is not possible to achieve a cost of less than 5, we return it.
 * Example 2:
 *
 *
 * Input: stones = [0,3,9]
 * Output: 9
 * Explanation:
 * The frog can jump directly to the last stone and come back to the first stone.
 * In this case, the length of each jump will be 9. The cost for the path will be max(9, 9) = 9.
 * It can be shown that this is the minimum achievable cost.
 *
 *
 * Constraints:
 *
 * 2 <= stones.length <= 105
 * 0 <= stones[i] <= 109
 * stones[0] == 0
 * stones is sorted in a strictly increasing order.
 */
public class N2498MFrogJumpII {

    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");

        doRun(4, new int[]{0,1,4,7,8});

        doRun(5, new int[]{0,2,5,6,7});

        doRun(9, new int[]{0,3,9});
        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, int[] stones) {
        int res = new N2498MFrogJumpII().maxJump(stones);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //2. one pass
    //Runtime: 2ms 99%; Memory: 55MB 98%
    //Time: O(N); Space: O(1)
    public int maxJump(int[] stones) {
        int res = stones[1] - stones[0];
        for (int i = 2; i < stones.length; i++)
            res = Math.max(res, stones[i] - stones[i - 2]);
        return res;
    }


    //1.Binary search
    //Runtime: 94ms 5%; Memory: 60MB 61%
    //Time: O( (logMax) * N); Space: O(N)
    public int maxJump_1(int[] stones) {
        int left = stones[1], right = stones[stones.length - 1];

        while (left <= right) {
            int mid = (left + right) >> 1;
            if (helper(stones, mid)) right = mid - 1;
            else left = mid + 1;
        }
        return left;
    }

    private boolean helper(int[] stones, int x){
        int[] memo = new int[stones.length];

        int idx = stones[0];
        while (idx < stones.length - 1){
            int nextStone = Math.min(stones[idx] + x, stones[stones.length - 1]);
            int nextIdx = Arrays.binarySearch(stones, nextStone);
            if (nextIdx < 0) nextIdx = -nextIdx - 1 - 1;
            while (memo[nextIdx] == 1 && nextIdx > idx) nextIdx--;
            if (nextIdx == idx) return false;

            memo[nextIdx] = 1;
            idx = nextIdx;
        }

        //idx = stones.length - 1;
        for (int i = stones.length - 2; i >= 0; i--) {
            if (memo[i] == 1) continue;
            if (stones[idx] - stones[i] > x) return false;
            idx = i;
        }
        return true;
    }
}
