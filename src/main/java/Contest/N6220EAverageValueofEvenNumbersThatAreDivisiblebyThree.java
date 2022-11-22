package Contest;

import static java.time.LocalTime.now;


/**
 * Given an integer array nums of positive integers, return the average value of all even integers that are divisible by 3.
 *
 * Note that the average of n elements is the sum of the n elements divided by n and rounded down to the nearest integer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,6,10,12,15]
 * Output: 9
 * Explanation: 6 and 12 are even numbers that are divisible by 3. (6 + 12) / 2 = 9.
 * Example 2:
 *
 * Input: nums = [1,2,4,7,10]
 * Output: 0
 * Explanation: There is no single number that satisfies the requirement, so return 0.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 */

/**
 * E - [time: 20-
 */
//2455. Average Value of Even Numbers That Are Divisible by Three
public class N6220EAverageValueofEvenNumbersThatAreDivisiblebyThree {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun(0, new int[]{1,2,4,7,10});
        doRun(9, new int[]{1,3,6,10,12,15, 6});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[] nums) {
        int res = new N6220EAverageValueofEvenNumbersThatAreDivisiblebyThree().averageValue(nums);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //@conchwu
    //Runtime: 2 ms, faster than 100.00% of Java online submissions for Average Value of Even Numbers That Are Divisible by Three.
    //Memory Usage: 46.2 MB, less than 60.00% of Java online submissions for Average Value of Even Numbers That Are Divisible by Three.
    public int averageValue(int[] nums) {
        int sum = 0, count = 0;
        for(int n : nums){
            if (n % 6 == 0) {
                sum += n;
                count++;
            }
        }
        return count == 0 ? 0 : sum / count;
    }

}
