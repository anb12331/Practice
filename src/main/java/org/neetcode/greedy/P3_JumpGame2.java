package org.neetcode.greedy;

public class P3_JumpGame2 {
    public int canJump0(int[] nums) {
        if(nums.length <= 1) return 0;
        memo = new int[nums.length];
//        for(int i = 0; i < memo.length; i++) memo[i] = -2;
        return canJump0(nums, nums.length - 1);
    }

    private int[] memo;

    private static int calc = 0;

    private int canJump0(int[] nums, int targetIndex) {
        if(targetIndex == 0) return 0;
        calc++;
        int last = targetIndex;
        int minSteps = -1;
        for(int j = last - 1; j >= 0; j--) {
            if(nums[j] >= last - j) {
                int resSteps;
                if(memo[j] != 0) {
                    resSteps = memo[j] != -1 ? 1 + memo[j] : -1;
                } else {
                    int jumps = canJump0(nums, j);
                    resSteps = jumps > -1 ? 1 + jumps : -1;
                }
                if(resSteps >= 0 && (minSteps < 0 || resSteps < minSteps)) minSteps = resSteps;
            }
        }
        memo[targetIndex] = minSteps;
        return minSteps;
    }

    private int canJump(int[] nums) {
        if(nums.length == 1) return 0;
        int jumps = 0;

        int curr = 0, maxJumpLen = nums[0];

        while(curr + maxJumpLen < nums.length - 1) {
            int maxReach = -1;
            int maxReachPos = -1;
            for(int i = 1; i <= maxJumpLen; i++) {
                int testPos = curr + i;
                int testPosJumpLen = nums[curr + i];
                int testPosJumpReach = testPos + testPosJumpLen;
                if(testPosJumpReach > maxReach) {
                    maxReach = testPosJumpReach;
                    maxReachPos = testPos;
                }
            }
            if(maxReachPos == -1) {
                return -1;
            } else {
                curr = maxReachPos;
                maxJumpLen = nums[maxReachPos];
                jumps++;
            }
        }

        jumps++;

        return jumps;
    }


    public static void main(String[] args) {
        System.out.println(new P3_JumpGame2()
                .canJump(new int[] {3,2,1,0,4})
        );
        System.out.println(new P3_JumpGame2()
                .canJump(new int[] {2,3,1,1,4}));

    }
}
