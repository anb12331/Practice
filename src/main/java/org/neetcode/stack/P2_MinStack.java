package org.neetcode.stack;

import java.util.Stack;

class MinStack {
    Stack<int[]> stack;
    public MinStack() {
        stack = new Stack<>();
    }

    public void push(int val) {
        int[] prev = stack.empty() ? null : stack.peek();
        if(prev == null || val < prev[1]) {
            stack.push(new int[]{val, val});
        } else {
            stack.push(new int[]{val, prev[1]});
        }
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek()[0];
    }

    public int getMin() {
        return stack.peek()[1];
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); // return -3
        minStack.pop();
        System.out.println(minStack.top());    // return 0
        System.out.println(minStack.getMin()); // return -2
    }
}
