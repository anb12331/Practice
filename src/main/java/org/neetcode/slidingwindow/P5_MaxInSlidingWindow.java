package org.neetcode.slidingwindow;

import java.util.*;

class P5_MaxInSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        /*The aim is to maintain the items in window as a sorted array, add and removing elements while
          maintaining the sorted order. I used a heap for that, with O(nlog(k)) time
          Best Solution - Monotonic Decreasing Stack
          In best solution (mono-decr. stack), to maintain sorting, elements lower than newly added element are simply
          removed as the window max will always be the newly added element. Only elements lower than lowest
          element are kept in queue, thus automatically maintaining a decreasing sorted order
          Worst case time complexity is O(2n), as any element can be added and removed exactly once.
         *
         */
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.comparingInt(o->-o));
        int[] res = new int[nums.length - k + 1];

        for(int i = 0; i < k; i++) maxHeap.add(nums[i]);
        Map<Integer, Integer> removed = new HashMap<>();

        for(int i = 0; i < nums.length - k + 1; i++) {
            while(removed.containsKey(maxHeap.peek())) {
                int removedFromHeapInt = maxHeap.poll();
                removed.put(removedFromHeapInt, removed.get(removedFromHeapInt) - 1);
                if(removed.get(removedFromHeapInt) == 0) removed.remove(removedFromHeapInt);
            }
            res[i] = maxHeap.peek();
            if(i + k < nums.length) {
                if(nums[i + k] > maxHeap.peek())
                    maxHeap.clear();
                maxHeap.add(nums[i + k]);
            }
            removed.put(nums[i], removed.getOrDefault(nums[i], 0) + 1);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7}; int k = 3;
        System.out.println(Arrays.toString(
                new P5_MaxInSlidingWindow().maxSlidingWindow(nums, k)));
    }

    public int[] maxSlidingWindow_bestSol(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        int j = 0;
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (!q.isEmpty() && q.peekFirst() < i - k + 1) q.pollFirst();
            while (!q.isEmpty() && nums[i] > nums[q.peekLast()]) q.pollLast();
            q.offer(i);
            if (i >= k - 1) ans[j++] = nums[q.peekFirst()];
        }
        return ans;
    }

}
