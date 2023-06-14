package org.neetcode.linkedlist;

import java.util.Comparator;
import java.util.PriorityQueue;

class P10_MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        ListNode resHead = new ListNode();
        ListNode res = resHead;

        for(int i = 0; i < lists.length; i++) {
            ListNode firstElem = lists[i];
            if(firstElem != null) {
                minHeap.add(firstElem);
            }
        }

        while(!minHeap.isEmpty()) {
            ListNode lowest = minHeap.poll();
            res.next = lowest;
            res = res.next;
            if(lowest.next != null)
                minHeap.add(lowest.next);
        }

        return resHead.next;
    }

    public static void main(String[] args) {
        int[][] lists = {{1,4,5},{1,3,4},{2,6}};
        ListNode res = new P10_MergeKSortedLists().mergeKLists(extracted(lists));
        System.out.println(ListNode.print(res));
    }

    private static ListNode[] extracted(int[][] lists) {
        ListNode[] parsedLists = new ListNode[lists.length];
        for(int i = 0; i < lists.length; i++) {
            ListNode first = null;
            ListNode prev = null;
            for(int num: lists[i]) {
                if(prev == null) {
                    first = new ListNode(num);
                    prev = first;
                } else {
                    ListNode next = new ListNode(num);
                    prev.next = next;
                    prev = next;
                }
            }
            parsedLists[i] = first;
        }
        return parsedLists;
    }
}
