package LeetCode;


import static java.time.LocalTime.now;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 *
 *
 * Example 1:
 *
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * Example 2:
 *
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * Example 3:
 *
 * Input: s = "A", numRows = 1
 * Output: "A"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consists of English letters (lower-case and upper-case), ',' and '.'.
 * 1 <= numRows <= 1000
 */
public class N6MZigzagConversion {
    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");

        doRun("a", "a", 2);
        doRun("PINALSIGYAHRPI", "PAYPALISHIRING", 4);
        doRun("PAHNAPLSIIGYIR", "PAYPALISHIRING", 3);


        doRun("abc", "abc", 1);
        doRun("a", "a", 1);

        System.out.println(now());
        System.out.println("==================");
    }

    //contest: 92  330
    //2547 1494 460 1908 1626
    static private void doRun(String expect, String s, int numRows) {
        String res = new N6MZigzagConversion().convert(s, numRows);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    //1.String traversal
    //Runtime: 3ms 95%; Memory: 42.3MB 95%
    //time: O(N); Space: O(N)
    public String convert(String s, int numRows) {
        int n = s.length();
        if (n <= numRows) return s;

        int span = numRows + (numRows - 2 < 0 ?  0 : numRows - 2);
        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = numRows + numRows - 2; i < numRows; i++, j--) {
            sb.append(s.charAt(i));

            int m = (i == 0 || i == numRows - 1) ? 0 : j;

            for (int x = i + span; x < n || (m != 0 && m < n); x += span) {
                if (m != 0 && m < n){
                    sb.append(s.charAt(m));
                    m += span;
                }
                if (x < n)
                    sb.append(s.charAt(x));
            }
        }
        return sb.toString();
    }
}
