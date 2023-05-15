package org.neetcode.intervals;

import java.util.*;

public class P3_MeetingRoom2 {

    /**
     * @param intervals: an array of meeting time intervals
     * @return: the minimum number of conference rooms required
     */
    public int minMeetingRooms(List<Interval> intervals) {
        intervals.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return Integer.compare(o1.end, o2.end);
            }
        });
        Deque<Integer> rooms = new ArrayDeque<>();
        int maxRooms = 0;

        for (Interval ir : intervals) {
            if (rooms.isEmpty()) {
                rooms.addLast(ir.end);
                if (rooms.size() > maxRooms) maxRooms = rooms.size();
            } else {
                int earliestEndTime = rooms.peekFirst();
                if (ir.start >= earliestEndTime) {
                    rooms.pollFirst();
                    rooms.addLast(ir.end);
                } else {
                    rooms.addLast(ir.end);
                    if (rooms.size() > maxRooms) maxRooms = rooms.size();
                }
            }
        }

        return maxRooms;
    }


    public static void main(String[] args) {
        int[][] intArr = {{1, 3}, {8, 10}, {7, 8}, {9,15}, {2,6}};

        P3_MeetingRoom2 p = new P3_MeetingRoom2();
        List<Interval> irs = new ArrayList<>();
        for (int[] ir : intArr)
            irs.add(p.new Interval(ir[0], ir[1]));

        System.out.println(p.minMeetingRooms(irs));
    }


    public class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

}
