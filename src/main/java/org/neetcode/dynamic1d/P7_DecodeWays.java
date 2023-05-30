package org.neetcode.dynamic1d;

class P7_DecodeWays {
    public int numDecodings(String s) {
        this.s = s;
        len = s.length();
        dp = new int[len];
        chars = s.toCharArray();
        return numDecodings(0);
    }

    String s;
    int len;
    char[] chars;
    int[] dp;

    private int numDecodings(int start) {
        //Can also be solved by starting from 0 (bottom-up) & storing only 2 vals, i-1 & i-2, similar to Fibonacci
        if(start >= len) return 0;

        if(dp[start] > 0) return dp[start] - 1;

        int count = 0;

        if(decode(chars[start], '-')) {
            if(start == len - 1) count += 1;
            else count += numDecodings(start + 1);
        }

        if(start <= len - 2 && decode(chars[start], chars[start + 1])) {
            if(start == len -2) count += 1;
            else count += numDecodings(start + 2);
        }

        dp[start] = count + 1;
        return count;
    }

    private boolean decode(char firstChar, char secondChar) {
        if(firstChar > '0') {
            if(secondChar == '-') return true;
            else if (firstChar == '1'
                    || (firstChar == '2' && secondChar <= '6')) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        String s;
        s = "11106"; //2
        s = "12"; //2
        s = "06"; //0
        String s1 = "226"; //3
        s = "0";
        s = "10";
        System.out.println(new P7_DecodeWays().numDecodings(s1));
    }
}
