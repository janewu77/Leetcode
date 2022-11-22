package LeetCode;


import java.util.*;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

/**
 * Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.
 *
 * An integer a is closer to x than an integer b if:
 *
 * |a - x| < |b - x|, or
 * |a - x| == |b - x| and a < b
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,3,4,5], k = 4, x = 3
 * Output: [1,2,3,4]
 * Example 2:
 *
 * Input: arr = [1,2,3,4,5], k = 4, x = -1
 * Output: [1,2,3,4]
 *
 *
 * Constraints:
 *
 * 1 <= k <= arr.length
 * 1 <= arr.length <= 104
 * arr is sorted in ascending order.
 * -104 <= arr[i], x <= 104
 */

/**
 * M :  [Time : 120+
 */
public class N658MFindKClosestElements {


    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        data = new int[]{1,2,3,4,5};
        doRun("1,2,3,4", data, 4, -1);

        data = new int[]{0,0,1,2,3,3,4,7,7,8};
        doRun("3,3,4", data, 3, 5);

        data = new int[]{1,3,3,4,5,7,7,8,8,8};
        doRun("4,5,7,7,8,8", data, 6, 6);

        data = new int[]{1};
        doRun("1", data, 1, 0);

        data = new int[]{1};
        doRun("1", data, 1, 1);

        data = new int[]{1,1,1,10,10,10};
        doRun("10", data, 1, 9);

        data = new int[]{1,2,3,4,5};
        doRun("1,2,3,4", data, 4, 3);

        data = new int[]{1,25,35,45,50,59};
        doRun("25", data, 1, 30);

        data = new int[]{1,1,2,2,2,2,2,3,3};
        doRun("2,3,3", data, 3, 3);

        //不会出现
//        data = new int[]{1,2};
//        doRun("1,2", data, 4, 1);
        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(String expect, int[] arr, int k, int x) {
        List<Integer> res1 = new N658MFindKClosestElements()
                .findClosestElements(arr, k, x);
        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }
    //An integer a is closer to x than an integer b if:
    //|a - x| < |b - x|, or
    //|a - x| == |b - x| and a < b
    private boolean isACloserThanB(int x, int a, int b){
        return Math.abs(a - x) < Math.abs(b - x)
                || (Math.abs(a - x) == Math.abs(b - x) && a < b);
    }


    //Runtime: 3 ms, faster than 99.87% of Java online submissions for Find K Closest Elements.
    //Memory Usage: 44.2 MB, less than 93.40% of Java online submissions for Find K Closest Elements.
    //Slide Window + Binary Search
    //Time: O( lg(N-K) + K); Space: O(1)
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0, right = arr.length - k;

        while (left < right) {
            int mid = (left + right) / 2;
            if (x - arr[mid] > arr[mid + k] - x)
                left = mid + 1;
            else right = mid;
        }

        List<Integer> res = new LinkedList<>();
        right = left + k;
        while (left < right) res.add(arr[left++]);
        return res;
//        return Arrays.stream(arr, left, left + k).boxed().collect(Collectors.toList());
    }


    //Runtime: 5 ms, faster than 90.39% of Java online submissions for Find K Closest Elements.
    //Memory Usage: 44.5 MB, less than 88.75% of Java online submissions for Find K Closest Elements.
    //slide window + binary search : (two pointers expand to K)
    //Time: O(lgN + K); Space:O(1)
    public List<Integer> findClosestElements_SlideWindow2(int[] arr, int k, int x) {

        List<Integer> res = new LinkedList<>();

        //Time: O(lgN)
        //Binary search
        int left = 0, right = arr.length, mid = 0;
        while (left < right) {
            mid = (right + left) / 2;
            if (arr[mid] >= x) right = mid;
            else left = mid + 1;
        }
        left--;
        right = left + 1;

        //Binary search
//        int left = Arrays.binarySearch(arr, 0, arr.length, x);
//        if (left < 0) left = Math.abs(left) - 2;
//        int right = left + 1;

        //Time: O(K)
        while (right - left < k + 1) {
            if (left == -1) {
                res.add(arr[right++]);
                continue;
            }
            //expand the window size
            if (right >= arr.length || Math.abs(arr[left] - x) <= Math.abs(arr[right] - x))
                res.add(0,arr[left--]);
            else
                res.add(arr[right++]);
        }
        return res;
    }

    //Runtime: 4 ms, faster than 94.54% of Java online submissions for Find K Closest Elements.
    //Memory Usage: 44.1 MB, less than 95.57% of Java online submissions for Find K Closest Elements.
    //Slide window : (two pointers shrink to K)
    //Time: O(N); Space: O(1)
    public List<Integer> findClosestElements_SlideWindow1(int[] arr, int k, int x) {

        List<Integer> res = new LinkedList<>();
        int left = 0, right = arr.length - 1;

        while (right - left > k - 1){
            //shrink the window size from N to K
            if (Math.abs(arr[left] - x) <= Math.abs(arr[right] - x))
                right--;
            else left++;
        }
        while (left <= right) res.add(arr[left++]);
        return res;
    }


    //Runtime: 33 ms, faster than 28.49% of Java online submissions for Find K Closest Elements.
    //Memory Usage: 44.7 MB, less than 87.04% of Java online submissions for Find K Closest Elements.
    //29ms, 45.3mb
    //Sort With Custom Comparator
    //Time: O(N + N * logN + K * logK); Space: O(N + logN + logK)
    public List<Integer> findClosestElements_sort2(int[] arr, int k, int x) {

        //Space: O(N)
        List<Integer> res = new LinkedList<>();

        //Time: O(N)
        for (int num : arr) res.add(num);
        //Time: O(N * logN); Space: O(logN)
        Collections.sort(res, Comparator.comparingInt(num -> Math.abs(num - x)));

        res = res.subList(0, k);
        //Time: O(K * logK); Space: O(logK)
        Collections.sort(res);
        return res;
    }

    //29ms, 45.3mb
    //Sort With Custom Comparator
    //Time: O(N + N * logN + K + K * logK); Space: O(N + logN + logK)
    public List<Integer> findClosestElements_sort1(int[] arr, int k, int x) {
        //Time: O(N); Space: O(N)
        int[][] data = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++){
            data[i][0] = arr[i];
            data[i][1] = i;
        }

        //Time: O(N * logN); Space: O(logN)
        Arrays.sort(data, (o1, o2) -> {
            int a = Math.abs(o1[0] - x);
            int b = Math.abs(o2[0] - x);
            if (a == b) return o1[1] - o2[1];
            return a - b;
        });

        //Time: O(K + K * logK); Space: O(logK)
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < k; i++) res.add(data[i][0]);
        Collections.sort(res);
        return res;
    }


}
