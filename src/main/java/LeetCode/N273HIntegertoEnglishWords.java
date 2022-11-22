package LeetCode;


import java.util.HashMap;
import java.util.Map;

/**
 *
 * Convert a non-negative integer num to its English words representation.
 *
 * Example 1:
 * Input: num = 123
 * Output: "One Hundred Twenty Three"
 *
 *
 * Example 2:
 * Input: num = 12345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 *
 *
 * Example 3:
 * Input: num = 1234567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 *
 *
 * Constraints:
 * 0 <= num <= 231 - 1
 *
 */

/**
 * H - [time: 60+]
 *  - 这个H,其实没有很难。但是比较逻嗦，边界容易出错。
 */
public class N273HIntegertoEnglishWords {

    public static void main(String[] args){

        String res = new N273HIntegertoEnglishWords().numberToWords(2147483600);
        System.out.println(res);

        String exected= "Zero";
        res = new N273HIntegertoEnglishWords().numberToWords(0);
        System.out.println( "1.["+exected.equals(res)+"]" + res);

        exected= "Four";
        res = new N273HIntegertoEnglishWords().numberToWords(4);
        System.out.println( "2.["+exected.equals(res)+"]" + res);

        exected= "Two Hundred Two";
        res = new N273HIntegertoEnglishWords().numberToWords(202);
        System.out.println( "["+exected.equals(res)+"]" + res);

        //Two Thousand and One
        exected= "Two Thousand";
        res = new N273HIntegertoEnglishWords().numberToWords(2000);
        System.out.println( "["+exected.equals(res)+"]" + res);

        exected= "Two Thousand One";
        res = new N273HIntegertoEnglishWords().numberToWords(2001);
        System.out.println( "["+exected.equals(res)+"]" + res);

        exected= "Two Thousand One Hundred One";
        res = new N273HIntegertoEnglishWords().numberToWords(2101);
        System.out.println( "["+exected.equals(res)+"]" + res);

        exected= "Two Million One";
        res = new N273HIntegertoEnglishWords().numberToWords(2000001);
        System.out.println( "["+exected.equals(res)+"]" + res);

        exected= "Two Million One Hundred One";
        res = new N273HIntegertoEnglishWords().numberToWords(2000101);
        System.out.println( "["+exected.equals(res)+"]" + res);

        exected= "Ten";
        res = new N273HIntegertoEnglishWords().numberToWords(10);
        System.out.println( "["+exected.equals(res)+"]" + res);

        exected= "Twenty";
        res = new N273HIntegertoEnglishWords().numberToWords(20);
        System.out.println( "["+exected.equals(res)+"]" + res);

        exected= "One Hundred";
        res = new N273HIntegertoEnglishWords().numberToWords(100);
        System.out.println( "["+exected.equals(res)+"]" + res);

        exected= "One Thousand";
        res = new N273HIntegertoEnglishWords().numberToWords(1000);
        System.out.println( "["+exected.equals(res)+"]" + res);

        exected= "Two Hundred Thousand";
        res = new N273HIntegertoEnglishWords().numberToWords(200000);
        System.out.println( "["+exected.equals(res)+"]" + res);

        exected= "One Million";
        res = new N273HIntegertoEnglishWords().numberToWords(1000000);
        System.out.println( "["+exected.equals(res)+"]" + res);

        exected= "One Hundred Million";
        res = new N273HIntegertoEnglishWords().numberToWords(100000000);
        System.out.println( "["+exected.equals(res)+"]" + res);

        exected= "One Billion";
        res = new N273HIntegertoEnglishWords().numberToWords(1000000000);
        System.out.println( "["+exected.equals(res)+"]" + res);

        exected= "One Hundred Million One Hundred";
        res = new N273HIntegertoEnglishWords().numberToWords(100000100);
        System.out.println( "["+exected.equals(res)+"]" + res);

    }

    private String[] english1to19 = {"Zero", "One", "Two", "Three", "Four", "Five",
            "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
            "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private String[] english20to90 = {"Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    //Runtime: 3 ms, faster than 96.12% of Java online submissions for Integer to English Words.
    //Memory Usage: 42.2 MB, less than 80.80% of Java online submissions for Integer to English Words.
    public String numberToWords(int num) {
        StringBuilder sb = new StringBuilder();
        if (num == 0) return "Zero";

        int bPart = (num / 1000000000) % 1000;
        if (bPart > 0) sb.append(helper(bPart)).append(" Billion ");

        int mPart = (num / 1000000 ) % 1000;
        if (mPart > 0) sb.append(helper(mPart)).append(" Million ");

        int tPart = (num / 1000) % 1000;
        if (tPart > 0) sb.append(helper(tPart)).append(" Thousand ");

        int hPart = num % 1000;
        sb.append(helper(hPart));

        return sb.toString().trim();
    }

    private String helper(int num){
        if (num == 0) return "";

        StringBuilder sb = new StringBuilder();

        int h = num / 100;
        if (h != 0) sb.append(english1to19[h]).append(" Hundred");

        int n = num % 100;
        if (h > 0 && n != 0) sb.append(" ");

        if (n >= 20) {
            sb.append(english20to90[n/10-1]);
            if (num % 10 > 0) sb.append(" ").append(english1to19[num % 10]);
        } else if (n > 0) sb.append(english1to19[n]);

        return sb.toString();
    }
}
