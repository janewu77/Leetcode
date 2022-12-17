package LeetCode;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import static java.time.LocalTime.now;

public class N150MEvaluateReversePolishNotation {

    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");
        doRun(6, new String[]{"4","13","5","/","+"});
        doRun(22, new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"});
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, String[] tokens) {
        int res = new N150MEvaluateReversePolishNotation().evalRPN(tokens);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //2.two pointers + in place
    //Runtime: 7ms, 81.80%; Memory: 41.6MB, 98.66%
    //Time: O(N); Space: O(1)
    private static final Map<String, BiFunction<Integer, Integer, Integer>> OPERATIONS = new HashMap<>();
    static {
        OPERATIONS.put("+", (a, b) -> a + b);
        OPERATIONS.put("-", (a, b) -> a - b);
        OPERATIONS.put("*", (a, b) -> a * b);
        OPERATIONS.put("/", (a, b) -> a / b);
    }

    public int evalRPN(String[] tokens) {
        int t = 0;
        for (int idx = 0; idx < tokens.length; idx++) {
            String token = tokens[idx];
            if (token.equals("*") || token.equals("/") || token.equals("+") || token.equals("-")) {

                //int value = eval(Integer.valueOf(tokens[--t]), Integer.valueOf(tokens[--t]), token);
                BiFunction<Integer, Integer, Integer> operator = OPERATIONS.get(token);
                int operandB = Integer.valueOf(tokens[--t]);
                int operandA = Integer.valueOf(tokens[--t]);
                int value = operator.apply(operandA, operandB);

                token = String.valueOf(value);
            }
            tokens[t++] = token;
        }
        return Integer.valueOf(tokens[0]);
    }


    //1.Stack
    //Runtime: 3ms, 99.5%; Memory: 42.1MB, 88.63%
    //Time: O(N); Space: O(N)
    public int evalRPN_1(String[] tokens) {
        Deque<Integer> deque = new ArrayDeque<>();
        int idx = 0;
        while (idx < tokens.length) {
            String token = tokens[idx];
            int intValue = 0;
            if (token.equals("*") || token.equals("/") || token.equals("+") || token.equals("-")) {
                intValue = eval(deque.pollLast(), deque.pollLast(), token);
            }else{
                intValue = Integer.valueOf(token);
            }
            deque.addLast(intValue);
            idx++;
        }
        return Integer.valueOf(deque.peekLast());
    }

    private int eval(int operand1, int operand2, String token){
        int res = 0;
        switch (token){
            case "*":
                res = operand2 * operand1;
                break;
            case "/":
                res = operand2 / operand1;
                break;
            case "+":
                res = operand2 + operand1;
                break;
            case "-":
                res = operand2 - operand1;
                break;
        }
        return res;
    }
}
