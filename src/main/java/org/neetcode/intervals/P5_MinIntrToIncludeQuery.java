package org.neetcode.intervals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class P5_MinIntrToIncludeQuery {
    public int[] minInterval(int[][] intervals, int[] queries) {
        long t1 = System.currentTimeMillis();
        Map<Integer, List<Integer>> rightMap = new HashMap<>();
        for(int i = 0; i < intervals.length; i++) {
            int[] intr = intervals[i];
            List<Integer> leftList = rightMap.get(intr[1]);
            if(leftList == null) {
                leftList = new ArrayList<>();
                rightMap.put(intr[1], leftList);
            }
            leftList.add(intr[0]);
        }
        System.out.println("interval map: " + (time() - t1));

        Long timer = time();
        List<Integer> rightList = new ArrayList<>(rightMap.keySet());
        Collections.sort(rightList);
        System.out.println("right sort: " + -(timer - time()));

        for(int i = 0; i < rightList.size(); i++) {
            int currRightVal = rightList.get(i);
            List<Integer> leftList = rightMap.get(currRightVal);
            Collections.sort(leftList);
            if(i > 0) {
                int prevRightVal = rightList.get(i - 1);
                List<Integer> prevLeftList = rightMap.get(prevRightVal);
                int prevRightMinIntr = prevRightVal - prevLeftList.get(prevLeftList.size() - 1);
                int k;
                for(k = leftList.size() - 1; k >= 0; k++) {
                    if(leftList.get(k) <= prevRightVal) {
                        if(currRightVal - leftList.get(k) < prevRightMinIntr) {
                            //keep val in list
                        } else {
                            break;
                        }
                    }
                }
                rightMap.put(currRightVal, leftList.subList(k + 1, leftList.size()));
            }
        }

        timer = System.currentTimeMillis();
        for(List<Integer> leftList: rightMap.values()) {
            Collections.sort(leftList);
        }
        System.out.println("left sorts: " + -(timer - time()));



        timer = time();
        int[] res = new int[queries.length];
        for(int i =0; i < queries.length; i++) {
            int q = queries[i];
            int rightValIndex = binarySearch(rightList, q);
            if (rightValIndex < 0) {
                rightValIndex = -(rightValIndex + 1);
                if (rightValIndex >= rightList.size()) {
                    res[i] = -1; continue;
                }
            }

            int smallestIntr = Integer.MAX_VALUE;
            boolean found = false;
            for(int r = rightValIndex; r < rightList.size(); r++) {
                int rightVal = rightList.get(r);

                int smallestPossibleIntr = rightVal - q + 1;
                if(smallestIntr <= smallestPossibleIntr) break;

                List<Integer> leftList = rightMap.get(rightVal);

                int smallestCurrentIntr = rightVal - leftList.get(leftList.size() - 1) + 1;
                if(smallestIntr <= smallestCurrentIntr) continue;

                int leftValIndex = binarySearch(leftList, q);
                if (leftValIndex < 0) {
                    leftValIndex = -(leftValIndex + 1);
                    leftValIndex--; //decrementing to get index just "Less Than' target
                    if (leftValIndex < 0) {
//                        res[i] = -1;
                        continue;
                    }
                }
                found = true;
                int leftVal = leftList.get(leftValIndex);
                smallestIntr = Math.min(rightVal - leftVal + 1, smallestIntr);
            }

            res[i] = found ? smallestIntr : -1;
        }
        System.out.println("search: " + -(timer - time()));
        System.out.println("total: " + -(t1 - time()));
        return res;
    }

    private long time() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        int[][] intervals = {{1,4},{2,4},{3,6},{4,4}};
        intervals = new int[][]{{2,3},{2,5},{1,8},{20,25}};
        intervals = new int[][]{{4,5},{5,8},{1,9},{8,10},{1,6}};
        int[] q = {2,3,4,5};
        q = new int[]{2,19,5,22};
        q = new int[]{7,9,3,9,3};

        Integer[] q1 = null;

        try {
            String arr = new String(Files.readAllBytes(Paths.get("src/main/java/org/neetcode/intervals/arr3")));
//            intervals = parseArray(arr); //Ans: 9260

            String queriesStr = new String(Files.readAllBytes(Paths.get("src/main/java/org/neetcode/intervals/arr4")));
            String[] s1 = queriesStr.split(",\\s*"); //Ans: 9260
//            q = new int[s1.length]; for(int i = 0; i < s1.length; i++) q[i] = Integer.parseInt(s1[i]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Arrays.toString(
                new P5_MinIntrToIncludeQuery().minInterval(intervals, q)
        ));
//        System.out.println(binarySearch(new Integer[] {4,6}, 2));
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

    private static int binarySearch(List<Integer> nums, int target) {
        //If no match, add one to result and multiply by -1
        //returns index just 'Greater Than' target
        //If target greater than max, returns max index + 1
        int first = 0;
        int last = nums.size() - 1;

        int mid = 0;
        while(first <= last) {
            mid = (first + last) >>> 1;

            if(nums.get(mid) == target) {
                return mid;
            } else if(target < nums.get(mid)) {
                last = mid - 1;
            } else if (target > nums.get(mid)) {
                first = ++mid;
            }
        }

        return -mid - 1;
    }
}
