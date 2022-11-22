package LeetCode;

/**
 * You are given an integer array height of length n. There are n vertical
 * lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 *
 * Find two lines that together with the x-axis form a container,
 * such that the container contains the most water.
 *
 * Return the maximum amount of water a container can store.
 *
 * Notice that you may not slant the container.
 *
 *
 * Example 1:
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
 * In this case, the max area of water (blue section) the container can contain is 49.
 *
 * Example 2:
 * Input: height = [1,1]
 * Output: 1
 *
 * Constraints:
 * n == height.length
 * 2 <= n <= 105
 * 0 <= height[i] <= 104
 */

/**
 * M - [time: 60-]
 */

public class N11MContainerWithMostWater {

    public static void main(String[] args) {
        //int expected;
        int[] data;

        data = new int[]{1,8,6,2,5,4,8,3,7};
        doRun(49, data);

        data = new int[]{1,1};
        doRun(1, data);

        data = new int[]{1,3,2,5,25,24,5};
        doRun(24, data);

    }

    private static void doRun(int expected, int[] height){
        int res = new N11MContainerWithMostWater().maxArea(height);
        //String strRes = Arrays.stream(res).mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(""));
        System.out.println("["+(expected == res)+"].[expected:"+ expected+"] res:"+res);
    }

    ////////////////////////////////////////////////////////////////////////////
    //Runtime: 4 ms, faster than 84.23% of Java online submissions for Container With Most Water.
    //Memory Usage: 71.6 MB, less than 81.04% of Java online submissions for Container With Most Water.
    //two pointers
    //Time: O(N); Space: O(1)
    public int maxArea(int[] height) {
        //if (height.length == 2) return Math.min(height[0], height[1]);
        int res = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            res = Math.max(res, (right - left) * Math.min(height[left], height[right]));
            if (height[left] <= height[right]) left++;
            else right--;
        }
        return res;
    }


    ////////////////////////////////////////////////////////////////////
    //Time Limit Exceeded
    public int maxArea_1(int[] height) {
        int res = 0;

        for (int i = 0; i < height.length-1; i++){
            for (int j = i + 1; j < height.length; j++) {
                res = Math.max(res, (j - i) * Math.min(height[i], height[j]));
            }
        }
        return res;
    }


}
