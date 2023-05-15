package org.neetcode.stack;

import java.util.*;

public class ValidParentheses {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>(s.length());

        Map<Character, Character> rightBrackets = Map.of('}', '{', ']', '[', ')', '(');

        for(char c: s.toCharArray()) {
            if(rightBrackets.containsKey(c)) {
                Character last = stack.peekLast();
                if(last == null) {
                    //adding right bracket on empty stack, invalid
                    return false;
                } else if(rightBrackets.containsKey(last)) {
                    //add right on right, valid
                    stack.add(c);
                } else {
                    //check if valid left bracket, and remove both
                    if(rightBrackets.get(c).equals(last)) {
                        //matching left bracket, pop last, valid
                        stack.pollLast();
                    } else {
                        //left bracket does not match c, invalid
                        return false;
                    }
                }
            } else {
                // if Left bracket, add without any restrictions, valid
                stack.add(c);
            }
        }

        //check if no trailing left brackets
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new ValidParentheses().isValid("([)]"));
    }

    public boolean isValid_goodSolution(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }
}
