package Contest;

import java.util.*;

import static java.time.LocalTime.now;

/**
 * You are given two 0-indexed integer arrays nums1 and nums2, each of size n, and an integer diff. Find the number of pairs (i, j) such that:
 *
 * 0 <= i < j <= n - 1 and
 * nums1[i] - nums1[j] <= nums2[i] - nums2[j] + diff.
 * Return the number of pairs that satisfy the conditions.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [3,2,5], nums2 = [2,2,1], diff = 1
 * Output: 3
 * Explanation:
 * There are 3 pairs that satisfy the conditions:
 * 1. i = 0, j = 1: 3 - 2 <= 2 - 2 + 1. Since i < j and 1 <= 1, this pair satisfies the conditions.
 * 2. i = 0, j = 2: 3 - 5 <= 2 - 1 + 1. Since i < j and -2 <= 2, this pair satisfies the conditions.
 * 3. i = 1, j = 2: 2 - 5 <= 2 - 1 + 1. Since i < j and -3 <= 2, this pair satisfies the conditions.
 * Therefore, we return 3.
 * Example 2:
 *
 * Input: nums1 = [3,-1], nums2 = [-2,2], diff = -1
 * Output: 0
 * Explanation:
 * Since there does not exist any pair that satisfies the conditions, we return 0.
 *
 *
 * Constraints:
 *
 * n == nums1.length == nums2.length
 * 2 <= n <= 105
 * -104 <= nums1[i], nums2[i] <= 104
 * -104 <= diff <= 104
 */

// 2426. Number of Pairs Satisfying Inequality

// 315 Count of Smaller numbers after self
// 2426 Number of Pairs Statisfying in equality
// 493 reverse pairs
public class N6198HNumberofPairsSatisfyingInequality {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun(9, new int[]{-4,-4,4,-1,-2,5}, new int[]{-2,2,-1,4,4,3}, 1);
        doRun(15, new int[]{1,1,1,1,1,1}, new int[]{1,1,1,1,1,1}, 0);

        doRun(2434,
                new int[]{60,-18,-49,-32,67,82,-28,-90,-74,-94,39,86,0,-63,-26,-20,47,53,-71,-74,-97,-10,80,-11,-48,22,62,53,-90,-79,57,-4,-85,-47,58,43,95,-50,17,69,-84,-82,-1,-3,83,-39,79,68,91,25,-97,-31,54,81,72,-2,-96,8,51,90,53,76,85,73,87,-41,71,-79,69,73,85,76,65,18,78,-91,89,68,70,-24,53,40,88,-45},
                new int[]{-98,-18,-79,-70,85,48,-91,71,91,-94,57,97,-48,42,1,41,-55,100,-61,61,8,-81,99,22,28,-55,24,-59,-34,47,-13,-41,97,-12,76,-87,-77,-84,-54,13,58,69,87,62,-7,76,10,71,37,18,-39,57,-47,55,-97,-28,-91,-40,45,51,-98,-51,92,48,-94,-25,-89,78,-97,-13,-27,-72,-96,37,88,-70,48,-10,62,91,42,46,22,93},
                46);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(long expect, int[] nums1, int[] nums2, int diff) {
        long res = new N6198HNumberofPairsSatisfyingInequality()
                .numberOfPairs(nums1, nums2, diff);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //4.Merge sort
    //nums1[i] - nums1[j] <= nums2[i] - nums2[j] + diff
    //nums1[i] - nums2[i] <= nums1[j] - nums2[j] + diff
    //Runtime: 40 ms, faster than 82.07% of Java online submissions for Number of Pairs Satisfying Inequality.
    //Memory Usage: 62.8 MB, less than 86.90% of Java online submissions for Number of Pairs Satisfying Inequality.
    public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        dif = diff;
        for (int i = 0; i < nums1.length; i++) nums1[i] -= nums2[i];
        return mergeSort(nums1, 0, nums1.length - 1);
    }
    private int dif;
    //Time: O(lgN * N); Space:O(lgN)
    private long mergeSort(int[] nums, int left, int right) {
        if (right == left) return 0;

        int mid = (left + right) / 2;
        long count = mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right);
        for (int i = mid, j = right; i >= left && j >= mid + 1;){
            if (nums[i] <= nums[j] + dif) {
                count += i - left + 1;j--;
            } else i--;
        }
        merge(nums, left, mid, mid + 1, right);
        return count;
    }

    //Time: O(N); Space:O(N)
    private void merge(int[] nums, int left1, int right1, int left2, int right2){
        if (left1 == left2) return;
        int[] data = Arrays.copyOfRange(nums, left1, right1 + 1);
        int idx = 0;
        while (idx < data.length && left2 <= right2) {
            nums[left1++] = data[idx] <= nums[left2] ? data[idx++] : nums[left2++];
        }
        while (idx < data.length) nums[left1++] = data[idx++];
        while (left2 <= right2) nums[left1++] = nums[left2++];
    }


    //3.Binary Index Tree
    //Runtime: 14 ms, faster than 98.98% of Java online submissions for Number of Pairs Satisfying Inequality.
    //Memory Usage: 60.4 MB, less than 88.94% of Java online submissions for Number of Pairs Satisfying Inequality.
    //Binary Index Tree
    //Time: O(N * logC); Space:O(C)
    public long numberOfPairs_3(int[] nums1, int[] nums2, int diff) {
        int offset = 30_000; // offset negative to non-negative
        int size = 2 * 3 * 10_000 + 1 + 1; // total possible values in nums plus one dummy
        int[] tree = new int[size]; //default is zero.

        long res = 0;
        for (int i = 0; i < nums1.length; i++) {
            //nums1[i] - nums1[j] <= nums2[i] - nums2[j] + diff
            //nums1[i] - nums2[i] <= nums1[j] - nums2[j] + diff
            nums2[i] = nums1[i] - nums2[i] + offset;
            res += queryBIT(nums2[i] + diff + 1, tree); //+1 <=
            updateBIT(nums2[i], tree, size);
        }

        return res;
    }
    private int lsb(int i) {
        return i&(-i);
    }
    //Time: O(logN)
    private int queryBIT(int i, int[] tree) {
        int sum = 0;
        for(; i >= 1; i -= lsb(i)) sum += tree[i];
        return sum;
    }
    private void updateBIT(int i, int[] tree, int size){
        i++; //后移一位放。
        for(; i < size; i += lsb(i)) tree[i]++;
    }


    //2.Binary Search
    // step 1: nums1[i] - nums1[j] <= nums2[i] - nums2[j] + diff  >>>>>>>> nums1[i] - nums2[i]  <= nums1[j]  - nums2[j] + diff
    // step 2: 从左往右，找出比当前值小的（binarySearch）
    //Runtime: 197 ms, faster than 33.16% of Java online submissions for Number of Pairs Satisfying Inequality.
    //Memory Usage: 59.5 MB, less than 89.63% of Java online submissions for Number of Pairs Satisfying Inequality.
    //Binary Search
    //Time: O(N * 2 * logN); Space:O(N)
    //Time: O(N * logN); Space:O(N)
    public long numberOfPairs_2(int[] nums1, int[] nums2, int diff) {
        List<int[]> list = new ArrayList<>();
        long res = 0;
        ComparatorN6198 comparatorN6198 = new ComparatorN6198();
        for (int i = 0; i < nums1.length; i++){
            //nums1[i] - nums1[j] <= nums2[i] - nums2[j] + diff
            nums2[i] = nums1[i] - nums2[i];

            //Time：O(lgN)
            int[] element = new int[]{nums2[i] + diff, i};
            int idx = Collections.binarySearch(list, element, comparatorN6198);
            res += (idx >= 0 ? idx : Math.abs(idx) - 1) ;

            element[0] -= diff;
            //Time：O(lgN)
            idx = Collections.binarySearch(list, element, comparatorN6198);
            if (idx < 0) idx = Math.abs(idx) - 1;
            list.add(idx, new int[]{nums2[i], i});
        }
        return res;
    }

    class ComparatorN6198 implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[0] - o2[0] == 0 ? o1[1] - o2[1] : o1[0] - o2[0];
        }
    }

    //1.Brute force
    //TLE!!!!
    //Brute force
    //Time: O(N*N); Space:O(N)
    public long numberOfPairs_1(int[] nums1, int[] nums2, int diff) {
        //nums1[i] - nums1[j] <= nums2[i] - nums2[j] + diff.
        for (int i = 0; i<nums1.length; i++)
            nums2[i] = nums1[i] - nums2[i] + diff;

        long res = 0;
        for (int i = 0; i < nums2.length; i++){
            int tmp = nums2[i] - diff;
            for (int j = i + 1; j < nums2.length; j++){
                if (tmp <= nums2[j]) res++;
            }
        }
        return res;
    }

}
