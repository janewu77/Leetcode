package LeetCode;

/**
 * The red-green-blue color "#AABBCC" can be written as "#ABC" in shorthand.
 *
 * For example, "#15c" is shorthand for the color "#1155cc".
 * The similarity between the two colors "#ABCDEF" and "#UVWXYZ" is -(AB - UV)2 - (CD - WX)2 - (EF - YZ)2.
 *
 * Given a string color that follows the format "#ABCDEF", return a string represents the color that is most similar to the given color and has a shorthand (i.e., it can be represented as some "#XYZ").
 *
 * Any answer which has the same highest similarity as the best answer will be accepted.
 *
 *
 *
 * Example 1:
 *
 * Input: color = "#09f166"
 * Output: "#11ee66"
 * Explanation:
 * The similarity is -(0x09 - 0x11)2 -(0xf1 - 0xee)2 - (0x66 - 0x66)2 = -64 -9 -0 = -73.
 * This is the highest among any shorthand color.
 * Example 2:
 *
 * Input: color = "#4e3fe1"
 * Output: "#5544dd"
 *
 *
 * Constraints:
 *
 * color.length == 7
 * color[0] == '#'
 * color[i] is either digit or character in the range ['a', 'f'] for i > 0.
 */

import static java.time.LocalTime.now;

public class N800ESimilarERGBColor {


    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun("#11ee66", "#09f166");

        doRun("#11ee66", "#09f166");
        doRun("#5544dd", "#4e3fe1");
        doRun("#1155cc", "#1155cc");

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(String expect, String color) {
        String res = new N800ESimilarERGBColor()
                .similarRGB(color);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //2.Math
    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Similar RGB Color.
    //Memory Usage: 40.2 MB, less than 95.24% of Java online submissions for Similar RGB Color.
    public String similarRGB(String color) {
        StringBuffer sb = new StringBuffer("#");
        for (int i = 1 ; i < color.length(); i += 2){
            int number = Integer.valueOf(color.substring(i, i + 2), 16);
            String X = Integer.toHexString(Math.round((float) number / 17));
            sb.append(X).append(X);
        }
        return sb.toString();
    }

    //1.Brute force
    //Runtime: 2 ms, faster than 83.33% of Java online submissions for Similar RGB Color.
    //Memory Usage: 40.8 MB, less than 85.71% of Java online submissions for Similar RGB Color.
    //Time: O(1); Space:O(1)
    public String similarRGB_1(String color) {

        StringBuffer sb = new StringBuffer("#");
        for (int i = 1 ; i < color.length(); i += 2){
            char x = color.charAt(i);
            char y = color.charAt(i + 1);
            int number = Integer.valueOf(color.substring(i, i + 2), 16);
            int k = 1;
            //Time: worst case: O(16)
            while (x != y) {
                String str = Integer.toHexString(number + k);
                x = str.charAt(0);
                y = str.length() == 2 ? str.charAt(1) : '0';
                if (x == y) break;

                str = Integer.toHexString(number - k);
                x = str.charAt(0);
                y = str.length() == 2 ? str.charAt(1) : '0';
                if (x == y) break;

                k++;
            }
            sb.append(x).append(y);
        }
        return sb.toString();
    }
}
