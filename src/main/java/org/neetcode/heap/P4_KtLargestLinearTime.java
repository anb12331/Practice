package org.neetcode.heap;

import java.util.Arrays;

public class P4_KtLargestLinearTime {
    public int findKthLargest(int[] nums, int k) {
        this.nums = nums;
        int n = nums.length;
        int pivotIndex = partition(nums, 0, n);
        int res = 0;

        while(true) {
            if (pivotIndex == n - k) {
                res = nums[pivotIndex];
                break;
            } else if (pivotIndex < n - k) {
                pivotIndex = partition(nums, pivotIndex + 1, nums.length);
            } else {
                pivotIndex = partition(nums, 0, pivotIndex);
            }
        }

        System.out.println(Arrays.toString(nums));

        return res;
    }

    private int partition(int[] nums, int start, int endExcl) {
        int pivot = nums[endExcl - 1];
        int pivotIndex = endExcl - 1;

        for(int i = start; i < endExcl; i++) {
            if(nums[i] <= pivot) {
                if(i > pivotIndex) {
                    swap(i, pivotIndex);
                    pivotIndex = i;
                }
            } else if (nums[i] > pivot) {
                if(i < pivotIndex) {
                    swap(i, pivotIndex);
                    pivotIndex = i;
                }
            }
        }

        return pivotIndex;
    }

    int[] nums;

    private void swap(int i, int pivotIndex) {
        int temp = nums[i];
        nums[i] = nums[pivotIndex];
        nums[pivotIndex] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,3,1,2,4,5,5,6,7,7,8,2,3,1,1,1,10,11,5,6,2,4,7,8,5,6};
        System.out.println(new P4_KtLargestLinearTime().findKthLargest(nums, 2));
    }
}
