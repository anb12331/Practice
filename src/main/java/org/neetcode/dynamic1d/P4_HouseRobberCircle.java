package org.neetcode.dynamic1d;

class P4_HouseRobberCircle {
    public int rob(int[] nums) {
        if(nums.length == 1) return nums[0];
        else if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int prevHouseRobbed = 0, prev2HouseRobbed = 0;

        for(int i = 0; i <  nums.length - 1; i++) {
            int n = nums[i];
            int temp = Math.max(n + prev2HouseRobbed, prevHouseRobbed);
            prev2HouseRobbed = prevHouseRobbed;
            prevHouseRobbed = temp;
        }

        int withFirstHouse = prevHouseRobbed;
        prevHouseRobbed = prev2HouseRobbed = 0;

        //with last house
        for(int i = 1; i <  nums.length; i++) {
            int n = nums[i];
            int temp = Math.max(n + prev2HouseRobbed, prevHouseRobbed);
            prev2HouseRobbed = prevHouseRobbed;
            prevHouseRobbed = temp;
        }
        int withLastHouse = prevHouseRobbed;

        return Math.max(withLastHouse, withFirstHouse);
    }

    public static void main(String[] args) {
        int[] nums = {2,3,2};
        int[] nums2 = {1,2,3,1};
        int[] nums3 = {1,2,3};
        System.out.println(new P4_HouseRobberCircle().rob(nums));
        System.out.println(new P4_HouseRobberCircle().rob(nums2));
        System.out.println(new P4_HouseRobberCircle().rob(nums3));
    }
}
