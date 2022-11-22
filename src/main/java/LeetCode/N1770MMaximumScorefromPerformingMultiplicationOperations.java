package LeetCode;


import java.util.Arrays;

import static java.time.LocalTime.now;

/**
 * You are given two integer arrays nums and multipliers of size n and m respectively, where n >= m. The arrays are 1-indexed.
 *
 * You begin with a score of 0. You want to perform exactly m operations. On the ith operation (1-indexed), you will:
 *
 * Choose one integer x from either the start or the end of the array nums.
 * Add multipliers[i] * x to your score.
 * Remove x from the array nums.
 * Return the maximum score after performing m operations.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3], multipliers = [3,2,1]
 * Output: 14
 * Explanation: An optimal solution is as follows:
 * - Choose from the end, [1,2,3], adding 3 * 3 = 9 to the score.
 * - Choose from the end, [1,2], adding 2 * 2 = 4 to the score.
 * - Choose from the end, [1], adding 1 * 1 = 1 to the score.
 * The total score is 9 + 4 + 1 = 14.
 * Example 2:
 *
 * Input: nums = [-5,-3,-3,-2,7,1], multipliers = [-10,-5,3,4,6]
 * Output: 102
 * Explanation: An optimal solution is as follows:
 * - Choose from the start, [-5,-3,-3,-2,7,1], adding -5 * -10 = 50 to the score.
 * - Choose from the start, [-3,-3,-2,7,1], adding -3 * -5 = 15 to the score.
 * - Choose from the start, [-3,-2,7,1], adding -3 * 3 = -9 to the score.
 * - Choose from the end, [-2,7,1], adding 1 * 4 = 4 to the score.
 * - Choose from the end, [-2,7], adding 7 * 6 = 42 to the score.
 * The total score is 50 + 15 - 9 + 4 + 42 = 102.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * m == multipliers.length
 * 1 <= m <= 103
 * m <= n <= 105
 * -1000 <= nums[i], multipliers[i] <= 1000
 */
public class N1770MMaximumScorefromPerformingMultiplicationOperations {

    public static void main(String[] args){
        System.out.println(now());
        int[] data, data2;


        data = new int[]{1,-3, -3, -2};
        data2 = new int[]{2,1};
        doRun(0, data,  data2);

        data = new int[]{1,-3};
        data2 = new int[]{2,1};
        doRun(-1, data,  data2);

        data = new int[]{-5,-3,-3,-2,7,1};
        data2 = new int[]{-10,-5,3,4,6};
        doRun(102, data,  data2);

        data = new int[]{1,3};
        data2 = new int[]{7,1};
        doRun(22, data,  data2);

        data = new int[]{1,2,3};
        data2 = new int[]{2,1};
        doRun(8, data,  data2);

        data = new int[]{1,2,3};
        data2 = new int[]{3,2,1};
        doRun(14, data,  data2);

        data = new int[]{-947,897,328,-467,14,-918,-858,-701,-518,-997,22,259,-4,968,947,582,-449,895,-121,-403,633,490,64,543,-396,-997,841,-398,247,297,-147,-708,804,-199,-765,-547,-599,406,-223,-11,663,746,-365,-859,256,-25,919,-334,490,-511,865,-139,-968,961,-793,451,317,645,-294,240,-312,-822,961,-572,309,579,161,780,525,-622,-511,423,946,-28,-199,822,-123,-316,-913,113,-458,-428,-414,49,922,722,-854,323,-219,581,302,124,164,31,727,186,308,-936,-937,-862,939,213,966,-74,-76,-1,521,777,-966,454,-199,526,-895,447,-749,-518,-639,849,-771,979,-760,-763,-601,-201,40,-911,207,890,-942,-352,700,267,830,-396,536,877,-896,-687,262,-60,-373,-373,526};
        data2 = new int[]{864,849,586,769,309,-413,318,652,883,-690,796,251,-648,433,1000,-969,422,194,-785,-242,-118,69,187,-925,-418,-556,88,-399,-619,-383,-188,206,-793,-9,738,-587,878,360,640,318,-399,-366,365,-291,-75,-451,-674,-199,177,862,1,11,390,-52,-101,127,-354,-717,-717,180,655,817,-898,28,-641,-35,-563,-737,283,-223,-322,-59,955,172,230,512,-205,-180,899,169,-663,-253,270,651,168,417,613,-443,980,-189,417,372,-891,-272,993,-877,906,680,-630,-328,-873,-811,78,-667,-2,190,-773,878,529,620,-951,-687,314,-989,-48,-601,-950,31,-789,-663,-480,750,-872,-358,529,-426,-111,517,750,-536,-673,370};
        doRun(32383191, data,  data2);

        System.out.println(now());
        System.out.println("==================");
    }

    private static void doRun(int expected, int[] nums, int[] multipliers){
        int res = new N1770MMaximumScorefromPerformingMultiplicationOperations().maximumScore(nums, multipliers);
        System.out.println("["+(expected == res)+"].expected:"+ expected+".res:"+res);
    }


    //Runtime: 18 ms, faster than 100.00% of Java online submissions for Maximum Score from Performing Multiplication Operations.
    //Memory Usage: 50 MB, less than 98.84% of Java online submissions for Maximum Score from Performing Multiplication Operations.
    //DP - 1d arrays
    //Time: O(M * M); Space: O(M)
    public int maximumScore(int[] nums, int[] multipliers) {
        int[] dp = new int[multipliers.length + 1];

        for (int idx = multipliers.length - 1; idx >= 0; idx--) {
            for (int left = 0; left <= idx; left++){
                int right =  nums.length - 1 - (idx - left);
                dp[left] = Math.max(
                        nums[left] * multipliers[idx] + dp[left + 1],
                        nums[right] * multipliers[idx] + dp[left]);
            }
        }
        return dp[0];
    }


    //Runtime: 63 ms, faster than 84.53% of Java online submissions for Maximum Score from Performing Multiplication Operations.
    //Memory Usage: 103.7 MB, less than 73.60% of Java online submissions for Maximum Score from Performing Multiplication Operations.
    //Dynamic Programming DP - 2d arrays
    //Time: O(M * M); Space: O(M * M)
    public int maximumScore_2(int[] nums, int[] multipliers) {
        int[][] dp = new int[multipliers.length + 1][multipliers.length + 1];

        for (int idx = multipliers.length - 1; idx >= 0; idx--) {
            for (int left = 0; left <= idx; left++){
                int right =  nums.length - 1 - (idx - left);
                dp[idx][left] = Math.max(
                        nums[left] * multipliers[idx] + dp[idx + 1][left + 1],
                        nums[right] * multipliers[idx] + dp[idx + 1][left]);
            }
        }
        return dp[0][0];
    }



    //Runtime: 193 ms, faster than 56.09% of Java online submissions for Maximum Score from Performing Multiplication Operations.
    //Memory Usage: 95.9 MB, less than 84.62% of Java online submissions for Maximum Score from Performing Multiplication Operations.
    //recursion + memo
    //Time: O(M + 2^M); Space: O(M * M)
    //Time: O(2^M); Space: O(M * M)
    public int maximumScore_1(int[] nums, int[] multipliers) {
        memo = new int[multipliers.length][multipliers.length];
        for (int[] item : memo) Arrays.fill(item, Integer.MIN_VALUE);

        return helper_recursion(nums, 0, multipliers, 0);
    }

    int[][] memo;
    private int helper_recursion(int[] nums, int begin, int[] multipliers, int idx){
        if (idx == multipliers.length) return 0;

        if (memo[idx][begin] != Integer.MIN_VALUE) return memo[idx][begin];

        int end = nums.length - 1 - (idx - begin);
        //if (begin > end) return 0;

        int res = Math.max(
                nums[begin] * multipliers[idx] + helper_recursion(nums, begin + 1,  multipliers, idx + 1), //The start
                nums[end] * multipliers[idx] + helper_recursion(nums, begin,  multipliers, idx + 1)); // The end

        return memo[idx][begin] = res;
    }
}
