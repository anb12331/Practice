package org.neetcode.twopointers;

import java.util.ArrayList;
import java.util.List;

public class P1_palindrome {
    public boolean isPalindrome(String s) {
        List<Character> cleanedString = new ArrayList<>();

        for(char c: s.toCharArray()) {
            if(Character.isLetterOrDigit(c)) {
                cleanedString.add(Character.toLowerCase(c));
            }
        }

        int len = cleanedString.size();
        for(int i = 0; i < len/2; i++) {
            if(cleanedString.get(i) != cleanedString.get(len - i - 1)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new P1_palindrome().isPalindrome("A man, a plan, a canal: Panama"));
    }
}
