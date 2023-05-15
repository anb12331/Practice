package org.neetcode.binarysearch;

class P4_RotatedSortedArray {
    public int findMin(int[] nums) {
        if(nums.length == 1) return nums[0];
        int lo = 0, hi = nums.length - 1;

        int mid = 0;
        while(lo <= hi) {
            mid = (lo + hi) >>> 1;

            int midNum = nums[mid];

            if(isMin(mid, nums)) {
                System.out.println("mid break");
                break;
            } else if(isMin(lo, nums)) {
                mid = lo;
                System.out.println("lo break");
                break;
            } else if(isMin(hi, nums)) {
                mid = hi;
                System.out.println("hi break");
                break;
            } else if(midNum < nums[lo]) { //mid Num is lower than first num, array is unsorted on left side
                hi = mid - 1;
            } else if(midNum > nums[hi]) { //mid Num is greater than first num, array is unsorted on right side
                lo = mid + 1;
            }
         }
        return nums[mid];
    }

    private boolean isMin(int mid, int[] nums) {
        int leftNum = mid == 0 ? nums[nums.length - 1] : nums[mid - 1];
        int rightNum = mid == nums.length - 1 ? nums[0] : nums[mid + 1];
        return  nums[mid] < leftNum && nums[mid] < rightNum;
    }

    public static void main(String[] args) {
        int[] nums = {3,4,5,1,2};
        int[] nums2 = {4,5,6,7,0,1,2};
        int[] nums3 = {11,13,15,17};

        System.out.println(new P4_RotatedSortedArray().findMin(nums));

        System.out.println(new P4_RotatedSortedArray()
                .findMin(nums2)
        );
        System.out.println(new P4_RotatedSortedArray()
                .findMin(nums3)
        );
    }

    private static int bs(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;

        int mid = 0;
        while(lo <= hi) {
            mid = (lo + hi) >>> 1;

            int midNum = nums[mid];

            if(midNum == target) {
                return mid;
            } else if(target > midNum) {
                lo = mid + 1;
            } else if(target < midNum) {
                hi = --mid;
            }
        }

        return -mid - 1;

    }

}
