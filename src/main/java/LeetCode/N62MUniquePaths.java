package LeetCode;

import javafx.util.Pair;

import java.util.*;

/**
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
 *
 * Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 *
 * The test cases are generated so that the answer will be less than or equal to 2 * 109.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: m = 3, n = 7
 * Output: 28
 * Example 2:
 *
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Down -> Down
 * 2. Down -> Down -> Right
 * 3. Down -> Right -> Down
 *
 *
 * Constraints:
 *
 * 1 <= m, n <= 100
 *
 *
 */

/**
 *  M - [30+
 *
 */
public class N62MUniquePaths {

    public static void main(String[] args){
        doRun(3, 3, 2);
        doRun(28, 3, 7);
    }

    private static void doRun(int expected, int m, int n){
        int res = new N62MUniquePaths().uniquePaths(m,n);
        System.out.println("["+(expected == res)+"].expected:"+ expected+".res:"+res);
    }

    ////////////////////////////////////////////////////////////////////////////////
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths.
    //Memory Usage: 41 MB, less than 40.73% of Java online submissions for Unique Paths.
    //优化了2d array
    //DP : 1d array
    //Time: O(M*N); Space: O(M);
    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) return 1;
        int[] res = new int[m];
        res[m-1] = 1;

        for (int j = n - 1 ; j >= 0; j--)
            for (int i = m - 2; i >= 0; i--)
                res[i] += res[i + 1];
        return res[0];
    }

    ////////////////////////////////////////////////////////////////////////
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths.
    //Memory Usage: 40.9 MB, less than 57.24% of Java online submissions for Unique Paths.
    //本质和uniquePaths_DFS一样。但逻辑清晰简单多了。 看了solution的提示，写出来的。
    //DP
    //Time: O(M*N); Space: O(M*N);
    public int uniquePaths_2(int m, int n) {
        if (m == 1 || n == 1) return 1;
        int[][] res = new int[m][n];
        res[m-1][n-1] = 1;

        for (int j = n - 1 ; j >= 0; j--) {
            for (int i = m - 1; i >= 0; i--) {
                if (i + 1 < m) res[i][j] += res[i + 1][j];
                if (j + 1 < n) res[i][j] += res[i][j + 1];
            }
        }
        return res[0][0];
    }

    ////////////////////////////////////////////////////////////////////
    //Runtime: 9 ms, faster than 5.30% of Java online submissions for Unique Paths.
    //Memory Usage: 42.6 MB, less than 5.09% of Java online submissions for Unique Paths.
    //逻辑写得太复杂了！
    //DFS : map + stack
    //Time:O(M*N), Space: O(M*N)
    public int uniquePaths_DFS(int m, int n) {
        if (m == 1 || n == 1) return 1;

        Pair<Integer,Integer> start = new Pair<>(0, 0);
        Pair<Integer,Integer> End = new Pair<>(m-1, n-1);

        Map<Pair<Integer,Integer>, Integer> finish = new HashMap<>();
        finish.put(End, 1);

        //DFS : FILO
        Stack<Pair<Integer,Integer>> stack = new Stack<>();
        stack.push(start);

        while(!stack.isEmpty()){

            Pair<Integer,Integer> currPair = stack.peek();
            int currCount = 0;
            boolean hasVisited = true;

            int i = currPair.getKey();
            int j = currPair.getValue();

            //right
            if (i + 1 < m) {
                Pair nextKey = new Pair<> (i + 1, j);
                if (finish.containsKey(nextKey)) {
                    currCount += finish.get(nextKey);
                } else {
                    hasVisited = false;
                    stack.push(nextKey);
                }
            }
            //down
            if (j + 1 < n) {
                Pair nextKey = new Pair<> (i , j + 1);
                if (finish.containsKey(nextKey)) {
                    currCount += finish.get(nextKey);
                } else {
                    hasVisited = false;
                    stack.push(nextKey);
                }
            }

            if (hasVisited) {
                currPair = stack.pop();
                finish.put(currPair, currCount);
            }
        }

        return finish.get(start);
    }
}
