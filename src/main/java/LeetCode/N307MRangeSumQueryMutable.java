package LeetCode;


/**
 * Given an integer array nums, handle multiple queries of the following types:
 *
 * Update the value of an element in nums.
 * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 * Implement the NumArray class:
 *
 * NumArray(int[] nums) Initializes the object with the integer array nums.
 * void update(int index, int val) Updates the value of nums[index] to be val.
 * int sumRange(int left, int right) Returns the sum of the elements of nums between
 * indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 *
 *
 * Example 1:
 *
 * Input
 * ["NumArray", "sumRange", "update", "sumRange"]
 * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
 * Output
 * [null, 9, null, 8]
 *
 * Explanation
 * NumArray numArray = new NumArray([1, 3, 5]);
 * numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
 * numArray.update(1, 2);   // nums = [1, 2, 5]
 * numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 3 * 104
 * -100 <= nums[i] <= 100
 * 0 <= index < nums.length
 * -100 <= val <= 100
 * 0 <= left <= right < nums.length
 * At most 3 * 104 calls will be made to update and sumRange.
 */

/**
 * M - [time: 60-]
 *  - Binary index tree; (Segment Tree)
 */
public class N307MRangeSumQueryMutable {

    public static void main(String[] args){
        new N307MRangeSumQueryMutable().doRun();
    }
    private void doRun(){
        int[] nums = new int[]{1, 3, 5};
        NumArray numArray = new NumArray(nums);
        int res = numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
        System.out.println(res);
        numArray.update(1, 2);   // nums = [1, 2, 5]
        res = numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8
        System.out.println(res);
    }

    //Runtime: 76 ms, faster than 96.97% of Java online submissions for Range Sum Query - Mutable.
    //Memory Usage: 70.6 MB, less than 95.43% of Java online submissions for Range Sum Query - Mutable.
    //1.Binary Index Tree
    //Time : O(n); Space: O(N)
    class NumArray {
        private int[] tree;

        //Time : O(n); Space: O(N)
        public NumArray(int[] nums) {
            // total possible values in nums plus one dummy
            //Space: O(N)
            tree = new int[nums.length + 1];   //default is zero.
            int i = 1;
            for (int n : nums) updateBIT(tree, i++, n);
        }

        //get the original value before update
        public void update(int index, int val) {
            index++;
            int v = queryBIT(tree, index) - (index > 1 ? queryBIT(tree, index - 1) : 0);
            updateBIT(tree, index, val - v);
        }

        public int sumRange(int left, int right) {
            return queryBIT(tree, right+1) - queryBIT(tree, left);
        }

        //standard Binary Index Tree
        private int lsb(int i){
            return i&(-i);
        }

        //time: O(logn)
        private int queryBIT(int[] tree, int i) {
            int sum = 0;
            for(; i >= 1; i -= lsb(i)) sum += tree[i];
            return sum;
        }

        //time: O(logn)
        private void updateBIT(int[] tree, int i, int val){
            for (; i<tree.length; i += lsb(i)) tree[i] += val;
        }
    }

    //https://leetcode.com/problems/range-sum-query-mutable/solution/
    //Sqrt Decomposition
    //Runtime: 216 ms, faster than 39.42% of Java online submissions for Range Sum Query - Mutable.
    //Memory Usage: 129.5 MB, less than 46.66% of Java online submissions for Range Sum Query - Mutable.
    //Time: O(n); Space: O(sqrt(n)）
    class NumArray_2 {
        private int[] b;
        private int len;
        private int[] nums;

        public NumArray_2(int[] nums) {
            this.nums = nums;
            double l = Math.sqrt(nums.length);
            len = (int) Math.ceil(nums.length/l);
            b = new int [len];
            for (int i = 0; i < nums.length; i++)
                b[i / len] += nums[i];
        }

        public int sumRange(int i, int j) {
            int sum = 0;
            int startBlock = i / len;
            int endBlock = j / len;
            if (startBlock == endBlock) {
                for (int k = i; k <= j; k++)
                    sum += nums[k];
            } else {
                for (int k = i; k <= (startBlock + 1) * len - 1; k++)
                    sum += nums[k];
                for (int k = startBlock + 1; k <= endBlock - 1; k++)
                    sum += b[k];
                for (int k = endBlock * len; k <= j; k++)
                    sum += nums[k];
            }
            return sum;
        }

        public void update(int i, int val) {
            int b_l = i / len;
            b[b_l] = b[b_l] - nums[i] + val;
            nums[i] = val;
        }

    }

}
