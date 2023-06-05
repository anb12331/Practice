package org.neetcode.intervals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class P4_NonOverlapinInterval {
    public int eraseOverlapIntervals_working(int[][] intervals, Set<String> removed) {
        //Iterate throuh sorted intervals, removing last ending interval for each intersection
        int intervalsRemoved = 0;

        Arrays.sort(intervals, Comparator.comparingInt(arr -> arr[0]));

        int[] intervalFirst = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (firstIntervalwithinSecond(intervalFirst, intervals[i])) {
                //mark first interval to be removed
                intervalsRemoved++;
                // determine which interval to remove
                //remove the interval that ends last
                if (intervalFirst[1] > intervals[i][1]) {
                    removed.add(Arrays.toString(intervalFirst));
                    intervalFirst = intervals[i];
                } else {
                    removed.add(Arrays.toString(intervals[i]));
                }
            } else {
                intervalFirst = intervals[i];
            }
        }
        return intervalsRemoved;
    }

    public boolean firstIntervalwithinSecond(int[] intervalFirst, int[] intervalSecond) {
        return intervalSecond[0] < intervalFirst[1];
    }

    public static void main(String[] args) {
        int[][] intervals = {{1,2},{2,3},{3,4},{1,3}};
        intervals = new int[][]{{1,2},{1,2},{1,2}};
        try {
            String arr = new String(Files.readAllBytes(Paths.get("src/main/java/org/neetcode/intervals/arr")));
            intervals = parseArray(arr); //Ans: 9260
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Set<String> removedW = new HashSet<>();
        System.out.println(new P4_NonOverlapinInterval().eraseOverlapIntervals_working(intervals, removedW));


        Set<String> removedNw = new HashSet<>();
        System.out.println(new P4_NonOverlapinInterval().eraseOverlapIntervals_notWorking(intervals, removedNw));

        System.out.println("mine:" + removedNw.size() + " theirs: " + removedW.size());
        String[] remCopy = removedNw.toArray(new String[0]);
        for(String s: remCopy) {
            if(removedNw.contains(s) && removedW.contains(s)) {
                removedNw.remove(s);
                removedW.remove(s);
            }
        }

        String[] sortedNW = removedNw.toArray(new String[0]);
        Arrays.sort(sortedNW);

        String[] sortedW = removedW.toArray(new String[0]);
        Arrays.sort(sortedW);

        System.out.println("mine: " + Arrays.toString(sortedNW));
        System.out.println("theirs: " + Arrays.toString(sortedW));

    }

    private static int[][] parseArray(String string) {
        List<int[]> res = new ArrayList<>();
        Pattern p = Pattern.compile("\\[([^\\]]+)\\]");
        Matcher m = p.matcher(string);
        while (m.find()) {
            String tuple = m.group(1);
            String[] tupleArr = tuple.split(",\\s*");
            res.add(new int[] {Integer.parseInt(tupleArr[0]), Integer.parseInt(tupleArr[1])});
        }

        return res.toArray(new int[0][0]);
    }

    public int eraseOverlapIntervals_notWorking(int[][] intervals, Set<String> removed) {
        //Not working - Repeatedly removing max overlapping interval and adjusting overlap counts, till no overlaps left
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int[] combined = new int[intervals.length * 2];
        for(int i = 0; i < intervals.length; i++) {
            combined[i] = intervals[i][0];
            combined[intervals.length + i] = intervals[i][1];
        }
        Arrays.sort(combined);
        System.out.println(Arrays.toString(combined));

        this.intervals = intervals;
        int deletions = 0;
        overlapMap = (HashSet<Integer>[])new HashSet[intervals.length];

        int equalIntervals = 0;

        for(int i = 0; i < intervals.length; i++) {
            for(int j = i + 1; j < intervals.length; j++) {
                if(intervals[i][0] == intervals[j][0] && intervals[i][1] == intervals[j][1])
                    equalIntervals++;
                if (intersects(i, j)) {
                    addOverlapToMap(i, j);
                    addOverlapToMap(j, i);
                } else {
                    break;
                }
            }
        }

        System.out.println("equal: " + equalIntervals);

        int totalOvp = 0;

        for(Set<Integer> ovp: overlapMap) {
            if(ovp != null)
                totalOvp += ovp.size();
        }

        System.out.println("totalOverlaps:" + totalOvp);
        /**
         for(int i = 0; i < intervals.length; i++) {
         while(overlapMap[i] != null) {
         int currOvpSize = overlapMap[i].size();
         int maxOvpSize = 0;
         int maxOvpIndex = -1;
         int equalOverlap = i;
         for(int k: overlapMap[i]) {
         if(overlapMap[k].size() > currOvpSize) {
         if(overlapMap[k].size() > maxOvpSize) {
         maxOvpSize = overlapMap[k].size();
         maxOvpIndex = k;
         }
         } else if (overlapMap[k].size() == currOvpSize) {
         if(maxOvpIndex == -1) {
         if(k > equalOverlap) equalOverlap = k;
         }
         }
         }
         //                int maxIntr = overlapMap[i].stream().max(Comparator.comparingInt(o -> overlapMap[o].size())).get();
         if(maxOvpIndex == -1) {
         maxOvpIndex = equalOverlap;
         }
         deletions++;

         removeOverlapFromMap(maxOvpIndex);

         }
         }
         **/
        System.out.println(mapCount);
        while(mapCount > 0) {
            int maxOverlapCount = 0;
            int intrIndex = -1;
            for(int i = 0; i < overlapMap.length; i++) {
                if(overlapMap[i] != null) {
                    if(overlapMap[i].size() > maxOverlapCount) {
                        maxOverlapCount = overlapMap[i].size();
                        intrIndex = i;
                    }
//                    else if(overlapMap[i].size() == maxOverlapCount) {
//                        intrIndex = i;
//                    }
                }
            }

            if(deletions == 9259) {
                int debug = 1;
            }

//            int intrIndex = overlapMap.keySet().stream().max(Comparator.comparingInt(o -> overlapMap.get(o).size())).get();
            //.iterator().next();
            deletions++;
            removed.add(Arrays.toString(intervals[intrIndex]));
            removeOverlapFromMap(intrIndex);
            /*
            Set<Integer> currOverlaps = overlapMap.get(intrIndex);
//            Integer[] currOverlapsCopy = currOverlaps.toArray(new Integer[0]);
            int overlapIntrIndex = currOverlaps.stream().max(Comparator.comparingInt(o -> overlapMap.get(o).size())).get();
//            for(int overlapIntrIndex: currOverlapsCopy) {
                deletions++;
                if(currOverlaps.size() >= overlapMap.get(overlapIntrIndex).size()) {
                    //remove current intr
                    removeOverlapFromMap(intrIndex);
//                    break;
                } else {
                    //remove overlapping intr
                    removeOverlapFromMap(overlapIntrIndex);
                }
//            }

             */
        }

        return deletions;
    }

    private Set<Integer>[] overlapMap;

    private void addOverlapToMap(int firstIntr, int secondIntr) {
        Set<Integer> overlaps = overlapMap[firstIntr];
        if(overlaps == null) {
            overlaps = new HashSet<>();
            overlapMap[firstIntr] = overlaps;
            mapCount++;
        }
        overlaps.add(secondIntr);
    }

    int mapCount = 0;

    private void removeOverlapFromMap(int intervalIndex) {
        Set<Integer> overlaps = overlapMap[intervalIndex];
        for(Integer overlapIntrIndex: overlaps) {
            overlapMap[overlapIntrIndex].remove(intervalIndex);
            if(overlapMap[overlapIntrIndex].isEmpty()) {
                overlapMap[overlapIntrIndex] = null;
                mapCount--;
            }

        }
        overlapMap[intervalIndex] = null;
        mapCount--;
    }

    int[][] intervals;

    private boolean intersects(int firstIntr, int secondIntr) {
        return intervals[firstIntr][1] > intervals[secondIntr][0];
    }
}
