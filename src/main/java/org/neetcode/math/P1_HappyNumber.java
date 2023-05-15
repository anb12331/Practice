package org.neetcode.math;

import java.util.HashSet;
import java.util.Set;

class P1_HappyNumber {
    public boolean isHappy(int n) {
        return calcIsHapppy(n) == 1;
    }

    private Set<Integer> calcHistory = new HashSet<>();

    private int calcIsHapppy(int n) {
        String numStr = String.valueOf(n);
        int sum = 0;

        for(char c: numStr.toCharArray()) {
            int d = c - '0';
            sum += d*d;
        }
        if(calcHistory.contains(sum))
            return 0;
        calcHistory.add(sum);

        return sum == 1 ? 1 : calcIsHapppy(sum);
    }

    public static void main(String[] args) {
        System.out.println(new P1_HappyNumber()
                .isHappy(2)
        );
    }
}
