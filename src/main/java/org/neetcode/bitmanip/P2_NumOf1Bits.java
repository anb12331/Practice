package org.neetcode.bitmanip;

public class P2_NumOf1Bits {
    public int hammingWeight(int n) {
        int res = 0;
        String bits = Integer.toBinaryString(n);

        //can also use this
        Integer.bitCount(n);

        for(char bit: bits.toCharArray()) {
           if(bit == '1') res++;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new P2_NumOf1Bits()
                .hammingWeight(-3)
        );
    }
}
