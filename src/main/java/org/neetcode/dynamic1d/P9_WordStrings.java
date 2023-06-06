package org.neetcode.dynamic1d;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class P9_WordStrings {
    public boolean wordBreak(String s, List<String> wordDict) {
        minWordLen = wordDict.get(0).length();
        for(String w: wordDict) {
            dict.add(w);
            wordLengths.add(w.length());
            if(w.length() < minWordLen) minWordLen = w.length();
        }
        this.s = s;
        chars = s.toCharArray();
        len = s.length();
        isValidWord = new Boolean[s.length()];
//        isValidWord[s.length()] = true;

        return wordBreak(0);
    }

    private boolean wordBreak(int first) {
        if(first == len) return true;
        if(isValidWord[first] != null) return isValidWord[first];

        boolean res = false;
        for(int i = first; i < len; i++) {
            if(wordLengths.contains(i + 1 - first)
                    && dict.contains(s.substring(first, i + 1))) {
                if(wordBreak(i + 1)) {
                    res = true;
                    break;
                } else {
                    res = false;
                }
            }
        }
        isValidWord[first] = res;
        return res;
    }
    Boolean[] isValidWord;
    String s;
    int len;
    Set<String> dict = new HashSet<>();
    int minWordLen = 0;
    char[] chars;
    Set<Integer> wordLengths = new HashSet<>();



    public static void main(String[] args) {
        String s = "applepenapple";
        List<String> wordDict = Arrays.asList("apple", "pen");

        s = "catsandog";
        wordDict = Arrays.asList("cats","dog","sand","and","cat");

        System.out.println(new P9_WordStrings().wordBreak(s, wordDict));
    }
}
