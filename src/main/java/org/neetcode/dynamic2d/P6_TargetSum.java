package org.neetcode.dynamic2d;

class P6_TargetSum {
    public int findTargetSumWays(int[] nums, int target) {
        sum = 0;
        for(int num: nums) sum += num;

        if(Math.abs(target) > sum) return 0;

        dp = new int[nums.length][sum + 1];

        for(int i = 0; i < nums.length; i++) {
            for(int j = 0; j <= sum; j++) {
                if(i == 0 && (j == nums[i])) {
                    dp[i][j] = nums[i] == 0 ? 2 : 1;
                    continue;
                }
                int currTarget = j;
                int prevNumPlus = currTarget - nums[i];
                int prevNumMinus = currTarget + nums[i];

                dp[i][j] = dp(i - 1, prevNumPlus) + dp(i - 1, prevNumMinus);
            }
        }
        if(target < 0) target = -target;
        return dp[nums.length - 1][target];
    }

    int sum;
    int[][] dp;
    private int dp(int i, int j) {
        if(j < 0) {
            return dp(i, -j);
        }
        if(i < 0 || j < 0 || j > sum)
            return 0;
        else
            return dp[i][j];
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1};
        int target = 3;

        System.out.println(new P6_TargetSum().findTargetSumWays(nums, target));
    }
}
