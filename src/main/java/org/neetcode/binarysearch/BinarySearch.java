package org.neetcode.binarysearch;

class BinarySearch {
    private static int binarySearch_JavaArrays(int[] a, int low, int hi, int key)
    {
        //If no match, add one to result and multiply by -1
        //returns index just 'Greater Than' target
        //If target greater than max, returns max index + 1
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
        return -mid - 1;
    }

    /**
     * If no match, add one to result and multiply by -1
     *  -> returns index just 'Greater Than' target
     *  -> If target greater than max, returns max index + 1
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearch(int[] nums, int target) {
        return binarySearch_JavaArrays(nums, 0, nums.length - 1, target);
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,7,9};
        System.out.println(binarySearch(nums, 5));
        System.out.println(binarySearch(nums, 4) + 1);
        System.out.println(binarySearch(nums, 8) + 1);
        System.out.println(binarySearch(nums, 10) + 1);
        System.out.println(binarySearch(nums, 0) + 1);
    }
}
