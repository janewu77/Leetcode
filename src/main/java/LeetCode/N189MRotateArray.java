package LeetCode;


/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 *
 *
 * Example 1:
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 *
 *
 * Example 2:
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 105
 *
 *
 * Follow up:
 *
 * Try to come up with as many solutions as you can.
 * There are at least three different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 */

/*
* M ：转换解决问题的思路。这里把"移动" 转换成了 "反转"
*   - 使用了反转技巧
*
* */
public class N189MRotateArray {

    public static void main(String[] args) {

        int[] nums1 = {1,2,3,4,5,6,7,8};
        new N189MRotateArray().rotate(nums1, 3);

        int[] nums2 = {-1,-100,3, 99};
        new N189MRotateArray().rotate(nums2, 2);

        int[] nums3 = {-1,-100,3, 99, 33};
        new N189MRotateArray().rotate(nums3, 3);

    }

    private void reverseArray(int[] nums, int begin, int end){
        while(begin <= end){
            int tmp = nums[begin];
            nums[begin++] = nums[end];
            nums[end--] = tmp;
        }
    }

    public void rotate(int[] nums, int k) {

        System.out.println();
        System.out.println("=======");

        k= k % nums.length;
        reverseArray(nums, 0, nums.length -1 );
        reverseArray(nums, 0, k-1 );
        reverseArray(nums, k, nums.length-1 );

        for (int i = 0; i< nums.length; i++){
            System.out.print(nums[i] + " ");
        }
    }

    /**
     * 还是超时
     */
    public void rotate_shiftBlock(int[] nums, int k) {
        System.out.println();
        System.out.println("=======");
        k =  k % nums.length;
        if (k == 0) return;
        int index_2 = nums.length - k;
        int index_1 = index_2 - k;
        while(index_2 > 0){
            if (index_1 < 0) {
                index_1 = 0;
            }
            nums = shiftBlock(nums, k, index_1, index_2);
            index_2 = index_1;
            index_1 = index_1 - k;
        }

        for (int i = 0; i< nums.length; i++){
            System.out.print(nums[i] + " ");
        }

    }
    private int[] shiftBlock(int[] nums, int k, int index_1, int index_2){
        if (k > (index_2 - index_1)){
            //余下的小块
            //System.out.print("@@@@@@");
            int x = index_2 - index_1 - 1;
            for(int i = x; i >= 0;i--){
                int tmp = nums[i];
                for (int j=0;j<k;j++){
                    nums[i+j] = nums[i+j+1];
                }
                nums[i+k] = tmp;
            }
            return nums;
        }
        for (int i=0;i<k;i++) {
            int tmp = nums[index_2+i];
            nums[index_2+i] = nums[index_1+i];
            nums[index_1+i] = tmp;
        }
        return nums;
    }

    /**
     * 超时
     */
    public void rotate_brute(int[] nums, int k) {
        System.out.println();
        System.out.println("=======");
        for (int i= 1; i<=k; i++){
            nums = moveOnce(nums);
        }
        for (int i= 0; i< nums.length; i++){
            System.out.print(nums[i] + " ");
        }
    }

    private int[] moveOnce(int[] nums){
        int tmp = nums[nums.length-1];
        for (int i = nums.length-1; i > 0; i--){
            nums[i] = nums[i-1];
        }
        nums[0] = tmp;
        return nums;
    }
}
