package LeetCode;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations
 * that the number could represent. Return the answer in any order.
 *
 * A mapping of digits to letters (just like on the telephone buttons) is given below.
 * Note that 1 does not map to any letters.
 *
 *
 *
 *
 * Example 1:
 *
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 *
 * Example 2:
 * Input: digits = ""
 * Output: []
 *
 *
 * Example 3:
 *
 * Input: digits = "2"
 * Output: ["a","b","c"]
 * Constraints:
 *
 * 0 <= digits.length <= 4
 * digits[i] is a digit in the range ['2', '9'].
 */

/**
 *
 * M: （耗时50min+)
 *  - 数字字母的映射关系，可写死。（对初始化不熟）
 *  - 用了递归。
 *  - 还有回溯，也可实现。
 */

public class N17MLetterCombinationsonaPhoneNumber {

    public static void main(String[] args) {
        List<String> result = new N17MLetterCombinationsonaPhoneNumber().letterCombinations("23");
        result.stream().forEach(System.out::println);
    }

    // 9
//     private Map<Character, String> letters = Map.of(
//         '2', "abc", '3', "def", '4', "ghi", '5', "jkl",
//         '6', "mno", '7', "pqrs", '8', "tuv", '9', "wxyz");


//    Runtime: 1 ms, faster than 94.10% of Java online submissions for Letter Combinations of a Phone Number.
//    Memory Usage: 40.4 MB, less than 99.67% of Java online submissions for Letter Combinations of a Phone Number.
    private Map<Character, String> letters = new HashMap<Character, String>(){
        {
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }
    };


    /**
     * Runtime: 1 ms, faster than 94.10% of Java online submissions for Letter Combinations of a Phone Number.
     * Memory Usage: 42.9 MB, less than 45.81% of Java online submissions for Letter Combinations of a Phone Number.
     * @param digits
     * @return
     */
     public List<String> letterCombinations(String digits) {
        if (digits.length() <= 0) return new ArrayList<>();

        List<String> prev_comb = letterCombinations(digits.substring(1));

        List<String> result = new ArrayList<>();
        String myString = letters.get(digits.charAt(0));

        for (char c : myString.toCharArray()){
            if (prev_comb.isEmpty()){
                result.add(String.valueOf(c));
            }else{
                for(String s : prev_comb){
                    StringBuilder sb = new StringBuilder();
                    sb.append(c);
                    sb.append(s);
                    result.add(sb.toString());
                }
                //这个运行速度较慢
                //result.addAll(prev_comb.stream().map(s->String.valueOf(c)+s).collect(Collectors.toList()));
            }
        }
        return result;
    }


}
