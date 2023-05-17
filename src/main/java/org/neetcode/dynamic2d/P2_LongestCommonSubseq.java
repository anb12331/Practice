package org.neetcode.dynamic2d;

import java.util.HashSet;
import java.util.Set;

public class P2_LongestCommonSubseq {
    public int longestCommonSubsequence(String text1, String text2) {
        this.text1 = text1.toCharArray();
        this.text2 = text2.toCharArray();
        Set<Character> charSet1 = new HashSet<>();

        for(char c: this.text1) charSet1.add(c);

        int matchingChars = 0;
        for(char c: this.text2) {
            if(charSet1.contains(c)) {
                matchingChars++;
            }
        }

        if(matchingChars == 0) return 0;

        dp = new int[text1.length()][text2.length()];


        m = text1.length();
        n = text2.length();

        for(int i = m - 1; i >= 0; i--)
            for(int j = n - 1; j >= 0; j--) {
                getSubseqLen(i, j);
            }

        return dp[0][0];
    }

    private int m,n;
    private char[] text1, text2;
    private int[][] dp;

    private int getSubseqLen(int i, int j) {
        if(i < 0 || i >= m || j < 0 || j >= n) return 0;
        if(dp[i][j] > 0) return dp[i][j];

        int subseqLen;

        if(text1[i] == text2[j]) {
            subseqLen = 1 + getSubseqLen(i + 1, j + 1);
        } else {
            subseqLen = Math.max(getSubseqLen(i + 1, j), getSubseqLen(i, j + 1));
        }

        dp[i][j] = subseqLen;
        return subseqLen;
    }

    public static void main(String[] args) {
        String t1 = "abcde", t2 = "ace";
        System.out.println(new P2_LongestCommonSubseq().longestCommonSubsequence(t1, t2));
    }
}
