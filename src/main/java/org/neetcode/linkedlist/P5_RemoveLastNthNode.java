package org.neetcode.linkedlist;

import org.neetcode.dynamic1d.P5_LongestPalindromeSubstr;

public class P5_RemoveLastNthNode {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) return head;
        int count = 1;
        ListNode countPtr = head;
        ListNode deletorPtr = new ListNode();
        deletorPtr.next = head;

        while(countPtr.next != null) {
            countPtr = countPtr.next;
            count++;
            if(count > n) {
                deletorPtr = deletorPtr.next;
            }
        }

        if(count == n) {
            head = deletorPtr.next.next;
        } else if(count > n) {
            deletorPtr.next = deletorPtr.next.next;
        }

        return head;

    }

    public static void main(String[] args) {
        ListNode head = ListNode.build(new int[] {1,2});
        head = new P5_RemoveLastNthNode().removeNthFromEnd(head,3);
        System.out.println(ListNode.print(head));
    }
}
