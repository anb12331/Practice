package org.neetcode.intervals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class P5_MinIntr_BetterSoln {

    public int[] minInterval(int[][] intervals, int[] queries) {
        // book-keeping & sorting
        int numIntervals = intervals.length;
        int numQueries = queries.length;

        // Sort by start times
        Arrays.sort(intervals, (o1, o2) -> (o1[0] - o2[0]));

        Query[] sortedQueries = new Query[numQueries];
        for (int i = 0; i < numQueries; i++) sortedQueries[i] =
                new Query(i, queries[i]);

        Arrays.sort(
                sortedQueries,
                (q1, q2) -> (q1.queryTimeStamp - q2.queryTimeStamp)
        );

        // algorithm

        Comparator<int[]> comparator = new IntervalComparator();
        PriorityQueue<int[]> pq = new PriorityQueue<>(comparator);
        int idx = 0;

        for (Query query : sortedQueries) {
            // 1. Keep taking all those queries which have lower starting time than the query time and add them to priority queue
            while (
                    (idx < numIntervals) &&
                            (query.queryTimeStamp >= intervals[idx][0])
            ) {
                pq.add(intervals[idx]);
                idx++;
            }

            // 2. Keep removing the inconsistent intervals and get the min size interval from priority queue
            while (!pq.isEmpty() && (pq.peek()[1] < query.queryTimeStamp)) {
                pq.remove();
            }

            // Now, priority queue must have the consistent & smallest interval
            int ans = pq.isEmpty() ? -1 : IntervalComparator.getSize(pq.peek());
            query.setResult(ans);
        }

        // reconversion
        int[] results = new int[numQueries];
        for (Query query : sortedQueries) {
            results[query.index] = query.result;
        }

        return results;
    }

    public static void main(String[] args) {
        try {
            Long t = System.currentTimeMillis();
            String arr = new String(Files.readAllBytes(Paths.get("src/main/java/org/neetcode/intervals/arr3")));
//            String arr = new String(Files.readAllBytes(Paths.get("src/main/java/org/neetcode/intervals/arr5")));
            int[][] intervals = parseArray(arr); //

            String queriesStr = new String(Files.readAllBytes(Paths.get("src/main/java/org/neetcode/intervals/arr4")));
//            String queriesStr = new String(Files.readAllBytes(Paths.get("src/main/java/org/neetcode/intervals/arr6")));
            String[] s1 = queriesStr.split(",\\s*"); //Ans: 9260
            int[] q = new int[s1.length]; for(int i = 0; i < s1.length; i++) q[i] = Integer.parseInt(s1[i]);
            int[] res = new P5_MinIntr_BetterSoln().minInterval(intervals, q);
            System.out.println("Total time: " + (System.currentTimeMillis() - t));
            if(res.length < 20)
                System.out.println(Arrays.toString(res));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
}



class Query {

    int index;
    int queryTimeStamp;
    int result;

    public Query(int index, int queryTimeStamp) {
        this.index = index;
        this.queryTimeStamp = queryTimeStamp;
        this.result = -1; // initially store as -1
    }

    @Override
    public String toString() {
        return "[" + index + "," + queryTimeStamp + "," + result + "]";
    }

    public void setResult(int result) {
        this.result = result;
    }
}

class IntervalComparator implements Comparator<int[]> {

    public static int getSize(int[] interval) {
        return (interval[1] - interval[0] + 1);
    }

    @Override
    public int compare(int[] o1, int[] o2) {
        int o1Size = getSize(o1), o2Size = getSize(o2);
        if (o1Size != o2Size) {
            return (o1Size - o2Size);
        }
        return (o1[1] - o2[1]);
    }
}
