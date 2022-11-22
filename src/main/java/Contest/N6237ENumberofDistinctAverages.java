package Contest;

import java.io.IOException;
import java.time.Instant;
import java.util.*;

import static java.time.LocalTime.now;

/**
 * You are given a 0-indexed integer array nums of even length.
 *
 * As long as nums is not empty, you must repetitively:
 *
 * Find the minimum number in nums and remove it.
 * Find the maximum number in nums and remove it.
 * Calculate the average of the two removed numbers.
 * The average of two numbers a and b is (a + b) / 2.
 *
 * For example, the average of 2 and 3 is (2 + 3) / 2 = 2.5.
 * Return the number of distinct averages calculated using the above process.
 *
 * Note that when there is a tie for a minimum or maximum number, any can be removed.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,1,4,0,3,5]
 * Output: 2
 * Explanation:
 * 1. Remove 0 and 5, and the average is (0 + 5) / 2 = 2.5. Now, nums = [4,1,4,3].
 * 2. Remove 1 and 4. The average is (1 + 4) / 2 = 2.5, and nums = [4,3].
 * 3. Remove 3 and 4, and the average is (3 + 4) / 2 = 3.5.
 * Since there are 2 distinct numbers among 2.5, 2.5, and 3.5, we return 2.
 * Example 2:
 *
 * Input: nums = [1,100]
 * Output: 1
 * Explanation:
 * There is only one average to be calculated after removing 1 and 100, so we return 1.
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 100
 * nums.length is even.
 * 0 <= nums[i] <= 100
 */

/**
 * E - [time: 10-
 */
//2465. Number of Distinct Averages
public class N6237ENumberofDistinctAverages {


     static public void main(String... args) throws IOException{
         System.out.println(now());
         System.out.println("==================");
//        //doRun(0, 0);
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(long expect, int n) {
//        long res = new N1().n1(n);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //2.sort
    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Number of Distinct Averages.
    //Memory Usage: 40 MB, less than 83.33% of Java online submissions for Number of Distinct Averages.
    //Time: O(N * LogN); Space: O(N)
    public int distinctAverages(int[] nums) {
        Arrays.sort(nums);
        Set<Integer> set = new HashSet<>();
        int i = 0, j = nums.length - 1;
        while (i < j) set.add (nums[i++] + nums[j--]);
        return set.size();
    }

    //1.two heaps
    //Runtime: 7 ms, faster than 16.67% of Java online submissions for Number of Distinct Averages.
    //Memory Usage: 41.7 MB, less than 16.67% of Java online submissions for Number of Distinct Averages.
    //Time: O(N * logN); Space: O(N)
    public int distinctAverages_1(int[] nums) {
        if (nums.length == 2) return 1;
        Set<Integer> set = new HashSet<>();
        Queue<Integer> minHeap = new PriorityQueue<>();
        Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for(int n : nums) {
            maxHeap.add(n); minHeap.add(n);
        }
        for (int i = 0; i < nums.length / 2; i++) set.add(minHeap.poll() + maxHeap.poll());
        return set.size();
    }

}


