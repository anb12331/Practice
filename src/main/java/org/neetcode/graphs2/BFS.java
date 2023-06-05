package org.neetcode.graphs2;

import java.util.*;

class BFS {
    public void bfs(int[][] edges, int nodes, int start) {
        List<Integer>[] nborMap = (ArrayList<Integer>[]) new ArrayList[nodes];

        for(int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            List<Integer> nbors = nborMap[edge[0]];
            if(nbors == null) {
                nbors = new ArrayList<>();
                nborMap[edge[0]] = nbors;
            }

            nbors.add(edge[1]);
        }

        Set<Integer> visited = new HashSet<>();

        Deque<Integer> q = new ArrayDeque<>();
        q.addLast(start);

        while(!q.isEmpty()) {
            Integer popped = q.pollFirst();
            if(visited.contains(popped)) continue;

            visited.add(popped);
            List<Integer> nbors = nborMap[popped];
            if(nbors != null) {
                for(Integer nb: nbors) {
                    if(!visited.contains(nb)) {
                        q.addLast(nb);
                    }
                }
            }
        }

        System.out.println(visited);
    }

    public static void main(String[] args) {
        int[][] edges = {{0,1},{1,2},{2,3},{3,4}};
        new BFS().bfs(edges, 5, 2);
    }
}
