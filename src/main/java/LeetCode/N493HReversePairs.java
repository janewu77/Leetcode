package LeetCode;


import java.util.*;

import static java.time.LocalTime.now;

/**
 * Given an integer array nums, return the number of reverse pairs in the array.
 *
 * A reverse pair is a pair (i, j) where:
 *
 * 0 <= i < j < nums.length and
 * nums[i] > 2 * nums[j].
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,2,3,1]
 * Output: 2
 * Explanation: The reverse pairs are:
 * (1, 4) --> nums[1] = 3, nums[4] = 1, 3 > 2 * 1
 * (3, 4) --> nums[3] = 3, nums[4] = 1, 3 > 2 * 1
 * Example 2:
 *
 * Input: nums = [2,4,3,5,1]
 * Output: 3
 * Explanation: The reverse pairs are:
 * (1, 4) --> nums[1] = 4, nums[4] = 1, 4 > 2 * 1
 * (2, 4) --> nums[2] = 3, nums[4] = 1, 3 > 2 * 1
 * (3, 4) --> nums[3] = 5, nums[4] = 1, 5 > 2 * 1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5 * 104
 * -231 <= nums[i] <= 231 - 1
 */
// 2426. Number of Pairs Satisfying Inequality
// 315 Count of Smaller numbers after self
// 2426 Number of Pairs Statisfying in equality
// 493 reverse pairs

/**
 * H - [time: 60-
 */
public class N493HReversePairs {


    public static void main(String[] args) {


        System.out.println(now());

        System.out.println(Integer.MAX_VALUE);//2147483647
        System.out.println(Integer.MIN_VALUE);//-2147483648

//        System.out.println(Integer.MAX_VALUE / 2);//
//        System.out.println(Integer.MIN_VALUE / 2);//-
//        System.out.println(1073741824 * 2);
//        System.out.println(1073741823 * 2);

        doRun(1, new int[]{-3, -2});

        doRun(9, new int[]{2147483647,2147483647,-2147483647,-2147483647,-2147483647,2147483647});
        doRun(1, new int[]{2147483647,1073741824,1073741823});
        doRun(2, new int[]{1,3,2,3,1});
        doRun(3, new int[]{2,4,3,5,1});
        doRun(0, new int[]{2147483647,2147483647,2147483647,2147483647,2147483647,2147483647});



        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect,int[] nums) {
        long res = new N493HReversePairs()
                .reversePairs(nums);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //https://leetcode.com/problems/reverse-pairs/discuss/112146/Java-MergeSort-O(n-long-n)-Templater-Solution-for-Reverse-Pairs-327-and-315
    //3.merge sort
    //0 <= i < j < nums.length
    //nums[i] > 2 * nums[j]
    //Runtime: 51 ms, faster than 99.45% of Java online submissions for Reverse Pairs.
    //Memory Usage: 50.7 MB, less than 89.40% of Java online submissions for Reverse Pairs.
    //merge sort
    //Time: O(lgN * N); Space:O(N + lgN)
    //Time: O(N * lgN); Space:O(N)
    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    private int mergeSort(int[] nums, int left, int right) {
        if (right == left) return 0 ;

        int mid = (left + right) / 2;
        int count = mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right);

        for (int i = left, j = mid + 1; i <= mid && j <= right;){
            if (nums[i] >  ((long)nums[j] + (long)nums[j]) ){
                count += mid - i + 1; j++;
            } else i++;
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


    //2。
    //Runtime: 121 ms, faster than 38.06% of Java online submissions for Reverse Pairs.
    //Memory Usage: 71 MB, less than 73.16% of Java online submissions for Reverse Pairs.
    //Binary Index Tree
    //Time:O(N * logN);Space: O(N)
    public int reversePairs_2(int[] nums) {
        int res = 0;
        //Space: O(N)
        int[] tree = new int[nums.length + 1];
        int[] orderedNums = Arrays.copyOf(nums, nums.length); //Time: O(N)
        //Time:O(NlogN); Space:O(logN)
        Arrays.sort(orderedNums);

        //nums[i] > 2 * nums[j]
        //Time:O(N * (4 * logN));
        for (int i = 0; i < nums.length; i++) {
            int target = nums[i] + nums[i];
            if (nums[i] < Integer.MIN_VALUE / 2) target = Integer.MIN_VALUE;
            else if (nums[i] > Integer.MAX_VALUE / 2) target = Integer.MAX_VALUE;

            int idx = Arrays.binarySearch(orderedNums, target);
            idx = idx >= 0 ? idx + 1 : Math.abs(idx) - 1;
            res += i - queryBIT(idx, tree);

            idx = Arrays.binarySearch(orderedNums, nums[i]);
            if (idx < 0) idx = Math.abs(idx) - 1;
            updateBIT(idx, tree);
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
    private void updateBIT(int i, int[] tree){
        i++; //后移一位放。
        for(; i < tree.length; i += lsb(i)) tree[i]++;
    }


    //1。
    //从左往右逐步增长List, 保持list有序， 查找list中大于2 * nums[j] 的个数。（总数-小数等于的数量 = 大于的数量）
    //
    //Runtime: 476 ms, faster than 5.04% of Java online submissions for Reverse Pairs.
    //Memory Usage: 51.1 MB, less than 87.95% of Java online submissions for Reverse Pairs.
    //binarySearch
    //Time: O(N * logN); Space:O(N)
    public int reversePairs_1(int[] nums) {
        int res = 0;
        List<int[]> list = new ArrayList<>();

        ComparatorN493 comparatorN493 = new ComparatorN493();
        for (int i = 0; i < nums.length; i++) {

            //nums[i] > 2 * nums[j] >>>>>
            int[] element = new int[]{nums[i] + nums[i], i};
            if (nums[i] < Integer.MIN_VALUE / 2) element[0] = Integer.MIN_VALUE;
            else if (nums[i] > Integer.MAX_VALUE / 2) element[0] = Integer.MAX_VALUE;

            //Time：O(logN)
            int idx = Collections.binarySearch(list, element, comparatorN493);
            res += list.size() - (idx >= 0 ? idx + 1 : Math.abs(idx) - 1);

            element[0] = nums[i];
            //Time：O(logN)
            idx = Collections.binarySearch(list, element, comparatorN493);
            if (idx < 0) idx = Math.abs(idx) - 1;
            list.add(idx, element);
        }
        return res;
    }

    class ComparatorN493 implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2) {
            return  o1[0] < o2[0] ? -1 : o1[0] > o2[0] ? 1 : o1[1] < o2[1] ? -1 : o1[1] > o2[1] ? 1 : 0;
        }
    }

}
