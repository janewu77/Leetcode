package LeetCode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 *
 *
 *
 *
 * Example 1:
 *
 * Input: numRows = 5
 * Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * Example 2:
 *
 * Input: numRows = 1
 * Output: [[1]]
 *
 *
 * Constraints:
 *
 * 1 <= numRows <= 30
 */
public class N118EPascalTriangle {

    public static void main(String[] args) {
        doRun(1);
        doRun(2);
        doRun(30);
    }
    private static int c = 1;
    private static void doRun( int numRows){
        List<List<Integer>> res = new N118EPascalTriangle().generate(numRows);
        System.out.println(res);
        //System.out.println("[" + (expected.equals(res.toString())) +"] "+(c++)+ ".result: "+ res + ".expected:"+expected);
    }

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Pascal's Triangle.
    //Memory Usage: 42.6 MB, less than 5.45% of Java online submissions for Pascal's Triangle.
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(Arrays.asList(1));

        for(int i = 2; i<=numRows; i++){
            List<Integer> currRow = new ArrayList<>();
            res.add(currRow);
            currRow.add(1);
            for (int k = 2; k<i; k++)
                currRow.add(res.get(i-2).get(k-2) + res.get(i-2).get(k-1));
            currRow.add(1);
        }
        return res;
    }
}
