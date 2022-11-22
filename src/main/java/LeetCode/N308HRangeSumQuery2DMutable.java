package LeetCode;


/**
 * Given a 2D matrix matrix, handle multiple queries of the following types:
 *
 * Update the value of a cell in matrix.
 * Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 * Implement the NumMatrix class:
 *
 * NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
 * void update(int row, int col, int val) Updates the value of matrix[row][col] to be val.
 * int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 *
 *
 * Example 1:
 *
 *
 * Input
 * ["NumMatrix", "sumRegion", "update", "sumRegion"]
 * [[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]], [2, 1, 4, 3], [3, 2, 2], [2, 1, 4, 3]]
 * Output
 * [null, 8, null, 10]
 *
 * Explanation
 * NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);
 * numMatrix.sumRegion(2, 1, 4, 3); // return 8 (i.e. sum of the left red rectangle)
 * numMatrix.update(3, 2, 2);       // matrix changes from left image to right image
 * numMatrix.sumRegion(2, 1, 4, 3); // return 10 (i.e. sum of the right red rectangle)
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * -105 <= matrix[i][j] <= 105
 * 0 <= row < m
 * 0 <= col < n
 * -105 <= val <= 105
 * 0 <= row1 <= row2 < m
 * 0 <= col1 <= col2 < n
 * At most 104 calls will be made to sumRegion and update.
 */
public class N308HRangeSumQuery2DMutable {

    public static void main(String[] args){
        new N308HRangeSumQuery2DMutable().doRun();
    }
    private void doRun(){
        int[][] nums = new int[][]{{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};
        int res;
        NumMatrix numMatrix = new NumMatrix(nums);
        res = numMatrix.sumRegion(2,1,4,3);
        System.out.println(res);
        numMatrix.update(3,2,2);
        res = numMatrix.sumRegion(2,1,4,3);
        System.out.println(res);
    }

    //3.precompute
    //Runtime: 22 ms, faster than 87.86% of Java online submissions for Range Sum Query 2D - Mutable.
    //Memory Usage: 51.6 MB, less than 50.21% of Java online submissions for Range Sum Query 2D - Mutable.
    class NumMatrix {
        private int[][] data;
        private int[][] rowSum;

        //Time: O(C * L); Space: O(C * L)
        public NumMatrix(int[][] matrix) {
            data = matrix;
            rowSum = new int[matrix.length + 1][matrix[0].length]; //fill row0 zero

            for (int i = 0; i < matrix.length; i++){
                for (int j = 0; j < matrix[0].length; j++) {
                    rowSum[i + 1][j] = rowSum[i][j] + data[i][j];
                }
            }
        }

        //Time: O(C)
        public void update(int row, int col, int val) {
            val = val - data[row][col];
            data[row][col] += val;

            for (int i = row + 1; i < rowSum.length; i++)
                rowSum[i][col] += val;
        }

        //Time: O(L)
        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            row2++;
            for (int col = col1; col <= col2; col++)
                sum += rowSum[row2][col] - rowSum[row1][col];
            return sum;
        }
    }

    //2.SegmentTree
    //Runtime: 17 ms, faster than 94.44% of Java online submissions for Range Sum Query 2D - Mutable.
    //Memory Usage: 48.4 MB, less than 92.80% of Java online submissions for Range Sum Query 2D - Mutable.
    class NumMatrix_2 {
        private int[] segTree;
        private int maxCol;

        //Time: O(C * L * Log(C * L)); Space: O(C * L)
        public NumMatrix_2(int[][] matrix) {
            segTree = new int[matrix.length * matrix[0].length * 2];
            maxCol = matrix[0].length;
            for (int i = 0; i < matrix.length; i++){
                for (int j = 0; j < matrix[0].length; j++) {
                    update(i, j, matrix[i][j]);
                }
            }
        }

        //Time: O(Log(C * L))
        public void update(int row, int col, int val) {
            updateSegTree(segTree, row * maxCol + col, val);
        }

        //Time: O(C * Log(C * L))
        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            int col = col2 - col1;
            for (int row = row1; row <= row2; row++){
                int idx = row * maxCol + col1;
                sum += querySegTree(segTree, idx, idx + col + 1);
            }
            return sum;
        }

        //time: O(Log(C * L))
        private int querySegTree(int[] tree, int i, int j) {
            int left = i + (tree.length >> 1), right = j + (tree.length >> 1);
            int sum = 0;
            while (left < right){
                if ((left & 1) == 1) sum += tree[left++];
                if ((right & 1) == 1) sum += tree[--right];
                left >>= 1;
                right >>= 1;
            }
            return sum;
        }

        //time: O(Log(C * L))
        private void updateSegTree(int[] tree, int i, int val){
            i += (tree.length >> 1);
            tree[i] = val;

            while (i > 1){
                i = i>>1;
                tree[i] = tree[i << 1] + tree[(i << 1) + 1];
            }
        }

    }


    //1.Binary Index Tree
    //Runtime: 23 ms, faster than 81.89% of Java online submissions for Range Sum Query 2D - Mutable.
    //Memory Usage: 51.3 MB, less than 64.81% of Java online submissions for Range Sum Query 2D - Mutable.
    class NumMatrix_1 {
        int[] bitTree;
        int maxCol;

        //Time: O(C * L * Log(C * L)); Space: O(C * L)
        public NumMatrix_1(int[][] matrix) {
            bitTree = new int[matrix.length * matrix[0].length + 1];
            maxCol = matrix[0].length;
            for (int i = 0; i < matrix.length; i++){
                int idx = 1 + i * maxCol ;
                for (int j = 0; j < matrix[0].length; j++) {
                    updateBIT(bitTree,  idx + j, matrix[i][j]);
                }
            }
        }

        //Time: O(Log(C * L))
        public void update(int row, int col, int val) {
            int idx = row * maxCol + col + 1;
            int originalVal = queryBIT(bitTree, idx) - queryBIT(bitTree, idx - 1);
            updateBIT(bitTree, idx, val - originalVal);
        }

        //Time: O(C * Log(C * L))
        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            int col = col2 - col1;
            for (int row = row1; row <= row2; row++){
                int idx1 = row * maxCol + col1;// + 1 - 1;
                sum += queryBIT(bitTree, idx1 + col + 1) - queryBIT(bitTree, idx1);
            }
            return sum;
        }

        private int lsb(int i){
            return i&(-i);
        }

        //time: O(Log(C * L))
        private int queryBIT(int[] tree, int i) {
            int sum = 0;
            for(; i >= 1; i -= lsb(i)) sum += tree[i];
            return sum;
        }

        //time: O(Log(C * L))
        private void updateBIT(int[] tree, int i, int val){
            for (; i<tree.length; i += lsb(i)) tree[i] += val;
        }
    }


}
