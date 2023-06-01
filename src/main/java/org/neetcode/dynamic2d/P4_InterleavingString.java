package org.neetcode.dynamic2d;

class P4_InterleavingString {
    /**
     * f(1,0) = s(1) == s1(1)
     * f(0,1) = s(1) == s2(1)
     *
     * for(i: 1 -> len(s1), j: 1 -> len(s2))
     * f(i,j) = [s(i+j) == s1(i) && f(i-1,j)] || [s(i+j) == s2(j) && f(i,j-1)]
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int s1Len = s1.length();
        int s2Len = s2.length();
        int sLen = s3.length();

        if(sLen != s1Len + s2Len) return false;
        if(sLen == 0) return true;
        if(s1Len == 0) return s2.equals(s3);
        else if(s2Len == 0) return s1.equals(s3);

        char[] s1c = s1.toCharArray();
        char[] s2c = s2.toCharArray();
        char[] sc = s3.toCharArray();

        boolean[][] dp = new boolean[s1Len + 1][s2Len + 1];

        for(int j = 0; j <= s2Len; j++) {
            for(int i = 0; i <= s1Len; i++) {
                if(i == 0 && j == 0) {
                    dp[i][j] = true;
                } else {
                    if(i > 0 && s1c[i - 1] == sc[i + j - 1] && dp[i - 1][j]) {
                        dp[i][j] = true;
                    }
                    // OR condition
                    if(j > 0 && s2c[j - 1] == sc[i + j - 1] && dp[i][j - 1]) {
                        dp[i][j] = true;
                    }
                }
            }
        }

        return dp[s1Len][s2Len];
    }



    public boolean isInterleave0(String s1, String s2, String s3) {
        int s1Len = s1.length();
        int s2Len = s2.length();
        int sLen = s3.length();

        if(sLen != s1Len + s2Len) return false;

        if(sLen == 0) return true;

        if(s1Len == 0) return s2.equals(s3);
        else if(s2Len == 0) return s1.equals(s3);

        boolean[][] dp = new boolean[s1Len + 1][s2Len + 1];
        /*
        dp[0][0] = true;
        dp[1][0] = s3.charAt(0) == s1.charAt(0);
        dp[0][1] = s3.charAt(0) == s2.charAt(0);
         */

        for(int i = 0; i <= s1Len; i++) {
            for(int j = 0; j <= s2Len; j++) {
                if(i == 0 && j == 0) {
                    dp[i][j] = true;
                } else {
                    dp[i][j] = (i > 0 && s1.charAt(i - 1) == s3.charAt(i + j - 1) && dp[i - 1][j]) ||
                            (j > 0 && s2.charAt(j - 1) == s3.charAt(i + j - 1) && dp[i][j - 1]);
                }
            }
        }

        return dp[s1Len][s2Len];
    }


    public static void main(String[] args) {
//        String s1 = "b", s2 = "a", s3 = "aa";
        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";
//        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc";

        System.out.println(new P4_InterleavingString().isInterleave0(s1, s2, s3));
    }
}
