package org.neetcode.graphs;

import java.util.*;

class P12_GraphAsValidTree {
    public boolean validTree_faster(int n, int[][] edges) {
        //using Union-Find forest algo - O(elog(v)) time - slower?
        //improved in realtime using rank, also log base is more than 2
        Map<Integer, Integer> parentMap = new HashMap<>();
        Map<Integer, Integer> ranks = new HashMap<>();

        for(int[] edge: edges) {
            int a = edge[0];
            int b = edge[1];

            int parentA = a;
            while(parentMap.containsKey(parentA)) {
                parentA = parentMap.get(parentA);
            }

            int parentB = b;
            while(parentMap.containsKey(parentB)) {
                parentB = parentMap.get(parentB);
            }

            if(parentA == parentB) {
                return false;
            } else {
                int rankA = ranks.getOrDefault(parentA, 1);
                int rankB = ranks.getOrDefault(parentB, 1);

                if(rankA >= rankB) {
                    parentMap.put(parentB, parentA);
                    ranks.put(parentA, rankA + 1);
                } else {
                    parentMap.put(parentA, parentB);
                    ranks.put(parentB, rankB + 1);
                }
            }
        }

        return true;
    }


    public boolean validTree_dfs(int n, int[][] edges) {
        //O(e + v) time
        for(int[] edge: edges) {
            int a = edge[0];
            int b = edge[1];

            List<Integer> adjA = adjMap.get(a);
            if(adjA == null) {
                adjA = new ArrayList<>();
                adjMap.put(a, adjA);
            }
            adjA.add(b);

            List<Integer> adjB = adjMap.get(b);
            if(adjB == null) {
                adjB = new ArrayList<>();
                adjMap.put(b, adjB);
            }
            adjB.add(a);
        }

        if(!dfs(edges[0][0], -1)) {
            return false;
        }

        return visited.size() == n;
    }

    Set<Integer> visited = new HashSet<>();

    private boolean dfs(int start, int prev) {
        if(visited.contains(start)) return false;
        visited.add(start);
        if(adjMap.containsKey(start)) {
            for(int nb: adjMap.get(start)) {
                if(nb == prev) continue;
                if(!dfs(nb, start)) {
                    return false;
                }
            }
        }

        return true;
    }

    Map<Integer, List<Integer>> adjMap = new HashMap<>();

    public static void main(String[] args) {
        int[][] edges = {{0,1},{0,2},{0,3},{1,4}}; int n = 5;
        System.out.println(new P12_GraphAsValidTree().validTree_dfs(n, edges));
        System.out.println(new P12_GraphAsValidTree().validTree_faster(n, edges));
    }
}
