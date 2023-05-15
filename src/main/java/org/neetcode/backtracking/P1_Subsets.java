package org.neetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

class P1_Subsets {
    public List<List<Integer>> subsets0(int[] nums) {
        int subsetLen = (int)Math.pow(2, nums.length);
        List<List<Integer>> res = new ArrayList<>(subsetLen);
        for(int i = 0; i < subsetLen; i++) {
            List<Integer> subset = new ArrayList<>();
            boolean[] bits = toBinaryArray(i, nums.length);
            for(int j = 0; j < nums.length; j++) {
                if(bits[j]) {
                    subset.add(nums[j]);
                }
            }
            res.add(subset);
        }

        return res;
    }

    private boolean[] toBinaryArray(int n, int maxBits) {
        boolean[] bits = new boolean[maxBits];
        for (int i = maxBits - 1; i >= 0; i--) {
            bits[i] = (n & (1 << i)) != 0;
        }
        return bits;
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> currSubsets = new ArrayList<>((int)Math.pow(2, nums.length));
        currSubsets.add(new ArrayList<>());

        for(int i = 0; i < nums.length; i++) {
            for(List<Integer> currSubset: currSubsets) {
                List<Integer> newSubset = new ArrayList<>(currSubset);
                newSubset.add(nums[i]);
                currSubsets.add(newSubset);
            }
        }

        return currSubsets;
    }

    public static void main(String[] args) {
        System.out.println("Use 'for'-loop to avoid ConcurrentModificationException");

        System.out.println(new P1_Subsets()
                .subsets(new int[]{1, 2, 3}));

    }

    public List<List<Integer>> subsets_withBacktrackDFS(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        backtrack_OR_dfs(ans, 0, nums, list);
        return ans;
    }

    public void backtrack_OR_dfs(
            List<List<Integer>> ans,
            int start,
            int[] nums,
            List<Integer> list
    ) {
        if (start >= nums.length) {
            ans.add(new ArrayList<>(list));
        } else {
            // add the element and start the  recursive call
            list.add(nums[start]);
            backtrack_OR_dfs(ans, start + 1, nums, list);
            // remove the element and do the backtracking call.
            list.remove(list.size() - 1);
            backtrack_OR_dfs(ans, start + 1, nums, list);
        }
    }
}
