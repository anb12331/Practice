package org.neetcode.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P3_TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> prevDiffs = new HashMap<>();
        int index = 0;
        for(int num: nums) {
            Integer existingDiffIndex = prevDiffs.get(num);
            if(existingDiffIndex != null) {
                return new int[] {existingDiffIndex, index};
            }
            prevDiffs.put(target - num, index);
            index++;
        }

        return null;
    }

    public static void main(String[] args) {
        int[] res = twoSum(new int[] {3,3}, 6);
        System.out.println(res[0] + ", " + res[1]);
    }

}
