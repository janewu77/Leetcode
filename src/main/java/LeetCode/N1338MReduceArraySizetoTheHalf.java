package LeetCode;

import javafx.util.Pair;

import java.util.*;

import static java.time.LocalTime.now;

/**
 *
 * You are given an integer array arr. You can choose a set of integers and remove
 * all the occurrences of these integers in the array.
 *
 * Return the minimum size of the set so that at least half of the integers of the array are removed.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [3,3,3,3,5,5,5,2,2,7]
 * Output: 2
 * Explanation: Choosing {3,7} will make the new array [5,5,5,2,2] which has size 5
 * (i.e equal to half of the size of the old array).
 * Possible sets of size 2 are {3,5},{3,2},{5,2}.
 * Choosing set {2,7} is not possible as it will make the new array [3,3,3,3,5,5,5]
 * which has a size greater than half of the size of the old array.
 * Example 2:
 *
 * Input: arr = [7,7,7,7,7,7]
 * Output: 1
 * Explanation: The only possible set you can choose is {7}. This will make the new array empty.
 *
 *
 * Constraints:
 *
 * 2 <= arr.length <= 105
 * arr.length is even.
 * 1 <= arr[i] <= 105
 *
 */

/**
 * M - [time: 60-
 *
 */
public class N1338MReduceArraySizetoTheHalf {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;

        System.out.println("========doRun_demo==========");
        data = new int[]{3,3,3,3,5,5,5,2,2,7};
        doRun_demo(2, data);

        data = new int[]{7,7,7,7,7,7};
        doRun_demo(1, data);

        data = new int[]{1,2,3,4,5,6,7,8,9,10};
        doRun_demo(5, data);

        data = new int[]{1,1,2,2,3,3,4,4,4,4};
        doRun_demo(2, data);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun_demo(int expect, int[] nums) {
        int res = new N1338MReduceArraySizetoTheHalf().minSetSize(nums);
//        String res = comm.toString(res1);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 30 ms, faster than 95.54% of Java online submissions for Reduce Array Size to The Half.
    //Memory Usage: 64.3 MB, less than 86.05% of Java online submissions for Reduce Array Size to The Half.
    //sort in place
    //Time: O(NlogN + NlogN + 2N); Space: O(1)
    public int minSetSize(int[] arr) {
        Arrays.sort(arr); //Time: O(NlogN)

        //Time: O(N)
        int idx = 0;
        int count = 1;
        //统计重复
        for(int i = 1; i < arr.length; i++){
            if (arr[i] != arr[i-1]) {
                arr[idx++] = count;
                count = 1;
            }else count++;
        }
        arr[idx++] = count;
        for(int i = idx; i < arr.length; i++) arr[i] = 0;

        //排序后，重复数将在后部。
        Arrays.sort(arr);//Time: O(NlogN)

        int res = 0;
        int target = arr.length / 2;
        int i = arr.length - 1;
        while (target > 0) {
            target -= arr[i--];
            res++;
        }
        return res;
    }

    //from solution
    //Runtime: 32 ms, faster than 94.31% of Java online submissions for Reduce Array Size to The Half.
    //Memory Usage: 50.9 MB, less than 96.32% of Java online submissions for Reduce Array Size to The Half.
    //Time: O(N + N + N); Space: O(N + maxCount);
    public int minSetSize_3(int[] arr) {

        int maxCount = 0;
        Map<Integer, Integer> map  = new HashMap<>();
        for (int n: arr) {
            int c = map.getOrDefault(n, 0) + 1;
            map.put(n, c);
            maxCount = Math.max(maxCount, c);
        }

        int[] counter = new int[maxCount + 1];
        for(int v : map.values()) counter[v]++;

        int target = arr.length / 2;
        int res = 0;
        int idx = maxCount;
        while (target > 0){
            target -= idx;
            counter[idx]--;
            while (idx >= 0 && counter[idx] == 0) idx--;
            res++;
        }
        return res;
    }

    //Runtime: 62 ms, faster than 75.89% of Java online submissions for Reduce Array Size to The Half.
    //Memory Usage: 85 MB, less than 63.51% of Java online submissions for Reduce Array Size to The Half.
    //hashMap + arrayList
    //Time: O(N + NlogN + N);  Space: O(N + N)
    public int minSetSize_2(int[] arr) {
        //number: count
        Map<Integer, Integer> map  = new HashMap<>();
        for (int n: arr) map.put(n, map.getOrDefault(n, 0) + 1);

        List<Integer> counts = new ArrayList<>(map.values());
        Collections.sort(counts);

        int target = arr.length / 2;
        int res = 0;
        int idx = counts.size() - 1;
        while (target > 0){
            target -= counts.get(idx--);
            res++;
        }
        return res;
    }

    //Runtime: 310 ms, faster than 5.02% of Java online submissions for Reduce Array Size to The Half.
    //Memory Usage: 111.6 MB, less than 5.03% of Java online submissions for Reduce Array Size to The Half.
    //hashMap + TreeSet
    //Time: O(N + NlogN + N/2 * logN); Space: O(N + N)
    public int minSetSize_1(int[] arr) {
        //number: count
        Map<Integer, Integer> map  = new HashMap<>();
        for (int n: arr) map.put(n, map.getOrDefault(n, 0) + 1);

        //count of repeat: count of number
        TreeSet<Pair<Integer, Integer>> countMap = new TreeSet<>((o1, o2) -> {
            if (o2.getValue().equals(o1.getValue()))  //equals
                return o2.getKey() - o1.getKey();
            return o2.getValue() - o1.getValue();
        });

        //Time: O(NlogN)
        for (int key: map.keySet())
            countMap.add(new Pair<>(key, map.get(key)));

        //Time: O(N/2 * logN)
        int target = arr.length / 2;
        int res = 0;
        while (target > 0){
            target -= countMap.pollFirst().getValue();
            res++;
        }
        return res;
    }

}
