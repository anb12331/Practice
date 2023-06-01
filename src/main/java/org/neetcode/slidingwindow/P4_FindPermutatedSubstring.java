package org.neetcode.slidingwindow;

import java.util.Arrays;

class P4_FindPermutatedSubstring {
    public boolean checkInclusion(String s1, String s2) {
        Integer[] tempS1Map = new Integer[26];

        for(char c: s1.toCharArray()) {
            if(tempS1Map[c - 'a'] != null)
                tempS1Map[c - 'a']++;
            else tempS1Map[c - 'a'] = 1;
        }

        char[] s2Chars = s2.toCharArray();
        int windowStart = 0, windowEnd = 0;

        while(windowEnd < s2.length()) {
            char curr = s2Chars[windowEnd];
            if(tempS1Map[curr - 'a'] != null && tempS1Map[curr - 'a'] > 0) {
                tempS1Map[curr - 'a']--;
                if(windowEnd - windowStart == s1.length() - 1) {
                    return true;
                } else {
                    windowEnd++;
                }
            } else {
                char charRemovedFromSlidWindow = s2Chars[windowStart];
                if(tempS1Map[charRemovedFromSlidWindow - 'a'] != null)
                    tempS1Map[charRemovedFromSlidWindow - 'a']++;
                windowStart++;

                if(windowStart > windowEnd) {
                    windowEnd++;
                }
            }
        }

        return false;
    }



    public static void main(String[] args) {
        String s1 = "ab", s2 = "eidbaooo";
        s2 = "eidboaoo";
//        s1 = "adc"; s2 = "dcda";
        System.out.println(new P4_FindPermutatedSubstring().checkInclusion(s1, s2));
    }
}
