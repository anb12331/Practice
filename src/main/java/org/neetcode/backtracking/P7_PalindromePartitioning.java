package org.neetcode.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class P7_PalindromePartitioning {
    public List<List<String>> partition(String s) {
        this.s = s;
        len = s.length();

        buildPalinMap();
        partition(0);

        return res;
    }

    private Map<Integer, List<Integer>> palinStartingFrom = new HashMap<>();
    String s;
    int len;
    private List<String> prefix = new ArrayList<>();
    private List<List<String>> res = new ArrayList<>();

    private void partition(int first) {
        for(int last: palinStartingFrom.get(first)) {
            prefix.add(s.substring(first, last + 1));
            if(last == len - 1) {
                List<String> newRes = new ArrayList<>(prefix);
                res.add(newRes);
            } else { // last < len - 1
                partition(last + 1);
            }
            prefix.remove(prefix.size() - 1);
        }
    }

    private void buildPalinMap() {
        char[] sc = s.toCharArray();
        for(int i = 0; i < len; i++) palinStartingFrom.put(i, new ArrayList<>());
        for(int i = 0; i < len; i++) {
            palinStartingFrom.get(i).add(i);

            int first = i - 1;
            int last = i + 1;
            while(first >= 0 && last < len) {
                if(sc[first] == sc[last]) {
                    palinStartingFrom.get(first).add(last);
                    first--;last++;
                } else {
                    break;
                }
            }

            first = i;
            last = i + 1;
            while(first >= 0 && last < len) {
                if(sc[first] == sc[last]) {
                    palinStartingFrom.get(first).add(last);
                    first--;last++;
                } else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        String s = "aab";
        s="a";
        System.out.println(new P7_PalindromePartitioning().partition(s));
    }
}


