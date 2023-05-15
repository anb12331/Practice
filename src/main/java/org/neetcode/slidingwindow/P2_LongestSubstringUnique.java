package org.neetcode.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P2_LongestSubstringUnique {
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();

        Map<Character, Integer> lastPosn = new HashMap<>();
        int start = 0;
        int maxSubstrLen = 0;

        for(int i = 0; i < chars.length; i++) {
            char c = chars[i];
            Integer lastRepeatPosn = lastPosn.get(c);
            if (lastRepeatPosn != null && lastRepeatPosn >= start) {
                maxSubstrLen = Math.max(maxSubstrLen, i - start); //excluding current index in prev. substring
                start = lastRepeatPosn + 1;
            }
            lastPosn.put(c, i);
        }
        maxSubstrLen = Math.max(maxSubstrLen, chars.length - start);
        return maxSubstrLen;
    }

    public static void main(String[] args) {
        System.out.println(new P2_LongestSubstringUnique().lengthOfLongestSubstring("abacabcdef"));
    }
}
