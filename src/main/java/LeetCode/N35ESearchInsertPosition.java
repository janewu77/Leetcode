package LeetCode;

/**
 *
 * Given a sorted array of distinct integers and a target value,
 * return the index if the target is found. If not,
 * return the index where it would be if it were inserted in order.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 *
 *
 * Example 1:
 * Input: nums = [1,3,5,6], target = 5
 * Output: 2
 *
 * Example 2:
 * Input: nums = [1,3,5,6], target = 2
 * Output: 1
 *
 * Example 3:
 * Input: nums = [1,3,5,6], target = 7
 * Output: 4
 */

/*
* 基本搜索算法：二分法 O(log n)
* */
public class N35ESearchInsertPosition {

    public static void main(String[] args) {

        //2
        System.out.println((new N35ESearchInsertPosition()).searchInsert(new int[]{1,3,5,6} , 5 ));
        // 1
        System.out.println((new N35ESearchInsertPosition()).searchInsert(new int[]{1,3,5,6} , 2 ));
        // 4
        System.out.println((new N35ESearchInsertPosition()).searchInsert(new int[]{1,3,5,6} , 7 ));

        // 2
        System.out.println((new N35ESearchInsertPosition()).searchInsert(new int[]{1,3,5,6} , 4 ));
    }

    // 2分法搜索
    // O(log n)
    public int searchInsert(int[] nums, int target) {
        int p, l = 0, r = nums.length - 1;

        while (l <= r) {
            p = (l + r) / 2;

            if (nums[p] == target)
                return p;

            if (nums[p] > target) {
                r = p - 1;
            }else {
                l = p + 1;
            }
        }
        return l;
    }

}

