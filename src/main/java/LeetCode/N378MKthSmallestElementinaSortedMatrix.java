package LeetCode;


import java.util.*;

/**
 * Given an n x n matrix where each of the rows and columns is sorted in ascending order,
 * return the kth smallest element in the matrix.
 *
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 *
 * You must find a solution with a memory complexity better than O(n2).
 *
 *
 *
 * Example 1:
 *
 * Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
 * Output: 13
 * Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
 *
 *
 * Example 2:
 * Input: matrix = [[-5]], k = 1
 * Output: -5
 *
 *
 * Constraints:
 *
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 300
 * -109 <= matrix[i][j] <= 109
 * All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
 * 1 <= k <= n2
 *
 *
 * Follow up:
 *
 * Could you solve the problem with a constant memory (i.e., O(1) memory complexity)?
 * Could you solve the problem in O(n) time complexity? The solution may be too advanced for an interview but you may find reading this paper fun.
 */

/**
 * M - [time: 60-]
 *
 * - Binary Search in Matrix
 * - Heap : PriorityQueue
 *
 */
public class N378MKthSmallestElementinaSortedMatrix {

    public static void main(String[] args){
        int[][] matrix = new int[][]{{-5}};
        doRun(-5, matrix, 1);

        matrix = new int[][] {{1,5,9}, {10,11,13}, {12, 13, 15}};
        doRun(15, matrix,9);
        doRun(12, matrix,6);

        matrix = new int[][] {{1,5,9}, {10,11,13}, {12, 13, 15}};
        doRun(13, matrix,8);

        matrix = new int[][] {{1,3,5}, {6,7,12}, {11,14,14}};
        doRun(3, matrix,2);

        matrix = new int[][] {{1,2,3,4}, {1,2,3,4}, {1,2,3,4}, {1,2,3,4}};
        doRun(3, matrix,9);

        matrix = new int[][] {{1,1,3,8,13}, {4,4,4,8,18}, {9,14,18,19,20}, {14,19,23,25,25}, {18,21,26,28,29}};
        doRun(18, matrix,13);

        matrix = new int[][]{{4,9,9,11,12,15,17,22,23},{8,10,11,14,14,17,20,23,26},{9,13,16,21,23,26,30,35,36},{9,14,18,21,26,29,32,35,39},{12,18,19,23,27,33,34,37,39},{15,20,24,26,32,34,36,39,42},{16,24,28,28,33,37,37,43,44},{18,28,28,29,35,42,44,49,52},{23,32,34,34,39,46,51,51,56}};
        doRun(22, matrix,30);

        matrix = new int[][] {{-5,-4}, {-5,-4}};
        doRun(-5, matrix,2);

    }
    private static void doRun(int expected, int[][] matrix, int k){
        int res = new N378MKthSmallestElementinaSortedMatrix().kthSmallest(matrix, k);
        System.out.println( "["+(expected == res) + "]expected:"+expected + ".res:"+ res);
    }

    ////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    //from jaskarans880
    //very good. Easy to understand
    //目前见到最优秀的！
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Kth Smallest Element in a Sorted Matrix.
    //Memory Usage: 48.4 MB, less than 86.64% of Java online submissions for Kth Smallest Element in a Sorted Matrix.
    //Time: O(K*log(Max−Min)); Space: O(1)
    public int kthSmallest(int[][] matrix, int k) {
        int n= matrix.length;
        int low = matrix[0][0];
        int high = matrix[n-1][n-1];

        while (low < high){
            int mid = low + (high - low) / 2;
            int count = lessEqual(matrix, mid); //找小于等于的
            if (count < k) low = mid + 1;
            else high = mid; //注意这里不减1
        }
        return low;
    }

    //from left bottom or right top we can count how many numbers are equal or less than our target
    public int lessEqual(int[][] matrix, int target){
        int count = 0;
        int i = matrix.length-1, j = 0;

        while (i >= 0 && j < matrix.length){
            if (matrix[i][j] > target)  i--;
            else {
                count = count + i + 1;
                j++;
            }
        }
        return count;
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    //Runtime: 2 ms, faster than 75.30% of Java online submissions for Kth Smallest Element in a Sorted Matrix.
    //Memory Usage: 47.9 MB, less than 90.22% of Java online submissions for Kth Smallest Element in a Sorted Matrix.
    //Binary search in Matrix + iteration
    //查到的那段逻辑写得有点复杂。有更简洁的。
    //Time: O(K*log(Max−Min)); Space: O(1)
    public int kthSmallest_x(int[][] matrix, int k) {
        int low = matrix[0][0];
        int high = matrix[matrix.length - 1][matrix[0].length - 1];
        if (low == high) return low;

        if (k == 1) return low;
        if (k == matrix.length * matrix[0].length) return high;

        int m, res, c1, c2;
        //Time: O(log(high−low));
        while(low < high){
            m = (high + low) / 2;
            c1 = c2 = 0;
            res = Integer.MIN_VALUE;

            //Time: O(K)
            // 双for 可以改成 two pointers, 将更简洁
            for (int j = 0; j< matrix.length; j++) {
                for(int i = matrix.length - 1; i >= 0; i--) {
                    if (matrix[i][j] == m) {
                        res = Math.max(matrix[i][j], res);
                        c2++;
                    }else if (matrix[i][j] < m) {
                        res = Math.max(matrix[i][j], res);
                        c1 += (i + 1);
                        c2 += (i + 1);
                        break;
                    }
                }
                if (c1 > k) break;
            }

            if (k > c1 && k < c2) return m; //for equal values.
            if (c2 == k) return res;
            if (c2 > k)  high = m - 1;
            else low = m + 1;
        }
        return low;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Runtime: 2 ms, faster than 75.30% of Java online submissions for Kth Smallest Element in a Sorted Matrix.
    //Memory Usage: 57.3 MB, less than 33.58% of Java online submissions for Kth Smallest Element in a Sorted Matrix.
    //Binary search in Matrix + recursion
    public int kthSmallest_BinarySearch_recursion(int[][] matrix, int k) {
        if (k == 1) return matrix[0][0];
        if (k == matrix.length * matrix[0].length) return matrix[matrix.length - 1][matrix[0].length - 1];

        return kthSmallest_helper(matrix, k, matrix[0][0], matrix[matrix.length - 1][matrix[0].length - 1]);
    }

    private int kthSmallest_helper(int[][] matrix, int k, int low, int high) {
        if (low == high) return low;

        int m = (high + low) / 2;
        int res = Integer.MIN_VALUE;
        int c1 = 0;
        int c2 = 0; //for equals
        for (int j = 0; j< matrix.length; j++) {
            for(int i = matrix.length - 1; i >= 0; i--) {
                if (matrix[i][j] == m) {
                    res = Math.max(matrix[i][j], res);
                    c2++;
                }else if (matrix[i][j] < m) {
                    res = Math.max(matrix[i][j], res);
                    c1 += (i + 1);
                    c2 += (i + 1);
                    break;
                }
            }
            if (c1 > k) break;
        }

        if (k > c1 && k < c2) return m;
        if (c2 == k) return res;
        if (c2 > k) {
            return kthSmallest_helper(matrix, k, low, m - 1 );
        } else {
            return kthSmallest_helper(matrix, k, m + 1 , high);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Runtime: 22 ms, faster than 53.73% of Java online submissions for Kth Smallest Element in a Sorted Matrix.
    //Memory Usage: 57.5 MB, less than 21.59% of Java online submissions for Kth Smallest Element in a Sorted Matrix.
    //最小堆 PriorityQueue
    //Time: O(N+K*logN); Space: O(min(N,K))
    public int kthSmallest_heap(int[][] matrix, int k) {
        if (k == 1) return matrix[0][0];
        if (k == matrix.length* matrix[0].length) return matrix[matrix.length-1][matrix[0].length-1];

        int maxN = Math.min(matrix.length, k);

        //min heap : init heap via first column
        //Space: O(min(N,K))
        PriorityQueue<MatrixNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for(int i = 0; i < maxN; i++){
            MatrixNode node = new MatrixNode(matrix[i][0], i,0);
            minHeap.add(node);
        }

        //count to K
        MatrixNode node = minHeap.peek();
        while (!minHeap.isEmpty()){
            node = minHeap.poll();
            k--;
            if (k==0) break;

            if ((++node.j) < maxN) {
                node.val = matrix[node.i][node.j];
                minHeap.add(node);
            }
        }
        return node.val;
    }

    private static class MatrixNode {
        int i, j, val;
        public MatrixNode(int v, int x, int y){
            i = x; j = y; val = v;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Runtime: 67 ms, faster than 5.41% of Java online submissions for Kth Smallest Element in a Sorted Matrix.
    //Memory Usage: 59.5 MB, less than 5.87% of Java online submissions for Kth Smallest Element in a Sorted Matrix.
    //tree set + hashmap
    //time: O(N*N) ; Space: O(N*N).
    public int kthSmallest_treeset(int[][] matrix, int k) {
        if (k == 1) return matrix[0][0];
        if (k == matrix.length* matrix[0].length) return matrix[matrix.length-1][matrix[0].length-1];

        Map<Integer, Integer> data = new HashMap<>();
        TreeSet<Integer> treeset = new TreeSet<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int val = matrix[i][j];
                int c = data.getOrDefault(val, 0) + 1;
                data.put(val, c);
                treeset.add(val);
            }
        }
        int x = 0;
        while(!treeset.isEmpty()){
            int v = treeset.first();
            treeset.remove(v);
            x = x + data.get(v);
            if (x >= k) return v;
        }
        return Integer.MIN_VALUE;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    //Memory Limit Exceeded
    public int kthSmallest_bucket(int[][] matrix, int k) {
        int offset = 100000000;
        int size = 100000000 * 2 + 1;
        int[] data = new int[size];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int val = matrix[i][j] + offset;
                data[val]++;
            }
        }

        int i = 0;
        for (int j = 0; j < data.length; j++) {
            if (data[j] > 0) i += data[j];
            if (i == k) return j - offset;
        }
        return Integer.MIN_VALUE;
    }
}


