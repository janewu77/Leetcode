package LeetCode;


import static java.time.LocalTime.now;


/**
 * You are given two 0-indexed arrays nums1 and nums2 of length n, both of which are permutations of [0, 1, ..., n - 1].
 *
 * A good triplet is a set of 3 distinct values which are present in increasing order by position both in nums1 and nums2. In other words, if we consider pos1v as the index of the value v in nums1 and pos2v as the index of the value v in nums2, then a good triplet will be a set (x, y, z) where 0 <= x, y, z <= n - 1, such that pos1x < pos1y < pos1z and pos2x < pos2y < pos2z.
 *
 * Return the total number of good triplets.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [2,0,1,3], nums2 = [0,1,2,3]
 * Output: 1
 * Explanation:
 * There are 4 triplets (x,y,z) such that pos1x < pos1y < pos1z. They are (2,0,1), (2,0,3), (2,1,3), and (0,1,3).
 * Out of those triplets, only the triplet (0,1,3) satisfies pos2x < pos2y < pos2z. Hence, there is only 1 good triplet.
 * Example 2:
 *
 * Input: nums1 = [4,0,1,3,2], nums2 = [4,1,0,2,3]
 * Output: 4
 * Explanation: The 4 good triplets are (4,0,3), (4,0,2), (4,1,3), and (4,1,2).
 *
 *
 * Constraints:
 *
 * n == nums1.length == nums2.length
 * 3 <= n <= 105
 * 0 <= nums1[i], nums2[i] <= n - 1
 * nums1 and nums2 are permutations of [0, 1, ..., n - 1].
 */

/**
 * H - [time: 120+
 */
public class N2179HCountGoodTripletsinanArray {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun_demo(77, new int[]{13,14,10,2,12,3,9,11,15,8,4,7,0,6,5,1}, new int[]{8,7,9,5,6,14,15,10,2,11,4,13,3,12,1,0});

        doRun_demo(1, new int[]{2,0,1,3}, new int[]{0,1,2,3});
        doRun_demo(4, new int[]{4,0,1,3,2}, new int[]{4,1,0,2,3});

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun_demo(long expect,  int[] nums1, int[] nums2) {
        long res = new N2179HCountGoodTripletsinanArray().goodTriplets(nums1, nums2);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //3.Binary Index Tree
    //Runtime: 16 ms, faster than 95.71% of Java online submissions for Count Good Triplets in an Array.
    //Memory Usage: 83.6 MB, less than 51.43% of Java online submissions for Count Good Triplets in an Array.
    //Time: O(N * LogN); Space: O(N)
    public long goodTriplets(int[] nums1, int[] nums2) {
        long res = 0;

        int[] pos = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) pos[nums2[i]] = i;

        FenwickTree fenwickTree = new FenwickTree(nums1.length);
        for (int i = 0; i < nums1.length; i++){
            int idx = pos[nums1[i]];
            long left = fenwickTree.queryBIT(idx);
            long right = nums1.length - 1 - idx - (i - left); //这里是与前一个不同之处。右边的数量可以计算出来。
            res += left * right;

            fenwickTree.updateBIT(idx + 1, 1);
        }

        return res;
    }

    //Binary Index Tree / Fenwick Tree
    class FenwickTree {

        private int[] tree;
        public FenwickTree(int n){
            tree = new int[n + 2];
        }

        private int lsb(int i){
            return i &(-i);
        }
        public int queryBIT(int i){
            int sum = 0;
            for (; i > 0; i -= lsb(i)) sum += tree[i];
            return sum;
        }
        public void updateBIT(int i, int val){
            for (; i < tree.length; i += lsb(i)) tree[i] += val;
        }
    }

    //2.Binary Index Tree X 2
    //Runtime: 22 ms, faster than 90.00% of Java online submissions for Count Good Triplets in an Array.
    //Memory Usage: 56.6 MB, less than 95.71% of Java online submissions for Count Good Triplets in an Array.
    //Time: O(N * LogN); Space: O(N)
    public long goodTriplets_2(int[] nums1, int[] nums2) {
        long res = 0;
        long[] left = new long[nums1.length];
        long[] right = new long[nums1.length];

        int[] pos = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) pos[nums2[i]] = i;

        FenwickTree fenwickTree = new FenwickTree(nums1.length);
        for (int i = 0; i < nums1.length; i++){
            int idx = pos[nums1[i]] + 1;
            left[i] = fenwickTree.queryBIT(idx - 1);
            fenwickTree.updateBIT(idx, 1);
        }

        fenwickTree = new FenwickTree(nums1.length);
        for (int i = nums1.length - 1; i >= 0; i--){
            int idx = pos[nums1[i]] + 1;
            int currTotal = nums1.length - 1 - i;
            right[i] = currTotal - fenwickTree.queryBIT(idx);
            fenwickTree.updateBIT(idx, 1);
        }

        for (int i = 0; i < nums1.length; i++)
            res += left[i] * right[i];
        return res;
    }


    //1.Brute Force
    //TLE
    //Time: O(N ^ 2); Space: O(N)
    public long goodTriplets_1(int[] nums1, int[] nums2) {
        long res = 0;

        int[] pos = new int[nums1.length];
        for (int i = 0; i < nums2.length; i++) pos[nums2[i]] = i;

        for (int i = 1; i < nums1.length - 1; i++) {
            int mid = pos[nums1[i]];
            int countLeft = 0, countRight = 0;
            for (int j = 0; j < i; j++) if (pos[nums1[j]] < mid) countLeft++;
            for (int j = i + 1; j < nums1.length; j++) if (pos[nums1[j]] > mid) countRight++;

            res += countLeft * countRight;
        }
        return res;
    }
}
