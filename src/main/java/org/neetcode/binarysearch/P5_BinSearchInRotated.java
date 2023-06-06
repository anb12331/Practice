package org.neetcode.binarysearch;

class P5_BinSearchInRotated {
    //4 5 6 7 8 0 1
    //7 8 0 1 2 3 4
    //      0
    public int search(int[] nums, int target) {
        int first = 0;
        int last = nums.length - 1;
        int mid = -1;
        boolean goLeft, goRight;

        while(first <= last) {
            goLeft = goRight = false;
            mid = (first + last) >>> 1;
            if(nums[mid] == target) {
                return mid;
            } else if(target > nums[mid]) {
                if(nums[last] >= nums[mid]) {
                    if(target > nums[last]) {
                        goLeft = true;
                    } else { //target <= last
                        goRight = true;
                    }
                } else { //last < nums[mid]
                    goRight = true;
                }
                //first = ++mid;
            } else { //target < nums[mid]
                if(nums[last] >= nums[mid]) {
                    goLeft = true;
                } else { //last < nums[mid]
                    if(target <= nums[last]) {
                        goRight = true;
                    } else {
                        goLeft = true;
                    }
                }
                //last = mid - 1;
            }
            if(goRight) {
                first = ++mid;
            } else if(goLeft) {
                last = mid - 1;
            }
        }

        return -1; //-mid - 1;
    }

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2}; //0
        int target = 0;
        nums = new int[] {4, 5, 6, 7, 8, 0, 1};
        target = 8;
        int[] nums2 = new int[] {7, 8, 0, 1, 2, 3, 4};
        target = 8;

//        System.out.println(new P5_BinSearchInRotated().search(nums, 8));
//        System.out.println(new P5_BinSearchInRotated().search(nums, 0));
//        System.out.println(new P5_BinSearchInRotated().search(nums2, 8));
//        System.out.println(new P5_BinSearchInRotated().search(nums2, 0));
        System.out.println(new P5_BinSearchInRotated().search(new int[] {1,3, 5}, 1));
    }

    private int binSearch(int[] nums, int target) {
        int first = 0;
        int last = nums.length - 1;
        int mid = -1;

        while(first <= last) {
            mid = (first + last) >>> 1;
            if(nums[mid] == target) {
                return mid;
            } else if(target > nums[mid]) {
                first = ++mid;
            } else { //target < nums[mid]
                last = mid - 1;
            }
        }

        return -mid - 1;
    }
}
