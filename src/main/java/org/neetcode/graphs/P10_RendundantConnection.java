package org.neetcode.graphs;

import java.util.*;

class P10_RendundantConnection {
    public int[] findRedundantConnection(int[][] edges) {
        edgeIndexes = new int[edges.length + 1][edges.length + 1];
        for(int i = 0; i < edges.length; i++) {
            int[] e = edges[i];
            edgeIndexes[e[0]][e[1]] = i;
            edgeIndexes[e[1]][e[0]] = i;

            if(!nbors.containsKey(e[0]))
                nbors.put(e[0], new ArrayList<>());
            if(!nbors.containsKey(e[1]))
                nbors.put(e[1], new ArrayList<>());

            nbors.get(e[0]).add(e[1]);
            nbors.get(e[1]).add(e[0]);
        }

        int resIndex = dfs(edges[0][0], -1);
        return edges[resIndex];
    }

    Set<Integer> visitedInCurrDfs = new HashSet<>();
    Map<Integer, List<Integer>> nbors = new HashMap<>();
    Deque<Integer> stack = new ArrayDeque<>();
    int[][] edgeIndexes;

    private int dfs(int start, int parent) {
        if(visitedInCurrDfs.contains(start)) {
            int popped = -1;
            int maxEdgeIndex = -1;
            int prev = start;
            while(popped != start) {
                popped = stack.pollFirst();
                int edgeIndex = edgeIndexes[prev][popped];
                maxEdgeIndex = Math.max(maxEdgeIndex, edgeIndex);
                prev = popped;
            }
            return maxEdgeIndex;
        }

        visitedInCurrDfs.add(start);
        stack.addFirst(start);

        if(nbors.containsKey(start)) {
            for(int nb: nbors.get(start)) {
                if(nb == parent) continue;
                int rendundantEdge = dfs(nb, start);
                if(rendundantEdge > -1) return rendundantEdge;
            }
        }

        visitedInCurrDfs.remove(start);
        stack.pollFirst();
        return -1;
    }

    public static void main(String[] args) {
        int[][] edges = {{1,2},{1,3},{2,3}};
        System.out.println(Arrays.toString(
                new P10_RendundantConnection().findRedundantConnection(edges)
        ));
    }
}
