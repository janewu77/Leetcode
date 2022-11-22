package LeetCode;


/**
 * Given a string s formed by digits ('0' - '9') and '#' .
 * We want to map s to English lowercase characters as follows:
 *
 * Characters ('a' to 'i') are represented by ('1' to '9') respectively.
 * Characters ('j' to 'z') are represented by ('10#' to '26#') respectively.
 * Return the string formed after mapping.
 *
 * It's guaranteed that a unique mapping will always exist.
 *
 *
 * Example 1:
 * Input: s = "10#11#12"
 * Output: "jkab"
 * Explanation: "j" -> "10#" , "k" -> "11#" , "a" -> "1" , "b" -> "2".
 *
 *
 * Example 2:
 * Input: s = "1326#"
 * Output: "acz"
 *
 * Example 3:
 * Input: s = "25#"
 * Output: "y"
 *
 * Example 4:
 * Input: s = "12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#"
 * Output: "abcdefghijklmnopqrstuvwxyz"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s[i] only contains digits letters ('0'-'9') and '#' letter.
 * s will be valid string such that mapping is always possible.
 *
 */

/**
 * 基本概念：
 *  - char 字母与数字的转换
 *  - StringBuilder
 *
 */
public class N1309EDecryptStringfromAlphabet2IntegerMapping {

    public static void main(String[] args) {

//        char a = char(64);
        String s = "12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#";
        String r = new N1309EDecryptStringfromAlphabet2IntegerMapping().freqAlphabets(s);
        System.out.println("reslut:"+(r.equals("abcdefghijklmnopqrstuvwxyz")) + "."+r);

    }

    public String freqAlphabets(String s) {

        StringBuilder result = new StringBuilder();

        int i =0;
        int maxLen = s.length();

        while (i< maxLen){
            //逐一取出需要转换的数字
            //int intC = new Integer(s.substring(i,i+1));
            int intC = Character.getNumericValue(s.charAt(i));

            int idxShape = i + 2;
            if(idxShape < maxLen && s.charAt(idxShape) == '#'){
                //int c2 = new Integer(s.substring(idxShape-1,idxShape));
                intC = intC * 10 + Character.getNumericValue(s.charAt(idxShape-1));
                i = idxShape;
            }

            //转成字符
            result.append((char)('a'+intC-1));
            i++;
        }

        return result.toString();
    }
}
