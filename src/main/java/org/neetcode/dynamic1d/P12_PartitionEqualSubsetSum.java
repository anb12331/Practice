package org.neetcode.dynamic1d;

import java.util.HashMap;
import java.util.Map;

class P12_PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {

        int sum = 0;
        for(int num: nums) sum += num;
        if(sum % 2 != 0) return false;
        int halfSum = sum/2;
        dp = (HashMap<Integer, Boolean>[])new HashMap[nums.length];
        this.nums = nums;

        return canPartition(0, halfSum);
    }

    Map<Integer, Boolean>[] dp;

    private boolean canPartition(int start, int targetSum) {
        if(start == nums.length) return false;
        if(targetSum == nums[start]) {
            System.out.println(nums[start]);
            return true;
        }
        if(dp[start] != null && dp[start].containsKey(targetSum))
            return dp[start].get(targetSum);
        boolean res = false;

        if(canPartition(start + 1, targetSum - nums[start])) {
            System.out.println(nums[start]);
            res = true;
        } else if (canPartition(start + 1, targetSum)) {
            res = true;
        }

        if(dp[start] == null) dp[start] = new HashMap<>();
        dp[start].put(targetSum, res);

        return res;
    }

    int[] nums;

    public static void main(String[] args) {
        int[] nums = {1,5,11,5};
        System.out.println(new P12_PartitionEqualSubsetSum().canPartition(nums));
    }
}
