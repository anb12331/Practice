package org.neetcode.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class P3_LongestRepeatingReplacement {
    public int characterReplacement(String s, int k) {
        if(s.length() == 1) return 1;
        char[] chars = s.toCharArray();
        int len = chars.length;

        letterCounts = new HashMap<>();
        int start = 0, end = 1;
        increment(chars[start]);
        increment(chars[end]);
        int maxWindowLength = 0;

        char testLetter = chars[start];
        //ABCB , k = 2
        while(start < end) {
            int windowLength = end - start + 1;

            if(windowLength > k + letterCounts.get(testLetter)) { //windowLenght limit exceeded
                //If window length is invalid due to start increment, adding should not make a difference
                //as prev window length was VALID and 1 higher than curr. window length

                //before invalidating window, check valid window lengths for all other letters in window
                Integer testLetterCount = letterCounts.get(testLetter);
                char maxRepeatedLetter = testLetter;
                for(Character c: letterCounts.keySet()) { // O(26) complexity
                    if(letterCounts.get(c) > testLetterCount) {
                        maxRepeatedLetter = c;
                        testLetterCount = letterCounts.get(c);
                    }
                }
                if(maxRepeatedLetter != testLetter) {
                    testLetter = maxRepeatedLetter;
                    continue;
                } else {
                    if(testLetter == chars[start]) {
                        testLetter = chars[start + 1];
                    }
                    decrement(chars[start]);
                    start++;
                    //decrease windowLength by 1 as the prev increment of end was invalid
                    maxWindowLength = Math.max(windowLength - 1, maxWindowLength);
                }
            }

            if(end < len - 1) {
                end++;
                increment(chars[end]);
            } else {
                windowLength = end - start + 1;
                maxWindowLength = Math.max(windowLength, maxWindowLength);
                break;
            }


        }

        return maxWindowLength;
    }

    private Map<Character, Integer> letterCounts;

    private void increment(char testLetter) {
        Integer count = letterCounts.getOrDefault(testLetter, 0);
        letterCounts.put(testLetter, count + 1);
    }

    private void decrement(char testLetter) {
        letterCounts.put(testLetter, letterCounts.get(testLetter) - 1);
    }

    public static void main(String[] args) {
        String s = "ACBBB"; //2
        String s2 = "ABAB"; //2
        String s3 = "AABABBA"; //1
        String s4 = "AXDBBBJKLMN"; //4
        String s5 = "KRSCDCSONAJNHLBMDQGIFCPEKPOHQIHLTDIQGEKLRLCQNBOHNDQGHJPNDQPERNFSSSRDEQLFPCCCARFMDLHADJADAGNNSBNCJQOF"; //4

//        System.out.println(new P3_LongestRepeatingReplacement().characterReplacement(s, 2));
//        System.out.println(new P3_LongestRepeatingReplacement().characterReplacement(s2, 2));
//        System.out.println(new P3_LongestRepeatingReplacement().characterReplacement(s3, 1));
//        System.out.println(new P3_LongestRepeatingReplacement().characterReplacement(s4, 4));
        System.out.println(new P3_LongestRepeatingReplacement().characterReplacement(s5, 4));
    }
}
