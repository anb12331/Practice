package org.neetcode.bitmanip;

class P1_SingleNumber {
    public int singleNumber(int[] nums) {
        int res = 0;

        for(int num: nums)
            res ^= num;

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new P1_SingleNumber()
                .singleNumber(new int[] {4,1,2,1,2}));
    }


}
