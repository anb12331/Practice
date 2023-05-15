package org.neetcode.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

class P2_LastStone {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(stones.length, (o1, o2) -> -o1.compareTo(o2));
        for(int i: stones) {
            pq.add(i);
        }

        while(pq.size() > 1) {
            Integer s1 = pq.poll();
            Integer s2 = pq.poll();
            if(s1 != s2) {
                pq.add(Math.abs(s1 - s2));
            }

        }

        return pq.isEmpty() ? 0: pq.peek();
    }

    public static void main(String[] args) {
        System.out.println(new P2_LastStone()
                .lastStoneWeight(new int[] {2,7,4,1,8,1})
        );
    }
}
