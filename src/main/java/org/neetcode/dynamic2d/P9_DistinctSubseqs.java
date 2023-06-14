package org.neetcode.dynamic2d;

class P9_DistinctSubseqs {
    public int numDistinct(String s, String t) {
        if(s.length() == t.length())
            return s.equals(t) ? 1 : 0;
        else if(s.length() < t.length())
            return 0;
        dp = new Integer[s.length()][t.length()];
        sc = s.toCharArray();
        tc = t.toCharArray();
        return countSubseq(s.length() - 1, t.length() - 1);
    }

    char[] sc;
    char[] tc;
    Integer[][] dp;

    private int countSubseq(int s1, int t1) {
        int res = 0;

        if(s1 >= -1 && t1 == -1) {
            return 1;
        } else if(s1 >= 0 && t1 >= 0 && s1 >= t1) {
            if(dp[s1][t1] != null) return dp[s1][t1];
            if(sc[s1] == tc[t1])
                res += countSubseq(s1 - 1, t1 - 1);
            res += countSubseq(s1 - 1, t1);
            dp[s1][t1] = res;
        }

        return res;
    }

    public static void main(String[] args) {
        String s = "rabbbit", t = "rabbit";
        s = "babgbag"; t = "bag";
        System.out.println(new P9_DistinctSubseqs().numDistinct(s, t));
    }
}
