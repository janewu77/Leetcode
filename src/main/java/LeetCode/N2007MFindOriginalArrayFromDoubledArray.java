package LeetCode;


import javafx.util.Pair;
import utils.comm;

import java.util.*;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

/**
 * An integer array original is transformed into a doubled array changed by appending twice the value of every element in original, and then randomly shuffling the resulting array.
 *
 * Given an array changed, return original if changed is a doubled array. If changed is not a doubled array, return an empty array. The elements in original may be returned in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: changed = [1,3,4,2,6,8]
 * Output: [1,3,4]
 * Explanation: One possible original array could be [1,3,4]:
 * - Twice the value of 1 is 1 * 2 = 2.
 * - Twice the value of 3 is 3 * 2 = 6.
 * - Twice the value of 4 is 4 * 2 = 8.
 * Other original arrays could be [4,3,1] or [3,1,4].
 * Example 2:
 *
 * Input: changed = [6,3,0,1]
 * Output: []
 * Explanation: changed is not a doubled array.
 * Example 3:
 *
 * Input: changed = [1]
 * Output: []
 * Explanation: changed is not a doubled array.
 *
 *
 * Constraints:
 *
 * 1 <= changed.length <= 105
 * 0 <= changed[i] <= 105
 */


public class N2007MFindOriginalArrayFromDoubledArray {

    public static void main(String[] args){
        System.out.println(now());
        int[] data;

        data = new int[]{0,0,5,10,0,0};
        doRun("0,0,5", data);

        data = new int[]{3,3,3,3};
        doRun("", data);

        data = new int[]{5,8,7,8,16,5,16,14,10,10};
        doRun("5,5,7,8,8", data);


        data = new int[]{5,7,2,10,4,2,7,14};
        doRun("", data);

        data = new int[]{0,0,0,0};
        doRun("0,0", data);

        data = new int[]{1,3,4,2,6,8,4,8};
        doRun("1,3,4,4", data);

        data = new int[]{1,3,4,2,6,8};
        doRun("1,3,4", data);

        data = new int[]{6,3,0,1};
        doRun("", data);

        data = new int[]{1};
        doRun("", data);



        System.out.println(now());
        System.out.println("==================");
    }

    private static void doRun(String expected, int[] changed){
        int[] res1 = new N2007MFindOriginalArrayFromDoubledArray().findOriginalArray(changed);
        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
        System.out.println("["+(expected.equals(res))+"]expect:" + expected + " res:" + res);
//        System.out.println("["+(expected == res)+"].expected:"+ expected+".res:"+res);
    }

    //Runtime: 13 ms, faster than 96.41% of Java online submissions for Find Original Array From Doubled Array.
    //Memory Usage: 123.8 MB, less than 85.93% of Java online submissions for Find Original Array From Doubled Array.
    //计数排序 counting sort
    //Time: O(N + N + MaxNum); Space: O(MaxNum)
    //MaxNum is the maximum number in the input.
    public int[] findOriginalArray3(int[] changed) {
        if (changed.length % 2 == 1) return new int[0];

        int numMax = 0; //maximum number in the input
        for (int n : changed) numMax = Math.max(n, numMax);
        int[] freq = new int[numMax + 1];
        for (int n : changed) freq[n]++; //counting sort

        int[] res = new int[changed.length / 2];
        int idx = 0;
        for (int i = 0; i <= numMax; i++){
            if (freq[i] > 0){
                int n2 = i << 1; // equal to i * 2
                if (n2 <= numMax && freq[n2] >= freq[i]) {
                    freq[n2] -= freq[i];
                    while(freq[i]-- > 0) res[idx++] = i;
                }else
                    return new int[0];
            }
        }
        return res;
    }

    //Runtime: 173 ms, faster than 60.63% of Java online submissions for Find Original Array From Doubled Array.
    //Memory Usage: 151.3 MB, less than 11.08% of Java online submissions for Find Original Array From Doubled Array.
    //sort
    //Time: O(NlogN + N + N); Space: O(lgN + N + N/2)
    //Time: O(NlogN); Space: O(lN)
    public int[] findOriginalArray2(int[] changed) {
        if (changed.length % 2 != 0) return new int[0];

        //Time: O(NlogN); Space:O(lgN)
        Arrays.sort(changed);

        //Time: O(N); Space:O(N)
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : changed) map.put(n, map.getOrDefault(n, 0) + 1);

        int idx = 0;
        //Time: O(N) Space:O(N/2)
        int[] res = new int[changed.length / 2];
        for (int k : changed){
            if (map.get(k) <= 0) continue;
            map.put(k, map.get(k) - 1);

            int k2 = k << 1;
            if (map.getOrDefault(k2, 0) <= 0) return new int[0];
            map.put(k2, map.get(k2) - 1);

            res[idx++] = k;//one by one
        }
        return res;
    }


    //Runtime: 391 ms, faster than 20.51% of Java online submissions for Find Original Array From Doubled Array.
    //Memory Usage: 60.2 MB, less than 91.92% of Java online submissions for Find Original Array From Doubled Array.
    //TreeMap
    public int[] findOriginalArray(int[] changed) {
        if (changed.length % 2 != 0) return new int[0];

        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < changed.length; i++)
            treeMap.put(changed[i], treeMap.getOrDefault(changed[i], 0) + 1);

        int idx = 0;
        int[] res = new int[changed.length / 2];
        for (int n : treeMap.keySet()) {
            int n2 = n << 1;

            if (treeMap.get(n) > treeMap.getOrDefault(n2, 0))
                return new int[0];

            int c1 = treeMap.get(n);
            treeMap.put(n, 0);
            if (treeMap.containsKey(n2))
                treeMap.put(n2, treeMap.get(n2) - c1);

            if (n == 0) c1 = c1 >> 1;
            while (c1-- > 0) res[idx++] = n;//duplicated ones
        }
        return res;
    }


}
