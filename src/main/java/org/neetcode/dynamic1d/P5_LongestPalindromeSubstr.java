package org.neetcode.dynamic1d;

import java.util.HashMap;
import java.util.Map;

public class P5_LongestPalindromeSubstr {
    public String longestPalindrome(String s) {
        if(s.length() == 1) return s;
        int len = s.length();
        palindromeCache = new Boolean[len][len];
        char[] chars = s.toCharArray();
        int[] longestPalidrome = {0,0};
        int longestLen = 0;
        for(int subStrLen = 1; subStrLen <= len; subStrLen++)
            for(int i = 0; i < len; i++) {
                if(i + subStrLen <= len) {
                    if (isPalindrome(chars, i, i + subStrLen - 1) && subStrLen > longestLen) {
                        longestPalidrome = new int[]{i, i + subStrLen - 1};
                        longestLen = subStrLen;
                    }
                }
            }

        return s.substring(longestPalidrome[0], longestPalidrome[1] + 1);
    }

    Boolean[][] palindromeCache;

    private boolean isPalindrome(char[] chars, int start, int endIncl) {
        if(start == endIncl) return true;
        if(start > endIncl) {
            throw new RuntimeException("invald start and end " + start + " " + endIncl);
        }
        Boolean res = palindromeCache[start][endIncl];
        if(res != null)
            return  res;

        int len = endIncl - start + 1;
        res = chars[start] == chars[endIncl];

        if(res && len > 3)
            res = isPalindrome(chars, start + 1, endIncl - 1);

        if(res && len > 3) {
            int debug = 1;
        }

        palindromeCache[start][endIncl] = res;

        return res;
    }

    public static void main(String[] args) {
        Long timer = System.currentTimeMillis();
        String s1 = "babaddtattarrattatddetartrateedredividerb";
        String s2 = "cbbd";
        String s = "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth";
        String s4 = "addta"; // abbax, aabba
        System.out.println(new P5_LongestPalindromeSubstr().longestPalindrome(s));
        System.out.println(new P5_LongestPalindromeSubstr().longestPalindrome(s1));
        System.out.println(new P5_LongestPalindromeSubstr().longestPalindrome(s2));
        System.out.println(new P5_LongestPalindromeSubstr().longestPalindrome(s4));
        System.out.println(System.currentTimeMillis() - timer);
    }

    //Find palindromes by expanding from center, for each letter of string
    public String longestPalindrome_bestSol(String s) {
        int strLength = s.length();

        if (strLength < 2) {
            return s;
        }

        int resultLength = 0;
        String result = "";

        for (int i = 0; i < s.length(); i++) {
            //Odd length
            int left = i, right = i;
            while (
                    left >= 0 &&
                            right < s.length() &&
                            s.charAt(left) == s.charAt(right)
            ) {
                if ((right - left + 1) > resultLength) {
                    result = s.substring(left, right + 1);
                    resultLength = right - left + 1;
                }
                left -= 1;
                right += 1;
            }

            // even length
            left = i;
            right = i + 1;
            while (
                    left >= 0 &&
                            right < s.length() &&
                            s.charAt(left) == s.charAt(right)
            ) {
                if ((right - left + 1) > resultLength) {
                    result = s.substring(left, right + 1);
                    resultLength = right - left + 1;
                }
                left -= 1;
                right += 1;
            }
        }

        return result;
    }
}
