package org.neetcode.binarysearch;

class P1_BinarySearch {
    public int search(int[] nums, int target) {
        return search(nums, target, 0, nums.length);
    }

    private int search(int[] nums, int target, int start, int endEx) {
        if(nums[start] == target) return start;
        else if(nums[endEx - 1] == target) return endEx - 1;
        else if(start == endEx - 1 || start == endEx - 2)
            return -1; //exit if length is one or 2 and neither first or last elem matches

        int mid = (start + endEx - 1) / 2;
        if(target == nums[mid]) {
            return mid;
        } else if(target > nums[mid]) {
            return search(nums, target, mid, endEx);
        } else /*if(target < nums[mid])*/ {
            return search(nums, target, start, mid);
        }
    }

    public static void main(String[] args) {
        System.out.println(
                binarySearch_JavaArrays(new int[] {-1,0,3,5,9,12}, 11)
//                new P1_BinarySearch().search(new int[] {-1,0,3,5,9,12}, 2)
        );
    }

    public static int binarySearch_JavaArrays(int[] a, int key) {
        if (a.length == 0)
            return -1;
        return binarySearch_JavaArrays(a, 0, a.length - 1, key);
    }

    public static int binarySearch_JavaArrays(int[] a, int low, int hi, int key)
    {
        int mid = 0;
        while (low <= hi)
        {
            mid = (low + hi) >>> 1;
            final int d = a[mid];
            if (d == key)
                return mid;
            else if (d > key)
                hi = mid - 1;
            else
                // This gets the insertion point right on the last loop.
                low = ++mid;
        }
        return - 1;
    }
}
