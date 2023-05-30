package org.neetcode.dynamic1d;

class P6_PalindromicSubstr {
    public int countSubstrings(String s) {
        char[] chars = s.toCharArray();
        int len = s.length();
        int count = 0;
        int first, last;

        for(int i = 0; i < len; i++) {
            //every single letter is a palindrome
            count++;

            //odd palindromes
            first = i - 1;
            last = i + 1;

            while(first >= 0 && last < len) {
                if(chars[first] == chars[last]) {
                    count++;
                } else {
                    break;
                }
                first--; last++;
            }

            //even palindromes
            first = i;
            last = i + 1;

            while(first >= 0 && last < len) {
                if(chars[first] == chars[last]) {
                    count++;
                } else {
                    break;
                }
                first--; last++;
            }


        }

        return count;
    }

    public static void main(String[] args) {
        String s;
        s = "fdsklf";

        System.out.println(new P6_PalindromicSubstr().countSubstrings(s));
    }
}
