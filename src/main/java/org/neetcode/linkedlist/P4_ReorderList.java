package org.neetcode.linkedlist;

class P4_ReorderList {
    public void reorderList(ListNode head) {
        if(head == null) return;

        ListNode startP = head;

        ListNode endP = head;
        ListNode midP = head;

        while(endP != null && endP.next != null) {
            endP = endP.next.next;
            midP = midP.next;
        }

        ListNode temp, prev;
        prev = null;

        //reversing after midpoint
        while(midP != null) {
            temp = midP.next;
            midP.next = prev;
            prev = midP;
            midP = temp;
        }

        endP = prev; //list reversed

        ListNode curr = head;
        ListNode endTemp;

        //merge first half of list with reversed 2nd half of list
        while(endP != null && curr != null && curr.next != endP) {
            temp = curr.next;
            curr.next = endP;
            endTemp = endP.next;
            endP.next = temp;
            endP = endTemp;
            curr = temp;
        }
    }


    public static void main(String[] args) {
        ListNode head = ListNode.build(new int[]{1,2,3,4,5});
        new P4_ReorderList().reorderList(head);
        System.out.println(ListNode.print(head)
        );
    }
}
