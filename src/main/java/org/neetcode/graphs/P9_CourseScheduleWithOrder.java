package org.neetcode.graphs;

import java.util.*;

public class P9_CourseScheduleWithOrder {
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        prereqMap = new HashMap<>();
        courseStatus = new int[numCourses];
        orderdedCoursesList = new ArrayList<>(numCourses);

        for(int i =0; i < numCourses; i++) {
            prereqMap.put(i, new ArrayList<>());
        }

        for(int[] prereqPair: prerequisites) {
            List<Integer> preReqs = prereqMap.get(prereqPair[0]);
            preReqs.add(prereqPair[1]);
        }

        for(Integer course: prereqMap.keySet()) {
            if(courseStatus[course] == 0) {
                if (!dfs(course)) return new int[] {};
            }
        }

        for(int i = 0; i < numCourses; i++) courseStatus[i] = orderdedCoursesList.get(i);

        return courseStatus;
    }

    private Map<Integer, List<Integer>> prereqMap;
    private int[] courseStatus;
    private List<Integer> orderdedCoursesList;

    private boolean dfs(Integer course) {
        if(courseStatus[course] > 0) {
            return true;
        } else if (courseStatus[course] == 0) {
            List<Integer> preReqs = prereqMap.get(course);
            courseStatus[course] = -1;
            if (preReqs != null)
                for (Integer preq : preReqs) {
                    if (courseStatus[preq] <= 0) {
                        if (!dfs(preq)) return false;
                    }
                }

            courseStatus[course] = 1;
        } else {
            return false;
        }

        orderdedCoursesList.add(course);

        return true;
    }

    public static void main(String[] args) {
        int[][] prereq;

        prereq = new int[][] {{1,0},{2,0},{3,1},{3,2}}; // 2

        prereq = new int[][] {{1,0},{0,3},{0,2},{3,2},{2,5},{4,5},{5,6},{2,4}}; // 7
//        prereq = new int[][] {{0,1},{1,0}}; // 2
        System.out.println(Arrays.toString(
                new P9_CourseScheduleWithOrder().findOrder(7, prereq)));
    }
}
