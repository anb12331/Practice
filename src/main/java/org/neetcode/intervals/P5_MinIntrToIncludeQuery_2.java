package org.neetcode.intervals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class P5_MinIntrToIncludeQuery_2 {
    public int[] minInterval(int[][] intervals, int[] queries) {
        //Groups intr by end point, then sorts each group by start point
        //BinSearch for end-point, then binSearch for nearest start point in current & following end-point groups
        //Very different from Neetcode solution but (almost) passes all Leetcode test cases
        long t1 = System.currentTimeMillis();
        System.out.println("all interval sort: " + (time() - t1));

        t1 = time();
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

        timer = System.currentTimeMillis();
        for(int i = 0; i < rightList.size(); i++) {
            int currRightVal = rightList.get(i);
            List<Integer> leftList = rightMap.get(currRightVal);
            Collections.sort(leftList);
        }
       System.out.println("left sorts: " + -(timer - time()));


        timer = time();
        int[] res = new int[queries.length];
        Map<Integer, Integer> resMap = new HashMap<>();


        Map<Integer, Set<Integer>> queryPositionMap = new HashMap<>();

        for(int i = 0; i < queries.length; i++) {
            Set<Integer> qIndexList = queryPositionMap.get(queries[i]);
            if(qIndexList == null) {
                qIndexList = new HashSet<>();
                queryPositionMap.put(queries[i], qIndexList);
            }
            qIndexList.add(i);
        }

        Arrays.sort(queries);

        int r;
        int rightValIndex = binarySearch(rightList, queries[0], 0);
        if (rightValIndex < 0) {
            rightValIndex = -(rightValIndex + 1);
            if (rightValIndex >= rightList.size()) {
                rightValIndex--;
            }
        }

        int prevVal = rightValIndex;
        int reuses = 0;
        for(int i =0; i < queries.length; i++) {
            int q = queries[i];
            if(resMap.containsKey(q)) {
                res[i] = resMap.get(q);
                reuses++;
                continue;
            }


            rightValIndex = binarySearch(rightList, q, prevVal);
            if (rightValIndex < 0) {
                rightValIndex = -(rightValIndex + 1);
                if (rightValIndex >= rightList.size()) {
                    rightValIndex--;
                }
            }
            prevVal = rightValIndex;


            int smallestIntr = Integer.MAX_VALUE;
            boolean found = false;
            int[] finalIntr = new int[3];
            for(r = rightValIndex; r < rightList.size(); r++) {
                int rightVal = rightList.get(r);

                if(q > rightVal) {
                    continue;
                }

                int smallestPossibleIntr = rightVal - q + 1;
                if(smallestIntr <= smallestPossibleIntr) break;

                List<Integer> leftList = rightMap.get(rightVal);

                int smallestCurrentIntr = rightVal - leftList.get(leftList.size() - 1) + 1;
                if(smallestIntr <= smallestCurrentIntr) continue;

                int leftValIndex = binarySearch_list(leftList, q, true);
                if (leftValIndex < 0) {
                    leftValIndex = -(leftValIndex + 1);
                    leftValIndex--; //decrementing to get index just "Less Than' target
                    if (leftValIndex < 0) {
                        continue;
                    }
                }
                found = true;
                int leftVal = leftList.get(leftValIndex);
                smallestIntr = Math.min(rightVal - leftVal + 1, smallestIntr);
                finalIntr[0] = leftVal;
                finalIntr[1] = rightVal;
            }
            r--;

            if(!found) {
                res[i] = -1; continue;
            }

            res[i] = smallestIntr;
            resMap.put(q, smallestIntr);
        }
        System.out.println("reused:" + reuses);
        System.out.println("search: " + -(timer - time()));
        System.out.println("total: " + -(t1 - time()));

        int[] newRes = new int[res.length];

        for(int i = 0; i < queries.length; i++) {
            Set<Integer> qIndexList = queryPositionMap.get(queries[i]);
            int qIndex = qIndexList.iterator().next();
            newRes[qIndex] = res[i];
            qIndexList.remove(qIndex);
        }

        return newRes;
    }

    private long time() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        int[][] intervals = {{1,4},{2,4},{3,6},{4,4}};
        intervals = new int[][]{{2,3},{2,5},{1,8},{20,25}};
        intervals = new int[][]{{4,5},{5,8},{1,9},{8,10},{1,6}};
        intervals = new int[][]{{9,9},{6,6},{9,10},{1,5},{1,5}};
        int[] q = {2,3,4,5};
        q = new int[]{2,19,5,22};
        q = new int[]{7,9,3,9,3};
        q = new int[]{7,1,9,8,1};

        Integer[] q1 = null;

        try {
//            String arr = new String(Files.readAllBytes(Paths.get("src/main/java/org/neetcode/intervals/arr3")));
            String arr = new String(Files.readAllBytes(Paths.get("src/main/java/org/neetcode/intervals/arr5")));
            intervals = parseArray(arr); //

//            String queriesStr = new String(Files.readAllBytes(Paths.get("src/main/java/org/neetcode/intervals/arr4")));
            String queriesStr = new String(Files.readAllBytes(Paths.get("src/main/java/org/neetcode/intervals/arr6")));
            String[] s1 = queriesStr.split(",\\s*"); //Ans: 9260
            q = new int[s1.length]; for(int i = 0; i < s1.length; i++) q[i] = Integer.parseInt(s1[i]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int[] res = new P5_MinIntrToIncludeQuery_2().minInterval(intervals, q);

        if(res.length < 20)
            System.out.println(Arrays.toString(res));

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

    private static int binarySearch(List<Integer> nums, int target, int seed) {
        //If no match, add one to result and multiply by -1
        //returns index just 'Greater Than' target
        //If target greater than max, returns max index + 1
        if(nums.get(seed) == target) return seed;

        int last = nums.size() - 1;
        int first = Math.max(0, 2*seed - last);

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

    private static int binarySearch_list(List<Integer> nums, int target, boolean startNearEnd) {
        //If no match, add one to result and multiply by -1
        //returns index just 'Greater Than' target
        //If target greater than max, returns max index + 1
        int first = 0;
        int last = nums.size() - 1;

        int mid = -1;
        while(first <= last) {
            int nextMid = (first + last) >>> 1;
            if(mid == -1) {
                mid = nextMid;
                mid = (mid + last) >>> 1;
            } else {
                mid = nextMid;
            }

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
