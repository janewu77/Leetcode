package LeetCode;

import java.util.Arrays;


/**
 *
 * You are given a rectangular cake of size h x w and two arrays of integers horizontalCuts and verticalCuts where:
 *
 * horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, and
 * verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.
 * Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the arrays horizontalCuts and verticalCuts. Since the answer can be a large number, return this modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
 * Output: 4
 * Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green piece of cake has the maximum area.
 * Example 2:
 *
 *
 * Input: h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
 * Output: 6
 * Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green and yellow pieces of cake have the maximum area.
 * Example 3:
 *
 * Input: h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3]
 * Output: 9
 *
 *
 * Constraints:
 *
 * 2 <= h, w <= 109
 * 1 <= horizontalCuts.length <= min(h - 1, 105)
 * 1 <= verticalCuts.length <= min(w - 1, 105)
 * 1 <= horizontalCuts[i] < h
 * 1 <= verticalCuts[i] < w
 * All the elements in horizontalCuts are distinct.
 * All the elements in verticalCuts are distinct.
 *
 */

/**
 * M : [耗时 20 + 60]
 *  - 第一次花了20分钟做完。 （但有edge错误 且没有优化）
 *  - 适当利用已有方法 比如 arrays.sort math.max等
 */
public class N1465MMaximumAreaofaPieceofCake {

    public static void main(String[] args) {

        int result = 0;
        result = new N1465MMaximumAreaofaPieceofCake().maxArea(5,2,new int[]{3,1,2},new int[]{1});
        System.out.println("expected:2 + result:"+result);

        result = new N1465MMaximumAreaofaPieceofCake().maxArea(5,2,new int[]{3,1,2},new int[]{1});
        System.out.println("expected:2 + result:"+result);
    }


    public int maxAreax(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        //horizontalCuts
        Arrays.sort(horizontalCuts);
        long maxHSpan = Math.max(horizontalCuts[0], h - horizontalCuts[horizontalCuts.length-1]);
        int lastCut = 0;
        for(int cCut : horizontalCuts){
            maxHSpan = Math.max(maxHSpan, cCut - lastCut);
            lastCut = cCut;
        }

        //verticalCuts
        Arrays.sort(verticalCuts);
        long maxVSpan = Math.max(verticalCuts[0], w - verticalCuts[verticalCuts.length-1]);
        lastCut = 0;
        for(int cCut : verticalCuts){
            maxVSpan = Math.max(maxVSpan, cCut - lastCut);
            lastCut = cCut;
        }
        return (int) ((maxHSpan * maxVSpan) % (1000000007));
    }


    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {

        long hSpan = getMaxSpan(h, horizontalCuts);
        long vSpan = getMaxSpan(w, verticalCuts);

        return (int) ((hSpan * vSpan) % (1000000007));
    }

    /**
     * Runtime: 15 ms, faster than 99.88% of Java online submissions for Maximum Area of a Piece of Cake After Horizontal and Vertical Cuts.
     * Memory Usage: 52 MB, less than 86.61% of Java online submissions for Maximum Area of a Piece of Cake After Horizontal and Vertical Cuts.
     * @param n
     * @param numCuts
     * @return
     */
    private long getMaxSpan(int n, int numCuts[]){
        Arrays.sort(numCuts);
        long maxSpan = Math.max(numCuts[0], n - numCuts[numCuts.length-1]);;
        int lastCut = 0;
        for(int cCut : numCuts){
            maxSpan = Math.max(maxSpan, cCut - lastCut);
            lastCut = cCut;
        }
        return maxSpan;
    }

    /**
     * Runtime: 22 ms, faster than 37.60% of Java online submissions for Maximum Area of a Piece of Cake After Horizontal and Vertical Cuts.
     * Memory Usage: 60.5 MB, less than 64.61% of Java online submissions for Maximum Area of a Piece of Cake After Horizontal and Vertical Cuts.
     * @param n
     * @param numCuts
     * @return
     */
    private long getMaxSpan1(int n, int numCuts[]){
        long maxSpan = 0;
        Arrays.sort(numCuts);
        int lastCut = 0;
        int i = 0;
        while(i < numCuts.length && lastCut + maxSpan < n ){

            while(i < numCuts.length && lastCut + maxSpan >= numCuts[i]) {
                lastCut = numCuts[i];
                i++;
            }
            if (i < numCuts.length ) {
                maxSpan = numCuts[i] - lastCut;
                lastCut = numCuts[i];
                i++;
            }
        }

        if( lastCut + maxSpan <= n) maxSpan = n - lastCut;
        return maxSpan;
    }
}

