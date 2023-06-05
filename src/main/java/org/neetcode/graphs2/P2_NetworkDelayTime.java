package org.neetcode.graphs2;

import java.util.*;

class P2_NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int start) {
        /**
         * Optimization: Can also use MinHeap (PriorityQueue) instead of Queue,
         *               - True use of Djikstra's - potentially faster
         *               - Mark node as visited only when popped from MinHeap.
         *               - See 2nd solution
         *               - However, slower on LeetCode (Update - now faster after 2 optimizations)
         */
        List<int[]>[] nborMap = (ArrayList<int[]>[]) new ArrayList[n];
        int msgBrdTime = -1;

        for(int i = 0; i < times.length; i++) {
            int[] edge = times[i];
            List<int[]> nbors = nborMap[edge[0] - 1];
            if(nbors == null) {
                nbors = new ArrayList<>();
                nborMap[edge[0] - 1] = nbors;
            }

            nbors.add(edge);
        }

        Map<Integer, Integer> minTimeMap = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        Deque<int[]> q = new ArrayDeque<>();
        q.addLast(new int[]{start,0});
        minTimeMap.put(start, 0);

        while(!q.isEmpty()) {
            int[] popped = q.pollFirst();
            if(visited.contains(popped[0])) continue;
            if(popped[1] > minTimeMap.getOrDefault(popped[0], Integer.MAX_VALUE))
                continue;

            visited.add(popped[0]);
            List<int[]> nbors = nborMap[popped[0] - 1];
            if(nbors != null) {
                for(int[] nb: nbors) {
                    int nborTime = popped[1] + nb[2];
                    if(nborTime < minTimeMap.getOrDefault(nb[1], Integer.MAX_VALUE)) {
                        minTimeMap.put(nb[1], nborTime);
                        visited.remove(nb[1]);
                    }
                    q.addLast(new int[]{nb[1], nborTime});
                }
            }
        }

        if(minTimeMap.size() == n) {
            for (int time : minTimeMap.values()) {
                msgBrdTime = Math.max(time, msgBrdTime);
            }
        }

        return msgBrdTime;

    }

    public int networkDelayTime_minHeapFaster(int[][] times, int n, int start) {
        List<int[]>[] nborMap = (ArrayList<int[]>[]) new ArrayList[n];
        int msgBrdTime = -1;

        for (int i = 0; i < times.length; i++) {
            int[] edge = times[i];
            List<int[]> nbors = nborMap[edge[0] - 1];
            if (nbors == null) {
                nbors = new ArrayList<>();
                nborMap[edge[0] - 1] = nbors;
            }

            nbors.add(edge);
        }

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        Map<Integer, Integer> visited = new HashMap<>();

        minHeap.add(new int[] {0, start});

        while(!minHeap.isEmpty() && visited.size() < n) { //if all nodes in visited, exit as min value guaranteed for all
            int[] popped = minHeap.poll();
            if(visited.containsKey(popped[1])) continue;

            visited.put(popped[1], popped[0]);

            List<int[]> nbors = nborMap[popped[1] - 1];
            if(nbors != null) {
                for(int[] nb: nbors) {
                    if(!visited.containsKey(nb[1])) {
                        //isVisited check not strictly reqd. here as also checking on pop,
                        //but saves log(n) time to insert into minHeap, if visited
                        int nborTime = popped[0] + nb[2];
                        minHeap.add(new int[]{nborTime, nb[1]});
                    }
                }
            }
        }

        if(visited.size() == n) {
            for (int time : visited.values()) {
                msgBrdTime = Math.max(time, msgBrdTime);
            }
        }

        return msgBrdTime;
    }

    public static void main(String[] args) {
        int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
        int n = 4, k = 2;

        times = new int[][] {{4,2,76},{1,3,79},{3,1,81},{4,3,30},{2,1,47},{1,5,61},{1,4,99},{3,4,68},{3,5,46},{4,1,6},
                {5,4,7},{5,3,44},{4,5,19},{2,3,13},{3,2,18},{1,2,0},{5,1,25},{2,5,58},{2,4,77},{5,2,74}};
        n = 5; k = 3;

        System.out.println(new P2_NetworkDelayTime().networkDelayTime_minHeapFaster(times, n, k));
    }

}
