package org.neetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

class P8_LetterCombosOfPhoneNum {
    public List<String> letterCombinations(String digits) {
        if(digits.length() > 0) {
            this.digits = digits.toCharArray();
            prefix = new char[digits.length()];
            letterCombinations(0);
        }
        return res;
    }

    char[] prefix;
    char[] digits;
    private void letterCombinations(int first) {
        int currNum = digits[first] - '0';
        for(char letterForNum: letterMap[currNum].toCharArray()) {
            prefix[first] = letterForNum;
            if(first == digits.length - 1) {
                res.add(String.valueOf(prefix));
            } else {
                letterCombinations(first + 1);
            }
            prefix[first] = 0;
        }
    }

    List<String> res = new ArrayList<>();
    String[] letterMap = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public static void main(String[] args) {
        String s = "23";
        s = "2";
        System.out.println(new P8_LetterCombosOfPhoneNum().letterCombinations(s));
    }
}
