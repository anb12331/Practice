package org.neetcode.twopointers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P2_TwoSum {

    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> diffs = new HashMap<>();

        int lowest = numbers[0];

        for(int i = 0; i < numbers.length; i++) {
            int curr = numbers[i];

            if(diffs.containsKey(curr)) {
                return new int[] {diffs.get(curr) + 1, i + 1};
            }

            int diff = target - curr;
            diffs.put(diff, i);

            if(diff < lowest) {
                break;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new P2_TwoSum()
                .twoSum(new int[] {-1,0}, -1)));
    }

    //Using binary search
    public int[] twoSum_bestSolution(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length-1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            int middle = left + (right - left) / 2;
            if (sum == target) {
                return new int[] {left + 1, right + 1};
            }
            if (sum > target) {
                right = numbers[left] + numbers[middle] >= target
                        ? middle
                        : right - 1;
            }
            else {
                left = numbers[middle] + numbers[right] <= target
                        ? middle
                        : left + 1;
            }
        }
        return null;
    }
}
