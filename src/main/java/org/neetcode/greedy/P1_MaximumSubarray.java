package org.neetcode.greedy;

public class P1_MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int highestSum = nums[0];
        int sum = 0;

        for(int num: nums) {
            if(sum < 0) sum = 0;
            sum += num;
            if(sum > highestSum) highestSum = sum;
        }

        return highestSum;
    }

    public static void main(String[] args) {
        System.out.println(new P1_MaximumSubarray()
                .maxSubArray(new int[] {5,4,-1,7,8})
        );
        System.out.println(new P1_MaximumSubarray()
                .maxSubArray(new int[] {-1,0})
        );
        System.out.println(new P1_MaximumSubarray()
                .maxSubArray(new int[] {-1})
        );
        System.out.println(new P1_MaximumSubarray()
                .maxSubArray(new int[] {-2,-1})
        );
    }
}
