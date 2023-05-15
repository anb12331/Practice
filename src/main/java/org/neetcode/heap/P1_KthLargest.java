package org.neetcode.heap;

import java.util.PriorityQueue;

class P1_KthLargest {
    public static void main(String[] args) {
        KthLargest k1 = new KthLargest(3, new int[] {4, 5, 8, 2});
        for(int i: new int[] {3,5,10,9,4}) {
            System.out.println(k1.add(i));
        }
    }
}

class KthLargest {
    PriorityQueue<Integer> pq;
    int k;
    public KthLargest(int k, int[] nums) {
        this.k = k;
        pq =  new PriorityQueue<>(k);
        for(int i: nums) {
            add(i);
        }
    }

    public int add(int val) {
        if(pq.size() < k) {
            pq.add(val);
        } else if(pq.size() >= k) {
            if (val > pq.peek()) {
                pq.add(val);
                pq.poll();
            }
        }
        return pq.peek();
    }
}
