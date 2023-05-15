package org.neetcode.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class P2_MergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        List<int[]> newInts = new ArrayList<>();

        int [] curr = intervals[0];

        for(int i = 1; i < intervals.length; i++) {
            int[] ir = intervals[i];

            if(curr[1] >= ir[0]) {
                curr[0] = Math.min(curr[0], ir[0]);
                curr[1] = Math.max(curr[1], ir[1]);
            } else {
                newInts.add(curr);
                curr = ir;
            }
        }
        newInts.add(curr);

        return newInts.toArray(new int[][]{});
    }

    public static void main(String[] args) {
        int[][] nums = {{1,2},{3,6},{6,7},{8,13},{12,16}};
        print(new P2_MergeIntervals().merge(nums));
    }

    static void print(int[][] arrs) {
        List<String> out = new ArrayList<>();
        for(int[] arr: arrs) {
            out.add(Arrays.toString(arr));
        }
        System.out.println(out);
    }
}
