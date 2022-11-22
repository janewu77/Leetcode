package LeetCode;

import static java.time.LocalTime.now;

/**
 *
 * Share
 * Given n non-negative integers representing an elevation map
 * where the width of each bar is 1, compute how much water it can trap after raining.
 *
 * Example 1:
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is
 * represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units
 * of rain water (blue section) are being trapped.
 *
 * Example 2:
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 *
 * Constraints:
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 */

//15:54
    // 16:05 Time Limit Exceeded
    // 17:37 success
public class N42HTrappingRainWater {

    public static void main(String[] args){
        System.out.println(now());
        int[] data1 = {0,1,0,2,1,0,1,2,2,1,2,1};
        int result1 = new N42HTrappingRainWater().trap(data1);
        System.out.println("expected:6 result:"+result1);

        System.out.println(now());
    }

    // 找最高的柱子，然后分别计算左、右trap的水。
    //find the highest bar, and compute from left and right respectively
    //Runtime: 1 ms, faster than 99.32% of Java online submissions for Trapping Rain Water.
    //Memory Usage: 42.8 MB, less than 95.53% of Java online submissions for Trapping Rain Water.
    //Time: O(N); Space: O(1)
    public int trap(int[] height) {
        int result = height[0];
        int idx = 0;

        //find peak
        for (int i = 1; i < height.length; i++){
            if (height[i] > result){
                result = height[i];
                idx = i;
            }
        }

        result = 0;
        //left
        int bar = 0;
        for (int i = 0; i < idx; i++) {
            if (bar > 0 && height[i] < bar) result += (bar - height[i]);
            bar = height[i] >= bar ? height[i]: bar;
        }

        //right
        bar = 0;
        for (int i = height.length - 1; i > idx; i--) {
            if (bar > 0 && height[i] < bar) result += (bar - height[i]);
            bar = height[i] >= bar ? height[i]: bar;
        }
        return result;
    }



    //Runtime: 285 ms, faster than 5.01% of Java online submissions for Trapping Rain Water.
    //Memory Usage: 49.8 MB, less than 8.58% of Java online submissions for Trapping Rain Water.
    public int trap3(int[] height) {
        int result = 0;

        int i = 0;
        int[] left = {0,0}; //index：height

        while (i < height.length && left[1] == 0 ){
            if (height[i] != 0) {
                left[0] = i;
                left[1] = height[i];
            }
            i++;
        }

        int[] right = {0,0};
        int bc;
        while (i < height.length) {
            right[0] = 0;
            right[1] = 0;
            bc = 0;
            while (i < height.length && left[1] > right[1]){
                if (height[i] >= right[1]) {
                    right[0] = i;
                    right[1] = height[i];
                }
                if( height[i] > 0 ) bc = bc + height[i];
                i++;
            }
            if (i < height.length || right[0] == height.length - 1){
                //计算当前水量
                if (right[0] - left[0] - 1 > 0)
                    result += Math.min(left[1], right[1]) * (right[0] - left[0] - 1) - bc + right[1];

                //重设界
                left[0] = right[0];
                left[1] = right[1];
            }else if(right[1] > 0){
                i = left[0] + 1;
                left[1] = right[1];//用矮的再找。
            }
        }
        return result;
    }

    //Time Limit Exceeded
    public int trap_2(int[] height) {
        if(height == null || height.length<=1) return 0;

        int result = 0;
        int begin = 0;
        for(int h: height){
            if(h == 0 ) begin++;
            else break;
        }

        int end = height.length - 1;
        for (int i = height.length -1; i >= 0; i--) {
            if(height[i] == 0 ) end--;
            else break;
        }

        while(begin < end) {
            int preH = 0;
            int tmp = 0;
            int ib = begin;
            int ie = end;

            begin = -1;
            end = 0;

            //{0,1,0,2,1,0,1,3,2,1,2,1};
            for (int i = ib; i <= ie; i++) {
                int h = height[i];
                if (h == 0) {
                    if (preH == 0) continue;
                    tmp++;
                } else {
                    if (preH != 0) result += tmp;

                    preH = h;
                    tmp = 0;
                    height[i] = h - 1;

                    if (height[i] > 0) {
                        if (begin < 0) begin = i;
                        end = i;
                    }
                }
            }
        }
        return result;
    }
}
