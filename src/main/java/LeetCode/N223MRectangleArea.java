package LeetCode;


/**
 * Given the coordinates of two rectilinear rectangles in a 2D plane, return the total area covered by the two rectangles.
 *
 * The first rectangle is defined by its bottom-left corner (ax1, ay1) and its top-right corner (ax2, ay2).
 *
 * The second rectangle is defined by its bottom-left corner (bx1, by1) and its top-right corner (bx2, by2).
 *
 *
 *
 * Example 1:
 *
 * Rectangle Area
 * Input: ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2
 * Output: 45
 * Example 2:
 *
 * Input: ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, bx1 = -2, by1 = -2, bx2 = 2, by2 = 2
 * Output: 16
 *
 *
 * Constraints:
 *
 * -104 <= ax1 <= ax2 <= 104
 * -104 <= ay1 <= ay2 <= 104
 * -104 <= bx1 <= bx2 <= 104
 * -104 <= by1 <= by2 <= 104
 */


public class N223MRectangleArea {


    //Runtime: 4 ms, faster than 81.38% of Java online submissions for Rectangle Area.
    //Memory Usage: 42.9 MB, less than 58.79% of Java online submissions for Rectangle Area.
    //Time: O(1); Space: O(1)
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {

        int x1 = Math.max(ax1, bx1);
        int x2 = Math.min(ax2, bx2);
        int y1 = Math.max(ay1, by1);
        int y2 = Math.min(ay2, by2);

        int res = (ax2 - ax1) * (ay2 - ay1) + (bx2 - bx1) * (by2 - by1);
        if (x2 > x1 && y2 > y1)  res -= (x2 - x1) * (y2 - y1);

        return res;
    }
}
