package org.neetcode.stack;

import java.util.*;

public class P4_GenerateParentheses {
    // This solution uses BFS to search the Decision Tree
    public List<String> generateParenthesis(int n) {
        List<Brackets> validParenths = new ArrayList<>();
        validParenths.add(new Brackets());
        int lCount = n, rCount = n;
        for(int i = 0; i < n*2; i++) {
            List<Brackets> newValidParenths = new ArrayList<>();
            for(Brackets list: validParenths) {

                Brackets newLeft = new Brackets(list);
                if(addParnthToStack('(', newLeft, n))
                    newValidParenths.add(newLeft);

                Brackets newRight = new Brackets(list);
                if(addParnthToStack(')', newRight, n))
                    newValidParenths.add(newRight);
            }
            validParenths = newValidParenths;
        }

        List<String> res = new ArrayList<>(validParenths.size());

        validParenths.forEach(l -> {
            char[] chars = new char[l.s.size()];
            for(int i = 0; i < l.s.size(); i++) chars[i] = l.s.get(i);
            res.add(String.valueOf(chars));
        });

        return res;
    }

    class Brackets {
        List<Character> s;
        int lCount;
        int rCount;

        Brackets() {
            s = new ArrayList<>();
        }
        Brackets(Brackets b) {
            this.s = new ArrayList<>(b.s);
            lCount = b.lCount;
            rCount = b.rCount;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            s.forEach(c->sb.append(c));
            return sb.toString();
        }
    }

    private boolean addParnthToStack(Character parenth, Brackets stack, int n) {
        if((parenth == '(' && stack.lCount >= n)
        || (parenth == ')' && stack.rCount >= n))
            return false;
        int size = stack.s.size();

        if(size == 0) {
            if(parenth == '(') {
                stack.s.add(parenth);
                stack.lCount++;
                return true;
            } else {
                return false;
            }

        }

        Character lastParenth = stack.s.get(size - 1);

        if(lastParenth == '(') {
            stack.s.add(parenth);
        } else { // ')'
            if(parenth == '(') {
                stack.s.add(parenth);
            } else { // adding ')' over ')'
                if(size >= 3) {
                    if(stack.rCount + 1 > stack.lCount) return false;
                    stack.s.add(parenth);
                } else {
                    return false;
                }
            }
        }

        if(parenth == '(') stack.lCount++; else stack.rCount++;

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new P4_GenerateParentheses().generateParenthesis(3));
    }

    Stack<Character> stack = new Stack<>();
    List<String> res = new ArrayList<>();

    public List<String> generateParenthesis_goodSol(int n) {
        backtrack(0, 0, n);
        return res;
    }

    // All possible bracket combinations can be thought of as a tree (Decision Tree).
    // We need to perform DFS (or BFS) on this Decision Tree to find the valid combinations
    // If any intermediate node is invalid, we can invalidate the entire sub-tree below this node
    // The below solution uses DFS to search. The advantage of DFS is that only a single stack has to
    // be maintained to stare the current result. While going up one level, the stack can be reverted to
    // previous state for use in next neighbor's dfs
    private void backtrack(int openN, int closedN, int n) {
        if (openN == closedN && closedN == n) {
            Iterator vale = stack.iterator();
            String temp = "";
            while (vale.hasNext()) {
                temp = temp + vale.next();
            }
            res.add(temp);
        }
        if (openN < n) {
            stack.push('(');
            backtrack(openN + 1, closedN, n);
            stack.pop();
        }
        if (closedN < openN) {
            stack.push(')');
            backtrack(openN, closedN + 1, n);
            stack.pop();
        }
    }
}
