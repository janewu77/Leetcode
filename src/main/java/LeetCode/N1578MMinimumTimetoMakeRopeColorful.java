package LeetCode;


import static java.time.LocalTime.now;

/**
 * Alice has n balloons arranged on a rope. You are given a 0-indexed string colors where colors[i] is the color of the ith balloon.
 *
 * Alice wants the rope to be colorful. She does not want two consecutive balloons to be of the same color, so she asks Bob for help. Bob can remove some balloons from the rope to make it colorful. You are given a 0-indexed integer array neededTime where neededTime[i] is the time (in seconds) that Bob needs to remove the ith balloon from the rope.
 *
 * Return the minimum time Bob needs to make the rope colorful.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: colors = "abaac", neededTime = [1,2,3,4,5]
 * Output: 3
 * Explanation: In the above image, 'a' is blue, 'b' is red, and 'c' is green.
 * Bob can remove the blue balloon at index 2. This takes 3 seconds.
 * There are no longer two consecutive balloons of the same color. Total time = 3.
 * Example 2:
 *
 *
 * Input: colors = "abc", neededTime = [1,2,3]
 * Output: 0
 * Explanation: The rope is already colorful. Bob does not need to remove any balloons from the rope.
 * Example 3:
 *
 *
 * Input: colors = "aabaa", neededTime = [1,2,3,4,1]
 * Output: 2
 * Explanation: Bob will remove the ballons at indices 0 and 4. Each ballon takes 1 second to remove.
 * There are no longer two consecutive balloons of the same color. Total time = 1 + 1 = 2.
 *
 *
 * Constraints:
 *
 * n == colors.length == neededTime.length
 * 1 <= n <= 105
 * 1 <= neededTime[i] <= 104
 * colors contains only lowercase English letters.
 */

/**
 * M - [time: 30+
 */
public class N1578MMinimumTimetoMakeRopeColorful {


    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun(10, "bbbb", new int[]{5,4,8,1});


        doRun(10, "aaaaa", new int[]{1,2,3,4,5});
        doRun(2, "aabaa", new int[]{1,2,3,4,1});

        doRun(3, "abaac", new int[]{1,2,3,4,5});
        doRun(0, "abc", new int[]{1,2,3});

        doRun(26, "aaabbbabbbb", new int[]{3,5,10,7,5,3,5,5,4,8,1});


        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, String colors, int[] neededTime) {
        int res = new N1578MMinimumTimetoMakeRopeColorful()
                .minCost(colors, neededTime);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //Runtime: 9 ms, faster than 92.94% of Java online submissions for Minimum Time to Make Rope Colorful.
    //Memory Usage: 51.4 MB, less than 88.71% of Java online submissions for Minimum Time to Make Rope Colorful.
    //Time: O(N); Space:O(1)
    public int minCost_2(String colors, int[] neededTime) {
        int res = 0, left = 0, right = 1;
        int sum = neededTime[left], max = neededTime[left];
        for (; right < neededTime.length; right++) {
            if (colors.charAt(right) != colors.charAt(left)) {
                if (right - left > 1) res += (sum - max);
                left = right;
                sum = neededTime[left]; max = neededTime[left];
            } else {
                sum += neededTime[right];
                max = Math.max(max, neededTime[right]);
            }
        }

        if (left < neededTime.length - 1) res += (sum - max);
        return res;
    }


    //Runtime: 7 ms, faster than 98.23% of Java online submissions for Minimum Time to Make Rope Colorful.
    //Memory Usage: 51.2 MB, less than 91.61% of Java online submissions for Minimum Time to Make Rope Colorful.
    //One pass | two pointers
    //Time: O(N); Space:O(1)
    public int minCost(String colors, int[] neededTime) {
        int res = 0, left = 0;

        for (int right = 1; right < neededTime.length; right++){

            if (colors.charAt(right) == colors.charAt(left)){
                res += Math.min(neededTime[left], neededTime[right]);
                left = neededTime[left] < neededTime[right] ? right: left;
            }else {
                left = right;
            }
        }
        return res;
    }
}
