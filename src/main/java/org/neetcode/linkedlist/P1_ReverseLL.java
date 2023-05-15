package org.neetcode.linkedlist;

import java.util.ArrayList;
import java.util.List;

class P1_ReverseLL {

    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        ListNode temp;

        while(curr != null) {
            temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        return prev;
    }

    public static void main(String[] args) {
        ListNode ll = ListNode.build(new int[]{1,2,3,4,5});
        System.out.println(ListNode.print(new P1_ReverseLL()
                .reverseList(ll)
        ));
    }
}
