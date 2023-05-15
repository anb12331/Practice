package org.neetcode.dynamic1d;

import java.util.ArrayList;
import java.util.List;

public class P3_HouseRobber {
    public int rob(int[] nums) {
        memo1 = new int[nums.length][nums.length];
        return robRecursive(nums, 0, nums.length - 1);
    }

    int[][] memo1;
    public int robRecursive(int[] nums, int start, int end) {
        if(start > end) return 0;

        int cached = memo1[start][end];
        if(cached > 0) return cached - 1;

        int len = end - start + 1;
        int res = 0;
        if(len >= 2) {
            res = Math.max(
                    nums[start] + robRecursive(nums, start + 2, end),
                    robRecursive(nums, start + 1, end)
            );
        } else if (len == 1) {
            res = nums[start];
        }

        if(start <= end)
            cacheRes(memo1, start, end, res);
        return res;
    }

    private void cacheRes(int[][] memo, int start, int end, int res) {
        memo[start][end] = res + 1;
    }

    public static void main(String[] args) {
        System.out.println(new P3_HouseRobber().rob(new int[] {1,2,3,1}));
        System.out.println(new P3_HouseRobber().rob(new int[] {2,7,9,3,1}));
    }

    public int rob_bestSol(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        int robWithOutIncludingLastHouse = 0, robWithIncludingLastHouse = 0;

        for (int n : nums) {
            int temp = Math.max(
                    robWithOutIncludingLastHouse + n,
                    robWithIncludingLastHouse
            );
            robWithOutIncludingLastHouse = robWithIncludingLastHouse;
            robWithIncludingLastHouse = temp;
        }
        return robWithIncludingLastHouse;
    }
}
