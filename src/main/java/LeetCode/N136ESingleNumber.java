package LeetCode;

import java.util.*;

/**
 *
 * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 *
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 *
 *
 * Example 1:
 * Input: nums = [2,2,1]
 * Output: 1
 *
 *
 * Example 2:
 * Input: nums = [4,1,2,1,2]
 * Output: 4
 *
 *
 * Example 3:
 * Input: nums = [1]
 * Output: 1
 *
 * Constraints:
 *
 * 1 <= nums.length <= 3 * 104
 * -3 * 104 <= nums[i] <= 3 * 104
 * Each element in the array appears twice except for one element which appears only once.
 */

/**
 * E - （二种解法耗时11m)
 *  - 位运算、数学计算。（未想到）
 */
public class N136ESingleNumber {

    public static void main(String[] args){
//        int i = 33;
//        int j = 33;
        int x = 2 ^ 2 ^ 1;
        System.out.println("x:"+x);
    }

    // 位运算： [^ :相同为0 不同为1]
    public int singleNumber(int[] nums) {
        int result = nums[0];
        for (int i = 1; i < nums.length; i++){
            result = result ^ nums[i];
        }
        return result;
    }
    // 这里种用了set的去重功能
    public int singleNumber_math(int[] nums) {
        Integer sum = 0;
        for (int n : nums){
            setData.add(n);
            sum = sum + n;
        }
        Integer total = 0;
//        for (Integer i : setData) total +=i;
        total = setData.stream().reduce(0, (acc, n) -> acc + n);
        return total - sum + total;
    }

    Set<Integer> setData = new HashSet<Integer>();
    public int singleNumber_set(int[] nums) {
        for (int n : nums){
            if (setData.contains(n)){
                setData.remove(n);
            }else{
                setData.add(n);
            }
        }

        Iterator<Integer> it = setData.iterator();
        if(it.hasNext()) return it.next();
        return -1;
    }

    Map<Integer,Integer> data = new HashMap<>();
    public int singleNumber_map(int[] nums) {
        for (int n : nums){
            if (data.containsKey(n)){
                data.remove(n);
            }else{
                data.put(n, 1);
            }
        }

        for (int k : data.keySet()){
            return k;
        }
        return -1;
    }
}
