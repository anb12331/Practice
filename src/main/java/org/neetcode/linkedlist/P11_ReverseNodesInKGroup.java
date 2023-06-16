package org.neetcode.linkedlist;

class P11_ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(k == 1) return head;
        int count = 0;
        ListNode next = head;
        ListNode reversedHead = null;
        ListNode reversedTail = null;
        ListNode prevReversedHead = null;
        ListNode newHead = null;

        int totalCount = 0;

        while(next != null) {
            totalCount++;
            next = next.next;
        }
        int totalKParts = totalCount/k;

        int kParts = -1;
        next = head;
        while(next != null) {
            ListNode curr = next;
            if(count%k == 0) {
                if(count > 0 && newHead == null) {
                    newHead = reversedTail;
                }
                kParts++;

                if(kParts == totalKParts) {
                    if(reversedHead != null)
                        reversedHead.next = next;
                    break;
                }

                prevReversedHead = reversedHead;
                reversedHead = curr;
                reversedTail = curr;
                next = curr.next;
            } else {
                if(prevReversedHead != null)
                    prevReversedHead.next = curr;
                next = curr.next;
                curr.next = reversedTail;
                reversedTail = curr;
            }
            count++;
        }

        if(totalCount % k == 0) {
            reversedHead.next = null;
        }

        if(count > 0 && newHead == null) {
            newHead = reversedTail;
        } else if(count == 0) {
            newHead = head;
        }

        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        head = new P11_ReverseNodesInKGroup().reverseKGroup(head, 3);
        System.out.println(ListNode.print(head));
    }
}
