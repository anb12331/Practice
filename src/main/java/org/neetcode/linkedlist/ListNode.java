package org.neetcode.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class ListNode {
    int val;
    ListNode next;
    ListNode prev;
    int val2;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public static ListNode build(int[] elems) {
        ListNode head = new ListNode(elems[0]);
        ListNode curr = head;

        for(int i = 1; i < elems.length; i++) {
            curr.next = new ListNode(elems[i]);
            curr = curr.next;
        }

        return head;
    }

    public static List<Integer> print(ListNode head) {
        List<Integer> res = new ArrayList<>();
        while(head != null) {
            res.add(head.val);
            head = head.next;
        }

        return res;
    }
}
