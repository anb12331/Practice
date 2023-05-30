package org.neetcode.graphs;

import java.util.*;

class P8_CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

       prereqMap = new HashMap<>();

        for(int[] prereqPair: prerequisites) {
            List<Integer> preReqs = prereqMap.get(prereqPair[0]);
            if (preReqs == null) {
                preReqs = new ArrayList<>();
                prereqMap.put(prereqPair[0], preReqs);
            }
            preReqs.add(prereqPair[1]);
        }

        for(Integer course: prereqMap.keySet()) {
            if(!dependMap.containsKey(course)) {
                Set<Integer> dependencies = new HashSet<>();
                if (!dfs(course, dependencies)) return false;
            }
        }

        return true;
    }

    private Map<Integer, List<Integer>> prereqMap;

    private Map<Integer, Set<Integer>> dependMap = new HashMap<>();

    private Set<Integer> ancestors = new HashSet<>();


    private boolean dfs(Integer course, Set<Integer> dependencies) {
        if(ancestors.contains(course)) {
            return false;
        } else if (dependMap.containsKey(course)) {
            /*
            Set<Integer> deps = dependMap.get(course);

            These conditions are not required as if ancestors were part of dependencies,
            this node would already have been visited!
            No Need to track Dependencies also, as if successfully completed, each
            course can be added to a 'Can Complete' set (dependMap) and checked against it

            if(ancestors.size() < deps.size()) {
                for(Integer a: ancestors) {
                    if(deps.contains(a)) return false;
                }
            } else {
                for(Integer d: deps) {
                    if(ancestors.contains(d)) return false;
                }
            }

            dependencies.addAll(deps);
            */
            return true;
        }
        List<Integer> preReqs = prereqMap.get(course);
        ancestors.add(course);
        if(preReqs != null)
            for(Integer preq: preReqs) {
                if(!dependMap.containsKey(preq)) {
                    if (!dfs(preq, dependencies)) return false;
//                    dependencies.add(preq);
                }
            }

        dependMap.put(course, null);
        ancestors.remove(course);

        return true;
    }

    public static void main(String[] args) {
        int[][] prereq;

        prereq = new int[][] {{1,0}}; // 2
        System.out.println(new P8_CourseSchedule().canFinish(2, prereq));
    }
}
