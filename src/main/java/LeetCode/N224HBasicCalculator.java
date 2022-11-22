package LeetCode;


import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

import static java.time.LocalTime.now;

/**
 * Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 *
 *
 * Example 1:
 *
 * Input: s = "1 + 1"
 * Output: 2
 * Example 2:
 *
 * Input: s = " 2-1 + 2 "
 * Output: 3
 * Example 3:
 *
 * Input: s = "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 3 * 105
 * s consists of digits, '+', '-', '(', ')', and ' '.
 * s represents a valid expression.
 * '+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
 * '-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
 * There will be no two consecutive operators in the input.
 * Every number and running calculation will fit in a signed 32-bit integer.
 */

public class N224HBasicCalculator {


    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");

        doRun(3, "1-(     -2)");

        doRun(-2, "- 2");
        doRun(3, "2-1+2");
        doRun(3, "2-1 + 2 ");

        doRun(13, "11  + 2 ");

        doRun(23, "(1+(4+5+2)-3)+(6+8)");
        doRun(-3, "- (2+1)");


        doRun(3, "1 + 2 ");
        doRun(3, " 1  + 2 ");

        doRun(9, " 11  -    2 ");
        doRun(13, " 11  + 2 ");
        doRun(10, " ( 11  -    2 ) + 1");

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, String s) {
        int res = new N224HBasicCalculator().calculate(s);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //6.
    //Runtime: 7 ms, faster than 90.70% of Java online submissions for Basic Calculator.
    //Memory Usage: 42.5 MB, less than 89.18% of Java online submissions for Basic Calculator.
    public int calculate(String s) {
        int result = 0, sign = 1, operand = 0;

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(sign);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if(Character.isDigit(c))
                operand = operand * 10 + (c - '0');

            else if(c == '+' || c == '-') {
                result += sign * operand;
                sign = stack.peek() * (c == '+' ? 1 : -1);
                operand = 0;

            } else if(c == '(') stack.push(sign);
            else if(c == ')') stack.pop();
        }

        result += sign * operand;
        return result;
    }

    //5.recursion
    //Runtime: 3 ms, faster than 99.27% of Java online submissions for Basic Calculator.
    //Memory Usage: 42.1 MB, less than 95.54% of Java online submissions for Basic Calculator.
    //Time: O(N); Space: O(N)
    int idx = 0;
    public int calculate5(String s) {
        int res = 0, operand = 0, sign = 1;
        while (idx < s.length()) {
            char c = s.charAt(idx++);
            if (Character.isDigit(c)) operand = operand * 10 + (c - '0');
            else if (c == '+' || c == '-') {
                res += sign * operand;
                operand = 0;
                sign = c == '-' ? -1 : 1;
            }
            else if (c == '(') operand = calculate5(s);
            else if (c == ')') return res + sign * operand;
        }
        return res + sign * operand;
    }


    //4.stack, '-'
    //Runtime: 17 ms, faster than 66.03% of Java online submissions for Basic Calculator.
    //Memory Usage: 42.1 MB, less than 95.54% of Java online submissions for Basic Calculator.
    public int calculate_4(String s) {
        s =  '(' + s.trim() + ')';

        boolean lastIsDigital = false;
        Deque<Integer> operandStack = new ArrayDeque<>();
        Deque<Character> operatorStack = new ArrayDeque<>();

        for (int idx = 0; idx < s.length(); idx++) {
            char c = s.charAt(idx);

            if (Character.isDigit(c) || c == ')') {

                int operand;
                if (c == ')') {
                    //compute
                    operand = operandStack.pollLast();
                    while (operatorStack.pollLast() != '(')
                        operand += operandStack.pollLast();
                }else{
                    //get operand from S
                    operand = s.charAt(idx) - '0';
                    while (idx + 1 < s.length() && Character.isDigit(s.charAt(idx + 1)))
                        operand = operand * 10 + (s.charAt(++idx) - '0');
                }

                if (!operatorStack.isEmpty() && operatorStack.peekLast() == '-'){
                    operatorStack.pollLast();
                    operatorStack.addLast('+');
                    operand = -operand;
                }
                operandStack.addLast(operand);
                lastIsDigital = true;

            } else if (c == '(' || c == '+' || c == '-') {
                if (c == '-' && (operandStack.isEmpty() || !lastIsDigital))
                    operandStack.addLast(0);
                operatorStack.add(s.charAt(idx));
                lastIsDigital = false;
            }
        }
        return operandStack.pollLast();
    }


    //3.stack , scan backward
    //Runtime: 16 ms, faster than 68.66% of Java online submissions for Basic Calculator.
    //Memory Usage: 41.9 MB, less than 99.62% of Java online submissions for Basic Calculator.
    //Time: O(N); Space: O(N)
    public int calculate_3(String s) {
        s =  '(' + s.trim() + ')';

        boolean lastIsDigital = false;
        Deque<Integer> operandStack = new ArrayDeque<>();
        Deque<Character> operatorStack = new ArrayDeque<>();

        for (int idx = s.length() - 1 ; idx >= 0; idx--) {
            char c = s.charAt(idx);
            if (Character.isDigit(s.charAt(idx))) {
                int operand = c - '0';
                int m = 10;
                while (idx - 1 >= 0 && Character.isDigit(s.charAt(idx - 1))) {
                    operand = (s.charAt(--idx) - '0') * m + operand;
                    m *= 10;
                }
                operandStack.addLast(operand);
                lastIsDigital = true;
            }else if (c == '(') {

                //compute
                //doEval(valueStack, signStack);
                int res = !lastIsDigital ? 0 : operandStack.pollLast();
                while (!operatorStack.isEmpty() && operatorStack.peekLast() !=')') {
                    if (operatorStack.pollLast() == '+') res += operandStack.pollLast();
                    else res -= operandStack.pollLast();
                }
                if (!operatorStack.isEmpty() && operatorStack.peekLast() ==')') operatorStack.pollLast();
                operandStack.add(res);

                lastIsDigital = true;
            } else if (c == ')' || c == '+' || c == '-') {
                operatorStack.add(s.charAt(idx));
                lastIsDigital = false;
            }
        }
        return operandStack.pollLast();
    }


    //2.stack, scan from left to right
    //Runtime: 18 ms, faster than 63.92% of Java online submissions for Basic Calculator.
    //Memory Usage: 42.5 MB, less than 87.79% of Java online submissions for Basic Calculator.
    //Time: O(N); Space: O(N)
    public int calculate_2(String s) {

        boolean lastIsDigital = false;
        Deque<Integer> operandStack = new ArrayDeque<>();
        Deque<Character> operatorStack = new ArrayDeque<>();
        for (int idx = 0; idx < s.length(); idx++) {
            char c = s.charAt(idx);
            if (Character.isDigit(c)) {
                int operand = c - '0';
                while (idx + 1 < s.length() && Character.isDigit(s.charAt(idx + 1)))
                    operand = operand * 10 + (s.charAt(++idx) - '0');
                operandStack.addLast(operand);
                //compute
                doEval2(operandStack, operatorStack);
                lastIsDigital = true;
            }else if (c == ')') {
                operatorStack.pollLast(); //pop '('
                //compute
                doEval2(operandStack, operatorStack);
                lastIsDigital = true;
            } else if (c == '(' || c == '+' || c == '-') {
                if (s.charAt(idx) == '-' && (operandStack.isEmpty() || !lastIsDigital))
                    operandStack.addLast(0);
                operatorStack.add(s.charAt(idx));
                lastIsDigital = false;
            }
        }
        return operandStack.pollLast();
    }
    private void doEval2(Deque<Integer> operandStack, Deque<Character> operatorStack){
        int res = operandStack.pollLast();
        if (!operatorStack.isEmpty() && operatorStack.peekLast() != '(') {
            char operator = operatorStack.pollLast();
            if (operator == '+') res += operandStack.pollLast();
            else res = operandStack.pollLast() - res; //!!!!!!
        }
        operandStack.addLast(res);
    }



    //1.stack, reverse stack
    //Runtime: 25 ms, faster than 46.93% of Java online submissions for Basic Calculator.
    //Memory Usage: 43.9 MB, less than 73.18% of Java online submissions for Basic Calculator.
    //Time: O(N); Space: O(N)
    public int calculate_1(String s) {
        s =  '(' + s.trim() + ')';
        boolean lastIsDigital = false;
        Deque<Integer> operandStack = new ArrayDeque<>();
        Deque<Character> operatorStack = new ArrayDeque<>();

        for (int idx = 0; idx < s.length(); idx++) {
            char c = s.charAt(idx);

            if (Character.isDigit(c)) {
                //get number
                int operand = c - '0';
                while (idx + 1 < s.length() && Character.isDigit(s.charAt(idx + 1)))
                    operand = operand * 10 + (s.charAt(++idx) - '0');

                operandStack.addLast(operand);
                lastIsDigital = true;

            }else if (c == ')') {
                //reverse
                Deque<Integer> operandStack2 = new ArrayDeque<>();
                Deque<Character> operatorStack2 = new ArrayDeque<>();
                while (operatorStack.peekLast() != '('){
                    operandStack2.addLast(operandStack.pollLast());
                    operatorStack2.addLast(operatorStack.pollLast());
                }
                operandStack2.addLast(operandStack.pollLast());
                operatorStack.pollLast(); //pop '('

                //compute
                int res = operandStack2.pollLast();
                while (!operatorStack2.isEmpty()) {
                    char operator = operatorStack2.pollLast();
                    if (operator == '+') res += operandStack2.pollLast();
                    else res -= operandStack2.pollLast();
                }

                operandStack.addLast(res);
                lastIsDigital = true;
            } else if (c == '(' || c == '+' || c == '-') {
                if (c == '-' && (operandStack.isEmpty() || !lastIsDigital))
                    operandStack.addLast(0);
                operatorStack.add(c);
                lastIsDigital = false;
            }
        }
        return operandStack.pollLast();
    }


}
