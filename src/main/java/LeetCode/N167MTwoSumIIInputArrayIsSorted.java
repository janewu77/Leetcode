package LeetCode;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
 *
 * Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.
 *
 * The tests are generated such that there is exactly one solution. You may not use the same element twice.
 *
 * Your solution must use only constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
 * Example 2:
 *
 * Input: numbers = [2,3,4], target = 6
 * Output: [1,3]
 * Explanation: The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We return [1, 3].
 * Example 3:
 *
 * Input: numbers = [-1,0], target = -1
 * Output: [1,2]
 * Explanation: The sum of -1 and 0 is -1. Therefore index1 = 1, index2 = 2. We return [1, 2].
 *
 *
 * Constraints:
 *
 * 2 <= numbers.length <= 3 * 104
 * -1000 <= numbers[i] <= 1000
 * numbers is sorted in non-decreasing order.
 * -1000 <= target <= 1000
 * The tests are generated such that there is exactly one solution.
 */
public class N167MTwoSumIIInputArrayIsSorted {

    //Runtime: 1 ms, faster than 99.60% of Java online submissions for Two Sum II - Input Array Is Sorted.
    //Memory Usage: 45 MB, less than 92.82% of Java online submissions for Two Sum II - Input Array Is Sorted.
    //Two pointers
    //Time: O(N); Space:O(1)
    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while(left < right){
            int sum = numbers[left] + numbers[right];
            if (sum == target) return new int[]{left + 1, right + 1};
            else if (sum > target) right--;
            else left++;
        }
        return null;
    }


    //Runtime: 4 ms, faster than 39.50% of Java online submissions for Two Sum II - Input Array Is Sorted.
    //Memory Usage: 49.9 MB, less than 59.51% of Java online submissions for Two Sum II - Input Array Is Sorted.
    //Binary Search
    //Time: O(N * logN); Space:O(1)
    public int[] twoSum_2(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++){
            int c = target - numbers[i];
            int idx = Arrays.binarySearch(numbers, i + 1, numbers.length, c);
            if (idx > 0) return new int[]{i + 1, idx + 1};
        }
        return null;
    }


    //Runtime: 8 ms, faster than 24.31% of Java online submissions for Two Sum II - Input Array Is Sorted.
    //Memory Usage: 50.3 MB, less than 24.88% of Java online submissions for Two Sum II - Input Array Is Sorted.
    //Hashmap + one pass
    //Time: O(N); Space:O(N)
    public int[] twoSum_1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                int j =  map.get(complement) + 1;
                if (j < i + 1) return new int[]{j, j + 1};
                else return new int[]{i + 1, j};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
