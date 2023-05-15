package org.neetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class P4_SubsetsWithDuplicates {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> allSubsets = new ArrayList<>((int)Math.pow(nums.length, 2));
        allSubsets.add(new ArrayList<>());
        List<List<Integer>> subsetsOfPrevDuplicate = null;

        for(int i = 0; i < nums.length; i++) {
            List<List<Integer>> newlyGeneratedSubsets = new ArrayList<>(allSubsets.size());

            for(List<Integer> currSubset: allSubsets) {
                List<Integer> newSubset = new ArrayList<>(currSubset);
                newSubset.add(nums[i]);
                newlyGeneratedSubsets.add(newSubset);
            }

            if(subsetsOfPrevDuplicate != null) {
                for(List<Integer> prevDuplicatesSubset: subsetsOfPrevDuplicate) {
                    List<Integer> newSubset = new ArrayList<>(prevDuplicatesSubset);
                    newSubset.add(nums[i]);
                    newlyGeneratedSubsets.add(newSubset);
                }
            }

            if(i < nums.length - 1 && nums[i] == nums[i + 1]) {
                subsetsOfPrevDuplicate = newlyGeneratedSubsets;
            } else {
                allSubsets.addAll(newlyGeneratedSubsets);
                if(subsetsOfPrevDuplicate != null) subsetsOfPrevDuplicate = null;
            }
        }

        return allSubsets;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,2,2};
        System.out.println(new P4_SubsetsWithDuplicates().subsetsWithDup(nums));
    }
}
