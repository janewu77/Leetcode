package LeetCode;

/**
 * Given an array nums containing n distinct numbers in the range [0, n],
 * return the only number in the range that is missing from the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,0,1]
 * Output: 2
 * Explanation: n = 3 since there are 3 numbers, so all numbers are in the range
 * [0,3]. 2 is the missing number in the range since it does not appear in nums.
 * Example 2:
 *
 * Input: nums = [0,1]
 * Output: 2
 * Explanation: n = 2 since there are 2 numbers, so all numbers are
 * in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.
 * Example 3:
 *
 * Input: nums = [9,6,4,2,3,5,7,0,1]
 * Output: 8
 * Explanation: n = 9 since there are 9 numbers, so all numbers are
 * in the range [0,9]. 8 is the missing number in the range since it does not appear in nums.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 104
 * 0 <= nums[i] <= n
 * All the numbers of nums are unique.
 *
 *
 * Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
 */

/**
 *
 * E - [time: 15-]
 *  - 也可以用公式 Gauss' Formula  math: n*(n+1)/2
 */
public class N268EMissingNumber {

    public static void main(String[] args) {

        int[] data;

        data = new int[]{1,2,0};
        doRun(3, data);

        data = new int[]{1,3,0};
        doRun(2, data);


        data = new int[]{1,2,6,3,5,4};
        doRun(0, data);


        data = new int[]{1,2,3};
        doRun(0, data);
    }

    private static void doRun(int expected, int[] nums){
        int res = new N268EMissingNumber().missingNumber(nums);
        //String strRes = Arrays.stream(res).mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(""));
        //System.out.println("=======");
        System.out.println("["+(expected == res)+"].[expected:"+ expected+"] res:"+res);
    }

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Missing Number.
    //Memory Usage: 50.7 MB, less than 73.87% of Java online submissions for Missing Number.
    //math: n*(n+1)/2
    public int missingNumber(int[] nums) {
        int sum = 0, i = 1;
        for (int n: nums) sum += (i++) - n;
        return sum;
    }

    //Runtime: 1 ms, faster than 78.58% of Java online submissions for Missing Number.
    //Memory Usage: 51.2 MB, less than 46.73% of Java online submissions for Missing Number.
    //Time: O(N); Space:O(1)
    public int missingNumber_1(int[] nums) {

        boolean missZero = true;
        int zeroIdx = 1;
        for (int i = 0; i < nums.length; i++){
            int idx = Math.abs(nums[i]) - 1;
            if (idx == -1) {
                missZero = false;
                continue;
            }
            nums[idx] = -nums[idx];
            if (nums[idx] == 0) zeroIdx = -idx;
        }
        if (missZero) return 0;

        for (int i = 0; i < nums.length; i++){
            if (nums[i] > 0)  return i + 1;
            if (nums[i] == 0 && zeroIdx > 0) return i + 1;
        }
        return -1;
    }
}
