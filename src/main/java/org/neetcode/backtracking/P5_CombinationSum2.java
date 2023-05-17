package org.neetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P5_CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        this.target = target;
        res = new ArrayList<>();
        curr = new ArrayList<>();
        Arrays.sort(candidates);
        int prev = candidates[0] + 1;
        for(int i = 0; i < candidates.length; i++) {
            if (candidates[i] != prev)
                backtrack(candidates, i, 0);
            prev = candidates[i];
        }
        return res;
    }

    int target;
    List<List<Integer>> res;
    List<Integer> curr;

    private void backtrack(int[] candidates, int start, int currSum) {
        int num = candidates[start];
        currSum += num;
        if(currSum == target) {
            List<Integer> newCurr = new ArrayList<>(curr);
            newCurr.add(num);
            res.add(newCurr);
        } else if (currSum > target) {
            return;
        }

        curr.add(num);
        int prev = 0;
        for(int i = start + 1; i < candidates.length; i++) {
            if(prev == 0 || candidates[i] != prev)
                backtrack(candidates, i, currSum);
            prev = candidates[i];
        }
        curr.remove(curr.size() - 1);
    }

    public static void main(String[] args) {
        int[] nums1 = {10,1,2,7,6,1,5};
        int[] nums = {2,5,2,1,2};
        System.out.println(new P5_CombinationSum2().combinationSum2_compact(nums1, 8));
    }

    private List<List<Integer>> combinationSum2_compact(int[] candidates, int target) {
        this.target = target;
        res = new ArrayList<>();
        curr = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack_compact(candidates, 0, 0);
        return res;
    }

    private void backtrack_compact(int[] candidates, int start, int currSum) {
        if(currSum == target) {
            List<Integer> newCurr = new ArrayList<>(curr);
            res.add(newCurr);
        } else if (currSum > target) {
            return;
        }

        int prev = 0;
        for(int i = start; i < candidates.length; i++) {
            if(prev != 0 && candidates[i] == prev) {
                continue;
            }
            curr.add(candidates[i]);
            backtrack_compact(candidates, i + 1, currSum + candidates[i]);
            curr.remove(curr.size() - 1);
            prev = candidates[i];
        }
    }
}
