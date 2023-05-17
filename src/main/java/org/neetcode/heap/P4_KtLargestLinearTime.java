package org.neetcode.heap;

import org.neetcode.sorting.A1_QuickSelect;

import java.util.Arrays;

public class P4_KtLargestLinearTime {
    public int findKthLargest(int[] nums, int k) {
        A1_QuickSelect quickSelect = new A1_QuickSelect();
        return quickSelect.quickSelect(nums, k);
    }

    public static void main(String[] args) {
        int[] nums = {3,2,3,1,2,4,5,5,6,7,7,8,2,3,1,1,1,10,11,5,6,2,4,7,8,5,6};
        System.out.println(new P4_KtLargestLinearTime().findKthLargest(nums, 2));
    }
}
