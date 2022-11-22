package LeetCode;


/**
 *
 * Given an array of integers heights representing the histogram's bar height
 * where the width of each bar is 1, return the area of the largest rectangle in the histogram.
 *
 *
 *
 * Example 1:
 * Input: heights = [2,1,5,6,2,3]
 * Output: 10
 * Explanation: The above is a histogram where width of each bar is 1.
 * The largest rectangle is shown in the red area, which has an area = 10 units.
 *
 *
 * Example 2:
 * Input: heights = [2,4]
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= heights.length <= 105
 * 0 <= heights[i] <= 104
 */

import java.util.*;

import static java.time.LocalTime.now;

/**
 * H - [time: 20-
 *
 */
public class N84HLargestRectangleinHistogram {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;


        data = new int[]{1,3,2,1,2,1};
        doRun_demo(6, data);

        data = new int[]{3,6,5,7,4,8,1,0};
        doRun_demo(20, data);

        data = new int[]{4,2,0,3,2,4,3,4};
        doRun_demo(10, data);

        data = new int[]{2,1,2};
        doRun_demo(3, data);

        data = new int[]{2,1,5,6,2,3};
        doRun_demo(10, data);

        data = new int[]{2,1,4,5,1,3,3};
        doRun_demo(8, data);


        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun_demo(int expect,  int[] heights) {
        int res = new N84HLargestRectangleinHistogram().largestRectangleArea(heights);
////        String res = comm.toString(res1);
//        String res = Arrays.stream(res1).mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(","));
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        //int[][] res1
        //        sb.append("[");
        //        for(int i = 0; i<res1.length; i++) {
        //            String str = Arrays.toString(res1[i]);
        //            sb.append(str).append(",");
        //        }
        //        if (sb.length() > 1) sb.deleteCharAt(sb.length()-1);
        //        sb.append("]");
    }


    //Runtime: 10 ms, faster than 98.63% of Java online submissions for Largest Rectangle in Histogram.
    //Memory Usage: 52.6 MB, less than 97.76% of Java online submissions for Largest Rectangle in Histogram.
    //Time: O(3N); Space: O(2N)
    public int largestRectangleArea(int[] heights) {
        int[] left = new int[heights.length];
        left[0] = -1;

        int[] right = new int[heights.length];
        right[heights.length - 1] = heights.length;

        //forward
        for (int i = 1; i < heights.length; i++){
            int idx = i - 1;
            while (idx >= 0 && heights[idx] >= heights[i]) idx = left[idx];
            left[i] = idx;
        }

        //backward
        for (int i = heights.length - 2; i >= 0; i--){
            int idx = i + 1;
            while (idx < heights.length && heights[idx] >= heights[i]) idx = right[idx];
            right[i] = idx;
        }

        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            res = Math.max(res, heights[i] * (right[i] - left[i] - 1));
        }
        return res;
    }

    //Runtime: 22 ms, faster than 91.13% of Java online submissions for Largest Rectangle in Histogram.
    //Memory Usage: 58.7 MB, less than 89.12% of Java online submissions for Largest Rectangle in Histogram.
    //stack
    //Time: O(N); Space: O(N)
    public int largestRectangleArea_stack(int[] heights) {
        int res = 0;
//        Stack<Integer> stack = new Stack<>();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);

        for (int i = 0; i < heights.length; i++){
            if (i > 0 && heights[i] == heights[i - 1]) continue;

            //从低往高 进 stack. 当出现 高->低时，计算一次面积。
            //计算面积时，从高往低算。 用i - stack顶的数字。
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i])
                res = Math.max(res, heights[stack.pop()] * (i - stack.peek() - 1)); //注意 * 后面的i
            stack.push(i);
        }

        //补算最后一个
        while (stack.peek() != -1)
            res = Math.max(res, heights[stack.pop()] * (heights.length - stack.peek()-1));

        return res;
    }


    //from Discuss
    //https://leetcode.com/problems/largest-rectangle-in-histogram/discuss/28941/segment-tree-solution-just-another-idea-onlogn-solution
    //Runtime: 102 ms, faster than 73.15% of Java online submissions for Largest Rectangle in Histogram.
    //Memory Usage: 91 MB, less than 48.69% of Java online submissions for Largest Rectangle in Histogram.
    //segment tree
    public int largestRectangleArea_2(int[] heights) {
        int n = heights.length;
        int[] segment = buildSegmentTree(heights);
        return divideConquer(heights, 0, n - 1, segment);
    }

    private static int divideConquer(int[] height, int l, int h, int[] segment) {
        if(l <= h) {
            if(l == h) return height[l] * 1;
            int minIndex = query(segment, height, l, h);
            int currArea = height[minIndex] * (h - l + 1);
            int leftArea = divideConquer(height, l, minIndex - 1, segment);
            int rightArea = divideConquer(height, minIndex + 1, h, segment);
            return Math.max(Math.max(currArea, leftArea), rightArea);
        }
        return 0;
    }

    private static int[] buildSegmentTree(int[] heights) {
        int n = heights.length;
        int[] segment = new int[2 * n];
        for(int i = n - 1, j = 2 * n - 1; i >= 0; i--, j--) {
            segment[j] = i;
        }
        for(int i = n - 1; i > 0; i--) {
            if(heights[segment[2 * i]] < heights[segment[2 * i + 1]]) segment[i] = segment[2 * i];
            else segment[i] = segment[2 * i + 1];
        }
        return segment;
    }

    private static int query(int[] segment, int[] heights, int i, int j) {
        int n = heights.length;
        int p = i + n;
        int q = j + n;
        int min = Integer.MAX_VALUE;
        int index = -1;
        while(p <= q) {
            if(p % 2 == 1) {
                if(heights[segment[p]] < min) {
                    min = heights[segment[p]];
                    index = segment[p];
                }
                p++;
            }
            if(q % 2 == 0) {
                if(heights[segment[q]] < min) {
                    min = heights[segment[q]];
                    index = segment[q];
                }
                q--;
            }
            p = p >> 1;
            q = q >> 1;
        }
        return index;
    }



    //Runtime: 280 ms, faster than 32.31% of Java online submissions for Largest Rectangle in Histogram.
    //Memory Usage: 74.6 MB, less than 84.53% of Java online submissions for Largest Rectangle in Histogram.
    //Brute Force
    //Time：O(N*N); Space: O(1)
    public int largestRectangleArea_bruteforce(int[] heights) {
        int res = 0;
        for (int i = 0; i < heights.length; i++){
            if (i > 0 && heights[i] == heights[i - 1]) continue;

            int left = i, right = i;
            while (left >= 0 && heights[left] >= heights[i])
                left--;

            while (right < heights.length && heights[right] >= heights[i])
                right++;
            res = Math.max(res, (right - left - 1) * heights[i]);
        }
        return res;
    }
}
