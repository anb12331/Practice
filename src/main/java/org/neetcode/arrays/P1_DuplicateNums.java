package org.neetcode.arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P1_DuplicateNums {
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num: nums) {
            if(set.contains(num)) {
                return true;
            }
            set.add(num);

        }

        return false;
    }

    public static boolean isAnagram(String s, String t) {
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();

        if(sc.length != tc.length) {
            return false;
        }

        Map<Character, Integer> wordCount = new HashMap<>();

        for(int i = 0; i < sc.length; i++) {
            char charS = sc[i];
            Integer initialCount = wordCount.getOrDefault(charS, 0);
            wordCount.put(charS, initialCount + 1);

            char charT = tc[i];
            Integer TCount = wordCount.getOrDefault(charT, 0);
            wordCount.put(charT, TCount - 1);
        }

        for(Integer count: wordCount.values()) {
            if(count != 0) return false;
        }

        return true;
    }

    public boolean isAnagram_BestSol(String s, String t) {
        if (s.length() != t.length())
            return false;

        int[] count = new int[128];

        for (final char c : s.toCharArray())
            ++count[c];

        for (final char c : t.toCharArray())
            if (--count[c] < 0)
                return false;

        return true;
    }

    public static void main(String[] args) {
//        System.out.println(containsDuplicate(new int[] {1,2,3,4}));
        System.out.println(isAnagram("amon", "monl"));
    }

}
