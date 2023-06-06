package org.neetcode.dynamic1d;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

class P11_LongestIncrSubseq {
    public int lengthOfLIS(int[] nums) {
        this.nums = nums;
        sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);
        cache = new int[nums.length];

        int maxRes = 0;
        for(int i = nums.length - 1; i >= 0; i--)
            maxRes = Math.max(lengthOfLIS(i), maxRes);

        return maxRes;
    }
    int[] sorted;
    private int lengthOfLIS(int start) {
        if(cache[start] > 0) return cache[start];

        int firstNum = nums[start];
        int lastGreaterNum = Integer.MAX_VALUE;
        int maxRes = 1; //0
        for(int i = start + 1; i < nums.length; i++) {
            if(nums[i] > firstNum && nums[i] < lastGreaterNum) {
                int res = 1 + lengthOfLIS(i); //don't add 1 here
                maxRes = Math.max(res, maxRes);
                lastGreaterNum = nums[i];
            }
        }
        //maxRes += 1 //add one below
        cache[start] = maxRes;
        return maxRes;
    }

    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.comparingInt(o -> -o));

    private int lengthOfLIS_nlogN(int start) {
        if(cache[start] > 0) return cache[start];

        int firstNum = nums[start];
        int lastGreaterNum = Integer.MAX_VALUE;
        int maxRes = 1; //0
        for(int i = start + 1; i < nums.length; i++) {
            if(nums[i] > firstNum && nums[i] < lastGreaterNum) {
                int res = 1 + lengthOfLIS(i); //don't add 1 here
                maxRes = Math.max(res, maxRes);
                lastGreaterNum = nums[i];
            }
        }
        //maxRes += 1 //add one below
        cache[start] = maxRes;
        maxHeap.add(maxRes);
        return maxRes;
    }

    int[] cache;

    int[] nums;

    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};
        // 2 4 3 5 1  0  7  8 6
        // 2 3 5 7 9 10 18 20 101
        //            2  2  1 1
        System.out.println(new P11_LongestIncrSubseq().lengthOfLIS(nums));
    }
}
