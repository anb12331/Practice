package org.neetcode.graphs2;

import java.util.*;

class P3_CheapestFlightsWithinKStops {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int maxAllowedStops) {
        List<int[]>[] nborMap = (ArrayList<int[]>[]) new ArrayList[n];
        int minTotalCost = -1;

        for(int i = 0; i < flights.length; i++) {
            int[] edge = flights[i];
            List<int[]> nbors = nborMap[edge[0]];
            if(nbors == null) {
                nbors = new ArrayList<>();
                nborMap[edge[0]] = nbors;
            }

            nbors.add(edge);
        }

//        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        Set<Integer> nodesReachableToDest = getVisitableNodes(flights, dst, n);

        if(!nodesReachableToDest.contains(src)) return -1;

        Deque<int[]> q = new ArrayDeque<>();
        q.addLast(new int[]{0, src, -1});

        while(!q.isEmpty()) {
            int[] popped = q.pollFirst();

            int stopsForNextDest = popped[2] + 1;
            List<int[]> nbors = nborMap[popped[1]];

            if (stopsForNextDest <= maxAllowedStops && nbors != null) {
                for (int[] nb : nbors) {
                    int cost = popped[0] + nb[2];
                    if (nb[1] == dst) {
                        if (minTotalCost == -1) minTotalCost = cost;
                        else minTotalCost = Math.min(minTotalCost, cost);
                    } else if(minTotalCost == -1 || cost < minTotalCost) {
                        if(nodesReachableToDest.contains(nb[1]))
                            q.addLast(new int[]{cost, nb[1], stopsForNextDest});
                    }
                }
            }

        }

        return minTotalCost;

    }

    private Set<Integer> getVisitableNodes(int[][] flights, int start, int n) {
        List<int[]>[] reverseNborMap = (ArrayList<int[]>[]) new ArrayList[n];

        for(int i = 0; i < flights.length; i++) {
            int[] edge = flights[i];
            List<int[]> nbors = reverseNborMap[edge[1]];
            if(nbors == null) {
                nbors = new ArrayList<>();
                reverseNborMap[edge[1]] = nbors;
            }

            nbors.add(edge);
        }

        Set<Integer> visited = new HashSet<>();

        Deque<Integer> q = new ArrayDeque<>();
        q.addLast(start);

        while(!q.isEmpty()) {
            Integer popped = q.pollFirst();
            if(visited.contains(popped)) continue;

            visited.add(popped);
            List<int[]> nbors = reverseNborMap[popped];
            if(nbors != null) {
                for(int[] nb: nbors) {
                    if(!visited.contains(nb[0])) {
                        q.addLast(nb[0]);
                    }
                }
            }
        }

        return visited;
    }

    public static void main(String[] args) {
        int[][] flights = {{0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}};
        int n = 4, src = 0, dst = 3, k = 1;

        flights = new int[][]{{0,1,100},{1,2,100},{0,2,500}};
        n = 3; src = 0; dst = 2; k = 0;

        flights = new int[][]{{0,1,1},{0,2,5},{1,2,1},{2,3,1}};
        n = 4; src = 0; dst = 3; k = 1;

        System.out.println(new P3_CheapestFlightsWithinKStops().findCheapestPrice(n, flights, src, dst, k));
    }

    public int findCheapestPrice_fasterSol_BellmanFord(int n, int[][] flights, int src, int dst, int k) {
        // initialize an array with max value of size n
        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);

        // price from source to source is always 0
        prices[src] = 0;

        for (int i = 0; i <= k; i++) {
            // make a copy of prices
            int[] temp = Arrays.copyOf(prices, prices.length);

            // loop through flights
            for (int j = 0; j < flights.length; j++) {
                int s = flights[j][0]; // from
                int d = flights[j][1]; // to
                int p = flights[j][2]; // price

                if (prices[s] == Integer.MAX_VALUE) {
                    continue;
                }

                if (prices[s] + p < temp[d]) {
                    temp[d] = prices[s] + p;
                }
            }

            // set prices to temp
            prices = temp;
        }

        if (prices[dst] != Integer.MAX_VALUE) {
            return prices[dst];
        }

        return -1;
    }
}
