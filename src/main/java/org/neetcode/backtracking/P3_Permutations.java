package org.neetcode.backtracking;

import java.util.*;

class P3_Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = permuteRecursive(nums, new HashSet<>());
        return res;
    }

    Map<Integer, List<List<Integer>>> permutCache = new HashMap<>();

    public List<List<Integer>> permuteRecursive(int[] nums, Set<Integer> excludeIndexes) {
        if(excludeIndexes.size() == nums.length) return null;

        List<List<Integer>> currList = getCache(excludeIndexes);
        if(currList != null) return currList;

        currList = new ArrayList<>();
        for(int d = 0; d < nums.length; d++) {
            if(!excludeIndexes.contains(d)) {
                Set<Integer> newExcl = new HashSet<>(excludeIndexes);
                newExcl.add(d);
                List<List<Integer>> lowerList = permuteRecursive(nums, newExcl);
                if(lowerList == null) {
                    lowerList = new ArrayList<>();
                    lowerList.add(new ArrayList<>());
                }
                for(List<Integer> list: lowerList) {
                    list.add(nums[d]);
                    currList.add(list);
                }
            }
        }
        addCache(currList, excludeIndexes);
        return currList;
    }

    int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
    private List<List<Integer>> getCache(Set<Integer> excludeIndexes) {
        List<List<Integer>> newRes = null;
        /*
        Integer primeSum = 0;

        for(Integer elem: excludeIndexes) {
            elem += 10;
            primeSum += elem*primes[elem];
        }

        List<List<Integer>> res = permutCache.get(primeSum);
        if(res != null) {
            newRes = new ArrayList<>(res.size());
            for(List<Integer> ls: res) {
                newRes.add(new ArrayList<>(ls));
            }
        }
         */
        return newRes;
    }

    private void addCache(List<List<Integer>> currList, Set<Integer> excludeIndexes) {
        /*
        Integer primeSum = 0;

        for(Integer elem: excludeIndexes) {
            elem += 10;
            primeSum += elem*primes[elem];
        }

        permutCache.put(primeSum, currList);
         */
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(new P3_Permutations()
                .permute(nums)
        );
    }
}
