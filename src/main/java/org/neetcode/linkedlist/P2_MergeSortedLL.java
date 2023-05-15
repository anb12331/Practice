package org.neetcode.linkedlist;

class P2_MergeSortedLL {

    public ListNode mergeTwoLists(ListNode p1, ListNode p2) {
        ListNode p3 = new ListNode(-1);
        ListNode curr = p3;

        while(p1 != null || p2 != null) {
            if(p2 == null || (p1 != null && p1.val <= p2.val)) {
                curr.next = p1;
                p1 = p1.next;
            } else if(p1 == null || p1.val > p2.val) {
                curr.next = p2;
                p2 = p2.next;
            }
            curr = curr.next;
        }

        return p3.next;
    }

    public static void main(String[] args) {
        ListNode ll1 = ListNode.build(new int[]{1,2,4});
        ListNode ll2 = ListNode.build(new int[]{0, 2, 5});

        System.out.println(ListNode.print(new P2_MergeSortedLL()
                .mergeTwoLists(ll1, ll2)
        ));
    }
}
