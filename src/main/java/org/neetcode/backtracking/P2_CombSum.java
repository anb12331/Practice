package org.neetcode.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class P2_CombSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        recursive(candidates, target, 0, new ArrayList<>(), 0, res);
        return res;
    }

    private void recursive(int[] nums, int target, int index, List<Integer> currCombin, int currSum, List<List<Integer>> validCombos) {
        if(index >= nums.length) return;
        int num = nums[index];
        recursive(nums, target, index + 1, new ArrayList<>(currCombin), currSum, validCombos);
        int diff = target - currSum;
        for(int i = 1; i <= diff/num; i++) {
            currCombin.add(num);
            currSum += num;
            if( currSum == target) {
                validCombos.add(currCombin);
            } else if(currSum > target) {
                break;
            } else if(currSum < target) {
                recursive(nums, target, index + 1, new ArrayList<>(currCombin), currSum, validCombos);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new P2_CombSum()
                .combinationSum(new int[] {2,3,5}, 8)
        );
    }

}
