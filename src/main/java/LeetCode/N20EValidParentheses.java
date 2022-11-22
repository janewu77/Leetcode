package LeetCode;

import java.util.*;

/**
 *
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 *
 * An input string is valid if:
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 *
 *
 * Example 1:
 * Input: s = "()"
 * Output: true
 *
 *
 * Example 2:
 * Input: s = "()[]{}"
 * Output: true
 *
 *
 * Example 3:
 * Input: s = "(]"
 * Output: false
 *
 *
 * Constraints:
 * 1 <= s.length <= 104
 * s consists of parentheses only '()[]{}'.
 */

/**
 * E - [ 38m ]
 *   - Stack Queu 的用法
 */
public class N20EValidParentheses {

    public static void main(String[] args){
        System.out.println("expect  true , result:"+ new N20EValidParentheses().isValid("()"));
        System.out.println("expect  false , result:"+ new N20EValidParentheses().isValid("(]"));
        System.out.println("expect  false , result:"+ new N20EValidParentheses().isValid("["));
        System.out.println("expect  false , result:"+ new N20EValidParentheses().isValid("}"));
    }

    /**
     * Runtime: 2 ms, faster than 89.70% of Java online submissions for Valid Parentheses.
     * Memory Usage: 40.5 MB, less than 84.59% of Java online submissions for Valid Parentheses.
     */
    private Map<String,String> con = new HashMap<String,String>(){{
        put("(",")");
        put("[","]");
        put("{","}");
    }};

    public boolean isValid(String s) {
        Stack<String> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            String c = s.substring(i, i+1);
            if (con.containsKey(c)) {
                stack.push(c);
                continue;
            }

            if (con.containsValue(c))
                if (stack.isEmpty() || !(con.get(stack.pop()).equals(c))) return false;
        }
        return stack.isEmpty();
    }
}
