package org.neetcode.dynamic1d;

class P10_MaxProductSubarray {
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < nums.length; i++) {
            int product = 1;
            for(int j = i; j < nums.length; j++) {
                product = product * nums[j];
                max = Math.max(product, max);
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,-2,4};
        nums = new int[]{-2,0,-1};
        System.out.println(new P10_MaxProductSubarray().maxProduct(nums));
    }

    public int maxProduct_bestSol(int[] nums) {
        int max=Integer.MIN_VALUE,cp=1;
        for(int i=0;i<nums.length;i++) {
            cp*=nums[i];
            max=Math.max(max,cp);
            if(cp==0)
            {
                cp=1;
            }
        }
        cp=1;
        for(int i=nums.length-1;i>=0;i--) {
            cp*=nums[i];
            max=Math.max(max,cp);
            if(cp==0)
            {
                cp=1;
            }
        }
        return max;
    }
}
