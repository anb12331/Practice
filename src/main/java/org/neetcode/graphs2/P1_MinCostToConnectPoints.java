package org.neetcode.graphs2;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class P1_MinCostToConnectPoints {
    public int minCostConnectPoints(int[][] points) {
        /**
         * TODO: Optimization - min dist can be recorded while adding to heap
         * Check P2_NetworkDelayTime for a better implementation of Djikstra's
         */
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        Set<Integer> visited = new HashSet<>();
        int cost = 0;
        visited.add(0);
        int[] p = points[0];

        while(visited.size() < points.length) {
            for(int i = 0; i < points.length; i++) {
                int[] p2 = points[i];
                if(visited.contains(i) || p==p2) continue;
                int dist = Math.abs(p[0]-p2[0]) + Math.abs(p[1]-p2[1]);
                minHeap.add(new int[] {dist, i});
            }
            int[] minEdge = minHeap.poll();
            while(visited.contains(minEdge[1])) {
                minEdge = minHeap.poll();
            }
            visited.add(minEdge[1]);
            p = points[minEdge[1]];
            cost += minEdge[0];
        }

        return cost;
    }

    public static void main(String[] args) {
        int[][] pts = {{0,0},{2,2},{3,10},{7,0},{5,2}};
        System.out.println(new P1_MinCostToConnectPoints().minCostConnectPoints(pts));
    }
}
