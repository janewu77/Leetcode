package LeetCode;

import utils.comm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.LocalTime.now;

/**
 * Given an array of unique integers, arr, where each integer arr[i] is strictly greater than 1.
 *
 * We make a binary tree using these integers, and each number may be used for any number of times.
 * Each non-leaf node's value should be equal to the product of the values of its children.
 *
 * Return the number of binary trees we can make. The answer may be too large so return the answer modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [2,4]
 * Output: 3
 * Explanation: We can make these trees: [2], [4], [4, 2, 2]
 * Example 2:
 *
 * Input: arr = [2,4,5,10]
 * Output: 7
 * Explanation: We can make these trees: [2], [4], [5], [10], [4, 2, 2], [10, 2, 5], [10, 5, 2].
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 1000
 * 2 <= arr[i] <= 109
 * All the values of arr are unique.
 */
public class N823MBinaryTreesWithFactors {

    public static void main(String[] args){
        int[] data;

        System.out.println(now());

        data = new int[]{4};
        doRun_demo(1, data);

        data = new int[]{2,4};
        doRun_demo(3, data);

        data = new int[]{2,4,5,10};
        doRun_demo(7, data);

        data = new int[]{18,3,6,2};
        doRun_demo(12, data);

        data = new int[]{2,4,8};
        doRun_demo(8, data);

        data = new int[]{2,4,8,16};
        doRun_demo(23, data);

        data = new int[]{45,42,2,18,23,1170,12,41,40,9,47,24,33,28,10,32,29,17,46,11,759,37,6,26,21,49,31,14,19,8,13,7,27,22,3,36,34,38,39,30,43,15,4,16,35,25,20,44,5,48};
        doRun_demo(777, data);

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun_demo(int expect, int[] arr) {
        int res = new N823MBinaryTreesWithFactors().numFactoredBinaryTrees(arr);
//        String res = comm.toString(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 31 ms, faster than 89.02% of Java online submissions for Binary Trees With Factors.
    //Memory Usage: 42.4 MB, less than 93.06% of Java online submissions for Binary Trees With Factors.
    //DP: bottom to up
    //Time: O(N*N);Space: O(N)
    private final static int MODULO = 1_000_000_007;
    public int numFactoredBinaryTrees(int[] arr) {
        //Time: O(NlgN)
        Arrays.sort(arr);

        //Space: O(N)
        Map<Integer, Long> map = new HashMap<>(); //factor: counts
        for (int n: arr) map.put(n, 1l);

        long res = map.get(arr[0]);
        //Time: O(N*N)
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] % arr[j] == 0) {
                    int div = arr[i] / arr[j];
                    if (map.containsKey(div))
                        map.put(arr[i], (map.get(arr[i]) + map.get(div) * map.get(arr[j])));
                }
            }
            res += map.get(arr[i]);
        }
        return (int)(res % MODULO);
    }

//    private void helper_backtracking(int[] arr, int begin, Map<Integer, Integer> map, int currFactor){
//
//        for (int i = begin; i < arr.length; i++){
//            if (map.containsKey(arr[i] * currFactor)){
//                map.put(arr[i] * currFactor,  map.get(arr[i] * currFactor) + 1);
//            }
//        }
//
//    }

}
