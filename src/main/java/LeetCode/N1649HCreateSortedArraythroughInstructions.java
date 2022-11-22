package LeetCode;

import java.util.*;

import static java.time.LocalTime.now;

/**
 * Given an integer array instructions, you are asked to create a sorted array from the elements in instructions. You start with an empty container nums. For each element from left to right in instructions, insert it into nums. The cost of each insertion is the minimum of the following:
 *
 * The number of elements currently in nums that are strictly less than instructions[i].
 * The number of elements currently in nums that are strictly greater than instructions[i].
 * For example, if inserting element 3 into nums = [1,2,3,5], the cost of insertion is min(2, 1) (elements 1 and 2 are less than 3, element 5 is greater than 3) and nums will become [1,2,3,3,5].
 *
 * Return the total cost to insert all elements from instructions into nums. Since the answer may be large, return it modulo 109 + 7
 *
 *
 *
 * Example 1:
 *
 * Input: instructions = [1,5,6,2]
 * Output: 1
 * Explanation: Begin with nums = [].
 * Insert 1 with cost min(0, 0) = 0, now nums = [1].
 * Insert 5 with cost min(1, 0) = 0, now nums = [1,5].
 * Insert 6 with cost min(2, 0) = 0, now nums = [1,5,6].
 * Insert 2 with cost min(1, 2) = 1, now nums = [1,2,5,6].
 * The total cost is 0 + 0 + 0 + 1 = 1.
 * Example 2:
 *
 * Input: instructions = [1,2,3,6,5,4]
 * Output: 3
 * Explanation: Begin with nums = [].
 * Insert 1 with cost min(0, 0) = 0, now nums = [1].
 * Insert 2 with cost min(1, 0) = 0, now nums = [1,2].
 * Insert 3 with cost min(2, 0) = 0, now nums = [1,2,3].
 * Insert 6 with cost min(3, 0) = 0, now nums = [1,2,3,6].
 * Insert 5 with cost min(3, 1) = 1, now nums = [1,2,3,5,6].
 * Insert 4 with cost min(3, 2) = 2, now nums = [1,2,3,4,5,6].
 * The total cost is 0 + 0 + 0 + 0 + 1 + 2 = 3.
 * Example 3:
 *
 * Input: instructions = [1,3,3,3,2,4,2,1,2]
 * Output: 4
 * Explanation: Begin with nums = [].
 * Insert 1 with cost min(0, 0) = 0, now nums = [1].
 * Insert 3 with cost min(1, 0) = 0, now nums = [1,3].
 * Insert 3 with cost min(1, 0) = 0, now nums = [1,3,3].
 * Insert 3 with cost min(1, 0) = 0, now nums = [1,3,3,3].
 * Insert 2 with cost min(1, 3) = 1, now nums = [1,2,3,3,3].
 * Insert 4 with cost min(5, 0) = 0, now nums = [1,2,3,3,3,4].
 * ​​​​​​​Insert 2 with cost min(1, 4) = 1, now nums = [1,2,2,3,3,3,4].
 * ​​​​​​​Insert 1 with cost min(0, 6) = 0, now nums = [1,1,2,2,3,3,3,4].
 * ​​​​​​​Insert 2 with cost min(2, 4) = 2, now nums = [1,1,2,2,2,3,3,3,4].
 * The total cost is 0 + 0 + 0 + 0 + 1 + 0 + 1 + 0 + 2 = 4.
 *
 *
 * Constraints:
 *
 * 1 <= instructions.length <= 105
 * 1 <= instructions[i] <= 105
 */
public class N1649HCreateSortedArraythroughInstructions {

    public static void main(String[] args){
        System.out.println(now());
//
        doRun(1, new int[]{1,5,6,2});
        doRun(4, new int[]{1,3,3,3,2,4,2,1,2});
        doRun(3, new int[]{1,2,3,6,5,4});

        //greater
//        doRun(3, new int[]{1,2,3,6,5,4});
//        doRun(2, new int[]{1,5,6,2});
//        doRun(17, new int[]{1,3,3,3,2,4,2,1,2});



        System.out.println(now());
    }
    private static int c = 1;
    private static void doRun(int expected, int[] nums){
        int res = new N1649HCreateSortedArraythroughInstructions().createSortedArray(nums);
        System.out.println("[" + (expected == res) +"] "+(c++)+ ".result:"+ res + ".expected:"+expected);
    }


    //4.Binary search tree
    //Runtime: 43 ms, faster than 97.33% of Java online submissions for Create Sorted Array through Instructions.
    //Memory Usage: 59.9 MB, less than 88.00% of Java online submissions for Create Sorted Array through Instructions.
    //Time: O(N * logM); Space: O(M);
    //M is the size of the tree
    public int createSortedArray(int[] instructions) {
        int max = 0;
        for (int i = 0; i < instructions.length; i++) max = Math.max(max, instructions[i]);
        int[] tree = new int[max + 2];

        long res = 0;
        for (int i = 0; i < instructions.length; i++){
            int counterLess = queryBIT(instructions[i], tree);
            int counterLarger = queryBIT( tree.length - 1, tree) - queryBIT(instructions[i] + 1, tree);
            res += Math.min(counterLess , counterLarger);
            updateBIT(instructions[i], tree);
        }
        return (int)(res % 1_000_000_007);
    }
    private int lsb(int i){
        return i&(-i);
    }
    private int queryBIT(int i, int[] tree) {
        int sum = 0;
        for(; i >= 1; i -= lsb(i)) sum += tree[i];
        return sum;
    }
    private void updateBIT(int i, int[] tree) {
        i++; //后移一位放。
        for(; i < tree.length; i += lsb(i)) tree[i]++;
    }

    //https://leetcode.com/articles/a-recursive-approach-to-segment-trees-range-sum-queries-lazy-propagation/
    //3.Segment tree
    //Runtime: 125 ms, faster than 61.33% of Java online submissions for Create Sorted Array through Instructions.
    //Memory Usage: 57.2 MB, less than 90.67% of Java online submissions for Create Sorted Array through Instructions.
    //Time: O(N * logM); Space: O(M);
    //M is the size of the tree (100_001 * 2)
    public int createSortedArray_3(int[] instructions) {
        int max = 0;
        for (int i = 0; i < instructions.length; i++) max = Math.max(max, instructions[i]);
        int[] segmentTree = new int[(max + 1) * 2]; //new int[100_001 + 100_001];
        long res = 0;

        for (int i = 0; i < instructions.length; i++){
            int counterLess = querySTree(0, instructions[i], segmentTree);
            int counterLarger = segmentTree[1] - querySTree(0, instructions[i] + 1, segmentTree);
//            int counterLarger = querySTree(instructions[i] + 1, segmentTree.length/2, segmentTree);
            res += Math.min(counterLess , counterLarger);
            updateSTree(instructions[i], 1, segmentTree);
        }
        return (int)(res % 1_000_000_007);
    }

    private int querySTree(int i, int j, int[] segmentTree){
        int left = i + (segmentTree.length >> 1), right = j + (segmentTree.length >> 1);
        int sum = 0;

        while (left < right) {
            if ((left & 1) == 1) sum += segmentTree[left++];
            if ((right & 1) == 1) sum += segmentTree[--right];
            left >>= 1;
            right >>= 1;
        }
        return sum;
    }
    private void updateSTree(int i, int val, int[] segmentTree){
        i += (segmentTree.length >> 1);
        segmentTree[i] += val;

        while (i > 1){
            i = i>>1;
            segmentTree[i] = segmentTree[i << 1] + segmentTree[(i << 1) + 1];
        }
    }

    //2.Merge sort
    //Runtime: 251 ms, faster than 41.56% of Java online submissions for Create Sorted Array through Instructions.
    //Memory Usage: 126.7 MB, less than 49.35% of Java online submissions for Create Sorted Array through Instructions.
    //Time: O(N * lgN); Space: O(N)
    public int createSortedArray_2(int[] instructions) {
        int[] less = new int[instructions.length];
        int[] larger = new int[instructions.length];

        int[] index = new int[instructions.length];
        for (int i = 0; i < instructions.length; i++) index[i] = i;

        mergeSort(instructions, 0, instructions.length - 1, index, less, larger);

        long count = 0;
        for (int i = 0; i < less.length; i++)
            count += Math.min(less[i], larger[i]);

        return (int)(count % 1_000_000_007);
    }

    private void mergeSort(int[] nums, int left, int right,int[] index,int[] less,int[] larger) {
        if (right == left) return;

        int mid = (left + right) / 2;
        mergeSort(nums, left, mid, index, less, larger);
        mergeSort(nums, mid + 1, right, index, less, larger);

        for (int i = mid, j = right; i >= left && j >= mid + 1;){
            if (nums[index[i]] < nums[index[j]]){
                less[index[j]] += i - left + 1; j--;
            } else i--;
        }

        for (int i = left, j = mid + 1; i <= mid && j <= right;){
            if (nums[index[i]] > nums[index[j]]){
                larger[index[j]] += mid - i + 1; j++;
            } else i++;
        }

        merge(nums, left, mid, mid + 1, right, index);
    }

    //Time: O(N); Space:O(N)
    private void merge(int[] nums, int left1, int right1, int left2, int right2, int[] index){
        if (left1 == left2) return;
        int begin = left1;
        int[] data = new int[right1 - left1 + 1 + right2 - left2 + 1];
        int idx = 0;

        while (left1 <= right1 && left2 <= right2) {
            data[idx++] = nums[index[left1]] <= nums[index[left2]]? index[left1++] : index[left2++];
        }

        while (left1 <= right1) data[idx++] = index[left1++];
        while (left2 <= right2) data[idx++] = index[left2++];

        for (int i = 0; i < data.length; i++) index[begin + i] = data[i];
    }


    //1.Binary search
    //Time Limit Exceeded
    //Time: O(N * lgN); Space: O(N)
    public int createSortedArray_1(int[] instructions) {
        long res = 0;
        ComparatorN1649Less comparatorN1649Less = new ComparatorN1649Less();
        ComparatorN1649Greater comparatorN1649Greater = new ComparatorN1649Greater();

        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < instructions.length; i++) {
            int[] element = new int[]{instructions[i], i};

            int idx1 = Collections.binarySearch(list, element, comparatorN1649Less);
            idx1 = idx1 == -1 ? 0: -idx1 - 1;

            int idx2 = Collections.binarySearch(list, element, comparatorN1649Greater);
            idx2 = idx2 == -1 ? 0: -idx2 - 1;
            idx2 = list.size() - idx2;
            res += Math.min(idx1, idx2);

            list.add(idx1, element);
        }
        return (int) (res % 1_000_000_007);
    }

    class ComparatorN1649Less implements Comparator<int[]> {
        @Override
        public int compare(int[] a, int[] b) {
            return a[0] - b[0] == 0 ? b[1] - a[1] : a[0] - b[0];
        }
    }
    class ComparatorN1649Greater implements Comparator<int[]> {
        @Override
        public int compare(int[] a, int[] b) {
            return a[0] - b[0] == 0 ? a[1] - b[1] : a[0] - b[0];
        }
    }
}
