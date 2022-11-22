package LeetCode;

/**
 *
 * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer
 * (similar to C/C++'s atoi function).
 *
 * The algorithm for myAtoi(string s) is as follows:
 *
 * Read in and ignore any leading whitespace.
 * Check if the next character (if not already at the end of the string) is '-' or '+'.
 * Read this character in if it is either. This determines if the final result is negative
 * or positive respectively. Assume the result is positive if neither is present.
 * Read in next the characters until the next non-digit character or the end of the input is reached.
 * The rest of the string is ignored.
 * Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read,
 * then the integer is 0. Change the sign as necessary (from step 2).
 * If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then clamp the integer
 * so that it remains in the range. Specifically, integers less than -231 should be clamped to -231,
 * and integers greater than 231 - 1 should be clamped to 231 - 1.
 * Return the integer as the final result.
 * Note:
 *
 * Only the space character ' ' is considered a whitespace character.
 * Do not ignore any characters other than the leading whitespace or the rest of the string after the digits.
 *
 *
 * Example 1:
 * Input: s = "42"
 * Output: 42
 * Explanation: The underlined characters are what is read in, the caret is the current reader position.
 * Step 1: "42" (no characters read because there is no leading whitespace)
 *          ^
 * Step 2: "42" (no characters read because there is neither a '-' nor '+')
 *          ^
 * Step 3: "42" ("42" is read in)
 *            ^
 * The parsed integer is 42.
 * Since 42 is in the range [-231, 231 - 1], the final result is 42.
 *
 *
 *
 * Example 2:
 * Input: s = "   -42"
 * Output: -42
 * Explanation:
 * Step 1: "   -42" (leading whitespace is read and ignored)
 *             ^
 * Step 2: "   -42" ('-' is read, so the result should be negative)
 *              ^
 * Step 3: "   -42" ("42" is read in)
 *                ^
 * The parsed integer is -42.
 * Since -42 is in the range [-231, 231 - 1], the final result is -42.
 *
 *
 * Example 3:
 * Input: s = "4193 with words"
 * Output: 4193
 * Explanation:
 * Step 1: "4193 with words" (no characters read because there is no leading whitespace)
 *          ^
 * Step 2: "4193 with words" (no characters read because there is neither a '-' nor '+')
 *          ^
 * Step 3: "4193 with words" ("4193" is read in; reading stops because the next character is a non-digit)
 *              ^
 * The parsed integer is 4193.
 * Since 4193 is in the range [-231, 231 - 1], the final result is 4193.
 *
 *
 * Constraints:
 * 0 <= s.length <= 200
 * s consists of English letters (lower-case and upper-case), digits (0-9), ' ', '+', '-', and '.'.
 *
 */

/**
 * M: - 耗时 40M 改写，则花了60+
 *  - 注意边界
 */
public class N8MStringtoInteger {

    public static void main(String[] args){

//        2147483647
//                -2147483648
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);

        long x = Long.valueOf("-2147483649");
        System.out.println("x:"+x);
        System.out.println("==============");

        String s1 = "42";
        int output = new N8MStringtoInteger().myAtoi(s1);
        System.out.println("expected: 42; output:"+output);

        String s2 = "   -42  ";
        int output2 = new N8MStringtoInteger().myAtoi(s2);
        System.out.println("expected: -42; output2:"+output2);

        String s3 = " 4193 with words";
        int output3 = new N8MStringtoInteger().myAtoi(s3);
        System.out.println("expected: 4193; output3:"+output3);

        String s4 = " -2147483649";
        int output4 = new N8MStringtoInteger().myAtoi(s4);
        System.out.println("expected: -2147483648; output4:"+output4);

        String s5 = " 2147483649";
        int output5 = new N8MStringtoInteger().myAtoi(s5);
        System.out.println("expected: 2147483647; output5:"+output5);

        String s6 = "";
        int output6 = new N8MStringtoInteger().myAtoi(s6);
        System.out.println("expected: 0; output6:"+output6);

        String s7 = "   0000000000012345678";
        int output7 = new N8MStringtoInteger().myAtoi(s7);
        System.out.println("expected: 12345678; output7:"+output7);

        String s8 = "   9223372036854775808";
        int output8 = new N8MStringtoInteger().myAtoi(s8);
        System.out.println("expected: 2147483647; output8:"+output8);

        String s9 = "+";
        int output9 = new N8MStringtoInteger().myAtoi(s9);
        System.out.println("expected: 0; output9:"+output9);

        String s10 = "3.14";
        int output10 = new N8MStringtoInteger().myAtoi(s10);
        System.out.println("expected: 3; output10:"+output10);

        String s11 = "21474836460";
        int output11 = new N8MStringtoInteger().myAtoi(s11);
        System.out.println(" 214 748 364 60 expected: 214 748 364 7; output:"+output11);

        String s12 = " ";
        int output12 = new N8MStringtoInteger().myAtoi(s12);
        System.out.println("expected: 0; output:"+output12);

        String s13 = " 1192820738r2";
        int output13 = new N8MStringtoInteger().myAtoi(s13);
        System.out.println("expected: 1192820738; output:"+output13);
    }

    // 不用Integer.MAX_VALUE Integer.MIN_VALUE; 也不用long时的解法
    public int myAtoi(String s) {

        if(s.length() < 1) return 0;

        int intMax = 2147483647;
        int intMin = -2147483648 ;

        int sign = 1;
        int i = 0;

        //skip whitespaces
        while (i < s.length() && s.charAt(i) == ' ') i++;

        //get sign
        if (i < s.length() && (s.charAt(i) == '-' || s.charAt(i) == '+')) {
            if (s.charAt(i) == '-') sign = -1;
            i++;
        }
        //skip lead zero
        while (i < s.length() && s.charAt(i) == '0') i++;

        int result = 0;
        int ps = i;
        while(i < s.length() && i-ps<9){
            if (!Character.isDigit(s.charAt(i))) break;
            result = result * 10 + (s.charAt(i) - '0');
            i++;
        }

        //处理最后1/2位
        if (i < s.length() && Character.isDigit(s.charAt(i))) {
            // 当前值已经过大 or 后面实际还有二位
            if ((result > intMax / 10) || (i+1 < s.length() && Character.isDigit(s.charAt(i+1))) )
                return sign < 0 ? intMin: intMax;
            else if (result == intMax / 10) {
                if (sign > 0 && s.charAt(i) - '0' > 7) return intMax;
                if (sign < 0 && s.charAt(i) - '0' > 8) return intMin;
            }
            result = sign * (s.charAt(i) - '0') + sign * result * 10;

        }else{
            result = sign * result;
        }
        return result;
    }


    public int myAtoi2(String s) {
        Long result = 0l;

        int sign = 1;
        int i = 0;
        char[] cList = s.trim().toCharArray();
        if(cList.length < 1) return 0;

        //get sign
        if (cList[i] == '-' || cList[i] == '+') {
            if (cList[i] == '-') sign = -1;
            i++;
        }
        //skip lead zero
        while (i < cList.length  && cList[i] == '0') i++;

        int n = 0; //防止超过Long的最大值
        StringBuilder sb = new StringBuilder();
        while( i < cList.length && n<12){
            if (!Character.isDigit(cList[i])) break;
            sb.append(cList[i]);
            i++; n++;
        }

        if(sb.length() >= 1)
            result = Long.valueOf(sb.toString()) * sign;

        if (result > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (result < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        return result.intValue();
    }

}
