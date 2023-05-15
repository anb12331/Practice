package org.neetcode.linkedlist;

import java.util.HashSet;
import java.util.Set;

class P3_Cycles {
    public boolean hasCycle(ListNode head) {
        ListNode curr = head;
        ListNode fastPointer = head != null ? head.next : null;

        while(curr != null && fastPointer != null) {
            if(curr == fastPointer) {
                return true; //loop detected, both pointers meet
            } else {
                if(fastPointer.next != null) {
                    fastPointer = fastPointer.next.next;
                } else {
                    fastPointer = null;
                }
                curr = curr.next;

            }
        }

        return false;
    }

    public static void main(String[] args) {
        ListNode l = ListNode.build(new int[] {1});
//        l.next.next.next = l; //create cycle
        System.out.println(new P3_Cycles()
                .hasCycle(l)
        );
    }
}
