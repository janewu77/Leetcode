package LeetCode;

import java.util.*;

import static java.time.LocalTime.now;

/**
 * You are given a positive integer k. You are also given:
 *
 * a 2D integer array rowConditions of size n where rowConditions[i] = [abovei, belowi], and
 * a 2D integer array colConditions of size m where colConditions[i] = [lefti, righti].
 * The two arrays contain integers from 1 to k.
 *
 * You have to build a k x k matrix that contains each of the numbers from 1 to k exactly once. The remaining cells should have the value 0.
 *
 * The matrix should also satisfy the following conditions:
 *
 * The number abovei should appear in a row that is strictly above the row at which the number belowi appears for all i from 0 to n - 1.
 * The number lefti should appear in a column that is strictly left of the column at which the number righti appears for all i from 0 to m - 1.
 * Return any matrix that satisfies the conditions. If no answer exists, return an empty matrix.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: k = 3, rowConditions = [[1,2],[3,2]], colConditions = [[2,1],[3,2]]
 * Output: [[3,0,0],[0,0,1],[0,2,0]]
 * Explanation: The diagram above shows a valid example of a matrix that satisfies all the conditions.
 * The row conditions are the following:
 * - Number 1 is in row 1, and number 2 is in row 2, so 1 is above 2 in the matrix.
 * - Number 3 is in row 0, and number 2 is in row 2, so 3 is above 2 in the matrix.
 * The column conditions are the following:
 * - Number 2 is in column 1, and number 1 is in column 2, so 2 is left of 1 in the matrix.
 * - Number 3 is in column 0, and number 2 is in column 1, so 3 is left of 2 in the matrix.
 * Note that there may be multiple correct answers.
 * Example 2:
 *
 * Input: k = 3, rowConditions = [[1,2],[2,3],[3,1],[2,3]], colConditions = [[2,1]]
 * Output: []
 * Explanation: From the first two conditions, 3 has to be below 1 but the third conditions needs 3 to be above 1 to be satisfied.
 * No matrix can satisfy all the conditions, so we return the empty matrix.
 *
 *
 * Constraints:
 *
 * 2 <= k <= 400
 * 1 <= rowConditions.length, colConditions.length <= 104
 * rowConditions[i].length == colConditions[i].length == 2
 * 1 <= abovei, belowi, lefti, righti <= k
 * abovei != belowi
 * lefti != righti
 *
 */

/**
 * H - [time: 120+
 *
 */

public class N2392HBuildaMatrixWithConditions {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;
        data = new int[]{0,1,4,6,7,10};

        int[][] rowConditions;
        int[][] colConditions;

        rowConditions = new int[][]{{1,2},{3,2}};
        colConditions = new int[][]{{2,1},{3,2}};
        doRun_demo("[[0, 0, 1],[3, 0, 0],[0, 2, 0]]", 3, rowConditions, colConditions);

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun_demo(String expect, int k, int[][] rowConditions, int[][] colConditions) {
        int[][] res1 = new N2392HBuildaMatrixWithConditions().buildMatrix(k, rowConditions, colConditions);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i<res1.length; i++) {
            String str = Arrays.toString(res1[i]);
            sb.append(str).append(",");
        }
        if (sb.length() > 1) sb.deleteCharAt(sb.length()-1);
        sb.append("]");

//        String res = comm.toString(res1);
//        String res = Arrays.stream(res1).mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect.equals(sb.toString()))+"]expect:" + expect + " res:" + sb.toString());
        System.out.println("==================");
    }


    //Runtime: 17 ms, faster than 100.00% of Java online submissions for Build a Matrix With Conditions.
    //Memory Usage: 50.3 MB, less than 100.00% of Java online submissions for Build a Matrix With Conditions.
    //Topological Sort: in-degree
    //Time: O(K + rowConditions.length); Space: O(K + N)
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        int[][] res = new int[k][k];

        //value : index
        int[] sortedRow = helper_sort(rowConditions, k);
        int[] sortedCol = helper_sort(colConditions, k);
        if (sortedRow.length == 0 || sortedCol.length == 0) return new int[0][0];

        //Time: O(K)
        for(int i = 1; i <= k; i++)
            res[sortedRow[i - 1]][sortedCol[i - 1]] = i;

        return res;
    }

    //Time: O(2K + rowConditions.length); Space: O(3K + N)
    private int[] helper_sort(int[][] rowConditions, int k){
        //Time: O(K); Space: O(K)
        Set<Integer> set = new HashSet<>();
        for(int i = 1; i <= k; i++) set.add(i);

        //Time: O(rowConditions.length); Space: O(N + K)
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] counter = new int[k]; //in degree
            for (int[] v : rowConditions) {
            List<Integer> list = map.getOrDefault(v[0], new ArrayList<>());
            map.put(v[0], list);
            list.add(v[1]);

            counter[v[1]-1]++;
            set.remove(v[1]);
        }

        //Time: O(K);Space: O(K)
        int[] res = new int[k];//value : index
        Queue<Integer> indegree0 = new LinkedList<>(set);
        int idx = 0;
        while (!indegree0.isEmpty()) {
            int value = indegree0.poll();

            res[value - 1] = idx++;

            if (map.containsKey(value)) {
                List<Integer> list = map.get(value);
                for (int nextValue : list) {
                    counter[nextValue - 1]--;
                    if (counter[nextValue - 1] == 0) indegree0.add(nextValue);
                }
            }
        }

        if (idx != k) return new int[0];//cycle
        return res;
    }
}
