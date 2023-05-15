package org.neetcode.math;

import java.util.Arrays;

public class P2_PlusOne {
    public int[] plusOne(int[] digits) {
        int lastI = digits.length - 1;
        int carryOver = 0;

        for (int i = lastI; i >= 0; i--) {
            if(carryOver > 0 || i == lastI /*firstTime*/) {
                if (digits[i] < 9) {
                    digits[i]++;
                    carryOver = 0;
                } else if (digits[i] == 9) {
                    digits[i] = 0;
                    carryOver = 1;
                } else {
                    throw new RuntimeException("Invalid Input. Should not be possible");
                }
            }
        }

        if(carryOver == 1) {
            int[] newDigits = new int[digits.length + 1];
            newDigits[0] = 1;
            for(int i = 0; i < digits.length; i++) newDigits[i + 1] = digits[i];
            return newDigits;
        } else
            return digits;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new P2_PlusOne()
                .plusOne(new int[] {9,9,9})
        ));
    }
}
