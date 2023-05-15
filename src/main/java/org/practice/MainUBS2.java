package org.practice;

import java.util.*;

public class MainUBS2 {
    public static void main(String[] args) {
//        System.out.println(missingWords("I am using cheese to imp prog", "am cheese to imp"));
        System.out.println(isBalanced("{}("));
    }

    static String isBalanced(String s) {
        char[] chars = s.toCharArray();
        Deque<Character> q = new ArrayDeque<>(s.length());
        Map<Character, Character> leftBrackets = new HashMap<>();
        leftBrackets.put('{', '}');
        leftBrackets.put('(', ')');

        leftBrackets.clear();

        for(char curr: chars) {
            if(!q.isEmpty()) {
                char head = q.peek();
                if(leftBrackets.containsKey(head)) {
                    if(leftBrackets.containsKey(curr)) {
                        q.push(curr);
                    } else {
                        if(leftBrackets.get(head) == curr) {
                            q.pop();
                        } else {
                            return "false";
                        }
                    }
                }

            } else {
                q.push(curr);
            }
            System.out.println(q);
        }

        return q.isEmpty() ? "true" : "false";
    }


    public static List<String> missingWords(String sFull, String tFull) {
        String[] sWords = sFull.split(" ");
        String[] tWords = tFull.split(" ");

        int s_p = 0;
        int t_p = 0;

        List<String> missing = new ArrayList<>(sWords.length - tWords.length);

        while (s_p < sWords.length) {
            if (t_p < tWords.length && sWords[s_p].equals(tWords[t_p])) {
                t_p++;
            } else {
                missing.add(sWords[s_p]);
            }
            s_p++;
        }

        return missing;
    }

}
