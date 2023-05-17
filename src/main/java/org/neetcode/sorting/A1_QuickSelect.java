package org.neetcode.sorting;

import org.neetcode.heap.P4_KtLargestLinearTime;

import java.util.Arrays;

public class A1_QuickSelect {
    public int quickSelect(int[] nums, int k) {
        int n = nums.length;
        int left = 0, right = nums.length - 1;
        int ptr = 0;
        while(true) {
            ptr = partition(left, right, nums);
            if (ptr > n - k) right = ptr - 1;
            else if (ptr < n - k) left = ptr + 1;
            else if (ptr == n - k) break;
        }

        return nums[ptr];
    }

    public void quickSort(int[] nums) {
        int left = 0, right = nums.length - 1;
        quickSortRecur(left, right, nums);
    }

    private void quickSortRecur(int left, int right, int[] nums) {
        if(left < right) {
            int ptr = partition(left, right, nums);
            quickSortRecur(left, ptr - 1, nums);
            quickSortRecur(ptr + 1, right, nums);
        }
    }

    private int partition(int left, int right, int[] nums) {
        if(left < 0 || right >= nums.length || left > right) {
            System.out.println("Invalid inputs= left:" + left + " right:" + right);
        } else if (left == right) {
            return left;
        }
        int ptr = left;
        int pivot = nums[right];

        for(int i = left; i < right; i++) {
            if(nums[i] <= pivot) {
                swap(i, ptr, nums);
                ptr++;
            }
        }

        swap(ptr, right, nums);
        return ptr;
    }

    private void swap(int i, int ptr, int[] nums) {
        int temp = nums[i]; nums[i] = nums[ptr]; nums[ptr] = temp;
    }

    public static void main(String[] args) {
//        int[] nums = {3,2,3,1,2,4,5,5,6,7,7,8,2,3,1,1,1,10,11,5,6,2,4,7,8,5,6};
//        int[] nums = {3,4,5,1,2};
        int[] nums = {2,1};
        A1_QuickSelect a = new A1_QuickSelect();
//        System.out.println(a.quickSelect(nums, 3));
        a.quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }

}
