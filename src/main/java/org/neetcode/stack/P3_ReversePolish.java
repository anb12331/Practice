package org.neetcode.stack;

import java.util.*;

public class P3_ReversePolish {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        Set<String> ops = Set.of("+", "-", "*", "/");
        int eval;
        for(String token: tokens) {
            if(ops.contains(token)) {
                eval = operate(
                        stack.pop(),
                        stack.pop(),
                        token);
                stack.addFirst(eval);
            } else {
                stack.addFirst(Integer.valueOf(token));
            }
        }
        return stack.pop();
    }

    private int operate(int arg2, int arg1, String op) {
        switch (op){
            case "+":
                return arg1 + arg2;
            case "-":
                return arg1 - arg2;
            case "*":
                return arg1 * arg2;
            case "/":
                return arg1 / arg2;
        }
        throw new UnsupportedOperationException("Unsupported operator: " + op);
    }

    public static void main(String[] args) {
        System.out.println(new P3_ReversePolish()
                .evalRPN(new String[] {"4","13","5","/","+"}));
    }
}
