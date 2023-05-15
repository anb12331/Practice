package org.practice;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("abcde", "abc", "axyz", "axyzw");

        Main trie = new Main();
        String out = trie.add(words);
        System.out.println(out);
    }

    private Tode root;

    public String add(List<String> words) {

        for(String word: words) {
            char[] s = word.toCharArray();
            if(root == null) {
                root = new Tode('0');
                root.isLeaf = false;
            }
            char[] parentWord = addRecursive(s, -1, root);
            if (parentWord != null) {
                return String.copyValueOf(parentWord);
            }
        }

        return null;
    }

    private char[] addRecursive(char[] s, int index, Tode curr) {
       if(curr.isLeaf != null && curr.isLeaf) {
            //input string is larger, curr is prefix. Return input str.
            return s;
        }

        if(index < s.length - 1) {
            curr.isLeaf = false; //not a leaf as child nodes exist
            char nextVal = s[index + 1];
            Tode next = curr.childs.get(nextVal);

            if(next == null) {
                next = new Tode(nextVal);
                if(curr.childs.isEmpty()) {
                    curr.firstChild = nextVal;
                }
                curr.childs.put(nextVal, next);
            }

            return addRecursive(s, index+1, next);
        } else if(index == s.length - 1) { //last letter of string
            if(curr.isLeaf == null) { //Node not visited before
                curr.isLeaf = true; //added complete string to Tree. Can exit now
                return null;
            } else {
                //input str is prefix as input str ended and current node already visited.
                //Find parent string by following first added chars
                List<Character> suffix = findSuffix(curr, new ArrayList<>());

                //concat current string and suffix
                char[] parentWord = new char[index + suffix.size()];
                for(int i = 0; i < parentWord.length; i++) {
                    if(i < index) {
                        parentWord[i] = s[i];
                    } else {
                        parentWord[i] = suffix.get(i - index);
                    }
                }
                return parentWord;
            }
        }
        return null; //not really needed
        }

    private List<Character> findSuffix(Tode curr, List<Character> result) {
        result.add(curr.value);
        if (!curr.childs.isEmpty()) {
            return findSuffix(curr.childs.get(curr.firstChild), result);
        } else {
            return result;
        }
    }
}

class Tode {
    char value;
    Map<Character, Tode> childs;
    char firstChild;
    Boolean isLeaf;

    public Tode(char value) {
        this.value = value;
        childs = new HashMap<>();
        isLeaf = null;
        firstChild = '0';
    }
}
