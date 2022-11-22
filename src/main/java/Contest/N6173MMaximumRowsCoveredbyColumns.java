package Contest;


/**
 * You are given a 0-indexed m x n binary matrix mat and an integer cols,
 * which denotes the number of columns you must choose.
 *
 * A row is covered by a set of columns if each cell in the row that has a value of 1
 * also lies in one of the columns of the chosen set.
 *
 * Return the maximum number of rows that can be covered by a set of cols columns.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: mat = [[0,0,0],[1,0,1],[0,1,1],[0,0,1]], cols = 2
 * Output: 3
 * Explanation:
 * As shown in the diagram above, one possible way of covering 3 rows is by selecting the 0th and 2nd columns.
 * It can be shown that no more than 3 rows can be covered, so we return 3.
 * Example 2:
 *
 *
 *
 * Input: mat = [[1],[0]], cols = 1
 * Output: 2
 * Explanation:
 * Selecting the only column will result in both rows being covered, since the entire matrix is selected.
 * Therefore, we return 2.
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 12
 * mat[i][j] is either 0 or 1.
 * 1 <= cols <= n
 */

import java.util.HashSet;
import java.util.Set;

import static java.time.LocalTime.now;

/**
 * H - [time: 120-
 */
//2397. Maximum Rows Covered by Columns
// 77. Combination
public class N6173MMaximumRowsCoveredbyColumns {


    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        data2 = new int[][]{{0,0,0}, {1,0,1}, {0,1,1} , {0,0,1}};
        doRun(3, data2,2);

        data2 = new int[][]{{1}, {0}};
        doRun(2, data2,1);


        data2 = new int[][]{{1,0}, {0,1}, {1,1} };
        doRun(1, data2,1);

        data2 = new int[][]{{1,0}, {0,1},};
        doRun(2, data2,2);


        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[][] mat, int cols) {
        int res = new N6173MMaximumRowsCoveredbyColumns()
                .maximumRows(mat, cols);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Maximum Rows Covered by Columns.
    //Memory Usage: 41.7 MB, less than 75.00% of Java online submissions for Maximum Rows Covered by Columns.
    //backtracking
    //Time: O(M * N + (k+m) * C(k,N)) ; Space:  O(M + k + C(k,N) )
    //C(k,N) = N!/((N-K)!*k!)
    //k : cols; N: the number of columns; M: the number of rows
    public int maximumRows(int[][] mat, int cols) {
        if (cols == mat[0].length) return mat.length;
        //Time: O(M * N); Space: O(M)
        //Convert each row into a decimal number and store in a array.
        int[] list = new int[mat.length];
        for (int i = 0; i < mat.length; i++)
            for (int j = 0; j < mat[0].length; j++)
                list[i] = list[i] * 2 + mat[i][j];

        return helper_backtrack(list, mat[0].length, cols, 0, new int[cols]);
    }

    //time: O((k+M) * C(k,N)); Space: O(k + C(k,N))
    private int helper_backtrack(int[] list, int n, int k, int begin, int[] selected) {
        if (k == 0){
            int count = 0;
            int bitMask = 0;
            for (int x : selected) bitMask |= 1<<x;
            for (int number : list)
                if ((number | bitMask) == bitMask) count++;
            return count;
        }

        int res = 0;
        for (int i = begin; i <= n - k + 1; i++) {
            selected[k - 1] = i;
            res = Math.max(res, helper_backtrack(list, n, k - 1, i + 1, selected));
            //set.remove(i);
        }
        return res;
    }


    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Maximum Rows Covered by Columns.
    //Memory Usage: 40.1 MB, less than 81.25% of Java online submissions for Maximum Rows Covered by Columns.
    //bitMask
    //Time: O(M * N + M * C(k,N)); Space: O(M);
    //N: the number of columns; M: the number of rows
    public int maximumRows_bit(int[][] mat, int cols) {

        int bitKMin = (1 << cols) - 1;  // '1's in the low position
        int bitKMax = bitKMin << (mat[0].length - cols);// '1's in the high position

        //Time: O(M * N); Space: O(M)
        //Convert each row into a decimal number and store in a array.
        int[] list = new int[mat.length];
        for (int i = 0; i < mat.length; i++)
            for (int j = 0; j < mat[0].length; j++)
                list[i] = list[i] * 2 + mat[i][j];

        int res = 0;
        //Time: (M * C(k,N))
        while (bitKMin <= bitKMax){
            int count = 0;
            //Time: O(M)
            for (int number : list)
                if ((number | bitKMin) == bitKMin) count++;

            res = Math.max(res, count);
            bitKMin = nextMask(bitKMin);
//            bitKMin++;
//            while (Integer.bitCount(bitKMin) != cols && bitKMin <= bitKMax) bitKMin++;
        }
        return res;
    }

    /**
     * c = n & -n - first bit (from the right) equal to 1 in n: 0000001000
     * r = n + c - the next mask after n if we able to change only bits to the left of the first bit found on previous step: 0011000000
     * r ^ n - represents how many bits are changed from n to r: 0001111000 - here we changed 1->0 (4th pos from the right), 1->0 (5th pos), 1->0 (6th pos) and 0-> 1 (7th pos)
     *
     * Now we should notice that r can contain number of 1-bits equal to such number in n, example: n = 0011101000 -> r = 0011110000,
     * or it can contain number of 1-bits less than number 1-bits in n, for example our main case: n = 0010111000 -> r = 0011000000 -
     * r has less 1-bits than n by 2.
     *
     * We want to determine the difference of 1-bits between r and n, hence we:
     *
     * shift r ^ n by 2 bits to the right (2 because if r and n contain the same number of 1-bits - r ^ n will contain 2 nearby bits anyway):
     * (r ^ n) >> 2: 0000011110
     *
     * divide it to c = 0000001000 to get a mask which has 1-bits from the right and the number of these 1-bits is equal to the difference of 1-bits between r and n:
     * ((r ^ n) >> 2) / c: 0000011110 (= 30) / 0000001000 (= 8) = 0000000011 (= 3) - mask representing the difference of 1-bits between r and n, we got it!
     *
     * Now it remains to add this mask to r: (((r ^ n) >> 2) / c) | r
     *
     * @param n
     * @return
     */
    private int nextMask(int n) {
        int c = (n & -n), r = n + c;
        return (((r ^ n) >> 2) / c) | r;
    }

//    //Time: O(N)
    //Integer.bitCount(mask)
//    private boolean countKOnes(int n, int k){
//        int c = 0;
//        while (n > 0) {
//            c += (n & 1);
//            if (c > k) return false;
//            n >>= 1;
//        }
//        return c == k;
//    }

}
