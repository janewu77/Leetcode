package LeetCode;

import utils.JPrint;
import utils.comm;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Given an array nums of n integers, are there elements a, b, c in nums
 * such that a + b + c = 0? Find all unique triplets in the array
 *  which gives the sum of zero.
 * Notice that the solution set must not contain duplicate triplets.
 *
 * Example 1:
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 *
 * Example 2:
 * Input: nums = []
 * Output: []
 *
 * Example 3:
 * Input: nums = [0]
 * Output: []
 *
 * Constraints:
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 *
*/

/*
*
* 基本结构：HashSet的应用 （all unique triplets）
* M： 先sort, 然后定一个数， 再用 left 、 right 进行遍历。利用排序后由小到大的特性，减少运算次数。
*
* */

public class N15M3Sum {

    public static void main(String[] args) {

        doRun("[]", new int[]{});
        doRun("[]", new int[]{0});
        doRun("[]", new int[]{-1,-3,-1});
        doRun("[]", new int[]{-1,-3,0});
        doRun("[]", new int[]{0,0,1});
        doRun("[]", new int[]{1,3,1});
        doRun("[[0, 0, 0]]", new int[]{0,0,0});
        doRun("[[0, 0, 0]]", new int[]{0,0,0,0});
        doRun("[]", new int[]{1,2,-2,-1});
        doRun("[[-1, 0, 1]]", new int[]{-1,0,1,0});
        doRun("[[-1, -1, 2],[-1, 0, 1]]", new int[]{-1,0,1,2,-1,-4});
        doRun("[[-1, 0, 1],[0, 0, 0]]", new int[]{1,-1,0,0,0,0,0,0,0});
        doRun("[[-2, 1, 1]]", new int[]{1,1,-2});
        doRun("[[-4, 2, 2]]", new int[]{2,2,3,-2,-4});

        String expect ="[[-82, 33, 49],[-82, 26, 56],[-82, 13, 69],[-82, 17, 65],[-82, 34, 48],[-82, 36, 46],[-82, -11, 93],[-82, 21, 61],[-70, 1, 69],[-70, -14, 84],[-70, -6, 76],[-70, 13, 57],[-70, 15, 55],[-70, 34, 36],[-70, 21, 49],[-66, 1, 65],[-66, 17, 49],[-66, -11, 77],[-66, 10, 56],[-66, -3, 69],[-49, 1, 48],[-49, -6, 55],[-49, 13, 36],[-49, 15, 34],[-49, 2, 47],[-49, 21, 28],[-49, -3, 52],[-43, -14, 57],[-43, -6, 49],[-43, 15, 28],[-43, 17, 26],[-43, 10, 33],[-43, 12, 31],[-43, -3, 46],[-29, 12, 17],[-29, -14, 43],[-29, 1, 28],[-14, -3, 17],[-14, 2, 12],[-14, 1, 13],[-11, -6, 17],[-11, 1, 10],[-3, 1, 2]]";
        int[] data = new int[]{34,55,79,28,46,33,2,48,31,-3,84,71,52,-3,93,15,21,-43,57,-6,86,56,94,74,83,-14,28,-66,46,-49,62,-11,43,65,77,12,47,61,26,1,13,29,55,-82,76,26,15,-29,36,-29,10,-70,69,17,49};
        doRun(expect, data);
    }

    static private void doRun(String expect, int[] nums) {
        List<List<Integer>> res1 = new N15M3Sum().threeSum(nums);
        String res = comm.toString(res1);

        if (res1.size() < 5)
            System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        else {
            Collections.sort(res1, Comparator.comparingInt(a -> a.get(0)));
            res = comm.toString(res1);
            System.out.println("["+(expect.equals(res))+"]");
            System.out.println(" expect:" + expect);
            System.out.println("    res:" + res);
            System.out.println("");
        }
    }

    //5.without sort
    //Runtime: 384 ms, faster than 24.18% of Java online submissions for 3Sum.
    //Memory Usage: 120.2 MB, less than 15.92% of Java online submissions for 3Sum.
    //Time: O(N * N * log3); Space:O(N)
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> resultSet = new HashSet();

        Set<Integer> duplicatedSet = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length - 2; i++) {
            if (!duplicatedSet.add(nums[i])) continue;

            for (int j = i + 1; j < nums.length; j++) {
                int value = 0 - nums[i] - nums[j];
                if (map.containsKey(value) && map.get(value) == i) {
                    List<Integer> list = new ArrayList<>(Arrays.asList(nums[i], nums[j], value));
                    Collections.sort(list);
                    resultSet.add(list);
                }
                map.put(nums[j], i);
            }
        }
        return new ArrayList<>(resultSet) ;
    }

    //先sort, 然后定一个数， 再用 left 、 right 进行遍历
    //跳过大于0的数列；跳过重复数值
    //update 2022.10.8
    //4.Two pointers
    //Runtime: 46 ms,42.15%; Memory: 49.3MB 51%
    //Time: O(N * LogN + N * N); Space : O(LogN)
    public List<List<Integer>> threeSum_4(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();

        //Space:O(LogN)
        Arrays.sort(nums);

        for(int i = 0; i <= nums.length - 3 && nums[i] <= 0;){
            int left = i + 1, right = nums.length - 1;
            if (0 - nums[i] - nums[left] < 0) break;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                    //skip duplicated number
                    while (left < right && nums[right] == nums[right + 1]) right--;

                } else if (sum < 0){
                    left++;
                    while (left < right && nums[left] == nums[left - 1]) left++;

                }else{
                    //sum == 0
                    resultList.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    right--;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                    left++;
                    while (left < right && nums[left] == nums[left - 1]) left++;
                }
            }
            i++;
            while(i < nums.length - 2 && nums[i] == nums[i - 1]) i++;
        }
        return resultList;
    }


    //3.Binary Search
    //Runtime: 99 ms, faster than 29.77% of Java online submissions for 3Sum.
    //Memory Usage: 60.2 MB, less than 31.74% of Java online submissions for 3Sum.
    //Time: O(N * logN + N * N * logN); Space:O(N + LogN)
    public List<List<Integer>> threeSum_3(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();

        //Space:O(LogN)
        Arrays.sort(nums);

        for(int i = 0; i < nums.length - 2 && nums[i] <= 0;){
            for (int j = i + 1; j < nums.length && nums[i] + nums[j] <= 0;) {
                int value = 0 - nums[i] - nums[j];
                int idx = Arrays.binarySearch(nums, j + 1, nums.length, value);
                if (idx >= 0)
                    resultList.add(Arrays.asList(nums[i], nums[j], value));
                j++;
                while (j < nums.length && nums[j] == nums[j - 1]) j++;
            }
            i++;
            while(i < nums.length - 2 && nums[i] == nums[i - 1]) i++;
        }
        return resultList;
    }


    //2.HashMap
    //Runtime: 69 ms, faster than 37.65% of Java online submissions for 3Sum.
    //Memory Usage: 50.3 MB, less than 46% of Java online submissions for 3Sum.
    //Time:O(N * logN + N * N); Space: O(logN + N)
    //Time:O(N * N); Space: O(logN + N)
    public List<List<Integer>> threeSum_2(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        Arrays.sort(nums);

        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++)
            map.put(nums[i], i);

        for(int i = 0; i < nums.length - 2 && nums[i] <= 0;){
            for (int j = i + 1; j < nums.length && nums[i] + nums[j] <= 0;) {
                int value = 0 - nums[i] - nums[j];
                if (value < nums[j]) break;

                if (map.getOrDefault(value, j) > j)
                    resultList.add(Arrays.asList(nums[i], nums[j], value));

                j++;
                while (j < nums.length && nums[j] == nums[j - 1]) j++;
            }
            i++;
            while(i < nums.length - 2 && nums[i] == nums[i - 1]) i++;
        }
        return resultList;
    }


    //1.brute force
    //TLE
    //Time: O(N * N * N * log3); Space: O(N)
    public List<List<Integer>> threeSum_brute(int[] nums) {
        Set<List<Integer>> resultSet = new HashSet();
        for (int i = 0; i < nums.length - 2; i++)
            for (int j= i + 1; j < nums.length - 1; j++)
                for (int k = j + 1 ; k < nums.length; k++)
                    if (0 == nums[i] + nums[j] + nums[k]) {
                        List<Integer> list = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k]));
                        Collections.sort(list);
                        resultSet.add(list);
                    }
        return new ArrayList<>(resultSet);
    }
}
