package org.neetcode.linkedlist;

class P9_FindDuplicateNumber {
    int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = -1;

        while(slow != fast) {
            if(fast == -1) fast = 0;
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        int meetingIndex = slow;
        int start = 0;
        //Using Floyd's algorithm to find loop start point.
        //Start of loop is the duplicate number, as atleast 2 indexes point to it
        while(start != meetingIndex) {
            start = nums[start];
            meetingIndex = nums[meetingIndex];
        }

        return start;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,4,2,2};
//        nums = new int[] {3,1,3,4,2};
        System.out.println(new P9_FindDuplicateNumber().findDuplicate(nums));
    }
}
