package org.neetcode.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P1_InsertInterval {

    public int[][] insert0(int[][] intervals, int[] newInterval) {

        int start = newInterval[0], end = newInterval[1];

        if(intervals.length == 0) return new int[][]{newInterval};

        int startLoc = bs(intervals, start, 0);
        int newStart, newEnd;
        boolean overLap = false;
        if(startLoc >= 0) {
            newStart = intervals[startLoc][0];
            overLap = true;
        } else {
            startLoc = -startLoc - 1;

            if(start > intervals[startLoc][0] && start <= intervals[startLoc][1]) {
                newStart = intervals[startLoc][0]; //start pt inside existing interval
                overLap = true;
            } else {
                newStart = start; //start pt outside interval
            }
        }

        int endLoc = bs(intervals, end, 1);


        if(endLoc >= 0) {
            newEnd = intervals[endLoc][1];
            overLap = true;
        } else {
            endLoc = -endLoc - 1;

            if(endLoc < intervals.length - 1 && end >= intervals[endLoc + 1][0]) {
                newEnd = intervals[endLoc + 1][1]; //end pt inside existing interval
                overLap = true;
            } else if(endLoc >= 0 && endLoc < intervals.length && end >= intervals[endLoc][0] && end <= intervals[endLoc][1]) {
                newEnd = intervals[endLoc][1];
            } else {
                newEnd = end; //start pt outside interval
            }
        }

        List<int[]> newInts = new ArrayList<>();
        int[] mergedInterval = new int[] {newStart, newEnd};
        boolean added = false;

        for(int[] interval: intervals) {
            if(interval[1] >= newStart && interval[0] <= newEnd) {
                if(!added) {
                    newInts.add(mergedInterval);
                    added = true;
                }
            } else {
                if(interval[0] >= newStart) {
                    if(!added) {
                        newInts.add(mergedInterval);
                        added = true;
                    }
                }
                newInts.add(interval);
            }
        }

        if(!added) {
            newInts.add(mergedInterval);
        }

        return newInts.toArray(new int[][]{});
    }

    private int bs(int[][] nums, int target, int subIndex) {
        int lo = 0, hi = nums.length - 1;

        int mid = 0;

        while(lo <= hi) {
            mid = (lo + hi) >>> 1;
            int midNum = nums[mid][subIndex];
            if(midNum == target) {
                return mid;
            } else if(target > midNum ){
                lo = mid + 1;
            } else if(target < midNum) {
                hi = --mid;
            }
        }

        return mid < 0 ? mid : -mid - 1;
    }


    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> newInts = new ArrayList<>();
        //Can be further optimized by recording elem indexes which are merged, and
        //excluding them in a single array copy operation

        int[] merged = new int[2];
        boolean added = false;

        for(int[] ir: intervals) {
            if(added) {
                newInts.add(ir);
                continue;
            }
            int compared = compareAndMergeLeft(newInterval, ir);
            if(compared < 0) {
                newInts.add(ir);
            } else if (compared > 0) {
                newInts.add(newInterval);
                newInts.add(ir);
                added = true;
            } else {
                //Do nothing, continue comparing & merging with next intervals
            }
        }

        if(!added) newInts.add(newInterval);

        return newInts.toArray(new int[][]{});
    }

    private int compareAndMergeLeft(int[] ir_new, int[] ir) {
        if(ir_new[1] < ir[0]) {
            return 1;
        } else if (ir_new[0] > ir[1]) {
            return -1;
        } else {
            ir_new[0] = Math.min(ir_new[0], ir[0]);
            ir_new[1] = Math.max(ir_new[1], ir[1]);
            return 0;
        }
    }

    public static void main(String[] args) {
        //[1,3]   [6,9]   [2,5]
        print((new P1_InsertInterval()
                .insert(new int[][]{{1,3}, {6,9}}, new int[]{2,5})
        ));

        print((new P1_InsertInterval()
                .insert(new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}}, new int[]{4,8})
        ));
        print((new P1_InsertInterval()
                .insert(new int[][]{{1,5}}, new int[]{0,3})
        ));
    }

    static void print(int[][] arrs) {
        List<String> out = new ArrayList<>();
        for(int[] arr: arrs) {
            out.add(Arrays.toString(arr));
        }
        System.out.println(out);
    }
}
