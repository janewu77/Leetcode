package LeetCode;



import java.util.*;

import static java.time.LocalTime.now;

/**
 * You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 *
 *
 *
 * Example 1:
 * Input: nums = [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 *
 *
 * Example 2:
 * Input: nums = [-1]
 * Output: [0]
 *
 * Example 3:
 * Input: nums = [-1,-1]
 * Output: [0,0]
 *
 *
 * Constraints:
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 *
 *
 */

/**
 * H - 【time: 2day+]
 *  - segment tree, binary index tree BIT
 *  - 转变问题，转成上面二种数据结构擅长的范围。
 *
 */
// 2426. Number of Pairs Satisfying Inequality
// 315 Count of Smaller numbers after self
// 493 reverse pairs

public class N315HCountofSmallerNumbersAfterSelf {

    public static void main(String[] args){
        System.out.println(now());
        doRun(new int[]{1}, "[0]");

        doRun(new int[]{2,1}, "[1, 0]");
        doRun(new int[]{5,2,6,1}, "[2, 1, 1, 0]");
        doRun(new int[]{5,2,3,2,1,5}, "[4, 1, 2, 1, 0, 0]");
        doRun(new int[]{2,0,1}, "[2, 0, 0]");

        doRun(new int[]{1,1,1,1}, "[0, 0, 0, 0]");

        doRun(new int[]{18,17,16,15,14,13,12,11,10}, "[8, 7, 6, 5, 4, 3, 2, 1, 0]");

        System.out.println(now());
    }
    private static int c = 1;
    private static void doRun(int[] nums, String expected){
        List<Integer> res = new N315HCountofSmallerNumbersAfterSelf().countSmaller(nums);
        System.out.println("[" + (expected.equals(res.toString())) +"] "+(c++)+ ".result:"+ res + ".expected:"+expected);
    }

    //4. Merge sort
    //Runtime: 147 ms, faster than 72.89% of Java online submissions for Count of Smaller Numbers After Self.
    //Memory Usage: 139.9 MB, less than 67.01% of Java online submissions for Count of Smaller Numbers After Self.
    //Time: O(N + N * lgN + N); Space:O(N)
    //Time: O(N * lgN); Space:O(N)
    public List<Integer> countSmaller(int[] nums) {
        int[] res = new int[nums.length];
        int[] index = new int[nums.length];
        for (int i = 0; i < nums.length; i++) index[i] = i;

        mergeSort(nums, 0, nums.length - 1, index, res);

        List<Integer> list = new ArrayList<>();
        for (int value : res) list.add(value);
        return list;
    }

    private void mergeSort(int[] nums, int left, int right,int[] index,int[] res) {
        if (right == left) return;

        int mid = (left + right) / 2;
        mergeSort(nums, left, mid, index, res);
        mergeSort(nums, mid + 1, right, index, res);

        for (int i = mid, j = right; i >= left && j >= mid + 1;){
            if (nums[index[i]] > nums[index[j]]){
                res[index[i]] += j - (mid + 1) + 1;  i--;
            } else j--;
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


    //3. Binary Search
    //Runtime: 1275 ms, faster than 13.10% of Java online submissions for Count of Smaller Numbers After Self.
    //Memory Usage: 143.7 MB, less than 48.33% of Java online submissions for Count of Smaller Numbers After Self.
    //Time: O(N * LgN); Space: O(N)
    public List<Integer> countSmaller_3(int[] nums) {
        List<Integer> result = new ArrayList<>();
        ComparatorN315 comparatorN315 = new ComparatorN315();
        List<int[]> data = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int[] element = new int[]{nums[i], i};
            int idx = Collections.binarySearch(data, element, comparatorN315);
            idx = - idx - 1;
            result.add(idx);
            data.add(idx, element);
        }
        Collections.reverse(result);
        return result;
    }

    class ComparatorN315 implements Comparator<int[]> {
        @Override
        public int compare(int[] a, int[] b) {
            return a[0] - b[0] == 0 ? a[1] - b[1] : a[0] - b[0];
        }
    }

    //2.Binary Index tree
    //Runtime: 26 ms, faster than 99.38% of Java online submissions for Count of Smaller Numbers After Self.
    //Memory Usage: 61.5 MB, less than 86.99% of Java online submissions for Count of Smaller Numbers After Self.
    //Binary Index tree
    //time: O(Nlog(M)); space: O(M)
    public List<Integer> countSmaller_2(int[] nums) {
        int offset = 10000; // offset negative to non-negative
        int size = 2 * 10000 + 1 + 1; // total possible values in nums plus one dummy

        int[] tree = new int[size]; //default is zero.
        List<Integer> result = new ArrayList<>();

        //注意这里必须从高到低
        for (int i = nums.length - 1; i >= 0; i--) {
            int val = nums[i] + offset;
            result.add(queryBIT(val, tree)); //这里实际查询的是 小于自己的数 的个数。
            updateBIT(val, tree);
        }
        Collections.reverse(result);
        return result;
    }

    private int lsb(int i) {
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


    //1.Segment tree
    //Runtime: 58 ms, faster than 93.73% of Java online submissions for Count of Smaller Numbers After Self.
    //Memory Usage: 61.3 MB, less than 88.09% of Java online submissions for Count of Smaller Numbers After Self.
    //segment tree
    //将原数组转换成"占位"坐标。然后就转成segment tree擅长的处理 range 的sum. 具体可以看Solution里的解说。
    //这里用segment tree， 最底层其实是比较稀疏，这与nums[i]的分布有关。
    public List<Integer> countSmaller_segmentTree(int[] nums) {
        if (nums.length == 1) return Arrays.asList(0);

        int offset = 10000; //用位移的方式把负数变正。
        int halfSizeOfTree = 10000 * 2 + 1; //所有可能数 ： -10000 <= nums[i] <= 10000

        //-104 <= nums[i] <= 104
        //size: (10000 * 2 + 1) * 2
        int[] segmentTree = new int[halfSizeOfTree * 2];
        List<Integer> res = new ArrayList<>();

        //从后往前做。
        //逐步建树，然后查询。
        for(int i = nums.length - 1; i >= 0; i--){
            int idx = nums[i] + offset;
            res.add(querySTree(0, idx, segmentTree));
            updateSTree(idx, 1, segmentTree);
        }
        //最后reverse，比 add(0,。。。）更高效。
        Collections.reverse(res);
        return res;
    }

    private int querySTree(int i, int j, int[] segmentTree){
        int left = i + segmentTree.length / 2; // from 0
        int right = j + segmentTree.length / 2;

        int sum = 0;
        while (left < right){
            if ((left & 1) == 1) sum += segmentTree[left++];
            if ((right & 1) == 1) sum += segmentTree[--right];
            left = left >> 1;
            right = right >> 1;
        }
        return sum;
    }

    //这里的update，是固定加1，表示后面有这个数字。 在其他case里，可调整update的具体内容。
    private void updateSTree(int i, int val, int[] segmentTree){
        i += segmentTree.length / 2;
        segmentTree[i] += val; // +1

        while (i > 1) {
            i = i >> 1;
            segmentTree[i] = segmentTree[i * 2] + segmentTree[i * 2 + 1];
        }
    }

    //time limit exceed
    //brute force o(N*N)
    public List<Integer> countSmaller_bruteForce(int[] nums) {
        if (nums.length == 1) return Arrays.asList(0);
        int max = nums[nums.length-1]; //for special case
        int min = nums[nums.length-1]; //for special case

        int[] list = new int[nums.length];
        for (int i = nums.length-2; i >= 0; i--){
            int c = 0;
            if (nums[i] > max){
                max = nums[i];
                list[i] = nums.length - i - 1;
                continue;
            }
            if (nums[i] <= min){
                min = nums[i];
                list[i] = 0;
                continue;
            }
            for (int j = i + 1; j < list.length; j++){
                if (nums[j] < nums[i]) {
                    c++;
                }else if (nums[j] == nums[i]) {
                    c += list[j];
                    break;
                }
            }
            list[i] = c;
        }

        List<Integer> res = new ArrayList<>();
        for (int k = 0; k < list.length; k++) res.add(list[k]);
        return res;
    }
}
