package Contest;


import static java.time.LocalTime.now;

/**
 * An integer n is strictly palindromic if, for every base b between 2 and n - 2 (inclusive), the string representation of the integer n in base b is palindromic.
 *
 * Given an integer n, return true if n is strictly palindromic and false otherwise.
 *
 * A string is palindromic if it reads the same forward and backward.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 9
 * Output: false
 * Explanation: In base 2: 9 = 1001 (base 2), which is palindromic.
 * In base 3: 9 = 100 (base 3), which is not palindromic.
 * Therefore, 9 is not strictly palindromic so we return false.
 * Note that in bases 4, 5, 6, and 7, n = 9 is also not palindromic.
 * Example 2:
 *
 * Input: n = 4
 * Output: false
 * Explanation: We only consider base 2: 4 = 100 (base 2), which is not palindromic.
 * Therefore, we return false.
 *
 *
 *
 * Constraints:
 *
 * 4 <= n <= 105
 */

/**
 * M - [time: 15-
 */
//2396. Strictly Palindromic Number
public class N6172MStrictlyPalindromicNumber {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun_demo(false, 9);
        doRun_demo(false, 4);

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun_demo(boolean expect, int space) {
        boolean res = new N6172MStrictlyPalindromicNumber().isStrictlyPalindromic(space);
////        String res = comm.toString(res1);
//        String res = Arrays.stream(res1).mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(","));
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        //int[][] res1
        //        sb.append("[");
        //        for(int i = 0; i<res1.length; i++) {
        //            String str = Arrays.toString(res1[i]);
        //            sb.append(str).append(",");
        //        }
        //        if (sb.length() > 1) sb.deleteCharAt(sb.length()-1);
        //        sb.append("]");
//        System.out.println("==================");
    }

    //
    public boolean isStrictlyPalindromic(int n) {
        return false;
    }

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Strictly Palindromic Number.
    //Memory Usage: 41 MB, less than 27.27% of Java online submissions for Strictly Palindromic Number.
    //brute force
    public boolean isStrictlyPalindromic_1(int n) {
        for(int b = 2; b <= n-2; b++)
            if (!isPalindromic(Integer.toString(n, b))) return false;
        return true;
    }
    private boolean isPalindromic(String x){
        for(int i = 0; i<x.length(); i++)
            if (x.charAt(i) != x.charAt(x.length() - 1 -i)) return false;
        return true;
    }
}
