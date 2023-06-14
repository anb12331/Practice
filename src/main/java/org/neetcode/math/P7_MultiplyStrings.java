package org.neetcode.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class P7_MultiplyStrings {
    public String multiply(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        List<Integer> res = new ArrayList<>(num1.length() + num2.length());
        int trailingZeros = 0;

        num1 = sb.append(num1).reverse().toString();
        sb.delete(0, num1.length());
        num2 = sb.append(num2).reverse().toString();
        sb.delete(0, num2.length());

        for(char d: num2.toCharArray()) {
            List<Integer> currRes = multiplyWithSingleNum(num1, d);
            res = add(res, currRes, trailingZeros);
            trailingZeros++;
        }

        boolean isLeadingZero = true;
        sb.ensureCapacity(res.size());
        for(int i = res.size() - 1; i >= 0; i--) {
            if(res.get(i) == 0 && isLeadingZero) {
                //skip leading zeros
            } else {
                isLeadingZero = false;
                sb.append(res.get(i));
            }
        }
        if(sb.length() == 0) sb.append(0);

        return sb.toString();
    }

    public List<Integer> multiplyWithSingleNum(String num1, char num2) {
        List<Integer> res = new ArrayList<>(num1.length() + 1);
        int[] carry = {0};
        for(char d1: num1.toCharArray()) {
            int product = ((int)d1 - '0')*((int)num2 - '0');
            product = addWithCarry(product, 0, carry);
            res.add(product);
        }

        if(carry[0] > 0) {
            res.add(carry[0]);
        }
//        Collections.reverse(res);
        return res;
    }

    public List<Integer> add(List<Integer> num1, List<Integer> num2, int trailingZeros) {
        int maxLen = Math.max(num1.size(), num2.size() + trailingZeros);
        List<Integer> res = new ArrayList<>(maxLen);
        int[] carry = {0};
        for(int i = 0; i < maxLen; i++) {
            int num1Digit = 0;
            if(i < num1.size()) num1Digit = num1.get(i);

            int num2Digit = 0;
            if(i >= trailingZeros && i < num2.size() + trailingZeros) {
                num2Digit = num2.get(i - trailingZeros);
            }

            int sum = addWithCarry(num1Digit, num2Digit, carry);
            res.add(sum);
        }
        if(carry[0] > 0) res.add(carry[0]);

//        Collections.reverse(res);
        return res;
    }

    private int addWithCarry(int num1, int num2, int[] carry) {
        int sum = num1 + num2 + carry[0];
        int newCarry = 0;
        if (sum >= 10) {
            newCarry = sum / 10;
            sum -= newCarry * 10;
        }
        carry[0] = newCarry;
        return sum;
    }


    public static void main(String[] args) {
        String num1 = "456", num2 = "123";
        System.out.println(new P7_MultiplyStrings().multiply(num1, num2));
    }
}
