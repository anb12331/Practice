package org.neetcode.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class P11_CountComponentsInUndirectedGraph {
    public int countComponents(int[][] edges) {
        Set<Integer> verts = new HashSet<>();
        Map<Integer, Integer> parentMap = new HashMap<>();
        Map<Integer, Integer> ranks = new HashMap<>();
        int componentMergeCount = 0;

        for(int i = 0; i < edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];

            verts.add(a);
            verts.add(b);

            int parentA =  a;
            while(parentMap.containsKey(parentA)) {
                parentA = parentMap.get(parentA);
            }

            int parentB =  b;
            while(parentMap.containsKey(parentB)) {
                parentB = parentMap.get(parentB);
            }

            if(parentA != parentB) {
                componentMergeCount++;
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

        return verts.size() - componentMergeCount;
    }

    public static void main(String[] args) {
        int[][] edges = {{1,2}, {0,1}, {3,4}, {4,5}, {4,0}, {3,2}};
        System.out.println(new P11_CountComponentsInUndirectedGraph().countComponents(edges));
    }
}
