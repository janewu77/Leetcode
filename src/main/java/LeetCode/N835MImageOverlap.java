package LeetCode;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * You are given two images, img1 and img2, represented as binary, square matrices of size n x n. A binary matrix has only 0s and 1s as values.
 *
 * We translate one image however we choose by sliding all the 1 bits left, right, up, and/or down any number of units. We then place it on top of the other image. We can then calculate the overlap by counting the number of positions that have a 1 in both images.
 *
 * Note also that a translation does not include any kind of rotation. Any 1 bits that are translated outside of the matrix borders are erased.
 *
 * Return the largest possible overlap.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: img1 = [[1,1,0],[0,1,0],[0,1,0]], img2 = [[0,0,0],[0,1,1],[0,0,1]]
 * Output: 3
 * Explanation: We translate img1 to right by 1 unit and down by 1 unit.
 *
 * The number of positions that have a 1 in both images is 3 (shown in red).
 *
 * Example 2:
 *
 * Input: img1 = [[1]], img2 = [[1]]
 * Output: 1
 * Example 3:
 *
 * Input: img1 = [[0]], img2 = [[0]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * n == img1.length == img1[i].length
 * n == img2.length == img2[i].length
 * 1 <= n <= 30
 * img1[i][j] is either 0 or 1.
 * img2[i][j] is either 0 or 1.
 */

/**
 * M - [time: 120+
 */
public class N835MImageOverlap {

    //4.Imagine Convolution
    //https://leetcode.com/problems/image-overlap/discuss/131344/An-interesting-SOLUTION%3A-Let-us-think-about-it-as-%22Convolution%22
    //Runtime: 53 ms, faster than 55.15% of Java online submissions for Image Overlap.
    //Memory Usage: 43.2 MB, less than 53.94% of Java online submissions for Image Overlap.
    public int largestOverlap(int[][] img1, int[][] img2) {
        int N = img1.length;

        int[][] B_padded = new int[3 * N - 2][3 * N - 2];
        for (int i = 0; i < N; ++i)
            for (int j = 0; j < N; ++j)
                B_padded[i + N - 1][j + N - 1] = img2[i][j];

        int maxOverlaps = 0;
        for (int xShift = 0; xShift < 2 * N - 1; ++xShift)
            for (int yShift = 0; yShift < 2 * N - 1; ++yShift)
                maxOverlaps = Math.max(maxOverlaps, convolute(img1, B_padded, xShift, yShift));
        return maxOverlaps;
    }
    private int convolute(int[][] A, int[][] kernel, int xShift, int yShift) {
        int result = 0;
        for (int i = 0; i < A.length; ++i)
            for (int j = 0; j < A.length; ++j)
                result += A[i][j] * kernel[i + xShift][j + yShift];
        return result;
    }


    //3.Linear Transformation - 2
    //Time: O(N^4); Space: O(N * N)
    //Runtime: 77 ms, faster than 50.91% of Java online submissions for Image Overlap.
    //Memory Usage: 42.4 MB, less than 86.67% of Java online submissions for Image Overlap.
    public int largestOverlap_3(int[][] img1, int[][] img2) {

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < img1.length; i++)
            for (int j = 0; j < img1.length; j++) {
                if (img1[i][j] == 1) list1.add(i * 100 + j);
                if (img2[i][j] == 1) list2.add(i * 100 + j);
            }

        Map<Integer, Integer> map = new HashMap<>();
        for (int p1 : list1) {
            for (int p2 : list2) {
                map.put(p1 - p2, map.getOrDefault(p1 - p2, 0) + 1);
            }
        }

        int res = 0;
        for (int value: map.values()) res = Math.max(res, value);
        return res;
    }

    //2.Linear Transformation
    //Runtime: 133 ms, faster than 36.36% of Java online submissions for Image Overlap.
    //Memory Usage: 42.9 MB, less than 72.73% of Java online submissions for Image Overlap.
    //Time: O(N * N + N * N * N * N + N); Space: O(2 * N * N)
    //Time: O(N^4); Space: O(N * N)
    public int largestOverlap_2(int[][] img1, int[][] img2) {
        //Space: O(2 * N * N)
        List<int[]> list1 = new ArrayList<>();
        List<int[]> list2= new ArrayList<>();
        //Time: O(N * N)
        for (int i = 0; i < img1.length; i++)
            for (int j = 0; j < img1[0].length; j++) {
                if (img1[i][j] == 1) list1.add(new int[]{i, j});
                if (img2[i][j] == 1) list2.add(new int[]{i, j});
            }

        Map<Pair<Integer, Integer>, Integer> map = new HashMap<>();
        //Time: worstcase : O(N * N * N * N)
        for (int[] pos1 : list1) {
            for (int[] pos2 : list2) {
                Pair<Integer, Integer> pair = new Pair<>(pos1[0] - pos2[0], pos1[1] - pos2[1]);
                map.put(pair, map.getOrDefault(pair, 0) + 1);
            }
        }

        int res = 0;
        for (int value: map.values()) res = Math.max(res, value);
        return res;
    }

    //1.shit & count
    //Runtime: 38 ms, faster than 70.30% of Java online submissions for Image Overlap.
    //Memory Usage: 43.8 MB, less than 37.58% of Java online submissions for Image Overlap.
    //Time: O(N^4)
    //let N be the width of the input matrix
    public int largestOverlap_1(int[][] img1, int[][] img2) {
        int maxOverlaps = 0;

        //Time: O(N * N * (N*N)); Space: O(1)
        for (int xShift = 0; xShift < img1.length; ++xShift)
            for (int yShift = 0; yShift < img1.length; ++yShift) {
                maxOverlaps = Math.max(maxOverlaps, helper_shiftAndCount(xShift, yShift, img1, img2));
                maxOverlaps = Math.max(maxOverlaps, helper_shiftAndCount(xShift, yShift, img2, img1));
            }
        return maxOverlaps;
    }

    //Time: O(N * N); Space: O(1)
    protected int helper_shiftAndCount(int xShift, int yShift, int[][] M, int[][] R) {
        int leftShiftCount = 0, rightShiftCount = 0;
        for (int mCol = xShift, rCol = 0; mCol < M.length; mCol++, rCol++) {
            for (int mRow = yShift, rRow = 0; mRow < M.length; mRow++, rRow++) {
                //left & right
                if (M[mCol][mRow] == 1 && M[mCol][mRow] == R[rCol][rRow])
                    leftShiftCount++;
                if (M[rCol][mRow] == 1 && M[rCol][mRow] == R[mCol][rRow])
                    rightShiftCount++;
            }
        }
        return Math.max(leftShiftCount, rightShiftCount);
    }

}
