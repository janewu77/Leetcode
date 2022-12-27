package LeetCode;

import javafx.util.Pair;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.time.LocalTime.now;

/**
 * You have n bags numbered from 0 to n - 1. You are given two 0-indexed integer arrays capacity and rocks. The ith bag can hold a maximum of capacity[i] rocks and currently contains rocks[i] rocks. You are also given an integer additionalRocks, the number of additional rocks you can place in any of the bags.
 *
 * Return the maximum number of bags that could have full capacity after placing the additional rocks in some bags.
 *
 *
 *
 * Example 1:
 *
 * Input: capacity = [2,3,4,5], rocks = [1,2,4,4], additionalRocks = 2
 * Output: 3
 * Explanation:
 * Place 1 rock in bag 0 and 1 rock in bag 1.
 * The number of rocks in each bag are now [2,3,4,4].
 * Bags 0, 1, and 2 have full capacity.
 * There are 3 bags at full capacity, so we return 3.
 * It can be shown that it is not possible to have more than 3 bags at full capacity.
 * Note that there may be other ways of placing the rocks that result in an answer of 3.
 * Example 2:
 *
 * Input: capacity = [10,2,2], rocks = [2,2,0], additionalRocks = 100
 * Output: 3
 * Explanation:
 * Place 8 rocks in bag 0 and 2 rocks in bag 2.
 * The number of rocks in each bag are now [10,2,2].
 * Bags 0, 1, and 2 have full capacity.
 * There are 3 bags at full capacity, so we return 3.
 * It can be shown that it is not possible to have more than 3 bags at full capacity.
 * Note that we did not use all of the additional rocks.
 *
 *
 * Constraints:
 *
 * n == capacity.length == rocks.length
 * 1 <= n <= 5 * 104
 * 1 <= capacity[i] <= 109
 * 0 <= rocks[i] <= capacity[i]
 * 1 <= additionalRocks <= 109
 */
public class N2279MMaximumBagsWithFullCapacityofRocks {



    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");
        int[] capacity;
        int[] rocks;

        capacity = new int[]{53,45,74,29,63,7,12,58,20,35,10,53,30,97,71,79,9,84,77,26,32,12,13,11,39,67,76,59,68,21,55,3,27,11,67,3,22,33,12,2,47,40,62,74,58,44,42,72,8,75,31,18,6,92,42,14,70,31,33,84,54,7,77,72,95,2,86,51,91,34,53,18,77,32,47,61,87,83,14,62,79,61,75,9,10,48,91,52,81,97,19,63,63,98,48,67,48,32,33,40};
        rocks = new int[]{30,42,45,19,61,5,3,26,5,33,2,39,23,58,50,70,0,75,22,13,4,1,1,11,19,10,46,14,59,5,23,3,24,7,36,2,13,30,11,1,0,40,46,29,9,9,18,34,3,62,1,7,5,19,26,10,24,27,25,15,31,1,52,31,85,0,3,0,8,26,0,9,49,12,45,21,80,7,10,33,31,43,75,9,6,17,4,15,40,70,10,19,59,24,37,29,17,8,7,35};
        doRun(12, capacity, rocks, 10);

        capacity = new int[]{54,18,91,49,51,45,58,54,47,91,90,20,85,20,90,49,10,84,59,29,40,9,100,1,64,71,30,46,91};
        rocks = new int[]{14,13,16,44,8,20,51,15,46,76,51,20,77,13,14,35,6,34,34,13,3,8,1,1,61,5,2,15,18};
        doRun(13, capacity, rocks, 77);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[] capacity, int[] rocks, int additionalRocks) {
        int res = new N2279MMaximumBagsWithFullCapacityofRocks().maximumBags(capacity, rocks, additionalRocks);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //1.Sort
    //Runtime: 14 ms 99%; Memory: 52.9 MB 81%
    //Time: O(N * logN); Space: (logN)
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        for (int i = 0; i < capacity.length; i++)
            capacity[i] -= rocks[i];

        Arrays.sort(capacity);

        int count = 0;
        for (int i = 0; i < capacity.length; i++) {
            additionalRocks -= capacity[i];
            if (additionalRocks < 0) break;
            count++;
        }
        return count;
    }


}
