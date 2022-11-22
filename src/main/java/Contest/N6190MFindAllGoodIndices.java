package Contest;



import java.util.*;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;


/**
 * You are given a 0-indexed integer array nums of size n and a positive integer k.
 *
 * We call an index i in the range k <= i < n - k good if the following conditions are satisfied:
 *
 * The k elements that are just before the index i are in non-increasing order.
 * The k elements that are just after the index i are in non-decreasing order.
 * Return an array of all good indices sorted in increasing order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,1,1,1,3,4,1], k = 2
 * Output: [2,3]
 * Explanation: There are two good indices in the array:
 * - Index 2. The subarray [2,1] is in non-increasing order, and the subarray [1,3] is in non-decreasing order.
 * - Index 3. The subarray [1,1] is in non-increasing order, and the subarray [3,4] is in non-decreasing order.
 * Note that the index 4 is not good because [4,1] is not non-decreasing.
 * Example 2:
 *
 * Input: nums = [2,1,1,2], k = 2
 * Output: []
 * Explanation: There are no good indices in this array.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 3 <= n <= 105
 * 1 <= nums[i] <= 106
 * 1 <= k <= n / 2
 */
/**
 * M - [time: 60:
 */

//2420. Find All Good Indices
public class N6190MFindAllGoodIndices {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;



        doRun("4,5", new int[]{878724,201541,179099,98437,35765,327555,475851,598885,849470,943442}, 4);

        doRun("2,3", new int[]{2,1,1,1,3,4,1}, 2);
        doRun("1", new int[]{440043,276285,336957}, 1);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(String expect, int[] nums, int k) {
        List<Integer> res1 = new N6190MFindAllGoodIndices()
                .goodIndices(nums, k);
        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 6 ms, faster than 100.00% of Java online submissions for Find All Good Indices.
    //Memory Usage: 56.2 MB, less than 100.00% of Java online submissions for Find All Good Indices.
    //two pass + 1d array
    //Time: O(N + N); Space:O(N)
    //Time: O(N); Space:O(N)
    public List<Integer> goodIndices(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        int[] suffix = new int[nums.length];

        //backward
        //in non-decreasing order.(increasing)
        for(int i = nums.length - 2; i >= 0; i--)
            if (nums[i] <= nums[i + 1])
                suffix[i] = suffix[i + 1] + 1;

        //forward
        //in non-increasing order(decreasing)
        int prefix = 0;
        for(int i = 1; i < nums.length - 1; i++){
            if (i >= k && prefix >= k - 1 && suffix[i + 1] >= k - 1 )
                res.add(i);

            prefix = nums[i] <= nums[i - 1] ? prefix + 1 : 0;
        }
        return res;
    }


    //Runtime: 16 ms, faster than 50.00% of Java online submissions for Find All Good Indices.
    //Memory Usage: 112.9 MB, less than 16.67% of Java online submissions for Find All Good Indices.
    //two pass + two 1d array  (more readable than one array...
    //Time: O(N + N); Space:O(N + N)
    //Time: O(N); Space:O(N)
    public List<Integer> goodIndices_21(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        int[] rightPart = new int[nums.length];
        int[] leftPart  = new int[nums.length];

        //backward
        //in non-decreasing order.(increasing)
        for(int i = nums.length - 2; i >= 0; i--){
            if (nums[i] <= nums[i + 1])
                rightPart[i] = rightPart[i + 1] + 1;
        }

        //forward
        int k1 = k - 1;
        for(int i = 1; i < nums.length - 1; i++){
            if (nums[i] <= nums[i - 1])
                leftPart[i] = leftPart[i - 1] + 1;

            if (leftPart[i - 1] >= k1 && rightPart[i + 1] >= k1 )
                res.add(i);
        }
        return res;
    }

    //Runtime: 38 ms, faster than 50.00% of Java online submissions for Find All Good Indices.
    //Memory Usage: 130 MB, less than 16.67% of Java online submissions for Find All Good Indices.
    //two pass + set + without sort
    //Time: O(N + N); Space:O(N)
    //Time: O(N); Space:O(N)
    public List<Integer> goodIndices_12(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();

        //Space:O(N)
        Set<Integer> set = new HashSet<>();
        int idx, count;

        //backward
        //in non-decreasing order.(increasing)
        idx = nums.length - 2; count = 1;
        //Time: O(N)
        while (idx >= 0) {
            if (count >= k) {
                set.add(idx);
                count--;
            }
            count = nums[idx + 1] >= nums[idx--] ? count + 1 : 1;
        }

        //forward
        //in non-increasing order(decreasing)
        idx = 1; count = 1;
        //Time: O(N)
        while (idx < nums.length){
            if (count >= k) {
                if (set.contains(idx)) res.add(idx);
                count--;
            }
            count = nums[idx - 1] >= nums[idx++] ? count + 1 : 1;
        }
        return res;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    //contest version
    //Runtime: 40 ms, faster than 50.00% of Java online submissions for Find All Good Indices.
    //Memory Usage: 130.5 MB, less than 16.67% of Java online submissions for Find All Good Indices.
    //two pass + set + sort
    //Time: O(N + N+ NlgN); Space:O(N + lgN)
    //Time: O(NlgN); Space:O(N)
    public List<Integer> goodIndices_1(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();

        //Space:O(N)
        Set<Integer> set = new HashSet<>();

        //in non-increasing order(decreasing)
        int idx = 0; //forward
        int prevOne = nums[idx++];
        int count = 1;
        //Time: O(N)
        while (idx < nums.length){

            if (count >= k) {
                set.add(idx);
                count--;
            }

            int currOne = nums[idx++];
            if (currOne <= prevOne) count++;
            else count = 1;
            prevOne = currOne;
        }

        //in non-decreasing order.(increasing)
        idx = nums.length - 1; //backward
        prevOne = nums[idx--];
        count = 1;
        //Time: O(N)
        while (idx >= 0) {
            if (count >= k) {
                if (set.contains(idx)) res.add(idx);
                count--;
            }
            int currOne = nums[idx--];
            if (currOne <= prevOne) count++;
            else count = 1;
            prevOne = currOne;
        }

        //Time: O(NLgN); Space:O(lgN)
        Collections.sort(res);
        return res;
    }

}
