package org.neetcode.greedy;

public class P2_JumpGame {
    public boolean canJump(int[] nums) {
        if(nums.length <= 1) return true;
        memo = new int[nums.length];
        return canJump(nums, nums.length - 1);
    }

    private int[] memo;

    private boolean canJump(int[] nums, int targetIndex) {
        if(targetIndex == 0) return true;
//        if(memo[targetIndex] > 0) return memo[targetIndex] == 2;
        int last = targetIndex;
        boolean res = false;
        for(int j = last - 1; j >= 0; j--) {
            if(nums[j] >= last - j) {
                if(memo[targetIndex] > 0) {
                    res = memo[targetIndex] == 2;
                } else {
                    res = canJump(nums, j);
                    memo[j] = res ? 2 : 1;
                }
                if(res) return res;
            }
        }
        memo[targetIndex] = 1;
        return false;
    }


    public static void main(String[] args) {
        System.out.println(new P2_JumpGame()
                .canJump(new int[] {3,2,1,0,4})
        );
        System.out.println(new P2_JumpGame()
                .canJump(new int[] {2,3,1,1,4})
        );
    }
}
