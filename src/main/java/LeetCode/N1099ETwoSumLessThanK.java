package LeetCode;

import java.io.IOException;
import java.util.Arrays;

import static java.time.LocalTime.now;

public class N1099ETwoSumLessThanK {


    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");

        doRun(58, new int[]{34,23,1,24,75,33,54,8}, 60);
        doRun(-1, new int[]{10,20,30}, 15);
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[] nums, int k) {
        int res = new N1099ETwoSumLessThanK().twoSumLessThanK(nums, k);

//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //2.Binary Search
    //Runtime: 2 ms, faster than 99.67% of Java online submissions for Two Sum Less Than K.
    //Memory Usage: 40.7 MB, less than 90.90% of Java online submissions for Two Sum Less Than K.
    //Time: O(N * logN); Space: O(logN)
    public int twoSumLessThanK(int[] nums, int k) {
        //Time: O(N * logN); Space: O(logN)
        Arrays.sort(nums);

        //Time: O(N * logN); Space: O(1)
        int res = -1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > k / 2) break;
            int idx = Arrays.binarySearch(nums,i + 1, nums.length, k - nums[i] - 1);
            if (idx < 0) idx = -idx - 2;
            if (idx > i) res = Math.max(res, nums[i] + nums[idx]);
        }
        return res;
    }

    //1.two pointers
    //Runtime: 4 ms, faster than 63.12% of Java online submissions for Two Sum Less Than K.
    //Memory Usage: 42.3 MB, less than 51.43% of Java online submissions for Two Sum Less Than K.
    //Time: O(N * logN); Space: O(logN)
    public int twoSumLessThanK_1(int[] nums, int k) {
        //Time: O(N * logN); Space: O(logN)
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        int res = -1;
        //Time: O(N/2)
        while (left < right) {
            if (nums[left] > k / 2) break;
            int sum = nums[left] + nums[right];
            if (sum < k) {
                res = Math.max(res, sum);
                left++;
            }else right--;
        }
        return res;
    }
}
